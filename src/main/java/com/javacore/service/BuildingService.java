package com.javacore.service;

import java.util.List;
import java.util.Map;

import com.javacore.model.dto.BuildingDTO;
import com.javacore.model.input.AssignmentBuildingInput;
import com.javacore.model.output.BuildingOutput;

public interface BuildingService {
	public List<BuildingOutput> findBuilding(Map<String, Object> buildingSearchInput, List<String> type);
	public List<BuildingOutput> findAll();
	public BuildingDTO findById(Long id);
	public void insert(BuildingDTO buildingDTO);
	public void delete(Long id);
	public void assignmentBuilding(AssignmentBuildingInput assignmentBuildingInput);
}
