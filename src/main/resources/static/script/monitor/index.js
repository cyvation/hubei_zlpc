var dt;//评查单位树头
//页面初始化
$(document).ready(function () {

    // 权限控制，控制数据展示与否
    init_radio_moinitor_getMenu();

    //初始化easyUI控件
    init_moniter_random_EasyUiCom();

    //获取案件列表
    load_monitor_random_sjpc_filter();

});

// 根据参数显示不同权限菜单
// 监控权限控制, FUNCTION_PARAM形式如：{ "gncs": xxx, "qxcs": xxx}
// qxcs参数形式如：[{"key": "dwypc", "value": "3", "describle": "单位已评查"}]
function init_radio_moinitor_getMenu() {

    if (isAdministrator()) {
        $('.radio').css('display', 'block');
        $($('.ZXpcjks_radio').children('.radio')[0]).children('.redio_click').addClass('redio_click_no');
    } else {
        var thisGncsj = FUNCTION_PARAM.qxcs;
        // if (isNull(thisGncsj) || thisGncsj.length == 0) {
        //     Alert("无权限，请联系管理员");
        //     return false;
        // }
        // 遍历该用户具有的所有角色
        for (var i = 0; i < thisGncsj.length; i++) {
            // 解析并遍历角色功能参数
            var role = JSON.parse(thisGncsj[i]);
            for (var j = 0; j < role.length; j++) {
                switch (role[j].value) {
                    case '0': // 个人被评查
                        $("#grbpc_radio").css('display', 'block');
                        break;
                    case '1': //个人已评查
                        $("#grypc_radio").css('display', 'block');
                        break;
                    case '2': //部门已评查
                        $("#bmbpc_radio").css('display', 'block');
                        break;
                    case '3': // 单位已评查
                        $("#byypc_radio").css('display', 'block');
                        break;
                    default:
                        Alert("请联系管理员获取您的监控权限!");
                        $("#cbt_moniter_random_cx").attr('disabled', 'disable');
                        return false;
                }
            }
        }
    }

    // 修正样式
    var radioArr = $('.ZXpcjks_radio').children('.radio');
    var resultARR = [];
    var displayRadio = [];
    for (var i = 0; i < radioArr.length; i++) {
        var sf_hidden = $(radioArr[i]).is(':hidden');
        if (sf_hidden == true) {
            resultARR.push("*");
        } else {
            displayRadio.push(radioArr[i]);
        }
    }
    if (displayRadio.length <= 2) {
        $(".ZXpcjks_radio").css("width","130px");
    } else {
        $(".ZXpcjks_radio").css("width","240px");
    }
    if (displayRadio.length > 0) {
        $(displayRadio[0]).children('.redio_click').addClass('redio_click_no');//类型
    }
}

