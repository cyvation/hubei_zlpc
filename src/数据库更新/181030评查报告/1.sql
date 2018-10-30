-- 新增功能分析报告
insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000711', '分析报告', '4200000007', '', 'view/evaluate/report/index.html', '', 15, '分析报告', '', '420000', '', 'N', 'N', 'report');

-- 添加分析报告相应模板
insert into xt_pc_wsmb (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000000002', '420000', '000', '季度评查报告', '4', '/Files/json/pcgb/moban/附件2：季度质量评查报告模板.doc', null, 4, '', '1', '3', '420000000004');

insert into xt_pc_wsmb (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000000003', '420000', '000', '年度评查报告', '4', '/Files/json/pcgb/moban/附件3：年度质量评查报告模板.doc', null, 4, '', '1', '5', '420000000004');

insert into xt_pc_wsmb (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000000004', '420000', '000', '半年评查报告', '4', '/Files/json/pcgb/moban/附件4：半年质量评查报告模板.doc', null, 4, '', '1', '4', '420000000004');

insert into xt_pc_wsmb (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000000006', '420000', '000', '其他评查报告', '4', '/Files/json/pcgb/moban/附件6：其他质量评查报告模板.doc', null, 4, '', '1', '6', '420000000004');

-- 表结构：yx_pc_jzwj

alter table yx_pc_jzwj add bszt char(1) default '0';
comment on column yx_pc_jzwj.bszt is '报送状态（0.未报送 1.已报送，市院默认9）';

--分配权限，仅管理员生成与查看
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
    insert into xt_qx_dwgn(dwbm, gnbm, sfsc) values(v_record.dwbm, '4200000711', 'N');

    insert into xt_qx_jsgnfp(dwbm, jsbm, gnbm, bz, gncs, bmbm) values(v_record.dwbm, '100', '4200000711', null, null, '9191');


  END LOOP;
END;




