package com.javacore.repository.impl;

import java.util.List;

import com.javacore.constant.SystemConstant;
import com.javacore.repository.RentAreaRespository;
import com.javacore.repository.entity.RentAreaEntity;

public class RentAreaRepositoryImpl extends SimpleRepository<RentAreaEntity> implements RentAreaRespository{

	@Override
	public List<RentAreaEntity> findRentArea(Long buildingId) {
		String sql = "select * from rentarea "+SystemConstant.ONE_EQUAL_ONE+"";
		if(buildingId != null) {
			sql += " and buildingid = "+buildingId+"";
		}
		return findByCondition(sql);
	}
	
}
