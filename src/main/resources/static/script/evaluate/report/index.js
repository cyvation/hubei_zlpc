var oper_type = 2; //默认保存（1为新增报告，2为修改报告）
var selectedNode; // 勾选的评查模板节点
var relativePath = '/Files/PCFA/';
var editReportPath = ""; //当前打开报告路径
var bgmc_jsbg;  //报告名称

$(function(){


    // 初始化数据字典
    init_eval_report();

    // 按钮事件绑定
    register_event_rept();

    // 加载右侧报告文件列表
    //  load_eval_report_wjlist();


});


function init_eval_report(){

    // 左侧评查报告模板树初始化（单层模板树）：
    $("#tree_pclb_rept").tree({
        url: getRootPath() + '/pcreport/getPcbgMb',
        queryParams: {pcflbm:'',wsmblb:'4'},
        loadFilter: function (data) {
            return data.code == 200 ? JSON.parse(data.data) : [];
        },
        onSelect: function (node) {
            //清理选中的评查受理编码
            document.getElementById('divDoc_bg').style.display = "none";
            document.getElementById('pnl_pcaj_rept').style.display = "";
            document.getElementById('pnl_bgsp_rept').style.display = "none";

            selectedNode = node;
            // 如果专项/交叉右边展示活动
            if (node.attributes.PCFLBM == '003' || node.attributes.PCFLBM == '005'){
                init_eval_report_hd();
            }else {
                init_eval_report_bg();
            }

            // 加载右侧报告文件列表
            load_eval_report_wjlist();
        },
        onLoadSuccess:function(node, data){
            if (data && data.length >=1){
                // 默认选中第一个报告模板
                var n = $("#tree_pclb_rept").tree('find',data[0].id);
                $("#tree_pclb_rept").tree('select',n.target);
            }

            // 加载右侧报告文件列表
            load_eval_report_wjlist();
        }
    });
    // 右侧查询条件初始化：
    //1. 单位
    if (DJDWBM == userInfo.DWBM) {
        $("#div_report_dw").css('display','');
        $('#report_dw').combotree({
            method: 'get',
            editable: false,
            lines: true,
            panelWidth:270,
            multiple: true,
            cascadeCheck: false,
            onShowPanel: index_onShowPanel,
            onHidePanel: index_onHidePanel,
            url: getRootPath() + '/organization/getDwbmTree',
            async: false,
            loadFilter: function (data) {
                return data.status == 200 ? JSON.parse(data.value) : [];
            },
            onLoadSuccess: function (node, data) {
                if (data != null && data.length >= 1) {
                    $('#report_dw').combotree('setValue', data[0].id); //单位默认选择
                }
                index_addMousedownDiv(this,'report_dw');
                $(".tree-icon,.tree-file").addClass("tree-file");
            }
        });
    }else{
        $("#div_report_dw").css('display','none');
    }

    // 3. 报告生成时间：
    $('#date_report_cjsj_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_report_cjsj_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    // 默认加载报告列表
    init_eval_report_bg();
}

// 报告文件初始化：
// 活动展示列表初始化：
function init_eval_report_bg(){

    // 显示新增报告按钮
    $("#btn_add_rept").css('display','');

    // 初始化报告文件列表
    $('#dg_pcaj_rept').datagrid({
        width: 'auto',
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        fitColumns: true,
        loadMsg: '数据加载中，请稍后...',
        autoRowHeight: false,
        rownumbers: true,
        pagination:true,
        pageSize: 20,
        pageNumber: 1,
        remoteSort:false,
        pageList: [20,40,60,80,100],
        toolbar: $('#tool_search_rept'),
        columns: [[
            {field: 'ck', title: '复选框', checkbox: true, width: 18},
            {field: 'NZRDWBM', title: '拟制单位', width: 90, hidden:true},
            {field: 'NZRDWMC', title: '拟制单位', width: 90, sortable: true},
            {field: 'JZWJBH', title: '文件编号', width: 100, sortable: true},
            {field: 'WJMC', title: '报告名称', width: 100, sortable: true},
            {field: 'NZRXM', title: '拟制人', width: 40, sortable: true},
            {field: 'NZRGH', title: '拟制人工号', width: 40, hidden:true},
            {field: 'CJSJ', title: '创建时间', fixed: true, width: 115, sortable: true,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'PCFLBM', title: '评查分类编码',  width: 0, hidden: true},
            {field: 'PCFLMC', title: '评查分类', width: 90,hidden: true},
            {field: 'WSCFLJ', title: '文件存放路径', width: 100, hidden: true},
            {field: 'SSZT', title: '报告状态', width: 120, sortable: true, hidden: true},
            // hidden: (userInfo.DWBM == DJDWBM ? true: false
            {field: 'BSZT', title: '报送状态', width: 70, hidden: true,
                formatter:function(value){
                    return value == '0' ? '未报送' : '已报送';
                }
            },
            {
                field: 'action', title: '操作', width: 220, align: 'center',
                formatter: function (value, row, index) {
                    var a = '<a href="#" onclick="look_and_feedback_report(' + index + ')">查看</a>';
                    var b = '';
                    var b1 = '';
                    // if ((row.SSZT == '未送审'|| row.SSZT.indexOf("退回") !=-1) && userInfo.DWBM == row.NZRDWBM && userInfo.spjsbm != '70') {
                    //     b= '<a href="#" onclick="go_sp_report(' + index + ')" style="margin-left: 5px">送审</a>';
                    // }
                    // if ((row.SSZT != '未送审' && row.SSZT.indexOf("退回") ==-1 )&& userInfo.DWBM == row.NZRDWBM){
                    //     b1= '<a href="#" onclick="look_sp_report(' + index + ')" style="margin-left: 5px">查看审批</a>';
                    // }
                    var c = '';
                    // if (row.SSZT == '送审中' && userInfo.DWBM == row.NZRDWBM){
                    //     c= '<a href="#" onclick="back_sp_report(' + index + ')" style="margin-left: 5px">撤回送审</a>';
                    // }

                    var d = '<a href="#" onclick="download_report(' + index + ')" style="margin-left: 5px">下载</a>';
                    var e = '';
                    if (row.SSZT !='已审批' && userInfo.DWBM == row.NZRDWBM){
                        e = '<a href="#" onclick="delete_eval_report(' + index + ')" style="margin-left:5px">删除</a>';
                    }
                    var f ='';
                    // if (!isNull(row.WSCFLJ) && userInfo.DWBM != DJDWBM && row.SSZT.indexOf('已审批') !=-1 && row.BSZT == '0'){
                    //     f = '<a href="#" onclick="send_report_djdw(' + index + ')" style="margin-left: 5px;">报送</a>';
                    // }
                    return a + b + b1 + c + d + e + f;
                }
            }
        ]],
        onClickRow: function (rowIndex, rowData) {
            $('#dg_pcaj_rept').datagrid('clearSelections');
            $('#dg_pcaj_rept').datagrid('highlightRow', rowIndex);
            $('#dg_pcaj_rept').datagrid('checkRow', rowIndex);
        }
    });

    $('#dg_pcaj_rept').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录',
        onSelectPage:function (pageNumber, pageSize) {
            var gridOpts = $("#dg_pcaj_rept").datagrid('options');
            gridOpts.pageNumber = pageNumber;
            gridOpts.pageSize = pageSize;

            load_eval_report_wjlist();
        }
    });

}


