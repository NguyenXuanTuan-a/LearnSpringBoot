package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.OrderDao;
import com.example.finalproject.shop.entity.OrderDetailEntity;
import com.example.finalproject.shop.entity.OrderEntity;
import com.example.finalproject.shop.entity.ProductEntity;
import com.example.finalproject.shop.model.OrderDTO;
import com.example.finalproject.shop.model.OrderDetailDTO;
import com.example.finalproject.shop.model.ProductDTO;



public interface OrderService {
	public List<OrderDTO> getAll();
	public List<OrderDTO>getOrderById_OrderDeatil(int id_orderdetail);
	public void addOrder(OrderDTO orderDTO);
	public void updateOrder(OrderDTO orderDTO);
	public void delete(OrderDTO orderDTO);
	public OrderDTO getById(int id);
}

@Transactional
@Service
class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductService productService;
	@Override
	public List<OrderDTO> getAll() {
		List<OrderEntity> orderEntitys = orderDao.getAll();
		List<OrderDTO> orderDtos = new ArrayList<OrderDTO>();
		for(OrderEntity orderEntity : orderEntitys) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setId(orderEntity.getId());
			orderDTO.setPrice(orderEntity.getPrice());
			orderDTO.setQty(orderEntity.getQty());
			
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(orderEntity.getProduct_id());
			orderDTO.setProductDTO(productDTO);
			
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setId(orderEntity.getId_orderdetail());
			orderDTO.setOrderDetailDTO(orderDetailDTO);
			
			orderDtos.add(orderDTO);
		}
		return orderDtos;
	}

	@Override
	public void addOrder(OrderDTO orderDTO) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setPrice(orderDTO.getPrice());
		orderEntity.setQty(orderDTO.getQty());
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(orderDTO.getProductDTO().getId());
		orderEntity.setProduct_id(productEntity.getId());
		
		
		orderEntity.setId_orderdetail(orderDTO.getOrderDetailDTO().getId());
		
		orderDao.addOrder(orderEntity);
		
	}

	@Override
	public void updateOrder(OrderDTO orderDTO) {
		
	}

	@Override
	public void delete(OrderDTO orderDTO) {
		
	}

	@Override
	public OrderDTO getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> getOrderById_OrderDeatil(int id_orderdetail) {
		List<OrderEntity> orderEntitys = orderDao.getOrderById_OrderDeatil(id_orderdetail);
		List<OrderDTO> orderDtos = new ArrayList<OrderDTO>();
		for(OrderEntity orderEntity : orderEntitys) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setId(orderEntity.getId());
			orderDTO.setPrice(orderEntity.getPrice());
			orderDTO.setQty(orderEntity.getQty());
			
			
			orderDTO.setProductDTO(productService.getById(orderEntity.getProduct_id()));
			
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setId(orderEntity.getId_orderdetail());
			orderDTO.setOrderDetailDTO(orderDetailDTO);
			
			orderDtos.add(orderDTO);
		}
		return orderDtos;
	}
	
}
