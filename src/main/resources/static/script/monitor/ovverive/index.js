/**
 * Created by Administrator on 2018/3/20.
 */

$(document).ready(function () {

    //样式初始化以及按钮事件绑定
    init_monitor_overview();

    // 数据加载
   // data_monitor_overview_rd();

});


function init_monitor_overview() {
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

    $('#date_monitor_overview_rd_begin').datebox({
        editable: false,
        value: '2013-01-01'
    });

    $('#date_monitor_overview_rd_end').datebox({
        editable: false,
        value:  '2017-12-31'//new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });


    //查询：
    $("#btn_monitor_overview_rd_search").unbind('click');
    $("#btn_monitor_overview_rd_search").bind('click',function () {
        data_monitor_overview_rd();
    });

    $('#date_monitor_overview_key_begin').datebox({
        editable: false,
        value: '2013-01-01'
    });

    $('#date_monitor_overview_key_end').datebox({
        editable: false,
        value: '2017-12-31'//new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    // 常规表格初始化：
    $("#table_monitor_overview_rd").datagrid({
        fitColumns: true,
        singleSelect:true,
        striped:true,
        rownumbers: true,
       // pagination:true,
        pageSize: 20,
        pageList: [20, 30, 40, 50, 100],
        pageNumber:1
    });
    $('#cbt_win_eval_build_pcfl').combotree({
        method: 'get',
        lines: true,
        multiple: false,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1){
                pcflbm = data[0].id;
                $('#cbt_win_eval_build_pcfl').combotree('setValue', pcflbm);
                //load_cbt_win_eval_build_pchd(pcflbm);
                data_monitor_overview_rd();
            }
        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }
        }
    });
    var resizeTabHeight = $("#monitor_tabs_box").height() - 62;
    $("#monitor_tabs_box").find(".panel-body").height(resizeTabHeight);
}

// 常规抽查数据获取
function data_monitor_overview_rd() {
    var obj=new Object();
    var dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').length==0?userInfo.DWBM:$('#monitor_handler_dw_combotree').combotree('getValues').join(",");
    obj.dwbm=dwbm;
    obj.startDate= $('#date_monitor_overview_rd_begin').datebox("getValue");
    obj.endDate= $('#date_monitor_overview_rd_end').datebox("getValue");
    obj.pcflbm =$('#cbt_win_eval_build_pcfl').combotree('getValue');//'001';
    console.log(dwbm);
    ShowProgress();
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/monitor/getpcyl',
        data: obj,
        success: function (result) {
            if (result.code == 200){
                var data = result.data;
                // 生成常规抽查/重点抽查总表
                // init_cg_data_grid(result.data.header, result.data.data, result.data);
                init_cg_data_grid(data.header, data.data, data.rowList);
                CloseProgress();
            } else {
                CloseProgress();
                Alert(result.note);
            }
        }
    });
}

/*
// 重点抽查数据获取
function data_monitor_overview_key() {
    var obj=new Object();
    // var dwbm = $('#cbt_monitor_overview_key').combotree('getValue') == ""?userInfo.DWBM:$('#cbt_monitor_overview_key').combotree('getValue');
    // obj.dwbm=dwbm;
    obj.startDate= $('#date_monitor_overview_key_begin').datebox("getValue");
    obj.endDate= $('#date_monitor_overview_key_end').datebox("getValue");
    obj.pcflbm ='002';
    ShowProgress();
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/monitor/getpcyl',
        data: obj,
        success: function (result) {
            if (result.code == 200){
                var data = result.data;
                // 生成常规抽查/重点抽查总表
                // init_key_data_grid(result.data.header, result.data.data, result.data);
                init_key_data_grid(data.header, data.data, data.rowList);
                CloseProgress();
            } else {
                CloseProgress();
                Alert(result.note);
            }
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}
*/

