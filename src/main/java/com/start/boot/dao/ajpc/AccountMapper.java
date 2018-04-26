package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Repository
public interface AccountMapper {

    void userLogin(Map map);

    void getFunction(Map map);

    void getTzgg(Map map);

    void getKjcd(Map map);

    void addKjcd(Map map);

    void delKjcd(Map map);

    void getXxsl(Map map);

    void getXxlb(Map map);

    void addXxjl(Map map);

    void updXxlb(Map map);

    void updatePassword(Map map);

    void getTaskCount(Map map);

    void getJsbm(Map map);

    void signByCaid(Map map);

    void getGncsByDwbm(Map map);
}