//初始化easyUI控件
function init_moniter_random_EasyUiCom() {
    //评查单位树
    $('#cbt_moniter_random_pcdw').combotree({
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
                $('#cbt_moniter_random_pcdw').combotree('setValue', data[0].id); //单位默认选择
                if ($(".redio_click_no").attr('data-value') == 3) {
                    $('#cbt_moniter_random_pcdw').combotree('enable');
                } else {
                    $('#cbt_moniter_random_pcdw').combotree('disable');
                }
            }
            index_addMousedownDiv(this,"cbt_moniter_random_pcdw");
        }
    });

    // 承办部门树：
    $("#cbt_moniter_random_dept").combotree({
        method: 'get',
        editable: false,
        panelWidth: 250,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        url: getRootPath() + '/filter/getAllBm',
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            index_addMousedownDiv(this,"cbt_moniter_random_dept");
        }
    });


    //评查方式树
    $('#cbt_moniter_random_pcfs').combotree({
        url: '',
        lines: true,
        multiple: true,
        cascadeCheck: false,
        editable: false,
        url: getRootPath() + '/manage/getpcfl',
        loadFilter: function (data) {
            var datas=[];
            for(var i=0;i<data.length;i++){
                if(data[i].id!='009'){//湖北：过滤线下评查
                    datas.push(data[i]);
                }
            }
            return datas
        },
        onLoadSuccess: function (node, data) {
            // if (data != null && data.length >= 1){
            //     var id = data[0].id;
            //     $('#cbt_moniter_random_pcfs').combotree('setValue', id); //评查方式默认选择
            // }
            index_addMousedownDiv(this,"cbt_moniter_random_pcfs");

        },
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onCheck: function (node, checked) {
            var selectedPcfs = $('#cbt_moniter_random_pcfs').combotree('getValues').join(",");//评查分类编码
            if (selectedPcfs.length == 3) { // 评查方式单选
                $('#cbt_moniter_random_cxgz').combotree({disabled: false});
                $("#cbt_moniter_random_cxgz").combotree({
                    method: 'get',
                    url: getRootPath() + '/filter/getSxgzMonitor',
                    queryParams: {
                        pcflbm: selectedPcfs
                    }
                });
            } else {
                $('#cbt_moniter_random_cxgz').combotree({disabled: true});
            }

        }
    });

    // 获取筛选规则列表
    $('#cbt_moniter_random_cxgz').combotree({
        method: 'get',
        lines: true,
        multiple: true,
        panelWidth: 270,
        url: getRootPath() + '/filter/getSxgzMonitor',
        queryParams: {
            pcflbm: "001",
        },
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data.length > 0) {
                //$('#cbt_moniter_random_cxgz').combotree('setValue',data[0].id);
            }
            index_addMousedownDiv(this,"cbt_moniter_random_cxgz");
        }

    });

    //评查结论树
    $('#cbt_moniter_random_pcjl').combotree({
        lines: true,
        multiple: true,
        cascadeCheck: false,
        editable: false,
        url: getRootPath() + '/filter/getPcjl',
        loadFilter: function (data) {
            if (data == null) {
                data = new Array();
            }
            /*var pcjlN = new Object();
             pcjlN.id = "1000000000001";
             pcjlN.text = "无问题";
             var attN = new Object();
             attN.PCJLBM = "1000000000001";
             attN.FBM = "-1";
             attN.PCJL = "无问题";
             pcjlN.attributes = attN;
             data.push(pcjlN);*/

            var pcjlY = new Object();
            pcjlY.id = "1000000000002";
            pcjlY.text = "有问题";
            var attY = new Object();
            attY.PCJLBM = "1000000000002";
            attY.FBM = "-1";
            attY.PCJL = "有问题";
            pcjlY.attributes = attY;
            data.push(pcjlY);

            return data;
        },
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {

            }
            index_addMousedownDiv(this,"cbt_moniter_random_pcjl");
        }
    });
    //评查状态树
    $('#cbt_moniter_random_pczt').combotree({
        lines: true,
        multiple: true,
        cascadeCheck: false,
        editable: false,
        url: getRootPath() + '/filter/getPczt',
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            /*if (data != null && data.length >= 1){
             }*/
            //$('#cbt_moniter_random_pczt').combotree('setValue', data.id);
            index_addMousedownDiv(this,"cbt_moniter_random_pczt");
        }
    });

    $('#cbt_moniter_random_pcy').textbox({});

    //承办检察官
    $('#cbt_moniter_random_cbjcg').textbox({});

    //案件名称
    $('#cbt_moniter_random_ajmc').textbox({});

    //完成日期
    $('#cbt_moniter_random_ksrq').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01',
        width: 130
    });

    $('#cbt_moniter_random_jsrq').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate()),
        width: 130
    });

    //radio点击事件
    // init_radio_monitor_Jsbm();
    $('.radio').click(function () {
        $(this).children(".redio_click").addClass('redio_click_no');
        $(this).siblings().children().removeClass('redio_click_no');
        if ($(".redio_click_no").attr('data-value') == 3) {
            $('#cbt_moniter_random_pcdw').combotree('enable');
        } else {
            $('#cbt_moniter_random_pcdw').combotree('setValue', dt);
            $('#cbt_moniter_random_pcdw').combotree('disable');
        }
        load_monitor_random_sjpc_filter();
    });

    //点击查询按钮事件
    $("#cbt_moniter_random_cx").unbind("click");
    $("#cbt_moniter_random_cx").bind("click", function () {
        load_monitor_random_sjpc_filter();
    });
