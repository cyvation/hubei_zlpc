
$(document).ready(function () {

    // 评查状态树
    $('#cbt_eval_handle_grid_pczt').combotree({
        editable: false,
        multiple: true,
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/filter/getPczt',
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1){
                index_addMousedownDiv(this,'cbt_eval_handle_grid_pczt');
            }

        }

    });

    // 案件列表DataGrid初始化
    $('#grid_eval_handle_list').datagrid({
        width: 'auto',
        fitColumns: true,
        autoSizeColumn: true,
        striped: true,
        singleSelect: false,
        fit: true,
        toolbar: '#tool_eval_handle_grid_list',
        rownumbers: true,
        idField: 'PCSLBM',
        // sortOrder
        multiSort:true,
        remoteSort:false,

    columns: [[
            { field: 'PCSLBM', title: '评查受理编码', hidden: true },
            { field: 'PCSAH', title: '评查案号', width: 160, sortable:true },
            { field: 'PCSS', title: '评查类别', width: 170, sortable:true},
            { field: 'PCHDBM', title: '评查活动编码', hidden: true},
            { field: 'AJMC', title: '案件名称', width: 160, sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            { field: 'BMSAH', title: '部门受案号', width: 160, sortable:true },
            { field: 'AJLB_BM', title: '案件类别编码', hidden: true},
            { field: 'AJLB_MC', title: '案件类别名称', width: 90, sortable:true },
            { field: 'BPC_BMMC', title: '承办部门', width: 90,sortable:true},
            { field: 'BPC_MC', title: '承办检察官', width: 90,sortable:true},
            { field: 'PCDWBM', title: '评查单位编码', hidden: true },
            { field: 'PCR_MC', title: '评查员', hidden: true },
            { field: 'CJSJ', title: '评查日期',  fixed:true, width: 115 ,sortable:true,
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
                formatter: function (value) { return sjzh(value);}
            },
            { field: 'PCJDBH', title: '评查节点编号', hidden: true },
            { field: 'PCJDMS', title: '评查状态', width: 100, sortable:true  },
            { field: 'PCFLBM', title: '评查分类编码', hidden: true },
            { field: 'PCFLMC', title: '评查分类名称', hidden: true },
            { field: 'action', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="goto_eval_handle_page(' + index + ')">办理</a>';
                    return e;
                }
            }
        ]],
        groupField: 'PCFLMC',
        view: groupview,
        groupFormatter: function (value, rows) {
            return value + '(' + rows.length + ')';
        },
        onClickRow: function (rowIndex, rowData) {
            $('#grid_eval_handle_list').datagrid('clearSelections');
            $('#grid_eval_handle_list').datagrid('highlightRow', rowIndex);
        }
    });

    // 查询
    $("#btn_eval_handle_grid_search").unbind( "click" );
    $("#btn_eval_handle_grid_search").bind("click", function () {
        load_grid_eval_handle_list();
    });

    // 加载评查案件列表
    load_grid_eval_handle_list();

});

// 加载评查案件列表
function load_grid_eval_handle_list() {

    // JS对象
    var obj = new Object();
    obj.keyword = $('#txt_eval_handle_grid_ajmc').val();
    obj.PCZT = $('#cbt_eval_handle_grid_pczt').combotree('getValues').join(",");

    $('#grid_eval_handle_list').datagrid({
        url: getRootPath()+'/handle/get_pclist',
        queryParams: { json : JSON.stringify(obj) }
    });
}

// 跳转到评查办理界面
function goto_eval_handle_page(index) {

    var rowDatas = $('#grid_eval_handle_list').datagrid('getRows');

    var obj = new Object();
    obj.PCSLBM = rowDatas[index].PCSLBM;
    obj.PCCZLX = '1'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = 'PCYYJ'; //仅评查审批阶段有
    obj.PCYJMC = '评查员'; //评查意见名称
    var url = "view/evaluate/handle/deal.html";
    load_function("评查办理", url, obj);

}