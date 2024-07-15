import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '空间',
    align: 'center',
    dataIndex: 'spaceId_dictText',
  },
  {
    title: '项目',
    align: 'center',
    dataIndex: 'projectId_dictText',
  },
  {
    title: '包名程',
    align: 'center',
    dataIndex: 'pkgName',
  },
  {
    title: '包扩展名',
    align: 'center',
    dataIndex: 'extends_dictText',
  },
  {
    title: '版本',
    align: 'center',
    dataIndex: 'version',
  },
  {
    title: '描述',
    align: 'center',
    dataIndex: 'description',
  },
  {
    title: '创建人',
    align: 'center',
    dataIndex: 'createBy',
  },
  {
    title: '创建时间',
    align: 'center',
    dataIndex: 'createTime',
  },
  {
    title: '修改人',
    align: 'center',
    dataIndex: 'updateBy',
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
    label: '空间',
    field: 'spaceId',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'rool_space,space_name,id',
    },
    //colProps: {span: 6},
  },
  {
    label: '项目',
    field: 'projectId',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'rool_project,project_name,id',
    },
    //colProps: {span: 6},
  },
  {
    label: '包扩展名',
    field: 'extend',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'extends',
    },
    //colProps: {span: 6},
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '空间',
    field: 'spaceId',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'rool_space,space_name,id',
    },
  },
  {
    label: '项目',
    field: 'projectId',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'rool_project,project_name,id',
    },
  },
  {
    label: '包名程',
    field: 'pkgName',
    component: 'Input',
  },
  {
    label: '包扩展名',
    field: 'extend',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'extends',
    },
  },
  {
    label: '版本',
    field: 'version',
    component: 'Input',
  },
  {
    label: '描述',
    field: 'description',
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
  spaceId: { title: '空间', order: 0, view: 'list', type: 'string', dictTable: 'rool_space', dictCode: 'id', dictText: 'space_name' },
  projectId: { title: '项目', order: 1, view: 'list', type: 'string', dictTable: 'rool_project', dictCode: 'id', dictText: 'project_name' },
  pkgName: { title: '包名程', order: 2, view: 'text', type: 'string' },
  extend: { title: '包扩展名', order: 3, view: 'list', type: 'string', dictCode: 'extends' },
  version: { title: '版本', order: 4, view: 'text', type: 'string' },
  description: { title: '描述', order: 5, view: 'textarea', type: 'string' },
  createBy: { title: '创建人', order: 6, view: 'text', type: 'string' },
  createTime: { title: '创建时间', order: 7, view: 'datetime', type: 'string' },
  updateBy: { title: '修改人', order: 8, view: 'text', type: 'string' },
  updateTime: { title: '修改时间', order: 9, view: 'datetime', type: 'string' },
};

/**
 * 流程表单调用这个方法获取formSchema
 * @param param
 */
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
