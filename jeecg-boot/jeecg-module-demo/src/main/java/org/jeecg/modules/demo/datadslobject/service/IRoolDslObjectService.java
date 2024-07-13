package org.jeecg.modules.demo.datadslobject.service;

import org.jeecg.modules.demo.datadslobject.entity.RoolDataDslCode;
import org.jeecg.modules.demo.datadslobject.entity.RoolDslObject;
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
public interface IRoolDslObjectService extends IService<RoolDslObject> {

	/**
	 * 添加一对多
	 *
	 * @param roolDslObject
	 * @param roolDataDslCodeList
	 */
	public void saveMain(RoolDslObject roolDslObject,List<RoolDataDslCode> roolDataDslCodeList) ;
	
	/**
	 * 修改一对多
	 *
   * @param roolDslObject
   * @param roolDataDslCodeList
	 */
	public void updateMain(RoolDslObject roolDslObject,List<RoolDataDslCode> roolDataDslCodeList);
	
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
