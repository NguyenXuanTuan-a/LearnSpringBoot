package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.ProductDao;
import com.example.finalproject.shop.entity.CategoriesEntity;
import com.example.finalproject.shop.entity.ProductEntity;
import com.example.finalproject.shop.entity.SubCategoriesEntity;
import com.example.finalproject.shop.model.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getAll();
	public void add(ProductDTO productDTO);
	public void delete(ProductDTO productDTO);
	public ProductDTO getById(int id);
	public void update(ProductDTO productDTO);
}

@Service
@Transactional
class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	@Override
	public List<ProductDTO> getAll() {
		List<ProductEntity> productEntities = productDao.getAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(ProductEntity pro : productEntities) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(pro.getId());
			productDTO.setId_categories(pro.getCategoriesEntity().getId());
			productDTO.setSub_id_categories(pro.getSubCategoriesE().getId());
			productDTO.setName(pro.getName());
			productDTO.setImage(pro.getImage());
			productDTO.setMrp(pro.getMrp());
			productDTO.setPrice(pro.getPrice());
			productDTO.setQty(pro.getQty());
			productDTO.setShort_desc(pro.getShort_desc());
			productDTO.setDescription(pro.getDescription());
			productDTO.setBest_seller(pro.getBest_seller());
			productDTO.setMeta_desc(pro.getMeta_desc());
			productDTO.setMeta_keyword(pro.getMeta_keyword());
			productDTO.setMeta_title(pro.getMeta_title());
			productDTO.setStatus(pro.getStatus());
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	@Override
	public void add(ProductDTO productDTO) {
		ProductEntity productEntity = new ProductEntity();
		
		CategoriesEntity categoriesEntity = new CategoriesEntity();
		categoriesEntity.setId(productDTO.getCategoriesDTO().getId());
		productEntity.setCategoriesEntity(categoriesEntity);
		
		SubCategoriesEntity subCategoriesEntity = new SubCategoriesEntity();
		subCategoriesEntity.setId(productDTO.getSubCategoriesDTO().getId());
		productEntity.setSubCategoriesE(subCategoriesEntity);
		
		productEntity.setName(productDTO.getName());
		productEntity.setImage(productDTO.getImage());
		productEntity.setMrp(productDTO.getMrp());
		productEntity.setPrice(productDTO.getPrice());
		productEntity.setQty(productDTO.getQty());
		productEntity.setShort_desc(productDTO.getShort_desc());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setBest_seller(productDTO.getBest_seller());
		productEntity.setMeta_desc(productDTO.getMeta_desc());
		productEntity.setMeta_keyword(productDTO.getMeta_keyword());
		productEntity.setMeta_title(productDTO.getMeta_title());
		productEntity.setStatus(productDTO.getStatus());
		productDao.add(productEntity);
	}
	@Override
	public void delete(ProductDTO productDTO) {
		ProductEntity productEntity = productDao.getProductById(productDTO.getId());
		if(productEntity != null) {
			productDao.delete(productEntity);
		}
		
	}
	@Override
	public ProductDTO getById(int id) {
		ProductEntity productEntity = productDao.getProductById(id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productEntity.getId());
		productDTO.setId_categories(productEntity.getCategoriesEntity().getId());
		productDTO.setSub_id_categories(productEntity.getSubCategoriesE().getId());
		productDTO.setName(productEntity.getName());
		productDTO.setImage(productEntity.getImage());
		productDTO.setMrp(productEntity.getMrp());
		productDTO.setPrice(productEntity.getPrice());
		productDTO.setQty(productEntity.getQty());
		productDTO.setShort_desc(productEntity.getShort_desc());
		productDTO.setDescription(productEntity.getDescription());
		productDTO.setBest_seller(productEntity.getBest_seller());
		productDTO.setMeta_desc(productEntity.getMeta_desc());
		productDTO.setMeta_keyword(productEntity.getMeta_keyword());
		productDTO.setMeta_title(productEntity.getMeta_title());
		productDTO.setStatus(productEntity.getStatus());
		return productDTO;
	}
	@Override
	public void update(ProductDTO productDTO) {
		ProductEntity productEntity = productDao.getProductById(productDTO.getId());
		if(productEntity != null) {
			/*
			 * CategoriesEntity categoriesEntity = new CategoriesEntity();
			 * categoriesEntity.setId(productDTO.getCategoriesDTO().getId());
			 * productEntity.setCategoriesEntity(categoriesEntity);
			 * 
			 * SubCategoriesEntity subCategoriesEntity = new SubCategoriesEntity();
			 * subCategoriesEntity.setId(productDTO.getSubCategoriesDTO().getId());
			 * productEntity.setSubCategoriesE(subCategoriesEntity);
			 */
			
			productEntity.setName(productDTO.getName());
			productEntity.setImage(productDTO.getImage());
			productEntity.setMrp(productDTO.getMrp());
			productEntity.setPrice(productDTO.getPrice());
			productEntity.setQty(productDTO.getQty());
			productEntity.setShort_desc(productDTO.getShort_desc());
			productEntity.setDescription(productDTO.getDescription());
			productEntity.setBest_seller(productDTO.getBest_seller());
			productEntity.setMeta_desc(productDTO.getMeta_desc());
			productEntity.setMeta_keyword(productDTO.getMeta_keyword());
			productEntity.setMeta_title(productDTO.getMeta_title());
			productEntity.setStatus(productDTO.getStatus());
			productDao.update(productEntity);
		}
		
	}
}
