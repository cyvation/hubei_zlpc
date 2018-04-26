/**
 * Created by user on 2017/11/4.
 */
var bmarr = [];
$(function () {

    //控件初始化及初始数据加载
    init_tool_eval_pool_list();

    //初始化人员库列表
    init_grid_eval_pool_list();


    $('.radiopcs').click(function () {
        $(this).children().addClass('redio_click_no');
        $(this).siblings().children().removeClass('redio_click_no');
        // var type =  $(this).attr("data-value");
        var type = $(".radiopcs .redio_click_no").attr('data-value');
        if (type == '1') {
            load_function("评查管理","view/manage/manage/index.html");
        }
        if (type == '2') {
            load_function("评查管理","view/manage/manage/case.html");
        }
        if (type == '3') {
            return;
        }
    });

})

// 初始化评查人员库列表工具
function init_tool_eval_pool_list() {

    //查询人员
    $('#btn_eval_pool_search').linkbutton({
        onClick: function () {
            load_grid_eval_pool_list();
        }
    });

    // 添加人员
    $('#btn_eval_pool_add').linkbutton({
        onClick: function () {

            // 弹出窗信息初始化及数据加载
            init_win_eval_pool_add();
        }
    });

    //删除人员库人员
    $('#btn_eval_pool_delete').linkbutton({
        onClick: function () {
            delete_eval_pool_evaluators();
        }
    });
    $("#cbx_win_eval_pool_type").combobox({
        editable:false,
        width:120,
        valueField: 'type',
        textField: 'text',
        data: [{
            type: '1',
            text: '全部人员'
        }, {
            type: '2',
            text: '人员库人员'
        }],
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (data) {
            index_addMousedownDiv($(this).combobox("panel").children().eq(0)[0],"cbx_win_eval_pool_type")

        }
    });

}

// 初始化人员库列表
function init_grid_eval_pool_list() {

    $("#cbt_win_eval_pool_jsmc").combobox({
        editable:true,
        limitToList:true,
        valueField:'id',
        textField: 'text',
        // onShowPanel: index_onShowPanel,
        // onHidePanel: index_onHidePanel,
        url: getRootPath()+'/manage/getJsmc',
        onLoadSuccess: function (node, data) {
            // index_addMousedownDiv(this,'cbt_win_eval_pool_jsmc');
        }
    });

    $('#grid_eval_pool_list').datagrid({
        striped: true,
        fitColumns: true,
        singleSelect: false,
        pagination: true,
        rownumbers: true,
        fit: true,
        idField: 'ID',
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        columns: [[
            { field: 'CK', width: 30, checkbox: true },
            { field: 'ID', title: '唯一标示', hidden: true },
            { field: 'DWMC', title: '单位名称', width: 120 },
            { field: 'MC', title: '姓名', width: 100 },
            { field: 'BMMC', title: '部门名称', width: 120 },
            { field: 'JSMC', title: '角色名称', width: 110 },
            { field: 'GZZH', title: '工作证号', width: 110 },
   /*         { field: 'YDDHHM', title: '联系电话', width: 120 },*/
            { field: 'DZYJ', title: '电子邮件', width: 120 },
            {
                field: 'action', title: '操作', width: 50, align: 'center',
                formatter: function (value, row, index) {
                    var d = '<a href="#" onclick="delete_eval_pool_evaluator(' + index + ')">删除</a> ';
                    return d;
                }
            }
        ]],
        loadMsg: '数据加载中，请稍后...',
        onClickRow: function (rowIndex, rowData) {
            $('#grid_eval_pool_list').datagrid('clearSelections');
            $('#grid_eval_pool_list').datagrid('highlightRow', rowIndex);
        }
    });

    $('#grid_eval_pool_list').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 设置人员库列表大小
    resize_grid_eval_pool_list();

    // 加载人员库列表
    load_grid_eval_pool_list();
}

// 加载人员库列表
function load_grid_eval_pool_list() {
    var mc = trim($('#txt_eval_pool_xm').textbox('getValue'));

    // JS对象
    var obj = new Object();
    obj.DWBM_RY = '';
    obj.YWBM = '';
    obj.PCR_MC = mc;

    $('#grid_eval_pool_list').datagrid({
        url:getRootPath()+ '/manage/get_pckry',
        queryParams: {
        name: 'easyui',
            json: JSON.stringify(obj)
    },

    // json=' + JSON.stringify(obj)

    });
    resize_grid_eval_pool_list();
}

