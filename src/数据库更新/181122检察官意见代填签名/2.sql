
--新增检察长角色
insert into xt_qx_jsbm (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
values ('420000', '120', '检察长', '9191', 9, '70');

-- 检委会按钮控制
update xt_pc_dzzy
set czlxbm = '2', dzmc='报送检委会'
where dzbh = '090';

-- 赋权，权限同副检察长
delete from xt_qx_jsgnfp where jsbm = '120';

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


    -- 各个地方开放检察长角色
    insert into xt_zzjg_jsbm (DWBM, JSBM, JSMC, BMBM, JSXH, SPJSBM)
    values (v_record.dwbm, '120', '检察长', '9191', 9, '70');

    -- 各个地方检察长赋权
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000003', null, null, '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000007', null, null, '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000008', null, '[{"key": "dwypc", "value": "3", "describle": "单位已评查"}]', '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000013', null, null, '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000014', null, null, '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000015', null, null, '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000016', null, null, '9191');
    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '120', '4200000701', null, null, '9191');

  END LOOP;
END;

--todo 添加检察长人员 xt_qx_ryjsfp