// 活动展示列表初始化：
function init_eval_report_hd(){

    // 隐藏新增报告按钮
    $("#btn_add_rept").css('display','none');


    // 初始化活动列表
    $("#dg_pcaj_rept").datagrid({
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        loadMsg: '数据加载中，请稍等。。。',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        rownumbers:true,
        multiSort:true,
        remoteSort:false,
        loadFilter: function (data) {
            return data;
        },
        columns: [[
            {field: 'ck', title: '复选框', checkbox: true, width: 18},
            {field: 'PCHDBM', title: '评查活动编码',  width: 0, hidden: true},
            {field: 'PCHDMC', title: '评查活动名称',  width: 150},
            {field: 'PCFLBM', title: '评查分类编码',  width: 0, hidden: true},
            {field: 'PCFLMC', title: '评查分类', width: 90, hidden: true},
            {field: 'CJRMC', title: '活动创建人',  width: 120,hidden:true},
            {field: 'NZRDWBM', title: '拟制单位', width: 90, hidden:true},
            {field: 'NZRDWMC', title: '拟制单位', width: 90, sortable: true},
            {field: 'JZWJBH', title: '文件编号', width: 150, sortable: true},
            {field: 'WJMC', title: '报告名称', width: 100, sortable: true},
            {field: 'WSCFLJ', title: '文件存放路径', width: 100, hidden: true},
            {field: 'NZRXM', title: '拟制人', width: 90, sortable: true},
            {field: 'NZRGH', title: '拟制人工号', width: 40, hidden:true},
            {field: 'CJSJ', title: '创建时间',  width: 120,
                formatter: function (value){return sjzh(value);}
            },
            {field: 'SSZT', title: '报告状态', width: 70, sortable: true, hidden: true},
            // hidden: (userInfo.DWBM == DJDWBM ? true: false),暂时不送审，湖北，雷军军
            {field: 'BSZT', title: '报送状态', width: 70, hidden: true,
                formatter:function(value){
                    return value == '0' ? '未报送' : '已报送';
                }
            },
            {
                field: 'CZ',
                title: '操作',
                align: 'center',
                width: 300,
                formatter: function (value, row, index) {
                    var a ='';
                    // if (isNull(row.WSCFLJ) ){
                    //     a = '<a href="#" onclick="generate_eval_hd_report(' + index + ')">生成报告</a>';
                    // }else{
                    //     a = '<a href="#" onclick="look_and_feedback_report(' + index + ')">查看报告</a>';
                    // }
                    if (!isNull(row.WSCFLJ) ){
                        a = '<a href="#" onclick="look_and_feedback_report(' + index + ')">查看报告</a>';
                    }

                    var b = '';
                    var b1 = '';
                    // if (!isNull(row.WSCFLJ) && (row.SSZT == '未送审'|| row.SSZT.indexOf("退回") !=-1) && userInfo.DWBM == row.NZRDWBM && userInfo.spjsbm != '70') {
                    //     b= '<a href="#" onclick="go_sp_report(' + index + ')" style="margin-left: 5px">送审</a>';
                    // }
                    // if (!isNull(row.WSCFLJ) && (row.SSZT != '未送审' && row.SSZT.indexOf("退回") ==-1 ) && userInfo.DWBM == row.NZRDWBM){
                    //     b1= '<a href="#" onclick="look_sp_report(' + index + ')" style="margin-left: 5px">查看审批</a>';
                    // }
                    var c = '';
                    // if (!isNull(row.WSCFLJ) && row.SSZT == '送审中' && userInfo.DWBM == row.NZRDWBM){
                    //     c= '<a href="#" onclick="back_sp_report(' + index + ')" style="margin-left: 5px">撤回送审</a>';
                    // }

                    var d = '';
                    if (!isNull(row.WSCFLJ)){
                        d = '<a href="#" onclick="download_report(' + index + ')" style="margin-left: 5px">下载</a>';
                    }
                    var e = '';
                    if (!isNull(row.WSCFLJ) && row.SSZT !='已审批' && userInfo.DWBM == row.NZRDWBM){
                        e = '<a href="#" onclick="delete_eval_report(' + index + ')" style="margin-left: 5px;">删除</a>';
                    }
                    var f = '';
                    // if (!isNull(row.WSCFLJ) && userInfo.DWBM != DJDWBM && row.SSZT.indexOf('已审批') !=-1 && userInfo.DWBM == row.NZRDWBM){
                    //     f = '<a href="#" onclick="send_report_djdw(' + index + ')" style="margin-left: 5px;">报送</a>';
                    // }

                    return a + b + b1 + c + d + e + f;
                }
            }
        ]]
    });
    $('#dg_pcaj_rept').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录',
        onSelectPage:function (pageNumber, pageSize) {
            var gridOpts = $("#dg_pcaj_rept").datagrid('options');
            gridOpts.pageNumber = pageNumber;
            gridOpts.pageSize = pageSize;

            load_eval_report_wjlist();
        }
    });
}

