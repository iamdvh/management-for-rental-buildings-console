package com.javacore.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javacore.constant.SystemConstant;
import com.javacore.repository.BuildingRepository;
import com.javacore.repository.entity.BuildingEntity;
import com.javacore.utils.StringUtils;

public class BuildingRepositoryImp extends SimpleRepository<BuildingEntity> implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> type) {

		StringBuilder joinQuery = new StringBuilder();
		StringBuilder whereQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder(
				"select  b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea,"
						+ "b.rentprice, b.managername, b.managerphone");
		finalQuery.append("\nfrom building b");
		buildSpecialQuery(params, type, joinQuery, whereQuery);
		buildNormalQuery(params, whereQuery);

		finalQuery.append(joinQuery).append("\n" + SystemConstant.ONE_EQUAL_ONE).append(whereQuery)
				.append("\ngroup by b.id");
		System.out.println("Final query: " + finalQuery.toString());
		System.out.println("-------------------------------------------");
		return findByCondition(finalQuery.toString());
	}

	private void buildSpecialQuery(Map<String, Object> params, List<String> type, StringBuilder joinQuery,
			StringBuilder whereQuery) {
		String districtCode = (String) params.get("districtCode");
		Integer rentAreaFrom = (Integer) params.get("rentAreaFrom");
		Integer rentAreaTo = (Integer) params.get("rentAreaTo");
		Long staff = (Long) params.get("staff");

		if (!StringUtils.isNullOrEmpty(districtCode)) {
			joinQuery.append("\njoin district d on b.districtid = d.id ");
			whereQuery.append("\nand d.code like'%" + districtCode + "%'");
		}
		if (rentAreaFrom != null | rentAreaTo != null) {
			joinQuery.append(
					"\njoin rentarea r on b.id = r.buildingid " + "join buildingrenttype t on b.id = t.buildingid ");
			if (rentAreaFrom != null && rentAreaTo != null) {
				whereQuery.append("\nand r.value between " + rentAreaFrom + " and " + rentAreaTo + "");
			} else if (rentAreaTo != null) {
				whereQuery.append("\nand r.value <= " + rentAreaTo + "");
			} else if (rentAreaFrom != null) {
				whereQuery.append("\nand r.value >= " + rentAreaFrom + "");
			}
		}
		if (type.size() > 0) {
			joinQuery.append(
					"\njoin buildingrenttype t on t.buildingid = b.id join renttype rt on t.renttypeid = rt.id ");
			List<String> preType = new ArrayList<String>();
			for (String str : type) {
				preType.add("'"+str+"'");
			}
			String types = String.join(",", preType);
			whereQuery.append("\nand rt.code in(" + types+ ")");
		}
		if (staff != null) {
			joinQuery.append("\njoin assignmentbuilding a on a.buildingid = b.id ");
			whereQuery.append("\nand a.staffid = " + staff + "");
		}
	}

	private void buildNormalQuery(Map<String, Object> params, StringBuilder whereQuery) {
		String name = (String) params.get("name");
		Long floorArea = (Long) params.get("floorArea");
		String street = (String) params.get("street");
		String ward = (String) params.get("ward");
		Integer numberOfBasement = (Integer) params.get("numberOfBasement");
		String direction = (String) params.get("direction");
		String level = (String) params.get("level");
		String managerName = (String) params.get("managerName");
		String managerPhone = (String) params.get("managerPhone");
		Integer rentPriceTo = (Integer) params.get("rentPriceTo");
		Integer rentPriceFrom = (Integer) params.get("rentPriceFrom");
		if (!StringUtils.isNullOrEmpty(name)) {
			whereQuery.append("\nand b.name like'%" + name + "%'");
		}
		if (floorArea != null) {
			whereQuery.append("\nand b.floorArea like'%" + floorArea + "%'");
		}
		if (!StringUtils.isNullOrEmpty(street)) {
			whereQuery.append("\nand b.street like'%" + street + "%'");
		}
		if (!StringUtils.isNullOrEmpty(ward)) {
			whereQuery.append("\nand ward like'%" + ward + "%'");
		}
		if (numberOfBasement != null) {
			whereQuery.append("\nand numberOfBasement like'%" + numberOfBasement + "%'");
		}
		if (!StringUtils.isNullOrEmpty(direction)) {
			whereQuery.append("\nand direction like'%" + direction + "%'");
		}
		if (!StringUtils.isNullOrEmpty(level)) {
			whereQuery.append("\nand level like'%" + level + "%'");
		}
		if (!StringUtils.isNullOrEmpty(managerName)) {
			whereQuery.append("\nand b.managername like'%" + managerName + "%'");
		}
		if (rentPriceFrom != null | rentPriceTo != null) {
			if (rentPriceFrom != null && rentPriceTo != null) {
				whereQuery.append("\nand rentprice between " + rentPriceFrom + "\nand " + rentPriceTo + "");
			} else if (rentPriceTo == null) {
				whereQuery.append("\nand rentprice >= " + rentPriceFrom + "");
			} else {
				whereQuery.append("\nand rentprice <= " + rentPriceTo + "");
			}
		}
		if (!StringUtils.isNullOrEmpty(managerPhone)) {
			whereQuery.append("\nand b.managerphone like'%" + managerPhone + "%'");
		}
	}

}
