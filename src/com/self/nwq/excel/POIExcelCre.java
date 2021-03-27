package com.self.nwq.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;

public class POIExcelCre {
    /**
     * POI 创建Excel文件
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void main(String[] args) throws Exception {
        //创建Excel文件薄
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建工作表sheeet
        HSSFSheet sheet=workbook.createSheet();
        //创建第一行
        HSSFRow row=sheet.createRow(0);
        String[] title={"id","name","sex"};
        HSSFCell cell=null;
        for (int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i=1;i<10;i++){
            HSSFRow nextrow=sheet.createRow(i);
            HSSFCell cell2=nextrow.createCell(0);
            cell2.setCellValue("a"+i);
            cell2=nextrow.createCell(1);
            cell2.setCellValue("user"+i);
            cell2=nextrow.createCell(2);
            cell2.setCellValue("男");
        }
        //创建一个文件
        File file=new File("D:/poi_test.xls");
        file.createNewFile();
        FileOutputStream stream=FileUtils.openOutputStream(file);
        workbook.write(stream);
        stream.close();

    }

}

