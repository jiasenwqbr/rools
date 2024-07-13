package org.jeecg.modules.demo.dataobject.service;

import org.jeecg.modules.demo.dataobject.entity.RoolDataObjectCode;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 源代码
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface IRoolDataObjectCodeService extends IService<RoolDataObjectCode> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<RoolDataObjectCode>
	 */
	public List<RoolDataObjectCode> selectByMainId(String mainId);
}
