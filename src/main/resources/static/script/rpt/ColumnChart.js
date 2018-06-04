//@ sourceURL=ColumnChart.js
var _chartid = getParameter("chartid");
var provinceid = getParameter("provinceid");
var categories = new Array();
var colors = new Array();
var items_id = new Array();
var series;
var config;
var details;
var type;
$(document).ready(function () {
//    var a="[['1','2','3','4','5'],['a','b','c','d','e'],['y1','y2','y3','y4','y5']] ";
//    var str = eval(a);
//    alert(str[0][3]);

    if (_chartid=="")
        document.write("编号为空，不能生成图表！");
    provinceid = provinceid == "" ? "100000" : provinceid;
    $.ajax({
        type: "post",
        async: true,
        data: { chartid: _chartid, provinceid:provinceid },
        url: "/Handler/Platform/PlatformHandler.ashx?action=ChartConfig",
        success: function (result) {
           
            var data = '';
            try {
                data = eval("(" + result + ")");
            } catch (e) {
                document.write(result);
                return;
            } 

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
                type: "post",
                async: true,
                data: {provinceid:provinceid, items: items },
                url: "/Handler/Platform/PlatformHandler.ashx?action=ChartData" + location.search.replace("?","&"),
                success: function (result) {
                    result = eval('(' + result + ')');
                    type = result.type;
                    series = eval('(' + result.data + ')');
                    //categories = ['武汉市院','黄石市院','十堰市院','宜昌市院','襄阳市院','鄂州市院','荆门市院','孝感市院','荆州市院','黄冈市院','咸宁市院','随州市院','恩施土家族苗族自治州院','汉江分院','神农架林区院','湖北省院武汉铁路运输分院'];
                    if (result.items != null) {
                        if (result.items.item != null) {
                            categories = result.items.item;
                            items_id = result.items.id;
                        }
                    }
                    else if (series.length > 0 && series[0]["id"] != null) {
                        for (var i = 0; i < series.length; i++) {
                            //categories.push(series[i]["name"]);
                            items_id.push(series[i]["id"]);
                        }
                    }

                    //var data1 = eval(result);
                    //series = "[";
                    //for (var i = 0; i < data1.length; i++) {
                    //    //, id:[" + data1[i][0] + "]
                    //    series += "{name:'" + data1[i][0] + "', data :[" + data1[i][1] + "]},";
                    //}
                    //series = series.length > 1 ? series.substring(0, series.length - 1) + "]" : "[]";
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
    var name, value;
    var str = location.href;
    var num = str.indexOf("?");
    str = str.substr(num + 1);
    var arr = str.split("&");

    for (var i = 0; i < arr.length; i++) {
        num = arr[i].indexOf("=");
        if (num > 0) {
            name = arr[i].substring(0, num);
            value = arr[i].substr(num + 1);
            if (name == param) {
                return value;
            }
        }
    }
    return '';
}

function InitChart() {
    var seriesJson = type == "0" ? [
        {
            colorByPoint: true,
            name: config[0].chart_tip,
            data: series
        }
    ] : series;
    var legendjson = type == "0" ? {
        align: config[0].legend_align,
        //          verticalAlign: 'top',
        y: config[0].legend_y,
        x: config[0].legend_x,
        itemStyle: {
            color: '#0389bc'
        }
    } : {
        enabled: true,
        itemStyle: {
            color: '#0389bc'
        }
    };
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
        legend: legendjson,
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
                    click: function(e) {
                        var exportExcel = 0;
                        var itemType = "";
                        var handlerurl = "";
                        var opentype = 0;
                        if (type == "0") {
                            var name = e.point.name;
                            for (i = 0; i < details.length; i++) {
                                if (e.point.name == details[i].itemname) {
                                    itemType = details[i].itemtype;
                                    exportExcel = details[i].exportexcel;
                                    handlerurl = details[i].detaileurl;
                                    opentype = details[i].opentype;
                                    break;
                                }
                            }
                            onClickAJXX(handlerurl, config[0].chartname + " -> " + name, opentype);
                        } else if (type == "1") {
                            // 一项多柱情况
                            //var name = e.point.category;  
                            var name = e.point.series.name;  // item名称
                            /*
                            for (i = 0; i < details.length; i++) {
                                if (name == details[i].itemname || category == details[i].itemname) {
                                    itemType = details[i].itemtype;
                                    exportExcel = details[i].exportexcel;
                                    handlerurl = details[i].detaileurl;
                                    opentype = details[i].opentype;
                                    break;
                                }
                            }
                            */
                            var index = details.length > e.point.series.index ? e.point.series.index : e.point.index;
                            exportExcel = details[index].exportexcel;
                            handlerurl = details[index].detaileurl;
                            opentype = details[index].opentype;
                            var itemid = items_id.length > e.point.index ? items_id[e.point.index] : "";

                            if (handlerurl != "") {
                                var url = '&itemid='+ itemid +'&itemname=' + name + location.search.replace('?','&') + "&exportExcel=" + exportExcel;
                                onClickAJXX(handlerurl + url, config[0].chartname + " -> " + name, opentype);
                                //onClickAJXX(handlerurl , config[0].chartname + " -> " + name, opentype);
                            }
                            
                        }
                    }
                },
            },

            bar: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    // ,allowOverlap 默认是 false，即不允许数据标签重叠
                },
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
                        var url = location.search.substr(15, location.search.length) + "&exportExcel=" + exportExcel + "&itemType=" + itemType + "&zoom=1";
                        onClickAJXX(handlerurl + url, config[0].chartname + " -> " + name, opentype);
                    }
                },
            }
        },
        //series: eval(series),
        series: seriesJson,
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
    //alert([handlerUrl, title,opentype]);
    if (handlerUrl == "") {
        return;
    }
    if (opentype == 0) {
        //本页显示
        window.location.href = handlerUrl;
    } else if (opentype == 1) {
        //winform弹窗显示
        frameObject.OpenDialogWeb(3, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '', true);
    } else if (opentype == 2) {
        frameObject.OpenDialogWeb(0, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '');
    } else if (opentype == 3) {
        //本级弹窗显示
        parent.ShowThisDialog(handlerUrl + "&opentype=" + opentype, title);
    } else {
        //默认winform弹窗显示
        frameObject.OpenDialogWeb(3, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '', true);
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

function tansDwbm(dwbm, sjtype) {
    var rel = dwbm;
    if (sjtype == "1") {
        rel = "=" + dwbm;
    } else {
        if (dwbm.substr(4, 2) != "00") {
            rel =  dwbm ;
        } else if(dwbm.substr(2, 2) != "00") {
            rel =  dwbm.substr(0, 4) + "%";
        } else {
            rel =  dwbm.substr(0, 2) + "%";
        }
    }
    return rel;
}
