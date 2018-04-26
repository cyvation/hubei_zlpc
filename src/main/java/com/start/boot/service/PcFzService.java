package com.start.boot.service;

import com.start.boot.domain.YX_PC_FZ;

import java.util.List;

/**
 * Created by caomin on 2017/11/8.
 */
public interface PcFzService {


    List<YX_PC_FZ> getListByPchdbmAndPczbm(String pchdbm, String pczbm);
    List<YX_PC_FZ> getListByPchdbm(String pchdbm);
}
