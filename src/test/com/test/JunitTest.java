/*
package com.test;

import org.joda.time.DateTime;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

*/
/*
*
 * 单元测试
 *
 * @caomin
 * @create 2017-12-14 17:07
 *
*//*


public class JunitTest {


    @Test
    public void test()throws Exception{
        File file = new File("d://log.txt");
        String s = DateTime.now().toString("YYYY-MM-dd");
        System.out.println(s);
        FileWriter fileWriter=new FileWriter(file,true);
        fileWriter.write("asdfasdfasdfsadf\r\n");
        fileWriter.flush();
    }

}
*/
