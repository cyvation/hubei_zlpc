--------------------------------------------
-- Export file for user ZLPC              --
-- Created by lei on 2018/10/22, 16:15:03 --
-- .存储过程修改

--位置：pkg_pcsx.proc_get_sjsx 大概 213 、298 、392、407
   --  pkg_pcsx.proc_get_sjsx_advance 大概 1606、1646
--------------------------------------------

spool pkg_pcsx.log

prompt
prompt Creating package PKG_PCSX
prompt =========================
prompt
CREATE OR REPLACE PACKAGE ZLPC.pkg_pcsx IS
  /*
   * 名    称：评查筛选
   * 创 建 者：ZJY
   * 描    述：评查案件筛选相关存储过程
   * 创建日期：2017-10-16
   *
   * 版    本：1.0.17.1016
   * 修改内容：XXXXXX
   * 修改日期：XXXX年XX月XX日
   * 修 改 人：XXX
   *
   * 版    本：1.0.17.1129
   * 修改内容：评查案件筛选列（随机/重点），随机评查案件筛选
   * 修改日期：2017年11月29日
   * 修 改 人：WH
   *
   *
   *版     本： 1.0.17.1203
   *修改内容： 评查案件筛选（已经评查/未评查），添加字段type
   *修改日期： 2017年12月3日
   *修改人： LEIJUN
   *
   **版     本： 1.0.17.1208
   *修改内容： 评查案件筛选（已经评查/未评查），修改，规则编码改为选填
   *修改日期： 2017年12月3日
   *修改人： LEIJUN
   *
   *
  */

  -- 获取筛选规则
  PROCEDURE proc_get_sxgz(p_dwbm   IN xt_pc_sxgz.dwbm%TYPE, -- 单位编码
                          p_pcflbm IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                          p_sslb   IN xt_pc_sxgz.sslb%TYPE, -- 所属类别
                          p_cursor OUT SYS_REFCURSOR, --返回数据集
                          p_errmsg OUT VARCHAR2 -- 错误信息
  );

  -- 获取筛选规则（监控里用）
  PROCEDURE proc_get_sxgz_monitor(p_dwbm   IN xt_pc_sxgz.dwbm%TYPE, -- 单位编码
                                  p_pcflbm IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                                  p_cursor OUT SYS_REFCURSOR, --返回数据集
                                  p_errmsg OUT VARCHAR2 -- 错误信息
  );

  -- 获取身份数据
  PROCEDURE proc_get_identity(p_cursor OUT SYS_REFCURSOR, --返回数据集
                              p_errmsg OUT VARCHAR2);

  -- 获取性别数据
  PROCEDURE proc_get_sex(p_cursor OUT SYS_REFCURSOR, --返回数据集
                         p_errmsg OUT VARCHAR2);

  -- 获取活动绑定的筛选规则列表
  PROCEDURE proc_get_hdsxgz(p_pcdwbm IN yx_pc_hd.pcdwbm%TYPE, -- 单位编码
                            p_pcflbm IN yx_pc_hd.pcflbm%TYPE, -- 评查分类编码
                            p_pchdbm IN yx_pc_hd.pchdbm%TYPE, -- 评查活动编码
                            p_cursor OUT SYS_REFCURSOR, --返回数据集
                            p_errmsg OUT VARCHAR2 -- 错误信息
  );

  -- 随机评查案件筛选（自定义）
  PROCEDURE proc_get_sjsx(p_pcdwbm    IN VARCHAR2, --评查单位编码
                          p_pcflbm    IN VARCHAR2, --评查分类编码
                          p_pchdbm    IN VARCHAR2, --评查活动编码
                          p_gzdwbm    IN VARCHAR2, --筛选规则所属单位编码
                          p_sxgzbm    IN VARCHAR2, --筛选规则编码
                          p_cbdwbm    IN VARCHAR2, --承办单位编码(in)
                          p_cbbmbm    IN VARCHAR2, --承办部门编码(in)
                          p_ajlb      IN VARCHAR2, --案件类别编码集合(in)
                          p_bmsah     IN VARCHAR2, --部门受案号(like)
                          p_ajmc      IN VARCHAR2, --案件名称(like)
                          p_cbrxm     IN VARCHAR2, --承办人姓名(like)
                          p_ay        IN VARCHAR2, --案由
                          p_sfkf      in varchar2, -- 是否交叉查询
                          p_slrqbng   IN DATE, --受理日期开始时间(>=)
                          p_slrqend   IN DATE, --受理日期结束时间(<=)
                          p_bjrqbng   IN DATE, --办结日期开始时间(>=)
                          p_bjrqend   IN DATE, --办结日期结束时间(<=)
                          p_wcrqbng   IN DATE, --完成日期开始时间(>=)
                          p_wcrqend   IN DATE, --完成日期结束时间(<=)
                          p_gdrqbng   IN DATE, --归档日期开始时间(>=)
                          p_gdrqend   IN DATE, --归档日期结束时间(<=)
                          p_gzrqbng   IN DATE, --筛选规则匹配日期开始时间(>=)
                          p_gzrqend   IN DATE, --筛选规则匹配日期结束时间(<=)
                          p_zdycxtj   IN VARCHAR2, --自定义查询条件
                          p_pagesize  IN NUMBER, --页大小
                          p_pageindex IN NUMBER, --页索引
                          p_count     OUT NUMBER, --总数
                          p_cursor    OUT SYS_REFCURSOR, --返回数据集
                          p_errmsg    OUT VARCHAR2);

  -- 获取随机评查案件筛选SQL
  FUNCTION func_get_sjsx(p_pcdwbm  IN VARCHAR2, --评查单位编码
                         p_pcflbm  IN VARCHAR2, --评查分类编码
                         p_pchdbm  IN VARCHAR2, --评查活动编码
                         p_gzdwbm  IN VARCHAR2, --筛选规则所属单位编码
                         p_sxgzbm  IN VARCHAR2, --筛选规则编码
                         p_cbdwbm  IN VARCHAR2, --承办单位编码(in)
                         p_cbbmbm  IN VARCHAR2, --承办部门编码(in)
                         p_ajlb    IN VARCHAR2, --案件类别编码集合(in)
                         p_bmsah   IN VARCHAR2, --部门受案号(like)
                         p_ajmc    IN VARCHAR2, --案件名称(like)
                         p_cbrxm   IN VARCHAR2, --承办人姓名(like)
                         p_ay      IN VARCHAR2, --案由
                         p_sfkf    IN VARCHAR2, -- 是否交叉查询
                         p_slrqbng IN DATE, --受理日期开始时间(>=)
                         p_slrqend IN DATE, --受理日期结束时间(<=)
                         p_bjrqbng IN DATE, --办结日期开始时间(>=)
                         p_bjrqend IN DATE, --办结日期结束时间(<=)
                         p_wcrqbng IN DATE, --完成日期开始时间(>=)
                         p_wcrqend IN DATE, --完成日期结束时间(<=)
                         p_gdrqbng IN DATE, --归档日期开始时间(>=)
                         p_gdrqend IN DATE, --归档日期结束时间(<=)
                         p_gzrqbng IN DATE, --筛选规则匹配日期开始时间(>=)
                         p_gzrqend IN DATE, --筛选规则匹配日期结束时间(<=)
                         p_zdycxtj IN VARCHAR2 --自定义查询条件
  ) RETURN CLOB;

  -- 筛选条件：基本信息表
  FUNCTION func_sxtj_jbxx(p_cbdwbm  IN VARCHAR2, --承办单位编码(in)
                          p_cbbmbm  IN VARCHAR2, --承办部门编码(in)
                          p_ajlb    IN VARCHAR2, --案件类别编码集合(in)
                          p_bmsah   IN VARCHAR2, --部门受案号(like)
                          p_ajmc    IN VARCHAR2, --案件名称(like)
                          p_cbrxm   IN VARCHAR2, --承办人姓名(like)
                          p_ay      IN VARCHAR2, --案由
                          p_sfkf    IN VARCHAR2, -- 是否交叉查询
                          p_slrqbng IN DATE, --受理日期开始时间(>=)
                          p_slrqend IN DATE, --受理日期结束时间(<=)
                          p_bjrqbng IN DATE, --办结日期开始时间(>=)
                          p_bjrqend IN DATE, --办结日期结束时间(<=)
                          p_wcrqbng IN DATE, --完成日期开始时间(>=)
                          p_wcrqend IN DATE, --完成日期结束时间(<=)
                          p_gdrqbng IN DATE, --归档日期开始时间(>=)
                          p_gdrqend IN DATE --归档日期结束时间(<=)
  ) RETURN CLOB;

  --筛选条件：拆案并案
  FUNCTION func_sxtj_caba(p_dwbm    IN VARCHAR2, --单位编码(in)
                          p_wcrqbng IN DATE, --完成日期开始时间(>=)
                          p_wcrqend IN DATE --完成日期结束时间(<=)
  ) RETURN VARCHAR2;

  -- 筛选条件（筛选规则程序）
  FUNCTION func_sxtj_sxgz(p_gzdwbm  IN VARCHAR2, --筛选规则所在单位编码
                          p_pcflbm  IN VARCHAR2, --评查分类编码
                          p_sxgzbm  IN VARCHAR2, --筛选规则编码（IN）
                          p_cbdwbm  IN VARCHAR2, --承办单位编码(IN)
                          p_gzrqbng IN DATE, --筛选规则匹配日期开始时间(>=)
                          p_gzrqend IN DATE --筛选规则匹配日期结束时间(<=)
  )  RETURN CLOB;

  -- 随机案件筛选JOB
  PROCEDURE proc_write_sjsx;

  -- 随机评查案件筛选（部门）
  PROCEDURE proc_get_sjsx_bm(p_pcdwbm      IN VARCHAR2, --评查单位编码
                             p_pcflbm      IN VARCHAR2, --评查分类编码
                             p_pchdbm      IN VARCHAR2, --评查活动编码
                             p_sxgzbm      IN VARCHAR2, --筛选规则编码
                             p_cbdwbm      IN VARCHAR2, --承办单位编码(=)
                             p_slrqbng     IN DATE, --受理日期开始时间(>=)
                             p_slrqend     IN DATE, --受理日期结束时间(<=)
                             p_bjrqbng     IN DATE, --办结日期开始时间(>=)
                             p_bjrqend     IN DATE, --办结日期结束时间(<=)
                             p_wcrqbng     IN DATE, --完成日期开始时间(>=)
                             p_wcrqend     IN DATE, --完成日期结束时间(<=)
                             p_gdrqbng     IN DATE, --归档日期开始时间(>=)
                             p_gdrqend     IN DATE, --归档日期结束时间(<=)
                             p_gzrqbng     IN DATE, --筛选规则匹配日期开始时间(>=)
                             p_gzrqend     IN DATE, --筛选规则匹配日期结束时间(<=)
                             p_zdycxtj     IN VARCHAR2, --自定义查询条件
                             p_cursor_cols OUT SYS_REFCURSOR, --返回数据集(列)
                             p_cursor_rows OUT SYS_REFCURSOR, --返回数据集(行)
                             p_cursor_data OUT SYS_REFCURSOR, --返回数据集(值)
                             p_errmsg      OUT VARCHAR2);

  -- 随机评查案件筛选（检察官）
  PROCEDURE proc_get_sjsx_jcg(p_pcdwbm      IN VARCHAR2, --评查单位编码
                              p_pcflbm      IN VARCHAR2, --评查分类编码
                              p_pchdbm      IN VARCHAR2, --评查活动编码
                              p_sxgzbm      IN VARCHAR2, --筛选规则编码
                              p_cbdwbm      IN VARCHAR2, --承办单位编码(=)
                              p_cbbm        IN VARCHAR2, --承办部门
                              p_cbr         IN VARCHAR2, --承办检察官
                              p_slrqbng     IN DATE, --受理日期开始时间(>=)
                              p_slrqend     IN DATE, --受理日期结束时间(<=)
                              p_bjrqbng     IN DATE, --办结日期开始时间(>=)
                              p_bjrqend     IN DATE, --办结日期结束时间(<=)
                              p_wcrqbng     IN DATE, --完成日期开始时间(>=)
                              p_wcrqend     IN DATE, --完成日期结束时间(<=)
                              p_gdrqbng     IN DATE, --归档日期开始时间(>=)
                              p_gdrqend     IN DATE, --归档日期结束时间(<=)
                              p_gzrqbng     IN DATE, --筛选规则匹配日期开始时间(>=)
                              p_gzrqend     IN DATE, --筛选规则匹配日期结束时间(<=)
                              p_zdycxtj     IN VARCHAR2, --自定义查询条件
                              p_pagesize    IN NUMBER, --页大小
                              p_pageindex   IN NUMBER, --页索引
                              p_count       OUT NUMBER, --总数
                              p_cursor_cols OUT SYS_REFCURSOR, --返回数据集(列)
                              p_cursor_rows OUT SYS_REFCURSOR, --返回数据集(行)
                              p_cursor_data OUT SYS_REFCURSOR, --返回数据集(值)
                              p_errmsg      OUT VARCHAR2);

  -- 重点案件筛选JOB
  PROCEDURE proc_write_zdsx;

  -- 评查案件筛选列（随机/重点）1.部门总表
  PROCEDURE proc_get_bmsxlist(p_dwbm      IN yx_pc_sxjl.dwbm%TYPE, --单位编码
                              p_bmbm      IN yx_pc_sxjl.bmbm%TYPE, -- 部门编码
                              p_gzbm      IN xt_pc_sxgz.gzbm%TYPE, -- 规则编码
                              p_pcflbm    IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                              p_pchdbm    IN yx_pc_jbxx.pchdbm%TYPE, -- 评查活动编码
                              p_gzrqbng   IN DATE, --筛选规则匹配日期开始时间(>=)
                              p_gzrqend   IN DATE, --筛选规则匹配日期结束时间(<=)
                              p_type      IN VARCHAR2, --标识已经评查/未评查(0/1)
                              p_pagesize  IN NUMBER, --页大小
                              p_pageindex IN NUMBER, --页索引
                              p_count     OUT NUMBER, --总数
                              p_cursor    OUT SYS_REFCURSOR, --返回数据集
                              p_errmsg    OUT VARCHAR2 -- 错误信息
  );

  -- 评查案件筛选列（随机/重点）检察官总表
  PROCEDURE proc_get_cbrsxlist(p_dwbm      IN yx_pc_sxjl.dwbm%TYPE, -- 承办人工号单位编码
                               p_cbrgh     IN yx_pc_sxjl.cbrgh%TYPE, -- 承办人工号
                               p_gzbm      IN xt_pc_sxgz.gzbm%TYPE, -- 规则编码
                               p_pcflbm    IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                               p_pchdbm    IN yx_pc_jbxx.pchdbm%TYPE, -- 评查活动编码
                               p_gzrqbng   IN DATE, --筛选规则匹配日期开始时间(>=)
                               p_gzrqend   IN DATE, --筛选规则匹配日期结束时间(<=)
                               p_type      IN VARCHAR, --0/1 (已经评查/未评查)
                               p_pagesize  IN NUMBER, --页大小
                               p_pageindex IN NUMBER, --页索引
                               p_count     OUT NUMBER, --总数
                               p_cursor    OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg    OUT VARCHAR2 -- 错误信息
  ) ;

  -- 评查监控
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


  -- 获取部门编码列表
  PROCEDURE proc_get_bmbm(p_dwbm   IN  xt_zzjg_bmbm.dwbm%TYPE, --单位编码
                          p_pcflbm IN  yx_pc_sxjl.pcflbm%TYPE, --评查分类编码
                          p_cursor OUT SYS_REFCURSOR, --返回数据集
                          p_errmsg OUT VARCHAR2 --异常信息
  );


  -- 获取所有部门编码列表
  PROCEDURE proc_get_allbm(p_dwbm   IN  xt_zzjg_bmbm.dwbm%TYPE, --单位编码
                           p_cursor OUT SYS_REFCURSOR, --返回数据集
                           p_errmsg OUT VARCHAR2 --异常信息
  );

  -- 获取所有模板编码
  PROCEDURE proc_get_mbbmj(p_pcflbm   IN  xt_zzjg_bmbm.dwbm%TYPE, --分类编码
                           p_gzbm     IN VARCHAR2, --规则编码
                           p_cursor   OUT SYS_REFCURSOR, --返回数据集
                           p_errmsg   OUT VARCHAR2 --异常信息
  );
  --随机评查案件筛选（湖北:抽取到本地）
  PROCEDURE proc_get_sjsx_advance(p_pcflbm    IN VARCHAR2, --评查分类编码
                                  p_pcmbbm    IN VARCHAR2, --评查模板编码
                                  p_sxgzbm    IN VARCHAR2, --筛选规则编码
                                  p_cbdwbm    IN VARCHAR2, --承办单位编码(in)
                                  p_cbbmbm    IN VARCHAR2, --承办部门编码(in)
                                  p_bmsah     IN VARCHAR2, --部门受案号(like)
                                  p_ajmc      IN VARCHAR2, --案件名称(like)
                                  p_cbrxm     IN VARCHAR2, --承办人姓名(like)
                                  p_sfkf      in varchar2, -- 是否交叉查询  湖北交叉评查，新增字段，雷军军
                                  p_wcrqbng   IN DATE, --完成日期开始时间(>=)
                                  p_wcrqend   IN DATE, --完成日期结束时间(<=)
                                  p_zdycxtj   IN VARCHAR2, --自定义查询条件
                                  p_pagesize  IN NUMBER, --页大小
                                  p_pageindex IN NUMBER, --页索引
                                  p_count     OUT NUMBER, --总数
                                  p_cursor    OUT SYS_REFCURSOR, --返回数据集
                                  p_errmsg    OUT VARCHAR2);

