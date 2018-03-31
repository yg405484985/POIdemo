package com.hzit.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hzit.bean.Emp;
import com.hzit.dao.EmpMapper;

/**
 * 和POI相关的
 * 
 * @author THINK
 *
 */
public class POIUtil {

	// 封装WorkBook
	@SuppressWarnings("resource")
	public static Workbook getWorkBook(List<Emp> empList, String[] headerBody) {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("emp信息表");
		// 编号 姓名 岗位 上级 入职日期 工资 部门编号
		Row rowHeader = sheet.createRow(0);

		// 封装第一行标题
		for (int i = 0; i < headerBody.length; i++) {
			// 把headerBody数组里面的内容，循环存放到第一行标题中
			rowHeader.createCell(i).setCellValue(headerBody[i]);
		}

		DataFormat dataFormat = workbook.createDataFormat();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(dataFormat.getFormat("m/d/yy h:mm"));
		// 封装list 去掉第一行
		for (int i = 1; i <= empList.size(); i++) {
			Row rowContext = sheet.createRow(i); // 获取行
			Emp emp = empList.get(i - 1);// 从0开始读的
			rowContext.createCell(0).setCellValue(emp.getEmpno());
			rowContext.createCell(1).setCellValue(emp.getEname());
			rowContext.createCell(2).setCellValue(emp.getJob());
			rowContext.createCell(3).setCellValue(emp.getMgr());

			rowContext.createCell(4).setCellStyle(cellStyle);// 设置日期格式
			rowContext.createCell(4).setCellValue(emp.getHireDate());
			rowContext.createCell(5).setCellValue(emp.getSal());
			rowContext.createCell(6).setCellValue(emp.getDeptno());
		}

		return workbook;
	}

	// 封装WorkBook
	@SuppressWarnings("resource")
	public static Workbook getWorkBookTemplet(List<Emp> empList, String templetPath) {

		try {
			// 指定模板的路径和名称
			String filePath = "/com/hzit/templet/" + templetPath;
			System.out.println("filePath:" + filePath);
			// 将得到文件转换成输入流
			InputStream inputStream = POIUtil.class.getResourceAsStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			// 编号 姓名 岗位 上级 入职日期 工资 部门编号
			// Row rowHeader = sheet.createRow(0);

			// 封装第一行标题
			// for (int i = 0; i < headerBody.length; i++) {
			// // 把headerBody数组里面的内容，循环存放到第一行标题中
			// rowHeader.createCell(i).setCellValue(headerBody[i]);
			// }

			DataFormat dataFormat = workbook.createDataFormat();
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(dataFormat.getFormat("m/d/yy h:mm"));
			// 封装list 去掉第一行
			for (int i = 1; i <= empList.size(); i++) {
				Row rowContext = sheet.createRow(i); // 获取行
				Emp emp = empList.get(i - 1);// 从0开始读的
				rowContext.createCell(0).setCellValue(emp.getEmpno());
				rowContext.createCell(1).setCellValue(emp.getEname());
				rowContext.createCell(2).setCellValue(emp.getJob());
				rowContext.createCell(3).setCellValue(emp.getMgr());

				rowContext.createCell(4).setCellStyle(cellStyle);// 设置日期格式
				rowContext.createCell(4).setCellValue(emp.getHireDate());
				rowContext.createCell(5).setCellValue(emp.getSal());
				rowContext.createCell(6).setCellValue(emp.getDeptno());
			}

			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取list
	 * 
	 * @param workbook
	 * @return
	 */
	public static List<Emp> getEmpListByWb(Workbook workbook) {

		List<Emp> empList = new ArrayList<Emp>();

		Sheet sheet = workbook.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum();// 去掉标题
		for (int i = 1; i <= lastRowNum; i++) {

			Row row = sheet.getRow(i);

			Emp emp = new Emp();

			System.out.println(fromartCell(row.getCell(0)));
			System.out.println(fromartCell(row.getCell(1)));
			System.out.println(fromartCell(row.getCell(2)));
			System.out.println(fromartCell(row.getCell(3)));
			emp.setEmpno((int) ((double) fromartCell(row.getCell(0))));
			emp.setEname((String) fromartCell(row.getCell(1)));
			emp.setJob((String) fromartCell(row.getCell(2)));
			emp.setMgr((int) ((double) fromartCell(row.getCell(3))));
			emp.setHireDate((Date) fromartCell(row.getCell(4)));
			emp.setSal(Double.parseDouble(fromartCell(row.getCell(5)).toString()));
			emp.setDeptno((int) ((double) fromartCell(row.getCell(6))));

			empList.add(emp);
		}
		return empList;
	}

	/**
	 * 获取不同类型的cell的值
	 * 
	 * @param cell
	 * @return
	 */
	public static Object fromartCell(Cell cell) {
		Object values = null;
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			values = cell.getBooleanCellValue();

		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {

			// cell.setcell

			values = cell.getNumericCellValue();
			// 判断的是 该数值是不是一个日期列
			if (cell.getCellStyle().getDataFormatString().contains("yy")) {
				Date dateCellValue = cell.getDateCellValue();
				// values = new SimpleDateFormat("yyyy-MM-dd
				// HH:mm:ss").format(dateCellValue);
				values = dateCellValue;
			}
		} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
			values = cell.getStringCellValue();
		}
		return values;
	}

}
