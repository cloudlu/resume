package com.lytx.finance.service.impl;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lytx.finance.dao.UserDAO;
import com.lytx.finance.service.UserService;
import com.lytx.finance.service.exception.UserExistsException;
import com.lytx.finance.service.exception.UserNotExistsException;
import com.lytx.finance.vo.Role;
import com.lytx.finance.vo.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements
		UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDAO userDAO;

    @Autowired
    @Qualifier("userDAO")
    public void setUserDAO(UserDAO userDAO) {
        this.dao = userDAO;
        this.userDAO = userDAO;
    }
    
	/*
	 * protected IUserInfoDAO getUserInfoDAO() { if (userInfoDAO == null) {
	 * userInfoDAO = DAOFactory.getDAOFacotry().getIUserInfoDAO(); } return
	 * userInfoDAO; }
	 */

	

	public User getUserByName(String name) {
		return userDAO.getUserByName(name);
	}

	/*@Override
	@RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
	public UserInfo getUserInfoByUserId(String userId) {
		return userInfoDAO.getUserInfoByUserId(userId);
	}*/

	//@Transactional
	public User save(User user) { //throws ExistsException, DataMissingException{
		
		if(null == user.getId()){
			/* new user, need to encode password */
			encodeUserPassword(user);
		}
		
		/*
		 * before save userinfo in userinforservice +++++++++++++++++++++++++++++++
		3724  mysql  TRACE  [main] openjpa.jdbc.SQL - <t 327916146, conn 1227641731> [1 ms] spent
		after save userinfo in userinforservice +++++++++++++++++++++++++++++++
		Exception-=============------------class org.springframework.dao.InvalidDataAccessApiUsageException
		exception happens after the save function call, seems the commit action controlled by transactional
		happens after ths save method, so try to catch exception inside the save functio is not reasonable.
		If we need to do this, we have to make sure the save function in dao layer happens during the function
		call. To make this happen, call flush function inside dao save function.
		 */
			
		return  super.save(user);
		
		/* } catch (ExistsException e){
			 throw new UserExistsException(userInfo.getUsername() + "or" + userInfo.getEmail() + "exists", e);
		 }*/
	}

	private void encodeUserPassword(User user) {
		//logger.debug("before encode: " + userInfo.getPassword());
		
		//logger.debug("after encode: " + userInfo.getPassword());
	}

	public boolean changeUserPassword(String username, String oldPassword,
			String newPassword) throws UserNotExistsException {
		
		User user = getUserByName(username);
		
		if(null == user){
			logger.error(username + "not exists");
			throw new UserNotExistsException();
		}
		
		
		
		logger.warn("invalid password for user: " + username);
		
		return false;
		
	}

	public void registerUser(User user) throws UserExistsException {
		if(null != getUserByName(user.getUsername())){
			throw new UserExistsException(user.getUsername());
		}
		save(user);
	}

	public Set<String> findRoles(String username) throws UserNotExistsException {
		User user = getUserByName(username);
		
		if(null == user){
			logger.error(username + "not exists");
			throw new UserNotExistsException();
		}
		
		Set<String> roles = new HashSet<String>();
		for(Role role : user.getRoles()){
			roles.add(role.getName());
		}
		return roles;
	}

	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
