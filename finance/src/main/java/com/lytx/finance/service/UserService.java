package com.lytx.finance.service;

import java.util.Set;

import com.lytx.finance.service.exception.UserExistsException;
import com.lytx.finance.service.exception.UserNotExistsException;
import com.lytx.finance.vo.User;

public interface UserService extends BaseService<User, Integer>{

	
	public User getUserByName(String name);
	
	public boolean changeUserPassword(String username, String oldPassword, String newPassword) throws UserNotExistsException;

	public void registerUser(User user) throws UserExistsException;

	public Set<String> findRoles(String username) throws UserNotExistsException;

	public Set<String> findPermissions(String username) throws UserNotExistsException;
	
}
