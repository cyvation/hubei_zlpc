var cwx_select_rowData;//错误项行数据

$(document).ready(function () {
    //样式初始化以及按钮事件绑定
    init_tccw_cw();
    $("#pcWin_win").hide();
});


function init_tccw_cw() {
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
            var children = root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for (var i = 0; i < children.length; i++) {
                valueArr.push(children[i].id);
            }
            $('#monitor_handler_dw_combotree').combotree("setValues", valueArr);
            index_addMousedownDiv(this, "monitor_handler_dw_combotree");
        }
    });
    //年度
    $('#monitor_handler_year_combotree').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#monitor_handler_year_combotree', data);
            }
            index_addMousedownDiv(this, "monitor_handler_year_combotree");
        }
    });
    $('#monitor_handler_year_combotree').combotree("loadData", getYearRange());
    //承办人身份
    $('#cbt_win_eval_build_cbrsf').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#cbt_win_eval_build_cbrsf', data);
            }
            index_addMousedownDiv(this, "cbt_win_eval_build_cbrsf");
        }
    });
    $('#cbt_win_eval_build_cbrsf').combotree("loadData", getCbrsfValues());
    //案件统计类别
    $('#tcxc_cwx_eval_build_ajtjlb').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#tcxc_cwx_eval_build_ajtjlb', data);
            }
            index_addMousedownDiv(this, "tcxc_cwx_eval_build_ajtjlb");
        }
    });
    $('#tcxc_cwx_eval_build_ajtjlb').combotree("loadData", getStajbs());
    //查询：
    $("#btn_monitor_overview_rd_search").unbind('click');
    $("#btn_monitor_overview_rd_search").bind('click', function () {
        load_tccw_cw();
    });
    //导出错误项备注说明
    $("#btn_tccw_cwx_bz_expExcel").unbind('click');
    $("#btn_tccw_cwx_bz_expExcel").bind('click', function () {
        cwx_bz_exp_excel();
    });
    $('#cbt_win_eval_build_pcfl').combotree({
        editable: false,
        method: 'get',
        panelWidth: 220,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1) {
                setAllCheckbox('#cbt_win_eval_build_pcfl', data);
            }
            index_addMousedownDiv(this, "cbt_win_eval_build_pcfl");
        }
    });
    load_cbt_win_eval_tccw_cw_pcmb(); //加载评查模板
    var resizeTabHeight = $("#tccw_cwx_tabs_box").height() - 62;
    $("#tccw_cwx_tabs_box").find(".panel-body").height(resizeTabHeight);
    init_table_analysis_tccw_cw(); //初始化错误项表格
    init_table_tccw_cwx_bz();
}

function cwx_bz_exp_excel(){
    var obj = new Object();
    obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    var dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').join(",").trim();
    obj.dwbm = dwbm;
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValues').join(",").trim();
    obj.ywtx = $('#cbt_win_eval_build_pcmb').combotree('getValues').join(",").trim();
    var cbrsfStr = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr, ",");
    obj.ajtjlb = $('#tcxc_cwx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    obj.flxtdm = "30002"; //错误编码
    obj.xtdm = cwx_select_rowData.xtdm;
    $.ajax({
        type: 'post',
        url: getRootPath() + '/analysis/getPcxBzByXtdmToExcel',
        dataType: 'json',
        data: {json: JSON.stringify(obj)},
        success: function (result) {
            if (result.code == 200) {
                var path = getRootPath();
                window.location.href = path+ result.data;
            }
        },
        error: function (xhr) {
            Alert('导出案件评查项备注错误\n\n' + xhr.responseText);
        }
    });
}


function init_table_analysis_tccw_cw() {

    $('#table_tcxc_cwx').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                title: '<span  style=\'font-size:16px;\'>错误项目分析报表</span>',
                colspan: 3,
                align: 'center',
                halign: 'center'
            }
        ], [
            {
                field: 'name',
                width: 240,
                title: '<span  style=\'font-size:16px\'>错误项</span>',
                align: 'left',
                halign: 'center'
            },
            {
                field: 'cnt',
                title: '<span  style=\'font-size:16px\'>存在该错误项的案件数</span>',
                width: 64,
                align: 'center',
                halign: 'center',
                formatter: function (value, row, index) {
                    var  r = '<a href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_tcxc_cwx_jbxx_window(' + index + ',\'#table_tcxc_cwx\')">' + value + '</a> ';
                    return r;
                }
            },
            {
                field: 'pczb',
                title: '<span  style=\'font-size:16px\'>占比</span>',
                width: 64,
                align: 'center',
                halign: 'center'
            }
        ]],
        loadFilter: function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        },
        onSelect:function (rowIndex, rowData) {
            if(rowIndex != -1){
                cwx_select_rowData = rowData;
                $("#btn_tccw_cwx_bz_expExcel").removeAttr("disabled");
            }
            load_table_tccw_cwx_bz(rowData);
        }
    });
}

function init_table_tccw_cwx_bz() {

    $('#table_tccw_cwx_bz').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field: "SM",
                title: '<span  style=\'font-size:16px;\'>错误备注说明(已过滤重复内容和空备注)</span>',
                align: 'left',
                halign: 'center',
                formatter: function (value, row, index) {
                    var  r = '<a href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_tcxc_cwx_jbxx_window(' + index + ',\'#table_tccw_cwx_bz\')">' + value + '</a> ';
                    return r;
                }
            }
        ]],
        loadFilter: function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        }
    });
}

