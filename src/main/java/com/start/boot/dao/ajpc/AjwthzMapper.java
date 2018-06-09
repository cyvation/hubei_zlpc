package com.start.boot.dao.ajpc;

import java.util.List;
import java.util.Map;

import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.query.QueryTable;
import org.springframework.stereotype.Repository;

@Repository
public interface AjwthzMapper {

    List<AjpcwtxVo> getAjwthzList(Map query) throws Exception;

    List<AjpcwtxVo> getBarAjwthzList(Map query) throws Exception;
    /**
     * 获取总体情况办结案件列表
     * @param map
     * @return
     */
    List<Map> getAjhzjbxx(Map map);
    Map getAjhzjbxxCount(Map map);
}
