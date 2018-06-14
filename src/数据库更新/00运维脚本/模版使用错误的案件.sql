--模版可能使用错误的案件
  WITH pcajTab AS ((SELECT * from yx_pc_jbxx WHERE sfsc='N' UNION ALL SELECT * from yx_pc_offline_jbxx WHERE sfsc='N'))
  --SELECT count(*) from (
  SELECT 
    txdm.mc "评查使用的条线",
    n.lbtx_mc "根据类别的条线", 
    n.ajlb_mc,
    dw.dwmc "评查单位",
    p.pcr_dwmc "评查员单位",
    p.pcr_mc "评查员",
    p.bmsah 
  from 
    pcajTab p 
   LEFT JOIN xt_tj_dw dw ON p.pcdwbm=dw.dwbm 
   INNER JOIN
    (SELECT a.*,ld.mc lbtx_mc from (SELECT j.bmsah,m.ywtx mbtx,j.ajlb_bm,j.ajlb_mc,d.ywbm ,d.ywtx lbtx 
       from   pcajTab j INNER JOIN xt_pc_mb m ON j.pcmbbm = m.pcmbbm  
      LEFT JOIN xt_dm_ajlbbm d ON j.ajlb_bm=d.ajlbbm) a
      LEFT JOIN xt_pc_dm ld ON a.lbtx=ld.dm
      WHERE mbtx!=lbtx 
    ) n  ON p.bmsah=n.bmsah
   INNER JOIN xt_pc_dm txdm ON txdm.dm=n.mbtx
   ORDER by p.pcdwbm,p.pcflbm,p.pchdbm
  -- )
  ;
