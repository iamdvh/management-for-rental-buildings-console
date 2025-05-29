package com.javacore.view.building;

import com.javacore.controller.BuildingController;

public class BuildingDeleteView {
public static void main(String[] args) {
	Long id = 9L;
	BuildingController buildingController = new BuildingController();
	buildingController.delete(id);
}
}
