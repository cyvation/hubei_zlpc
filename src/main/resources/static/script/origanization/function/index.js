/**
 * 功能模块(新系统)
 */
var current_select_func_fflbm = '0'; // 当前选中父分类编码
var current_select_func_gnflbm = '0'; // 当前选中功能分类
var current_select_func_gnflmc; // 当前选中功能分类

var userInfo;//当前登录用户信息

// 入口
$(document).ready(function () {

    // 界面标签样式设置及事件绑定
    org_func_marksInit();

    // 标签初始化数据加载
    org_func_marksDataBind();
});

// 界面标签样式设置及事件绑定
function org_func_marksInit() {
    $('#btn_func_gnfl_edit').linkbutton('disable');
    $('#btn_func_gnfl_remove').linkbutton('disable');
    // $('#btn_func_gndy_add').linkbutton('disable');

    // 功能分类树初始化
    $('#tree_func_gnfl').tree({
        url: getRootPath()+'/modulefunction/GetGnflCombTree?dwbm=' + userInfo.DWBM,
        method: 'post',
        lines: true,
        loadMsg: '数据加载中，请稍后...',
        loadFilter: function (data) {
            return data.code==200?data.data:[];
        },
        onDblClick: function () {
            if ($('#tree_func_gnfl').tree('getSelected').state == "open") {
                $('#tree_func_gnfl').tree('collapse', $('#tree_func_gnfl').tree('getSelected').target);
            }
            else {
                $('#tree_func_gnfl').tree('expand', $('#tree_func_gnfl').tree('getSelected').target);
            }
        },
        onSelect: function (node) {
            var parent = $('#tree_func_gnfl').tree('getParent', node.target);
            if (parent && parent.id) {
                current_select_func_fflbm = parent.id;
            }
            if (!node && !node.id) {
                return true;
            }
            if (node.children) {//有子节点的不能删
                $('#btn_func_gnfl_remove').linkbutton('disable');
            } else {
                $('#btn_func_gnfl_remove').linkbutton('enable');
            }
            current_select_func_gnflbm = node.id;
            current_select_func_gnflmc = node.text;
            if ('0' == current_select_func_gnflbm) {//选中根节点(虚拟节点)
                $('#btn_func_gnfl_edit').linkbutton('disable');
                $('#btn_func_gndy_add').linkbutton('disable');
            } else {
                $('#btn_func_gnfl_edit').linkbutton('enable');
                $('#btn_func_gndy_add').linkbutton('enable');
            }

            //右侧显示对应该分类的功能定义列表
            load_org_func_gndy_grid();

        },
        onLoadSuccess: function () {
            var node = $('#tree_func_gnfl').tree('find', current_select_func_gnflbm);
            if (node && node.id) {
                $('#tree_func_gnfl').tree('expandTo', node.target);
                $('#tree_func_gnfl').tree('select', node.target);
            }
        }
    });

    // 显示功能信息的grid
    $('#dg_func_gndy').datagrid({
        width: 'auto',
        striped: true,
        singleSelect: false,
        fitColumns: true,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        onClickRow: function (rowIndex, rowData) {
            $('#dg_func_gndy').datagrid('clearSelections');
            $('#dg_func_gndy').datagrid('highlightRow', rowIndex);
        },
        columns: [[
            {field: 'GNMC', title: '功能名称', width: 140},
            {field: 'GNCT', title: '功能窗体', width: 230},
            {field: 'GNXH', title: '序号', width: 40, align: 'center'},
            {field: 'GNCXJ', title: '功能图标', width: 120},
            {field: 'GNXSMC', title: '显示名称', width: 140},
            {field: 'GNBM', title: '功能编码', width: 90},
            {field: 'FLMC', title: '功能分类', width: 120},
            {field: 'FLBM', title: '父类编码',  hidden: true},
            {field: 'GNSM', title: '说明', width: 60},
            {
                field: 'action', title: '操作', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="edit_org_func_gndy_row(' + index + ')">编辑</a> ';
                    var d = '<a href="#" onclick="delete_org_func_gndy_row(' + index + ')">删除</a>';
                    return e + d;
                }
            }
        ]]
    });

    // 功能分类弹出窗口中的功能分类选中框
    $('#txt_win_func_gnfl_ffl_cbt').combotree({
        url: getRootPath()+'/modulefunction/GetGnflCombTree?dwbm=' + userInfo.DWBM,
        valueField: 'id',
        textField: 'text',
        loadFilter: function (data) {
            return data.code==200?data.data:[];
        },
        onLoadSuccess: function () {
            var node = $('#txt_win_func_gnfl_ffl_cbt').combotree('tree').tree('find', current_select_func_fflbm);
            if (node && node.id) {//展开到父分类
                $('#txt_win_func_gnfl_ffl_cbt').combotree('tree').tree('expandTo', node.target);
            }
        }
    });

    // 添加功能权限窗口
    $('#win_func_gndy_manage').window({
        modal: true,
        maximizable: false,
        minimizable: false,
        closed: true,
        collapsible: false,
        title: '添加功能权限'
    });

    // 重置功能定义信息的高度
    resize_org_func_dg_gndy_height();

}

