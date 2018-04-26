/*creat by cj on 2018/1/25*/
/**
 * 案件质量年度趋势图相关js
 *
 */
var excel_url =[];
/**
 * 所有图初始化
 */
// 初始化质量问题分布饼图
// init_pie_zlwtfbt();
//
// // 初始化地图（map）
// init_map_map_dt_ajzlndqut();
//
// // 初始化地图-排名柱状图
// init_map_rank_dt_ajzlndqut();
//
// // 检察官办案质量排名柱状图
// init_axis_jcgbazlpm();
//
// // 部门办案质量排名斜圆环柱
// init_pie_bmbazlpm();

/*$(document).ready(function () {

    //初始化加载下拉框
    init_data();

    //导出Excel初始化
    init_eval_handle_transmit_Excel();

    //tab切换时加载初始化地图-排名柱状图
    $('#tt').tabs({
        border:false,
        onSelect:function(title){
            alert("点击tab");
            // 初始化地图-排名柱状图
            init_map_rank_dt_ajzlndqut();
            // return false;
        }
    });

    // 导出Excel初始化确认按钮
    /!*$("#export_excel_btn").unbind( "click" );
    $('#export_excel_btn').bind('click', function(){
        alert('easyui-linkbutton');
    });*!/
});*/

//？
function inittable_situ_build_rd_dept_filter() {
    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath()+'/count/bmBanAjZhiLiangPaiMinAjJbxx',

        queryParams: clickObj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]],
        loadFilter: function(data){
            if (data.code == 200) {
                return JSON.parse(data.data);
            } else {
                return [];
            }
        },
    });
}

$(function () {

    //初始化加载下拉框
    init_data();

    //导出Excel初始化
    // init_eval_handle_transmit_Excel();

    var mapwidth = $("#echarts_map_ajzlndqut").width();
    var mapheight = $("#echarts_map_ajzlndqut").height();
    $("#echarts_axis_ajzlndqut").css("width",mapwidth+"px");
    $("#echarts_axis_ajzlndqut").css("height",mapheight+"px");
    //tab切换时加载初始化地图-排名柱状图
    $('#tt').tabs({
        border:false,
        onSelect:function(title){
            // 初始化地图-排名柱状图
            init_map_rank_dt_ajzlndqut();
        }
    });

    // 初始化导出窗口
    init_eval_handle_transmit_Excel()

});

//导出Excel初始化
function init_eval_handle_transmit_Excel() {
    $("#transmit_Excel").bind("click", function () {
        $('#transmit').window('open');
    });
}

// 导出Excel初始化确认按钮
$("#export_excel_btn").unbind( "click" );
$('#export_excel_btn').bind('click', function(){


    // 获取要导出的数据类型
    var allSelected = $("#transmit :checked");
    if (allSelected.length ==0) {
        Alert("请勾选要导出的数据");
        return;
    }

    for (var i =0; i<allSelected.length; i++) {
        switch ($(allSelected[i]).val()){
            case '1': //地图
                echarts_export_excel_map();
                break;
            case '2': // 排名
                echarts_export_excel_Pm();
                break;
            case '3': // 检察官办案质量排名
                echarts_export_excel_jcg();
                break;
            case '4': // 质量问题分布图(不合格)
                echarts_export_excel_quantity(); // 不合格
                break;
            case '5': // 质量问题分布图(瑕疵)
                echarts_export_excel_quantityFlaw(); // 瑕疵
                break;
            case '6': // 部门办案质量排名
                echarts_export_excel_dept();
                break;

        }
    }

});


