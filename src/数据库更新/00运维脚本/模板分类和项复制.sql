select f.*,d.* from xt_pc_pcxfl f 
left join xt_pc_dm d on decode(f.pcxflmc,'准确','正确','规范','正确','其他情况','其他',pcxflmc) =d.mc

where f.pcmbbm='4200000010000007';

update xt_pc_pcxfl f set (f.xtdm,f.xtfdm,sftj)=(

select d.dm,d.fdm,d.sftj from  xt_pc_dm d where decode(f.pcxflmc,'准确','正确','规范','正确','其他情况','其他',pcxflmc) =d.mc and rownum<2
) 

 

select * from xt_pc_pcxfl t where  t.pcmbbm like '420000001%' order by t.pcmbbm,t.pcxflbm;

-- insert into xt_pc_pcxfl

select replace(t.pcxflbm,'420000001','420000003') ,
t.pcxflmc,replace(t.pcxflfbm,'420000001','420000003') ,
replace(t.pcmbbm,'420000001','420000003') ,t.pcjlbm,t.xh,t.sftj,t.sm,t.mrz,t.xtdm,t.xtfdm
from xt_pc_pcxfl t where  t.pcmbbm like '420000001%' order by t.pcmbbm,t.pcxflbm;


-- insert into xt_pc_pcx
select replace(t.pcxbm,'420000001','420000003') ,
pcxmc,replace(t.pcxflbm,'420000001','420000003') ,
replace(t.pcmbbm,'420000001','420000003'),
pcxlx,pcfs,pcjlbm,fz_gd,fz_qsz,fz_jsz,zdpccx,xh,sftj,bz,mrz
from xt_pc_pcx t  where  t.pcmbbm like '420000001%' order by t.pcmbbm,t.pcxflbm,t.pcxbm;
