
var current_select_unit; //当前选中单位
var need_expand_node; //刷新数据后，需要展开节点ID
var pc_bmbm = '0100'; //评查部门编码
var pc_fzr_jsbm = '101'; //评查负责人角色编码
var pc_zz_jsbm = '102'; //评查组长角色编码
var pc_zy_jsbm = '103'; //评查组员角色编码

$(document).ready(function () {

    // 界面标签样式设置及事件绑定
    org_marksInit();

    // 标签初始化数据加载
    org_marksDataBind();
});

// 界面标签样式设置及事件绑定
function org_marksInit() {

}

// 标签初始化数据加载
function org_marksDataBind() {

    current_select_unit = userInfo.DWBM;

    // 默认选中单位节点
    need_expand_node = current_select_unit;

    // 加载对应单位的组织机构树，因默认选中本单位，故可无需等待单位列表加载完成才加载数据
    load_tree_org_dwbmjs();

    // 初始化人员列表DataGrid样式
    init_grid_org_user_manage_list();

    //获取人员数据
    //load_grid_org_user_manage_list('', '', '', '', '', current_select_unit);

    // 加载本单位及下级列表，默认选中本单位
    load_cbt_org_tool_manage_unit();

    // 初始化人员信息
    init_pnl_org_user_manage();

    // 初始化人员新增弹出框
    init_win_org_user_manage_add();

    // 初始化部门编辑弹出窗
    init_win_org_dep_manage();

    // 初始化角色编辑弹出窗
    init_win_org_role_mange();

    // 初始化角色权限编辑弹出窗
    init_win_org_role_right();

    // 初始化单位功能权限编辑弹出窗
    init_win_org_unit_right();

    $('#pnl_org_user_manage').panel({
        onResize: function (width, height) {
            $('#grid_org_user_manage_list').datagrid('options').width = width;
            $('#grid_org_user_manage_list').datagrid('resize');
        }
    });   

}

// 加载本单位及下级列表，默认选中本单位
function load_cbt_org_tool_manage_unit() {

    $('#cbt_org_tool_manage_unit').combotree({
        method: 'get',
        lines: true,
        panelWidth:250,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },

        onLoadSuccess: function () {
            // 设置默认选中单位为当前单位
            $('#cbt_org_tool_manage_unit').combotree('setValue', userInfo.DWBM);
            //// 加载对应单位的组织机构树
             load_tree_org_dwbmjs();
            index_addMousedownDiv(this,"cbt_org_tool_manage_unit");
        },

        onSelect: function (node) {
            // 设置当前选中单位
            current_select_unit = node.id;
            // 加载对应单位的组织机构树
            load_tree_org_dwbmjs();
        }
    });

}

// 加载对应单位的组织机构树
function load_tree_org_dwbmjs() {
    // 搜索
    $('#tree_org_dwbmjs_search').searchbox({
        searcher: function (value, name) {
            $("#tree_org_dwbmjs").tree("search", value);
        },
        prompt: '请输入值'
    });

    // 组织机构树
    $("#tree_org_dwbmjs").tree({
        url: getRootPath()+'/organization/getDwBmJsByDwbm?dwbm=' + current_select_unit,
        method: 'get',
        lines: true,
        loadFilter:function (data) {
            if(data.status==200){

                var res=[];
                var n = 0;
                var zzjgJson= JSON.parse(data.value);
                console.log(zzjgJson[0].children[0].children);
                for(var i =0; i< zzjgJson[0].children[0].children.length;i++){
                    var js = zzjgJson[0].children[0].children[i];
                    if(/*js.id != "9191108" && */js.id != "9191101" && js.id != "9191102" && js.id != "9191105"){
                        res.push(js);
                    }
                }
                zzjgJson[0].children[0].children=res;
                return zzjgJson;

            }else{
                return [];
            }
        },
        onLoadSuccess: function (node, data) {
            // 默认需要展开节点
            if (!isNull(need_expand_node)) {
                focuse_expand_tree_org_dwbmjs(need_expand_node);
                need_expand_node = '';
            }
        },
        onDblClick: function (node) {
            if (node.state == "closed")
                $("#tree_org_dwbmjs").tree('expand', node.target);
            else
                $("#tree_org_dwbmjs").tree('collapse', node.target);
        },
        onSelect: function (node) {
            if (node.state == "closed")
                $("#tree_org_dwbmjs").tree('expand', node.target);
            else
                $("#tree_org_dwbmjs").tree('collapse', node.target);

            var bm = node.id;
            var mc = node.text;
            var bits = getLength(bm.toString());
            if (bits == 6) {
                //选择单位：增加部门
                $('#btn_org_user_manage_add').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_add_dep').linkbutton({ disabled: false });
                $('#btn_org_tool_manage_edit_dep').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_delete_dep').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_add_role').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_edit_role').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_delete_role').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_asign_role_right').linkbutton({ disabled: true });
                //刷新人员信息            
                load_grid_org_user_manage_list('', '', '', '', '', current_select_unit);
            }
            else if (bits == 4) {
                //选择部门：增删改部门、增加角色
                $('#btn_org_user_manage_add').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_add_dep').linkbutton({ disabled: false });
                $('#btn_org_tool_manage_edit_dep').linkbutton({ disabled: false });
                // 评查部门不能删除
                $('#btn_org_tool_manage_delete_dep').linkbutton({ disabled: bm == pc_bmbm });
                $('#btn_org_tool_manage_add_role').linkbutton({ disabled: false });
                $('#btn_org_tool_manage_edit_role').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_delete_role').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_asign_role_right').linkbutton({ disabled: true });
                //刷新人员信息
                load_grid_org_user_manage_list(bm, '', '', '', '', current_select_unit);
            }
            else if (bits == 7) { //部门编码 + 角色编码
                //选择角色：删改角色、角色权限、增加人员
                $('#btn_org_tool_manage_add_dep').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_edit_dep').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_delete_dep').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_add_role').linkbutton({ disabled: true });
                $('#btn_org_tool_manage_edit_role').linkbutton({ disabled: false });
                var jsbm = sub_jsbm(bm);
                var isEnable = (jsbm == pc_fzr_jsbm) || (jsbm == pc_zz_jsbm) || (jsbm == pc_zy_jsbm);
                $('#btn_org_tool_manage_delete_role').linkbutton({ disabled: isEnable });
                $('#btn_org_user_manage_add').linkbutton({ disabled: isEnable });
                $('#btn_org_tool_manage_asign_role_right').linkbutton({ disabled: false });
                //获取父节点
                var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
                //刷新人员信息
                load_grid_org_user_manage_list(pNode.id, sub_jsbm(bm), '', '', '', current_select_unit);
            }
        }
    });
}

