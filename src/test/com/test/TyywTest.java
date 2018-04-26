//package com.test;
//
//import com.start.boot.Application;
//import com.start.boot.dao.ajpc.MessageMapper;
//import com.start.boot.dao.tyyw.TyywTestMapper;
//import com.start.boot.domain.MessageExample;
//import com.start.boot.domain.TyywTestExample;
//import com.start.boot.excel.ExcelUtils;
//import com.start.boot.pojo.vo.AdvanceQueryVo;
//import com.start.boot.service.AdvanceQueryService;
//import com.start.boot.service.SystemCoreConfigService;
//import org.joda.time.DateTime;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//*
// * 统一业务多数据源测试
// *
// * @caomin
// * @create 2017-12-13 11:20
// *
//
//
//
//
//@WebAppConfiguration
//@SpringBootTest(classes = Application.class)
//@RunWith(value= SpringJUnit4ClassRunner.class)
//public class TyywTest {
//
//
//
//
//     @Autowired
//     TyywTestMapper tyywTestMapper;
//
//
//
//
//
//     @Autowired
//    MessageMapper messageMapper;
//
//
//     @Autowired
//     AdvanceQueryService advanceQueryService;
//     @Autowired
//    private SystemCoreConfigService systemCoreConfigService;
//
//
//    @Autowired
//    ExcelUtils excelUtils;
//
//
//     @Test
//    public void test() throws Exception {
//         TyywTestExample tyywTestExample = new TyywTestExample();
//         long l = tyywTestMapper.countByExample(tyywTestExample);
//         long l1 = messageMapper.countByExample(new MessageExample());
//         System.out.println("多数据源测试成功");
//     }
//
//
//     @Test
//     public void testGetAnYou()throws Exception{
//         List<AdvanceQueryVo> allCondition = advanceQueryService.getAllCondition();
//
//         System.out.println(allCondition);
//     }
//
//     @Test
//     public  void testFile() throws IOException {
//         //写短信记录
//         String fileAdress = systemCoreConfigService.getSystemConfigValue("message_file_adress");
//         String s = DateTime.now().toString("YYYY-MM-dd");
//         File file = new File(fileAdress+s+".txt");
//         if (!file.getParentFile().exists()){
//             file.getParentFile().mkdirs();
//         }
//         FileWriter fileWriter=new FileWriter(file,true);
//         fileWriter.write("aaaaaaaaaaaaaaa\r\n");
//         fileWriter.flush();
//         fileWriter.close();
//     }
//
//     @Test
//     public void testExcelModel(){
//
//         excelUtils.testReadExcel();
//
//
//     }
//
//
//}
