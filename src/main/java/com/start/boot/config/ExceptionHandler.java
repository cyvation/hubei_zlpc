package com.start.boot.config;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.start.boot.common.MessageResult;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandler {

    /**
     * 日志对象
     */
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)//异常捕获
    public ModelAndView defaultErrorHandler(Exception exception, HttpServletResponse response)  {
        logger.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        MessageResult messageResult = new MessageResult();
        messageResult.setCode(500);
        messageResult.setMessage("服务器出错"+exception.getMessage());
        try{
            FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
            HashMap<String, Object> error = new HashMap<>();
            String message = JSONObject.toJSONString(messageResult);
            error.put("message",message);
            fastJsonJsonView.setAttributesMap(error);
            modelAndView.setView(fastJsonJsonView);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("json序列化出错");
        }
        response.setStatus(500);
        return modelAndView;
    }
}