//点击查询按钮事件
    $("#cbt_moniter_random_excel").unbind("click");
    $("#cbt_moniter_random_excel").bind("click", function () {
        load_monitor_random_sjpc_filter_excel();
    });
    init_table_monitor_Ajlb_DataGrid_NULL();

    $("#cbt_moniter_random_excel").unbind("click");
    $("#cbt_moniter_random_excel").bind("click", function () {
        load_monitor_random_sjpc_filter_excel();
    });
    // 分页控件(中文)
    $('#datagrid_easyui_monitor_random_sjpc').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    //选择分组时，自动查询事件
    $('#cbt_moniter_random_xzfzfs').combobox('setValue', 'BFZ');
    var item = $('#datagrid_easyui_monitor_random_sjpc').datagrid('getRows');

    $("#cbt_moniter_random_xzfzfs").combobox({
        onChange: function (newValue, oldValue) {
            var data = $('#datagrid_easyui_monitor_random_sjpc').datagrid('getData');
            $('#datagrid_easyui_monitor_random_sjpc').datagrid('loadData', []);
            if (isNull(newValue) || newValue == 'BFZ') {
                $('#datagrid_easyui_monitor_random_sjpc').datagrid('loadData', {total: 0, rows: []});
                init_table_monitor_Ajlb_DataGrid_NULL();
            } else {
                init_table_monitor_Ajlb_DataGrid(newValue);
            }
            $('#datagrid_easyui_monitor_random_sjpc').datagrid('loadData', data);
        }
    });
}

// 案件列表DataGrid初始化(当分组方式为空)
function init_table_monitor_Ajlb_DataGrid_NULL() {
    /*$('#datagrid_easyui_monitor_random_sjpc').datagrid('loadData',{total:0,rows:[]})*/

    $('#datagrid_easyui_monitor_random_sjpc').datagrid({
        //width: 'auto',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        pagination: true,
        rownumbers: true,
        fit: true,
        idField: 'PCSLBM',
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        multiSort: true,
        remoteSort: false,
        columns: [[
            {field: 'PCSLBM', title: '评查受理编码', hidden: true},
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCSAH', title: '评查案号', width: 180, sortable: true},
            {field: 'PCFLMC', title: '评查分类名称', width: 90, sortable: true},
            {
                field: 'AJMC', title: '案件名称', width: 160, sortable: true,
                formatter: function (value) {
                    return tipMessage(value);
                }
            },
            {field: 'BMSAH', title: '部门受案号', width: 160, sortable: true},
            {field: 'BPC_DWMC', title: '承办单位', width: 90, sortable: true},
            {field: 'BPC_BMMC', title: '承办部门', width: 90, sortable: true},
            {field: 'BPC_MC', title: '承办检察官', width: 90, sortable: true},
            {field: 'PCR_DWMC', title: '评查员单位', width: 90, sortable: true},
            {field: 'PCR_MC', title: '评查员', width: 90, sortable: true},
            {
                field: 'CJSJ', title: '评查日期', fixed: true, width: 115, sortable: true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter: function (a, b) {

                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]) {
                        if (a[0] == b[0]) {
                            return (a[1] > b[1] ? 1 : -1);
                        } else {
                            return (a[0] > b[0] ? 1 : -1);
                        }
                    } else {
                        return (a[2] > b[2] ? 1 : -1);
                    }

                },
            },
            {field: 'PCJDMS', title: '评查状态', width: 90, sortable: true},
            {field: 'SXGZMC', title: '筛选规则', width: 90, sortable: true},
            {field: 'PCJL', title: '评查结论', width: 90, sortable: true},
            {
                field: 'action', title: '操作', width: 80,
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="pcWinPage(' + index + ',\'#datagrid_easyui_monitor_random_sjpc\')">查看</a>'
                    return e;
                }
            }
        ]]
    });

    // 分页控件(中文)
    $('#datagrid_easyui_monitor_random_sjpc').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });
}

