package com.hzit.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzit.bean.Emp;
import com.hzit.service.IEmpService;
import com.hzit.util.POIUtil;

/**
 * 导出excel数据
 * 
 * @author THINK
 *
 */
@EnableAutoConfiguration
@MapperScan("com.hzit.dao")
@ComponentScan("com.hzit.service")
@Controller
public class ExportController {

	@Autowired
	private IEmpService empService;

	@RequestMapping("exportExcel")
	public String exportExcel(HttpServletResponse response) throws Exception {

		// 导出的文件名称
		String fileName = "Emp员工信息表.xlsx";
		// 文件直接在浏览器上显示或者在访问时弹出文件下载对话框。
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
		// 设置响应的文本类型
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");

		// 得到List数据
		List<Emp> empList = empService.getEmpList();

		// 数据放到WorkBook里面
		String headerBody[] = { "编号", "姓名", "岗位", "上级", "入职日期", "工资", "部门编号" };
		Workbook workbook = POIUtil.getWorkBook(empList, headerBody);

		// 输出
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();

		return null;
	}

	@RequestMapping("exportExcelTemplet")
	public String exportExcelTemplet(HttpServletResponse response) throws Exception {

		// 导出的文件名称
		String fileName = "Emp员工信息表(模板).xlsx";
		// 文件直接在浏览器上显示或者在访问时弹出文件下载对话框。
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
		// 设置响应的文本类型
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");

		// 得到List数据
		List<Emp> empList = empService.getEmpList();

		// 数据放到WorkBook里面
		String headerBody[] = { "编号", "姓名", "岗位", "上级", "入职日期", "工资", "部门编号" };
		Workbook workbook = POIUtil.getWorkBookTemplet(empList, "EmpExporTemplate.xlsx");

		// 输出
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();

		return null;
	}

}
