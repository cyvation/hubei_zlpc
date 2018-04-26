var pcxx;
var kd_SXGZBM;
var ISDJDW = false;
var isZb = true;
$(document).ready(function () {

    // 新建评查界面传递的参数，评查分类及评查模板等信息
    pcxx = FUNCTION_PARAM;
    // 界面标签样式设置及事件绑定
    eval_keypoint_marksInit(pcxx);

    // 标签初始化数据加载
    eval_keypoint_marksDataBind(pcxx);
});

// 界面标签样式设置及事件绑定
function eval_keypoint_marksInit(pcxx) {

    // if(DJDWBM == userInfo.DWBM){
        ISDJDW = true;
        $('#cbt_eval_build_kp_chart_pcdw_div').css('display', 'block');
        $('#cbt_eval_build_kp_custom_pcdw_div').css('display', 'block');
        $('#cbt_eval_build_kp_chart_pcdw').combotree({
            method: 'get',
            editable: false,
            lines: true,
            panelWidth:270,
            onShowPanel: index_onShowPanel,
            onHidePanel: index_onHidePanel,
            url: getRootPath() + '/organization/getDwbmTree',
            async: false,
            loadFilter: function (data) {
                return data.status == 200 ? JSON.parse(data.value) : [];
            },
            onLoadSuccess: function (node, data) {
                if (data != null && data.length >= 1) {
                    $('#cbt_eval_build_kp_chart_pcdw').combotree('setValue', data[0].id); //单位默认选择
                }
                index_addMousedownDiv(this,'cbt_eval_build_kp_chart_pcdw');
            }
        });
        $('#cbt_eval_build_kp_custom_pcdw').combotree({
            method: 'get',
            editable: false,
            lines: true,
            panelWidth:270,
            onShowPanel: index_onShowPanel,
            onHidePanel: index_onHidePanel,
            url: getRootPath() + '/organization/getDwbmTree',
            async: false,
            loadFilter: function (data) {
                return data.status == 200 ? JSON.parse(data.value) : [];
            },
            onLoadSuccess: function (node, data) {
                if (data != null && data.length >= 1) {
                    $('#cbt_eval_build_kp_custom_pcdw').combotree('setValue', data[0].id); //单位默认选择
                }
                index_addMousedownDiv(this,'cbt_eval_build_kp_custom_pcdw');
            }
        });
    // }
    // 部门总表样式初始化
    $('#table_eval_build_kp_chart').datagrid({

        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMBM',
        autoRowHeight:false
    });
    //加载总分评查模板
    $('#cbt_eval_build_kp_chart_pcmb').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/manage/get_pchd',
        queryParams: {
            pcflbm: pcxx.PCFLBM
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_eval_build_kp_chart_pcmb').combotree('setValue', data[0].id);
            }
        },
    });
    //设置总表默认查询为当年时间
    //完成日期
    $('#date_eval_build_kp_chart_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_eval_build_kp_chart_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });
    // 查询
    $("#btn_eval_build_kp_chart_search").unbind( "click" );
    $("#btn_eval_build_kp_chart_search").bind("click", function () {
        // 加载部门总表
        load_table_eval_build_kp_chart(pcxx);
    });
    //总表
    $("#btn_eval_build_kp_chart").unbind( "click" );
    $("#btn_eval_build_kp_chart").bind("click", function () {
        $("#page_eval_build_kp_chart").css("left","0px");
        $("#page_eval_build_kp_custom").css("left","200%");
        isZb = true;
        // 加载部门总表
        var data = $('#table_eval_build_kp_chart').datagrid('getRows');
        if (!data || data.length <= 0){
            load_table_eval_build_kp_chart(pcxx);
        }
    });

    // 自定义查询
    $('#table_eval_build_kp_custom').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
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
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {

                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_kp_custom\',1)">查看</a>'

                    var d = '<a href="#" onclick="get_custom_filter_obj(' + index + ')" style="margin-left: 10px">评查</a> ';

                    return r + d;
                }
            }
        ]]
    });
    $('#table_eval_build_kp_custom').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    //自定义
    $(".ZDpc_bm_tj_2").click(function(){
        $(this).addClass("ZDpc_bm_tj_2_click");
        $(this).siblings().removeClass("ZDpc_bm_tj_2_click")
    })

    // 自定义
    $("#btn_eval_build_kp_custom").unbind( "click" );
    $("#btn_eval_build_kp_custom").bind("click", function () {
        $("#page_eval_build_kp_chart").css("left","-200%");
        $("#page_eval_build_kp_custom").css("left","0");
        isZb = false;
        // 初始化自定义筛选条件
        init_eval_build_kp_custom_condition(pcxx);

        // 查询
        $("#btn_eval_build_kp_custom_search").unbind( "click" );
        $("#btn_eval_build_kp_custom_search").bind("click", function () {
            // 加载案件列表
            load_table_eval_build_kp_custom(pcxx);
        });
    });

    // 自定义查询时间为本年度
    //完成日期
    $('#date_eval_build_kp_custom_begin').datebox({
        editable: false,
        value: '2013-01-01'
        // value: new Date().getFullYear() + '-01-01'
    });

    $('#date_eval_build_kp_custom_end').datebox({
        editable: false,
        value: '2017-12-31'
        // value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    // 样式设置
    $(".ZDpc_bm_tj_2").click(function(){
        $(this).addClass("ZDpc_bm_tj_2_click");
        $(this).siblings().removeClass("ZDpc_bm_tj_2_click")
    });

    // excel表格导出
    $("#btn_eval_build_kp_chart_export").bind('click',function () {
        kp_chart_export();
    });
    //高级查询
    $("#btn_eval_build_kp_custom_search_advance").unbind( "click" );
    $("#btn_eval_build_kp_custom_search_advance").bind("click", function () {
        $("#window_gjcx_kp").window("open");
        $("#window_gjcx_kp").window("refresh","view/evaluate/build/special_gjcx_kp.html");
    });
}

// 标签初始化数据加载
function eval_keypoint_marksDataBind(pcxx) {
    // 默认加载案件筛选
    $("#btn_eval_build_kp_custom").click();
}

// 部门总表
function load_table_eval_build_kp_chart(pcxx) {
    var BPCDWBM = $("#cbt_eval_build_kp_chart_pcdw").combotree('getValue');
    var PCHDBM = $("#cbt_eval_build_kp_chart_pcmb").combotree('getValue');
    // JS对象
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = PCHDBM;
    obj.SXGZBM = '';
    obj.CBDWBM = userInfo.DWBM;
    ISDJDW ? obj.CBDWBM = (BPCDWBM != '' ? BPCDWBM : userInfo.DWBM) : userInfo.DWBM;
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQBNG = '';
    obj.WCRQBNG = '';
    obj.WCRQBNG = '';
    obj.GZRQBNG = $('#date_eval_build_kp_chart_begin').datebox('getValue');
    obj.GZRQEND = $('#date_eval_build_kp_chart_end').datebox('getValue');
    obj.ZDYCXTJ = '';
    //判断时间
    if(!checkTime(obj.GZRQBNG,obj.GZRQEND)){
        return;
    }
    //显示loading
    ShowProgress();
    // 请求数据
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/filter/get_sjsx_bm',
        data: {
            json: JSON.stringify(obj)
        },
        dataType: "json",
        success: function (result) {

            if (result.status == 200){
                var data = result.value;
                // 生成部门总表

                generate_table_eval_build_kp_chart(data.Columns, data.Rows, data.KeyValues);
                CloseProgress();
            } else {
                CloseProgress();
                Alert(result.note);
            }
        }
    });
}

