 CREATE TABLE v_pcx AS
 SELECT p.pcslbm,p.xtdm zxdm,x.mc zxmc,p.fflxtdm xmdm,ff.mc xmmc,p.flxtdm lxdm, f.mc lxmc,j.pcjl,p.pcjg,j.BMSAH,j.AJMC,j.AJLB_BM,j.AJLB_MC,j.BPC_DWBM,j.BPC_DWMC,j.WCRQ_NF,j.ywtx,j.ywtx_mc,j.stajbs,p.sm from
                (
                    SELECT onx.pcslbm,onx.xtdm,onx.pcjg,onx.flxtdm,onx.FFLXTDM,
                    concat(onx.sm,decode(onx.PCJG,'0',NULL,'1',NULL,onx.pcjg)) sm
                    FROM yx_pc_pcx onx
                    WHERE onx.pcjg != '0'
                    
                    UNION ALL
                    SELECT ofx.pcslbm,ofx.xtdm,ofx.pcjg,ofx.flxtdm,ofx.FFLXTDM,
                    concat(ofx.sm,decode(ofx.PCJG,'0',NULL,'1',NULL,ofx.pcjg)) sm
                    FROM yx_pc_offline_pcx ofx
                    WHERE ofx.pcjg != '0'
                   
                )  p
              
           INNER JOIN xt_pc_dm x ON p.xtdm=x.dm
          INNER JOIN xt_pc_dm ff ON p.FFLXTDM=ff.dm
          INNER JOIN xt_pc_dm f ON p.FLXTDM=f.dm
          INNER JOIN v_pcajxx j ON p.pcslbm=j.PCSLBM 
                 AND j.stajbs='0' AND j.WCRQ_NF<'2018'
          ORDER by p.flxtdm,p.fflxtdm,p.pcslbm;
  --------------------------   
/* UPDATE v_pcx v SET (v.bpc_dwbm,v.bpc_dwmc)=(SELECT bpc_dwbm,bpc_dwmc from v_pcajxx p WHERE v.pcslbm=p.PCSLBM);*/
          
 SELECT count(*) from  v_pcx t  ;
/*事实认定  20002
证据采信 20001
法律适用 20003
办案程序 20004
法律文书 20008
司法责任制落实 20010
错误 30002
瑕疵 30003*/



--项目问题数(案件数)
SELECT v.xmdm,v.zxdm,v.xmmc,v.zxmc,COUNT(*) from v_pcx v GROUP BY v.xmdm,v.xmmc,v.zxdm,v.zxmc ORDER by v.xmdm,decode(v.zxdm,'40000','99999',v.zxdm);
 


--市级院项目问题数(案件数) 神龙架算县级院
SELECT v.xmdm,v.xmmc ，COUNT(*) from v_pcx v 
WHERE EXISTS ( SELECT 1 from xt_tj_dw d WHERE d.dwbm=v.bpc_dwbm AND d.dwjb =3 AND d.dwbm!='429191')
GROUP BY v.xmdm,v.xmmc/*,v.zxdm,v.zxmc*/ ORDER by v.xmdm;

--区县院项目问题数(案件数) 神龙架算县级院
SELECT v.xmdm,v.xmmc ，COUNT(*) from v_pcx v 
WHERE EXISTS ( SELECT 1 from xt_tj_dw d WHERE d.dwbm=v.bpc_dwbm AND (d.dwjb =4 OR d.dwbm='429191'))
GROUP BY v.xmdm,v.xmmc/*,v.zxdm,v.zxmc*/ ORDER by v.xmdm;


--市级院细项问题数(案件数) 神龙架算县级院
WITH zx AS (SELECT DISTINCT v.xmdm,v.zxdm,v.xmmc,v.zxmc from v_pcx v)
SELECT zx.*,nvl(a.cnt,0) sl from  
(SELECT v.xmdm,v.zxdm,v.xmmc,v.zxmc,COUNT(*) cnt from v_pcx v 
WHERE EXISTS ( SELECT 1 from xt_tj_dw d WHERE d.dwbm=v.bpc_dwbm AND d.dwjb =3 AND d.dwbm!='429191')
GROUP BY v.xmdm,v.xmmc,v.zxdm,v.zxmc 
) a RIGHT JOIN zx ON a.xmdm=zx.xmdm AND a.zxdm=zx.zxdm 
ORDER by zx.xmdm,decode(zx.zxdm,'40000','99999',zx.zxdm);

