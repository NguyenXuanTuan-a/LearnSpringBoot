package com.nguyenxuantuan.helloword.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenxuantuan.helloword.model.EmployeDTO;
import com.nguyenxuantuan.helloword.service.EmployeService;

@Controller
public class EmployeController {
	@Autowired
	EmployeService empService; // Dung tinh da hinh( va dung autowired thi co the khoi tao interface)
	
	public static List<EmployeDTO> emp = new ArrayList<EmployeDTO>();
	@GetMapping(value = "/")
	public String home() {
	//	emp.addAll(Arrays.asList(new EmployeDTO(1,"tuan","giamdoc"), new EmployeDTO(2, "tu", "congnhan")));
		return "hello";
	}
	@GetMapping(value = "/home")
	public String employe(HttpServletRequest request, Model model) {
		request.setAttribute("emp", empService.getAll());
		return "homeemploye";
	}
	/*
	 * @PostMapping("/addemploye") public String addemp(HttpServletRequest
	 * request, @RequestParam(name="id")int id,@RequestParam(name = "name")String
	 * name ,@RequestParam(name = "role")String role) { emp.add(new EmployeDTO(id,
	 * name, role)); return "redirect:/home"; }
	 */
	@PostMapping("/addemploye")
	public String addemp(HttpServletRequest request, @ModelAttribute(name="emp")EmployeDTO emp) {
		empService.add(emp);
		return "redirect:/home";
	}
	@GetMapping("/deleteemploye")
	public String deleteEmp(HttpServletRequest request ,@RequestParam(name = "id")int id) {
		empService.delete(id);
		return "redirect:/home";
	}
	@GetMapping("/edit/{id}")
	public String edit(HttpServletRequest request, @PathVariable int id) {
		request.setAttribute("e", empService.get(id));
		return "update";
	}
	
	  @PostMapping("/update") 
	  public String updateEmp(HttpServletRequest request,@ModelAttribute(name="employe")EmployeDTO emp) 
	  { 
		  empService.update(emp);
		  return "redirect:/home"; 
		  
	  }
	  
	 
}
