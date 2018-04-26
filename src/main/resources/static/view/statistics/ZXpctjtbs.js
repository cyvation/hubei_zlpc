//柱状图

var myChart3 = echarts.init(document.getElementById('main3'));
var option3 = {

    tooltip : {
        trigger: 'axis'
    },
    legend: {
        x:'left',
        data:['审结','评查']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'审结',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'评查',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            markPoint : {
                data : [
                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        },
    ]
};
myChart3.setOption(option3);





//表格

$('#dg').datagrid({
    url:'../datagrid_data1.json',
    rownumbers:true,
    singleSelect:true,
    autoRowHeight:false,
    /* pagination:true,
     pageSize:20,*/
    columns:[[
        {field:'itemid',title:'Item ID',width:'10%'},
        {field:'productid',title:'Product ID',width:'10%'},
        {field:'listprice',title:'List Price',width:'10%'},
        {field:'unitcost',title:'Unit Cost',width:'10%'},
        {field:'attr1',title:'Attribute',width:'10%'},
        {field:'status',title:'Status',width:'10%'},
        {field:'itemid',title:'Status',width:'10%'},
        {field:'itemid',title:'Status',width:'10%'},
        {field:'itemid',title:'Status',width:'10%'},
    ]],
    onHeaderContextMenu: function(e, field){
        e.preventDefault();
        if (!cmenu){
            createColumnMenu();
        }
        cmenu.menu('show', {
            left:e.pageX,
            top:e.pageY
        });
    }
});