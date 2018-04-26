/**
 * Created by user on 2017/11/4.
 * modified
 */
$(function () {

    //初始化评查列表
    init_tool_done_list();
})

// 初始化评查列表工具
function init_tool_done_list() {

    // 设置时间为本年
    $('#date_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    $('.radios').click(function () {
       $(this).children().addClass('redio_click_no');
       $(this).siblings().children().removeClass('redio_click_no');
       $('#btn_done_search').click();
    });

    $('#btn_done_search').click(function () {
        var type = $(".redio_click_no").attr('data-value');

        if (type == '1') {
            $("#grid_done_list_pc_div").css('display', '');
            $("#grid_done_list_fa_div").css('display', 'none');
            $("#grid_done_list_fk_div").css('display', 'none');
            $("#grid_done_list_offline_div").css('display', 'none');
            init_grid_done_list_pc();
        } else if (type == '2') {
            $("#grid_done_list_pc_div").css('display', 'none');
            $("#grid_done_list_fa_div").css('display', '');
            $("#grid_done_list_fk_div").css('display', 'none');
            $("#grid_done_list_offline_div").css('display', 'none');
            init_grid_done_list_fa();
        } else if (type == '3'){
            $("#grid_done_list_pc_div").css('display', 'none');
            $("#grid_done_list_fa_div").css('display', 'none');
            $("#grid_done_list_fk_div").css('display', '');
            $("#grid_done_list_offline_div").css('display', 'none');
            init_grid_done_list_fk();
        } else {
            $("#grid_done_list_pc_div").css('display', 'none');
            $("#grid_done_list_fa_div").css('display', 'none');
            $("#grid_done_list_fk_div").css('display', 'none');
            $("#grid_done_list_offline_div").css('display', '');
            init_grid_done_list_offine();
            init_win_pc();
        }
    });

    //查询已评查列表
    init_grid_done_list_pc();
}

/////////////////////////<!--已评查/已审批案件，已审批报告-->////////////////////////////
// 设置评查列表
function init_grid_done_list_pc() {

    $('#grid_done_list_pc').datagrid({
        width: 'auto',
        striped: true,
        fitColumns: true,
        singleSelect: false,
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        columns: [[
            {field: 'PCSAH', title: '评查案号', width: 160},
            {field: 'AJMC', title: '案件名称', width: 120,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'BMSAH', title: '部门受案号', width: 120},
            {field: 'BPC_DWMC', title: '承办单位', width: 90},
            {field: 'BPC_MC', title: '承办检察官', width: 110},
            {field: 'PCJL', title: '评查结论', width: 100},
            {field: 'PCJDMS', title: '评查状态', width: 100},
            {field: 'CJSJ',formatter:function (value,row,index) {
                    if(value != null && value != undefined){
                      return  DateParser_heng(value)
                    }
            },title: '评查日期',  fixed:true, width: 125 },
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCFLMC', title: '评查分类名称', hidden: true},
            {
                field: 'action', title: '操作', width: 90,
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="pcWinPage(' + index +',\'#grid_done_list_pc\')">查看</a>'
                    // var e = '<a href="#" onclick="pcWinPage(' + row + ')">查看</a>';
                    return e;
                }
            }
        ]],
        loadMsg: '数据加载中，请稍后...',
        onClickRow: function (rowIndex, rowData) {
            $('#grid_done_list_pc').datagrid('clearSelections');
            $('#grid_done_list_pc').datagrid('highlightRow', rowIndex);
        }
    });

     $('#grid_done_list_pc').datagrid('getPager').pagination({
          beforePageText: '第',
          afterPageText: '页   共{pages}页',
          displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
      });

    // 设置已经评查表
    resize_grid_done_list();

    // 加载已评查库列表
    load_grid_done_list_pc();
}
//加载评查列表
function load_grid_done_list_pc() {
    var type = $(".redio_click_no").attr('data-value');
    var ajmc = $('#txt_done_ajmc').textbox('getValue');

    var begin = $("#date_begin").datebox('getValue');
    var end = $("#date_end").datebox('getValue');

    // JS对象
    var obj = new Object();
    obj.TYPE = type;
    obj.NAME = ajmc;
    obj.begin = begin;
    obj.end = end;

    $('#grid_done_list_pc').datagrid({
        url: getRootPath() + '/handle/get_pcyblist',
        queryParams: {
            name: 'easyui',
            json: JSON.stringify(obj)
        }

    });
}

