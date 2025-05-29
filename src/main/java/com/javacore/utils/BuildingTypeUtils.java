package com.javacore.utils;

import java.util.ArrayList;
import java.util.List;

import com.javacore.enums.BuildingTypeEnum;

public class BuildingTypeUtils {
	public static String getName(String value){
		List<String> newType = new ArrayList<>();
		for(String oldType : value.split(", ")) {
			newType.add(BuildingTypeEnum.valueOf(oldType).getBuildingName());
		}
		return String.join(", ", newType);
	}
}
	