package org.jeecg.modules.demo.datadrlobject.service.impl;

import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import org.jeecg.modules.demo.datadrlobject.mapper.RoolDataDrlCodeMapper;
import org.jeecg.modules.demo.datadrlobject.service.IRoolDataDrlCodeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Service
public class RoolDataDrlCodeServiceImpl extends ServiceImpl<RoolDataDrlCodeMapper, RoolDataDrlCode> implements IRoolDataDrlCodeService {
	
	@Autowired
	private RoolDataDrlCodeMapper roolDataDrlCodeMapper;
	
	@Override
	public List<RoolDataDrlCode> selectByMainId(String mainId) {
		return roolDataDrlCodeMapper.selectByMainId(mainId);
	}
}
