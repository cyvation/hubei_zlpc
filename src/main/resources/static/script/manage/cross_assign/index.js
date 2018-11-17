var pcxx;
var ISDJDW = false;
var bmsahlist = [];
var sfzdy = 'N';
$(document).ready(function () {


    pcxx = FUNCTION_PARAM;

    // 界面标签样式设置及事件绑定
    eval_random_marksInit(pcxx);

});

// 界面标签样式设置及事件绑定
function eval_random_marksInit(pcxx) {

    // 案件列表
    $('#table_manage_jxpcaj').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageSize:20,
        pageNumber:1,
        multiSort:true,
        remoteSort:false,
        columns:[[
            {field:'ck',checkbox:true },
            {field:'BMSAH',title:'部门受案号',width:160,sortable:true },
            {field:'AJMC',title:'案件名称',width:120,sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90,sortable:true},
            {field:'DWMC',title:'承办单位',width:90,sortable:true},
            {field:'CBR',title:'承办检察官',width:90,sortable:true},
            {
                field: 'SLRQ', title: '受理日期', width: 90,
                formatter: function (value) {return sjzh(value);},
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
            {field:'WCBZRQ',title:'完成日期',width:90,
                formatter: function (value){return sjzh(value);},
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
            {field:'JSDW',title:'接受单位',width:90,hidden:true},
            {field:'JSDWMC',title:'接受单位',width:90},
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_manage_jxpcaj\',1)">查看</a>';

                    //var d = '<a href="#" onclick="assign_jxpcaj()" style="margin-left: 10px">分配</a> ';

                    var d = '<a href="#" onclick="init_manage_jxpc_window(' + index +')" style="margin-left: 10px">开放</a>';
                    if (DJDWBM == userInfo.DWBM || row.JSDW != undefined){
                        d = '';
                    }
                    var e = '<a href="#" onclick="remove_assign_jxpcaj(' + index +')" style="margin-left: 10px">移除</a>';
                    if (DJDWBM == userInfo.DWBM || row.JSDW == undefined){
                        e = '';
                    }


                    return r + d + e ;
                }
            }
        ]]
    });
    $('#table_manage_jxpcaj').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });


    //自定义默认查询为当年时间
    //完成日期
    $('#date_manage_cross_start').datebox({
        editable: true,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_manage_cross_end').datebox({
        editable: true,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });


    //承办单位
    ISDJDW = true;
    $('#cbt_eval_build_rd_custom_pcdw_div').css('display', 'block');
    $('#cbt_manage_cross_cbdw').combotree({
        method: 'get',
        editable: false,
        lines: true,
        panelWidth:270,
        // multiple: true,
        //cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_manage_cross_cbdw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,'cbt_manage_cross_cbdw');
        },
        onSelect: function (node) {
         //   load_manage_jxpc_jsdw();

        }

    });

    // 抽查方式
    $('#cbt_manage_cross_lb').combotree({
        method: 'get',
        lines: true,
        multiSort:false,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1){
                pcflbm = data[0].id;

                $('#cbt_manage_cross_lb').combotree('setValue', pcflbm);
                load_manage_jxpc_gz_mb();

            }

        },
        onSelect: function (node) {
            load_manage_jxpc_gz_mb();

        }
    });

    

    // 样式设置
    // $(".pcajsx_tj_2").click(function(){
    //     $(this).addClass("pcajsx_tj_click");
    //     $(this).siblings().removeClass("pcajsx_tj_click")
    // });

    // 查询
    $("#btn_manage_cross_search").unbind( "click" );
    $("#btn_manage_cross_search").bind("click", function () {

        // 加载案件列表
        load_table_manage_jxpcaj();

    });

    // 批量分配
    $("#btn_manage_cross_batchassign").unbind( "click" );
    $("#btn_manage_cross_batchassign").bind("click", function () {

        init_manage_jxpc_window();

    });

    if(DJDWBM != userInfo.DWBM){
        $("#btn_manage_cross_batchassign").css('display','');
    }

    // 批量移除
    $("#btn_manage_cross_batchremove").unbind( "click" );
    $("#btn_manage_cross_batchremove").bind("click", function () {

        remove_assign_jxpcaj();

    });

    if(DJDWBM != userInfo.DWBM){
        $("#btn_manage_cross_batchremove").css('display','');
    }

}

// 初始化开放条件
function init_manage_jxpc_window(index) {
    // 接收单位,与承办单位平级
    $('#cbt_manage_cross_jsdw').combotree({
        method: 'get',
        editable: false,
        lines: true,
        panelWidth:270,
        // multiple: true,
        //cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/filter/getJsdw',
        queryParams: {
            dwbm:  $("#cbt_manage_cross_cbdw").combotree('getValue')
        },
        async: false,
        loadFilter: function (data) {
            return isNull(data) ? []  : data;
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_manage_cross_jsdw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,'cbt_manage_cross_cbdw');
        }
    });


    // 确定
    $("#btn_manage_jxpc_assign_confirm").unbind('click');
    $("#btn_manage_jxpc_assign_confirm").bind('click',function () {
        assign_jxpcaj(index);
    });

    // 取消
    $("#btn_manage_jxpc_assign_cancle").unbind('click');
    $("#btn_manage_jxpc_assign_cancle").bind('click',function () {
        $("#win_manage_crossassign_jsdw").window('close');
    });

    $("#win_manage_crossassign_jsdw").window('open');

}


