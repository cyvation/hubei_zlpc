package com.start.boot.domain;


import com.start.boot.common.Param_Pager;

/**
 * 通知公告查询实体类
 * Created by lei on 2017/11/1.
 */
public class Param_Tzgg extends Param_Pager {

    private String dwbm;
    private String KeyWord;
    private String FBSJBeg;
    private String FBSJEnd;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getFBSJBeg() {
        return FBSJBeg;
    }

    public void setFBSJBeg(String FBSJBeg) {
        this.FBSJBeg = FBSJBeg;
    }

    public String getFBSJEnd() {
        return FBSJEnd;
    }

    public void setFBSJEnd(String FBSJEnd) {
        this.FBSJEnd = FBSJEnd;
    }

}
