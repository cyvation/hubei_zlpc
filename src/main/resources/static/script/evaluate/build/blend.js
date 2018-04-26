
var EVAL_DATA; //评查方案信息（基本信息、评查组、评查案件）
var IS_EDIT = false; //新增or编辑，false为新增，true为编辑
var PCZ_LIST = new Array(); //评查组信息(临时保存)
var showTot = true;
var showTj = true;
var sxgzbm;//缓存筛选规则编码

$(document).ready(function () {

    // 新建评查界面传递的参数，评查分类及评查模板等信息
    var pcxx = FUNCTION_PARAM;

    // 界面标签样式设置及事件绑定
    eval_special_marksInit(pcxx);

    // 标签初始化数据加载
    eval_special_marksDataBind(pcxx);

    // 案件筛选界面标签样式设置及事件绑定
    eval_special_win_filter_marksInit(pcxx);

    // 初始化操作按钮
    init_special_toolbar();

    var vcount = $('#table_win_eval_bulid_sp_filtered').datagrid('getRows').length;

});

// 初始化操作按钮
function init_special_toolbar() {

    // 新建评查活动时，无需工具栏
    if (!EVAL_DATA || !EVAL_DATA.PCHDBM || isNull(EVAL_DATA.PCHDBM)){
        return;
    }

    // 查看审批记录
    $('#btn_evaluate_build_sp_spyj').css('display', 'none');
    $("#btn_evaluate_build_sp_spyj").unbind("click");
    $("#btn_evaluate_build_sp_spyj").bind("click", function () {
        // 查看审批记录
        lookup_eval_special_approve();
    });

    // 送审
    $('#btn_evaluate_build_sp_approve').css('display', 'none');
    $("#btn_evaluate_build_sp_approve").unbind("click");
    $("#btn_evaluate_build_sp_approve").bind("click", function () {
        // todo送审
        send_eval_special_approve();
    });

    // 评查启动
    $('#btn_evaluate_build_sp_start').css('display', 'none');
    $("#btn_evaluate_build_sp_start").unbind("click");
    $("#btn_evaluate_build_sp_start").bind("click", function () {
        // 启动
        start_eval_special();
    });

    // 评查结束
    $('#btn_evaluate_build_sp_end').css('display', 'none');
    $("#btn_evaluate_build_sp_end").unbind("click");
    $("#btn_evaluate_build_sp_end").bind("click", function () {
        // 结束
        finish_eval_special();
    });

    // 生成评查方案（文书）
    $('#btn_evaluate_build_sp_generate').css('display', 'none');
    $("#btn_evaluate_build_sp_generate").unbind("click");
    $("#btn_evaluate_build_sp_generate").bind("click", function () {

        // 重新生成方案
        generate_pcfa(true);
    });

    // 活动审批
    $('#btn_evaluate_build_sp_examine').css('display', 'none');
    $("#btn_evaluate_build_sp_examine").unbind("click");
    $("#btn_evaluate_build_sp_examine").bind("click", function () {
        // 审批 ,提交审批结论
        deal_eval_special_approve();
    });

    // 获取评查活动状态，控制操作权限
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/blend/getHdztBM',
        data: {pchdbm: EVAL_DATA.PCHDBM},
        dataType: "json",
        success: function (result) {

            if (result.status != 200) {
                Alert("获取评查活动状态失败！");
                return;
            }

            var data = result.value;
            if (data.HDZT_BM == '001' || data.HDZT_BM == '002') {// 评查分配、案件筛选

                $('#btn_evaluate_build_sp_approve').css('display', ''); //送审
                $('#btn_evaluate_build_sp_generate').css('display', ''); //生成方案

            } else if (data.HDZT_BM == '003') {// 方案审批

                EVAL_DATA.PCSPBM = data.PCSPBM;

                // 审批意见(审批意见不为空)
                if (!isNull(data.SPJL)){
                    $('#btn_evaluate_build_sp_spyj').css('display', '');
                }

                // 审批按钮(审批人且审批结论为空)
                if(userInfo.DWBM == data.SPRDWBM && userInfo.GH == data.SPRGH && isNull(data.SPJL)){
                    $('#btn_evaluate_build_sp_examine').css('display', '');
                }

                // 送审人
                if (userInfo.DWBM == data.SSRDWBM && userInfo.GH == data.SSRGH) {

                    // 审批通过
                    if (data.SPJL == '同意' && data.SFQD == 'N') {
                        $('#btn_evaluate_build_sp_start').css('display', ''); //启动
                    } else if (data.SPJL == '不同意') {
                        $('#btn_evaluate_build_sp_generate').css('display', ''); //生成方案
                        $('#btn_evaluate_build_sp_approve').css('display', ''); //送审
                    }
                }

            } else if (data.HDZT_BM == '004') {// 评查办理

                $('#btn_evaluate_build_sp_spyj').css('display', ''); //审批意见

                // 评查结束(启动人：正常操作都是送审人)
                if (userInfo.DWBM == data.QDR_DWBM && userInfo.GH == data.QDR_GH) {
                    $('#btn_evaluate_build_sp_end').css('display', '');
                }
            } else {
                if(!isNull(data.SPJL)){
                    $('#btn_evaluate_build_sp_spyj').css('display', ''); //审批意见
                }
            }

        }

    });
}

function eval_special_marksInit(pcxx) {

    // 评查信息保存按钮
    $('#btn_save_eval_build_sp_info').hide();

    //展开 收起
    $(".ZXpc_pcfa_but_1").unbind( "click" );
    $(".ZXpc_pcfa_but_1").bind("click", function () {
        showTot = !showTot
        openDiv(this,1)
    });
    $(".ZXpc_pcfa_but").unbind( "click" );
    $(".ZXpc_pcfa_but").bind("click", function () {
        showTj = !showTj
        openDiv(this,2)
    });
    if(typeof(pcxx.PCMBJ) != "undefined" ){
        // 评查模板
        $('#cbt_eval_bulid_sp_pcmb').combotree({
            method: 'get',
            lines: true,
            onShowPanel: index_onShowPanel,
            onHidePanel: index_onHidePanel,
            url: getRootPath()+'/template/templateByPcmbbm',
            queryParams: {
                pcflbm: pcxx.PCFLBM,
                pcmbbm: pcxx.PCMBJ
            },
            onLoadSuccess: function (node,data) {
                if (data.length > 0){
                    $('#cbt_eval_bulid_sp_pcmb').combotree('setValue', data[0].id);
                    load_cbt_eval_bulid_sp_sxgz(pcxx.PCFLBM, data[0].attributes.YWTX);
                    load_cbt_eval_bulid_sp_assign_sxgz(pcxx.PCFLBM, data[0].attributes.YWTX);
                }
                index_addMousedownDiv(this,'cbt_eval_bulid_sp_pcmb');
            },
            onSelect: function (node) {
                load_cbt_eval_bulid_sp_sxgz(pcxx.PCFLBM, node.attributes.YWTX);
                load_cbt_eval_bulid_sp_assign_sxgz(pcxx.PCFLBM, node.attributes.YWTX);
            }
        });
    }else{
        // 评查模板
        $('#cbt_eval_bulid_sp_pcmb').combotree({
            method: 'get',
            lines: true,
            onShowPanel: index_onShowPanel,
            onHidePanel: index_onHidePanel,
            url: getRootPath()+'/template/template',
            queryParams: {
                pcflbm: pcxx.PCFLBM
            },
            onLoadSuccess: function (node,data) {
                if (data.length > 0){
                    $('#cbt_eval_bulid_sp_pcmb').combotree('setValue', data[0].id);
                    load_cbt_eval_bulid_sp_sxgz(pcxx.PCFLBM, data[0].attributes.YWTX);
                    load_cbt_eval_bulid_sp_assign_sxgz(pcxx.PCFLBM, data[0].attributes.YWTX);
                }
                index_addMousedownDiv(this,'cbt_eval_bulid_sp_pcmb');
            },
            onSelect: function (node) {
                load_cbt_eval_bulid_sp_sxgz(pcxx.PCFLBM, node.attributes.YWTX);
                load_cbt_eval_bulid_sp_assign_sxgz(pcxx.PCFLBM, node.attributes.YWTX);
            }
        });
    }




    // 承办部门下拉树
    $("#txt_eval_build_sp_cbbm").combotree({
        method: 'get',
        disabled: false,
        editable: false,
        lines: true
        // multiple: true, //湖北：禁止多选，改为单选部门
        // cascadeCheck: false
    });

    // 承办单位列表
    $('#cbt_eval_bulid_sp_dwbm').combotree({
        method: 'get',
        lines: true,
        panelWidth:250,
        multiple: true,  //湖北:禁止多选，改为单选单位
        cascadeCheck:false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },
        onLoadSuccess: function (node, data) {
            $('#cbt_eval_bulid_sp_dwbm').combotree('setValue', userInfo.DWBM);
            index_addMousedownDiv(this,'cbt_eval_bulid_sp_dwbm');
        },
        onChange: function (newValue, oldValue) {

            //仅选中一个单位时加载部门列表
            if(isNull(newValue) || newValue.length != 1){
                $("#txt_eval_build_sp_cbbm").combotree('disable');
                return;
            }
            // if(isNull(newValue)){
            //     $("#txt_eval_build_sp_cbbm").combotree('disable');
            //     return;
            // }

            $("#txt_eval_build_sp_cbbm").combotree('enable');
            // 承办部门下拉树
            $("#txt_eval_build_sp_cbbm").combotree({
                onShowPanel: index_onShowPanel,
                onHidePanel: index_onHidePanel,
                url: getRootPath() + '/filter/getAllBm',
                queryParams: {
                    dwbm: newValue[0]
                    // dwbm: newValue
                },
                onLoadSuccess: function (node, data) {
                    index_addMousedownDiv(this,'txt_eval_build_sp_cbbm');
                }
            });
        }
    });

    // 筛选案件列表
    $('#table_eval_bulid_sp_filter').datagrid({
        border:0,
        fit: true,
        height: 300,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageSize:20,
        pageNumber:1,
        multiSort:true,
        remoteSort:false,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:240,sortable:true },
            {field:'AJMC',title:'案件名称',width:100,sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:200,sortable:true},
            {field:'DWMC',title:'承办单位',width:90,sortable:true},
            {field:'CBR',title:'承办检察官',width:90,sortable:true},
            {field:'SLRQ',title:'受理日期',width:90,sortable:true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }

                }},
            {field:'WCBZRQ',title:'完成日期',width:90,sortable:true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }

                },
            },
            {field:'DWBM',title:'单位编码',hidden:true},
            {field:'SXGZBM',title:'筛选规则编码',hidden:true},
            {field: 'action', title: '操作', width: 90, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_bulid_sp_filter\',1)">查看</a>';
                    var d = '<a href="#" onclick="filter_special_case(' + index + ',\'' + row.BMSAH + '\')" style="margin-left:10px">筛选</a> ';
                    return r + d;
                }
            }
        ]]
    });
    $('#table_eval_bulid_sp_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 查询
    $("#btn_eval_build_sp_search").unbind( "click" );
    $("#btn_eval_build_sp_search").bind("click", function () {

        // 加载案件列表
        load_table_eval_bulid_sp_filter(pcxx);

    });

    // 高级查询
    $("#btn_eval_build_sp_search_advance").unbind( "click" );
    $("#btn_eval_build_sp_search_advance").bind("click", function () {
        $("#window_gjcx").window("open");
        sxgzbm = $("#cbt_eval_bulid_sp_sxgz").combotree('getValue');
        $("#window_gjcx").window("refresh","view/evaluate/build/special_gjcx.html");
    });

    // 已筛选案件列表
    $('#table_win_eval_bulid_sp_filtered').datagrid({
        border:0,
        height: 300,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        multiSort:true,
        remoteSort:false,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:210,sortable:true  },
            {field:'AJMC',title:'案件名称',width:100,sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:150,sortable:true },
            {field:'DWMC',title:'承办单位',width:90,sortable:true },
            {field:'CBR',title:'承办检察官',width:90,sortable:true },
            {field:'SLRQ',title:'受理日期',width:90,sortable:true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }


                },
            },
            {field:'WCBZRQ',title:'完成日期',width:90,sortable:true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }
                },
            },
            {field:'DWBM',title:'单位编码',hidden:true},
            {field:'SXGZBM',title:'筛选规则编码',hidden:true},
            {field: 'action', title: '操作', width: 90, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_win_eval_bulid_sp_filtered\',1)">查看</a> ';
                    var d = '<a href="#" onclick="remove_special_case(' + index + ')">移除</a> ';
                    return r + d;
                }
            }
        ]]
    });
    // 已筛选
    $("#btn_eval_build_sp_filtered").unbind( "click" );
    $("#btn_eval_build_sp_filtered").bind("click", function () {
        var vcount = $('#table_win_eval_bulid_sp_filtered').datagrid('getRows').length;
        // //湖北:easy_ui的datagrid数据缓存同步有问题，所以加载前需要清空
        // $("#table_win_eval_bulid_sp_filtered").datagrid("loadData", {total:0,rows:[]});
        // vcount = $('#table_win_eval_bulid_sp_filtered').datagrid('getRows').length;
        $("#win_eval_build_sp_filtered").show();
        $("#win_eval_build_sp_filtered").window({
            title:'已筛选',
            width:800,
            height:500,
            collapsible:false,
            minimizable:false,
            maximizable:false,
            modal:true,
            resizable:true,
            cache:false
        });

    });
    // 下一步（评查案件分配）
    $("#view_evaluate_build_sp_assign").unbind( "click" );
    $("#view_evaluate_build_sp_assign").bind("click", function () {
        // 创建评查活动
        click_btn_wind_eval_next(pcxx);
    });

    // 生成评查方案（切换界面）
    $("#view_evaluate_build_sp_generate").unbind("click");
    $("#view_evaluate_build_sp_generate").bind("click",function () {
        // todo生成评查方案
        $('.ZXpc_pcfa_bottom').css('display','none');
        $('#ZXpc_pcfa_tabs').css('display','block');
        $('.ZXpc_pcfa').css('margin-top','51px').css('margin-left','10px').css('padding-right','10px').css('height','calc(100% - 50px)').css('width','calc(100% - 10px)');

        // 首次生成
        generate_pcfa(false);

        init_special_toolbar();
    });

    // tabs导航切换
    $("#ZXpc_pcfa_tabs>ul>li").unbind("click");
    $('#ZXpc_pcfa_tabs>ul>li').bind('click',function(){
        $('#ZXpc_pcfa_tabs>ul>li').removeClass('active');
        $(this).addClass('active');
    });

}

