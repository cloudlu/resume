/**
 * 
 */
package com.lytx.finance.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.lytx.finance.dao.BaseDAO;

/**
 * @author cloudlu
 *
 */
public abstract class BaseDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);
		  
	private Class<T> persistentClass;
		  
	@Resource
	private SessionFactory sessionFactory;
		  
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public SessionFactory getSessionFactory() {
	   return this.sessionFactory;
	}
	  
    public Session getSession() throws HibernateException {
	   Session sess = getSessionFactory().getCurrentSession();
	   if (sess == null) {
	     sess = getSessionFactory().openSession();
	   }
	   return sess;
	}
		  
	@Autowired
	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
	  this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id) {
	    IdentifierLoadAccess byId = getSession().byId(this.persistentClass);
	    T entity = (T) byId.load(id);
	    return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
	    return getSession().createCriteria(this.persistentClass).list();
	}

	public List<T> search(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Get a list of T with start row and limit numbers.
	 * 
	 * @param hql
	 *            The hql to execute.
	 * @param startRow
	 *            should >= 0, If startRow < 0, set to 0.
	 * @param pageSize
	 *            should > 0, If paseSize <= 0, ignore this param.
	 * @return The selected T object list
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getOnePage(String hql, int startRow, int pageSize) {
		// assert startRow >= 0 : "startRow should >= 0";
		// assert pageSize > 0 : "pageSize should > 0";
		/*
		 * EntityManagerFactory factory = Persistence
		 * .createEntityManagerFactory("mysql");
		 * 
		 * EntityManager em = factory.createEntityManager();
		 */
		Query q = getSession().createQuery(hql);
		if (startRow < 0)
			startRow = 0;
		q.setFirstResult(startRow);
		if (pageSize > 0)
			q.setMaxResults(pageSize);
		/*
		 * em.close(); factory.close();
		 */
		return q.list();
	}
	
	
	public long getTotalCount() {
		Long count = new Long(0);
		Query q = getSession().createQuery("select count(t) from "
				+ persistentClass.getSimpleName() + " t");
		if (!q.list().isEmpty()) {
			count = (Long) q.list().get(0);
		}
		return count.longValue();
	}

	/**
	 * Count the number of T entities with hql
	 * 
	 * @param hql
	 *            The hql to execute
	 * @param classname
	 *            object name used in hql
	 * @return the number, 0 if no entity found
	 */
	protected long getTotalCount(String hql, String classname) {
		assert hql.contains(classname) : "object name should be contains in hql";
		Long count = new Long(0);
		// remove orderby in hql;
		int fromIndex = hql.indexOf("from");
		int orderByIndex = hql.indexOf("order by");

		StringBuffer buff = new StringBuffer();
		if (fromIndex == -1) {
			// Illegal hql
			return 0;
		} else if (orderByIndex > 0) {
			// remove orderby
			buff.append("select count(");
			buff.append(classname);
			buff.append(") ");
			buff.append(hql.substring(fromIndex, orderByIndex));
		} else {
			buff.append("select count(");
			buff.append(classname);
			buff.append(") ");
			buff.append(hql.substring(fromIndex));
		}
		
		logger.debug(buff.toString());
		
		Query q = getSession().createQuery(buff.toString());
		
		if (!q.list().isEmpty()) {
			count = (Long) q.list().get(0);
		}
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	public boolean exists(ID id) {
		IdentifierLoadAccess byId = getSession().byId(this.persistentClass);
	    T entity = (T) byId.load(id);
	    return null != entity;
	}

	@SuppressWarnings("unchecked")
	public T save(T object) {
		return (T) getSession().merge(object);
	}

	public void remove(T object) {
		getSession().delete(object);
	}

	public void remove(ID id) {
		T entity = findById(id);	
		if(null != entity)
			remove(entity);
	}
		  
		  
}
