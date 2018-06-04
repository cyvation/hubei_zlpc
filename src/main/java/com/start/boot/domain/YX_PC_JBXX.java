package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.start.boot.common.Param_Pager;

import java.util.Date;

public class YX_PC_JBXX extends Param_Pager {
    @JSONField(name ="PCSLBM")
    private String PCSLBM;
    @JSONField(name = "PCSAH")
    private String PCSAH;
    @JSONField(name = "PCDWBM")
    private String PCDWBM;
    @JSONField(name = "PCFLBM")
    private String PCFLBM;
    @JSONField(name = "PCHDBM")
    private String PCHDBM;
    @JSONField(name = "BMSAH")
    private String BMSAH;
    @JSONField(name = "TYSAH")
    private String TYSAH;

    @JSONField(name = "AJMC")
    private String AJMC;

    @JSONField(name = "AJLBBM")
    private String AJLBBM;

    @JSONField(name = "AJLBMC")
    private String AJLBMC;

    @JSONField(name = "BPC_DWBM")
    private String BPCDWBM;

    @JSONField(name = "DWMC")
    private String BPCDWMC;

    @JSONField(name = "BPC_BMBM")
    private String BPCBMBM;

    @JSONField(name = "BPC_BMMC")
    private String BPCBMMC;

    @JSONField(name = "BPC_GH")
    private String BPCGH;

    @JSONField(name = "CBR")
    private String BPCMC;

    @JSONField(name = "SLRQ")
    private Date BPCSLRQ;

    @JSONField(name = "BPC_WCRQ",format = "yyyy-MM-dd")
  /*  @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8") */
    private Date BPCWCRQ;


    @JSONField(name = "LCSLBH",format = "yyyy-MM-dd")
    private String LCSLBH;



    @JSONField(name = "PCJDBH")
    private String PCJDBH;
    @JSONField(name = "PCJDMS")
    private String PCJDMS;
    @JSONField(name = "SXR_DWBM")
    private String SXRDWBM;
    @JSONField(name = "SXR_GH")
    private String SXRGH;
    @JSONField(name = "FPDZ_FPR_DWBM")
    private String FPDZFPRDWBM;
    @JSONField(name = "FPDZ_FPR_GH")
    private String FPDZFPRGH;
    @JSONField(name = "FPDR_FPR_DWBM")
    private String FPDRFPRDWBM;
    @JSONField(name = "FPDR_FPR_DWMC")
    private String FPDRFPRDWMC;
    @JSONField(name = "FPDR_FPR_GH")
    private String FPDRFPRGH;
    @JSONField(name = "FPDR_FPR_MC")
    private String FPDRFPRMC;
    @JSONField(name = "PCZ_BM")
    private String PCZBM;
    @JSONField(name = "PCZ_MC")
    private String PCZMC;
    @JSONField(name = "PCR_DWBM")
    private String PCRDWBM;
    @JSONField(name = "PCR_DWMC")
    private String PCRDWMC;
    @JSONField(name = "PCR_GH")
    private String PCRGH;
    @JSONField(name = "PCR_MC")
    private String PCRMC;
    @JSONField(name = "PCMBBM")
    private String PCMBBM;
    @JSONField(name = "AJGLZT")
    private String AJGLZT;
    @JSONField(name = "PCJG")
    private String PCJG;
    @JSONField(name = "PCJL")
    private String PCJL;
    @JSONField(name = "PCBGBH")
    private String PCBGBH;
    @JSONField(name = "ETL_CSZT")
    private String ETLCSZT;
    @JSONField(name = "ETL_CSSBCS")
    private Short ETLCSSBCS;
    @JSONField(name = "SM")
    private String SM;
    @JSONField(name = "SFSC")
    private String SFSC;

    @JSONField(name = "CJSJ",format = "yyyy-MM-dd")
 /*   @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")*/
    private Date CJSJ;


    @JSONField(name = "ZHXGSJ",format = "yyyy-MM-dd")
/*    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")*/
    private Date ZHXGSJ;

    @JSONField(name = "SXGZBM")
    private String SXGZBM;


    @JSONField(name = "WCBZRQ",format = "yyyy-MM-dd")
 /*   @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")*/
    private Date BPCWCBZRQ;



    @JSONField(name = "SXR_DWMC")
    private String SXRDWMC;
    @JSONField(name = "SXR_MC")
    private String SXRMC;
    @JSONField(name = "FPDZ_FPR_DWMC")
    private String FPDZFPRDWMC;
    @JSONField(name = "FPDZ_FPR_MC")
    private String FPDZFPRMC;


    private String CLJG;

    @JSONField(name = "SFLDBA")
    private String SFLDBA;

    public String getCLJG() {
        return CLJG;
    }

