var pcxx;
var rd_SXGZBM;
var ISDJDW = false;
var myObj;
$(document).ready(function () {

    // 新建评查界面传递的参数，评查分类及评查模板等信息
    pcxx = FUNCTION_PARAM;

    // 界面标签样式设置及事件绑定
    eval_random_marksInit(pcxx);

    // 标签初始化数据加载
    eval_random_marksDataBind(pcxx);
});

// 界面标签样式设置及事件绑定
function eval_random_marksInit(pcxx) {
    //市院有选择其他院案件的权限
    // if(DJDWBM == userInfo.DWBM){
        ISDJDW = true;
        $('#cbt_eval_build_rd_custom_pcdw_div').css('display', 'block');
        $('#cbt_eval_build_rd_custom_pcdw').combotree({
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
                    $('#cbt_eval_build_rd_custom_pcdw').combotree('setValue', data[0].id); //单位默认选择
                }
                index_addMousedownDiv(this,'cbt_eval_build_rd_custom_pcdw');
            }
        });
    // }

    //检查官筛选条件加载
    init_eval_build_rd_stuff_codition(pcxx);
    // 检察官总表样式初始化
    $('#table_eval_build_rd_stuff').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'GH',
        pagination:true,
        pageNumber:1,
        pageSize:20
    });

    //设置检察官默认查询为当年时间
    //完成日期
    $('#date_eval_build_rd_stuff_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_eval_build_rd_stuff_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    $('#table_eval_build_rd_stuff').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });
    // 检察官
    $("#btn_eval_build_rd_stuff").unbind( "click" );
    $("#btn_eval_build_rd_stuff").bind("click", function () {
        $("#page_eval_build_rd_stuff").css("left","0");
        $("#page_eval_build_rd_custom").css("left","100%");
        // 加载检察官总表
        var data = $('#table_eval_build_rd_stuff').datagrid('getRows');
        if (!data || data.length <= 0){
            load_table_eval_build_rd_stuff(pcxx);
        }
    });
    // 查询
    $("#btn_eval_build_rd_stuff_search").unbind( "click" );
    $("#btn_eval_build_rd_stuff_search").bind("click", function () {
        // 加载检察官总表
        load_table_eval_build_rd_stuff(pcxx);
    });

    // 检察官导出excel表格
    $("#btn_eval_build_rd_stuff_export").bind('click',function () {
        rd_stuff_export();
    });
    // 自定义查询
    $('#table_eval_build_rd_custom').datagrid({
        border:0,
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
            {field:'BMSAH',title:'部门受案号',width:160,sortable:true },
            {field:'AJMC',title:'案件名称',width:160,sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90,sortable:true},
            {field:'DWMC',title:'承办单位',width:90,sortable:true},
            {field:'CBR',title:'承办检察官',width:90,sortable:true},
            {
                field: 'SLRQ', title: '受理日期', sortable: true, width: 90,
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
            {field:'WCBZRQ',title:'完成日期',width:90,sortable:true,
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
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_rd_custom\',1)">查看</a>';

                    /*var r = '<a href="#" onclick="lookup_random_case(\'' + row.BMSAH + '\')">查看</a> ';*/
                    var d = '<a href="#" onclick="get_custom_filter_obj(' + index + ')" style="margin-left: 10px">评查</a> ';

                    return r + d;
                }
            }
         ]]
    });
    $('#table_eval_build_rd_custom').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    //自定义默认查询为当年时间

    if(pcxx.PCFLBM == "001"){//湖北的常规评查
        //完成日期
        $('#date_eval_build_rd_custom_begin').datebox({
            editable: true,
            // value:'2017-01-01'
            value: new Date().getFullYear() + '-01-01'
        });

        $('#date_eval_build_rd_custom_end').datebox({
            editable: true,
            // value:'2017-12-31'
            value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
        });
    }else if(pcxx.PCFLBM == "007"){//湖北的随机评查
        //完成日期
        $('#date_eval_build_rd_custom_begin').datebox({
            editable: true,
            value:'2013-01-01'
            // value: new Date().getFullYear() + '-01-01'
        });

        $('#date_eval_build_rd_custom_end').datebox({
            editable: true,
            value:'2016-12-31'
            // value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
        });
    }else{
        //完成日期
        $('#date_eval_build_rd_custom_begin').datebox({
            editable: true,
            value: new Date().getFullYear() + '-01-01'
        });

        $('#date_eval_build_rd_custom_end').datebox({
            editable: true,
            value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
        });
    }


    // 切换选择交叉案件
    $('#eval_rd__is_jxpc').switchbutton({
        checked: false,

        onChange: function(checked){
            //console.log(checked);
            if(checked){

                var dw = $("#cbt_eval_build_rd_custom_pcdw").combotree('getValue');
                dw = userInfo.DWBM; // 交叉暂时只能查询当前人兄弟单位分配的案件
                // 接收单位,与承办单位平级
                $('#cbt_eval_build_rd_custom_pcdw').combotree({
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
                        dwbm:  dw
                    },
                    async: false,
                    loadFilter: function (data) {
                        return isNull(data) ? []  : data;
                    },
                    onLoadSuccess: function (node, data) {
                        if (data != null && data.length >= 1) {
                            $('#cbt_eval_build_rd_custom_pcdw').combotree('setValue', data[0].id); //单位默认选择
                        }
                        index_addMousedownDiv(this,'cbt_eval_build_rd_custom_pcdw');
                    }
                });

            }else{
                $('#cbt_eval_build_rd_custom_pcdw').combotree({
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
                            $('#cbt_eval_build_rd_custom_pcdw').combotree('setValue', data[0].id); //单位默认选择
                        }
                        index_addMousedownDiv(this,'cbt_eval_build_rd_custom_pcdw');
                    }
                });
            }
        }
    })

    // 案件筛选
    $("#btn_eval_build_rd_custom").unbind( "click" );
    $("#btn_eval_build_rd_custom").bind("click", function () {
        $("#page_eval_build_rd_stuff").css("left","-100%");
        $("#page_eval_build_rd_custom").css("left","0");
        // 初始化自定义筛选条件
        init_eval_build_rd_custom_condition(pcxx);
        // 查询
        $("#btn_eval_build_rd_custom_search").unbind( "click" );
        $("#btn_eval_build_rd_custom_search").bind("click", function () {

            // 加载案件列表
            load_table_eval_build_rd_custom(pcxx);

        });
    });

    // 样式设置
    $(".pcajsx_tj_2").click(function(){
        $(this).addClass("pcajsx_tj_click");
        $(this).siblings().removeClass("pcajsx_tj_click")
    });

    //高级查询
    $("#btn_eval_build_rd_custom_search_advance").unbind( "click" );
    $("#btn_eval_build_rd_custom_search_advance").bind("click", function () {
        $("#window_gjcx_rd").window("open");
        $("#window_gjcx_rd").window("refresh","view/evaluate/build/special_gjcx_rd.html");
    });
}

