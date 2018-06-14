
drop TABLE xt_tj_dw  ;
create table XT_TJ_DW
(
  ID    VARCHAR2(10),
  PID   VARCHAR2(10),
  DWBM  CHAR(6) not null,
  DWMC  VARCHAR2(300),
  DWJB  CHAR(1),
  FDWBM CHAR(6),
  XH    NUMBER,
  DWJC  VARCHAR2(60),
  SFHJ  CHAR(1),
  SFSC  CHAR(1)
);
insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420000', '-1', '420000', '省院', '2', '100000', 2, '鄂检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420100', '420000', '420100', '武汉市人民检察院', '3', '420000', 4, '武检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420102', '420100', '420102', '江岸区院', '4', '420100', 5, '武岸检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420103', '420100', '420103', '江汉区院', '4', '420100', 6, '武江检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420104', '420100', '420104', '硚口区院', '4', '420100', 7, '硚检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420105', '420100', '420105', '汉阳区院', '4', '420100', 8, '武阳检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420106', '420100', '420106', '武昌区人民检察院', '4', '420100', 9, '昌检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420107', '420100', '420107', '青山区院', '4', '420100', 10, '武青检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420111', '420100', '420111', '洪山区院', '4', '420100', 11, '洪检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420112', '420100', '420112', '东西湖区院', '4', '420100', 12, '武东检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420113', '420100', '420113', '汉南区院', '4', '420100', 13, '鄂武南检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420114', '420100', '420114', '蔡甸区人民检察院', '4', '420100', 14, '蔡检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420115', '420100', '420115', '江夏区院', '4', '420100', 15, '武夏检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420116', '420100', '420116', '黄陂区院', '4', '420100', 16, '陂检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420117', '420100', '420117', '新洲区人民检察院', '4', '420100', 17, '武新检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420191', '420100', '420191', '武汉经济技术开发区院', '4', '420100', 18, '武开检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420192', '420100', '420192', '东湖开发区院', '4', '420100', 19, '武东湖检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420193', '420100', '420193', '武汉市城郊地区人民检察院', '4', '420100', 20, '武城郊检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420200', '420000', '420200', '黄石市院', '3', '420000', 22, '黄检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420202', '420200', '420202', '黄石市黄石港区人民检察院', '4', '420200', 23, '港检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420203', '420200', '420203', '西塞山区检察院', '4', '420200', 24, '西检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420204', '420200', '420204', '下陆区院', '4', '420200', 25, '下检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420205', '420200', '420205', '铁山区检察院', '4', '420200', 26, '铁检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420222', '420200', '420222', '阳新县检察院', '4', '420200', 27, '阳检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420281', '420200', '420281', '大冶市检察院', '4', '420200', 28, '冶检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420300', '420000', '420300', '十堰市人民检察院', '4', '420000', 30, '十检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420302', '420300', '420302', '十堰市茅箭区人民检察院', '4', '420300', 31, '十茅检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420303', '420300', '420303', '十堰市张湾区人民检察院', '4', '420300', 32, '十张检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420321', '420300', '420321', '湖北省十堰市郧阳区人民检察院', '4', '420300', 33, '鄂十郧检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420322', '420300', '420322', '郧西县人民检察院', '4', '420300', 34, '西检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420323', '420300', '420323', '竹山县人民检察院', '4', '420300', 35, '竹检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420324', '420300', '420324', '竹溪县人民检察院', '4', '420300', 36, '溪检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420325', '420300', '420325', '房县人民检察院', '4', '420300', 37, '房检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420381', '420300', '420381', '丹江口市人民检察院', '4', '420300', 38, '丹检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420500', '420000', '420500', '宜昌市院', '3', '420000', 40, '宜检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420502', '420500', '420502', '西陵区院', '4', '420500', 41, '西检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420503', '420500', '420503', '伍家岗区院', '4', '420500', 42, '宜伍检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420504', '420500', '420504', '点军区院', '4', '420500', 43, '点检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420505', '420500', '420505', '猇亭区院', '4', '420500', 44, '猇检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420506', '420500', '420506', '夷陵区院', '4', '420500', 45, '夷检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420525', '420500', '420525', '远安县院', '4', '420500', 46, '远检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420526', '420500', '420526', '兴山县院', '4', '420500', 47, '兴检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420527', '420500', '420527', '秭归县院', '4', '420500', 48, '秭检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420528', '420500', '420528', '长阳县院', '4', '420500', 49, '长检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420529', '420500', '420529', '五峰县院', '4', '420500', 50, '五检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420581', '420500', '420581', '宜都市院', '4', '420500', 51, '都检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420582', '420500', '420582', '当阳市院', '4', '420500', 52, '当检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420583', '420500', '420583', '枝江市院', '4', '420500', 53, '枝检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420591', '420500', '420591', '葛洲坝院', '4', '420500', 54, '鄂宜葛检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420592', '420500', '420592', '三峡坝区院', '4', '420500', 55, '鄂宜三检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420600', '420000', '420600', '襄阳市人民检察院', '3', '420000', 57, '襄检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420602', '420600', '420602', '襄城区人民检察院', '4', '420600', 58, '襄城检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420606', '420600', '420606', '樊城区人民检察院', '4', '420600', 59, '樊城检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420607', '420600', '420607', '襄州区人民检察院', '4', '420600', 60, '襄州检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420624', '420600', '420624', '南漳县人民检察院', '4', '420600', 61, '南检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420625', '420600', '420625', '谷城县人民检察院', '4', '420600', 62, '谷检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420626', '420600', '420626', '保康县人民检察院', '4', '420600', 63, '保检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420682', '420600', '420682', '老河口市人民检察院', '4', '420600', 64, '河检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420683', '420600', '420683', '枣阳市人民检察院', '4', '420600', 65, '枣检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420684', '420600', '420684', '宜城市人民检察院', '4', '420600', 66, '宜检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420691', '420600', '420691', '襄阳市城郊地区人民检察院', '4', '420600', 67, '襄阳城郊检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420693', '420600', '420693', '高新区人民检察院', '4', '420600', 68, '鄂襄高新检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420700', '420000', '420700', '鄂州市检察院', '4', '420000', 70, '鄂州检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420702', '420700', '420702', '梁子湖区人民检察院', '4', '420700', 71, '梁检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420703', '420700', '420703', '鄂州市华容区人民检察院', '4', '420700', 72, '华检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420704', '420700', '420704', '鄂州市鄂城区人民检察院', '4', '420700', 73, '鄂城检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420800', '420000', '420800', '荆门市院', '3', '420000', 75, '荆检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420802', '420800', '420802', '东宝区院', '4', '420800', 76, '东检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420804', '420800', '420804', '掇刀区院', '4', '420800', 77, '掇检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420821', '420800', '420821', '京山县院', '4', '420800', 78, '京检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420822', '420800', '420822', '沙洋县院', '4', '420800', 79, '沙检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420881', '420800', '420881', '钟祥市院', '4', '420800', 80, '钟检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420892', '420800', '420892', '沙洋地区院', '4', '420800', 81, '荆沙检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420900', '420000', '420900', '孝感市院', '4', '420000', 83, '孝检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420902', '420900', '420902', '孝感市孝南区人民检察院', '4', '420900', 84, '鄂孝南检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420921', '420900', '420921', '孝昌县人民检察院', '4', '420900', 85, '鄂孝昌检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420922', '420900', '420922', '大悟县人民检察院', '4', '420900', 86, '悟检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420923', '420900', '420923', '云梦县人民检察院', '4', '420900', 87, '云检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420981', '420900', '420981', '应城市人民检察院', '4', '420900', 88, '应检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420982', '420900', '420982', '安陆市人民检察院', '4', '420900', 89, '鄂安检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('420984', '420900', '420984', '汉川市人民检察院', '4', '420900', 90, '鄂川检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421000', '420000', '421000', '荆州市院', '3', '420000', 92, '鄂荆检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421002', '421000', '421002', '沙市区院', '4', '421000', 93, '鄂荆沙检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421003', '421000', '421003', '荆州区院', '4', '421000', 94, '鄂荆州区检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421022', '421000', '421022', '公安县院', '4', '421000', 95, '鄂公检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421023', '421000', '421023', '监利县院', '4', '421000', 96, '鄂监检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421024', '421000', '421024', '江陵县院', '4', '421000', 97, '鄂江检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421081', '421000', '421081', '石首市院', '4', '421000', 98, '鄂石检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421083', '421000', '421083', '洪湖市院', '4', '421000', 99, '鄂洪检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421087', '421000', '421087', '松滋市院', '4', '421000', 100, '鄂松检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421093', '421000', '421093', '江北地区院', '4', '421000', 101, '鄂荆江检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421100', '420000', '421100', '黄冈市院', '4', '420000', 103, '黄检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421102', '421100', '421102', '黄冈市黄州区人民检察院', '3', '421100', 104, '黄州检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421121', '421100', '421121', '团风县人民检察院', '4', '421100', 105, '团检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421122', '421100', '421122', '红安县人民检察院', '4', '421100', 106, '鄂红检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421123', '421100', '421123', '罗田县人民检察院', '4', '421100', 107, '罗检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421124', '421100', '421124', '英山县人民检察院', '4', '421100', 108, '英检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421125', '421100', '421125', '浠水县人民检察院', '4', '421100', 109, '浠检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421126', '421100', '421126', '蕲春县人民检察院', '4', '421100', 110, '蕲检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421127', '421100', '421127', '黄梅县人民检察院', '4', '421100', 111, '梅检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421181', '421100', '421181', '麻城市人民检察院', '4', '421100', 112, '麻检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421182', '421100', '421182', '武穴市人民检察院', '4', '421100', 113, '武检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421200', '420000', '421200', '咸宁市院', '3', '420000', 115, '咸检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421202', '421200', '421202', '咸安区人民检察院', '4', '421200', 116, '咸安检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421221', '421200', '421221', '嘉鱼县人民检察院', '4', '421200', 117, '嘉检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421222', '421200', '421222', '通城县人民检察院', '4', '421200', 118, '隽检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421223', '421200', '421223', '崇阳县人民检察院', '4', '421200', 119, '崇检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421224', '421200', '421224', '通山县人民检察院', '4', '421200', 120, '通检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421281', '421200', '421281', '赤壁市人民检察院', '4', '421200', 121, '赤检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421300', '420000', '421300', '随州市院', '4', '420000', 123, '随检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421302', '421300', '421302', '湖北省随州市曾都区人民检察院', '4', '421300', 124, '鄂曾都检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421321', '421300', '421321', '随县人民检察院', '4', '421300', 125, '随县检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('421381', '421300', '421381', '广水市人民检察院', '4', '421300', 126, '广检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422800', '420000', '422800', '恩施土家族苗族自治州人民检察院', '4', '420000', 128, '恩州检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422801', '422800', '422801', '恩施市人民检察院', '4', '422800', 129, '恩市检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422802', '422800', '422802', '利川市人民检察院', '4', '422800', 130, '利检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422822', '422800', '422822', '建始县人民检察院', '3', '422800', 131, '建检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422823', '422800', '422823', '巴东县人民检察院', '4', '422800', 132, '巴检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422825', '422800', '422825', '宣恩县人民检察院', '4', '422800', 133, '宣检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422826', '422800', '422826', '咸丰县人民检察院', '4', '422800', 134, '咸检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422827', '422800', '422827', '来凤县人民检察院', '4', '422800', 135, '来检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('422828', '422800', '422828', '鹤峰县人民检察院', '4', '422800', 136, '鹤检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429000', '420000', '429000', '汉江分院', '3', '420000', 138, '鄂汉检', 'Y', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429004', '429000', '429004', '仙桃市人民检察院', '4', '429000', 139, '鄂仙检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429005', '429000', '429005', '潜江市人民检察院', '4', '429000', 140, '鄂潜检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429006', '429000', '429006', '天门市人民检察院', '4', '429000', 141, '天检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429191', '420000', '429191', '神农架林区人民检察院', '3', '420000', 142, '神检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429200', '420000', '429200', '湖北省人民检察院武汉铁路运输分院', '4', '420000', 144, '鄂检铁分', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429201', '429200', '429201', '武汉铁检', '4', '429200', 145, '鄂武铁检', 'N', 'N');

insert into xt_tj_dw (ID, PID, DWBM, DWMC, DWJB, FDWBM, XH, DWJC, SFHJ, SFSC)
values ('429202', '429200', '429202', '襄阳铁检', '4', '429200', 146, '鄂襄铁检', 'N', 'N');