//查询条件初始化
function init_data() {

    //tab切换时加载初始化地图-排名柱状图
    /*$('#tt').tabs({
        border:false,
        onSelect:function(title){
            // 初始化地图-排名柱状图
            init_map_rank_dt_ajzlndqut();
        }
    });*/

    // 结果等次事件（检察官办案质量排名）
    $('#cbt_situation_btfx_jgdc').combotree({
        onClick: function(node){
            init_axis_jcgbazlpm();
            // return false;
        }

    });

    // 结果等次事件（部门办案质量排名）
    $('#cbt_situation_btfx_jgdc1').combotree({
        onClick: function(node){
            init_pie_bmbazlpm();
            // return false;
        }

    });


    //查询日期
    var dateJson = [];
    var date=new Date;
    var currYear=date.getFullYear();
    for(var i = 2016;i <= currYear;i++){
        dateJson.push({
            value:i
        });
    }

    //单位combotree初始化
    $('#cbt_situation_pcaj_dw').combotree({
        method:'get',
        editable: false,
        panelWidth:270,
        lines: true,
        multiple: false,
        cascadeCheck:false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        async:false,
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1){
                dt =data[0].id;
                $('#cbt_situation_pcaj_dw').combotree('setValue', data[0].id); //单位默认选择
                index_addMousedownDiv(this,'cbt_situation_pcaj_dw');
            }
            // 初始化质量问题分布饼图
            init_pie_zlwtfbt();

            // 初始化地图（map）
            init_map_map_dt_ajzlndqut();

        }
    });


    //开始日期datebox初始化
    $('#cbt_situation_btfx_start').datebox({
        editable: false,
        value: new Date().getFullYear()-1 + '-' + 1 + '-' + 1

        /*onLoadSuccess:function () {
            debugger;
            $("#cbt_situation_btfx_start").datebox("setValue","2016-01-01");
        }*/

    });

    //结束日期datebox初始化
    $('#cbt_situation_btfx_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    //评查方式combobox初始化
    /* $('#cbt_situation_btfx_pcfs').combobox({
         idField: 'id',
         valueField :'label',
         textField: 'value',
         data: [{
             label: '001',
             value: '随机'
         }, {
             label: '002',
             value: '专项'
         }, {
             label: '003',
             value: '重点'
         }],
         onLoadSuccess: function (data) {
             $('#cbt_situation_btfx_pcfs').combobox('setValue', data[0].label); //评查方式默认选择
         }
     });*/


    //结果等次combobox初始化（检察官办案质量排名）
    $('#cbt_situation_btfx_jgdc').combotree({
        lines: true,
        multiple: false,
        cascadeCheck:false,
        editable: false,
        url: getRootPath()+'/filter/getPcjl',
        /*loadFilter: function (data) {
            /!*if(data == null){
                data = new Array();
            }*!/
            return data;
        },*/
        onLoadSuccess: function (data) {

            $('#cbt_situation_btfx_jgdc').combotree('setValue', '瑕疵案件'); //结果等次默认选择瑕疵案件
            init_axis_jcgbazlpm();

        }
    });

    //结果等次combobox初始化（部门办案质量排名）
    $('#cbt_situation_btfx_jgdc1').combotree({
        lines: true,
        multiple: false,
        cascadeCheck:false,
        editable: false,
        url: getRootPath()+'/filter/getPcjl',
        /*loadFilter: function (data) {
            /!*if(data == null){
                data = new Array();
            }*!/
            return data;
        },*/
        onLoadSuccess: function (data) {

            $('#cbt_situation_btfx_jgdc1').combotree('setValue', '瑕疵案件'); //结果等次默认选择瑕疵案件
            init_pie_bmbazlpm();

        }

    });

}

// 处理后台返回数据（质量问题分布图）
function formatPieData(data) {

    var categories = [];
    var datas = [];
    var total = [];
    var totalCount=0;

    for (var i = 0; i < data.length; i++) {
        categories.push(data[i].pcxmc || "");
        datas.push({name: data[i].pcxmc, value: data[i].count || 0});
        totalCount += data[i].count;
    }
    total.push(totalCount);
    return {category: categories, data: datas, total: total};
}
// 处理后台返回数据（检察官办案质量排名）
function formatAxisData(data) {

    var categories = [];
    var datas = [];

    for (var i = 0; i < data.data.length; i++) {
        categories.push(data.data[i].mc || "");
        datas.push({name: data.data[i].mc, value: data.data[i].count || 0, gh: data.data[i].gh});
    }
    return {category: categories, data: datas};
}
// 处理后台返回数据（部门办案质量排名）
function formatPieData_bmbazl(data) {

    var categories = [];
    var datas = [];

    for (var i = 0; i < data.data.length; i++) {
        categories.push(data.data[i].bmmc || "");
        datas.push({name: data.data[i].bmmc, value: data.data[i].count || 0, bmbm: data.data[i].bmbm});
    }
    return {category: categories, data: datas};
}
// 处理后台返回数据（地图-排名）
function formatPieData_map_rank_dt_ajzlndqut(data) {

    var categories = [];
    var datas = [];

    for (var i = 0; i < data.data.length; i++) {
        categories.push(data.data[i].dwmc || "");
        datas.push({name: data.data[i].dwmc, value: data.data[i].count || 0, bmbm: data.data[i].bmbm});
    }
    return {category: categories, data: datas};

}

