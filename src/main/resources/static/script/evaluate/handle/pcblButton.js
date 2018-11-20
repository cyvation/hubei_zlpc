
// 新建文书
function butAddWs(num) {
    var obj = get_eval_info_doc(PCBGLX);
    var cbrsf = $("input[name='rd_eval_info_sfldba']:checked").val();
    if(isNull(cbrsf)){
        Alert("未勾选承办人身份！");
        return;
    }
    // 评查结束验证
    var pcjg = $("input[name='rd_eval_info_pcjl_jg']:checked").val();
    if(isNull(pcjg)){
        Alert("未勾选结果等次建议！");
        return;
    }

    if(isNull(obj)){
        $.ajax({
            type: "get",
            async: false,
            url: getRootPath() + '/menu/queryWsmb',
            data: { pcflbm: EVAL_CASE.PCFLBM, wsmblb: PCBGLX },
            dataType: "json",
            success: function(data) {
                if(data.code==200){

                    if(data.data.length <= 0){
                        Alert("未获取到评查报告模板。");
                        return;
                    }

                    generate_doc_file(data.data[0]);

                }else {
                    Alert('获取报告模板失败：' +data.message)
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                Alert('获取报告模板失败。')
            }
        });
    } else {
        // 打开卷宗文件
        isApproveDoc = false;
        open_eval_file(obj.jzwjbh, obj.pczybm, obj.pczylx, obj.wjlx, obj.wjlj);
    }
}

// 生成个案评定报告
function butAddPdBg(num){

    var obj = get_eval_info_doc(PCBGLX);

    var pcjg = $("input[name='rd_eval_info_pcjl_jg']:checked").val();
    if(isNull(pcjg)){
        Alert("未勾选结果等次建议！");
        return;
    }

    if(isNull(obj)){

        $.ajax({
            type: 'POST',
            url: getRootPath()+'/pdx/generatePdxDoc',
            data: {"pcslbm":EVAL_CASE.PCSLBM},
            dataType: "json",
            success: function (result) {
                if (result == null || result == undefined) {
                    CloseProgress();
                    Alert("服务端返回数据为空。");
                    return;
                }

                if (result.code != 200){
                    CloseProgress();
                    Alert(result.note);
                    return;
                }
                try {
                   // $("#pcWin_win_new_pcx").window('close');
                    show_eval_doc_panel("doc");
                    CloseProgress();

                    var error = OpenFile(getRootPath() + "/Files/PCJZ/" + result.data, "TANGER_OCX");
                    if (!isNull(error)) {
                        Alert(error);
                        return;
                    }

                    load_tree_eval_doc_files();
                    isApproveDoc = false;
                    editDocPath = result.data;
                    opening_eval_doc_file = result.value;
                    SetSaveButtonState("TANGER_OCX", true);

                } catch (e) {
                    CloseProgress();
                }

            }
        });
    } else {
        // 打开卷宗文件
        isApproveDoc = false;
        open_eval_file(obj.jzwjbh, obj.pczybm, obj.pczylx, obj.wjlx, obj.wjlj);
    }

}

// 自动评查报告
function butAddZdpc() {

    $.ajax({
        type: "get",
        async: false,
        url: getRootPath() + '/menu/queryWsmb',
        data: { pcflbm: EVAL_CASE.PCFLBM, wsmblb: ZDPCBG },
        dataType: "json",
        success: function(data) {
            if(data.code==200){

                if(data.data.length <= 0){
                    Alert("未获取到评查报告模板。");
                    return;
                }

                generate_auto_doc_file(data.data[0]);

            }else {
                Alert('获取报告模板失败：' +data.message)
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert('获取报告模板失败。')
        }
    });
}

// 新增评查意见
function butAddPcyj(num) {
    var obj = get_eval_info_doc(PCLZDLX);
    if(isNull(obj)){
        Alert("请确认是否已生成评查流转单！");
        return;
    }

    add_eval_info_approve(obj.wjlj);
}

// 小组联席会议
function butAddXzlxhy(num) {
    var obj = get_eval_info_doc(PCLZDLX);
    if(isNull(obj)){
        Alert("请确认是否已生成评查流转单！");
        return;
    }

    add_eval_info_approve_jwh(obj.wjlj, '小组联席会议');
}

// 新增检委会意见
function butAddJwhyj(num) {
    var obj = get_eval_info_doc(PCLZDLX);
    if(isNull(obj)){
        Alert("请确认是否已生成评查流转单！");
        return;
    }

    add_eval_info_approve_jwh(obj.wjlj, '检委会意见');
}


// 创建或者打开评查流转单
function addOrOpenApproveDoc() {
    var obj = get_eval_info_doc(PCLZDLX);
    if(isNull(obj)){
        $.ajax({
            type: "get",
            async: false,
            url: getRootPath() + '/menu/queryWsmb',
            data: { pcflbm: EVAL_CASE.PCFLBM, wsmblb: PCLZDLX },
            dataType: "json",
            success: function(data) {
                if(data.code==200){

                    if(data.data.length <= 0){
                        Alert("未获取到评查报告模板。");
                        return;
                    }

                    generate_doc_file(data.data[0]);

                }else {
                    Alert('获取报告模板失败：' +data.message)
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                Alert('获取报告模板失败。')
            }
        });
    } else {
        // 打开卷宗文件
        isApproveDoc = true;
        open_eval_file(obj.jzwjbh, obj.pczybm, obj.pczylx, obj.wjlx, obj.wjlj);
    }
}

// 评查报审（评查员，评查办理阶段）
function butPcblPcbs(num) {

    var obj = get_eval_info_doc(PCBGLX);
    if(isNull(obj)){
        Alert("未生成评查报告，请先生成报告！");
        return;
    }

    EVAL_CASE.addjcg = '0'
    /*$.ajax({
        type: 'get',
        url: getRootPath()+'/handle/IsCreatePcbg',
        async:false,
        data: {pcslbm:EVAL_CASE.PCSLBM},
        success: function (result) {
            if (result.code == 200) {
                if(result.data==false){
                    Alert("未生成评查报告，请先生成报告！");
                    return;
                }
            }
        }
    });*/
    addOrOpenApproveDoc();

    isSendApprove = "0";
}

// 发送承办人(评查办理阶段)
function butPcblFscbr(num) {

    addOrOpenApproveDoc();

    isSendApprove = "-1";
}

// 评查报审（评查员）
function butPcbs(num) {

    var obj = get_eval_info_doc(PCLZDLX);
    // if(isNull(obj)){
    //     Alert("未生成评查流转单！");
    //     return;
    // }
   // save_edit_document();
    addOrOpenApproveDoc();

    var pcjl = EVAL_CASE.PCJL;
    var pcflbm = EVAL_CASE.PCFLBM;
    var pcjdbh = EVAL_CASE.PCJDBH;
   // send_eval_handle_deal_approve("20");
    if(pcjdbh == '006'){ // 组长
        send_eval_handle_deal_approve("20");
    }

    if (pcjdbh == '007'){
        if (pcjl == '合格案件'){
            send_eval_handle_deal_approve("60");
        }else{
            send_eval_handle_deal_approve("60");
        }
    }

  //  isSendApprove = "1";
}

// 评查报审（负责人）
function butFzrbs(num) {
    save_edit_document();
    //send_eval_handle_deal_approve("50");
    send_eval_handle_deal_approve(EVAL_CASE.SPJSBM);
}

// 发送承办人
function butFscbr(num) {

    save_edit_document();
    Confirm("确认", "是否发送给承办检察官？", function (r) {
        if (r) {

            var obj = new Object();
            obj.PCSLBM = EVAL_CASE.PCSLBM;
            obj.PCFLBM = EVAL_CASE.PCFLBM;
            obj.PCHDBM = EVAL_CASE.PCHDBM;
            obj.BMSAH = EVAL_CASE.BMSAH;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/handle/sendFeedback',
                data: { json: JSON.stringify(obj) },
                dataType: "json",
                success: function (result) {

                    if (result.status == 200){
                        // 初始化操作按钮
                        init_eval_handle_deal_toolbar(true);
                        Alert("发送给承办人成功！");
                    } else {
                        Alert(result.note);
                    }
                }

            });
        }
    });
}

// 办理阶段，新增检察官意见
function butStuffYj(num){
    addOrOpenApproveDoc();

    EVAL_CASE.addjcg = '1'
}

// 评查审批
function send_eval_handle_deal_approve(spjsbm, callback) {

    // 案件列表DataGrid初始化
    $('#grid_eval_handle_deal_fssp').datagrid({
        width:'460px',
        height: '280px',
        fitColumns: true,
        singleSelect: true,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        url: getRootPath()+'/manage/getPcsp',
        queryParams : {
            spjsbm : spjsbm,
            pchdbm : EVAL_CASE.PCHDBM,
            pcflbm : EVAL_CASE.PCFLBM
        },
        idField: 'ID',
        columns: [[
            { field: 'ID', title: '唯一标示', hidden: true },
            {field: 'ck', title: '复选框', checkbox: true, width: 80},
            { field: 'DWBM', title: '单位编码', hidden: true},
            { field: 'DWMC', title: '单位名称', width: 120},
            { field: 'MC', title: '姓名', width: 100},
            { field: 'GH', title: '工号', hidden: true},
            { field: 'JSMC', title: '角色',  width: 100 }
        ]]
    });

    // 提交
    $("#btn_eval_handle_deal_fssp_confirm").unbind("click");
    $("#btn_eval_handle_deal_fssp_confirm").bind("click", function () {

        var row = $("#grid_eval_handle_deal_fssp").datagrid("getSelected");
        if(!row){
            Alert("请选择审批人员！");
            return;
        }

        // JS对象
        var obj = new Object();
        obj.PCSLBM = EVAL_CASE.PCSLBM;
        obj.PCFLBM = EVAL_CASE.PCFLBM;
        obj.PCHDBM = EVAL_CASE.PCHDBM;
        obj.BMSAH = EVAL_CASE.BMSAH;
        obj.SPRDWBM  = row.DWBM;
        obj.SPRDWMC  = row.DWMC;
        obj.SPRGH = row.GH;
        obj.SPRXM = row.MC;
        obj.SPJSBM = row.SPJSBM;
        obj.SPJSMC = row.JSMC;

        if (callback){
            try{
                callback(obj);
            }catch(e){

            }
        }else{
            $.ajax({
                url: getRootPath() + "/handle/sendApprove",
                data: { json: JSON.stringify(obj)},
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {

                    if (result.status == 200){

                        // 初始化操作按钮
                        init_eval_handle_deal_toolbar(true);

                        init_eval_handle_bottom_tool('');
                        Alert("送审成功！");

                        $('#win_eval_handle_deal_fssp').window('close');
                    }else{
                        Alert(result.note);
                    }
                }
            });
        }
    });

    // 提交
    $("#btn_eval_handle_deal_fssp_cancle").unbind("click");
    $("#btn_eval_handle_deal_fssp_cancle").bind("click", function () {
        $('#win_eval_handle_deal_fssp').window('close');
    });

    // 弹出送审窗口
    $('#win_eval_handle_deal_fssp').window('open');
}

function close_win_eval_handle_deal_fssp() {
    $('#win_eval_handle_deal_fssp').window('close');
}

// 评查结束（通知承办人）
function butPcjsAndTzcbr(num) {
    // 是否发送评查通知
    save_edit_document();
    finish_eval_handle_deal("Y");
}

// 评查结束（不通知承办人）
function butPcjs(num) {
    save_edit_document();
    finish_eval_handle_deal("Y");
}

//湖北 李志恒 编辑案卡信息后，没有点击保存，数据一样会通过验证，再次自动保存数据 2018年5月8日
function saveAkxx(){
    var vo = initPostData();
    // 评查结论
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE.PCSLBM;
    var cljg = $("input[name='rd_eval_info_pcjl_cl']:checked").val();
    obj.CLJG = isNull(cljg) ? "" : cljg;
    var pcjl = $("input[name='rd_eval_info_pcjl_jg']:checked").val();
    obj.PCJL = isNull(pcjl) ? "" : pcjl;
    obj.SM = $("#txt_eval_info_pcjl_bz").val();
    var cbrsf = $("input[name='rd_eval_info_sfldba']:checked").val();
    obj.SFLDBA = cbrsf;

    $.ajax({
        url: getRootPath() + "/filter/saveEvalCards",
        data: { json: JSON.stringify(vo), jbxx: JSON.stringify(obj)},
        type: 'post',
        async: false,
        dataType: 'json',
        success: function (result) {

            if (result.status == 200){
                EVAL_CASE.CLJG = obj.CLJG;
                EVAL_CASE.PCJL = obj.PCJL;
                EVAL_CASE.SFLDBA = obj.SFLDBA;
                EVAL_CASE.SM = obj.SM;
            }else{
                Alert(result.note);
            }
        }
    });
}

// 评查结束
function finish_eval_handle_deal(sffs) {

    // 评查结束验证
    var pcjg = $("input[name='rd_eval_info_pcjl_jg']:checked").val();
    if(isNull(pcjg)){
        Alert("未勾选结果等次建议！");
        return;
    }
    var cbrsf = $("input[name='rd_eval_info_sfldba']:checked").val();
    if(isNull(cbrsf)){
        Alert("未勾选承办人身份！");
        return;
    }
    saveAkxx();
    var obj = get_eval_info_doc(PCLZDLX);
    // if(isNull(obj)){
    //     Alert("未生成评查流转单！");
    //     return;
    // }
    var obj = get_eval_info_doc(PCBGLX);
    if(isNull(obj)){
        Alert("未生成评查报告！");
        return;
    }

    // 合格案件可以在办理阶段直接结束。
    if (EVAL_CASE.PCJDBH == '006' && EVAL_CASE.PCJL !='合格案件' ){
        Alert("非合格案件请走审批！");
        return;
    }

    Confirm("确认", "是否评查结束？", function (r) {
        if (r) {

            var obj = new Object();
            obj.PCSLBM = EVAL_CASE.PCSLBM;
            obj.PCFLBM = EVAL_CASE.PCFLBM;
            obj.PCHDBM = EVAL_CASE.PCHDBM;
            obj.BMSAH = EVAL_CASE.BMSAH;
            obj.SFFS = sffs;
            obj.BPC_DWBM = EVAL_CASE.BPC_DWBM;
            obj.BPC_GH = EVAL_CASE.BPC_GH;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/handle/finishEval',
                data: { json: JSON.stringify(obj) },
                dataType: "json",
                success: function (result) {

                    if (result.status == 200){
                        // 初始化操作按钮
                        init_eval_handle_deal_toolbar(true);
                        Alert("评查案件结束成功！");
                    } else {
                        Alert(result.note);
                    }
                }

            });
        }
    });
}

