SELECT substr(t.pcmbj,15,4), t.* from yx_pc_hd t WHERE pcflbm=008;

SELECT m.pcmbmc,s.gzmc,s.xh,s.sm, j.ajmc,j.ajlb_mc,j.pcmbbm,j.*,j.ROWID from yx_pc_jbxx j 
INNER JOIN xt_pc_sxgz s ON s.gzbm=j.sxgzbm AND s.xh<51
INNER JOIN xt_pc_mb m ON j.pcmbbm=m.pcmbbm
 WHERE substr(j.sxgzbm,11,2)!=substr(j.pcmbbm,15,4) ORDER by j.cjsj DESC;