--区县院细项问题数(案件数) 神龙架算县级院
WITH zx AS (SELECT DISTINCT v.xmdm,v.zxdm,v.xmmc,v.zxmc from v_pcx v)
SELECT zx.*,nvl(a.cnt,0) sl from  
(SELECT v.xmdm,v.zxdm,v.xmmc,v.zxmc,COUNT(*) cnt from v_pcx v 
WHERE EXISTS ( SELECT 1 from xt_tj_dw d WHERE d.dwbm=v.bpc_dwbm AND (d.dwjb =4 OR d.dwbm='429191'))
GROUP BY v.xmdm,v.xmmc,v.zxdm,v.zxmc 
) a RIGHT JOIN zx ON a.xmdm=zx.xmdm AND a.zxdm=zx.zxdm 
ORDER by zx.xmdm,decode(zx.zxdm,'40000','99999',zx.zxdm);

--市级院细项问题数(案件数) 神龙架算县级院+排序
WITH zx AS (SELECT DISTINCT v.xmdm,v.zxdm,v.xmmc,v.zxmc from v_pcx v)
SELECT zx.*,nvl(a.cnt,0) sl from  
(SELECT v.xmdm,v.zxdm,v.xmmc,v.zxmc,COUNT(*) cnt from v_pcx v 
WHERE EXISTS ( SELECT 1 from xt_tj_dw d WHERE d.dwbm=v.bpc_dwbm AND d.dwjb =3 AND d.dwbm!='429191')
GROUP BY v.xmdm,v.xmmc,v.zxdm,v.zxmc 
) a RIGHT JOIN zx ON a.xmdm=zx.xmdm AND a.zxdm=zx.zxdm 
ORDER by nvl(a.cnt,0) desc,zx.xmdm,decode(zx.zxdm,'40000','99999',zx.zxdm);

--区县院细项问题数(案件数) 神龙架算县级院+排序
WITH zx AS (SELECT DISTINCT v.xmdm,v.zxdm,v.xmmc,v.zxmc from v_pcx v)
SELECT zx.*,nvl(a.cnt,0) sl from  
(SELECT v.xmdm,v.zxdm,v.xmmc,v.zxmc,COUNT(*) cnt from v_pcx v 
WHERE EXISTS ( SELECT 1 from xt_tj_dw d WHERE d.dwbm=v.bpc_dwbm AND (d.dwjb =4 OR d.dwbm='429191'))
GROUP BY v.xmdm,v.xmmc,v.zxdm,v.zxmc 
) a RIGHT JOIN zx ON a.xmdm=zx.xmdm AND a.zxdm=zx.zxdm 
ORDER BY  nvl(a.cnt,0) desc, zx.xmdm,decode(zx.zxdm,'40000','99999',zx.zxdm);




 --按条线
  SELECT 
  xmmc,ywtx_mc,  COUNT(*)
  FROM v_pcx WHERE xmdm IN ('20008','20010')
 GROUP BY xmmc,ywtx_mc  ORDER BY xmmc DESC,COUNT(*) DESC;

--因某项目问题评为瑕疵、不合格案件
  SELECT   xmmc,  COUNT(*)
  FROM v_pcx WHERE xmdm IN ('20008','20010')
  AND pcjl IN ('瑕疵案件','不合格案件')
 GROUP BY xmmc   ORDER BY xmmc;
 
--审查逮捕案件因证据采信错误瑕疵导致案件问题  ajlb_bm='0201'
 SELECT  DISTINCT bmsah/*,ajmc*/  from v_pcx x 
 WHERE /*x.lxdm='30002' AND*/ x.xmdm='20001' AND x.ajlb_bm='0201';
 
 --刑事审判监督业务中因证据采信错误瑕疵导致导致法院驳回案件问题  ajlb_bm='0304'
SELECT  */*DISTINCT bmsah,ajmc */ from v_pcx x 
 WHERE /*x.lxdm='30002' AND*/ x.xmdm='20001' AND x.ajlb_bm='0304';

