$(function() {

    // 案件列表DataGrid初始化
    $('#grid_approve_list').datagrid({
        width: 'auto',
        fitColumns: true,
        autoSizeColumn: true,
        striped: true,
        singleSelect: false,
        fit: true,
        toolbar: '#tool_eval_approve_grid_list',
        // checkOnSelect: false,
        // loadMsg: '数据加载中，请稍后...',
        // pagination: false,
        rownumbers: true,
        idField: 'ID',
        multiSort:true,
        remoteSort:false,
        // pageSize: 20,
        // pageList: [10, 20, 30, 50, 100],
        columns: [[
            {field: 'SPLX', title: '审批类型', width: 120,sortable:true},
            {field: 'MC', title: '案件名称', width: 150,sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'SSRDWMC', title: '送审人单位', width: 140, sortable:true},
            {field: 'SSRXM', title: '送审人', width: 70,sortable:true},
            {
                field: 'SSRQ', title: '送审时间', width: 110, sortable:true,
                formatter: function (value) {
                    return sjzh(value);
                },
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }

                },
            },
            {
                field: 'action', title: '操作', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='#' onclick=\"show_eval_approve_window("
                        + index + ")\">审批</a>";
                }
            }]],
        groupField: 'SPLX',
        view: groupview,
        groupFormatter: function (value, rows) {
            return value + '(' + rows.length + ')';
        },
        onClickRow: function (rowIndex, rowData) {
            $('#grid_approve_list').datagrid('clearSelections');
            $('#grid_approve_list').datagrid('highlightRow',
                rowIndex);
        }
    });

	// 查询
	$("#btn_eval_approval_grid_search").unbind("click");
	$("#btn_eval_approval_grid_search").bind("click", function() {
		load_grid_eval_handle_list();
	});

    load_grid_eval_handle_list();
});

// 加载评查案件列表
function load_grid_eval_handle_list() {

    // JS对象
    var obj = new Object();
    obj.keyword = $('#txt_eval_approval_grid_ajmc').val();

    $('#grid_approve_list').datagrid({
        url : getRootPath() + '/handle/get_splst',
        queryParams: { json : JSON.stringify(obj) }
    });
}

// 评查审批弹出框
function show_eval_approve_window(index) {
    var rows = $('#grid_approve_list').datagrid('getRows');
    var row = rows[index];

    //1.评查案件 2.评查报告 3.评查方案
    switch(row.SPWJLX){
        case "1":
            goto_eval_handle_page(row.PCSPBM, row.SPWJBM, row.SPJSMC);
            break;
        case "2":
            goto_eval_pcbg_page(row.SPWJBM,row.PCSPBM);
            break;
        case "3":
            goto_eval_pcfa_page(row.SPWJBM);
            break;
        default:
            break;
    }
}

// 跳转到评查方案界面
function goto_eval_pcfa_page(pchdbm) {

    $.ajax({
        url: getRootPath() + "/manage/getPcyInfo",
        data: {pchdbm: pchdbm},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (data) {
            if (data.status != 200) {
                Alert(data.note);
                return;
            }

            var param = data.value;
            // JS对象,需要传输的值
            var obj = new Object();
            obj.PCFLBM = param.PCFLBM;
            obj.PCMBBM = param.PCMBJ;
            obj.PCHDBM = param.PCHDBM;

            // var url = "view/evaluate/build/special.html" + "?json=" + JSON.stringify(obj);
            var url = "view/evaluate/build/special.html";
            load_function("专项评查 > 评查方案", url, param);
        }
    });
}

// 跳转到评查办理界面
function goto_eval_handle_page(pcspbm, pcslbm, spjsmc) {

    var obj = new Object();
    obj.PCSLBM = pcslbm;
    obj.PCCZLX = '2'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = pcspbm; //仅评查审批阶段有
    obj.PCYJMC = spjsmc; //评查意见名称，案管负责人/分管副检察长
    var url = "view/evaluate/handle/deal.html"
    load_function("评查审批", url, obj)
}

function goto_eval_pcbg_page(spwjbm,pcspbm) {

    var obj = new Object();
    obj.SPWJBM = spwjbm;
    obj.PCSPBM = pcspbm; //仅评查审批阶段有
    var url = "view/evaluate/report/report.html";
    load_function("报告审批", url, obj);

}