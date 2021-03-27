package com.self.nwq;


import com.self.nwq.excel.POIExcelReadHigh;
import com.self.nwq.xml.Data;
import com.self.nwq.xml.XmlSAXReader;

import java.io.*;
import java.util.List;

public class Main {


    //private static String headStr = "D:/androidWorkSpace/Work/app/WeLineWorker3/strings/src/main/res/";
    private static String headStr = "D:/androidWorkSpace/Work/app/WeLineDownloadOffline/strings/src/main/res/";
    private static String ExcelFilePath = "D:/AndroidStr/stringExcel.xlsx";

    public static void main(String[] args) {
        try {
            POIExcelReadHigh poiExcelReadHigh = new POIExcelReadHigh();
            List<List<Data>> list = poiExcelReadHigh.parse(ExcelFilePath);
            XmlSAXReader xmlSAXReader = new XmlSAXReader();
            int i = 0;
            for (List<Data> data : list) {
                String fileAllPath = headStr + pathArray[i] + fileName;
//这个是覆盖的
//                File file = new File(fileAllPath);
//                FileInputStream in = new FileInputStream(file);
//                List<Data> oldList = xmlSAXReader.parse(in);
//                in.close();
//                oldList.addAll(data);
//                String s = xmlSAXReader.serialize(oldList);
//                addFileOver(s,fileAllPath);
//这个是追加的
                String s = xmlSAXReader.serialize(data);
                String s1 = s.replace("'", "\\'");
                String s2 = s.replace("\"", "\\\"");
                addFile(s2, fileAllPath);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //追加写入
    public static boolean addFile(String string, String path) {
        //文件的续写
        FileWriter fw = null;
        try {
            fw = new FileWriter(path, true);
            //写入换行
            fw.write("\r\n");//Windows平台下用\r\n，Linux/Unix平台下用\n
            //续写一个hello world!
            fw.write(string);
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    //覆盖最后一行写入
    public static void rewriteendline(String filepath, String string)
            throws Exception {
        RandomAccessFile file = new RandomAccessFile(filepath, "rw");
        long len = file.length();
        long start = file.getFilePointer();
        long nextend = start + len - 1;

        int i = -1;
        file.seek(nextend);
        byte[] buf = new byte[1];
        while (nextend > start) {
            i = file.read(buf, 0, 1);
            if (buf[0] == '\r') {
                file.setLength(nextend - start);
                break;
            }
            nextend--;
            file.seek(nextend);

        }
        file.close();
        writeendline(filepath, string);

    }

    private static void writeendline(String filepath, String string)
            throws Exception {
        RandomAccessFile file = new RandomAccessFile(filepath, "rw");
        long len = file.length();
        long start = file.getFilePointer();
        long nextend = start + len - 1;
        byte[] buf = new byte[1];
        file.seek(nextend);
        file.read(buf, 0, 1);
        if (buf[0] == '\n')
            file.writeBytes(string);
        else
            file.writeBytes("\r\n" + string);
        file.close();
    }

    //覆盖写入
    public static boolean addFileOver(String string, String path) {

        PrintStream stream = null;
        try {
            stream = new PrintStream(path);//写入的文件path
            stream.print(string);//写入的字符串
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static String fileName = "/strings.xml";

    private static String[] pathArray = {"values",
            "values-zh",
            "values-zh-rTW",
            "values-fr",
            "values-de",
            "values-it",
            "values-ja",
            "values-ko",
            "values-ru",
            "values-es",
            "values-th",
            "values-vi",
            "values-ar"
    };
}

