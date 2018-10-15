
-- .存储过程修改

--位置：pkg_pcbl.proc_get_pcspr 大概1138、1178行

 --获取评查审批送审接收人员（获取比当前登录人审批角色权限大的所有审批人；
    --不包含承办检察官和承办部门领导。）
    PROCEDURE proc_get_pcspr(p_dwbm      IN  xt_zzjg_dwbm.dwbm%TYPE,
                             p_gh        IN  xt_zzjg_rybm.gh%TYPE,
                             p_spjsbm    IN  xt_qx_jsbm.spjsbm%TYPE,
                             p_pchdbm    IN  yx_pc_jbxx.pchdbm%TYPE,
                             p_pcflbm    IN  yx_pc_jbxx.pcflbm%TYPE,
                             p_cursor    OUT SYS_REFCURSOR, --返回数据集
                             p_errmsg    OUT VARCHAR2 --异常信息
                             )IS
        v_sql  CLOB;
        v_sfjs xt_pc_lb.sfjs%TYPE;
    BEGIN

        SELECT sfjs INTO v_sfjs FROM xt_pc_lb WHERE pcflbm = p_pcflbm;
        IF v_sfjs = 'Y'
        THEN

            v_sql:='SELECT ry.dwbm, dw.dwmc, ry.mc, ry.gh, bm.jsmc, bm.jsbm, bm.spjsbm
                      FROM xt_zzjg_rybm ry
                     INNER JOIN (SELECT f.dwbm, f.gh, ''103'' jsbm
                                  FROM yx_pc_pcfzr f
                                 WHERE f.pchdbm = :pchdbm
                                 UNION
                                 SELECT x.dwbm, x.gh, x.jsbm
                                  FROM yx_pc_xzrydy x
                                 WHERE x.pchdbm = :pchdbm
                                   AND x.jsbm = ''102''
                                   /*湖北：审批人员添加分管检察长，雷军军，1012*/
                                    UNION
                             SELECT  jsfp.dwbm,jsfp.gh,jsfp.jsbm
                               FROM  xt_qx_ryjsfp jsfp
                              WHERE jsfp.dwbm = :PCDWBM
                                AND jsfp.jsbm IN(''107'')
                                 ) xz
                        ON xz.dwbm = ry.dwbm
                       AND xz.gh = ry.gh
                     INNER JOIN xt_zzjg_dwbm dw
                        ON ry.dwbm = dw.dwbm
                     INNER JOIN xt_qx_jsbm bm
                        ON bm.jsbm = xz.jsbm
                     WHERE ry.sfsc = ''N''
                       AND bm.spjsbm IS NOT NULL
                       AND bm.spjsbm<>''30''
                       AND bm.spjsbm>=:spjsbm
                     ORDER BY bm.spjsbm DESC';

            OPEN p_cursor FOR v_sql
                USING p_pchdbm, p_pchdbm,SUBSTR(p_pchdbm,0,6), p_spjsbm;

        ELSE

            v_sql:='SELECT ry.dwbm, dw.dwmc, ry.mc, ry.gh, js.jsmc, js.jsbm, bm.spjsbm
                      FROM xt_zzjg_rybm ry
                     INNER JOIN xt_qx_ryjsfp fp
                        ON ry.dwbm = fp.dwbm
                       AND ry.gh = fp.gh
                     INNER JOIN xt_zzjg_dwbm dw
                        ON ry.dwbm = dw.dwbm
                     INNER JOIN xt_zzjg_jsbm js
                        ON fp.dwbm = js.dwbm
                       AND fp.bmbm = js.bmbm
                       AND fp.jsbm = js.jsbm
                     INNER JOIN xt_qx_jsbm bm
                        ON js.jsbm=bm.jsbm
                     WHERE fp.dwbm = :dwbm
                       AND fp.bmbm = ''9191''
                       AND ry.sfsc = ''N''
                       AND bm.spjsbm IS NOT NULL
                       AND bm.spjsbm<>''30''
                       AND bm.spjsbm>=:spjsbm
                     ORDER BY bm.spjsbm DESC';




            OPEN p_cursor FOR v_sql
                USING p_dwbm, p_spjsbm;

        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            IF p_cursor%ISOPEN
            THEN
                CLOSE p_cursor;
            END IF;
            OPEN p_cursor FOR 'SELECT 1 FROM dual';
            p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
                        chr(10) || SQLERRM;
   END proc_get_pcspr;



   -- 存储过程修改：pkg_pcbl.proc_deal_pcfk
   -- 位置：大概796 --824
       -- 承办人反馈
    PROCEDURE proc_deal_pcfk(p_pcslbm  IN yx_pc_sp.spwjbm%TYPE, -- 评查受理编码
                             p_pcflbm  IN yx_pc_jbxx.pcflbm%TYPE,
                             p_pchdbm  IN yx_pc_jbxx.pchdbm%TYPE,
                             p_bmsah   IN yx_pc_jbxx.bmsah%TYPE,
                             p_czrdwbm IN yx_pc_jbxx.bpc_dwbm%TYPE, -- 单位编码
                             p_czrdwmc IN yx_pc_jbxx.bpc_dwmc%TYPE, -- 单位名称
                             p_czrgh   IN yx_pc_jbxx.bpc_gh%TYPE, -- 工号
                             p_czrxm   IN yx_pc_jbxx.bpc_mc%TYPE, -- 姓名
                             p_sfyy    IN CHAR, --是否有异议（Y/N）
                             p_sprdwbm IN yx_pc_jbxx.bpc_dwbm%TYPE, -- 业务部门领导单位编码
                             p_sprgh   IN yx_pc_jbxx.bpc_gh%TYPE, -- 业务部门领导工号
                             p_errmsg  OUT VARCHAR2 -- 错误信息
                             ) IS
        v_spwjlx yx_pc_sp.spwjlx%TYPE DEFAULT '1'; -- 审批文件类型 (1评查案件,2评查报告,3评查方案)
    BEGIN
        p_errmsg := '';

         -- 湖北检察官反馈全部反馈到评查员， 不走部门反馈流程 ，雷军军，1015
       /* IF p_sfyy = 'Y'
        THEN
            -- 更新评查状态
            pkg_pclc.proc_add_bmfk(p_pcslbm => p_pcslbm,
                                   p_pcflbm => p_pcflbm,
                                   p_pchdbm => p_pchdbm,
                                   p_bmsah => p_bmsah,
                                   p_czr_dwbm => p_czrdwbm,
                                   p_czr_dwmc => p_czrdwmc,
                                   p_czr_gh => p_czrgh,
                                   p_czr_mc => p_czrxm);
            -- 添加评查反馈信息
            INSERT INTO yx_pc_pcfk
              (pcslbm, fkxh, fzrdwbm, fzrgh, fkzt, fssj)
            VALUES
              (p_pcslbm, 1, p_sprdwbm, p_sprgh, '0', SYSDATE);

        ELSE
            -- 更新评查状态
            pkg_pclc.proc_add_lcjs(p_pcslbm => p_pcslbm,
                                   p_pcflbm => p_pcflbm,
                                   p_pchdbm => p_pchdbm,
                                   p_bmsah => p_bmsah,
                                   p_czr_dwbm => p_czrdwbm,
                                   p_czr_dwmc => p_czrdwmc,
                                   p_czr_gh => p_czrgh,
                                   p_czr_mc => p_czrxm);
        END IF;*/

         -- 更新评查状态
            pkg_pclc.proc_add_lcjs(p_pcslbm => p_pcslbm,
                                   p_pcflbm => p_pcflbm,
                                   p_pchdbm => p_pchdbm,
                                   p_bmsah => p_bmsah,
                                   p_czr_dwbm => p_czrdwbm,
                                   p_czr_dwmc => p_czrdwmc,
                                   p_czr_gh => p_czrgh,
                                   p_czr_mc => p_czrxm);

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            p_errmsg := chr(10) || dbms_utility.format_error_backtrace || SQLERRM;
    END proc_deal_pcfk;


    -- 存储过程修改：pkg_pcbl.proc_deal_ajqr
    -- 位置：大概637行

       -- 评查案件确认
    PROCEDURE proc_deal_ajqr(p_pcslbm  IN yx_pc_sp.spwjbm%TYPE, -- 评查受理编码
                             p_czrdwbm IN yx_pc_jbxx.pcr_dwbm%TYPE, -- 评查人单位编码
                             p_czrdwmc IN yx_pc_jbxx.pcr_dwmc%TYPE, -- 评查人单位名称
                             p_czrgh   IN yx_pc_jbxx.pcr_gh%TYPE, -- 评查人工号
                             p_czrxm   IN yx_pc_jbxx.pcr_mc%TYPE, -- 评查人姓名
                             p_cursor  OUT SYS_REFCURSOR, --返回数据集
                             p_errmsg  OUT VARCHAR2 -- 错误信息
                             ) IS
        v_record yx_pc_jbxx%ROWTYPE;
        v_sql    CLOB;
    BEGIN
        p_errmsg := '';

        -- 获取评查案件当前阶段及评查模板编码
        EXECUTE IMMEDIATE 'SELECT jb.*
                             FROM yx_pc_jbxx jb
                            WHERE jb.pcslbm = :pcslbm'
            INTO v_record
            USING p_pcslbm;
        IF v_record.pcjdbh IS NULL
          OR v_record.pcmbbm IS NULL
        THEN
            p_errmsg := '未获取到评查案件状态或评查模板编码。';
            RETURN;
        END IF;

        -- 判断当前评查阶段是否为待评查
        IF v_record.pcjdbh = pkg_pclc.g_jdbh_ajjs
        THEN
            -- 更新评查状态为办理
            pkg_pclc.proc_add_pcbl(p_pcslbm => p_pcslbm,
                                   p_pcflbm => v_record.pcflbm,
                                   p_pchdbm => v_record.pchdbm,
                                   p_bmsah => v_record.bmsah,
                                   p_czr_dwbm => p_czrdwbm,
                                   p_czr_dwmc => p_czrdwmc,
                                   p_czr_gh => p_czrgh,
                                   p_czr_mc => p_czrxm);

            -- 导入评查模板（评查项分类）
            DELETE FROM yx_pc_pcxfl WHERE pcslbm = p_pcslbm;
            INSERT INTO yx_pc_pcxfl
            (pcslbm, pcxflbm, pcxflmc, pcxflfbm, pcmbbm, pcjlbm, xh, sftj, sm, pcjg, pcyj, jlsj, pcrdwbm, pcrdwmc, pcrgh, pcrmc, flxtdm,fflxtdm,ywtx,pcflbm )
            SELECT p_pcslbm, pcxflbm, pcxflmc, pcxflfbm, pcmbbm, pcjlbm, xh, sftj, sm, mrz, '', SYSDATE, p_czrdwbm, p_czrdwmc, p_czrgh, p_czrxm,flxtdm,fflxtdm,ywtx,pcflbm
              FROM xt_pc_pcxfl fl
             WHERE fl.pcmbbm = v_record.pcmbbm;

            -- 导入评查模板（评查项）
            DELETE FROM yx_pc_pcx WHERE pcslbm = p_pcslbm;
            INSERT INTO yx_pc_pcx
            (pcslbm, pcxbm, pcxmc, pcxflbm, pcmbbm, pcxlx, pcfs, pcjlbm, fz_gd, fz_qsz, fz_jsz, zdpccx, xh, sftj, bz,
             pcjg, pcyj, jlsj, pcrdwbm, pcrdwmc, pcrgh, pcrmc, sm, flxtdm, fflxtdm, ywtx, pcflbm, xtdm)
            SELECT p_pcslbm,pcxbm, pcxmc, pcxflbm, pcmbbm, pcxlx, pcfs, pcjlbm, fz_gd, fz_qsz, fz_jsz, zdpccx, xh, sftj, bz,
                   DECODE(pcfs, '1', '0', ''), '', SYSDATE, p_czrdwbm, p_czrdwmc, p_czrgh, p_czrxm, '', flxtdm, fflxtdm, ywtx, pcflbm, xtdm
              FROM xt_pc_pcx
             WHERE pcmbbm = v_record.pcmbbm;

        END IF;

        v_sql := 'SELECT jb.*, aj.ysay_aymc AY, dw.dwmc PCDWMC,
                         sp.pcspbm PCSPBM_BL, sp.ssrdwbm, sp.ssrgh, sp.sprdwbm, sp.sprgh, sp.spjl, sp.spjsbm,lb.sfpcfp
                    FROM yx_pc_jbxx jb
                    INNER JOIN xt_pc_lb lb
                    on jb.pcflbm = lb.pcflbm
                   INNER JOIN tyyw_gg_ajjbxx@tyyw_link.net aj
                      ON jb.bmsah = aj.bmsah
                    LEFT JOIN (SELECT p.pcspbm, p.spwjbm pcslbm, p.ssrdwbm, p.ssrgh, p.sprdwbm, p.sprgh, p.spjl, p.spjsbm
                                FROM yx_pc_sp p
                               INNER JOIN (SELECT MAX(t.pcspbm) AS pcspbm
                                            FROM yx_pc_sp t
                                           WHERE t.spwjbm = :pcslbm
                                             AND t.sfsc = ''N'') s
                                  ON p.pcspbm = s.pcspbm) sp
                     ON jb.pcslbm = sp.pcslbm
                    LEFT JOIN xt_zzjg_dwbm dw
                      ON jb.pcdwbm = dw.dwbm
                   WHERE jb.pcslbm = :pcslbm';
        OPEN p_cursor FOR v_sql
            USING p_pcslbm, p_pcslbm;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            IF p_cursor%ISOPEN
            THEN
                CLOSE p_cursor;
            END IF;
            OPEN p_cursor FOR 'SELECT 1 FROM dual';
            p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
                        chr(10) || SQLERRM;
    END proc_deal_ajqr;