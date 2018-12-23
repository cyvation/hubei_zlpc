/**
 * Created by Administrator on 2018/3/20.
 */

$(document).ready(function () {

    //样式初始化以及按钮事件绑定
    init_monitor_overview();
    $("#pcWin_win").hide();
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
                var root = $('#monitor_handler_dw_combotree').combotree('tree').tree('getRoot');
                var children = root.children
                var valueArr = new Array();
                valueArr.push(data[0].id)
                for (var i = 0; i < children.length; i++) {
                    valueArr.push(children[i].id);
                }
                $('#monitor_handler_dw_combotree').combotree("setValues", valueArr);
            }
            index_addMousedownDiv(this, "monitor_handler_dw_combotree");
        }
    });
    //年度
    // $('#monitor_handler_year_combotree').combotree({
    //     editable: false,
    //     panelWidth: 160,
    //     lines: true,
    //     multiple: true,
    //     cascadeCheck: false,
    //     onShowPanel: index_onShowPanel,
    //     onHidePanel: index_onHidePanel,
    //     onLoadSuccess: function (node, data) {
    //         if (data != null && data.length >= 1) {
    //             setAllCheckbox('#monitor_handler_year_combotree', data);
    //         }
    //         index_addMousedownDiv(this, "monitor_handler_year_combotree");
    //     }
    // });
    // $('#monitor_handler_year_combotree').combotree("loadData", getYearRange());

    $('#tjfx_nd_wc_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#tjfx_nd_wc_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

// 评查日期
    $('#tjfx_nd_pc_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#tjfx_nd_pc_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });


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
    $('#tjfx_tx_cbt_build_ajtjlb').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#tjfx_tx_cbt_build_ajtjlb', data);
            }
            index_addMousedownDiv(this, "tjfx_tx_cbt_build_ajtjlb");
        }
    });
    $('#tjfx_tx_cbt_build_ajtjlb').combotree("loadData", getStajbs());
    //查询：
    $("#btn_monitor_overview_rd_search").unbind('click');
    $("#btn_monitor_overview_rd_search").bind('click', function () {
        data_monitor_overview_rd();
    });
    //导出Excel
    $("#btn_tx_expExcel").unbind('click');
    $("#btn_tx_expExcel").bind('click', function () {
        ajzlqkfx_tx_expExcel();
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
    load_cbt_win_eval_ajzlfx_pcmb(); //加载评查模板
    var resizeTabHeight = $("#monitor_tabs_box").height() - 62;
    $("#monitor_tabs_box").find(".panel-body").height(resizeTabHeight);
    init_table_analysis_ajqkzlfx_tx(); //初始化年份统计报表
}

function init_table_analysis_ajqkzlfx_tx() {

    $('#table_analysis_ajqkzlfx_tx').treegrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: false,
        treeField: 'name',
        animate: true,
        idField: 'id',
        columns: [[
            {
                field: 'name',
                title: '<span  style=\'font-size:16px;\'>条线</span>',
                rowspan: 3,
                width: 160,
                align: 'left',
                halign: 'center'
            },
            {
                title: '<span  style=\'font-size:16px;\'>评查情况</span>',
                colspan: 3
            },
            {
                field: 'wts',
                title: '<span  style=\'font-size:16px\'>评查结果等次</span>',
                colspan: 8,
                align: 'center',
            }
        ], [
            {
                field: 'bjs',
                width: 48,
                title: '<span  style=\'font-size:16px\'>办结数</span>',
                rowspan: 2,
                align: 'center',
                halign: 'center'
            },
            {
                field: 'pcajs',
                title: '<span  style=\'font-size:16px\'>评查案件数</span>',
                width: 48,
                rowspan: 2,
                align: 'center'
            },
            {
                field: 'pcajZb',
                title: '<span  style=\'font-size:16px\'>评查比(评查案件数/办结数)</span>',
                width: 64,
                rowspan: 2,
                align: 'center'
            }, {
                title: '<span  style=\'font-size:16px\'>优质</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:16px\'>合格</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:16px\'>瑕疵</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:16px\'>不合格</span>',
                colspan: 2,
                align: 'center'
            }
        ], [
            {
                field: 'yzajs',
                width:24,
                title: '<span  style=\'font-size:16px\'>件数</span>',
                align: 'center',
                formatter: function (value, row, index) {
                    var r = '';
                    if (row.name!= "合计") {
                        r = '<a href="#"  style="color: #145bae;text-decoration: none;"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="alert_jbxx_tx_tj_window(this ,' + index + ', \'优质案件\')">' + value + '</a> ';
                    } else {
                        r = value;
                    }
                    return r;
                }
            },
            {
                field: 'yzajZb',
                width:24,
                title: '<span  style=\'font-size:16px\'>占比(优质数/评查案件数)%</span>',
                align: 'center'
            },
            {
                field: 'hgajs',
                width:24,
                title: '<span  style=\'font-size:16px\'>合格</span>',
                align: 'center',
                formatter: function (value, row, index) {
                    var r = '';
                    if (row.name!= "合计") {
                        r = '<a href="#"  style="color: #145bae;text-decoration: none;"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="alert_jbxx_tx_tj_window(this ,' + index + ', \'合格案件\')">' + value + '</a> ';
                    } else {
                        r = value;
                    }
                    return r;
                }
            },
            {
                field: 'hgajZb',
                width:24,
                title: '<span  style=\'font-size:16px\'>占比(合格数/评查案件数)%</span>',
                align: 'center'
            },
            {
                field: 'xcajs',
                width:24,
                title: '<span  style=\'font-size:16px\'>瑕疵</span>',
                align: 'center',
                formatter: function (value, row, index) {
                    var r = '';
                    if (row.name!= "合计") {
                        r = '<a href="#"  style="color: #145bae;text-decoration: none;"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="alert_jbxx_tx_tj_window(this ,' + index + ', \'瑕疵案件\')">' + value + '</a> ';
                    } else {
                        r = value;
                    }
                    return r;
                }
            },
            {
                field: 'xcajZb',
                width:24,
                title: '<span  style=\'font-size:16px\'>占比(瑕疵数/评查案件数)%</span>',
                align: 'center'
            },
            {
                field: 'bhgajs',
                width:24,
                title: '<span  style=\'font-size:16px\'>不合格</span>',
                align: 'center',
                formatter: function (value, row, index) {
                    var r = '';
                    if (row.name!= "合计") {
                        r = '<a href="#"  style="color: #145bae;text-decoration: none;"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="alert_jbxx_tx_tj_window(this ,' + index + ', \'不合格案件\')">' + value + '</a> ';
                    } else {
                        r = value;
                    }
                    return r;
                }
            },
            {
                field: 'bhgajZb',
                width:24,
                title: '<span  style=\'font-size:16px\'>占比(不合格数/评查案件数)%</span>',
                align: 'center'
            }
        ]],
        loadFilter: function (result) {
            $("#btn_tx_expExcel").removeAttr("disabled");
            return result.code == 200 ? JSON.parse(result.data) : [];
        }
    });
}

