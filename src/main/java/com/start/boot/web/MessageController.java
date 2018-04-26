package com.start.boot.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.Message;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.query.MessageQuery;
import com.start.boot.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消息接口
 *
 * @caomin
 * @create 2017-12-05 9:34
 **/
@Api("消息相关接口")
@RequestMapping("/message")
@RestController
public class MessageController extends ArchivesSystemBaseController{

    @Autowired
    MessageService messageService;


    @ApiOperation("评查结束，给承办人发送消息")
    @PostMapping("/sendMessageToCbr")
    public MessageResult sendMessageToCbr(@RequestBody Message message)throws  Exception{
        message.setXxlx("0");
        messageService.save(message);
        return  new MessageResult("发送成功",200);
    }

    @ApiOperation("获取评查结束后的通知消息")
    @GetMapping("/getMessagePcEndList")
    public String getMessageFkList(String ajmc)throws  Exception{
        String currentDwbm = getCurrentDwbm();
        String currentGh = getCurrentGh();
        List<YX_PC_JBXX> messagePcEndList = messageService.getMessagePcEndList(currentDwbm, currentGh,ajmc);

        ArrayList<Map> resultMap = new ArrayList<>();
        messagePcEndList.stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
                failure(e.getMessage(), "没有查询到相关信息");
            }
        });
        String result = success(resultMap, "成功");
        return result;
    }

    @ApiOperation("修改消息状态为已读")
    @GetMapping("/updateMessageZt")
    public MessageResult updateMessageZt(String PCSLBM){
        messageService.updateMessageZt(PCSLBM);
        return new MessageResult("修改成功",200);
    }

    @ApiOperation("获取消息列表")
    @PostMapping("/getMessageList")
    public MessageResult getMessageList(@RequestBody(required = false) MessageQuery messageQuery){
        Page<Object> objects = PageHelper.startPage(messageQuery.getPage(), messageQuery.getPageSize());
        messageQuery.setDwbm(getCurrentDwbm());
        messageQuery.setGh(getCurrentGh());
        List<Message> messageList = messageService.getMessageList(messageQuery);
        return  new MessageResult("成功",200,messageList,(int)objects.getTotal());
    }

    @ApiOperation("获取消息数量")
    @GetMapping("/getMessageCount")
    public MessageResult getMessageCount(MessageQuery messageQuery){
        messageQuery.setDwbm(getCurrentDwbm());
        messageQuery.setGh(getCurrentGh());
        Integer messageCount = messageService.getMessageCount(messageQuery);
        return  new MessageResult("成功",200,messageCount);
    }

    @ApiOperation("批量更新消息状态")
    @PostMapping("/batchUpdateXxzt")
    public MessageResult batchUpdateXxzt(@RequestBody MessageQuery messageQuery){
        messageQuery.setDwbm(getCurrentDwbm());
        messageQuery.setGh(getCurrentGh());
             messageService.batchUpdateXxzt(messageQuery);
        return  new MessageResult("操作成功",200);
    }
    @ApiOperation("更新某个人的所有消息为已读")
    @GetMapping("/updateMessageZtByDwbmAndGh")
    public MessageResult updateMessageZtByDwbmAndGh(){
        messageService.updateMessageZtByDwbmAndGh(getCurrentDwbm(),getCurrentGh());
        return  new MessageResult("操作成功",200);
    }





}