function register_event_rept(){

    $("#btn_add_rept").click(function () {
        alert_pcmb_window();
    });

    $("#btn_search_pcaj_rept").click(function () {
        load_eval_report_wjlist();
    });

    // 批量下载报告
    $("#btn_download_rept").click(function () {
        download_report();
    });

    $("#report_type").combotree({
        id:'id',
        text:'text',
        editable: false,
        multiple: true,
        cascadeCheck: false,
        data:[
            {"id":0,"text":"待审批" },
            {"id":1,"text":"审批完成"}
        ]
    });

}

// 加载报告文件列表
function load_eval_report_wjlist() {

    // 判断哪种报告类型：
    if (selectedNode.attributes.PCFLBM == '003' || selectedNode.attributes.PCFLBM == '008') {
        load_eval_report_hd();
        return;
    }

    var obj = new Object();
    if(DJDWBM == userInfo.DWBM){
        obj.dwbm = isNull($("#report_dw").combotree('getValues').join(",")) ? userInfo.DWBM : $("#report_dw").combotree('getValues').join(",");
    }else{
        obj.dwbm = userInfo.DWBM;
    }
    obj.nzr_dwbm = userInfo.DWBM;
    obj.nzr_gh = userInfo.GH;
    obj.startDate = $("#date_report_cjsj_begin").datebox('getValue');
    obj.endDate = $("#date_report_cjsj_end").datebox('getValue');
    // 文书模板编号
    obj.wsmbbh = isNull(selectedNode) ? "" : selectedNode.id;
    // 是否已经审批
    obj.sfsp = $("#report_type").combotree('getValues').join(",");
    var gridOpts = $("#dg_pcaj_rept").datagrid('options');
    obj.page = gridOpts.pageNumber;
    obj.rows = gridOpts.pageSize;
    $.ajax({
        url: getRootPath() + '/pcreport/getReportList',
        data: obj,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var data = JSON.parse(data.data);
                $('#dg_pcaj_rept').datagrid('loadData',data);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ShowError(textStatus + "，" + errorThrown);
        }
    });

    resize_dg_pcaj_rept();
}

