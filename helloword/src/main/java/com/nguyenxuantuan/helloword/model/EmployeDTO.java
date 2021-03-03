package com.nguyenxuantuan.helloword.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {
		private int id;
		private String name;
		private String role;
}
