var column=[
    {field: 'id',title: '<span  style="font-size:14px">id</span>',hidden:true },
    {field: 'pid',title: '<span  style="font-size:14px">pid</span>',hidden:true},
    {field: 'bjrNum',title: '<span  style="font-size:14px">1办案人数</span>',width: 70,align: 'center'/*,
     formatter: function (value, row, index) {
     var r= '<a href="#" onclick="pcWin_generalList(' + index +',0)">'+value+'</a>';
     return r;
     }*/
    },
    {field: 'bjajNum',title: '<span  style=\'font-size:14px\'>2办结案件数</span>',width: 80, align: 'center'/*,
     formatter: function (value, row, index) {
     var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="pcWin_generalList(this,' + index +',0)">'+value+'</a>';
     return r;
     }*/
    },
    {field: 'pcrNum',title: '<span  style=\'font-size:14px\'>3评查人员数</span>',width: 70,align: 'center'/*,
     formatter: function (value, row, index) {
     var r= '<a href="#" onclick="pcWin_generalList(' + index +',1)">'+value+'</a>';
     return r;
     }*/
    },
    {field: 'pcajNum',title: '<span  style=\'font-size:14px\'>4评查案件数</span>',width: 80,align: 'center',
     formatter: function (value, row, index) {
         var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="pcWin_generalList(this,' + index +',1)">'+value+'</a>';
     return r;
     }
    },
    {field: 'pcbl',title: '<span  style=\'font-size:14px\'>评查比例（4/2）</span>',width: 100,align: 'center'},
    {field: 'bpcAvgNum',title: '<span  style=\'font-size:14px\'>承办人平均被评查案件数（4/1）件</span>',width: 180,align: 'center'},
    {field: 'avgNum',title: '<span  style=\'font-size:14px\'>评查员平均评查案件数（4/3）件</span>',width: 180,align: 'center'}
];
var object= ['时间分析','地区分析','条线分析'];
var tabIndex=0;

$(document).ready(function () {
    init_general_overview();
    init_general_tab();
    init_tabIndex_tab();
    $("#pcWin_win_offline").hide();
});
function init_tabIndex_tab(){
    if(tabIndex==0)
        init_general_data("table_general_date");
    else if(tabIndex==1)
        init_general_data("table_general_dq");
    else if(tabIndex==2)
        init_general_data("table_general_tx");
}
function init_general_tab() {
    try {
        for (var i = 0; i < object.length; i++) {
            if (i != getSelectTabIndex()) {
                tabIndex = getSelectTabIndex();
                $("#tabs_general_overview").tabs('close', object[i]);
            }
        }
    }catch(err) {
    }
    //查询：
    $("#btn_general_data_search").unbind('click');
    $("#btn_general_data_search").bind('click',function () {
        if(tabIndex==0) {
           // init_general_data("table_general_date");
            idName="table_general_date";
            data_general_data("table_general_date");
        }else if(tabIndex==1) {
           // init_general_data("table_general_dq");
            idName="table_general_dq";
            data_general_data("table_general_dq");
        }else if(tabIndex==2) {
           // init_general_data("table_general_tx");
            idName="table_general_tx";
            data_general_data("table_general_tx");
        }
    });
    // tab标签切换
    $("#tabs_general_overview").tabs({
        onSelect:function(title,index){
            if (index ==0) { // 加载时间分析数据
                tabIndex=0;
                init_general_data("table_general_date");
            }
            if (index == 1) { //加载地区分析数据
                tabIndex=1;
                init_general_data("table_general_dq");
            }
            if (index == 2) { //加载条线分析数据
                tabIndex=2;
                init_general_data("table_general_tx");
            }
        }
    });
}
function init_general_overview() {
    //评查单位树
    $('#general_dw_combotree').combotree({
        method: 'get',
        editable: false,
        panelWidth: 250,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                dt = data[0].id;
                $('#general_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
            }
            var root = $('#general_dw_combotree').combotree('tree').tree('getRoot');
            var children=root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for(var i= 0;i<children.length;i++){
                valueArr.push(children[i].id);
            }
            $('#general_dw_combotree').combotree("setValues", valueArr);
            index_addMousedownDiv(this,"general_dw_combotree");
        }
    });
