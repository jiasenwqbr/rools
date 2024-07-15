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
    dataIndex: 'templeateName'
   },
   {
    title: '功能',
    align:"center",
    dataIndex: 'functions_dictText'
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
      field: 'templeateName',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'templeateName',
    component: 'Input',
  },
  {
    label: '功能',
    field: 'functions',
    component: 'JCheckbox',
    componentProps:{
        dictCode:"functions"
     },
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
  templeateName: {title: '名称',order: 0,view: 'text', type: 'string',},
  functions: {title: '功能',order: 1,view: 'checkbox', type: 'string',dictCode: 'functions',},
  createBy: {title: '创建人',order: 3,view: 'text', type: 'string',},
  createTime: {title: '创建时间',order: 4,view: 'datetime', type: 'string',},
  updateBy: {title: '修改人',order: 5,view: 'text', type: 'string',},
  updateTime: {title: '修改时间',order: 6,view: 'datetime', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}