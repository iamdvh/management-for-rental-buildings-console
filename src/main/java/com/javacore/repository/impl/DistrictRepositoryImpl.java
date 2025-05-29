package com.javacore.repository.impl;

import java.util.List;

import com.javacore.constant.SystemConstant;
import com.javacore.repository.DistrictRepository;
import com.javacore.repository.entity.DistrictEntity;
import com.javacore.utils.StringUtils;

public class DistrictRepositoryImpl extends SimpleRepository<DistrictEntity> implements DistrictRepository{
	public List<DistrictEntity> findDistrict(Long id){
		String sql = "select * from district "+SystemConstant.ONE_EQUAL_ONE+"";
		if(id != null) {
			sql += " and id = "+id+"";
		}
		return findByCondition(sql);
	}
}
