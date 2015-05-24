package com.lytx.finance.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface to execute CRUD command for T entity.
 * 
 * @version 1.0
 * @author cloudlu
 *
 * @param <T> Entity to 
 * @param <ID> Entity's id
 */

public interface BaseDAO <T, ID extends Serializable>{
	/**
	 * R: find T by Id. 
	 * @param id T's Id
	 * @return If id is not exist (e.g. id = null), return null, else return T with ID equals id.
	 */
	T findById(ID id);
	
	/**
	 * R: get all Ts
	 * @return a List of T
	 */
	List<T> findAll();

	
	List<T> search(String searchTerm);
	
	/**
	 * R: Count all Ts
	 * @return the number of T entities, return 0 when no entity is found
	 */
	long getTotalCount();
	
    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(ID id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the persisted object
     */
    T save(T object);


    /**
     * Generic method to delete an object
     * @param object the object to remove
     */
    void remove(T object);

    /**
     * Generic method to delete an object
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(ID id);

    /**
     * Find a list of records by using a named query
     * @param queryName query name of the named query
     * @param queryParams a map of the query names and the values
     * @return a list of the records found
     */
//    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);
	
//	List<T> getOnePage(int startRow, int pageSize);
	
//	int getTotalCount(String hql);
	
//	List getOnePage(String hql, int startRow, int pageSize);
	
//	List<T> findByHql(String hql);
}
