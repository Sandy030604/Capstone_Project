package com.makemytrip.testdata;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadConfirmation {
    @DataProvider(name = "confirmation")
    public Object[][] confirm() {
        //name age email phoneno
        List<Object[]> dataList = new ArrayList<>();
        String filePath = "C:\\Users\\santhosh.krishnan\\Downloads\\Capstone_Project_MakeMyTrip\\src\\test\\java\\com\\makemytrip\\testdata\\Mmt.xlsx";

        try (FileInputStream input = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet xssfSheet = workbook.getSheet("Sheet1");


            int lastRow = xssfSheet.getLastRowNum();
            System.out.println("Last Row Number: " + lastRow);

            Row headerRow = xssfSheet.getRow(0);


            int noofcol = headerRow.getPhysicalNumberOfCells();
            System.out.println("No of columns: " + noofcol);

            for (int rsize = 1; rsize <= lastRow; rsize++) { // Start from 1 if row 0 is header
                Row row = xssfSheet.getRow(rsize);
                List<Object> rowData = new ArrayList<>();
                for (int csize = 0; csize < noofcol; csize++) {
                    Cell cell = row.getCell(csize, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf((long) cell.getNumericCellValue())); // Convert numeric to String
                            break;
                        default:
                            rowData.add(""); // Handle other cell types
                    }
                }
                dataList.add(rowData.toArray());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList.toArray(new Object[0][]);
    }
}
