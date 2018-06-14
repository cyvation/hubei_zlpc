
--清理数据
 
/*truncate TABLE xt_zzjg_bmbm; 
truncate TABLE xt_zzjg_jsbm; 
truncate TABLE xt_zzjg_rybm; */
-- 导入单位数据
/*INSERT INTO xt_zzjg_dwbm
(dwbm, dwmc, fdwbm, dwjb, sfsc, dwjc, dwsx)
SELECT dwbm, dwmc, fdwbm, dwjb, sfsc, dwjc, dwsx
FROM xt_zzjg_dwbm@tyyw_link.net WHERE dwbm LIKE '42%' ORDER by dwbm;*/

DECLARE
    v_sql    VARCHAR2(3000);
    v_cursor SYS_REFCURSOR;
    v_dwbm   CHAR(6) DEFAULT '420000';
    v_record xt_zzjg_dwbm%ROWTYPE;
BEGIN

    -- 查询本单位及所有下级单位
    v_sql := 'SELECT *
                FROM xt_zzjg_dwbm
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
    
        -- 导入人员数据
        INSERT INTO xt_zzjg_rybm
            (dwbm,
             gh,
             dlbm,
             kl,
             mc,
             gzzh,
             yddhhm,
             dzyj,
             ydwbm,
             ydwmc,
             sflsry,
             sfsc,
             sftz,
             xb,
             caid,
             zrjcggh)
            SELECT dwbm,
                   gh,
                   dlbm,
                   kl,
                   mc,
                   gzzh,
                   yddhhm,
                   dzyj,
                   ydwbm,
                   ydwmc,
                   sflsry,
                   sfsc,
                   sftz,
                   xb,
                   caid,
                   zrjcggh
              FROM xt_zzjg_rybm@tyyw_link.net
             WHERE dwbm = v_record.dwbm;
    
        -- 新增评查部门及评查角色
        INSERT INTO xt_zzjg_bmbm
            (dwbm,
             bmbm,
             fbmbm,
             bmmc,
             bmjc,
             bmwhjc,
             bmahjc,
             sflsjg,
             sfcbbm,
             bmxh,
             bz,
             sfsc,
             bmys)
        VALUES
            (v_record.dwbm,
             '9191',
             NULL,
             '案件质量评查',
             '案件质量评查',
             NULL,
             NULL,
             'N',
             NULL,
             1,
             NULL,
             'N',
             NULL);
    
       insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '100', '评查管理员', '9191', 1, '0 ');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '101', '评查组员', '9191', 3, '10');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '102', '评查组长', '9191', 4, '20');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '104', '案管负责人', '9191', 6, '50');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '105', '承办检察官', '9191', 7, '');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '106', '业务部门领导', '9191', 8, '30');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '107', '分管副检察长', '9191', 9, '60');

insert into XT_ZZJG_JSBM (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values (v_record.dwbm, '108', '评查员', '9191', 2, '10');


/*    
        -- 下级单位及角色功能权限
        IF v_record.dwbm != v_dwbm
        THEN
        
            -- 下级单位功能授权
            INSERT INTO xt_qx_dwgn
                (dwbm, gnbm, sfsc)
                SELECT v_record.dwbm, gnbm, 'N'
                  FROM xt_qx_dwgn
                 WHERE dwbm = v_dwbm;
        
            -- 下级单位的角色功能授权   
            INSERT INTO xt_qx_jsgnfp
                (dwbm, jsbm, gnbm, bmbm)
                SELECT v_record.dwbm, jsbm, gnbm, bmbm
                  FROM xt_qx_jsgnfp
                 WHERE dwbm = v_dwbm;
        
        END IF;*/
    END LOOP;
END;
