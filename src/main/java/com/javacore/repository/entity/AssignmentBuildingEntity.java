package com.javacore.repository.entity;

import com.javacore.annotation.Column;
import com.javacore.annotation.Entity;
import com.javacore.annotation.Table;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity {
	@Column(name = "buildingid")
	private Long buildingId;
	@Column(name = "staffid")
	private Long staffId;
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
}
