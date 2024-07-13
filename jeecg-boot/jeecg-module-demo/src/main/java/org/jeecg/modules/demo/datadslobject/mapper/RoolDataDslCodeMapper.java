package org.jeecg.modules.demo.datadslobject.mapper;

import java.util.List;
import org.jeecg.modules.demo.datadslobject.entity.RoolDataDslCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: DSL规则文件
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface RoolDataDslCodeMapper extends BaseMapper<RoolDataDslCode> {

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
   * @return List<RoolDataDslCode>
   */
	public List<RoolDataDslCode> selectByMainId(@Param("mainId") String mainId);
}
