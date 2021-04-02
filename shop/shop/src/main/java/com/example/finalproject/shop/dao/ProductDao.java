package com.example.finalproject.shop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.entity.ProductEntity;

public interface ProductDao {
	public List<ProductEntity> getAll();
	public void add(ProductEntity productEntity);
	public void delete(ProductEntity productEntity);
	public ProductEntity getProductById(int id);
	public void update(ProductEntity productEntity);
}

@Repository
@Transactional
class ProductDaoImpl implements ProductDao{

	@Autowired
	private EntityManager entityManager;
	@Override
	public List<ProductEntity> getAll() {
		String jql ="Select p from ProductEntity p";
		return entityManager.createQuery(jql,ProductEntity.class).getResultList();
	}
	@Override
	public void add(ProductEntity productEntity) {
		entityManager.persist(productEntity);
		
	}
	@Override
	public void delete(ProductEntity productEntity) {
		entityManager.remove(productEntity);
		
	}
	@Override
	public ProductEntity getProductById(int id) {
		return entityManager.find(ProductEntity.class, id);
	}
	@Override
	public void update(ProductEntity productEntity) {
		entityManager.merge(productEntity);
		
	}
}
