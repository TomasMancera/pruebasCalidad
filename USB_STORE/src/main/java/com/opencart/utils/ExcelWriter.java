package com.opencart.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    public static void writeProductsToExcel(List<String> products, String outputPath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ProductosAgregados");

        // Encabezado
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Producto Agregado");

        // Datos
        for (int i = 0; i < products.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(products.get(i));
        }

        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
