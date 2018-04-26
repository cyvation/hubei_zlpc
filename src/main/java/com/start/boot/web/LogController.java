/*
package com.start.boot.web;


import com.start.boot.domain.LogEntity;
import com.start.boot.srevice.LogService;
import com.start.boot.support.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * Created by lei on 2017/10/31.
 *//*

@Component
public class LogController  extends ArchivesSystemBaseController implements HandlerExceptionResolver{

    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        ex.printStackTrace();
        if (handler instanceof HandlerMethod) {

            LOGGER.info(">>>>>>系统异常，记录异常信息到数据库------start------");

            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 远程访问IP
            String ip = IPUtils.getIpAddr(request);
            // 出错类名字
            String className = handlerMethod.getBeanType().getName();
            // 出错方法
            String methodName = handlerMethod.getMethod().getName();
            //参数
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            StringBuilder sb = new StringBuilder();
            for (MethodParameter methodParameter : methodParameters) {
                sb.append(methodParameter);
            }

            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw, true));

            // 插入异常日志到数据库
            LogEntity logEntity = new LogEntity();

            logEntity.setOperatorIp(ip);
            logEntity.setDwbm(getCurrentDwbm());
            logEntity.setException(ex.getMessage());
            logEntity.setOpertorName(getCurrentMC());
            logEntity.setOperatorId(getCurrentGh());
            logEntity.setMssage(ex.getClass().getSimpleName());
            logEntity.setLogger(className);
            logEntity.setRunInfo(String.format("className:%s methodName:%s ", className, methodName));

            System.out.println(logEntity.toString());
            super.testLog(ex.getMessage(),sb.toString(),ex);

            LOGGER.info(">>>>>>系统异常，记录异常信息到数据库------end------");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/view/other/404");


            return modelAndView;
    }
}
*/