function load_cbt_eval_bulid_sp_sxgz(pcflbm, ywtx) {
    // 获取筛选规则列表
    $('#cbt_eval_bulid_sp_sxgz').combotree({
        method: 'get',
        lines: true,
        panelWidth:270,
        // multiple:true,//湖北:禁止多选，单选筛选规则
        // cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getSxgzByPcmbbm',
        queryParams: {
            pcflbm: pcflbm,
            sslb: "",
            ywtx: ywtx  //添加业务条线编码 LZH 2018年4月2日
        },
        onLoadSuccess:function(node,data){
            if(data.length > 0){
                $('#cbt_eval_bulid_sp_sxgz').combotree('setValue',data[0].id);
            }
            index_addMousedownDiv(this,'cbt_eval_bulid_sp_sxgz');
        }
    });
}

// 重置报告显示面板大小
function resize_div_pcfa_doc() {
    var height = $('#ZXpc_pcfa_tabs').height();
    var width = $('#ZXpc_pcfa_tabs').width();
    // 文书控件展示区域
    $('#div_pcfa_doc').height(height - 50);
    $('#div_pcfa_doc').width(width - 5);

}

// 评查活动启动
function start_eval_special(){
    Confirm("确认", "是否启动评查活动？", function (r) {
        if (r) {

            var obj = new Object();
            obj.PCHDBM = EVAL_DATA.PCHDBM;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/blend/startPchd',
                data: { json: JSON.stringify(obj) },
                dataType: "json",
                success: function (result) {

                    if (result.status == 200){
                        // 初始化操作按钮
                        init_special_toolbar();
                        Alert("启动成功！");
                    } else {
                        Alert(result.note);
                    }
                }

            });
        }
    });
}

// 评查活动结束
function finish_eval_special(){
    Confirm("确认", "是否结束评查活动？", function (r) {
        if (r) {

            var obj = new Object();
            obj.PCHDBM = EVAL_DATA.PCHDBM;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/blend/finishPchd',
                data: { json: JSON.stringify(obj) },
                dataType: "json",
                success: function (result) {

                    if (result.status == 200){
                        // 初始化操作按钮
                        init_special_toolbar();
                        Alert("结束成功！");
                    } else {
                        Alert(result.note);
                    }
                }

            });
        }
    });
}

// 评查活动审批
function deal_eval_special_approve(){

    $("#txt_hd_sp_spjg").textbox('setValue','');//初始化意见框
    $("#txt_hd_sp_spjg").textbox('readonly',false);//初始化意见框


    var openJxss=true;
    // 二次审批或者打开审批意见，隐藏继续审批按钮
    var jsbmJ = userInfo.JSBM;
    for (var jsbmIndex = 0; jsbmIndex < jsbmJ.length; jsbmIndex++) {
        var tempJsbm = jsbmJ[jsbmIndex];
        if (tempJsbm.SPJSBM && tempJsbm.SPJSBM == '60') {
            openJxss=false;
        }

    }



    $("#btn_sp_bg_confirm").css('display','');
    $("#btn_sp_bg_cancel").css('display','');
    // 提交
    $("#btn_sp_bg_confirm").unbind("click");
    $("#btn_sp_bg_confirm").bind("click", function () {

        // JS对象
        var obj = new Object();
        obj.PCSPBM  = EVAL_DATA.PCSPBM;
        obj.SPJL  = $("input[name='is_check_of_sp']:checked").val();
        obj.SPYJ  = $('#txt_hd_sp_spjg').textbox('getValue');
        if(isNull(obj.SPYJ)){
            Alert("请填写审批意见！");
            return;
        }


        //
        if (obj.SPJL == '同意') {
            if (openJxss){
                send_eval_special_approve_fjcz();
            }
        }
        $.ajax({
            url: getRootPath() + "/blend/uptDealFasp",
            data: { json: JSON.stringify(obj)},
            type: 'post',
            async: true,
            dataType: 'json',
            success: function (result) {

                if (result.status == 200){

                    // 初始化操作按钮
                    init_special_toolbar();
                    $('#window_hd_sp').window('close');
                }else{
                    Alert(result.note);
                }
            }
        });
    });

    // 提交
    $("#btn_sp_bg_cancel").unbind("click");
    $("#btn_sp_bg_cancel").bind("click", function () {
        $('#window_hd_sp').window('close');
    });

    $('#window_hd_sp').window('open');
}

