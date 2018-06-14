--评查案件没有在办结表里的
SELECT * from yx_pc_jbxx b WHERE 
NOT EXISTS (SELECT * from tyyw_bjaj j WHERE j.bmsah=b.bmsah)
AND b.sfsc='N'
AND b.wcrq_nf>='2013' AND b.wcrq_nf<'2018'