// 初始化人员列表DataGrid样式
function init_grid_org_user_manage_list() {
    $('#grid_org_user_manage_list').datagrid({
        width: 'auto',
        striped: true,
        singleSelect: false,
        //fit: true,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        toolbar: $('#tool_org_user_manage_search'),
        columns: [[
            { field: 'MC', title: '姓名', width: 80 },
            { field: 'GH', title: '工号', width: 50 },
            { field: 'BMMC', title: '部门', width: 130 },
            { field: 'JSMC', title: '角色', width: 120 },
            { field: 'DLBM', title: '登录别名', width: 80 },
            { field: 'GZZH', title: '工作证号', width: 80 },
            { field: 'YBMMC', title: '统一业务部门', width: 100 },
            { field: 'YJSMC', title: '统一业务角色', width: 100 },
            //{ field: 'SFTZ', title: '是否在职', width: 70, align: 'center',
            //    formatter: function (value) {
            //        if (value == "N") {
            //            return '<div><img src="./Scripts/jquery-easyui-1.4.1/themes/icons/ok.png"/></div>';
            //        }
            //        else {
            //            return '<div><img src="./Scripts/jquery-easyui-1.4.1/themes/icons/cross.png"/></div>';
            //        }
            //    }
            //},
            {field: 'BMBM', title: '部门编码', hidden: true },
            { field: 'JSBM', title: '角色编码', hidden: true },
            //{ field: 'SFSC', title: '是否启用', width: 70, align: 'center',
            //    formatter: function (value) {
            //        if (value == "N") {
            //            return '<div><img src="./Scripts/jquery-easyui-1.4.1/themes/icons/ok.png"/></div>';
            //        }
            //        else {
            //            return '<div><img src="./Scripts/jquery-easyui-1.4.1/themes/icons/cross.png"/></div>';
            //        }
            //    }
            //},
            {field: 'action', title: '操作', width: 100, align: 'center',
            formatter: function (value, row, index) {
                /*var e = '<a href="#" onclick="trans_user_job(' + index + ')">调岗</a> ';*/
                var d = '<a href="#" onclick="remove_user_job(' + index + ')">移除</a>';
                return d;
            }
        }
        ]],
        onClickRow: function (rowIndex, rowData) {
            $('#grid_org_user_manage_list').datagrid('clearSelections');
            $('#grid_org_user_manage_list').datagrid('highlightRow', rowIndex);
        },
        groupField: 'JSMC',
        view: groupview,
        groupFormatter: function (value, rows) {
            return ((value == '') ? "未分配角色" : value); // +' - ' + rows.length + ' 条';
        }
    });

    //重新设定右侧人员列表的宽度/高度
    resize_grid_org_user_manage_list();
}

// 加载人员列表DataGrid数据
function load_grid_org_user_manage_list(bmbm, jsbm, xm, gh, gzzh, dwbm) {
    // JS对象
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.bmbm = bmbm;
    obj.jsbm = jsbm;
    obj.gh = gh;
    obj.gzzh = gzzh;
    obj.xm = xm;

    $('#grid_org_user_manage_list').datagrid({
        url:getRootPath()+ '/organization/getRyInfo',
        queryParams: obj,

        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        }

    });
}

// 重置人员列表DataGrid的高度\宽度
function resize_grid_org_user_manage_list() {
    var height = $('#pnl_org_user_manage').height();
    var h = $('#tool_org_user_manage_search').height();
    var width = $('#pnl_org_user_manage').width();
    $('#grid_org_user_manage_list').datagrid('options').width = width;
    // $('#grid_org_user_manage_list').datagrid('options').height = height - h + 20;
    $('#grid_org_user_manage_list').datagrid('resize');
}

