
DECLARE
  v_sql    CLOB;
  p_errmsg CLOB;
  v_cursor SYS_REFCURSOR;
  TYPE record_type IS RECORD(
    --pcslbm CHAR(22),
    BPC_DWBM CHAR(6),
    BPC_MC   VARCHAR2(60));
  v_record record_type;
  v_gh     CHAR(4);
BEGIN

  -- 查询本单位及所有下级单位
  v_sql := 'SELECT distinct  BPC_DWBM,BPC_MC from yx_pc_offline_jbxx j WHERE j.bpc_gh IS NULL AND j.sfsc=''N''';

  OPEN v_cursor FOR v_sql;

  LOOP
    FETCH v_cursor
      INTO v_record;
    EXIT WHEN v_cursor%NOTFOUND;
  
    SELECT MAX(to_number(gh))
      INTO v_gh
      FROM XT_ZZJG_RYBM
     WHERE dwbm = v_record.bpc_dwbm;
  
    IF v_gh IS NULL THEN
      v_gh := '0001'; --对应单位、业务下无文书模板编号，则从0001开始
    ELSE
      v_gh := lpad(to_char(to_number(v_gh) + 1),4,'0'); --自增1
    END IF;
  
    --避免与统一业务工号有重复，+6000
    IF to_number(v_gh) < 4000 THEN
      v_gh := to_char(6000 + to_number(v_gh));
    END IF;
  
    INSERT INTO xt_zzjg_rybm
      (dwbm,
       gh,
       dlbm,
       kl,
       mc,
       sflsry,
       sftz,
       xb,
       sfsc,
       dzyj)
    VALUES
      (v_record.bpc_dwbm,
       v_gh,
       v_record.bpc_mc,
       '1bbd886460827015e5d605ed44252251',
       v_record.bpc_mc,
       'N',
       'N',
       '1',
       'N',
       '123@123.com');
  
    UPDATE yx_pc_offline_jbxx o
       SET o.bpc_gh = v_gh
     WHERE o.bpc_dwbm = v_record.bpc_dwbm
       AND o.bpc_mc = v_record.bpc_mc
       AND o.bpc_gh IS NULL;
  
  END LOOP;
END;