    public void setCLJG(String CLJG) {
        this.CLJG = CLJG;
    }

    public String getPCSLBM() {
        return PCSLBM;
    }

    public void setPCSLBM(String PCSLBM) {
        this.PCSLBM = PCSLBM;
    }

    public String getPCSAH() {
        return PCSAH;
    }

    public void setPCSAH(String PCSAH) {
        this.PCSAH = PCSAH;
    }

    public String getPCDWBM() {
        return PCDWBM;
    }

    public void setPCDWBM(String PCDWBM) {
        this.PCDWBM = PCDWBM;
    }

    public String getPCFLBM() {
        return PCFLBM;
    }

    public void setPCFLBM(String PCFLBM) {
        this.PCFLBM = PCFLBM;
    }

    public String getPCHDBM() {
        return PCHDBM;
    }

    public void setPCHDBM(String PCHDBM) {
        this.PCHDBM = PCHDBM;
    }

    public String getBMSAH() {
        return BMSAH;
    }

    public void setBMSAH(String BMSAH) {
        this.BMSAH = BMSAH;
    }

    public String getTYSAH() {
        return TYSAH;
    }

    public void setTYSAH(String TYSAH) {
        this.TYSAH = TYSAH;
    }

    public String getAJMC() {
        return AJMC;
    }

    public void setAJMC(String AJMC) {
        this.AJMC = AJMC;
    }

    public String getAJLBBM() {
        return AJLBBM;
    }

    public void setAJLBBM(String AJLBBM) {
        this.AJLBBM = AJLBBM;
    }

    public String getAJLBMC() {
        return AJLBMC;
    }

    public void setAJLBMC(String AJLBMC) {
        this.AJLBMC = AJLBMC;
    }

    public String getBPCDWBM() {
        return BPCDWBM;
    }

    public void setBPCDWBM(String BPCDWBM) {
        this.BPCDWBM = BPCDWBM;
    }

    public String getBPCDWMC() {
        return BPCDWMC;
    }

    public void setBPCDWMC(String BPCDWMC) {
        this.BPCDWMC = BPCDWMC;
    }

    public String getBPCBMBM() {
        return BPCBMBM;
    }

    public void setBPCBMBM(String BPCBMBM) {
        this.BPCBMBM = BPCBMBM;
    }

    public String getBPCBMMC() {
        return BPCBMMC;
    }

    public void setBPCBMMC(String BPCBMMC) {
        this.BPCBMMC = BPCBMMC;
    }

    public String getBPCGH() {
        return BPCGH;
    }

    public void setBPCGH(String BPCGH) {
        this.BPCGH = BPCGH;
    }

    public String getBPCMC() {
        return BPCMC;
    }

    public void setBPCMC(String BPCMC) {
        this.BPCMC = BPCMC;
    }

    public Date getBPCSLRQ() {
        return BPCSLRQ;
    }

    public void setBPCSLRQ(Date BPCSLRQ) {
        this.BPCSLRQ = BPCSLRQ;
    }

    public Date getBPCWCRQ() {
        return BPCWCRQ;
    }

    public void setBPCWCRQ(Date BPCWCRQ) {
        this.BPCWCRQ = BPCWCRQ;
    }

    public String getLCSLBH() {
        return LCSLBH;
    }

    public void setLCSLBH(String LCSLBH) {
        this.LCSLBH = LCSLBH;
    }

    public String getPCJDBH() {
        return PCJDBH;
    }

    public void setPCJDBH(String PCJDBH) {
        this.PCJDBH = PCJDBH;
    }

    public String getPCJDMS() {
        return PCJDMS;
    }

    public void setPCJDMS(String PCJDMS) {
        this.PCJDMS = PCJDMS;
    }

    public String getSXRDWBM() {
        return SXRDWBM;
    }

    public void setSXRDWBM(String SXRDWBM) {
        this.SXRDWBM = SXRDWBM;
    }

    public String getSXRGH() {
        return SXRGH;
    }

    public void setSXRGH(String SXRGH) {
        this.SXRGH = SXRGH;
    }

    public String getFPDZFPRDWBM() {
        return FPDZFPRDWBM;
    }

    public void setFPDZFPRDWBM(String FPDZFPRDWBM) {
        this.FPDZFPRDWBM = FPDZFPRDWBM;
    }

    public String getFPDZFPRGH() {
        return FPDZFPRGH;
    }

    public void setFPDZFPRGH(String FPDZFPRGH) {
        this.FPDZFPRGH = FPDZFPRGH;
    }

    public String getFPDRFPRDWBM() {
        return FPDRFPRDWBM;
    }

