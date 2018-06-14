--案件质量分析-按条线
--办结数	评查案件数	评查比	优质案件数	优质占比  合格案件件数	合格占比	瑕疵案件数	瑕疵占比	不合格案件数	不合格占比
WITH 
   bjtab AS (SELECT * from v_bjajxx WHERE 1=1),  
   pctab AS (SELECT * from v_pcajxx WHERE 1=1),
   hztab AS (
   --汇总临时表start
     SELECT u.ywtx,u.ajlb_bm,
      SUM(decode(coltype,'bjajs',cnt,0)) bjajs, --办结案件数
      SUM(decode(coltype,'pcajs',cnt,0)) pcajs, --评查案件数
      SUM(decode(coltype,'yzajs',cnt,0)) yzajs, --优质案件数
      SUM(decode(coltype,'hgajs',cnt,0)) hgajs, --合格案件数
      SUM(decode(coltype,'xcajs',cnt,0)) xcajs, --瑕疵案件数
      SUM(decode(coltype,'bhgajs',cnt,0)) bhgajs,  --不合格案件数
      'N' sfhj
      from (
            --办结案件数
            SELECT ywtx,ajlb_bm, COUNT(*) cnt, 'bjajs' coltype FROM bjtab GROUP BY ywtx,ajlb_bm
            UNION ALL
            --评查案件数
            SELECT ywtx,ajlb_bm, COUNT(bmsah) cnt, 'pcajs' coltype FROM ( SELECT DISTINCT ywtx,ajlb_bm,bmsah from pctab ) a GROUP BY a.ywtx,a.ajlb_bm
            UNION ALL
            SELECT ywtx,ajlb_bm,COUNT(bmsah) cnt, 'yzajs' coltype FROM pctab WHERE  pcjl='优质案件' GROUP BY ywtx,ajlb_bm 
            UNION ALL
            SELECT ywtx,ajlb_bm,COUNT(bmsah) cnt, 'hgajs' coltype FROM pctab WHERE  pcjl='合格案件' GROUP BY ywtx,ajlb_bm 
            UNION ALL
            SELECT ywtx,ajlb_bm,COUNT(bmsah) cnt, 'xcajs' coltype FROM pctab WHERE  pcjl='瑕疵案件' GROUP BY ywtx,ajlb_bm 
            UNION ALL
            SELECT ywtx,ajlb_bm,COUNT(bmsah) cnt, 'bhgajs' coltype FROM pctab WHERE  pcjl='不合格案件' GROUP BY ywtx,ajlb_bm 
        ) u 
      GROUP BY u.ywtx,u.ajlb_bm 
     --汇总临时表end   
     )
   ,txtab AS (--业务条线、案件类别临时表（只统计评查系统里出现过的类别）start
     SELECT pctx.ywtx,
       (CASE WHEN pctx.ywtx = '-1' AND pctx.ajlb_bm='-1' THEN '合计' WHEN  pctx.ywtx != '-1' AND pctx.ajlb_bm='-1' THEN d.mc ELSE d.mc END  ) ywtx_mc,
       pctx.ajlb_bm,ajlb.ajlbmc ajlb_mc 
     FROM 
     (
      SELECT '-1' ywtx,'-1' ajlb_bm FROM dual --增加总计
      UNION ALL
      SELECT ywtx,'-1' ajlb_bm FROM v_pcajxx GROUP BY ywtx --增加条线合计
      UNION ALL
      SELECT ywtx,ajlb_bm
        FROM v_pcajxx
       GROUP BY ywtx, ajlb_bm
      ) pctx 
     LEFT JOIN xt_pc_dm d ON pctx.ywtx=d.dm
     LEFT JOIN xt_dm_ajlbbm@tyyw_link.net ajlb ON pctx.ajlb_bm=ajlb.ajlbbm
   ) --业务条线、案件类别临时表（只统计评查系统里出现过的类别）end  
        
--算比例start 
SELECT 
 (CASE WHEN y.ywtx = '-1' AND y.ajlb_bm='-1' THEN '100000' WHEN  y.ywtx != '-1' AND y.ajlb_bm='-1' THEN y.ywtx ELSE y.ywtx||y.ajlb_bm END  ) ID,
 (CASE WHEN y.ywtx = '-1' AND y.ajlb_bm='-1' THEN '-1' WHEN  y.ywtx != '-1' AND y.ajlb_bm='-1' THEN '100000' ELSE y.ywtx END  ) pid,
 (CASE WHEN y.ywtx = '-1' AND y.ajlb_bm='-1' THEN '合计' WHEN  y.ywtx != '-1' AND y.ajlb_bm='-1' THEN x.ywtx_mc||'(合计)' ELSE x.ajlb_mc END  ) NAME,
 y.ywtx,x.ywtx_mc,y.ajlb_bm,x.ajlb_mc,
 bjajs,pcajs,yzajs,hgajs,xcajs,bhgajs,
 to_char(decode(y.bjajs,0,0,y.pcajs*100/y.bjajs),'fm999999990.0099')||'%' pczb,
 to_char(decode(y.pcajs,0,0,y.yzajs*100/y.pcajs),'fm999999990.0099')||'%' yzajzb,
 to_char(decode(y.pcajs,0,0,y.hgajs*100/y.pcajs),'fm999999990.0099')||'%' hgajzb,
 to_char(decode(y.pcajs,0,0,y.xcajs*100/y.pcajs),'fm999999990.0099')||'%' xcajzb,
 to_char(decode(y.pcajs,0,0,y.bhgajs*100/y.pcajs),'fm999999990.0099')||'%' bhgajzb,
 sfhj
 FROM 
  (
  SELECT ywtx,ajlb_bm,bjajs,pcajs,yzajs,hgajs,xcajs,bhgajs,sfhj from hztab 
  UNION ALL
  --增加合计行
  SELECT nvl(ywtx,'-1') ywtx,'-1',sum(bjajs),sum(pcajs),sum(yzajs),sum(hgajs),sum(xcajs),sum(bhgajs),'Y' sfhj 
       FROM hztab  GROUP BY ROLLUP (ywtx)  
  ) y
/*RIGHT*/INNER JOIN txtab x ON x.ywtx=y.ywtx AND x.ajlb_bm=y.ajlb_bm
  WHERE y.pcajs >0 --只显示评查数大于0的
  ORDER by x.ywtx,x.ajlb_bm   
--计算比例end
 
