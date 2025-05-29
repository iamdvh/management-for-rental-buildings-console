package com.javacore.repository.entity;
import com.javacore.annotation.Entity;
import com.javacore.annotation.Table;
@Entity
@Table(name = "customer")
public class CustomerEntity {
	private String fullname;
	private int phone;
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
}
