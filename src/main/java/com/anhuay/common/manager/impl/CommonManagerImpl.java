package com.anhuay.common.manager.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.anhuay.common.domain.TreeCode;
import com.anhuay.common.manager.CommonManager;
import com.common.manager.impl.BaseManagerImpl;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;

import net.sf.json.JSONObject;

@Service(value = "commonManager")
public class CommonManagerImpl extends BaseManagerImpl implements CommonManager {

	private Logger logger = LoggerFactory.getLogger(CommonManagerImpl.class);

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 保存策略模板
	 * 
	 * @author Yum
	 */
	public BaseResult<Object> saveStrategyTemplet(JSONObject body) {

		try {

			return BaseResultHelper.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

	@Override
	public List<TreeCode> getTreeCode(String treeCodeType) {
		// TODO Auto-generated method stub

		String sql = "select  tree_code_id,tree_code_name,parent_id,refer_code,tree_code_type  from sys_tree_code where tree_code_type =  ?  order by sort,tree_code_id ";
		List<TreeCode> ret = jdbcTemplate.query(sql, new Object[] { treeCodeType }, new RowMapper<TreeCode>() {

			@Override
			public TreeCode mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				TreeCode code = new TreeCode();
				code.setParentId(rs.getString("parent_id"));
				code.setReferCode(rs.getString("refer_code"));
				code.setTreeCodeId(rs.getString("tree_code_id"));
				code.setTreeCodeName(rs.getString("tree_code_name"));
				return code;
			}
		});

		return ret;
	}

	/**
	 * 普通结构
	 * 
	 * @author Yum
	 */
	@Override
	public List<TreeCode> getTreeCodeForTable(String table) {

		if (StringUtils.isBlank(table)) {
			return null;
		}
		List<TreeCode> resultList = new ArrayList<TreeCode>();
		String sql = "select  id,name,code  from " + table + "   order by id asc";
		List<TreeCode> ret = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<TreeCode>() {

			@Override
			public TreeCode mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				TreeCode code = new TreeCode();
				code.setParentId("");
				// code.setReferCode(rs.getString("code"));
				code.setReferCode("");
				code.setTreeCodeId(rs.getString("code"));
				code.setTreeCodeName(rs.getString("name"));
				return code;
			}
		});
		resultList.addAll(ret);

		return resultList;
	}

	/**
	 * 多级结构
	 * 
	 * @author Yum
	 */
	@Override
	public List<TreeCode> getMutiTreeCode(String tables) {
		// TODO Auto-generated method stub

		if (StringUtils.isBlank(tables)) {
			return null;
		}
		String[] tableArray = tables.split(",");
		List<TreeCode> resultList = new ArrayList<TreeCode>();
		for (String table : tableArray) {
			String sql = "select  id,name,code,parent_code  from " + table + "   order by code ";
			List<TreeCode> ret = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<TreeCode>() {

				@Override
				public TreeCode mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					TreeCode code = new TreeCode();
					code.setParentId(rs.getString("parent_code"));
					// code.setReferCode(rs.getString("code"));
					code.setReferCode("");
					code.setTreeCodeId(rs.getString("code"));
					code.setTreeCodeName(rs.getString("name"));
					return code;
				}
			});
			resultList.addAll(ret);
		}

		return resultList;
	}
	

}