// 加载活动列表
function load_eval_report_hd() {

    var obj = new Object();
    if(DJDWBM == userInfo.DWBM){
        obj.dwbm = isNull($("#report_dw").combotree('getValues').join(",")) ? userInfo.DWBM : $("#report_dw").combotree('getValues').join(",");
    }else{
        obj.dwbm = userInfo.DWBM;
    }
    obj.nzr_dwbm = userInfo.DWBM;
    obj.nzr_gh = userInfo.GH;
    obj.startDate = $("#date_report_cjsj_begin").datebox('getValue');
    obj.endDate = $("#date_report_cjsj_end").datebox('getValue');
    obj.pcflbm = selectedNode.attributes.PCFLBM;

    // 文书模板编号
    obj.wsmbbh = isNull(selectedNode) ? "" : selectedNode.id;
    // 是否已经审批
    obj.sfsp = $("#report_type").combotree('getValues').join(",");

    var gridOpts = $("#dg_pcaj_rept").datagrid('options');
    obj.page = gridOpts.pageNumber;
    obj.rows = gridOpts.pageSize;
    $.ajax({
        url: getRootPath() + '/pcreport/getReportHd',
        data: obj,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var data = JSON.parse(data.data);
                $('#dg_pcaj_rept').datagrid('loadData',data);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ShowError(textStatus + "，" + errorThrown);
        }
    });


    //清理选中的评查受理编码
    // pcsl_bms = null;
    // document.getElementById('divDoc_bg').style.display = "none";
    // document.getElementById('pnl_pcaj_rept').style.display = "";
    resize_dg_pcaj_rept();
}


