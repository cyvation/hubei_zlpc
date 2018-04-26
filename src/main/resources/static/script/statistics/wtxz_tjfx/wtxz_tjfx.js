/*creat by cjc on 2017/11/21*/

$(function () {
    //初始化加载下拉框
    init_combobox_data();
    //echarts图初始化
    $("#wtxz_barq").combobox("setValue","2016");
    $("#wtxz_pcfl").combobox("setValue","001");
    $("#wtxz_pcjg").combotree("setValue","一般司法错误");
    serch_btn();
});
//点击查询
function serch_btn() {
    //加载表格
    init_datagrid();
    get_datagrid();
    //加载饼图
    init_pie();
}
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
    $('#wtxz_barq').combobox({
        // idField: 'id',
        textField: 'value',
        data:dateJson
    });
    //评查方式
    $('#wtxz_pcfl').combobox({
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
    //评查结果
    // $('#wtxz_pcjg').combobox({
    //     textField: 'value',
    //     data: [{
    //         value: '司法瑕疵'
    //     }, {
    //         value: '无问题'
    //     }, {
    //         value: '严重司法错误'
    //     }, {
    //         value: '一般司法错误'
    //     }, {
    //         value: '优秀'
    //     }]
    // });
    // //评查结果
    $('#wtxz_pcjg').combotree({
        editable: false,
        url: getRootPath()+'/filter/getPcjl',
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1){

            }
        }
    });
}
//datagrid初始化
function init_datagrid(){
    $('#wtxz_table').datagrid({
        width:'auto',
        // fitColumns: true,
        loadMsg: '数据加载中，请稍后...',
        singleSelect:true,
        striped:true,
        columns : [ [
            {field : 'name',title : '评查类别', align: 'center', width : 150},
            {field : 'Jan',title : '一月',  width : 125},
            {field : 'Feb',title : '二月',  width : 125},
            {field : 'Mar',title : '三月',  width : 125},
            {field : 'Apr',title : '四月',  width : 125},
            {field : 'May',title : '五月',  width : 125},
            {field : 'Jun',title : '六月',  width : 125},
            {field : 'Jul',title : '七月',  width : 125},
            {field : 'Aug',title : '八月',  width : 125},
            {field : 'Sep',title : '九月',  width : 125},
            {field : 'Oct',title : '十月',  width : 125},
            {field : 'Nov',title : '十一月', width : 125},
            {field: 'Dec',title: '十二月',width : 125
                // formatter: function (value, row, index) {
                //     console.log(value);
                // }
            }] ],
        onSelect:function(rowIndex,rowData){
            // alert("我被点击了！");
        },
        onLoadSuccess:function(){

        }
    });
}
//datagrid获取后台数据
function get_datagrid() {
    //获取当前combobox中的值
    var barq = $('#wtxz_barq').combobox('getValue');
    var pcfl = $('#wtxz_pcfl').combobox('getValue');
    //获取后台数据
    $.ajax({
        type: 'POST',
        url:getRootPath() + '/count/getPcxFlByYearAndMbbmAndPcjg',
        dataType: 'json',
        data: {
            year: barq,
            pcflbm: pcfl
        },
        success: function (data) {
            if (data == "" || data == null || data == undefined) {
                // Alert("未查询到任何数据！");
                return;
            } else {
                var realData = FromateGridData(data);
                $('#wtxz_table').datagrid('loadData', realData)
            }
        },
        error: function (xhr) {
            Alert('获取表格数据出错！\n\n' + xhr.responseText);
        }
        // error: function(XMLHttpRequest, textStatus, errorThrown) {
        //     alert(XMLHttpRequest.status);
        //     alert(XMLHttpRequest.readyState);
        //     alert(textStatus);
        // }
    });
}
//绘制eharts饼图
function getPie(data) {
    var pieChart = echarts.init(document.getElementById('wtxz_pie'));
    option = {
        title: {
            text: data.title,
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox:{
            saveAsImage: {show: true}
        },
        legend: {
            orient: 'horizontal',
            left: 'left',
            bottom:'0',
            data: data.legend
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '40%'],
                data: data.datas,
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
    pieChart.setOption(option);
}
//echarts饼图请求数据
function init_pie() {
    var barq = $('#wtxz_barq').combobox('getValue');
    var pcfl = $('#wtxz_pcfl').combobox('getValue');
    var pcjg = $('#wtxz_pcjg').combobox('getText');
    // var data =[{"name":"一般司法错误","month":null,"result":[{"pcxmc":"其他","count":2},{"pcxmc":"证据收集调取不符合规定；","count":1},{"pcxmc":"其他违反证据规定的情形。","count":1},{"pcxmc":"案件主要事实认定错误，影响结论正确性；","count":1}]}]
    // getPie(FromateDatas(data));
    $.ajax({
        type: 'POST',
        url:getRootPath() +  '/count/getPcxFlByYearAndBinTu',
        dataType: 'json',
        data: {
            year: barq,
            pcflbm: pcfl,
            pcjg:pcjg
        },
        success: function (data) {

            if (data == "" || data == null || data == undefined) {
                // Alert("未查询到任何数据！");
                return;
            } else {
                var realData = eval(data);
                getPie(FromateDatas(data));
            }
        },
        error: function (xhr) {
            Alert('动态页出错\n\n' + xhr.responseText);
        }
        // error: function(XMLHttpRequest, textStatus, errorThrown) {
        //     alert(XMLHttpRequest.status);
        //     alert(XMLHttpRequest.readyState);
        //     alert(textStatus);
        // }
    });
}



//拼装所需要的数据格式
function FromateDatas(data){
    var seriers = [];
    var legend = [];
    var title = data[0].name;
    if(data[0].result == null || data[0].result == undefined || data[0  ].result == "" ){
        var nullData = {legend:[],datas:[{name:"",value:0}],title:title}
        return nullData;
    }else{
        for(var i = 0 ; i < data[0].result.length; i++){
            legend.push(data[0].result[i].pcxmc);
            seriers.push({value:data[0].result[i].count,name:data[0].result[i].pcxmc})
        }
        return {legend:legend,datas:seriers,title:title};
    }
}
//拼装所需要的表格JSON格式
function FromateGridData(data) {
    var sfyzcw = {
        name: "严重司法错误",
        Jan: "",
        Feb: "",
        Mar: "",
        Apr: "",
        May: "",
        Jun: "",
        Jul: "",
        Aug: "",
        Sep: "",
        Oct: "",
        Nov: "",
        Dec: ""
    };
    var ybsfcw = {
        name: "一般司法错误",
        Jan: "",
        Feb: "",
        Mar: "",
        Apr: "",
        May: "",
        Jun: "",
        Jul: "",
        Aug: "",
        Sep: "",
        Oct: "",
        Nov: "",
        Dec: ""
    };
    var sfxc = {
        name: "司法瑕疵",
        Jan: "",
        Feb: "",
        Mar: "",
        Apr: "",
        May: "",
        Jun: "",
        Jul: "",
        Aug: "",
        Sep: "",
        Oct: "",
        Nov: "",
        Dec: ""
    };
    var month = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    var datas = [];
    for (var i = 0; i < data.length; i++) {

        switch (data[i].name) {
            case "严重司法错误":
                var res = "";

                for (var j = 0; j < data[i].result.length; j++) {
                    res += "<ul class='td_text' title="+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")>"
                    res = res + "<li style='width: 120px;'>"+ (j+1) +"."+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")</li>";
                    res += "</ul>"
                }
                for (var m = 1; m <= 12; m++) {
                    if (m == data[i].month) {
                        var mon = month[m - 1];
                        sfyzcw[mon] = res;
                    }
                }
            case "一般司法错误":
                var res = "";
                for (var j = 0; j < data[i].result.length; j++) {
                    res += "<ul class='td_text' title="+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")>"
                    res = res + "<li style='width:120px;'>"+ (j+1) +"."+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")</li>";
                    res += "</ul>"
                    // res = res + "<div class='td_text' title="+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")>"+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")</div>\n";
                }
                for (var m = 1; m <= 12; m++) {
                    if (m == data[i].month) {
                        var mon = month[m - 1];
                        ybsfcw[mon] = res;
                    }
                }
            case "司法瑕疵":
                var res = "";
                for (var j = 0; j < data[i].result.length; j++) {
                    res += "<ul class='td_text' title="+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")>"
                    res = res + "<li style='width: 120px;'>"+ (j+1) +"."+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")</li>";
                    res += "</ul>"
                    // res = res + "<div  class='td_text' title="+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")>"+ data[i].result[j].pcxmc + "(" + data[i].result[j].count + ")</div><br>";
                }
                for (var m = 1; m <= 12; m++) {
                    if (m == data[i].month) {
                        var mon = month[m - 1];
                        sfxc[mon] = res;
                    }
                }
        }
    }
    datas.push(sfyzcw);
    datas.push(ybsfcw);
    datas.push(sfxc);
    return datas;
}


