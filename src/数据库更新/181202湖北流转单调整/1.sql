
--移除 新增检察官意见
delete from xt_pc_dzzy where dzbh = '030';

-- 所有案件必须送审
delete from xt_pc_dzzy where dzbh = '004';

delete from xt_pc_dzzy where dzbh = '089';


-- 暂时不启用专项活动
update xt_pc_lb
set sfqy = 'N'
where pcflbm = '003';