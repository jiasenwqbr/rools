package org.jeecg.modules.demo.datadrlobject.mapper;

import java.util.List;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface RoolDataDrlCodeMapper extends BaseMapper<RoolDataDrlCode> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<RoolDataDrlCode>
   */
	public List<RoolDataDrlCode> selectByMainId(@Param("mainId") String mainId);
}
