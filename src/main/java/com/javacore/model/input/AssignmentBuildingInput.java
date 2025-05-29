package com.javacore.model.input;

public class AssignmentBuildingInput {
	private Long buildingId;
	private Long[] assignStaff;

	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long[] getAssignStaff() {
		return assignStaff;
	}
	public void setAssignStaff(Long[] assignStaff) {
		this.assignStaff = assignStaff;
	}
}
