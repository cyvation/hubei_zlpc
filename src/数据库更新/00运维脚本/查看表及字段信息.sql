
select decode(type,'table',A.table_name,null) 表名,A.COLUMN_NAME 字段名,A.DATA_TYPE 数据类型,A.NULLABLE 可为空,A.comments 描述
  from (select 'table' type,t.table_name,
               '【' || nvl(t.comments, t.table_name) || '】' as COLUMN_NAME,
               t.comments as DATA_TYPE,
               null NULLABLE,
               t.comments,
               0 as col_id
          from user_tab_comments t
         where t.table_type = 'TABLE'
           and t.table_name not like '%$0'
        union
        select 'column' type, 
               b.TABLE_NAME,
               b.COLUMN_NAME,
               decode(b.DATA_TYPE,
                      'NUMBER', b.DATA_TYPE || '(' || b.DATA_PRECISION || decode(b.DATA_SCALE,0,'',',' || b.DATA_SCALE) || ')',
                      'VARCHAR2', b.DATA_TYPE || '(' || b.DATA_LENGTH ||')',
                      b.DATA_TYPE),
               b.NULLABLE,
               c.comments,
               b.COLUMN_ID
          from user_tab_columns b, user_col_comments c
         where b.table_name not like '%$0'
           and b.TABLE_NAME = c.table_name
           and b.COLUMN_NAME = c.column_name) A
 order by TABLE_NAME, col_id;
