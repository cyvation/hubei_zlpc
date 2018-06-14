update xt_qx_gnfl set flmc = '专题分析' where flbm = '4200000004' and flmc = '统计分析';
insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000010', '评查统计', '4200000002', '', 'view/monitor/statis/index.html', '', 3, '评查统计', '', '420000', '', 'N', 'N', 'announce');
insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000010', 'N');



