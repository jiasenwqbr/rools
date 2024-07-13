package org.jeecg.modules.demo.dataobject.mapper;

import java.util.List;
import org.jeecg.modules.demo.dataobject.entity.RoolDataObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 字段
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
public interface RoolDataObjectMapper extends BaseMapper<RoolDataObject> {

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
   * @return List<RoolDataObject>
   */
	public List<RoolDataObject> selectByMainId(@Param("mainId") String mainId);
}