// 弹出报告模板选择窗口
function alert_pcmb_window(){

    // 加载评查模板：
    $('#cbt_bgmb_win_templet_rept').combotree({
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/pcreport/getPcbgMb',
        queryParams: {pcflbm:'000',wsmblb:'4'},
        loadFilter: function (data, parent) {
            return data.code == 200 ? JSON.parse(data.data) : []
        },
        onLoadSuccess: function (node, data) {
            // 默认选中左侧选中的模板
            if (selectedNode){
                $('#cbt_bgmb_win_templet_rept').combotree('setValue',selectedNode.id);
            }
            index_addMousedownDiv(this, 'cbt_bgmb_win_templet_rept');
        },
        onSelect: function (record) {
            $("#bgmc_win_templet_rept").textbox('setValue',record.attributes.WSMBMC);
            if (record && record.attributes.SM == 'S' && rows.length > 1) {
                $('#win_templet_rept').window('close');
                return true;
            }
        }
    });

    $("#bgmc_win_templet_rept").textbox('setValue','');

    // 案件完成范围时间初始化：
    $('#date_aj_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_aj_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    // 确认生成报告
    $("#btn_ok_win_templet_rept").linkbutton({
        onClick: function(){
            generate_eval_report();
        }
    });

    // 取消
    $("#btn_cancle_win_templet_rept").linkbutton({
        onClick: function(){
            $("#win_templet_rept").window('close');
        }
    });

    $("#win_templet_rept").window('open');

}

// 生成报告
function generate_eval_report(){

    // 校验：
    var mc = $('#bgmc_win_templet_rept').textbox('getValue');
    var t = $('#cbt_bgmb_win_templet_rept').combotree('tree'); // 获取树对象
    var n = t.tree('getSelected');
    var wsCon = n.attributes;

    if (mc == "") {
        Alert("请填写评查报告名称");
        return;
    }

    bgmc_jsbg = mc;
    if (n == null) {
        Alert("请选择报告模板");
        return;
    }


    oper_type = 1;
    var obj = new Object();
    obj.startDate = $("#date_aj_begin").datebox('getValue');
    obj.endDate = $("#date_aj_end").datebox('getValue');
    obj.pczybm = wsCon.JZMLH;
    obj.wsmbbh = wsCon.WSMBBH;
    obj.wjmc = bgmc_jsbg;
    obj.wsmblj = wsCon.WSMBLJ;
    obj.wjlx = wsCon.WSMBLB; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
    obj.gxlx = wsCon.SFGX; //活动内共享
    obj.jzmlh = wsCon.JZMLH; //评查报告目录
    obj.fjzwjbh = "-1";
    obj.wscflj = "";
    obj.pczylx = '0'; //0.评查活动 1.评查案件

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/pcreport/generateDoc',
        contentType:'application/json',
        data: JSON.stringify(obj),
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.code != 200) {
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();
                $("#win_templet_rept").window('close');
                load_eval_report_wjlist();
                var error = OpenFile(getRootPath() + relativePath + result.data, "TANGER_OCX_PCBG");
                if (!isNull(error)) {
                    Alert(error);
                    return;
                }

                editReportPath = result.data;
                SetSaveButtonState("TANGER_OCX_PCBG", true);

            } catch (e) {
                CloseProgress();
            }
        }
    });

}

// 生成一份空白word供评查员编辑
function generate_blank_word(wsCon){
    oper_type = 1;
    var obj = new Object();
    obj.pczybm = wsCon.JZMLH;
    obj.wsmbbh = wsCon.WSMBBH;
    obj.wjmc = bgmc_jsbg;
    obj.wsmblj = wsCon.SM;
    obj.wjlx = wsCon.WSMBLB; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
    obj.gxlx = wsCon.SFGX; //活动内共享
    obj.jzmlh = wsCon.JZMLH; //评查报告目录
    obj.fjzwjbh = "-1";
    obj.wscflj = "";
    obj.pczylx = '0'; //0.评查活动 1.评查案件

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/pcreport/generateBlankWord',
        contentType:'application/json',
        data: JSON.stringify(obj),
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.code != 200) {
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();
                $("#win_templet_rept").window('close');
                load_eval_report_wjlist();
                var error = OpenFile(getRootPath() +result.data, "TANGER_OCX_PCBG");
                if (!isNull(error)) {
                    Alert(error);
                    return;
                }

                editReportPath = result.data;
                SetSaveButtonState("TANGER_OCX_PCBG", true);

            } catch (e) {
                CloseProgress();
            }
        }
    });

}

// 生成报告并打开
function generate_report(PCHDBM,PCSLBM,wsCon){

    //暂存，用于文书保存成功后自动关联案件
    //pcsl_bms = data.join(",");


    // clear_bg_info_rept();
    oper_type = 1;
    var obj = new Object();
    obj.PCHDBM = PCHDBM;
    obj.PCZYBM = PCHDBM;
    obj.WSMBBH = wsCon.WSMBBH;
    obj.WJMC = bgmc_jsbg;
    obj.WSMBLJ = wsCon.WSMBLJ;
    obj.WJLX = wsCon.WSMBLB; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
    obj.GXLX = wsCon.SFGX; //活动内共享
    obj.JZMLH = wsCon.JZMLH; //评查报告目录
    obj.FJZWJBH = "-1";
    obj.WSCFLJ = "";
    obj.PCZYLX = '0'; //0.评查活动 1.评查案件
    obj.PCSLBM = PCSLBM;

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/manage/generateDocFile',
        data: {json: JSON.stringify(obj)},
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.status != 200) {
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();
                //  load_eval_report_wjlist();
                var error = OpenFile(getRootPath() +result.value, "TANGER_OCX_PCBG");
                if (!isNull(error)) {
                    Alert(error);
                    return;
                }

                editReportPath = result.note;
                SetSaveButtonState("TANGER_OCX_PCBG", true);

            } catch (e) {
                CloseProgress();
            }
        }
    });

}

