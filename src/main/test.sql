select to_char(0.1,'fm999999990.99999') ||'%' from dual;

SELECT x.ywtx,x.ywtx_mc,x.ajlb_bm,x.ajlb_mc,
  y.bars,y.bjajs,y.pcrys,y.pcajs
  ,to_char((y.pcajs*100/y.bjajs),'fm999999990.00999')||'%'  "评查比例（4/2）"
  ,round((y.pcajs/y.bars),4)   "人均被评查件（4/1）"
  ,round((y.pcajs/y.pcrys),4)  "人均评查数件（4/3）";

WITH  hztab  AS
( --汇总临时表start
    SELECT u.ywtx,u.ajlb_bm,
      SUM(decode(coltype,'bars',cnt,0)) bars, --办案人数
      SUM(decode(coltype,'bjajs',cnt,0)) bjajs, --办结案件数
      SUM(decode(coltype,'pcrys',cnt,0)) pcrys, --评查人员数
      SUM(decode(coltype,'pcajs',cnt,0)) pcajs, --评查案件数
      'N' sfhj
    FROM
      (
        --办案人数
        SELECT ywtx,ajlb_bm, count(*) cnt, 'bars' coltype from ( SELECT DISTINCT ywtx,ajlb_bm,bpc_dwbm,bpc_gh from v_pcajxx) a GROUP BY ywtx,ajlb_bm
        UNION ALL
        --办结案件数
        SELECT ywtx,ajlb_bm, count(*) cnt, 'bjajs' coltype from  v_bjajxx  a GROUP BY ywtx,ajlb_bm
        UNION ALL
        --评查人员数
        SELECT ywtx,ajlb_bm, count(*) cnt, 'pcrys' coltype from ( SELECT DISTINCT ywtx,ajlb_bm,bpc_dwbm,bpc_gh from v_pcajxx v ) a GROUP BY ywtx,ajlb_bm
        UNION ALL
        --评查案件数
        SELECT ywtx,ajlb_bm, count(bmsah) cnt, 'pcajs' coltype from ( SELECT DISTINCT ywtx,ajlb_bm,bmsah from v_pcajxx v ) a GROUP BY ywtx,ajlb_bm
      ) u
    GROUP BY u.ywtx,u.ajlb_bm
) --汇总临时表end
  ,txtab AS (--业务条线、案件类别临时表（只统计评查系统里出现过的类别）start
    SELECT pctx.ywtx,d.mc ywtx_mc,pctx.ajlb_bm,ajlb.ajlbmc ajlb_mc from
      (SELECT ywtx,ajlb_bm
       FROM v_pcajxx
       GROUP BY ywtx, ajlb_bm) pctx
      LEFT JOIN xt_pc_dm d ON pctx.ywtx=d.dm
      LEFT JOIN xt_dm_ajlbbm@tyyw_link.net ajlb ON pctx.ajlb_bm=ajlb.ajlbbm
) --业务条线、案件类别临时表（只统计评查系统里出现过的类别）end
--算比例start
SELECT y.ywtx,txtab.ywtx_mc,y.ajlb_bm,txtab.ajlb_mc,
  y.bars,y.bjajs,y.pcrys,y.pcajs
  ,to_char((y.pcajs*100/y.bjajs),'fm999999990.0099')||'%'  "评查比例（4/2）"
  ,round(decode(y.bars,0,0,(y.pcajs/y.bars)),4)   "人均被评查件（4/1）"
  ,round(decode(y.pcrys,0,0,(y.pcajs/y.pcrys)),4)  "人均评查数件（4/3）"
  ,y.sfhj "是否合计行"
FROM
  (--算数量start
    SELECT ywtx,ajlb_bm,bars,bjajs,pcrys,pcajs,sfhj from hztab
    UNION ALL
    --增加合计行
    SELECT nvl(ywtx,'ALL') ywtx,NULL,sum(bars),sum(bjajs),sum(pcrys),sum(pcajs),'Y' sfhj from hztab  GROUP BY ROLLUP (ywtx)
  ) y--算数量end
  RIGHT JOIN txtab ON txtab.ywtx=y.ywtx AND txtab.ajlb_bm=y.ajlb_bm
--算比例end
;
 