// 案件列表DataGrid初始化
function init_table_monitor_Ajlb_DataGrid(groupCol) {

    $('#datagrid_easyui_monitor_random_sjpc').datagrid({
        //width: 'auto',
        resizable: true,
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        pagination: true,
        rownumbers: true,
        fit: true,
        idField: 'PCSLBM',
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        multiSort: true,
        remoteSort: false,
        columns: [[
            {field: 'PCSLBM', title: '评查受理编码', hidden: true},
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCSAH', title: '评查案号', width: 180, sortable: true},
            {field: 'PCFLMC', title: '评查分类名称', width: 90, sortable: true},
            {
                field: 'AJMC', title: '案件名称', width: 160, sortable: true,
                formatter: function (value) {
                    return tipMessage(value);
                }
            },
            {field: 'BMSAH', title: '部门受案号', width: 160, sortable: true},
            {field: 'BPC_DWMC', title: '承办单位', width: 90, sortable: true},
            {field: 'BPC_BMMC', title: '承办部门', width: 90, sortable: true},
            {field: 'BPC_MC', title: '承办检察官', width: 90, sortable: true},
            {field: 'PCR_DWMC', title: '评查员单位', width: 90, sortable: true},
            {field: 'PCR_MC', title: '评查员', width: 90, sortable: true},
            {
                field: 'CJSJ', title: '评查日期', fixed: true, width: 115, sortable: true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter: function (a, b) {
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]) {
                        if (a[0] == b[0]) {
                            return (a[1] > b[1] ? 1 : -1);
                        } else {
                            return (a[0] > b[0] ? 1 : -1);
                        }
                    } else {
                        return (a[2] > b[2] ? 1 : -1);
                    }


                }
            },
            {field: 'PCJDMS', title: '评查状态', width: 90, sortable: true},
            {
                field: 'PCJL', title: '评查结论', width: 90, sortable: true,
                formatter: function (value) {
                    if (value == undefined || value == null || value == "") {
                        return "评查办理"
                    }
                    return value;
                }
            },
            {
                field: 'action', title: '操作', width: 80,
                formatter: function (value, row, index) {
                    // var e = '<a href="#" onclick="goto_case_handle_page(' + row.ID + ')">办理</a> ';
                    var e = '<a href="#" onclick="pcWinPage(' + index + ',\'#datagrid_easyui_monitor_random_sjpc\')">查看</a>'
                    return e;
                }
            }
        ]],
        groupField: groupCol,
        view: groupview,
        groupFormatter: function (value, rows) {
            if (value == undefined || value == null || value == "") {
                return "评查办理" + ' - ' + rows.length + ' 条';
            }
            return value + ' - ' + rows.length + ' 条';
        },
        onClickRow: function (rowIndex, rowData) {
            $('#datagrid_easyui_monitor_random_sjpc').datagrid('clearSelections');
            $('#datagrid_easyui_monitor_random_sjpc').datagrid('highlightRow', rowIndex);
        }
    });

    // 分页控件(中文)
    $('#datagrid_easyui_monitor_random_sjpc').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });
}

//获取案件列表（查询）
function load_monitor_random_sjpc_filter() {

    var obj = new Object();
    obj.PCDWBM = $('#cbt_moniter_random_pcdw').combotree('getValue') == undefined ? userInfo.DWBM : $('#cbt_moniter_random_pcdw').combotree('getValues').join(",");//评查单位编码
    obj.PCFLBM = $('#cbt_moniter_random_pcfs').combotree('getValues').join(",");//评查分类编码
    var tempPCJL = $('#cbt_moniter_random_pcjl').combotree('getText');
    var tempArr = tempPCJL.split(",");
    var tempStr = '';
    if (tempArr.length > 0 && tempPCJL) {
        for (var o in tempArr) {
            tempStr += "'" + tempArr[o] + "'" + ",";
        }
        tempStr = tempStr.substring(0, tempStr.length - 1);
        obj.PCJL = tempStr;
    }
    //obj.PCJL = $('#cbt_moniter_random_pcjl').combotree('getText');
    obj.PCZT = $('#cbt_moniter_random_pczt').combotree('getValues').join(",");//评查状态
    obj.sxgzbm = $('#cbt_moniter_random_cxgz').combotree('getValues').join(","); //筛选规则
    obj.PCY = $('#cbt_moniter_random_pcy').textbox('getValue');//评查员
    obj.CBR = $('#cbt_moniter_random_cbjcg').textbox('getValue');//承办检察官
    obj.WCRQBNG = $('#cbt_moniter_random_ksrq').datebox('getValue');//评查日期开始
    obj.WCRQEND = $('#cbt_moniter_random_jsrq').datebox('getValue');//评查日期结束
    obj.TYPE = $(".redio_click_no").attr('data-value');//类型
    obj.AJMC = $('#cbt_moniter_random_ajmc').textbox('getValue');//案件名称
    obj.bmbm = $("#cbt_moniter_random_dept").combotree('getValues').join(","); // 部门

    $('#datagrid_easyui_monitor_random_sjpc').datagrid({
        url: getRootPath() + '/filter/getPcjk',
        async: false,
        queryParams: {json: JSON.stringify(obj)}
    });

}
//获取案件列表（导出
function load_monitor_random_sjpc_filter_excel() {

    var obj = new Object();
    obj.PCDWBM = $('#cbt_moniter_random_pcdw').combotree('getValue') == undefined ? userInfo.DWBM : $('#cbt_moniter_random_pcdw').combotree('getValues').join(",");//评查单位编码
    obj.PCFLBM = $('#cbt_moniter_random_pcfs').combotree('getValues').join(",");//评查分类编码
    var tempPCJL = $('#cbt_moniter_random_pcjl').combotree('getText');
    var tempArr = tempPCJL.split(",");
    var tempStr = '';
    if (tempArr.length > 0 && tempPCJL) {
        for (var o in tempArr) {
            tempStr += "'" + tempArr[o] + "'" + ",";
        }
        tempStr = tempStr.substring(0, tempStr.length - 1);
        obj.PCJL = tempStr;
    }
    //obj.PCJL = $('#cbt_moniter_random_pcjl').combotree('getText');
    obj.PCZT = $('#cbt_moniter_random_pczt').combotree('getValues').join(",");//评查状态
    obj.sxgzbm = $('#cbt_moniter_random_cxgz').combotree('getValues').join(","); //筛选规则
    obj.PCY = $('#cbt_moniter_random_pcy').textbox('getValue');//评查员
    obj.CBR = $('#cbt_moniter_random_cbjcg').textbox('getValue');//承办检察官
    obj.WCRQBNG = $('#cbt_moniter_random_ksrq').datebox('getValue');//评查日期开始
    obj.WCRQEND = $('#cbt_moniter_random_jsrq').datebox('getValue');//评查日期结束
    obj.TYPE = $(".redio_click_no").attr('data-value');//类型
    obj.AJMC = $('#cbt_moniter_random_ajmc').textbox('getValue');//案件名称
    obj.bmbm = $("#cbt_moniter_random_dept").combotree('getValues').join(","); // 部门
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportPCmonitor',
        dataType: 'json',
        data: {json: JSON.stringify(obj)},
        success: function (result) {
            if (result.code == 200) {
                window.location.href =getRootPath()+ result.data;
            }
        },
        error: function (xhr) {
            Alert('导出评查监控出错\n\n' + xhr.responseText);
        }
    });

}