// 初始化人员信息
function init_pnl_org_user_manage() {

    // 人员信息：查询
    $('#btn_org_user_manage_search').linkbutton({
        iconCls: 'icon-search',
        onClick: function () {
            var dwbm = current_select_unit;
            var bmbm = '';
            var jsbm = '';
            var xm = trim($('#txt_org_user_manage_xm').textbox('getValue'));
            var gh = trim($('#txt_org_user_manage_gh').textbox('getValue'));
            var gzzh = trim($('#txt_org_user_manage_gzzh').textbox('getValue'));

            var node_focused = $('#tree_org_dwbmjs').tree('getSelected');
            var node_id_length ;
            if (node_focused == null){
                alert("请选择角色");
                return;
            }else {
                 node_id_length = getLength(node_focused.id.toString());
            }
            if (node_id_length == 6) {
                bmbm = '';
                jsbm = '';
            }
            if (node_id_length == 4) {
                bmbm = node_focused.id;
                jsbm = '';
            }
            if (node_id_length == 7) {
                var node_bm = $('#tree_org_dwbmjs').tree('getParent', node_focused.target);
                bmbm = node_bm.id;
                jsbm = node_focused.id;
            }
            jsbm = sub_jsbm(jsbm);

            // 加载人员列表
            load_grid_org_user_manage_list(bmbm, jsbm, xm, gh, gzzh, dwbm);
        }
    });

    // 人员信息：添加人员
    $('#btn_org_user_manage_add').linkbutton({
        onClick: function () {
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var jsbm = sub_jsbm(node.id);
            var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
            var bmbm = pNode.id;
            var xm = trim($('#txt_win_org_user_add_xm').textbox('getValue'));

            
            // 加载弹出框中人员列表
            load_grid_win_org_user_add_list(current_select_unit, bmbm, jsbm, xm);

            $('#win_org_user_manage_add').window({
                title: '添加人员'
            });
            $('#win_org_user_manage_add').window('open');
        }
    });
}

// 初始化人员新增窗体
function init_win_org_user_manage_add() {

    //选择人员列表初始化
    $('#grid_win_org_user_add_list').datagrid({
        width: 635,
        striped: true,
        singleSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        toolbar: $('#tool_win_org_user_add'),
        columns: [[
            // { field: 'ck', width: 50, checkbox: true },
            // { field: 'MC', title: '姓名', width: 100 },
            // { field: 'GH', title: '工号', width: 50 },
            // { field: 'DWMC', title: '单位', width: 200 },
            // { field: 'DLBM', title: '登录别名', width: 100 },
            // { field: 'GZZH', title: '工作证号', width: 100 }
            {field: 'MC', title: '姓名', width: 100},
            {field: 'BMMC', title: '部门名称', width: 100},
            {field: 'JSMC', title: '角色', width: 100},
            {field: 'GZZH', title: '工作证号', width: 100}
        ]]
    });

    // 调整分页控件位置
    // resize_grid_win_org_user_add_list();

    //选择人员窗口 -- 按钮：查询
    $('#btn_win_org_user_add_search').linkbutton({
        onClick: function () {
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var jsbm = sub_jsbm(node.id);
            var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
            var bmbm = pNode.id;
            var xm = trim($('#txt_win_org_user_add_xm').textbox('getValue'));
            load_grid_win_org_user_add_list(current_select_unit, bmbm, jsbm, xm);
        }
    });

    //选择人员窗口 -- 按钮：添加
    $('#btn_win_org_user_add_add').linkbutton({
        onClick: function () {
            var data = $('#grid_win_org_user_add_list').datagrid('getChecked');
            if (data.length <= 0) {
                Alert("请勾选人员！");
                return;
            }

            // JS对象

            var dwbm = current_select_unit;
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var jsbm = sub_jsbm(node.id);
            var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
            var bmbm = pNode.id;
            var arr = new Array();
            for (var i = 0; i < data.length; i++) {
                var obj = new Object();
                obj.dwbm = dwbm;
                obj.jsbm = jsbm;
                obj.bmbm = bmbm;
                obj.gh = data[i].GH;
                obj.ydwbm = data[i].DWBM;
                obj.ydwmc = data[i].DWMC;
                obj.ybmbm = data[i].BMBM;
                obj.ybmmc = data[i].BMMC;
                obj.yjsbm = data[i].JSBM;
                obj.yjsmc = data[i].JSMC;
                arr[i] = obj;
            }

            $('#btn_win_org_user_add_add').linkbutton('disable');
            // 添加
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/organization/addRYJSFP',
                data: JSON.stringify(arr),
                contentType:'application/json',
                dataType: "json",
                success: function (result) {
                    $('#btn_win_org_user_add_add').linkbutton('enable');

                    // 刷新未分配人员
                    $('#grid_win_org_user_add_list').datagrid('load');
                    // 刷新分配人员列表
                    $('#grid_org_user_manage_list').datagrid('load');

                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    if(result.status == 200){
                        $('#win_org_user_manage_add').window('close');
                            //刷新数据
                        $('#grid_org_user_manage_list').datagrid('load');
                    } Alert(result.note);

                }
            });
        }
    });

}

// 载入弹出窗口未分配角色人员数据
function load_grid_win_org_user_add_list(dwbm, bmbm, jsbm, xm) {
    // JS对象
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.bmbm = bmbm;
    obj.jsbm = jsbm;
    obj.xm = xm;

    $('#grid_win_org_user_add_list').datagrid({
        url: getRootPath()+'/organization/getWfpRyInfo',
        queryParams:obj,
        loadFilter:function (result) {
            return result.status == 200?JSON.parse(result.value):[];
        }
    });


}

// 重置弹出窗口选择人员信息的高度\宽度
function resize_grid_win_org_user_add_list() {
    var height = $('#pnl_win_org_user_add_list').height();
    var width = $('#win_org_user_manage_add').window('options').width;
    var h = $('#tool_win_org_user_add').height();
    //$('#grid_win_org_user_add_list').datagrid('options').width = width;
    $('#grid_win_org_user_add_list').datagrid('options').height = height;
    $('#grid_win_org_user_add_list').datagrid('resize');
}

