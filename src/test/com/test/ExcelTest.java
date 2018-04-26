/*
package com.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * excel读取测试
 *
 * @caomin
 * @create 2017-12-24 19:48
 **//*

public class ExcelTest {

    @Test
    public void testReadExcel() {
        try {

            HSSFWorkbook sheets1 = new HSSFWorkbook(new FileInputStream("E:\\周报\\46周_曹敏_个人周报.xlsx"));
            XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream("E:\\周报\\46周_曹敏_个人周报.xlsx"));

            // 读取Excel
            Workbook wb = sheets1;

            // 获取sheet数目
            for (int t = 0; t < wb.getNumberOfSheets(); t++) {
                Sheet sheet = wb.getSheetAt(t);
                Row row = null;
                int lastRowNum = sheet.getLastRowNum();

                // 循环读取
                for (int i = 0; i <= lastRowNum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        // 获取每一列的值
                        for (int j = 0; j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String value = getCellValue(cell) ;
                            if(!value.equals("")){
                                System.out.print(value + " | ");
                            }
                        }
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
/***
 * 读取单元格的值
 *
 * @Title: getCellValue
 * @Date : 2014-9-11 上午10:52:07
 * @param cell
 * @return
 *//*

    private String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result = cell.getNumericCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }





    */
/**
 * 读取Excel
 *
 * @author zengwendong
 *//*

    public class ReadExcelUtils {
        private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
        private Workbook wb;
        private Sheet sheet;
        private Row row;

        public ReadExcelUtils(String filepath) {
            if(filepath==null){
                return;
            }
            String ext = filepath.substring(filepath.lastIndexOf("."));
            try {
                InputStream is = new FileInputStream(filepath);
                if(".xls".equals(ext)){
                    wb = new HSSFWorkbook(is);
                }else if(".xlsx".equals(ext)){
                    wb = new XSSFWorkbook(is);
                }else{
                    wb=null;
                }
            } catch (FileNotFoundException e) {
                logger.error("FileNotFoundException", e);
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }

        */
/**
 * 读取Excel表格表头的内容
 *
 * @param
 * @return String 表头内容的数组
 * @author zengwendong
 *//*

        public String[] readExcelTitle() throws Exception{
            if(wb==null){
                throw new Exception("Workbook对象为空！");
            }
            sheet = wb.getSheetAt(0);
            row = sheet.getRow(0);
            // 标题总列数
            int colNum = row.getPhysicalNumberOfCells();
            System.out.println("colNum:" + colNum);
            String[] title = new String[colNum];
            for (int i = 0; i < colNum; i++) {
                // title[i] = getStringCellValue(row.getCell((short) i));
                title[i] = row.getCell(i).getCellFormula();
            }
            return title;
        }

        */
/**
 * 读取Excel数据内容
 *
 * @param
 * @return Map 包含单元格数据内容的Map对象
 * @author zengwendong
 *//*

        public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{
            if(wb==null){
                throw new Exception("Workbook对象为空！");
            }
            Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();

            sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;
                Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
                while (j < colNum) {
                    Object obj = getCellFormatValue(row.getCell(j));
                    cellValue.put(j, obj);
                    j++;
                }
                content.put(i, cellValue);
            }
            return content;
        }

        */
/**
 *
 * 根据Cell类型设置数据
 *
 * @param cell
 * @return
 * @author zengwendong
 *//*

        private Object getCellFormatValue(Cell cell) {
            Object cellvalue = "";
            if (cell != null) {
                // 判断当前Cell的Type
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                    case Cell.CELL_TYPE_FORMULA: {
                        // 判断当前的cell是否为Date
                        if (DateUtil.isCellDateFormatted(cell)) {
                            // 如果是Date类型则，转化为Data格式
                            // data格式是带时分秒的：2013-7-10 0:00:00
                            // cellvalue = cell.getDateCellValue().toLocaleString();
                            // data格式是不带带时分秒的：2013-7-10
                            Date date = cell.getDateCellValue();
                            cellvalue = date;
                        } else {// 如果是纯数字

                            // 取得当前Cell的数值
                            cellvalue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    }
                    case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                        // 取得当前的Cell字符串
                        cellvalue = cell.getRichStringCellValue().getString();
                        break;
                    default:// 默认的Cell值
                        cellvalue = "";
                }
            } else {
                cellvalue = "";
            }
            return cellvalue;
        }

        @Test
        public   void test2() {
            try {
                String filepath = "D:/2.xls";
                ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
                // 对读取Excel表格标题测试
//          String[] title = excelReader.readExcelTitle();
//          System.out.println("获得Excel表格的标题:");
//          for (String s : title) {
//              System.out.print(s + " ");
//          }

                // 对读取Excel表格内容测试
                Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();
                System.out.println("获得Excel表格的内容:");
                for (int i = 1; i <= map.size(); i++) {
                    System.out.println(map.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println("未找到指定路径的文件!");
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
*/
