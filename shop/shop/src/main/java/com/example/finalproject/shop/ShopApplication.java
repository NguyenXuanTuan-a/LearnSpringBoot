package com.example.finalproject.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.finalproject.shop.service.LoginService;

@SpringBootApplication(scanBasePackages = {"com.example.finalproject.shop"})
public class ShopApplication extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoginService loginService;
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Test các security

		// http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().httpBasic();//
		// Chống tấn công và authorizeRequests().anyRequest().authenticated() là
		// antMatchers("/").permitAll() là cho phép truy câp đường dẫn / mà k // xác
		// thực người dùng tất cả các đường dẫn đều phải login
		// cần đăng nhập còn các đường dẫn khác bắt buộc phải đăng nhập

		// Để form login
		
		  http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/client/**").hasAnyRole("USER").antMatchers("/admin/**")
		  .hasAnyRole("ADMIN").and()
		  .formLogin().loginPage("/login").defaultSuccessUrl("/homeuser").failureUrl("/login?e=error").permitAll().and() .logout().permitAll();
		 
		//.antMatchers("/adduser").permitAll().antMatchers("/add").permitAll()
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/css/**", "/assets/js/**", "/images/**","/assets/fonts/**","/assets/img/**","/assets/weather/**",
				"/assets/calendar/**","/client/**","/client/js/**"); // để các đường dẫn trong static js hay css sẽ không
																	// bị chặn .spring Security sẽ không chặn đường dẫn
																	// /css/
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("admin");
		 auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
	}

	 @Bean 
	 public BCryptPasswordEncoder passwordEncoder() {
		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12); 
		 return bCryptPasswordEncoder;
		 }
	 
}
