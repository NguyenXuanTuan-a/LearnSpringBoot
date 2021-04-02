package com.example.finalproject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
	private int id;
	private String coupon_code;
	private int coupon_value;
	private int status;
}
