package com.start.boot.utils;

import com.start.boot.pojo.vo.BgAjlb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xwpf.usermodel.*;
import org.joda.time.DateTime;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PoiUtils {


    private static Log logger = LogFactory.getLog(PoiUtils.class);

    /**
     * 写案件列表到报告中
     *
     * @param list     案件列表
     * @param document 文档对象
     */
    private void writeAjList(List<BgAjlb> list, XWPFDocument document) {
        Iterator<XWPFTable> itTable = document.getTablesIterator();
        while (itTable.hasNext()) {
            XWPFTable table = (XWPFTable) itTable.next();
            //新增表格行
            for (int i = table.getNumberOfRows(); i <= list.size(); i++) {
                XWPFTableRow row = table.createRow();
                row.setRepeatHeader(true);
                row.setCantSplitRow(true);
            }
            // 添加边框
            CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
            CTBorder hBorder = borders.addNewInsideH();
            hBorder.setVal(STBorder.Enum.forString("single"));
            hBorder.setSz(new BigInteger("1"));
            hBorder.setColor("000000");

            CTBorder vBorder = borders.addNewInsideV();
            vBorder.setVal(STBorder.Enum.forString("single"));
            vBorder.setSz(new BigInteger("1"));
            vBorder.setColor("000000");

            CTBorder lBorder = borders.addNewLeft();
            lBorder.setVal(STBorder.Enum.forString("single"));
            lBorder.setSz(new BigInteger("1"));
            lBorder.setColor("000000");

            CTBorder rBorder = borders.addNewRight();
            rBorder.setVal(STBorder.Enum.forString("single"));
            rBorder.setSz(new BigInteger("1"));
            rBorder.setColor("000000");

            CTBorder tBorder = borders.addNewTop();
            tBorder.setVal(STBorder.Enum.forString("single"));
            tBorder.setSz(new BigInteger("1"));
            tBorder.setColor("000000");

            CTBorder bBorder = borders.addNewBottom();
            bBorder.setVal(STBorder.Enum.forString("single"));
            bBorder.setSz(new BigInteger("1"));
            bBorder.setColor("000000");

            int rcount = table.getNumberOfRows();
            //设置案件列表每一列对应的值
            for (int i = 1; i < rcount; i++) {
                BgAjlb bgAjlb = list.get(i - 1);
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (int j = 0; j < cells.size(); j++) {
                    XWPFTableCell cell = cells.get(j);
                    switch (j) {
                        case 0:
                            cell.setText(bgAjlb.getXh());
                            break;
                        case 1:
                            cell.setText(bgAjlb.getBadw());
                            break;
                        case 2:
                            cell.setText(bgAjlb.getAjmc());
                            break;
                        case 3:
                            cell.setText(bgAjlb.getCbr());
                            break;
                        case 4:
                            cell.setText(bgAjlb.getPcy());
                            break;
                        case 5:
                            cell.setText(bgAjlb.getPcdw());
                            break;
                        case 6:
                            cell.setText(bgAjlb.getPcjl());
                            break;
                        case 7:
                            cell.setText(bgAjlb.getPcfxwt());
                            break;

                    }
                }
            }
        }
    }


    /**
     * 替换段落里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInPara(XWPFDocument doc, Map<String, String> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para   要替换的段落
     * @param params 参数
     */
    private void replaceInPara(XWPFParagraph para, Map<String, String> params) {
        List<XWPFRun> runs;
        Set<Entry<String, String>> entries = params.entrySet();
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                if (StringUtils.isEmpty(runText))continue;
                for (Entry<String, String> entry : entries) {
                    if (runText.contains(entry.getKey())){
                        String replace = runText.replace(entry.getKey(), entry.getValue());
                        run.setText(replace,0);
                    }
                }
            }
        }
    }

    /**
     * 替换表格里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInTable(XWPFDocument doc, Map<String, String> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        this.replaceInPara(para, params);
                    }
                }
            }
        }
    }

    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据报告模板，生成报告
     *
     * @param sourcePath 报告模板路径（绝对路径
     * @param targetPath 生成后的报告存放路径
     * @param parms      报告中要替换的内容，key-value
     * @param ajlbs      案件列表
     * @return
     * @throws Exception
     */
    public   boolean  createBg(String sourcePath, String targetPath, Map<String, String> parms, List<BgAjlb> ajlbs) throws Exception {
         InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(sourcePath);
            File file = new File(targetPath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            os = new FileOutputStream(file);
            XWPFDocument doc = new XWPFDocument(is);
            //替换段落里面的变量
            this.replaceInPara(doc, parms);
            //替换表格里面的变量
            this.replaceInTable(doc, parms);
            //生成案件列表
            if (!CollectionUtils.isEmpty(ajlbs)){
                this.writeAjList(ajlbs, doc);
            }
            doc.write(os);
        } catch (FileNotFoundException f) {
            f.printStackTrace();
            logger.error("生成报告失败" + f.getMessage());
            throw new RuntimeException("没有找到模板文件" + f.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成报告失败" + e.getMessage());
            throw new RuntimeException("生成报告失败" + e.getMessage());
        } finally {
            if (null != is) {
                this.close(os);
            }
            if (null != os) {
                this.close(is);
            }
        }
        return true;
    }

    /**
     * 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
     *
     * @throws Exception
     */
    @Test
    public void testTemplateWrite() throws Exception {
        String sourcePath = "h:/附件2：季度质量评查报告模板.docx";
        String targetPath = "g:/cm.doc";
        Map<String, String> map = new HashMap<String, String>();
        map.put("${DWMC}", "上海市人民检察院");
        map.put("${PCSAH}", "山东省院常评[2018]3700001000023号");
        map.put("${PCR}", "柴萌");
        map.put("${PCRQ}", DateTime.now().toString());
        map.put("${PCJDMS}", "评查办理");
        map.put("${CBDW}", "山东省院");
        map.put("${CBBM}", "侦查监督处");
        map.put("${CBR}", "吴鹏飞");
        map.put("${PCYJ}", "合格案件");
        map.put("${BZXX}", "备注");

        ArrayList<BgAjlb>
                ajlbs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BgAjlb bgAjlb = new BgAjlb();
            bgAjlb.setBadw("山东省" + i);
            bgAjlb.setCbr("柴萌" + i);
            bgAjlb.setAjmc("案件名称" + i);
            bgAjlb.setPcdw("山东省" + i);
            bgAjlb.setPcfxwt("问题列表" + i);
            bgAjlb.setPcjl("评查结论" + i);
            bgAjlb.setPcy("评查员" + i);
            bgAjlb.setXh("序号" + i);
            ajlbs.add(bgAjlb);
        }
        createBg(sourcePath, targetPath, map, ajlbs);
    }

}