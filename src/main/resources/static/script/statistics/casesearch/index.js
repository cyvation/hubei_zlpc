/*creat by cjc on 2017/11/19*/

$(function () {
    //初始化加载下拉框
    init_combobox_data();
    //echarts图初始化
    $("#pcaj_barq").combobox("setValue","2016");
    init_line();
});
//查询条件初始化
function init_combobox_data() {
    //查询日期
    var dateJson = [];
    var date=new Date;
    var currYear=date.getFullYear();
    for(var i = 2016;i <= currYear;i++){
        dateJson.push({
           value:i
        });
    }
    $('#pcaj_barq').combobox({
        // idField: 'id',
        textField: 'value',
        data:dateJson
    });
    //评查方式
    $('#pcaj_pcfs').combobox({
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
        }]
    });
}

//echars折线图获取后台数据
function init_line() {
    //获取当前combobox中的值
    var barq = $('#pcaj_barq').combobox('getValue');
    var pcfs = $('#pcaj_pcfs').combobox('getValue');
    //获取后台数据
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/count/getAjzlLinesByYearAndPcflbm',
        dataType: 'json',
        data: {
            year: barq,
            pcflbm: pcfs
        },
        success: function (data) {

            var realData=[{name:'0',value:'0'}];
            if (data == "" || data == null || data == undefined) {
                // Alert("未查询到任何数据！");
                ECharts.RenderChart(ECharts.ChartOptionTemplates.Lines(realData,"年度办案质量趋势图",ECharts.ChartTooltipTemplates.PercentTooltip),"echart1");
                return;
            } else {
                realData=eval(data);
                ECharts.RenderChart(ECharts.ChartOptionTemplates.Lines(realData,"年度办案质量趋势图",ECharts.ChartTooltipTemplates.PercentTooltip),"echart1");
            }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
    });
    //饼图请求数据
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/count/getAjzlPieByYearAndPcflbm',
        dataType: 'json',
        data: {
            year: barq,
            pcflbm: pcfs
        },
        success: function (data) {
            var realData=[{name:'0',value:'0'}];
            if (data == "" || data == null || data == undefined) {
                // Alert("未查询到任何数据！");
                ECharts.RenderChart(ECharts.ChartOptionTemplates.Pie(realData,"年度办案质量图"), "echart2");
                return;
            } else {
                var realData = eval(data);
                ECharts.RenderChart(ECharts.ChartOptionTemplates.Pie(realData,"年度办案质量图"), "echart2");
            }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }

    });
}

