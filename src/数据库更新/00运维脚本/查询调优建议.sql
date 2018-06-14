--测试脚本beign
 DECLARE
      my_task_name VARCHAR2(30);
      my_sqltext   CLOB;
      userName     varchar2(30) default 'AGZS';
    BEGIN
      my_sqltext := 'SELECT * FROM XT_ZZJG_DWBM';
      my_task_name := DBMS_SQLTUNE.CREATE_TUNING_TASK(
              sql_text    => my_sqltext,
              user_name   => userName,   
              scope       => 'COMPREHENSIVE',
            time_limit  => 60,
            task_name   => 'tuning_sql_test',
            description => 'Task to tune a query on a specified table');

    DBMS_SQLTUNE.EXECUTE_TUNING_TASK( task_name => 'tuning_sql_test');
  END;
--测试脚本end



--调优建议
select * from dba_advisor_actions where task_name = 'tuning_sql_test'
