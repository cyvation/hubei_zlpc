/**
 * 人员信息管理
 */
$(document).ready(function () {
    // 界面标签样式设置及事件绑定
    org_user_marksInit();

    // 标签初始化数据加载
    org_user_marksDataBind();

    // 人员同步
    init_win_org_user_manage_sync();

});

function org_user_marksInit() {
    // 查询栏单位编码ComboTree初始化
    $('#cbt_user_search_dw').combotree({
        method: 'get',
        lines: true,
        panelWidth:250,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/user/GetDwbmCombTree?dwbm=' + userInfo.DWBM,
        loadFilter: function (data) {
            if (data.status == 200) {
                return JSON.parse(data.value);
            } else {
                return data = new Array();
            }
        },
        onLoadSuccess: function (node, data) {
            $('#cbt_user_search_dw').combotree('setValue', userInfo.DWBM);
            index_addMousedownDiv(this,'cbt_user_search_dw');
        }
    });

    // 初始化人员信息表格
    init_org_user_grid();

    // 初始化人员信息编辑窗口
    init_win_org_user_manage();

}

function org_user_marksDataBind() {
    // 查询人员按钮事件
    $('#btn_user_search').linkbutton({

        onClick: function () {
            load_org_user_grid();
        }

    });

    // 新增人员按钮事件
    $('#btn_user_add').linkbutton({

        onClick: function () {
            open_win_org_user_manage_sync();
        }

    });


    // 添加人员弹出窗【确定】按钮事件
    $('#btn_win_org_user_manage_confirm').linkbutton({
        iconCls: 'icon-ok',

        onClick: function () {
            save_org_user_info();
        }

    });

    // 添加人员弹出窗【取消】按钮事件
    $('#btn_win_org_user_manage_cancel').linkbutton({
        iconCls: 'icon-cancel',

        onClick: function () {
            $('#win_org_user_manage').window('close');
        }

    });

    // 人员同步按钮事件
    $('#btn_user_sync').linkbutton({

        onClick: function () {
            // 加载弹出框中人员列表
            var dwbm = $('#cbt_user_search_dw').combotree('getValue');
            var mc = $('#txt_win_org_user_sync_xm').textbox('getValue');
            if (isNull(dwbm)) dwbm = userInfo.DWBM;
            $('#grid_win_org_user_sync_list').datagrid({
                url: getRootPath()+'/user/TyywNewUserList?dwbm=' + dwbm + '&mc=' + mc,
                loadFilter: function(data){
                    if (data.status == 200) {
                        return JSON.parse(data.value);
                    } else {
                        return [];
                    }
                }
            });

            $('#win_org_user_manage_sync').window({
                title: '统一业务新增人员（' + $('#cbt_user_search_dw').combotree('getText') + '）'
            });
            $('#win_org_user_manage_sync').window('open');
        }
    });

}

// 人员信息表格初始化
function init_org_user_grid() {
    $('#dg_org_user').datagrid({
        loadMsg: '数据加载中，请稍后...',
        url: getRootPath()+'/user/GetUserList?mc=&gh=&dwbm=' + userInfo.DWBM,
        width: 'auto',
        striped: true,
        fitColumns: true,
        singleSelect: false,
        pagination: true,
        rownumbers: true,
        resizable:false,
        toolbar: $('#div_user_search'),
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        columns: [[
            {field: 'DWMC', title: '单位名称', width: 100},
            {field: 'MC', title: '姓名', width: 100},
            {field: 'GH', title: '工号', width: 60},
            // {field: 'XB', title: '性别', width: 60},
            {field: 'DLBM', title: '登录别名', width: 100},
            {field: 'GZZH', title: '工作证号', width: 100},
            {field: 'YDDHHM', title: '联系电话', width: 110},
            {field: 'DZYJ', title: '电子邮件', width: 200},
            // {field: 'DLIP', title: '登陆IP', width: 150},
            {
                field: 'action', title: '操作', width: 160, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="edit_org_user(' + index + ')">编辑</a> ';
                    var d = '<a href="#" onclick="delete_org_user(' + index + ')">删除</a> ';
                    var s = '<a href="#" onclick="reset_org_user_pwd(' + index + ')">密码重置</a> ';
                    return r + d + s;
                }
            }
        ]],
        loadFilter: function(data){
            if (data.status == 200) {
                return JSON.parse(data.value);
            } else {
                return [];
            }
        },
        onClickRow: function (rowIndex, rowData) { //人员信息表格单行高亮
            $('#dg_org_user').datagrid('clearSelections');
            $('#dg_org_user').datagrid('highlightRow', rowIndex);
        }

    });

    $('#dg_org_user').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    resize_org_user_grid_height();
    resize_org_user_grid_width();

}

