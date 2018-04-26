package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by lei on 2017/11/4.
 */
@Repository
public interface HandleMapper {

    void get_pclist(Map map);

    void getPchdList(Map map);

    void get_splst(Map map);

    void get_cbrfklist(Map map);

    void get_cbbmfklist(Map map);

    void getDocFiles(Map map);

    void uptDealAjqr(Map map);

    void uptSendPcsp(Map map);

    void uptDealPcsp(Map map);

    void uptSendPcfk(Map map);

    void uptDealPcfk(Map map);

    void uptDealBmfk(Map map);

    void uptDealPcjs(Map map);

    void addPcyj(Map map);

    void get_pcyblist(Map map);

    void get_spxx(Map map);

    void cancelApprove(Map map);

    void backApprove(Map map);

    void getYwbmfzr(Map map);
}
