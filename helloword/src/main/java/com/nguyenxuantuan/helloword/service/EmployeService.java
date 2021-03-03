package com.nguyenxuantuan.helloword.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenxuantuan.helloword.dao.EmployeDao;
import com.nguyenxuantuan.helloword.model.EmployeDTO;

public interface EmployeService {
	void add(EmployeDTO emp);
	void delete(int id);
	List<EmployeDTO> getAll();
	void update(EmployeDTO emp);
	EmployeDTO get(int id);
}
@org.springframework.transaction.annotation.Transactional // bao toan tinh toan ven cua du lieu
@Service
class EmployeServiceImpl implements EmployeService{
	
	@Autowired
	EmployeDao employeDao;
	@Override
	public void add(EmployeDTO emp) {
		employeDao.add(emp);
		
	}

	@Override
	public void delete(int id) {
		employeDao.delete(id);
		
	}

	@Override
	public List<EmployeDTO> getAll() {
		return employeDao.getAll();
	}

	@Override
	public void update(EmployeDTO emp) {
		employeDao.update(emp);
	}

	@Override
	public EmployeDTO get(int id) {
		return employeDao.get(id);
	}
	
}