// 标签初始化数据加载
function eval_random_marksDataBind(pcxx) {
    // 默认加载案件筛选
    $("#btn_eval_build_rd_custom").click();
}

//初始化检察官筛选条件
function init_eval_build_rd_stuff_codition(pcxx) {

    $('#cbt_eval_build_rd_stuff_pcmb').combotree({
        method: 'get',
        lines: true,
        queryParams:{
            pcflbm: pcxx.PCFLBM
        },
        url: getRootPath()+'/manage/get_pchd',
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_eval_build_rd_stuff_pcmb').combotree('setValue', data[0].id);
            }
        }
    });
    // 承办部门树加载
    $('#cbbm_stuff').combotree({
        method: 'get',
        lines: true,
        multiple: true,
        cascadeCheck:false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        queryParams:{
            pcflbm: pcxx.PCFLBM
                },
        url: getRootPath()+'/filter/getBmList',
        onLoadSuccess: function (node, data) {
            index_addMousedownDiv(this,'cbbm_stuff');
        }
    });
}
// 检察官总表
function load_table_eval_build_rd_stuff(pcxx, pageIndex, pageSize) {
    var pchdbm = $('#cbt_eval_build_rd_stuff_pcmb').combotree('getValue');
    if(!pageIndex){
        pageIndex = 1;
    }
    if(!pageSize){
        pageSize = $('#table_eval_build_rd_stuff').datagrid('getPager').pagination('options').pageSize;
    }

    // JS对象
    var BPCDWBM =  $("#cbt_eval_build_rd_stuff_pcdw").combotree('getValue');
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = pchdbm;
    obj.SXGZBM = '';
    obj.CBDWBM = userInfo.DWBM;
    ISDJDW ? obj.CBDWBM = (BPCDWBM != '' ? BPCDWBM : userInfo.DWBM) : userInfo.DWBM;
    obj.CBBMBM = $("#cbbm_stuff").combotree('getValues').join(',');
    obj.CBRXM = '';
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQBNG = '';
    obj.WCRQBNG = '';
    obj.WCRQBNG = '';
    obj.GZRQBNG = $('#date_eval_build_rd_stuff_begin').datebox('getValue');
    obj.GZRQEND = $('#date_eval_build_rd_stuff_end').datebox('getValue');
    obj.ZDYCXTJ = '';
    obj.page = pageIndex;
    obj.rows = pageSize;
    //判断时间
    if(!checkTime(obj.GZRQBNG,obj.GZRQEND)){
        return;
    }
    ShowProgress();
    myObj = obj;
    // 请求数据
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/filter/get_sjsx_jcg',
        data: {
            json: JSON.stringify(obj)
        },
        dataType: "json",
        success: function (result) {

            if (result.status == 200){
                var data = result.value;
                // 生成检察官总表
                generate_table_eval_build_rd_stuff(data.Columns, data.Rows, data.KeyValues);

                $('#table_eval_build_rd_stuff').datagrid('getPager').pagination({
                    total:data.count,
                    pageNumber:pageIndex,
                    pageSize:pageSize,
                    beforePageText: '第',
                    afterPageText: '页   共{pages}页',
                    displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录',
                    onSelectPage:function(pageNumber, pageSize){
                        load_table_eval_build_rd_stuff(pcxx, pageNumber, pageSize);
                    }
                });

                CloseProgress();
            } else {
                CloseProgress();
                Alert(result.note);
            }
        }
    });
}

