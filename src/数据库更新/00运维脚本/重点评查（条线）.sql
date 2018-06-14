--捕后不起诉的审查逮捕案件（侦监）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_ZJ_FZXYR T ON AJ.BMSAH = T.BMSAH
                           AND AJ.CBDW_BM = T.CBDW_BM
 INNER JOIN YX_AG_GLAJ GL ON T.BMSAH = GL.YAJ_BMSAH
                         AND GL.SFSC = 'N'
                         AND GL.YAJ_AJLB_BM = '0201'
 INNER JOIN (SELECT *
               FROM TYYW_GS_XYRQK
              WHERE SJCLQK_DM LIKE '0322110102%') GS ON GS.BMSAH =
                                                        GL.GLAJ_BMSAH
                                                    AND GS.XYRBH = T.XYRBH
                                                    AND GS.SFSC = 'N'
 WHERE T.SFSC = 'N'
   AND T.SJRQ IS NOT NULL
   AND T.SJCLJG_DM LIKE '0201101201%'
   AND AJ.SFSC = 'N'
   AND EXISTS (SELECT 1
          FROM TYYW_GG_XYRJBXX XYR
         WHERE XYR.SFSC = 'N'
           AND AJ.BMSAH = XYR.BMSAH
           AND T.XYRBH = XYR.XYRBH
           AND XYR.ZASNL >= '18');

--捕后不起诉的审查逮捕案件（未检）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_ZJ_FZXYR T ON AJ.BMSAH = T.BMSAH
                           AND AJ.CBDW_BM = T.CBDW_BM
 INNER JOIN YX_AG_GLAJ GL ON T.BMSAH = GL.YAJ_BMSAH
                         AND GL.SFSC = 'N'
                         AND GL.YAJ_AJLB_BM = '0201'
 INNER JOIN (SELECT *
               FROM TYYW_GS_XYRQK
              WHERE SJCLQK_DM LIKE '0322110102%') GS ON GS.BMSAH =
                                                        GL.GLAJ_BMSAH
                                                    AND GS.XYRBH = T.XYRBH
                                                    AND GS.SFSC = 'N'
 WHERE T.SFSC = 'N'
   AND T.SJRQ IS NOT NULL
   AND T.SJCLJG_DM LIKE '0201101201%'
   AND AJ.SFSC = 'N'
   AND EXISTS (SELECT 1
          FROM TYYW_GG_XYRJBXX XYR
         WHERE XYR.SFSC = 'N'
           AND AJ.BMSAH = XYR.BMSAH
           AND T.XYRBH = XYR.XYRBH
           AND XYR.ZASNL < '18'
           AND XYR.ZASNL > '0');

--捕后撤案的审查逮捕案件（侦监）
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_ZJ_FZXYR T ON AJ.BMSAH = T.BMSAH
                           AND T.SFSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 WHERE T.SFSC = 'N'
   AND T.SJRQ IS NOT NULL
   AND T.SJCLJG_DM LIKE '0201101201%'
   AND AJ.SFSC = 'N'
   AND T.BHCARQ IS NOT NULL
   AND EXISTS (SELECT 1
          FROM TYYW_GG_XYRJBXX XYR
         WHERE XYR.SFSC = 'N'
           AND AJ.BMSAH = XYR.BMSAH
           AND T.XYRBH = XYR.XYRBH
           AND XYR.ZASNL >= '18');
--捕后撤案的审查逮捕案件（未检）
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_ZJ_FZXYR T ON AJ.BMSAH = T.BMSAH
                           AND T.SFSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 WHERE T.SFSC = 'N'
   AND T.SJRQ IS NOT NULL
   AND T.SJCLJG_DM LIKE '0201101201%'
   AND AJ.SFSC = 'N'
   AND T.BHCARQ IS NOT NULL
   AND EXISTS (SELECT 1
          FROM TYYW_GG_XYRJBXX XYR
         WHERE XYR.SFSC = 'N'
           AND AJ.BMSAH = XYR.BMSAH
           AND T.XYRBH = XYR.XYRBH
           AND XYR.ZASNL < '18'
           AND XYR.ZASNL > '0');