// 初始化部门编辑工具
function init_win_org_dep_manage() {

    //添加部门
    $('#btn_org_tool_manage_add_dep').linkbutton({
        onClick: function () {
            $('#txt_win_org_dep_mange_bmmc').textbox('setValue', '');
            $('#txt_win_org_dep_mange_bmjc').textbox('setValue', '');
            $('#txt_win_org_dep_mange_bmxh').numberbox('setValue', 0);
            $('#txt_win_org_dep_mange_bmbz').textbox('setValue', '');

            // 添加部门 -- 按钮：取消
            $('#btn_win_org_dep_mange_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_org_dep_manage').window('close');
                }
            });

            // 添加部门 -- 按钮：确定
            $('#btn_win_org_dep_mange_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    // JS对象
                    var obj = new Object();
                    obj.dwbm = current_select_unit;
                    obj.bmmc = $('#txt_win_org_dep_mange_bmmc').textbox('getValue');
                    obj.bmjc = $('#txt_win_org_dep_mange_bmjc').textbox('getValue');
                    obj.bmxh = $('#txt_win_org_dep_mange_bmxh').numberbox('getValue');
                    obj.bz = $('#txt_win_org_dep_mange_bmbz').textbox('getValue');
                    if (isNull(obj.bmmc) || isNull(obj.bmjc) || isNull(obj.bmxh)) {
                        Alert("部门名称或者部门简称或者部门序号不能为空！");
                        return;
                    }
                    var node = $('#tree_org_dwbmjs').tree('getSelected');
                    if (!node) {
                        Alert("请在左边选中一个单位");
                        return;
                    }
                    var bmbm = node.id;
                    if (bmbm.length >= 6)
                        bmbm = "";
                    obj.fbmbm = bmbm;

                    $('#btn_win_org_dep_mange_confirm').linkbutton('disable');
                    // 添加部门
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/organization/addBmInfo',
                        contentType:'application/json',
                        data: JSON.stringify(obj),
                        async: false,
                        dataType: "json",
                        success: function (result) {
                            $('#btn_win_org_dep_mange_confirm').linkbutton('enable');

                            if(result.status == 200){// 对一般处理程序返回的数据，进行错误处理及数据过滤
                                // 刷新组织机构树
                                load_tree_org_dwbmjs();

                            }
                            Alert(result.note);
                            $('#win_org_dep_manage').window('close');

                        }
                    });
                }
            });

            $('#win_org_dep_manage').window('open');
        }
    });

    //修改部门
    $('#btn_org_tool_manage_edit_dep').linkbutton({
        onClick: function () {
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var bmbm = node.id;
            var bmmc = node.text;
            var dwbm = current_select_unit;

            $('#btn_org_tool_manage_edit_dep').linkbutton('disable');
            // 获取部门信息
            $.ajax({
                type: 'GET',
                url:getRootPath()+ '/organization/getBmInfo',
                data: {
                    dwbm: dwbm,
                    bmbm: bmbm
                },
                dataType: "json",
                success: function (result) {
                    $('#btn_org_tool_manage_edit_dep').linkbutton('enable');
                    //获取后台返回数据并判断是否成功
                    if(result.status == 200){// 对一般处理程序返回的数据，进行错误处理及数据过滤
                        result = result.value
                       //数据回填
                        $('#txt_win_org_dep_mange_bmmc').textbox('setValue', bmmc);
                        $('#txt_win_org_dep_mange_bmjc').textbox('setValue', result.bmjc);
                        $('#txt_win_org_dep_mange_bmxh').numberbox('setValue', result.bmxh);
                        $('#txt_win_org_dep_mange_bmbz').textbox('setValue', result.bz);
                    }

                }
            });

            // 修改部门 -- 按钮：取消
            $('#btn_win_org_dep_mange_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_org_dep_manage').window('close');
                }
            });
            // 修改部门 -- 按钮：确定
            $('#btn_win_org_dep_mange_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    // JS对象
                    var obj = new Object();
                    obj.dwbm = current_select_unit;
                    obj.bmbm = bmbm;
                    obj.bmmc = $('#txt_win_org_dep_mange_bmmc').textbox('getValue');
                    obj.bmjc = $('#txt_win_org_dep_mange_bmjc').textbox('getValue');
                    obj.bmxh = $('#txt_win_org_dep_mange_bmxh').numberbox('getValue');
                    obj.bz = $('#txt_win_org_dep_mange_bmbz').textbox("getValue");
                    if (isNull(obj.bmmc) || isNull(obj.bmjc) || isNull(obj.bmxh)) {
                        Alert("部门名称或者部门简称或者部门序号不能为空！");
                        return;
                    }

                    $('#btn_win_org_dep_mange_confirm').linkbutton('disable');
                    // 编辑部门
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/organization/addBmInfo',
                        contentType:'application/json',
                        data:JSON.stringify(obj),
                        async: false,
                        dataType: "json",
                        success: function (result) {
                            $('#btn_win_org_dep_mange_confirm').linkbutton('enable');

                            if(result.status == 200){// 对一般处理程序返回的数据，进行错误处理及数据过滤
                                // 刷新组织机构树
                                load_tree_org_dwbmjs();

                            }Alert(result.note);

                            $('#win_org_dep_manage').window('close');

                        }
                    });
                }
            });
            $('#win_org_dep_manage').window('open');
        }
    });

    // 删除部门信息
    $('#btn_org_tool_manage_delete_dep').linkbutton({
        onClick: function () {
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var dwbm = current_select_unit;
            var bmbm = node.id;
            Confirm('确认', '确认删除当前选中部门？', function (r) {
                if (r) {
                    // 删除部门
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/organization/deleteBmInfo',
                        data: {
                            bmbm: bmbm,
                            dwbm: dwbm
                        },
                        dataType: "json",
                        success: function (result) {
                            // 对一般处理程序返回的数据，进行错误处理及数据过滤
                            if(result.status == 200){
                                need_expand_node = current_select_unit;
                                // 刷新组织机构树
                                load_tree_org_dwbmjs();
                            }Alert(result.note);
                        }
                    });
                }
            });
        }
    });
}

