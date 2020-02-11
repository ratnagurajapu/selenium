package com.simplilearn.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

	private static XSSFSheet worksheet;

	private static XSSFWorkbook workbook;

	private static XSSFCell Cell;
	private static final String path="/src/test/resources";
	private static final String filename="TestData.xlsx";
	private static final String sheetName="TestData";
	// This method is to set the File path and to open the Excel file, Pass
	// Sheetname as Arguments to this method

	public static void setExcelFile()
			throws Exception {

		try {
			String completePath= System.getProperty("user.dir")+path+"/"+filename;
			// Open the Excel file
			FileInputStream inputStream = new FileInputStream(completePath);
			
			 //InputStream ExcelFile=ExcelReader.class.getClassLoader().getResourceAsStream(filename);

			// Access the required test data sheet

			workbook = new XSSFWorkbook(inputStream);

			for (int pageCount = 0; pageCount < workbook.getNumberOfSheets(); pageCount++) {

				String getSheetName = workbook.getSheetName(pageCount);

				if (getSheetName.equals(sheetName)) {
					worksheet = workbook.getSheetAt(pageCount);
				}

			}

		} catch (Exception e) {

			throw (e);

		}

	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = worksheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	public static Integer getCellDataInt(int RowNum, int ColNum)
			throws Exception {

		try {

			Cell = worksheet.getRow(RowNum).getCell(ColNum);

			Integer CellData = (int) Cell.getNumericCellValue();

			return CellData;

		} catch (Exception e) {

			return null;

		}

	}

	public static Double getCellDataDouble(int RowNum, int ColNum)
			throws Exception {

		try {

			Cell = worksheet.getRow(RowNum).getCell(ColNum);

			Double CellData = Cell.getNumericCellValue();

			return CellData;

		} catch (Exception e) {

			return null;

		}

	}

	public static Float getCellDataFloat(int RowNum, int ColNum)
			throws Exception {

		try {

			Cell = worksheet.getRow(RowNum).getCell(ColNum);

			Float CellData = (float) Cell.getNumericCellValue();

			return CellData;

		} catch (Exception e) {

			return null;

		}

	}

}
