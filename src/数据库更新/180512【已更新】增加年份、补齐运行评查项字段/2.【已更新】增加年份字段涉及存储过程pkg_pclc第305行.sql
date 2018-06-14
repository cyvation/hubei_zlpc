-- 案件筛选
    PROCEDURE proc_add_jbxx(p_pcdwbm     IN yx_pc_jbxx.pcdwbm%TYPE, -- 本次评查发起单位编码
                            p_pcflbm     IN yx_pc_jbxx.pcflbm%TYPE, -- 评查类别编码
                            p_pchdbm     IN yx_pc_jbxx.pchdbm%TYPE, -- 评查活动编码
                            p_pcmbbm     IN yx_pc_jbxx.pcmbbm%TYPE, -- 评查指标模板编码
                            p_sxgzbm     IN yx_pc_jbxx.sxgzbm%TYPE, -- 筛选规则编码
                            p_bpc_wcbzrq IN yx_pc_jbxx.bpc_wcbzrq%TYPE, -- 被评查案件完成标志日期
                            p_dpc_bmsah  IN yx_pc_jbxx.bmsah%TYPE,
                            p_dpc_dwbm   IN yx_pc_jbxx.bpc_dwbm%TYPE,
                            p_czr_dwbm   IN yx_lc_sl.czr_dwbm%TYPE,
                            p_czr_dwmc   IN yx_lc_sl.czr_dwmc%TYPE,
                            p_czr_gh     IN yx_lc_sl.czr_gh%TYPE,
                            p_czr_mc     IN yx_lc_sl.czr_mc%TYPE,
                            p_pcslbm     OUT yx_pc_jbxx.pcslbm%TYPE, -- 评查受理编码
                            p_pcsah      OUT yx_pc_jbxx.pcsah%TYPE, -- 评查受案号（类似部门受案号）
                            p_errmsg     OUT VARCHAR2) IS
        v_sql    CLOB;
        v_pcslbm yx_pc_jbxx.pcslbm%TYPE; --评查实例编码
        v_pcsah  yx_pc_jbxx.pcsah%TYPE; -- 评查受案号（类似部门受案号）
        v_lcslbh yx_pc_jbxx.lcslbh%TYPE DEFAULT '01'; --新增流程编码
        v_lcjdbm yx_lc_sl.lcjdbm%TYPE; --流程节点编码
        v_lcjdmc yx_lc_sl.lcjdmc%TYPE; --流程节点名称
        v_count NUMBER;--湖北：案件部门受案号全库唯一
        v_ajmc yx_pc_jbxx.ajmc%TYPE;--湖北：案件部门受案号全库唯一
        v_dwmc varchar2(200);--湖北：案件部门受案号全库唯一
    BEGIN
        p_errmsg := '';

        -- 0.湖北：案件部门受案号全库唯一
        SELECT count(1) INTO v_count FROM yx_pc_jbxx jb WHERE jb.sfsc='N' AND jb.bmsah=p_dpc_bmsah;
        IF v_count>0 THEN
           SELECT max(jb.ajmc),max(dw.dwmc) INTO v_ajmc,v_dwmc
             FROM yx_pc_jbxx jb
             LEFT JOIN xt_zzjg_dwbm dw ON jb.pcdwbm=dw.dwbm
            WHERE jb.sfsc='N'
              AND jb.bmsah=p_dpc_bmsah;
            p_errmsg := v_ajmc||'('||p_dpc_bmsah||')该案件已被'||v_dwmc||'评查。';
            RETURN;
        END IF;

        -- 1.获取评查受理编码
        v_pcslbm := pkg_pclc.func_get_pcslbm(p_pcdwbm, p_pcflbm);
        IF v_pcslbm IS NULL
           OR length(v_pcslbm) <= 21
        THEN
            p_errmsg := '生成评查实例编号失败。';
            RETURN;
        END IF;

        -- 2.获取评查受案号
        v_pcsah := pkg_pclc.func_get_pcsah(TRIM(v_pcslbm));
        IF v_pcsah IS NULL
           OR length(v_pcsah) <= 21
        THEN
            p_errmsg := '生成评查受案号失败。';
            RETURN;
        END IF;

        -- 3.新增评查基本信息
        v_sql := 'INSERT INTO yx_pc_jbxx
                  (pcslbm, pcsah, pcdwbm, pcflbm, pchdbm,
                   bmsah, tysah, ajmc, ajlb_bm, ajlb_mc, bpc_dwbm, bpc_dwmc,
                   bpc_bmbm, bpc_bmmc, bpc_gh, bpc_mc, bpc_slrq, bpc_wcrq,
                   sxr_dwbm, sxr_dwmc, sxr_gh, sxr_mc,
                   lcslbh,pcjdbh,pcjdms, pcmbbm, sxgzbm, bpc_wcbzrq, wcrq_nf)
                  SELECT :pcslbm,:pcsah,:pcdwbm,:pcflbm,:pchdbm,
                         aj.bmsah,aj.tysah,aj.ajmc,aj.ajlb_bm,aj.ajlb_mc,aj.cbdw_bm,aj.cbdw_mc,
                         aj.cbbm_bm,aj.cbbm_mc,aj.cbrgh,aj.cbr,aj.slrq,aj.wcrq,
                         :sxr_dwbm, :sxr_dwmc, :sxr_gh, :sxr_mc,
                         :lcslbh,:pcjdbh,:pcjdms,:pcmbbm,:sxgzbm,NVL2(:bpc_wcbzrq, :bpc_wcbzrq, aj.wcrq),to_char(NVL(aj.wcrq,aj.bpc_wcbzrq),''YYYY'')
                    FROM tyyw_gg_ajjbxx' ||
                 pkg_default.g_tyyw_link || ' aj
                   WHERE aj.sfsc = ''N''
                     AND aj.cbdw_bm = :cbdw_bm
                     AND aj.bmsah = :bmsah';
        EXECUTE IMMEDIATE v_sql
            USING v_pcslbm, v_pcsah, p_pcdwbm, p_pcflbm, p_pchdbm, p_czr_dwbm, p_czr_dwmc, p_czr_gh, p_czr_mc, v_lcslbh, v_lcjdbm, v_lcjdmc, p_pcmbbm, p_sxgzbm, p_bpc_wcbzrq, p_bpc_wcbzrq, p_dpc_dwbm, p_dpc_bmsah;

        p_pcslbm := TRIM(v_pcslbm);
        p_pcsah  := TRIM(v_pcsah);

        -- 4.新增评查流程
        pkg_pclc.proc_add_lc(p_pcflbm   => p_pcflbm,
                             p_pcslbm   => TRIM(v_pcslbm),
                             p_czr_dwbm => p_czr_dwbm,
                             p_czr_dwmc => p_czr_dwmc,
                             p_czr_gh   => p_czr_gh,
                             p_czr_mc   => p_czr_mc);

        COMMIT;
        -- 5.更新流程状态
        pkg_pclc.proc_upd_lc(p_pcslbm   => TRIM(v_pcslbm),
                             p_lcjdbm   => g_jdbh_ajsx,
                             p_lcjdsm   => '案件筛选',
                             p_czr_dwbm => p_czr_dwbm,
                             p_czr_dwmc => p_czr_dwmc,
                             p_czr_gh   => p_czr_gh,
                             p_czr_mc   => p_czr_mc);

        -- 6.记录操作日志
        pkg_pclc.proc_add_rz(p_czlx    => g_rzlx_pcaj,
                             p_pclbbm  => p_pchdbm,
                             p_pcslbm  => TRIM(v_pcslbm),
                             p_gnbm    => '',
                             p_gnmc    => '评查案件筛选',
                             p_czsm    => '案件【' || p_dpc_bmsah || '】被筛选。',
                             p_czrdwbm => p_czr_dwbm,
                             p_czrdwmc => p_czr_dwmc,
                             p_czrgh   => p_czr_gh,
                             p_czrmc   => p_czr_mc,
                             p_ip      => '');

    EXCEPTION
        WHEN OTHERS THEN
            p_errmsg := chr(10) || dbms_utility.format_error_backtrace ||
                        chr(10) || SQLERRM;
            ROLLBACK;
    END proc_add_jbxx;