package com.example.finalproject.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.service.CategoriesService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	private	CategoriesService categoriesService;
	
	@GetMapping("/categories")
	public String categories(HttpServletRequest request) {
		List<CategoriesDTO> catedto = categoriesService.getAll();
		request.setAttribute("cate", catedto);
		return "admin/categories";
	}
	@GetMapping("/categoriesdelete/{id}")
	public String delete(@PathVariable(name = "id")int id) {
		CategoriesDTO caDto = categoriesService.getCategoriesById(id);
		categoriesService.delete(caDto);
		return "redirect:/admin/categories";
	}
	@GetMapping("/categoriesupdate/{id}")
	public String update(@PathVariable(name = "id")int id, HttpServletRequest request) {
		CategoriesDTO caDto = categoriesService.getCategoriesById(id);
		request.setAttribute("cadto", caDto);
		return "admin/update";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute()CategoriesDTO cadto) {
		categoriesService.update(cadto);
		return "redirect:/admin/categories";
	}
	@GetMapping("/addcategories")
	public String add() {
		return "admin/addcategories";
	}
	@PostMapping("/add")
	public String add(@ModelAttribute(name="cadto")CategoriesDTO cadto) {
		categoriesService.add(cadto);
		return "redirect:/admin/categories";
	}
	@GetMapping("/check/{id}")
	public String check(HttpServletRequest request,@PathVariable(name="id")int id) {
		CategoriesDTO caDto = categoriesService.getCategoriesById(id);
		int a = caDto.getStatus();
		if(a==1) {
			a=0;
		}else {
			a=1;
		}
		caDto.setStatus(a);
		categoriesService.update(caDto);
		return "redirect:/admin/categories";
	}
	
}
