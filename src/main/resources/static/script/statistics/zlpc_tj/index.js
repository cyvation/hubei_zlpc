var cbdwbm;
$(document).ready(function () {
    $('#cbt_win_eval_build_pcjg').combobox('setValue',"全部");
    $('#cbt_win_eval_build_pczt').combobox('setValue',"0");
    // 样式初始化以及事件绑定
    init_statistics_analysis();

});

// 加载评查分类
function load_cbt_win_eval_build_pcfl() {

    $('#cbt_win_eval_build_pcfl').combotree({
        method: 'get',
        lines: true,
        multiple: true,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1){
                pcflbm = data[0].id;
                $('#cbt_win_eval_build_pcfl').combotree('setValue', pcflbm);

                load_cbt_win_eval_build_pchd(pcflbm);
            }

        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }
        }
    });
    //评查单位树
    $('#monitor_handler_dw_combotree').combotree({
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
                $('#monitor_handler_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
            }
            var root = $('#monitor_handler_dw_combotree').combotree('tree').tree('getRoot');
            var children=root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for(var i= 0;i<children.length;i++){
                valueArr.push(children[i].id);
            }
            $('#monitor_handler_dw_combotree').combotree("setValues", valueArr);
            index_addMousedownDiv(this,"monitor_handler_dw_combotree");
        }
    });
}

// 加载评查活动
function load_cbt_win_eval_build_pchd(pcflbm) {

    $('#cbt_win_eval_build_pchd').combotree('loadData',[]);
    $('#cbt_win_eval_build_pchd').combotree('clear');
    $('#cbt_win_eval_build_pchd').combotree('setValue', '');

    $('#cbt_win_eval_build_pchd').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/manage/get_pchd',
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess: function (node, data) {
            // 默认选中刑事
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_build_pchd').combotree('setValue', data[0].id);
            }

        },
        onSelect: function (node) {

        }
    });

}

// 加载评查模板
function load_cbt_win_eval_build_pcmb(pcflbm) {

    $('#cbt_win_eval_build_pcmb').combotree('loadData',[]);
    $('#cbt_win_eval_build_pcmb').combotree('clear');
    $('#cbt_win_eval_build_pcmb').combotree('setValue', '');

    $('#cbt_win_eval_build_pcmb').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/template/template',
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess: function (node,data) {
            // 专项默认选中
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_build_pcmb').combotree('setValue', data[0].id);
            }

        },
        onSelect: function (node) {

        }
    });

}
// 样式初始化：
function init_statistics_analysis() {

    init_monitor_statistiscs_dw();



}