--捕后判无罪的审查逮捕案件（侦监）
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_ZJ_FZXYR T ON AJ.BMSAH = T.BMSAH
                           AND T.SFSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 INNER JOIN YX_AG_GLAJ GL ON T.BMSAH = GL.YAJ_BMSAH
                         AND GL.SFSC = 'N'
                         AND GL.YAJ_AJLB_BM = '0201'
 INNER JOIN (SELECT *
               FROM TYYW_GS_XYRQK
              WHERE YSPJZM_AYDM LIKE '03333344008%') GS ON GS.BMSAH =
                                                           GL.GLAJ_BMSAH
                                                       AND GS.SFSC = 'N'
                                                       AND GS.XYRBH = T.XYRBH
 WHERE T.SFSC = 'N'
   AND T.SJRQ IS NOT NULL
   AND T.SJCLJG_DM LIKE '0201101201%'
   AND AJ.SFSC = 'N'
   AND EXISTS (SELECT 1
          FROM TYYW_GG_XYRJBXX XYR
         WHERE XYR.SFSC = 'N'
           AND AJ.BMSAH = XYR.BMSAH
           AND T.XYRBH = XYR.XYRBH
           AND XYR.ZASNL >= '18');

--捕后判无罪的审查逮捕案件（未检）
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_ZJ_FZXYR T ON AJ.BMSAH = T.BMSAH
                           AND T.SFSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 INNER JOIN YX_AG_GLAJ GL ON T.BMSAH = GL.YAJ_BMSAH
                         AND GL.SFSC = 'N'
                         AND GL.YAJ_AJLB_BM = '0201'
 INNER JOIN (SELECT *
               FROM TYYW_GS_XYRQK
              WHERE YSPJZM_AYDM LIKE '03333344008%') GS ON GS.BMSAH =
                                                           GL.GLAJ_BMSAH
                                                       AND GS.SFSC = 'N'
                                                       AND GS.XYRBH = T.XYRBH
 WHERE T.SFSC = 'N'
   AND T.SJRQ IS NOT NULL
   AND T.SJCLJG_DM LIKE '0201101201%'
   AND AJ.SFSC = 'N'
   AND EXISTS (SELECT 1
          FROM TYYW_GG_XYRJBXX XYR
         WHERE XYR.SFSC = 'N'
           AND AJ.BMSAH = XYR.BMSAH
           AND T.XYRBH = XYR.XYRBH
           AND XYR.ZASNL < '18'
           AND XYR.ZASNL > '0');
-----------------416--------------------------
--不起诉(公诉)

SELECT aj.cbdw_bm DWBM,
       aj.bmsah   BMSAH,
       aj.wcrq    WCBZRQ
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_GS_XYRQK T ON AJ.BMSAH = T.BMSAH
                           AND T.SFSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 WHERE SJCLQK_DM BETWEEN '0322110102000' AND '0322110102032'
   AND AJ.SFSC = 'N'
   AND aj.wcrq IS NOT NULL
   AND AJ.AJLB_BM = '0301';

--绝对不诉(公诉)
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_GS_XYRQK T ON AJ.BMSAH = T.BMSAH
                           AND T.SFSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 WHERE SJCLQK_DM BETWEEN '0322110102000' AND '0322110102012'
   AND AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0301';

--绝对不起诉(公诉)
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0301'
   AND EXISTS
 (SELECT 1
          FROM TYYW_GS_XYRQK T
         WHERE T.sfsc = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND SJCLQK_DM BETWEEN '0322110102010' AND '0322110102012')
--相对不起诉(公诉)
  SELECT COUNT(1)
          FROM TYYW_GG_AJJBXX AJ
         WHERE AJ.SFSC = 'N'
           AND AJ.AJLB_BM = '0301'
           AND EXISTS
         (SELECT 1
                  FROM TYYW_GS_XYRQK T
                 WHERE T.sfsc = 'N'
                   AND AJ.BMSAH = T.BMSAH
                   AND SJCLQK_DM BETWEEN '0322110102020' AND '0322110102022')
        
        --绝对不诉(未检)
          SELECT COUNT(1)
                  FROM TYYW_GG_AJJBXX AJ
                 INNER JOIN TYYW_GS_XYRQK T ON AJ.BMSAH = T.BMSAH
                                           AND T.SFSC = 'N'
                                           AND AJ.CBDW_BM = T.CBDW_BM
                 WHERE SJCLQK_DM BETWEEN '0322110102010' AND '0322110102012'
                   AND AJ.SFSC = 'N'
                   AND AJ.AJLB_BM = '0314';



--绝对不起诉(未检)
SELECT COUNT(1)
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0314'
   AND EXISTS
 (SELECT 1
          FROM TYYW_GS_XYRQK T
         WHERE T.sfsc = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND SJCLQK_DM BETWEEN '0322110102010' AND '0322110102012')
