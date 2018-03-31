package com.hzit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzit.bean.Emp;
import com.hzit.dao.EmpMapper;
import com.hzit.service.IEmpService;

@Service
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private EmpMapper empMapper;

	@Override
	public List<Emp> getEmpList() {

		return empMapper.getEmpList();

	}

	@Override
	public int saveEmp(List<Emp> empList) {

		return empMapper.saveEmp(empList);
	}

}