// 送审到安管负责人审批
function send_eval_special_approve() {

    // 案件列表DataGrid初始化
    $('#grid_win_pcfa_agfzr').datagrid({
        width:'460px',
        height: '280px',
        fitColumns: true,
        singleSelect: true,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        url: getRootPath()+'/blend/getHdsp',
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
    $("#btnFAConfirm").unbind("click");
    $("#btnFAConfirm").bind("click", function () {

        var row = $("#grid_win_pcfa_agfzr").datagrid("getSelected");
        if(!row){
            Alert("请选择审批人员！");
            return;
        }

        // JS对象
        var obj = new Object();
        obj.PCHDBM  = EVAL_DATA.PCHDBM;
        obj.SPRDWBM  = row.DWBM;
        obj.SPRDWMC  = row.DWMC;
        obj.SPRGH = row.GH;
        obj.SPRXM = row.MC;
        $.ajax({
            url: getRootPath() + "/blend/addPcfaps",
            data: { json: JSON.stringify(obj)},
            type: 'post',
            async: true,
            dataType: 'json',
            success: function (result) {

                if (result.status == 200){

                    // 初始化操作按钮
                    init_special_toolbar();
                    Alert("送审成功！");

                    $('#win_pcfa_sstoanfzr').window('close');
                }else{
                    Alert(result.note);
                }
            }
        });
    });

    // 提交
    $("#btnFACancle").unbind("click");
    $("#btnFACancle").bind("click", function () {
        $('#win_pcfa_sstoanfzr').window('close');
    });

    // 弹出送审窗口
    $('#win_pcfa_sstoanfzr').window('open');
}

// 查看审批记录
function lookup_eval_special_approve() {

    // 审批列表DataGrid初始化
    $('#grid_spxx_list').datagrid({
        fitColumns: true,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        idField: 'ID',
        columns: [[
            {field: 'ID', title: '唯一标示', hidden: true},
            {field: 'SPRDWMC', title: '审批人单位', width: 120},
            {field: 'SPRXM', title: '审批人姓名', width: 100},
            {field: 'SPJL', title: '审批结论', width: 80},
            {field: 'SPYJ', title: '审批意见', width: 140},
            {field: 'SPRQ', title: '审批日期', width: 100},
            {field: 'action', title: '操作', width: 60,
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="watch_spyj(' + index + ')">查看</a> ';
                    return e;
                }
            }
        ]]
    });
    init_spxx_grid();
}

// 加载审批信息
function init_spxx_grid() {
    // JS对象
    var obj = new Object();
    obj.SPWJBM  = EVAL_DATA.PCHDBM;

    $.ajax({
        url: getRootPath() + "/handle/get_spxx",
        data: { json: JSON.stringify(obj)},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {
            if (result.status != 200) {
                Alert("获取审批记录失败！");
                return;
            }
            var realData = JSON.parse(result.value);
            $('#grid_spxx_list').datagrid("loadData",realData);
        }
    });

    $('#win_spyj_sstoanfzr').window('open');
}

// 加载审批信息
function watch_spyj(index) {

    var rowDatas = $('#grid_spxx_list').datagrid('getRows');
    //获取意见值
    var spyj=rowDatas[index].SPYJ;
    var spjl=rowDatas[index].SPJL;
    // $('.spyj_btn').attr('name','is_check_of_spyj');
    //给意见框赋值
    $("input[name='is_check_of_sp'][value="+ spjl +"]").prop("checked",true);
    $("#txt_hd_sp_spjg").textbox('setValue',spyj);
    //控制显示
    $("input[name='is_check_of_sp']").attr("disabled","disabled");
    $('#txt_hd_sp_spjg').textbox('readonly',true);
    $('#btn_sp_bg_confirm').css('display', 'none');
    $('#btn_sp_bg_cancel').css('display', 'none');


    $('#window_hd_sp').window('open');
}

// 生成评查方案
function generate_pcfa(isOverride) {

    $('#ZXpc_pcfa_tabs>ul>li').removeClass('active');
    $('#tab_eval_build_wenshu').addClass('active');

    var displayType = $('.ZXpc_pcfa').css('display');
    if (displayType == "" || displayType == "block"){
        load_eval_build_wenshu();
    }

    // 是否覆盖原方案
    if(isOverride && !isNull(EVAL_DATA.PCFALJ)){
        Confirm("确认", "是否新生成评查方案，并覆盖原方案？", function (r) {
            if (r) {
                gernerate_scheme_file();
            }
        });
    } else {
        gernerate_scheme_file();
    }

}

// 生成评查方案并替换原方案
function gernerate_scheme_file() {

    $.ajax({
        type: "get",
        async: false,
        url: getRootPath() + '/menu/queryWsmb',
        data: { pcflbm: EVAL_DATA.PCFLBM, wsmblb: '1' },
        dataType: "json",
        success: function(data) {
            if(data.code==200){

                if(data.data.length <= 0){
                    Alert("未获取到评查方案模板。");
                    return;
                }

                ShowProgress();

                var obj = new Object();
                obj.PCHDBM = EVAL_DATA.PCHDBM;
                obj.PCZYBM = EVAL_DATA.PCHDBM;
                obj.WSMBBH = data.data[0].wsmbbh;
                obj.WJMC = data.data[0].wsmbmc;
                obj.WSMBLJ = data.data[0].wsmblj;
                obj.WJLX = data.data[0].wsmblb; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
                obj.GXLX = data.data[0].sfgx; //活动内共享
                obj.JZMLH = data.data[0].jzmlh; //评查报告目录
                obj.FJZWJBH = "-1";
                obj.WSCFLJ = "";
                obj.PCZYLX = "0"; //0.评查活动 1.评查案件
                obj.PCSLBM = "";

                // 获取文书并用文书控件加载文书
                $.ajax({
                    type: 'POST',
                    url: getRootPath()+'/blend/generateDoc',
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
                            CloseProgress();

                            // 加载文书
                            var error = OpenFile(getRootPath() + result.value, "TANGER_OCX_PCFA");
                            if (!isNull(error)) {
                                Alert(error);
                                return;
                            }

                            EVAL_DATA.PCFALJ = result.note; //评查方案保存路径

                        } catch (e) {
                            CloseProgress();
                        }
                    }
                });

            }else {
                Alert('获取报告模板失败：' +data.message)
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert('获取报告模板失败。')
        }
    });

}

// 保存评查方案
function save_pcfa_file() {

    document.all("TANGER_OCX_PCFA").CancelLastCommand = true;

    // 编辑保存
    var url = getRootPath() + "/blend/uploadFile?wjlj=" + EVAL_DATA.PCFALJ;
    var fileresult = SaveToUrl(url);
    if (fileresult == null && fileresult != "") {
        Alert("修改评查方案成功。");
    } else {
        Alert("修改评查方案失败。" + fileresult);
    }
}

// 切换表单
function load_eval_build_biaodan(){
    $('.ZXpc_pcfa').css('display','block');
    document.getElementById('div_pcfa_doc').style.display = "none";
    $('.ZXpc_pcfa').css('margin-top','51px').css('margin-left','10px').css('padding-right','10px').css('height','calc(100% - 50px)').css('width','calc(100% - 10px)');
    resize_table_eval_bulid_sp_assign(0);
}

// 切换文书
function load_eval_build_wenshu(){
    resize_div_pcfa_doc();
    $('.ZXpc_pcfa').css('display','none');
    document.getElementById('div_pcfa_doc').style.display = "";

    if(isNull(EVAL_DATA.PCFALJ))
        return;

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/blend/getPcfaPath',
        data: { filePath: EVAL_DATA.PCFALJ },
        dataType: "text",
        success: function (result) {

            try {
                if(!result || isNull(result))
                    return;

                // 加载文书
                var error = OpenFile("./" + result, "TANGER_OCX_PCFA");
                //CloseProgress();
                if (!isNull(error)) {
                    Alert(error);
                    return;
                }

            } catch (e) {
            }
        }
    });

}

function eval_special_marksDataBind(pcxx){

    // 评查方案编辑
    if (FUNCTION_PARAM && FUNCTION_PARAM.PCHDMC && FUNCTION_PARAM.PCMBJ
        && FUNCTION_PARAM.PCZLB && FUNCTION_PARAM.PCZLB.length >= 1
        && FUNCTION_PARAM.PCAJLB && FUNCTION_PARAM.PCAJLB.length >= 1){

        EVAL_DATA = FUNCTION_PARAM;

        ShowProgress();

        try {
            // todo生成评查方案
            $('.ZXpc_pcfa_bottom').css('display','none');
            $('#ZXpc_pcfa_tabs').css('display','block');
            $('.ZXpc_pcfa').css('margin-top','51px').css('margin-left','10px').css('padding-right','10px').css('height','calc(100% - 50px)').css('width','calc(100% - 10px)');

            load_eval_build_biaodan();

            // 初始化功能页面，加载评查信息、评查组信息
            init_eval_build_special(EVAL_DATA);

            // 跳转到案件分配
            init_eval_build_special_assign();

        }catch (e){
        }
        CloseProgress();

    }else{

        // 评查方案信息初始化
        EVAL_DATA = new Object();
        EVAL_DATA.PCDWBM = "";
        EVAL_DATA.PCFLBM = pcxx.PCFLBM;
        EVAL_DATA.PCHDBM = "";
        EVAL_DATA.SXGZJ = "";
        EVAL_DATA.SFSS = "Y";
        EVAL_DATA.SFFY = "Y";
        EVAL_DATA.PCKSSJ = "";
        EVAL_DATA.PCJSSJ = "";
        EVAL_DATA.PCHDMC = "";
        EVAL_DATA.PCMBJ = "";
        EVAL_DATA.SM = "";
        EVAL_DATA.PCZLB = new Array();
        EVAL_DATA.PCAJLB = new Array();

    }

    // 重点评查的日期范围为2013-01-01至2017-12-31
    //完成日期
    $('#date_eval_build_sp_begin').datebox({
        editable: false,
        value: '2013-01-01'
        // value: new Date().getFullYear() + '-01-01'
    });

    $('#date_eval_build_sp_end').datebox({
        editable: false,
        value: '2017-12-31'
        // value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    $('#date_eval_build_sp_assign_begin').datebox({
        editable: false,
        value: '2013-01-01'
        // value: new Date().getFullYear() + '-01-01'
    });

    $('#date_eval_build_sp_assign_end').datebox({
        editable: false,
        value: '2017-12-31'
        // value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });
}

// 获取案件列表（查询）
function load_table_eval_bulid_sp_filter(pcxx) {
    var bmsah = $('#txt_eval_build_sp_bmsah').textbox('getValue').trim();
    var ajmc = $('#txt_eval_build_sp_ajmc').textbox('getValue').trim();
    var cbrxm = $('#txt_eval_build_sp_cbr').textbox('getValue').trim();
    var sxgzbm = $('#cbt_eval_bulid_sp_sxgz').combotree('getValue');
    var sxgzmc = $('#cbt_eval_bulid_sp_sxgz').combotree('getText').trim();
    if((sxgzbm.substring(9,10)=="9" || sxgzmc.indexOf("自定义案件") > 0) &&
        bmsah=="" && ajmc == "" && cbrxm==""){
        Alert("自定义案件查询请输入查询条件!");
        return;
    }
    var isReturn = false;
    //湖北 已评查的案件在查询时需提示案件已在评查办理中
    if(bmsah!='' || bmsah.length > 0){
        $.ajax({
            url: getRootPath() + "/filter/getAjxxByBmsah",
            data: { bmsah: bmsah },
            type: 'post',
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.status != 200) {
                    Alert(result.note);
                    isReturn = true;
                }
            }
        });
    }
    if(isReturn){
        return;
    }
    var pchdbm = $('#cbt_eval_bulid_sp_pcmb').combotree('getValue');
    // JS对象
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = pchdbm;
    obj.GZDWBM = '';
    obj.SXGZBM = $('#cbt_eval_bulid_sp_sxgz').combotree('getValues').join(',');
    obj.CBDWBM =  $('#cbt_eval_bulid_sp_dwbm').combotree('getValues').join(",");
    obj.CBBMBM = $('#txt_eval_build_sp_cbbm').combotree('getValues').join(",");
    obj.AJLB = '';
    obj.BMSAH = bmsah;
    obj.AJMC = ajmc;
    obj.CBRXM = cbrxm;
    obj.XYR = '';
    obj.AY = '';
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQBNG = '';
    obj.WCRQBNG = $('#date_eval_build_sp_begin').datebox('getValue');
    obj.WCRQEND = $('#date_eval_build_sp_end').datebox('getValue');
    obj.GZRQBNG = $('#date_eval_build_sp_begin').datebox('getValue');
    obj.GZRQEND = $('#date_eval_build_sp_end').datebox('getValue');
    obj.ZDYCXTJ = get_eval_build_sp_zdycxtj();

    $('#table_eval_bulid_sp_filter').datagrid({
        url: getRootPath()+'/filter/getSjsx',
        queryParams: { json : JSON.stringify(obj) }
    });
}