    public void setFPDRFPRDWBM(String FPDRFPRDWBM) {
        this.FPDRFPRDWBM = FPDRFPRDWBM;
    }

    public String getFPDRFPRDWMC() {
        return FPDRFPRDWMC;
    }

    public void setFPDRFPRDWMC(String FPDRFPRDWMC) {
        this.FPDRFPRDWMC = FPDRFPRDWMC;
    }

    public String getFPDRFPRGH() {
        return FPDRFPRGH;
    }

    public void setFPDRFPRGH(String FPDRFPRGH) {
        this.FPDRFPRGH = FPDRFPRGH;
    }

    public String getFPDRFPRMC() {
        return FPDRFPRMC;
    }

    public void setFPDRFPRMC(String FPDRFPRMC) {
        this.FPDRFPRMC = FPDRFPRMC;
    }

    public String getPCZBM() {
        return PCZBM;
    }

    public void setPCZBM(String PCZBM) {
        this.PCZBM = PCZBM;
    }

    public String getPCZMC() {
        return PCZMC;
    }

    public void setPCZMC(String PCZMC) {
        this.PCZMC = PCZMC;
    }

    public String getPCRDWBM() {
        return PCRDWBM;
    }

    public void setPCRDWBM(String PCRDWBM) {
        this.PCRDWBM = PCRDWBM;
    }

    public String getPCRDWMC() {
        return PCRDWMC;
    }

    public void setPCRDWMC(String PCRDWMC) {
        this.PCRDWMC = PCRDWMC;
    }

    public String getPCRGH() {
        return PCRGH;
    }

    public void setPCRGH(String PCRGH) {
        this.PCRGH = PCRGH;
    }

    public String getPCRMC() {
        return PCRMC;
    }

    public void setPCRMC(String PCRMC) {
        this.PCRMC = PCRMC;
    }

    public String getPCMBBM() {
        return PCMBBM;
    }

    public void setPCMBBM(String PCMBBM) {
        this.PCMBBM = PCMBBM;
    }

    public String getAJGLZT() {
        return AJGLZT;
    }

    public void setAJGLZT(String AJGLZT) {
        this.AJGLZT = AJGLZT;
    }

    public String getPCJG() {
        return PCJG;
    }

    public void setPCJG(String PCJG) {
        this.PCJG = PCJG;
    }

    public String getPCJL() {
        return PCJL;
    }

    public void setPCJL(String PCJL) {
        this.PCJL = PCJL;
    }

    public String getPCBGBH() {
        return PCBGBH;
    }

    public void setPCBGBH(String PCBGBH) {
        this.PCBGBH = PCBGBH;
    }

    public String getETLCSZT() {
        return ETLCSZT;
    }

    public void setETLCSZT(String ETLCSZT) {
        this.ETLCSZT = ETLCSZT;
    }

    public Short getETLCSSBCS() {
        return ETLCSSBCS;
    }

    public void setETLCSSBCS(Short ETLCSSBCS) {
        this.ETLCSSBCS = ETLCSSBCS;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }

    public Date getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(Date CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getSFSC() {
        return SFSC;
    }

    public void setSFSC(String SFSC) {
        this.SFSC = SFSC;
    }


    public Date getZHXGSJ() {
        return ZHXGSJ;
    }

    public void setZHXGSJ(Date ZHXGSJ) {
        this.ZHXGSJ = ZHXGSJ;
    }

    public String getSXGZBM() {
        return SXGZBM;
    }

    public void setSXGZBM(String SXGZBM) {
        this.SXGZBM = SXGZBM;
    }

    public Date getBPCWCBZRQ() {
        return BPCWCBZRQ;
    }

    public void setBPCWCBZRQ(Date BPCWCBZRQ) {
        this.BPCWCBZRQ = BPCWCBZRQ;
    }

    public String getSXRDWMC() {
        return SXRDWMC;
    }

    public void setSXRDWMC(String SXRDWMC) {
        this.SXRDWMC = SXRDWMC;
    }

    public String getSXRMC() {
        return SXRMC;
    }

    public void setSXRMC(String SXRMC) {
        this.SXRMC = SXRMC;
    }

    public String getFPDZFPRDWMC() {
        return FPDZFPRDWMC;
    }

    public void setFPDZFPRDWMC(String FPDZFPRDWMC) {
        this.FPDZFPRDWMC = FPDZFPRDWMC;
    }

    public String getFPDZFPRMC() {
        return FPDZFPRMC;
    }

    public void setFPDZFPRMC(String FPDZFPRMC) {
        this.FPDZFPRMC = FPDZFPRMC;
    }

    public String getSFLDBA() {
        return SFLDBA;
    }

    public void setSFLDBA(String SFLDBA) {
        this.SFLDBA = SFLDBA;
    }
}