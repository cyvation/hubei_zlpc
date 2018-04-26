package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.AjwthzMapper;
import com.start.boot.service.AjwthzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.query.QueryTable;


@Service
public class AjwthzServiceImpl implements AjwthzService {
    @Autowired
    private AjwthzMapper ajwthzMapper;

    @Override
    public List<AjpcwtxVo> getAjwthzList(QueryTable query) throws  Exception{
        List<AjpcwtxVo> list = null;
        try {
            list = ajwthzMapper.getAjwthzList(query);
        }catch (Exception e){
            throw  e;
        }
        return  list;
    }
}