// 生成检察官总表
function generate_table_eval_build_rd_stuff(columns, rows, data) {

    if (columns.length <= 0) return;

    // 清空历史数据
    $('#table_eval_build_rd_stuff').datagrid('loadData',[]);

    // 动态生成行/列
    var colstr = "{field:'GH',title:'工号',hidden:true, align: 'center', halign: 'center'}," +
        "{field:'MC',title:'检察官',width:100, align: 'center', halign: 'center',fixed:'true'}," +
        "{field:'PCL',title:'评查率(%)',width:90, align: 'center', halign: 'center',fixed:'true', formatter: function (value, row, index) {row.PCL = cacluePcl(row); return cacluePcl(row);}}," +
        "{field:'PCZS',title:'评查数(件)',width:90, align: 'center', halign: 'center',fixed:'true'},"; //拼接动态列
    var rowFormat = "GH:'@GH', MC:'@MC', PCL:'0', PCZS:'0/0',"; //拼接每行，默认（0,0）
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
    var table_widht = $(".pcajsx_content_1").width();
    // 添加列
    var options = {};
    options.columns = eval("[[" + colstr + "]]");
    $('#table_eval_build_rd_stuff').datagrid(options);

    // 添加行
    for (var i = 0; i < rows.length; i++) {
        if (!rows[i])
            continue;

        var rowData = rowFormat;
        rowData = rowData.replace("@GH", rows[i].GH);
        rowData= rowData.replace("@MC", rows[i].MC);
        // 添加每一行数据
        var row = eval("({" + rowData + "})");
        $('#table_eval_build_rd_stuff').datagrid('appendRow', row);
    }

    // 填数据
    for (var i = 0; i < data.length; i++) {
        if (!data[i])
            continue;

        var rowIndex = $('#table_eval_build_rd_stuff').datagrid('getRowIndex', data[i].GH);
        if (rowIndex == -1) return;

        // 已经评查数量/总量------>已经评查/未评查
       // var row = eval("({" + data[i].GZBM + ':"<a style=\'cursor:pointer;font-weight:bold\'>" + data[i].YPC + "/" + (data[i].ZS -data[i].YPC) + "</a>"})');
        var rowx=data[i].GZBM +":\"<a style='cursor:pointer;font-weight:bold'><span data-index="+ rowIndex +" data-field="+ data[i].GZBM +" class='ypc' onclick=ypc_jcg_click(this)>" + data[i].YPC +"</span>/<span data-index="+rowIndex+" data-field="+ data[i].GZBM +" onclick=wpc_jcg_click(this) class='wpc'>" +(data[i].ZS-data[i].YPC) +"</span></a>\""
        var row = eval("({"+rowx+"})");
        // 更新数据
        $('#table_eval_build_rd_stuff').datagrid('updateRow',{
            index: rowIndex,
            row: row
        });
    }

    // 去除数据为0/0的整列，并隐藏,由于有数据的项添加了点击事件，可以根据这个来判断
    // 检察院去除数据为0/0的整列，并隐藏,由于有数据的项添加了点击事件，可以根据这个来判断
    var keyArray = new Array();
    var rows = $('#table_eval_build_rd_stuff').datagrid('getRows');
    var rowLength =rows.length;
    var firstrowData =rows[0];

    // 遍历第一行保留单元格为0/0的key,没有则不存在数据为0/0的整列
    // 行单元格为0/0填充数组：
    for (var key in firstrowData ) {
        // 保留前四列
        if (key !='GH' && key !='MC' && key !='PCL' && key !='PCZS') {
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

                $('#table_eval_build_rd_stuff').datagrid('hideColumn',keyArray[i]);
            }
        }
    }

    var data = $('#table_eval_build_rd_stuff').datagrid('getData');
    $('#table_eval_build_rd_stuff').datagrid('loadData', data);

}