//获取案件列表（导出
function load_monitor_random_sjpc_filter_excel() {

    var obj = new Object();
    obj.PCDWBM = $('#cbt_moniter_random_pcdw').combotree('getValue') == undefined ? userInfo.DWBM : $('#cbt_moniter_random_pcdw').combotree('getValues').join(",");//评查单位编码
    obj.PCFLBM = $('#cbt_moniter_random_pcfs').combotree('getValues').join(",");//评查分类编码
    var tempPCJL = $('#cbt_moniter_random_pcjl').combotree('getText');
    var tempArr = tempPCJL.split(",");
    var tempStr = '';
    if (tempArr.length > 0 && tempPCJL) {
        for (var o in tempArr) {
            tempStr += "'" + tempArr[o] + "'" + ",";
        }
        tempStr = tempStr.substring(0, tempStr.length - 1);
        obj.PCJL = tempStr;
    }
    //obj.PCJL = $('#cbt_moniter_random_pcjl').combotree('getText');
    obj.PCZT = $('#cbt_moniter_random_pczt').combotree('getValues').join(",");//评查状态
    obj.sxgzbm = $('#cbt_moniter_random_cxgz').combotree('getValues').join(","); //筛选规则
    obj.PCY = $('#cbt_moniter_random_pcy').textbox('getValue');//评查员
    obj.CBR = $('#cbt_moniter_random_cbjcg').textbox('getValue');//承办检察官
    obj.WCRQBNG = $('#cbt_moniter_random_ksrq').datebox('getValue');//评查日期开始
    obj.WCRQEND = $('#cbt_moniter_random_jsrq').datebox('getValue');//评查日期结束
    obj.TYPE = $(".redio_click_no").attr('data-value');//类型
    obj.AJMC = $('#cbt_moniter_random_ajmc').textbox('getValue');//案件名称
    obj.bmbm = $("#cbt_moniter_random_dept").combotree('getValues').join(","); // 部门
    $.ajax({
        type: 'post',
        url: getRootPath() + '/count/exportPCmonitor',
        dataType: 'json',
        data: {json: JSON.stringify(obj)},
        success: function (result) {
            if (result.code == 200) {
                var path = getRootPath();
                window.location.href = path+ result.data;
            }
            //$('#transmit').window('close');
        },
        error: function (xhr) {
            Alert('导出评查监控出错\n\n' + xhr.responseText);
        }
    });

}

