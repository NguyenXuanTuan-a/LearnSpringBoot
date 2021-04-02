package com.example.finalproject.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalproject.shop.dao.AdminUserDao;
import com.example.finalproject.shop.entity.AdminUserEntity;
import com.example.finalproject.shop.model.AdminUserDTO;
import com.example.finalproject.shop.utils.PasswordGenerator;

public interface AdminUserService {
	public AdminUserDTO getAdminByUserName(String username);
	public void add(AdminUserDTO adminUserDTO);
	public void delete(AdminUserDTO adminUserDTO);
	public AdminUserDTO getAdminUserById(int id);
	public List<AdminUserDTO> getAll();
}
@Service
@Transactional
class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	static PasswordGenerator passwordGenerator;

	@Override
	public AdminUserDTO getAdminByUserName(String username) {
		AdminUserEntity adminUserEntity = adminUserDao.getAdminUser(username);
		AdminUserDTO adminUserDTO = new AdminUserDTO();
		adminUserDTO.setId(adminUserEntity.getId());
		adminUserDTO.setUsername(adminUserEntity.getUsername());
		adminUserDTO.setPassword(passwordGenerator.getHashString(adminUserEntity.getPassword()));
		adminUserDTO.setRole(adminUserEntity.getRole());
		adminUserDTO.setEmail(adminUserEntity.getEmail());
		adminUserDTO.setName(adminUserEntity.getName());
		adminUserDTO.setAdded_on(adminUserEntity.getAdded_on());
		adminUserDTO.setMobile(adminUserEntity.getMobile());
		return adminUserDTO;
	}

	@Override
	public void add(AdminUserDTO adminUserDTO) {
		Date date = new Date();
		AdminUserEntity adminUserEntity = new AdminUserEntity();
		adminUserEntity.setName(adminUserDTO.getName());
		adminUserEntity.setUsername(adminUserDTO.getUsername());
		adminUserEntity.setEmail(adminUserDTO.getEmail());
		adminUserEntity.setAdded_on(date);
		adminUserEntity.setMobile(adminUserDTO.getMobile());
		adminUserEntity.setRole(adminUserDTO.getRole());
		adminUserEntity.setPassword(passwordGenerator.getHashString(adminUserDTO.getPassword()));
		adminUserDao.add(adminUserEntity);
		
		
	}

	@Override
	public void delete(AdminUserDTO adminUserDTO) {
		AdminUserEntity adminUserEntity = adminUserDao.getAdminUserById(adminUserDTO.getId());
		
		if(adminUserEntity != null) {
			adminUserDao.delete(adminUserEntity);
		}
		
	}

	@Override
	public AdminUserDTO getAdminUserById(int id) {
		AdminUserEntity adminUserEntity = adminUserDao.getAdminUserById(id);
		AdminUserDTO adminUserDTO = new AdminUserDTO();
		adminUserDTO.setId(adminUserEntity.getId());
		adminUserDTO.setUsername(adminUserEntity.getUsername());
		adminUserDTO.setPassword(passwordGenerator.getHashString(adminUserEntity.getPassword()));
		adminUserDTO.setRole(adminUserEntity.getRole());
		adminUserDTO.setEmail(adminUserEntity.getEmail());
		adminUserDTO.setName(adminUserEntity.getName());
		adminUserDTO.setAdded_on(adminUserEntity.getAdded_on());
		adminUserDTO.setMobile(adminUserEntity.getMobile());
		
		return adminUserDTO;
	}

	@Override
	public List<AdminUserDTO> getAll() {
		List<AdminUserEntity> adminUserEntities = adminUserDao.getAll();
		List<AdminUserDTO> adminUserDTOs = new ArrayList<AdminUserDTO>();
		for(AdminUserEntity adminE : adminUserEntities) {
			AdminUserDTO adminUserDTO = new AdminUserDTO();
			adminUserDTO.setId(adminE.getId());
			adminUserDTO.setUsername(adminE.getUsername());
			adminUserDTO.setPassword(passwordGenerator.getHashString(adminE.getPassword()));
			adminUserDTO.setRole(adminE.getRole());
			adminUserDTO.setEmail(adminE.getEmail());
			adminUserDTO.setName(adminE.getName());
			adminUserDTO.setAdded_on(adminE.getAdded_on());
			adminUserDTO.setMobile(adminE.getMobile());
			
			adminUserDTOs.add(adminUserDTO);
		}
		return adminUserDTOs;
	}
	
}
