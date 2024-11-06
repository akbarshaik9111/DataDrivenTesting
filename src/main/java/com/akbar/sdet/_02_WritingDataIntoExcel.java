package com.akbar.sdet;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class _02_WritingDataIntoExcel {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\data\\employee.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee_Info");
        XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("NAME");
            headerRow.createCell(2).setCellValue("SALARY");

        XSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue(101);
            row1.createCell(1).setCellValue("Akbar");
            row1.createCell(2).setCellValue(1000.0);

        XSSFRow row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue(102);
            row2.createCell(1).setCellValue("Malin");
            row2.createCell(2).setCellValue(2000.0);

        XSSFRow row3 = sheet.createRow(3);
            row3.createCell(0).setCellValue(103);
            row3.createCell(1).setCellValue("Samad");
            row3.createCell(2).setCellValue(3000.0);

        // Attach workbook to the file
        workbook.write(fos);

        // Close the workbook
        workbook.close();

        // Close the file
        fos.close();
        System.out.println("Finished");
    }
}
