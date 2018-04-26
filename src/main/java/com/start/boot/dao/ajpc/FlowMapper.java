package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by lei on 2017/11/12.
 */
@Repository
public interface FlowMapper {

    void updFpjs(Map map);

    void updPcbl(Map map);

    void updPcsp(Map map);

    void updPcfk(Map map);

    void updBmfk(Map map);

    void updLcjs(Map map);

    void updPcjs(Map map);

    void updHdPcsx(Map map);

    void updHdPcfp(Map map);

    void updHdFasp(Map map);

    void updHdPcqd(Map map);

    void updHdJshd(Map map);

}