// 生成部门总表
function generate_table_eval_build_kp_chart(columns, rows, data) {

    if (columns.length <= 0) return;

    // 清空历史数据
    $('#table_eval_build_kp_chart').datagrid('loadData',[]);

    // 动态生成行/列
    var colstr = "{field:'BMBM',title:'部门编码',hidden:true, align: 'center', halign: 'center'}," +
        "{field:'BMMC',title:'部门',width:100, align: 'center', halign: 'center',fixed:'true'}," +
        "{field:'PCL',title:'评查率(%)',hidden:true,width:90, align: 'center', halign: 'center',fixed:'true', formatter: function (value, row, index) { var pcsArr = row.PCZS.split('/'); return Math.round((pcsArr[0] * 100) / pcsArr[1]) + '%'; }}," +
        "{field:'PCZS',title:'评查数(件)',width:90, align: 'center', halign: 'center',fixed:'true'},"; //拼接动态列
    var rowFormat = "BMBM:'@BMBM', BMMC:'@BMMC', PCL:'0', PCZS:'0/0',"; //拼接每行，默认（0,0）
    for (var i = 0; i < columns.length; i++) {
        if (!columns[i])
            continue;

        colstr += "{ field:'" + columns[i].GZBM + "', title:'" + columns[i].GZMC + "',width:90,align: 'center', halign: 'center' }";
        rowFormat += columns[i].GZBM + ":'0/0'";
        if (i < columns.length - 1) {
            colstr += ',';
            rowFormat += ',';
        }
    }
    var table_widht = $(".ZDpc_bm_content").width();
    // 添加列
    var options = {};
    options.columns = eval("[[" + colstr + "]]");
    $('#table_eval_build_kp_chart').datagrid(options);

    // 添加行
    for (var i = 0; i < rows.length; i++) {
        if (!rows[i])
            continue;

        var rowData = rowFormat;
        rowData = rowData.replace("@BMBM", rows[i].BMBM);
        rowData= rowData.replace("@BMMC", rows[i].BMMC);
        // 添加每一行数据
        var row = eval("({" + rowData + "})");

        $('#table_eval_build_kp_chart').datagrid('appendRow', row);
    }

    // 填数据
    for (var i = 0; i < data.length; i++) {
        if (!data[i])
            continue;

        var rowIndex = $('#table_eval_build_kp_chart').datagrid('getRowIndex', data[i].BMBM);
        if (rowIndex == -1) return;

       // var row = eval("({" + data[i].GZBM + ':"<a style=\'cursor:pointer;font-weight:bold\'>" + data[i].YPC + "/" + data[i].ZS + "</a>"})');
        var rowx=data[i].GZBM +":\"<a style='cursor:pointer;font-weight:bold'><span data-index="+rowIndex +" data-field="+ data[i].GZBM +" class='ypc' onclick=ypc_kp_click(this)>" + data[i].YPC +"</span>/<span data-index="+rowIndex+" data-field="+ data[i].GZBM +" onclick=wpc_kp_click(this) class='wpc'>" +(data[i].ZS-data[i].YPC) +"</span></a>\""
        var row = eval("({"+rowx+"})");

        // 更新数据
        $('#table_eval_build_kp_chart').datagrid('updateRow',{
            index: rowIndex,
            row: row
        });
    }

    // 重点去除数据为0/0的整列，并隐藏,由于有数据的项添加了点击事件，可以根据这个来判断
    var keyArray = new Array();
    var rows = $('#table_eval_build_kp_chart').datagrid('getRows');
    var rowLength =rows.length;
    var firstrowData =rows[0];

    // 遍历第一行保留单元格为0/0的key,没有则不存在数据为0/0的整列
    // 行单元格为0/0填充数组：
    for (var key in firstrowData ) {
        // 保留前四列
        if (key !='BMBM' && key !='BMMC' && key !='PCL' && key !='PCZS') {
            // 单元格为0/0
            if(firstrowData[key].indexOf("onclick") ==-1) {
                keyArray.push(key);
            }
        }
    }

    //判断第一行是否存在0/0，如果存在，借助数组除去
    if (keyArray.length >0 && rowLength > 1) {

        // 从第二行开始遍历
        for (var i=1; i<rowLength; i++) {
            var rowsData = rows[i];

            for (var j=0; j<keyArray.length; j++) {
                if(rowsData[keyArray[j]].indexOf("onclick") !=-1 ) {
                    keyArray.splice(j,1);
                    j--;

                }
            }
        }


    }


    // 隐藏：
    if (keyArray.length >0 && rowLength > 1) {
        for (var i =0; i<keyArray.length; i++) {
            if (keyArray[i] !=undefined) {

                $('#table_eval_build_kp_chart').datagrid('hideColumn',keyArray[i]);
            }
        }
    }

    var data = $('#table_eval_build_kp_chart').datagrid('getData');
    $('#table_eval_build_kp_chart').datagrid('loadData', data);

}