// 设置已经评查表
function resize_grid_done_list() {
    var width = $('#pnl_done_list').width();
    $('#grid_done_list_pc').datagrid('options').width = width  - 10;
    var height = $('#pnl_done_list').height();
    var h = $('#tool_done_list').height() + 10;
    $('#grid_done_list_pc').datagrid('options').height = height - h -3;
    $('#grid_done_list_pc').datagrid('resize');
}

////////////////////////////////<!--已审批方案 -->/////////////////////

<!--已审批方案-->
function init_grid_done_list_fa() {

    // 案件列表DataGrid初始化
    $('#grid_done_list_fa').datagrid({
        width: 'auto',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        idField: 'ID',
          pageSize: 20,
          pageList: [10, 20, 30, 50, 100],
        columns: [[
            {field: 'SPWJLX', title: '审批类型', width: 80},
            {field: 'SPNAME', title: '名称', width: 120},
            {field: 'SSRDWMC', title: '送审人单位名称', width: 120},
            {field: 'SSRXM', title: '送审人姓名', width: 80},
            {field: 'SPJL', title: '审批结论', width: 80},
            {field: 'SPYJ', title: '审批意见', width: 120},
            {field: 'SPWJBM', title: '审批类型',hidden:true},
            {field: 'PCSLBM', title: '评查受理编码',hidden: true},
            {field: 'SPRQ',formatter:function (value,row,index) {
                if(value != null && value != undefined){
                    return  DateParser_heng(value)
                }
            }, title: '审批日期', width: 100},
            {
                field: 'action', title: '操作', width: 80,
                formatter: function (value, row, index) {
                    switch(row.SPWJLX){
                        case "案件":
                            var e = '<a href="#" onclick="pcWinPage(' + index +',\'#grid_done_list_fa\')">查看</a>'
                            break;
                        case "报告":
                            break;
                        case "活动":
                            var e = '<a href="#" onclick="goto_eval_pcfa_page(' + row.SPWJBM + ')">查看</a>'
                            break;
                        default:
                            break;
                    }
                    // var e = '<a href="#" onclick="pcWinPage(' + index +',\'#grid_done_list_fa\')">查看</a>'
                    // var e = '<a href="#" onclick="pcWinPage(' + row + ')">查看</a>';
                    return e;
                }
            }


        ]],
        groupField: 'SPWJLX',
        view: groupview,
        groupFormatter: function (value, rows) {
            return value + '(' + rows.length + ')';
        },
        onClickRow: function (rowIndex, rowData) {
            $('#grid_done_list_fa').datagrid('clearSelections');
            $('#grid_done_list_fa').datagrid('highlightRow', rowIndex);
        }
    });
        $('#grid_done_list_fa').datagrid('getPager').pagination({
            beforePageText: '第',
            afterPageText: '页   共{pages}页',
            displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
        });
    // 页面分页控件放置到底部
    resize_grid_done_list_fa();

    load_grid_done_list_fa();

}

// 设置审批列表
function resize_grid_done_list_fa() {
    var width = $('#pnl_done_list').width();
    $('#grid_done_list_fa').datagrid('options').width = width - 10;
    var height = $('#pnl_done_list').height();
    var h = $('#tool_done_list').height() + 10;
    $('#grid_done_list_fa').datagrid('options').height = height - h -3;
    $('#grid_done_list_fa').datagrid('resize');
}

// 加载已审批列表
function load_grid_done_list_fa() {
    var type = $(".redio_click_no").attr('data-value');
    var ajmc = $('#txt_done_ajmc').textbox('getValue');

    var begin = $("#date_begin").datebox('getValue');
    var end = $("#date_end").datebox('getValue');

    // JS对象
    var obj = new Object();
    obj.TYPE = type;
    obj.NAME = ajmc;
    obj.begin =begin;
    obj.end = end;

    $('#grid_done_list_fa').datagrid({
        url: getRootPath() + '/handle/get_pcyblist',
        queryParams: {
            name: 'easyui',
            json: JSON.stringify(obj)
        }

    });
}

