/**
 * Created by user on 2017/10/23.
 */
//饼图1

var myChart3 = echarts.init(document.getElementById('main3'));
var option3 = {
    title : {
        text: '评查问题',

    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'right',
        data:['直达','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
    },

    calculable : false,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : [50, 100],

            // for funnel
            x: '60%',
            width: '35%',
            funnelAlign: 'left',
            max: 1048,

            data:[
                {value:335, name:'直达'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1048, name:'百度'},
                {value:251, name:'谷歌'},
                {value:147, name:'必应'},
                {value:102, name:'其他'}
            ]
        }
    ]
};
myChart3.setOption(option3);

//饼图1

var myChart4 = echarts.init(document.getElementById('main4'));
var option4 = {
    title : {
        text: '评查问题',

    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'right',
        data:['直达','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
    },

    calculable : false,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : [50, 100],

            // for funnel
            x: '60%',
            width: '35%',
            funnelAlign: 'left',
            max: 1048,

            data:[
                {value:335, name:'直达'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1048, name:'百度'},
                {value:251, name:'谷歌'},
                {value:147, name:'必应'},
                {value:102, name:'其他'}
            ]
        }
    ]
};
myChart4.setOption(option4);





















//表格1

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
//表格1

$('#dgg').datagrid({
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