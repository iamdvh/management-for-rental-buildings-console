package com.javacore.view.building;

import com.javacore.controller.BuildingController;
import com.javacore.model.input.AssignmentBuildingInput;

public class AssignmentBuilding {
	public static void main(String[] args) {
		Long buildingid = 4l;
		Long[] assignStaff= {2l, 3l};
		AssignmentBuildingInput assignmentBuildingInput = new AssignmentBuildingInput();
		assignmentBuildingInput.setBuildingId(buildingid);
		assignmentBuildingInput.setAssignStaff(assignStaff);
		BuildingController buildingController = new BuildingController();
		buildingController.assignmentBuilding(assignmentBuildingInput);
	}
}
