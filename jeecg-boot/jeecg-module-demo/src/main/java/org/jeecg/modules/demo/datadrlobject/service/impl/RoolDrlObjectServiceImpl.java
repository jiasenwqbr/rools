package org.jeecg.modules.demo.datadrlobject.service.impl;

import org.jeecg.modules.demo.datadrlobject.entity.RoolDrlObject;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import org.jeecg.modules.demo.datadrlobject.mapper.RoolDataDrlCodeMapper;
import org.jeecg.modules.demo.datadrlobject.mapper.RoolDrlObjectMapper;
import org.jeecg.modules.demo.datadrlobject.service.IRoolDrlObjectService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Service
public class RoolDrlObjectServiceImpl extends ServiceImpl<RoolDrlObjectMapper, RoolDrlObject> implements IRoolDrlObjectService {

	@Autowired
	private RoolDrlObjectMapper roolDrlObjectMapper;
	@Autowired
	private RoolDataDrlCodeMapper roolDataDrlCodeMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(RoolDrlObject roolDrlObject, List<RoolDataDrlCode> roolDataDrlCodeList) {
		roolDrlObjectMapper.insert(roolDrlObject);
		if(roolDataDrlCodeList!=null && roolDataDrlCodeList.size()>0) {
			for(RoolDataDrlCode entity:roolDataDrlCodeList) {
				//外键设置
				entity.setProjectDataId(roolDrlObject.getId());
				roolDataDrlCodeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(RoolDrlObject roolDrlObject,List<RoolDataDrlCode> roolDataDrlCodeList) {
		roolDrlObjectMapper.updateById(roolDrlObject);
		
		//1.先删除子表数据
		roolDataDrlCodeMapper.deleteByMainId(roolDrlObject.getId());
		
		//2.子表数据重新插入
		if(roolDataDrlCodeList!=null && roolDataDrlCodeList.size()>0) {
			for(RoolDataDrlCode entity:roolDataDrlCodeList) {
				//外键设置
				entity.setProjectDataId(roolDrlObject.getId());
				roolDataDrlCodeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		roolDataDrlCodeMapper.deleteByMainId(id);
		roolDrlObjectMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			roolDataDrlCodeMapper.deleteByMainId(id.toString());
			roolDrlObjectMapper.deleteById(id);
		}
	}
	
}
