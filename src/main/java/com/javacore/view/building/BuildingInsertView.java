package com.javacore.view.building;

import com.javacore.controller.BuildingController;
import com.javacore.model.dto.BuildingDTO;

public class BuildingInsertView {
	public static void main(String[] args) {
		String name = "Building The SkyLine";
		Integer floorArea = 300;
		String street = "31 Lý Chính Thắng";
		String ward = "Phường 7";
		Long districtId =(long) 1;
		Integer rentPrice =33;
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setName(name);
		buildingDTO.setFloorArea(floorArea);
		buildingDTO.setRentPrice(rentPrice);
		buildingDTO.setStreet(street);
		buildingDTO.setWard(ward);
		buildingDTO.setDistrictId(districtId);
		BuildingController buildingController = new BuildingController();
		buildingController.insert(buildingDTO);
	}
}
