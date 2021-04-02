package com.example.finalproject.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private int id;
	private AdminUserDTO user;
	private CouponDTO couponDTO;
	private String address;
	private String city;
	private float total_price;
	private Date added_on;
	
	
}