// 添加到收藏夹
function butAddScj(num) {

    // 确认收藏
    $("#collBc").unbind( "click" );
    $("#collBc").bind("click", function () {
        var data={
            "zlmc":$('#collPcaj').val(),
            "gyzy": $('#collPcah').val(),
            "tjly": $('#collBz').val(),
            "json": 2,
            "gxbm": EVAL_CASE.PCSLBM,
        };
        $.ajax({
            url: getRootPath() + "/person/savePersonAj",
            data:JSON.stringify(data),
            type: 'post',
            contentType:'application/json',
            async: true,
            dataType: 'json',
            success: function (result) {
                if(result.code!=200){
                    // 初始化操作按钮
                    init_eval_handle_deal_toolbar(true);
                    Alert(result.note);
                }else{
                    Alert(data.zlmc+'收藏成功');
                    $('#addColl').window('close');
                    $('.star').hide();
                }
            }
        });
    });

    $('#addColl').window('open');
}

// 新建文书（弹窗）
function butAddWs_Window(num) {

    $('#addWs').window('open');
}

// 文书模板列表加载
function addWsTable() {

    $.ajax({
        type: "get",
        async: false,
        url: getRootPath() + '/menu/queryWsmb?pcflbm='+EVAL_CASE.PCFLBM,
        dataType: "json",
        success: function(data) {
            if(data.code==200){
                $('#addWsType').datagrid('loadData',data.data);
            }else {
                Alert('queryWsmb接口错误：' +data.message)
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert('queryWsmb接口访问错误')
        }
    });

    // 确定按钮
    $("#addWsBc").unbind( "click" );
    $("#addWsBc").bind("click", function () {
        var ws=$('#addWsType').datagrid('getSelected')
        if(ws!=undefined&&ws!=null&&ws!=''){
            generate_doc_file(ws);
        }
    });
}

// 生成评查报告
function generate_doc_file(wsCon) {

    ShowProgress();

    var obj = new Object();
    obj.PCHDBM = EVAL_CASE.PCHDBM;
    obj.PCZYBM = EVAL_CASE.PCSLBM;
    obj.WSMBBH = wsCon.wsmbbh;
    obj.WJMC = wsCon.wsmbmc;
    obj.WSMBLJ = wsCon.wsmblj;
    obj.WJLX = wsCon.wsmblb; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
    obj.GXLX = wsCon.sfgx; //活动内共享
    obj.JZMLH = wsCon.jzmlh; //评查报告目录
    obj.FJZWJBH = "-1";
    obj.WSCFLJ = "";
    obj.PCZYLX = "1"; //0.评查活动 1.评查案件
    obj.PCSLBM = EVAL_CASE.PCSLBM;

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/manage/generateDocFile',
        data: { json: JSON.stringify(obj) },
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.status != 200){
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();

                var error = OpenFile(getRootPath() + result.value, "TANGER_OCX");
                if (!isNull(error)) {
                    Alert(error);
                    return;
                }

                load_tree_eval_doc_files();
                isApproveDoc = obj.WJLX == PCLZDLX; //是否评查流转单
                editDocPath = result.note;
                opening_eval_doc_file = result.value;
                SetSaveButtonState("TANGER_OCX", true);

                // 关闭新建评查报告弹窗
                try{
                    $('#addWs').window('close');
                }catch(e){

                }
            } catch (e) {
                CloseProgress();
            }
        }
    });
}