// 查看或修改
function look_and_feedback_report(index) {
    var selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];
    view_doc_office_ocx_rept(selectedRow);
}

// 送审
function go_sp_report(index) {
    var bg = $("#dg_pcaj_rept").datagrid('getRows')[index];

    var spjsbm = '0';
    var jsbmj = userInfo.JSBM;
    for(var i = 0; i < jsbmj.length; i++){
        var jsbm = jsbmj[i];
        if (jsbm.SPJSBM > spjsbm){
            spjsbm = jsbm.SPJSBM;
        }
    }

    //初始化送审接收人员表[流程：评查管理员->案管负责人->分管副检察长（必须）->检察长（非必须）]
    $('#dg_spry_win_send_check_rept').datagrid({
        width: '460px',
        height: '280px',
        fitColumns: true,
        singleSelect: true,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        idField: 'ID',
        method: 'get',
        url: getRootPath()+'/pcreport/getPcsp',
        queryParams : {
             spjsbm : '40'
            //spjsbm : spjsbm
        },
        columns: [[
            {field: 'ID', title: '唯一标示', hidden: true},
            {field: 'ck', title: '复选框', checkbox: true, width: 80},
            {field: 'DWBM', title: '单位编码', hidden: true},
            {field: 'DWMC', title: '单位名称', width: 120},
            {field: 'MC', title: '姓名', width: 100},
            {field: 'GH', title: '工号', hidden: true},
            {field: 'JSMC', title: '角色', width: 100}
        ]],
        loadFilter: function (data) {
            return data.code == 200 ? JSON.parse(data.data) : [];
        },
        groupField: 'JSMC',
        view: groupview,
        groupFormatter: function (value, rows) {
            return ((value == '') ? "无角色" : value); // +' - ' + rows.length + ' 条';
        }
    });

    // 送审确定
    $("#btn_ok_win_send_check_rept").linkbutton({
        onClick:function(){
            confirm_send_check_rept(bg);
        }
    });

    // 送审取消
    $("#btn_cancel_win_send_check_rept").linkbutton({
        onClick:function(){
            $("#win_send_check_rept").window('close');
        }
    });

    $("#win_send_check_rept").window('open');

}

function confirm_send_check_rept(bg){
    // 校验：
    var selectedRy = $("#dg_spry_win_send_check_rept").datagrid('getSelected');
    if (!selectedRy){
        Alert("请选择审批人！");
        return;
    }
    var obj = new Object();
    obj.spwjlx = '2';
    obj.spwjbm = bg.JZWJBH;
    obj.ssrxm = userInfo.MC;
    obj.ssrgh = userInfo.GH;
    obj.ssrdwbm = userInfo.DWBM;
    obj.ssrdwmc = userInfo.DWMC;

    obj.sprdwbm = selectedRy.DWBM;
    obj.sprdwmc = selectedRy.DWMC;
    obj.sprgh = selectedRy.GH;
    obj.sprxm = selectedRy.MC;
    obj.spjsbm = selectedRy.SPJSBM;
    obj.spjsmc = selectedRy.JSMC;

    $.ajax({
        type: 'POST',
        url: getRootPath() + "/pcreport/sendPcbgSp",
        dataType: 'json',
        data: obj,
        success: function (data) {
            if (data.code == 200) {
                $("#win_send_check_rept").window('close');
                Alert("送审成功");
                load_eval_report_wjlist();
                SetSaveButtonState("TANGER_OCX_PCBG", false);
            }
        }
    })


}

// 撤回送审
function back_sp_report(index){
    var selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];
    var jzwjbh = selectedRow.JZWJBH;
    Confirm("确认", "确认撤销已经送审的评查报告？", function (r) {
        if (r) {
            var obj = {spwjbm: jzwjbh};
            $.ajax({
                type: 'POST',
                url: getRootPath() + '/pcreport/cancelSendSp',
                dataType: "json",
                data: obj,
                success: function (data) {
                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    if (data.code == 200) {
                        Alert("撤销送审成功！");
                        load_eval_report_wjlist();
                    }
                }
            });
        }
    });

}

