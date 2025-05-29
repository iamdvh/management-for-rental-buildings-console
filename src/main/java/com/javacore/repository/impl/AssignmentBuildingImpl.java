package com.javacore.repository.impl;

import java.util.List;

import com.javacore.constant.SystemConstant;
import com.javacore.repository.AssignmentBuilding;
import com.javacore.repository.entity.AssignmentBuildingEntity;

public class AssignmentBuildingImpl extends SimpleRepository<AssignmentBuildingEntity> implements AssignmentBuilding{

	@Override
	public List<AssignmentBuildingEntity> findAssignmentBuilding(Long buildingId, Long staffId) {
		StringBuilder sql = new StringBuilder("select * from assignmentbuilding "+SystemConstant.ONE_EQUAL_ONE+"");
		if(buildingId != null) {
			sql.append(" and buildingid = "+buildingId+"");
		}
		if(staffId != null) {
			sql.append(" and staffid = "+staffId+"");
		}
		return findByCondition(sql.toString());
	}

}
