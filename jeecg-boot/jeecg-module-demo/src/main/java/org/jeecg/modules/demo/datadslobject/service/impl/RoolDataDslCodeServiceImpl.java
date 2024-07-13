package org.jeecg.modules.demo.datadslobject.service.impl;

import org.jeecg.modules.demo.datadslobject.entity.RoolDataDslCode;
import org.jeecg.modules.demo.datadslobject.mapper.RoolDataDslCodeMapper;
import org.jeecg.modules.demo.datadslobject.service.IRoolDataDslCodeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: DSL规则文件
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Service
public class RoolDataDslCodeServiceImpl extends ServiceImpl<RoolDataDslCodeMapper, RoolDataDslCode> implements IRoolDataDslCodeService {
	
	@Autowired
	private RoolDataDslCodeMapper roolDataDslCodeMapper;
	
	@Override
	public List<RoolDataDslCode> selectByMainId(String mainId) {
		return roolDataDslCodeMapper.selectByMainId(mainId);
	}
}