// 初始化角色编辑弹出窗
function init_win_org_role_mange() {

    //添加角色
    $('#btn_org_tool_manage_add_role').linkbutton({
        onClick: function () {
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            $('#txt_win_org_role_mange_ssbm').textbox('setValue', node.text);
            $('#txt_win_org_role_mange_ssbm').textbox('disable', 'true');
            $('#txt_win_org_role_mange_jsmc').textbox('setValue', '');
            $('#txt_win_org_role_mange_jsxh').numberbox('setValue', 0);

            // 添加角色 -- 按钮：取消
            $('#btn_win_org_role_mange_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_org_role_mange').window('close');
                }
            });

            // 添加角色 -- 按钮：确定
            $('#btn_win_org_role_mange_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    // JS对象
                    var obj = new Object();
                    obj.dwbm = current_select_unit;
                    var node = $('#tree_org_dwbmjs').tree('getSelected');
                    obj.bmbm = node.id;
                    obj.jsmc = $('#txt_win_org_role_mange_jsmc').textbox('getValue');
                    obj.jsxh = $('#txt_win_org_role_mange_jsxh').numberbox('getValue');

                    $('#btn_win_org_role_mange_confirm').linkbutton('disable');
                    //添加角色

                    $.ajax({
                        type: 'POST',
                        url:getRootPath()+ '/organization/addJsInfo',
                        data:JSON.stringify(obj),
                        contentType:"application/json",
                        dataType: "json",

                        success: function (result) {
                            $('#btn_win_org_role_mange_confirm').linkbutton('enable');
                            // 对一般处理程序返回的数据，进行错误处理及数据过滤
                                if(result.status == 200){
                                    // 刷新组织机构树
                                    load_tree_org_dwbmjs();
                                }
                                Alert(result.note);

                               $('#win_org_role_mange').window('close');
                        }
                    });
                }
            });
            $('#win_org_role_mange').window('open');
        }
    });

    //修改角色
    $('#btn_org_tool_manage_edit_role').linkbutton({
        onClick: function () {
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var dwbm = current_select_unit;
            var jsbm = sub_jsbm(node.id);
            var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
            var bmbm = pNode.id;
            $('#txt_win_org_role_mange_jsmc').textbox('setValue', node.text);
            $('#txt_win_org_role_mange_ssbm').textbox('setValue', pNode.text);
            $('#txt_win_org_role_mange_ssbm').textbox('disable', 'true');

            $('#btn_org_tool_manage_edit_role').linkbutton('disable');
            // 获取角色信息
            $.ajax({
                type: 'GET',
                url:getRootPath()+'/organization/getJsxh',
                data: {
                    dwbm: dwbm,
                    bmbm: bmbm,
                    jsbm: jsbm
                },
                dataType: "json",
                success: function (result) {
                    $('#btn_org_tool_manage_edit_role').linkbutton('enable');
                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    if(result.status == 200){
                        $('#txt_win_org_role_mange_jsxh').numberbox('setValue', result.value);
                    }
                }
            });

            // 修改角色 -- 按钮：取消
            $('#btn_win_org_role_mange_cancel').linkbutton({
                iconCls: 'icon-cancel',
                onClick: function () {
                    $('#win_org_role_mange').window('close');
                }
            });

            // 修改角色 -- 按钮：确定
            $('#btn_win_org_role_mange_confirm').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    // JS对象
                    var obj = new Object();
                    obj.jsmc = $('#txt_win_org_role_mange_jsmc').textbox('getValue');
                    obj.jsxh = $('#txt_win_org_role_mange_jsxh').numberbox('getValue');
                    if (isNull(obj.jsmc) || isNull(obj.jsxh)) {
                        Alert("角色名称或者角色序号不能为空！");
                        return;
                    }
                    obj.dwbm = current_select_unit;
                    var node = $('#tree_org_dwbmjs').tree('getSelected');
                    obj.jsbm = sub_jsbm(node.id);
                    var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
                    obj.bmbm = pNode.id;

                    $('#btn_win_org_role_mange_confirm').linkbutton('disable');
                    // 编辑角色
                    $.ajax({
                        type: 'POST',
                        url:getRootPath()+ '/organization/addJsInfo',
                        data:JSON.stringify(obj),
                        contentType:"application/json",
                        dataType: "json",
                        success: function (result) {
                        $('#btn_win_org_role_mange_confirm').linkbutton('enable');
                        if(result.status == 200){
                            need_expand_node = add_jsbm(obj.bmbm, result.value);
                            // 刷新组织机构树
                            load_tree_org_dwbmjs();
                        }
                            Alert(result.note);
                            $('#win_org_role_mange').window('close');

                        }
                    });
                }
            });
            $('#win_org_role_mange').window('open');
        }
    });

    // 删除角色
    $('#btn_org_tool_manage_delete_role').linkbutton({
        onClick: function () {
            Confirm('确认', '确认删除当前选中角色？', function (r) {
                if (r) {
                    var node = $('#tree_org_dwbmjs').tree('getSelected');
                    var jsbm = sub_jsbm(node.id);
                    var dwbm = current_select_unit;
                    var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
                    var bmbm = pNode.id;

                    // 删除角色
                    $.ajax({
                        type: 'POST',
                        url: getRootPath()+'/organization/deleteJsInfo',
                        data: {
                            dwbm: dwbm,
                            bmbm: bmbm,
                            jsbm: jsbm
                        },
                        dataType: "json",
                        success: function (result) {
                            if(result.status == 200){
                                // 选中角色所在部门
                                need_expand_node = bmbm;
                                // 刷新组织机构树
                                load_tree_org_dwbmjs();

                            } Alert(result.note);

                        }
                    });
                }
            });
        }
    });
}

