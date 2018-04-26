package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * Created by lei on 2017/10/30.
 */
@Repository
public interface PcmbMapper {
    void getPcmb(Map map);
    List<Map> getPcmbByPcmbbm(Map map);
}
