package com.self.nwq.excel;

import com.self.nwq.xml.Data;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POIExcelReadHigh {
    /**
     * POI 读取高版本Excel文件
     *
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void main(String[] args) throws Exception {
        //创建Excel，读取文件内容
        File file = new File("D:/poi_test.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }


    /**
     * @param filePath  文件位置
     * @param StartRow  从第几行开始解析
     * @param ValueCell 第几列读取值
     * @return
     */
    public List<Data> parse(String filePath, int StartRow, int keyCell, int ValueCell) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return null;
        }
        //创建Excel，读取文件内容
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        if (StartRow > lastRowNum) {
            System.out.println("开始行号大于总行号");
            return null;
        }
        List<Data> list = new ArrayList<>();
        Data data;
        for (int i = StartRow; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            if (keyCell > lastCellNum && ValueCell > lastCellNum) {
                System.out.println("开始列号大于总列号");
                continue;
            }
            data = new Data();
            data.setKeyStr(row.getCell(keyCell).getStringCellValue());
            data.setValueStr(row.getCell(ValueCell).getStringCellValue());
            list.add(data);
        }
        return list;
    }

    /**
     * @param filePath 文件位置
     * @return
     */
    public List<List<Data>> parse(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return null;
        }
        //创建Excel，读取文件内容
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();

        List<List<Data>> list = new ArrayList<>();

        Data data;
        String key = "";
        Row row1 = sheet.getRow(0);
        int lastCellNum1 = row1.getLastCellNum();

        for (int i = 0; i < lastCellNum1 - 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                if (j == 0) {
                    key = row.getCell(j).getStringCellValue();
                } else {
                    data = new Data();
                    data.setKeyStr(key);
                    data.setValueStr(row.getCell(j).getStringCellValue());
                    list.get(j - 1).add(data);
                }
            }
        }
        return list;
    }


}