// 人员信息弹出窗初始化
function init_win_org_user_manage() {
    $('#win_org_user_manage').window({
        modal: true,
        maximizable: false,
        minimizable: false,
        closed: true,
        collapsible: false,
        title: '新增人员信息'
    });
}

// 加载人员grid数据
function load_org_user_grid() {
    var xm = $('#txt_user_search_xm').val();
    var gh = $('#txt_user_search_gh').val();
    var dwbm = $('#cbt_user_search_dw').combotree('getValue');

    if (isNull(dwbm)) {
        dwbm = userInfo.DWBM;
    }

    $('#dg_org_user').datagrid({
        url: getRootPath()+'/user/GetUserList?mc=' + xm + '&gh=' + gh + '&dwbm=' + dwbm
    });

    resize_org_user_grid_height();
    resize_org_user_grid_width();
}

// 新增人员时打开弹出窗
function open_win_org_user_manage_sync() {
    // 人员信息编辑弹出窗中的单位编码ComboTree初始化
    $('#cbt_win_user_dw').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url:getRootPath()+ '/user/GetDwbmCombTree?dwbm=' + userInfo.DWBM,
        loadFilter: function (data) {
            if (data.status == 200) {
                return JSON.parse(data.value);
            } else {
                return data = new Array();
            }
        },
        onLoadSuccess: function (node, data) {
            $('#cbt_win_user_dw').combotree('setValue', userInfo.DWBM);
            index_addMousedownDiv(this,'cbt_win_user_dw');
        }
    });
    $('#win_org_user_manage').window({
        title: '新增人员信息'
    });
    var search_dwbm = $('#cbt_user_search_dw').combotree("getValue");
    $('#cbt_win_user_dw').combotree("setValue", search_dwbm);
    $('#win_org_user_manage').window('open');
    clear_win_org_user_manage();
}

// 清空添加人员信息窗口的控件值
function clear_win_org_user_manage() {
    $('#txt_win_user_mc').textbox('setValue', '');
    $('#txt_win_user_dlbm').textbox('setValue', '');
    $('#txt_win_user_gzzh').textbox('setValue', '');
    $('#txt_win_user_yddhhm').textbox('setValue', '');
    $('#txt_win_user_dzyx').textbox('setValue', '');
    $('#txt_win_user_caid').textbox('setValue', '');
    /*$('#txt_win_user_dlip').textbox('setValue', '');*/
}

// 保存人员信息
function save_org_user_info() {
    // 获取按钮上的文本
    var title = $('#win_org_user_manage').panel('options').title;
    var dwbm = $('#cbt_win_user_dw').combotree("getValue");
    var xdwbm = null;
    var mc = trim($('#txt_win_user_mc').textbox("getValue"));

    if (!isNull(mc)) {
        if (getLength(mc) > 60) {
            Alert('名称不能超过60个字符,汉字则不能超过30个字符');
            return;
        }
    }

    var dlbm = trim($('#txt_win_user_dlbm').textbox("getValue"));
    if (!isNull(dlbm)) {
        if (getLength(dlbm) > 60) {
            Alert('登录别名不能超过60个字符,汉字则不能超过30个字符');
            return;
        }
    }

    var gzzh = trim($('#txt_win_user_gzzh').textbox('getValue'));
    if (!isNull(gzzh)) {
        if (getLength(gzzh) > 20) {
            Alert("工作证号不能超过20位!");
            return;
        }
    }
    /*var dlip = trim($('#txt_win_user_dlip').textbox('getValue'));
    if (!isNull(dlip)) {
        if (getLength(dlip) > 20) {
            Alert("登陆IP不能超过20位!");
            return;
        }
    }*/

    var xb = $("input[name='txt_win_user_xb']:checked").val();
    var lsryCkv = $("input[name='txt_win_user_sflsry']:checked").val();
    var lsry = 'N';
    if ('1' == lsryCkv) {
        lsry = 'Y';
    }

    var yddhhm = trim($('#txt_win_user_yddhhm').textbox('getValue'));
    if (!isNull(yddhhm)) {
        if (!HRFilePhonevalidate(yddhhm)) {
            Alert("请输入正确的移动电话号码!");
            return;
        }
    }

    var dzyj = trim($('#txt_win_user_dzyx').textbox('getValue'));
    if (!isNull(dzyj)) {
        if (!(mainIsValidate(dzyj))) {
            Alert("请输入正确的邮箱");
            return;
        }
    }

    var CAIDH = trim($('#txt_win_user_caid').textbox('getValue'));
    if (!isNull(CAIDH)) {
        if (getLength(CAIDH) > 100) {
            Alert('CAID号不能超过100个字符,汉字则不能超过50个字符');
            return;
        }
    }

    var action = '';
    var gh = '';
    if (title == '新增人员信息') {
        action = 'AddUser';
    } else {
        action = 'UpdateUser';
        dwbm = trim($('#txt_win_user_dwbm').textbox('getValue'));
        xdwbm = $('#cbt_win_user_dw').combotree("getValue");
        gh = $('#txt_win_user_gh').textbox('getValue');
    }

    $.ajax({
        type: 'POST',
        url: getRootPath()+'/user/AddOrUpdateRybm',
        data: {
            action:action,
            mc: mc, dlbm: dlbm, gzzh: gzzh,
            xb: xb, sflsry: lsry, yddhhm: yddhhm, dzyj: dzyj,
            CAID: CAIDH, gh: gh,  dwbm: dwbm, xdwbm: xdwbm
        },
        success: function (result) {
            // if ('AddUser' == action) {
            //     $('#txt_user_search_xm').textbox("setValue", mc);
            //     $('#cbt_user_search_dw').combotree("setValue", dwbm);
            // }
            // else {
            //     $('#cbt_user_search_dw').combotree("setValue", xdwbm);
            // }

            load_org_user_grid();
            Alert(JSON.parse(result).note);
        }
    });
    $('#win_org_user_manage').window('close');
}

