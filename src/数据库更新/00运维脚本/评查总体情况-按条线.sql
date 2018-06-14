--评查汇总按条线

WITH  
   bjtab AS (SELECT * from v_bjajxx WHERE 1=1),  
   pctab AS (SELECT * from v_pcajxx WHERE 1=1),
   hztab AS( 
     --汇总临时表start
      SELECT u.ywtx,u.ajlb_bm,
        SUM(decode(coltype,'bars',cnt,0)) bars, --办案人数
        SUM(decode(coltype,'bjajs',cnt,0)) bjajs, --办结案件数
        SUM(decode(coltype,'pcrys',cnt,0)) pcrys, --评查人员数
        SUM(decode(coltype,'pcajs',cnt,0)) pcajs, --评查案件数
        'N' sfhj
      FROM 
      (
        --办案人数
        SELECT ywtx,ajlb_bm, count(*) cnt, 'bars' coltype from ( SELECT DISTINCT ywtx,ajlb_bm,cbdwbm ,cbrgh from bjtab ) a GROUP BY ywtx,ajlb_bm
        UNION ALL
        --办结案件数
        SELECT ywtx,ajlb_bm, count(*) cnt, 'bjajs' coltype from  bjtab  a GROUP BY ywtx,ajlb_bm
        UNION ALL
        --评查人员数
        SELECT ywtx,ajlb_bm, count(*) cnt, 'pcrys' coltype from ( SELECT DISTINCT ywtx,ajlb_bm,bpc_dwbm,bpc_gh from pctab v ) a GROUP BY ywtx,ajlb_bm
        UNION ALL
        --评查案件数
        SELECT ywtx,ajlb_bm, count(bmsah) cnt, 'pcajs' coltype from ( SELECT DISTINCT ywtx,ajlb_bm,bmsah from pctab v ) a GROUP BY ywtx,ajlb_bm
      ) u 
      GROUP BY u.ywtx,u.ajlb_bm 
  ) --汇总临时表end
  ,txtab AS (--业务条线、案件类别临时表（只统计评查系统里出现过的类别）start
    SELECT pctx.ywtx,
      (CASE WHEN pctx.ywtx = '-1' AND pctx.ajlb_bm='-1' THEN '合计' WHEN  pctx.ywtx != '-1' AND pctx.ajlb_bm='-1' THEN d.mc ELSE d.mc END  ) ywtx_mc,
      pctx.ajlb_bm,ajlb.ajlbmc ajlb_mc 
    FROM 
    (
      SELECT '-1' ywtx,'-1' ajlb_bm FROM dual --增加总计
      UNION ALL
      SELECT ywtx,'-1' ajlb_bm FROM pctab GROUP BY ywtx --增加条线合计
      UNION ALL
      SELECT ywtx,ajlb_bm
        FROM pctab
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
    y.bars "办案人数",y.bjajs "办结案件数",y.pcrys "评查人员数",y.pcajs "评查案件数"
    ,to_char(decode(y.bjajs,0,0,y.pcajs*100/y.bjajs),'fm999999990.009')||'%' "评查比例（4/2）"
    ,round(decode(y.bars,0,0,(y.pcajs/y.bars)),4)   "人均被评查件（4/1）"
    ,round(decode(y.pcrys,0,0,(y.pcajs/y.pcrys)),4)  "人均评查数件（4/3）" 
    ,y.sfhj "是否合计行"
  FROM 
    (--计算数量start
     SELECT ywtx,ajlb_bm,bars,bjajs,pcrys,pcajs,sfhj from hztab
      UNION ALL
      --增加合计行
      SELECT nvl(ywtx,'-1') ywtx,'-1',sum(bars),sum(bjajs),sum(pcrys),sum(pcajs),'Y' sfhj
       FROM hztab  GROUP BY ROLLUP (ywtx)   
     ) y--计算数量end
  RIGHT JOIN txtab x ON x.ywtx=y.ywtx AND x.ajlb_bm=y.ajlb_bm
  ORDER by x.ywtx,x.ajlb_bm   
 --计算比例end
 ;
 

