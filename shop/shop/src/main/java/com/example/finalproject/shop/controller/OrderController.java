package com.example.finalproject.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalproject.shop.model.OrderDTO;
import com.example.finalproject.shop.model.OrderDetailDTO;
import com.example.finalproject.shop.service.OrderDetailService;
import com.example.finalproject.shop.service.OrderService;

@Controller
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public String order(HttpServletRequest request) {
		List<OrderDetailDTO> orderDetailDTOs = orderDetailService.getAll();
		request.setAttribute("order", orderDetailDTOs);
		return "admin/order";
	}
	
	@GetMapping("/orderdetail/{id}")
	public String orderDetail(HttpServletRequest request,@PathVariable(name = "id")int id) {
		List<OrderDTO> orderDTOs = orderService.getOrderById_OrderDeatil(id);
		request.setAttribute("orderdetail", orderDTOs);
		return "admin/orderdetail";
	}
}
