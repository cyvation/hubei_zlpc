package com.start.boot.query;

import com.start.boot.domain.YX_PC_PCZL;

import java.util.Date;

/**
 * Created by lei on 2018/1/29.
 */
public class PczlQuery extends YX_PC_PCZL{

    private String keyword;

    private String date_start;

    private String date_end;

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
