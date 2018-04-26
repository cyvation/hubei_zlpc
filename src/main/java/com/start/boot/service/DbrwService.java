package com.start.boot.service;

import com.start.boot.pojo.vo.DbrwVo;
import com.start.boot.query.DbrwQuery;

import java.util.List;

/**
 * 待办任务service
 *
 * @caomin
 * @create 2017-12-05 10:27
 **/
public interface DbrwService {
    /**
     * 获取工作桌面所有的菜单
     * @return
     */
    List<DbrwVo>getAllDeskTopMenu(DbrwQuery dbrwQuery) throws Exception;

}
