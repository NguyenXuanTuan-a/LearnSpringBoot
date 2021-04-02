package com.example.finalproject.shop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.finalproject.shop.entity.AdminUserEntity;

public interface AdminUserDao {
	public AdminUserEntity getAdminUser(String username);
	public void add(AdminUserEntity adminUserEntity);
	public void delete(AdminUserEntity adminUserEntity);
	public AdminUserEntity getAdminUserById(int id);
	public List<AdminUserEntity> getAll();
}

@Repository
class AdminUserDaoImpl implements AdminUserDao{
	@Autowired
	private EntityManager entityManager ;
	@Override
	public AdminUserEntity getAdminUser(String username) {
		String jql = "Select u from AdminUserEntity u where u.username = :username";
		return entityManager.createQuery(jql,AdminUserEntity.class).setParameter("username", username).getSingleResult();
	}
	@Override
	public void add(AdminUserEntity adminUserEntity) {
		entityManager.persist(adminUserEntity);
	}
	@Override
	public void delete(AdminUserEntity adminUserEntity) {
		entityManager.remove(adminUserEntity);
		
	}
	@Override
	public AdminUserEntity getAdminUserById(int id) {
		return entityManager.find(AdminUserEntity.class, id);
	}
	@Override
	public List<AdminUserEntity> getAll() {
		String jql= "Select u from AdminUserEntity u";
		return entityManager.createQuery(jql,AdminUserEntity.class).getResultList();
	}

}
