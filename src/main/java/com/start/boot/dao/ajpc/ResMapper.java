package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by lei on 2017/10/31.
 */
@Repository
public interface ResMapper {

    void getTzgg(Map map);

    void addTzgg(Map map);

    void updTzgg(Map map);

    void delTzgg(Map map);

}
