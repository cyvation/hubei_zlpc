select * from xt_qx_jsgnfp t WHERE jsbm=100 AND gnbm='4200000002';


INSERT INTO xt_qx_jsgnfp 

SELECT dwbm,'100','4200000002',NULL,NULL,'9191' from xt_zzjg_dwbm  d WHERE d.dwbm LIKE '42%'
AND NOT EXISTS (SELECT 1 from xt_qx_jsgnfp f WHERE jsbm='100' AND gnbm='4200000002' AND f.dwbm=d.dwbm)


SELECT * from xt_zzjg_dwbm ;
