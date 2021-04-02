package com.example.finalproject.shop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.entity.CategoriesEntity;
import com.example.finalproject.shop.model.CategoriesDTO;

public interface CategoriesDao {
		public List<CategoriesEntity> getAll();
		public void addCa(CategoriesEntity categoriesEntity);
		public void update(CategoriesEntity categoriesEntity);
		public void delete(CategoriesEntity categoriesEntity);
		public CategoriesEntity getCategoriesById(int id);
		public CategoriesEntity getCategoriesById_categories(int id_categories);
}

@Repository
@Transactional
class CategoriesDaoImpl implements CategoriesDao{
	
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<CategoriesEntity> getAll() {
		String jql ="Select c from CategoriesEntity c";
		return entityManager.createQuery(jql, CategoriesEntity.class).getResultList();
	}
	@Override
	public void addCa(CategoriesEntity categoriesEntity) {
		entityManager.persist(categoriesEntity);	
	}
	@Override
	public void update(CategoriesEntity categoriesEntity) {
		entityManager.merge(categoriesEntity);
		
	}
	@Override
	public void delete(CategoriesEntity categoriesEntity) {
		entityManager.remove(categoriesEntity);
		
	}
	@Override
	public CategoriesEntity getCategoriesById(int id) {
		return entityManager.find(CategoriesEntity.class, id);
	}
	@Override
	public CategoriesEntity getCategoriesById_categories(int id_categories) {
		return entityManager.find(CategoriesEntity.class, id_categories);
	}
	
	
}
