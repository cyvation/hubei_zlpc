$(document).ready(function () {

    //样式初始化以及按钮事件绑定
    init_monitor_overview();
    $("#pcWin_win").hide();
});


function init_monitor_overview() {
    //评查单位树
    $('#tcxc_xcx_dw_combotree').combotree({
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
                $('#tcxc_xcx_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
            }
            var root = $('#tcxc_xcx_dw_combotree').combotree('tree').tree('getRoot');
            var children = root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for (var i = 0; i < children.length; i++) {
                valueArr.push(children[i].id);
            }
            $('#tcxc_xcx_dw_combotree').combotree("setValues", valueArr);
            index_addMousedownDiv(this, "tcxc_xcx_dw_combotree");
        }
    });
    //年度
    $('#tcxc_xcx_year_combotree').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#tcxc_xcx_year_combotree', data);
            }
            index_addMousedownDiv(this, "tcxc_xcx_year_combotree");
        }
    });
    $('#tcxc_xcx_year_combotree').combotree("loadData", getYearRange());
    //承办人身份
    $('#tcxc_xcx_eval_build_cbrsf').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#tcxc_xcx_eval_build_cbrsf', data);
            }
            index_addMousedownDiv(this, "tcxc_xcx_eval_build_cbrsf");
        }
    });
    $('#tcxc_xcx_eval_build_cbrsf').combotree("loadData", getCbrsfValues());
    //案件统计类别
    $('#tcxc_xcx_eval_build_ajtjlb').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#tcxc_xcx_eval_build_ajtjlb', data);
            }
            index_addMousedownDiv(this, "tcxc_xcx_eval_build_ajtjlb");
        }
    });
    $('#tcxc_xcx_eval_build_ajtjlb').combotree("loadData", getStajbs());
    //查询：
    $("#btn_tcxc_xcx_search").unbind('click');
    $("#btn_tcxc_xcx_search").bind('click', function () {
        load_table_tcxc_xcx();
    });
    //导出瑕疵项备注说明
    $("#btn_tcxc_xcx_bz_expExcel").unbind('click');
    $("#btn_tcxc_xcx_bz_expExcel").bind('click', function () {
        xcx_bz_exp_excel();
    });

    $('#tcxc_xcx_eval_build_pcfl').combotree({
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
                setAllCheckbox('#tcxc_xcx_eval_build_pcfl', data);
            }
            index_addMousedownDiv(this, "tcxc_xcx_eval_build_pcfl");
        }
    });
    load_cbt_win_eval_ajzlfx_pcmb(); //加载评查模板
    var resizeTabHeight = $("#tcxc_xcx_tabs_box").height() - 62;
    $("#tcxc_xcx_tabs_box").find(".panel-body").height(resizeTabHeight);
    init_table_tcxc_xcx(); //初始化年份统计报表
    init_table_tcxc_xcx_bz();
}

function xcx_bz_exp_excel(){
    var obj = new Object();
    obj.wcrqnf = $('#tcxc_xcx_year_combotree').combotree('getValues').join(",").trim();
    var dwbm = $('#tcxc_xcx_dw_combotree').combotree('getValues').join(",").trim();
    obj.dwbm = dwbm;
    obj.pcflbm = $('#tcxc_xcx_eval_build_pcfl').combotree('getValues').join(",").trim();
    obj.ywtx = $('#tcxc_xcx_eval_build_pcmb').combotree('getValues').join(",").trim();
    var cbrsfStr = $('#tcxc_xcx_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr, ",");
    var ajtjlb = $('#tcxc_xcx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(ajtjlb, ",");
    obj.flxtdm = "30003"; //瑕疵编码
     obj.xtdm = selectRowData.xtdm;
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
var selectRowData;

function init_table_tcxc_xcx() {

    $('#table_tcxc_xcx').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                title: '<span  style=\'font-size:16px;\'>瑕疵项目分析报表</span>',
                colspan: 3,
                align: 'center',
                halign: 'center'
            }
        ], [
            {
                field: 'name',
                width: 240,
                title: '<span  style=\'font-size:16px\'>瑕疵项</span>',
                align: 'left',
                halign: 'center'
            },
            {
                field: 'cnt',
                title: '<span  style=\'font-size:16px\'>存在该瑕疵项的案件数</span>',
                width: 64,
                align: 'center',
                halign: 'center',
                formatter: function (value, row, index) {
                    var  r = '<a href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_tcxc_xcx_jbxx_window(' + index + ', \'#table_tcxc_xcx\')">' + value + '</a> ';
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
                selectRowData = rowData;
                $("#btn_tcxc_xcx_bz_expExcel").removeAttr("disabled");
            }
            load_table_tcxc_xcx_bz(rowData);
        }
    });
}

