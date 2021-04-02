package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.AdminUserDao;
import com.example.finalproject.shop.entity.AdminUserEntity;

@Service
@Transactional
public class LoginService implements UserDetailsService {
	@Autowired
	private AdminUserDao adminUserDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminUserEntity adminUserEntity = adminUserDao.getAdminUser(username);
		
		if(adminUserEntity ==null) {
			throw new UsernameNotFoundException("Khong tim thay Admin User");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(adminUserEntity.getRole()));
		UserDetails detail = new User(adminUserEntity.getUsername(), adminUserEntity.getPassword(), true, true, true, true, authorities);
		return detail;
	}

}
