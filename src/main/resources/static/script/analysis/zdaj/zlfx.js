var ztqkcolumn=[[
    {field: 'id',title: '<span  style="font-size:14px">id</span>',hidden:true },
    {field: 'pid',title: '<span  style="font-size:14px">pid</span>',hidden:true},
    {field: 'name', title: '<span  style="font-size:14px">筛选规则</span>', width: 300, align: 'left'},
    {field: 'cbrcount',title: '<span  style="font-size:14px">1办案人数</span>',width: 70,align: 'center'},
    {field: 'bjcount',title: '<span  style=\'font-size:14px\'>2办结案件数</span>',width: 80, align: 'center'},
    {field: 'pcrcount',title: '<span  style=\'font-size:14px\'>3评查人员数</span>',width: 70,align: 'center'},
    {field: 'pccount',title: '<span  style=\'font-size:14px\'>4评查案件数</span>',width: 80,align: 'center',
        formatter: function (value, row, index) {
            var r= (row.name.indexOf("合计")>0||row.name=='合计') ?value:'<a href="#"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="alert_jbxx_tx_tj_window(this,' + index +')">'+value+'</a>';
            return r;
        }
    },
    {field: 'pcl',title: '<span  style=\'font-size:14px\'>评查比例（4/2）</span>',width: 100,align: 'center'},
    {field: 'cbrbpcl',title: '<span  style=\'font-size:14px\'>承办人平均被评查案件数（4/1）件</span>',width: 180,align: 'center'},
    {field: 'pcrpcl',title: '<span  style=\'font-size:14px\'>评查员平均评查案件数（4/3）件</span>',width: 180,align: 'center'}
]];

var zlfxcolumn=  [[
    {
        field: 'name',
        title: '<span  style=\'font-size:16px;\'>筛选规则</span>',
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
        align: 'center'
    }
], [
    {
        field: 'bjcount',
        width: 48,
        title: '<span  style=\'font-size:16px\'>办结数</span>',
        rowspan: 2,
        align: 'center',
        halign: 'center'
    },
    {
        field: 'pccount',
        title: '<span  style=\'font-size:16px\'>评查案件数</span>',
        width: 48,
        rowspan: 2,
        align: 'center'
    },
    {
        field: 'pcl',
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
        field: 'yzcount',
        width:24,
        title: '<span  style=\'font-size:16px\'>件数</span>',
        align: 'center',
        formatter: function (value, row, index) {
            var r = '';
            if (row.name.indexOf("合计")>0||row.name=='合计') {
                r = value;
            } else {
                r = '<a href="#"  style="color: #145bae;text-decoration: none;"  data-field={"id":"'+row.id+'","pid":"'+row.pid+'"} onclick="alert_jbxx_tx_tj_window(this ,' + index + ', \'优质案件\')">' + value + '</a> ';
            }
            return r;
        }
    },
    {
        field: 'yzpcl',
        width:24,
        title: '<span  style=\'font-size:16px\'>占比(优质数/评查案件数)%</span>',
        align: 'center'
    },
    {
        field: 'hgcount',
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
        field: 'hgpcl',
        width:24,
        title: '<span  style=\'font-size:16px\'>占比(合格数/评查案件数)%</span>',
        align: 'center'
    },
    {
        field: 'xccount',
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
        field: 'xcpcl',
        width:24,
        title: '<span  style=\'font-size:16px\'>占比(瑕疵数/评查案件数)%</span>',
        align: 'center'
    },
    {
        field: 'bhgcount',
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
        field: 'bhgpcl',
        width:24,
        title: '<span  style=\'font-size:16px\'>占比(不合格数/评查案件数)%</span>',
        align: 'center'
    }
]];

$(document).ready(function () {

    //查询工具初始化
    init_analysis_zdfx_tool();

    // 按钮事件绑定
    regisiter_analysis_zdfx__envent();

    // treedatagrid样式
    init_analysis_zdfx_treegrid();

});

