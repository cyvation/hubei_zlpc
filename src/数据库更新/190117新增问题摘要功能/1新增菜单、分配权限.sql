insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC,  DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000009', '问题摘要', '4200000002', '', 'view/monitor/overview/caseview.html', '', 2, '问题摘要',  '420000', '', 'N', 'N', 'approval');


INSERT INTO xt_qx_dwgn (dwbm,gnbm,sfsc)
  SELECT d.dwbm,g.gnbm,'N' from xt_zzjg_dwbm  d
    LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm ='4200000009'
  WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
--AND NOT EXISTS (SELECT 1 from xt_qx_dwgn f WHERE   f.gnbm=g.gnbm AND f.dwbm=d.dwbm)
;

INSERT INTO xt_qx_jsgnfp --管理员
  SELECT d.dwbm,'100',g.gnbm,NULL,NULL,'9191' from xt_zzjg_dwbm  d
    LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm = '4200000009'
  WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
--AND NOT EXISTS (SELECT 1 from xt_qx_jsgnfp f WHERE jsbm='100' AND f.gnbm=g.gnbm AND f.dwbm=d.dwbm)
;

INSERT INTO xt_qx_jsgnfp --案管
  SELECT d.dwbm,'104',g.gnbm,NULL,NULL,'9191' from xt_zzjg_dwbm  d
    LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm = '4200000009'
  WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
--AND NOT EXISTS (SELECT 1 from xt_qx_jsgnfp f WHERE jsbm='100' AND f.gnbm=g.gnbm AND f.dwbm=d.dwbm)
;