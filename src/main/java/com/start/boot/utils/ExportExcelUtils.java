package com.start.boot.utils;

import com.start.boot.annotation.ExcelProperty;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.pojo.vo.ExcelMapDataVo;
import com.start.boot.pojo.vo.ExcelVo;
import com.start.boot.pojo.vo.ExcelWriteToFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * excel导出
 */
@Component
public class ExportExcelUtils {

    /**
     * 导出数据
     * @param excelVo
     */
    public String exportExcelData(ExcelVo excelVo) {
        List<String> header = excelVo.getHeader();
        String title = excelVo.getFileName();
        List<List<String>> data = excelVo.getData();
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = getHssfCellStyle(workbook);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = getHssfCellStyleTwo(workbook);
        getExcelHeader(header, sheet, style);
        HSSFRow row;
        getExcelData(data, workbook, sheet, style2);
        String wzbsPath = SystemConfiguration.wzbsPath;
        //String filePath =SystemConfiguration.resourcefilepath;
        String filePath = "/File/";
        String path=wzbsPath+filePath;
        if (StringUtils.isNotEmpty(excelVo.getFilePath())){
            path=excelVo.getFilePath();
        }
        String fileName = UUID.randomUUID().toString();
        if (StringUtils.isNotEmpty(excelVo.getFileName())){
            fileName=excelVo.getFileName();
        }
        try {
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(path+fileName+".xls");
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e.getMessage());
        }
        return filePath+fileName+".xls";
    }



    /**
     * 导出excel数据，从指定行开始写
     * @param excelVo
     */
    public String exportExcelDataWriteLineNumber(ExcelWriteToFile excelVo) throws IOException {
        String title = excelVo.getFileName();
        List<List<String>> data = excelVo.getData();
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = getHssfCellStyle(workbook);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = getHssfCellStyleTwo(workbook);
        HSSFRow row;
        getExcelDataWriteToLine(data, workbook, sheet, style2,excelVo.getStartLine());
        String wzbsPath = SystemConfiguration.wzbsPath;
        String filePath = "/File/";
        String path=wzbsPath+filePath;
        if (StringUtils.isNotEmpty(excelVo.getFilePath())){
            path=excelVo.getFilePath();
        }

        File sourceFile = new File(excelVo.getSourcefile());
        //导出的文件
        File targetFile = new File(path+"/"+excelVo.getFileName()+".xls");

        if (!sourceFile.getParentFile().exists()){
            sourceFile.getParentFile().mkdirs();
        }
        if (!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        //拷贝导出模板
        FileUtils.copyFile(sourceFile,targetFile);
        try {
            OutputStream out = new FileOutputStream(targetFile);
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return path+"/"+excelVo.getFileName()+".xls";
    }


    /**
     * 导出Map数据
     * @param excelVo
     * @return
     */
    public String exportExcelMapData(ExcelMapDataVo excelVo) {
        List<String> header = excelVo.getHeader();
        String title = excelVo.getFileName();
        List<Map> data = excelVo.getDataMap();
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = getHssfCellStyle(workbook);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = getHssfCellStyleTwo(workbook);
        getExcelHeader(header, sheet, style);
        HSSFRow row;
        getExcelMapData(data, workbook,excelVo.getKeys(),sheet, style2);
        String wzbsPath = SystemConfiguration.wzbsPath;
        String filePath =SystemConfiguration.resourcefilepath;
        //String filePath = "d:/";
        String path=wzbsPath+filePath;
        if (StringUtils.isNotEmpty(excelVo.getFilePath())){
            path=excelVo.getFilePath();
        }
        String fileName = UUID.randomUUID().toString();
        if (StringUtils.isNotEmpty(excelVo.getFileName())){
            fileName=excelVo.getFileName();
        }
        try {
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(path+fileName+".xls");
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return filePath+fileName+".xls";
    }


    /**
     * 导出java 对象
     * @param data 数据
     * @param fileName 文件名
     * @param filePath 文件路径
     * @return
     */
    public String exportExcelToBean(List data,String fileName,String filePath){
        if (!CollectionUtils.isEmpty(data)) {
            Field[] declaredFields = data.get(0).getClass().getDeclaredFields();

            List<String> header=new ArrayList<>();
            List<Field>valueField=new ArrayList<>();
            for (Field declaredField : declaredFields) {
                if (declaredField.getAnnotation(ExcelProperty.class)!=null){
                    valueField.add(declaredField);
                    header.add("");
                }
            }
            //获取表头
            for (Field declaredField : declaredFields) {
                ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                if (annotation!=null){
                    if (annotation.order()!=-1){
                        valueField.set(annotation.order(),declaredField);
                        header.set(annotation.order(),annotation.name());
                    }else {
                        valueField.add(declaredField);
                        header.add(annotation.name());
                    }
                }
            }
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(fileName);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            HSSFCellStyle style = getHssfCellStyle(workbook);
            // 生成并设置另一个样式
            HSSFCellStyle style2 = getHssfCellStyleTwo(workbook);
            getExcelHeader(header, sheet, style);
            List<List<String>> lists = new ArrayList<>();
            //获取数据
            data.stream().forEach(t->{
                ArrayList<String> rowData = new ArrayList<>();
                for (Field field : valueField) {
                    try {
                        field.setAccessible(true);
                        if (field.getAnnotation(ExcelProperty.class)==null)continue;
                        Object o = field.get(t);
                        String cellData=null;
                        if (o instanceof String){
                            cellData=(String)o;
                        }
                        else if ( o.getClass().getSimpleName().equalsIgnoreCase("Timestamp")){
                            cellData = o.toString();
                        }else {
                            cellData=o.toString();
                        }
                        rowData.add(cellData);
                    } catch (IllegalAccessException e) {
                        throw  new RuntimeException("excel导出数据出错，数据转换出错");
                    }
                }
                lists.add(rowData);
            });
            getExcelData(lists, workbook, sheet, style2);
            String wzbsPath = SystemConfiguration.wzbsPath;
            if (org.springframework.util.StringUtils.isEmpty(filePath)){
                filePath="/File/";
            }
            String path=wzbsPath+filePath;
            if (org.springframework.util.StringUtils.isEmpty(fileName)){
                fileName = UUID.randomUUID().toString();
            }
            try {
                File file = new File(path);
                if (!file.exists()){
                    file.mkdirs();
                }
                OutputStream out = new FileOutputStream(path+fileName+".xls");
                workbook.write(out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();

                throw new RuntimeException(e.getMessage());
            }
            return filePath+fileName+".xls";

        }
        return  null;

    }

    /**
     *
     * excel 导出 java对象  支持2007+。复杂表格表头导出
     * @param data 数据
     * @param startLine 从多少行开写
     * @param fileName 文件名字
     * @param sheetName 工作表名称
     * @param filePath 文件相对于服务器路径
     * @param sourceFilePath 拷贝文件路径
     * @return
     * @throws Exception
     */
    public String exportExcelToBean(List data,int startLine,String fileName,String sheetName,String filePath,String sourceFilePath) throws Exception {
        if (!CollectionUtils.isEmpty(data)) {
            Field[] declaredFields = data.get(0).getClass().getDeclaredFields();

            List<Field>valueField=new ArrayList<>();
            for (Field declaredField : declaredFields) {
                if (declaredField.getAnnotation(ExcelProperty.class)!=null){
                    valueField.add(declaredField);

                }
            }
            //获取表头
            for (Field declaredField : declaredFields) {
                ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                if (annotation!=null){
                    if (annotation.order()!=-1){
                        valueField.set(annotation.order(),declaredField);
                    }else {
                        valueField.add(declaredField);
                    }
                }
            }
            String wzbsPath = SystemConfiguration.wzbsPath;
            if (org.springframework.util.StringUtils.isEmpty(filePath)){
                filePath="/File/";
            }
            String path=wzbsPath+filePath;
            if (org.springframework.util.StringUtils.isEmpty(fileName)){
                fileName = UUID.randomUUID().toString();
            }
            String name = path + fileName + ".xlsx";
            File destFile = new File(name);
            if (!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }
            File srcFile = new File(sourceFilePath);
            FileUtils.copyFile(srcFile, destFile);
            // 声明一个工作薄
            FileInputStream is = new FileInputStream(destFile);
            //HSSFWorkbook workbook = new HSSFWorkbook(is);
            Workbook workbook = WorkbookFactory.create(is);
            // 生成一个表格
            Sheet sheet = workbook.getSheet(sheetName);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成并设置另一个样式
            CellStyle style2 = getCellStyleTwo(workbook);
            List<List<String>> lists = new ArrayList<>();
            //获取数据
            data.stream().forEach(t->{
                ArrayList<String> rowData = new ArrayList<>();
                for (Field field : valueField) {
                    try {
                        field.setAccessible(true);
                        if (field.getAnnotation(ExcelProperty.class)==null)continue;
                        Object o = field.get(t);
                        String cellData=null;
                        if (o instanceof String){
                            cellData=(String)o;
                        }
                        else if ( o.getClass().getSimpleName().equalsIgnoreCase("Timestamp")){
                            cellData = o.toString();
                        }else {
                            cellData=o.toString();
                        }
                        rowData.add(cellData);
                    } catch (IllegalAccessException e) {
                        throw  new RuntimeException("excel导出数据出错，数据转换出错"+"错误出现在ExportExcelUtils 354行");
                    }
                }
                lists.add(rowData);
            });
            getExcelDataWriteToLine(lists, workbook, sheet, style2,startLine);

            OutputStream out=null;
            try {


                out= new FileOutputStream(destFile);
                workbook.write(out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage()+"错误出现在ExportExcelUtils 371行");
            }
            return filePath + fileName + ".xlsx";
        }
        return  null;

    }
    private void getExcelData(List<List<String>> data, HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle style2) {
        HSSFRow row;
        if (!data.isEmpty()) {
            int index=0;
            HSSFFont font3 = workbook.createFont();
            for (int i = 0; i < data.size(); i++) {
                index++;
                row = sheet.createRow(index);
                List<String> rowData = data.get(i);
                if (!rowData.isEmpty()){
                    for (int j = 0; j < rowData.size(); j++) {
                        HSSFCell cell = row.createCell(j);
                        cell.setCellStyle(style2);
                        String cellData = rowData.get(j);

                        if (cellData != null && !cellData.isEmpty()) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(cellData);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(cellData));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        cellData);
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }

                    }
                }
            }
        }
    }
    private void getExcelDataWriteToLine(List<List<String>> data, Workbook workbook,  Sheet sheet, CellStyle style2,int startLine) {
        Row row;
        if (!data.isEmpty()) {
            int index=startLine;
            for (int i = 0; i < data.size(); i++) {
                index++;
                row = sheet.createRow(index);
                List<String> rowData = data.get(i);
                if (!rowData.isEmpty()){
                    for (int j = 0; j < rowData.size(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(style2);
                        String cellData = rowData.get(j);

                        if (cellData != null && !cellData.isEmpty()) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(cellData);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(cellData));
                            } else {
                                cell.setCellValue(cellData);
                            }
                        }

                    }
                }
            }
        }
    }

    private void getExcelMapData(List<Map> data, HSSFWorkbook workbook, List<String> keys, HSSFSheet sheet, HSSFCellStyle style2) {
        HSSFRow row;
        if (!data.isEmpty()) {
            int index=0;
            for (int i = 0; i < data.size(); i++) {
                index++;
                row = sheet.createRow(index);
                Map<String,Object> map = data.get(i);
                Set<String> keySet = map.keySet();
                for (String s : keySet) {
                    if (keys.contains(s)){
                        int i1 = keys.indexOf(s);
                        HSSFCell cell = row.createCell(i1);
                        cell.setCellStyle(style2);

                        Object o = map.get(s);
                        String cellData=null;
                        if (o.getClass().getSimpleName().equalsIgnoreCase("Timestamp")){
                            cellData = o.toString();
                        }else {
                            cellData = (String) o;
                        }
                        if (cellData != null && !cellData.isEmpty()) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(cellData);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(cellData));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        cellData);
                                HSSFFont font3 = workbook.createFont();
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }

                    }
                }
            }
        }
    }


    private void getExcelHeader(List<String> header, HSSFSheet sheet, HSSFCellStyle style) {
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        if (!header.isEmpty()){
            for (int i = 0; i < header.size(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                HSSFRichTextString text = new HSSFRichTextString(header.get(i));
                cell.setCellValue(text);
            }
        }
    }

    private HSSFCellStyle getHssfCellStyleTwo(HSSFWorkbook workbook) {
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        // 把字体应用到当前的样式
        style2.setFont(font2);
        return style2;
    } private  CellStyle getCellStyleTwo(Workbook workbook) {
        CellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        // 把字体应用到当前的样式
        style2.setFont(font2);
        return style2;
    }

    private HSSFCellStyle getHssfCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
}