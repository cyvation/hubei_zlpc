-- 文书模板
insert into xt_pc_wsmb (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000000007', '420000', '000', '个案评定报告', '3', '/Files/json/pcgb/moban/评定报告模板.doc', null, 1, '', '1', '6', '420000000003');

insert into xt_pc_wsmb (WSMBBH, DWBM, PCFLBM, WSMBMC, WSMBLB, WSMBLJ, WSMBNR, WSPX, SPBBM, SFGX, SM, JZMLH)
values ('420000000008', '420000', '000', '评查意见书', '3', '/Files/json/pcgb/moban/评查意见书.doc', null, 2, '', '1', '6', '420000000003');


-- 评查意见书按钮
insert into xt_pc_dzzy (DZBH, PCFLBM, LCMBBM, LCJDBH, CZLXBM, DZMC, ICON, CLCX, CLCXCS, DZXH, SM, XYCX, XYCXCS)
values ('033', '000', '0000000001', '006', '1', '生成评查意见书', '', 'butAddAjpcDoc', '', 2, '', '', '');

-- 旧数据调整
update yx_pc_jzwj wj
set wj.wsmbbh = '420000000007'
where wj.wjlx = '3'
and   wj.wsmbbh is null;
