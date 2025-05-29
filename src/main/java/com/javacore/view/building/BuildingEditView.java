package com.javacore.view.building;

import com.javacore.controller.BuildingController;
import com.javacore.model.dto.BuildingDTO;

public class BuildingEditView {
	public static void main(String[] args) {
		Long id =  12L;
		String name = "Test update";
		Integer floorArea = 100;
		String street = "32 Lý Chính Thắng";
		String ward = "Phường 7";
		Long districtId = null;
		Integer rentPrice = null;
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setId(id);
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
