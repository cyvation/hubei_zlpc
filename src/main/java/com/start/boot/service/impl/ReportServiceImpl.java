package com.start.boot.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.start.boot.dao.ajpc.ReportMapper;
import com.start.boot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportMapper reportMapper;
    @Override
    public Map getPcbgMb(String pcflbm,String wsmblb) {
        return reportMapper.getPcbgMb(pcflbm,wsmblb);
    }

    @Override
    public String getMaxPczybm(String pczybm) {
        String newPczybm="";
       String maxPczybm = reportMapper.getMaxPczybm(pczybm);
       if(StringUtils.isEmpty(maxPczybm)||maxPczybm==""){
      return pczybm.substring(0,13)+"001";
       }
       Long maxLong = Long.parseLong(maxPczybm)+1;
       newPczybm =maxLong.toString();
        return  newPczybm;
    }
}