// 获取自定义查询条件
function get_eval_build_sp_zdycxtj() {
    var bmsahs = "";
    var rows = $("#table_win_eval_bulid_sp_filtered").datagrid("getRows");
    for (var i = 0; i < rows.length; i++){
        if(rows[i]){
            if (i == 0){
                bmsahs = bmsahs + "'" + rows[i].BMSAH + "'"
            } else{
                bmsahs = bmsahs + ",'" + rows[i].BMSAH + "'"
            }
        }
    }

    return isNull(bmsahs) ? "" : " AND aj.bmsah NOT IN (" + bmsahs + ")";
}

// 筛选案件
function filter_special_case(index,row) {
    window.event ? window.event.cancelBubble = true : e.stopPropagation();
    var index = $("#table_eval_bulid_sp_filter").datagrid("getRowIndex",row);
    $("#table_eval_bulid_sp_filter").datagrid("selectRow",index);
    var remover = $("#table_eval_bulid_sp_filter").datagrid("getSelected");
    if(!remover)
        return;

    var index = $("#table_win_eval_bulid_sp_filtered").datagrid("getRowIndex", remover.BMSAH);
    if (index == -1){
        $('#table_win_eval_bulid_sp_filtered').datagrid('appendRow',{
            BMSAH: remover.BMSAH,
            AJMC: remover.AJMC,
            AJLBMC: remover.AJLBMC,
            DWBM: remover.DWBM,
            DWMC: remover.DWMC,
            CBR: remover.CBR,
            SLRQ: remover.SLRQ,
            WCBZRQ: remover.WCBZRQ,
            SXGZBM: remover.SXGZBM
        });
    }
    var index = $("#table_eval_bulid_sp_filter").datagrid("getRowIndex", remover.BMSAH);
    $("#table_eval_bulid_sp_filter").datagrid("deleteRow", index);

    $("#btn_eval_build_sp_filtered").text("已筛选(" + $('#table_win_eval_bulid_sp_filtered').datagrid('getRows').length + ")");
}

// 移除案件
function remove_special_case(index) {

    var remover = $("#table_win_eval_bulid_sp_filtered").datagrid("getSelected");
    if(!remover)
        return;
    $('#table_eval_bulid_sp_filter').datagrid('appendRow',{
        BMSAH: remover.BMSAH,
        AJMC: remover.AJMC,
        AJLBMC: remover.AJLBMC,
        DWBM: remover.DWBM,
        DWMC: remover.DWMC,
        CBR: remover.CBR,
        SLRQ: remover.SLRQ,
        WCBZRQ: remover.WCBZRQ,
        SXGZBM: remover.SXGZBM
    });
    var index = $("#table_win_eval_bulid_sp_filtered").datagrid("getRowIndex", remover.BMSAH);
    $("#table_win_eval_bulid_sp_filtered").datagrid("deleteRow", index);

    $("#btn_eval_build_sp_filtered").text("已筛选(" + $('#table_win_eval_bulid_sp_filtered').datagrid('getRows').length + ")");
}

// 初始化功能页面，加载评查基本信息、评查组信息
function init_eval_build_special(data) {

    // 评查活动信息
    $('#txt_eval_build_sp_pchdmc').textbox('setValue', data.PCHDMC);
    $('#txt_eval_build_sp_bz').textbox('setValue', data.SM);

    // 评查组信息
    PCZ_LIST = new Array();
    $("#div_eval_build_sp_pcz").empty();
    data.PCZLB.forEach(function (item) {
        if(item){
            append_eval_build_sp_pcz(item);
        }
    });

    // 评查信息保存按钮
    $("#btn_save_eval_build_sp_info").unbind( "click" );
    $("#btn_save_eval_build_sp_info").bind("click", function () {

        var obj = new Object();
        obj.PCDWBM = EVAL_DATA.PCDWBM;
        obj.PCFLBM = EVAL_DATA.PCFLBM;
        obj.PCHDBM = EVAL_DATA.PCHDBM;
        obj.SXGZJ = "";
        obj.SFSS = "Y";
        obj.SFFY = "Y";
        obj.PCKSSJ = "";
        obj.PCJSSJ = "";
        obj.PCHDMC = $('#txt_eval_build_sp_pchdmc').textbox('getValue');
        obj.PCMBJ = $('#cbt_eval_bulid_sp_pcmb').combotree('getValue');
        obj.SM = $('#txt_eval_build_sp_bz').textbox('getValue');

        // 更新评查方案信息
        $.ajax({
            url: getRootPath() + "/blend/updPchd",
            data: { json: JSON.stringify(obj)},
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data.status == 200){
                    // 更新评查方案信息
                    EVAL_DATA.SXGZJ = "";
                    EVAL_DATA.SFSS = "Y";
                    EVAL_DATA.SFFY = "Y";
                    EVAL_DATA.PCKSSJ = "";
                    EVAL_DATA.PCJSSJ = "";
                    EVAL_DATA.PCHDMC = $('#txt_eval_build_sp_pchdmc').textbox('getValue');
                    EVAL_DATA.PCMBJ = $('#cbt_eval_bulid_sp_pcmb').combotree('getValue');
                    EVAL_DATA.SM = $('#txt_eval_build_sp_bz').textbox('getValue');
                    $('#btn_save_eval_build_sp_info').hide();
                }else {
                    Alert(data.note);
                }
            }
        });

    });
    $('#txt_eval_build_sp_pchdmc').textbox({
        onChange: function (newValue, oldValue) {
            $('#btn_save_eval_build_sp_info').show();
        }
    });
    $('#cbt_eval_bulid_sp_pcmb').combotree({
        onChange: function (newValue, oldValue) {
            if(!isNull(oldValue)){
                $('#btn_save_eval_build_sp_info').show();
            }
        }
    });
    $('#txt_eval_build_sp_bz').textbox({
        onChange: function (newValue, oldValue) {
            $('#btn_save_eval_build_sp_info').show();
        }
    });

    // 编辑评查方案
    IS_EDIT = true;
}

// 创建评查方案（添加活动、添加评查组、添加评查案件）
function click_btn_wind_eval_next(pcxx){

    if(IS_EDIT){
        return;
    }

    // JS对象,需要传输的值
    var obj = new Object();
    obj.PCDWBM = IS_EDIT ? EVAL_DATA.PCDWBM : "";
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = IS_EDIT ? EVAL_DATA.PCHDBM : "";
    obj.SXGZJ = "";
    obj.SFSS = "Y";
    obj.SFFY = "Y";
    obj.PCKSSJ = "";
    obj.PCJSSJ = "";
    obj.PCHDMC = $('#txt_eval_build_sp_pchdmc').textbox('getValue');
    obj.PCMBJ = $('#cbt_eval_bulid_sp_pcmb').combotree('getValue');
    obj.SM = $('#txt_eval_build_sp_bz').textbox('getValue');
    obj.PCZLB = PCZ_LIST;
    obj.PCAJLB = $("#table_win_eval_bulid_sp_filtered").datagrid("getRows");

    // 验证
    if (isNull(obj.PCHDMC)){
        Alert("请输入评查活动名称！");
        return;
    }
    if (isNull(obj.PCMBJ)){
        Alert("请选择评查模板！");
        return;
    }
    if (obj.PCZLB == null || obj.PCZLB.length <= 0){
        Alert("请建立评查组！");
        return;
    }
    if (obj.PCAJLB == null || obj.PCAJLB.length <= 0){
        Alert("请筛选评查案件！");
        return;
    }

    ShowProgress();
    // 生成评查方案
    $.ajax({
        url: getRootPath() + "/blend/addPcfa",
        data: { json: JSON.stringify(obj)},
        type: 'post',
        async: false,
        dataType: 'json',
        success: function (data) {
            if (data.status == 200){

                try {
                    // 评查活动信息
                    EVAL_DATA = data.value;

                    // 初始化功能页面，加载评查信息
                    init_eval_build_special(EVAL_DATA);

                    // 跳转到案件分配
                    init_eval_build_special_assign();
                    //活动生成后，评查模板不可以在改变
                    if(typeof(obj.PCMBJ) != "undefined" ){
                        // 评查模板
                        $('#cbt_eval_bulid_sp_pcmb').combotree({
                            method: 'get',
                            lines: true,
                            onShowPanel: index_onShowPanel,
                            onHidePanel: index_onHidePanel,
                            url: getRootPath()+'/template/templateByPcmbbm',
                            queryParams: {
                                pcflbm: obj.PCFLBM,
                                pcmbbm: obj.PCMBJ
                            },
                            onLoadSuccess: function (node,data) {
                                if (data.length > 0){
                                    $('#cbt_eval_bulid_sp_pcmb').combotree('setValue', data[0].id);
                                    load_cbt_eval_bulid_sp_sxgz(pcxx.PCFLBM, data[0].attributes.YWTX);
                                    load_cbt_eval_bulid_sp_assign_sxgz(pcxx.PCFLBM, data[0].attributes.YWTX);
                                }
                                index_addMousedownDiv(this,'cbt_eval_bulid_sp_pcmb');
                            },
                            onSelect: function (node) {
                                load_cbt_eval_bulid_sp_sxgz(pcxx.PCFLBM, node.attributes.YWTX);
                                load_cbt_eval_bulid_sp_assign_sxgz(pcxx.PCFLBM, node.attributes.YWTX);
                            }
                        });
                    }

                }catch (e){

                }
                CloseProgress();

            }else {
                CloseProgress();
                Alert(data.note);
            }
        }
    });

}