// 设置人员库列表大小
function resize_grid_eval_pool_list() {
    var width = $('#pnl_eval_pool_list').width();
    $('#grid_eval_pool_list').datagrid('options').width = width - 10;
    var height = $('#pnl_eval_pool_list').height();
    var h = $('#tool_eval_pool_list').height() + 10;
    $('#grid_eval_pool_list').datagrid('options').height = height - h;
    $('#grid_eval_pool_list').datagrid('resize');
}

//////////////////////////////*组织机构人员信息加载*/////////////////////////////

function dgDwbm(bmarr,children) {
    if(children){
        for(a in children){
            bmarr.push(children[a].id);
            dgDwbm(bmarr,children[a].children);
        }
    }
}

// 初始化人员添加弹出窗
function init_win_eval_pool_add() {

    //清理组织机构人员窗口缓存
    $('#win_eval_pool_add').window({
        onClose: function () {
            $('#txt_win_eval_pool_mc').textbox('clear');
        }
    });

    // 单位编码ComboTree初始化
    $('#cbt_win_eval_pool_xzdw').combotree({
        method: 'get',
        multiple: true,
        animate: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },
        onLoadSuccess: function (node,data) {
           // var bmarr = [];
            for(o in data) {
                bmarr.push(data[o].id);
                dgDwbm(bmarr, data[o].children);
                $('#cbt_win_eval_pool_xzdw').combotree('setValues', bmarr);
            }
            // if ($('#cbt_win_eval_pool_xzdw').combobox('getValue') == "") {
            //     $('#cbt_win_eval_pool_xzdw').combotree('setValue', userInfo.DWBM); //单位默认选择
            // }
            index_addMousedownDiv(this,'cbt_win_eval_pool_xzdw');
        }
    });

    // 添加人员提交按钮事件
    $('#btn_win_eval_pool_asign').linkbutton({
        onClick: function () {

            add_eval_pool_evaluators();

        }
    });
    $('#win_eval_pool_add').window('open');

    // 新增人员列表，子弹出框grid
    init_grid_eval_win_list();
}

// 新增人员列表，子弹出框grid
function init_grid_eval_win_list() {

    //初始化组织机构人员表格
    $('#grid_win_eval_pool_user_list').datagrid({
        striped: true,
        fitColumns: true,
        singleSelect: false,
        pagination: true,
        rownumbers: true,
        fit: true,
        idField: 'ID',
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        pagination: {
            beforePageText: '第',
            afterPageText: '页   共{pages}页',
            displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
        },
        columns: [[
            { field: 'CK', width: 80, checkbox: true },
            { field: 'ID', title: '唯一标示', hidden: true },
            { field: 'DWMC', title: '单位名称', width: 100 },
            { field: 'MC', title: '姓名', width: 80 },
            { field: 'BMMC', title: '部门名称', width: 100 },
            { field: 'JSMC', title: '角色名称', width: 100 },
            { field: 'DZYJ', title: '电子邮件', width: 130 }
        ]]
    });

    // 重置高度
    resize_grid_win_eval_pool_user_list();

    // 组织机构人员查询
    load_grid_eval_win_list();
}

// 重置弹出窗口选择人员信息的高度\宽度
function resize_grid_win_eval_pool_user_list() {
    var height = $('#pnl_win_eval_pool_user_list').height();
    var width = $('#pnl_win_eval_pool_user_list').width();
    var h = $('#tool_win_eval_pool_add').height();
    $('#grid_win_eval_pool_user_list').datagrid('options').width = width;
    $('#grid_win_eval_pool_user_list').datagrid('options').height = height - h;
    $('#grid_win_eval_pool_user_list').datagrid('resize');
}

// 组织机构人员查询
function load_grid_eval_win_list() {

    //组织机构人员查询
    $('#btn_win_eval_pool_search').linkbutton({
        onClick: function () {
            var mc = $('#txt_win_eval_pool_mc').textbox("getValue").trim();
            var jsmc = $('#cbt_win_eval_pool_jsmc').textbox("getText").trim();
            var temp = $('#cbt_win_eval_pool_xzdw').combotree('getValues').join(',');
            if(temp == null || temp ==''){
                $('#cbt_win_eval_pool_xzdw').combotree('setValues', bmarr);
            }
            var dwbm = $('#cbt_win_eval_pool_xzdw').combotree('getValues').join(',');
            var type = $('#cbx_win_eval_pool_type').combobox("getValue");
            load_grid_win_eval_pool_user_list(dwbm, mc, type, jsmc);
        }
    });
}

