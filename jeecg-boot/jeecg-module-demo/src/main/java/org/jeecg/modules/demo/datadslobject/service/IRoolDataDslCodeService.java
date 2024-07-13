package org.jeecg.modules.demo.datadslobject.service;

import org.jeecg.modules.demo.datadslobject.entity.RoolDataDslCode;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: DSL规则文件
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface IRoolDataDslCodeService extends IService<RoolDataDslCode> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<RoolDataDslCode>
	 */
	public List<RoolDataDslCode> selectByMainId(String mainId);
}