// 初始化角色功能权限编辑弹出窗
function init_win_org_role_right() {
    //角色功能权限列表初始化
    $('#grid_win_org_role_right_list').datagrid({
        width: 720,
        striped: true,
        singleSelect: false,
        checkOnSelect :false,
        loadMsg: '数据加载中，请稍后...',
        pagination: false,
        rownumbers: true,
        fitColumns: true,
        idField: 'GNBM',
        //treeField: 'GNMC',
        toolbar: $('#tool_win_org_role_right'),
        columns: [[
            { field: 'CK', width: 50, checkbox: true },
            { field: 'GNMC', title: '功能名称', width: 150 },
            { field: 'GNSM', title: '功能说明', width: 100 },
            { field: 'GNCT', title: '功能窗体', width: 200 },
            { field: 'GNXSMC', title: '功能显示名称', width: 100 },
            { field: 'GNCS', title: '编辑', width: 100,
                formatter: function(value,row,index){

            var r = '<a href="#" onclick="editGncs(' + index +')">编辑权限</a>'
            return r;

             }
            }
        ]],
        onLoadSuccess: function (data) {
            var rowDatas = $('#grid_win_org_role_right_list').datagrid('getRows');
            for (i = 0; i < rowDatas.length; i++) {
                if (rowDatas[i].JSGN !=undefined && rowDatas[i].JSGN.length > 0) {
                    $('#grid_win_org_role_right_list').datagrid('checkRow', i);
                }
                else {
                    $('#grid_win_org_role_right_list').datagrid('uncheckRow', i);
                }
            }
        },
        groupField: 'FLMC',
        view: groupview,
        groupFormatter: function (value, rows) {
            return ((value == '') ? "未分配权限" : value); // +' - ' + rows.length + ' 条';
        }
    });

    // 角色功能权限分配
    $('#btn_org_tool_manage_asign_role_right').linkbutton({
        onClick: function () {
            var dwbm = current_select_unit;
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            var jsbm = sub_jsbm(node.id);
            var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
            var bmbm = pNode.id;
            // 加载角色功能权限列表
            load_grid_win_org_role_right_list(dwbm, bmbm, jsbm);

            $('#win_org_role_right').window({
                title: '【' + node.text + '】功能权限分配'
            });
            $('#win_org_role_right').window('open');
        }
    });

    //角色权限 -- 按钮：添加
    $('#btn_win_org_role_right_asign').linkbutton({
        onClick: function () {
            var data = $('#grid_win_org_role_right_list').datagrid('getChecked');
            var gnbmj = '';
            for (var i = 0; i < data.length; i++) {
                gnbmj = gnbmj + ',' + data[i].GNBM;
            }
            //if (gnbmj == '') {
            //    return;
            //}

            // JS对象
            var obj = new Object();
            obj.dwbm = current_select_unit;
            var node = $('#tree_org_dwbmjs').tree('getSelected');
            obj.jsbm = sub_jsbm(node.id);
            var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
            obj.bmbm = pNode.id;
            obj.gnbm = gnbmj;

            $('#btn_win_org_role_right_asign').linkbutton('disable');
            // 分配功能权限
            $.ajax({
                type: 'POST',
                url: getRootPath()+ '/organization/addJsgnfp',
                contentType:'application/json',
                data: JSON.stringify(obj),
                dataType: "json",
                success: function (result) {
                    $('#btn_win_org_role_right_asign').linkbutton('enable');

                    if(result.status == 200){
                        $('#win_org_role_right').window('close');
                        Alert(result.note);
                    }
                }
            });
        }
    });
}

// 编辑功能参数
function editGncs(index) {

    $("#origination_gnmc").textbox('setValue','');
    $("#origination_gncs").textbox('setValue','');

    var obj = new Object();
    obj.dwbm = current_select_unit;
    obj.gnbm = $("#grid_win_org_role_right_list").datagrid('getRows')[index].GNBM;
    var node = $('#tree_org_dwbmjs').tree('getSelected');
    obj.jsbm = sub_jsbm(node.id);
    var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
    obj.bmbm = pNode.id;

    // 获取该功能的功能参数：
    $.ajax({
        type: 'post',
        url: getRootPath()+ '/organization/getGncs',
        data: JSON.stringify(obj),
        contentType:'application/json',
        dataType: "json",
        success: function (result) {

            if(result.status == 200){
                var gnList = result.value[0];

                if (!isNull(gnList)) {
                    // 功能名称
                   $("#origination_gnmc").textbox('setValue',gnList.GNMC);
                   //  // 功能编码
                   // $("#origination_gnbm").textbox('setValue',gnList.GNBM);
                   // 功能参数
                   $("#origination_gncs").textbox('setValue',gnList.GNCS);

                }

                // 确认修改
                $("#btn_origanization_jsgnfp_gncs_confirm").unbind('click');
                $("#btn_origanization_jsgnfp_gncs_confirm").bind('click',function(){

                    // 更新功能参数
                    orignization_udate_gncs(index);

                });

                // 取消修改
                $("#btn_origanization_jsgnfp_gncs_cancle").unbind('click');
                $("#btn_origanization_jsgnfp_gncs_cancle").bind('click',function(){

                    $("#win_origanization_jsgnfp_gncs").window('close');

                });
                
                // 展示修改功能窗口：
                $("#win_origanization_jsgnfp_gncs").window('open');

            }
        }
    });

}

