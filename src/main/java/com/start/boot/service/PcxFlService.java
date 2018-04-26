package com.start.boot.service;

import com.start.boot.domain.Yx_Pc_Pcx;
import com.start.boot.domain.Yx_Pc_PcxFl;
import com.start.boot.pojo.dto.CqPcxFlDto;
import com.start.boot.pojo.vo.ZdpcxVo;
import com.start.boot.query.ZdpccxQuery;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/18
 * @说明 评查项分类Service
 */
public interface PcxFlService {

    /**
     * 根据 评查项分类编码和评查模板编码获取 评 查项
     * @param pcxflbm
     * @param
     * @return
     */
    List<Yx_Pc_Pcx> getPcxByPcxflbmAndPcslbm(String pcxflbm, String pcslbm);

    /**
     * 获取评查项分类
     * @param pcslbm
     * @return
     */
    List<Yx_Pc_PcxFl>  getPcxFl(String pcslbm);

    /**
     * 根据评查受理编码 获取扣分值总和
     * @param pcslbm
     * @return
     */
    String getSumKfz(String pcslbm);

    /**
     * 获取某一个评分项下的扣分分数
     * @param pcslbm
     * @param pcflbm
     * @return
     */
    String getSumKfzByPcslbmAndPcflbm(String pcslbm,String pcflbm);

    /**
     * 获取自动评查项结果
     * @param pcslbm
     * @param pcxflbm
     * @return
     */
    List<ZdpcxVo>getZdpcxResult(String pcslbm, String pcxflbm);

    ZdpcxVo getZdpccxByPcx(ZdpccxQuery query) throws Exception;


    List<CqPcxFlDto>  getCqPcx(String pcslbm);
}