// 自定义案件列表
function load_table_eval_build_rd_custom(pcxx) {
    var bmsah = $('#txt_eval_build_rd_custom_bmsah').textbox('getValue').trim();
    var ajmc = $('#txt_eval_build_rd_custom_ajmc').textbox('getValue').trim();
    var cbrxm = $('#txt_eval_build_rd_custom_cbjcg').textbox('getValue').trim();
    var sxgzbm = $('#cbt_eval_build_rd_custom_sxgz').combotree('getValue');
    var sxgzmc = $('#cbt_eval_build_rd_custom_sxgz').combotree('getText').trim();

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
    var BPCDWBM = $("#cbt_eval_build_rd_custom_pcdw").combotree('getValue');
    var pchdbm = $("#cbt_eval_build_rd_custom_pcmb").combotree('getValue');
    // JS对象
    var obj = new Object();
    obj.PCDWBM = userInfo.DWBM;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = pchdbm;
    obj.GZDWBM = '';
    obj.SXGZBM = $('#cbt_eval_build_rd_custom_sxgz').combotree('getValue');
    obj.CBDWBM = userInfo.DWBM;
    //ISDJDW ? obj.CBDWBM = (BPCDWBM != '' ? BPCDWBM : userInfo.DWBM) : userInfo.DWBM;
    obj.CBDWBM = (BPCDWBM != '' ? BPCDWBM : userInfo.DWBM) ;
    obj.CBBMBM = $('#txt_eval_build_rd_custom_cbbm').combotree('getValue');
    obj.AJLB = '';
    obj.BMSAH = bmsah;
    obj.AJMC = ajmc;
    obj.CBRXM = cbrxm;
    obj.AY = '';
    obj.SLRQBNG = '';
    obj.SLRQEND = '';
    obj.BJRQBNG = '';
    obj.BJRQEND = '';
    obj.WCRQBNG = $('#date_eval_build_rd_custom_begin').datebox('getValue');
    obj.WCRQEND = $('#date_eval_build_rd_custom_end').datebox('getValue');
    obj.GZRQBNG = $('#date_eval_build_rd_custom_begin').datebox('getValue');
    obj.GZRQEND = $('#date_eval_build_rd_custom_end').datebox('getValue');
    obj.ZDYCXTJ = '';
    //判断时间
    if(!checkTime(obj.GZRQBNG,obj.GZRQEND)){
        return;
    }

    // 判断是否交叉评查
    var isjxkf = $("#eval_rd__is_jxpc").switchbutton("options").checked;
    obj.type = isjxkf ? 'Y' : 'N';

    var tree = $("#cbt_eval_build_rd_custom_sxgz").combotree("tree");
    var node = tree.tree("getSelected");
    var url = "";
    if(node.attributes.SFZDY == "Y"){
        url = getRootPath()+'/filter/getSjsx';
    }else{
        url = getRootPath()+'/filter/getSjsxAdvance';
    }
    $('#table_eval_build_rd_custom').datagrid({
        url: url,
        queryParams: { json : JSON.stringify(obj) }
    });
}