function init_table_tcxc_xcx_bz() {

    $('#table_tcxc_xcx_bz').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field: "SM",
                title: '<span  style=\'font-size:16px;\'>瑕疵备注说明(已过滤重复内容和空备注)</span>',
                align: 'left',
                halign: 'center',
                formatter: function (value, row, index) {
                    var  r = '<a href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_tcxc_xcx_jbxx_window(' + index + ', \'#table_tcxc_xcx_bz\')">' + value + '</a> ';
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
function load_cbt_win_eval_ajzlfx_pcmb() {

    $('#tcxc_xcx_eval_build_pcmb').combotree('loadData', []);
    $('#tcxc_xcx_eval_build_pcmb').combotree('clear');
    $('#tcxc_xcx_eval_build_pcmb').combotree('setValue', '');

    $('#tcxc_xcx_eval_build_pcmb').combotree({
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
                setAllCheckbox('#tcxc_xcx_eval_build_pcmb', data);
            }
            index_addMousedownDiv(this, "tcxc_xcx_eval_build_pcmb");
        },
    });

}

// 瑕疵项数据获取
function load_table_tcxc_xcx() {
    var obj = new Object();
    obj.wcrqnf = $('#tcxc_xcx_year_combotree').combotree('getValues').join(",").trim();
    if(obj.wcrqnf == ""){
        Alert("请选择年度!");
        return;
    }
    var dwbm = $('#tcxc_xcx_dw_combotree').combotree('getValues').join(",").trim();
    if(dwbm == ""){
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = $('#tcxc_xcx_eval_build_pcfl').combotree('getValues').join(",").trim();
    if(obj.pcflbm == ""){
        Alert("请选择评查方式!");
        return;
    }
    obj.ywtx = $('#tcxc_xcx_eval_build_pcmb').combotree('getValues').join(",").trim();
    if( obj.ywtx == ""){
        Alert("请选择评查模板!");
        return;
    }
    obj.cbrsf = $('#tcxc_xcx_eval_build_cbrsf').combotree('getValues').join(",").trim();
    if(obj.cbrsf == ""){
        Alert("请选择承办人身份!");
        return;
    }
    obj.ajtjlb = $('#tcxc_xcx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    obj.wtType = "30003"; //瑕疵编码
    $('#table_tcxc_xcx').datagrid({
        url: getRootPath() + "/analysis/getTcfx",
        method: 'post',
        queryParams: obj
    });
}

// 瑕疵项备注数据获取
function load_table_tcxc_xcx_bz(node) {
    var obj = new Object();
    obj.wcrqnf = $('#tcxc_xcx_year_combotree').combotree('getValues').join(",").trim();
    if(obj.wcrqnf == ""){
        Alert("请选择年度!");
        return;
    }
    var dwbm = $('#tcxc_xcx_dw_combotree').combotree('getValues').join(",").trim();
    if(dwbm == ""){
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = $('#tcxc_xcx_eval_build_pcfl').combotree('getValues').join(",").trim();
    if(obj.pcflbm == ""){
        Alert("请选择评查方式!");
        return;
    }
    obj.ywtx = $('#tcxc_xcx_eval_build_pcmb').combotree('getValues').join(",").trim();
    if( obj.ywtx == ""){
        Alert("请选择评查模板!");
        return;
    }
    var cbrsfStr = $('#tcxc_xcx_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr, ",");
    obj.ajtjlb = $('#tcxc_xcx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    obj.flxtdm = "30003"; //瑕疵编码
    obj.xtdm = node.xtdm;
    $('#table_tcxc_xcx_bz').datagrid({
        url: getRootPath() + "/analysis/getPcxBzByXtdm",
        method: 'post',
        queryParams: {json: JSON.stringify(obj)}
    });
}

function alert_tcxc_xcx_jbxx_window(index, name) {

    var obj = new Object();
    var thisRow = $(name).datagrid('getRows')[index];
    obj.wcrqnf = $('#tcxc_xcx_year_combotree').combotree('getValues').join(",").trim();
    obj.dwbm = $('#tcxc_xcx_dw_combotree').combotree('getValues').join(",").trim();
    obj.pcflbm = $('#tcxc_xcx_eval_build_pcfl').combotree('getValues').join(",").trim();
    obj.ywtx = $('#tcxc_xcx_eval_build_pcmb').combotree('getValues').join(",").trim();
    obj.cbrsf = $('#tcxc_xcx_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(obj.cbrsf, ",");
    obj.ajtjlb = $('#tcxc_xcx_eval_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    obj.wtType = "30003"; //瑕疵编码
    if(name == "#table_tcxc_xcx"){
        obj.xtdm = thisRow.xtdm;
    }else{
        obj.xtdm = thisRow.XTDM;
        obj.sm = thisRow.SM;
    }
    var ypcUrl = getRootPath() + "/analysis/getTccwxxPcAjxxByParams";
    // 已评查案件datagrid
    $("#table_tcxc_xcx_ajxx_filter").datagrid({
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
                        return ' <a href="#" onclick="pcWin_analysis_xcx_pcyl(' + index + ')">查看</a>   ';
                    } else {
                        return '<a href="#" onclick="pcWinPage(' + index + ',\'#table_tcxc_xcx_ajxx_filter\',0)">查看</a>';
                    }
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_tcxc_xcx_ajxx_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_tcxc_xcx_ajxx_filter").window('setTitle', '已经评查案件');
    $("#win_tcxc_xcx_ajxx_filter").window('open');
}

function pcWin_analysis_xcx_pcyl(index) {
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

