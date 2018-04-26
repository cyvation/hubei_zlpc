package com.start.boot.pojo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caomin on 2017/11/15.
 */
public class PcxVoList {

   private List<Yx_Pc_PcxFlVo> pcxFlVos=new ArrayList();

    public List<Yx_Pc_PcxFlVo> getPcxFlVos() {
        return pcxFlVos;
    }

    public void setPcxFlVos(List<Yx_Pc_PcxFlVo> pcxFlVos) {
        this.pcxFlVos = pcxFlVos;
    }
}
