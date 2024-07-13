package org.jeecg.modules.demo.datadslobject.service.impl;

import org.jeecg.modules.demo.datadslobject.entity.RoolDslObject;
import org.jeecg.modules.demo.datadslobject.entity.RoolDataDslCode;
import org.jeecg.modules.demo.datadslobject.mapper.RoolDataDslCodeMapper;
import org.jeecg.modules.demo.datadslobject.mapper.RoolDslObjectMapper;
import org.jeecg.modules.demo.datadslobject.service.IRoolDslObjectService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 项目对象
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Service
public class RoolDslObjectServiceImpl extends ServiceImpl<RoolDslObjectMapper, RoolDslObject> implements IRoolDslObjectService {

	@Autowired
	private RoolDslObjectMapper roolDslObjectMapper;
	@Autowired
	private RoolDataDslCodeMapper roolDataDslCodeMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(RoolDslObject roolDslObject, List<RoolDataDslCode> roolDataDslCodeList) {
		roolDslObjectMapper.insert(roolDslObject);
		if(roolDataDslCodeList!=null && roolDataDslCodeList.size()>0) {
			for(RoolDataDslCode entity:roolDataDslCodeList) {
				//外键设置
				entity.setProjectDataId(roolDslObject.getId());
				roolDataDslCodeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(RoolDslObject roolDslObject,List<RoolDataDslCode> roolDataDslCodeList) {
		roolDslObjectMapper.updateById(roolDslObject);
		
		//1.先删除子表数据
		roolDataDslCodeMapper.deleteByMainId(roolDslObject.getId());
		
		//2.子表数据重新插入
		if(roolDataDslCodeList!=null && roolDataDslCodeList.size()>0) {
			for(RoolDataDslCode entity:roolDataDslCodeList) {
				//外键设置
				entity.setProjectDataId(roolDslObject.getId());
				roolDataDslCodeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		roolDataDslCodeMapper.deleteByMainId(id);
		roolDslObjectMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			roolDataDslCodeMapper.deleteByMainId(id.toString());
			roolDslObjectMapper.deleteById(id);
		}
	}
	
}
