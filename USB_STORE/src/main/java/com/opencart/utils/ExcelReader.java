package com.opencart.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private Sheet sheet;

    public ExcelReader(String excelPath, String sheetName) {
        try {
            FileInputStream file = new FileInputStream(excelPath);
            Workbook workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
}