// 更新功能参数：
function orignization_udate_gncs(index) {

    var obj = new Object();
    obj.gnbm = $("#grid_win_org_role_right_list").datagrid('getRows')[index].GNBM;
    obj.dwbm = current_select_unit;
    var node = $('#tree_org_dwbmjs').tree('getSelected');
    obj.jsbm = sub_jsbm(node.id);
    var pNode = $('#tree_org_dwbmjs').tree('getParent', node.target);
    obj.bmbm = pNode.id;
    var gncs = $('#origination_gncs').textbox('getText');
    obj.gncs = gncs;

    $.ajax({
        type: 'post',
        url: getRootPath()+ '/organization/updateGncs',
        data: JSON.stringify(obj),
        contentType:'application/json',
        dataType: "json",
        success: function (result) {

            if(result.status == 200){
                $("#win_origanization_jsgnfp_gncs").window('close');
                Alert(result.note);
            }
        }
    });
}

// 加载角色功能权限列表
function load_grid_win_org_role_right_list(dwbm, bmbm, jsbm) {

    $('#grid_win_org_role_right_list').datagrid({
        method:'get',
        url:getRootPath()+ '/organization/getJsGnqx?bmbm=' + bmbm + '&jsbm=' + jsbm + '&dwbm=' + dwbm,
        loadFilter:function (result) {
            return result.status == 200?JSON.parse(result.value):[];
        }

    });
}

// 初始化单位功能权限编辑弹出窗
function init_win_org_unit_right() {

    // 单位功能权限列表初始化
    $('#grid_win_org_unit_right_list').datagrid({
        height: 415,
        width: 490,
        striped: true,
        singleSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: false,
        rownumbers: true,
        fitColumns: true,
        idField: 'GNBM',
        toolbar: $('#tool_win_org_unit_right_list'),
        //treeField: 'gnmc',
        columns: [[
            { field: 'CK', width: 50, checkbox: true },
            { field: 'GNMC', title: '功能名称', width: 150 }
        ]],
        onLoadSuccess: function (data) {
            var rowDatas = $('#grid_win_org_unit_right_list').datagrid('getRows');
            for (i = 0; i < rowDatas.length; i++) {
                if (rowDatas[i].ZT == 1) {
                    $('#grid_win_org_unit_right_list').datagrid('checkRow', i);
                }
                else {
                    $('#grid_win_org_unit_right_list').datagrid('uncheckRow', i);
                }
            }
        },
        groupField: 'FLMC',
        view: groupview,
        groupFormatter: function (value, rows) {
            return ((value == '') ? "未分配权限" : value); // +' - ' + rows.length + ' 条';
        }
    });

    // 单位功能权限分配
    $('#btn_org_tool_manage_asign_unit_right').linkbutton({
        onClick: function () {

            // 初始化单位列表
            $("#tree_win_org_unit_right").tree({
                url: getRootPath()+'/organization/getDwbmTree',
                method: 'get',
                lines: true,

                loadFilter:function (data) {
                    return data.status==200?JSON.parse(data.value):[];
                },
                onLoadSuccess: function (node, data) {
                    //加载成功，展开登录用户单位的子节点
                    var dwbm = userInfo.DWBM;
                    dNode = $('#tree_win_org_unit_right').tree('find', dwbm); //获取顶级节点
                    $('#tree_win_org_unit_right').tree('select', dNode.target);

                    $('#win_org_unit_right').window('open');
                },
                onSelect: function (node) {
                    var xzdwbm = node.id;

                    // 加载当前选中单位功能权限
                    load_grid_win_org_unit_right_list(xzdwbm);
                }
            });
        }
    });

    //单位权限分配
    $('#btn_win_org_unit_right_asign').linkbutton({
        onClick: function () {
            var data = $('#grid_win_org_unit_right_list').datagrid('getChecked');
            var gnbmj = '';
            for (var i = 0; i < data.length; i++) {
                gnbmj = gnbmj + data[i].GNBM + ',';
            }
            var node = $('#tree_win_org_unit_right').tree('getSelected');
            var dwbm = node.id;

            $('#btn_win_org_unit_right_asign').linkbutton('disable');
            // 分配功能权限
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/organization/addDwGnQx',
                data: {
                    dwbm: dwbm,
                    gnbmj: gnbmj
                },
                dataType: "json",
                success: function (result) {
                    $('#btn_win_org_unit_right_asign').linkbutton('enable');

                    if(result.status == 200){

                        Alert(result.note);
                    }
                }
            });
        }
    });
}

// 加载单位功能权限列表
function load_grid_win_org_unit_right_list(dwbm) {
    $('#grid_win_org_unit_right_list').datagrid({
        method:'get',
        url:getRootPath()+ '/organization/getAllGnqx?dwbm=' + dwbm,
        loadFilter:function (result) {
            return result.status == 200?JSON.parse(result.value):[];
        }
    });
}