// 自定义案件列表
function load_table_eval_build_kp_custom(pcxx) {
    var BPCDWBM = $("#cbt_eval_build_kp_custom_pcdw").combotree('getValue');
    var PCHDBM = $("#cbt_eval_build_kp_custom_pcmb").combotree('getValue');
    // JS对象
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = PCHDBM;
    obj.GZDWBM = '';
    obj.SXGZBM = $('#cbt_eval_build_kp_custom_sxgz').combotree('getValue');
    obj.CBDWBM = userInfo.DWBM;
    ISDJDW ? obj.CBDWBM = (BPCDWBM != '' ? BPCDWBM : userInfo.DWBM) : userInfo.DWBM;
    obj.CBBMBM = $('#cbt_eval_build_kp_custom_cbbm').combotree('getValue');
    obj.AJLB = '';
    obj.BMSAH = $('#txt_eval_build_kp_custom_bmsah').textbox('getValue');
    obj.AJMC = $('#txt_eval_build_kp_custom_ajmc').textbox('getValue');
    obj.CBRXM = $('#txt_eval_build_kp_custom_cbjcg').textbox('getValue');
    obj.AY = '';
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQEND = '';
    obj.WCRQBNG = $('#date_eval_build_kp_custom_begin').datebox('getValue');
    obj.WCRQEND = $('#date_eval_build_kp_custom_end').datebox('getValue');
    obj.GZRQBNG = $('#date_eval_build_kp_custom_begin').datebox('getValue');
    obj.GZRQEND = $('#date_eval_build_kp_custom_end').datebox('getValue');
    obj.ZDYCXTJ = '';
    //判断时间
    if(!checkTime(obj.GZRQBNG,obj.GZRQEND)){
        return;
    }
    $('#table_eval_build_kp_custom').datagrid({
        url: getRootPath()+'/filter/getSjsx',
        queryParams: { json : JSON.stringify(obj) }
    });
}

