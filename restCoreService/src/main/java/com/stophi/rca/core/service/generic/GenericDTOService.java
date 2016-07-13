package com.stophi.rca.core.service.generic;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.stophi.rca.core.persistence.model.AbstractDTO;


/**
 * The Interface GenericDTOService represents the generic interface for operations using DTOs.
 *
 * @author cscheuermann
 * @param <T> the generic type
 */
public interface GenericDTOService<T extends AbstractDTO> {
	
	/**
	 * Sql query.
	 *
	 * @param sqlQuery the sql query
	 * @param parameters the parameters
	 * @param dto the dto
	 * @return the list
	 */
	List<T> sqlQuery(String sqlQuery, HashMap<String, String> parameters, Class<T> dto);
	
	
	/**
	 * Sql query raw.
	 *
	 * @param sqlQuery the sql query
	 * @param parameters the parameters
	 * @return the list
	 */
	List<Map<String,Object>> sqlQueryRaw(String sqlQuery, Map<String, String> parameters);
	
	
	/**
	 * Sql query that returns a ResultSet.
	 *
	 * @param sqlQuery the sql query
	 * @param parameters the parameters
	 * @return the result set
	 */
	ResultSet sqlQueryResultSet(String sqlQuery, LinkedHashMap<String, String> parameters);

}