// 人员调岗
function trans_user_job(index) {
    // 获取选中人员信息
    var row = $('#grid_org_user_manage_list').datagrid('getRows')[index];

    // 加载基本信息
    $('#txt_win_org_user_edit_xm').textbox('setValue', row.MC);
    $('#txt_win_org_user_edit_xm').textbox('disable', 'true');
    // 加载部门下拉框
    load_cbt_win_org_user_edit_bm(current_select_unit, row.BMBM);
    // 加载角色下拉框
    load_cbx_win_org_user_edit_js(current_select_unit, row.BMBM, row.JSBM);

    //人员调岗 -- 按钮：取消
    $('#btn_win_org_user_edit_cancel').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#win_org_user_manage_edit').window('close');
        }
    });

    //人员调岗 -- 按钮：确定
    $('#btn_win_org_user_edit_confirm').linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            // JS对象
            var obj = new Object();
            obj.dwbm = current_select_unit;
            obj.bmbm_old = row.BMBM;
            obj.jsbm_old = row.JSBM;
            obj.bmbm = $('#cbt_win_org_user_edit_bm').combotree('getValue');
            obj.jsbm = $('#cbx_win_org_user_edit_js').combotree('getValue');
            obj.rygh = row.GH;

            $('#btn_win_org_user_edit_confirm').linkbutton('disable');
            // 移除岗位,增加岗位
            $.ajax({
                type: 'POST',
                url:getRootPath() +'/organization/transJob',
                data: JSON.stringify(obj),
                dataType: "json",
                contentType:'application/json',

                success: function (result) {
                    $('#btn_win_org_user_edit_confirm').linkbutton('enable');
                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    if(result.status == 200){

                        $('#win_org_user_manage_edit').window('close');
                        // 刷新人员列表
                        $('#grid_org_user_manage_list').datagrid('load');
                    }
                    Alert(result.note);
                }
            });
        }
    });

    $('#win_org_user_manage_edit').window('open');
}

// 加载人员调岗部门下拉框
function load_cbt_win_org_user_edit_bm(dwbm, bmbm) {
    $('#cbt_win_org_user_edit_bm').combotree({
        method: 'get',
        url: getRootPath()+'/organization/getBmList?dwbm=' + dwbm,
        onSelect: function (node) {
            $("#cbx_win_org_user_edit_js").combobox('clear');
            // 加载人员调岗角色下拉框
            load_cbx_win_org_user_edit_js(dwbm, node.id, '');
        },
        onLoadSuccess: function () {
            if (!isNull(bmbm))
                $('#cbt_win_org_user_edit_bm').combotree('setValue', bmbm);
        }
    });
}

// 加载人员调岗角色下拉框
function load_cbx_win_org_user_edit_js(dwbm, bmbm, jsbm) {
    $('#cbx_win_org_user_edit_js').combobox({
        valueField: 'id',
        textField: 'text',
        methond:'get',
        url: getRootPath()+'/organization/getJSInfoByDwBm?bmbm=' + bmbm + '&dwbm=' + dwbm,
        loadFilter:function (result) {
          return result.status == 200 ? JSON.parse(result.value):[];
        },

        onLoadSuccess: function () {
            if (isNull(jsbm)) {
                var data = $('#cbx_win_org_user_edit_js').combobox('getData');
                if (data.length > 0) {
                    $('#cbx_win_org_user_edit_js').combobox('select', data[0].id);
                }
            } else {
                $('#cbx_win_org_user_edit_js').combobox('select', jsbm);
            }
        }
    });
}

// 解除人员岗位
function remove_user_job(index) {
    // 清空所有选中的行，并让该行高亮
    $('#grid_org_user_manage_list').datagrid('clearSelections');
    $('#grid_org_user_manage_list').datagrid('highlightRow', index);

    Confirm('确认', '确认解除当前选中人员岗位？', function (r) {
        if (r) {
            var row = $('#grid_org_user_manage_list').datagrid('getRows')[index];
            // JS对象
            var obj = new Object();
            obj.dwbm = current_select_unit;
            obj.bmbm = row.BMBM;
            obj.jsbm = row.JSBM;
            obj.rygh = row.GH;

            // 移除岗位
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/organization/removeJob',
                data: JSON.stringify(obj),
                dataType: "json",
                contentType:'application/json',
                success: function (result) {

                    // 刷新人员列表
                    $('#grid_org_user_manage_list').datagrid('load');

                    // 对一般处理程序返回的数据，进行错误处理及数据过滤

                    if(result.status == 200){
                        Alert("解除人员岗位成功！");
                        // 刷新人员列表
                        $('#grid_org_user_manage_list').datagrid('load');
                    }
                    // doActionWithErrorHandle(result, function (data) {
                    //     Alert("解除人员岗位成功！");
                    //     // 刷新人员列表
                    //     $('#grid_org_user_manage_list').datagrid('load');
                    // });
                }
            });
        }
    });
}

// 通过ID展开节点
function focuse_expand_tree_org_dwbmjs(nodeID) {
    if (isNull(nodeID)) {
        return;
    }
    var rootNotes = $('#tree_org_dwbmjs').tree('getRoots'), children;
    for (var i = 0; i < rootNotes.length; i++) {
        if (rootNotes[i].id == nodeID || rootNotes[i].text == nodeID) {
            //如果匹配到则展开选中节点
            $('#tree_org_dwbmjs').tree('select', rootNotes[i].target);
            $('#tree_org_dwbmjs').tree('expand', rootNotes[i].target);
            return;
        }
        children = $('#tree_org_dwbmjs').tree('getChildren', rootNotes[i].tartget);
        for (var j = 0; j < children.length; j++) {
            if (children[j].id == nodeID || children[j].text == nodeID) {
                //如果匹配到则选中节点，展开父节点
                $('#tree_org_dwbmjs').tree('select', children[j].target);
                $('#tree_org_dwbmjs').tree('expandTo', children[j].target);
                return;
            }
        }
    }
}

// 截取角色编码，树形角色ID为 “部门编码 + 角色编码” 例：0101 + 001
function sub_jsbm(jsbm) {
    return jsbm.substr(4, 3);
}

// 合成树形角色ID
function add_jsbm(bmbm, jsbm) {
    return bmbm + jsbm;
}