// 评查分配界面初始化
function init_eval_build_special_assign() {

    $(".ZXpc_pcfa_cen").css('display','none');
    $(".ZXpc_pcfa_cen1").css('display','block');
    $("#view_evaluate_build_sp_assign").css('display','none');
    $("#view_evaluate_build_sp_generate").css('display','block');
    $("#view_evaluate_build_sp_approve").css('display','block');

    if(!showTj){
        resize_table_eval_bulid_sp_assign(110);
    }
    if(!showTot){
        resize_table_eval_bulid_sp_assign(50);
    }

    // 案件列表
    $('#table_eval_bulid_sp_assign').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        columns:[[
            {field:'PCDWBM',title:'评查单位',hidden:true},
            {field:'PCFLBM',title:'评查分类',hidden:true},
            {field:'PCHDBM',title:'评查活动',hidden:true},
            {field:'PCMBBM',title:'评查模板',hidden:true},
            {field:'PCSAH',title:'评查受案号',width:220},
            {field:'BMSAH',title:'部门受案号',width:220 },
            {field:'AJMC',title:'案件名称',width:220,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:150},
            {field:'DWMC',title:'承办单位',width:100},
            {field:'CBR',title:'承办检察官',width:90},
            {field:'SLRQ',title:'受理日期',width:100,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'WCBZRQ',title:'完成日期',width:100,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'DWBM',title:'单位编码',hidden:true},
            {field:'SXGZBM',title:'筛选规则编码',hidden:true},
            {field:'PCSLBM',title:'评查受理编码',hidden:true},
            {field:'PCZ_BM',title:'评查组编码',hidden:true},
            {field:'PCZ_MC',title:'评查组名称',width:90},
            {field:'PCR_DWBM',title:'评查员单位编码',hidden:true},
            {field:'PCR_DWMC',title:'评查员单位',width:100},
            {field:'PCR_GH',title:'评查员工号',hidden:true},
            {field:'PCR_MC',title:'评查员名称',width:90},
            {field:'PCJDMS',title:'评查状态',width:100},
            {field: 'action', title: '操作', width: 90, align: 'center',
                formatter: function (value, row, index) {
                    var r;
                    var d;
                    if(parseInt(row.PCJDBH) <= parseInt("005") || typeof(row.PCJDBH) == "undefined"){
                         r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_bulid_sp_assign\',1)">查看</a> ';
                         d = '<a href="#" onclick="assign_special_case(' + index + ')">分配</a> ';
                    }else {
                        r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_bulid_sp_assign\',1)">查看</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                        d = '';
                    }

                    return r + d;
                }
            }
        ]],
        groupField: 'PCR_MC',
        view: groupview,
        groupFormatter: function (value, rows) {
            var result = "";
            if (value && !isNull(value)){
                result = value;
            }else{
                result = "未分配";
            }

            return result + '(' + rows.length + ')';
        }
    });
    $('#table_eval_bulid_sp_assign').datagrid('loadData', EVAL_DATA.PCAJLB);

    // 自动分配
    $("#btn_eval_build_sp_assign").unbind( "click" );
    $("#btn_eval_build_sp_assign").bind("click", function () {

        // 确定
        $("#btn_win_eval_build_sp_assign_confirm").unbind( "click" );
        $("#btn_win_eval_build_sp_assign_confirm").bind("click", function () {
            assign_random_pcy();
        });

        // 清空内部缓存：
        $("#txt_win_eval_build_sp_assign_fpsf").textbox('setText',"");
        $('#win_eval_build_sp_assign').window('open');
    });

    // 筛选
    $("#btn_eval_build_sp_assign_filter").unbind( "click" );
    $("#btn_eval_build_sp_assign_filter").bind("click", function () {

        // 筛选窗体
        $("#win_eval_build_sp_assign_filter").show();
        $("#win_eval_build_sp_assign_filter").window({
            title:'案件筛选',
            width:1000,
            height:600,
            collapsible:false,
            minimizable:false,
            maximizable:false,
            modal:true,
            resizable:true
        });

    });
}

// 随机分配：每一个案件通过随机数，指定评查人员
function assign_random_pcy() {

    ShowProgress();
    var type = $("#cbx_win_eval_build_sp_assign_fpsf").combobox('getValue')
    var obj = new Object();
    obj.type = type;
    obj.pchdbm = EVAL_DATA.PCHDBM;
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/blend/invokeSf',
        data: obj,
        async: true,
        dataType: 'json',
        success: function (result) {
            if (result.status == 200) {

                // 评查案件信息
                EVAL_DATA.PCAJLB = result.value;
                $('#table_eval_bulid_sp_assign').datagrid('loadData', EVAL_DATA.PCAJLB);
                CloseProgress();

            } else {
                CloseProgress();
                Alert("分配算法执行失败");
            }
        }
    });

    $("#win_eval_build_sp_assign").window('close');
}

// 分配单个评查案件
function assign_special_case(index) {
    window.event ? window.event.cancelBubble = true : e.stopPropagation();
    $("#table_eval_bulid_sp_assign").datagrid("selectRow",index);
    var aj = $("#table_eval_bulid_sp_assign").datagrid("getSelected");
    if(!aj){
        Alert("请选择评查案件！");
        return;
    }
    var currIndex = $("#table_eval_bulid_sp_assign").datagrid("getRowIndex", aj.BMSAH);
    if(currIndex == -1){
        Alert("请选择评查案件！");
        return;
    }

    $("#table_win_eval_build_sp_assign_manual").datagrid({
        width: 'auto',
        height: 265,
        striped: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        resizable:false,
        idField: 'ID',
        columns: [[
            {field: 'ID', title: '标识', hidden:true},
            {field: 'DWBM', title: '单位编码', hidden:true},
            {field: 'DWMC', title: '单位',width:110},
            {field: 'GH', title: '工号', hidden:true},
            {field: 'MC', title: '姓名',width:90},
            {field: 'PCZBM', title: '评查组编码', hidden:true},
            {field: 'PCZMC', title: '评查组',width:100},
            {field: 'JSMC', title: '角色', width: 90},
            {field: 'JSBM', title: '角色编码', hidden:true}
        ]]
    });
    $('#table_win_eval_build_sp_assign_manual').datagrid('loadData', []);

    // 评查员列表
    for (var i = 0; i < PCZ_LIST.length; i++){
        if (!PCZ_LIST[i].PCYARR || PCZ_LIST[i].PCYARR.length <= 0)
            continue;

        for (var j = 0; j < PCZ_LIST[i].PCYARR.length; j++){
            if (PCZ_LIST[i].PCYARR[j].ID == (aj.PCR_DWBM + aj.PCR_GH))
                continue;

            $('#table_win_eval_build_sp_assign_manual').datagrid('appendRow',{
                ID: PCZ_LIST[i].PCYARR[j].ID,
                DWBM: PCZ_LIST[i].PCYARR[j].DWBM,
                DWMC: PCZ_LIST[i].PCYARR[j].DWMC,
                GH: PCZ_LIST[i].PCYARR[j].GH,
                MC: PCZ_LIST[i].PCYARR[j].MC,
                PCZBM: PCZ_LIST[i].PCZBM,
                PCZMC: PCZ_LIST[i].PCZMC,
                JSMC: PCZ_LIST[i].PCYARR[j].JSMC,
                JSBM: PCZ_LIST[i].PCYARR[j].JSBM
            });
        }
    }

    // 确定
    $("#btn_win_eval_build_sp_assign_manual_confirm").unbind( "click" );
    $("#btn_win_eval_build_sp_assign_manual_confirm").bind("click", function () {

        var row = $("#table_win_eval_build_sp_assign_manual").datagrid("getSelected");
        if(!row){
            Alert("请选择评查人员！");
            return;
        }

        ShowProgress();
        var obj = new Object();
        obj.PCDWBM = aj.PCDWBM;
        obj.PCFLBM = aj.PCFLBM;
        obj.PCHDBM = aj.PCHDBM;
        obj.PCMBBM = aj.PCMBBM;
        obj.DWBM = aj.DWBM;
        obj.BMSAH = aj.BMSAH;
        obj.PCSLBM = aj.PCSLBM;
        obj.PCSAH = aj.PCSAH;
        obj.PCZ_BM = row.PCZBM;
        obj.PCZ_MC = row.PCZMC;
        obj.PCR_DWBM = row.DWBM;
        obj.PCR_DWMC = row.DWMC;
        obj.PCR_GH = row.GH;
        obj.PCR_MC = row.MC;
        var arr = new Array();
        arr.push(obj);
        // 分配案件
        $.ajax({
            url: getRootPath() + "/blend/assignCase",
            data: { json: JSON.stringify(arr)},
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data.status == 200){

                    // 更新评查案件列表
                    $("#table_eval_bulid_sp_assign").datagrid("updateRow",{
                        index: index,
                        row: {
                            PCZ_BM : row.PCZBM,
                            PCZ_MC : row.PCZMC,
                            PCR_GH : row.GH,
                            PCR_MC : row.MC,
                            PCR_DWBM : row.DWBM,
                            PCR_DWMC : row.DWMC
                        }
                    });
                    EVAL_DATA.PCAJLB = $('#table_eval_bulid_sp_assign').datagrid('getRows');
                    $('#table_eval_bulid_sp_assign').datagrid('loadData', EVAL_DATA.PCAJLB);

                    CloseProgress();
                }else {
                    CloseProgress();
                    Alert(data.note);
                }
            }
        });
        $("#win_eval_build_sp_assign_manual").window('close');
    });

    $('#win_eval_build_sp_assign_manual').window('open');
}

// 评查组人员角色框编辑功能
$.extend($.fn.datagrid.methods, {
    editCell: function(jq,param){
        return jq.each(function(){
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] != param.field){
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    }
});
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#table_eval_build_sp_pcz_ry').datagrid('validateRow', editIndex)){

        var ed = $('#table_eval_build_sp_pcz_ry').datagrid('getEditor', {index:editIndex,field:'JSBM'});
        if (ed && ed.target){
            var JSMC = $(ed.target).combobox('getText');
            $('#table_eval_build_sp_pcz_ry').datagrid('getRows')[editIndex]['JSMC'] = JSMC;
        }

        $('#table_eval_build_sp_pcz_ry').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field){
    if (endEditing()){
        $('#table_eval_build_sp_pcz_ry').datagrid('selectRow', index)
            .datagrid('editCell', {index:index,field:field});
        editIndex = index;
    }
}

