//@ sourceURL=LineChart.js
var _chartid = getParameter("chartid");
var provinceid = getParameter("provinceid");
var categories = new Array();
var colors = new Array();
var series;
var config;
var details;
var type;
//var timeList;
$(document).ready(function () {

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
            
            var items = config[0].items;
            
            $.ajax({
                type: "post", async: true, 
                data: {provinceid: provinceid, type: "line", items:items, startdate: getParameter("startdate"), enddate: getParameter("enddate") },
                url: "/Handler/Platform/PlatformHandler.ashx?action=ChartData" + location.search.replace("?","&"),
                success: function (result) {
                    result = eval('(' + result + ')');
                    categories = result.items;
                    //series = eval('(' + result.data + ')');

                    series = eval('(' + result.data + ')');
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
    var json =
    {
        title: {
            text: '',
            floating: true
        },
        chart: {
            type: config[0].chart_type,
            //backgroundColor: 'transparent'
            backgroundColor: config[0].chart_bgcolor,
        },
        credits: {
            enabled: false
        },
        legend: {
            enabled: true,
            itemStyle: {
                color: '#0389bc'
            }
        },
        tooltip: {
            useHTML: true
        },
        xAxis: {
            tickWidth: config[0].xaxis_tickwidth,
            lineColor: config[0].xaxis_linecolor,
            categories: categories,
            labels: {
                style: {
                    color: '#00e1e1'
                }
            }
        },
        yAxis: {
            gridLineColor: '#002146',
            title: {
                text: ''
            },
            labels: {
                style: {
                    color: '#0389bc'
                }
            }
        },
        //colors: colorJson,
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    allowOverlap: true,
                    color: '#0ff'
                },
                events: {
                    click: function (e) {
                        var exportExcel = 0;
                        var name = e.point.name;
                        var type = "";
                        var handlerurl = "";
                        var opentype = 0;
                        for (i = 0; i < details.length; i++) {
                            if (e.point.name == details[i].itemname) {
                                type = details[i].itemtype;
                                exportExcel = details[i].exportexcel;
                                handlerurl = details[i].detaileurl;
                                opentype = details[i].opentype;
                                break;
                            }
                        }
                        onClickAJXX(handlerurl, config[0].chartname + " -> " + name, opentype);

                    }
                }
            },

            bar: {
                dataLabels: {
                    enabled: true,
                    // ,allowOverlap 默认是 false，即不允许数据标签重叠
                }
            }
        },
        //series: eval(series),
        series: series,
        exporting: {
            url: '/Handler/common.ashx?action=UpdateBmp',
            filename: config[0].chartname,
            width: myWidth
        }
    };
    if (type == "0") {
        json.colors = colors;
    }
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
