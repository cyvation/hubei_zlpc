-- Add/modify columns
alter table YX_PC_JBXX add WCRQ_NF char(4);
-- Add comments to the columns
comment on column YX_PC_JBXX.WCRQ_NF
  is '案件办结年份';

create index idx_yx_pc_jbxx_year on YX_PC_JBXX (wcrq_nf);



-- Add/modify columns
alter table yx_pc_offline_jbxx add WCRQ_NF char(4);
-- Add comments to the columns
comment on column yx_pc_offline_jbxx.WCRQ_NF
  is '案件办结年份';

create index idx_yx_pc_offline_jbxx_year on yx_pc_offline_jbxx  (wcrq_nf);