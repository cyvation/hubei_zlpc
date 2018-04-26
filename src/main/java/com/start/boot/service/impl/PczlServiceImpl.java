package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.YX_PC_PCZLMapper;
import com.start.boot.domain.YX_PC_PCZL;
import com.start.boot.domain.YX_PC_PCZLExample;
import com.start.boot.query.PczlQuery;
import com.start.boot.service.PczlService;
import com.start.boot.support.utils.DateUtils;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.*;

/**
 * Created by lei on 2018/1/27.
 */
@Service
public class PczlServiceImpl implements PczlService {

    @Autowired
    private YX_PC_PCZLMapper yxPcPczlMapper;


    /**
     * 添加评查资料
     * @param pczl
     * @return
     */
    @Override
    @Transactional
    public YX_PC_PCZL insertPczl(YX_PC_PCZL pczl) {

        String bh = getBh(pczl);
        pczl.setBh(bh);
        pczl.setZhxgsj(new Date());
        yxPcPczlMapper.insertSelective(pczl);

        return pczl;
    }

    protected String getBh(YX_PC_PCZL pczl) {

       YX_PC_PCZLExample example = new YX_PC_PCZLExample();
       example.setOrderByClause("BH DESC");
        List<Map> yx_pc_pczls = yxPcPczlMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(yx_pc_pczls)) {
            String bh = (String) yx_pc_pczls.get(0).get("bh");
            Long i = Long.valueOf(bh) + 1;
            return String.valueOf(i);
        }else {
            DateTime dateTime=new DateTime();
            int year = dateTime.getYear();
            return  pczl.getDwbm()+ year +"000001";
        }

    }

    /**
     * 获取评查资料
     * @param pczl
     * @return
     */
    @Override
    public List<Map> getPczl(PczlQuery pczl) throws ParseException {
        YX_PC_PCZLExample example=new YX_PC_PCZLExample();
        example.setOrderByClause("zhxgsj DESC");

        String keyword = pczl.getKeyword();
        Integer zllx = pczl.getZllx();
        String date_start = pczl.getDate_start();
        String date_end = pczl.getDate_end();

        // 查看别人共享的
        YX_PC_PCZLExample.Criteria criteria = example.createCriteria();
        criteria.andSfscEqualTo("N").
                andGxbmEqualTo("1")
                .andZllxEqualTo(zllx);

        // 查看自己的
        YX_PC_PCZLExample.Criteria criteria2 = example.createCriteria();
        criteria2.andSfscEqualTo("N")
                .andDwbmEqualTo(pczl.getDwbm())
                .andGhEqualTo(pczl.getGh())
                .andZllxEqualTo(zllx);


        if (!StringUtils.isEmpty(keyword)) {
            criteria.andZlmcLike("%"+keyword +"%");
            criteria2.andZlmcLike("%"+keyword +"%");
        }

        if (StringUtils.isNotEmpty(date_start)) {
            DateTime dateTime=new DateTime(DateUtils.converToDate(date_start));
            Date date = dateTime.plusDays(1).toLocalDate().toDate();
            criteria.andCjsjGreaterThanOrEqualTo(date);
            criteria2.andCjsjGreaterThanOrEqualTo(date);
        }
        if (StringUtils.isNotEmpty(date_end)) {
            DateTime dateTime=new DateTime(DateUtils.converToDate(date_end));
            Date date = dateTime.plusDays(1).toLocalDate().toDate();
            criteria.andCjsjLessThanOrEqualTo(date);
            criteria2.andCjsjLessThanOrEqualTo(date);
        }

        example.or(criteria2);
        List<Map> pczlList = yxPcPczlMapper.selectByExample(example);
//        pczlList.stream().forEach(x->{
//            if (x.get("gh").equals(pczl.getGh())) {
//                x.put("grzl", "1");
//            } else {
//                x.put("grzl", "0");
//            }
//
//        });
        return pczlList;
    }

    /**
     * 删除典型案例
     * @param bh
     * @return
     */
    @Override
    @Transactional
    public boolean delPczl(String bh) {

        if (StringUtils.isNotEmpty(bh)) {

            YX_PC_PCZL yxPcPczl = yxPcPczlMapper.selectByPrimaryKey(bh);
            yxPcPczl.setZhxgsj(new Date());
            yxPcPczl.setSfsc("Y");

            int i = yxPcPczlMapper.updateByPrimaryKeySelective(yxPcPczl);
            if (i != -1) {
                return true;
            }
        }

        return false;
    }

    /**
     * 修改典型案例
     * @param pczl
     * @return
     */
    @Override
    @Transactional
    public YX_PC_PCZL updatePczl(YX_PC_PCZL pczl) {

        pczl.setZhxgsj(new Date());
        yxPcPczlMapper.updateByPrimaryKeySelective(pczl);
        return pczl;
    }

    /**
     * 获取案件信息
     * @param bh
     * @return
     */
    @Override
    public YX_PC_PCZL getPczlByBh(String bh) {

        if (StringUtils.isNotEmpty(bh)) {
            return yxPcPczlMapper.selectByPrimaryKey(bh);
        }

        return null;
    }

    @Override
    @Transactional
    public YX_PC_PCZL savePczl(YX_PC_PCZL pczl) {

        pczl.setZhxgsj(new Date());
        yxPcPczlMapper.updateByPrimaryKeySelective(pczl);
        return pczl;
    }

}
