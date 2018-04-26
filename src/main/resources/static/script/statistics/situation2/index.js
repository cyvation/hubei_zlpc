$(document).ready(function () {
    //初始化加载下拉框
    init_statistics_situation2_EasyUiCom();

    $("#cb_situation_year").combobox("setValue", "2018");
    init_eval_handle_transmit_Excel();
});

//初始化easyUI控件
function init_statistics_situation2_EasyUiCom() {
//初始化时间
    $('#cbt_situation_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + 1 + '-' + 1
    });
    $('#cbt_situation_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });

    //查询日期
    var dateJson = [];
    var date = new Date;
    var currYear = date.getFullYear();
    for (var i = currYear - 2 ; i <= currYear; i++) {
        dateJson.push({
            value: i
        });
    }
    //年度下拉框
    $('#cb_situation_year').combobox({
        // idField: 'id',
        textField: 'value',
        data: dateJson,
        onSelect: function (node) {
            btn_year_quality_line_search($('#cbt_situation_dw').combotree('getValue'));
        }

    });

    //评查单位树
    $('#cbt_situation_dw').combotree({
        method: 'get',
        editable: false,
        lines: true,
        panelWidth:270,
        // multiple: true,
        // cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_situation_dw').combotree('setValue', data[0].id); //单位默认选择
                btn_situation_search();
            }
            index_addMousedownDiv(this,'cbt_situation_dw');
        }
    });


}

//获取查询条件
function btn_situation_search() {
    var obj = new Object();
    obj.dwbm = $('#cbt_situation_dw').combotree('getValues').join(",");
    obj.startDate = $('#cbt_situation_start').datebox('getValue');
    obj.endDate = $('#cbt_situation_end').datebox('getValue');
    btn_year_quality_line_search($('#cbt_situation_dw').combotree('getValue'));
    btn_situation_map_search(obj)
    btn_year_quality_scatter(obj);
}


/**
 * 曲线图
 */
//获取数据调用年度质量图绘图
function btn_year_quality_line_search(dwbm) {
    var obj = new Object();
    //获取当前combobox中的值
    var year = $('#cb_situation_year').combobox('getValue');
    obj.wcrqStart = year + "-1-1";
    obj.wcrqEnd = year + "-12-30";
    obj.dwbm = dwbm;
    $.ajax({
        type: 'get',
        url: getRootPath() + "/count/shGetYearProblemAnalyze",
        dataType: 'json',
        data: obj,
        success: function (data) {

            render_year_quality_echart(data);
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });
}

//绘制echart年度办案质量曲线图


function render_year_quality_echart(data) {
    var categories = [];
    var series = [];
    if (data != null && data != undefined) {
        data.forEach(function (t) {
            categories.push(t.pcjl);
            var obj = new Object();
            obj.type = 'line';
            obj.name = t.pcjl;
            obj.data = t.data;
            obj.smooth = true;
            series.push(obj);
        });
    }
    var quality_bar = echarts.init(document.getElementById('echart_year_quality_bar'));
    quality_bar.on("click", function (param) {
        $("#win_statistics_situatuion").window("setTitle", param.name)
        $('#win_statistics_situatuion').window('open');
        var query = new Object();

        //获取当前combobox中的值
        query.dwbm = $('#cbt_situation_dw').combobox('getValue');
        query.year = $('#cb_situation_year').combobox('getValue');
        query.pcjl = param.seriesName;
        var months = ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];

        for (var i = 0; i < months.length; i++) {

            if (months[i] == param.name) {
                query.month = i + 1;
                break;
            }
        }
        $("#table_win_statistics_situatuion").datagrid({
            method: 'get',
            border: 0,
            fit: true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath() + '/count/shGetYearProblemAnalyzeJbxx',
            queryParams: query,
            pagination: true,
            pageNumber: 1,
            pageSize: 20,
            columns: [[
                {field: 'BMSAH', title: '部门受案号', width: 160},
                {
                    field: 'AJMC', title: '案件名称', width: 160,
                    formatter: function (value) {
                        return tipMessage(value);
                    }
                },
                {field: 'PCRMC', title: '评查员', width: 90},
                {field: 'AJLBMC', title: '案件类别名称', width: 90},
                {field: 'BPCDWMC', title: '承办单位', width: 90},
                {field: 'BPCMC', title: '承办检察官', width: 90},
                {
                    field: 'CJSJ', title: '评查日期', fixed: true, width: 115,
                    formatter: function (value) {
                        return sjzh(value);
                    }
                },
                {
                    field: 'action', title: '操作', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                        var r = '<a href="#" onclick="pcWinPage(' + index + ',\'#table_win_statistics_situatuion\')">查看</a>'
                        return r;
                    }
                }


            ]],
            loadFilter: function (result) {
                return result.code == 200 ? JSON.parse(result.data) : [];
            }
        });

    })
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'        // 默认为直线，可选为：'line' | 'shadow'
            },
            //transitionDuration:1000,
            // enterable:true,
            hideDelay: 500,
            formatter: function (params) {
                var str = '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                    //评查树
                    + '月份:' + params[0].axisValue
                    + '</div>'
                for (var i = 0; i < params.length; i++) {
                    str += '<a style="width: 80px;display: inline-block;">' + params[i].seriesName + '</a>' + '<a>' + params[i].value + '</a>件<br>'
                }
                return str
            },
        },
        legend: {
            data: categories,
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            //  boundaryGap: false,//数值轴两端的空白策略
            type: 'category',
            data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        },

        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value} 件'
            },
            show: true
        },


        series: series,
    };
    quality_bar.setOption(option);
    //echart图标点击事件

}


