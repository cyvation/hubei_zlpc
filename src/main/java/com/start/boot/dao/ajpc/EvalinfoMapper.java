package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 获取盘查卷宗接口
 * Created by user on 2017/11/12.
 */
@Repository
public interface EvalinfoMapper {
    /**
     * 获取评查卷宗文件
     * @return
     */
    public List<Map> getPcjz(Map map);
}