//加载筛选规则
function load_cbt_eval_build_rd_custom_sxgz(pchdbm){
    $('#cbt_eval_build_rd_custom_sxgz').combotree('loadData',[]);
    $('#cbt_eval_build_rd_custom_sxgz').combotree('clear');
    $('#cbt_eval_build_rd_custom_sxgz').combotree('setValue', '');
    // 获取评查活动绑定的筛选规则列表
    $('#cbt_eval_build_rd_custom_sxgz').combotree({
        method: 'get',
        lines: true,
        panelWidth:260,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getHdsxgz',
        queryParams: {
            pcflbm: pcxx.PCFLBM,
            pchdbm: pchdbm
        },
        onLoadSuccess:function(node,data){
            if(data.length > 0){
                $('#cbt_eval_build_rd_custom_sxgz').combotree('setValue',data[0].id);
            }
            index_addMousedownDiv(this,"cbt_eval_build_rd_custom_sxgz");
        }
    });
}

// 初始化自定义筛选条件
function init_eval_build_rd_custom_condition(pcxx) {
    var path = "/template/template";
    if(pcxx.SFJS == "N"){
        path = "/manage/get_pchd";
    }
    // 获取评查模板
    $('#cbt_eval_build_rd_custom_pcmb').combotree({
        method: 'get',
        lines: true,
        panelWidth:260,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+ path,
        queryParams: {
            pcflbm: pcxx.PCFLBM
        },
        onLoadSuccess:function(node,data){
            if (data != null && data.length >= 1){
                pchdbm = data[0].id;
                $('#cbt_eval_build_rd_custom_pcmb').combotree('setValue',  pchdbm);
                load_cbt_eval_build_rd_custom_sxgz(pchdbm);
            }
            index_addMousedownDiv(this,"cbt_eval_build_rd_custom_pcmb");
        },
        onSelect: function (node) {
            load_cbt_eval_build_rd_custom_sxgz(node.id);
        }
    });

    // 承办部门树加载
    $('#txt_eval_build_rd_custom_cbbm').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getAllBm',
        onLoadSuccess: function (node, data) {
            index_addMousedownDiv(this,'txt_eval_build_rd_custom_cbbm');
        }
    });
}

