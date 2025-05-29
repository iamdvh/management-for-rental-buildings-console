package com.javacore.repository.entity;

import com.javacore.annotation.Column;
import com.javacore.annotation.Entity;
import com.javacore.annotation.Table;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
	@Column(name = "buildingid")
	private Long buildingId;
	@Column(name = "value")
	private Integer value;
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
