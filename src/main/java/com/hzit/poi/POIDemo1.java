package com.hzit.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class POIDemo1 {

	/**
	 * 创建一个表格
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@Test
	public void test1() throws Exception {

		// 1.工作薄
		Workbook workbook = new XSSFWorkbook();

		// 2.创建sheet页码
		Sheet sheet = workbook.createSheet("bj1707");

		// 3.创建行
		Row row = sheet.createRow(0);// 根据行的下标创建

		// 4.创建列
		Cell cell1 = row.createCell(0);
		Cell cell2 = row.createCell(1);
		Cell cell3 = row.createCell(2);
		Cell cell4 = row.createCell(3);

		// 设置日期的格式
		DataFormat dataFormat = workbook.createDataFormat();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(dataFormat.getFormat("m/d/yy h:mm"));

		// 赋值
		cell1.setCellValue("小白");
		cell2.setCellValue("20");
		cell3.setCellValue("男");
		// 设置单元格内容
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue(new Date());

		// 5.输出
		FileOutputStream outputStream = new FileOutputStream("C:\\poi\\info.xlsx");
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		System.out.println("创建成功!");
	}

	/**
	 * 读取表格 指定的内容
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@Test
	public void test2() throws Exception {

		// 1.创建工作薄
		FileInputStream inputStream = new FileInputStream("C:\\poi\\商品信息.xlsx");
		Workbook workbook = new XSSFWorkbook(inputStream);

		// System.out.println(workbook);

		// 2.获取sheet页码
		Sheet sheet = workbook.getSheetAt(0);// 获取第一个sheet页码

		// 3.获取row
		Row row = sheet.getRow(1);// 第二行

		// 4.获取列
		Cell cell = row.getCell(1);// 第二列

		// 5.获取内容
		String value = cell.getStringCellValue();
		// double value = cell.getNumericCellValue();

		System.out.println(value);

	}

	/**
	 * 循环
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@Test
	public void test3() throws Exception {

		// 1.创建工作薄
		FileInputStream inputStream = new FileInputStream("C:\\poi\\商品信息.xlsx");
		Workbook workbook = new XSSFWorkbook(inputStream);

		// System.out.println(workbook);

		// 2.获取sheet页码
		Sheet sheet = workbook.getSheetAt(0);// 获取第一个sheet页码

		// 3.获取row(类型长度)
		int lastRowNum = sheet.getLastRowNum();// 获取最后一行
		for (int i = 0; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);// 循环获取行

			// 获取列
			short lastCellNum = row.getLastCellNum();
			for (int j = 0; j < lastCellNum; j++) {
				Cell cell = row.getCell(j);
				// 获取内容，判断类型
				Object value = fromartCell(cell);
				System.out.print(value + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 获取不同类型的cell的值
	 * 
	 * @param cell
	 * @return
	 */
	public Object fromartCell(Cell cell) {
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
