INSERT INTO xt_qx_dwgn (dwbm,gnbm,sfsc)
SELECT d.dwbm,g.gnbm,'N' from xt_zzjg_dwbm  d
LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm BETWEEN '4200000201' AND '4200000234'
 WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
AND NOT EXISTS (SELECT 1 from xt_qx_dwgn f WHERE   f.gnbm=g.gnbm AND f.dwbm=d.dwbm);

INSERT INTO xt_qx_jsgnfp
SELECT d.dwbm,'100',g.gnbm,NULL,NULL,'9191' from xt_zzjg_dwbm  d
LEFT JOIN xt_qx_gndy g ON g.dwbm='420000' AND g.gnbm BETWEEN '4200000201' AND '4200000234'
 WHERE d.dwbm LIKE '42%' AND d.sfsc='N'
AND NOT EXISTS (SELECT 1 from xt_qx_jsgnfp f WHERE jsbm='100' AND f.gnbm=g.gnbm AND f.dwbm=d.dwbm);

