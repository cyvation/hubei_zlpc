-- 修改内容：流转单，评查员意见格式：【建议评定为xx案件】， 检察官意见格式：【（xx评查员代填，见附件）】
-- 同时，模板、xml文件也要同步更新

--pkg_pcbg.proc_get_pcjbxx
--位置大概 21 行
create or replace package body PKG_PCBG is

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
                         ''建议评定为'' || jb.pcjl  as  PCYYJ,
                         (jb.pcr_mc || ''    '' || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) PCYLK,
                         (jb.bpc_mc || ''('' || jb.pcr_mc || ''代填,见附件)'' || ''    ''   || to_char(sysdate,''yyyy"年"MM"月"dd"日"'')) JCGLK

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

  --获取评查问题（重点评查报告评查存在的内容）
  PROCEDURE proc_get_pcwtx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                           p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                           p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                           p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                           p_cursor OUT SYS_REFCURSOR, --返回数据集
                           p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT pcslbm, pcxbm, pcxmc, pcjg, pcyj, jlsj, pcrdwbm, pcrdwmc, pcrgh, pcrmc
                    FROM yx_pc_pcx
                   WHERE pcjg <> ''0''
                     AND pcslbm='''||p_pcslbm||'''';

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
    END proc_get_pcwtx;

  --获取评查基本信息（随机评查报告基本信息）
  PROCEDURE proc_get_sjjbxx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                            p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                            p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                            p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                            p_cursor OUT SYS_REFCURSOR, --返回数据集
                            p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT jb.pcdwbm,dw.dwmc,jb.ajmc,jb.ajlb_mc,jb.bpc_bmmc,jb.bpc_mc,jb.pcr_mc,jb.pcjl PCJL,jb.sm BZ,
                         NVL2(jb.cjsj,to_char(jb.cjsj,''yyyy"年"MM"月"dd"日"''),'''') PCRQ
                         ,decode(jb.sfldba,''Y'',''领导'',''非领导'') cbrsf_mc
                    FROM YX_PC_JBXX jb
                   INNER JOIN xt_zzjg_dwbm dw
                      ON jb.pcdwbm=dw.dwbm
                     AND jb.sfsc=''N''
                   WHERE jb.pcslbm='''||p_pcslbm||'''';

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
    END proc_get_sjjbxx;

  --获取评查问题（随机评查报告评查存在的内容）
  PROCEDURE proc_get_sjpcx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                           p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                           p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                           p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                           p_shcema OUT SYS_REFCURSOR, --返回数据集
                           p_cursor OUT SYS_REFCURSOR, --返回数据集
                           p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    v_schema CLOB;
    BEGIN
      p_errmsg := '';
      --湖北：加备注
      v_sql := 'WITH tab_msg AS
                      (SELECT fl.pcslbm, fl.pcxflfbm, replace(to_char(wmsys.wm_concat(fl.pcxflmc || ''：'' || x.xxxx || '' '')), '','', '''') AS MSG
                         FROM yx_pc_pcxfl fl
                        INNER JOIN (
                       SELECT pcx.pcslbm, pcx.pcxflbm, replace(to_char(wmsys.wm_concat(DECODE(pcx.pcjg, ''1'', nvl2(pcx.sm,pcx.pcxmc || ''(备注:'' || pcx.sm || '')；'' ,pcx.pcxmc||''；''), pcx.pcxmc || ''('' || pcx.pcjg || '')；''))), '','', '''') AS XXXX
                         FROM yx_pc_pcx pcx
                        WHERE pcx.pcslbm = '''||p_pcslbm||'''
                          AND ((pcx.pcfs = ''1'' AND pcx.pcjg = ''1'') OR
                              (pcx.pcfs = ''2'' AND pcx.pcjg IS NOT NULL))
                         GROUP BY pcx.pcslbm, pcx.pcxflbm) x
                           ON fl.pcslbm = x.pcslbm
                          AND fl.pcxflbm = x.pcxflbm
                        WHERE fl.pcslbm = '''||p_pcslbm||'''
                        GROUP BY fl.pcslbm, fl.pcxflfbm)
                       SELECT /*translate(rownum, ''1234567890'', ''一二三四五六七八九○'') || ''、''|| ( fl.pcxflmc || ''方面为'' || jg.pcxflmc || ''：'' || xx.msg) MC
                              (fl.pcxflmc || ''方面为'' || jg.pcxflmc || ''：'' || xx.msg) MC*/
                              (translate(rownum, ''1234567890'', ''一二三四五六七八九○'') || ''、''|| fl.pcxflmc || ''方面为'' || jg.pcxflmc) Title,  xx.msg Content
                         FROM yx_pc_pcxfl fl
                        INNER JOIN (SELECT *
                                      FROM yx_pc_pcxfl fl
                                     WHERE fl.pcslbm = '''||p_pcslbm||'''
                                       AND fl.pcjg = ''1'') jg
                           ON fl.pcslbm = jg.pcslbm
                          AND fl.pcxflbm = jg.pcxflfbm
                        INNER JOIN tab_msg xx
                           ON fl.pcslbm = xx.pcslbm
                          AND fl.pcxflbm = xx.pcxflfbm
                        WHERE fl.pcslbm = '''||p_pcslbm||'''
                          AND fl.pcxflfbm IS NULL';

      v_schema := 'SELECT ''Bold'' TitleStyle, ''left'' TitleAlign, ''仿宋'' TitleName, ''16'' TitleSize,
                            ''无'' ContentStyle, ''left'' ContentAlign, ''仿宋'' ContentName, ''16'' ContentSize
                       FROM dual';

      OPEN p_shcema FOR v_schema;
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
    END proc_get_sjpcx;

  --获取被评查活动基本信息(重点评查报告基本内容)
  PROCEDURE proc_get_zd_jbxx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                             p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                             p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                             p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                             p_cursor OUT SYS_REFCURSOR, --返回数据集
                             p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT jb.ajmc,jb.ajlb_mc,jb.pcr_dwmc,jb.pcr_mc,
                         jb.bpc_dwmc, jb.bpc_bmmc, jb.bpc_mc,
                         to_char(SYSDATE,''yyyy"年"MM"月"dd"日"'') DQRQ,--当前日期
                         jb.pcjl,jb.sm BZ,dw.dwmc DWMC,ryk.bmmc PCR_BMMC
                         ,decode(jb.sfldba,''Y'',''领导'',''非领导'') cbrsf_mc
                    FROM yx_pc_jbxx jb
                   INNER JOIN xt_zzjg_dwbm dw
                      ON jb.pcdwbm = dw.dwbm
                     AND jb.sfsc = ''N''
                   INNER JOIN yx_pc_hd hd
                      ON jb.pchdbm = hd.pchdbm
                    LEFT JOIN (SELECT js.dwbm, js.gh, js.bmmc
                                 FROM (SELECT fp.dwbm, fp.gh, fp.ybmmc bmmc
                                         FROM xt_qx_ryjsfp fp
                                        WHERE fp.dwbm = '''||p_dwbm||'''
                                          AND fp.gh = '''||p_gh||'''
                                       UNION
                                       SELECT r.dwbm, r.gh, r.bmmc bmmc
                                         FROM xt_pc_ryk r
                                        WHERE r.dwbm = '''||p_dwbm||'''
                                          AND r.gh = '''||p_gh||''') js
                                WHERE rownum <= 1) ryk
                      ON jb.pcr_dwbm = ryk.dwbm
                     AND jb.pcr_gh=ryk.gh
                   WHERE jb.pcr_dwbm='''||p_dwbm||'''
                     AND jb.pcr_gh='''||p_gh||'''
                    -- AND jb.pchdbm='''||p_pchdbm||'''
                     AND jb.pcslbm='''||p_pcslbm||'''';
      /*INSERT INTO test_t(id, tj, sj) VALUES(3828, v_sql, SYSDATE);
      COMMIT;*/
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
    END proc_get_zd_jbxx;

  --获取被评查活动基本信息(重点评查报告基本内容)
  PROCEDURE proc_get_zd_caxx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                             p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                             p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                             p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                             p_cursor OUT SYS_REFCURSOR, --返回数据集
                             p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT xyr.bmsah, xyr.xyrbh, xyr.xm, xyr.sfzf, xyr.cym, xyr.ch, xyr.xb_mc, xyr.mz_mc, xyr.gj_mc, xyr.csd_mc, xyr.zjlx_mc, xyr.zjhm, xyr.csrq,
                         xyr.NL, xyr.zasnl, xyr.zsd_mc, xyr.zsdxxdz, xyr.hjszd_mc, xyr.gzdw, xyr.gzdwszd_mc, xyr.sfdwld, xyr.sfdrsz, xyr.zj_mc,
                         xyr.zw, xyr.sf_mc, xyr.qtgzsf_mc, xyr.sjyzk_mc, xyr.zzmm_mc, xyr.rddb_mc, xyr.sfwljysrddb, xyr.zxwy_mc, xyr.sfwljyszxwy,
                         xyr.sfncjczzry, ca.CASY_MC
                    FROM tyyw_gg_xyrjbxx@tyyw_link.net xyr
                   INNER JOIN tyyw_ziz_cxajsc@tyyw_link.net ca
                      ON xyr.bmsah=ca.bmsah
                   INNER JOIN yx_pc_jbxx jb
                      ON jb.bmsah=xyr.bmsah
                   WHERE jb.pcr_dwbm='''||p_dwbm||'''
                     AND jb.pcr_gh='''||p_gh||'''
                     --AND jb.pchdbm='''||p_pchdbm||'''
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
    END proc_get_zd_caxx;

  --获取被评查活动基本信息(专项评查报告内容)
  PROCEDURE proc_get_zx_jbxx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                             p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                             p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                             p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                             p_cursor OUT SYS_REFCURSOR, --返回数据集
                             p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT lb.pcflmc,jb.sm BZ
                    FROM yx_pc_jbxx jb
                   INNER JOIN xt_pc_lb lb
                      ON jb.pcflbm=lb.pcflbm
                     AND jb.sfsc=''N''
                     AND lb.pcflbm IN (''003'',''008'')
                   INNER JOIN yx_pc_hd hd
                      ON jb.pchdbm=hd.pchdbm
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
    END proc_get_zx_jbxx;

  --获取专项评查方案基本信息
  PROCEDURE proc_get_zxfa_jbxx(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                               p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                               p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                               p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                               p_cursor OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT hd.pchdmc pchdmc,
                         hd.cjrdwbm,
                         dw.dwmc CJRDW,
                         ryk.bmmc CJRBM,
                         hd.cjrmc CJR,
                         to_char(SYSDATE, ''yyyy"年"MM"月"dd"日"'') DQSJ --当前时间
                    FROM yx_pc_hd hd
                   INNER JOIN xt_zzjg_dwbm dw
                      ON hd.cjrdwbm=dw.dwbm
                    LEFT JOIN (SELECT js.dwbm, js.gh, js.bmmc
                                 FROM (SELECT fp.dwbm, fp.gh, fp.ybmmc bmmc
                                         FROM xt_qx_ryjsfp fp
                                        WHERE fp.dwbm = '''||p_dwbm||'''
                                          AND fp.gh = '''||p_gh||'''
                                       UNION
                                       SELECT r.dwbm, r.gh, r.bmmc bmmc
                                         FROM xt_pc_ryk r
                                        WHERE r.dwbm = '''||p_dwbm||'''
                                          AND r.gh = '''||p_gh||''') js
                                WHERE rownum <= 1) ryk
                      ON hd.cjrdwbm=ryk.dwbm
                     AND hd.cjrgh=ryk.gh
                   WHERE hd.pchdbm='''||p_pchdbm||'''';

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
    END proc_get_zxfa_jbxx;

  --获取专项评查方案--获取组长
  PROCEDURE proc_get_zxfa_pczz(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                               p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                               p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                               p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                               p_cursor OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT to_char(wmsys.wm_concat(DISTINCT bm.mc)) PCZZ
                    FROM yx_pc_xzrydy ry
                   INNER JOIN xt_zzjg_rybm bm
                      ON ry.dwbm = bm.dwbm
                     AND ry.gh = bm.gh
                     AND ry.jsbm = ''102''
                   WHERE ry.pchdbm='''||p_pchdbm||'''';

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
    END proc_get_zxfa_pczz;

  --获取专项评查方案--获取小组成员
  PROCEDURE proc_get_zxfa_pczy(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                               p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                               p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                               p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                               p_cursor OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT to_char(wmsys.wm_concat(DISTINCT dw.dwmc||ryk.bmmc||bm.mc)) PCZY
                    FROM yx_pc_xzrydy ry
                   INNER JOIN xt_zzjg_rybm bm
                      ON ry.dwbm=bm.dwbm
                     AND ry.gh=bm.gh
                     AND ry.jsbm = ''101''
                   INNER JOIN yx_pc_fz fz
                      ON ry.pczbm=fz.pczbm
                   INNER JOIN xt_pc_ryk ryk
                      ON ryk.dwbm=ry.dwbm
                     AND ryk.gh=ry.gh
                   INNER JOIN xt_zzjg_dwbm dw
                      ON ry.dwbm=dw.dwbm
                   WHERE ry.pchdbm='''||p_pchdbm||'''';

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
    END proc_get_zxfa_pczy;

  --获取专项评查方案--获取案件列表
  PROCEDURE proc_get_zxfa_ajlb(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                               p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                               p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                               p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                               p_shcema OUT SYS_REFCURSOR, --返回数据集
                               p_cursor OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    v_schema CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT rownum, jb.bpc_dwmc, jb.ajmc, jb.bpc_mc, jb.pcr_dwmc, jb.pcr_mc
                    FROM yx_pc_jbxx jb
                   WHERE jb.pchdbm='''||p_pchdbm||'''';

      v_schema := 'SELECT ''排序'' Name, 2 Width FROM dual
                     UNION ALL
                     SELECT ''被评查单位'' Name, 3 Width FROM dual
                     UNION ALL
                     SELECT ''被评查案件名称'' Name, 4 Width FROM dual
                     UNION ALL
                     SELECT ''被评查案件承办人'' Name, 3 Width FROM dual
                     UNION ALL
                     SELECT ''评查单位'' Name, 2 Width FROM dual
                     UNION ALL
                     SELECT ''评查人'' Name, 2 Width FROM dual';

      OPEN p_shcema FOR v_schema;
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
    END proc_get_zxfa_ajlb;

  --获取自动评查结果--生成自动评查报告
  PROCEDURE proc_get_zdpc_ajmc(p_dwbm   IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                               p_gh     IN yx_pc_jbxx.bpc_gh%TYPE,
                               p_pchdbm IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                               p_pcslbm IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                               p_cursor OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT DISTINCT jb.ajmc
                    FROM yx_pc_jbxx jb
                   WHERE jb.pcslbm='''||p_pcslbm||'''
                     AND jb.sfsc=''N''';

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
    END proc_get_zdpc_ajmc;

  --获取自动评查结果--生成自动评查报告
  PROCEDURE proc_get_zdpcjg(p_dwbm       IN xt_zzjg_dwbm.dwbm%TYPE, --评查人单位编码
                            p_gh         IN yx_pc_jbxx.bpc_gh%TYPE, --评查人工号
                            p_bmsah      IN yx_pc_jbxx.bmsah%TYPE, --部门受案号
                            p_pchdbm     IN yx_pc_jbxx.pchdbm%TYPE, --评查活动编码
                            p_pcslbm     IN yx_pc_jbxx.pcslbm%TYPE, --评查受理编号
                            p_pcflbm     IN VARCHAR2, --评查分类编码
                            p_zdpc_cxbm  IN VARCHAR2, --自动评查程序编码（IN）
                            p_shcema OUT SYS_REFCURSOR, --返回数据集
                            p_cursor OUT SYS_REFCURSOR, --返回数据集
                            p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql CLOB;
    v_schema CLOB;
    v_record yx_pc_jbxx%ROWTYPE;
    BEGIN
      p_errmsg := '';

      SELECT * INTO v_record FROM yx_pc_jbxx WHERE pcslbm = p_pcslbm;

      pkg_zdpccx.proc_write_zdpcjg(p_pcslbm => v_record.pcslbm,
                                   p_bmsah  => v_record.bmsah,
                                   p_dwbm   => v_record.bpc_dwbm,
                                   p_pcflbm => '000',
                                   p_zdpc_cxbm => p_zdpc_cxbm);

      v_sql := 'SELECT rownum, zd.zdpc_cxmc, zd.zdpcjg, zd.pcly
                    FROM yx_pc_zdpccx zd
                   WHERE zd.pcslbm='''||p_pcslbm||'''';

      v_schema := 'SELECT ''序号'' Name, 2 Width FROM dual
                     UNION ALL
                     SELECT ''质量标准'' Name, 4 Width FROM dual
                     UNION ALL
                     SELECT ''评查结果'' Name, 4 Width FROM dual
                     UNION ALL
                     SELECT ''法律依据'' Name, 4 Width FROM dual';

      /* INSERT INTO test_t(id, tj, sj) VALUES('1', v_sql, SYSDATE);
           COMMIT;*/
      OPEN p_shcema FOR v_schema;
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
    END proc_get_zdpcjg;

end PKG_PCBG;
