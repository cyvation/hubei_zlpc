--线下评查人与已有人员重复的数据
SELECT o.bpc_dwmc,o.bpc_gh,o.bpc_mc,r.gh,r.mc from yx_pc_offline_jbxx o INNER JOIN 
xt_zzjg_rybm r ON r.gh<6000 AND o.bpc_gh>6000 AND o.bpc_dwbm=r.dwbm AND o.bpc_mc=r.mc;

--修改线下评查人工号
UPDATE yx_pc_offline_jbxx o SET o.bpc_gh=(
SELECT min(r.gh) from 
 xt_zzjg_rybm r WHERE  r.gh<6000  AND o.bpc_dwbm=r.dwbm AND o.bpc_mc=r.mc
 ) WHERE o.bpc_gh>6000 AND EXISTS (SELECT 1 from  xt_zzjg_rybm z WHERE  z.gh<6000 AND o.bpc_gh>6000 AND o.bpc_dwbm=z.dwbm AND o.bpc_mc=z.mc)
 ;

--删除不需要的工号
SELECT r.*,r.rowid from 
--SELECT r.dwbm,r.gh,r.mc,a.gh,a.mc from 
xt_zzjg_rybm r 
 INNER JOIN (SELECT o.dwbm ,o.gh,o.mc,o.dlbm from xt_zzjg_rybm o WHERE o.gh<6000 ) a
 ON r.dwbm=a.dwbm AND r.mc=a.mc and r.dlbm=a.dlbm  
WHERE 
 r.gh>6000 AND r.dwbm=a.dwbm   and r.dlbm=a.mc
 ORDER by r.dwbm,r.gh;

--评查人数
SELECT x.PCR_DWBM,x.BPC_DWBM,x.BPC_GH from v_pcajxx  x WHERE x.BPC_DWBM=422828 GROUP BY x.PCR_DWBM,x.PCR_GH,PCR_MC;

--办案人
SELECT b.cbdwbm,b.cbrgh,cbrmc from v_bjajxx b WHERE b.cbdwbm=422828 GROUP BY b.cbdwbm,b.cbrgh,b.cbrmc
;
