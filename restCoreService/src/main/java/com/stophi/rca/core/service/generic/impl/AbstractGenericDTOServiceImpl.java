package com.stophi.rca.core.service.generic.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.stophi.rca.core.persistence.dao.DTODao;
import com.stophi.rca.core.persistence.model.AbstractDTO;
import com.stophi.rca.core.service.generic.GenericDTOService;


// TODO: Auto-generated Javadoc
/**
 * An abstract implementation for the GenericDTOService.
 *
 * @author cscheuermann
 * @param <T> the generic type
 */
public abstract class AbstractGenericDTOServiceImpl<T extends AbstractDTO> implements GenericDTOService<T> {
	
	/** The dao. */
	@Resource(name = "dtoDAO")
	private DTODao<T> dao;

	/* (non-Javadoc)
	 * @see com.stophi.rca.core.service.generic.GenericDTOService#sqlQuery(java.lang.String, java.util.HashMap, java.lang.Class)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<T> sqlQuery(String sqlQuery, HashMap<String, String> parameters, Class<T> dto) {
		List<T> resultList = dao.query(sqlQuery, parameters, dto);
		
		return resultList;
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.core.service.generic.GenericDTOService#sqlQueryRaw(java.lang.String, java.util.Map)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> sqlQueryRaw(String sqlQuery, Map<String, String> parameterMap) {
		List<Map<String,Object>> results = dao.queryRaw(sqlQuery, parameterMap);
		
		return results;
	}
	
	/* (non-Javadoc)
	 * @see com.stophi.rca.core.service.generic.GenericDTOService#sqlQueryResultSet(java.lang.String, java.util.LinkedHashMap)
	 */
	@Override
	@Transactional(readOnly = true)
	public ResultSet sqlQueryResultSet(String sqlQuery, LinkedHashMap<String, String> parameters) {
		ResultSet resultSet = dao.queryResultSet(sqlQuery, parameters);
		
		return resultSet;
	}
	
}
