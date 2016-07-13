package com.stophi.rca.test.core.persistence.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stophi.rca.core.persistence.criteria.AbstractEntityCriteria;
import com.stophi.rca.core.persistence.criteria.HibernateCriteria;
import com.stophi.rca.core.persistence.dao.PersistentObjectDAO;
import com.stophi.rca.test.core.persistence.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/META-INF/persistence.testbeans.xml" })
public class PersistentObjectDAOTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Resource(name = "persistentObjectDAO")
	private PersistentObjectDAO<Product> dao;
	
	private static final String CREATE_TABLE_QUERY = "CREATE MEMORY TABLE Product_Table ("
			+ "productId INTEGER NOT NULL,"
			+ "ticker VARCHAR(50) NOT NULL,"
			+ "assetClass VARCHAR(100),"
			+ "PRIMARY KEY (productId));";
	
	private static final String INSERT_TEST_DATA = "INSERT INTO Product_Table (productId, ticker, assetClass) "
			+ "VALUES ((1,'SPY', 'Equity: U.S. - Large Cap'),(2, 'TLT', 'Fixed Income: U.S. Government Treasury Long-Term'))";
			

	@Before
	public void setUp() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(CREATE_TABLE_QUERY);
		query.executeUpdate();
		insertTestData();
	}
	
	@Test
	@Transactional
	public void testFindById() {
		Product product = dao.findById(Product.class, new Long(1));
		
		assertTrue(product.getTicker().equals("SPY") && product.getProductId() == 1);
	}
	
	@Test
	@Transactional
	public void testFindByAttributes() {
		AbstractEntityCriteria entityCriteria = new AbstractEntityCriteria(Product.class) {
			
			@Override
			protected void fillCriteria(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("ticker", "SPY"));
			}
		};
		
		List<Product> products = dao.findByAttributes(entityCriteria);
		
		assertTrue(products.get(0).getTicker().equals("SPY") && products.get(0).getProductId() == 1);
	}
	
	@Test
	@Transactional
	public void testFindAll() {
		List<Product> products = dao.findAll(Product.class);
		assertTrue(products.size() == 2);
	}
	
	@Test
	@Transactional
	public void testFind() {
		HibernateCriteria<Product> hibernateCriteria = new HibernateCriteria<Product>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Product> doInHibernate(Session session) {
				String queryString = "from " + Product.class.getSimpleName()
                        + " as product where product.ticker = :ticker";

                Query query = session.createQuery(queryString);
                query.setParameter("ticker", "SPY");

                return query.list();
			}
		};
		List<Product> products = dao.find(hibernateCriteria);
		
		assertTrue(products.get(0).getTicker().equals("SPY") && products.get(0).getProductId() == 1);
		
	}
	
	@Test
	@Transactional
	public void testCount() {
		AbstractEntityCriteria entityCriteria = new AbstractEntityCriteria(Product.class) {
			
			@Override
			protected void fillCriteria(DetachedCriteria criteria) {
			}
		};
		
		Long count = dao.count(entityCriteria, "ticker");
		
		assertTrue(count == 2);
	}
	
	@Test
	@Transactional
	public void testFindPaged() {
			AbstractEntityCriteria entityCriteria = new AbstractEntityCriteria(Product.class) {
			
			@Override
			protected void fillCriteria(DetachedCriteria criteria) {
			}
		};
		
		List<Product> products = dao.findPaged(entityCriteria, 0, 1);
		
		assertTrue(products.size() == 1);
	}
	
	
	@After
	public void tearDown() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("DROP Table Product_Table;");
		query.executeUpdate();
	}
	
	private void insertTestData() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(INSERT_TEST_DATA);
		query.executeUpdate();
	}

}