// 初始化自定义筛选条件
function init_eval_build_kp_custom_condition(pcxx) {
    //加载总分评查模板
    $('#cbt_eval_build_kp_custom_pcmb').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/manage/get_pchd',
        queryParams: {
            pcflbm: pcxx.PCFLBM
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_eval_build_kp_custom_pcmb').combotree('setValue', data[0].id);
                load_cbt_eval_build_kp_custom_sxgz(data[0].id);
            }
        },
        onSelect: function (node) {
            // 加载对应评查模板
            load_cbt_eval_build_kp_custom_sxgz(node.id);
        }
    });
    // 承办部门树加载
    $('#cbt_eval_build_kp_custom_cbbm').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/filter/getAllBm',
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess:function(node,data){
            index_addMousedownDiv(this,'cbt_eval_build_kp_custom_cbbm');
        }
    });

}
function load_cbt_eval_build_kp_custom_sxgz(pchdbm){
    // 获取评查活动绑定的筛选规则列表
    $('#cbt_eval_build_kp_custom_sxgz').combotree({
        method: 'get',
        lines: true,
        panelWidth:270,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getHdsxgz',
        queryParams: {
            pcflbm: pcxx.PCFLBM,
            pchdbm: pchdbm
        },
        onLoadSuccess:function(node,data){
            if(data.length > 0){
                $('#cbt_eval_build_kp_custom_sxgz').combotree('setValue',data[0].id);
            }
            index_addMousedownDiv(this,'cbt_eval_build_kp_custom_sxgz');
        }
    });
}
// 承办部门对应未评查案件列表
function alert_win_eval_build_kp_chart_filter(index, row, gzbm, type) {
    var PCHDBM = "";
    if(isZb){
        PCHDBM = $("#cbt_eval_build_kp_chart_pcmb").combotree('getValue');
    }else {
        PCHDBM = $("#cbt_eval_build_kp_custom_pcmb").combotree('getValue');
    }
    // 如果点击的是评查数或评查率那一栏，就查该部门下所有案件
    if(gzbm == 'PCZS' || gzbm == 'PCL'){
       //gzbm =null;
        var firstrowData = $('#table_eval_build_kp_chart').datagrid('getRows')[index];
        for (var key in firstrowData ) {
            // 保留前四列
            if (key !='BMBM' && key !='BMMC' && key !='PCL' && key !='PCZS') {
                if (type == '0') { // 获取所有左侧有值的规则
                    // 单元格左侧
                    if(firstrowData[key] !="0/0" && firstrowData[key].charAt(125) !='0') {
                        gzbm += ","+key;
                    }

                }else {
                    // 单元格左侧
                    if(firstrowData[key] !="0/0" && firstrowData[key].charAt(216) !='0') {
                        gzbm += ","+key;
                    }
                }

            }
        }
        gzbm = gzbm.substr(5);
    }

    var obj = new Object();
    obj.CBDWBM = '';
    obj.CBBMBM = row.BMBM;
    obj.SXGZBM = gzbm;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = PCHDBM.substr(6);
    obj.GZRQBNG = $('#date_eval_build_kp_chart_begin').datebox('getValue'); // 总表规则开始
    obj.GZRQEND =$('#date_eval_build_kp_chart_end').datebox('getValue'); // 总表规则结束
    obj.type = type; // 0/1 --已经评查/未评查

    // 已经评查
    if (type =='0') {
        // 自定义查询
        $('#table_eval_build_kp_dept_filter').datagrid({
            border:0,
            fit:true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath()+'/filter/getBmwpcList',
            queryParams: { json : JSON.stringify(obj) },
            pagination:true,
            pageNumber:1,
            pageSize:20,
            columns:[[
                {field:'BMSAH',title:'部门受案号',width:160 },
                {field:'AJMC',title:'案件名称',width:160,
                    formatter: function (value) { return tipMessage(value); }},
                {field:'AJLB_MC',title:'案件类别',width:90},
                {field:'DWMC',title:'承办单位',width:90},
                {field:'CBRMC',title:'承办检察官',width:90},
                {field:'PCR_DWMC',title:'评查单位',width:90},
                {field:'PCRQ',title:'评查日期', fixed:true, width: 115 ,
                    formatter: function (value) {
                        return sjzh(value);
                    }
                },
                {field: 'action', title: '操作', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                            var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_kp_dept_filter\')">查看</a>'
                            return r;
                    }
                }
            ]]
        });
    }else {
        // 自定义查询---未评查
        $('#table_eval_build_kp_dept_filter').datagrid({
            border:0,
            fit:true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath()+'/filter/getBmwpcList',
            queryParams: { json : JSON.stringify(obj) },
            pagination:true,
            pageNumber:1,
            pageSize:20,
            columns:[[
                {field:'BMSAH',title:'部门受案号',width:160 },
                {field:'AJMC',title:'案件名称',width:160,
                    formatter: function (value) { return tipMessage(value); }},
                {field:'AJLB_MC',title:'案件类别',width:90},
                {field:'DWMC',title:'承办单位',width:90},
                {field:'CBRMC',title:'承办检察官',width:90},
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
                {field: 'action', title: '操作', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                        var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_kp_dept_filter\',1)">查看</a>'
                        var d = '<a href="#" onclick="get_dept_filter_obj(' + index + ')" style="margin-left: 10px">评查</a> ';
                            return r + d;
                    }
                }
            ]]
        });
    }

    $('#table_eval_build_kp_custom').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 筛选窗体
    if (type == '0') {
        $('#win_eval_build_kp_dept_filter').window('setTitle','已评查案件列表');
    }else {
        $('#win_eval_build_kp_dept_filter').window('setTitle','未评查案件列表');
    }
    $('#win_eval_build_kp_dept_filter').window('open');
}



