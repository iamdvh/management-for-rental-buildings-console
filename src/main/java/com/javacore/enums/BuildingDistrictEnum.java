package com.javacore.enums;

public enum BuildingDistrictEnum {
	Q1("Quận 1"), Q2("Quận 2"), Q3("Quận 3");
	private final String district;
	private BuildingDistrictEnum(String district) {
		this.district = district;
	}
	public String district() {
		return this.district;
	}
}
