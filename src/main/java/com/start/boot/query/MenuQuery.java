package com.start.boot.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("菜单查询对象")
public class MenuQuery {
  @ApiModelProperty("单位编码")
  private String  dwbm;

  @ApiModelProperty("工号")
  private String  gh;

  @ApiModelProperty("评查分类编码")
  private String  pcflbm;

  @ApiModelProperty("评查活动编码")
  private String  pchdbm;

  @ApiModelProperty("评查资源编码")
  private String  pczybm;

  @ApiModelProperty("流程模板编码")
  private String  lcmbbm;

  @ApiModelProperty("流程节点编码")
  private String  lcjdbh;

  @ApiModelProperty("操作类型编码")
  private String  czlxbm;

  @ApiModelProperty("说明")
  private String  sm;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public String getPczybm() {
        return pczybm;
    }

    public void setPczybm(String pczybm) {
        this.pczybm = pczybm;
    }

    public String getLcmbbm() {
        return lcmbbm;
    }

    public void setLcmbbm(String lcmbbm) {
        this.lcmbbm = lcmbbm;
    }

    public String getLcjdbh() {
        return lcjdbh;
    }

    public void setLcjdbh(String lcjdbh) {
        this.lcjdbh = lcjdbh;
    }

    public String getCzlxbm() {
        return czlxbm;
    }

    public void setCzlxbm(String czlxbm) {
        this.czlxbm = czlxbm;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }
}
