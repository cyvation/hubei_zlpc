package com.start.boot.service;


import com.start.boot.domain.Wsmb;

import java.util.List;

public interface WsmbService {


    List<Wsmb>getWsbmList(String pcflbm,String wsmblb);

}