// 案件筛选（总表）
function get_dept_filter_obj(index){

    // 选择模板，进行评查
    alert_win_keypoint_pcmb(index, 'table_eval_build_kp_dept_filter', function () {
        $('#win_eval_build_kp_dept_filter').window('close');
    })

}

// 选择评查模板（重点总表 + 自定义）[callback 回调函数，案件筛选成功后调用]
function alert_win_keypoint_pcmb(index, tableName, callback) {

    // 获取模板编码集合(通过筛选规则获取评查模板集合)：
    var obj = new Object();
    obj.PCFLBM = pcxx.PCFLBM;
    obj.SXGZBM = $('#'+tableName+'').datagrid('getRows')[index].SXGZBM;

    $.ajax({
        url: getRootPath() + "/filter/getPcmbj",
        data: { json: JSON.stringify(obj) },
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {
            if (result.status == 200) {

                // 评查模板集合：
                var pcmbj = result.value;
                if (isNull(pcmbj)) {
                    Alert("该规则尚未绑定模板");
                    return;
                }

                // 单模板
                if (pcmbj.length == 1) {
                    var pcmbbm= pcmbj[0].PCMBBM;
                    filter_keypoint_case(pcmbbm, index, tableName, callback);
                    return;
                }

                // 多模板
                if (pcmbj.length >=2) {

                    // 初始化评查模板列表：
                    $('#table_build_keypoint_mb').datagrid({
                        fitColumns: true,
                        singleSelect: true,
                        checkOnSelect: false,
                        rownumbers: true,
                        idField: 'ID',
                        columns:[[
                            { field: 'ID', title: '唯一标示', hidden: true },
                            {field: 'ck', title: '复选框', checkbox: true, width: 80},
                            {field:'PCMBBM',title:'评查模板编码', width:90, hidden:true},
                            {field:'PCMBMC',title:'评查模板', width:90}
                        ]]
                    });

                    $('#table_build_keypoint_mb').datagrid('loadData',pcmbj);


                    // 确认
                    $("#btn_eval_build_keypoint_mb_confirm").unbind("click");
                    $("#btn_eval_build_keypoint_mb_confirm").bind("click", function () {

                        var row = $('#table_build_keypoint_mb').datagrid("getSelected");
                        if(!row){
                            Alert("请选择一个模板！");
                            return;
                        }

                        var pcmbbm =row.PCMBBM;
                        filter_keypoint_case(pcmbbm, index, tableName, function () {

                            $('#win_eval_build_keypoint_mbbm').window('close');

                            // 关闭未评查案件列表
                            if (callback) {
                                callback();
                            }
                        });

                    });

                    // 取消
                    $("#btn_eval_build_keypoint_mb_cancle").unbind("click");
                    $("#btn_eval_build_keypoint_mb_cancle").bind("click", function () {
                        $('#win_eval_build_keypoint_mbbm').window('close');
                    });

                    // 弹出模板选择框
                    $('#win_eval_build_keypoint_mbbm').window('open');
                }
            }

        }
    });

}

