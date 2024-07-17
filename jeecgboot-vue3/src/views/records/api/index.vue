<template>
  <BasicTable :ellipsis="true" @register="registerTable" :searchInfo="searchInfo" :columns="logColumns" :expand-column-width="16">
    <template #tableTitle>
      <a-tabs defaultActiveKey="2" @change="tabChange" size="small">
        <a-tab-pane tab="调用日志" key="2" />
      </a-tabs>
    </template>
    <template #expandedRowRender="{ record }">
      <div v-if="searchInfo.logType == 2">
        <div style="margin-bottom: 5px">
          <a-badge status="success" style="vertical-align: middle" />
          <span style="vertical-align: middle">请求方法:{{ record.method }}</span></div
        >
        <div>
          <a-badge status="processing" style="vertical-align: middle" />
          <span style="vertical-align: middle">请求参数:{{ record.requestParam }}</span></div
        >
      </div>
    </template>
  </BasicTable>
</template>
<script lang="ts" name="monitor-log" setup>
  import { ref } from 'vue';
  import { BasicTable } from '/@/components/Table';
  import { getLogList } from './log.api';
  import { columns, searchFormSchema, operationLogColumn, operationSearchFormSchema, exceptionColumns } from './log.data';
  import { useListPage } from '/@/hooks/system/useListPage';
  // const checkedKeys = ref<Array<string | number>>([]);

  const logColumns = ref<any>(columns);
  const searchSchema = ref<any>(searchFormSchema);
  const searchInfo = { logType: '2' };
  // 列表页面公共参数、方法
  const { tableContext } = useListPage({
    designScope: 'user-list',
    tableProps: {
      title: '日志列表',
      api: getLogList,
      expandRowByClick: true,
      showActionColumn: false,
      rowSelection: {
        columnWidth: 20,
      },
      formConfig: {
        schemas: searchSchema,
        fieldMapToTime: [['fieldTime', ['createTime_begin', 'createTime_end'], 'YYYY-MM-DD']],
      },
    },
  });

  const [registerTable, { reload }] = tableContext;

  // 日志类型
  function tabChange(key) {
    searchInfo.logType = key;
    //update-begin---author:wangshuai ---date:20220506  for：[VUEN-943]vue3日志管理列表翻译不对------------
    if (key == '2') {
      logColumns.value = operationLogColumn;
      searchSchema.value = operationSearchFormSchema;
    } else if (key == '4') {
      searchSchema.value = searchFormSchema;
      logColumns.value = exceptionColumns;
    } else {
      searchSchema.value = searchFormSchema;
      logColumns.value = columns;
    }
    //update-end---author:wangshuai ---date:20220506  for：[VUEN-943]vue3日志管理列表翻译不对--------------
    reload();
  }

  /**
   * 选择事件
   */
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  // function onSelectChange(selectedRowKeys: (string | number)[]) {
  //   checkedKeys.value = selectedRowKeys;
  // }
</script>
<style lang="less" scoped>
  .error-box {
    white-space: break-spaces;
  }
</style>
