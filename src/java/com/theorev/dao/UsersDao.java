package com.theorev.dao;

import java.util.List;

import org.hibernate.Query;

import com.theorev.model.Users;
import com.theorev.service.OdontoSoftService;
import com.theorev.util.HibernateUtil;

public class UsersDao extends OdontoSoftService<Users> {

	@Override
	public Users findById(Object... valrs) throws Exception {
		Users user = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT u FROM Users u WHERE u.username = :user AND u.password = :pass");
			query.setParameter("user", valrs[0]);
			query.setParameter("pass", valrs[1]);
			user = (Users) query.uniqueResult();
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findBy(Object... valrs) throws Exception {
		List<Users> lista = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT u FROM Users u WHERE u.username = :user AND u.password = :pass");
			query.setParameter("user", valrs[0]);
			query.setParameter("pass", valrs[1]);
			lista = query.list();
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return lista;
	}

}