// 案件筛选（总表）
function get_custom_filter_obj(index){

    // 评查模板确认
    alert_win_keypoint_pcmb(index, 'table_eval_build_kp_custom', function(){

    });
}
// 案件筛选（高级查询）
function get_custom_filter_obj_gjcx(index){

    // 评查模板确认
    alert_win_keypoint_pcgz_pcmb(index,'kd_gjcx_datagrid',function () {

    });
}

// 选择评查模板（高级查询）[callback 回调函数，案件筛选成功后调用]
function alert_win_keypoint_pcgz_pcmb(index, tableName, callback) {
    var PCHDBM = "";
    if(isZb){
        PCHDBM = $("#cbt_eval_build_kp_chart_pcmb").combotree('getValue');
    }else {
        PCHDBM = $("#cbt_eval_build_kp_custom_pcmb").combotree('getValue');
    }
    // 获取评查活动绑定的筛选规则列表
    $('#cbt_eval_bulid_sp_sxgzRand_kp').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getHdsxgz',
        queryParams: {
            pcflbm: pcxx.PCFLBM,
            pchdbm: PCHDBM
        },
        onLoadSuccess:function(node,data){
            // if(data.length > 0){
            //     $('#cbt_eval_bulid_sp_sxgzRand_kp').combotree('setValue',data[0]);
            // }
            index_addMousedownDiv(this,'cbt_eval_bulid_sp_sxgzRand_kp');
        },
        onSelect: function (node) {
            $('#cbt_eval_bulid_sp_sxgzRand_mb_kp').combotree('loadData', []);
            $('#cbt_eval_bulid_sp_sxgzRand_mb_kp').combotree('setValue', "");
            $('#cbt_eval_bulid_sp_sxgzRand_mb_kp').combotree('setText', "");

            var obj = new Object();
            obj.PCFLBM = pcxx.PCFLBM;
            obj.SXGZBM = node.id;
            // 获取模板编码集合(通过筛选规则获取评查模板集合)
            $.ajax({
                url: getRootPath() + "/filter/getPcmbj",
                data: { json: JSON.stringify(obj)},
                type: 'get',
                async: true,
                dataType: 'json',
                success: function (result) {
                    if (result.status == 200) {

                        // 获取评查活动绑定的筛选规则列表
                        var pcmbj = result.value;
                        var data = [];
                        for(var i = 0; i < pcmbj.length; i++) {
                            data.push({ id: pcmbj[i].PCMBBM, text: pcmbj[i].PCMBMC });
                        }
                        $('#cbt_eval_bulid_sp_sxgzRand_mb_kp').combotree('loadData', data);
                    }
                }
            });

        }
    });

    // 确认
    $("#btn_eval_build_keypoint_mb_confirm_gzmb").unbind("click");
    $("#btn_eval_build_keypoint_mb_confirm_gzmb").bind("click", function () {

        var sxgzbm = $('#cbt_eval_bulid_sp_sxgzRand_kp').combotree('getValue');
        if(isNull(sxgzbm)){
            Alert("请选择筛选规则！");
            return;
        }
        var pcmbbm = $('#cbt_eval_bulid_sp_sxgzRand_mb_kp').combotree('getValue');
        if(isNull(pcmbbm)){
            Alert("请选择评查模板！");
            return;
        }

        // 评查案件筛选
        kd_SXGZBM = sxgzbm;
        filter_keypoint_case(pcmbbm, index, tableName, function () {
            $('#win_eval_build_keypoint_gzmb').window('close');
            // 关闭未评查列表弹窗
            if (callback) {
                callback();
            }
        });

    });

    // 取消
    $("#btn_eval_build_keypoint_mb_cancle_gzmb").unbind("click");
    $("#btn_eval_build_keypoint_mb_cancle_gzmb").bind("click", function () {
        $('#win_eval_build_random_gzmb').window('close');
    });

    // 弹出模板选择框
    $('#win_eval_build_keypoint_gzmb').window('open');
}