// 标签初始化数据加载
function org_func_marksDataBind() {

    // 初始化功能分类编辑弹出窗
    init_win_org_func_gnfl_manage();

    // 初始化功能定义编辑弹出窗
    init_win_org_func_gndy_manage();

}

// 功能分类管理
function init_win_org_func_gnfl_manage() {
    // 功能分类的【删除】按钮
    $('#btn_func_gnfl_remove').linkbutton({
        onClick: function () {
            Confirm('确认', '确定删除【' + current_select_func_gnflmc + '】？', function (r) {
                if (r) {
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/modulefunction/DeleteGnfl?dwbm=' + userInfo.DWBM + '&flbm=' + current_select_func_gnflbm,
                        dataType: "json",
                        success: function (result) {
                            if (parseInt(0) == parseInt(result.data.gndy_count) && isNull(result.data.errMsg)) {
                                // 展开时选中上级节点
                                current_select_func_gnflbm = current_select_func_fflbm;
                                $('#tree_func_gnfl').tree('reload');
                                Alert(result.message);
                            } else {
                                Alert('删除失败！<br>该分类下有功能定义。');
                            }
                        }
                    });
                }
            });
        }
    });

    // 功能分类的【新增分类】按钮
    $('#btn_func_gnfl_add').linkbutton({
        onClick: function () {
            $('#win_func_gnfl_manage').window('setTitle', '添加功能分类');
            $('#txt_win_func_gnfl_flmc').textbox('setValue', '');
            $('#txt_win_func_gnfl_flxh').numberbox('setValue', 0);
            $('#txt_win_func_gnfl_icon').textbox('setValue', '');
            $('#txt_win_func_gnfl_ffl_cbt').combotree('setValue', current_select_func_gnflbm);

            // 添加功能分类 -- 按钮：取消
            $('#btn_win_func_gnfl_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_func_gnfl_manage').window('close');
                }
            });

            // 添加功能分类 -- 按钮：确定
            $('#btn_win_func_gnfl_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    var add_fflbm = $('#txt_win_func_gnfl_ffl_cbt').combotree('getValue');
                    var obj = new Object();
                    if ('0' != add_fflbm) {
                        obj.fflbm = add_fflbm;
                    }
                    obj.flmc = $('#txt_win_func_gnfl_flmc').textbox('getValue');
                    obj.flxh = $('#txt_win_func_gnfl_flxh').numberbox('getValue');
                    obj.icon = $('#txt_win_func_gnfl_icon').textbox('getValue');
                    obj.dwbm = userInfo.DWBM;
                    if (isNull(obj.flmc) || isNull(obj.flxh)) {
                        Alert("分类名称或分类序号不能为空！");
                        return;
                    }
                    $('#btn_win_func_gnfl_confirm').linkbutton('disable');
                    // 异步提交 添加的功能分类信息
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/modulefunction/AddGnfl',
                        data: obj,
                        dataType: "json",
                        success: function (result) {
                            Alert("功能分类添加成功！");
                            $('#win_func_gnfl_manage').window('close');
                            $('#tree_func_gnfl').tree('reload');
                            $('#txt_win_func_gnfl_ffl_cbt').combotree('reload');
                            resize_org_func_dg_gndy_height();
                        }
                    });
                    $('#btn_win_func_gnfl_confirm').linkbutton('enable');
                }
            });
            $('#win_func_gnfl_manage').window('open');
        }
    });

    // 功能分类的 【编辑分类】按钮
    $('#btn_func_gnfl_edit').linkbutton({
        onClick: function () {
            $('#win_func_gnfl_manage').window('setTitle', '编辑功能分类');
            // 功能分类信息
            $.ajax({
                type: 'GET',
                url: getRootPath()+'/modulefunction/GetGnflInfo?dwbm=' + userInfo.DWBM + '&flbm=' + current_select_func_gnflbm,
                dataType: "json",
                success: function (result) {
                    doActionWithErrorHandle(result, function (data) {
                        if (isNull(result.data[0].fflbm)) {
                            current_select_func_fflbm = '0';
                        } else {
                            current_select_func_fflbm = result.data[0].fflbm;
                        }
                        $('#txt_win_func_gnfl_ffl_cbt').combotree({
                            onSelect: function (node) {
                                if (node.id == result.data[0].flbm) {//选中的父分类与自身分类相同时
                                    $('#txt_win_func_gnfl_ffl_cbt').combotree('setValue', current_select_func_fflbm);
                                } else {
                                    current_select_func_fflbm = node.id;
                                    $('#txt_win_func_gnfl_ffl_cbt').combotree('setValue', node.id);
                                }
                            }
                        });
                        $('#txt_win_func_gnfl_ffl_cbt').combotree('setValue', current_select_func_fflbm);
                        $('#txt_win_func_gnfl_flmc').textbox('setValue', result.data[0].flmc);
                        $('#txt_win_func_gnfl_flxh').numberbox('setValue', result.data[0].flxh);
                        $('#txt_win_func_gnfl_icon').textbox('setValue', result.data[0].icon);

                    });
                }
            });
            // 编辑功能分类 -- 按钮：取消
            $('#btn_win_func_gnfl_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_func_gnfl_manage').window('close');
                }
            });
            // 编辑功能分类 -- 按钮：确定
            $('#btn_win_func_gnfl_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    var obj = new Object();
                    var win_select_fflbm = $('#txt_win_func_gnfl_ffl_cbt').combotree('getValue');

                    if ('0' != win_select_fflbm) {
                        obj.fflbm = win_select_fflbm;
                    }
                    obj.flbm = current_select_func_gnflbm;
                    obj.flmc = $('#txt_win_func_gnfl_flmc').textbox('getValue');
                    obj.flxh = $('#txt_win_func_gnfl_flxh').numberbox('getValue');
                    obj.icon = $('#txt_win_func_gnfl_icon').textbox('getValue');
                    obj.dwbm = userInfo.DWBM
                    if (isNull(obj.flmc) || isNull(obj.flxh)) {
                        Alert("分类名称或分类序号不能为空！");
                        return true;
                    }

                    $('#btn_win_func_gnfl_confirm').linkbutton('disable');
                    // 异步提交 编辑的功能分类信息
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/modulefunction/AddGnfl',
                        data: obj,
                        dataType: "json",
                        success: function (result) {

                            Alert("功能分类修改成功！");
                            $('#win_func_gnfl_manage').window('close');
                            // 刷新功能分类树
                            $('#tree_func_gnfl').tree('reload');
                            resize_org_func_dg_gndy_height();

                        }
                    });
                    $('#btn_win_func_gnfl_confirm').linkbutton('enable');
                }
            });
            $('#win_func_gnfl_manage').window('open');
        }
    });
}

