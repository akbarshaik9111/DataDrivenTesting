package com.akbar.sdet;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

// Excel File --> WorkBook --> Sheets --> Rows --> Cells

public class _01_ReadingDataFromExcel {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\data\\booksdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Demo");
        int rowCount = sheet.getLastRowNum();
        int cellCount = sheet.getRow(0).getLastCellNum();
        System.out.println(rowCount);
        System.out.println(cellCount);

        /*
            As per Excel:
                Row count starts from 0
                Cell count starts from 1

            As per java
                Both row and cell count starts from 0
         */

        for(int r = 0; r <= rowCount; r++) {
            XSSFRow currentRow = sheet.getRow(r);
            for(int c = 0; c < cellCount; c++) {
                XSSFCell cell = currentRow.getCell(c);
                System.out.print(cell.toString()+"\t");
            }
            System.out.println();
        }
    }
}
