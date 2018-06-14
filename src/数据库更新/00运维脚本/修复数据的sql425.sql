---数据备份-------

--将新筛选的还原
UPDATE yx_pc_jbxx j
   SET PCJDBH  ='005',
PCJDMS  ='待评查',

FPDZ_FPR_DWBM  =NULL,
FPDZ_FPR_GH  =NULL,
FPDR_FPR_DWBM  =NULL,
FPDR_FPR_DWMC  =NULL,
FPDR_FPR_GH  =NULL,
FPDR_FPR_MC  =NULL,
PCR_DWBM  =NULL,
PCR_DWMC  =NULL,
PCR_GH  =NULL,
PCR_MC  =NULL
        WHERE j.pcdwbm = '422800'
   AND pcz_mc = '公诉三组'
   AND j.sxr_gh = '0024'
   AND pcjl IS NULL
  AND j.pcslbm NOT IN (SELECT w.PCZYBM from yx_pc_jzwj w);
  
  --查看先前已经办理有结果的
  SELECT j.*,
       ROWID
  FROM yx_pc_jbxx j
 WHERE j.pcdwbm = '422800'
   AND pcz_mc = '公诉三组'
   AND j.sxr_gh = '0024'
   AND pcjl IS NOT NULL;
   ---更新有结果的数据状态
   UPDATE yx_pc_jbxx j
   SET PCJDBH  ='006',
PCJDMS  ='评查办理'
   WHERE j.pcdwbm = '422800'
   AND pcz_mc = '公诉三组'
   AND j.sxr_gh = '0024'
   AND pcjl IS NOT NULL;
   
   --查看已有评查结果的最大流程节点
   SELECT MAX(d.jdzxzgh),MAX(d.jdzxz),MAX(d.jdzxzdwbm),MAX(d.jdzxzdwmc),d.pcslbm FROM yx_lc_sljd d WHERE d.pcslbm IN (SELECT j.pcslbm from yx_pc_jbxx j
   WHERE j.pcdwbm = '422800'
   AND pcz_mc = '公诉三组'
   AND j.sxr_gh = '0024'
   AND pcjl IS NOT NULL) GROUP BY  d.pcslbm;
   
   --更新员评查人信息
      UPDATE yx_pc_jbxx jb
   SET  (jb.pcr_gh,jb.pcr_mc,jb.pcr_dwbm,jb.pcr_dwmc)=
   (SELECT MAX(d.jdzxzgh),MAX(d.jdzxz),MAX(d.jdzxzdwbm),MAX(d.jdzxzdwmc) FROM yx_lc_sljd d WHERE d.pcslbm IN (SELECT j.pcslbm from yx_pc_jbxx j
   WHERE j.pcdwbm = '422800'
   AND jb.pcslbm=j.pcslbm  
   AND pcz_mc = '公诉三组'
   AND jb.sxr_gh = '0024'
   AND pcjl IS NOT NULL) GROUP BY  d.pcslbm)
   WHERE jb.pcdwbm = '422800'
   AND pcz_mc = '公诉三组'
   AND jb.sxr_gh = '0024'
   AND pcjl IS NOT NULL; 

   --
     UPDATE yx_pc_jbxx jb
   SET  (jb.pcjdbh,jb.pcjdms,jb.pcr_gh,jb.pcr_mc,jb.pcr_dwbm,jb.pcr_dwmc)=
   (SELECT '011','评查结束' , MAX(d.nzrgh),MAX(d.nzrxm),MAX(d.nzrdwbm),MAX(d.nzrdwmc) FROM yx_pc_jzwj d WHERE d.pczybm IN (SELECT j.pcslbm from yx_pc_jbxx j
   WHERE j.pcdwbm = '422800'
   AND jb.pcslbm=j.pcslbm  
   AND pcz_mc = '公诉三组'
   AND jb.sxr_gh = '0024'
   AND pcjl IS NOT NULL) GROUP BY  d.pczybm)
   WHERE jb.pcdwbm = '422800'
   AND pcz_mc = '公诉三组'
   AND jb.sxr_gh = '0024'
   AND pcjl IS NOT NULL
   AND EXISTS (SELECT 1 from yx_pc_jzwj z WHERE z.pczybm=jb.pcslbm); 