// 点击分析按钮事件
$("#cbt_situation_btfx_fx").unbind( "click" );
$("#cbt_situation_btfx_fx").bind("click", function () {


    // 初始化质量问题分布饼图
    init_pie_zlwtfbt();

    // 初始化地图（map）
    init_map_map_dt_ajzlndqut();

    // tab切换时加载初始化地图-排名柱状图
    var tab = $('#tt').tabs('getSelected');
    var index = $('#tt').tabs('getTabIndex',tab);
    if (index == 1) { //地图-排名（柱状图）index
        init_map_rank_dt_ajzlndqut();
    }

    // 检察官办案质量排名柱状图
    init_axis_jcgbazlpm();

    // 部门办案质量排名斜圆环柱
    init_pie_bmbazlpm();

    //window.event.stopPropagation();

});

/********************************************************************************
 * 质量问题分布图
 * 饼图
 */
// 初始化质量问题分布饼图
function init_pie_zlwtfbt() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式
    // pie图数据请求参数obj传递
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;
    // objParams.pcflbm = pcflbm;


    //弹窗参数提供
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.startDate = startDate;
    obj.endDate = endDate;
    // obj.pcflbm = pcflbm;

    // 调用函数加载饼图数据（瑕疵案件）
    load_flaw_case(objParams);

    // 调用函数加载饼图数据（不合格案件）
    load_unqualified_case(objParams);

}