// 生成自动评查报告
function generate_auto_doc_file(wsCon) {

    ShowProgress();

    // 生成临时报告文件
    var obj = new Object();
    obj.PCHDBM = EVAL_CASE.PCHDBM;
    obj.PCZYBM = EVAL_CASE.PCHDBM;
    obj.WSMBBH = wsCon.wsmbbh;
    obj.WJMC = wsCon.wsmbmc;
    obj.WSMBLJ = wsCon.wsmblj;
    obj.WJLX = wsCon.wsmblb; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
    obj.GXLX = wsCon.sfgx; //活动内共享
    obj.JZMLH = wsCon.jzmlh; //评查报告目录
    obj.FJZWJBH = "-1";
    obj.WSCFLJ = "";
    obj.PCZYLX = "1"; //0.评查活动 1.评查案件
    obj.PCSLBM = EVAL_CASE.PCSLBM;

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/manage/generateTempDoc',
        data: { json: JSON.stringify(obj) },
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.status != 200){
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();

                var error = OpenFile(getRootPath() + result.value, "TANGER_OCX");
                if (!isNull(error)) {
                    Alert(error);
                    return;
                }

                isApproveDoc = false; //是否评查流转单
                editDocPath = "";
                opening_eval_doc_file = "";
                SetSaveButtonState("TANGER_OCX", false);

            } catch (e) {
                CloseProgress();
            }
        }
    });
}