// 加载评查模板
function load_cbt_win_eval_ajzlfx_pcmb() {

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
function data_monitor_overview_rd() {
    var obj = new Object();
    // obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    // if(obj.wcrqnf == ""){
    //     Alert("请选择年度!");
    //     return;
    // }
    obj.startDate = $('#tjfx_nd_wc_start').datebox('getValue');
    obj.endDate = $('#tjfx_nd_wc_end').datebox('getValue');
    obj.pcstartDate=$('#tjfx_nd_pc_start').datebox('getValue');
    obj.pcendDate = $('#tjfx_nd_pc_end').datebox('getValue');

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
    obj.ywtxmc = $('#cbt_win_eval_build_pcmb').combotree('getText').trim();
    if(obj.ywtx == ""){
        Alert("请选择评查模板!");
        return;
    }
    var cbrsfStr = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr , ",");
    if(obj.cbrsf == ""){
        Alert("请选择承办人身份!");
        return;
    }
    obj.ajtjlb = $('#tjfx_tx_cbt_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    $('#table_analysis_ajqkzlfx_tx').treegrid({
        url: getRootPath() + "/analysis/getAjzlqkfxByTx",
        method: 'post',
        queryParams: obj
    });
}


function ajzlqkfx_tx_expExcel(){
    var obj = new Object();
    // obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    // if(obj.wcrqnf == ""){
    //     Alert("请选择年度!");
    //     return;
    // }
    obj.startDate = $('#tjfx_nd_wc_start').datebox('getValue');
    obj.endDate = $('#tjfx_nd_wc_end').datebox('getValue');
    obj.pcstartDate=$('#tjfx_nd_pc_start').datebox('getValue');
    obj.pcendDate = $('#tjfx_nd_pc_end').datebox('getValue');

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
    obj.ywtxmc = $('#cbt_win_eval_build_pcmb').combotree('getText').trim();
    if(obj.ywtx == ""){
        Alert("请选择评查模板!");
        return;
    }
    var cbrsfStr = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr , ",");
    if(obj.cbrsf == ""){
        Alert("请选择承办人身份!");
        return;
    }
    obj.ajtjlb = $('#tjfx_tx_cbt_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    $.ajax({
        type: 'post',
        url: getRootPath() + '/analysis/getAjzlqkfxByTxToExcel',
        dataType: 'json',
        data: obj,
        success: function (result) {
            if (result.code == 200) {
                var path = getRootPath();
                window.location.href = path+ result.data;
            }
        },
        error: function (xhr) {
            Alert('导出案件质量情况条线分析错误\n\n' + xhr.responseText);
        }
    });
}

