package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileReader {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    String filePath;

    public ExcelFileReader( String filePath) {
        this.filePath = filePath;
    }

    public int getLastRowNumber(String sheetName) throws IOException {
        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public int getLastCellNumber(String sheetName) throws IOException {
        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(0);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getCellValue(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);
        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String data) throws IOException {
        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fo = new FileOutputStream(filePath);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

//    public static void getRowValues(String sheetName) throws IOException{
//        fi = new FileInputStream(filePath);
//        wb = new XSSFWorkbook(fi);
//        ws = wb.getSheet(sheetName);
//        int rowCount = ws.getLastRowNum();
//
//        List<Map <String, String>> loginData = new ArrayList<>();
//        Row headerRow = ws.getRow(0);
//        if(headerRow == null) {
//            throw new IllegalArgumentException("Sheet is empty or missing header row");
//        }
//
//        for (int i = 0; i <= rowCount; i++) {
//            Map<String, String> rowData = new HashMap<>();
//            Row row = ws.getRow(i);
//            if(row == null) {
//                continue;
//            }
//            for (int j = 0; j < row.getLastCellNum(); j++) {
//                String key = headerRow.getCell(j).getStringCellValue();
//                String value = row.getCell(j).getStringCellValue();
//                rowData.put(key, value);
//            }
//            loginData.add(rowData);
//        }
//        System.out.println(loginData);
//    }

//    public static void main(String[] args) throws IOException {
//        getRowValues("Sheet1");
//    }
}
