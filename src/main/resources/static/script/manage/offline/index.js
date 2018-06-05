/**
 * Created by user on 2017/11/4.
 * modified
 */
$(function () {
    //初始化评查列表
    init_tool_done_list();
    init_win_pc();
})

// 初始化评查列表工具
function init_tool_done_list() {
    //评查单位树
    $('#cbt_offline_pcdw').combotree({
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
                $('#cbt_offline_pcdw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,"cbt_offline_pcdw");
        }
    });
    //评查单位树
    $('#cbt_offline_bpcdw').combotree({
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
                $('#cbt_offline_bpcdw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,"cbt_offline_bpcdw");
        }
    });
    // 设置时间为本年
    $('#date_offline_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_offline_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    $('#btn_offline_search').click(function () {
            init_grid_done_list_pc();
    });
    //查询已评查列表
    init_grid_done_list_pc();
}
function init_win_pc(){
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
                    var e = '<a href="#" onclick="pcWin_xlpcLn(' + index +')">查看</a>  ';
                  /*  if(userInfo.GH==row.PCR_GH){*/
                  //      e += '<a href="#" onclick="pcWinPage_offline(' + index +',\'#grid_done_list_pc\')">编辑</a>  ';
                        e += '<a href="#" onclick="deletePcaj(' + index +')">删除</a>';
                   /* }*/
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
    resize_grid_done_list();
    // 加载已评查库列表
    load_grid_done_list_pc();
}
// 设置已经评查表
function resize_grid_done_list() {
    var width = $('#pnl_done_list').width();
    $('#grid_done_list_pc').datagrid('options').width = width  - 10;
    var height = $('#pnl_done_list').height();
    var h = $('#tool_offline_list').height() + 10;
    $('#grid_done_list_pc').datagrid('options').height = height - h -3;
    $('#grid_done_list_pc').datagrid('resize');
}
//加载评查列表
function load_grid_done_list_pc() {
    var ajmc = $('#txt_offline_ajmc').val();
    var pcr = $('#txt_offline_pcr').val();
    var bpcr = $('#txt_offline_bpcr').val();
    var begin = $("#date_offline_begin").datebox('getValue');
    var end = $("#date_offline_end").datebox('getValue');
    var dw=$('#cbt_offline_pcdw').combotree('getValue') == undefined ? userInfo.DWBM : $('#cbt_offline_pcdw').combotree('getValues').join(",");//评查单位编码
    var dws=$('#cbt_offline_bpcdw').combotree('getValue') == undefined ? "" : $('#cbt_offline_bpcdw').combotree('getValues').join(",");//评查单位编码
    // JS对象
    var basciInfo ={
        'ajmc':ajmc,
        'pcr':pcr,
        'bpcr':bpcr,
        'begin':begin,
        'end':end,
        'dwbm':dw,
        'bpcdw':dws
    };
    $('#grid_done_list_pc').datagrid({
        url: getRootPath() + '/offline/loadOfflineList',
        queryParams: {
            name: 'easyui',
            json: JSON.stringify(basciInfo)
        }

    });
}
function pcWinPage_offline(index,id,type) {
    var rowDatas = $(id).datagrid('getRows');
    if(type==''||type==null||type==undefined){
        type=0;
    }
    var obj = new Object();
    obj.PCSLBM = rowDatas[index].PCSLBM
    obj.BMSAH = rowDatas[index].BMSAH;
    obj.DWBM = rowDatas[index].PCDWBM;
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
function pcWin_xlpcLn(index) {
    $('#pcWin_pcylCon').find("table").remove();
    var datas=$('#grid_done_list_pc').datagrid('getRows')[index];
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
function deletePcaj(index){
    var datas=$('#grid_done_list_pc').datagrid('getRows')[index];
    Confirm("确认", "删除后将看不到此数据，是否确认删除？", function (r) {
        if (r) {
            $.ajax({
                url: getRootPath() + '/offline/updatePcaj',
                type: 'get',
                dataType: 'json',
                data: {pcslbm: datas.PCSLBM,dw:datas.PCDWBM},
                success: function (result) {
                    if (result.status == 200) {
                        Alert("删除成功！");
                        $('#grid_done_list_pc').datagrid('load');
                    }else{
                        Alert(result);
                    }
                }
            });
        }
    })
}


function export_excel_dw() {
    var ajmc = $('#txt_offline_ajmc').val();
    var pcr = $('#txt_offline_pcr').val();
    var bpcr = $('#txt_offline_bpcr').val();
    var begin = $("#date_offline_begin").datebox('getValue');
    var end = $("#date_offline_end").datebox('getValue');
    var dw=$('#cbt_offline_pcdw').combotree('getValue') == undefined ? userInfo.DWBM : $('#cbt_offline_pcdw').combotree('getValues').join(",");//评查单位编码
    // JS对象
    var basciInfo ={
        'ajmc':ajmc,
        'pcr':pcr,
        'bpcr':bpcr,
        'begin':begin,
        'end':end,
        'dwbm':dw
    };
    $.ajax({
        url:getRootPath()+"/count/exportOfflineExcel",
        type:"post",
        data:{json: JSON.stringify(basciInfo)},
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
