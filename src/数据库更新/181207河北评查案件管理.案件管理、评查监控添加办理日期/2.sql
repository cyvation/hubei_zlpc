
--修改内容：评查管理.案件管理添加办理条件查询
--位置：pkg_pcgl.proc_get_pcaj  大概:907 、971行

-- 获取评查案件列表
PROCEDURE proc_get_pcaj(p_pcflbm    IN yx_pc_jbxx.pcflbm%TYPE, --评查分类编码
p_ajmc      IN yx_pc_jbxx.ajmc%TYPE, --案件名称
p_pcrmc     IN yx_pc_jbxx.pcr_mc%TYPE, --评查员
p_cbrmc     IN yx_pc_jbxx.bpc_mc%TYPE, --承办检察官 湖北：新增查询条件180523
p_sfldba    IN VARCHAR, --是否领导办案 湖北：新增查询条件180523
p_dwbm      IN yx_pc_jbxx.pcdwbm%TYPE,--单位编码
p_pcjl      IN VARCHAR2,--评查结论 湖北：新增查询条件180517
p_pczt      IN VARCHAR2,--评查状态 湖北：新增查询条件180517
p_pckssj    IN yx_pc_hd.pckssj%TYPE, -- 评查开始时间
p_pcjssj    IN yx_pc_hd.pcjssj%TYPE, -- 评查结束时间
p_slrq      IN yx_pc_jbxx.bpc_slrq%TYPE, -- 案件受理时间
p_wcrj      IN yx_pc_jbxx.bpc_wcbzrq%TYPE, -- 案件受理结束时间
p_pagesize  IN NUMBER, --页大小
p_pageindex IN NUMBER, --页索引
p_count     OUT NUMBER, --总数
p_cursor    OUT SYS_REFCURSOR, --返回数据集
p_errmsg    OUT VARCHAR2 -- 错误信息
)IS
v_sql CLOB;
/* v_dwbmj CLOB;*/
BEGIN
  p_errmsg := '';

  /*-- 查询该单位及其下级院：
  v_sql := 'SELECT dwbm BM, dwmc MC, fdwbm FBM
              FROM xt_zzjg_dwbm
             START WITH dwbm = :dwbm
                    AND sfsc = ''N''
            CONNECT BY fdwbm = PRIOR dwbm
                   AND sfsc = ''N''';


  EXECUTE IMMEDIATE 'select  wmsys.wm_concat(BM) FROM (' || v_sql || ')'
   INTO v_dwbmj
         USING p_dwbm;
*/

  v_sql := 'SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.fpdr_fpr_dwbm, jb.fpdr_fpr_dwmc,
                             jb.lcslbh, jb.pcjdbh, jb.pcjdms, jb.fpdz_fpr_dwbm, jb.fpdz_fpr_gh, jb.fpdr_fpr_gh, jb.fpdr_fpr_mc,
                             jb.pcz_bm, jb.pcz_mc, jb.pcr_dwbm, jb.pcr_dwmc, jb.pcr_gh, jb.pcr_mc, jb.bpc_dwbm, jb.bpc_dwmc, jb.bpc_mc, jb.cjsj, jb.zhxgsj,
                             jb.sxgzbm, jb.bpc_wcbzrq, jb.fpdz_fpr_dwmc, jb.fpdz_fpr_mc, jb.pcjl, jb.pcjg, jb.sfldba
                        FROM yx_pc_jbxx jb
                       WHERE sfsc=''N''
                         AND jb.pcdwbm =:dwbm';

  -- 评查分类编码
  v_sql := v_sql ||
           pkg_common.func_get_queryinsqlcommon('jb.pcflbm', p_pcflbm);

  /* -- 评查单位：
   v_sql := v_sql ||
            pkg_common.func_get_queryinsql('jb.pcdwbm', v_dwbmj);*/

  -- 案件名称
  v_sql := v_sql ||
           pkg_common.func_get_querylikesql('jb.ajmc', p_ajmc);

  -- 是否领导办案
  v_sql := v_sql ||
           pkg_common.func_get_queryinsql('jb.sfldba', p_sfldba);

  -- 评查员
  v_sql := v_sql ||
           pkg_common.func_get_querylikesql('jb.pcr_mc', p_pcrmc);
  -- 承办检察官
  v_sql := v_sql ||
           pkg_common.func_get_querylikesql('jb.bpc_mc', p_cbrmc);

  -- 评查开始时间
  v_sql := v_sql ||
           pkg_common.func_get_querydatesql('jb.cjsj',
                                            p_pckssj,
                                            p_pcjssj);

  -- 案件受理时间
  v_sql := v_sql ||
           pkg_common.func_get_querydatesql('jb.bpc_wcbzrq',
                                            p_slrq,
                                            p_wcrj);

  --评查结论
  v_sql := v_sql ||
           pkg_common.func_get_queryinsql('jb.pcjl', p_pcjl);

  --评查状态
  v_sql := v_sql ||
           pkg_common.func_get_queryinsql('jb.pcjdbh', p_pczt);
  IF p_pcjl IS NOT NULL
  THEN
    v_sql := v_sql ||
             pkg_common.func_get_queryinsql('jb.pcjdbh', p_pczt);
  ELSE
    v_sql := v_sql ||
             pkg_common.func_get_queryegtsql('jb.pcjdbh', '005');
  END IF;

  -- INSERT INTO test_t(id,tj,sj) VALUES(12565,v_sql,SYSDATE);

  --获取总数
  EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
  INTO p_count
  USING p_dwbm;




  -- 获取数据
  v_sql := pkg_common.func_get_pagebyindex(v_sql,
                                           p_pageindex,
                                           p_pagesize);

  OPEN p_cursor FOR v_sql
  USING p_dwbm;



  EXCEPTION
  WHEN OTHERS THEN
  IF p_cursor%ISOPEN
  THEN
    CLOSE p_cursor;
  END IF;
  OPEN p_cursor FOR 'SELECT sysdate FROM dual';
  p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
              SQLERRM;
END proc_get_pcaj;