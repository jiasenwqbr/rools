package org.jeecg.modules.demo.dataobject.service;

import org.jeecg.modules.demo.dataobject.entity.RoolDataObject;
import org.jeecg.modules.demo.dataobject.entity.RoolDataObjectCode;
import org.jeecg.modules.demo.dataobject.entity.RoolProjectDataObject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 项目对象
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface IRoolProjectDataObjectService extends IService<RoolProjectDataObject> {

	/**
	 * 添加一对多
	 *
	 * @param roolProjectDataObject
	 * @param roolDataObjectList
	 * @param roolDataObjectCodeList
	 */
	public void saveMain(RoolProjectDataObject roolProjectDataObject,List<RoolDataObject> roolDataObjectList,List<RoolDataObjectCode> roolDataObjectCodeList) ;
	
	/**
	 * 修改一对多
	 *
   * @param roolProjectDataObject
   * @param roolDataObjectList
   * @param roolDataObjectCodeList
	 */
	public void updateMain(RoolProjectDataObject roolProjectDataObject,List<RoolDataObject> roolDataObjectList,List<RoolDataObjectCode> roolDataObjectCodeList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
