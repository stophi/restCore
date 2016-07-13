package com.stophi.rca.core.persistence.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stophi.rca.core.persistence.dao.DTODao;
import com.stophi.rca.core.persistence.model.AbstractDTO;


/**
 * Implementation for the DTODao.
 *
 * @author cscheuermann
 * @param <T> the generic type
 */
@Repository("dtoDAO")
public class DTODaoImpl<T extends AbstractDTO> implements DTODao<T>{

	/** The logger. */
	private final static Logger log = LoggerFactory.getLogger(DTODaoImpl.class);
	
	/** Injected session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * Current session.
	 *
	 * @return the session
	 */
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.core.persistence.dao.DTODao#query(java.lang.String, java.util.HashMap, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String sqlQuery, HashMap<String, String> params, Class<T> dto) {
		if (log.isDebugEnabled()) {
			log.debug("Query requested: " + sqlQuery);
		}

		SQLQuery query = prepareQuery(sqlQuery, params, Transformers.aliasToBean(dto));
		return query.list();
	}
	
	/* (non-Javadoc)
	 * @see com.stophi.rca.core.persistence.dao.DTODao#queryRaw(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryRaw(String sqlQuery, Map<String, String> params) {
		if (log.isDebugEnabled()) {
			log.debug("Query requested: " + sqlQuery);
		}
		
		SQLQuery query = prepareQuery(sqlQuery, params, Transformers.ALIAS_TO_ENTITY_MAP);

		return query.list();
	}
	
	/* (non-Javadoc)
	 * @see com.stophi.rca.core.persistence.dao.DTODao#queryResultSet(java.lang.String, java.util.LinkedHashMap)
	 */
	@Override
	public ResultSet queryResultSet(final String sqlQuery, final LinkedHashMap<String, String> params) {
		ResultSet resultSet = currentSession().doReturningWork(new ReturningWork<ResultSet>() {

			@Override
			public ResultSet execute(Connection connection) throws SQLException {
				CallableStatement statement = connection.prepareCall(sqlQuery);
				int parameterCount = 1;
				
				if (params != null) {
					for (String parameterKey : params.keySet()) {
						statement.setString(parameterCount, params.get(parameterKey));
						parameterCount++;
					}
				}

				return statement.executeQuery();

			}
		});
		
		return resultSet;
	}
	
	/**
	 * Prepare query.
	 *
	 * @param sqlQuery the sql query
	 * @param params the parameter map
	 * @param resultTransformer the result transformer
	 * @return the SQL query
	 */
	private SQLQuery prepareQuery(String sqlQuery, Map<String, String> params, ResultTransformer resultTransformer) {
		SQLQuery query = currentSession().createSQLQuery(sqlQuery);

		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if (resultTransformer != null) {
			query.setResultTransformer(resultTransformer);
		}
		
		return query;
	}

}
