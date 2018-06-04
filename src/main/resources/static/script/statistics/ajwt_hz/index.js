var cbdwbm;
$(document).ready(function () {

    // 样式初始化以及事件绑定
    init_statistics_analysis();
    // 数据加载--单位展示
    //data_monitor_statistiscs_dw();
    init_echarts();
});

//初始化柱状图
function init_echarts() {
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: [
                ['product', '错误', '瑕疵']
            ]
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [
            {
                type: 'bar', barMinHeight: 1, barGap: '10%', itemStyle: {
                normal: {
                    label: {
                        show: true,  //柱头数字
                        position: 'top',
                        textStyle: {
                            fontSize: '15',
                            fontFamily: '微软雅黑',
                            fontWeight: 'bold'
                        }
                    }
                }
            }
            },
            {
                type: 'bar', barMinHeight: 1, barGap: '10%', itemStyle: {
                normal: {
                    label: {
                        show: true,  //柱头数字
                        position: 'top',
                        textStyle: {
                            fontSize: '15',
                            fontFamily: '微软雅黑',
                            fontWeight: 'bold'
                        }
                    }
                }
            }
            }
        ]
    };
    myChart.on('click', function (params) {
        if (params.componentType === 'series') {
            if (params.seriesType === 'bar') {
                if (params.data.length == 5) {
                    var name = params.seriesName;
                    var count;
                    if(name == "错误"){
                        if(params.data[1] == 0){
                            return;
                        }
                        alert_bar_jbxx_ypc_ajwthz_window(params.data[3]);
                    }else if(name == "瑕疵"){
                        if(params.data[2] == 0){
                            return;
                        }
                        alert_bar_jbxx_ypc_ajwthz_window(params.data[4]);
                    }
                }
            }
        }
    });
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

// 加载评查分类
function load_cbt_win_eval_ajwt_pcfl() {

    $('#cbt_win_eval_ajwt_pcfl').combotree({
        method: 'get',
        lines: true,
        url: getRootPath() + '/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查
            if (data != null && data.length >= 1) {
                pcflbm = data[0].id;
                $('#cbt_win_eval_ajwt_pcfl').combotree('setValue', pcflbm);
                load_cbt_win_eval_ajwt_pcmb(pcflbm, data[0].attributes.SFJS);
            }

        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }
            // 加载对应评查模板
            load_cbt_win_eval_ajwt_pcmb(node.id, node.attributes.SFJS);
        }
    });
}

// 加载评查活动
function load_cbt_win_eval_build_pchd(pcflbm) {

    $('#cbt_win_eval_build_pchd').combotree('loadData', []);
    $('#cbt_win_eval_build_pchd').combotree('clear');
    $('#cbt_win_eval_build_pchd').combotree('setValue', '');

    $('#cbt_win_eval_build_pchd').combotree({
        method: 'get',
        lines: true,
        url: getRootPath() + '/manage/get_pchd',
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
function load_cbt_win_eval_ajwt_pcmb(pcflbm, sfjs) {

    $('#cbt_win_eval_ajwt_pcmb').combotree('loadData', []);
    $('#cbt_win_eval_ajwt_pcmb').combotree('clear');
    $('#cbt_win_eval_ajwt_pcmb').combotree('setValue', '');
    var url = "";
    if (sfjs == "Y" || pcflbm == "009") {
        url = getRootPath() + '/template/template';
    } else {
        url = getRootPath() + '/manage/get_pchd'
    }
    $('#cbt_win_eval_ajwt_pcmb').combotree({
        method: 'get',
        lines: true,
        url: url,
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess: function (node, data) {
            // 默认选中
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_ajwt_pcmb').combotree('setValue', data[0].id);
            }

        },
        onSelect: function (node) {

        }
    });

}

// 样式初始化：
function init_statistics_analysis() {

    // 单位tab
    init_monitor_statistiscs_dw();

}

// 单位tab样式以及事件：
function init_monitor_statistiscs_dw() {
    //加载评查分类
    load_cbt_win_eval_ajwt_pcfl();
    //加载组织机构单位编码
    load_cbt_win_eval_ajwt_zzjg_dw();

    //单位--评查时间
    $('#ajwt_dw_start').datebox({
        editable: false,
        // value: new Date().getFullYear() + '-01-01'
        value: '2013-01-01'
    });

    $('#ajwt_dw_end').datebox({
        editable: false,
        // value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
        value: '2017-12-31'
    });

    // 单位表格查询：
    $("#btn_ajwt_dw_search").unbind('click');
    $("#btn_ajwt_dw_search").bind('click', function () {
        data_monitor_statistiscs_dw();
    });
    var resizeTabHeight = $("#ajwt_tabsBox").height() - 62;

    // 单位表格datagrid
    $('#table_ajwt_statistiscs_dw').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field: 'pcxflfmc',
                title: '<span  style=\'font-size:16px;\'>项目</span>',
                rowspan: 2,
                width: 64,
                align: 'center',
                halign: 'center'
            },
            {
                title: '<span  style=\'font-size:16px;margin-top: 15px;display: inline-block;\'>具体内容及扣分标准</span>',
                colspan: 2
            },
            {
                field: 'wts',
                width: 100,
                title: '<span  style=\'font-size:16px\'>问题案件数</span>',
                rowspan: 2,
                align: 'center',
                halign: 'center',
                formatter: function (value, row, index) {
                    var r = '';
                    if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_ajwthz_window(' + index + ')">' + value + '</a> ';
                    } else {
                        r = value;
                    }
                    return r;
                }
            }
        ], [
            {
                field: 'pcxflmc',
                width: 64,
                title: '<span  style=\'font-size:16px\'>子项</span>',
                align: 'center',
                halign: 'center'
            },
            {
                field: 'pcxmc',
                title: '<span  style=\'font-size:16px\'>评查项内容</span>',
                width: 180,
                align: 'left'
            }
        ]],
        loadFilter: function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        },
        onLoadSuccess: function (data) {
            if (data.rows.length > 0) {
                //调用mergeCellsByField()合并单元格
                mergeCellsByField("table_ajwt_statistiscs_dw", "pcxflfmc,pcxflmc");
            }
        }

    });
}

