package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.NoticeMapper;
import com.start.boot.domain.Notice;
import com.start.boot.domain.NoticeExample;
import com.start.boot.service.NoticeService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @caomin
 * @create 2017-12-21 11:00
 **/
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public Notice save(Notice notice) {
        if (!StringUtils.isEmpty(notice.getBh())){
            noticeMapper.updateByPrimaryKeySelective(notice);
        }else {
            String bh = getBh(notice);
            notice.setBh(bh);
            noticeMapper.insertSelective(notice);
        }
        return notice;
    }


    private String getBh(Notice notice){
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.setOrderByClause("BH DESC");
        List<Notice> notices = noticeMapper.selectByExample(noticeExample);
        if (!CollectionUtils.isEmpty(notices)){

            String bh = notices.get(0).getBh();
            Long i = Long.valueOf(bh) + 1;
            return String.valueOf(i);
        }else {
            DateTime dateTime=new DateTime();
            int year = dateTime.getYear();
            return  notice.getFbrdwbm()+year+"000001";
        }
    }

    @Override
    public Notice getNoticeByBh(String bh) {
        if (!StringUtils.isEmpty(bh)){
            return noticeMapper.selectByPrimaryKey(bh);
        }
        return null;
    }

    @Override
    public Notice saveNotice(Notice notice) {
          noticeMapper.updateByPrimaryKey(notice);
          return notice;
    }
}
