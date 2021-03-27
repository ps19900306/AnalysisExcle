package com.self.nwq.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;

public class POIEcxelCreHigh {
    /**
     * POI 创建高版本Excel文件
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void main(String[] args) throws Exception {
        //创建Excel文件薄
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建工作表sheeet
        Sheet sheet=workbook.createSheet();
        //创建第一行
        Row row=sheet.createRow(0);
        String[] title={"id","name","sex"};
        Cell cell=null;
        for (int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i=1;i<10;i++){
            Row nextrow=sheet.createRow(i);
            Cell cell2=nextrow.createCell(0);
            cell2.setCellValue("a"+i);
            cell2=nextrow.createCell(1);
            cell2.setCellValue("user"+i);
            cell2=nextrow.createCell(2);
            cell2.setCellValue("男");
        }
        //创建一个文件
        File file=new File("D:/poi_test.xlsx");
        file.createNewFile();
        FileOutputStream stream= FileUtils.openOutputStream(file);
        workbook.write(stream);
        stream.close();

    }

}

