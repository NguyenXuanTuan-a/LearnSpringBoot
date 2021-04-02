package com.example.finalproject.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.finalproject.shop.model.CouponDTO;
import com.example.finalproject.shop.service.CouponService;

@Controller
@RequestMapping("/admin")
public class CouponController {
	@Autowired
	private CouponService couponService;
	
	@GetMapping("coupon")
	public String getCoupon(HttpServletRequest request) {
		List<CouponDTO> couponDTOs = couponService.getAll();
		request.setAttribute("coupon", couponDTOs);
		return "admin/coupon";
	}
	@GetMapping("/couponcheck/{id}")
	public String check(HttpServletRequest request,@PathVariable(name="id")int id) {
		CouponDTO couponDTO = couponService.getCouponById(id);
		int a = couponDTO.getStatus();
		if(a==1) {
			a=0;
		}else {
			a=1;
		}
		couponDTO.setStatus(a);
		couponService.update(couponDTO);
		return "redirect:/admin/coupon";
	}
}
