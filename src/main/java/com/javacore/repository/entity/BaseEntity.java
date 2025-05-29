package com.javacore.repository.entity;

import com.javacore.annotation.Column;

public class BaseEntity {
	@Column(name = "id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