// 单位tab样式以及事件：
function init_monitor_statistiscs_dw(){
    //加载评查分类
    load_cbt_win_eval_build_pcfl();
    //单位--评查时间
    $('#monitor_statis_dw_start').datebox({
        editable: false,
        value:  '2013-01-01'
    });

    $('#monitor_statis_dw_end').datebox({
        editable: false,
        value: '2017-12-31' //new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });

    // 单位表格查询：
    $("#btn_monitor_statis_dw_search").unbind('click');
    $("#btn_monitor_statis_dw_search").bind('click', function () {
        data_monitor_statistiscs_dw();
    });
    var resizeTabHeight = $("#tabsBox").height() - 62;
    $("#tabs").find(".panel-body").height(resizeTabHeight);
}

// 展示单位表格数据：
function data_monitor_statistiscs_dw() {

    var obj = new Object();
    var t = $('#monitor_handler_dw_combotree').combotree('tree');	// 获取树对象
    var n = t.tree('getSelected');
    obj.sfhj= 'N';//n.SFHJ;
    obj.dwbm =  $('#monitor_handler_dw_combotree').combotree('getValues').join(",")
    obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
    var pcflbm=$('#cbt_win_eval_build_pcfl').combotree('getValues');
    obj.pcflbm =$('#cbt_win_eval_build_pcfl').combotree('getValues').join(",");
    var tree = $('#cbt_win_eval_build_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if(node.attributes.SFJS == "Y"){
        obj.pcmbbm = $('#cbt_win_eval_build_pcmb').combotree('getValue');
    }else{
        obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
    }
    var zd=true,sj=true,xx=true,cg=true;
    //var array=[];
    var fl="";
    if($('#monitor_handler_dw_combotree').combotree('getValues').length==0){
        Alert("请选择单位！");
        return;
    }
    if(pcflbm.length==0){
        Alert("请选择评查方式！");
        return;
    }

    for(var i=0;i<pcflbm.length;i++){
        if("001"==pcflbm[i]){
            cg=false;
           // fl+="<th>常规<br>抽查</th>";
        }else if("007"==pcflbm[i]){
            sj=false;
          //  fl+="<th>随机<br>评查</th>";
        }else if("008"==pcflbm[i]){
            zd=false;
          //  fl+="<th >重点<br>评查</th>";
        }else if("009"==pcflbm[i]){
            xx=false;
          //  fl+="<th >2013案<br>件评查</th>";
        }
    }
    var jg=$('#cbt_win_eval_build_pcjg').combobox('getValue');
    obj.pcjl=jg;
    var ajjg="";
    var yz=true,hg=true,xc=true,bhg=true;
    if(jg=="全部"||jg==""){
        yz=false;hg=false;xc=false;bhg=false;
       // ajjg+="<th>优<br>质</th><th>合<br>格</th><th>瑕<br>疵</th><th>不合<br>格</th>";
    }else if(jg=="优质案件"){
      //  ajjg+="<th>优<br>质</th>";
        yz=false;
    }else if(jg=="合格案件"){
     //   ajjg+="<th>合<br>格</th>";
        hg=false;
    }else if(jg=="瑕疵案件"){
     //   ajjg+="<th>瑕<br>疵</th>";
        xc=false;
    }else if(jg=="不合格案件"){
     //   ajjg+="<th>不合<br>格</th>";
       bhg=false;
    }

    obj.pczt=$('#cbt_win_eval_build_pczt').combobox('getValue');
    // 单位表格datagrid
    $('#table_monitor_statistiscs_dw').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        //toolbar: $('#tool_org_manage_search'),
        url: getRootPath() + "/queryAll/getZlpcTableData",
        method:'post',
        queryParams:{json : JSON.stringify(obj)},
        columns: [[
            {
                field: 'name',
                title: '<span  style=\'font-size:16px;\'>单<br>位<br>名<br>称</span>',
                rowspan: 3,
                width: 260,
                align: 'left',
                halign: 'center',
                formatter: function (value, row, index) {
                    var r ='';
                    for(var i=0;i<row.dwjb;i++){
                        r+="&emsp;"
                    }
                    r+=row.sfhj=="Y"?(value+"合计"):value;
                    return r;
                }
            },
            {
                title: '<span  style="font-size:14px">评查方式</span>',
                colspan: 4,//pcflbm.length==1?0:pcflbm.length,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>结果等次</span>',
                colspan: 4,//(jg=="全部")?4:0,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>证据采信</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>事实认定</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>法律适用</span>',
                colspan: 3,
                align: 'center'
            }
            ,{
                title: '<span  style=\'font-size:14px\'>办案程序</span>',
                colspan: 2,
                align: 'center'
            },{
                title: '<span  style=\'font-size:14px\'>法律文书</span>',
                colspan: 2,
                align: 'center'
            },{
                title: '<span  style=\'font-size:14px\'>侦查监督</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>司法责任制落实</span>',
                colspan: 2,
                align: 'center'
            }
            ,{
                title: '<span  style=\'font-size:14px\'>系统规范应用</span>',
                align: 'center'
            }
            ,{
                title: '<span  style=\'font-size:14px\'>其他情况</span>',
                align: 'center'
            }
        ]
            , [
                {title: '<span  style=\'font-size:14px\'>常规<br>抽查</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px\'>随机<br>评查</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px;\'>重点<br>评查</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px\'>2013案<br>件评查</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px\'>优<br>质</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px\'>合<br>格</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',align: 'center'},
                {title: '<span  style=\'font-size:14px\'>不合<br>格</span>',align: 'center'},
                {   //证据采信
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                }
                ,{  //事实认定 瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }
                ,
                {   //法律适用  瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                }
                , { //办案程序
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }
                , { //法律文书
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }, {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }, { //侦查监督  错误
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }, {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {   //司法责任制落实
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {   // 系统规范应用 瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {   //其他 瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
            ], [
                {
                    field: 'cgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0){
                            r ='<a href="#" data-field={\"pcflbm\":\"001\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'sjpCount',
                    title: '<span  style=\'font-size:14px\'>次/件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcflbm\":\"007\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'zdpcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcflbm\":\"008\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'ajCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcflbm\":\"009\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'yzCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcjl\":\"优质案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'hgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcjl\":\"合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'xcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcjl\":\"瑕疵案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'bhgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        value=value==undefined?"0":value;
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"pcjl\":\"不合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },/* {
                    field: 'pcwtzsCount',
                    title: '<span  style=\'font-size:14px\'>件/处</span>',
                    align: 'center'
                }
                , */{
                    field: 'zjcxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'zjcxfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                ,{
                    field: 'ssrdXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'ssrdCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'flsyXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'bacxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bacxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'flwsCwCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"法律文书\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flwsXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"法律文书\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'zcjdCwCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"侦查监督\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zcjdXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"侦查监督\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'sfzrzlsCwCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"司法责任制落实\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'sfzrzlsXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"司法责任制落实\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'xtgfyyXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"系统规范应用\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'qtqkXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if ((row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM)&&row.sfhj!="Y"&&index!=0) {
                            r = '<a href="#" data-field={\"zmc\":\"其他\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
            ]],
        loadFilter:function (result) {
            return result.code == 200 ?JSON.parse( result.data)  : [];
        }

    });

}


// 已经评查案件（type对应0，1,2分别是单位，部门，检察官表）
function alert_jbxx_ypc_window(el,type, index) {

    var obj = new Object();
    var ypcUrl = getRootPath() ;

    // 判断哪个表：
   var thisRow = $('#table_monitor_statistiscs_dw').datagrid('getRows')[index];
    obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
    obj.dwbmList = thisRow.dwbm;

    ypcUrl += "/queryAll/getAjjbxx";

    var currField = $(el).attr("data-field");
    var jsonField = JSON.parse(currField);


    if (!isNull(jsonField.zmc)) {
        obj.zmc = jsonField.zmc;
    }
    if (!isNull(jsonField.pcxflmc)) {
        obj.pcxflmc = jsonField.pcxflmc;
    }

    if (!isNull(jsonField.pcflbm)) {
        obj.pcflbm = jsonField.pcflbm;
    }else{
        obj.pcflbm =$('#cbt_win_eval_build_pcfl').combotree('getValues').join(",");
    }
    if (!isNull(jsonField.pcjl)) {
        obj.pcjl = jsonField.pcjl;
    }else{
        obj.pcjl = $('#cbt_win_eval_build_pcjg').combobox('getValue')=="全部"?"":$('#cbt_win_eval_build_pcjg').combobox('getValue');
    }
    // 已评查案件datagrid
    $("#table_eval_build_statistics_analysis_filter").datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        // url: getRootPath() + "/queryTable/getAjjbxx",
        url:ypcUrl,
        queryParams:obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90},
            {field:'DWMC',title:'承办单位',width:90},
            {field:'CBRMC',title:'承办检察官',width:90},
            {field:'PCR_DWMC',title:'评查单位',width:90},
            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  row.PCFLBM=="009"? '<a href="#" onclick="pcWin_xlpcLn(' + index +')">查看</a>':'<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_statistics_analysis_filter\',0)">查看</a>';
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_eval_build_statistics_analysis_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });


    $("#win_eval_build_statistics_analysis_filter").window('setTitle','已经评查案件');
    $("#win_eval_build_statistics_analysis_filter").window('open');

}

//获取已审结未评查案件信息（type对应0，1,2分别是单位，部门，检察官表）
function alert_statistic_shengji_window(el,type,index){

    var obj = new Object();
    // 判断哪个表：
    var  thisRow = $('#table_monitor_statistiscs_dw').datagrid('getRows')[index];
    obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
    obj.dwbmList = thisRow.dwbm;
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValue');
    var tree = $('#cbt_win_eval_build_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if(node.attributes.SFJS == "Y"){
        obj.pcmbbm = $('#cbt_win_eval_build_pcmb').combotree('getValue');
    }else{
        obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
    }

    $('#table_eval_build_statistics_analysis_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        url: getRootPath() + "/queryTable/getWpcAjjbxx",
        queryParams:obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90},
            {field:'DWMC',title:'承办单位',width:90},
            {field:'CBRMC',title:'承办检察官',width:90},
            {field:'SLRQ',title:'受理日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'WCBZRQ',title:'完成日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_statistics_analysis_filter\',1)">查看</a>'
                    return r;
                }
            }
        ]]
    });

    $('#table_eval_build_statistics_analysis_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 展示已审结未评查案件信息
    $("#win_eval_build_statistics_analysis_filter").window('setTitle','已经审结案件');
    $("#win_eval_build_statistics_analysis_filter").window('open');


}