function init_analysis_zdfx_treegrid(){
    var column;
    var index = getSelectTabIndex();
    if (index == 1){
        column = ztqkcolumn;
    }else if (index == 2){
        column = zlfxcolumn;
    }

    $('#table_analysis_zdaj').treegrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: false,
        treeField: 'name',
        animate: true,
        idField: 'id',
        columns: column,
        loadFilter: function (result) {
            $("#btn_tx_expExcel").removeAttr("disabled");
            return result.code == 200 ? result.data : [];
        }
    });
}

function regisiter_analysis_zdfx__envent() {
    //查询：
    $("#btn_analysis_zdaj_search").unbind('click');
    $("#btn_analysis_zdaj_search").bind('click', function () {
        load_analysis_zdaj_data();
    });
    //导出Excel
    $("#analysis_zdaj_expExcel").unbind('click');
    $("#analysis_zdaj_expExcel").bind('click', function () {
        analysis_zdaj_expExcel();
    });
}

function init_analysis_zdfx_tool() {

    //承办单位树
    $('#analysis_zdaj_dw').combotree({
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
                $('#analysis_zdaj_dw').combotree('setValue', data[0].id); //单位默认选择
                var root = $('#analysis_zdaj_dw').combotree('tree').tree('getRoot');
                var children = root.children
                var valueArr = new Array();
                valueArr.push(data[0].id)
                for (var i = 0; i < children.length; i++) {
                    valueArr.push(children[i].id);
                }
                $('#analysis_zdaj_dw').combotree("setValues", valueArr);
            }
            index_addMousedownDiv(this, "analysis_zdaj_dw");
        }
    });

// 评查日期
    $('#analysis_zdaj_pc_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#analysis_zdaj_pc_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });


    $('#analysis_zdaj_bj_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#analysis_zdaj_bj_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });


    //承办人身份
    $('#analysis_zdaj_cbrsf').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#analysis_zdaj_cbrsf', data);
            }
            index_addMousedownDiv(this, "analysis_zdaj_cbrsf");
        }
    });
    $('#analysis_zdaj_cbrsf').combotree("loadData", getCbrsfValues());

    //案件统计类别
    $('#analysis_zdaj_ajtjlb').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#analysis_zdaj_ajtjlb', data);
            }
            index_addMousedownDiv(this, "analysis_zdaj_ajtjlb");
        }
    });
    $('#analysis_zdaj_ajtjlb').combotree("loadData", getStajbs());

    // 筛选规则
    $('#analysis_zdaj_sxgz').combotree({
        method: 'get',
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/analysis/getSxgz',
        queryParams:{"pcflbm":'008'},
        loadFilter:function(data){
            return data.code == 200 ? JSON.parse(data.data) : [];
        },
        onLoadSuccess: function (node, data) {
           if (data != null && data.length >= 1){
              //       setAllCheckbox('#analysis_zdaj_ajtjlb', data);
              //       $('#analysis_zdaj_sxgz').combotree("setValue", data[0].id);
                index_addMousedownDiv(this, "zdaj_ztqk_sxgz");
            }
        },
        onSelect: function (node) {
            if (!node) {
                Alert("请选择筛选规则！");
                return;
            }
        }
    });


    var resizeTabHeight = $("#monitor_tabs_box").height() - 62;
    $("#monitor_tabs_box").find(".panel-body").height(resizeTabHeight);

}