function alert_jbxx_ypc_ajwthz_window(index) {
    var obj = new Object();
    obj.pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
    var ypcUrl = getRootPath();
    if (obj.pcflbm == '009') {//湖北2013年案件线下评查
        ypcUrl = ypcUrl + "/queryTable/getOfflineAjwthzjbxx";
    } else {
        ypcUrl = ypcUrl + "/queryTable/getAjwthzjbxx";
    }
    var thisRow = $('#table_ajwt_statistiscs_dw').datagrid('getRows')[index];
    obj.startDate = $('#ajwt_dw_start').datebox('getValue');
    obj.endDate = $("#ajwt_dw_end").datebox('getValue');

    obj.pcxflbm = thisRow.pcxflbm;
    obj.pcxbm = thisRow.pcxbm;
    obj.dwbmList = $('#cbt_win_eval_ajwt_dw').combotree('getValues').join(',');

    // 已评查案件datagrid
    $("#table_eval_build_statistics_ajwthz_filter").datagrid({
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
                field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
                    if (pcflbm == '009') {
                        return ' <a href="#" onclick="pcWinPage_offline(' + index + ',\'#table_eval_build_statistics_ajwthz_filter\')">查看</a>   ';
                    } else {
                        return '<a href="#" onclick="pcWinPage(' + index + ',\'#table_eval_build_statistics_ajwthz_filter\',0)">查看</a>';
                    }
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_eval_build_statistics_ajwthz_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_eval_build_statistics_ajwthz_filter").window('setTitle', '已经评查案件');
    $("#win_eval_build_statistics_ajwthz_filter").window('open');
}

function alert_bar_jbxx_ypc_ajwthz_window(pcxflbm) {
    var obj = new Object();
    obj.pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
    var ypcUrl = getRootPath();
    if (obj.pcflbm == '009') {//湖北2013年案件线下评查
        ypcUrl = ypcUrl + "/queryTable/getOfflineAjwthzjbxx";
    } else {
        ypcUrl = ypcUrl + "/queryTable/getAjwthzjbxx";
    }
    obj.startDate = $('#ajwt_dw_start').datebox('getValue');
    obj.endDate = $("#ajwt_dw_end").datebox('getValue');

    obj.pcxflbm = pcxflbm;
    obj.dwbmList = $('#cbt_win_eval_ajwt_dw').combotree('getValues').join(',');

    // 已评查案件datagrid
    $("#table_eval_build_statistics_ajwthz_filter").datagrid({
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
                field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
                    if (pcflbm == '009') {
                        return ' <a href="#" onclick="pcWinPage_offline(' + index + ',\'#table_eval_build_statistics_ajwthz_filter\')">查看</a>   ';
                    } else {
                        return '<a href="#" onclick="pcWinPage(' + index + ',\'#table_eval_build_statistics_ajwthz_filter\',0)">查看</a>';
                    }
                }
            }
        ]]
    });

    // 分页控件(中文)
    $('#table_eval_build_statistics_ajwthz_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_eval_build_statistics_ajwthz_filter").window('setTitle', '已经评查案件');
    $("#win_eval_build_statistics_ajwthz_filter").window('open');
}

//加载组织机构单位编码
function load_cbt_win_eval_ajwt_zzjg_dw() {
    $('#cbt_win_eval_ajwt_dw').combotree({
        method: 'get',
        lines: true,
        panelWidth: 250,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/organization/getDwbmTree',
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_ajwt_dw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this, "cbt_win_eval_ajwt_dw");
        }
    });
};

