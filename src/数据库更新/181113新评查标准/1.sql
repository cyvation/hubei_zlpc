--xt_dc_ajlb
--xt_dc_mb
--xt_dc_pdx
--xt_dc_pdxfl
--yx_dc_pdx

-- Create table
create table XT_DC_AJLB
(
  YWBM   CHAR(2) not null,
  AJLBBM CHAR(4) not null,
  AJLBMC VARCHAR2(300),
  AJSLJC VARCHAR2(60),
  SFSC   CHAR(1),
  XH     NUMBER,
  DCMBBM VARCHAR2(9)
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
-- Add comments to the table
comment on table XT_DC_AJLB
is '等次评定与特定案件类别的对应关系表（一对多）';
-- Add comments to the columns
comment on column XT_DC_AJLB.DCMBBM
is '等次评定模板';

-- Create table
create table XT_DC_MB
(
  DCMBBM VARCHAR2(9) not null,
  DCMBMC VARCHAR2(300),
  DWBM   CHAR(6),
  SFQY   CHAR(1) default 'Y',
  CJSJ   DATE default SYSDATE,
  XH     NUMBER,
  SM     VARCHAR2(4000),
  SFTY   CHAR(1) default 'Y'
)
tablespace ZLPC
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 16
next 8
minextents 1
maxextents unlimited
);
-- Add comments to the table
comment on table XT_DC_MB
is '等次评定模板';
-- Add comments to the columns
comment on column XT_DC_MB.DCMBBM
is '等次评定编码（单位编码+3位数字）';
comment on column XT_DC_MB.DCMBMC
is '等次评定模板名称';
comment on column XT_DC_MB.DWBM
is '单位编码';
comment on column XT_DC_MB.SFQY
is '是否启用';
comment on column XT_DC_MB.CJSJ
is '创建时间';
comment on column XT_DC_MB.XH
is '序号';
comment on column XT_DC_MB.SM
is '备注';
comment on column XT_DC_MB.SFTY
is 'Y通用，N特定案件(看xt_dc_ajlb表)';
-- Create/Recreate primary, unique and foreign key constraints
alter table XT_DC_MB
  add constraint PK_XT_DC_MB primary key (DCMBBM)
  using index
  tablespace ZLPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );

-- Create table
create table XT_DC_PDX
(
  PDXBM   VARCHAR2(13) not null,
  PDXMC   VARCHAR2(4000),
  PDXFLBM VARCHAR2(13),
  DCMBBM  VARCHAR2(9) not null,
  PDXLX   NUMBER default -1,
  PCFS    CHAR(1) default 1,
  PCJLBM  VARCHAR2(13),
  FZ_GD   NUMBER,
  ZDPCCX  VARCHAR2(20),
  XH      NUMBER,
  SFTJ    CHAR(1) default 'Y',
  BZ      VARCHAR2(4000),
  MRZ     VARCHAR2(300),
  FLXTDM  VARCHAR2(13),
  FFLXTDM VARCHAR2(13),
  XTDM    VARCHAR2(13),
  YWTX    VARCHAR2(13)
)
tablespace ZLPC
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 16
next 8
minextents 1
maxextents unlimited
);
-- Add comments to the table
comment on table XT_DC_PDX
is '等次评定项';
-- Add comments to the columns
comment on column XT_DC_PDX.PDXBM
is '等次评定项编码（等次模板编码+4位数值）';
comment on column XT_DC_PDX.PDXMC
is '等次评定项名称';
comment on column XT_DC_PDX.PDXFLBM
is '等次评定项分类编码';
comment on column XT_DC_PDX.DCMBBM
is '等次评定模板编码（单位编码+3位数字）';
comment on column XT_DC_PDX.PDXLX
is '评查项类型(-1.扣分 0.只记录不打分 1.加分)';
comment on column XT_DC_PDX.PCFS
is '评定方式（1.Checkbox 2.Input）';
comment on column XT_DC_PDX.PCJLBM
is '评查结论编码';
comment on column XT_DC_PDX.FZ_GD
is '分值（固定）';
comment on column XT_DC_PDX.ZDPCCX
is '自动评查程序编码';
comment on column XT_DC_PDX.XH
is '序号';
comment on column XT_DC_PDX.SFTJ
is '是否统计';
comment on column XT_DC_PDX.BZ
is '备注';
comment on column XT_DC_PDX.MRZ
is '默认值';
comment on column XT_DC_PDX.FLXTDM
is '分类系统代码 XT_PC_DM';
comment on column XT_DC_PDX.FFLXTDM
is '父分类系统父代码 XT_PC_DM';
comment on column XT_DC_PDX.XTDM
is '评定项所属系统代码XT_PC_DM';
comment on column XT_DC_PDX.YWTX
is '业务条线代码';
-- Create/Recreate primary, unique and foreign key constraints
alter table XT_DC_PDX
  add constraint PK_XT_DC_PDX primary key (PDXBM)
  using index
  tablespace ZLPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );

