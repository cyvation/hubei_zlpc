/*
package com.test;

import com.start.boot.Application;
import com.start.boot.dao.ajpc.AdvancedQueryMapper;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.YX_PC_JBXXExample;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

*/
/**
 * @caomin
 * @create 2018-01-04 14:41
 **//*

@WebAppConfiguration
@SpringBootTest(classes = Application.class)
@RunWith(value= SpringJUnit4ClassRunner.class)
public class PcfkTest {
    @Autowired
    YX_PC_JBXXMapper jbxxMapper;


    @Test
    public void test(){
        YX_PC_JBXXExample yx_pc_jbxxExample = new YX_PC_JBXXExample();
        Date date = DateTime.now().toLocalDate().minusDays(10).toDate();
        yx_pc_jbxxExample.createCriteria().andPcjdbhEqualTo("008").andZhxgsjLessThanOrEqualTo(date);
        List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(yx_pc_jbxxExample);

        System.out.println(yx_pc_jbxxes);

    }

}
*/