////////////////////////////<!--已反馈-->///////////////////
<!--已反馈-->
function init_grid_done_list_fk() {

    // 案件列表DataGrid初始化
    $('#grid_done_list_fk').datagrid({
        width: 'auto',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        idField: 'ID',
         pageSize: 20,
         pageList: [10, 20, 30, 50, 100],
        columns: [[
            {field: 'PCYJLX', title: '评查意见类型', width: 90},
            {field: 'PCSAH', title: '评查案号', width: 120},
            {field: 'AJMC', title: '案件名称', width: 80,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'BMSAH', title: '部门受案号', width: 120},
            {field: 'BPC_DWMC', title: '承办单位', width: 90},
            {field: 'BPC_BMMC', title: '承办部门', width: 90},
            {field: 'BPC_MC', title: '承办检察官', width: 80},
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCYJJL', title: '评查意见结论', width: 100},
            {field: 'PCYJ', title: '评查意见', width: 100},
            {field: 'PCSLBM', title: '评查受理编码',hidden: true},
            {field: 'YJTCSJ',formatter:function (value,row,index) {
                if(value != null && value != undefined){
                    return  DateParser_heng(value)
                }
            }, title: '反馈时间', width: 100},
            {field: 'action', title: '操作', width: 80,
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="pcWinPage(' + index +',\'#grid_done_list_fk\')">查看</a>'
                    return e;
                }
            }
        ]],
        groupField: 'PCYJLX',
        view: groupview,
        groupFormatter: function (value, rows) {
            return value + '(' + rows.length + ')';
        },
        onClickRow: function (rowIndex, rowData) {
            $('#grid_done_list_fk').datagrid('clearSelections');
            $('#grid_done_list_fk').datagrid('highlightRow', rowIndex);
        }
    });
    // 分页控件(中文)
      $('#grid_done_list_fk').datagrid('getPager').pagination({
          beforePageText: '第',
          afterPageText: '页   共{pages}页',
          displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
      });

    // 页面分页控件放置到底部

    resize_grid_done_list_fk();

    load_grid_done_list_fk();

}

// 设置反馈列表
function resize_grid_done_list_fk() {
    var width = $('#pnl_done_list').width();
    $('#grid_done_list_fk').datagrid('options').width = width - 10;
    var height = $('#pnl_done_list').height();
    var h = $('#tool_done_list').height() + 10;
    $('#grid_done_list_fk').datagrid('options').height = height - h -3;
    $('#grid_done_list_fk').datagrid('resize');
}

