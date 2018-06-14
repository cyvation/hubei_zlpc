insert into XT_PC_WSMB (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000900001', '420000', null, '案件质量评查分析报告', '9', 'template/ftlFile/案件质量评查分析报告.ftl', null, 0, null, 'N', null, null);

--新增报告菜单授权
INSERT INTO xt_qx_dwgn (dwbm,gnbm,sfsc)
  SELECT d.dwbm,g.gnbm,'N' from xt_zzjg_dwbm  d
    LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm ='4200000710'
  WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
        AND NOT EXISTS (SELECT 1 from xt_qx_dwgn f WHERE   f.gnbm=g.gnbm AND f.dwbm=d.dwbm);

INSERT INTO xt_qx_jsgnfp
  SELECT d.dwbm,'100',g.gnbm,NULL,NULL,'9191' from xt_zzjg_dwbm  d
    LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm ='4200000710'
  WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
        AND NOT EXISTS (SELECT 1 from xt_qx_jsgnfp f WHERE jsbm='100' AND f.gnbm=g.gnbm AND f.dwbm=d.dwbm);