 CREATE TABLE v_pcx AS
 SELECT p.pcslbm,p.xtdm zxdm,x.mc zxmc,p.fflxtdm xmdm,ff.mc xmmc,p.flxtdm lxdm, f.mc lxmc,j.pcjl,p.pcjg,j.BMSAH,j.WCRQ_NF,j.ywtx,j.ywtx_mc,j.stajbs,p.sm from
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
          
 SELECT count(*) from  v_pcx t  ;
/*事实认定  20002
证据采信 20001
法律适用 20003
办案程序 20004
错误 30002
瑕疵 30003*/

--项目问题数
SELECT xmdm,
       xmmc,
       COUNT(*)
  FROM v_pcx
 GROUP BY xmdm,
          xmmc
 ORDER BY xmdm;
 
 --按条线
  SELECT 
  xmmc,
       ywtx_mc,
       COUNT(*)
  FROM v_pcx WHERE xmdm IN ('20002','20001','20003','20004')
 GROUP BY xmmc,ywtx_mc 
 ORDER BY xmmc,ywtx_mc;

--因某项目问题评为瑕疵、不合格案件
  SELECT   xmmc,  COUNT(*)
  FROM v_pcx WHERE xmdm IN ('20002','20001','20003','20004')
  AND pcjl IN ('瑕疵案件','不合格案件')
 GROUP BY xmmc  
 ORDER BY xmmc;
