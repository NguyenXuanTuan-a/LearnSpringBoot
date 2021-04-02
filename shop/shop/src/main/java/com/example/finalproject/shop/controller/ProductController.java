package com.example.finalproject.shop.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.model.ProductDTO;
import com.example.finalproject.shop.model.SubCategoriesDTO;
import com.example.finalproject.shop.service.CategoriesService;
import com.example.finalproject.shop.service.ProductService;
import com.example.finalproject.shop.service.SubCategoriesService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoriesService categoriesService;
	
	@Autowired
	private SubCategoriesService subCategoriesService;
	
	@GetMapping("/product")
	public String product(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAll();
		request.setAttribute("product",productDTOs);
		return "admin/product";
	}
	
	@GetMapping("/addproduct")
	public String add(Model model, HttpServletRequest request) {
		model.addAttribute("product", new ProductDTO());
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		List<SubCategoriesDTO>subCategoriesServices = subCategoriesService.getAll();
		request.setAttribute("cate", categoriesDTOs);
		request.setAttribute("subcate", subCategoriesServices);
		return "admin/addproduct";
	}
	@PostMapping("/addpro")
	public String add(@ModelAttribute()ProductDTO productDTO ) {
		productDTO.setImage(productDTO.getMultipartFile().getOriginalFilename());
		MultipartFile file = productDTO.getMultipartFile();
		File dir = new File("C:\\Users\\PC\\Downloads\\shop\\shop\\src\\main\\resources\\static\\images\\flags\\" + productDTO.getCategoriesDTO().getCategories() );
		
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		File newFile = new File("C:\\Users\\PC\\Downloads\\shop\\shop\\src\\main\\resources\\static\\images\\flags\\" + productDTO.getCategoriesDTO().getCategories() + "\\" + file.getOriginalFilename());
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			
			fileOutputStream.write(file.getBytes());
			
			fileOutputStream.close();
			
			productService.add(productDTO);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(productDTO.getCategoriesDTO().getId());
		productDTO.setCategoriesDTO(categoriesDTO);
		
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(productDTO.getSubCategoriesDTO().getId());
		productDTO.setSubCategoriesDTO(subCategoriesDTO);
		return "redirect:/admin/product";
	}
	@GetMapping("/deletepro/{id}")
	public String delete(@PathVariable()int id) {
		ProductDTO productDTO = productService.getById(id);
		productService.delete(productDTO);
		return "redirect:/admin/product";
	}
	
	@GetMapping("/checkpro/{id}")
	public String check(@PathVariable(name = "id")int id) {
		ProductDTO productDTO = productService.getById(id);
		int a = productDTO.getStatus();
		if(a==1) {
			a=0;
		}else {
			a=1;
		}
		productDTO.setStatus(a);
		productService.update(productDTO);
		return "redirect:/admin/product";
	}
	@GetMapping("/updatepro/{id}")
	public String updateProduct(@PathVariable()int id,Model model ,HttpServletRequest request) {
		ProductDTO productDTO = productService.getById(id);
		model.addAttribute("product", productDTO);
		
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		List<SubCategoriesDTO>subCategoriesServices = subCategoriesService.getAll();
		request.setAttribute("cate", categoriesDTOs);
		request.setAttribute("subcate", subCategoriesServices);
		return "admin/updateproduct";
	}
}
