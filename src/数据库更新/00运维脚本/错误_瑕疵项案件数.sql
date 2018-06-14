       

--错误/瑕疵项案件数
 SELECT dm.mc,dm.dm,nvl(a.cnt,0) cnt FROM
     --运行评查项里的错误/瑕疵案件数start
     (SELECT pcx.xtdm,COUNT(pcslbm) cnt
        FROM 
        (--合并线上线下start
          SELECT onx.pcslbm,onx.xtdm,onx.pcjg,onx.flxtdm FROM yx_pc_pcx onx 
           WHERE onx.pcjg != '0'  AND onx.flxtdm = '30002'
           UNION ALL 
          SELECT ofx.pcslbm,ofx.xtdm,ofx.pcjg,ofx.flxtdm FROM yx_pc_offline_pcx ofx
           WHERE ofx.pcjg != '0'  AND ofx.flxtdm = '30002'
        ) pcx--合并线上线下end
       WHERE EXISTS (
             SELECT 1 FROM v_pcajxx ajxx WHERE pcx.pcslbm = ajxx.PCSLBM
               /*AND ajxx.wcrq_nf in ('2013','2014','2015','2016','2017','2018')       
                 AND ajxx.stajbs in ('0')     
                AND ajxx.sfldba in ('N', 'Y')
                AND ajxx.PCFLBM IN ('001')*/
       )
       GROUP BY pcx.xtdm
      ) a 
      --运行评查项里的错误/瑕疵案件数end
 RIGHT JOIN 
  (SELECT dm,mc --从评查模版里提取定义的错误/瑕疵项start
    FROM (SELECT DISTINCT x.xtdm FROM xt_pc_pcx x WHERE x.flxtdm = '30002') a
   INNER JOIN xt_pc_dm d ON a.xtdm = d.dm
     AND d.sfsc = 'N'
    ) dm   --从评查模版里提取定义的错误/瑕疵项end     
    ON a.xtdm=dm.dm
 ORDER BY nvl(a.cnt,0) desc, a.xtdm;
 
 --程序使用的
select DISTINCT dm ,mc, nvl(sum(cnt), 0) cnt from (
        select * from (SELECT PCX_XTDM.dm ,PCX_XTDM.mc, nvl(aj.cnt, 0) cnt FROM (SELECT dm, mc from
        (SELECT DISTINCT x.xtdm from xt_pc_pcx x
        WHERE x.flxtdm = '30002') a
        INNER JOIN xt_pc_dm d ON a.xtdm=d.dm and d.sfsc = 'N') PCX_XTDM
        LEFT JOIN (SELECT COUNT(distinct pcx.pcslbm) cnt, pcx.xtdm FROM v_pcajxx ajxx
        INNER JOIN yx_pc_pcx pcx ON pcx.pcslbm = ajxx.pcslbm
        WHERE 1=1 AND pcx.pcjg != '0'      AND pcx.flxtdm in (30002)
            AND pcx.xtdm in (SELECT dm from
            (SELECT DISTINCT x.xtdm from xt_pc_pcx x
            WHERE x.flxtdm = '30002') a
            INNER JOIN xt_pc_dm d ON a.xtdm=d.dm and d.sfsc = 'N')
        group by pcx.xtdm) AJ ON AJ.xtdm = PCX_XTDM.dm ORDER BY nvl(aj.cnt, 0) desc, PCX_XTDM.dm)
        union all
        select * from(SELECT PCX_XTDM.dm ,PCX_XTDM.mc, nvl(aj.cnt, 0) cnt FROM (SELECT dm, mc from
        (SELECT DISTINCT x.xtdm from xt_pc_pcx x
        WHERE x.flxtdm = '30002' ) a
        INNER JOIN xt_pc_dm d ON a.xtdm=d.dm and d.sfsc = 'N') PCX_XTDM
        LEFT JOIN (SELECT COUNT(distinct pcx.pcslbm) cnt, pcx.xtdm FROM v_pcajxx ajxx
        INNER JOIN yx_pc_offline_pcx pcx ON pcx.pcslbm = ajxx.pcslbm
        WHERE 1=1 AND pcx.pcjg != '0' AND pcx.flxtdm in (30002)
            AND pcx.xtdm in (SELECT dm from
            (SELECT DISTINCT x.xtdm from xt_pc_pcx x
            WHERE x.flxtdm = '30002') a
            INNER JOIN xt_pc_dm d ON a.xtdm=d.dm and d.sfsc = 'N')
        group by pcx.xtdm) AJ ON AJ.xtdm = PCX_XTDM.dm ORDER BY nvl(aj.cnt, 0) desc, PCX_XTDM.dm))
        group by dm,mc ORDER BY nvl(cnt, 0) desc;
 
         --错误项案件数
         SELECT dm.mc,dm.dm,nvl(a.cnt,0) cnt FROM
             --运行评查项里的错误/瑕疵案件数start
             (SELECT pcx.xtdm,COUNT(pcslbm) cnt
                FROM
                (--合并线上线下start
                  SELECT onx.pcslbm,onx.xtdm,onx.pcjg,onx.flxtdm FROM yx_pc_pcx onx
                   WHERE onx.pcjg != '0'  AND onx.flxtdm = #{flxtdm}
                   UNION ALL
                  SELECT ofx.pcslbm,ofx.xtdm,ofx.pcjg,ofx.flxtdm FROM yx_pc_offline_pcx ofx
                   WHERE ofx.pcjg != '0'  AND ofx.flxtdm = #{flxtdm}
                ) pcx--合并线上线下end
               WHERE EXISTS (
                     SELECT 1 FROM v_pcajxx ajxx WHERE pcx.pcslbm = ajxx.PCSLBM
                    <if test="wcrqnf !=null and wcrqnf!=''">
                        AND ajxx.wcrq_nf in (${wcrqnf})
                    </if>
                    <if test="ajtjlb !=null and ajtjlb!=''">
                        AND ajxx.stajbs in (${ajtjlb})
                    </if>
                    <if test="cbrsf !=null and cbrsf=='N,Y'">
                        AND ajxx.sfldba in ('N', 'Y')
                    </if>
                    <if test="cbrsf !=null and cbrsf!='' and cbrsf!='N,Y'">
                        AND ajxx.sfldba = #{cbrsf}
                    </if>
                    <if test="ywtx !=null and ywtx!=''">
                        AND ajxx.ywtx in (${ywtx})
                    </if>
                    <if test="pcflbm !=null and pcflbm!=''">
                        AND ajxx.pcflbm in (${pcflbm})
                    </if>
                    <if test="dwbm !=null and dwbm!=''">
                        AND ajxx.bpc_dwbm in (${dwbm})
                    </if>
               )
               GROUP BY pcx.xtdm
              ) a
              --运行评查项里的错误/瑕疵案件数end
         RIGHT JOIN
          (SELECT dm,mc --从评查模版里提取定义的错误/瑕疵项start
            FROM (SELECT DISTINCT x.xtdm
                    FROM xt_pc_pcx x
                   WHERE x.flxtdm =  #{flxtdm}) a
           INNER JOIN xt_pc_dm d ON a.xtdm = d.dm
            AND d.sfsc = 'N'
            ) dm   --从评查模版里提取定义的错误/瑕疵项end
            ON a.xtdm=dm.dm
         ORDER BY nvl(a.cnt,0) desc, a.xtdm
