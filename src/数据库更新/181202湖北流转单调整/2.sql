-- 位置：pkg_pcbg.proc_get_pcjbxx
--大概修改位置：25、26行
--内容：添加了俩行  评查小组联席会议意见 、评查领导小组办公室会议意见


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
                         lb.pcflmc,
                         NVL2(jb.zhxgsj,to_char(jb.zhxgsj,''yyyy"年"MM"月"dd"日"''),''无'') JSRQ,
                         NVL2(jb.bpc_wcbzrq,to_char(jb.bpc_wcbzrq,''yyyy"年"MM"月"dd"日"''),''无'') WCRQ ,dw.dwmc PCDWMC,
                         ''建议评定为'' || jb.pcjl  as  PCYYJ,
                         (jb.pcr_mc || ''    '' || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) PCYLK,
                         (jb.bpc_mc || ''('' || jb.pcr_mc || ''代填,见附件)'' || ''    ''   || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) JCGLK,
                         (jb.bpc_mc || ''('' || jb.pcr_mc || ''代填)'' || ''    ''   || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) PCGROUPLK,
                         (jb.bpc_mc || ''('' || jb.pcr_mc || ''代填)'' || ''    ''   || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) LDGROUPLK
                    FROM yx_pc_jbxx jb
                    inner join xt_pc_lb lb
                    on jb.pcflbm = lb.pcflbm
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