// 功能定义管理
function init_win_org_func_gndy_manage() {
    //功能定义刷新按钮
    $('#btn_func_gndy_refresh').linkbutton({
        onClick: function () {
            $('#dg_func_gndy').datagrid('reload');
            resize_org_func_dg_gndy_height();
        }
    });
    // 功能定义新增按钮
    $('#btn_func_gndy_add').linkbutton({
        onClick: function () {
            clear_win_org_func_gndy();
            $('#txt_win_func_gndy_flmc').textbox('setValue', current_select_func_gnflmc);
            $('#txt_win_func_gndy_gnxh').numberbox('setValue', 0);
            $('#win_func_gndy_manage').window('setTitle', '添加功能定义');
            // 添加功能定义 -- 按钮：取消
            $('#btn_win_func_gndy_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_func_gndy_manage').window('close');
                }
            });
            // 添加功能定义 -- 按钮：确定
            $('#btn_win_func_gndy_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    var obj = new Object();
                    obj.flbm = current_select_func_gnflbm;
                    obj.gnmc = $('#txt_win_func_gndy_gnmc').textbox('getValue');
                    obj.gnct = $('#txt_win_func_gndy_gnct').textbox('getValue');
                    obj.gnxsmc = $('#txt_win_func_gndy_gnxsmc').textbox('getValue');
                    obj.gnxh = $('#txt_win_func_gndy_gnxh').numberbox('getValue');
                    obj.gncxj = $('#txt_win_func_gndy_gncxj').textbox('getValue');
                    // obj.GNCS = $('#txt_win_func_gndy_gncs').textbox('getValue');
                    // obj.CSCS = $('#txt_win_func_gndy_cscs').textbox('getValue');
                    obj.gnsm = $('#txt_win_func_gndy_gnsm').textbox('getValue');
                    obj.dwbm = userInfo.DWBM;
                    if (isNull(obj.gnmc) || isNull(obj.gnxh)) {
                        Alert("功能名称或者序号不能为空！");
                        return true;
                    }
                    $('#btn_win_func_gndy_confirm').linkbutton('disable');
                    // 异步提交 添加的功能定义信息
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/modulefunction/AddGndy',
                        data: obj,
                        dataType: "json",
                        success: function (result) {
                            // 对一般处理程序返回的数据，进行错误处理及数据过滤

                                Alert(result.message);
                                $('#win_func_gndy_manage').window('close');
                                // 刷新功能定义表格
                                $('#dg_func_gndy').datagrid('reload');
                                resize_org_func_dg_gndy_height();

                        }
                    });
                    $('#btn_win_func_gndy_confirm').linkbutton('enable');
                }
            });
            $('#win_func_gndy_manage').window('open');
        }
    });


}