// 载入组织机构人员grid数据
function load_grid_win_eval_pool_user_list(dwbm, mc, type, jsmc) {

    // JS对象
    var obj = new Object();
    obj.PCR_MC = mc;
    obj.DWBM_RY = dwbm;
    obj.TYPE = type;
    obj.YWBM = '00';
    obj.BMBM = '';
    obj.JSMC = jsmc;

    $('#grid_win_eval_pool_user_list').datagrid({
        url:getRootPath()+ '/manage/get_zzjgry',
        queryParams: {
            json: JSON.stringify(obj)
        }
    });
}

// 添加评查人员到评查人员库
function add_eval_pool_evaluators() {
    var rows = $('#grid_win_eval_pool_user_list').datagrid('getChecked');
    if (rows.length == 0) {
        Alert("请选择人员！");
        return;
    }
    var ghstr = "";
    var array = new Array();
    for (var i = 0; i < rows.length; i++) {
        if (ghstr.indexOf(rows[i].GH) < 0) {
            ghstr += rows[i].GH + ",";
            // JS对象
            var obj = new Object();
            obj.DWBM_RY = rows[i].DWBM;
            obj.PCR_GH = rows[i].GH;
            obj.BMBM=rows[i].BMBM;
            obj.BMMC=rows[i].BMMC;
            obj.JSBM=rows[i].JSBM;
            obj.JSMC=rows[i].JSMC;
            obj.YWBM = '00';
            array[i] = obj;
        }
    }

    $('#btn_win_eval_pool_asign').linkbutton('disable');
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/manage/add_pcryk',
        data: {  json: JSON.stringify(array) },
        dataType: "json",
        success: function (result) {
            $('#btn_win_eval_pool_asign').linkbutton('enable');

            // 对一般处理程序返回的数据，进行错误处理及数据过滤
            doActionWithErrorHandle(result, function (data) {
                Alert("人员添加成功！");
                //刷新数据
                $('#grid_win_eval_pool_user_list').datagrid('clearChecked');
                $('#grid_eval_pool_list').datagrid('load');
                $('#win_eval_pool_add').window('close');
            });
        }
    });
}

//删除人员操作
function delete_eval_pool_evaluator(index) {
    var rows = $('#grid_eval_pool_list').datagrid('getRows');

    $.messager.confirm('确认?', '您确定要删除当前选中的评查员?', function (p) {
        if (p) {
            var array = new Array();
            // JS对象
            var obj = new Object();
            obj.RYK_DWBM = rows[index].RYK_DWBM;
            obj.DWBM_RY = rows[index].DWBM;
            obj.YWBM = '00';
            obj.PCR_GH = rows[index].GH;
            array[0] = obj;
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/manage/del_rykry',
                data: { action: 'DeleteRykInfo', json: JSON.stringify(array) },
                dataType: "json",
                success: function (result) {
                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    doActionWithErrorHandle(result, function (data) {
                        Alert("删除人员成功！");
                        //刷新数据
                        $('#grid_eval_pool_list').datagrid('clearChecked');
                        $('#grid_eval_pool_list').datagrid('load');
                    });
                }
            });
        }
    });

}

// 批量删除评查库人员
function delete_eval_pool_evaluators() {
    var rows = $('#grid_eval_pool_list').datagrid('getChecked');

    $.messager.confirm('确认?', '您确定要删除当前选中的评查员?', function (p) {
        if (p) {
            var array = new Array();
            for (var i = 0; i < rows.length; i++) {
                // JS对象
                var obj = new Object();
                obj.RYK_DWBM = rows[i].RYK_DWBM;
                obj.DWBM_RY = rows[i].DWBM;
                obj.YWBM = '00';
                obj.PCR_GH = rows[i].GH;
                array[i] = obj;
            }
            $.ajax({
                type: 'POST',
                url: getRootPath()+'/manage/del_rykry',
                data: { action: 'DeleteRykInfo', json: JSON.stringify(array) },
                dataType: "json",
                success: function (result) {
                    // 对一般处理程序返回的数据，进行错误处理及数据过滤
                    doActionWithErrorHandle(result, function (data) {
                        Alert("删除人员成功！");

                        $('#grid_eval_pool_list').datagrid('clearChecked');
                        //刷新数据
                        $('#grid_eval_pool_list').datagrid('load');
                    });
                }
            });
        }
    });
}