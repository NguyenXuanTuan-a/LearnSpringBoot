package com.example.finalproject.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finalproject.shop.model.AdminUserDTO;
import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.model.ProductDTO;
import com.example.finalproject.shop.model.SubCategoriesDTO;
import com.example.finalproject.shop.service.CategoriesService;
import com.example.finalproject.shop.service.ProductService;
import com.example.finalproject.shop.service.SubCategoriesService;

@Controller
public class LoginController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private SubCategoriesService subCategoriesService;
	@GetMapping(value = "/header")
	public String name(HttpServletRequest request,HttpSession session) {
		UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Lấy ra tên đã login ở security
		//request.setAttribute("user", userDetails.getUsername() /* lấy username */);
		session.setAttribute("user",  userDetails.getUsername() );
		return "admin/header";
	}
	@GetMapping(value = "/index")
	public String index(HttpServletRequest request,HttpSession session) {
		UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Lấy ra tên đã login ở security
		//request.setAttribute("user", userDetails.getUsername() /* lấy username */);
		session.setAttribute("user",  userDetails.getUsername() );
		return "admin/index";
	}
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(name = "e" ,required = false)String error) {
		if(error !=null) {
			request.setAttribute("e", error);
		}
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
	
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		return "user/login";
	}
	@GetMapping(value = "/form")
	public String form(HttpServletRequest request, @RequestParam(name = "e" ,required = false)String error) {
		if(error !=null) {
			request.setAttribute("e", error);
		}
		return "forms";
	}

	@GetMapping(value = "/homeuser")
	public String top(HttpServletRequest request, HttpSession session) {
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
		
		List<ProductDTO> productDTOs = productService.getAll();
		request.setAttribute("product", productDTOs);
		
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Lấy ra tên đã login ở security
		//request.setAttribute("user", userDetails.getUsername() /* lấy username */);
		session.setAttribute("user",  userDetails.getUsername() );
		return "redirect:/home";
	}
	 
	@GetMapping(value="/home")
	public String homeClient(HttpServletRequest request,HttpSession session) {
		List<ProductDTO> productDTOs = productService.getAll();
		request.setAttribute("product", productDTOs);
		
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
	
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		
		return "user/homeclient";
	}
	
	
}
