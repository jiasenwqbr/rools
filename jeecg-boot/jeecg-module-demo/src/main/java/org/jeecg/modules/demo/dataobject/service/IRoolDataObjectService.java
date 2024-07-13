package org.jeecg.modules.demo.dataobject.service;

import org.jeecg.modules.demo.dataobject.entity.RoolDataObject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 字段
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface IRoolDataObjectService extends IService<RoolDataObject> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<RoolDataObject>
	 */
	public List<RoolDataObject> selectByMainId(String mainId);
}
