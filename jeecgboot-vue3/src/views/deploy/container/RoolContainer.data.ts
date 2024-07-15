import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '名称',
    align:"center",
    dataIndex: 'containerName'
   },
   {
    title: '组名称',
    align:"center",
    dataIndex: 'groupName'
   },
   {
    title: '构建ID',
    align:"center",
    dataIndex: 'componentId'
   },
   {
    title: '版本',
    align:"center",
    dataIndex: 'version'
   },
   {
    title: '创建人',
    align:"center",
    dataIndex: 'createBy'
   },
   {
    title: '创建时间',
    align:"center",
    dataIndex: 'createTime'
   },
   {
    title: '修改人',
    align:"center",
    dataIndex: 'updateBy'
   },
   {
    title: '修改时间',
    align:"center",
    dataIndex: 'updateTime'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "名称",
      field: 'containerName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "组名称",
      field: 'groupName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "构建ID",
      field: 'componentId',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "版本",
      field: 'version',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'containerName',
    component: 'Input',
  },
  {
    label: '组名称',
    field: 'groupName',
    component: 'Input',
  },
  {
    label: '构建ID',
    field: 'componentId',
    component: 'Input',
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
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  containerName: {title: '名称',order: 0,view: 'text', type: 'string',},
  groupName: {title: '组名称',order: 1,view: 'text', type: 'string',},
  componentId: {title: '构建ID',order: 2,view: 'text', type: 'string',},
  version: {title: '版本',order: 3,view: 'text', type: 'string',},
  createBy: {title: '创建人',order: 5,view: 'text', type: 'string',},
  createTime: {title: '创建时间',order: 6,view: 'datetime', type: 'string',},
  updateBy: {title: '修改人',order: 7,view: 'text', type: 'string',},
  updateTime: {title: '修改时间',order: 8,view: 'datetime', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}