//----------- 生成常规抽查datagrid------------
function init_cg_data_grid(headers, rows, data){

    // 清空历史数据
    $('#table_monitor_overview_rd').datagrid('loadData',[]);
    // 动态生成行/列
    var colstr = "{field:'dwbm',title:'单位编码',hidden:true,align: 'center', halign: 'center'}," +
        "{field:'dwmc',title:'单位名称',width:265, align: 'center', halign: 'center',fixed:'true'},"; //拼接动态列
  //  var rowFormat = "dwbm:'@dwbm',dwmc:'@dwmc',"; //拼接每行，默认（0,0）
   var rowFormat ='';
    for (var i = 0; i < headers.length; i++) {
        if (!headers[i])
            continue;
        colstr += "{ field:'" + headers[i].sxgzbm + "', title:'" + headers[i].gzmc + "',width:90,align: 'center', halign: 'center' }";
       rowFormat += headers[i].sxgzbm + ":'0/0'";
        if (i < headers.length - 1) {
            colstr += ',';
           rowFormat += ',';
        }
    }
    var table_widht = $(".easyui_table_yscx").width();
    var options = {};
    options.columns = eval("[[" + colstr + "]]");
    $('#table_monitor_overview_rd').datagrid(options);


    // // 添加行
    // for (var i = 0; i < rows.length; i++) {
    //     if (!rows[i])
    //         continue;
    //
    //     var rowData = rowFormat;
    //     // rowData = rowData.replace("@dwbm", rows[i].dwbm).replace("@dwmc",rows[i].dwmc);
    //     rowData = rowData.replace("@dwbm", rows[i].dwbm);
    //     rowData = rowData.replace("@dwmc",rows[i].dwmc);
    //     // 添加每一行数据
    //     var row = eval("({" + rowData + "})");
    //     $('#table_monitor_overview_rd').datagrid('appendRow', row);
    // }
    // 加载数据
    $('#table_monitor_overview_rd').datagrid('loadData',data);

    //=======================
     // 填数据
    // for (var i = 0; i < rows.length; i++) {
    //     if (!rows[i])
    //         continue;
    //
    //    // var rowIndex = $('#table_monitor_overview_rd').datagrid('getRowIndex', rows[i].dwbm);
    //     // if (rowIndex == -1) return;
    //     // 已经评查数量/总量------>已经评查/未评查
    //    var bmlist= rows[i].gzbmDtoList;
    //
    //     // 批量更新一行
    //     for(var j = 0; j < bmlist.length; j++) {
    //         var rowx=bmlist[j].sxgzbm +":\"<a style='cursor:pointer;font-weight:bold'><span data-index="+ i +" data-field="+ bmlist[j].sxgzbm +" class='ypc' onclick=ypc_monitor_overview_rd_click(this)>" + bmlist[j].ypc +"</span>/<span data-index="+i+" data-field="+ bmlist[j].sxgzbm +" onclick=wpc_monitor_overview_rd_click(this) class='wpc'>" +(bmlist[j].wpc) +"</span></a>\""
    //         var row = eval("({"+rowx+"})");
    //         // 更新数据
    //         $('#table_monitor_overview_rd').datagrid('updateRow',{
    //             // index: rowIndex,
    //             index: i,
    //             row: row
    //         });
    //     }
    //
    // }

}

//----------- 生成重点抽查datagrid------------
/*function init_key_data_grid(headers, rows, data){


    // 清空历史数据
    $('#table_monitor_overview_key').datagrid('loadData',[]);


    // 动态生成行/列
    var colstr = "{field:'dwbm',title:'单位编码',width:100,hidden:true,align: 'center', halign: 'center'}," +
        "{field:'dwmc',title:'单位名称',width:265, align: 'center', halign: 'center',fixed:'true'},"; //拼接动态列
    // var rowFormat = "dwbm:'@dwbm',dwmc:'@dwmc',"; //拼接每行，默认（0,0）
    var rowFormat='';
    for (var i = 0; i < headers.length; i++) {
        if (!headers[i])
            continue;
        colstr += "{ field:'" + headers[i].sxgzbm + "', title:'" + headers[i].gzmc + "',width:90,align: 'center', halign: 'center' }";
        rowFormat += headers[i].sxgzbm + ":'0/0'";
        if (i < headers.length - 1) {
            colstr += ',';
           rowFormat += ',';
        }
    }
    var options = {};
    options.columns = eval("[[" + colstr + "]]");
    $('#table_monitor_overview_key').datagrid(options);

    // 加载数据
    $('#table_monitor_overview_key').datagrid('loadData',data);

    // 添加行
    // for (var i = 0; i < rows.length; i++) {
    //     if (!rows[i])
    //         continue;
    //    // var rowIndex =   $('#table_monitor_overview_key').datagrid('getRowIndex', rows[i].dwbm);
    //    // if (rowIndex == -1) return;
    //     var rowData = rowFormat;
    //
    //     // rowData = rowData.replace("@dwbm", rows[i].dwbm).replace("@dwmc",rows[i].dwmc);
    //     rowData = rowData.replace("@dwbm", rows[i].dwbm);
    //     rowData = rowData.replace("@dwmc",rows[i].dwmc);
    //     // 添加每一行数据
    //     var row = eval("({" + rowData + "})");
    //     $('#table_monitor_overview_key').datagrid('appendRow', row);
    // }

    //=======================
    // 填数据
    // for (var i = 0; i < rows.length; i++) {
    //     if (!rows[i])
    //         continue;
    //
    //     var tempDatas = $('#table_monitor_overview_key').datagrid('getRows');
    //     var rowIndex=-1;
    //     for (var t=0;t<tempDatas.length;t++){
    //         if(tempDatas[t].dwbm==rows[i].dwbm){
    //             rowIndex=t;break
    //         }
    //     }
    //     if (rowIndex == -1) return;
    //     // 已经评查数量/总量------>已经评查/未评查
    //     var bmlist= rows[i].gzbmDtoList;
    //
    //     // 批量更新一行
    //     for(var j = 0; j < bmlist.length; j++) {
    //         var rowx=bmlist[j].sxgzbm +":\"<a style='cursor:pointer;font-weight:bold'><span data-index="+ rowIndex +" data-field="+ bmlist[j].sxgzbm +" class='ypc' onclick=ypc_monitor_overview_key_click(this)>" + bmlist[j].ypc +"</span>/<span data-index="+rowIndex+" data-field="+ bmlist[j].sxgzbm +" onclick=wpc_monitor_overview_key_click(this) class='wpc'>" +(bmlist[j].wpc) +"</span></a>\""
    //         var row = eval("({"+rowx+"})");
    //         // 更新数据
    //         $('#table_monitor_overview_key').datagrid('updateRow',{
    //              ////index: rowIndex,
    //             index: rowIndex,
    //             row: row
    //         });
    //     }
    //
    // }

}*/

