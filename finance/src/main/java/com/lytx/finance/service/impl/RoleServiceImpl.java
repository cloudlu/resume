/**
 * 
 */
package com.lytx.finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lytx.finance.dao.RoleDAO;
import com.lytx.finance.service.RoleService;
import com.lytx.finance.service.exception.RoleNotExistsException;
import com.lytx.finance.vo.Role;

/**
 * @author cloud lu
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	
	private RoleDAO roleDAO;

    @Autowired
    @Qualifier("roleDAO")
    public void setRoleDAO(RoleDAO roleDAO) {
        this.dao = roleDAO;
        this.roleDAO = roleDAO;
    }
	
	/* (non-Javadoc)
	 * @see com.elulian.CustomerSecurityManagementSystem.service.IRoleService#getRoleByName(java.lang.String)
	 */
	public Role getRoleByName(String name) {
		return roleDAO.getRoleByName(name);
	}

}