// 撤回审批
function butAddChsp(num) {

    Confirm("确认", "是否撤回审批？", function (r) {
        if (r) {

            var obj = new Object();
            obj.PCSLBM = EVAL_CASE.PCSLBM;
            obj.PCFLBM = EVAL_CASE.PCFLBM;
            obj.PCHDBM = EVAL_CASE.PCHDBM;
            obj.PCSPBM = EVAL_CASE.PCSPBM_BL;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/handle/cancelApprove',
                data: { json: JSON.stringify(obj) },
                dataType: "json",
                success: function (result) {

                    if (result.status == 200){
                        // 初始化操作按钮
                        init_eval_handle_deal_toolbar(true);
                        Alert("撤回审批成功！");
                    } else {
                        Alert(result.note);
                    }
                }

            });
        }
    });
}

// 退回送审
function butAddThss(num) {

    save_edit_document();
    Confirm("确认", "是否退回送审？", function (r) {
        if (r) {

            var obj = new Object();
            obj.PCSLBM = EVAL_CASE.PCSLBM;
            obj.PCFLBM = EVAL_CASE.PCFLBM;
            obj.PCHDBM = EVAL_CASE.PCHDBM;
            obj.PCSPBM = EVAL_CASE.PCSPBM_BL;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/handle/backApprove',
                data: { json: JSON.stringify(obj) },
                dataType: "json",
                success: function (result) {

                    if (result.status == 200){
                        // 初始化操作按钮
                        init_eval_handle_deal_toolbar(true);
                        Alert("退回送审成功！");
                    } else {
                        Alert(result.note);
                    }
                }

            });
        }
    });
}

function click_pcbl_pcxx_top_ajxx() {
    skipWebPage("案件信息", getRootPath() + "/case.html", { pcslbm: EVAL_CASE.PCSLBM, bmsah: EVAL_CASE.BMSAH }, '1');
}