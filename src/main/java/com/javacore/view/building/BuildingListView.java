package com.javacore.view.building;
import java.util.List;

import com.javacore.controller.BuildingController;
import com.javacore.model.output.BuildingOutput;

public class BuildingListView {
	public static void main(String[] args) {
		BuildingController buildingController = new BuildingController();
		List<BuildingOutput> buildings = buildingController.findAll();
				 for (BuildingOutput item : buildings) {
				      System.out.println("Name: " + item.getName());
				      System.out.println("Address: " + item.getAddress());
//				      System.out.println("Type: " +item.getType());
				      System.out.println("---------------------------------");
				    }
	}
}
