package com.rays.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAOHibImpl implements UserDAOInt {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public long add(UserDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		long pk = (long) session.save(dto);
		return pk;

	}

	@Override
	public void update(UserDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		session.update(dto);

	}

	@Override
	public UserDTO delete(long id) {

		Session session = sessionFactory.getCurrentSession();
		UserDTO dto = findByPk(id);
		session.delete(dto);
		return dto;
	}

	@Override
	public UserDTO findByPk(long pk) {
		Session session = sessionFactory.getCurrentSession();
		UserDTO dto = session.get(UserDTO.class, pk);
		return dto;
	}

	@Override
	public UserDTO authenticate(String login, String password) {

		List list = null;
		UserDTO dto = null;

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", password));

		list = criteria.list();

		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		return dto;

	}

	@Override
	public UserDTO findByLogin(String login) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);
		UserDTO dto = null;
		criteria.add(Restrictions.eq("login", login));
		List list = criteria.list();
		dto = (UserDTO) list.get(0);
		return dto;
	}

	@Override
	public List search(UserDTO dto, int pageNo, int pageSize) {

		List list = null;

		Session session = sessionFactory.getCurrentSession();
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

		return list = criteria.list();

	}

}
