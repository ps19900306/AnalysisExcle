package com.self.nwq.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;

public class POIExcelRead {
    /**
     * POI 读取Excel文件
     *
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void main(String[] args) throws Exception {
        //创建Excel，读取文件内容
        File file = new File("D:/poi_test.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
        //两种方式读取工作表
        //  HSSFSheet sheet=workbook.getSheet("Sheet0");
        HSSFSheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                HSSFCell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                System.out.print(value + " ");
            }
            System.out.println();

        }

    }
}

