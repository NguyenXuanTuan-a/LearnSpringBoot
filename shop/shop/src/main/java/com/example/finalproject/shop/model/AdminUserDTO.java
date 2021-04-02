package com.example.finalproject.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDTO {
	private int id;
	private String name;
	private String username;
	private String password;
	private String role;
	private String email;
	private String mobile;
	private Date added_on;
}