//年份
//     $('#general_date').combotree({
//         editable: false,
//         panelWidth: 160,
//         lines: true,
//         multiple: true,
//         cascadeCheck: false,
//         onShowPanel: index_onShowPanel,
//         onHidePanel: index_onHidePanel,
//         onLoadSuccess: function (node, data) {
//             if (data != null && data.length >= 1) {
//                 setAllCheckbox('#general_date', data);
//             }
//             index_addMousedownDiv(this, "general_date");
//         }
//     });
//     $('#general_date').combotree("loadData", getYearRange());

    $('#general_wcdate_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#general_wcdate_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

// 评查日期
    $('#general_pcdate_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#general_pcdate_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });


    //承办人身份
    $('#general_cbrsf').combotree({
        editable: false,
        panelWidth: 220,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#general_cbrsf').combotree('setValues', ['N','Y']);
            }
            index_addMousedownDiv(this, "general_cbrsf");
        }
    });
    $('#general_cbrsf').combotree("loadData", getCbrsfValues());
    //评查方式
    $('#general_pcfl').combotree({
        method: 'get',
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var valueArr = new Array();
            if (data != null && data.length >= 1){
                for(var i= 0;i<data.length;i++){
                    valueArr.push(data[i].id);
                }
                $('#general_pcfl').combotree("setValues", valueArr);
            }
            index_addMousedownDiv(this, "general_pcfl");
        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }
        }
    });
    $('#general_pcmb').combotree('loadData', []);
    $('#general_pcmb').combotree('clear');
    $('#general_pcmb').combotree('setValue', '');

    $('#general_pcmb').combotree({
        editable: false,
        method: 'get',
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + "/analysis/getPcdmByFdm",
        onLoadSuccess: function (node, data) {
            // 默认选中
            var valueArr = new Array();
            if (data != null && data.length >= 1) {
                for(var i= 0;i<data.length;i++){
                    valueArr.push(data[i].id);
                }
                $('#general_pcmb').combotree("setValues", valueArr);
            }
            index_addMousedownDiv(this, "general_pcmb");
        },
    });
    $('#stajbs').combotree({
        editable: false,
        panelWidth: 220,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#stajbs').combotree('setValues', ['0','1']);
            }
            index_addMousedownDiv(this, "stajbs");
        }
    });
    $('#stajbs').combotree("loadData", getStajbs());
    var resizeTabHeight = $("#general_tabs_box").height() - 62;
    $("#general_tabs_box").find(".panel-body").height(resizeTabHeight);
}

