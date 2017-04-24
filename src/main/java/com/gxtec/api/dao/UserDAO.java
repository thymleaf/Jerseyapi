package com.gxtec.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gxtec.api.bean.User;

public class UserDAO {

	public List<User> getUsers() {

		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from User");
		List<User> users = query.list();
		session.close();
		return users;
	}
}
