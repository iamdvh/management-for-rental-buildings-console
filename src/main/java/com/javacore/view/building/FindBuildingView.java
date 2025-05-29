package com.javacore.view.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javacore.controller.BuildingController;
import com.javacore.model.output.BuildingOutput;

public class FindBuildingView {
	public static void main(String[] args) {
		String name = null;
		Integer floorArea = null;
		String districtCode = null;
		String ward = null;
		String street = null;
		Integer numberOfBasement = null;
		String direction = null;
		String level = null;
		Integer rentAreaFrom = null;
		Integer rentAreaTo = null;
		Integer rentPriceFrom = null;
		Integer rentPriceTo = null;
		String managerName = null;
		String managerPhone = null;
		Long staff = null;
		List<String> type = new ArrayList<>();
//		type.add("tang-tret");
//		type.add("nguyen-can");
		Map<String, Object> buildingSearchInput = new HashMap<>();
		buildingSearchInput.put("name", name);
		buildingSearchInput.put("floorArea",floorArea);
		buildingSearchInput.put("districtCode",districtCode);
		buildingSearchInput.put("ward",ward);
		buildingSearchInput.put("street",street);
		buildingSearchInput.put("numberOfBasement", numberOfBasement);
		buildingSearchInput.put("direction",direction);
		buildingSearchInput.put("level",level);
		buildingSearchInput.put("rentAreaFrom",rentAreaFrom);
		buildingSearchInput.put("rentAreaTo",rentAreaTo);
		buildingSearchInput.put("rentPriceFrom",rentPriceFrom);
		buildingSearchInput.put("rentPriceTo",rentPriceTo);
		buildingSearchInput.put("mangerName",managerName);
		buildingSearchInput.put("managerPhone",managerPhone);
		buildingSearchInput.put("staff",staff);
		BuildingController buildingController = new BuildingController();
		List<BuildingOutput> buildings = buildingController.findBuilding(buildingSearchInput, type);
		for (BuildingOutput building : buildings) {
			System.out.println("Name: " + building.getName());
			System.out.println("Address : " + building.getAddress());
			System.out.println("Manager name: " + building.getManagerName());
			System.out.println("Manager phone: "+building.getManagerPhone());
			System.out.println("Floor area  : " + building.getFloorArea());
			System.out.println("Rent area: " + building.getRenArea());
			System.out.println("Rent price : " + building.getRentPrice());
			System.out.println("Type: " + building.getType());
			System.out.println("---------------------------------");
		}
	}
}
