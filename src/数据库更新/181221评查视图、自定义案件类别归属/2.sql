-- 自定义案件归属问题
update xt_pc_sxgz
set fgzbm = '420000008002',fgzmc = '侦监'
where gzbm = '420000008902';

update xt_pc_sxgz
set fgzbm = '420000008003',fgzmc = '公诉'
where gzbm = '420000008903';

update xt_pc_sxgz
set fgzbm = '420000008006',fgzmc = '审监'
where gzbm = '420000008906';

update xt_pc_sxgz
set fgzbm = '420000008007',fgzmc = '民事'
where gzbm = '420000008907';

update xt_pc_sxgz
set fgzbm = '420000008008',fgzmc = '行政'
where gzbm = '420000008908';

update xt_pc_sxgz
set fgzbm = '420000008009',fgzmc = '控申'
where gzbm = '420000008909';

update xt_pc_sxgz
set fgzbm = '420000008016',fgzmc = '执检'
where gzbm = '420000008916';

update xt_pc_sxgz
set fgzbm = '420000008017',fgzmc = '未检'
where gzbm = '420000008917';