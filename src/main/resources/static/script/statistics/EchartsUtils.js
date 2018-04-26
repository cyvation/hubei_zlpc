 var ECharts = {
    ChartTooltipTemplates:{
        PercentTooltip:{
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params)//数据格式
            {
                var relVal = params[0].name + "<br/>";
                for (var i = 0; i < params.length; i++) {
                    if (params[i].seriesName != null && params[i].seriesName != undefined) {
                        relVal += params[i].seriesName + ' : ' + params[i].value + "%" + "<br/>";
                    }
                }
                return relVal;
            },
        }
    },

    ChartDataFormate: {
        /*
        * 通用简单图 数据处理 data=[{name:XXX,value:XXX},{name:XXX,value:XXX}….]
        * */
        FormateNOGroupData: function (data) {
            var categories = [];

            var datas = [];

            for (var i = 0; i < data.length; i++) {

                categories.push(data[i].name || "");

                datas.push({name: data[i].name, value: data[i].value || 0});

            }

            return {category: categories, data: datas};

        },
        /*
        * 通用分组图 数据处理   data=[{name:XXX,group:XXX,value:XXX},{name:XXX,group:XXX,value:XXX]
        * type bar line
       * */
        FormateWithGroupData: function (data, type) {
            //type 默认值折线形
            var chart_type = 'line';

            if (type)
                chart_type = type || 'line';
            //x坐标轴
            var xAxis = [];
            //组别
            var group = [];
            //图线数据
            var series = [];

            for (var i = 0; i < data.length; i++) {

                for (var j = 0; j < xAxis.length && xAxis[j] != data[i].name; j++) ;

                    if (j == xAxis.length)

                        xAxis.push(data[i].name);

              /*  for(var j = 0 ; j < month.length ; j++){
                    xAxis.push(month[j])
                }*/

                for (var k = 0; k < group.length && group[k] != data[i].group; k++) ;

                if (k == group.length)

                    group.push(data[i].group);

            }

            for (var i = 0; i < group.length; i++) {

                var temp = [];

                for (var j = 0; j < data.length; j++) {

                    if (group[i] == data[j].group) {

                        for(var n = temp.length  ; n < xAxis.length ; n++){

                           if(data[j].name == xAxis[n]){
                               temp.push(data[j].value);
                               break;
                           }else{
                               temp.push(0)
                           }
                        }

                    }
                }
             while (temp.length < xAxis.length){
                    temp.push(0);
             }
                switch (type) {

                    case 'bar':

                        var series_temp = {name: group[i], data: temp, type: chart_type};

                    case 'line':

                        var series_temp = {name: group[i], data: temp, type: chart_type};

                    default:

                        var series_temp = {name: group[i], data: temp, type: chart_type};

                }

                //一数组形式存数据
                series.push(series_temp);

            }

            return {category: group, xAxis: xAxis, series: series};

        },
    },
    ChartOptionTemplates: {
        //通用的图表基本配置
        CommonOption: {//通用的图表基本配置

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            toolbox: {

                show: true, //是否显示工具栏

                feature: {

                    mark: true,

                    restore: true, //复原

                    saveAsImage: true, //是否保存图片

                    dataView: false,
                }

            }
        },

        CommonLineOption: {//通用的折线图表的基本配置

            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
                },
            },
            grid: {
                left: '2%',
                right: '2%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {

                show: true,

                feature: {

                    mark: true,


                    restore: {show: true}, //复原

                    saveAsImage: true, //是否保存图片

                    magicType: {show: true, type: ['line', 'bar']},

                    dataView: false,

                    }

                }
        },

        Pie: function (data, name,param) {//data:数据格式：{name：xxx,value:xxx}...

            var pie_datas = ECharts.ChartDataFormate.FormateNOGroupData(data);

            var option = {

                title: {
                    text: name,
                    x: 'center'
                },

                legend: {
                    orient: 'horizontal',
                    left: 'left',
                    bottom: '0',
                    data: pie_datas.category

                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                series: [
                    {
                        name:'占比',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '45%'],
                        data: pie_datas.data,
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
            if(param != null && param != "" && param != undefined){
                ECharts.ChartOptionTemplates.CommonOption.tooltip = param;
            }
            return $.extend({}, ECharts.ChartOptionTemplates.CommonOption, option);
        },

        Lines: function (data, name,param) { //data:数据格式：{name：xxx,group:xxx,value:xxx}...

            var stackline_datas = ECharts.ChartDataFormate.FormateWithGroupData(data, 'line');

            var option = {

                title: {
                    text: name,
                },

                legend: {

                    data: stackline_datas.category

                },

                xAxis: [{

                    type: 'category', //X轴均为category，Y轴均为value

                    data: stackline_datas.xAxis,

                    boundaryGap: false//数值轴两端的空白策略

                }],

                yAxis: {
                    type: 'value',
                    axisLabel: {
                        show: true,
                        interval: 'auto',
                        formatter: '{value} %'
                    },
                    show: true
                },
                series: stackline_datas.series

            };


            if(param != null && param != "" && param != undefined){
                ECharts.ChartOptionTemplates.CommonLineOption.tooltip=param;
            }
            return $.extend({},ECharts.ChartOptionTemplates.CommonLineOption, option);

        },

    },
/*
* 图表展现
* */
    RenderChart: function (option, container) {
    var chart = echarts.init(document.getElementById(container));
    chart.setOption(option);
}
}