// 常规抽查数据获取
function load_analysis_zdaj_data() {
    var obj = new Object();
    var dwbm = $('#analysis_zdaj_dw').combotree('getValues').join(",").trim();

    obj.pcstartDate=$('#analysis_zdaj_pc_start').datebox('getValue');
    obj.pcendDate = $('#analysis_zdaj_pc_end').datebox('getValue');

    obj.startDate = $('#analysis_zdaj_bj_start').datebox('getValue');
    obj.endDate = $('#analysis_zdaj_bj_end').datebox('getValue');


    if(dwbm == ""){
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = '008';

    var cbrsfStr = $('#analysis_zdaj_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = cbrsfStr;
    obj.ajtjlb = $('#analysis_zdaj_ajtjlb').combotree('getValues').join(",").trim();

    obj.gzbmj = $('#analysis_zdaj_sxgz').combotree('getValues').join(",");

    var index = getSelectTabIndex();
    if (index == 1){
        var url =getRootPath() + "/analysis/loadZdZtqk";
    }else if (index == 2){
        var url =getRootPath() + "/analysis/loadZdZlfx";
    }


    $('#table_analysis_zdaj').treegrid({
        url: url,
        method: 'post',
        queryParams: obj
    });
}


function analysis_zdaj_expExcel(){
    var obj = new Object();
    var dwbm = $('#analysis_zdaj_dw').combotree('getValues').join(",").trim();

    obj.pcstartDate=$('#analysis_zdaj_pc_start').datebox('getValue');
    obj.pcendDate = $('#analysis_zdaj_pc_end').datebox('getValue');

    obj.startDate = $('#analysis_zdaj_bj_start').datebox('getValue');
    obj.endDate = $('#analysis_zdaj_bj_end').datebox('getValue');


    if(dwbm == ""){
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = '008';

    var cbrsfStr = $('#analysis_zdaj_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = cbrsfStr;
    obj.ajtjlb = $('#analysis_zdaj_ajtjlb').combotree('getValues').join(",").trim();

    obj.gzbmj = $('#analysis_zdaj_sxgz').combotree('getValues').join(",");

    var index = getSelectTabIndex();
    if (index == 1){
        obj.exporttype = 'ztqk';
    }else if (index == 2){
        obj.exporttype = 'zlfx';
    }


    $.ajax({
        type: 'post',
        url: getRootPath() + '/analysis/exportZdFxExcel',
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

    obj.gzbmj = jsonField.id;

    obj.dwbm = $('#analysis_zdaj_dw').combotree('getValues').join(",").trim();
    obj.pcflbm = '008';

    obj.pcstartDate=$('#analysis_zdaj_pc_start').datebox('getValue');
    obj.pcendDate = $('#analysis_zdaj_pc_end').datebox('getValue');
    obj.startDate = $('#analysis_zdaj_bj_start').datebox('getValue');
    obj.endDate = $('#analysis_zdaj_bj_end').datebox('getValue');

    obj.cbrsf = $('#analysis_zdaj_cbrsf').combotree('getValues').join(",").trim();
    obj.ajtjlb = $('#analysis_zdaj_ajtjlb').combotree('getValues').join(",").trim();
    obj.pcjl = isNull(pcjl) ? "" : pcjl;
    var ypcUrl = getRootPath() + "/analysis/getZdAjJbxx";

    // 已评查案件datagrid
    $("#table_analysis_zdaj_filter").datagrid({
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
            {field: 'PCSLBM', title: '评查受理编码', hidden:true},
            {field: 'BMSAH', title: '部门受案号', width: 160},
            {field: 'AJMC', title: '案件名称', width: 160},
            {field: 'AJLB_MC', title: '案件类别', width: 90},
            {field: 'BPC_DWMC', title: '承办单位', width: 90},
            {field: 'BPC_MC', title: '承办检察官', width: 90},
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
                    return '<a href="#" onclick="pcWinPage(' + index + ',\'#table_analysis_zdaj_filter\',0)">查看</a>';
                }
            }
        ]],
        loadFilter:function(result){
            return result.code == 200 ? result.data :[];
        }

    });

    // 分页控件(中文)
    $('#table_analysis_zdaj_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_analysis_zdaj_filter").window('setTitle', '已经评查案件');
    $("#win_analysis_zdaj_filter").window('open');
}