// 评查小组弹窗
function alert_win_eval_build_sp_pcz(pczbm) {
    // 评查组
    var pcz;
    if (pczbm){
        pcz = get_eval_build_sp_pcz(pczbm);
        pcz.PCHDBM = IS_EDIT ? EVAL_DATA.PCHDBM : "";
    } else {
        // 评查组信息
        pcz = new Object();
        pcz.PCHDBM = IS_EDIT ? EVAL_DATA.PCHDBM : "";
        pcz.PCZBM = "PCZ000" + PCZ_LIST.length;
        pcz.PCZMC = "";
        pcz.SM = "";

        // 评查员列表
        var pcyArr = new Array();
        pcz.PCYARR = pcyArr;
    }
    $('#txt_win_eval_build_sp_pcz_mc').textbox('setValue', pcz.PCZMC);
    $('#txt_win_eval_build_sp_pcz_bz').textbox('setValue', pcz.SM);

    // 人员库列表
    $('#table_eval_build_sp_pcz_ryk').datagrid({

        striped: true,
        fitColumns: true,
        rownumbers: true,
        resizable:false,
        idField: 'ID',
        columns: [[
            {field: 'ID', title: '标识', hidden:true},
            {field: 'DWBM', title: '单位编码', hidden:true},
            {field: 'DWMC', title: '单位名称',width:150},
            {field: 'GH', title: '工号', hidden:true},
            {field: 'MC', title: '名称',width:120}
        ]]
    });
    $('#table_eval_build_sp_pcz_ryk').datagrid('loadData', []);

    // 小组人员
    $("#table_eval_build_sp_pcz_ry").datagrid({
        width: 'auto',
        striped: true,
        fitColumns: true,
        singleSelect: true,
        onClickCell: onClickCell,
        rownumbers: true,
        resizable:false,
        idField: 'ID',
        columns: [[
            {field: 'ID', title: '标识', hidden:true},
            {field: 'DWBM', title: '单位编码', hidden:true},
            {field: 'DWMC', title: '单位名称',width:100},
            {field: 'GH', title: '工号', hidden:true},
            {field: 'MC', title: '名称',width:90},
            {field: 'JSMC', title: '角色名称', hidden:true},
            {field: 'JSBM', title: '角色编码', width: 90,
                formatter: function (value, row) {
                    return row.JSMC;
                },
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'JSBM',
                        textField: 'JSMC',
                        method: 'get',
                        wincom:true,
                        data: [{
                            JSBM: '101',
                            JSMC: '组员'
                        }, {
                            JSBM: '102',
                            JSMC: '组长'
                        }],
                        required: true
                    }
                }
            }
        ]],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件，保存代码是否这里写？？？
            //console.info(rowData);
            editRow = undefined;
        }
    });

    $('#table_eval_build_sp_pcz_ry').datagrid('loadData', pcz.PCYARR);

    var obj = new Object();
    obj.DWBM_RY = "";
    obj.YWBM = "";
    $.ajax({
        url: getRootPath() + "/blend/getPckryAll",
        data: { json: JSON.stringify(obj)},
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (data) {
            if (data.status == 200){
                // 人员库列表
                var rykList = data.value;

                // 排除已添加人员
                rykList.forEach(function (n) {
                    if (!is_eval_build_sp_pcz_ry(n.ID)) {
                        $('#table_eval_build_sp_pcz_ryk').datagrid('appendRow',{
                            ID: n.ID,
                            DWBM: n.DWBM,
                            DWMC: n.DWMC,
                            GH: n.GH,
                            MC: n.MC
                        });
                    }
                });
                $('#table_eval_build_sp_pcz_ryk').datagrid('clearSelections');
            }else {
                Alert(data.note);
            }
        }
    });

    // 添加
    $("#btn_win_eval_build_sp_pcz_add").unbind( "click" );
    $("#btn_win_eval_build_sp_pcz_add").bind("click", function () {
        var remover = $("#table_eval_build_sp_pcz_ryk").datagrid("getSelections");
        if(!remover)
            return;
        var index=[];
        for(var z=0;z<remover.length;z++){
            $('#table_eval_build_sp_pcz_ry').datagrid('appendRow',{
                ID: remover[z].ID,
                DWBM: remover[z].DWBM,
                DWMC: remover[z].DWMC,
                GH: remover[z].GH,
                MC: remover[z].MC,
                JSMC: '组员',
                JSBM: '101'
            });
            index.push($("#table_eval_build_sp_pcz_ryk").datagrid("getRowIndex", remover[z].ID));
        }
        index = index.sort(sortNumber)
        for(var j=0;j<index.length;j++){
            $("#table_eval_build_sp_pcz_ryk").datagrid("deleteRow", index[j]-j);
        }

    });

    // 移除
    $("#btn_win_eval_build_sp_pcz_yc").unbind( "click" );
    $("#btn_win_eval_build_sp_pcz_yc").bind("click", function () {
        var remover = $("#table_eval_build_sp_pcz_ry").datagrid("getSelected");
        if(!remover)
            return;
        var index = $("#table_eval_build_sp_pcz_ry").datagrid("getRowIndex", remover.ID);
        if(index == "-1")
            return;
        $('#table_eval_build_sp_pcz_ryk').datagrid('appendRow',{
            ID: remover.ID,
            DWBM: remover.DWBM,
            DWMC: remover.DWMC,
            GH: remover.GH,
            MC: remover.MC
        });
        $("#table_eval_build_sp_pcz_ry").datagrid("deleteRow", index);
    });

    // 获取小组及成员信息
    $("#btn_eval_build_sp_pcz_confirm").unbind( "click" );
    $("#btn_eval_build_sp_pcz_confirm").bind("click", function () {
        $('td[field="DWBM"]').click();
        // 评查组信息
        pcz.PCZMC = $('#txt_win_eval_build_sp_pcz_mc').textbox('getValue');
        pcz.SM = $('#txt_win_eval_build_sp_pcz_bz').textbox('getValue');
        pcz.PCYARR = $("#table_eval_build_sp_pcz_ry").datagrid("getRows");

        // 验证
        if (isNull(pcz.PCZMC)){
            Alert("请输入评查组名称！");
            return;
        }
        if (pcz.PCYARR == null || pcz.PCYARR.length <= 0){
            Alert("请选择评查员！");
            return;
        }

        // 添加或更新评查组信息
        addOrUpd_eval_build_sp_pcz(pcz);

        //展开评查小组div
        if(!showTj){
            showTj = true
            openDiv($(".ZXpc_pcfa_but"), 2);
        }
    });

    //单位下拉框加载
    load_window_kjrk_content_tj_dw();

    $("#win_eval_build_sp_pcz").window('open');

}

function sortNumber(a,b) {
    return a - b
}

//评查小组单位下拉框加载
function load_window_kjrk_content_tj_dw() {;
    $('#window_kjrk_content_tj_dw').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/organization/getDwbmTree',
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onSelect: function (node) {
            var selectId = node.id;
            var filterData = [];
            var rykRows = $('#table_eval_build_sp_pcz_ryk').datagrid("getRows");
            for (var i = 0; i < rykRows.length; i++) {
                if (selectId == rykRows[i].DWBM) {
                    filterData.push(rykRows[i]);
                }
            }
            $('#table_eval_build_sp_pcz_ryk').datagrid("loadData", filterData);
        },
        onLoadSuccess: function (node, data) {
            index_addMousedownDiv(this,'window_kjrk_content_tj_dw');
        }
    });
}

// 添加或者更新评查组
function addOrUpd_eval_build_sp_pcz(pcz) {
    var index = get_eval_build_sp_pcz_index(pcz.PCZBM);
    if (index == -1){
        // 编辑模式，直接更新数据库
        if (!IS_EDIT){
            append_eval_build_sp_pcz(pcz);
            $("#win_eval_build_sp_pcz").window('close');
        }else{
            // 添加评查组
            $.ajax({
                url: getRootPath() + "/blend/addPcz",
                data: { json: JSON.stringify(pcz)},
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.status == 200){

                        // 评查组信息更新
                        pcz.PCZBM = data.value;

                        append_eval_build_sp_pcz(pcz);
                        $("#win_eval_build_sp_pcz").window('close');

                    }else {
                        Alert(data.note);
                    }
                }
            });
        }
    } else  {
        // 编辑模式，直接更新数据库
        if (!IS_EDIT){
            replace_eval_build_sp_pcz(index, pcz);
            $("#win_eval_build_sp_pcz").window('close');
        }else{
            // 更新评查组
            $.ajax({
                url: getRootPath() + "/blend/addPcz",
                data: { json: JSON.stringify(pcz)},
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.status == 200){

                        replace_eval_build_sp_pcz(index, pcz);
                        $("#win_eval_build_sp_pcz").window('close');

                    }else {
                        Alert(data.note);
                    }
                }
            });
        }
    }
}

// 删除评查小组
function delete_eval_build_sp_pcz(pczbm) {

    Confirm("确认", "确定删除？", function (r) {
        if (r) {

            // 编辑模式，直接更新数据库
            if (!IS_EDIT){
                remove_eval_build_sp_pcz(pczbm);
            }else{

                var pcz = get_eval_build_sp_pcz(pczbm);
                // 验证是否已分配案件给评查组
                $.ajax({
                    url: getRootPath() + "/blend/valPcz",
                    data: { json: JSON.stringify(pcz)},
                    type: 'post',
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.status == 200){
                            var cnt = data.value;
                            if (cnt && cnt != "0"){
                                Alert("评查组已分配有 " + cnt + " 件案件，请在调整分配信息后移除。");
                                return;
                            }else{
                                // 删除评查组
                                $.ajax({
                                    url: getRootPath() + "/blend/delPcz",
                                    data: { json: JSON.stringify(pcz)},
                                    type: 'post',
                                    async: false,
                                    dataType: 'json',
                                    success: function (data) {
                                        if (data.status == 200){
                                            remove_eval_build_sp_pcz(pczbm);
                                        }else {
                                            Alert(data.note);
                                        }
                                    }
                                });
                            }
                        }else {
                            Alert(data.note);
                        }
                    }
                });
            }
        }
    });
}

// 添加评查小组
function append_eval_build_sp_pcz(pcz) {

    // 判断评查组是否已存在
    var index = get_eval_build_sp_pcz_index(pcz.PCZBM);
    if (index != -1)
        return;

    // 生成评查小组HTML
    var html = "<div id=\"" + pcz.PCZBM + "\" class=\"ZXpc_pcfa_xz_rys\" style=\"margin-left: 0\">";
    html += genarate_eval_build_sp_pcz(pcz);
    html += "</div>";
    $("#div_eval_build_sp_pcz").append(html);

    // 评查组信息写入EVAL_DATA
    PCZ_LIST.push(pcz);
}

// 更新评查小组
function replace_eval_build_sp_pcz(index, pcz) {

    // 更新评查组HTML
    $("#" + pcz.PCZBM + "").html(genarate_eval_build_sp_pcz(pcz));

    // 评查组信息写入EVAL_DATA
    PCZ_LIST.splice(index, 1, pcz);
}

