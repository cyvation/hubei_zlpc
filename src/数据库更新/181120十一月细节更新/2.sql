-- pkg_pcgl.proc_get_ryinfo
-- 大概601、639，添加部门约束

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
                      AND ry.sfsc = ''N''
                      and js.bmbm = '''||p_bmbm||''' ';