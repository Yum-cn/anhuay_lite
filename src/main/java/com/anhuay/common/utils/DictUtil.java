package com.anhuay.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.anhuay.common.domain.Node;
import com.anhuay.common.domain.TreeCode;

public class DictUtil {

	public static List<Node> getNodeList(List<TreeCode> list) {
		List<Node> ret = new ArrayList<>();
		for (TreeCode treeCode : list) {
			Node node = new Node(treeCode.getTreeCodeId(), treeCode.getParentId(), treeCode.getTreeCodeName(),
					treeCode.getReferCode());
			ret.add(node);
		}
		return ret;
	}

	/**
	 * 获取当前节点的名称（父级名称）
	 * 
	 * @param tables
	 *            eg: "table1,table2,table3"
	 * @author Yum
	 */
	public static String getNodeName(List<TreeCode> list, String codeId) {

		// List<TreeCode> list = getVancloudMutiTreeCode(tables);
		String names = new TreeCodeUtil().getAllParentName(list, codeId);
		return names;
	}

	// 判断是否有子节点
	public static boolean hasChild(List<Node> list, Node node) {
		return getChildList(list, node).size() > 0 ? true : false;
	}

	// 得到子节点列表
	public static List<Node> getChildList(List<Node> list, Node node) {
		List<Node> li = new ArrayList<>();
		Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if (n.getParentId().equalsIgnoreCase(node.getId())) {
				li.add(n);
			}
		}
		return li;
	}

	public static String getJson(String json) {
		return modifyStr(json);
	}

	/**
	 * 查询
	 * 
	 * @param list
	 * @param node
	 */
	public static String recursionFn(List<Node> list, Node node, StringBuffer returnStr) {
		if (hasChild(list, node)) {
			returnStr.append("{\"id\":\"");
			returnStr.append(node.getId());
			returnStr.append("\",\"pid\":\"");
			returnStr.append(node.getParentId());
			returnStr.append("\",\"name\":");
			returnStr.append("\"" + node.getName() + "\"");
			returnStr.append(",\"referCode\":");
			returnStr.append("\"" + node.getReferCode() + "\"");
			returnStr.append(",\"sub_data\":[");

			List<Node> childList = getChildList(list, node);
			Iterator<Node> it = childList.iterator();
			while (it.hasNext()) {
				Node n = it.next();
				recursionFn(list, n, returnStr);
			}
			returnStr.append("]},");
		} else {
			returnStr.append("{\"id\":\"");
			returnStr.append(node.getId());
			returnStr.append("\",\"pid\":\"");
			returnStr.append(node.getParentId());
			returnStr.append("\",\"name\":");
			returnStr.append("\"" + node.getName() + "\"");
			returnStr.append(",\"referCode\":");
			returnStr.append("\"" + node.getReferCode() + "\"");
			returnStr.append(",\"sub_data\":");
			returnStr.append("[]},");
		}

		return returnStr.toString();
	}

	/**
	 * {"success":true, children":[ ] }
	 * 
	 * @param returnStr
	 * @return
	 */
	// Json格式
	public static String modifyStr(String returnStr) {
		returnStr = returnStr.substring(0, returnStr.length() - 1);
		return returnStr.replaceAll(",]", "]");
	}

	public static String getJsonList(String json) {
		json = modifyStr(json);
		json = StringUtils.substringAfter(json, "[");
		json = "[" + StringUtils.substringBeforeLast(json, "}");
		return json;
	}
}
