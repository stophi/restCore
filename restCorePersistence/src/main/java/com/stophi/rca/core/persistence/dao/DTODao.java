package com.stophi.rca.core.persistence.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.stophi.rca.core.persistence.model.AbstractDTO;


/**
 * Interface DTODao to serve as a generic DAO for DTO processing.
 *
 * @author cscheuermann
 * @param <T> the generic type
 */
public interface DTODao<T extends AbstractDTO> {

	
	/**
	 * Query.
	 *
	 * @param sqlQuery the sql query
	 * @param params the params
	 * @param dto the dto
	 * @return the list
	 */
	List<T> query(String sqlQuery, HashMap<String, String> params, Class<T> dto);
	
	
	/**
	 * Query raw.
	 *
	 * @param sqlQuery the sql query
	 * @param params the params
	 * @return the list
	 */
	List<Map<String,Object>> queryRaw(String sqlQuery, Map<String, String> params);
	
	/**
	 * Query that returens a ResultSet.
	 *
	 * @param sqlQuery the sql query
	 * @param params the params
	 * @return the result set
	 */
	ResultSet queryResultSet(final String sqlQuery, final LinkedHashMap<String, String> params);
	
}
