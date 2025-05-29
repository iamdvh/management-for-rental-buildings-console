package com.javacore.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.javacore.converter.BuildingConverter;
import com.javacore.model.dto.BuildingDTO;
import com.javacore.model.input.AssignmentBuildingInput;
import com.javacore.model.output.BuildingOutput;
import com.javacore.repository.AssignmentBuilding;
import com.javacore.repository.BuildingRepository;
import com.javacore.repository.DistrictRepository;
import com.javacore.repository.entity.AssignmentBuildingEntity;
import com.javacore.repository.entity.BuildingEntity;
import com.javacore.repository.impl.AssignmentBuildingImpl;
import com.javacore.repository.impl.BuildingRepositoryImp;
import com.javacore.repository.impl.DistrictRepositoryImpl;
import com.javacore.service.BuildingService;

public class BuildingServiceImp implements BuildingService {
	DistrictRepository districtRepository = new DistrictRepositoryImpl();
	BuildingRepository buildingRepository = new BuildingRepositoryImp();
	BuildingConverter buildingConverter = new BuildingConverter();
	AssignmentBuilding assignmentBuilding = new AssignmentBuildingImpl();

	@Override
	public List<BuildingOutput> findBuilding(Map<String, Object> buildingSearchInput, List<String> type) {
		List<BuildingOutput> buildingOutputs = new ArrayList<>();
		List<BuildingEntity> buildingEntity = buildingRepository.findBuilding(buildingSearchInput, type);
		for (BuildingEntity item : buildingEntity) {

			BuildingOutput buildingOutput = buildingConverter.convertBuildingEntityToBuildingOutput(item);

			buildingOutputs.add(buildingOutput);
		}
		return buildingOutputs;
	}

	@Override
	public List<BuildingOutput> findAll() {
		List<BuildingEntity> buildingEntity = buildingRepository.findAll();
		List<BuildingOutput> results = new ArrayList<>();
		for (BuildingEntity item : buildingEntity) {
			BuildingOutput buildingOutput = buildingConverter.convertBuildingEntityToBuildingOutput(item);
			results.add(buildingOutput);
		}
		return results;
	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity result = buildingRepository.findById(id);
		BuildingDTO building = new BuildingDTO();
		building.setName(result.getName());
		return building;
	}

	@Override
	public void insert(BuildingDTO buildingDTO) {
		if (buildingDTO.getId() == null) {
			BuildingEntity buildingEntity = buildingConverter.convertBuildingDtoToBuildingEntity(buildingDTO);
			buildingRepository.insert(buildingEntity);
		} else {
			BuildingEntity buildingEntity = buildingConverter.convertBuildingDtoToBuildingEntity(buildingDTO);
			buildingRepository.update(buildingEntity);
		}

	}

	@Override
	public void delete(Long id) {
		buildingRepository.delete(id);
	}

	@Override
	public void assignmentBuilding(AssignmentBuildingInput assignmentBuildingInput) {
		Long buildingId = assignmentBuildingInput.getBuildingId();
		List<Long> staffToDelete = new ArrayList<Long>();
		List<Long> staffToAdd = new ArrayList<Long>();
		List<AssignmentBuildingEntity> listAssignments = assignmentBuilding
				.findAssignmentBuilding(assignmentBuildingInput.getBuildingId(), null);
		HashSet<Long> crrStaff = new HashSet<Long>();
		HashSet<Long> inputStaff = new HashSet<Long>();
		for (AssignmentBuildingEntity item : listAssignments) {
			crrStaff.add(item.getStaffId());
		}
		for (Long item : assignmentBuildingInput.getAssignStaff()) {
			inputStaff.add(item);
		}
		for (Long item : inputStaff) {
			if(!crrStaff.contains(item)) {
				staffToAdd.add(item);
			}
		}
		for (Long item : crrStaff) {
			if(!inputStaff.contains(item)) {
				staffToDelete.add(item);
			}
		}

		if (staffToDelete.size() > 0) {
			for (Long itemD : staffToDelete) {
				List<AssignmentBuildingEntity> listDelete = assignmentBuilding.findAssignmentBuilding(buildingId, itemD);
				Long deleteId = listDelete.get(0).getId();
				assignmentBuilding.delete(deleteId);
			}
		}
		if (staffToAdd.size() > 0) {
			for (Long itemA : staffToAdd) {
				AssignmentBuildingEntity assignment = new AssignmentBuildingEntity();
				assignment.setBuildingId(assignmentBuildingInput.getBuildingId());
				assignment.setStaffId(itemA);
				assignmentBuilding.insert(assignment);
			}
		}
	}
}
