package com.rays.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.UnknownSqlResultSetMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAOHibImpl implements UserDAOInt {

	@Autowired
	private SessionFactory sessionfactory = null;

	public long add(UserDTO dto) {
		Session session = sessionfactory.getCurrentSession();
		long pk = (Long) session.save(dto);
		return pk;
	}

	public void delete(UserDTO dto) {
		Session session = sessionfactory.getCurrentSession();
		session.delete(dto);

	}

	public void update(UserDTO dto) {
		Session session = sessionfactory.getCurrentSession();
		session.update(dto);
	}

	public UserDTO findByPk(long id) {
		Session session = sessionfactory.getCurrentSession();
		UserDTO dto = session.get(UserDTO.class, id);
		return dto;
	}

	public UserDTO findByLogin(String login) {

		Session session = sessionfactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);
		UserDTO dto = null;
		criteria.add(Restrictions.eq("login", login));
		List list = criteria.list();
		dto = (UserDTO) list.get(1);
		return dto;
	}

	public UserDTO authenticate(String login, String password) {

		List list = null;
		UserDTO dto = null;

		Session session = sessionfactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", password));
		list = criteria.list();

		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		return dto;

	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		List list = null;

		Session session = sessionfactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);

		if (dto != null) {
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName()));
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);

		}

		list = criteria.list();
		System.out.println("list ===> " + list.size());
		return list;
	}

}
