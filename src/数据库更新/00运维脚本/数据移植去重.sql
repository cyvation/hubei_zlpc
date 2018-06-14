

怎么样抽取重复记录
select * from table t1  where t1.rowed !=
(select max(rowed) from table t2 
where t1.id=t2.id and t1.name=t2.name)
或者
select count(*), t.col_a,t.col_b from table t
group by col_a,col_b
having count(*)>1

如果想删除重复记录，可以把第一个语句的select替换为delete;

最高效的删除重复记录方法 ( 因为使用了ROWID) 

DELETE FROM EMP E 
 WHERE E.ROWID > (SELECT MIN(X.ROWID)  FROM EMP X WHERE X.EMP_NO = E.EMP_NO);

--借用中间表,然后删除rn字段，
 create table offence_one as   select a.* from 
  select count(*) from 
   (select o.*,
               row_number() over(partition by num_plate, happen_time order by o.offence_id ) rn
          from offence o) a
 where a.rn =1;;
最后：
 insert into offence select * from offence_one ; 



如果存在就更新，不存在就插入可以用一个语句实现吗
[A]9i已经支持了，是Merge，但是只支持select子查询，
如果是单条数据记录，可以写作select …… from dual的子查询。
语法为：
MERGE INTO TAB_TO c
USING (SELECT course_name, period, course_hours FROM TAB_FROM) cu
ON (c.course_name = cu.course_name AND c.period = cu.period)
WHEN MATCHED THEN
  UPDATE SET c.course_hours = cu.course_hours
WHEN NOT MATCHED THEN
  INSERT
    (c.course_name, c.period, c.course_hours)
  VALUES
    (cu.course_name, cu.period, cu.course_hours);

