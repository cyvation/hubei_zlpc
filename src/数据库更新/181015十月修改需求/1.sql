--1. 人员库变动，系统中业务条线 xt_dm_ywbm ---> xt_pc_dm(fdm=1)

alter table xt_pc_ryk modify ywbm varchar2(5) default null;

-- 2.评查管理新增重点案件信息库
insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000029', '重点案件信息库', '4200000005', '', 'view/manage/keyinfo/index.html', '', 5, '重点案件信息库', '', '420000', '', 'N', 'N', 'manage');


-- 3.专项新建报告模板，xt_pc_wsmb,替换了模板
update xt_pc_wsmb set wsmblj = 'WORD/专项评查报告模板.doc' where wsmbbh = '420000003003';


-- 4. 移除部门反馈
delete from xt_pc_dbrw where gnbm = '4200000005';

-- 4. 按钮添加 报审、二次报审、发送检察官
insert into xt_pc_dzzy (DZBH, PCFLBM, LCMBBM, LCJDBH, CZLXBM, DZMC, ICON, CLCX, CLCXCS, DZXH, SM, XYCX, XYCXCS)
values ('002', '000', '0000000001', '006', '1', '评查报审', 'pcbs', 'butPcblPcbs', '', 3, '', 'validatePcbs', '');

insert into xt_pc_dzzy (DZBH, PCFLBM, LCMBBM, LCJDBH, CZLXBM, DZMC, ICON, CLCX, CLCXCS, DZXH, SM, XYCX, XYCXCS)
values ('007', '000', '0000000001', '007', '1', '评查报审', 'pcbs', 'butPcbs', '', 4, '', 'validateSfwcscsp', '');

insert into xt_pc_dzzy (DZBH, PCFLBM, LCMBBM, LCJDBH, CZLXBM, DZMC, ICON, CLCX, CLCXCS, DZXH, SM, XYCX, XYCXCS)
values ('008', '000', '0000000001', '007', '1', '发送承办人', 'fscbr', 'butFscbr', '', 5, '', 'validateSpJdFscbr', '');


-- 5.检察官默认5天发聩，反馈意见修改
update xt_yx_pz
set value = '无异议（无反馈意见）'
where key = 'pcfk_zd_fkyj';


update xt_yx_pz
set value = '5'
where key = 'pcfk_min_days';