function export_excel_dw() {
    var obj = new Object();
    var t = $('#monitor_handler_dw_combotree').combotree('tree');	// 获取树对象
    var n = t.tree('getSelected');
    obj.sfhj= 'N';//n.SFHJ;
    obj.dwbm =  $('#monitor_handler_dw_combotree').combotree('getValues').join(",")
    obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
    var pcflbm=$('#cbt_win_eval_build_pcfl').combotree('getValues');
    obj.pcflbm =$('#cbt_win_eval_build_pcfl').combotree('getValues').join(",");
    var tree = $('#cbt_win_eval_build_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if(node.attributes.SFJS == "Y"){
        obj.pcmbbm = $('#cbt_win_eval_build_pcmb').combotree('getValue');
    }else{
        obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
    }
    if($('#monitor_handler_dw_combotree').combotree('getValues').length==0){
        Alert("请选择单位！");
        return;
    }
    if(pcflbm.length==0){
        Alert("请选择评查方式！");
        return;
    }
    var jg=$('#cbt_win_eval_build_pcjg').combobox('getValue');
    obj.pcjl=jg;

    obj.pczt=$('#cbt_win_eval_build_pczt').combobox('getValue');
    $.ajax({
        url:getRootPath()+"/queryAll/exportDwExcel",
        type:"get",
        data:{json : JSON.stringify(obj)},
        success:function (data) {
            if (data.code == 200) {
                window.location.href=getRootPath()+ data.data;
            }
            CloseProgress();
        },
        error:function () {
            CloseProgress();
        }
    });
}
function pcWin_xlpcLn(index) {
    $('#pcWin_win').window({
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
    $('#pcWin_pcylCon').find("table").remove();
    var datas=$('#table_monitor_overview_rd_filter').datagrid('getRows')[index];
    $('#pcWin_win').window('open');
    // 评查案件信息初始化
    $('#win_pcWin_lbl_eval_handle_eval_ajmc').text(datas.AJMC);
    $('#win_pcWin_lbl_eval_handle_eval_cbr').text(datas.BPC_MC);
    $('#win_pcWin_lbl_eval_handle_eval_pcr').text(datas.PCR_MC);
    $('#win_pcWin_lbl_eval_handle_eval_pcsah').text(datas.PCSAH);
    $('#win_pcWin_lbl_eval_handle_eval_pcsj').text(sjzh(datas.CJSJ));
    $('#win_pcWin_lbl_eval_handle_eval_ajsj').text(sjzh(datas.BPC_WCRQ));
    // $('#win_pcWin_lbl_eval_handle_eval_ay').text(data.AY);
    $.ajax({
        url: getRootPath() + '/offline/getPcjgInfo',
        type: 'get',
        dataType: 'json',
        data: {pcslbm:datas.PCSLBM,dw:datas.PCDWBM},
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
                $('#pcWin_pcylCon').html(html);
            }
        }
    });
}



