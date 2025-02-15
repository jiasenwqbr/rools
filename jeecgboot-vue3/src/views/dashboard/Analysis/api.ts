import { defHttp } from '/@/utils/http/axios';

enum Api {
  loginfo = '/sys/loginfo',
  visitInfo = '/sys/visitInfo',
  groupCardData = '/dashboard/getGroupCardData',
}
/**
 * 日志统计信息
 * @param params
 */
export const getLoginfo = (params) => defHttp.get({ url: Api.loginfo, params }, { isTransformResponse: false });
/**
 * 访问量信息
 * @param params
 */
export const getVisitInfo = (params) => defHttp.get({ url: Api.visitInfo, params }, { isTransformResponse: false });

/**
 * card group
 */

export const getGroupCardData = () => defHttp.get({ url: Api.groupCardData }, { isTransformResponse: false });
