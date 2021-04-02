package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.SubCategoriesDao;
import com.example.finalproject.shop.entity.CategoriesEntity;
import com.example.finalproject.shop.entity.ProductEntity;
import com.example.finalproject.shop.entity.SubCategoriesEntity;
import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.model.ProductDTO;
import com.example.finalproject.shop.model.SubCategoriesDTO;

public interface SubCategoriesService {
	public List<SubCategoriesDTO> getAll();
	public SubCategoriesDTO getSubById(int id);
	public void update(SubCategoriesDTO subCategoriesDTO);
	public void add(SubCategoriesDTO subCategoriesDTO);
	public void delete(SubCategoriesDTO subCategoriesDTO);
	public SubCategoriesDTO getListProduct(int id_subcategories);

}

@Service
@Transactional
class SubCategoriesServiceImpl implements SubCategoriesService{
	@Autowired
	private SubCategoriesDao subCategoriesDao;
	
	@Override
	public List<SubCategoriesDTO> getAll() {
		List<SubCategoriesEntity> subcaE = subCategoriesDao.getAll();
		List<SubCategoriesDTO> subDtos = new ArrayList<SubCategoriesDTO>();
		for(SubCategoriesEntity subca : subcaE) {
			SubCategoriesDTO subdto = new SubCategoriesDTO();
			subdto.setId(subca.getId());
			subdto.setId_categories(subca.getCategoriesE().getId());
			CategoriesDTO categoriesdto = new CategoriesDTO();
			categoriesdto.setId(subca.getCategoriesE().getId());
			categoriesdto.setCategories(subca.getCategoriesE().getCategories());
			subdto.setCategoriesDTO(categoriesdto);
			subdto.setSub_categories(subca.getSub_categories());
			subdto.setStatus(subca.getStatus());
			subDtos.add(subdto);
			
		}
		return subDtos;
	}

	@Override
	public SubCategoriesDTO getSubById(int id) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(id);
		SubCategoriesDTO subCategoriesDTO = new SubCategoriesDTO();
		subCategoriesDTO.setId(subCategoriesEntity.getId());
		CategoriesDTO categoriesdto = new CategoriesDTO();
		categoriesdto.setId(subCategoriesEntity.getCategoriesE().getId());
		categoriesdto.setCategories(subCategoriesEntity.getCategoriesE().getCategories());
		subCategoriesDTO.setCategoriesDTO(categoriesdto);
		subCategoriesDTO.setStatus(subCategoriesEntity.getStatus());
		subCategoriesDTO.setSub_categories(subCategoriesEntity.getSub_categories());
		return subCategoriesDTO;
	}

	@Override
	public void update(SubCategoriesDTO subCategoriesDTO) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(subCategoriesDTO.getId());
		if(subCategoriesDTO != null) {
			CategoriesEntity categoriesE = new CategoriesEntity();
			categoriesE.setId(subCategoriesDTO.getCategoriesDTO().getId());
			categoriesE.setCategories(subCategoriesDTO.getCategoriesDTO().getCategories());
			subCategoriesEntity.setCategoriesE(categoriesE);
			subCategoriesEntity.setStatus(subCategoriesDTO.getStatus());
			subCategoriesEntity.setSub_categories(subCategoriesDTO.getSub_categories());
			subCategoriesDao.update(subCategoriesEntity);
		}
		
	}

	@Override
	public void add(SubCategoriesDTO subCategoriesDTO) {
		SubCategoriesEntity subCategoriesEntity = new SubCategoriesEntity();
		subCategoriesEntity.setSub_categories(subCategoriesDTO.getSub_categories());
		subCategoriesEntity.setStatus(subCategoriesDTO.getStatus());
		CategoriesEntity categoriesE = new CategoriesEntity();
		categoriesE.setId(subCategoriesDTO.getCategoriesDTO().getId());
		subCategoriesEntity.setCategoriesE(categoriesE);
		subCategoriesDao.add(subCategoriesEntity); 
		
		
	}

	@Override
	public void delete(SubCategoriesDTO subCategoriesDTO) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(subCategoriesDTO.getId());
		if(subCategoriesEntity != null) {
			subCategoriesDao.delete(subCategoriesEntity);
		}
		
	}

	@Override
	public SubCategoriesDTO getListProduct(int id_subcategories) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(id_subcategories);
		SubCategoriesDTO subCategoriesDTO = new SubCategoriesDTO();
		subCategoriesDTO.setId(subCategoriesEntity.getId());
		
		CategoriesDTO categoriesdto = new CategoriesDTO();
		categoriesdto.setId(subCategoriesEntity.getCategoriesE().getId());
		categoriesdto.setCategories(subCategoriesEntity.getCategoriesE().getCategories());
		subCategoriesDTO.setCategoriesDTO(categoriesdto);
		
		subCategoriesDTO.setStatus(subCategoriesEntity.getStatus());
		subCategoriesDTO.setSub_categories(subCategoriesEntity.getSub_categories());
		List<ProductDTO> proList = new ArrayList<ProductDTO>();
		for(ProductEntity pro : subCategoriesEntity.getProductEntities()) {
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
		subCategoriesDTO.setProductDTOs(proList);
		return subCategoriesDTO;
	}
	
}
