package com.start.boot.adquery;

import com.start.boot.query.AdvanceQueryView;
import com.start.boot.query.BaseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * 高级查询请求对象
 *
 * @caomin
 * @create 2017-12-14 11:04
 **/
public class AdQueryRequest extends BaseQuery{

    /**
     * 查询是否过滤单位，默认过滤（只显示本单位的，专项和交叉查询时。设置为N）
     */
    private String bdw="Y";

    private String pcflbm;

    // 完成日期
    private String startDate;
    private String endDate;
    /**
     * 承办单位
     */
    private String extCbdw;

    public String getExtCbdw() {
        return extCbdw;
    }

    public void setExtCbdw(String extCbdw) {
        this.extCbdw = extCbdw;
    }

    private List<AdvanceQueryView> parmarter=new ArrayList<>();

    public String getBdw() {
        return bdw;
    }

    public void setBdw(String bdw) {
        this.bdw = bdw;
    }

    public List<AdvanceQueryView> getParmarter() {
        return parmarter;
    }

    public void setParmarter(List<AdvanceQueryView> parmarter) {
        this.parmarter = parmarter;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