// 修改人员操作
function edit_org_user(index) {

    // 人员信息编辑弹出窗中的单位编码ComboTree初始化
    $('#cbt_win_user_dw').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/user/GetDwbmCombTree?dwbm=' + userInfo.DWBM,
        loadFilter: function (data) {
            if (data.status == 200) {
                return JSON.parse(data.value);
            } else {
                return data = new Array();
            }
        },
        onLoadSuccess: function () {
            $('#cbt_win_user_dw').combotree('setValue', userInfo.DWBM);
            index_addMousedownDiv(this,'cbt_win_user_dw');
        }
    });

    var rowDatas = $('#dg_org_user').datagrid('getRows');
    var gh = rowDatas[index].GH;
    var dwbm = rowDatas[index].DWBM;

    // 查找人员信息
    $.post(getRootPath() + "/user/GetUserByGh", { gh: gh, dwbm: dwbm},
        function (data) {
            if (!isNull(data)) {
                var arrUser = new Array();
                arrUser = JSON.parse(data).value.split(',', 11);
                for (var i = 0; i < arrUser.length; i++) {
                    var tmp = arrUser[i].split(':', 2);
                    match_org_user_contrl(tmp);
                }
            }
        });
    $('#win_org_user_manage').window({
        title: '编辑人员信息'
    });
    $('#win_org_user_manage').window('open');
}

// 匹配控件进行赋值
function match_org_user_contrl(data) {
    switch (data[0]) {
        case "gh":
            $('#txt_win_user_gh').textbox('setValue', data[1]);
            break;
        case "dwbm": {
            $('#txt_win_user_dwbm').textbox('setValue', data[1]);
            $('#cbt_win_user_dw').combotree('setValue', data[1]);
            $('#cbt_user_search_dw').combotree('setValue', data[1]);
            break;
        }
        case "mc":
            $('#txt_win_user_mc').textbox('setValue', data[1]);
            break;
        case "dlbm":
            $('#txt_win_user_dlbm').textbox('setValue', data[1]);
            break;
        case "gzzh":
            $('#txt_win_user_gzzh').textbox('setValue', data[1]);
            break;
        case "xb": {
            var xb = data[1];
            if (xb == "男") {
                document.getElementsByName('txt_win_user_xb')[0].checked = true;
            }
            else {
                document.getElementsByName('txt_win_user_xb')[1].checked = true;
            }
        }
            break;
        case "sflsry": {
            var lsry = data[1];
            if (lsry == "Y") {
                document.getElementsByName('txt_win_user_sflsry')[0].checked = true;
            }
            else {
                document.getElementsByName('txt_win_user_sflsry')[1].checked = true;
            }
        }
            break;
        case "yddhhm":
            $('#txt_win_user_yddhhm').textbox('setValue', data[1]);
            break;
        case "dzyj":
            $('#txt_win_user_dzyx').textbox('setValue', data[1]);
            break;
        case "caid":
            $('#txt_win_user_caid').textbox('setValue', data[1]);
            break;
       /* case "dlip":
            $('#txt_win_user_dlip').textbox('setValue', data[1]);
            break;*/
    }
}

