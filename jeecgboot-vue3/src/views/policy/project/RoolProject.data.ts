import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '项目名称',
    align:"center",
    dataIndex: 'projectName'
   },
   {
    title: '描述',
    align:"center",
    dataIndex: 'description'
   },
   {
    title: 'Group ID',
    align:"center",
    dataIndex: 'groupId'
   },
   {
    title: 'Artifact ID',
    align:"center",
    dataIndex: 'artifactId'
   },
   {
    title: 'Version',
    align:"center",
    dataIndex: 'version'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "项目名称",
      field: 'projectName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "Group ID",
      field: 'groupId',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "Artifact ID",
      field: 'artifactId',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "Version",
      field: 'version',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '项目名称',
    field: 'projectName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入项目名称!'},
          ];
     },
  },
  {
    label: '描述',
    field: 'description',
    component: 'InputTextArea',
  },
  {
    label: 'Group ID',
    field: 'groupId',
    component: 'Input',
  },
  {
    label: 'Artifact ID',
    field: 'artifactId',
    component: 'Input',
  },
  {
    label: 'Version',
    field: 'version',
    component: 'Input',
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
  projectName: {title: '项目名称',order: 0,view: 'text', type: 'string',},
  description: {title: '描述',order: 1,view: 'textarea', type: 'string',},
  groupId: {title: 'Group ID',order: 2,view: 'text', type: 'string',},
  artifactId: {title: 'Artifact ID',order: 3,view: 'text', type: 'string',},
  version: {title: 'Version',order: 4,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}