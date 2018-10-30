--存储过程修改：pkg_pcbl.proc_get_splst
-- 位置：大概144 - 157行

-- 评查审批案件列表
PROCEDURE proc_get_splst(p_sprdwbm   IN yx_pc_sp.sprdwbm%TYPE, --分配人单位编码
p_sprgh     IN yx_pc_sp.sprgh%TYPE, --分配人工号
p_keyword   IN VARCHAR2,  --关键字
p_pagesize  IN NUMBER, --页大小
p_pageindex IN NUMBER, --页索引
p_count     OUT NUMBER, --总数
p_cursor    OUT SYS_REFCURSOR, --返回数据集
p_errmsg    OUT VARCHAR2) IS
v_sql CLOB;
BEGIN
  p_count  := 0;
  p_errmsg := '';

  v_sql :='SELECT t.* FROM
                 (SELECT ROWNUM ID,sp.pcspbm, sp.spwjlx, sp.spwjbm, sp.ssrdwbm, sp.ssrdwmc, sp.ssrgh, sp.ssrxm, sp.ssrq,
                         sp.sprdwbm, sp.sprdwmc, sp.sprgh, sp.sprxm, sp.sprq, sp.spjl, sp.spyj, sp.cjsj, sp.zhxgsj,jb.ajmc MC,''评查案件'' SPLX,
                         sp.spjsbm, sp.spjsmc
                    FROM yx_pc_sp sp
                   INNER JOIN yx_pc_jbxx jb
                      ON jb.pcslbm=sp.spwjbm
                     AND jb.sfsc=''N''
                     AND sp.sfsc=''N''
                   WHERE sp.sprdwbm = '''||p_sprdwbm||'''
                     AND sp.sprgh = '''||p_sprgh||'''
                     AND jb.pcjdbh = :pcjdbh --评查审批
                     AND sp.spwjlx=''1''
                     AND sp.spjl IS NULL
                  UNION
                  SELECT ROWNUM ID,sp.pcspbm, sp.spwjlx, sp.spwjbm, sp.ssrdwbm, sp.ssrdwmc, sp.ssrgh, sp.ssrxm, sp.ssrq,
                         sp.sprdwbm, sp.sprdwmc, sp.sprgh, sp.sprxm, sp.sprq, sp.spjl, sp.spyj, sp.cjsj, sp.zhxgsj,hd.pchdmc MC,''评查方案'' SPLX,
                         sp.spjsbm, sp.spjsmc
                    FROM yx_pc_sp sp
                   INNER JOIN yx_pc_hd hd
                      ON sp.spwjbm=hd.pchdbm
                     AND sp.sfsc=''N''
                     AND hd.sfsc=''N''
                   WHERE sp.sprdwbm = '''||p_sprdwbm||'''
                     AND sp.sprgh = '''||p_sprgh||'''
                     AND hd.hdzt_bm = :hdzt_bh --活动审批
                     AND sp.spwjlx=''3''
                     AND sp.spjl IS NULL
                  UNION
                  SELECT ROWNUM ID,sp.pcspbm, sp.spwjlx, sp.spwjbm, sp.ssrdwbm, sp.ssrdwmc, sp.ssrgh, sp.ssrxm, sp.ssrq,
                         sp.sprdwbm, sp.sprdwmc, sp.sprgh, sp.sprxm, sp.sprq, sp.spjl, sp.spyj, sp.cjsj, sp.zhxgsj,jz.wjmc MC,''评查报告'' SPLX,
                         sp.spjsbm, sp.spjsmc
                    FROM yx_pc_sp sp
                   INNER JOIN yx_pc_jzwj jz
                      ON sp.spwjbm=jz.jzwjbh
                     AND sp.sfsc=''N''
                     AND jz.sfsc=''N''
                     AND jz.wjlx=''1'' --评查报告
                   WHERE sp.sprdwbm = '''||p_sprdwbm||'''
                     AND sp.sprgh = '''||p_sprgh||'''
                     AND sp.spwjlx=''2''
                     AND sp.spjl IS NULL
                   UNION
                    SELECT ROWNUM ID,sp.pcspbm, sp.spwjlx, sp.spwjbm, sp.ssrdwbm, sp.ssrdwmc, sp.ssrgh, sp.ssrxm, sp.ssrq,
                       sp.sprdwbm, sp.sprdwmc, sp.sprgh, sp.sprxm, sp.sprq, sp.spjl, sp.spyj, sp.cjsj, sp.zhxgsj,jz.wjmc MC,''分析报告'' SPLX,
                       sp.spjsbm, sp.spjsmc
                  FROM yx_pc_sp sp
                 INNER JOIN yx_pc_jzwj jz
                    ON sp.spwjbm=jz.jzwjbh
                   AND sp.sfsc=''N''
                   AND jz.sfsc=''N''
                   AND jz.wjlx=''4'' --分析报告，雷军军，湖北
                 WHERE sp.sprdwbm = '''||p_sprdwbm||'''
                   AND sp.sprgh = '''||p_sprgh||'''
                   AND sp.spwjlx=''2''
                   AND sp.spjl IS NULL) t
                 WHERE 1=1';

  -- 审批文件名称
  v_sql := v_sql || pkg_common.func_get_querylikesql('t.mc', p_keyword);

  v_sql := v_sql || ' ORDER BY t.ssrq ASC';

  --获取总数
  EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
  INTO p_count
  USING pkg_pclc.g_jdbh_pcsp, pkg_pclc.g_hdbh_sp ;

  -- 获取数据
  v_sql := pkg_common.func_get_pagebyindex(v_sql,
                                           p_pageindex,
                                           p_pagesize);


  OPEN p_cursor FOR v_sql
  USING pkg_pclc.g_jdbh_pcsp, pkg_pclc.g_hdbh_sp ;

  EXCEPTION
  WHEN OTHERS THEN
  IF p_cursor%ISOPEN
  THEN
    CLOSE p_cursor;
  END IF;
  OPEN p_cursor FOR 'SELECT 1 FROM dual';
  p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
              chr(10) || SQLERRM;
END proc_get_splst;