// 查看审批
function look_sp_report(index) {
    var selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];
    document.getElementById('divDoc_bg').style.display = "none";
    document.getElementById('pnl_pcaj_rept').style.display = "none";
    document.getElementById('pnl_bgsp_rept').style.display = "";

    clear_bg_info_rept();


    var jzwjbh = selectedRow.JZWJBH;
    //获取报告基本信息
    $.ajax({
        url: getRootPath() + '/pcreport/getPcbgDetailInfo',
        dataType: 'json',
        data: {jzwjbh: jzwjbh},
        success: function (data) {
            if (data.code == 200) {
                $('#bgmc_bg_info_rept').textbox('setValue', data.data.wjmc);
                $('#cjr_bg_info_rept').textbox('setValue', data.data.nzrxm);
                $('#cjsj_bg_info_rept').textbox('setValue', sjzh(data.data.cjsj));
            } else {
                Alert("无数据或错误");
            }

        }
    });

    //评查报告审批表初始化
    $('#dg_bgsp_rept').datagrid({
        url: getRootPath() + '/pcreport/getPcspbgInfo',
        width: 'auto',
        toolbar: $('#head_bgsp_rept'),
        striped: true,
        fitColumns: true,
        autoRowHeight: false,
        singleSelect: true,
        pageSize: 10,
        pagination: true,
        rownumbers: true,
        loadMsg: '',
        queryParams: {spwjbm: selectedRow.JZWJBH},
        loadFilter: function (data) {
            return data.code == 200 ? data.data : [];
        },
        columns: [[
            {field: 'SPYJ', title: '审批意见', width: 150},
            {field: 'SPRXM', title: '审批人', width: 60},
            {field: 'SSRQ', title: '审批日期', width: 60},
            {field: 'SPJL', title: '审批结论', width: 60}
        ]]
    });

    $('#dg_bgsp_rept').datagrid('getPager').pagination({
        pageList: [10, 20, 30, 50, 100],
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    resize_dg_bgsp_rept();

    $('#pnl_bgsp_rept').panel({
        onResize: function (width, height) {
            resize_dg_bgsp_rept();
        }
    });

}

// 下载报告
function download_report(index){
    var selectedRow;
    var jzwjbhj;

    if (index != undefined){
        selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];
        jzwjbhj = selectedRow.JZWJBH;
    }else{
        selectedRow = $("#dg_pcaj_rept").datagrid('getChecked');
        if (!selectedRow){
            Alert("请选择报告进行下载！");
            return;
        }
        var arr =[];
        for(var i= 0; i < selectedRow.length; i++){
            arr.push(selectedRow[i].JZWJBH);
        }
        jzwjbhj = arr.join(",");
    }


    if (isNull(jzwjbhj)) {
        Alert("不存在评查报告附件");
        return;
    }else {
        //window.location =   getRootPath() + relativePath + url;

        $.ajax({
            url: getRootPath() + '/pcreport/downloadDoc',
            dataType: 'json',
            data: {jzwjbhj: jzwjbhj,modelName:selectedNode.text},
            success: function (data) {
                if (data.code == 200) {
                    var url = data.data;
                    window.location =   getRootPath() + url;

                } else {
                    Alert("下载失败");
                }

            }
        });



    }
}

// 删除报告
function delete_eval_report(index){
    var selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];
    var jzwjbh = selectedRow.JZWJBH;
    $.messager.confirm('确认', '您确认要删除所选评查报告吗？', function (p) {
        if (p) {
            var obj = {jzwjbh: jzwjbh};
            $.ajax({
                type: 'POST',
                url: getRootPath() + '/pcreport/delPcbg',
                dataType: "json",
                data: obj,
                success: function (res) {
                    if (res.code == 200) {
                        Alert("删除成功报告成功！");
                        load_eval_report_wjlist();
                    }
                }
            });
        }
    });
}

// 发送报告到市院[接收人：默认市院评查员、案管负责人、助理]
function send_report_djdw(index){
    var selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];
    var jzwjbh = selectedRow.JZWJBH;

    $.ajax({
        type: 'post',
        url: getRootPath()+'/pcreport/sendDoc',
        data: {
            jzwjbh: jzwjbh
        },
        success: function (result) {
            Alert("报送成功");
            // 不可修改报告
            SetSaveButtonState("TANGER_OCX_PCBG", false);
            load_eval_report_wjlist();
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });


}

