/**
 * Created by user on 2017/11/4.
 */
$(function() {
    // 案件列表DataGrid初始化
    $('#grid_dptfeedback_list').datagrid({
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        idField: 'ID',
        columns: [[
            {field: 'PCSLBM', title: '评查受理编码', hidden: true},
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCHDBM', title: '评查活动编码', hidden: true},
            {field: 'BPC_DWBM', title: ' 业务部门领导单位编码', hidden: true},
            {field: 'BPC_GH', title: ' 业务部门领导工号', hidden: true},
            {field: 'BMSAH', title: '部门受案号', width: 120},
            {field: 'AJMC', title: '案件名称', width: 80,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'AJLB_MC', title: '案件类别名称', width: 60},
            {field: 'BPC_DWMC', title: '承办单位', width: 80},
            {field: 'BPC_BMMC', title: '承办部门', width: 100},
            {field: 'BPC_MC', title: '承办人', width: 80},
            {
                field: 'CJSJ', title: '创建时间', width: 100,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {
                field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="goto_eval_handle_page(\'' + row.PCSLBM + '\')">反馈</a> ';
                    return r;
                }
            }]],
        onClickRow: function (rowIndex, rowData) {
            $('#grid_dptfeedback_list').datagrid('clearSelections');
            $('#grid_dptfeedback_list').datagrid('highlightRow', rowIndex);
        }
    });

    // 查询
    $("#btn_eval_handle_grid_search").unbind("click");
    $("#btn_eval_handle_grid_search").bind("click", function () {
        load_grid_dptfeedback_list();
    });

    // 加载评查案件列表
    load_grid_dptfeedback_list();
});

// 初始数据加载
function load_grid_dptfeedback_list() {
    // JS对象
    var obj = new Object();
    obj.keyword = $('#txt_eval_handle_grid_ajmc').val();
    $('#grid_dptfeedback_list').datagrid({
        url : getRootPath() + '/handle/get_cbbmfklist',
        queryParams : {
            json : JSON.stringify(obj)
        }
    });
}

// 跳转到评查办理界面
function goto_eval_handle_page(pcslbm) {

    var obj = new Object();
    obj.PCSLBM = pcslbm;
    obj.PCCZLX = '4'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = 'BMFZRYJ'; //仅评查审批阶段有
    obj.PCYJMC = '部门负责人'; //评查意见名称
    var url = "view/evaluate/handle/deal.html"
    load_function("评查反馈", url, obj)
}

