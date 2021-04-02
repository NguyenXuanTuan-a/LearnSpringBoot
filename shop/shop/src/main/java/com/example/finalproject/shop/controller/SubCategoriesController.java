package com.example.finalproject.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.model.SubCategoriesDTO;
import com.example.finalproject.shop.service.CategoriesService;
import com.example.finalproject.shop.service.SubCategoriesService;

@Controller
@RequestMapping("/admin")
public class SubCategoriesController {
	@Autowired
	private SubCategoriesService subCategoriesService;

	@GetMapping("/subcategories")
	public String get(HttpServletRequest request) {
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("sub", subCategoriesDTOs);
		return "admin/subcategories";
	}
	@GetMapping("/checksub/{id}")
	public String check(HttpServletRequest request,@PathVariable(name="id")int id) {
		SubCategoriesDTO subcaDto = subCategoriesService.getSubById(id);
		int a = subcaDto.getStatus();
		if(a==0) {
			a=1;
		}else {
			a=0;
		}
		subcaDto.setStatus(a);
		subCategoriesService.update(subcaDto);
		return "redirect:/admin/subcategories";
	}
	@Autowired
	private CategoriesService categoriesService;
	@GetMapping("/updatesub/{id}")
	public String update(HttpServletRequest request, @PathVariable(name = "id")int id , Model model) {
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(id);
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		model.addAttribute("sub",subCategoriesDTO);
		request.setAttribute("cate", categoriesDTOs);
		return "admin/updateSubCate";
	}
	@PostMapping("/updatesubcate")
	public String update(@ModelAttribute()SubCategoriesDTO subCategoriesDTO ) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(subCategoriesDTO.getCategoriesDTO().getId());
		System.out.println(categoriesDTO.getId());
		subCategoriesDTO.setCategoriesDTO(categoriesDTO);
		subCategoriesService.update(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}

	@GetMapping("/addsub")
	public String add(HttpServletRequest request , Model model) {
		model.addAttribute("subcate", new SubCategoriesDTO());
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("cate", categoriesDTOs);
		return "admin/addsubcategories";
	}
	
	@PostMapping("/addsubcate")
	public String add(@ModelAttribute()SubCategoriesDTO subCategoriesDTO) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(subCategoriesDTO.getCategoriesDTO().getId());
		subCategoriesDTO.setCategoriesDTO(categoriesDTO);
		subCategoriesService.add(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}
	@GetMapping("/deletesub/{id}")
	public String delete(HttpServletRequest request,@PathVariable(name = "id")int id) {
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(id);
		subCategoriesService.delete(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}
}
