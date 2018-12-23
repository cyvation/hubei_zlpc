
var option=[
    {
        field: 'pcajs',title: '<span  style="font-size:14px">1评查案件数（件）</span>',width: 120,rowspan: 4,align: 'center',
        formatter: function (value, row, index) {
            var r = (row.name.indexOf("合计") > 0 || row.name == '合计') ? value : '<a href="#" data-field={"id":"' + row.id + '","pid":"' + row.pid + '","fl":""} onclick="pcWin_pcList(this,0)">' + value + '</a>';
            return r;
        }
    },
    {
        field: 'cwajs',title: '<span  style=\'font-size:14px\'>2存在问题的案件数</span>',width:120,rowspan: 4, align: 'center',
        formatter: function (value, row, index) {
            var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
            return r;
        }
    },
    {
        field: 'cwBl',title: '<span  style=\'font-size:14px\'>3存在问题的案件比例（2/1）</span>',width: 100,rowspan: 4,align: 'center'
    },
    {
        field: 'cws',title: '<span  style=\'font-size:14px\'>4问题个数</span>',rowspan: 4,width: 120,align: 'center'
    },
    {
        field: 'ajpjcws',title: '<span  style=\'font-size:14px\'>案件平均问题个数(4/1)</span>',rowspan: 4,width: 80,align: 'center'
    }
    ,{
        field: 'pjcws',title: '<span  style=\'font-size:14px\'>存在问题案件平均问题个数（4/2）</span>',rowspan: 4,width: 100,align: 'center'
    },
    {
        title: '<span  style=\'font-size:14px\'>条线通用</span>',colspan: 16,rowspan: 2,align: 'center'
    },{
        title: '<span  style=\'font-size:14px\'>个别条线适用</span>', colspan: 10,align: 'center'
    }
];
var options=[
    [
        {title: '<span  style=\'font-size:14px\'> 侦监</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px\'>公诉</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>未检</span>', colspan: 6,align: 'center'}
    ]
    ,[
        {title: '<span  style=\'font-size:14px;\'>证据采信</span>', colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>事实认定</span>', colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>法律适用</span>', colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px\'>办案程序</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>法律文书</span>', colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>司法责任制落实</span>', colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>系统规范应用</span>', colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px\'>其他情况</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>侦查监督</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>出席二审法庭</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>特别程序</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>出席二审法庭</span>',colspan: 2,align: 'center'},
        {title: '<span  style=\'font-size:14px;\'>法律监督</span>',colspan: 2,align: 'center'}
    ]
    ,[
        {field:'zjcxHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20001","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'zjcxBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'ssrdHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20002","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'ssrdBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'flsyHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20003","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'flsyBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'bacxHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20004","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'bacxBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'flwsHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20008","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'flwsBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'sfHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20010","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'sfBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'xtgfHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20011","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'xtgfBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'qtHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20020","ywtx":""} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'qtBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'zcjdHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20009","ywtx":"10002"} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'zcjdBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'cxesgHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20006","ywtx":"10003"} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'cxesgBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'tbHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20005","ywtx":"10017"} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'tbBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'cxeswHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20006","ywtx":"10017"} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'cxeswBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'},
        {field:'fljdHj',title: '<span  style=\'font-size:14px;\'>合计</span>', align: 'center',
            formatter: function (value, row, index) {
                var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#" data-field={"id":"'+row.id+'","pid":"'+row.pid+'","fl":"20007","ywtx":"10017"} onclick="pcWin_pcList(this,1)">'+value+'</a>';
                return r;
            }
        },
        {field:'fljdBl',title: '<span  style=\'font-size:14px;\'>占比</span>', align: 'center'}
    ]
];
var tabIndex=0;
var object= ['时间分析','地区分析','条线分析'];
$(document).ready(function () {
    init_errorItem_overview();
    init_errorItem_tab();
    init_tabIndex_tab();
    $("#pcWin_win_offline").hide();
});
function init_tabIndex_tab(){
    if(tabIndex==0)
        init_errorItem_data("table_errorItem_date");
    else if(tabIndex==1)
        init_errorItem_data("table_errorItem_dq");
    else if(tabIndex==2)
        init_errorItem_data("table_errorItem_tx");
}
function init_errorItem_tab() {
    try {
        for (var i = 0; i < object.length; i++) {
            if (i != getSelectTabIndex()) {
                tabIndex=getSelectTabIndex();
                $("#tabs_errorItem_overview").tabs('close', object[i]);
            }
        }
    }
    catch(err) {
    }
    //$('#pcWin_win').window({closed: true});
    //查询：
    $("#btn_errorItem_data_search").unbind('click');
    $("#btn_errorItem_data_search").bind('click',function () {
        if(tabIndex==0) {
            //init_errorItem_data("table_errorItem_date");
            data_errorItem_data("table_errorItem_date");
        }else if(tabIndex==1) {
            //init_errorItem_data("table_errorItem_dq");
            data_errorItem_data("table_errorItem_dq");
        }else if(tabIndex==2) {
           // init_errorItem_data("table_errorItem_tx");
            data_errorItem_data("table_errorItem_tx");
        }
    });
    // tab标签切换
    $("#tabs_errorItem_overview").tabs({
        onSelect:function(title,index){
            if (index ==0) { // 加载时间分析数据
                tabIndex=0;
                init_errorItem_data("table_errorItem_date");
            }
            if (index == 1) { //加载地区分析数据
                tabIndex=1;
                init_errorItem_data("table_errorItem_dq");
            }
            if (index == 2) { //加载条线分析数据
                tabIndex=2;
                init_errorItem_data("table_errorItem_tx");
            }
        }
    });
}
function init_errorItem_overview() {
    //评查单位树
    $('#errorItem_dw_combotree').combotree({
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
                $('#errorItem_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
            }
            var root = $('#errorItem_dw_combotree').combotree('tree').tree('getRoot');
            var children=root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for(var i= 0;i<children.length;i++){
                valueArr.push(children[i].id);
            }
            $('#errorItem_dw_combotree').combotree("setValues", valueArr);
            index_addMousedownDiv(this,"errorItem_dw_combotree");
        }
    });
//年份
    $('#errorItem_date').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#errorItem_date', data);
            }
            index_addMousedownDiv(this, "errorItem_date");
        }
    });
    $('#errorItem_date').combotree("loadData", getYearRange());
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
    var resizeTabHeight = $("#errorItem_tabs_box").height() - 62;
    $("#errorItem_tabs_box").find(".panel-body").height(resizeTabHeight);
}
function data_errorItem_data(id) {
    var obj=new Object();
    var dwbm = $('#errorItem_dw_combotree').combotree('getValues').length==0?"":$('#errorItem_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    if($('#errorItem_date').combotree('getValues').length==0){
        Alert("请选择时间");
        return;
    }
    obj.date= $('#errorItem_date').combotree('getValues').length==0?"":$('#errorItem_date').combotree("getValues").join(",");
    obj.flxtdm='30002';
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    var url="";
    if(tabIndex==0) {
        url=getRootPath() + '/analysis/loadDateData';
    }else if(tabIndex==1){
        url=getRootPath() + '/analysis/loadDqdata';
    }else if(tabIndex==2){
        url=getRootPath() + '/analysis/loadTxData';
    }
    $('#'+id).treegrid({
        url: url,
        queryParams:{json : JSON.stringify(obj)},
        loadFilter:function (data) {
            return data.code ==200 ?JSON.parse( data.data):[];
        }
    });
}
function init_errorItem_data(id) {
    var column=[];
    var url="";
    if(tabIndex==0) {
        column.push({field: 'name', title: '<span  style="font-size:14px">年份</span>',rowspan: 4, width: 100, align: 'center'});
    }else if(tabIndex==1){
        column.push({field: 'name', title: '<span  style="font-size:14px">地区</span>',rowspan: 4, width: 350, align: 'left'});
    }else if(tabIndex==2){
        column.push({field: 'name', title: '<span  style="font-size:14px">条线</span>',rowspan: 4, width: 350, align: 'left'});
    }
    for(var i=0;i<option.length;i++){
        column.push(option[i]);
    }
    var columns=[];
    columns.push(column);
    for(var i=0;i<options.length;i++){
        columns.push(options[i]);
    }
    $('#'+id).treegrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: false,
        treeField: 'name',
        animate: true,
        idField: 'id',
        method: 'post',
        columns:columns,
        toolbar:"#tool"
    });
}

