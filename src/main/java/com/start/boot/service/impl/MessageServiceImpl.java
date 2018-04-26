package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.MessageMapper;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.Message;
import com.start.boot.domain.MessageExample;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.YX_PC_JBXXExample;
import com.start.boot.query.MessageQuery;
import com.start.boot.query.Query;
import com.start.boot.service.JbxxService;
import com.start.boot.service.MessageService;
import com.start.boot.utils.QueryUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    QueryUtils queryUtils;


    @Autowired
    MessageMapper messageMapper;

    @Autowired
    YX_PC_JBXXMapper jbxxMapper;


    @Autowired
    JbxxService jbxxService;

    @Override
    public Message save(Message message) {
        DateTime dateTime = new DateTime();
        Date nowDate = dateTime.toDate();
        message.setZhxgsj(nowDate);
        message.setFsrq(nowDate);
        message.setSffs("0");
        message.setXxzt("0");
        message.setSfsc("N");
        message.setXxxh(UUID.randomUUID().toString());
        messageMapper.insertSelective(message);
        return message;
    }

    @Override
    public List<Map<String,Object>>  getMessage() throws Exception {
        DateTime dateTime = new DateTime();
        LocalDate todayLocal = new LocalDate(dateTime);
        LocalDate date = new LocalDate(dateTime.minusDays(1));
        Date yesToday = date.toDate();
        Date today = todayLocal.toDate();
        LinkedList<String> groupBy = new LinkedList<>();
        Query query = new Query();
        groupBy.add("JSRDWBM");
        groupBy.add("JSRGH");
        groupBy.add("XXLX");

        HashMap<String, String> disPlayColumn = new HashMap<>();
        disPlayColumn.put("JSRDWBM","JSRDWBM");
        disPlayColumn.put("JSRGH","JSRGH");
        disPlayColumn.put("XXLX","XXLX");
        disPlayColumn.put("count(1)","EMAILCOUNT");
        query.setTableName("YX_XT_XXJL")
                .setDisplayColumnName(disPlayColumn)
                .setGroupByClause(groupBy)
                .createCriteria()
                .andIsNotNull("JSRDWBM")
                .andEqualTo("SFFS","0")
                .andbetween("FSRQ",yesToday,today);
       return queryUtils.getMap(query.build());
    }

    @Override
    public String getEmailAdress(String dwbm, String gh) throws Exception {
        HashMap<String, String> disPlayColumn = new HashMap<>();
        disPlayColumn.put("DZYJ","DZYJ");
        Query query = new Query();
        query.setTableName("XT_ZZJG_RYBM")
                .setDisplayColumnName(disPlayColumn)
                .createCriteria()
                .andEqualTo("DWBM",dwbm)
                .andEqualTo("GH",gh);
        List<Map<String,Object>> map = queryUtils.getMap(query.build());
        if (!CollectionUtils.isEmpty(map)&& map.size()>0){
            try {
                return (String) map.get(0).get("DZYJ");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<YX_PC_JBXX> getMessagePcEndList(String dwbm, String gh, String ajmc) {
        ArrayList<YX_PC_JBXX> result = new ArrayList<>();
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        if (StringUtils.isNotEmpty(dwbm)&&StringUtils.isNotEmpty(gh)){
            criteria.andJsrdwbmEqualTo(dwbm)
                    .andJsrghEqualTo(gh)
                    .andXxlxEqualTo("0")
                    .andXxztEqualTo("0");

        }

        List<Message> messages = messageMapper.selectByExample(messageExample);
       ArrayList<String> pcslbmlist = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messages)){
            for (Message message : messages) {
                String pcslbm = message.getGlbmsah();
                if (StringUtils.isNotEmpty(pcslbm)) {
                    YX_PC_JBXX jbxx = jbxxService.getJbxx(pcslbm);
                    if (jbxx!=null){
                        result.add(jbxx);
                        pcslbmlist.add(jbxx.getPCSLBM());
                    }
                }
            }
        }
        if (StringUtils.isNotEmpty(ajmc)){
            YX_PC_JBXXExample yx_pc_jbxxExample = new YX_PC_JBXXExample();
            YX_PC_JBXXExample.Criteria criteria1 = yx_pc_jbxxExample.createCriteria();
            if (!CollectionUtils.isEmpty(pcslbmlist)){
                criteria1.andPcslbmIn(pcslbmlist);
            }
            criteria1.andAjmcLike("%"+ajmc+"%");
             return  jbxxMapper.selectByExample(yx_pc_jbxxExample);
        }
        return result;
    }

    @Override
    public void updateMessageZt(String glbmsah) {
       messageMapper.updateMessageZt(glbmsah);

    }

    @Override
    public void updateMessageFszt( String xxlx,String dwbm, String gh) {
        messageMapper.updateMessageFszt(xxlx,dwbm,gh);
    }


    @Override
    public List<Message> getMessageList(MessageQuery query) {
        MessageExample messageExample = new MessageExample();
        String dwbm = query.getDwbm();
        String gh = query.getGh();
        if (StringUtils.isNotEmpty(dwbm)&&StringUtils.isNotEmpty(gh)){
            MessageExample.Criteria criteria = messageExample.createCriteria();
            criteria.andJsrdwbmEqualTo(dwbm)
                    .andJsrghEqualTo(gh).andSfscEqualTo("N");
            String searchName = query.getSearchName();
            if (StringUtils.isNotEmpty(searchName)){
                criteria.andXxnrLike("%"+searchName+"%");
            }
            if (StringUtils.isNotEmpty(query.getXxzt())){
                criteria.andXxztEqualTo(query.getXxzt());
            }else {
                criteria.andXxztEqualTo("0");
            }
            Date startTime = query.getStartTime();
            if (startTime !=null){
                criteria.andFsrqGreaterThanOrEqualTo(startTime);
            }
            Date endTime = query.getEndTime();
            if (endTime !=null){
                DateTime dateTime=new DateTime(endTime);
                Date date = dateTime.plusDays(1).toLocalDate().toDate();
                criteria.andFsrqLessThanOrEqualTo(date);
            }
            messageExample.setOrderByClause("FSRQ DESC");
            return  messageMapper.selectByExample(messageExample);
        }
        return null;
    }

    @Override
    public Integer getMessageCount(MessageQuery query) {
        MessageExample messageExample = new MessageExample();
        String dwbm = query.getDwbm();
        String gh = query.getGh();
        if (StringUtils.isNotEmpty(dwbm)&&StringUtils.isNotEmpty(gh)) {
            MessageExample.Criteria criteria = messageExample.createCriteria();
            criteria.andJsrdwbmEqualTo(dwbm)
                    .andJsrghEqualTo(gh).andSfscEqualTo("N").andXxztEqualTo("0");
            return Math.toIntExact(messageMapper.countByExample(messageExample));
        }
        return null;
    }

    @Override
    public void batchUpdateXxzt(MessageQuery messageQuery) {
        if (messageQuery.getIds().isEmpty()){
            return;
        }
        messageMapper.batchUpdateXxzt(messageQuery.getIds());
    }

    @Override
    public void updateMessageZtByDwbmAndGh(String currentDwbm, String currentGh) {
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andJsrdwbmEqualTo(currentDwbm).andJsrghEqualTo(currentGh).andXxztEqualTo("0");
        Message record = new Message();
        record.setXxzt("1");
        messageMapper.updateByExampleSelective(record,messageExample);

    }
}