function load_manage_jxpc_gz_mb(){

    var tree = $('#cbt_manage_cross_lb').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    var pcflbm = node.id;

    var path = "/template/template";
    // if(node.attributes.SFJS == "Y"){
    //     path = "/manage/get_pchd";
    // }

    // 评查模板
    // 获取评查模板
    $('#cbt_manage_cross_pcmb').combotree({
        method: 'get',
        lines: true,
        panelWidth:260,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+ path,
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess:function(node,data){
            if (data != null && data.length >= 1){
               var  pchdbm = data[0].id;
                $('#cbt_manage_cross_pcmb').combotree('setValue',  pchdbm);
                load_cbt_eval_build_rd_custom_sxgz(pcflbm, pchdbm);
            }
            index_addMousedownDiv(this,"cbt_manage_cross_pcmb");
        },
        onSelect: function (node) {
            load_cbt_eval_build_rd_custom_sxgz(pcflbm, node.id);
        }
    });

}


// 自定义案件列表
function load_table_manage_jxpcaj() {
    var bmsah = $('#txt_manage_cross_bmsah').textbox('getValue').trim();
    var ajmc = $('#txt_manage_cross_cbdw').textbox('getValue').trim();
    var cbrxm = $('#txt_manage_cross_stuff').textbox('getValue').trim();
    var sxgzbm = $('#cbt_manage_cross_sxgz').combotree('getValue');
    var sxgzmc = $('#cbt_manage_cross_sxgz').combotree('getText').trim();

    var tree = $('#cbt_manage_cross_lb').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点

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
    var BPCDWBM = $("#cbt_manage_cross_cbdw").combotree('getValue');
    var pchdbm = $("#cbt_manage_cross_pcmb").combotree('getValue');
    // JS对象
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = node.id;
    obj.GZDWBM = '';
    obj.SXGZBM = $('#cbt_manage_cross_sxgz').combotree('getValue');
    obj.CBDWBM = userInfo.DWBM;
    ISDJDW ? obj.CBDWBM = (BPCDWBM != '' ? BPCDWBM : userInfo.DWBM) : userInfo.DWBM;
    obj.CBBMBM = '';
    obj.AJLB = '';
    obj.BMSAH = bmsah;
    obj.AJMC = ajmc;
    obj.CBRXM = cbrxm;
    obj.AY = '';
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQEND = '';
    obj.WCRQBNG = $('#date_manage_cross_start').datebox('getValue');
    obj.WCRQEND = $('#date_manage_cross_end').datebox('getValue');
    obj.GZRQBNG = $('#date_manage_cross_start').datebox('getValue');
    obj.GZRQEND = $('#date_manage_cross_end').datebox('getValue');
    obj.ZDYCXTJ = '';
    //判断时间
    if(!checkTime(obj.GZRQBNG,obj.GZRQEND)){
        return;
    }

    var tree = $("#cbt_manage_cross_sxgz").combotree("tree");
    var node = tree.tree("getSelected");
    var url = "";

    if(node.attributes.SFZDY == "Y"){
        url = getRootPath()+'/filter/getSjsx';
    }else{
        url = getRootPath()+'/filter/getSjsxAdvance';
    }

    $('#table_manage_jxpcaj').datagrid({
         url:  url,
        queryParams: { json : JSON.stringify(obj) }
    });
}

//加载筛选规则
function load_cbt_eval_build_rd_custom_sxgz(pcflbm, pchdbm){

    // 获取评查活动绑定的筛选规则列表
    // $('#cbt_manage_cross_sxgz').combotree({
    //     method: 'get',
    //     lines: true,
    //     panelWidth:260,
    //     onShowPanel: index_onShowPanel,
    //     onHidePanel: index_onHidePanel,
    //     url: getRootPath()+'/filter/getHdsxgz',
    //     queryParams: {
    //         pcflbm: pcflbm,
    //         pchdbm: pchdbm
    //     },
    //     onLoadSuccess:function(node,data){
    //         if(data.length > 0){
    //             $('#cbt_manage_cross_sxgz').combotree('setValue',data[0].id);
    //         }
    //         index_addMousedownDiv(this,"cbt_manage_cross_sxgz");
    //     }
    // });

    $('#cbt_manage_cross_sxgz').combotree({
        method: 'get',
        lines: true,
        multiple: false,
        panelWidth: 270,
        checkbox : false,//显示多选框
        url: getRootPath() + '/filter/getSxgzByPcflbmAndPcmb',
        queryParams: {
            pcflbm: pcflbm,
            pcmbmb: pchdbm
        },
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data.length > 0) {
                $('#cbt_manage_cross_sxgz').combotree('setValue',data[0].id);
            }
            index_addMousedownDiv(this,"cbt_manage_cross_sxgz");
        }

    });
}