// 展示单位表格数据：
function data_monitor_statistiscs_dw() {
    var obj = new Object();
    obj.dwbm = $('#cbt_win_eval_ajwt_dw').combotree('getValues').join(',');
    obj.startDate = $('#ajwt_dw_start').datebox('getValue');
    obj.endDate = $("#ajwt_dw_end").datebox('getValue');
    obj.pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
    obj.page = 1;
    obj.rows = 1000;
    var tree = $('#cbt_win_eval_ajwt_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if (node.attributes.SFJS == "Y" || obj.pcflbm == "009") {
        obj.pcmbbm = $('#cbt_win_eval_ajwt_pcmb').combotree('getValue');
    } else {
        var tree = $("#cbt_win_eval_ajwt_pcmb").combotree("tree");
        var node = tree.tree("getSelected");
        obj.pcmbbm = node.attributes.PCMBJ;
    }
    var url = "";
    var ztUrl = "";
    if (obj.pcflbm != "009") { //pcflbm为009时，需要查询线下案件问题汇总
        url = getRootPath() + "/queryTable/getDwAjwthzTableData";
        ztUrl = getRootPath() + "/queryTable/getDwAjwthzBarData";
    } else {
        url = getRootPath() + "/queryTable/getDwOfflineAjwthzTableData";
        ztUrl = getRootPath() + "/queryTable/getOfflineDwAjwthzBarData";
    }

    $('#table_ajwt_statistiscs_dw').datagrid({
        url: url,
        method: 'post',
        queryParams: obj
    });

    $.ajax({
        type: 'POST',
        url: ztUrl,
        data: obj,
        dataType: "json",
        success: function (result) {
            var data = eval(result);
            if (data && typeof data === "object") {
                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                myChart.setOption(data);
            }
        }
    });


}


/**
 * EasyUI DataGrid根据字段动态合并单元格
 * 参数 tableID 要合并table的id
 * 参数 colList 要合并的列,用逗号分隔(例如："name,department,office");
 */
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var curPcxflfmc = "";
    var perPcxflfmc = "";
    for (j = ColArray.length - 1; j >= 0; j--) {
        PerTxt = "";
        perPcxflfmc = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
                curPcxflfmc = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
                curPcxflfmc = tTable.datagrid("getRows")[i]["pcxflfmc"];
            }
            if (PerTxt == CurTxt && curPcxflfmc == perPcxflfmc) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;

                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],　　//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
                tmpA = 1;
            }
            PerTxt = CurTxt;
            perPcxflfmc = curPcxflfmc;
        }
    }
}

function export_excel_ajwthz() {
    var obj = new Object();
    obj.dwbm = $('#cbt_win_eval_ajwt_dw').combotree('getValues').join(',');
    obj.startDate = $('#ajwt_dw_start').datebox('getValue');
    obj.endDate = $("#ajwt_dw_end").datebox('getValue');
    obj.pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
    obj.page = 1;
    obj.rows = 1000;
    var tree = $('#cbt_win_eval_ajwt_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if (node.attributes.SFJS == "Y" || obj.pcflbm == "009") {
        obj.pcmbbm = $('#cbt_win_eval_ajwt_pcmb').combotree('getValue');
    } else {
        var tree = $("#cbt_win_eval_ajwt_pcmb").combotree("tree");
        var node = tree.tree("getSelected");
        obj.pcmbbm = node.attributes.PCMBJ;
    }
    var url = "";
    if (obj.pcflbm != "009") { //pcflbm为009时，需要查询线下案件问题汇总
        url = getRootPath() + "/queryTable/exportDwAjwthzTableDataExcel";
    } else {
        url = getRootPath() + "/queryTable/getDwOfflineAjwthzTableDataExcel";
    }

    $.ajax({
        url: url,
        type: "get",
        data: obj,
        success: function (data) {
            if (data.code == 200) {
                window.location.href = getRootPath() + data.data;
            }
            CloseProgress();
        },
        error: function () {
            CloseProgress();
        }
    });
}

function pcWinPage_offline(index, id, type) {
    var rowDatas = $(id).datagrid('getRows')[index];
    if (type == '' || type == null || type == undefined) {
        type = 0;
    }
    var obj = new Object();
    obj.PCSLBM = rowDatas.PCSLBM
    obj.BMSAH = rowDatas.BMSAH;
    obj.DWBM = rowDatas.PCDWBM;
    obj.PCCZLX = '0'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = ''; //仅评查审批阶段有
    obj.type = 0;
    var url = "view/evaluate/build/offline.html";
    loadPcblWin_offline(url, obj);
}

function loadPcblWin_offline(href, param) {
    // 换从功能参数，作为参数传递
    FUNCTION_PARAM = param;
    $('#pcblWin').window('open').window('refresh', href);
}





