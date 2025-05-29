package com.javacore.repository.impl;

import java.util.List;

import com.javacore.constant.SystemConstant;
import com.javacore.repository.BuildingRentTypeRespository;
import com.javacore.repository.entity.BuildingRentTypeEntity;

public class BuildingRentTypeRespositoryImpl extends SimpleRepository<BuildingRentTypeEntity> implements BuildingRentTypeRespository{

	@Override
	public List<BuildingRentTypeEntity> findType(Long buildingId) {
		String sql = "select b.buildingid, r.name from buildingrenttype b join renttype r on b.renttypeid = r.id "+SystemConstant.ONE_EQUAL_ONE+"";
		if(buildingId!=null) {
			sql += " and b.buildingid = "+buildingId;
		}
		return findByCondition(sql);
	}
	
}
