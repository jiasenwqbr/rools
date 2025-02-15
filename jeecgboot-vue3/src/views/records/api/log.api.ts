import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/sys/record/apiLog/list',
}

/**
 * 查询日志列表
 * @param params
 */
export const getLogList = (params) => {
  return defHttp.get({ url: Api.list, params });
};
