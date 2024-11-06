package com.akbar.sdet;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class _03_WritingDynamicDataIntoExcel {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\data\\employee.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee_Details");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int noOfRows = sc.nextInt();

        System.out.println("Enter the number of cells: ");
        int noOfCells = sc.nextInt();

        for(int r = 0; r <= noOfRows; r++) {
            XSSFRow currentRow = sheet.createRow(r);
            for (int c = 0; c < noOfCells; c++) {
                XSSFCell cell = currentRow.createCell(c);
                cell.setCellValue(sc.next());
            }
        }

        // Attach workbook to the file
        workbook.write(fos);

        // Close the workbook
        workbook.close();

        // Close the file
        fos.close();

        System.out.println("Finished");
    }
}