// 加载评查模板
function load_cbt_win_eval_tccw_cw_pcmb() {

    $('#cbt_win_eval_build_pcmb').combotree('loadData', []);
    $('#cbt_win_eval_build_pcmb').combotree('clear');
    $('#cbt_win_eval_build_pcmb').combotree('setValue', '');

    $('#cbt_win_eval_build_pcmb').combotree({
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
            if (data != null && data.length >= 1) {
                setAllCheckbox('#cbt_win_eval_build_pcmb', data);
            }
            index_addMousedownDiv(this, "cbt_win_eval_build_pcmb");
        },
    });

}

// 常规抽查数据获取
function load_tccw_cw() {
    var obj = new Object();
    obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    if (obj.wcrqnf == "") {
        Alert("请选择年度!");
        return;
    }
    var dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').join(",").trim();
    if (dwbm == "") {
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValues').join(",").trim();
    if (obj.pcflbm == "") {
        Alert("请选择评查方式!");
        return;
    }
    obj.ywtx = $('#cbt_win_eval_build_pcmb').combotree('getValues').join(",").trim();
    if (obj.ywtx == "") {
        Alert("请选择评查模板!");
        return;
    }
    obj.cbrsf = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    if (obj.cbrsf == "") {
        Alert("请选择承办人身份!");
        return;
    }
    obj.ajtjlb = $('#tcxc_cwx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    obj.wtType = "30002"; //错误编码
    $('#table_tcxc_cwx').datagrid({
        url: getRootPath() + "/analysis/getTcfx",
        method: 'post',
        queryParams: obj
    });
}

// 错误项备注数据获取
function load_table_tccw_cwx_bz(node) {
    var obj = new Object();
    obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    if(obj.wcrqnf == ""){
        Alert("请选择年度!");
        return;
    }
    var dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').join(",").trim();
    if(dwbm == ""){
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValues').join(",").trim();
    if(obj.pcflbm == ""){
        Alert("请选择评查方式!");
        return;
    }
    obj.ywtx = $('#cbt_win_eval_build_pcmb').combotree('getValues').join(",").trim();
    if( obj.ywtx == ""){
        Alert("请选择评查模板!");
        return;
    }
    var cbrsfStr = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr, ",");
    obj.ajtjlb = $('#tcxc_cwx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    obj.flxtdm = "30002"; //错误编码
    obj.xtdm = node.xtdm;
    $('#table_tccw_cwx_bz').datagrid({
        url: getRootPath() + "/analysis/getPcxBzByXtdm",
        method: 'post',
        queryParams: {json: JSON.stringify(obj)}
    });
}

function alert_tcxc_cwx_jbxx_window(index, name) {

    var obj = new Object();
    var thisRow = $(name).datagrid('getRows')[index];
    obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    obj.dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').join(",").trim();
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValues').join(",").trim();
    obj.ywtx = $('#cbt_win_eval_build_pcmb').combotree('getValues').join(",").trim();
    obj.cbrsf = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(obj.cbrsf, ",");
    obj.ajtjlb = $('#tcxc_cwx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    obj.wtType = "30002"; //错误编码
    if(name == "#table_tcxc_cwx"){
        obj.xtdm = thisRow.xtdm;
    }else{
        obj.xtdm = thisRow.XTDM;
        obj.sm = thisRow.SM;
    }
    obj.xtdm = thisRow.xtdm;
    var ypcUrl = getRootPath() + "/analysis/getTccwxxPcAjxxByParams";


    // 已评查案件datagrid
    $("#table_tcxc_cwx_ajxx_filter").datagrid({
        border: 0,
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        url: ypcUrl,
        queryParams: obj,
        columns: [[
            {field: 'BMSAH', title: '部门受案号', width: 160},
            {
                field: 'AJMC', title: '案件名称', width: 160,
                formatter: function (value) {
                    return tipMessage(value);
                }
            },
            {field: 'AJLBMC', title: '案件类别', width: 90},
            {field: 'DWMC', title: '承办单位', width: 90},
            {field: 'CBRMC', title: '承办检察官', width: 90},
            {field: 'PCR_DWMC', title: '评查单位', width: 90},
            {
                field: 'CJSJ', title: '评查日期', fixed: true, width: 115,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {
                field: 'CZ', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var pcflbm = row.PCFLBM;
                    if (pcflbm == '009') {
                        return ' <a href="#" onclick="pcWin_analysis_cwx_pcyl(' + index + ')">查看</a>   ';
                    } else {
                        return '<a href="#" onclick="pcWinPage(' + index + ',\'#table_tcxc_cwx_ajxx_filter\',0)">查看</a>';
                    }
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_tcxc_cwx_ajxx_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_tcxc_cwx_ajxx_filter").window('setTitle', '已经评查案件');
    $("#win_tcxc_cwx_ajxx_filter").window('open');
}


function pcWin_analysis_cwx_pcyl(index) {
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
    var datas=$('#table_analysis_nd_filter').datagrid('getRows')[index];
    $('#pcWin_win').window('open');
    $("#pcWin_win").show();
    // 评查案件信息初始化
    $('#win_pcWin_lbl_eval_handle_eval_ajmc').text(datas.AJMC);
    $('#win_pcWin_lbl_eval_handle_eval_cbr').text(datas.CBRMC);
    $('#win_pcWin_lbl_eval_handle_eval_pcr').text(datas.PCR_MC);
    $('#win_pcWin_lbl_eval_handle_eval_pcsah').text(datas.BMSAH);
    $('#win_pcWin_lbl_eval_handle_eval_pcsj').text(sjzh(datas.CJSJ));
    $('#win_pcWin_lbl_eval_handle_eval_ajsj').text(sjzh(datas.BPC_WCRQ));
    $.ajax({
        url: getRootPath() + '/offline/getPcjgInfo',
        type: 'get',
        dataType: 'json',
        data: {pcslbm:datas.PCSLBM,dw:datas.BPC_DWBM},
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