// 加载瑕疵案件数据
var myChart_zlwtfbt_flaw = echarts.init(document.getElementById('echarts_load_flaw_case'));
function load_flaw_case(obj) {

    //瑕疵案件数据请求data Object
    var requestObj = new Object();
    var pcjl = "瑕疵案件";
    requestObj = obj;
    requestObj.pcjl = pcjl;

    //饼图请求数据（瑕疵案件）
    $.ajax({
        type: 'get',
        url: getRootPath() + '/count/shYearProlbemNatureAnalyze',
        dataType: 'json',
        data: requestObj,
        success: function (data) {
            //处理后台返回数据
            var formatedPidData = formatPieData(data);

            obj.pcjl = "瑕疵案件";
            var titleTextXC = "瑕疵案件";


            echart_pie_flaw(formatedPidData, myChart_zlwtfbt_flaw,obj,titleTextXC);

        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });
}
// 饼图构建（瑕疵）
function echart_pie_flaw(data, myChartFlaw, paramObj, titleText) {

    option = {
        title: {
            text: titleText,
            x: 'left'
        },
        tooltip: {
            show: true,
            trigger: 'item',
            // formatter: "{a} <br/>{b} {c} ({d}%)"
            formatter: function (params, ticket, callback) {
                var num = data.total;
                var str = '<div style="text-align: center" id="toolTipBox"><p style="font-size: 12px;margin: 0px;color: #000000">总数</p><p style="font-size: 20px;margin: 0px">' +num+ '</p></div>';
                return str;
            },
            position: function (point, params, dom, rect, size) {
                var marginW = Math.ceil(size.contentSize[0]/2);
                var marginH = Math.ceil(size.contentSize[1]/2);
                dom.style.marginLeft = '-' + marginW + 'px';
                dom.style.marginTop = '-' + marginH + 'px';
                return ['50%', '50%'];
            },
            alwaysShowContent: true,
            backgroundColor: '#f3f5f6',
            textStyle:{
                color:'#333'
            }
        },
        //color:['#1E579D','#FFAD00','#EF8015','#66BF4B','#FF4900','#83437E','#0DB0D3','#F59A31'],
        color:['#ff6666','#ff9933','#99cc33','#66cccc','#0099cc','#9966cc','#ff99cc'],
        legend: {
            show: true,
            orient: 'vertical',
            left: 'right',
            data:data.category
        },
        series: [
            {
                name:'评查问题',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                        formatter: "{c}件 ({d}%)",

                        // position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: 20,
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data.data,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    /*itemStyle: {
        label:{
            show: true,
                formatter: '{b} : {c} ({d}%)'
        },
        labelLine:{show:true}
    }*/

    myChartFlaw.setOption(option);


}
//质量问题分布图（瑕疵案件）echarts图表点击跳转
myChart_zlwtfbt_flaw.on('click', function (params){

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式
    //弹窗参数提供
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.startDate = startDate;
    obj.endDate = endDate;
    obj.pcjl = "瑕疵案件";
    var name = params.name;
    obj.pcxmc = name;

    $("#win_situ_build_rd_dept_filter").window("setTitle",params.name);
    $('#win_situ_build_rd_dept_filter').window('open');

    /*var name = params.name;
    paramObj.pcxmc = name;
    paramObj.pcjl = "瑕疵案件";*/

    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath()+'/count/getAjjbxxYearProlbemNatureAnalyze',
        queryParams: obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]],
        loadFilter: function(data){
            if (data.code == 200) {
                return JSON.parse(data.data);
            } else {
                return [];
            }
        },
    });

    /*$('#win_situ_build_rd_dept_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/
    // return false;
});

// 加载不合格案件数据
var myChart_zlwtfbt_unqualified = echarts.init(document.getElementById('echarts_load_unqualified_case'));
function load_unqualified_case(obj) {

    //不合格案件数据请求data Object
    var requestObj = new Object();
    var pcjl = "不合格案件";
    requestObj = obj;
    requestObj.pcjl = pcjl;

    //饼图请求数据（不合格案件）
    $.ajax({
        type: 'get',
        url: getRootPath() + '/count/shYearProlbemNatureAnalyze',
        dataType: 'json',
        data: requestObj,
        success: function (data) {

            //处理后台返回数据
            var formatedPidData = formatPieData(data);

            obj.pcjl = "不合格案件";
            var titleTextBHG = "不合格案件";


            echart_pie_unqualified(formatedPidData, myChart_zlwtfbt_unqualified, obj, titleTextBHG);

        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });

}
// 饼图构建（不合格）
function echart_pie_unqualified(data, myChartUnqua, paramObj, titleText) {

    option = {
        title: {
            text: titleText,
            x: 'left'
        },
        tooltip: {
            trigger: 'item',
            // formatter: "{a} <br/>{b} {c} ({d}%)"
            formatter: function (params, ticket, callback) {
                var num = data.total;
                var str = '<div style="text-align: center" id="toolTipBox"><p style="font-size: 12px;margin: 0px;color: #000000">总数</p><p style="font-size: 20px;margin: 0px">' +num+ '</p></div>';
                return str;
            },
            position: function (point, params, dom, rect, size) {
                var marginW = Math.ceil(size.contentSize[0]/2);
                var marginH = Math.ceil(size.contentSize[1]/2);
                dom.style.marginLeft = '-' + marginW + 'px';
                dom.style.marginTop = '-' + marginH + 'px';
                return ['50%', '50%'];
            },
            alwaysShowContent: true,
            backgroundColor: '#f3f5f6',
            textStyle:{
                color:'#333'
            }
        },
        //color:['#F54DA4','#15CBBF','#0DB0D3','#F59A31','#66BF4B'],
        color:['#666699','#0099cc','#ffcc33','#F59A31','#cc0033'],
        legend: {
            show: true,
            orient: 'vertical',
            left: 'right',
            data:data.category
        },
        series: [
            {
                name:'评查问题',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                        //position: 'center'
                        formatter: "{c}件 ({d}%)"
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: 20,
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data.data,
                itemStyle: {
                    emphasis: {
                        /*shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)',*/

                    }
                }
            }
        ]
    };
    /*itemStyle: {
        label:{
            show: true,
                formatter: '{b} : {c} ({d}%)'
        },
        labelLine:{show:true}
    }*/

    myChartUnqua.setOption(option);



}
//质量问题分布图（不合格案件）echarts图表点击跳转
myChart_zlwtfbt_unqualified.on('click', function (params){

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式
    //弹窗参数提供
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.startDate = startDate;
    obj.endDate = endDate;
    obj.pcjl = "不合格案件";
    var name = params.name;
    obj.pcxmc = name;

    $("#win_situ_build_rd_dept_filter").window("setTitle",params.name);
    $('#win_situ_build_rd_dept_filter').window('open');

    /*var name = params.name;
    paramObj.pcxmc = name;
    paramObj.pcjl = "不合格案件";*/

    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath()+'/count/getAjjbxxYearProlbemNatureAnalyze',
        queryParams: obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]]
    });

    /*$('#win_situ_build_rd_dept_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/
    // return false;
});




/********************************************************************************
 * 地图
 * map
 */
