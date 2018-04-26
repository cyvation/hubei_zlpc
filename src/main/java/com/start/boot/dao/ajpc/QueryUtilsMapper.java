package com.start.boot.dao.ajpc;


import com.start.boot.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询mapper
 * Created by caomin on 2017/11/14
 */
@Repository
public interface QueryUtilsMapper {
    List<Map<String,Object>> query(Query query);
}
