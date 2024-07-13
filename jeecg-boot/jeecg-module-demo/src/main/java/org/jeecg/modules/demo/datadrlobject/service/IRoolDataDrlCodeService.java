package org.jeecg.modules.demo.datadrlobject.service;

import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface IRoolDataDrlCodeService extends IService<RoolDataDrlCode> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<RoolDataDrlCode>
	 */
	public List<RoolDataDrlCode> selectByMainId(String mainId);
}
