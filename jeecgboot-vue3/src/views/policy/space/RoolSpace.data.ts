import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '名称',
    align: 'center',
    dataIndex: 'spaceName',
  },
  {
    title: '创建人',
    align: 'center',
    dataIndex: 'contributors',
  },
  {
    title: '详细说明',
    align: 'center',
    dataIndex: 'remark',
  },
  {
    title: '创建时间',
    align: 'center',
    dataIndex: 'createTime',
  },
  {
    title: '修改时间',
    align: 'center',
    dataIndex: 'updateTime',
  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'spaceName',
    component: 'Input',
    //colProps: {span: 6},
  },
  {
    label: '创建人',
    field: 'contributors',
    component: 'Input',
    //colProps: {span: 6},
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'spaceName',
    component: 'Input',
  },
  {
    label: '创建人',
    field: 'contributors',
    component: 'Input',
  },
  {
    label: '详细说明',
    field: 'remark',
    component: 'InputTextArea',
  },

  // TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];

// 高级查询数据
export const superQuerySchema = {
  spaceName: { title: '名称', order: 0, view: 'text', type: 'string' },
  contributors: { title: '创建人', order: 1, view: 'text', type: 'string' },
  remark: { title: '详细说明', order: 2, view: 'textarea', type: 'string' },
};

/**
 * 流程表单调用这个方法获取formSchema
 * @param param
 */
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
