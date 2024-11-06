package com.akbar.rahul;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _01_ExcelDemo {

    public List<String> getData(String testcase) throws IOException {

        List<String> list = new ArrayList<>();

        // TO GET THE EXCEL FILE LOCATION
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\data\\booksdata.xlsx");

        // CREATE THE OBJECT FOR XSSFWORKBOOK CLASS AND PASS FILEINPUTSTREAM AS AN ARGUMENT
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // TO KNOW HOW MANY SHEETS ARE PRESENT IN THE WORKBOOK
        int sheets = workbook.getNumberOfSheets();
        //System.out.println(sheets);

        // USING FOR LOOP TO IDENTIFY THE SHEET
        for(int i = 0; i < sheets; i++) {
            if(workbook.getSheetName(i).equalsIgnoreCase("booksdata")) {

                // IDENTIFIED THE SHEET BASED ON INDEX
                XSSFSheet sheet = workbook.getSheetAt(i);
                //System.out.println(sheet.getSheetName());

                // ITERATE THE SHEET TO GET ACCESS OF ALL THE ROWS
                Iterator<Row> rows = sheet.iterator();

                // IDENTIFIED THE FIRST ROW
                Row headerRow = rows.next();

                // ITERATE THE ROW TO GET ACCESS OF ALL THE CELLS
                Iterator<Cell> cells = headerRow.iterator();

                // IDENTIFY THE "TESTCASES" CELL
                int k = 0;
                int column = 0;
                while(cells.hasNext()) {
                    Cell cellVal = cells.next();

                    // USE SMART LOGIC AND IDENTIFY THE "TESTCASES" COLUMN NUMBER
                    if(cellVal.getStringCellValue().equalsIgnoreCase("testcases")) {
                        column = k;
                    }
                    k++;
                }
                //System.out.println(column);

                while(rows.hasNext()) {
                    // ITERATE THE ALL THE ROWS
                    Row row = rows.next();

                    // WRITE A CONDITION TO ITERATE IN SPECIFIC COLUMN AND IDENTIFY THE TEST CASE
                    if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testcase)) {

                        // ITERATE THE CELL IN THAT PARTICULAR ROW AND ADD DATA TO THE LIST
                        Iterator<Cell> cell = row.iterator();
                        while(cell.hasNext()) {
                            Cell c = cell.next();
                            if(c.getCellType() == CellType.STRING) {
                                list.add(c.getStringCellValue());
                            } else {
                                list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }
                }
            }
        }
        return list;
    }
}
