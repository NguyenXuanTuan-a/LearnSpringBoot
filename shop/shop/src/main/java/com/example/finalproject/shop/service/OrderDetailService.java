package com.example.finalproject.shop.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.OrderDetailDao;
import com.example.finalproject.shop.entity.OrderDetailEntity;

import com.example.finalproject.shop.model.OrderDetailDTO;

public interface OrderDetailService {
	public List<OrderDetailDTO> getAll();
	public void add(OrderDetailDTO orderDetailDTO);
	public void update(OrderDetailDTO orderDetailDTO);
	public void delete(OrderDetailDTO orderDetailDTO);
	public OrderDetailDTO getOrderDetailById(int id);
	public List<OrderDetailDTO>getOrderDetailByIdUser(int user_id);
	public OrderDetailDTO getOrderDetailById_User(int user_id);
	Date stringToDate(String string) throws ParseException;
	String dateToString(Date date);
}

@Service
@Transactional
class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private CouponService couponService;
	@Override
	public List<OrderDetailDTO> getAll() {
		List<OrderDetailDTO> orderDetailDTOs = new ArrayList<OrderDetailDTO>();
		List<OrderDetailEntity> orderDetailEntities = orderDetailDao.getAll();
		for(OrderDetailEntity orderDetailEntity : orderDetailEntities) {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setId(orderDetailEntity.getId());
			orderDetailDTO.setUser(adminUserService.getAdminUserById(orderDetailEntity.getUser_id()));
			orderDetailDTO.setCouponDTO(couponService.getCouponById(orderDetailEntity.getCoupon_id()));
			orderDetailDTO.setAdded_on(orderDetailEntity.getAdded_on());
			orderDetailDTO.setAddress(orderDetailEntity.getAddress());
			orderDetailDTO.setTotal_price(orderDetailEntity.getTotal_price());
			orderDetailDTO.setCity(orderDetailEntity.getCity());
			orderDetailDTOs.add(orderDetailDTO);
			
		}
		return orderDetailDTOs;
	}

	@Override
	public void add(OrderDetailDTO orderDetailDTO) {
		Date date = new Date();
		OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
		orderDetailEntity.setUser_id(orderDetailDTO.getUser().getId());
		orderDetailEntity.setAddress(orderDetailDTO.getAddress());
		orderDetailEntity.setCity(orderDetailDTO.getCity());
		orderDetailEntity.setTotal_price(orderDetailDTO.getTotal_price());
		orderDetailEntity.setCoupon_id(1);
		orderDetailEntity.setAdded_on(date);
		
		orderDetailDao.add(orderDetailEntity);
		orderDetailDTO.setId(orderDetailEntity.getId());
	}

	@Override
	public void update(OrderDetailDTO orderDetailDTO) {
		
	}

	@Override
	public void delete(OrderDetailDTO orderDetailDTO) {
		
	}

	@Override
	public OrderDetailDTO getOrderDetailById(int id) {
		OrderDetailEntity orderDetailEntity = orderDetailDao.getOrderDetailById(id);
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setId(orderDetailEntity.getId());
		orderDetailDTO.setUser(adminUserService.getAdminUserById(orderDetailEntity.getUser_id()));
		orderDetailDTO.setCouponDTO(couponService.getCouponById(orderDetailEntity.getCoupon_id()));
		orderDetailDTO.setAdded_on(orderDetailEntity.getAdded_on());
		orderDetailDTO.setAddress(orderDetailEntity.getAddress());
		orderDetailDTO.setTotal_price(orderDetailEntity.getTotal_price());
		orderDetailDTO.setCity(orderDetailEntity.getCity());
		
		return orderDetailDTO;
	}

	@Override
	public List<OrderDetailDTO> getOrderDetailByIdUser(int user_id) {
		List<OrderDetailDTO> orderDetailDTOs = new ArrayList<OrderDetailDTO>();
		List<OrderDetailEntity> orderDetailEntities = orderDetailDao.getOrderDetailByIdUser(user_id);
		for(OrderDetailEntity orderDetailEntity : orderDetailEntities) {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setId(orderDetailEntity.getId());
			orderDetailDTO.setUser(adminUserService.getAdminUserById(orderDetailEntity.getUser_id()));
			orderDetailDTO.setCouponDTO(couponService.getCouponById(orderDetailEntity.getCoupon_id()));
			orderDetailDTO.setAdded_on(orderDetailEntity.getAdded_on() );
			orderDetailDTO.setAddress(orderDetailEntity.getAddress());
			orderDetailDTO.setTotal_price(orderDetailEntity.getTotal_price());
			orderDetailDTO.setCity(orderDetailEntity.getCity());
			orderDetailDTOs.add(orderDetailDTO);
			
		}
		return orderDetailDTOs;
	}

	@Override
	public OrderDetailDTO getOrderDetailById_User(int user_id) {
		OrderDetailEntity orderDetailEntity = orderDetailDao.getOrderDetailById_User(user_id);
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setId(orderDetailEntity.getId());
		orderDetailDTO.setUser(adminUserService.getAdminUserById(orderDetailEntity.getUser_id()));
		orderDetailDTO.setCouponDTO(couponService.getCouponById(orderDetailEntity.getCoupon_id()));
		orderDetailDTO.setAdded_on(orderDetailEntity.getAdded_on());
		orderDetailDTO.setAddress(orderDetailEntity.getAddress());
		orderDetailDTO.setTotal_price(orderDetailEntity.getTotal_price());
		orderDetailDTO.setCity(orderDetailEntity.getCity());
		
		return orderDetailDTO;
	}
	
	@Override
	public String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return dateFormat.format(date);
	}

	@Override
	public Date stringToDate(String string) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return dateFormat.parse(string);
	}

	
}
