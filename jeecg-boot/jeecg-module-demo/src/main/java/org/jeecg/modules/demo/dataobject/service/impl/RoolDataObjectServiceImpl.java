package org.jeecg.modules.demo.dataobject.service.impl;

import org.jeecg.modules.demo.dataobject.entity.RoolDataObject;
import org.jeecg.modules.demo.dataobject.mapper.RoolDataObjectMapper;
import org.jeecg.modules.demo.dataobject.service.IRoolDataObjectService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 字段
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Service
public class RoolDataObjectServiceImpl extends ServiceImpl<RoolDataObjectMapper, RoolDataObject> implements IRoolDataObjectService {
	
	@Autowired
	private RoolDataObjectMapper roolDataObjectMapper;
	
	@Override
	public List<RoolDataObject> selectByMainId(String mainId) {
		return roolDataObjectMapper.selectByMainId(mainId);
	}
}