// 移除评查组
function remove_eval_build_sp_pcz(pczbm) {

    // 清除EVAL_DATA中评查组信息
    var index = get_eval_build_sp_pcz_index(pczbm);
    PCZ_LIST.splice(index, 1);

    // 移除评查组HTML
    $("#" + pczbm + "").remove();
}

// 获取评查组信息
function get_eval_build_sp_pcz(pczbm) {

    // 获取评查组信息
    var pcz;
    for (var i = 0; i < PCZ_LIST.length; i++){
        if (PCZ_LIST[i].PCZBM == pczbm){
            pcz = PCZ_LIST[i];
            break;
        }
    }

    return pcz;
}

// 获取评查组Index
function get_eval_build_sp_pcz_index(pczbm) {

    var index = -1;
    for (var i = 0; i < PCZ_LIST.length; i++){
        if (PCZ_LIST[i].PCZBM == pczbm){
            index = i;
            break;
        }
    }

    return index;
}

// 是否评查组成员
function is_eval_build_sp_pcz_ry(id) {

    for (var i = 0; i < PCZ_LIST.length; i++){
        if (!PCZ_LIST[i].PCYARR || PCZ_LIST[i].PCYARR.length <= 0)
            continue;

        for (var j = 0; j < PCZ_LIST[i].PCYARR.length; j++){
            if (PCZ_LIST[i].PCYARR[j].ID == id) {
                return true;
            }
        }
    }

    return false;
}

// 生成评查小组HTML
function genarate_eval_build_sp_pcz(pcz) {
    var html = "";
    // 评查组信息
    html += "<div class=\"ZXpc_pcfa_xz_zz\"></div>";
    html += "<div class=\"ZXpc_pcfa_xz_one\">";
    html += "    <p style=\"margin-top: -4px\">";
    html += "        <span>组名</span>&nbsp;&nbsp;";
    html += "        <span>" + pcz.PCZMC + "</span>";
    html += "    </p>";
    html += "    <p class=\"ap\">";
    html += "        <span>组长</span>&nbsp;&nbsp;";
    for (var i = 0; i < pcz.PCYARR.length; i++){
        if (pcz.PCYARR[i].JSBM == "102"){
            pcz.PCYARR[i].ID = pcz.PCYARR[i].DWBM + pcz.PCYARR[i].GH;
            html += "<span id=\"" + pcz.PCYARR[i].DWBM + "," + pcz.PCYARR[i].JSBM + pcz.PCYARR[i].GH + "\">" + pcz.PCYARR[i].MC + "</span>&nbsp;&nbsp;";
        }
    }
    html += "    </p>";

    html += "    <p class=\"ap\">";
    html += "        <span>组员</span>&nbsp;&nbsp;";
    var isMore = false;
    var length = -1;
    for (var i = 0; i < pcz.PCYARR.length; i++){
        if (pcz.PCYARR[i].JSBM == "101"){

            pcz.PCYARR[i].ID = pcz.PCYARR[i].DWBM + pcz.PCYARR[i].GH;
            length += pcz.PCYARR[i].MC.length + 1;
            if (length <= 8){
                html += "<span id=\"" + pcz.PCYARR[i].DWBM + "," + pcz.PCYARR[i].JSBM + pcz.PCYARR[i].GH + "\">" + pcz.PCYARR[i].MC + "</span>&nbsp;";
            }else if(!isMore && length >= 9){
                isMore = true;
                html += "<span id=\"\">...</span>";
            }else {
                break;
            }
        }
    }
    html += "    </p>";
    html += "</div>";

    // 鼠标选中样式
    html += "<div class=\"ZXpc_pcfa_xz_rys_hover\">";
    html += "    <div style=\"margin: 25px auto; width: 120px;height: 70px;\">";
    html += "        <div onclick=\"alert_win_eval_build_sp_pcz('" + pcz.PCZBM + "')\" class=\"yuan_all\">";
    html += "            <div class=\"yuan\">";
    html += "                <img src=\"image/home/bianji.png\" style=\"margin: 10px\">";
    html += "            </div>";
    html += "            <span style=\"font-size: 14px;color: #fff;margin: 5px 0 0 5px;display:inline-block;\">编辑</span>";
    html += "        </div>";
    html += "        <div onclick=\"delete_eval_build_sp_pcz('" + pcz.PCZBM + "')\" class=\"yuan_all\" style=\"float: right;\">";
    html += "            <div class=\"yuan\">";
    html += "                <img src=\"image/home/del.png\" style=\"margin: 10px\">";
    html += "            </div>";
    html += "            <span style=\"font-size: 14px;color: #fff;margin: 5px 0 0 5px;display:inline-block;\">删除</span>";
    html += "        </div>";
    html += "    </div>";
    html += "</div>";

    return html;
}

// 折叠
function openDiv(node,num){
    if(num == 1){
        if(showTot){
            $("#ZXpc_pcfa_tot").animate({height: "50px"}, 0 );
            $(node).css('background','url(image/index/jian.png)');

            // 若为分配界面，则调整分配列表大小
            var isAssign = $("#div_eval_build_sp_assign").css("display");
            if(isAssign && (isAssign == "" || isAssign == "block")){
                resize_table_eval_bulid_sp_assign(-50);
            }else{
                resize_table_eval_bulid_sp_filter(-50);
            }

        }else{
            $("#ZXpc_pcfa_tot").animate({height: "0px"}, 0 );
            $(node).css('background','url(image/index/jia.png)');
            // 若为分配界面，则调整分配列表大小
            var isAssign = $("#div_eval_build_sp_assign").css("display");
            if(isAssign && (isAssign == "" || isAssign == "block")){
                resize_table_eval_bulid_sp_assign(50);
            }else{
                resize_table_eval_bulid_sp_filter(50);
            }
        }
    }else{
        if(showTj){
            $("#div_eval_build_sp_pcz").animate({height: "110px"}, 0 );
            $(".ZXpc_pcfa_xz_rys").css('display','block');
            $(node).css('background','url(image/index/jian.png)');
            // 若为分配界面，则调整分配列表大小
            var isAssign = $("#div_eval_build_sp_assign").css("display");
            if(isAssign && (isAssign == "" || isAssign == "block")){
                resize_table_eval_bulid_sp_assign(-110);
            }else{
                resize_table_eval_bulid_sp_filter(-110);
            }
        }else{
            $("#div_eval_build_sp_pcz").animate({height: "0px"}, 0 );
            $(".ZXpc_pcfa_xz_rys").css('display','none');
            $(node).css('background','url(image/index/jia.png)');
            // 若为分配界面，则调整分配列表大小
            var isAssign = $("#div_eval_build_sp_assign").css("display");
            if(isAssign && (isAssign == "" || isAssign == "block")){
                resize_table_eval_bulid_sp_assign(110);
            }else{
                resize_table_eval_bulid_sp_filter(110);
            }
        }
    }
}

// 筛选界面列表调整
function resize_table_eval_bulid_sp_filter(height) {
    $("#div_eval_build_sp_filter").height(function (n, c) {
        return c + height;
    });
    try{
        $("#table_eval_bulid_sp_filter").datagrid('resize', { height: $('#div_table_eval_bulid_sp_filter').height()});
    }catch (e){
        console.log(e);
    }
}

// 分配界面列表调整
function resize_table_eval_bulid_sp_assign(height){
    $("#div_eval_build_sp_assign").height(function (n, c) {
        return c + height;
    });
    try{
        $("#table_eval_bulid_sp_assign").datagrid('resize', { height: $('#div_table_eval_bulid_sp_assign').height()});
    }catch (e){
        console.log(e);
    }
}

function load_cbt_eval_bulid_sp_assign_sxgz(pcflbm,ywtx){
    // 获取筛选规则列表
    $('#cbt_eval_bulid_sp_assign_sxgz').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getSxgzByPcmbbm',
        queryParams: {
            pcflbm: pcflbm,
            sslb: "",
            ywtx: ywtx
        },
        onLoadSuccess: function (node, data) {
            if(data.length > 0){
                $('#cbt_eval_bulid_sp_assign_sxgz').combotree('setValue',data[0].id);
            }
            index_addMousedownDiv(this,'cbt_eval_bulid_sp_assign_sxgz');
        }
    });
}
// 案件筛选界面标签样式设置及事件绑定
function eval_special_win_filter_marksInit(pcxx) {
    // 承办部门下拉树
    $("#txt_eval_build_sp_assign_cbbm").combotree({
        method: 'get',
        disabled: false,
        editable: false,
        lines: true
        // multiple: true,
        // cascadeCheck: false
    });

    // 承办单位列表
    $('#cbt_eval_bulid_sp_assign_dwbm').combotree({
        method: 'get',
        lines: true,
        panelWidth:250,
        multiple: true,//湖北：禁止多选，单选单位
        cascadeCheck:false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },
        onLoadSuccess: function (node, data) {

            $('#cbt_eval_bulid_sp_assign_dwbm').combotree('setValue', userInfo.DWBM);
            index_addMousedownDiv(this,'cbt_eval_bulid_sp_assign_dwbm');
        },
        onChange: function (newValue, oldValue) {

            // 仅选中一个单位时加载部门列表
            if(isNull(newValue) || newValue.length != 1){
                $("#txt_eval_build_sp_assign_cbbm").combotree('disable');
                return;
            }
            // if(isNull(newValue)){
            //     $("#txt_eval_build_sp_assign_cbbm").combotree('disable');
            //     return;
            // }

            $("#txt_eval_build_sp_assign_cbbm").combotree('enable');
            // 承办部门下拉树
            $("#txt_eval_build_sp_assign_cbbm").combotree({
                onShowPanel: index_onShowPanel,
                onHidePanel: index_onHidePanel,
                url: getRootPath() + '/filter/getAllBm',
                queryParams: {
                    dwbm: newValue[0]
                    // dwbm: newValue
                },
                onLoadSuccess: function (node, data) {
                    index_addMousedownDiv(this,'txt_eval_build_sp_assign_cbbm');
                }
            });
        }
    });

    // 筛选案件列表
    $('#table_eval_bulid_sp_assign_filter').datagrid({
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        toolbar: $('#tool_table_eval_bulid_sp_assign_filter'),
        pagination:true,
        pageSize:20,
        pageNumber:1,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90},
            {field:'DWMC',title:'承办单位',width:90},
            {field:'CBR',title:'承办检察官',width:90},
            {field:'SLRQ',title:'受理日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'WCBZRQ',title:'完成日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'DWBM',title:'单位编码',hidden:true},
            {field:'SXGZBM',title:'筛选规则编码',hidden:true},
            {field: 'action', title: '操作', width: 90, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_bulid_sp_assign_filter\',1)">查看 </a> ';
                    var d = '<a href="#" onclick="filter_special_assign(' + index + ')" style="margin-left: 10px">筛选</a> ';
                    return r + d;
                }
            }
        ]]
    });
    $('#table_eval_bulid_sp_assign_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 查询
    $("#btn_eval_build_sp_assign_search").unbind( "click" );
    $("#btn_eval_build_sp_assign_search").bind("click", function () {
        // 加载案件列表
        load_table_eval_bulid_sp_assign_filter(pcxx);
    });

    // 高级查询
    $("#btn_eval_build_sp_assign_search_advance").unbind( "click" );
    $("#btn_eval_build_sp_assign_search_advance").bind("click", function () {

    });
}

