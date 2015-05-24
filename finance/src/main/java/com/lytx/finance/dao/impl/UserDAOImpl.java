/**
 * 
 */
package com.lytx.finance.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lytx.finance.dao.UserDAO;
import com.lytx.finance.service.exception.UserNotExistsException;
import com.lytx.finance.vo.User;

/**
 * @author cloudlu
 *
 */
@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<User, Integer> implements
UserDAO {

private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public User getUserByName(String name) {
		@SuppressWarnings("unchecked")
		List<User> list = getSession().getNamedQuery("findUserByName").setString("username", name).list();
		
		if(list.isEmpty())
			return null;
		return list.get(0);
	}

	/*@Override
	public long getTotalCount() {
		StringBuffer hql = new StringBuffer(
				"select userInfo from UserInfo userInfo where ");
		boolean flag = false;
		if (condition.getSearchBranch() != null
				&& condition.getSearchBranch().length() != 0
				&& (!condition.getSearchBranch().equalsIgnoreCase("ALL"))) {
			flag = true;
			hql.append("userInfo.branch like '%" + condition.getSearchBranch()
					+ "%' ");
		}
		if (condition.getUsername() != null
				&& condition.getUsername().length() != 0) {
			if (flag)
				hql.append(" and ");
			flag = true;
			hql.append("userInfo.username = '" + condition.getUsername() + "' ");
		}
		if (null != condition.getRealname()
				&& 0 != condition.getRealname().length()) {
			if (flag)
				hql.append(" and ");
			flag = true;
			hql.append("userInfo.realname = '" + condition.getRealname() + "' ");
		}
		if (!flag) {
			hql = new StringBuffer(" select userInfo from UserInfo userInfo ");
		}
		return super.getTotalCount(hql.toString(), "userInfo");
	}

	*//**
	 * @param condition should not be null
	 * @return Result list (UserInfo), list is empty if no result found
	 *//*
	@Override
	public List<User> getUserInfoByCondition(Condition condition) {
		boolean flag = false;
		StringBuffer hql = new StringBuffer(
				"select userInfo from UserInfo userInfo where ");
		if (condition.getSearchBranch() != null
				&& condition.getSearchBranch().length() != 0
				&& (!condition.getSearchBranch().equalsIgnoreCase("ALL"))) {
			flag = true;
			hql.append("userInfo.branch like '%" + condition.getSearchBranch()
					+ "%' ");
		}
		if (condition.getUsername() != null
				&& condition.getUsername().length() != 0) {
			if (flag)
				hql.append(" and ");
			flag = true;
			hql.append("userInfo.username = '" + condition.getUsername() + "' ");
		}
		if (condition.getRealname() != null
				&& condition.getRealname().length() != 0) {
			if (flag)
				hql.append(" and ");
			flag = true;
			hql.append("userInfo.realname = '" + condition.getRealname() + "' ");
		}
		if (!flag) {
			hql = new StringBuffer(
					" select userInfo from UserInfo userInfo order by userInfo.branch desc");
		} else {
			hql.append(" order by userInfo.branch desc");
		}
		logger.debug("UserInfoDAO user condition to hql----> " + hql);
		return super.getOnePage(hql.toString(), condition.getStartRow(),
				condition.getMaxRow());
	}*/

}
