package com.example.finalproject.shop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.entity.CouponEntity;



public interface CouponDao {
	public List<CouponEntity> getAll();
	public void add(CouponEntity couponEntity);
	public void update(CouponEntity couponEntity);
	public void delete(CouponEntity couponEntity);
	public CouponEntity getCouponById(int id);
}

@Repository
@Transactional
class CouponDaoImpl implements CouponDao{
	
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<CouponEntity> getAll() {
		String jql="Select c from CouponEntity c";
		return entityManager.createQuery(jql,CouponEntity.class).getResultList();
	}

	@Override
	public void add(CouponEntity couponEntity) {
		entityManager.persist(couponEntity);
		
	}

	@Override
	public void update(CouponEntity couponEntity) {
		entityManager.merge(couponEntity);
		
	}

	@Override
	public void delete(CouponEntity couponEntity) {
		entityManager.remove(couponEntity);
		
	}

	@Override
	public CouponEntity getCouponById(int id) {
		
		return entityManager.find(CouponEntity.class, id);
	}
	
}