function pcWin_pcList(el,type){
    var obj=new Object();
    var dwbm = $('#errorItem_dw_combotree').combotree('getValues').length==0?"":$('#errorItem_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    obj.wcrqnf= $('#errorItem_date').combotree('getValues').length==0?"":$('#errorItem_date').combotree("getValues").join(",");
    obj.flxtdm='30002';
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    obj.type=type;
    var currField = $(el).attr("data-field");
    var jsonField = JSON.parse(currField);
    if(tabIndex==0){
        obj.wcrqnf=jsonField.id;
    }else if(tabIndex==1){
        obj.dwbm=jsonField.id;
    }else{
        obj.pcxId=jsonField.id;
        obj.ywtx=jsonField.pid;
    }
    if(type==1){
        obj.fl=jsonField.fl;
        obj.ywtx=(jsonField.ywtx==undefined)?obj.ywtx:jsonField.ywtx;
    }
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
        url: getRootPath() + "/analysis/getErrPclbAjJbxx",
        queryParams:{json:JSON.stringify(obj)},
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLB_MC',title:'案件类别',width:90},
            {field:'CBDWMC',title:'承办单位',width:90},
            {field:'CBRMC',title:'承办检察官',width:90},
            {field:'WCRQ',title:'完成日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWin_xlpcLn(' + index +')">查看</a>';
                }
            }
        ]]
        , loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        }
    });
    $("#win_eval_build_table").window('setTitle','评查案件列表');
    $("#win_eval_build_table").window('open');
}
function pcWin_xlpcLn(index) {
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
    $('#pcWin_win_offline').window('open');
    $("#pcWin_win_offline").show();
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
    var dwbm = $('#errorItem_dw_combotree').combotree('getValues').length==0?"":$('#errorItem_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    if($('#errorItem_date').combotree('getValues').length==0){
        Alert("请选择时间");
        return;
    }
    obj.date= $('#errorItem_date').combotree('getValues').length==0?"":$('#errorItem_date').combotree("getValues").join(",");
    obj.flxtdm='30002';
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    var str="";
    if(tabIndex==0)
       str="问题项目时间分析";
    else if(tabIndex==1)
        str="问题项目地区分析";
    else if(tabIndex==2)
        str="问题项目条线分析";
    obj.type=tabIndex;
    obj.excelName=str;
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/analysis/excel_export_data',
        data: {json:JSON.stringify(obj)},
        success: function (result) {
            if (result.code == 200){
                window.location.href=getRootPath()+result.data;
            }
        }
    });
}