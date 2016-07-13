package com.stophi.rca.test.core.persistence.dao;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stophi.rca.core.persistence.dao.DTODao;
import com.stophi.rca.core.persistence.model.AbstractDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/META-INF/persistence.testbeans.xml" })
public class DTODaoTest {
	
	public static final String CREATE_FINDER_FILTER_DEP_TABLE = "CREATE MEMORY TABLE finder_filters_dep ("
			+ "\"productId\" INTEGER NOT NULL,"
			+ "\"ticker\" VARCHAR(50) NOT NULL,"
			+ "\"assetClassId\" INTEGER NOT NULL,"
			+ "\"assetClass\" VARCHAR(100),"
			+ "\"edlId\" INTEGER NOT NULL,"
			+ "\"economicDevelopment\" VARCHAR(100),"
			+ "\"regionId\" INTEGER NOT NULL,"
			+ "\"region\" VARCHAR(255),"
			+ "\"geographyId\" INTEGER NOT NULL,"
			+ "\"geography\" VARCHAR(255),"
			+ "\"categoryId\" INTEGER NOT NULL,"
			+ "\"category\" VARCHAR(255),"
			+ "\"focusId\" INTEGER NOT NULL,"
			+ "\"focus\" VARCHAR(255),"
			+ "\"nicheId\" INTEGER NOT NULL,"
			+ "\"niche\" VARCHAR(255),"
			+ "\"segmentId\" INTEGER NOT NULL,"
			+ "\"segment\" VARCHAR(255),"
			+ "\"issuerId\" INTEGER NOT NULL,"
			+ "\"issuer\" VARCHAR(255),"
			+ "\"brandId\" INTEGER,"
			+ "\"brand\" VARCHAR(255),"
			+ "\"selectionCriteriaId\" INTEGER NOT NULL,"
			+ "\"selectionCriteria\" VARCHAR(255),"
			+ "\"weightingSchemeId\" INTEGER NOT NULL,"
			+ "\"weightingScheme\" VARCHAR(255),"
			+ "PRIMARY KEY (\"productId\"));";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Resource(name = "dtoDAO")
	private DTODao<AbstractDTO> dao;
	
	@Before
	public void setUp() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(CREATE_FINDER_FILTER_DEP_TABLE);
		query.executeUpdate();		
	}

	@Test
	@Transactional
	public void queryTest() {
		String sqlQuery = "Select * from finder_filters_dep;"; 
		HashMap<String, String> params = null; 
		
		List<AbstractDTO> results = dao.query(sqlQuery, params, AbstractDTO.class);
		
		assertTrue(results.isEmpty());
	}
	
	@Test
	@Transactional
	public void testQueryRaw() {
		String sqlQuery = "Select * from finder_filters_dep;"; 
		HashMap<String, String> params = null;
		
		List<Map<String, Object>> results = dao.queryRaw(sqlQuery, params);
		
		assertTrue(results.isEmpty());
	}
	
	@Test
	@Transactional
	public void testQueryResultSet() {
		String sqlQuery = "Select * from finder_filters_dep;"; 
		LinkedHashMap<String, String> params = null;
		
		ResultSet result = dao.queryResultSet(sqlQuery, params);
		
		assertTrue(result != null);
	}
	
	@After
	public void tearDown() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("DROP Table finder_filters_dep;");
		query.executeUpdate();
	}

}
