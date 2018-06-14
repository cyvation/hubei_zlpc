INSERT INTO xt_qx_gnfl VALUES ('4200000007','评查报告',NULL,7,'420000','N','statistics',NULL);

UPDATE xt_qx_gnfl SET flxh=10 WHERE flbm='4200000006';
UPDATE xt_qx_gndy t SET t.flbm='4200000007',gnxh='13' WHERE gnbm='4200000205';

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000206', '评查总体情况', '4200000007', '', 'view/analysis/pcGeneral/index.html', '', 1, '评查总体情况', NULL, '420000', '', 'N', 'N', 'random');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000207', '案件质量情况', '4200000007', '', 'view/analysis/ajqkzlfx/ndfx.html', '', 2, '案件质量情况', NULL, '420000', '', 'N', 'N', 'quality');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000208', '错误项目分析', '4200000007', '', 'view/analysis/errorItem/index.html', '', 3, '错误项目分析', NULL, '420000', '', 'N', 'N', 'approval');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000209', '瑕疵项目分析', '4200000007', '', 'view/analysis/flaw/index.html', '', 4, '瑕疵项目分析', NULL, '420000', '', 'N', 'N', 'casesearch');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000210', '突出错误分析', '4200000007', '', 'view/analysis/tccwxc/cwfx.html', '', 5, '突出错误分析', NULL, '420000', '', 'N', 'N', 'random');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000211', '突出瑕疵分析', '4200000007', '', 'view/analysis/tccwxc/xcfx.html', '', 6, '突出瑕疵分析', NULL, '420000', '', 'N', 'N', 'quality');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000307', '案件质量情况地区分析', '4200000007', '', 'view/analysis/ajqkzlfx/dqfx.html', '', 8, '案件质量情况地区分析', '', '420000', '', 'N', 'N', 'quality');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000308', '案件质量情况条线分析', '4200000007', '', 'view/analysis/ajqkzlfx/txfx.html', '', 9, '案件质量情况条线分析', '', '420000', '', 'N', 'N', 'approval');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000311', '报表导航', '4200000007', '', 'view/analysis/ajqkzlfx/txfx.html', '', 0, '报表导航', '', '420000', '', 'N', 'N', 'approval');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000206', 'N');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000207', 'N');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000208', 'N');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000209', 'N');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000210', 'N');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000211', 'N');

insert into xt_qx_gndy (GNBM, GNMC, FLBM, GNCXJ, GNCT, GNSM, GNXH, GNXSMC, GNCS, DWBM, CSCS, SFSC, SFMTCK, ICON)
values ('4200000312', '分析导航', '4200000004', '', 'view/analysis/fxdh/fxdh.html', '', 12, '分析导航', '', '420000', '', 'N', 'N', 'approval');

insert into xt_qx_dwgn (DWBM, GNBM, SFSC)
values ('420000', '4200000312', 'N');