// 载入功能权限grid数据
function load_org_func_gndy_grid() {
    var gnfl = $('#tree_func_gnfl').tree('getSelected');
    $('#dg_func_gndy').datagrid({
        url: getRootPath()+'/modulefunction/GetGndyList?flbm=' + gnfl.id,
        loadFilter: function (data) {
            return data.code==200?JSON.parse(data.data):[];
        },
    });
}

// 清空添加功能权限的窗体数据
function clear_win_org_func_gndy() {
    $('#txt_win_func_gndy_gnmc').textbox('setValue', '');
    $('#txt_win_func_gndy_gnct').textbox('setValue', '');
    $('#txt_win_func_gndy_gnxsmc').textbox('setValue', '');
    $('#txt_win_func_gndy_gnxh').textbox('setValue', '');
    $('#txt_win_func_gndy_gncxj').textbox('setValue', '');
    // $('#txt_win_func_gndy_gncs').textbox('setValue', '');
    // $('#txt_win_func_gndy_cscs').textbox('setValue', '');
    $('#txt_win_func_gndy_gnsm').textbox('setValue', '');
}

// 功能定义表格行 -- 操作：编辑
function edit_org_func_gndy_row(index) {

    // 设置窗口标题
    $('#win_func_gndy_manage').window('setTitle', '编辑功能定义');

    var rowDatas = $('#dg_func_gndy').datagrid('getRows');

    // 将信息显示到编辑界面
    var gnmc = rowDatas[index].GNMC;
    var gnsm = rowDatas[index].GNSM;
    var gnct = rowDatas[index].GNCT;
    var gnxh = rowDatas[index].GNXH;
    var gncxj = rowDatas[index].GNCXJ;
    var gnxsmc = rowDatas[index].GNXSMC;
    var gnbm = rowDatas[index].GNBM;
    var flbm = rowDatas[index].FLBM;
    var flmc = rowDatas[index].FLMC;
    // var flbm = current_select_func_gnflbm;
    // var gncs = rowDatas[index].GNCS;
    // var cscs = rowDatas[index].CSCS;

    // $('#txt_win_func_gndy_flmc').textbox('setValue', current_select_func_gnflmc);
    $('#txt_win_func_gndy_flmc').textbox('setValue', flmc);
    $('#txt_win_func_gndy_gnmc').textbox('setValue', gnmc);
    $('#txt_win_func_gndy_gnct').textbox('setValue', gnct);
    $('#txt_win_func_gndy_gnxsmc').textbox('setValue', gnxsmc);
    $('#txt_win_func_gndy_gnxh').textbox('setValue', gnxh);
    $('#txt_win_func_gndy_gncxj').textbox('setValue', gncxj);
    // $('#txt_win_func_gndy_gncs').textbox('setValue', gncs);
    // $('#txt_win_func_gndy_cscs').textbox('setValue', cscs);
    $('#txt_win_func_gndy_gnsm').textbox('setValue', gnsm);
    $('#txt_win_func_gndy_gnbm').val(gnbm);

    // 编辑功能定义：按钮- - 取消
    $('#btn_win_func_gndy_cancel').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#win_func_gndy_manage').window('close');
        }
    });

    // 编辑功能定义 -- 按钮：确定
    $('#btn_win_func_gndy_confirm').linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            var obj = new Object();
            obj.flbm = flbm;
            obj.gnbm = gnbm;
            obj.gnmc = $('#txt_win_func_gndy_gnmc').textbox('getValue');//名称
            obj.gnct = $('#txt_win_func_gndy_gnct').textbox('getValue');
            obj.gnxsmc = $('#txt_win_func_gndy_gnxsmc').textbox('getValue');
            obj.gnxh = $('#txt_win_func_gndy_gnxh').numberbox('getValue');//序号
            obj.gncxj = $('#txt_win_func_gndy_gncxj').textbox('getValue');
            // obj.GNCS = $('#txt_win_func_gndy_gncs').textbox('getValue');
            // obj.CSCS = $('#txt_win_func_gndy_cscs').textbox('getValue');
            obj.gnsm = $('#txt_win_func_gndy_gnsm').textbox('getValue');
            obj.dwbm = userInfo.DWBM //单位编码
            if (isNull(obj.gnmc) || isNull(obj.gnxh)) {
                Alert("功能名称或序号不能为空！");
                return true;
            }
            $('#btn_win_func_gndy_confirm').linkbutton('disable');
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/modulefunction/AddGndy',
                data: obj,
                dataType: "json",
                success: function (result) {

                    Alert(result.message);
                    $('#win_func_gndy_manage').window('close');
                    // 刷新功能定义表格
                    $('#dg_func_gndy').datagrid('reload');
                    resize_org_func_dg_gndy_height();

                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    /*doActionWithErrorHandle(result, function (data) {
                        $('#win_func_gndy_manage').window('close');
                        // 刷新功能定义表格
                        $('#dg_func_gndy').datagrid('reload');
                        resize_org_func_dg_gndy_height();

                    });*/
                }
            });
            $('#btn_win_func_gndy_confirm').linkbutton('enable');
        }
    });
    $('#win_func_gndy_manage').window('open');

}

// 功能定义表格行 -- 操作：删除
function delete_org_func_gndy_row(index) {
    var rowDatas = $('#dg_func_gndy').datagrid('getRows');
    var gnbm = rowDatas[index].GNBM;
    Confirm('确认', '确定删除选中功能定义？', function (r) {
        if (r) {
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/modulefunction/DeleteGndy?dwbm=' + userInfo.DWBM + '&gnbm=' + gnbm,
                dataType: "json",
                success: function (result) {
                    if (isNull(result.errMsg)) {
                        //重新载入grid数据
                        load_org_func_gndy_grid();
                        Alert(result.message);
                    } else {
                        Alert(result.message + '<br>' + result.data);
                    }
                }
            });
        }
    });
}

// 判断是否为空
function isNull(data) {
    return (data == "" || data == undefined || data == null || data == 'null') ? true : false;
}

// 重置功能定义信息的高度
function resize_org_func_dg_gndy_height() {
    var h = $('#pnl_func_gndy_dg').height();
    $('#dg_func_gndy').datagrid('options').height = h - 30;
    $('#dg_func_gndy').datagrid('resize');
}

