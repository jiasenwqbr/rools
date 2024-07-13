package org.jeecg.modules.demo.dataobject.service.impl;

import org.jeecg.modules.demo.dataobject.entity.RoolDataObjectCode;
import org.jeecg.modules.demo.dataobject.mapper.RoolDataObjectCodeMapper;
import org.jeecg.modules.demo.dataobject.service.IRoolDataObjectCodeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 源代码
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Service
public class RoolDataObjectCodeServiceImpl extends ServiceImpl<RoolDataObjectCodeMapper, RoolDataObjectCode> implements IRoolDataObjectCodeService {
	
	@Autowired
	private RoolDataObjectCodeMapper roolDataObjectCodeMapper;
	
	@Override
	public List<RoolDataObjectCode> selectByMainId(String mainId) {
		return roolDataObjectCodeMapper.selectByMainId(mainId);
	}
}
