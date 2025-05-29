package com.javacore.repository;

import java.util.List;

import com.javacore.repository.entity.BuildingRentTypeEntity;

public interface BuildingRentTypeRespository extends JdbcRespository<BuildingRentTypeEntity>{
	List<BuildingRentTypeEntity> findType(Long buildingId);
}