// 加载反馈列表
function load_grid_done_list_fk() {
    var type = $(".redio_click_no").attr('data-value');
    var ajmc = $('#txt_done_ajmc').textbox('getValue');

    var begin = $("#date_begin").datebox('getValue');
    var end = $("#date_end").datebox('getValue');

    // JS对象
    var obj = new Object();
    obj.TYPE = type;
    obj.NAME = ajmc;
    obj.begin = begin;
    obj.end = end;


    $('#grid_done_list_fk').datagrid({
        url: getRootPath() + '/handle/get_pcyblist',
        queryParams: {
                name: 'easyui',
                json: JSON.stringify(obj)
        }
    });
}
// 跳转到评查方案界面
function goto_eval_pcfa_page(pchdbm) {

    $.ajax({
        url: getRootPath() + "/manage/getPcyInfo",
        data: {pchdbm: pchdbm},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (data) {
            if (data.status != 200) {
                Alert(data.note);
                return;
            }

            var param = data.value;
            // JS对象,需要传输的值
            var obj = new Object();
            obj.PCFLBM = param.PCFLBM;
            obj.PCMBBM = param.PCMBJ;
            obj.PCHDBM = param.PCHDBM;

            // var url = "view/evaluate/build/special.html" + "?json=" + JSON.stringify(obj);
            // var url = "view/evaluate/build/special.html";
            var title = "";
            var url = "";
            //湖北：根据不同的评查分类跳转到不同的页面
            if(obj.PCFLBM == "008"){
                title = "重点评查 > 评查方案";
                url = "view/evaluate/build/blend.html";
            }else{
                title = "专项评查 > 评查方案";
                url = "view/evaluate/build/special.html";
            }
            load_function(title, url, param);
        }
    });
}
function init_grid_done_list_offine() {

    $('#grid_done_list_offline').datagrid({
        width: 'auto',
        striped: true,
        fitColumns: true,
        singleSelect: false,
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        columns: [[
            {field: 'PCSAH', title: '评查案号', width: 160},
            {field: 'AJMC', title: '案件名称', width: 120,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'BMSAH', title: '部门受案号', width: 120},
            {field: 'BPC_DWMC', title: '承办单位', width: 90},
            {field: 'BPC_MC', title: '承办检察官', width: 110},
            {field: 'BPC_WCRQ', title: '案件完成日期',formatter:function (value,row,index) {
                if(value != null && value != undefined){
                    return  sjzh(value);
                }
            },  fixed:true, width: 125 },
            {field: 'PCJL', title: '评查结论', width: 100},
            {field: 'PCJDMS', title: '评查状态', width: 100},
            {field: 'CJSJ', title: '评查时间',formatter:function (value,row,index) {
                if(value != null && value != undefined){
                    return  sjzh(value);
                }
            },title: '评查日期',  fixed:true, width: 125 },
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCFLMC', title: '评查分类名称', hidden: true},
            {
                field: 'PCSLBM', title: '操作', width: 90,
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="pcWin_pcInfo(' + index +')">查看</a>  ';
                    if(userInfo.GH==row.PCR_GH){
                        e += '<a href="#" onclick="pcWinPage_offline(' + index +',\'#grid_done_list_offline\')">编辑</a>  ';
                       // e += '<a href="#" onclick="deletePcaj(' + index +')">删除</a>';
                    }
                    return e;
                }
            }
        ]],
        loadMsg: '数据加载中，请稍后...',
        loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        },
        onClickRow: function (rowIndex, rowData) {
            $('#grid_done_list_pc').datagrid('clearSelections');
            $('#grid_done_list_pc').datagrid('highlightRow', rowIndex);
        }
    });
    resize_grid_done_offline_list();
    // 加载已评查库列表
    load_grid_done_list_offline();
}
// 设置已经评查表
function resize_grid_done_offline_list() {
    var width = $('#pnl_done_list').width();
    $('#grid_done_list_offline').datagrid('options').width = width  - 10;
    var height = $('#pnl_done_list').height();
    var h = $('#tool_done_list').height() + 10;
    $('#grid_done_list_offline').datagrid('options').height = height - h -3;
    $('#grid_done_list_offline').datagrid('resize');
}
//加载评查列表
function load_grid_done_list_offline() {
    var ajmc = $('#txt_done_ajmc').val();
    var begin = $("#date_begin").datebox('getValue');
    var end = $("#date_end").datebox('getValue');

    // JS对象
    var basciInfo ={
        'ajmc':ajmc,
        'begin':begin,
        'end':end,
        'dwbm':userInfo.DWBM
    };
    $('#grid_done_list_offline').datagrid({
        url: getRootPath() + '/offline/loadOfflineList',
        queryParams: {
            name: 'easyui',
            json: JSON.stringify(basciInfo)
        }

    });
}
function pcWinPage_offline(index,id,type) {
    var rowDatas = $(id).datagrid('getRows')[index];
    if(type==''||type==null||type==undefined){
        type=0;
    }
    var obj = new Object();
    obj.PCSLBM = rowDatas.PCSLBM
    obj.BMSAH = rowDatas.BMSAH;
    obj.DWBM = rowDatas.PCDWBM;
    obj.PCCZLX = '1'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = ''; //仅评查审批阶段有
    obj.type=0;
    var url = "view/evaluate/build/offline.html";
    loadPcblWin_offline(url, obj);
}

function loadPcblWin_offline(href, param) {
    // 换从功能参数，作为参数传递
    FUNCTION_PARAM = param;
    $('#pcblWin').window('open').window('refresh', href);
}
function init_win_pc(){
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
}
function pcWin_pcInfo(index) {
    $('#pcWin_pcylCon_offline').find("table").remove();
    var datas=$('#grid_done_list_offline').datagrid('getRows')[index];
    $('#pcWin_win_offline').window('open');
    // 评查案件信息初始化
    $('#win_offline_ajmc').text(datas.AJMC);
    $('#win_offline_cbr').text(datas.BPC_MC);
    $('#win_offline_pcr').text(datas.PCR_MC);
    $('#win_offline_pcsah').text(datas.PCSAH);
    $('#win_offline_pcsj').text(sjzh(datas.CJSJ));
    $('#win_offline_ajsj').text(sjzh(datas.BPC_WCRQ));
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
                $('#pcWin_pcylCon_offline').html(html);
            }
        }
    });
}