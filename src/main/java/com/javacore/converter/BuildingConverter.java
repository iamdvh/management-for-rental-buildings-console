package com.javacore.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.javacore.model.dto.BuildingDTO;
import com.javacore.model.input.AssignmentBuildingInput;
import com.javacore.model.output.BuildingOutput;
import com.javacore.repository.BuildingRentTypeRespository;
import com.javacore.repository.DistrictRepository;
import com.javacore.repository.RentAreaRespository;
import com.javacore.repository.entity.AssignmentBuildingEntity;
import com.javacore.repository.entity.BuildingEntity;
import com.javacore.repository.entity.BuildingRentTypeEntity;
import com.javacore.repository.entity.DistrictEntity;
import com.javacore.repository.entity.RentAreaEntity;
import com.javacore.repository.impl.BuildingRentTypeRespositoryImpl;
import com.javacore.repository.impl.DistrictRepositoryImpl;
import com.javacore.repository.impl.RentAreaRepositoryImpl;

public class BuildingConverter {
	public BuildingOutput convertBuildingEntityToBuildingOutput(BuildingEntity buildingEntity) {
		DistrictRepository districtRepository = new DistrictRepositoryImpl();
		RentAreaRespository rentAreaRespository = new RentAreaRepositoryImpl();
		BuildingRentTypeRespository buildingRentTypeRespository = new BuildingRentTypeRespositoryImpl();
		String districtName = null;
		if(buildingEntity.getDistrictId() != null) {
			 DistrictEntity district = districtRepository.findById(buildingEntity.getDistrictId());
				 districtName = district.getName();
		}
		// rent area
		List<RentAreaEntity> rentArea = rentAreaRespository.findRentArea(buildingEntity.getId());
		List<String> value = new ArrayList<>();
		for (RentAreaEntity item : rentArea) {
			value.add(String.valueOf(item.getValue()));
		}
		String rentAreaValues = String.join(", ",value);
		// type
		List<BuildingRentTypeEntity> buildingTypes = buildingRentTypeRespository.findType(buildingEntity.getId());
		List<String> allType = new ArrayList<String>();
		for (BuildingRentTypeEntity item : buildingTypes) {
			allType.add(item.getName());
		}
		String getType = String.join(", ", allType);
		//
		BuildingOutput buildingOutput = new BuildingOutput();
		buildingOutput.setName(buildingEntity.getName());
		buildingOutput.setFloorArea(buildingEntity.getFloorArea());
		List<String> preAddress = Arrays.asList(buildingEntity.getStreet(), buildingEntity.getWard(), districtName);
		buildingOutput.setAddress(String.join(" - ", preAddress));
		buildingOutput.setManagerName(buildingEntity.getManagerName());
		buildingOutput.setManagerPhone(buildingEntity.getManagerPhone());
		buildingOutput.setRenArea(rentAreaValues);
		buildingOutput.setRentPrice(buildingEntity.getRentPrice());
		buildingOutput.setType(getType);
		return buildingOutput;
	}
	public BuildingEntity convertBuildingDtoToBuildingEntity(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setId(buildingDTO.getId());
		buildingEntity.setFloorArea(buildingDTO.getFloorArea());
		buildingEntity.setName(buildingDTO.getName());
		buildingEntity.setStreet(buildingDTO.getStreet());
		buildingEntity.setWard(buildingDTO.getWard());
//		buildingEntity.set(buildingDTO.getDistrictId());
		buildingEntity.setRentPrice(buildingDTO.getRentPrice());
//		buildingEntity.setDistrict(buildingDTO.getDistrict());
		return buildingEntity;
	}
	public AssignmentBuildingEntity convertASMBEToABI(AssignmentBuildingInput assignmentBuildingInput, Long staffId) {
		AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
		assignmentBuildingEntity.setBuildingId(assignmentBuildingInput.getBuildingId());
		assignmentBuildingEntity.setStaffId(staffId);
		return assignmentBuildingEntity;
	}
}
