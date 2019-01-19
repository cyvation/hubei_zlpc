CREATE OR REPLACE VIEW V_BJAJXX
(pcflbm, bmsah, tysah, ajmc, ajlb_bm, ajlb_mc, cbdwbm, cbdwmc, cbbmbm, cbbmmc, cbrgh, cbrmc, slrq, wcrq, way, ywtx, ywtx_mc, sfldba, wcrq_nf, stajbs, gzbm, gzmc, fgzbm)
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
      nvl(st.stajbs,'0') stajbs,
      gz.gzbm gzbm,
      gz.gzmc ,
      NULL fgzbm
    FROM tyyw_bjaj b
      LEFT JOIN xt_dm_stajbs st ON b.ajlb_bm=st.ajlb_bm
      LEFT JOIN xt_pc_sxgz gz ON b.ywtx=gz.ywtx AND gz.sfzdy='N' AND gz.pcflbm=b.pcflbm
      INNER JOIN xt_pc_dm d ON b.ywtx = d.dm
      LEFT JOIN yx_pc_jbxx j ON b.bmsah=j.bmsah AND j.sfsc='N'
    WHERE b.pcflbm IN ('001','007') /*and b.wcrq_nf<'2018'*/--and b.wcrq<to_date('2018-01-01','yyyy-MM-dd')

    UNION ALL

    SELECT s.pcflbm,
      s.bmsah,
      s.tysah,
      s.ajmc,
      s.ajlb_bm,
      s.ajlb_mc,
      s.dwbm,
      s.dwmc,
      s.bmbm,
      s.bmmc,
      s.cbrgh,
      s.cbrmc,
      s.slrq,
      s.wcrq,
      '0' way,
      s.ywtx,
      d.mc ywtx_mc,
      nvl(j.sfldba,'N') sfldba,
      to_char(s.wcrq,'yyyy') wcrq_nf,
      nvl(st.stajbs, '0') stajbs,
      s.sxgzbm,
      s.sxgzmc,
      s.fgzbm
    FROM yx_pc_sxjl s
      LEFT JOIN xt_dm_stajbs st ON s.ajlb_bm = st.ajlb_bm
      INNER JOIN xt_pc_dm d ON s.ywtx = d.dm
      LEFT JOIN yx_pc_jbxx j ON s.bmsah=j.bmsah AND j.sfsc='N'
     INNER JOIN xt_pc_sxgz gz ON s.sxgzbm=gz.gzbm AND gz.sfzdy='N' --重点非自定义
    WHERE s.pcflbm = '008' /*and s.wcrq_nf<'2018'*/

   UNION ALL

    SELECT zx.pcflbm,
      zx.bmsah,
      zx.tysah,
      zx.ajmc,
      zx.ajlb_bm,
      zx.ajlb_mc,
      zx.bpc_dwbm,
      zx.bpc_dwmc,
      zx.bpc_bmbm,
      zx.bpc_bmmc,
      zx.bpc_gh,
      zx.bpc_mc,
      zx.bpc_slrq,
      zx.bpc_wcrq wcrq,
      zx.way,
      m.ywtx,
      d.mc ywtx_mc,
      nvl(zx.sfldba,'N') sfldba,
      wcrq_nf,
      nvl(st.stajbs,'0') stajbs,
      zx.sxgzbm gzbm,
      gz.gzmc ,
      gz.fgzbm fgzbm
    FROM yx_pc_jbxx zx
      LEFT JOIN xt_dm_stajbs st ON zx.ajlb_bm=st.ajlb_bm
      INNER JOIN xt_pc_mb m ON zx.pcmbbm = m.pcmbbm
      INNER JOIN xt_pc_dm d ON m.ywtx=d.dm
      INNER JOIN xt_pc_sxgz gz ON zx.sxgzbm=gz.gzbm AND gz.sfzdy='Y' --重点自定义筛选的案件
    WHERE zx.pcflbm='008'
          AND zx.sfsc='N'

    UNION ALL --专项的办结案件

    SELECT zx.pcflbm,
      zx.bmsah,
      zx.tysah,
      zx.ajmc,
      zx.ajlb_bm,
      zx.ajlb_mc,
      zx.bpc_dwbm,
      zx.bpc_dwmc,
      zx.bpc_bmbm,
      zx.bpc_bmmc,
      zx.bpc_gh,
      zx.bpc_mc,
      zx.bpc_slrq,
      zx.bpc_wcrq wcrq,
      zx.way,
      m.ywtx,
      d.mc ywtx_mc,
      nvl(zx.sfldba,'N') sfldba,
      wcrq_nf,
      nvl(st.stajbs,'0') stajbs,
      zx.sxgzbm gzbm,
      gz.gzmc ,
      gz.fgzbm fgzbm
    FROM yx_pc_jbxx zx
      LEFT JOIN xt_dm_stajbs st ON zx.ajlb_bm=st.ajlb_bm
      INNER JOIN xt_pc_mb m ON zx.pcmbbm = m.pcmbbm
      INNER JOIN xt_pc_dm d ON m.ywtx=d.dm
      INNER JOIN xt_pc_sxgz gz ON zx.sxgzbm=gz.gzbm
    WHERE zx.pcflbm='003'
          AND zx.sfsc='N'

/*

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
       nvl(st.stajbs,'0') stajbs,
       NULL gzbm,
       d.mc gzmc ,
       NULL fgzbm
  FROM yx_pc_offline_jbxx o
  LEFT JOIN xt_dm_stajbs st ON o.ajlb_bm=st.ajlb_bm
 INNER JOIN xt_pc_mb m ON o.pcmbbm = m.pcmbbm
 INNER JOIN xt_pc_dm d ON m.ywtx=d.dm
 WHERE o.wcrq_nf>='2013' --AND o.wcrq_nf<'2018'
 AND o.sfsc='N'
 */;
