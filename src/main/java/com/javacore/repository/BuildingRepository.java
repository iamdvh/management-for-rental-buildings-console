package com.javacore.repository;

import java.util.List;
import java.util.Map;

import com.javacore.repository.entity.BuildingEntity;

public interface BuildingRepository extends JdbcRespository<BuildingEntity>{
	List<BuildingEntity> findBuilding(Map<String, Object> buildingSearchInput, List<String> type);
}