// 初始化案件质量年度趋势图地图
function init_map_map_dt_ajzlndqut(){

    var obj = new Object();
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    obj.dwbm = dwbm;
    obj.startDate = startDate;
    obj.endDate = endDate;

    $.ajax({
        type: 'get',
        url: getRootPath() + "/count/shGetMap",
        dataType: 'json',
        data: obj,
        success: function (data) {

            if(data.code == 200){
                echarts_map_ajzlndqut(data.data);
            }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });

}
// 绘制案件质量年度趋势图地图分布图
function echarts_map_ajzlndqut(data){
    var series = [];
    var categories = [];
    var seriesCommon = {
        type: 'map',
        map: 'hubei',
        itemStyle: {
            normal: {
                label: {show: false
                },
            },
            emphasis: {label: {show: true}}
        },
        aspectScale: 1.0, //地图长宽比. default: 0.75
        zoom: 1.1, //控制地图的zoom
        roam: true,
    };

    for(var i = 0 ; i < data.length ; i++){
        var evSeries = new Object();
        evSeries.data = data[i].data;
        categories.push(data[i].name);
        evSeries.name = data[i].name;
        series.push($.extend({}, seriesCommon, evSeries));
    }

    $.get('Files/json/420000.json', function (hubeiJson) {

        echarts.registerMap('hubei', hubeiJson);
        var chart = echarts.init(document.getElementById('echarts_map_ajzlndqut'));

        var option = {
            legend: {
                orient: 'vertical',
                left: 'left',
                data:categories,
                textStyle:{
                    fontSize:15,
                }
            },
            title: {
                //text: '上海区院案件概况',
                left: 'center'
            },
            tooltip: {
                triggerOn:'click',
                enterable:true,
                hideDelay:1000,
                trigger: 'item',
                formatter: function (params) {
                    var str ='<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                        //评查树
                        + params.name
                        + '</div>'
                    var series = option.series;
                    for(var i = 0 ; i < series.length ; i ++){
                        var num = 0;
                        var dwbm = 0;

                        series[i].data.forEach(function (t) {
                            if(t.name == params.name){
                                num = t.value;
                                dwbm = t.dwbm;
                            }
                        });
                        str +='<a style="width: 130px;display: inline-block;"  onclick=commonClick("'+series[i].name+'","'+dwbm+'")>'+series[i].name+'</a>'+'<a  onclick=commonClick("'+series[i].name+'","'+dwbm+'")>' + num + '</a>件<br>'
                    }
                    return str
                },



            },
            visualMap: {
                min: 0,
                max: 100,
                text:['高','低'],
                realtime: false,
                calculable: true,
                inRange: {
                    color: ['lightskyblue','yellow', 'orangered']
                }
            },
            series:series,
            textStyle:{
                fontSize:12,
                color:"#1a32ff"
            }

        }

        chart.setOption(option);


    });
}
// 地图悬浮框点击事件
function commonClick(pcjl,dwbm) {

    var obj = new Object();
    obj.startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    obj.endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    obj.dwbm = dwbm;
    obj.pcjl = pcjl;


    $("#win_situ_build_rd_dept_filter").window("setTitle",pcjl);
    $('#win_situ_build_rd_dept_filter').window('open');

    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath() + "/count/getMapAjJbxx",
        queryParams: obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]],
        loadFilter: function(data){
            if (data.code == 200) {
                return JSON.parse(data.data);
            } else {
                return [];
            }
        },
    });


}


