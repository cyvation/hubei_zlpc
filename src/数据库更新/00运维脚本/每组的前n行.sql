select *
  from (select type,week,judge_by_score,amount,
               row_number() over(partition by type, week order by type, week, score) rn
          from a_temp)
 where rn <= 2 --每组前二行
  ;