// 检察官对应未评查案件列表
function alert_win_eval_build_rd_jcg_filter(index,row, gzbm,type) {

    // 如果点击的是评查数或评查率那一栏，就查该部门下所有案件
    if(gzbm == 'PCZS' || gzbm == 'PCL'){
        var firstrowData = $('#table_eval_build_rd_stuff').datagrid('getRows')[index];
        for (var key in firstrowData ) {
            // 保留前四列
            if (key !='GH' && key !='MC' && key !='PCL' && key !='PCZS') {
                if (type == '0') { // 获取所有左侧有值的规则
                    // 单元格左侧
                    if(firstrowData[key] !="0/0" && firstrowData[key].charAt(126) !='0') {
                        gzbm += ","+key;
                    }

                }else {
                    // 单元格左侧
                    if(firstrowData[key] !="0/0" && firstrowData[key].charAt(218) !='0') {
                        gzbm += ","+key;
                    }
                }

            }
        }
        gzbm = gzbm.substr(5);
    }
    var obj = new Object();
    var cxpchdbm = $("#cbt_eval_build_rd_stuff_pcmb").combotree('getValue');
    obj.CBDWBM = userInfo.DWBM;
    ISDJDW ? obj.CBDWBM = $("#cbt_eval_build_rd_stuff_pcdw").combotree('getValue') : userInfo.DWBM;
    obj.CBRGH = row.GH;
    obj.SXGZBM = gzbm;
    obj.PCFLBM = pcxx.PCFLBM;
    obj.PCHDBM = cxpchdbm.substr(6);
    obj.GZRQBNG = $('#date_eval_build_rd_stuff_begin').datebox('getValue'); // 检察官规则开始
    obj.GZRQEND =$('#date_eval_build_rd_stuff_end').datebox('getValue'); // 检察官规则结束
    obj.type = type; //检察官已经评查/未评查（0/1）

    if (type =='0') {
        // 自定义查询
        $('#table_eval_build_rd_jcg_filter').datagrid({
            border:0,
            fit:true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath()+'/filter/getCbrwpcList',
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
                {field:'PCR_DWMC',title:'评查单位',width:90},
                {field:'CBRMC',title:'承办检察官',width:90},

                {field:'PCRQ',title:'评查日期', fixed:true, width: 115 ,
                    formatter: function (value) {
                        return sjzh(value);
                    }
                },
                {field: 'action', title: '操作', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                            var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_rd_jcg_filter\',0)">查看</a>'
                            return r;
                    }
                }
            ]]
        });
    }else {
        // 自定义查询
        $('#table_eval_build_rd_jcg_filter').datagrid({
            border:0,
            fit:true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath()+'/filter/getCbrwpcList',
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
                        var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_rd_jcg_filter\',1)">查看</a>'
                        var d = '<a href="#" onclick="get_jcg_filter_obj(' + index + ')" style="margin-left: 10px">评查</a> ';
                            return r+d;
                    }
                }
            ]]
        });
    }

    $('#table_eval_build_rd_jcg_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 筛选窗体
    if (type == '0') {
        $('#win_eval_build_rd_jcg_filter').window('setTitle','已评查案件列表');
    }else {
        $('#win_eval_build_rd_jcg_filter').window('setTitle','未评查案件列表');
    }

    $('#win_eval_build_rd_jcg_filter').window('open');

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

// 筛选案件（检察官）
function get_jcg_filter_obj(index){

    // 评查模板确认
    alert_win_random_pcmb(index, 'table_eval_build_rd_jcg_filter', function () {
        $('#win_eval_build_rd_jcg_filter').window('close');
    });

}

// 选择评查模板（高级查询）[callback 回调函数，案件筛选成功后调用]
function alert_win_random_pcgz_pcmb(index, tableName, callback) {

    // 获取评查活动绑定的筛选规则列表
    var pchdbm = $("#cbt_eval_build_rd_custom_pcmb").combotree('getValue');
    $('#cbt_eval_bulid_sp_sxgzRand').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/filter/getHdsxgz',
        queryParams: {
            pcflbm: pcxx.PCFLBM,
            pchdbm: pchdbm
        },
        onLoadSuccess:function(node,data){
        },
        onSelect: function (node) {
            $('#cbt_eval_bulid_sp_sxgzRand_mb').combotree('loadData', []);
            $('#cbt_eval_bulid_sp_sxgzRand_mb').combotree('setValue', "");
            $('#cbt_eval_bulid_sp_sxgzRand_mb').combotree('setText', "");

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
                        $('#cbt_eval_bulid_sp_sxgzRand_mb').combotree('loadData', data);
                    }
                }
            });

        }
    });

    // 确认
    $("#btn_eval_build_random_mb_confirm_gzmb").unbind("click");
    $("#btn_eval_build_random_mb_confirm_gzmb").bind("click", function () {

        var sxgzbm = $('#cbt_eval_bulid_sp_sxgzRand').combotree('getValue');
        if(isNull(sxgzbm)){
            Alert("请选择筛选规则！");
            return;
        }
        var pcmbbm = $('#cbt_eval_bulid_sp_sxgzRand_mb').combotree('getValue');
        if(isNull(pcmbbm)){
            Alert("请选择评查模板！");
            return;
        }

        // 评查案件筛选
        rd_SXGZBM = sxgzbm;
        filter_random_case(pcmbbm, index, tableName, function () {
            $('#win_eval_build_random_gzmb').window('close');
            // 关闭未评查列表弹窗
            if (callback) {
                callback();
            }
        });

    });

    // 取消
    $("#btn_eval_build_random_mb_cancle_gzmb").unbind("click");
    $("#btn_eval_build_random_mb_cancle_gzmb").bind("click", function () {
        $('#win_eval_build_random_gzmb').window('close');
    });

    // 弹出模板选择框
    $('#win_eval_build_random_gzmb').window('open');
}

