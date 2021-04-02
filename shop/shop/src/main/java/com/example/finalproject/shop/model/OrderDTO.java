package com.example.finalproject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private int id;
	private int qty;
	private float price;
	private ProductDTO productDTO;
	private OrderDetailDTO orderDetailDTO;
}
