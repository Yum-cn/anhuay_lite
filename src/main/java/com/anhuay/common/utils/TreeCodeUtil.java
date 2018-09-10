package com.anhuay.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.anhuay.common.domain.TreeCode;

public class TreeCodeUtil {

	 String tempString = "";

	public  String getAllParentName(List<TreeCode> list, String id) {
		String ids = getAllParentId(list, id);
		//System.out.println(ids);
		Map<String, String> map = new HashMap<String, String>();
		if (!CollectionUtils.isEmpty(list)) {
			for (TreeCode treeCode : list) {
				map.put(treeCode.getTreeCodeId(), treeCode.getTreeCodeName());
			}
		}
		String values = "";
		if (StringUtils.isBlank(ids)) {
			return null;
		}
		String[] idArray = ids.split(",");
		if (idArray == null) {
			return null;
		}
		for (int i = 0; i < idArray.length; i++) {
			if (StringUtils.isNotBlank(values)) {
				values += ",";
			}
			if (map.get(idArray[i]) != null) {
				values += map.get(idArray[i]);
			}

		}
		return values;
	}

	public  String getAllParentId(List<TreeCode> list, String id) {
		String tempId = id;
		if (StringUtils.isNotBlank(tempString)) {
			if (StringUtils.isBlank(tempId)) {
				tempId = tempString;
			} else {
				tempId = tempId + "," + tempString;
			}
			tempString = tempId;
		} else {
			tempString = tempId;
		}

		for (TreeCode treeCode : list) {
			if (StringUtils.equals(id, treeCode.getTreeCodeId())) {
				getAllParentId(list, treeCode.getParentId());
			}
		}

		return tempString;
	}
	

	public  String getAllChildId(List<TreeCode> list, String id) {
		String tempId = id;
		if (StringUtils.isNotBlank(tempString)) {
			if (StringUtils.isBlank(tempId)) {
				tempId = tempString;
			} else {
				tempId = tempId + "," + tempString;
			}
			tempString = tempId;
		} else {
			tempString = tempId;
		}

		for (TreeCode treeCode : list) {
			if (StringUtils.equals(id, treeCode.getParentId())) {
				getAllChildId(list, treeCode.getTreeCodeId());
			}
		}

		return tempString;
	}
}
