package com.hzit.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.resource.spi.RetryableException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hzit.bean.Emp;
import com.hzit.service.IEmpService;
import com.hzit.util.POIUtil;

@EnableAutoConfiguration
@MapperScan("com.hzit.dao")
@ComponentScan("com.hzit.service")
@Controller
public class UploadController {

	@Autowired
	private IEmpService empService;

	// 下载批量导入模板
	@RequestMapping("downloadTemplet")
	public String exportExcelTemplet(HttpServletResponse response) throws Exception {

		// 导出的文件名称
		String fileName = "批量导入模板.xlsx";
		// 文件直接在浏览器上显示或者在访问时弹出文件下载对话框。
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
		// 设置响应的文本类型
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");

		// 得到List数据

		InputStream inputStream = this.getClass().getResourceAsStream("/com/hzit/templet/" + fileName);

		Workbook workbook = new XSSFWorkbook(inputStream);

		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();

		return null;
	}

	@RequestMapping(value = "uploadEmp", method = RequestMethod.POST)
	@ResponseBody
	public String uploadEmp(MultipartFile file) throws Exception {

		// 流对象
		InputStream inputStream = file.getInputStream();

		// 获取文件的名称
		String fileName = file.getOriginalFilename(); // indexshtml
		// 获取文件的后缀名
		String end = fileName.substring(fileName.lastIndexOf(".") + 1);

		Workbook workBook = null;

		if ("xls".equals(end)) {
			// 创建workBook
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
			workBook = new HSSFWorkbook(poifsFileSystem);
		} else if ("xlsx".equals(end)) {
			workBook = new XSSFWorkbook(inputStream);
		} else {
			return null;
		}

		List<Emp> empList = POIUtil.getEmpListByWb(workBook);

		System.out.println("list:" + empList);
		int saveEmp = empService.saveEmp(empList);

		// 判断excel文档的类型

		return "ok";

	}

}