//  获取案件列表（案件筛选界面）
function load_table_eval_bulid_sp_assign_filter(pcxx) {

    // JS对象
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = pcxx.PCHDBM;
    obj.GZDWBM = '';
    obj.SXGZBM = $('#cbt_eval_bulid_sp_assign_sxgz').combotree('getValue');
    obj.CBDWBM = $('#cbt_eval_bulid_sp_assign_dwbm').combotree('getValues').join(",");
    obj.CBBMBM = $('#txt_eval_build_sp_assign_cbbm').combotree('getValues').join(",");
    obj.AJLB = '';
    obj.BMSAH = $('#txt_eval_build_sp_assign_bmsah').textbox('getValue');
    obj.AJMC = $('#txt_eval_build_sp_assign_ajmc').textbox('getValue');
    obj.CBRXM = $('#txt_eval_build_sp_assign_cbr').textbox('getValue');
    obj.AY = '';
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQBNG = '';
    obj.WCRQBNG = $('#date_eval_build_sp_assign_begin').datebox('getValue');
    obj.WCRQEND = $('#date_eval_build_sp_assign_end').datebox('getValue');
    obj.GZRQBNG = $('#date_eval_build_sp_assign_begin').datebox('getValue');
    obj.GZRQEND = $('#date_eval_build_sp_assign_end').datebox('getValue');
    //obj.ZDYCXTJ = get_eval_build_sp_zdycxtj();
    obj.ZDYCXTJ = '';

    $('#table_eval_bulid_sp_assign_filter').datagrid({
        url: getRootPath()+'/filter/getSjsx',
        queryParams: { json : JSON.stringify(obj) }
    });
}

// 筛选案件
function  filter_special_assign(index) {

    var rowDatas = $('#table_eval_bulid_sp_assign_filter').datagrid('getRows');
    var param = rowDatas[index];

    Confirm("确认", "确认筛选案件【" + param.AJMC + "】？", function (r) {
        if (r) {

            // JS对象
            var obj = new Object();
            obj.PCFLBM = EVAL_DATA.PCFLBM;
            obj.PCHDBM = EVAL_DATA.PCHDBM;
            obj.PCMBBM = EVAL_DATA.PCMBJ;
            obj.BMSAH = param.BMSAH;
            obj.SXGZBM = param.SXGZBM;
            obj.BPC_WCBZRQ = param.WCBZRQ;
            obj.BPC_DWBM = param.DWBM;

            $.ajax({
                url: getRootPath() + "/filter/uptZxpcsx",
                data: {json: JSON.stringify(obj)},
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {

                    if (result.status == 200) {

                        var data = result.value;
                        $('#table_eval_bulid_sp_assign_filter').datagrid('deleteRow', index);
                        var rows = $('#table_eval_bulid_sp_assign_filter').datagrid('getRows');
                        $('#table_eval_bulid_sp_assign_filter').datagrid('loadData', rows);

                        $('#table_eval_bulid_sp_assign').datagrid('appendRow',{
                            PCDWBM: data.PCHDBM.substr(0, 6),
                            PCFLBM: data.PCFLBM,
                            PCHDBM: data.PCHDBM,
                            PCMBBM: data.PCMBBM,
                            PCSAH: data.PCSAH,
                            PCSLBM: data.PCSLBM,
                            BMSAH: param.BMSAH,
                            AJMC: param.AJMC,
                            AJLBMC: param.AJLBMC,
                            DWBM: param.DWBM,
                            DWMC: param.DWMC,
                            CBR: param.CBR,
                            SLRQ: param.SLRQ,
                            WCBZRQ: param.WCBZRQ,
                            SXGZBM: param.SXGZBM,
                            PCZ_BM: '',
                            PCZ_MC: '',
                            PCR_DWBM: '',
                            PCR_DWMC: '',
                            PCR_GH: '',
                            PCR_MC: ''
                        });

                        EVAL_DATA.PCAJLB = $('#table_eval_bulid_sp_assign').datagrid('getRows');
                        $('#table_eval_bulid_sp_assign').datagrid('loadData', EVAL_DATA.PCAJLB);

                    } else {
                        Alert(result.note);
                    }
                }
            });
        }
    });
}

// 查看案件详细信息
function lookup_special_case(bmsah) {

    $.ajax({
        type: 'GET',
        url: getRootPath()+'/caseinfo/getCaseInfo',
        data: { bmsah: bmsah, pcslbm: "" },
        dataType: "json",
        success: function (result) {

            if (result.status == 200){
                var data = result.value;
                $('#win_eval_build_sp_caseinfo').window({
                    closed: true,
                    collapsible: false,
                    maximizable: false,
                    minimizable: false,
                    modal: true,
                    title: "案件详细信息",
                    width: 720,
                    height: 650,
                    content: data
                });

                // 弹出查看框
                $('#win_eval_build_sp_caseinfo').window('open');
            } else {
                Alert(result.note);
            }
        }
    });

}

// 高级查询筛选案件
function filter_special_gjcx_case(index,row) {
    window.event ? window.event.cancelBubble = true : e.stopPropagation();
    var index = $("#gjcx_datagrid").datagrid("getRowIndex",row);
    $("#gjcx_datagrid").datagrid("selectRow",index);
    var remover = $("#gjcx_datagrid").datagrid("getSelected");
    if(!remover)
        return;

    var index = $("#table_win_eval_bulid_sp_filtered").datagrid("getRowIndex", remover.BMSAH);
    if (index == -1){
        $('#table_win_eval_bulid_sp_filtered').datagrid('appendRow',{
            BMSAH: remover.BMSAH,
            AJMC: remover.AJMC,
            AJLBMC: remover.AJLBMC,
            DWBM: remover.DWBM,
            DWMC: remover.DWMC,
            CBR: remover.CBR,
            SLRQ: remover.SLRQ,
            WCBZRQ: remover.WCBZRQ,
            SXGZBM: remover.SXGZBM
        });
    }
    var index = $("#gjcx_datagrid").datagrid("getRowIndex", remover.BMSAH);
    $("#gjcx_datagrid").datagrid("deleteRow", index);

    $("#btn_eval_build_sp_filtered").text("已筛选(" + $('#table_win_eval_bulid_sp_filtered').datagrid('getRows').length + ")");
}

function getFpsfList() {
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/blend/getsfList',
        success:function (data) {
            var result=  data.data;
            var curArry = new Array();
            for(var i = 0;i<result.length;i++){
                var curObj = {"sflx":"","sfmc":""};
                curObj.sflx = result[i].sflx;
                curObj.sfmc = result[i].sfmc;
                curArry.push(curObj);
            }
            $("#cbx_win_eval_build_sp_assign_fpsf").combobox( {
                valueField: 'sflx',
                textField: 'sfmc',
                data:curArry,
                onSelect:function(record){
                    for(var j = 0;j<result.length;j++){
                        if (result[j].sflx == record.sflx){
                            $("#txt_win_eval_build_sp_assign_fpsf").textbox("setValue",result[j].sm);
                        }
                    }
                }
            });
        }

    })
}
function invokeSfcx() {
    var type=  $("#cbx_win_eval_build_sp_assign_fpsf").combobox('getValue')
    var obj=new Object();
    obj.type=type;
    obj.pchdbm=EVAL_DATA.PCHDBM;
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/blend/invokeSf',
        data:obj,
        success:function () {
        }
    })
}

// 送审到副分检察长
function send_eval_special_approve_fjcz() {

    // 案件列表DataGrid初始化
    $('#grid_win_pcfa_agfzr_jxss').datagrid({
        width:'460px',
        height: '280px',
        fitColumns: true,
        singleSelect: true,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        url: getRootPath()+'/blend/getHdspFjcz',
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
    $("#btnFAConfirm_jxss").unbind("click");
    $("#btnFAConfirm_jxss").bind("click", function () {

        var row = $("#grid_win_pcfa_agfzr_jxss").datagrid("getSelected");
        if(!row){
            Alert("请选择审批人员！");
            return;
        }

        // JS对象
        var obj = new Object();
        obj.PCHDBM  = EVAL_DATA.PCHDBM;
        obj.SPRDWBM  = row.DWBM;
        obj.SPRDWMC  = row.DWMC;
        obj.SPRGH = row.GH;
        obj.SPRXM = row.MC;
        $.ajax({
            url: getRootPath() + "/blend/addPcfaps",
            data: { json: JSON.stringify(obj)},
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (result) {

                if (result.status == 200){

                    // 初始化操作按钮
                    init_special_toolbar();
                    Alert("送审成功！");

                    $('#win_pcfa_sstoanfzr_jxss').window('close');
                }else{
                    Alert(result.note);
                }
            }
        });
    });

    // 提交
    $("#btnFACancle_jxss").unbind("click");
    $("#btnFACancle_jxss").bind("click", function () {
        $('#win_pcfa_sstoanfzr_jxss').window('close');
    });

    // JS对象
    var obj = new Object();
    obj.PCSPBM  = EVAL_DATA.PCSPBM;
    obj.SPJL  = $("input[name='is_check_of_sp']:checked").val();
    obj.SPYJ  = $('#txt_hd_sp_spjg').textbox('getValue');
    if(isNull(obj.SPYJ)){
        Alert("请填写审批意见！");
        return;
    }
    $.ajax({
        url: getRootPath() + "/blend/uptDealFasp",
        data: { json: JSON.stringify(obj)},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {

            if (result.status == 200){
                // 初始化操作按钮
                init_special_toolbar();
                $('#window_hd_sp').window('close');
            }else{
                Alert(result.note);
            }
        }
    });

    // 弹出送审窗口
    $('#win_pcfa_sstoanfzr_jxss').window('open');

    $('#window_hd_sp').window('close');
}