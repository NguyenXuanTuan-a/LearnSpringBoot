package com.nguyenxuantuan.helloword.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenxuantuan.helloword.model.Person;

@Controller
public class PersonController {
	public static List<Person> per1 = new ArrayList<Person>();
	@GetMapping("/list")
	public String person(HttpServletRequest request, Model model) {
		per1.addAll(Arrays.asList(new Person(1,"A",3) ,new Person(2,"B",26) , new Person(3,"C",46))) ;
		Person p1 = new Person();
		p1.setId(1);
		p1.setAge(3);
		p1.setName("Tuan");
		request.setAttribute("person", p1);
		model.addAttribute("p1",p1);
		request.setAttribute("per1", per1);
		return "person";
	}
	
	@PostMapping(value = "/addperson")
	public String addPerson(HttpServletRequest request , @RequestParam(name="id" , required = true)int id ,
			@RequestParam(name="name",required = true)String name ,@RequestParam(name="age",required = true)int age) {
			per1.add(new Person(id,name,age));
		return "redirect:/list";
	}
}
