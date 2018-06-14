UPDATE xt_qx_gndy g SET g.sfsc='Y' WHERE g.flbm=''4200000004';

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000201', '评查概览', '4200000004', '', 'view/monitor/overview/index.html', '', 1, '评查概览', NULL, '420000', '', 'N', 'N', 'log');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000202', '案件问题汇总', '4200000004', '', 'view/statistics/ajwt_hz/index.html', '', 2, '案件问题汇总', NULL, '420000', '', 'N', 'N', 'random');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000203', '评查结果统计', '4200000004', '', 'view/statistics/zlpc_tj/index.html', '', 3, '评查结果统计', NULL, '420000', '', 'N', 'N', 'approval');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000204', '案件质量分析', '4200000004', '', 'view/statistics/situation3/index.html', '', 4, '案件质量分析', NULL, '420000', '', 'N', 'N', 'casesearch');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000205', '承办人案件质量统计', '4200000004', '', 'view/statistics/promoter/index.html', '', 5, '承办人案件质量统计', NULL, '420000', '', 'N', 'N', 'casesearch');

