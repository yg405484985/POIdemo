package com.hzit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hzit.bean.Emp;

@Repository
public interface EmpMapper {

	/**
	 * 查询
	 * 
	 * @return
	 */
	List<Emp> getEmpList();

	/**
	 * 添加
	 * 
	 * @param emp
	 * @return
	 */
	int saveEmp(List<Emp> empList);

}
