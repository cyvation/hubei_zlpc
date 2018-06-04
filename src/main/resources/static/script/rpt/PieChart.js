//@ sourceURL=piechart.js
var _chartid = getParameter("chartid");
var provinceid = getParameter("provinceid");
var categories = new Array();
var colors = new Array();
var series;
var drilldown;
var config;
var details;
var type;
$(document).ready(function () {
    //    var a="[['1','2','3','4','5'],['a','b','c','d','e'],['y1','y2','y3','y4','y5']] ";
    //    var str = eval(a);
    //    alert(str[0][3]);

    if (_chartid == "")
        document.write("编号为空，不能生成图表！");
    provinceid = provinceid == "" ? "100000" : provinceid;
    $.ajax({
        type: "post",
        async: true,
        data: { chartid: _chartid, provinceid:provinceid },
        url: "/Handler/Platform/PlatformHandler.ashx?action=ChartConfig",
        success: function (result) {
            var data = eval("(" + result + ")");

            details = eval("(" + data.details + ")");

            for (i = 0; i < details.length; i++) {
                categories.push(details[i].itemname);
                colors.push(details[i].itemcolor);
            }

            //series = "[['受理',3],['已办',2],['在办',3],['归档',4]]";
            // 数据
            var ChartConfig = data.chartConfig;
            config = eval('(' + ChartConfig + ')');
            
            $.ajax({
                type: "post", async: true, data: { provinceid: provinceid, type: "pie"  },
                url: "/Handler/Platform/PlatformHandler.ashx?action=ChartData"+ location.search.replace("?","&"),
                success: function (result) {
                    result = eval('(' + result + ')');
                    drilldown = eval(result.children);
                    result = eval('(' + result.data + ')');
                    type = result.type;
                    series = result.data;

                    InitChart();
                }
            });


        },
        error: function (data) {
            console.log("Error:" + data.responseText);
        }
    });


    //var colors = ['#7cb5ec', '#90ed7d', '#f45b5b', 'gray'];
    //var categories = ['受理', '已办', '在办', '归档'];

});

// param 为 参数的名称
function getParameter(param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
}

function InitChart() {
    var json = {
        title: {
            text: '',
            floating: true
        },
        chart: {
            type: config[0].chart_type,
            //backgroundColor: 'transparent'
            backgroundColor: config[0].chart_bgcolor,
            //plotBorderWidth: null,
            //plotShadow: false
        },
        credits: {
            enabled: false
        },
        legend: {
            //align: config[0].legend_align,
            ////          verticalAlign: 'top',
            //y: config[0].legend_y
            itemStyle: {
                color: '#0389bc'
            }
        },
        tooltip: {
            useHTML: true,
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.y} <br>占比 {point.percentage:.1f} %</b>'
        },
        //xAxis: {
        //    tickWidth: config[0].xaxis_tickwidth,
        //    lineColor: config[0].xaxis_linecolor,
        //    categories: categories,
        //    labels: {
        //        style: {
        //            //color: '#00e1e1'
        //        }
        //    }
        //},
        //yAxis: {
        //    //gridLineColor: '#ffffff',
        //    title: {
        //        text: ''
        //    },
        //    labels: {
        //        style: {
        //            //color: '#0389bc'
        //        }
        //    }
        //},
        //options3d: {
        //    enabled: true,     //显示图表是否设置为3D， 我们将其设置为 true
        //    alpha: 15,         //图表视图旋转角度
        //    beta: 15,          //图表视图旋转角度
        //    depth: 50,         //图表的合计深度，默认为100
        //    viewDistance: 25   //定义图表的浏览长度
        //},
        //colors: colors,
        colors: ['#f48c05', '#00abeb', '#19c92f', '#8d0af1', '#1ce1e3', '#0072ff', '#e6004c', '#e9bd23', '#00abeb', '#ff6000', '#113fe8'],
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                },
                showInLegend: true,
                events: {
                    click: function(e) {
                        var exportExcel = 0;
                        var itemType = "";
                        var handlerurl = "";
                        var opentype = 0;
                        var name = e.point.category;
                        for (i = 0; i < details.length; i++) {
                            if (name == details[i].itemname) {
                                itemType = details[i].itemtype;
                                exportExcel = details[i].exportexcel;
                                handlerurl = details[i].detaileurl;
                                opentype = details[i].opentype;
                                break;
                            }
                        }
                        //var url = location.search.substr(15, location.search.length) + "&exportExcel=" + exportExcel;
                        //onClickAJXX(handlerurl + url, config[0].chartname + " -> " + name, opentype);
                    }
                },
            }
        },
        series: [
            {
                size: '90%',
                innerSize: '30%',
                colorByPoint: true,
                name: config[0].chart_tip,
                data: series
            }
        ],
        drilldown: {
            series: drilldown
        },
        exporting: {
            url: '/Handler/common.ashx?action=UpdateBmp',
            filename: config[0].chartname,
            width: myWidth
        }
    }
    //if (drilldown != null) {
    //    json.drilldown = { series: drilldown };
    //}
    $('#chartid').highcharts(json);
}

function onClickAJXX(handlerUrl, title, opentype) {
    //alert([handlerUrl, title]);
    if (handlerUrl == "") {
        return;
    }
    if (opentype == 0) {
        window.location.href = handlerUrl;
    }
    else {
        window.open(handlerUrl);
    }

}

function tansParamFun(value) {
    var rel = value;
    try {
        rel = eval(value);
        if (value.substr(0, 1) == "'" && value.substr(value.length - 1, 1) == "'") {
            rel = "'" + rel + "'";
        }
    } catch (e) {

    }
    return rel;
}