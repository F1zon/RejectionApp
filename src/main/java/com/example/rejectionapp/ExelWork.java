package com.example.rejectionapp;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExelWork {

    private File exel;

    public ExelWork() {}

    public ExelWork(File exel) {
        this.exel = exel;
    }

    public void setArrForTXT() {
//        Path path = exel.toPath();
        try (FileWriter fw = new FileWriter("arr.txt");
             XSSFWorkbook workbook = new XSSFWorkbook(exel)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator
                        = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String str = String.valueOf(cell.getNumericCellValue()).replace(",", ".");

                    if (!str.equals("0.0")) {
                        System.out.println(str);
                        fw.write(str + "\n");
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public File CreateAndSetFileExel(double[] arr) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        Map<String, String> data = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            data.put(String.valueOf(i), String.valueOf(arr[i]));
        }

        Set<String> keyset = data.keySet();
        int rowNum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rowNum++);
            String numArr = data.get(key);

            Cell cell = row.createCell(0);
            cell.setCellValue(numArr);
        }

        File result = new File("Result.xlsx");

        try {
            FileOutputStream fos = new FileOutputStream(result);
            workbook.write(fos);
            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
