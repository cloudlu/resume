/**
 * 
 */
package com.lytx.finance.dao;

import com.lytx.finance.service.exception.RoleNotExistsException;
import com.lytx.finance.vo.Role;

/**
 * @author cloudlu
 *
 */
public interface RoleDAO extends BaseDAO<Role, Integer> {

	
	public Role getRoleByName(String name);
}
