package com.hzit.service;

import java.util.List;

import com.hzit.bean.Emp;

public interface IEmpService {
	List<Emp> getEmpList();

	int saveEmp(List<Emp> empList);
}
