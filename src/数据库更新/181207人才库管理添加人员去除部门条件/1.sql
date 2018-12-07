-- pkg_pcgl.proc_get_ryinfo
-- 大概位置：638 、664行

--获取人员信息通过单位编码
PROCEDURE proc_get_ryinfo(p_rykdwbm       IN VARCHAR2, --人员库单位编码
p_ywbm          IN VARCHAR2, --业务编码
p_dwbm          IN VARCHAR2, --单位编码‘
p_bmbm          in varchar2, -- 部门
p_jsmc          IN VARCHAR2, --角色名称
p_mc            IN VARCHAR2, --姓名
p_type          IN VARCHAR2, --类型
p_pagesize      IN NUMBER, --页大小
p_pageindex     IN NUMBER, --页索引
p_count         OUT NUMBER, --总数
p_cursor        OUT SYS_REFCURSOR, --返回数据集
p_errmsg        OUT VARCHAR2 --异常信息
) IS
v_sql       CLOB;
v_condition VARCHAR(4000);
BEGIN
  --湖北：增加角色搜索过滤
  IF p_type = '1' --全部人员
  THEN
    v_sql := 'SELECT DISTINCT ROWNUM ID , dw.dwmc, ry.mc, ry.dzyj, ryk.gh rykgh, ry.dwbm, ry.gh, ry.dlbm, ry.yddhhm,
                          ry.gzzh, ry.sflsry, ry.sftz, ry.xb, bm.bmbm, bm.bmmc, js.jsbm, js.jsmc
                     FROM XT_ZZJG_RYBM RY
                     LEFT JOIN xt_qx_ryjsfp@tyyw_link.net qx
                       ON ry.dwbm = qx.dwbm
                      AND ry.gh = qx.gh
                    LEFT JOIN xt_zzjg_bmbm@tyyw_link.net bm
                       ON qx.dwbm = bm.dwbm
                      AND qx.bmbm = bm.bmbm
                    LEFT JOIN xt_qx_jsbm@tyyw_link.net js
                       ON qx.dwbm = js.dwbm
                      AND qx.bmbm = js.bmbm
                      AND qx.jsbm = js.jsbm
                    INNER JOIN xt_zzjg_dwbm DW
                       ON RY.dwbm = DW.dwbm
                     LEFT JOIN xt_pc_ryk ryk
                       ON ryk.dwbm = ry.dwbm
                      AND ryk.gh = ry.gh
                      AND ryk.ryk_dwbm = '''||p_rykdwbm||'''
                      AND ryk.ywbm = '''||p_ywbm||'''
                    WHERE ryk.gh is null
                      AND ry.sfsc = ''N''';

  ELSE --type=2 全库人员
    v_sql := 'SELECT DISTINCT rownum id, dw.dwmc,r.mc,r.dzyj,ry.gh rykgh,ry.dwbm,ry.gh,ry.sfsc,js.jsbm, js.jsmc
                       FROM XT_PC_RYK RY
                      INNER JOIN XT_ZZJG_RYBM R
                         ON RY.DWBM = R.DWBM
                        AND RY.GH = R.GH
                      INNER JOIN XT_ZZJG_DWBM DW
                         ON RY.DWBM = DW.DWBM
                      LEFT JOIN xt_qx_jsbm@tyyw_link.net JS
                         ON RY.dwbm = JS.dwbm
                        AND RY.bmbm = JS.bmbm
                        AND RY.jsbm = JS.jsbm
                      WHERE ry.ryk_dwbm IN (SELECT dwbm FROM xt_zzjg_dwbm START WITH dwbm = '''||p_rykdwbm||''' CONNECT BY PRIOR dwbm = fdwbm)
                        AND ry.ryk_dwbm <> '''||p_rykdwbm||'''
                        AND (ry.dwbm,ry.gh) NOT IN (SELECT dwbm,gh FROM xt_pc_ryk WHERE ryk_dwbm = '''||p_rykdwbm||''')
                        AND ry.ywbm =  '''||p_ywbm||''' ';
  END IF;
  p_count     := 0;
  v_condition := '';
  IF p_dwbm IS NOT NULL
  THEN
    v_condition := v_condition || ' AND ry.dwbm  in (' || p_dwbm || ')';
  END IF;

  IF p_bmbm IS NOT NULL
  THEN
    v_condition := v_condition || ' AND js.bmbm = ''' || p_bmbm || '''';
  END IF;


  IF p_mc IS NOT NULL
  THEN
    v_condition := v_condition || ' AND RY.MC like ''%' || p_mc || '%''';
  END IF;

  IF p_jsmc IS NOT NULL
  THEN
    v_condition := v_condition || ' AND JS.JSMC = ''' || p_jsmc || '''';
  END IF;

  IF v_condition IS NOT NULL
  THEN
    v_sql := v_sql || v_condition;
  END IF;

  EXECUTE IMMEDIATE  '  SELECT count(1)   FROM  (' || v_sql || ')'
  INTO p_count;

  v_sql := pkg_common.func_get_pagebyindex(v_sql, p_pageindex, p_pagesize);
  OPEN p_cursor FOR v_sql;


  EXCEPTION
  WHEN OTHERS THEN
  IF p_cursor%ISOPEN
  THEN
    CLOSE p_cursor;
  END IF;
  OPEN p_cursor FOR 'SELECT sysdate FROM dual';
  p_errmsg := '获取人员信息通过单位编码失败。' || chr(10) || dbms_utility.format_error_backtrace || chr(10) || SQLERRM;
END proc_get_ryinfo;