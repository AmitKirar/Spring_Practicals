package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.service.AttachmentService;

@Repository
public class UserDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public RoleDAO roleDao;

	@Autowired
	AttachmentService attachmentService;

	public void populate(UserDTO dto) {
		RoleDTO roleDto = roleDao.findByPk(dto.getRoleId());
		dto.setRoleName(roleDto.getName());

//		if (dto.getId() != null && dto.getId() > 0) {
//			UserDTO userData = findByPk(dto.getId());
//		}
	}

	public long add(UserDTO dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(UserDTO dto) {

		populate(dto);
		entityManager.merge(dto);
	}

	public void delete(UserDTO dto) {
		AttachmentDTO attachemtDto = attachmentService.findById(dto.getImageId());
		if (attachemtDto != null) {
			attachmentService.delete(attachemtDto.getId());
		}
		entityManager.remove(dto);
	}

	public UserDTO findByPk(long pk) {
		UserDTO dto = entityManager.find(UserDTO.class, pk);
		return dto;
	}
	
	public UserDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);

		List<UserDTO> list = tq.getResultList();

		UserDTO dto = null;

		if (list.size() > 0) {

			dto = list.get(0);

		}

		return dto;
	}


}