// 重点评查
function filter_keypoint_case(pcmbbm, index, tableName, callback){
    var param = $('#'+tableName+'').datagrid('getRows')[index];
    var PCHDBM = "";
    if(isZb){
        PCHDBM = $("#cbt_eval_build_kp_chart_pcmb").combotree('getValue');
    }else {
        PCHDBM = $("#cbt_eval_build_kp_custom_pcmb").combotree('getValue');
    }
    Confirm("确认", "是否对案件【" + param.AJMC + "】发起评查？", function (r) {
        if (r) {

            ShowProgress();

            // 高级查询：
            if (tableName == 'kd_gjcx_datagrid') {
                param.WCBZRQ = param.WCRQ;
                param.SXGZBM = kd_SXGZBM;
                param.DWBM = param.CBDW_BM;
            }
            // JS对象
            var obj = new Object();
            obj.PCFLBM = pcxx.PCFLBM;
            obj.PCHDBM = PCHDBM;
            obj.PCMBBM = pcmbbm;
            obj.BMSAH = param.BMSAH;
            obj.SXGZBM = param.SXGZBM;
            obj.BPC_WCBZRQ = param.WCBZRQ;
            obj.BPC_DWBM = param.DWBM;
            obj.PCR_DWBM = userInfo.DWBM;
            obj.PCR_DWMC = userInfo.DWMC;
            obj.PCR_GH = userInfo.GH;
            obj.PCR_MC = userInfo.MC;

            $.ajax({
                url: getRootPath() + "/filter/uptSjpcsx",
                data: { json: JSON.stringify(obj) },
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {

                    if (result.status == 200) {

                        $('#'+tableName+'').datagrid('deleteRow',index);
                        var rows = $('#'+tableName+'').datagrid('getRows');
                        $('#'+tableName+'').datagrid('loadData',rows);

                        CloseProgress();
                        Confirm("确认", "筛选成功，是否跳转到评查办理页面？", function (r) {
                            if (r) {
                                // 跳转到评查办理界面
                                if (callback){
                                    callback();
                                }
                                goto_eval_handle_page(result.value);
                                $("#window_gjcx_kp").window("close");
                            }
                        });
                    } else {
                        CloseProgress();
                        Alert(result.note);
                    }

                }
            });
        }
    });
}

