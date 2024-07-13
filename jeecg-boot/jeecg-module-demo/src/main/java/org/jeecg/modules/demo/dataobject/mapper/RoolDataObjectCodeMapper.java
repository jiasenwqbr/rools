package org.jeecg.modules.demo.dataobject.mapper;

import java.util.List;
import org.jeecg.modules.demo.dataobject.entity.RoolDataObjectCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 源代码
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface RoolDataObjectCodeMapper extends BaseMapper<RoolDataObjectCode> {

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
   * @return List<RoolDataObjectCode>
   */
	public List<RoolDataObjectCode> selectByMainId(@Param("mainId") String mainId);
}
