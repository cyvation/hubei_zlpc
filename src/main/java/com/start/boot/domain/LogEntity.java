package com.start.boot.domain;

import java.util.Date;

/**
 * 日志实体
 * Created by lenovo on 2017/10/24.
 */
public class LogEntity {

    //日志编号
    private Integer logId;

    //记录时间
    private Date logDateTime;

    //日志级别
    private String logLevel;

    //出错类
    private String logger;

    //错误描述
    private String mssage;

    //异常信息
    private String exception;

    //运行信息
    private String runInfo;

    //单位
    private String dwbm;

    //操作人id
    private String operatorId;

    //操作人
    private String opertorName;

    //操作人ip
    private String operatorIp;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(Date logDateTime) {
        this.logDateTime = logDateTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMssage() {
        return mssage;
    }

    public void setMssage(String mssage) {
        this.mssage = mssage;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getRunInfo() {
        return runInfo;
    }

    public void setRunInfo(String runInfo) {
        this.runInfo = runInfo;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOpertorName() {
        return opertorName;
    }

    public void setOpertorName(String opertorName) {
        this.opertorName = opertorName;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "logId=" + logId +
                ", logDateTime=" + logDateTime +
                ", logLevel='" + logLevel + '\'' +
                ", logger='" + logger + '\'' +
                ", mssage='" + mssage + '\'' +
                ", exception='" + exception + '\'' +
                ", runInfo='" + runInfo + '\'' +
                ", dwbm='" + dwbm + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", opertorName='" + opertorName + '\'' +
                ", operatorIp='" + operatorIp + '\'' +
                '}';
    }
}
