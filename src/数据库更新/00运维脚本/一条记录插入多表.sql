[Q]怎么实现一条记录根据条件多表插入
[A]9i以上可以通过Insert all语句完成，仅仅是一个语句，如：
INSERT ALL
WHEN (id=1) THEN
INTO table_1 (id, name)
values(id,name)
WHEN (id=2) THEN
INTO table_2 (id, name)
values(id,name)
ELSE
INTO table_other (id, name)
values(id, name)
SELECT id,name
FROM a;
如果没有条件的话，则完成每个表的插入，如
INSERT ALL
INTO table_1 (id, name)
values(id,name)
INTO table_2 (id, name)
values(id,name)
INTO table_other (id, name)
values(id, name)
SELECT id,name
FROM a;