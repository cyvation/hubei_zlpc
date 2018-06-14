
--正式库上已更新
-- UPDATE xt_pc_dm SET dm='40000' WHERE dm='40001' AND mc='其他';
-- UPDATE xt_pc_pcx  x SET xtdm='40000' WHERE xtdm='40001' AND x.pcxmc='其他';
-- UPDATE yx_pc_pcx  x SET xtdm='40000' WHERE xtdm='40001' AND x.pcxmc='其他';

--专有业务条线
alter table XT_PC_DM add zyywtx VARCHAR2(300);
-- Add comments to the columns
comment on column XT_PC_DM.zyywtx
is '适用于个别业务条线，多个逗号间隔';
UPDATE xt_pc_dm t SET t.zyywtx='10002' WHERE dm='20009';
UPDATE xt_pc_dm t SET t.zyywtx='10017' WHERE dm='20007';
UPDATE xt_pc_dm t SET t.zyywtx='10003,10017' WHERE dm='20006';
UPDATE xt_pc_dm t SET t.zyywtx='10017' WHERE dm='20005';

--办结案件视图
CREATE OR REPLACE VIEW V_BJAJXX
(pcflbm, bmsah, tysah, ajmc, ajlb_bm, ajlb_mc, cbdwbm, cbdwmc, cbbmbm, cbbmmc, cbrgh, cbrmc, slrq, wcrq, way, ywtx, ywtx_mc, sfldba, wcrq_nf, stajbs)
  AS
    SELECT b.pcflbm,
      b.bmsah,
      b.tysah,
      b.ajmc,
      b.ajlb_bm,
      b.ajlb_mc,
      b.cb_dwbm,
      b.cb_dwmc,
      b.cb_bmbm,
      b.cb_bmmc,
      b.cbrgh,
      b.cbr,
      b.slrq,
      b.wcrq,
      b.way,
      b.ywtx,
      d.mc ywtx_mc,
      nvl(j.sfldba,'N') sfldba,
      b.wcrq_nf,
      nvl(st.stajbs,'0') stajbs
    FROM tyyw_bjaj b
      LEFT JOIN xt_dm_stajbs st ON b.ajlb_bm=st.ajlb_bm
      INNER JOIN xt_pc_dm d ON b.ywtx = d.dm
      LEFT JOIN yx_pc_jbxx j ON b.bmsah=j.bmsah AND j.sfsc='N'
    WHERE b.wcrq_nf>='2013' --AND b.wcrq_nf<'2018'
    UNION ALL

    SELECT o.pcflbm,
      o.bmsah,
      o.tysah,
      o.ajmc,
      o.ajlb_bm,
      o.ajlb_mc,
      o.bpc_dwbm,
      o.bpc_dwmc,
      o.bpc_bmbm,
      o.bpc_bmmc,
      o.bpc_gh,
      o.bpc_mc,
      o.bpc_slrq,
      o.bpc_wcrq wcrq,
      o.way,
      m.ywtx,
      d.mc ywtx_mc,
      nvl(o.sfldba,'N') sfldba,
      wcrq_nf,
      nvl(st.stajbs,'0') stajbs
    FROM yx_pc_offline_jbxx o
      LEFT JOIN xt_dm_stajbs st ON o.ajlb_bm=st.ajlb_bm
      INNER JOIN xt_pc_mb m ON o.pcmbbm = m.pcmbbm
      INNER JOIN xt_pc_dm d ON m.ywtx=d.dm
    WHERE o.wcrq_nf>='2013' --AND o.wcrq_nf<'2018'
          AND o.sfsc='N';


