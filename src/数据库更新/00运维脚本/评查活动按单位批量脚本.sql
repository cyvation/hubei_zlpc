 
CREATE TABLE pc_hd_bak AS SELECT *
FROM yx_pc_hd WHERE pcflbm IN (001,002) ORDER by pcflbm,pchdbm;

DECLARE
    v_sql    VARCHAR2(3000);
    v_cursor SYS_REFCURSOR;
    v_dwbm   CHAR(6) DEFAULT '420000';
    v_record xt_zzjg_dwbm%ROWTYPE;
BEGIN


    -- 查询本单位及所有下级单位
    v_sql := 'SELECT *
                FROM xt_zzjg_dwbm where dwbm !=''420000''
               START WITH dwbm = :dwbm
                      AND sfsc = ''N''
             CONNECT BY fdwbm = PRIOR dwbm
                    AND sfsc = ''N''';

    OPEN v_cursor FOR v_sql
        USING v_dwbm;

    LOOP
        FETCH v_cursor
            INTO v_record;
        EXIT WHEN v_cursor%NOTFOUND;
    insert into yx_pc_hd 
SELECT
 REPLACE(PCHDBM, '420000'||PCFLBM,v_record.dwbm||PCFLBM),
 PCHDMC,
 v_record.dwbm,
 PCFLBM,
 SFSS,
 SFFY,
 PCKSSJ,
 PCJSSJ,
 XH,
 v_record.dwbm,
 CJRGH,
 CJRMC,
 CJSJ,
 SFQD,
 QDR_DWBM,
 QDR_GH,
 QDR_MC,
 QD_SJ,
 SFJS,
 JSR_DWBM,
 JSR_GH,
 JSR_MC,
 JS_SJ,
 SM,
 PCMBJ,
 SFSC,
 SXGZJ,
 HDZT_BM,
 HDZT_MS
  FROM pc_hd_bak WHERE pcflbm IN (001,002) ORDER by pcflbm,pchdbm;

    END LOOP;
END;
