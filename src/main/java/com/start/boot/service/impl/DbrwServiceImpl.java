package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.DbrwMapper;
import com.start.boot.domain.Dbrw;
import com.start.boot.domain.DbrwExample;
import com.start.boot.pojo.vo.DbrwVo;
import com.start.boot.query.DbrwQuery;
import com.start.boot.service.DbrwService;
import com.start.boot.validate.DbrwValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @caomin
 * @create 2017-12-05 10:28
 **/
@Service
public class DbrwServiceImpl implements DbrwService {

    @Autowired
    DbrwMapper dbrwMapper;

    @Autowired
    DbrwValidate dbrwValidate;

    @Override
    public List<DbrwVo> getAllDeskTopMenu(DbrwQuery dbrwQuery) throws Exception{
        ArrayList<DbrwVo> result = new ArrayList<>();
        List<Dbrw> dbrws = dbrwMapper.selectByExample(new DbrwExample());
        for (Dbrw dbrw : dbrws) {
            DbrwVo dbrwVo = new DbrwVo();
            BeanUtils.copyProperties(dbrw,dbrwVo);
            String validatename = dbrw.getValidatename();
            if (StringUtils.isNotEmpty(validatename)){
                try {
                    Method method = dbrwValidate.getClass().getDeclaredMethod(validatename,DbrwQuery.class);
                    Object invoke = method.invoke(dbrwValidate, dbrwQuery);
                    if (invoke!=null){
                        if (invoke instanceof Integer){
                            dbrwVo.setCount((Integer) invoke);
                        }
                    }else {//如果返回结果为null，设置为0
                        dbrwVo.setCount(0);
                    }
                }catch (NoSuchMethodException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            result.add(dbrwVo);
        }

        return result;
    }
}