function alert_jbxx_tx_tj_window(el , index, pcjl) {
    var obj = new Object();
    var currField = $(el).attr("data-field");
    var jsonField = JSON.parse(currField);
    if(jsonField.pid == '-1'){
        obj.ywtx=jsonField.id;
    }else{
        obj.ywtx=jsonField.pid;
        obj.ajlbbm=jsonField.id;
    }
    // obj.wcrqnf = $('#monitor_handler_year_combotree').combotree('getValues').join(",").trim();
    obj.startDate = $('#tjfx_nd_wc_start').datebox('getValue');
    obj.endDate = $('#tjfx_nd_wc_end').datebox('getValue');
    obj.pcstartDate=$('#tjfx_nd_pc_start').datebox('getValue');
    obj.pcendDate = $('#tjfx_nd_pc_end').datebox('getValue');

    obj.dwbm = $('#monitor_handler_dw_combotree').combotree('getValues').join(",").trim();
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValues').join(",").trim();
    obj.cbrsf = $('#cbt_win_eval_build_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(obj.cbrsf, ",");
    obj.ajtjlb = $('#tjfx_tx_cbt_build_ajtjlb').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    obj.pcjl = pcjl;
    var ypcUrl = getRootPath() + "/analysis/getPcAjxxByParams";

    // 已评查案件datagrid
    $("#table_analysis_tx_filter").datagrid({
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
                        return ' <a href="#" onclick="pcWin_analysis_tx_pcyl(' + index + ')">查看</a>   ';
                    } else {
                        return '<a href="#" onclick="pcWinPage(' + index + ',\'#table_analysis_tx_filter\',0)">查看</a>';
                    }
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_analysis_tx_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_analysis_tx_filter").window('setTitle', '已经评查案件');
    $("#win_analysis_tx_filter").window('open');
}

function pcWin_analysis_tx_pcyl(index) {
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
    var datas=$('#table_analysis_tx_filter').datagrid('getRows')[index];
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