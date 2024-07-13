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
    title: '对象名称',
    align: 'center',
    dataIndex: 'objectName',
  },
  {
    title: '对象类型',
    align: 'center',
    dataIndex: 'objectType_dictText',
  },
  {
    title: '软件包',
    align: 'center',
    dataIndex: 'pkg',
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
    label: '对象名称',
    field: 'objectName',
    component: 'Input',
    //colProps: {span: 6},
  },
  {
    label: '对象类型',
    field: 'objectType',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'object_data_type',
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
    label: '对象名称',
    field: 'objectName',
    component: 'Input',
  },
  {
    label: '对象类型',
    field: 'objectType',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'object_data_type',
    },
  },
  {
    label: '软件包',
    field: 'pkg',
    component: 'Input',
  },
  {
    label: '描述',
    field: 'description',
    component: 'Input',
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
  objectName: { title: '对象名称', order: 2, view: 'text', type: 'string' },
  objectType: { title: '对象类型', order: 3, view: 'list', type: 'string', dictCode: 'object_data_type' },
  pkg: { title: '软件包', order: 4, view: 'text', type: 'string' },
  description: { title: '描述', order: 5, view: 'text', type: 'string' },
  createBy: { title: '创建人', order: 6, view: 'text', type: 'string' },
  createTime: { title: '创建时间', order: 7, view: 'datetime', type: 'string' },
};

/**
 * 流程表单调用这个方法获取formSchema
 * @param param
 */
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