// 删除人员操作
function delete_org_user(index) {
    $.messager.confirm('确认', '您确认想要删除所选人员吗？', function (r) {
        if (r) {
            var rowDatas = $('#dg_org_user').datagrid('getRows');
            var gh = rowDatas[index].GH;
            var dwbm = rowDatas[index].DWBM;
            var ghj = '';
            var data = $('#dg_org_user').datagrid('getChecked');
            ghj = gh;

            if (ghj == '') {
                return;
            }
            $.post(getRootPath()+"/user/DeleteUser", {
                    ghj: ghj, dwbm: dwbm
                },
                function (result) {
                    load_org_user_grid();
                    Alert(JSON.parse(result).note);
                });
        }
    });
}

// 重置密码
function reset_org_user_pwd(index) {
    $.messager.confirm('确认', '您确认想要将所选人员重置密码吗？', function (r) {
        if (r) {
            var ghj = '';
            var rowDatas = $('#dg_org_user').datagrid('getRows');
            var dwbm = rowDatas[index].DWBM;

            ghj = rowDatas[index].GH;
            if (ghj == '') {
                return;
            }
            $.post(getRootPath()+"/user/ResetUserPwd", {
                    ghj: ghj, dwbm: dwbm
                },
                function (result) {
                    Alert(JSON.parse(result).note);
                });
        }
    });
}

// 重置人员信息表格的高度
function resize_org_user_grid_height() {
    var pnl_user_height = $('#pnl_org_user').height();
    $('#dg_org_user').datagrid('options').height = pnl_user_height;
    $('#dg_org_user').datagrid('resize');
}

// 重置人员信息的表格宽度
function resize_org_user_grid_width() {
    var pnl_user_width = $('#pnl_org_user').width();
    $('#dg_org_user').datagrid('options').width = pnl_user_width;
    $('#dg_org_user').datagrid('resize');
}

// 人员同步
function init_win_org_user_manage_sync() {

    // 选择人员列表初始化
    $('#grid_win_org_user_sync_list').datagrid({
        striped: true,
        singleSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        toolbar: $('#tool_win_org_user_sync'),
        columns: [[
            { field: 'ck', width: 50, checkbox: true },
            { field: 'MC', title: '姓名', width: 100 },
            { field: 'XB', title: '性别', width: 40 },
            { field: 'GH', title: '工号', width: 50 },
            { field: 'DWMC', title: '单位', width: 200 },
            { field: 'DLBM', title: '登录别名', width: 100 },
            { field: 'GZZH', title: '工作证号', width: 100 }
        ]]
    });

    // 调整分页控件位置
    $('#btn_win_org_user_sync_search').linkbutton({
        onClick: function () {
            var dwbm = $('#cbt_user_search_dw').combotree('getValue');
            var mc = $('#txt_win_org_user_sync_xm').textbox('getValue');

            if (isNull(dwbm)) dwbm = userInfo.DWBM;
            $('#grid_win_org_user_sync_list').datagrid({
                url: getRootPath()+'/user/TyywNewUserList?dwbm=' + dwbm + '&mc=' + mc,
                loadFilter: function(data){
                    if (data.status == 200) {
                        return JSON.parse(data.value);
                    } else {
                        return [];
                    }
                }
            });
        }
    });
    $('#btn_win_org_user_sync_add').linkbutton({
        onClick: function () {
            var dwbm = $('#cbt_user_search_dw').combotree('getValue');
            var rows = $('#grid_win_org_user_sync_list').datagrid('getSelections');

            if (rows.length == 0) {
                Alert('请选择需要同步的人员！');
            } else {
                Confirm("确认", "确定要同步选中的人员？", function (p) {
                    if (p) {
                        var gh = "";
                        for (var i = 0; i < rows.length; i++) {
                            gh += rows[i].GH + ",";
                        }
                        // 添加同步的人员
                        $.ajax({
                            type: 'POST',
                            url:getRootPath()+ '/user/UserSync',
                            data: { dwbm: dwbm, gh: gh },
                            dataType: "json",
                            success: function (result) {
                                if (result.status==200) {
                                    $('#win_org_user_manage_sync').window('close');
                                }
                                Alert(result.note);
                                $('#dg_org_user').datagrid('load');
                            }
                        });
                    }
                });
            }
        }
    })
}

