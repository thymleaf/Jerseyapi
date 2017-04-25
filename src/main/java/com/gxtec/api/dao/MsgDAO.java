package com.gxtec.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.gxtec.api.bean.Message;

@SuppressWarnings("unchecked")
public class MsgDAO {

	public List<Message> getMessages(int type)
	{
		Session session = SessionUtil.getSession();
		Query<Message> query = session.createQuery("from Message where status=:type")
				.setParameter("type", String.valueOf(type));
		List<Message> messages = query.getResultList();
		session.close();
		return messages;
	}
}
