package com.gxtec.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.gxtec.api.bean.User;

@SuppressWarnings("unchecked")
public class UserDAO {

	public List<User> getUsers() {

		Session session = SessionUtil.getSession();
		Query<User> query = session.createQuery("from User");
		List<User> users = query.getResultList();
		session.close();
		return users;
	}
	
	public User queryByName(String name, String password)
	{
		Session session = SessionUtil.getSession();
		Query<User> query = session.createQuery(
				"from User " +
				"where name=:name and " + 
				"password=:password")
				.setParameter("name", name)
				.setParameter("password", password);
		System.out.println(query.getQueryString());
		User user = query.uniqueResult();
		session.close();
		return user;
	}
}