// 选择评查模板（随机总表 + 检察官 + 自定义）[callback 回调函数，案件筛选成功后调用]
function alert_win_random_pcmb(index, tableName, callback) {

    var obj = new Object();
    obj.PCFLBM = pcxx.PCFLBM;
    obj.SXGZBM = $('#'+tableName+'').datagrid('getRows')[index].SXGZBM;

    // 获取模板编码集合(通过筛选规则获取评查模板集合)
    $.ajax({
        url: getRootPath() + "/filter/getPcmbj",
        data: { json: JSON.stringify(obj)},
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (result) {
            if (result.status == 200) {

                var pcmbj = result.value;
                if (isNull(pcmbj)) {
                    Alert("该规则尚未绑定评查模板");
                    return;
                }

                // 单模板
                if (pcmbj.length == 1) {
                    var pcmbbm= pcmbj[0].PCMBBM;
                    filter_random_case(pcmbbm, index, tableName, callback);
                    return;
                }

                // 多模板
                if (pcmbj.length >= 2) {

                    // 初始化评查模板列表
                    $('#table_build_random_mb').datagrid({
                        fitColumns: true,
                        singleSelect: true,
                        checkOnSelect: false,
                        rownumbers: true,
                        idField: 'ID',
                        columns:[[
                            { field: 'ID', title: '唯一标示', hidden: true },
                            { field: 'ck', title: '复选框', checkbox: true, width: 80 },
                            { field:'PCMBBM', title:'评查模板编码',width:90, hidden:true },
                            { field:'PCMBMC', title:'评查模板', width:90 }
                        ]]
                    });
                    $('#table_build_random_mb').datagrid('loadData', pcmbj);

                    // 确认
                    $("#btn_eval_build_random_mb_confirm").unbind("click");
                    $("#btn_eval_build_random_mb_confirm").bind("click", function () {

                        var row = $('#table_build_random_mb').datagrid("getSelected");
                        if(!row){
                            Alert("请选择评查模板！");
                            return;
                        }

                        // 评查案件筛选
                        var pcmbbm =row.PCMBBM;
                        filter_random_case(pcmbbm, index, tableName, function () {
                            $('#win_eval_build_random_mbbm').window('close');
                            // 关闭未评查列表弹窗
                            if (callback) {
                                callback();
                            }
                        });

                    });

                    // 取消
                    $("#btn_eval_build_random_mb_cancle").unbind("click");
                    $("#btn_eval_build_random_mb_cancle").bind("click", function () {
                        $('#win_eval_build_random_mbbm').window('close');
                    });

                    // 弹出模板选择框
                    $('#win_eval_build_random_mbbm').window('open');
                }
            }
        }
    });
}

// 筛选案件（自定义）
function get_custom_filter_obj(index){

    // 评查模板确认
    alert_win_random_pcmb(index,'table_eval_build_rd_custom',function () {

    });
}
// 筛选案件（自定义模式下高级查询）
function get_custom_filter_obj_gjcx(index){

    // 评查模板确认
    alert_win_random_pcgz_pcmb(index,'rd_gjcx_datagrid',function () {
        
    });
}

