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

import com.example.finalproject.shop.model.AdminUserDTO;
import com.example.finalproject.shop.service.AdminUserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@GetMapping("/user")
	public String userList(HttpServletRequest request) {
		List<AdminUserDTO> adminUserDTOs = adminUserService.getAll();
		request.setAttribute("listuser", adminUserDTOs);
		System.out.println(adminUserDTOs);
		return "admin/user";
	}
	@GetMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id")int id) {
		AdminUserDTO adminUserDTO = adminUserService.getAdminUserById(id);
		adminUserService.delete(adminUserDTO);
		return "redirect:/admin/user";
	}
	@GetMapping("/adduser")
	public String add() {
		return "admin/adduser";
	}
	@PostMapping("/add-user")
	public String addUser(@ModelAttribute() AdminUserDTO adminUserDTO) {
		adminUserDTO.setRole("ROLE_ADMIN");
		adminUserService.add(adminUserDTO);
		return "redirect:/admin/user";
	}
}