/********************************************************************************
 * 地图
 * 排名
 */
//初始化地图-排名
var myChart_map_rank = echarts.init(document.getElementById('echarts_axis_ajzlndqut'));
function init_map_rank_dt_ajzlndqut() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期

    // 地图-排名柱状图数据
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;

    //点击事件参数提供
    var clickObj = new Object();
    clickObj.dwbm = dwbm;
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;


    //请求地图-排名柱状图数据
    $.ajax({
        type: 'get',
        url: getRootPath() + '/count/getPm',
        dataType: 'json',
        data: objParams,
        success: function (data) {
            //处理后台返回数据
            var formatedAxisData = formatPieData_map_rank_dt_ajzlndqut(data);


            // 装载数据以及点击事件
            echarts_axis_ajzlndqut(formatedAxisData, myChart_map_rank, clickObj);

        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }

    });


}
/**
 * 地图-排名柱状图构建（案件质量年度趋势图）
 * @param requiredData（legend.data; yAxis.data; series.data）
 * @param myChart
 * @param clickObj 点击事件参数
 */
function echarts_axis_ajzlndqut(requiredData, myChart, clickObj) {

    option = {
        /*title: {
            text: '不合格案件和
            瑕疵案件排名',
        },*/
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            formatter: "{c} 件"
        },
        //color:['#48cda6','#fd87ab','#11abff','#ffdf33','#968ade','#de0710','#441cff'],
        legend: {
            data: requiredData.category //['浦东新区', '徐汇', '长宁', '普陀', '虹口', '黄埔', '崇明']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01],
            //formatter: '{value} 件'
            axisLabel: {
                formatter: '{value}件'
            }
        },
        yAxis: {
            type: 'category',
            data: requiredData.category //['浦东新区', '徐汇', '长宁', '普陀', '虹口', '黄埔', '崇明']
        },
        series: [
            {
                //name: '2011年',
                type: 'bar',
                data: requiredData.data//[7, 13, 23, 30, 49, 53, 68],//需要升序数据

            }
        ]
    };
    myChart.setOption(option);


}
//地图-排名echarts图表点击跳转
myChart_map_rank.on('click', function (params){

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    //点击事件参数提供
    var clickObj = new Object();
    clickObj.dwbm = dwbm;
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.bmbm = params.data.bmbm;

    $("#win_situ_build_rd_dept_filter").window("setTitle",params.name);
    $('#win_situ_build_rd_dept_filter').window('open');


    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath()+'/count/getPmAjJbxx',
        queryParams: clickObj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]],
        loadFilter: function(data){
            if (data.code == 200) {
                return JSON.parse(data.data);
            } else {
                return [];
            }
        },
    });

    /*$('#win_situ_build_rd_dept_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/
});



/********************************************************************************
 * 检察官办案质量排名
 * 柱状图
 */
var myChart_jcgbazlpm = echarts.init(document.getElementById('echarts_axis_jcgbazlpm'));
function init_axis_jcgbazlpm() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation_btfx_jgdc').combotree('getText');
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式


    // 检察官办案质量排名柱状图后台请求数据
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;
    objParams.pcjl = pcjl;
    // objParams.pcflbm = pcflbm;


    //点击事件参数提供
    var clickObj = new Object();
    clickObj.dwbm = dwbm;
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;
    // clickObj.pcflbm = pcflbm;

    //请求检察官办案质量排名柱状图数据
    $.ajax({
        type: 'get',
        url: getRootPath() + '/count/getPersonPaiMinByPcjlAndRQ',
        dataType: 'json',
        data: objParams,
        success: function (data) {

            //处理后台返回数据
            var formatedAxisData = formatAxisData(data);


            // 装载数据以及点击事件
            echarts_axis_jcgbazlpm(formatedAxisData, myChart_jcgbazlpm, clickObj);

        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });


};
/**
 * 检察官办案质量排名柱状图构建
 * @param requiredData (xAxis.data; series.data)
 * @param myChart
 * @param clickObj
 */