function cg_pcgl_get_excel_export_data() {

    var obj=new Object();

  /*  var tab = $('#tabs_monitor_overview').tabs('getSelected');
    var tabIndex = $('#tabs_monitor_overview').tabs('getTabIndex',tab);

    if (tabIndex == 0) {
        obj.pcflbm ='001';
    }else {
        obj.pcflbm ='002';
    }*/
    var dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').length==0?userInfo.DWBM:$('#monitor_handler_dw_combotree').combotree('getValues').join(",");
    obj.dwbm=dwbm;
    obj.pcflbm =$('#cbt_win_eval_build_pcfl').combotree('getValue');
    obj.startDate= $('#date_monitor_overview_rd_begin').datebox("getValue");
    obj.endDate= $('#date_monitor_overview_rd_end').datebox("getValue");


    $.ajax({
        type: 'GET',
        url: getRootPath()+'/monitor/pcglExportExcel',
        data: obj,
        success: function (result) {
            if (result.code == 200){
               window.location.href=getRootPath()+result.data;
            }
        }
    });
}

// 常规抽查已评查
function ypc_monitor_overview_rd_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    alert_win_monitor_overview_rd_filter(currIndex, currField,'0');
}

// 常规评查未评查
function wpc_monitor_overview_rd_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    alert_win_monitor_overview_rd_filter(currIndex, currField,'1');
}

// 重点抽查已评查
function ypc_monitor_overview_key_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    alert_win_monitor_overview_key_filter(currIndex, currField,'0');
}

// 重点评查未评查
function wpc_monitor_overview_key_click(el) {
    var currIndex = $(el).attr("data-index");
    var currField = $(el).attr("data-field");
    alert_win_monitor_overview_key_filter(currIndex, currField,'1');
}

// 常规抽查对应已评查/未评查案件列表已经评查/未评查（0/1）
function alert_win_monitor_overview_rd_filter(index, gzbm,type) {
    var rowDatas = $('#table_monitor_overview_rd').datagrid('getRows');
    var dwbm = rowDatas[index].dwbm;

    // 判断用户是否具有权限
    if (DJDWBM !=userInfo.DWBM && dwbm != userInfo.DWBM) {
        return;
    }

    var obj = new Object();
    obj.dwbm = dwbm;
    obj.sxgzbm = gzbm;
    var pcflbm=$('#cbt_win_eval_build_pcfl').combotree('getValue');
    obj.pcflbm =pcflbm;//'001';
    obj.startDate = $('#date_monitor_overview_rd_begin').datebox('getValue');
    obj.endDate =$('#date_monitor_overview_rd_end').datebox('getValue');
    url = getRootPath() + '/monitor/getPcylAjJbxx';
    columns =[[]];

    if (type =='0') { // 已经评查
        obj.wpc ='false';
        columns = [[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90},
            {field:'DWMC',title:'承办单位',width:90},
            {field:'PCR_DWMC',title:'评查单位',width:90},
            {field:'CBRMC',title:'承办检察官',width:90},

            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var r=(pcflbm=="009")? '<a href="#" onclick="pcWin_xlpcLn(' + index +')">查看</a>':'<a href="#" onclick="pcWinPage(' + index +',\'#table_monitor_overview_rd_filter\',0)">查看</a>'

                    return r;
                }
            }
        ]];
    }else{ // 未评查
        obj.wpc ='true';
        columns = [[
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
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_monitor_overview_rd_filter\',1)">查看</a>'
                    return r;
                }
            }
        ]]

    }

        $('#table_monitor_overview_rd_filter').datagrid({
            border:0,
            fit:true,
            fitColumns: true,
            singleSelect: true,
            rownumbers: true,
            idField: 'BMSAH',
            url: getRootPath()+'/monitor/getPcylAjJbxx',
            queryParams: obj,
            pagination:true,
            pageNumber:1,
            pageSize:20,
            columns:columns,
            loadFilter:function (data) {
                return data.code ==200 ? JSON.parse(data.data):[];
            }
        });

    $('#table_monitor_overview_rd_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 筛选窗体
    if (type == '0') {
        $('#win_monitor_overview_rd_filter').window('setTitle','已评查案件列表');
    }else {
        $('#win_monitor_overview_rd_filter').window('setTitle','未评查案件列表');
    }

    $('#win_monitor_overview_rd_filter').window('open');

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
