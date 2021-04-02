package com.example.finalproject.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GenerationType;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String categories;
	private int status;
	@OneToMany(mappedBy = "categoriesE",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<SubCategoriesEntity> subcates;
	
	@OneToMany(mappedBy = "categoriesEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ProductEntity> productE;
}