function echarts_axis_jcgbazlpm(requiredData, myChart, clickObj) {

    option = {
        tooltip: {
            trigger: 'axis',
            formatter: "{c} 件"
        },
        toolbox: {
            /*feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }*/
        },
        color:['#15A8D3'],
        legend: {
            //data:['办案数量']
        },
        xAxis: [
            {
                type: 'category',
                data: requiredData.category
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '（件）',
                min: 0,
                //max: 200,
                interval: 10,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name:'办案数量',
                type:'bar',
                data: requiredData.data //降序数据
            }
        ]
    };

    myChart.setOption(option);

}
//检察官办案质量排名echarts图表点击跳转
myChart_jcgbazlpm.on('click', function (params){
    $("#win_situ_build_rd_dept_filter").window("setTitle",params.name);
    $('#win_situ_build_rd_dept_filter').window('open');

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation_btfx_jgdc').combotree('getText');

    //点击事件参数提供
    var clickObj = new Object();
    clickObj.dwbm = dwbm;
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;
    var name = params.name;
    clickObj.pcxmc = name;
    clickObj.gh = params.data.gh;


    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath()+'/count/getPersonPaiMinByPcjlAndRQAjJbxx',
        queryParams: clickObj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]],
        loadFilter: function(data){
            if (data.code == 200) {
                return JSON.parse(data.data);
            } else {
                return [];
            }
        },

    });

    /*$('#win_situ_build_rd_dept_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/

});




/********************************************************************************
 * 部门办案质量排名
 * 斜圆环柱
 */
var myChart_bmbazlpm = echarts.init(document.getElementById('echarts_axis_bmbazlpm'));
function init_pie_bmbazlpm() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation_btfx_jgdc1').combotree('getText');


    // 部门办案质量排名饼图后台请求数据
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;
    objParams.pcjl = pcjl;

    //点击事件参数提供
    var clickObj = new Object();
    clickObj.dwbm = dwbm;
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;


    //请求部门办案质量排名饼图数据
    $.ajax({
        type: 'get',
        url: getRootPath() + '/count/bmBanAjZhiLiangPaiMin',
        dataType: 'json',
        data: objParams,
        success: function (data) {

            //处理后台返回数据
            var formatedPidData = formatPieData_bmbazl(data); // 装载ecahrt的数据

            // 装载数据以及点击事件
            echarts_axis_bmbazlpm(formatedPidData, myChart_bmbazlpm, clickObj);

        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });

}
/**
 * 部门办案质量排名饼图构建
 * @param requiredData (legend.data; series.data)
 * @param myChart
 * @param clickObj
 */
function echarts_axis_bmbazlpm(requiredData, myChart, clickObj) {

    option = {

        /*tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },*/
        color: ['#C1232B','#B5C334','#fcc80a','#E87C25','#27727B',
            '#FE8463','#9BCA63','#fab47f','#F3A43B','#60C0DD',
            '#D7504B','#C6E579','#beae01','#F0805A','#26C0C0'],
        legend: {
            orient: 'vertical',
            left: 'right',
            data: requiredData.category
        },
        series : [
            {

                type:'pie',
                radius : [30, 110],
                center: ['50%', '50%'],
                roseType : 'radius',

                data: requiredData.data,
                label: {
                    normal: {
                        show: true,
                        formatter: "{d}% ({c}件)"
                        // position: 'center'

                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '20',
                            fontWeight: 'bold'
                        }
                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);

}
//部门办案质量排名echarts图表点击跳转
myChart_bmbazlpm.on('click', function (params){

    $("#win_situ_build_rd_dept_filter").window("setTitle",params.name);
    $('#win_situ_build_rd_dept_filter').window('open');

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation_btfx_jgdc1').combotree('getText');

    var clickObj = new Object();
    clickObj.dwbm = dwbm;
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;
    var name = params.name;
    clickObj.pcxmc = name;
    clickObj.bmbm = params.data.bmbm;

    $('#table_situ_build_rd_dept_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageNumber:1,
        pageSize:20,
        method: "GET",
        url: getRootPath()+'/count/bmBanAjZhiLiangPaiMinAjJbxx',

        queryParams: clickObj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_situ_build_rd_dept_filter\')">查看</a>';
                }
            }
        ]],
        loadFilter: function(data){
            if (data.code == 200) {
                return JSON.parse(data.data);
            } else {
                return [];
            }
        },
    });


    /*$('#win_situ_build_rd_dept_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/
    // return false;
});


/********************************************************************************
 *
 * Excel 导出：
 *
 * 保持与后台约定好的格式：
 *filePath--文件路径
 *fileName -- 文件名
 *List<String>header--表格头
 *List<String>colHeader -- 第一列标题
 *List<List<String>>data--数据
 *
 *
 */

