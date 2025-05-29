package com.javacore.repository;

import java.util.List;

import com.javacore.repository.entity.AssignmentBuildingEntity;

public interface AssignmentBuilding extends JdbcRespository<AssignmentBuildingEntity>{
	public List<AssignmentBuildingEntity> findAssignmentBuilding(Long buildingId, Long staffId);
}
