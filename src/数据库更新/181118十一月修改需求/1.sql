--1.个案报告与评定报告合并
-- 历史数据
update yx_pc_jzwj
set wjlx = '3'
where wjlx = '6';

-- 按钮 butAddWs-->butAddPdBg
update xt_pc_dzzy
set dzmc = '生成个案评定报告', clcx = 'butAddPdBg'
where dzbh = '001' or dzbh = '006';


--2.检察官意见，改为评查员填写，在报审前填写
-- 移除老的
delete from xt_pc_dzzy where dzbh = '008';
-- 检察官意见
insert into xt_pc_dzzy (DZBH, PCFLBM, LCMBBM, LCJDBH, CZLXBM, DZMC, ICON, CLCX, CLCXCS, DZXH, SM, XYCX, XYCXCS)
values ('030', '000', '0000000001', '006', '1', '新增检察官意见', 'fscbr', 'butStuffYj', '', 2, '', 'validateAddStuff', '');

-- 3.小组联席会议\检委会意见 改为报审后可以出现
update xt_pc_dzzy
set lcjdbh = '007'
where dzbh = '089' or dzbh = '090';


----
--等次评定项与评查项的关联表
-- Create table
create table XT_DCX_PCX
(
  PDXBM    VARCHAR2(13) not null,
  PDXMC    VARCHAR2(4000),
  PCX_XTDM VARCHAR2(13) not null,
  PCXMC    VARCHAR2(4000),
  DCMBBM   VARCHAR2(9),
  DCMBMC   VARCHAR2(4000),
  PDJLBM   VARCHAR2(13),
  PDJLMC   VARCHAR2(13),
  BZ       VARCHAR2(4000),
  SFSC     CHAR(1) default 'N' not null
)
tablespace ZLPC
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 64
minextents 1
maxextents unlimited
);
-- Add comments to the table
comment on table XT_DCX_PCX
is '等次评定项与评查项的关联，一对多';
-- Add comments to the columns
comment on column XT_DCX_PCX.PDXBM
is '等次评定项编码（等次模板编码+4位数值）';
comment on column XT_DCX_PCX.PDXMC
is '评定项名称';
comment on column XT_DCX_PCX.PCX_XTDM
is '评查项所属系统代码XT_PC_DM 对应40001..等';
comment on column XT_DCX_PCX.PCXMC
is '评查项名称';
comment on column XT_DCX_PCX.DCMBBM
is '等次评定模板编码（单位编码+3位数字）';
comment on column XT_DCX_PCX.DCMBMC
is '等次评定模板名称';
comment on column XT_DCX_PCX.PDJLBM
is '评定结论编码（系统分类代码表 XT_DM_FLDM的评查结果代码对应，优质9102000000001、合格、瑕疵、不合格）';
comment on column XT_DCX_PCX.PDJLMC
is '优质、合格、瑕疵、不合格';
comment on column XT_DCX_PCX.BZ
is '备注';
comment on column XT_DCX_PCX.SFSC
is '是否删除';
