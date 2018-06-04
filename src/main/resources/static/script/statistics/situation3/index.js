/*creat by cj on 2018/1/25*/
/**
 * 案件质量年度趋势图相关js
 *
 */
var excel_url =[];

 $(document).ready(function () {
     // 检察官办案质量排名柱状图
     init_axis_jcgbazlpm();

     // 单位办案质量排名柱状图
     init_axis_dwbazlpm();

     // 部门办案质量排名斜圆环柱
     init_pie_bmbazlpm();

});

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

var index; //当前tab的index
$(function () {

    //初始化加载下拉框
    init_data();


});


function openwin(url) {
    var a = document.createElement("a");
    a.setAttribute("href", url);
    a.setAttribute("target", "_blank");
    a.setAttribute("id", "openwin");
    document.body.appendChild(a);
    a.click();
}
// 处理comobtree多选参数
function handleCombotreeMultipleParam(dwbm) {
    var tempStr = '';
    for (temdwbm = 0; temdwbm < dwbm.length; temdwbm++) {
        tempStr += "'" + dwbm[temdwbm] + "'" + ",";
    }
    tempStr = tempStr.substring(0, tempStr.length - 1);
    tempStr = "(" + tempStr + ")";
    return tempStr;
}
// 导出检察官
$('#div_massAnalysis_export_jcg').unbind("click");
$('#div_massAnalysis_export_jcg').bind('click', function () {
    echarts_export_excel_jcg();
});
// 导出部门
$('#div_massAnalysis_export_bm').unbind("click");
$('#div_massAnalysis_export_bm').bind('click', function () {
    echarts_export_excel_dept();
});
// 导出单位
$('#div_massAnalysis_export_dw').unbind("click");
$('#div_massAnalysis_export_dw').bind('click', function () {
    echarts_export_excel_dw();
});




