package com.javacore.repository.entity;

import com.javacore.annotation.Column;
import com.javacore.annotation.Entity;
import com.javacore.annotation.Table;

@Entity
@Table(name = "buildingrenttype")
public class BuildingRentTypeEntity {
	@Column(name = "buildingid")
	private String buildingId;
	@Column(name = "name")
	private String name;
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