// 生成活动报告
function generate_eval_hd_report(index){
    var selectedRow = $("#dg_pcaj_rept").datagrid('getRows')[index];

    var wsInfo = selectedNode.attributes;

    var pchdbm = selectedRow.PCHDBM;
    var pcslbm = '';

    // 活动报告名称默认采用活动名称
    bgmc_jsbg = selectedRow.PCHDMC;

    generate_report(pchdbm, pcslbm, wsInfo);
}


//使用office控件查看评查报告
function view_doc_office_ocx_rept(row) {

    // clear_bg_info_rept();
    if (row.WSCFLJ == "") {
        document.getElementById('divDoc_bg').style.display = "none";
        Alert("文件路径不存在");
    }
    else {

        show_eval_doc_panel("doc");
        // document.getElementById('divDoc_bg').style.display = "";
        document.getElementById('divDoc_bg').style.display = "";
        document.getElementById('pnl_pcaj_rept').style.display = "";
        document.getElementById('pnl_bgsp_rept').style.display = "none";
        var docId = "TANGER_OCX_PCBG";
        OpenFile(getRootPath()+relativePath+row.WSCFLJ,docId);
        editReportPath = row.WSCFLJ;
        // 审批通过报告可再编辑

        // 如果是报送来的，不可修改
        if (row.BSZT == '1'){
            SetSaveButtonState(docId, false);
        }else{
            SetSaveButtonState(docId, true);
        }

    }
}

// 保存评查报告
function save_report_file() {

    //document.all("TANGER_OCX_PCBG").CancelLastCommand = true;

    if(isNull(editReportPath) || editReportPath == ""){
        return;
    }
    // 编辑保存
    var url = getRootPath() + "/manage/savePCBG?wjlj=" + editReportPath + "&Pczylx=" +'0';
    var fileresult = SaveToUrl(url);
    if (fileresult == null && fileresult != "") {
    } else {
        Alert("修改评查报告失败。" + fileresult);
    }
}

// 显示评查卷宗预览区域
function show_eval_doc_panel(type) {
    $("#layout_center_rept").css('display', 'block');
    //$("#pcbl_pcxx_center_left").css('display', 'none');
    document.getElementById('pnl_bgsp_rept').style.display = "none";
    document.getElementById('divDoc_bg').style.display = "none";
    document.getElementById('pnl_pcaj_rept').style.display = "";
    // show_eval_info_pcjz_area(); //显示评查卷宗浮动面板
    $("#win_eval_report_preview_doc").window('open');

    switch (type) {
        case "doc":
            // 加载文书
            document.getElementById('divDoc_bg').style.display = "";
            break;
        default:
            break;
    }

}

// 展示评查卷宗浮动面板
function show_eval_info_pcjz_area() {

    $("#evalinfo_position_one").hide();
    $("#evalinfo_position_two").show();
    $(".center_right_box").removeClass('center_right_box_click');
    $(".center_right_box:last-child").addClass('center_right_box_click');
    $('.center_right_box_sj').removeClass('center_right_box_sj');
    $(".center_right_box:last-child").children('div').addClass('center_right_box_sj');
}

// 重置报告审批表格大小
function resize_dg_bgsp_rept() {
    var width = $('#panel_dg_bgsp_rept').width();
    var hight = $('#panel_dg_bgsp_rept').height();
    $('#dg_bgsp_rept').datagrid('options').height = hight;
    $('#dg_bgsp_rept').datagrid('options').width = width - 65;
    $('#dg_bgsp_rept').datagrid('resize');
}

//清除报告基本信息数据
function clear_bg_info_rept() {
    $('#bgmc_bg_info_rept').textbox('setValue', '');
    $('#cjr_bg_info_rept').textbox('setValue', '');
    $('#cjsj_bg_info_rept').textbox('setValue', '');
}

// 重置评查案件信息
function resize_dg_pcaj_rept() {
    var h = $('#pnl_pcaj_rept').height();
    $('#dg_pcaj_rept').datagrid('options').height = h - 15;
    var w = $('#pnl_pcaj_rept').width();
    $('#dg_pcaj_rept').datagrid('options').width = w - 10;
    $('#dg_pcaj_rept').datagrid('resize');
}