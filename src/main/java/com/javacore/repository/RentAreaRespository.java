package com.javacore.repository;

import java.util.List;

import com.javacore.repository.entity.RentAreaEntity;

public interface RentAreaRespository extends JdbcRespository<RentAreaEntity>{
	List<RentAreaEntity> findRentArea(Long buildingId);
}
