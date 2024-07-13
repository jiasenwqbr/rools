package org.jeecg.modules.demo.dataobject.service.impl;

import org.jeecg.modules.demo.dataobject.entity.RoolProjectDataObject;
import org.jeecg.modules.demo.dataobject.entity.RoolDataObject;
import org.jeecg.modules.demo.dataobject.entity.RoolDataObjectCode;
import org.jeecg.modules.demo.dataobject.mapper.RoolDataObjectMapper;
import org.jeecg.modules.demo.dataobject.mapper.RoolDataObjectCodeMapper;
import org.jeecg.modules.demo.dataobject.mapper.RoolProjectDataObjectMapper;
import org.jeecg.modules.demo.dataobject.service.IRoolProjectDataObjectService;
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
public class RoolProjectDataObjectServiceImpl extends ServiceImpl<RoolProjectDataObjectMapper, RoolProjectDataObject> implements IRoolProjectDataObjectService {

	@Autowired
	private RoolProjectDataObjectMapper roolProjectDataObjectMapper;
	@Autowired
	private RoolDataObjectMapper roolDataObjectMapper;
	@Autowired
	private RoolDataObjectCodeMapper roolDataObjectCodeMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(RoolProjectDataObject roolProjectDataObject, List<RoolDataObject> roolDataObjectList,List<RoolDataObjectCode> roolDataObjectCodeList) {
		roolProjectDataObjectMapper.insert(roolProjectDataObject);
		if(roolDataObjectList!=null && roolDataObjectList.size()>0) {
			for(RoolDataObject entity:roolDataObjectList) {
				//外键设置
				entity.setProjectDataId(roolProjectDataObject.getId());
				roolDataObjectMapper.insert(entity);
			}
		}
		if(roolDataObjectCodeList!=null && roolDataObjectCodeList.size()>0) {
			for(RoolDataObjectCode entity:roolDataObjectCodeList) {
				//外键设置
				entity.setProjectDataId(roolProjectDataObject.getId());
				roolDataObjectCodeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(RoolProjectDataObject roolProjectDataObject,List<RoolDataObject> roolDataObjectList,List<RoolDataObjectCode> roolDataObjectCodeList) {
		roolProjectDataObjectMapper.updateById(roolProjectDataObject);
		
		//1.先删除子表数据
		roolDataObjectMapper.deleteByMainId(roolProjectDataObject.getId());
		roolDataObjectCodeMapper.deleteByMainId(roolProjectDataObject.getId());
		
		//2.子表数据重新插入
		if(roolDataObjectList!=null && roolDataObjectList.size()>0) {
			for(RoolDataObject entity:roolDataObjectList) {
				//外键设置
				entity.setProjectDataId(roolProjectDataObject.getId());
				roolDataObjectMapper.insert(entity);
			}
		}
		if(roolDataObjectCodeList!=null && roolDataObjectCodeList.size()>0) {
			for(RoolDataObjectCode entity:roolDataObjectCodeList) {
				//外键设置
				entity.setProjectDataId(roolProjectDataObject.getId());
				roolDataObjectCodeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		roolDataObjectMapper.deleteByMainId(id);
		roolDataObjectCodeMapper.deleteByMainId(id);
		roolProjectDataObjectMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			roolDataObjectMapper.deleteByMainId(id.toString());
			roolDataObjectCodeMapper.deleteByMainId(id.toString());
			roolProjectDataObjectMapper.deleteById(id);
		}
	}
	
}