-- Create table
create table XT_DC_PDXFL
(
  PDXFLBM  VARCHAR2(13) not null,
  PDXFLMC  VARCHAR2(400),
  PDXFLFBM VARCHAR2(13),
  DCMBBM   VARCHAR2(9) not null,
  PDJLBM   VARCHAR2(13),
  XH       NUMBER,
  SFTJ     CHAR(1),
  SM       VARCHAR2(4000),
  MRZ      VARCHAR2(300),
  FLXTDM   VARCHAR2(13),
  FFLXTDM  VARCHAR2(13),
  YWTX     VARCHAR2(13)
)
tablespace ZLPC
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 16
next 8
minextents 1
maxextents unlimited
);
-- Add comments to the table
comment on table XT_DC_PDXFL
is '等次评定项分类';
-- Add comments to the columns
comment on column XT_DC_PDXFL.PDXFLBM
is '等次评定项分类编码';
comment on column XT_DC_PDXFL.PDXFLMC
is '等次评定项分类名称';
comment on column XT_DC_PDXFL.PDXFLFBM
is '等次评定项父分类编码';
comment on column XT_DC_PDXFL.DCMBBM
is '等次评定模板编码（单位编码+3位数字）';
comment on column XT_DC_PDXFL.PDJLBM
is '评定结论编码（系统分类代码表 XT_DM_FLDM的评查结果代码对应）';
comment on column XT_DC_PDXFL.XH
is '序号';
comment on column XT_DC_PDXFL.SFTJ
is '是否统计';
comment on column XT_DC_PDXFL.SM
is '备注信息';
comment on column XT_DC_PDXFL.MRZ
is '默认值';
comment on column XT_DC_PDXFL.FLXTDM
is '分类系统代码 XT_PC_DM';
comment on column XT_DC_PDXFL.FFLXTDM
is '父分类系统父代码 XT_PC_DM';
comment on column XT_DC_PDXFL.YWTX
is '业务条线代码';
-- Create/Recreate primary, unique and foreign key constraints
alter table XT_DC_PDXFL
  add constraint PK_XT_DC_PDXFL primary key (PDXFLBM)
  using index
  tablespace ZLPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );

