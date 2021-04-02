package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.CouponDao;
import com.example.finalproject.shop.entity.CouponEntity;
import com.example.finalproject.shop.model.CouponDTO;

public interface CouponService {
	public List<CouponDTO> getAll();
	public void add(CouponDTO couponDTO);
	public void update(CouponDTO couponDTO);
	public void delete(CouponDTO couponDTO);
	public CouponDTO getCouponById(int id);
}

@Service
@Transactional
class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponDao couponDao;
	@Override
	public List<CouponDTO> getAll() {
		List<CouponEntity> couponEntities = couponDao.getAll();
		List<CouponDTO> couponDTOs = new ArrayList<CouponDTO>();
		for(CouponEntity couponEntity : couponEntities) {
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setId(couponEntity.getId());
			couponDTO.setCoupon_code(couponEntity.getCoupon_code());
			couponDTO.setCoupon_value(couponEntity.getCoupon_value());
			couponDTO.setStatus(couponEntity.getStatus());
			
			couponDTOs.add(couponDTO);
		}
		return couponDTOs;
	}

	@Override
	public void add(CouponDTO couponDTO) {
		
		
	}

	@Override
	public void update(CouponDTO couponDTO) {
		CouponEntity couponEntity = new CouponEntity();
		couponEntity.setId(couponDTO.getId());
		couponEntity.setCoupon_code(couponDTO.getCoupon_code());
		couponEntity.setCoupon_value(couponDTO.getCoupon_value());
		couponEntity.setStatus(couponDTO.getStatus());
		couponDao.update(couponEntity);
		
	}

	@Override
	public void delete(CouponDTO couponDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CouponDTO getCouponById(int id) {
		CouponEntity couponEntity = couponDao.getCouponById(id);
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setId(couponEntity.getId());
		couponDTO.setCoupon_code(couponEntity.getCoupon_code());
		couponDTO.setCoupon_value(couponEntity.getCoupon_value());
		couponDTO.setStatus(couponEntity.getStatus());
		return couponDTO;
	}
	
}