/**
 * 概况地图
 */
//获取数据调用评查概况地图分布图
function btn_situation_map_search(obj) {
    $.ajax({
        type: 'get',
        url: getRootPath() + "/count/shGetMapAjZlqk",
        dataType: 'json',
        data: obj,
        success: function (data) {
            if (data.code == 200) {
                render_situation_map_echart(data.data);
            }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });
}

//绘制echart评查概况地图分布图
function render_situation_map_echart(data) {
    var series = [];
    var categories = [];
    var seriesCommon = {
        type: 'map',
        map: 'hubei',
        itemStyle: {
            normal: {
                label: {
                    show: false
                },
            },
            emphasis: {label: {show: true}}
        },
        aspectScale: 1.0, //地图长宽比. default: 0.75
        zoom: 1.1, //控制地图的zoom，动手自己更改下 看看什么效果吧
        roam: true,
    };

    for (var i = 0; i < data.length; i++) {
        var evSeries = new Object();
        evSeries.data = data[i].data;
        categories.push(data[i].name)
        evSeries.name = data[i].name;
        series.push($.extend({}, seriesCommon, evSeries));
    }

    $.get('Files/json/420000.json', function (hubeiJson) {

        echarts.registerMap('hubei', hubeiJson);
        var chart = echarts.init(document.getElementById('echart_situation_map'));

        var option = {
            legend: {
                orient: 'vertical',
                left: 'left',
                data: categories,
                textStyle:{
                    fontSize:15,
                }
            },
            tooltip: {
                triggerOn: 'click',
                enterable: true,
                hideDelay: 1000,
                trigger: 'item',
                formatter: function (params) {
                    var str = '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                        //评查树
                        + params.name
                        + '</div>'
                    var series = option.series;
                    var totalypc = 0;
                    var totalwpc = 0;
                    for (var i = 0; i < series.length; i++) {
                        var num = 0;
                        var dwbm = 0;
                        var pcflbm = 0;
                        series[i].data.forEach(function (t) {
                            if (t.name == params.name) {
                                num = t.value;
                                dwbm = t.dwbm;
                                pcflbm = t.pcflbm;
                                totalypc = t.ypc + totalypc;
                                totalwpc = t.wpc + totalwpc;
                            }
                        });
                        str += '<a style="width: 130px;display: inline-block;" onclick=commonClick("situation","' + pcflbm + '","' + dwbm + '")>' + series[i].name + '</a>' + '<a onclick=commonClick("situation","' + pcflbm + '","' + dwbm + '")>' + num + '</a>件<br>';
                    }
                    if (totalwpc + totalypc == 0) {
                        totalwpc = 1;
                    }
                    var pcl = parseFloat((totalypc / (totalwpc + totalypc)) * 100).toFixed(2) + '%';

                    str += '<a style="width: 130px;display: inline-block;" >' + "评查率" + '</a>' + '<a>' + pcl + '</a><br>'
                    return str
                },


            },
            visualMap: {
                min: 0,
                max: 1000,
                text: ['高', '低'],
                realtime: false,
                calculable: true,
                inRange: {
                    color: ['lightskyblue', 'yellow', 'orangered']
                }
            },
            series: series,
            textStyle: {
                fontSize: 12,
                color: "#1a32ff"
            }

        }

        chart.setOption(option);

    });
}

/**
 * 散点图
 */
//获取数据调用年度质量散点分布图
function btn_year_quality_scatter(obj) {
    $.ajax({
        type: 'get',
        url: getRootPath() + "/count/getPcjbGk",
        dataType: 'json',
        data: obj,
        success: function (data) {
            if(data.code == 200){
                for(var i = 0 ; i < data.data.data.length ; i++){
                    data.data.data[i].pcl =  (data.data.data[i].pcl*100).toFixed(2);
                }
                render_quality_scatter_echart(data.data);
           }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });
}

//绘制echart年度办案质量散点图
function render_quality_scatter_echart(data) {

    var realData = [];
    for (var i = 0; i < data.data.length; i++) {
        var array = [data.data[i].sj, data.data[i].pc, data.data[i].pcl, data.data[i].dwmc, data.data[i].dwbm];
        realData.push(array);
    }
    var x =(data.sjMax / 10) * 10;
    var y = (data.pcMax / 10) * 10;
    if(x*0.1>y){
        x=y*10;
    };
    //评查率 10% 的线
    var markLineOpt = {
        animation: false,
        label: {
            normal: {
                width: 1,
                formatter: '10%',
                textStyle: {
                    align: 'right'
                }
            }
        },
        lineStyle: {
            normal: {
                type: 'dashed',
                color: "#1b3330"
            }
        },
        tooltip: {
            formatter: 'y = 0.1 * x'
        },
        data: [[{
            coord: [0, 0],
            symbol: 'none'
        }, {
            coord: [x, x * 0.1],
            symbol: 'none'
        }]]
    };

    var mychart = echarts.init(document.getElementById('echart_quality_scatter'));

    var schema = [
        {name: 'sj', index: 0, text: '审结数'},
        {name: 'pc', index: 1, text: '评查数'},
        {name: 'pcl', index: 2, text: '评查率'},
        {name: 'dwmc', index: 3, text: '单位'},
        {name: 'dwbm', index: 4, text: '单位编码'},
    ];

    var itemStyle = {
        normal: {
            opacity: 0.8,
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowOffsetY: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
    };

    var option = {
        legend: {
            y: 'top',
            textStyle: {
                color: '#fff',
                fontSize: 16
            }
        },
        grid: {
            x: '7%',
            x2: 150,
            y: '18%',
            y2: '10%'
        },
        tooltip: {
            enterable: true,
            padding: 10,
            borderWidth: 1,
            //transitionDuration:1000,
            triggerOn: 'click',
            hideDelay: 1000,
            formatter: function (obj) {
                var value = obj.value;
                return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                    //评查树
                    + ' ' + value[3]
                    + '</div>'
                    + '<a style="width: 80px;display: inline-block;" onclick=commonClick("scatter","' + schema[1].text + '","' + value[4] + '") >' + schema[1].text + '</a>' + '<a onclick=commonClick("scatter","' + schema[1].text + '","' + value[4] + '")>' + value[1] + '件</a><br>'
                    + '<a style="width: 80px;display: inline-block;" onclick=commonClick("scatter","' + schema[0].text + '","' + value[4] + '") >' + schema[0].text + '</a>' + '<a onclick=commonClick("scatter","' + schema[1].text + '","' + value[4] + '")>' + value[0] + '件</a><br>'
                    + '<a style="width: 80px;display: inline-block;" >' + schema[2].text + '：</a>' + '<a>' + value[2] + "%" + '</a><br>'
            }
        },
        xAxis: {
            type: 'value',
            name: '审结数',
            nameGap: 16,
            nameTextStyle: {
                // color: '#fff',
                fontSize: 14
            },
            boundaryGap: false,
            splitLine: {
                show: false
            },
        },
        yAxis: {
            type: 'value',
            name: '评查数',
            nameLocation: 'end',
            nameTextStyle: {
                fontSize: 16
            },
        },
        visualMap: [
            {
                left: 'right',
                top: '10%',
                dimension: 1,
                min: 50,
                max: 200,
                itemWidth: 15,
                itemHeight: 60,
                calculable: true,
                precision: 0.1,
                text: ['圆形大小：评查数'],
                textGap: 30,
                inRange: {
                    symbolSize: [10, 70]
                },
            },
            {
                left: 'right',
                bottom: '5%',
                dimension: 2,
                min: 0,
                max: 10,
                itemWidth: 15,
                itemHeight: 60,
                calculable: true,
                precision: 0.01,
                text: ['颜色：评查率'],
                textGap: 30,
                inRange: {
                    color: ['#FF0E07', '#FFC103', '#03FF4E']
                },
            }
        ],
        series: [
            {
                name: '上海质量',
                type: 'scatter',
                itemStyle: itemStyle,
                data: realData,
                markLine: markLineOpt,
            },
        ]
    };
    mychart.setOption(option);

}




function commonClick(type, param, value) {
    var obj = new Object();
    obj.startDate = $('#cbt_situation_start').datebox('getValue');
    obj.endDate = $('#cbt_situation_end').datebox('getValue');
    if (type == 'scatter') {
        obj.dwbm = value;
        obj.wczt = param == "评查数" ? "pcs" : "sjs";
        $("#table_win_statistics_situatuion").datagrid({
            method: 'get',
            border: 0,
            fit: true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath() + '/count/shPcjbqkerGetPcslbmPcjlIsNotNullJbxx',
            queryParams: obj,
            pagination: true,
            pageNumber: 1,
            pageSize: 20,
            columns: [[
                {field: 'BMSAH', title: '部门受案号', width: 160},
                {
                    field: 'AJMC', title: '案件名称', width: 160,
                    formatter: function (value) {
                        return tipMessage(value);
                    }
                },
                {field: 'PCRMC', title: '评查员', width: 90},
                {field: 'AJLBMC', title: '案件类别名称', width: 90},
                {field: 'BPCDWMC', title: '承办单位', width: 90},
                {field: 'BPCMC', title: '承办检察官', width: 90},
                {
                    field: 'CJSJ', title: '评查日期', fixed: true, width: 115,
                    formatter: function (value) {
                        return sjzh(value);
                    }
                },
                {
                    field: 'action', title: '操作', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                        var r = '<a href="#" onclick="pcWinPage(' + index + ',\'#table_win_statistics_situatuion\')">查看</a>'
                        return r;
                    }
                }


            ]],
            loadFilter: function (result) {
                return result.code == 200 ? JSON.parse(result.data) : [];
            }
        });
        $('#win_statistics_situatuion').window('open');
    }
    if (type == 'situation') {
        obj.dwbm = value;
        obj.pcflbm = param;

        $("#table_win_statistics_situatuion").datagrid({
            method: 'get',
            border: 0,
            fit: true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath() + '/count/getMapAjJbxxJbqk',
            queryParams: obj,
            pagination: true,
            pageNumber: 1,
            pageSize: 20,
            columns: [[
                {field: 'BMSAH', title: '部门受案号', width: 160},
                {
                    field: 'AJMC', title: '案件名称', width: 160,
                    formatter: function (value) {
                        return tipMessage(value);
                    }
                },
                {field: 'PCRMC', title: '评查员', width: 90},
                {field: 'AJLBMC', title: '案件类别名称', width: 90},
                {field: 'BPCDWMC', title: '承办单位', width: 90},
                {field: 'BPCMC', title: '承办检察官', width: 90},
                {
                    field: 'CJSJ', title: '评查日期', fixed: true, width: 115,
                    formatter: function (value) {
                        return sjzh(value);
                    }
                },
                {
                    field: 'action', title: '操作', width: 80, align: 'center',
                    formatter: function (value, row, index) {
                        var r = '<a href="#" onclick="pcWinPage(' + index + ',\'#table_win_statistics_situatuion\')">查看</a>'
                        return r;
                    }
                }

            ]],
            loadFilter: function (result) {
                return result.code == 200 ? JSON.parse(result.data) : [];
            }
        });
        $('#win_statistics_situatuion').window('open');
    }
}

function init_eval_handle_transmit_Excel() {

    $("#cbt_situation_transmit").unbind('click');
    $("#cbt_situation_transmit").bind("click", function () {
        $('#transmit2').window('open');
    });

    $("#export_excel_btn2").unbind('click');
    $("#export_excel_btn2").bind('click', function () {

        // 获取要导出的数据类型
        var allSelected = $("#transmit2 :checked");
        if (allSelected.length == 0) {
            Alert("请勾选要导出的数据");
            return;
        }

        for (var i = 0; i < allSelected.length; i++) {
            switch ($(allSelected[i]).val()) {
                case '1': //地图
                    echarts_export_situation_excel_map();
                    break;
                case '2': // 评查工作情况
                    echarts_export_situation_excel_condition();
                    break;
                case '3': // 评查成效
                    echarts_export_situation_excel_effect();
                    break;
            }
        }


    });

}


// 评查基本情况地图导出Excel:
function echarts_export_situation_excel_map() {

    var obj = new Object();
    obj.dwbm = $('#cbt_situation_dw').combotree('getValue');
    obj.startDate = $('#cbt_situation_start').datebox('getValue');
    obj.endDate = $('#cbt_situation_end').datebox('getValue');

    $.ajax({
        type: 'post',
        url: getRootPath() + "/count/exportSituation",
        dataType: 'json',
        data: obj,
        success: function (result) {
            if (result.code == 200) {
                window.location.href = getRootPath() + result.data;
            }
        },
        error: function (xhr) {
            Alert('导出评查基本情况地图出错\n\n' + xhr.responseText);
        }
    });


}

// 工作情况
function echarts_export_situation_excel_condition() {

    var obj = new Object();
    obj.dwbm = $('#cbt_situation_dw').combotree('getValue');
    obj.startDate = $('#cbt_situation_start').datebox('getValue');
    obj.endDate = $('#cbt_situation_end').datebox('getValue');

    $.ajax({
        type: 'post',
        url: getRootPath() + "/count/exportSituationScatter",
        dataType: 'json',
        data: obj,
        success: function (result) {
            if (result.code == 200) {
                window.location.href = getRootPath() + result.data;
            }
        },
        error: function (xhr) {
            Alert('导出评查基本情况地图出错\n\n' + xhr.responseText);
        }
    });


}

// 评查成效
function echarts_export_situation_excel_effect() {

    var obj = new Object();
    //获取当前combobox中的值
    var year = $('#cb_situation_year').combobox('getValue');
    obj.wcrqStart = year + "-1-1";
    obj.wcrqEnd = year + "-12-30";
    obj.dwbm = $('#cbt_situation_dw').combotree('getValue');
    $.ajax({
        type: 'POST',
        url: getRootPath() + "/count/exportQualityLine",
        dataType: 'json',
        data: obj,
        success: function (result) {
            if (result.code == 200) {
                window.location.href = getRootPath() + result.data;
            }else{
                Alert('数据为空\n\n' + '请确保图中有数据');
            }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });

}