END pkg_pcsx;
/

prompt
prompt Creating package body PKG_PCSX
prompt ==============================
prompt
CREATE OR REPLACE PACKAGE BODY ZLPC.pkg_pcsx IS

  -- 获取筛选规则列表
  PROCEDURE proc_get_sxgz(p_dwbm   IN xt_pc_sxgz.dwbm%TYPE, -- 单位编码
                          p_pcflbm IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                          p_sslb   IN xt_pc_sxgz.sslb%TYPE, -- 所属类别(例如：刑事、民事的区分等)
                          p_cursor OUT SYS_REFCURSOR, --返回数据集
                          p_errmsg OUT VARCHAR2 -- 错误信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';
      --湖北：添加ywtx字段
      v_sql := 'SELECT gz.gzbm, gz.dwbm, gz.pcflbm, gz.gzmc, gz.sxcx, gz.cxcs, gz.sm, ''-1'' FGZBM
                         ,ywtx
                    FROM xt_pc_sxgz gz
                   WHERE gz.dwbm = :dwbm
                     AND gz.pcflbm = :pcflbm';
      v_sql := v_sql ||
               pkg_common.func_get_queryequalsql('gz.sslb', p_sslb);

      OPEN p_cursor FOR v_sql
      USING p_dwbm, p_pcflbm;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT sysdate FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_sxgz;

  -- 获取筛选规则列表（监控里用）
  /*    PROCEDURE proc_get_sxgz_monitor(p_dwbm   IN xt_pc_sxgz.dwbm%TYPE, -- 单位编码
                                      p_pcflbm IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                                      p_cursor OUT SYS_REFCURSOR, --返回数据集
                                      p_errmsg OUT VARCHAR2 -- 错误信息
                                      ) IS
      v_sql CLOB;
      BEGIN
          p_errmsg := '';

          v_sql := 'SELECT NVL2(gz.sslb, ''('' || gz.sslb || '')'', '''') || gz.gzmc AS gzmc,gz.gzbm,gz.dwbm, gz.pcflbm,
                                                                     gz.sxcx, gz.cxcs, gz.sm, ''-1'' FGZBM
                  FROM xt_pc_sxgz gz
                  WHERE gz.pcflbm = :pcflbm
                    AND gz.dwbm = :dwbm
                  ORDER BY gz.sslb, gz.gzbm';

          OPEN p_cursor FOR v_sql
              USING p_pcflbm,p_dwbm;

      EXCEPTION
          WHEN OTHERS THEN
              IF p_cursor%ISOPEN
              THEN
                  CLOSE p_cursor;
              END IF;
              OPEN p_cursor FOR 'SELECT sysdate FROM dual';
              p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                          SQLERRM;
      END proc_get_sxgz_monitor;
  */
  -- 获取筛选规则列表（监控里用）
  PROCEDURE proc_get_sxgz_monitor(p_dwbm   IN xt_pc_sxgz.dwbm%TYPE, -- 单位编码
                                  p_pcflbm IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                                  p_cursor OUT SYS_REFCURSOR, --返回数据集
                                  p_errmsg OUT VARCHAR2 -- 错误信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      /*v_sql := 'SELECT NVL2(gz.sslb, ''('' || gz.sslb || '')'', '''') || gz.gzmc AS gzmc,gz.gzbm,gz.dwbm, gz.pcflbm,
                                                                 gz.sxcx, gz.cxcs, gz.sm, ''-1'' FGZBM
              FROM xt_pc_sxgz gz
              WHERE gz.dwbm = :dwbm ';*/

      v_sql := 'SELECT decode(gz.sfzdy,''N'',lb.pcflmc||''-''|| gz.gzmc,lb.pcflmc||''-''||gz.sslb||''-''|| gz.gzmc) AS gzmc,
                    gz.gzbm,gz.dwbm, gz.pcflbm, gz.sxcx, gz.cxcs, gz.sm,fgzbm FROM
                   (
                    SELECT gzmc,gzbm,dwbm,pcflbm,sxcx,cxcs,sm,sslb,''-1'' fgzbm,sfzdy from xt_pc_sxgz WHERE fgzbm IS NULL
                    UNION ALL
                    SELECT gzmc,gzbm,dwbm,pcflbm,sxcx,cxcs,sm,sslb,fgzbm,sfzdy from xt_pc_sxgz WHERE fgzbm IS NOT NULL
                   ) gz
                  INNER JOIN xt_pc_lb lb ON lb.pcflbm=gz.pcflbm AND lb.sfqy=''Y''
                  WHERE gz.dwbm = :dwbm ';
      --湖北：没有传pcflbm默认全部规则
      IF  p_pcflbm IS NOT NULL AND length(p_pcflbm)>0 THEN
        v_sql := v_sql||'AND gz.pcflbm = '''||p_pcflbm||''' ';
      END IF;

      /*v_sql := v_sql||' ORDER BY gz.sslb, gz.gzbm';*/
      v_sql := v_sql||' ORDER BY gz.pcflbm,gz.sslb, gz.gzbm';

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
    END proc_get_sxgz_monitor;

  --获取身份数据
  PROCEDURE proc_get_identity(p_cursor OUT SYS_REFCURSOR, --返回数据集
                              p_errmsg OUT VARCHAR2) IS
    v_sql VARCHAR2(2000);
    BEGIN

      v_sql := 'SELECT dm BM, mc MC, fdm FBM
                    FROM xt_dm_fldm' ||
               pkg_default.g_tyyw_link || '
                   WHERE sfsc = ''N''
                     AND lbbm = ''9916''';

      OPEN p_cursor FOR v_sql;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
                  chr(10) || SQLERRM;
    END proc_get_identity;

  --获取性别数据
  PROCEDURE proc_get_sex(p_cursor OUT SYS_REFCURSOR, --返回数据集
                         p_errmsg OUT VARCHAR2) IS
    v_sql VARCHAR2(2000);
    BEGIN

      v_sql := 'SELECT dm BM, mc MC, fdm FBM
                    FROM xt_dm_fldm' ||
               pkg_default.g_tyyw_link || '
                   WHERE sfsc = ''N''
                     AND lbbm = ''9909''';

      OPEN p_cursor FOR v_sql;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
                  chr(10) || SQLERRM;
    END proc_get_sex;

  -- 获取活动绑定的筛选规则列表
  PROCEDURE proc_get_hdsxgz(p_pcdwbm IN yx_pc_hd.pcdwbm%TYPE, -- 单位编码
                            p_pcflbm IN yx_pc_hd.pcflbm%TYPE, -- 评查分类编码
                            p_pchdbm IN yx_pc_hd.pchdbm%TYPE, -- 评查活动编码
                            p_cursor OUT SYS_REFCURSOR, --返回数据集
                            p_errmsg OUT VARCHAR2 -- 错误信息
  ) IS
    v_sql CLOB;
    v_sxgzj yx_pc_hd.sxgzj%TYPE; --筛选规则编码集合
    BEGIN
      p_errmsg := '';

      -- 获取绑定的筛选规则集合
      SELECT MAX(t.sxgzj) INTO v_sxgzj
      FROM yx_pc_hd t
      WHERE t.pchdbm=p_pchdbm;

      -- 获取筛选规则列表(湖北：加是否自定义筛选SFZDY)
      v_sql := 'SELECT gz.gzbm GZBM, gz.gzmc GZMC, ''-1'' FGZBM, SFZDY
                    FROM xt_pc_sxgz gz
                   WHERE gz.dwbm = :dwbm
                     AND gz.pcflbm = :pcflbm';
      v_sql := v_sql || pkg_common.func_get_queryinsql('gz.gzbm', v_sxgzj);
      OPEN p_cursor FOR v_sql
      USING p_pcdwbm, p_pcflbm;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT sysdate FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_hdsxgz;

  -- 随机评查案件筛选（自定义）
  PROCEDURE proc_get_sjsx(p_pcdwbm    IN VARCHAR2, --评查单位编码
                          p_pcflbm    IN VARCHAR2, --评查分类编码
                          p_pchdbm    IN VARCHAR2, --评查活动编码
                          p_gzdwbm    IN VARCHAR2, --筛选规则所属单位编码
                          p_sxgzbm    IN VARCHAR2, --筛选规则编码
                          p_cbdwbm    IN VARCHAR2, --承办单位编码(in)
                          p_cbbmbm    IN VARCHAR2, --承办部门编码(in)
                          p_ajlb      IN VARCHAR2, --案件类别编码集合(in)
                          p_bmsah     IN VARCHAR2, --部门受案号(like)
                          p_ajmc      IN VARCHAR2, --案件名称(like)
                          p_cbrxm     IN VARCHAR2, --承办人姓名(like)
                          p_ay        IN VARCHAR2, --案由
                          p_sfkf      in varchar2, -- 是否交叉查询, 湖北交叉评查，新增字段，雷军军
                          p_slrqbng   IN DATE, --受理日期开始时间(>=)
                          p_slrqend   IN DATE, --受理日期结束时间(<=)
                          p_bjrqbng   IN DATE, --办结日期开始时间(>=)
                          p_bjrqend   IN DATE, --办结日期结束时间(<=)
                          p_wcrqbng   IN DATE, --完成日期开始时间(>=)
                          p_wcrqend   IN DATE, --完成日期结束时间(<=)
                          p_gdrqbng   IN DATE, --归档日期开始时间(>=)
                          p_gdrqend   IN DATE, --归档日期结束时间(<=)
                          p_gzrqbng   IN DATE, --筛选规则匹配日期开始时间(>=)
                          p_gzrqend   IN DATE, --筛选规则匹配日期结束时间(<=)
                          p_zdycxtj   IN VARCHAR2, --自定义查询条件
                          p_pagesize  IN NUMBER, --页大小
                          p_pageindex IN NUMBER, --页索引
                          p_count     OUT NUMBER, --总数
                          p_cursor    OUT SYS_REFCURSOR, --返回数据集
                          p_errmsg    OUT VARCHAR2) IS
    v_sql CLOB;
    BEGIN
      p_count  := 0;
      p_errmsg := '';

      v_sql := pkg_pcsx.func_get_sjsx(p_pcdwbm  => p_pcdwbm,
                                      p_pcflbm  => p_pcflbm,
                                      p_pchdbm  => p_pchdbm,
                                      p_gzdwbm  => p_gzdwbm,
                                      p_sxgzbm  => p_sxgzbm,
                                      p_cbdwbm  => p_cbdwbm,
                                      p_cbbmbm  => p_cbbmbm,
                                      p_ajlb    => p_ajlb,
                                      p_bmsah   => p_bmsah,
                                      p_ajmc    => p_ajmc,
                                      p_cbrxm   => p_cbrxm,
                                      p_ay      => p_ay,
                                      p_sfkf    => p_sfkf, -- 湖北交叉评查，新增字段，雷军军
                                      p_slrqbng => p_slrqbng,
                                      p_slrqend => p_slrqend,
                                      p_bjrqbng => p_bjrqbng,
                                      p_bjrqend => p_bjrqend,
                                      p_wcrqbng => p_wcrqbng,
                                      p_wcrqend => p_wcrqend,
                                      p_gdrqbng => p_gdrqbng,
                                      p_gdrqend => p_gdrqend,
                                      p_gzrqbng => p_gzrqbng,
                                      p_gzrqend => p_gzrqend,
                                      p_zdycxtj => p_zdycxtj);

      -- 按受理日期排序
      v_sql := v_sql || ' ORDER BY aj.slrq DESC ';

      --获取总数
      EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
      INTO p_count;

      -- 获取对应页数据
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
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_sjsx;

  -- 获取随机评查案件筛选SQL
  FUNCTION func_get_sjsx(p_pcdwbm  IN VARCHAR2, --评查单位编码
                         p_pcflbm  IN VARCHAR2, --评查分类编码
                         p_pchdbm  IN VARCHAR2, --**注意：评查模版编码
                         p_gzdwbm  IN VARCHAR2, --筛选规则所属单位编码
                         p_sxgzbm  IN VARCHAR2, --筛选规则编码
                         p_cbdwbm  IN VARCHAR2, --承办单位编码(in)
                         p_cbbmbm  IN VARCHAR2, --承办部门编码(in)
                         p_ajlb    IN VARCHAR2, --案件类别编码集合(in)
                         p_bmsah   IN VARCHAR2, --部门受案号(like)
                         p_ajmc    IN VARCHAR2, --案件名称(like)
                         p_cbrxm   IN VARCHAR2, --承办人姓名(like)
                         p_ay      IN VARCHAR2, --案由
                         p_sfkf      in varchar2, -- 是否交叉查询 湖北交叉评查，新增字段，雷军军
                         p_slrqbng IN DATE, --受理日期开始时间(>=)
                         p_slrqend IN DATE, --受理日期结束时间(<=)
                         p_bjrqbng IN DATE, --办结日期开始时间(>=)
                         p_bjrqend IN DATE, --办结日期结束时间(<=)
                         p_wcrqbng IN DATE, --完成日期开始时间(>=)
                         p_wcrqend IN DATE, --完成日期结束时间(<=)
                         p_gdrqbng IN DATE, --归档日期开始时间(>=)
                         p_gdrqend IN DATE, --归档日期结束时间(<=)
                         p_gzrqbng IN DATE, --筛选规则匹配日期开始时间(>=)
                         p_gzrqend IN DATE, --筛选规则匹配日期结束时间(<=)
                         p_zdycxtj IN VARCHAR2 --自定义查询条件
  ) RETURN CLOB IS
    v_sql CLOB;
    BEGIN
      --湖北：评查筛选返回增加模版编码
      --湖北： 评查筛选添加交叉查询
      v_sql := 'SELECT ROWNUM ID, aj.bmsah BMSAH, aj.ajmc AJMC, aj.ajlb_mc AJLBMC, aj.slrq SLRQ,
                         aj.cbdw_bm DWBM,aj.cbdw_mc DWMC, aj.cbbm_bm CBBMBM,aj.cbbm_mc CBBMMC,
                         aj.cbrgh CBRGH, aj.cbr CBR, aj.ysay_aymc YSAY, aj.wcrq WCRQ,
                         aj.ajlb_bm AJLBBM, xx.sxgzbm SXGZBM, xx.wcbzrq WCBZRQ,''' ||p_pchdbm||''' PCMBBM,
                         jxaj.jsdw,jxaj.jsdwmc
                    FROM (' ||
               func_sxtj_sxgz(p_gzdwbm,
                              p_pcflbm,
                              p_sxgzbm,
                              p_cbdwbm,
                              p_gzrqbng,
                              p_gzrqend) ||
               ') xx
                 INNER JOIN tyyw_gg_ajjbxx' ||
               pkg_default.g_tyyw_link ||
               ' aj
                    ON aj.bmsah = xx.bmsah
                 left join yx_pc_jxpcaj jxaj
                 on aj.bmsah = jxaj.bmsah
                 WHERE aj.sfsc = ''N''';

      -- 1.筛选条件：基本信息表
      v_sql := v_sql || func_sxtj_jbxx(p_cbdwbm  => p_cbdwbm,
                                       p_cbbmbm  => p_cbbmbm,
                                       p_ajlb    => p_ajlb,
                                       p_bmsah   => p_bmsah,
                                       p_ajmc    => p_ajmc,
                                       p_cbrxm   => p_cbrxm,
                                       p_ay      => p_ay,
                                       p_sfkf    => p_sfkf, ---- 湖北交叉评查，新增字段，雷军军
                                       p_slrqbng => p_slrqbng,
                                       p_slrqend => p_slrqend,
                                       p_bjrqbng => p_bjrqbng,
                                       p_bjrqend => p_bjrqend,
                                       p_wcrqbng => p_wcrqbng,
                                       p_wcrqend => p_wcrqend,
                                       p_gdrqbng => p_gdrqbng,
                                       p_gdrqend => p_gdrqend);

      -- 2.筛选条件：已评查过案件不再参与评查
      /*        v_sql := v_sql ||
                       ' AND NOT EXISTS (SELECT 1
                                                    FROM yx_pc_jbxx jb
                                                   WHERE aj.bmsah = jb.bmsah
                                                     AND jb.pcdwbm = ''' ||
                       p_pcdwbm || '''
                                                     AND jb.pcflbm = ''' ||
                       p_pcflbm || '''
                                                     AND jb.sfsc=''N'')';*/
      --湖北：全库唯一
      v_sql := v_sql ||
               ' AND NOT EXISTS (SELECT 1
                                            FROM yx_pc_jbxx jb
                                           WHERE aj.bmsah = jb.bmsah
                                             AND jb.sfsc=''N'')';

      -- 3.是否拆并案(无拆案且无并案) 湖北:注释掉
      /*        v_sql := v_sql || func_sxtj_caba(p_dwbm    => p_cbdwbm,
                                                p_wcrqbng => p_wcrqbng,
                                                p_wcrqend => p_wcrqend);*/

      -- 4.自定义查询条件（高级查询）
      v_sql := v_sql || p_zdycxtj;
      /*INSERT INTO test_t VALUES(1234,v_sql,SYSDATE);
      COMMIT;*/
      RETURN v_sql;

    END func_get_sjsx;

  -- 筛选条件：基本信息表
  FUNCTION func_sxtj_jbxx(p_cbdwbm  IN VARCHAR2, --承办单位编码(in)
                          p_cbbmbm  IN VARCHAR2, --承办部门编码(in)
                          p_ajlb    IN VARCHAR2, --案件类别编码集合(in)
                          p_bmsah   IN VARCHAR2, --部门受案号(like)
                          p_ajmc    IN VARCHAR2, --案件名称(like)
                          p_cbrxm   IN VARCHAR2, --承办人姓名(like)
                          p_ay      IN VARCHAR2, --案由
                          p_sfkf    IN VARCHAR2, -- 是否交叉查询  湖北交叉评查，新增字段，雷军军
                          p_slrqbng IN DATE, --受理日期开始时间(>=)
                          p_slrqend IN DATE, --受理日期结束时间(<=)
                          p_bjrqbng IN DATE, --办结日期开始时间(>=)
                          p_bjrqend IN DATE, --办结日期结束时间(<=)
                          p_wcrqbng IN DATE, --完成日期开始时间(>=)
                          p_wcrqend IN DATE, --完成日期结束时间(<=)
                          p_gdrqbng IN DATE, --归档日期开始时间(>=)
                          p_gdrqend IN DATE --归档日期结束时间(<=)
  ) RETURN CLOB IS
    v_sql CLOB;
    BEGIN
      v_sql := ' ';

      -- 处理案件是来自本身的，还是其他单位开放过来的
      -- 湖北交叉评查，新增字段，雷军军
      if p_sfkf = 'Y'
      then
        v_sql := v_sql || pkg_common.func_get_queryinsql('jxaj.dwbm', p_cbdwbm);
      else
        v_sql := v_sql || pkg_common.func_get_queryinsql('aj.cbdw_bm', p_cbdwbm);
      end if;

      /*  --承办单位编码
        v_sql := v_sql ||
                 pkg_common.func_get_queryinsql('aj.cbdw_bm', p_cbdwbm);*/

      --承办部门编码
      v_sql := v_sql ||
               pkg_common.func_get_queryinsql('aj.cbbm_bm', p_cbbmbm);

      --案件类别
      v_sql := v_sql ||
               pkg_common.func_get_queryinsql('aj.ajlb_bm', p_ajlb);

      --部门受案号
      v_sql := v_sql ||
               pkg_common.func_get_querylikesql('aj.bmsah', p_bmsah);

      --案件名称
      v_sql := v_sql ||
               pkg_common.func_get_querylikesql('aj.ajmc', p_ajmc);

      --承办人
      v_sql := v_sql ||
               pkg_common.func_get_querylikesql('aj.cbr', p_cbrxm);

      --案由
      v_sql := v_sql ||
               pkg_common.func_get_queryinsql('aj.ysay_aydm', p_ay);

      --受理日期
      v_sql := v_sql ||
               pkg_common.func_get_querydatesql('aj.slrq',
                                                p_slrqbng,
                                                p_slrqend);

      --办结日期
      v_sql := v_sql ||
               pkg_common.func_get_querydatesql('aj.bjrq',
                                                p_bjrqbng,
                                                p_bjrqend);

      --完成日期
      v_sql := v_sql ||
               pkg_common.func_get_querydatesql('aj.wcrq',
                                                p_wcrqbng,
                                                p_wcrqend);

      --归档日期
      v_sql := v_sql ||
               pkg_common.func_get_querydatesql('aj.gdrq',
                                                p_gdrqbng,
                                                p_gdrqend);

      RETURN v_sql;

    END func_sxtj_jbxx;

  --筛选条件：拆案并案
  FUNCTION func_sxtj_caba(p_dwbm    IN VARCHAR2, --单位编码(in)
                          p_wcrqbng IN DATE, --完成日期开始时间(>=)
                          p_wcrqend IN DATE --完成日期结束时间(<=)
  ) RETURN VARCHAR2 IS
    BEGIN

      RETURN ' AND NOT EXISTS (SELECT 1
                                   FROM (SELECT bmsah_y bmsah
                                           FROM yx_ag_cajl' || pkg_default.g_tyyw_link || '
                                          WHERE sfsc = ''N''
                                          UNION
                                         SELECT bmsah_b bmsah
                                           FROM yx_ag_bajl' || pkg_default.g_tyyw_link || '
                                          WHERE sfsc = ''N''
                                        ) jb
                                  WHERE aj.bmsah = jb.bmsah) ';
    END func_sxtj_caba;

  -- 筛选条件（筛选规则程序）
  FUNCTION func_sxtj_sxgz(p_gzdwbm  IN VARCHAR2, --筛选规则所在单位编码
                          p_pcflbm  IN VARCHAR2, --评查分类编码
                          p_sxgzbm  IN VARCHAR2, --筛选规则编码（IN）
                          p_cbdwbm  IN VARCHAR2, --承办单位编码(IN)
                          p_gzrqbng IN DATE, --筛选规则匹配日期开始时间(>=)
                          p_gzrqend IN DATE --筛选规则匹配日期结束时间(<=)
  ) RETURN CLOB IS
    v_sql    CLOB;
    v_str    CLOB;
    v_tmp    CLOB;
    v_cursor SYS_REFCURSOR; -- 临时游标
    TYPE record_type IS RECORD(
      id   NUMBER,
      gzbm VARCHAR2(100),
      sxcx VARCHAR2(1000),
      cxcs VARCHAR2(4000));
    v_record record_type;
    BEGIN
      v_sql := ' ';
      v_str := ' ';
      v_tmp := ' ';

      -- 获取筛选规则程序
      v_sql := 'SELECT rownum id, gzbm, sxcx, cxcs
                    FROM xt_pc_sxgz
                   WHERE length(sxcx) > 0';

      IF p_sxgzbm IS NULL
      THEN
        v_sql := v_sql || 'AND (sm IS NULL OR sm <> ''自动筛选时不执行，仅界面自定义查询使用'')';
      END IF;

      -- 筛选规则所属单位编码
      v_sql := v_sql ||
               pkg_common.func_get_queryequalsql('dwbm', p_gzdwbm);
      -- 筛选规则所属分类编码
      v_sql := v_sql ||
               pkg_common.func_get_queryequalsql('pcflbm', p_pcflbm);
      -- 筛选规则编码（IN）
      v_sql := v_sql || pkg_common.func_get_queryinsql('gzbm', p_sxgzbm);

      -- 拼接所有重点筛选规则程序(UNION)
      OPEN v_cursor FOR v_sql;
      LOOP
        FETCH v_cursor
        INTO v_record;
        EXIT WHEN v_cursor%NOTFOUND;

        EXECUTE IMMEDIATE 'SELECT ' || v_record.sxcx ||
                          '(:gzbm, :cbdwbm, :gzrqbng, :gzrqend, :zdycxtj) s FROM DUAL'
        INTO v_tmp
        USING v_record.gzbm, p_cbdwbm, p_gzrqbng, p_gzrqend, v_record.cxcs;

        IF v_record.id = 1
        THEN
          v_str := v_tmp;
        ELSE
          v_str := v_str || chr(10) || ' UNION ' || v_tmp;
        END IF;
      END LOOP;

      IF v_cursor%ISOPEN
      THEN
        CLOSE v_cursor;
      END IF;

      IF v_str IS NULL
      THEN
        v_str := ' ';
      END IF;

      RETURN v_str;

    END func_sxtj_sxgz;

  -- 随机案件筛选JOB
  PROCEDURE proc_write_sjsx IS
    v_sql    CLOB;
    v_str    CLOB;
    p_gzdwbm CHAR(6) DEFAULT '420000';
    v_pcflbm CHAR(3) DEFAULT '001';
    v_count_wsx   NUMBER DEFAULT 0;
    v_count_ysx   NUMBER DEFAULT 0;
    v_curdate DATE;
    v_gzrqbng DATE;
    v_gzrqend DATE;
    BEGIN

      v_curdate := SYSDATE;

      -- 判断是否首次执行Job
      SELECT COUNT(1) INTO v_count_wsx FROM yx_pc_sxjl WHERE pcflbm = v_pcflbm;
      SELECT COUNT(1) INTO v_count_ysx FROM yx_pc_jbxx WHERE pcflbm = v_pcflbm;
      IF v_count_wsx + v_count_ysx <= 0
      THEN
        v_gzrqbng := v_curdate - 530;
      ELSE
        v_gzrqbng := v_curdate - 2;
      END IF;
      v_gzrqend := v_curdate + 1;

      -- 1.清空案件列表
      EXECUTE IMMEDIATE ' DELETE FROM yx_pc_sxjl WHERE pcflbm = :pcflbm AND wcbzrq > :wcbzrq'
      USING v_pcflbm, v_gzrqbng;

      -- 2.遍历本院及下级单位
      /*FOR dw IN (SELECT dwbm, dwmc, fdwbm
                   FROM xt_zzjg_dwbm
                  WHERE dwbm = p_gzdwbm
                 UNION ALL
                 SELECT dwbm, dwmc, fdwbm
                   FROM xt_zzjg_dwbm
                  START WITH fdwbm = p_gzdwbm
                         AND sfsc = 'N'
                 CONNECT BY fdwbm = PRIOR dwbm
                        AND sfsc = 'N')
      LOOP*/

      -- 2.1.获取筛选规则SQL
      v_sql := pkg_pcsx.func_sxtj_sxgz(p_gzdwbm  => p_gzdwbm,
                                       p_pcflbm  => v_pcflbm,
                                       p_sxgzbm  => '',
                                       p_cbdwbm  => '',--dw.dwbm,
                                       p_gzrqbng => v_gzrqbng,
                                       p_gzrqend => v_gzrqend);

      -- 2.2.筛选出单位对应的各规则重点案件
      v_sql := 'INSERT INTO yx_pc_sxjl
                      (pcflbm, dwbm, bmsah, tysah, ajmc, ajlb_bm, ajlb_mc,
                       dwmc, bmbm, bmmc, cbrgh, cbrmc, slrq, wcrq, sm, sxgzbm, wcbzrq)
                      SELECT :pcflbm, x.cbdw_bm, x.bmsah, x.tysah, x.ajmc, x.ajlb_bm, x.ajlb_mc,
                             x.cbdw_mc, x.cbbm_bm, x.cbbm_mc, x.cbrgh, x.cbr, x.slrq, x.wcrq, :sm, a.sxgzbm, a.wcbzrq
                        FROM (SELECT t.bmsah, MAX(t.sxgzbm) SXGZBM, MAX(t.wcbzrq) WCBZRQ
                                FROM (' || v_sql || ') t
                               GROUP BY t.bmsah) a
                       INNER JOIN tyyw_gg_ajjbxx' || pkg_default.g_tyyw_link || ' x
                          ON a.bmsah = x.bmsah
                       WHERE 1 = 1
                         AND NOT EXISTS (SELECT 1
                                           FROM yx_pc_jbxx jb
                                          WHERE a.bmsah = jb.bmsah
                                            AND jb.pcdwbm = x.cbdw_bm
                                            AND jb.pcflbm = ''' || v_pcflbm || '''
                                            AND jb.sfsc=''N'')
                         AND NOT EXISTS (SELECT 1
                                   FROM (SELECT bmsah_y bmsah
                                           FROM yx_ag_cajl' || pkg_default.g_tyyw_link || '
                                          WHERE sfsc = ''N''
                                          UNION
                                         SELECT bmsah_b bmsah
                                           FROM yx_ag_bajl' || pkg_default.g_tyyw_link || '
                                          WHERE sfsc = ''N''
                                        ) jb
                                  WHERE a.bmsah = jb.bmsah)';
      --AND jb.pcdwbm = ''' || dw.dwbm || '''
      --INSERT INTO test_t(id, tj, sj) VALUES('1111102', v_sql, SYSDATE);
      --COMMIT;

      EXECUTE IMMEDIATE v_sql
      USING v_pcflbm, '0';
      --END LOOP;

    END;

  -- 随机评查案件筛选（部门）
  PROCEDURE proc_get_sjsx_bm(p_pcdwbm      IN VARCHAR2, --评查单位编码
                             p_pcflbm      IN VARCHAR2, --评查分类编码
                             p_pchdbm      IN VARCHAR2, --评查活动编码
                             p_sxgzbm      IN VARCHAR2, --筛选规则编码
                             p_cbdwbm      IN VARCHAR2, --承办单位编码(=)
                             p_slrqbng     IN DATE, --受理日期开始时间(>=)
                             p_slrqend     IN DATE, --受理日期结束时间(<=)
                             p_bjrqbng     IN DATE, --办结日期开始时间(>=)
                             p_bjrqend     IN DATE, --办结日期结束时间(<=)
                             p_wcrqbng     IN DATE, --完成日期开始时间(>=)
                             p_wcrqend     IN DATE, --完成日期结束时间(<=)
                             p_gdrqbng     IN DATE, --归档日期开始时间(>=)
                             p_gdrqend     IN DATE, --归档日期结束时间(<=)
                             p_gzrqbng     IN DATE, --筛选规则匹配日期开始时间(>=)
                             p_gzrqend     IN DATE, --筛选规则匹配日期结束时间(<=)
                             p_zdycxtj     IN VARCHAR2, --自定义查询条件
                             p_cursor_cols OUT SYS_REFCURSOR, --返回数据集(列)
                             p_cursor_rows OUT SYS_REFCURSOR, --返回数据集(行)
                             p_cursor_data OUT SYS_REFCURSOR, --返回数据集(值)
                             p_errmsg      OUT VARCHAR2) IS
    v_sql   CLOB;
    v_bms   CLOB;
    v_sxgzj yx_pc_hd.sxgzj%TYPE; --筛选规则编码集合
    v_sql_jl CLOB DEFAULT '';
    v_sql_jb CLOB DEFAULT '';
    BEGIN
      p_errmsg := '';

      IF TRUE --获取筛选规则列表，作为列
      THEN

        -- 获取绑定的筛选规则集合
        SELECT MAX(t.sxgzj) INTO v_sxgzj
        FROM yx_pc_hd t
        WHERE t.pchdbm=p_pchdbm;

        -- 获取筛选规则列表
        v_sql := 'SELECT gz.gzbm GZBM, gz.gzmc GZMC
                    FROM xt_pc_sxgz gz
                   WHERE gz.pcflbm = :pcflbm';
        v_sql := v_sql || pkg_common.func_get_queryinsql('gz.gzbm', v_sxgzj);


        -- INSERT INTO test_t(id,tj,sj)VALUES(0123,v_sql,SYSDATE);

        OPEN p_cursor_cols FOR v_sql
        USING p_pcflbm;
      END IF;

      IF TRUE --获取承办部门列表，作为行
      THEN

        -- 筛选记录表基本查询条件
        v_sql_jl := v_sql_jl || pkg_common.func_get_queryinsql('jl.sxgzbm', v_sxgzj);
        v_sql_jl := v_sql_jl || pkg_common.func_get_querydatesql('jl.wcbzrq', p_gzrqbng, p_gzrqend);
        -- 基本信息表基本查询条件
        v_sql_jb := v_sql_jb || pkg_common.func_get_queryinsql('jb.sxgzbm', v_sxgzj);
        v_sql_jb := v_sql_jb || pkg_common.func_get_querydatesql('jb.bpc_wcbzrq', p_gzrqbng, p_gzrqend);
        -- 获取部门及规则对应的未评查及已评查数值,要求查出一级部门
        v_sql := 'SELECT DISTINCT bmt.bmbm,bmbm.bmmc
                    FROM (SELECT DISTINCT decode(bmbm.fbmbm,NULL,bmbm.bmbm,bmbm.fbmbm)BMBM,bmbm.dwbm
                            FROM xt_zzjg_bmbm@tyyw_link.net bmbm
                           INNER JOIN (SELECT jl.bmbm BMBM, jl.bmmc BMMC,jl.dwbm
                                         FROM yx_pc_sxjl jl
                                        WHERE jl.pcflbm = :pcflbm
                                          AND jl.dwbm = :cbdwbm
                                          AND jl.bmbm IS NOT NULL ' || v_sql_jl ||'
                                        UNION
                                       SELECT  jb.bpc_bmbm BMBM, jb.bpc_bmmc BMMC,jb.bpc_dwbm
                                         FROM  yx_pc_jbxx jb
                                        WHERE  jb.pcflbm = :pcflbm
                                          AND  jb.sfsc = ''N''
                                          AND  jb.bpc_dwbm = :cbdwbm
                                          AND  jb.bpc_bmbm IS NOT NULL ' || v_sql_jb ||') bm
                              ON bmbm.bmbm = bm.bmbm
                             AND bmbm.sfsc =''N''
                             AND bmbm.dwbm =bm.dwbm) bmt
                    INNER JOIN xt_zzjg_bmbm@tyyw_link.net bmbm
                       ON bmbm.dwbm =bmt.dwbm
                      AND bmbm.bmbm = bmt.bmbm
                      AND bmbm.sfsc = ''N''';


        --INSERT INTO test_t(id, tj, sj) VALUES(1210, v_sql, SYSDATE);
        -- COMMIT;
        OPEN p_cursor_rows FOR v_sql
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

        --获取人员列表
        EXECUTE IMMEDIATE 'SELECT wmsys.wm_concat(bmbm) FROM (' || v_sql || ')'
        INTO v_bms
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

        IF v_bms IS NULL
        THEN
          v_bms := '''''';
        END IF;

      END IF;

      IF TRUE --获取部门及规则对应的未评查及已评查数值，作为值
      THEN

        -- 获取部门及规则对应的未评查及已评查数值
        v_sql := 'WITH tab_data AS
                  (SELECT hz.bmbm BMBM, hz.gzbm GZBM, SUM(decode(hz.pczt, ''0'', 1, 0)) WPC , SUM(decode(hz.pczt, ''1'', 1, 0)) YPC, COUNT(1) ZS
                    FROM
                    (SELECT bmt.bmsah,decode(bm.fbmbm,NULL,bm.bmbm,bm.fbmbm) BMBM,bmt.gzbm,pczt FROM xt_zzjg_bmbm@tyyw_link.net bm
              INNER JOIN
                    (SELECT jl.bmsah bmsah, jl.bmbm bmbm, jl.sxgzbm gzbm, ''0'' pczt,jl.dwbm
                            FROM yx_pc_sxjl jl
                           WHERE jl.pcflbm = :pcflbm
                             AND jl.dwbm = :cbdwbm
                             AND jl.bmbm IN (' || v_bms ||') ' || v_sql_jl ||'
                          UNION
                          SELECT jb.bmsah bmsah, jb.bpc_bmbm bmbm, jb.sxgzbm gzbm, ''1'' pczt,jb.bpc_dwbm
                            FROM yx_pc_jbxx jb
                           WHERE jb.pcflbm = :pcflbm
                             AND jb.sfsc =''N''
                             AND jb.bpc_dwbm = :cbdwbm
                             AND jb.bpc_bmbm IN (' || v_bms ||')' || v_sql_jb ||')bmt
                              ON bm.bmbm = bmt.bmbm
                             AND bm.dwbm = bmt.dwbm
                             AND bm.sfsc = ''N''
                             ) hz
                   GROUP BY hz.bmbm, hz.gzbm)
                   SELECT tj.bmbm, ''PCZS'' GZBM, SUM(tj.wpc) WPC, SUM(tj.ypc) YPC, SUM(tj.zs) ZS
                     FROM tab_data tj
                    GROUP BY tj.bmbm
                    UNION
                   SELECT dt.bmbm, dt.gzbm GZBM, dt.wpc WPC, dt.ypc YPC, dt.zs ZS
                     FROM tab_data dt';

        --INSERT INTO test_t(id, tj, sj) VALUES('12101', v_sql, SYSDATE);
        --COMMIT;

        OPEN p_cursor_data FOR v_sql
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

      END IF;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor_cols%ISOPEN
      THEN
        CLOSE p_cursor_cols;
      END IF;
      IF p_cursor_rows%ISOPEN
      THEN
        CLOSE p_cursor_rows;
      END IF;
      IF p_cursor_data%ISOPEN
      THEN
        CLOSE p_cursor_data;
      END IF;
      OPEN p_cursor_cols FOR 'SELECT sysdate FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_sjsx_bm;

  -- 随机评查案件筛选（检察官）
  PROCEDURE proc_get_sjsx_jcg(p_pcdwbm      IN VARCHAR2, --评查单位编码
                              p_pcflbm      IN VARCHAR2, --评查分类编码
                              p_pchdbm      IN VARCHAR2, --评查活动编码
                              p_sxgzbm      IN VARCHAR2, --筛选规则编码
                              p_cbdwbm      IN VARCHAR2, --承办单位编码(=)
                              p_cbbm        IN VARCHAR2, --承办部门
                              p_cbr         IN VARCHAR2, --承办检察官
                              p_slrqbng     IN DATE, --受理日期开始时间(>=)
                              p_slrqend     IN DATE, --受理日期结束时间(<=)
                              p_bjrqbng     IN DATE, --办结日期开始时间(>=)
                              p_bjrqend     IN DATE, --办结日期结束时间(<=)
                              p_wcrqbng     IN DATE, --完成日期开始时间(>=)
                              p_wcrqend     IN DATE, --完成日期结束时间(<=)
                              p_gdrqbng     IN DATE, --归档日期开始时间(>=)
                              p_gdrqend     IN DATE, --归档日期结束时间(<=)
                              p_gzrqbng     IN DATE, --筛选规则匹配日期开始时间(>=)
                              p_gzrqend     IN DATE, --筛选规则匹配日期结束时间(<=)
                              p_zdycxtj     IN VARCHAR2, --自定义查询条件
                              p_pagesize    IN NUMBER, --页大小
                              p_pageindex   IN NUMBER, --页索引
                              p_count       OUT NUMBER, --总数
                              p_cursor_cols OUT SYS_REFCURSOR, --返回数据集(列)
                              p_cursor_rows OUT SYS_REFCURSOR, --返回数据集(行)
                              p_cursor_data OUT SYS_REFCURSOR, --返回数据集(值)
                              p_errmsg      OUT VARCHAR2) IS
    v_sql    CLOB;
    v_sql_ry CLOB;
    v_ghs    CLOB;
    v_sxgzj yx_pc_hd.sxgzj%TYPE; --筛选规则编码集合
    v_sql_jl CLOB DEFAULT '';
    v_sql_jb CLOB DEFAULT '';
    BEGIN
      p_errmsg := '';

      IF TRUE --获取筛选规则列表，作为列
      THEN

        -- 获取绑定的筛选规则集合
        SELECT MAX(t.sxgzj) INTO v_sxgzj
        FROM yx_pc_hd t
        WHERE t.pchdbm=p_pchdbm;

        -- 获取筛选规则列表
        v_sql := 'SELECT gz.gzbm GZBM, gz.gzmc GZMC
                    FROM xt_pc_sxgz gz
                   WHERE gz.pcflbm = :pcflbm';
        v_sql := v_sql || pkg_common.func_get_queryinsql('gz.gzbm', v_sxgzj);
        OPEN p_cursor_cols FOR v_sql
        USING p_pcflbm;

      END IF;

      IF TRUE --获取承办部门列表，作为行
      THEN

        -- 筛选记录表基本查询条件
        v_sql_jl := v_sql_jl || pkg_common.func_get_queryinsql('jl.sxgzbm', v_sxgzj);
        v_sql_jl := v_sql_jl || pkg_common.func_get_queryinsqlcommon('jl.bmbm', p_cbbm);
        v_sql_jl := v_sql_jl || pkg_common.func_get_querylikesql('jl.cbrmc', p_cbr);
        v_sql_jl := v_sql_jl || pkg_common.func_get_querydatesql('jl.wcbzrq', p_gzrqbng, p_gzrqend);
        -- 筛选记录表基本查询条件
        v_sql_jb := v_sql_jb || pkg_common.func_get_queryinsql('jb.sxgzbm', v_sxgzj);
        v_sql_jb := v_sql_jb || pkg_common.func_get_queryinsqlcommon('jb.bpc_bmbm', p_cbbm);
        v_sql_jb := v_sql_jb || pkg_common.func_get_querylikesql('jb.bpc_mc', p_cbr);
        v_sql_jb := v_sql_jb || pkg_common.func_get_querydatesql('jb.bpc_wcbzrq', p_gzrqbng, p_gzrqend);
        -- 获取检察官及规则对应的未评查及已评查数值
        v_sql := 'SELECT ry.gh GH, ry.mc MC
                   FROM (SELECT t.gh GH, MAX(t.mc) MC, SUM(decode(t.pczt, ''1'', 1, 0)) / (SUM(decode(t.pczt, ''0'', 1, 0)) + SUM(decode(t.pczt, ''1'', 1, 0))) PCL
                          FROM (SELECT jl.cbrgh GH, jl.cbrmc MC, jl.bmbm BMBM, jl.bmsah, ''0'' pczt
                                  FROM yx_pc_sxjl jl
                                 WHERE jl.pcflbm = :pcflbm
                                   AND jl.dwbm = :cbdwbm
                                   AND jl.cbrgh IS NOT NULL ' || v_sql_jl ||'
                                 UNION
                                SELECT jb.bpc_gh GH, jb.bpc_mc MC, jb.bpc_bmbm BMBM, jb.bmsah, ''1'' pczt
                                  FROM yx_pc_jbxx jb
                                 WHERE jb.pcflbm = :pcflbm
                                   AND jb.sfsc = ''N''
                                   AND jb.bpc_dwbm = :cbdwbm
                                   AND jb.bpc_gh IS NOT NULL ' || v_sql_jb ||'
                                ) t
                          GROUP BY t.gh) ry
                   ORDER BY ry.pcl' ;

        --获取总数
        EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
        INTO p_count
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

        -- 获取对应页数据
        v_sql_ry := pkg_common.func_get_pagebyindex(v_sql,
                                                    p_pageindex,
                                                    p_pagesize);

        OPEN p_cursor_rows FOR v_sql_ry
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

        --获取人员列表
        EXECUTE IMMEDIATE 'SELECT wmsys.wm_concat(gh) FROM (' || v_sql_ry || ')'
        INTO v_ghs
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

        IF v_ghs IS NULL
        THEN
          v_ghs := '''''';
        END IF;

      END IF;

      IF TRUE --获取检察官及规则对应的未评查及已评查数值，作为值
      THEN

        -- 获取检察官及规则对应的未评查及已评查数值
        v_sql := 'WITH tab_data AS
                  (SELECT hz.gh GH, hz.gzbm GZBM, SUM(decode(hz.pczt, ''0'', 1, 0)) WPC, SUM(decode(hz.pczt, ''1'', 1, 0)) YPC, COUNT(1) ZS
                    FROM (SELECT jl.bmsah bmsah, jl.cbrgh gh, jl.sxgzbm gzbm, ''0'' pczt
                            FROM yx_pc_sxjl jl
                           WHERE jl.pcflbm = :pcflbm
                             AND jl.dwbm = :cbdwbm
                             AND jl.cbrgh IN (' || v_ghs ||')' || v_sql_jl ||'
                          UNION
                          SELECT jb.bmsah bmsah, jb.bpc_gh gh, jb.sxgzbm gzbm, ''1'' pczt
                            FROM yx_pc_jbxx jb
                           WHERE jb.pcflbm = :pcflbm
                             AND jb.bpc_dwbm = :cbdwbm
                             AND jb.sfsc = ''N''
                             AND jb.bpc_gh IN (' || v_ghs ||')' || v_sql_jb ||'
                          ) hz
                   GROUP BY hz.gh, hz.gzbm)
                   SELECT tj.gh GH, ''PCZS'' GZBM, SUM(tj.wpc) WPC, SUM(tj.ypc) YPC, SUM(tj.zs) ZS
                     FROM tab_data tj
                    GROUP BY tj.gh
                    UNION
                   SELECT dt.gh GH, dt.gzbm GZBM, dt.wpc WPC, dt.ypc YPC, dt.zs ZS
                     FROM tab_data dt';

        OPEN p_cursor_data FOR v_sql
        USING p_pcflbm, p_cbdwbm, p_pcflbm, p_cbdwbm;

      END IF;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor_cols%ISOPEN
      THEN
        CLOSE p_cursor_cols;
      END IF;
      IF p_cursor_rows%ISOPEN
      THEN
        CLOSE p_cursor_rows;
      END IF;
      IF p_cursor_data%ISOPEN
      THEN
        CLOSE p_cursor_data;
      END IF;
      OPEN p_cursor_cols FOR 'SELECT sysdate FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_sjsx_jcg;

  -- 重点案件筛选JOB
  PROCEDURE proc_write_zdsx IS
    v_sql    CLOB;
    v_str    CLOB;
    p_gzdwbm CHAR(6) DEFAULT '420000';
    v_pcflbm CHAR(3) DEFAULT '002';
    v_count_wsx   NUMBER DEFAULT 0;
    v_count_ysx   NUMBER DEFAULT 0;
    v_curdate DATE;
    v_gzrqbng DATE;
    v_gzrqend DATE;
    BEGIN

      v_curdate := SYSDATE;

      -- 判断是否首次执行Job
      SELECT COUNT(1) INTO v_count_wsx FROM yx_pc_sxjl WHERE pcflbm = v_pcflbm;
      SELECT COUNT(1) INTO v_count_ysx FROM yx_pc_jbxx WHERE pcflbm = v_pcflbm;
      IF v_count_wsx + v_count_ysx <= 0
      THEN
        v_gzrqbng := v_curdate - 730;
      ELSE
        v_gzrqbng := v_curdate - 2;
      END IF;
      v_gzrqend := v_curdate + 1;

      -- 1.清空案件列表
      EXECUTE IMMEDIATE ' DELETE FROM yx_pc_sxjl WHERE pcflbm = :pcflbm AND wcbzrq > :wcbzrq'
      USING v_pcflbm, v_gzrqbng;

      /*-- 2.遍历本院及下级单位
      FOR dw IN (SELECT dwbm, dwmc, fdwbm
                   FROM xt_zzjg_dwbm
                  WHERE dwbm = p_gzdwbm
                 UNION ALL
                 SELECT dwbm, dwmc, fdwbm
                   FROM xt_zzjg_dwbm
                  START WITH fdwbm = p_gzdwbm
                         AND sfsc = 'N'
                 CONNECT BY fdwbm = PRIOR dwbm
                        AND sfsc = 'N')
      LOOP*/

      -- 2.1.获取筛选规则SQL
      v_sql := pkg_pcsx.func_sxtj_sxgz(p_gzdwbm  => p_gzdwbm,
                                       p_pcflbm  => v_pcflbm,
                                       p_sxgzbm  => '',
                                       p_cbdwbm  => '',--dw.dwbm,
                                       p_gzrqbng => v_gzrqbng,
                                       p_gzrqend => v_gzrqend);

      -- 2.2.筛选出单位对应的各规则重点案件
      v_sql := 'INSERT INTO yx_pc_sxjl
                      (pcflbm, dwbm, bmsah, tysah, ajmc, ajlb_bm, ajlb_mc,
                       dwmc, bmbm, bmmc, cbrgh, cbrmc, slrq, wcrq, sm, sxgzbm, wcbzrq)
                      SELECT :pcflbm, x.cbdw_bm, x.bmsah, x.tysah, x.ajmc, x.ajlb_bm, x.ajlb_mc,
                             x.cbdw_mc, x.cbbm_bm, x.cbbm_mc, x.cbrgh, x.cbr, x.slrq, x.wcrq, :sm, a.sxgzbm, a.wcbzrq
                        FROM (SELECT t.bmsah, MAX(t.sxgzbm) SXGZBM, MAX(t.wcbzrq) WCBZRQ
                                FROM (' || v_sql || ') t
                               GROUP BY t.bmsah) a
                       INNER JOIN tyyw_gg_ajjbxx' || pkg_default.g_tyyw_link || ' x
                          ON a.bmsah = x.bmsah
                       WHERE 1 = 1
                         AND NOT EXISTS (SELECT 1
                                           FROM yx_pc_jbxx jb
                                          WHERE a.bmsah = jb.bmsah
                                            AND jb.pcdwbm = x.cbdw_bm

                                            AND jb.pcflbm = ''' || v_pcflbm || '''
                                            AND jb.sfsc=''N'')
                         AND NOT EXISTS (SELECT 1
                                   FROM (SELECT bmsah_y bmsah
                                           FROM yx_ag_cajl' || pkg_default.g_tyyw_link || '
                                          WHERE sfsc = ''N''
                                          UNION
                                         SELECT bmsah_b bmsah
                                           FROM yx_ag_bajl' || pkg_default.g_tyyw_link || '
                                          WHERE sfsc = ''N''
                                        ) jb
                                  WHERE a.bmsah = jb.bmsah)';

      --AND jb.pcdwbm = ''' || dw.dwbm || '''
      /*  INSERT INTO test_t(id, tj, sj) VALUES('1111102', v_sql, SYSDATE);
        COMMIT;*/

      EXECUTE IMMEDIATE v_sql
      USING v_pcflbm, '0';
      --END LOOP;

    END;

  -- 评查案件筛选列（随机/重点）1.部门总表
  PROCEDURE proc_get_bmsxlist(p_dwbm      IN yx_pc_sxjl.dwbm%TYPE, --单位编码
                              p_bmbm      IN yx_pc_sxjl.bmbm%TYPE, -- 部门编码
                              p_gzbm      IN xt_pc_sxgz.gzbm%TYPE, -- 规则编码
                              p_pcflbm    IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                              p_pchdbm    IN yx_pc_jbxx.pchdbm%TYPE, -- 评查活动编码
                              p_gzrqbng   IN DATE, --筛选规则匹配日期开始时间(>=)
                              p_gzrqend   IN DATE, --筛选规则匹配日期结束时间(<=)
                              p_type      IN VARCHAR2, --标识已经评查/未评查(0/1)
                              p_pagesize  IN NUMBER, --页大小
                              p_pageindex IN NUMBER, --页索引
                              p_count     OUT NUMBER, --总数
                              p_cursor    OUT SYS_REFCURSOR, --返回数据集
                              p_errmsg    OUT VARCHAR2 -- 错误信息
  )IS
    v_sql CLOB;
    v_bmbmj VARCHAR2(200);
    BEGIN
      p_errmsg := '';

      --查出本部门及下级部门
      v_sql:='SELECT bm.bmbm
                  FROM xt_zzjg_bmbm@tyyw_link.net bm
                 WHERE bm.dwbm =:dwbm
                   AND (bm.fbmbm =:bmbm OR bm.bmbm=:bmbm)
                   AND bm.sfsc=''N''';


      EXECUTE IMMEDIATE 'select  wmsys.wm_concat(bmbm) FROM (' || v_sql || ')'
      INTO v_bmbmj
      USING p_dwbm,p_bmbm,p_bmbm;



      IF p_type='0'--已评查

      THEN

        v_sql:='SELECT jb.pcslbm,jb.pcflbm,jb.pcdwbm,jb.bmsah,jb.tysah,jb.ajmc,jb.bpc_dwbm,jb.ajlb_mc,
                           jb.bpc_dwmc DWMC,jb.bpc_gh,jb.bpc_mc CBRMC,jb.pcr_dwbm,jb.pcr_dwmc,
                           jb.pcr_gh,jb.pcr_mc,jb.cjsj PCRQ,jb.zhxgsj WCBZRQ
                      FROM yx_pc_jbxx jb
                     INNER JOIN xt_pc_sxgz sx
                        ON jb.sxgzbm=sx.gzbm
                     WHERE jb.sfsc =''N''
                       AND jb.pcjdbh>=''005''
                       AND jb.bpc_dwbm='''||p_dwbm||'''
                       --AND jb.sxgzbm='''||p_gzbm||'''
                       AND jb.pcflbm='''||p_pcflbm||'''';
        -- AND jb.pchdbm='''||p_pchdbm||''''

        -- 筛选完成标志日期
        v_sql := v_sql ||pkg_common.func_get_querydatesql('jb.bpc_wcbzrq' ,p_gzrqbng,p_gzrqend );

        -- 筛选规则(未传则查询该部门下所有)
        v_sql :=v_sql ||pkg_common.func_get_queryinsql('jb.sxgzbm',p_gzbm);

        --承办部门
        -- v_sql :=v_sql ||pkg_common.func_get_queryinsql('jb.bpc_bmbm',p_bmbm);
        v_sql :=v_sql ||pkg_common.func_get_queryinsql('jb.bpc_bmbm',v_bmbmj);

        --评查活动编码
        v_sql := v_sql ||
                 pkg_common. func_get_querylikesql ('jb.pchdbm',p_pchdbm);


        v_sql := v_sql || ' ORDER BY jb.zhxgsj desc';

      ELSE --未评查
        v_sql := 'SELECT gz.gzbm,gz.gzmc,sx.pcflbm, sx.dwbm, sx.bmsah, sx.tysah, sx.ajmc, sx.ajlb_bm, sx.ajlb_mc,
                             sx.dwmc, sx.bmbm, sx.bmmc, sx.cbrgh, sx.cbrmc, sx.slrq, sx.wcrq,sx.sxgzbm, sx.wcbzrq
                        FROM yx_pc_sxjl sx
                       INNER JOIN xt_pc_sxgz gz
                          ON sx.sxgzbm=gz.gzbm
                          --ON sx.dwbm= gz.dwbm
                         AND sx.pcflbm=gz.pcflbm
                         AND sx.sxgzbm=gz.gzbm
                       WHERE sx.dwbm='''||p_dwbm||'''
                         AND sx.pcflbm='''||p_pcflbm||'''
                         AND sx.bmbm='''||p_bmbm||'''';


        --规则编码
        v_sql := v_sql || pkg_common.func_get_queryinsql('sx.sxgzbm', p_gzbm);

        -- 筛选完成标志日期
        v_sql := v_sql ||pkg_common.func_get_querydatesql('sx.wcbzrq' ,p_gzrqbng,p_gzrqend );

        --承办部门
        --v_sql :=v_sql ||pkg_common.func_get_queryinsql('sx.bmbm',p_bmbm);
        v_sql :=v_sql ||pkg_common.func_get_queryinsql('sx.bmbm',v_bmbmj);

        v_sql := v_sql || ' ORDER BY sx.wcbzrq desc';
      END IF;

      /*        INSERT INTO test_t(id, tj, sj) VALUES('1213', v_sql, SYSDATE);
              COMMIT;*/

      --获取总数
      EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
      INTO p_count;

      -- 获取数据
      v_sql := pkg_common.func_get_pagebyindex(v_sql,
                                               p_pageindex,
                                               p_pagesize);

      OPEN p_cursor FOR v_sql;

      /* INSERT INTO test_t(id, tj, sj) VALUES('1', v_sql, SYSDATE);
       COMMIT;*/
      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT sysdate FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;
    END proc_get_bmsxlist;

  -- 评查案件筛选列（随机/重点）检察官总表
  PROCEDURE proc_get_cbrsxlist(p_dwbm      IN yx_pc_sxjl.dwbm%TYPE, -- 承办人工号单位编码
                               p_cbrgh     IN yx_pc_sxjl.cbrgh%TYPE, -- 承办人工号
                               p_gzbm      IN xt_pc_sxgz.gzbm%TYPE, -- 规则编码
                               p_pcflbm    IN xt_pc_sxgz.pcflbm%TYPE, -- 评查分类编码
                               p_pchdbm    IN yx_pc_jbxx.pchdbm%TYPE, -- 评查活动编码
                               p_gzrqbng   IN DATE, --筛选规则匹配日期开始时间(>=)
                               p_gzrqend   IN DATE, --筛选规则匹配日期结束时间(<=)
                               p_type      IN VARCHAR, --0/1 （已经筛选/未筛选）
                               p_pagesize  IN NUMBER, --页大小
                               p_pageindex IN NUMBER, --页索引
                               p_count     OUT NUMBER, --总数
                               p_cursor    OUT SYS_REFCURSOR, --返回数据集
                               p_errmsg    OUT VARCHAR2 -- 错误信息
  ) IS
    v_sql CLOB;
    BEGIN
      p_errmsg := '';

      IF p_type ='0'--已评查
      THEN
        v_sql:='SELECT jb.pcflbm,jb.pcdwbm,jb.bmsah,jb.tysah,jb.ajmc,jb.bpc_dwbm,jb.ajlb_mc,
                           jb.bpc_dwmc DWMC,jb.bpc_gh,jb.bpc_mc CBRMC,jb.pcr_dwbm,jb.pcr_dwmc,jb.pcslbm,
                           jb.pcr_gh,jb.pcr_mc,jb.cjsj PCRQ,jb.zhxgsj WCBZRQ
                      FROM yx_pc_jbxx jb
                     INNER JOIN xt_pc_sxgz sx
                        ON jb.sxgzbm=sx.gzbm
                     WHERE jb.sfsc =''N''
                       AND jb.pcjdbh>=''005''
                       AND jb.bpc_dwbm='''||p_dwbm||'''
                      -- AND jb.sxgzbm='''||p_gzbm||'''
                       AND jb.pcflbm='''||p_pcflbm||'''';
        -- AND jb.pchdbm='''||p_pchdbm||'''

        -- 筛选完成标志日期
        v_sql := v_sql ||pkg_common.func_get_querydatesql('jb.bpc_wcbzrq' ,p_gzrqbng,p_gzrqend );

        -- 筛选规则
        v_sql :=v_sql ||pkg_common.func_get_queryinsql('jb.sxgzbm',p_gzbm);

        --承办人
        v_sql := v_sql ||
                 pkg_common.func_get_queryequalsql('jb.bpc_gh', p_cbrgh);

        --评查活动编码
        v_sql := v_sql ||
                 pkg_common.func_get_querylikesql ('jb.pchdbm',p_pchdbm);

        v_sql := v_sql || ' ORDER BY jb.zhxgsj desc';
      ELSE  --未评查
        v_sql := 'SELECT gz.gzbm,gz.gzmc,sx.pcflbm, sx.dwbm, sx.bmsah, sx.tysah, sx.ajmc, sx.ajlb_bm, sx.ajlb_mc,
                         sx.dwmc, sx.bmbm, sx.bmmc, sx.cbrgh, sx.cbrmc, sx.slrq, sx.wcrq,sx.sxgzbm, sx.wcbzrq
                    FROM yx_pc_sxjl sx
                   INNER JOIN xt_pc_sxgz gz
                      ON sx.sxgzbm=gz.gzbm
                      --ON sx.dwbm= gz.dwbm
                     AND sx.pcflbm=gz.pcflbm
                   --  AND sx.sxgzbm=gz.gzbm
                   WHERE sx.dwbm='''||p_dwbm||'''
                     AND sx.pcflbm='''||p_pcflbm||'''
                     AND sx.cbrgh='''||p_cbrgh||'''';

        -- 筛选完成标志日期
        v_sql := v_sql ||pkg_common.func_get_querydatesql('sx.wcbzrq' ,p_gzrqbng,p_gzrqend );

        -- 筛选规则
        v_sql :=v_sql ||pkg_common.func_get_queryinsql('sx.sxgzbm',p_gzbm);

        /*--承办人
        v_sql := v_sql ||
                 pkg_common.func_get_queryequalsql('sx.cbrgh', p_cbrgh);*/

        v_sql := v_sql || ' ORDER BY sx.wcbzrq desc';
      END IF;

      /* INSERT INTO test_t(id, tj, sj) VALUES('0208', v_sql, SYSDATE);
      COMMIT;*/

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
    END proc_get_cbrsxlist;

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
  -- 获取部门编码列表
  PROCEDURE proc_get_bmbm(p_dwbm   IN  xt_zzjg_bmbm.dwbm%TYPE, --单位编码
                          p_pcflbm IN  yx_pc_sxjl.pcflbm%TYPE, --评查分类编码
                          p_cursor OUT SYS_REFCURSOR, --返回数据集
                          p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql clob;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT bm.dwbm, bm.bmbm BMBM, NVL2(bmbm.fbmbm,bmbm.fbmbm, ''-1'') FBMBM, bm.bmmc BMMC
                    FROM xt_zzjg_bmbm@tyyw_link.net bmbm
                   INNER JOIN (SELECT jl.bmbm bmbm, jl.bmmc bmmc, jl.dwbm
                                 FROM yx_pc_sxjl jl
                                WHERE jl.dwbm = :dwbm
                                  AND jl.pcflbm=:pcflbm
                                  AND jl.bmbm IS NOT NULL
                                UNION
                               SELECT jb.bpc_bmbm bmbm, jb.bpc_bmmc bmmc, jb.bpc_dwbm
                                 FROM yx_pc_jbxx jb
                                WHERE jb.sfsc = ''N''
                                  AND jb.bpc_dwbm = :dwbm
                                  AND jb.bpc_bmbm IS NOT NULL
                                  AND jb.pcflbm = :pcflbm) bm
                      ON bmbm.dwbm=bm.dwbm
                     AND bmbm.bmbm=bm.bmbm
                   WHERE bmbm.dwbm = :dwbm
                     AND bmbm.sfsc = ''N''';

      -- INSERT INTO test_t(id, tj, sj) VALUES('1210', v_sql, SYSDATE);
      --  COMMIT;

      OPEN p_cursor FOR v_sql
      USING p_dwbm,p_pcflbm,p_dwbm,p_pcflbm,p_dwbm;



      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := chr(10) || dbms_utility.format_error_backtrace || chr(10) || SQLERRM;
    END proc_get_bmbm;

  -- 获取所有部门编码列表
  PROCEDURE proc_get_allbm(p_dwbm   IN  xt_zzjg_bmbm.dwbm%TYPE, --单位编码
                           p_cursor OUT SYS_REFCURSOR, --返回数据集
                           p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql clob;
    BEGIN
      p_errmsg := '';

      v_sql := 'SELECT bm.dwbm, bm.bmbm BMBM, NVL2(bm.fbmbm,bm.fbmbm, ''-1'') FBMBM, bm.bmmc BMMC
                    FROM xt_zzjg_bmbm@tyyw_link.net bm
                   WHERE bm.dwbm = :dwbm
                     AND bm.sfsc = ''N''';
      OPEN p_cursor FOR v_sql
      USING p_dwbm;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := chr(10) || dbms_utility.format_error_backtrace || chr(10) || SQLERRM;
    END proc_get_allbm;

  -- 获取所有模板编码
  PROCEDURE proc_get_mbbmj(p_pcflbm IN xt_zzjg_bmbm.dwbm%TYPE, --分类编码
                           p_gzbm   IN VARCHAR2, --规则编码
                           p_cursor OUT SYS_REFCURSOR, --返回数据集
                           p_errmsg OUT VARCHAR2 --异常信息
  ) IS
    v_sql   CLOB;
    v_mbbmj CLOB;

    BEGIN
      p_errmsg := '';

      -- 获取绑定的筛选规则集合
      SELECT MAX(gz.pcmbj)
      INTO v_mbbmj
      FROM xt_pc_sxgz gz
      WHERE gz.pcflbm = p_pcflbm
            AND gz.gzbm = p_gzbm;

      v_sql := ' SELECT mb.pcmbbm, mb.pcmbmc
                     FROM xt_pc_mb mb
                    WHERE mb.pcflbm = :pcflbm
                      AND mb.sfqy = ''Y''';

      v_sql := v_sql ||
               pkg_common.func_get_queryinsql('mb.pcmbbm', v_mbbmj);

      OPEN p_cursor FOR v_sql
      USING p_pcflbm;

      EXCEPTION
      WHEN OTHERS THEN
      IF p_cursor%ISOPEN
      THEN
        CLOSE p_cursor;
      END IF;
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
                  chr(10) || SQLERRM;
    END proc_get_mbbmj;
  --随机评查案件筛选（湖北:抽取到本地）
  PROCEDURE proc_get_sjsx_advance(p_pcflbm    IN VARCHAR2, --评查分类编码
                                  p_pcmbbm    IN VARCHAR2, --评查模板编码
                                  p_sxgzbm    IN VARCHAR2, --筛选规则编码
                                  p_cbdwbm    IN VARCHAR2, --承办单位编码(in)
                                  p_cbbmbm    IN VARCHAR2, --承办部门编码(in)
                                  p_bmsah     IN VARCHAR2, --部门受案号(like)
                                  p_ajmc      IN VARCHAR2, --案件名称(like)
                                  p_cbrxm     IN VARCHAR2, --承办人姓名(like)
                                  p_sfkf      in varchar2, -- 是否交叉查询 湖北交叉评查，新增字段，雷军军
                                  p_wcrqbng   IN DATE, --完成日期开始时间(>=)
                                  p_wcrqend   IN DATE, --完成日期结束时间(<=)
                                  p_zdycxtj   IN VARCHAR2, --自定义查询条件
                                  p_pagesize  IN NUMBER, --页大小
                                  p_pageindex IN NUMBER, --页索引
                                  p_count     OUT NUMBER, --总数
                                  p_cursor    OUT SYS_REFCURSOR, --返回数据集
                                  p_errmsg    OUT VARCHAR2) IS
    v_sql CLOB;
    --p_sfkf char;
    BEGIN
      -- p_sfkf :=  'Y';
      v_sql := 'select distinct aj.bmsah BMSAH, aj.ajmc AJMC, aj.ajlb_mc AJLBMC, aj.slrq SLRQ,
                         aj.dwbm DWBM,aj.dwmc DWMC, aj.bmbm CBBMBM,aj.bmmc CBBMMC,
                         aj.cbrgh CBRGH, aj.cbrmc CBR, aj.wcrq WCRQ,
                         aj.ajlb_bm AJLBBM, aj.sxgzbm SXGZBM, aj.wcbzrq WCBZRQ,'''||p_pcmbbm||''' PCMBBM,
                         jxaj.jsdw,jxaj.jsdwmc
                         from yx_pc_sxjl aj
                         left join yx_pc_jxpcaj jxaj
                         on aj.bmsah = jxaj.bmsah
                         and aj.pcflbm = jxaj.pcflbm

                WHERE NOT EXISTS (SELECT 1 FROM yx_pc_jbxx jb WHERE aj.bmsah = jb.bmsah AND jb.sfsc=''N'')';

      v_sql := v_sql || pkg_common.func_get_queryequalsql('aj.bmbm', p_cbbmbm);

      v_sql := v_sql || pkg_common.func_get_querylikesql('aj.cbrmc',p_cbrxm);

      v_sql := v_sql || pkg_common.func_get_querylikesql('aj.bmsah',p_bmsah);

      v_sql := v_sql || pkg_common.func_get_querylikesql('aj.ajmc',p_ajmc);

      v_sql := v_sql ||
               pkg_common.func_get_querydatesql('aj.wcrq',
                                                p_wcrqbng,
                                                p_wcrqend);

      -- 处理案件是来自本身的，还是其他单位开放过来的
      -- 湖北交叉评查，新增字段，雷军军
      if p_sfkf = 'Y'
      then
        v_sql := v_sql || pkg_common.func_get_queryequalsql('jxaj.dwbm', p_cbdwbm);
      else
        v_sql := v_sql || pkg_common.func_get_queryinsql('aj.dwbm', p_cbdwbm);
      end if;

      --v_sql := v_sql || pkg_common.func_get_queryinsql('aj.dwbm', p_cbdwbm);

      v_sql := v_sql || ' AND (aj.sxgzbm in (select gzbm from xt_pc_sxgz where fgzbm = '||p_sxgzbm||') OR aj.sxgzbm IN ('||p_sxgzbm||'))';

      v_sql := v_sql || pkg_common.func_get_queryequalsql('aj.pcflbm', p_pcflbm);

      v_sql := v_sql || p_zdycxtj;

      v_sql := v_sql || ' GROUP BY  aj.bmsah,aj.ajmc ,aj.ajlb_mc ,aj.slrq ,aj.dwbm , aj.dwmc,
                   aj.bmbm ,aj.bmmc ,aj.cbrgh ,aj.cbrmc ,aj.wcrq ,aj.ajlb_bm ,
                   aj.sxgzbm, aj.wcbzrq,jxaj.jsdw,jxaj.jsdwmc';

      v_sql := v_sql || ' ORDER BY aj.slrq';

      insert into test_t
      values('8888',v_sql,sysdate);
      commit;
      --获取总数
      EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM (' || v_sql || ')'
      INTO p_count;

      -- 获取对应页数据
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
      OPEN p_cursor FOR 'SELECT 1 FROM dual';
      p_errmsg := dbms_utility.format_error_backtrace || chr(10) ||
                  SQLERRM;

    END proc_get_sjsx_advance;
END pkg_pcsx;
/


spool off
