<template>
  <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :width="896" @ok="handleSubmit">
    <BasicForm @register="registerForm" ref="formRef" />
    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" animated @change="handleChangeTabs">
      <a-tab-pane tab="字段" key="roolDataObject" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="roolDataObject"
          :loading="roolDataObjectTable.loading"
          :columns="roolDataObjectTable.columns"
          :dataSource="roolDataObjectTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
        />
      </a-tab-pane>
      <a-tab-pane tab="源代码" key="roolDataObjectCode" :forceRender="true">
        <RoolDataObjectCodeForm ref="roolDataObjectCodeForm" :disabled="formDisabled" />
      </a-tab-pane>
    </a-tabs>
  </BasicModal>
</template>

<script lang="ts" setup>
  import { ref, computed, unref, reactive } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { JVxeTable } from '/@/components/jeecg/JVxeTable';
  import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts';
  import RoolDataObjectCodeForm from './RoolDataObjectCodeForm.vue';
  import { formSchema, roolDataObjectColumns } from '../RoolProjectDataObject.data';
  import { saveOrUpdate, roolDataObjectList, roolDataObjectCodeList } from '../RoolProjectDataObject.api';
  import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils';
  // Emits声明
  const emit = defineEmits(['register', 'success']);
  const isUpdate = ref(true);
  const formDisabled = ref(false);
  const refKeys = ref(['roolDataObject', 'roolDataObjectCode']);
  const activeKey = ref('roolDataObject');
  const roolDataObject = ref();
  const roolDataObjectCodeForm = ref();
  const tableRefs = { roolDataObject };
  const roolDataObjectTable = reactive({
    loading: false,
    dataSource: [],
    columns: roolDataObjectColumns,
  });
  //表单配置
  const [registerForm, { setProps, resetFields, setFieldsValue, validate }] = useForm({
    //labelWidth: 150,
    schemas: formSchema,
    showActionButtonGroup: false,
    baseColProps: { span: 12 },
  });
  //表单赋值
  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    //重置表单
    await reset();
    setModalProps({ confirmLoading: false, showCancelBtn: data?.showFooter, showOkBtn: data?.showFooter });
    isUpdate.value = !!data?.isUpdate;
    formDisabled.value = !data?.showFooter;
    if (unref(isUpdate)) {
      //表单赋值
      await setFieldsValue({
        ...data.record,
      });
      roolDataObjectCodeForm.value.initFormData(roolDataObjectCodeList, data?.record?.id);
      requestSubTableData(roolDataObjectList, { id: data?.record?.id }, roolDataObjectTable);
    }
    // 隐藏底部时禁用整个表单
    setProps({ disabled: !data?.showFooter });
  });
  //方法配置
  const [handleChangeTabs, handleSubmit, requestSubTableData, formRef] = useJvxeMethod(
    requestAddOrEdit,
    classifyIntoFormData,
    tableRefs,
    activeKey,
    refKeys,
    validateSubForm
  );

  //设置标题
  const title = computed(() => (!unref(isUpdate) ? '新增' : !unref(formDisabled) ? '编辑' : '详情'));

  async function reset() {
    await resetFields();
    activeKey.value = 'roolDataObject';
    roolDataObjectTable.dataSource = [];
    roolDataObjectCodeForm.value.resetFields();
  }
  function classifyIntoFormData(allValues) {
    let main = Object.assign({}, allValues.formValue);
    return {
      ...main, // 展开
      roolDataObjectList: allValues.tablesValue[0].tableData,
      roolDataObjectCodeList: roolDataObjectCodeForm.value.getFormData(),
    };
  }
  //校验所有一对一子表表单
  function validateSubForm(allValues) {
    return new Promise((resolve, reject) => {
      Promise.all([roolDataObjectCodeForm.value.validateForm(1)])
        .then(() => {
          resolve(allValues);
        })
        .catch((e) => {
          if (e.error === VALIDATE_FAILED) {
            // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
            activeKey.value = e.index == null ? unref(activeKey) : refKeys.value[e.index];
          } else {
            console.error(e);
          }
        });
    });
  }
  //表单提交事件
  async function requestAddOrEdit(values) {
    try {
      setModalProps({ confirmLoading: true });
      //提交表单
      await saveOrUpdate(values, isUpdate.value);
      //关闭弹窗
      closeModal();
      //刷新列表
      emit('success');
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }
</script>

<style lang="less" scoped>
  /** 时间和数字输入框样式 */
  :deep(.ant-input-number) {
    width: 100%;
  }

  :deep(.ant-calendar-picker) {
    width: 100%;
  }
</style>
