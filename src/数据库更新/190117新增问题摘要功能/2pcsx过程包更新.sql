

-- 评查案件概览（含问题数量和问题描述）
PROCEDURE proc_get_pcajgllist(p_dwbm      IN yx_pc_jbxx.pcdwbm%TYPE, -- 传入单位编码
p_cbdwbm    IN yx_pc_jbxx.bpc_dwbm%TYPE, -- 承办单位编码(湖北)
p_bmbm      IN yx_pc_jbxx.bpc_bmbm%TYPE, -- 传入部门编码
p_gh        IN yx_pc_jbxx.pcr_gh%TYPE, -- 传入工号
p_pcdwbm    IN yx_pc_jbxx.pcdwbm%TYPE, -- 评查单位编码
p_pcflbm    IN yx_pc_jbxx.pcflbm%TYPE, -- 评查分类编码
p_sxgzbm    IN yx_pc_jbxx.sxgzbm%TYPE, -- 筛选规则编码
p_pcy       IN yx_pc_jbxx.pcr_mc%TYPE, -- 评查员
p_cbr       IN yx_pc_jbxx.bpc_mc%TYPE, -- 承办人
p_cbrsf     IN VARCHAR2, -- 承办人身份
p_pcjl      IN yx_pc_jbxx.pcjl%TYPE, -- 评查结论
p_pczt      IN yx_pc_jbxx.pcjdbh%TYPE, -- 评查状态
p_ywtx      IN VARCHAR2, -- 业务条线
p_pcxbm     IN VARCHAR2, -- 评查项
p_wcrqbng   IN yx_pc_jbxx.cjsj%TYPE, -- 评查开始时间
p_wcrqend   IN yx_pc_jbxx.cjsj%TYPE, -- 评查结束时间
p_bjrqbng   IN yx_pc_jbxx.bpc_wcbzrq%TYPE, -- 案件办结开始时间
p_bjrqend   IN yx_pc_jbxx.bpc_wcbzrq%TYPE, -- 案件办结结束时间
p_type      IN VARCHAR2, --类型
p_ajmc      IN yx_pc_jbxx.ajmc%TYPE, --案件名称
p_pagesize  IN NUMBER, --页大小
p_pageindex IN NUMBER, --页索引
p_count     OUT NUMBER, --总数
p_cursor    OUT SYS_REFCURSOR, --返回数据集
p_errmsg    OUT VARCHAR2 -- 错误信息
) IS
v_sql      CLOB;
v_conditon CLOB;
v_ywtx_cond CLOB;
v_pcx_condition CLOB;
v_join_type VARCHAR2(80);
BEGIN
  p_errmsg   := '';
  v_conditon := '';
  v_pcx_condition := ' AND x.pcjg != ''0'' AND x.xtdm != ''40293''';
  v_ywtx_cond := '';
  v_join_type := ' LEFT JOIN ';

  --完成日期
  v_conditon := v_conditon || pkg_common.func_get_querydatesql('jb.cjsj', p_wcrqbng, p_wcrqend);

  --  案件办结时间
  v_conditon := v_conditon || pkg_common.func_get_querydatesql('jb.bpc_wcrq', p_bjrqbng, p_bjrqend);

  --评查单位编码
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.pcdwbm', p_pcdwbm);
  --承办单位编码
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.bpc_dwbm', p_cbdwbm);

  --承办部门编码
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.bpc_bmbm', p_bmbm);

  --评查分类编码
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.pcflbm', p_pcflbm);

  --筛选规则编码
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.sxgzbm', p_sxgzbm);

  --评查员
  v_conditon := v_conditon ||
                pkg_common.func_get_querylikesql('jb.pcr_mc', p_pcy);

  --承办人
  v_conditon := v_conditon ||
                pkg_common.func_get_querylikesql('jb.bpc_mc', p_cbr);
  --承办人身份
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.sfldba', p_cbrsf);

  --评查结论
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.pcjl', p_pcjl);

  --评查状态
  v_conditon := v_conditon ||
                pkg_common.func_get_queryinsql('jb.pcjdbh', p_pczt);


  IF p_pcjl IS NOT NULL
  THEN
    v_conditon := v_conditon ||
                  pkg_common.func_get_queryinsql('jb.pcjdbh', p_pczt);
    /* ELSE
       v_conditon := v_conditon ||
                     pkg_common.func_get_queryegtsql('jb.pcjdbh', '005');*/
  END IF;

  --案件名称
  v_conditon := v_conditon ||
                pkg_common.func_get_querylikesql('jb.ajmc', p_ajmc);

  --业务条线
  v_ywtx_cond := ' INNER JOIN xt_pc_mb mb ON mb.pcflbm=jb.pcflbm AND mb.pcmbbm=jb.pcmbbm '
                 || pkg_common.func_get_queryinsql('mb.ywtx', p_ywtx);


  --评查项
  IF p_pcxbm IS NOT NULL THEN
    v_pcx_condition := ' AND x.pcjg != ''0'' AND concat(x.fflxtdm,x.xtdm)  IN (' || p_pcxbm || ') ' ;
    v_join_type := ' INNER JOIN ';
  END IF;

  v_sql := 'WITH pcxtab AS (
                 SELECT row_number() over (partition by x.pcslbm order by pcxbm ) xh,
                        x.pcxbm,
                        x.pcxmc,
                        x.pcjg,
                        x.sm,
                        decode(x.pcjg,''1'',x.sm,x.pcjg) bz,
                        x.flxtdm,
                        x.fflxtdm,
                        x.xtdm,
                        x.pcslbm
                   FROM yx_pc_jbxx jb
                  INNER JOIN yx_pc_pcx x ON x.pcslbm = jb.pcslbm
                              '||v_pcx_condition||'
                   '||v_ywtx_cond||'
                  WHERE jb.sfsc=''N''' || v_conditon||'
                ),
                hztab AS (
                  SELECT p.pcslbm,COUNT(p.pcxbm) wtsl,LISTAGG(p.xh||'':''|| pcxmc
                    ||(CASE  WHEN  p.bz IS NOT NULL AND p.bz<>''其他'' AND p.bz<>''无''  THEN ''(备注:''||p.bz||'')''  ELSE  '''' END)
                        , ''。'') WITHIN GROUP(ORDER BY p.pcslbm) AS wtms
                   FROM pcxtab p
                  GROUP BY p.pcslbm
                 )
              SELECT to_char(nvl(a.wtsl,0)) wtsl,nvl(a.wtms,'' '') wtms,jb.*,decode(jb.sfldba,''Y'',''领导'',''非领导'') sf_mc, lb.pcflmc, gz.gzmc sxgzmc,gz.sslb ywtxmc
               FROM yx_pc_jbxx jb
              INNER JOIN xt_pc_lb lb ON lb.pcflbm=jb.pcflbm
              INNER JOIN xt_pc_sxgz gz ON jb.sxgzbm = gz.gzbm
             '||v_ywtx_cond||'
             '||v_join_type||' hztab a  ON a.pcslbm=jb.pcslbm
              WHERE  jb.sfsc=''N''' || v_conditon||'
                ORDER by nvl(a.wtsl,0) DESC'  ;


  INSERT INTO test_t(id, tj, sj) VALUES('20190116', v_sql, SYSDATE);
  COMMIT;

  --获取总数
  EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
  INTO p_count;

  -- 获取数据
  v_sql := pkg_common.func_get_pagebyindex(v_sql, p_pageindex, p_pagesize);

  OPEN p_cursor FOR v_sql;

  EXCEPTION
  WHEN OTHERS THEN
  IF p_cursor%ISOPEN THEN
    CLOSE p_cursor;
  END IF;
  OPEN p_cursor FOR 'SELECT sysdate FROM dual';
  p_errmsg := dbms_utility.format_error_backtrace || chr(10) || SQLERRM;
END proc_get_pcajgllist;