// 导出地图Excel数据：
function echarts_export_excel_map() {

    var obj = new Object();
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    obj.dwbm = dwbm;
    obj.startDate = startDate;
    obj.endDate = endDate;

    $.ajax({
        type: 'post',
        url: getRootPath() + "/count/exportMapExcel",
        dataType: 'json',
        data: obj,
        success: function (result) {
            if(result.code == 200){
                window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');
        },
        error: function (xhr) {
            Alert('导出地图Excel数据出错\n\n' + xhr.responseText);
        }
    });

}

// 导出案件质量年度趋势图排名Excel数据：
function echarts_export_excel_Pm() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期

    // 地图-排名柱状图数据
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;


    //请求地图-排名柱状图数据
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportPm',
        dataType: 'json',
        data: objParams,
        success: function (result) {
            if (result.code == 200){
                window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');

        },
        error: function (xhr) {
            Alert('导出案件质量年度趋势图排名出错\n\n' + xhr.responseText);
        }

    });

}

// 导出检察官办案质量排名Excel数据：
function echarts_export_excel_jcg() {
    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation_btfx_jgdc').combotree('getText');
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式


    // 检察官办案质量排名柱状图后台请求数据
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;
    objParams.pcjl = pcjl;

    //请求检察官办案质量排名柱状图数据
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportJcg',
        dataType: 'json',
        data: objParams,
        success: function (result) {
            if (result.code == 200){
                window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');

        },
        error: function (xhr) {
            Alert('导出检察官办案质量排名Excel出错\n\n' + xhr.responseText);
        }
    });


}

//质量问题分布图 --不合格案件Excel数据：
function echarts_export_excel_quantity() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式
    // pie图数据请求参数obj传递
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;


    //不合格案件数据请求data Object
    var requestObj = new Object();
    var pcjl = "不合格案件";
    requestObj = objParams;
    requestObj.pcjl = pcjl;

    //饼图请求数据（不合格案件）
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportQuantity',
        dataType: 'json',
        data: requestObj,
        success: function (result) {
            if (result.code == 200) {
                window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');

        },
        error: function (xhr) {
            Alert('导出质量问题分布图(不合格)出错\n\n' + xhr.responseText);
        }
    });


}

//质量问题分布图 --瑕疵案件Excel数据：
function echarts_export_excel_quantityFlaw() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    // var pcflbm = $('#cbt_situation_btfx_pcfs').combobox('getValue'); //评查方式
    // pie图数据请求参数obj传递
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;


    //瑕疵案件数据请求data Object
    var requestObj = new Object();
    var pcjl = "瑕疵案件";
    requestObj = objParams;
    requestObj.pcjl = pcjl;

    //饼图请求数据（瑕疵案件）
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportQuantityFlaw',
        dataType: 'json',
        data: requestObj,
        success: function (result) {
            if (result.code == 200) {
               window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');

        },
        error: function (xhr) {
            Alert('导出质量问题分布图(瑕疵)出错\n\n' + xhr.responseText);
        }
    });


}

// 导出部门办案质量Excel数据：
function echarts_export_excel_dept() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValue');//单位
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation_btfx_jgdc1').combotree('getText');


    // 部门办案质量排名饼图后台请求数据
    var objParams = new Object();
    objParams.dwbm = dwbm;
    objParams.startDate = startDate;
    objParams.endDate = endDate;
    objParams.pcjl = pcjl;


    //请求部门办案质量排名饼图数据
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportDept',
        dataType: 'json',
        data: objParams,
        success: function (result) {
            if (result.code == 200) {
               window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');

        },
        error: function (xhr) {
            Alert('导出部门办案质量Excel出错\n\n' + xhr.responseText);
        }
    });

}