// 评查案件筛选
function filter_random_case(pcmbbm, index, tableName, callback) {
    var pchdbm = $("#cbt_eval_build_rd_custom_pcmb").combotree('getValue');
    var param = $('#'+tableName+'').datagrid('getRows')[index];
    if(param.DWBM==userInfo.DWBM&&param.CBR==userInfo.MC){
        Alert("不能评查自己承办的案件！");
        return ;
    }
    Confirm("确认", "是否对案件【" + param.AJMC + "】发起评查？", function (r) {
        if (r) {

            ShowProgress();

            // 如果是自定义高级筛选：
            if (tableName == 'rd_gjcx_datagrid') {
                param.WCBZRQ = param.WCRQ;
                param.SXGZBM = rd_SXGZBM;
                param.DWBM = param.CBDW_BM;
            }

            // JS对象
            var obj = new Object();
            obj.PCFLBM = pcxx.PCFLBM;
            obj.PCHDBM = pchdbm;
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

                        $('#'+tableName+'').datagrid('deleteRow', index);
                        var rows = $('#'+tableName+'').datagrid('getRows');
                        $('#'+tableName+'').datagrid('loadData', rows);

                        CloseProgress();
                        Confirm("确认", "筛选成功，是否跳转到评查办理页面？", function (r) {
                            if (r) {
                                // 跳转到评查办理界面
                                if (callback){
                                    callback();
                                }
                                goto_eval_handle_page(result.value);
                                $("#window_gjcx_rd").window("close");
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


// 导出检察官Excel表格
function rd_stuff_export() {
    myObj.rows = undefined;
    myObj.page = undefined;
    $.ajax({
        type: 'post',
        url: getRootPath()+'/filter/getSjsxJcgGetExecel',
        data: {
            json: JSON.stringify(myObj)
        },
        dataType: "json",
        success: function (result) {
            if (result.code == 200){
                var path = getRootPath() + result.data;
                window.location.href = path;
            }
        }
    });


    // // 拼接检察官表格的表头,同时保留表头field：
    // var array = new Array();
    // var all = $('#table_eval_build_rd_stuff').datagrid('options').columns;
    // var header='[';
    // for (var i =0;i <all[0].length; i++) {
    //     if (!all[0][i].hidden ) {
    //         header +=  all[0][i].title + ',';
    //         array.push(all[0][i].field);
    //     }
    // }
    //
    //
    // // 拼接检察官表格数据：
    // var totalData = $("#table_eval_build_rd_stuff").datagrid('getRows');
    // var objData = new Array();
    // for (var i =0; i< totalData.length; i++) {
    //     var data =  totalData[i];
    //     var rowData = "*";
    //
    //     // 使用表头的key:
    //     for (var j=0;j < array.length; j++) {
    //         rowData += "," +  data[array[j]];
    //     }
    //
    //     objData[i] =rowData;
    // }
    //
    // var obj = new Object();
    // obj.filename='检察官筛选表';
    // obj.header = header.substr(0,header.length-1)+']';
    // obj.data=objData;
    //
    // $.ajax({
    //     type: 'post',
    //     url: getRootPath()+'/common/exportExcel',
    //     data: {
    //         json: JSON.stringify(obj)
    //     },
    //     dataType: "json",
    //     success: function (result) {
    //
    //         if (result.code == 200){
    //             window.location.href = getRootPath() + result.data;
    //             }
    //         }
    //
    // });
}

// 部门单元格左边
function ypc_bm_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    var rowDatas = $('#table_eval_build_rd_dept').datagrid('getRows');
    alert_win_eval_build_rd_dept_filter(currIndex,rowDatas[currIndex], currField,'0');
}

// 部门单元格右边点击
function wpc_bm_click(el) {

    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    var rowDatas = $('#table_eval_build_rd_dept').datagrid('getRows');
    alert_win_eval_build_rd_dept_filter(currIndex,rowDatas[currIndex], currField,'1');
}

// 检察官单元格左边单击
function ypc_jcg_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    var rowDatas = $('#table_eval_build_rd_stuff').datagrid('getRows');
    alert_win_eval_build_rd_jcg_filter(currIndex,rowDatas[currIndex], currField,'0');
}

// 检察官单元格右边单击
function wpc_jcg_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    var rowDatas = $('#table_eval_build_rd_stuff').datagrid('getRows');
    alert_win_eval_build_rd_jcg_filter(currIndex,rowDatas[currIndex], currField,'1');
}

function parseDom(arg) {
    var objE = document.createElement("div");
    objE.innerHTML = arg;
    return objE.childNodes;
}

// 计算评查率
function cacluePcl(row) {
    var rowPCZS =  row.PCZS;
    if(rowPCZS != "0/0"){
        var objE = document.createElement("div");
        objE.innerHTML = rowPCZS;
        var parseDom1 = objE.childNodes;
        var ypcValue = parseDom1[0].children[0].innerHTML;
        var wpcValue = parseDom1[0].children[1].innerHTML;
        var pcl = (parseInt(ypcValue)/(parseInt(wpcValue) + parseInt(ypcValue)))*100 ;
    }
    if (pcl != 0) {
        pcl = parseFloat(pcl).toFixed(2);
        if(isNaN(pcl)){
            pcl = 0;
        }
    }
    return pcl + "%";
    // return "111";
}

