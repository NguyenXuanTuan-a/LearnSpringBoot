package com.nguyenxuantuan.helloword.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // bean
public class HelloWord {
	@Value("${my.key}")
	private String hello;
	@GetMapping("/hi")
	public String hello(Model model , HttpServletRequest request) {
		System.out.println(hello);
		// thymeleaf
		String s ="Hello tao thich m";
		request.setAttribute("hello", s);
		return "hello";
	}	
}
