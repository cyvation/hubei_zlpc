
-- 流转单修改了，使用新模板与xml，同时相应的存储过程也要修改。


-- 存储过程pkg_pcbg.proc_get_pcjbxx 大概 22 行
--获取被评查活动基本信息(随机评查报告基本内容，可复用)
PROCEDURE proc_get_pcjbxx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
p_cursor OUT SYS_REFCURSOR, --返回数据集
p_errmsg OUT VARCHAR2 --异常信息
) IS
v_sql CLOB;
BEGIN
  p_errmsg := '';

  v_sql := 'SELECT jb.pcslbm, jb.pcsah, jb.pcdwbm, jb.pcflbm, jb.pchdbm, jb.bmsah, jb.ajmc, jb.ajlb_bm, jb.ajlb_mc, jb.bpc_dwbm, jb.bpc_dwmc,
                         jb.bpc_bmbm, jb.bpc_bmmc, jb.bpc_gh, jb.bpc_mc, jb.pcjdbh, jb.pcjdms, jb.pcz_bm, jb.pcz_mc, jb.pcr_dwbm, jb.pcr_dwmc,
                         jb.pcr_gh, jb.pcr_mc, jb.pcmbbm, jb.ajglzt, jb.pcjg, jb.pcjl, jb.pcbgbh, jb.sxgzbm,
                         NVL2(jb.cjsj,to_char(jb.cjsj,''yyyy"年"MM"月"dd"日"''),''无'') PCRQ,
                         NVL2(jb.zhxgsj,to_char(jb.zhxgsj,''yyyy"年"MM"月"dd"日"''),''无'') JSRQ,
                         NVL2(jb.bpc_wcbzrq,to_char(jb.bpc_wcbzrq,''yyyy"年"MM"月"dd"日"''),''无'') WCRQ ,dw.dwmc PCDWMC,
                         (jb.pcr_mc || ''    '' || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) PCYLK,
                         (jb.bpc_mc || ''    '' || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) JCGLK
                    FROM yx_pc_jbxx jb
                   INNER JOIN xt_zzjg_dwbm dw
                      ON jb.pcdwbm=dw.dwbm
                     AND jb.sfsc=''N''
                   WHERE jb.pcr_dwbm='''||p_dwbm||'''
                     AND jb.pcr_gh='''||p_gh||'''
                     AND jb.pchdbm='''||p_pchdbm||'''
                     AND jb.pcslbm='''||p_pcslbm||'''';

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
END proc_get_pcjbxx;