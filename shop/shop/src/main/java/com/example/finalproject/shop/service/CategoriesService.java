package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.CategoriesDao;
import com.example.finalproject.shop.entity.CategoriesEntity;
import com.example.finalproject.shop.entity.ProductEntity;
import com.example.finalproject.shop.entity.SubCategoriesEntity;
import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.model.ProductDTO;
import com.example.finalproject.shop.model.SubCategoriesDTO;

public interface CategoriesService {
	public List<CategoriesDTO> getAll();
	public void add(CategoriesDTO categoriesDTO);
	public void update(CategoriesDTO categoriesDTO);
	public void delete(CategoriesDTO categoriesDTO);
	public CategoriesDTO getCategoriesById(int id);
	public CategoriesDTO getListProduct(int id_categories);
}

@Service
@Transactional
class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	private CategoriesDao categoriesDao; 
	@Override
	public List<CategoriesDTO> getAll() {
		List<CategoriesEntity> caE = categoriesDao.getAll();
		List<CategoriesDTO>cadtos = new ArrayList<CategoriesDTO>();
		for(CategoriesEntity ca : caE) {
			CategoriesDTO cadto = new CategoriesDTO();
			cadto.setId(ca.getId());
			cadto.setCategories(ca.getCategories());
			cadto.setStatus(ca.getStatus());
			
			List<SubCategoriesDTO> sublist = new ArrayList<SubCategoriesDTO>();
			for(SubCategoriesEntity e : ca.getSubcates()) {
				SubCategoriesDTO subdto = new SubCategoriesDTO();
				subdto.setId(e.getId());
				subdto.setId_categories(e.getCategoriesE().getId());
				CategoriesDTO categoriesdto = new CategoriesDTO();
				categoriesdto.setId(e.getCategoriesE().getId());
				categoriesdto.setCategories(e.getCategoriesE().getCategories());
				subdto.setCategoriesDTO(categoriesdto);
				subdto.setSub_categories(e.getSub_categories());
				subdto.setStatus(e.getStatus());
				sublist.add(subdto);
			}
			List<ProductDTO> proList = new ArrayList<ProductDTO>();
			for(ProductEntity pro : ca.getProductE()) {
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
				proList.add(productDTO);
			}
			cadto.setProductDTOs(proList);
			cadto.setSubCategoriesDTOs(sublist);
			cadtos.add(cadto);
		}
		
		return cadtos;
	}
	@Override
	public void add(CategoriesDTO categoriesDTO) {
		CategoriesEntity caE = new CategoriesEntity();
		caE.setCategories(categoriesDTO.getCategories());
		caE.setStatus(categoriesDTO.getStatus());
		categoriesDao.addCa(caE);
		
	}
	@Override
	public void update(CategoriesDTO categoriesDTO) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(categoriesDTO.getId());
		if(cae != null) {
			cae.setCategories(categoriesDTO.getCategories());
			cae.setStatus(categoriesDTO.getStatus());
			categoriesDao.update(cae);
		}
		
	}
	@Override
	public void delete(CategoriesDTO categoriesDTO) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(categoriesDTO.getId());
		if(cae != null) {
			categoriesDao.delete(cae);
		}
		
	}
	@Override
	public CategoriesDTO getCategoriesById(int id) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(id);
		CategoriesDTO cadto = new CategoriesDTO();
		cadto.setId(cae.getId());
		cadto.setCategories(cae.getCategories());
		cadto.setStatus(cae.getStatus());
		return cadto;
	}
	@Override
	public CategoriesDTO getListProduct(int id_categories) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(id_categories);
		CategoriesDTO cadto = new CategoriesDTO();
		cadto.setId(cae.getId());
		cadto.setCategories(cae.getCategories());
		cadto.setStatus(cae.getStatus());
		List<ProductDTO> proList = new ArrayList<ProductDTO>();
		for(ProductEntity pro : cae.getProductE()) {
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
			proList.add(productDTO);
		}
		cadto.setProductDTOs(proList);
		return cadto;
	}	
}
