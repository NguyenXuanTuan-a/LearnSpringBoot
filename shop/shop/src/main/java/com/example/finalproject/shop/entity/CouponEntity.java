package com.example.finalproject.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="coupon_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "coupon_code")
	private String coupon_code;
	@Column(name = "coupon_value")
	private int coupon_value;
	@Column(name="status")
	private int status;

}