//查询条件初始化
function init_data() {

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
        multiple: true,
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
            var root = $('#cbt_situation_pcaj_dw').combotree('tree').tree('getRoot');
            var children=root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for(var i= 0;i<children.length;i++){
                valueArr.push(children[i].id);
            }
            $('#cbt_situation_pcaj_dw').combotree("setValues", valueArr);
            // 初始化质量问题分布饼图
            // init_pie_zlwtfbt();

            // 初始化地图（map）
            // init_map_map_dt_ajzlndqut();

        }
    });
    //开始日期datebox初始化
    $('#cbt_situation_btfx_start').datebox({
        editable: false,
        // value: new Date().getFullYear()-1 + '-' + 1 + '-' + 1
        value: '2013-01-01'
    });

    //结束日期datebox初始化
    $('#cbt_situation_btfx_end').datebox({
        editable: false,
        // value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
        value: '2017-12-31'
    });



    //结果等次combobox初始化（检察官办案质量排名）
    $('#cbt_situation3_btfx_jgdc_jcg').combotree({
        lines: true,
        multiple: false,
        cascadeCheck:false,
        editable: false,
        url: getRootPath()+'/filter/getPcjl',
        onLoadSuccess: function (data) {

            $('#cbt_situation3_btfx_jgdc_jcg').combotree('setValue', '合格案件'); //结果等次默认选择瑕疵案件
            init_axis_jcgbazlpm();
        },
        onClick: function(node){
            init_axis_jcgbazlpm();
        }
    });

    //结果等次combobox初始化（部门办案质量排名）
    $('#cbt_situation3_btfx_jgdc_bm').combotree({
        lines: true,
        multiple: false,
        cascadeCheck:false,
        editable: false,
        url: getRootPath()+'/filter/getPcjl',
        onLoadSuccess: function (data) {

            $('#cbt_situation3_btfx_jgdc_bm').combotree('setValue', '合格案件'); //结果等次默认选择合格案件
            init_pie_bmbazlpm();
        },
        onClick: function(node){
            init_pie_bmbazlpm();
        }

    });

    //结果等次combobox初始化（单位办案质量排名）
    $('#cbt_situation3_btfx_jgdc_dw').combotree({
        lines: true,
        multiple: false,
        cascadeCheck:false,
        editable: false,
        url: getRootPath()+'/filter/getPcjl',
        onLoadSuccess: function (data) {

            $('#cbt_situation3_btfx_jgdc_dw').combotree('setValue', '合格案件'); //结果等次默认选择不合格案件
            init_axis_dwbazlpm();

        },
        onClick: function(node){
            init_axis_dwbazlpm();
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
        datas.push({name: data.data[i].mc, value: data.data[i].count || 0, gh: data.data[i].gh,dwbm:data.data[i].dwbm});
    }
    return {category: categories, data: datas};
}

// 处理后台返回数据（单位办案质量排名）
function formatdwData(data) {

    var categories = [];
    var datas = [];

    for (var i = 0; i < data.data.length; i++) {
        categories.push(data.data[i].dwmc || "");
        datas.push({name: data.data[i].dwmc, value: data.data[i].count || 0, dwmc: data.data[i].dwmc,dwbm:data.data[i].dwbm});
    }
    return {category: categories, data: datas};
}

// 处理后台返回数据（部门办案质量排名）
function formatPieData_bmbazl(data) {

    var categories = [];
    var datas = [];

    for (var i = 0; i < data.data.length; i++) {
        categories.push(data.data[i].mc || "");
        datas.push({name: data.data[i].mc, value: data.data[i].cnt || 0, ywtxbm:data.data[i].ywtxbm});
    }
    return {category: categories, data: datas};
}


// 点击分析按钮事件
$("#cbt_situation_btfx_fx").unbind( "click" );
$("#cbt_situation_btfx_fx").bind("click", function () {


    // 检察官办案质量排名柱状图
    init_axis_jcgbazlpm();

    // 单位办案质量排名柱状图
    init_axis_dwbazlpm();

    // 部门办案质量排名斜圆环柱
    init_pie_bmbazlpm();

    //window.event.stopPropagation();

});


/********************************************************************************
 * 检察官办案质量排名
 * 柱状图
 */
var myChart_jcgbazlpm = echarts.init(document.getElementById('echarts_axis_jcgbazlpm'));
function init_axis_jcgbazlpm() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_jcg').combotree('getText');


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
            axisPointer: {
                type: 'shadow'
            },
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
           data: requiredData.category //data:['办案数量']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value',
            min: 0,
            interval: 'auto',
            // boundaryGap: [0, 0.01],
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

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_jcg').combotree('getText');

    //点击事件参数提供
    var clickObj = new Object();
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;
    var name = params.name;
    clickObj.pcxmc = name;
    clickObj.gh = params.data.gh;
    clickObj.dwbm = params.data.dwbm;


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

            {field:'ZHXGSJ',title:'评查结束日期', fixed:true, width: 115 ,
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
 * 单位办案质量排名
 * 柱状图
 */
var myChart_dwbazlpm = echarts.init(document.getElementById('echarts_axis_dwbazlpm'));
function init_axis_dwbazlpm() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_dw').combotree('getText');


    //单位办案质量排名柱状图后台请求数据
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

    //请求单位办案质量排名柱状图数据
    $.ajax({
        type: 'get',
        url: getRootPath() + '/count/getDwPaiMinByPcjlAndRQ',
        dataType: 'json',
        data: objParams,
        success: function (data) {

            //处理后台返回数据
            var formatedAxisData = formatdwData(data);


            // 装载数据以及点击事件
            echarts_axis_dwbazlpm(formatedAxisData, myChart_dwbazlpm, clickObj);

        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });


};
/**
 * 单位办案质量排名柱状图构建
 * @param requiredData (xAxis.data; series.data)
 * @param myChart
 * @param clickObj
 */
function echarts_axis_dwbazlpm(requiredData, myChart, clickObj) {

    option = {
        tooltip: {
            trigger: 'axis',
            formatter: "{c} 件"
        },
        toolbox: {

        },
        color:['#DC143C'],
        legend: {
            //data:['办案数量']
        },
        xAxis: [
            {
                type: 'category',
                data: requiredData.category,
                axisLabel:{
                    interval:0,
                    rotate:-30
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '（件）',
                min: 0,
                //interval: 'auto',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name:'案件数量',
                type:'bar',
                data: requiredData.data //降序数据
            }
        ]
    };

    myChart.setOption(option);

}
//单位办案质量排名echarts图表点击跳转
myChart_dwbazlpm.on('click', function (params){
    $("#win_situ_build_rd_dept_filter").window("setTitle",params.name);
    $('#win_situ_build_rd_dept_filter').window('open');

    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_dw').combotree('getText');

    //点击事件参数提供
    var clickObj = new Object();
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;
    var name = params.name;
    clickObj.dwbm = params.data.dwbm;


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
        url: getRootPath()+'/count/getDwPaiMinByPcjlAndRQAjJbxx',
        queryParams: clickObj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'ZHXGSJ',title:'评查结束日期', fixed:true, width: 115 ,
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
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_bm').combotree('getText');


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
        // url: getRootPath() + '/count/bmBanAjZhiLiangPaiMin',
        url: getRootPath() + '/count/ywtxAjZhiLiangPaiMin',
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
                radius : '70%',
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

    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_bm').combotree('getText');

    var clickObj = new Object();
    clickObj.startDate = startDate;
    clickObj.endDate = endDate;
    clickObj.pcjl = pcjl;
    clickObj.dwbm = dwbm;
    clickObj.ywtxbm = params.data.ywtxbm;

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
        // url: getRootPath()+'/count/bmBanAjZhiLiangPaiMinAjJbxx',
        url: getRootPath()+'/count/ywtxAjZhiLiangPaiMinAjJbxx',
        queryParams: clickObj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLBMC', title: '案件类别名称', width: 90},
            {field: 'PCRMC', title: '评查员', width: 90},
            {field: 'BPCDWMC', title: '承办单位', width: 90},
            {field: 'BPCMC', title: '承办检察官', width: 90},

            {field:'ZHXGSJ',title:'评查结束日期', fixed:true, width: 115 ,
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

// 导出检察官办案质量排名Excel数据：
function echarts_export_excel_jcg() {
    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_jcg').combotree('getText');
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

// 导出单位办案质量排名Excel数据：
function echarts_export_excel_dw() {
    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_dw').combotree('getText');
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
        url: getRootPath() + '/count/exporDw',
        dataType: 'json',
        data: objParams,
        success: function (result) {
            if (result.code == 200){
                window.location.href =getRootPath()+ result.data;
            }
            $('#transmit').window('close');

        },
        error: function (xhr) {
            Alert('导出单位办案质量排名Excel出错\n\n' + xhr.responseText);
        }
    });


}

// 导出部门办案质量Excel数据：
function echarts_export_excel_dept() {

    // 获取当前筛选条件值
    var dwbm = $('#cbt_situation_pcaj_dw').combotree('getValue')==undefined?userInfo.DWBM:$('#cbt_situation_pcaj_dw').combotree('getValues');//单位
    dwbm = handleCombotreeMultipleParam(dwbm);
    var startDate = $('#cbt_situation_btfx_start').datebox('getValue'); //开始日期
    var endDate = $('#cbt_situation_btfx_end').datebox('getValue'); //结束日期
    var pcjl = $('#cbt_situation3_btfx_jgdc_bm').combotree('getText');


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