-- Create table
create table YX_DC_PDX
(
  PCSLBM  CHAR(22) not null,
  PDXBM   VARCHAR2(13) not null,
  PDXMC   VARCHAR2(4000),
  PDXFLBM VARCHAR2(13),
  DCMBBM  VARCHAR2(9) not null,
  AJLBBM  CHAR(4) not null,
  AJLBMC  VARCHAR2(300),
  PDXLX   NUMBER default -1,
  PCFS    CHAR(1),
  PCJLBM  VARCHAR2(13),
  FZ_GD   NUMBER,
  ZDPCCX  CHAR(20),
  XH      NUMBER,
  SFTJ    CHAR(1),
  BZ      VARCHAR2(4000),
  PDJG    VARCHAR2(4000),
  PDYJ    VARCHAR2(4000),
  JLSJ    DATE default sysdate,
  PCRDWBM CHAR(6),
  PCRDWMC VARCHAR2(300),
  PCRGH   CHAR(4),
  PCRMC   VARCHAR2(200),
  SM      VARCHAR2(4000),
  FLXTDM  VARCHAR2(13),
  FFLXTDM VARCHAR2(13),
  YWTX    VARCHAR2(13),
  PCFLBM  CHAR(3),
  WAY     CHAR(1) default '0',
  XTDM    VARCHAR2(13)
)
tablespace ZLPC
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 16
next 8
minextents 1
maxextents unlimited
);
-- Add comments to the table
comment on table YX_DC_PDX
is '等次评定项记录';
-- Add comments to the columns
comment on column YX_DC_PDX.PCSLBM
is '评查受理编码';
comment on column YX_DC_PDX.PDXBM
is '等次评定项模板编码';
comment on column YX_DC_PDX.PDXMC
is '等次评定项名称';
comment on column YX_DC_PDX.PDXFLBM
is '等次评定项分类编码';
comment on column YX_DC_PDX.DCMBBM
is '等次评定编码（单位编码+3位数字）';
comment on column YX_DC_PDX.PDXLX
is '评查项类型(-1.扣分 0.只记录不打分 1.加分)';
comment on column YX_DC_PDX.PCFS
is '评查方式（1.Checkbox 2.Input）';
comment on column YX_DC_PDX.PCJLBM
is '评查结论编码';
comment on column YX_DC_PDX.FZ_GD
is '分值（固定）';
comment on column YX_DC_PDX.ZDPCCX
is '自动评查程序编码';
comment on column YX_DC_PDX.XH
is '序号';
comment on column YX_DC_PDX.SFTJ
is '是否统计';
comment on column YX_DC_PDX.BZ
is '备注（评查项模板备注）';
comment on column YX_DC_PDX.PDJG
is '评查结果';
comment on column YX_DC_PDX.PDYJ
is '评查意见';
comment on column YX_DC_PDX.JLSJ
is '记录时间';
comment on column YX_DC_PDX.PCRDWBM
is '评查人单位编码（评查单元，评查内容分配给不同人评查）';
comment on column YX_DC_PDX.PCRDWMC
is '评查人单位名称';
comment on column YX_DC_PDX.PCRGH
is '评查人工号';
comment on column YX_DC_PDX.PCRMC
is '评查人名称';
comment on column YX_DC_PDX.SM
is '说明';
comment on column YX_DC_PDX.FLXTDM
is '分类系统代码 XT_PC_DM';
comment on column YX_DC_PDX.FFLXTDM
is '父分类系统父代码 XT_PC_DM';
comment on column YX_DC_PDX.YWTX
is '业务条线代码';
comment on column YX_DC_PDX.PCFLBM
is '评查分类编码';
comment on column YX_DC_PDX.WAY
is '评查方式：线上0 ;线下1';
comment on column YX_DC_PDX.XTDM
is '评查项所属系统代码XT_PC_DM';
-- Create/Recreate primary, unique and foreign key constraints
alter table YX_DC_PDX
  add constraint PK_YX_DC_PDX primary key (PCSLBM, PDXBM)
  using index
  tablespace ZLPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 192K
  next 1M
  minextents 1
  maxextents unlimited
  );
-- Create/Recreate indexes
create index IDX_YX_DC_PDX_PCSLBM on YX_DC_PDX (PCSLBM)
tablespace ZLPC
pctfree 10
initrans 2
maxtrans 255
storage
(
initial 64K
next 1M
minextents 1
maxextents unlimited
);
create index IDX_YX_DC_PDX_XTDM on YX_DC_PDX (XTDM)
tablespace ZLPC
pctfree 10
initrans 2
maxtrans 255
storage
(
initial 64K
next 1M
minextents 1
maxextents unlimited
);