// 查看案件详细信息
function lookup_keypoint_case(bmsah) {

    $.ajax({
        type: 'GET',
        url: getRootPath()+'/caseinfo/getCaseInfo',
        data: { bmsah: bmsah, pcslbm: "" },
        dataType: "json",
        success: function (result) {

            if (result.status == 200){
                var data = result.value;
                $('#win_eval_build_kp_caseinfo').window({
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
                $('#win_eval_build_kp_caseinfo').window('open');
            } else {
                Alert(result.note);
            }
        }
    });

}

// 跳转到评查办理界面
function goto_eval_handle_page(data) {

    var obj = new Object();
    obj.PCSLBM = data.PCSLBM;
    obj.PCCZLX = '1'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = 'PCYYJ'; //仅评查审批阶段有
    obj.PCYJMC = '评查员'; //评查意见名称
    var url = "view/evaluate/handle/deal.html";
    load_function("评查办理", url, obj);

}

// Excel表格导出
function kp_chart_export() {

    // 拼接总表表格数据,同时保留表头field：
    var array = new Array();
    var all = $('#table_eval_build_kp_chart').datagrid('options').columns;
    var header='[';
    for (var i =0;i <all[0].length; i++) {
        if (!all[0][i].hidden ) {
            header +=  all[0][i].title +',';
            array.push(all[0][i].field);
        }
    }


    // 拼接总表表格数据：
    var totalData = $("#table_eval_build_kp_chart").datagrid('getRows');
    var objData = new Array();
    for (var i =0; i< totalData.length; i++) {
        var data =  totalData[i];
        var rowData = "*";

        // 使用表头的key:
        for (var j=0;j < array.length; j++) {
            rowData += "," +  data[array[j]];
        }

        objData[i] =rowData;
    }

    var obj = new Object();
    obj.filename='总表筛选表';
    obj.header = header.substr(0,header.length-1)+']';
    obj.data=objData;

    $.ajax({
        type: 'post',
        url: getRootPath()+'/common/exportExcel',
        data: {
            json: JSON.stringify(obj)
        },
        dataType: "json",
        success: function (result) {

            if (result.code == 200){
                window.location.href =getRootPath()+ result.data;
            }
        }

    });
}

// 总表单元格左边单机
function ypc_kp_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    var rowDatas = $('#table_eval_build_kp_chart').datagrid('getRows');
    alert_win_eval_build_kp_chart_filter(currIndex,rowDatas[currIndex], currField,'0');
}

// 总表单元格右边单机
function wpc_kp_click (el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    var rowDatas = $('#table_eval_build_kp_chart').datagrid('getRows');
    alert_win_eval_build_kp_chart_filter(currIndex,rowDatas[currIndex], currField,'1');
}

