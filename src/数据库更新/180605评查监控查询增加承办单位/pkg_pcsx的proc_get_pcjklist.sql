    -- 评查监控 240行
    PROCEDURE proc_get_pcjklist(p_dwbm      IN yx_pc_jbxx.pcdwbm%TYPE, -- 传入单位编码
    p_cbdwbm    IN yx_pc_jbxx.bpc_dwbm%TYPE, -- 传入承办单位编码(湖北)
    p_bmbm      IN yx_pc_jbxx.bpc_bmbm%TYPE, -- 传入部门编码
    p_gh        IN yx_pc_jbxx.pcr_gh%TYPE, -- 传入工号
    p_pcdwbm    IN yx_pc_jbxx.pcdwbm%TYPE, -- 评查单位编码
    p_pcflbm    IN yx_pc_jbxx.pcflbm%TYPE, -- 评查分类编码
    p_sxgzbm    IN yx_pc_jbxx.sxgzbm%TYPE, -- 筛选规则编码
    p_pcy       IN yx_pc_jbxx.pcr_mc%TYPE, -- 评查员
    p_cbr       IN yx_pc_jbxx.bpc_mc%TYPE, -- 承办人
    p_pcjl      IN yx_pc_jbxx.pcjl%TYPE, -- 评查结论
    p_pczt      IN yx_pc_jbxx.pcjdbh%TYPE, -- 评查状态
    p_wcrqbng   IN yx_pc_jbxx.cjsj%TYPE, -- 评查开始时间
    p_wcrqend   IN yx_pc_jbxx.cjsj%TYPE, -- 评查结束时间
    p_type      IN VARCHAR2,--类型
    p_ajmc      IN yx_pc_jbxx.ajmc%TYPE, --案件名称
    p_pagesize  IN NUMBER, --页大小
    p_pageindex IN NUMBER, --页索引
    p_count     OUT NUMBER, --总数
    p_cursor    OUT SYS_REFCURSOR, --返回数据集
    p_errmsg    OUT VARCHAR2 -- 错误信息
    ) ;

    -- 评查监控（不同角色，查看的案件列表不同：评查员/评查组长/承办部门/案管负责人/评查管理员）
    PROCEDURE proc_get_pcjklist(p_dwbm      IN yx_pc_jbxx.pcdwbm%TYPE, -- 传入单位编码
    p_cbdwbm    IN yx_pc_jbxx.bpc_dwbm%TYPE, -- 承办单位编码(湖北)
    p_bmbm      IN yx_pc_jbxx.bpc_bmbm%TYPE, -- 传入部门编码
    p_gh        IN yx_pc_jbxx.pcr_gh%TYPE, -- 传入工号
    p_pcdwbm    IN yx_pc_jbxx.pcdwbm%TYPE, -- 评查单位编码
    p_pcflbm    IN yx_pc_jbxx.pcflbm%TYPE, -- 评查分类编码
    p_sxgzbm    IN yx_pc_jbxx.sxgzbm%TYPE, -- 筛选规则编码
    p_pcy       IN yx_pc_jbxx.pcr_mc%TYPE, -- 评查员
    p_cbr       IN yx_pc_jbxx.bpc_mc%TYPE, -- 承办人
    p_pcjl      IN yx_pc_jbxx.pcjl%TYPE, -- 评查结论
    p_pczt      IN yx_pc_jbxx.pcjdbh%TYPE, -- 评查状态
    p_wcrqbng   IN yx_pc_jbxx.cjsj%TYPE, -- 评查开始时间
    p_wcrqend   IN yx_pc_jbxx.cjsj%TYPE, -- 评查结束时间
    p_type      IN VARCHAR2,--类型
    p_ajmc      IN yx_pc_jbxx.ajmc%TYPE, --案件名称
    p_pagesize  IN NUMBER, --页大小
    p_pageindex IN NUMBER, --页索引
    p_count     OUT NUMBER, --总数
    p_cursor    OUT SYS_REFCURSOR, --返回数据集
    p_errmsg    OUT VARCHAR2 -- 错误信息
    ) IS
    v_sql       CLOB;
    v_conditon  CLOB;
    BEGIN
      p_errmsg := '';
      v_conditon := '';

      --完成日期
      v_conditon := v_conditon || pkg_common.func_get_querydatesql('jb.cjsj', p_wcrqbng, p_wcrqend);

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
      ELSE
        v_conditon := v_conditon ||
                      pkg_common.func_get_queryegtsql('jb.pcjdbh', '005');
      END IF;

      --案件名称
      v_conditon := v_conditon ||
                    pkg_common.func_get_querylikesql('jb.ajmc', p_ajmc);


      v_sql :=' SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm,
                             jb.bpc_dwmc, jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc,
                             jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sfsc, jb.cjsj, jb.zhxgsj PCJSRQ,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.sxr_dwmc, jb.sxr_mc, lb.pcflmc, gz.gzmc sxgzmc
                        FROM yx_pc_jbxx jb
                       INNER JOIN xt_pc_lb lb
                          ON lb.pcflbm=jb.pcflbm
                       INNER JOIN xt_pc_sxgz gz
                          ON jb.sxgzbm = gz.gzbm
                       WHERE jb.sfsc=''N''' || v_conditon;

      IF p_type = '0' --承办人可以查看 自己所承办的案件评查情况
      THEN
        v_sql :='SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm,
                             jb.bpc_dwmc, jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc,
                             jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sfsc, jb.cjsj, jb.zhxgsj PCJSRQ,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.sxr_dwmc, jb.sxr_mc, lb.pcflmc, gz.gzmc sxgzmc
                        FROM yx_pc_jbxx jb
                       INNER JOIN xt_pc_lb lb
                          ON lb.pcflbm=jb.pcflbm
                       INNER JOIN xt_pc_sxgz gz
                          ON jb.sxgzbm = gz.gzbm
                       WHERE jb.sfsc=''N''
                       AND (jb.bpc_dwbm='''||p_dwbm||''' AND jb.bpc_gh='''||p_gh||''')' || v_conditon;
      END IF;

      IF p_type = '1' --评查员仅能看个人评查案件，组长能看评查组案件
      THEN
        v_sql :='SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm,
                             jb.bpc_dwmc, jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc,
                             jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sfsc, jb.cjsj, jb.zhxgsj PCJSRQ,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.sxr_dwmc, jb.sxr_mc, lb.pcflmc, gz.gzmc sxgzmc
                        FROM yx_pc_jbxx jb
                       INNER JOIN xt_pc_lb lb
                          ON lb.pcflbm=jb.pcflbm
                       INNER JOIN xt_pc_sxgz gz
                          ON jb.sxgzbm = gz.gzbm
                       WHERE jb.sfsc=''N''
                         AND (jb.pcr_dwbm='''||p_dwbm||''' AND jb.pcr_gh='''||p_gh||''') ' || v_conditon || '
                      UNION
                      SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm,
                             jb.bpc_dwmc, jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc,
                             jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sfsc, jb.cjsj, jb.zhxgsj PCJSRQ,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.sxr_dwmc, jb.sxr_mc, lb.pcflmc, gz.gzmc sxgzmc
                        FROM yx_pc_jbxx jb
                       INNER JOIN xt_pc_lb lb
                          ON lb.pcflbm=jb.pcflbm
                       INNER JOIN xt_pc_sxgz gz
                          ON jb.sxgzbm = gz.gzbm
                       INNER JOIN yx_pc_xzrydy zz
                          ON zz.pchdbm = jb.pchdbm
                         AND zz.pczbm = jb.pcz_bm
                         AND zz.jsbm=''102''
                       WHERE zz.dwbm='''||p_dwbm||'''
                         AND zz.gh='''||p_gh||'''
                         AND jb.sfsc=''N''' || v_conditon;
      END IF;

      IF p_type = '2' --承办部门能看部门被评查案件
      THEN
        v_sql :=' SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm,
                             jb.bpc_dwmc, jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc,
                             jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sfsc, jb.cjsj, jb.zhxgsj PCJSRQ,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.sxr_dwmc, jb.sxr_mc, lb.pcflmc, gz.gzmc sxgzmc
                        FROM yx_pc_jbxx jb
                       INNER JOIN xt_pc_lb lb
                          ON lb.pcflbm=jb.pcflbm
                       INNER JOIN xt_pc_sxgz gz
                          ON jb.sxgzbm = gz.gzbm
                       INNER JOIN xt_qx_ryjsfp@tyyw_link.net js
                          ON jb.bpc_dwbm=js.dwbm
                         AND jb.bpc_bmbm=js.bmbm
                       WHERE jb.sfsc=''N''
                         AND js.dwbm='''||p_dwbm||'''
                         AND js.gh='''||p_gh||'''' || v_conditon;
      END IF;

      IF p_type = '3' --案管负责人/管理员等可查看所有评查案件
      THEN
        v_sql :=' SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm,
                             jb.bpc_dwmc, jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc,
                             jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sfsc, jb.cjsj, jb.zhxgsj PCJSRQ,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.sxr_dwmc, jb.sxr_mc, lb.pcflmc, gz.gzmc sxgzmc
                        FROM yx_pc_jbxx jb
                       INNER JOIN xt_pc_lb lb
                          ON lb.pcflbm=jb.pcflbm
                       INNER JOIN xt_pc_sxgz gz
                          ON jb.sxgzbm = gz.gzbm
                       WHERE jb.sfsc=''N''' || v_conditon;
      END IF;

      --INSERT INTO test_t(id, tj, sj) VALUES('2017126', v_sql, SYSDATE);
      --COMMIT;

      --获取总数
      EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
      INTO p_count;

      -- 获取数据
      v_sql := pkg_common.func_get_pagebyindex(v_sql,
                                               p_pageindex,
                                               p_pagesize);

      OPEN p_cursor FOR v_sql;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT sysdate FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_pcjklist;