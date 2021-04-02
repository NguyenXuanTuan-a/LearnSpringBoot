package com.example.finalproject.shop.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDTO {
	private int id;
	private String categories;
	private int status;
	private List<SubCategoriesDTO>subCategoriesDTOs;
	private List<ProductDTO>productDTOs;
}
