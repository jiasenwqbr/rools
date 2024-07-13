package org.jeecg.modules.demo.datadrlobject.service;

import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDrlObject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface IRoolDrlObjectService extends IService<RoolDrlObject> {

	/**
	 * 添加一对多
	 *
	 * @param roolDrlObject
	 * @param roolDataDrlCodeList
	 */
	public void saveMain(RoolDrlObject roolDrlObject,List<RoolDataDrlCode> roolDataDrlCodeList) ;
	
	/**
	 * 修改一对多
	 *
   * @param roolDrlObject
   * @param roolDataDrlCodeList
	 */
	public void updateMain(RoolDrlObject roolDrlObject,List<RoolDataDrlCode> roolDataDrlCodeList);
	
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