--评查视图
CREATE OR REPLACE VIEW V_PCAJXX AS
  SELECT
    PCSLBM,
    PCSAH,
    PCDWBM,
    ju.PCFLBM,
    PCHDBM,
    BMSAH,
    TYSAH,
    AJMC,
    ju.AJLB_BM,
    ju.AJLB_MC,
    BPC_DWBM,
    BPC_DWMC,
    BPC_BMBM,
    BPC_BMMC,
    BPC_GH,
    BPC_MC,
    BPC_SLRQ,
    BPC_WCRQ,
    LCSLBH,
    PCJDBH,
    PCJDMS,
    SXR_DWBM,
    SXR_GH,
    FPDZ_FPR_DWBM,
    FPDZ_FPR_GH,
    FPDR_FPR_DWBM,
    FPDR_FPR_DWMC,
    FPDR_FPR_GH,
    FPDR_FPR_MC,
    PCZ_BM,
    PCZ_MC,
    PCR_DWBM,
    PCR_DWMC,
    PCR_GH,
    PCR_MC,
    ju.PCMBBM,
    AJGLZT,
    PCJG,
    PCJL,
    PCBGBH,
    ju.SM,
    ju.cjsj,
    ZHXGSJ,
    SXGZBM,
    SXR_DWMC,
    SXR_MC,
    FPDZ_FPR_DWMC,
    FPDZ_FPR_MC,
    WAY,
    WCRQ_NF,
    mu.ywtx,
    mu.pcmbmc ywtx_mc,
    nvl(ju.sfldba,'N') sfldba,
    nvl(st.stajbs,'0') stajbs
  from yx_pc_jbxx ju LEFT JOIN xt_pc_mb mu ON   ju.pcmbbm=mu.pcmbbm
    LEFT JOIN xt_dm_stajbs st ON ju.ajlb_bm=st.ajlb_bm
  WHERE ju.sfsc='N' AND ju.pcjdbh='011' AND to_char(nvl(ju.bpc_wcrq,ju.bpc_wcbzrq),'yyyy')>='2013'
  --AND  ju.wcrq_nf>='2013' --AND ju.wcrq_nf<'2018'

  UNION  ALL

  SELECT
    PCSLBM,
    PCSAH,
    PCDWBM,
    jv.PCFLBM,
    PCHDBM,
    BMSAH,
    TYSAH,
    AJMC,
    jv.AJLB_BM,
    jv.AJLB_MC,
    BPC_DWBM,
    BPC_DWMC,
    BPC_BMBM,
    BPC_BMMC,
    BPC_GH,
    BPC_MC,
    BPC_SLRQ,
    BPC_WCRQ,
    LCSLBH,
    PCJDBH,
    PCJDMS,
    SXR_DWBM,
    SXR_GH,
    FPDZ_FPR_DWBM,
    FPDZ_FPR_GH,
    FPDR_FPR_DWBM,
    FPDR_FPR_DWMC,
    FPDR_FPR_GH,
    FPDR_FPR_MC,
    PCZ_BM,
    PCZ_MC,
    PCR_DWBM,
    PCR_DWMC,
    PCR_GH,
    PCR_MC,
    jv.PCMBBM,
    AJGLZT,
    PCJG,
    PCJL,
    PCBGBH,
    jv.SM,
    jv.cjsj,
    ZHXGSJ,
    SXGZBM,
    SXR_DWMC,
    SXR_MC,
    FPDZ_FPR_DWMC,
    FPDZ_FPR_MC,
    WAY,
    WCRQ_NF,
    mv.ywtx,
    mv.pcmbmc ywtx_mc,
    nvl(jv.sfldba,'N') sfldba,
    nvl(st.stajbs,'0') stajbs
  from
    yx_pc_offline_jbxx jv LEFT JOIN xt_pc_mb mv ON   jv.pcmbbm=mv.pcmbbm
    LEFT JOIN xt_dm_stajbs st ON jv.ajlb_bm=st.ajlb_bm
  WHERE  jv.wcrq_nf>='2013' --AND jv.wcrq_nf<'2018'
         AND jv.sfsc='N';



-- SELECT dm,mc,NULL ywtx,xh from xt_pc_dm t WHERE t.zyywtx IS NULL AND t.fdm=2
-- UNION ALL
-- SELECT dm,mc,'10002' ywtx,(xh+100) xh from xt_pc_dm zj WHERE instr(zj.zyywtx,'10002')>0 AND zj.fdm=2
-- UNION ALL
-- SELECT dm,mc,'10003' ywtx,(xh+200) xh from xt_pc_dm gs WHERE instr(gs.zyywtx,'10003')>0 AND gs.fdm=2
-- UNION ALL
-- SELECT dm,mc,'10017' ywtx,(xh+300) xh from xt_pc_dm wj WHERE instr(wj.zyywtx,'10017')>0 AND wj.fdm=2
-- ORDER by xh