--相对不起诉(未检)
  SELECT COUNT(1)
          FROM TYYW_GG_AJJBXX AJ
         WHERE AJ.SFSC = 'N'
           AND AJ.AJLB_BM = '0314'
           AND EXISTS
         (SELECT 1
                  FROM TYYW_GS_XYRQK T
                 WHERE T.sfsc = 'N'
                   AND AJ.BMSAH = T.BMSAH
                   AND SJCLQK_DM BETWEEN '0322110102020' AND '0322110102022');


-- 起诉后撤回的一审公诉案件（公诉）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_GS_XYRQK T ON AJ.BMSAH = T.BMSAH
                           AND T.SfSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 WHERE SJCLQK_DM = '0322110101000'
   AND CHQSRQ IS NOT NULL
   AND AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0301';

-- 起诉后撤回的一审公诉案件（未检）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 INNER JOIN TYYW_GS_XYRQK T ON AJ.BMSAH = T.BMSAH
                           AND T.SfSC = 'N'
                           AND AJ.CBDW_BM = T.CBDW_BM
 WHERE SJCLQK_DM = '0322110101000'
   AND CHQSRQ IS NOT NULL
   AND AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0314';
-----------++++++++++++++++++++++++++++++++++++++++++++++++++--------------
--抗诉后撤回抗诉的二审抗诉或者审判监督抗诉案件（审判监督）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE EXISTS (SELECT 1
          FROM TYYW_GS_ESKS T
         WHERE T.SFSC = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND CHKSRQ IS NOT NULL)
   AND AJ.SFSC = 'N'
   AND aj.wcrq IS NOT NULL
   AND AJ.AJLB_BM = '0304'
UNION ALL
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE EXISTS (SELECT 1
          FROM TYYW_GS_SPJDCXKS T
         WHERE T.sfsc = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND bychksrq IS NOT NULL)
   AND AJ.SFSC = 'N'
   AND aj.wcrq IS NOT NULL
   AND AJ.AJLB_BM = '0305';

--变更检察机关处理决定的刑事申诉案件（控申）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'
   AND EXISTS
   (SELECT 1
          FROM TYYW_SS_XSSSFC T
         WHERE T.SFSC = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND (T.SSLB_DM BETWEEN '0930136301000' AND '0930136301006')
           AND T.LAFCJG_DM IN ('0916132402000', '0916132403000')
   )
   AND aj.wcrq IS NOT NULL
   AND AJ.AJLB_BM = '0901';

--决定予以赔偿的国家赔偿案件（控申）
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'  
   AND EXISTS (SELECT 1
          FROM tyyw_ss_xspc T
         WHERE T.SFSC = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND INSTR(JDQK_DMS,'0903133701000') > 0)
   AND aj.wcrq IS NOT NULL
   AND AJ.AJLB_BM = '0904';

--做出不支持监督申请决定的行政诉讼裁判结果监督案件（民行）
   SELECT *
          FROM TYYW_GG_AJJBXX AJ
         WHERE AJ.SFSC = 'N'
           AND EXISTS
         (SELECT 1
                  FROM TYYW_MX_MSXZJD T
                 WHERE T.SFSC = 'N'
                   AND AJ.BMSAH = T.BMSAH
                   AND (T.AJXZ_DM BETWEEN '0702500000000' AND '0702512000000')
                   AND T.JACLQK_DM IN ('0704220100004', '0704220100005')
         )
         AND aj.wcrq IS NOT NULL
         AND AJ.AJLB_BM = '0701'
         ;
----418-起诉后判无罪---------------------------------
 --公诉
SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'
   AND EXISTS
 (SELECT 1
          FROM TYYW_GS_XYRQK T
         WHERE T.SFSC = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND T. YSXGX_DM BETWEEN '0333334400800' AND '0333334400802'
           AND T.SXPJRQ IS NOT NULL)
   AND aj.wcrq IS NOT NULL
   AND AJ.AJLB_BM = '0301'           ;
          
 --未检
 SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0314'
   AND EXISTS
 (SELECT 1
          FROM TYYW_GS_XYRQK T
         WHERE T.SFSC = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND T.YSXGX_DM BETWEEN '0333334400800' AND '0333334400802'
           AND T.SXPJRQ IS NOT NULL)  ;

--二审抗诉 审监
 SELECT *
  FROM TYYW_GG_AJJBXX AJ
 WHERE AJ.SFSC = 'N'
   AND AJ.AJLB_BM = '0304'
   AND EXISTS
 (SELECT 1
          FROM TYYW_GS_XYRQK T
         WHERE T.SFSC = 'N'
           AND AJ.BMSAH = T.BMSAH
           AND T.ESXGX_DM BETWEEN '0333334400800' AND '0333334400802')   ; 
 邓涛
起诉后判无罪的就是这三类