// 查看案件详细信息
function lookup_random_case(bmsah) {

    $.ajax({
        type: 'GET',
        url: getRootPath()+'/caseinfo/getCaseInfo',
        data: { bmsah: bmsah, pcslbm: "" },
        dataType: "json",
        success: function (result) {

            if (result.status == 200){
                var data = result.value;
                $('#win_eval_build_rd_caseinfo').window({
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
                $('#win_eval_build_rd_caseinfo').window('open');
            } else {
                Alert(result.note);
            }
        }
    });

}

// 开放案件给其他单位评查
function assign_jxpcaj(index){

    var jsdw = $("#cbt_manage_cross_jsdw").combotree('getValue');
    if (isNull(jsdw)){
        Alert("请选择接受单位！");
        return;
    }

    var bmsahlist;
    var selectedRow;
    if (index != undefined){
        selectedRow = $("#table_manage_jxpcaj").datagrid('getRows')[index];
        bmsahlist = selectedRow.BMSAH;

        if(selectedRow.JSDW != undefined){
            Alert("请先移除在开放案件");
            return;
        }

    }else{
        selectedRow = $("#table_manage_jxpcaj").datagrid('getChecked');
        if (!selectedRow){
            Alert("请勾选案件进行分配！");
            return;
        }
        var arr =[];
        for(var i= 0; i < selectedRow.length; i++){
            if (selectedRow.JSDW != undefined){
                Alert("您勾选的案件中有已经开放过的案件！");
                return;
            }

            arr.push(selectedRow[i].BMSAH);
        }
        bmsahlist = arr.join(",");
        // bmsahlist = arr;
    }

    if (isNull(bmsahlist)) {
        Alert("未勾选案件");
        return;
    }
    

    var obj = new Object();
    obj.dwbm = $("#cbt_manage_cross_cbdw").combotree('getValue');
    obj.dwmc = $("#cbt_manage_cross_cbdw").combotree('getText');
    obj.czr_dwbm = userInfo.DWBM;
    obj.czr_dwmc = userInfo.DWMC;
    obj.czr_gh = userInfo.GH;
    obj.czr_mc = userInfo.MC;
    obj.jsdw = $("#cbt_manage_cross_jsdw").combotree('getValue');
    obj.jsdwmc = $("#cbt_manage_cross_jsdw").combotree('getText');
    obj.pcflbm = $("#cbt_manage_cross_lb").combotree('getValue');
    obj.bmsahlist = bmsahlist;

    var sxgzbm = $("#cbt_manage_cross_sxgz").combotree('getValue');
    var sxgzmc = $("#cbt_manage_cross_sxgz").combotree('getText');
    if (sxgzbm.substring(9,10)=="9" || sxgzmc.indexOf("自定义案件") > 0){
        obj.sfzdy = 'Y';
    }else{
        obj.sfzdy = 'N';
    }


    obj.sm = '';

    $.ajax({
        type: "POST",
        url: getRootPath() + "/filter/assignJxAj",
        data: obj,
        async: true,
        dataType: 'json',
        success: function (result) {

            if (result.status == 200) {
                Alert("开放成功！");
                $("#win_manage_crossassign_jsdw").window('close');

                load_table_manage_jxpcaj();

            } else {
                CloseProgress();
                Alert(result.note);
            }

        }
    });


}

// 移除
function remove_assign_jxpcaj(index){

    var bmsahlist;

    if (index != undefined){
        var selectedRow = $("#table_manage_jxpcaj").datagrid('getRows')[index];
        if (!isNull(selectedRow) && isNull(selectedRow.jsdw)){
            Alert("请选择已经开放的案件进行移除");
            return;
        }

        bmsahlist = selectedRow.BMSAH;

    }else{
        var selectedRow = $("#table_manage_jxpcaj").datagrid('getChecked');
        if (!selectedRow ){
            Alert("请勾选案件！");
            return;
        }
        var arr =[];
        for(var i= 0; i < selectedRow.length; i++){
            arr.push(selectedRow[i].BMSAH);
        }
        bmsahlist = arr.join(",");
    }


    var obj = new Object();

    obj.bmsahlist = bmsahlist;
    obj.dwbm = $("#cbt_manage_cross_cbdw").combotree('getValue');
    obj.dwmc = $("#cbt_manage_cross_cbdw").combotree('getText');
    obj.czr_dwbm = userInfo.DWBM;
    obj.czr_dwmc = userInfo.DWMC;
    obj.czr_gh = userInfo.GH;
    obj.czr_mc = userInfo.MC;

        $.ajax({
            type: "POST",
            url: getRootPath() + "/filter/removeAssignJxaj",
            data: obj,
            async: true,
            dataType: 'json',
            success: function (result) {

                if (result.status == 200) {
                    Alert("移除成功！");
                    load_table_manage_jxpcaj();
                } else {
                    CloseProgress();
                    Alert(result.note);
                }

            }
        });


}


