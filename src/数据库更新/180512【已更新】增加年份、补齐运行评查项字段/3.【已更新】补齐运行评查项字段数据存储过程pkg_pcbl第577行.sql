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
                         sp.pcspbm PCSPBM_BL, sp.ssrdwbm, sp.ssrgh, sp.sprdwbm, sp.sprgh, sp.spjl, sp.spjsbm
                    FROM yx_pc_jbxx jb
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