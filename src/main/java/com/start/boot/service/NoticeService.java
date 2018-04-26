package com.start.boot.service;

import com.start.boot.domain.Notice;

/**
 * 通知公告service
 *
 * @caomin
 * @create 2017-12-21 10:36
 **/
public interface NoticeService {


    Notice save(Notice notice);

    Notice getNoticeByBh(String bh);

    Notice saveNotice(Notice notice);
}