function data_general_data(id) {
    var obj=new Object();
    var dwbm = $('#general_dw_combotree').combotree('getValues').length==0?"":$('#general_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    // if($('#general_date').combotree('getValues').length==0){
    //     Alert("请选择时间");
    //     return;
    // }
    obj.startDate = $('#general_wcdate_start').datebox('getValue');
    obj.endDate = $('#general_wcdate_end').datebox('getValue');
    obj.pcstartDate=$('#general_pcdate_start').datebox('getValue');
    obj.pcendDate = $('#general_pcdate_end').datebox('getValue');
   // obj.wcrqnf= $('#general_date').combotree('getValues').length==0?"":$('#general_date').combotree("getValues").join(",");
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    var url="";
    if(tabIndex==0) {
        url=getRootPath() + '/analysis/loadDateGeneral';
    }else if(tabIndex==1){
        url=getRootPath() + '/analysis/loadDqGeneral';
    }else if(tabIndex==2){
        url=getRootPath() + '/analysis/loadTxGeneral';
    }
    if(tabIndex!=0){
        $('#'+id).treegrid({
            url: url,
            queryParams:{json : JSON.stringify(obj)},
            loadFilter:function (data) {
                return data.code ==200 ?JSON.parse( data.data):[];
            }
        });
    }else {
        $('#' + id).datagrid({
            url: url,
            queryParams: {json: JSON.stringify(obj)},
            loadFilter: function (data) {
                return data.code == 200 ? JSON.parse(data.data) : [];
            }
        });
    }
}
function init_general_data(id) {
    var option=[];
    var url="";
    if(tabIndex==0) {
        option.push({field: 'name', title: '<span  style="font-size:14px">年份</span>', width: 100, align: 'center'});
    }else if(tabIndex==1){
        option.push({field: 'name', title: '<span  style="font-size:14px">地区</span>', width: 300, align: 'left'});
    }else if(tabIndex==2){
        option.push({field: 'name', title: '<span  style="font-size:14px">条线</span>', width: 300, align: 'left'});
    }
    for(var i=0;i<column.length;i++){
        option.push(column[i]);
    }
    var columns=[];
    columns.push(option);
    if(tabIndex!=0){
        $('#'+id).treegrid({
            fitColumns: true,
            striped: true,
            nowrap: false,
            rownumbers: true,
            treeField: 'name',
            animate: true,
            idField: 'id',
            method: 'post',
            columns:columns,
            toolbar:"#tool"
        });
    }else {
        $('#' + id).datagrid({
            striped: true,
            loadMsg: '数据加载中，请稍后...',
            fitColumns: true,
            rownumbers: true,
            singleSelect: true,
            async: false,
            method: 'post',
            columns: columns,
            toolbar:"#tool"
        });
    }
}

function pcWin_generalList(el,index,type){
    var obj=new Object();
    var dwbm = $('#general_dw_combotree').combotree('getValues').length==0?"":$('#general_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    obj.startDate = $('#general_wcdate_start').datebox('getValue');
    obj.endDate = $('#general_wcdate_end').datebox('getValue');
    obj.pcstartDate=$('#general_pcdate_start').datebox('getValue');
    obj.pcendDate = $('#general_pcdate_end').datebox('getValue');

   // obj.wcrqnf= $('#general_date').combotree('getValues').length==0?"":$('#general_date').combotree("getValues").join(",");
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    obj.pcxId="";
    if(tabIndex==0){
        var thisRow = $('#'+idName).datagrid('getRows')[index];
        obj.wcrqnf=thisRow.id;
    }else if(tabIndex==1){
        var currField = $(el).attr("data-field");
        var jsonField = JSON.parse(currField);
        obj.dwbm=jsonField.id;
    }else{
        var currField = $(el).attr("data-field");
        var jsonField = JSON.parse(currField);
        obj.pcxId=jsonField.id;
        obj.ywtx=jsonField.pid;
    }
    obj.tabIndex=tabIndex;
    obj.type=type;
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    $("#table_eval_Ajlb").datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        pageList: [10, 20, 30, 50, 100],
        url: getRootPath() + "/analysis/getPclbAjJbxx",
        queryParams:{json:JSON.stringify(obj)},
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLB_MC',title:'案件类别',width:90},
            {field:'CBDWMC',title:'承办单位',width:90},
            {field:'BCBRMC',title:'承办检察官',width:90},
            {field:'WCRQ',title:'完成日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return '<a href="#" onclick="pcWin_pcCountWin(' + index +')">查看</a>';
                }
            }
        ]]
        , loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        }
    });
    $("#win_eval_build_table").window('setTitle',type==0?'办结案件列表':'评查案件列表');
    $("#win_eval_build_table").window('open');
}
// 办理win弹框加载
// 跳转到评查办理界面
// type类别 0：评查信息  1：案件信息
/*function pcWinPages(index,id,type) {
    var rowDatas = $(id).datagrid('getRows');
    if(type==''||type==null||type==undefined){
        type=0;
    }
    var obj = new Object();
    obj.PCSLBM = rowDatas[index].PCSLBM ;
    obj.BMSAH = rowDatas[index].BMSAH;
    obj.PCCZLX = '0'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = ''; //仅评查审批阶段有
    obj.type=type;
    var url = "view/evaluate/pcblWin/deal.html";
    loadPcblWin(url, obj);
}

function loadPcblWin(href, param) {

    // 换从功能参数，作为参数传递
    FUNCTION_PARAM = param;
    if(FUNCTION_PARAM.type==1){
        skipWebPage("案件信息", getRootPath() + "/case.html", { pcslbm: "", bmsah: param.BMSAH }, '1');
    }else {
        $('#pcblWin').window('open').window('refresh', href);
    }
}*/
function pcWin_pcCountWin(index) {
    $('#pcWin_win_offline').window({
        width: 780,
        height: 500,
        modal: true,
        title: '评查预览',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {
        }
    });
    $('#pcWin_pcylCon_offline').find("table").remove();
    var datas=$('#table_eval_Ajlb').datagrid('getRows')[index];
    $('#pcWin_win_offline').window('open');$("#pcWin_win_offline").show();
    // 评查案件信息初始化
    $('#win_pcWin_ajmc').text(datas.AJMC);
    $('#win_pcWin_cbr').text(datas.BCBRMC);
    $('#win_pcWin_pcr').text(datas.CBRMC);
    $('#win_pcWin_pcsah').text(datas.BMSAH);
    $('#win_pcWin_pcsj').text(sjzh(datas.CJSJ));
    $('#win_pcWin_ajsj').text(sjzh(datas.WCRQ));
    // $('#win_pcWin_lbl_eval_handle_eval_ay').text(data.AY);
    $.ajax({
        url: getRootPath() + '/offline/getPcjgInfo',
        type: 'get',
        dataType: 'json',
        data: {pcslbm:datas.PCSLBM,dw:datas.CBDWBM},
        success: function (result) {
            if (result.status == 200) {
                var data = result.value;
                var html = '<table border="">';
                for (var i = 0; i < data.length; i++) {
                    html += '<tr class="thead">';
                    var htmlTit = '';
                    var htmlCon = '';

                    for (var z = 0; z < data[i].children.length; z++) {
                        htmlTit += '<td>' + data[i].children[z].pcxflmc + '</td>';
                        var pcList = data[i].children[z].children.length;
                        var pclistCon = '';
                        var num = 1;
                        for (var j = 0; j < data[i].children[z].children.length; j++) {
                            var bz = "";
                            if(data[i].children[z].children[j].sm == 'null' || data[i].children[z].children[j].sm == '' || data[i].children[z].children[j].sm == undefined || data[i].children[z].children[j].sm =="" || data[i].children[z].children[j].sm == null){
                                data[i].children[z].children[j].sm = "";
                            }else{
                                bz = "(备注:"+ data[i].children[z].children[j].sm+")";
                            }
                            if (data[i].children[z].children[j].PCFS == '1' && data[i].children[z].children[j].pcjg == '1') {
                                pclistCon += '<p>' + num + '.' + data[i].children[z].children[j].pcxmc +bz +'</p>';
                                num++;
                            } else if (data[i].children[z].children[j].PCFS == '2' && data[i].children[z].children[j].pcjg != '1' && data[i].children[z].children[j].pcjg != '0' && data[i].children[z].children[j].pcjg != "" && data[i].children[z].children[j].pcjg != undefined && data[i].children[z].children[j].PCFS != null) {
                                pclistCon += '<p>' + num + '.' + data[i].children[z].children[j].pcxmc + '(' + data[i].children[z].children[j].pcjg + ')' + bz + '</p>';
                                num++;
                            }
                        }
                        htmlCon += '<td>' + pclistCon + '</td>';

                    }
                    html += '<td rowspan="2">' + data[i].pcxflmc + '</td>';
                    html += htmlTit;
                    html += '</tr>';
                    html += '<tr class="tbody">';
                    html += htmlCon;
                    html += '</tr>';

                }

                html += '</table>';
                $('#pcWin_pcylCon_offline').html(html);
            }
        }
    });
}

function excel_export_data() {
    var obj=new Object();
    var dwbm = $('#general_dw_combotree').combotree('getValues').length==0?"":$('#general_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    // if($('#general_date').combotree('getValues').length==0){
    //     Alert("请选择时间");
    //     return;
    // }
    obj.startDate = $('#general_wcdate_start').datebox('getValue');
    obj.endDate = $('#general_wcdate_end').datebox('getValue');
    obj.pcstartDate=$('#general_pcdate_start').datebox('getValue');
    obj.pcendDate = $('#general_pcdate_end').datebox('getValue');

   // obj.wcrqnf= $('#general_date').combotree('getValues').length==0?"":$('#general_date').combotree("getValues").join(",");
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    var str="";
    if(tabIndex==0)
        str="评查总体情况时间分析";
    else if(tabIndex==1)
        str="评查总体情况地区分析";
    else if(tabIndex==2)
        str="评查总体情况条线分析";
    obj.type=tabIndex;
    obj.excelName=str;
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/analysis/excel_export_dataPc',
        data: {json:JSON.stringify(obj)},
        success: function (result) {
            if (result.code == 200){
                window.location.href=getRootPath()+result.data;
            }
        }
    });
}