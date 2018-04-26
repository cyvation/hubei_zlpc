package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CaseInfoMapper {

    void getGlajs(Map map);

    void getDocFiles(Map map);

    void getDossierFiles(Map map);

    void getCaseInfo(Map map);

}
