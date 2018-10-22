-- 新增交叉案件分配功能
insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000040', '交叉案件分配', '4200000005', '', 'view/manage/cross_assign/index.html', '', 6, '交叉案件分配', '', '420000', '', 'N', 'N', 'manage');


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


    -- 新增交叉案件分配
    insert into xt_qx_dwgn(dwbm, gnbm, sfsc) values(v_record.dwbm, '4200000040', 'N');

    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '100', '4200000040', null, null, '9191');


  END LOOP;
END;
