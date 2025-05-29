package com.javacore.enums;

public enum BuildingTypeEnum {
	TANG_TRET("Tầng trệt"),
	NGUYEN_CAN("Nguyên căn"),
	NOI_THAT("Nội thất");
	private final String BuildingName;
	private BuildingTypeEnum(String BuildingType) {
		this.BuildingName = BuildingType;
	}
	public String getBuildingName() {
		return BuildingName;
	}
}
