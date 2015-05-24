/**
 * 
 */
package com.lytx.finance.service;

import com.lytx.finance.service.exception.RoleNotExistsException;
import com.lytx.finance.vo.Role;

/**
 * @author cloud lu
 *
 */
public interface RoleService extends BaseService<Role, Integer> {
	
	public Role getRoleByName(String name);
}
