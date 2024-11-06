package com.akbar.rahul;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class _03_DataProvider {

    @DataProvider(name="logindata")
    public Object[][] getData() throws IOException {

        DataFormatter dataFormat = new DataFormatter();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\data\\booksdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(1);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();

         Object[][] data = new Object[rowCount-1][colCount];
         for(int i = 0; i < rowCount - 1; i++) {
              row = sheet.getRow(i+1);
              for(int j = 0; j < colCount; j++) {
                 XSSFCell cell = row.getCell(j);
                  data[i][j] = dataFormat.formatCellValue(cell);
              }
         }
        return data;
    }

    @Test(dataProvider = "logindata")
    public void loginTest(String username, String password, String id) {
        System.out.println(username+"\t"+password+"\t"+id);
    }
}
