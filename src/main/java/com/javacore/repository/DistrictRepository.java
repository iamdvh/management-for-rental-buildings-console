package com.javacore.repository;

import java.util.List;

import com.javacore.repository.entity.DistrictEntity;

public interface DistrictRepository extends JdbcRespository<DistrictEntity>{
	public List<DistrictEntity> findDistrict(Long id);
}
