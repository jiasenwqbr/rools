import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '包名',
    align:"center",
    dataIndex: 'packageId_dictText'
   },
   {
    title: '服务器',
    align:"center",
    dataIndex: 'serverId_dictText'
   },
   {
    title: '容器',
    align:"center",
    dataIndex: 'containerId_dictText'
   },
   {
    title: '描述',
    align:"center",
    dataIndex: 'description'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '包名',
    field: 'packageId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"rool_build_jar,pkg_name,id"
     },
  },
  {
    label: '服务器',
    field: 'serverId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"rool_server_template,templeate_name,id"
     },
  },
  {
    label: '容器',
    field: 'containerId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"rool_container,container_id,id"
     },
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
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  packageId: {title: '包名',order: 0,view: 'list', type: 'string',dictTable: "rool_build_jar", dictCode: 'id', dictText: 'pkg_name',},
  serverId: {title: '服务器',order: 1,view: 'list', type: 'string',dictTable: "rool_server_template", dictCode: 'id', dictText: 'templeate_name',},
  containerId: {title: '容器',order: 2,view: 'list', type: 'string',dictTable: "rool_container", dictCode: 'id', dictText: 'container_id',},
  description: {title: '描述',order: 3,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}