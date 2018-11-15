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

-- Create table
create table YX_PC_JXPCAJ
(
  DWBM     CHAR(6),
  DWMC     VARCHAR2(300),
  BMSAH    VARCHAR2(300),
  AJMC     VARCHAR2(300 CHAR),
  PCFLBM   CHAR(3),
  AJLB_BM  CHAR(4),
  AJLB_MC  VARCHAR2(300),
  JSDW     CHAR(6),
  JSDWMC   VARCHAR2(300),
  CJSJ     DATE default SYSDATE,
  SM       VARCHAR2(3000),
  CZR_DWBM CHAR(6),
  CZR_DWMC VARCHAR2(300),
  CZR_GH   CHAR(4),
  CZR_MC   VARCHAR2(60)
)
tablespace ZLPC
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 64
next 8
minextents 1
maxextents unlimited
);
-- Add comments to the columns
comment on column YX_PC_JXPCAJ.DWBM
is '承办单位';
comment on column YX_PC_JXPCAJ.DWMC
is '被评查案件所属单位名称';
comment on column YX_PC_JXPCAJ.BMSAH
is '被评查案件部门受案号';
comment on column YX_PC_JXPCAJ.AJMC
is '被评查案件名称';
comment on column YX_PC_JXPCAJ.PCFLBM
is '评查类别编码';
comment on column YX_PC_JXPCAJ.AJLB_BM
is '被评查案件类别编码';
comment on column YX_PC_JXPCAJ.AJLB_MC
is '被评查案件类别名称';
comment on column YX_PC_JXPCAJ.JSDW
is '接受单位';
comment on column YX_PC_JXPCAJ.JSDWMC
is '接受单位名称';
comment on column YX_PC_JXPCAJ.CJSJ
is '分配时间';
comment on column YX_PC_JXPCAJ.SM
is '说明';
comment on column YX_PC_JXPCAJ.CZR_DWBM
is '分配人单位';
comment on column YX_PC_JXPCAJ.CZR_DWMC
is '分配人单位名称';
comment on column YX_PC_JXPCAJ.CZR_GH
is '分配人工号';
comment on column YX_PC_JXPCAJ.CZR_MC
is '分配人姓名';

