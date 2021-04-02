package com.example.finalproject.shop.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoriesDTO {
	private int id;
	private int id_categories;
	private String sub_categories;
	private int status;
	private CategoriesDTO categoriesDTO;
	private List<ProductDTO> productDTOs;
}
