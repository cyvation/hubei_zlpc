var cbdwbm;
$(document).ready(function () {

    // 样式初始化以及事件绑定
    init_statistics_analysis();

    // 数据加载--单位展示
    //data_monitor_statistiscs_dw();
});

// 加载评查分类
function load_cbt_win_eval_ajwt_pcfl() {

    $('#cbt_win_eval_ajwt_pcfl').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1){
                pcflbm = data[0].id;
                $('#cbt_win_eval_ajwt_pcfl').combotree('setValue', pcflbm);

                $('#pop_ups_win_input_ajwt_pchd').show();
                $('#pop_ups_win_input_ajwt_pcmb').hide();
                load_cbt_win_eval_build_pchd(pcflbm);
            }

        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }

            // 获取选中节点自定义数据
            if (node.attributes.SFJS == "Y") {

                $('#pop_ups_win_input_ajwt_pcmb').show();
                $('#pop_ups_win_input_ajwt_pchd').hide();
                // 加载对应评查模板
                load_cbt_win_eval_ajwt_pcmb(node.id);
            }else {
                $('#pop_ups_win_input_ajwt_pchd').show();
                $('#pop_ups_win_input_ajwt_pcmb').hide();
                // 加载对应评查活动
                load_cbt_win_eval_build_pchd(node.id);
            }

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
function load_cbt_win_eval_ajwt_pcmb(pcflbm) {

    $('#cbt_win_eval_ajwt_pcmb').combotree('loadData',[]);
    $('#cbt_win_eval_ajwt_pcmb').combotree('clear');
    $('#cbt_win_eval_ajwt_pcmb').combotree('setValue', '');

    $('#cbt_win_eval_ajwt_pcmb').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/template/template',
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess: function (node,data) {
            // 专项默认选中
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
function init_monitor_statistiscs_dw(){
    //加载评查分类
    load_cbt_win_eval_ajwt_pcfl();
    //加载组织机构单位编码
    load_cbt_win_eval_ajwt_zzjg_dw();

    //单位--评查时间
    $('#ajwt_dw_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#ajwt_dw_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });

    // 单位表格查询：
    $("#btn_ajwt_dw_search").unbind('click');
    $("#btn_ajwt_dw_search").bind('click', function () {
        data_monitor_statistiscs_dw();
    });
    var resizeTabHeight = $("#ajwt_tabsBox").height() - 62;
}

//加载组织机构单位编码
function load_cbt_win_eval_ajwt_zzjg_dw(){
    $('#cbt_win_eval_ajwt_dw').combotree({
        method: 'get',
        lines: true,
        panelWidth:250,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_ajwt_dw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,"cbt_win_eval_ajwt_dw");
        }
    });
};

// 展示单位表格数据：
function data_monitor_statistiscs_dw() {

    var obj = new Object();
    obj.dwbm =$('#cbt_win_eval_ajwt_dw').combotree('getValue');
    obj.startDate = $('#ajwt_dw_start').datebox('getValue');
    obj.endDate = $("#ajwt_dw_end").datebox('getValue');
    obj.pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
    obj.page = 1;
    obj.rows = 1000;
    var tree = $('#cbt_win_eval_ajwt_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if(node.attributes.SFJS == "Y"){
        obj.pcmbbm = $('#cbt_win_eval_ajwt_pcmb').combotree('getValue');
    }else{
        obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
        obj.pcmbbm = $('#cbt_win_eval_ajwt_pcmb').combotree('getValue');
    }

    // 单位表格datagrid
    $('#table_ajwt_statistiscs_dw').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        url: getRootPath() + "/queryTable/getDwAjwthzTableData",
        method:'post',
        queryParams:obj,
        columns: [[
            {
                field: 'pcxflfmc',
                title: '<span  style=\'font-size:22px;\'>项目</span>',
                rowspan: 2,
                width: 64,
                align: 'center',
                halign: 'center'
            },
            {
                title: '<span  style=\'font-size:22px;margin-top: 15px;display: inline-block;\'>具体内容及扣分标准</span>',
                colspan: 2
            },
            {
                field: 'wts',
                width: 100,
                title: '<span  style=\'font-size:22px\'>问题数</span>',
                rowspan: 2,
                align: 'center',
                halign: 'center'
            }], [
            {
                field: 'pcxflmc',
                width:64,
                title: '<span  style=\'font-size:22px\'>子项</span>',
                align: 'center',
                halign: 'center'
            },
            {
                field: 'pcxmc',
                title: '<span  style=\'font-size:22px\'>评查项内容</span>',
                width:220,
                align: 'left'
            }
        ]],
        loadFilter:function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        },
        onLoadSuccess:function (data) {
            if (data.rows.length > 0) {
                //调用mergeCellsByField()合并单元格
                mergeCellsByField("table_ajwt_statistiscs_dw", "pcxflfmc,pcxflmc");
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
    var curPcxflfmc ="";
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
            if (PerTxt == CurTxt && curPcxflfmc== perPcxflfmc) {
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

// 已经评查案件（type对应0，1,2分别是单位，部门，检察官表）
function alert_jbxx_ypc_window(el,type, index) {

    var obj = new Object();
    var ypcUrl = getRootPath() ;

    // 判断哪个表：
    if (type == 0) {
       var thisRow = $('#table_ajwt_statistiscs_dw').datagrid('getRows')[index];
        obj.startDate = $('#ajwt_dw_start').datebox('getValue');
        obj.endDate = $("#ajwt_dw_end").datebox('getValue');
        obj.dwbmList = thisRow.dwbm;

        ypcUrl += "/queryTable/getAjjbxx";
    }

    if (type == 1) {
        var thisRow = $('#table_monitor_statistiscs_bm').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_bm_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_bm_end").datebox('getValue');
        obj.dwbmList = $("#monitor_handler_dw_combotree").combotree('getValue');
        obj.bmbmList =thisRow.bmbm;

        ypcUrl += "/queryTable/getAjjbxxByBm";
    }

    if (type == 2) {
        var thisRow = $('#table_monitor_statistiscs_stuff').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_stuff_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_stuff_end").datebox('getValue');
        obj.dwbmList = $("#monitor_handler_stuff_dw_combotree").combotree('getValue');
        obj.bmbmList =$("#monitor_handler_stuff_bm_combotree").combotree('getValue');
        obj.ghList = thisRow.gh;

        ypcUrl += "/queryTable/getAjjbxxByJcg";
    }

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
    }
    if (!isNull(jsonField.pcjl)) {
        obj.pcjl = jsonField.pcjl;
    }

    // 已评查案件datagrid
    $("#table_eval_ajwt_statistics_analysis_filter").datagrid({
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
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_ajwt_statistics_analysis_filter\',0)">查看</a>';
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_eval_ajwt_statistics_analysis_filter').datagrid('getPager').pagination({
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
    if (type ==0) {
        var  thisRow = $('#table_ajwt_statistiscs_dw').datagrid('getRows')[index];
        obj.startDate = $('#ajwt_dw_start').datebox('getValue');
        obj.endDate = $("#ajwt_dw_end").datebox('getValue');
        obj.dwbmList = thisRow.dwbm;
        obj.pcflbm = $('#cbt_win_eval_ajwt_pcfl').combotree('getValue');
        var tree = $('#cbt_win_eval_ajwt_pcfl').combotree('tree');	// 获取树对象
        var node = tree.tree('getSelected');		// 获取选择的节点
        if(node.attributes.SFJS == "Y"){
            obj.pcmbbm = $('#cbt_win_eval_ajwt_pcmb').combotree('getValue');
        }else{
            obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
        }
    }

    if (type == 1) {
        var thisRow = $('#table_monitor_statistiscs_bm').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_bm_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_bm_end").datebox('getValue');
        obj.dwbmList = thisRow.dwbm;
    }

    if (type == 2) {
        var thisRow = $('#table_monitor_statistiscs_stuff').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_stuff_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_stuff_end").datebox('getValue');
        obj.dwbmList = $("#monitor_handler_stuff_dw_combotree").combotree('getValue');
        obj.bmbmList =$("#monitor_handler_stuff_bm_combotree").combotree('getValue');
        obj.ghList = thisRow.gh;
    }

    $('#table_eval_ajwt_statistics_analysis_filter').datagrid({
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
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_ajwt_statistics_analysis_filter\',1)">查看</a>'
                    return r;
                }
            }
        ]]
    });

    $('#table_eval_ajwt_statistics_analysis_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 展示已审结未评查案件信息
    $("#win_eval_ajwt_statistics_analysis_filter").window('setTitle','已经审结案件');
    $("#win_eval_ajwt_statistics_analysis_filter").window('open');


}





