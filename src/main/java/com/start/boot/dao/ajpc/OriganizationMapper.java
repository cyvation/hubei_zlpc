package com.start.boot.dao.ajpc;

import com.start.boot.domain.Jsgnfp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/25.
 */
@Repository
public interface OriganizationMapper {

    void getDwbm(Map map);

    void getDwBmJsInfoByDwBm(Map map);

    void addBmInfo(Map map);

    void getBmInfo(Map map);

    void deleteBmInfo(Map map);

    void addJsInfo(Map map);

    void getJsxh(Map map);

    void deleteJsInfo(Map map);

    void addJsgnfp(Map map);

    void getJsGnqx(Map map);

    void removeDwGnQx(Map map) throws Exception;

    void addDwGnQx(Map map);

    void getRyInfo(Map map);

    void getWfpRyInfo(Map map);

    void getAllGN(Map map);

    void addRYJSFP(Map map);

    void removeJob(Map map) throws Exception;

    void transJob(Map map) throws Exception;

    void getJSInfoByDwBm(Map map) ;

    void getBMBM(Map map);

    List<Map> getGncs(Jsgnfp map);

    int updateGncs(Jsgnfp jsgnfp);

    List<Map> getPjDwbm(Map map);
}
