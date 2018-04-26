/**
 * Created by user on 2017/11/4.
 */
$(function () {

    // 案件列表DataGrid初始化
    $('#grid_feedback_list').datagrid({
        width: 'auto',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        idField: 'ID',
        toolbar: '#tool_feedback_list',
        multiSort:true,
        remoteSort:false,
        columns: [[
            { field: 'AJMC', title: '案件名称', width: 160,
                formatter: function (value) { return tipMessage(value); }},
            { field: 'BMSAH', title: '部门受案号', width: 160},
            { field: 'AJLB_BM', title: '案件类别编码', hidden: true},
            { field: 'AJLB_MC', title: '案件类别名称', width: 90, sortable:true},
            { field: 'PCDWBM', title: '评查单位编码',width: 90 },
            { field: 'PCR_MC', title: '评查员',width: 90, sortable:true },
           /* { field: 'BPC_BMMC', title: '承办部门', width: 90},
            { field: 'BPC_MC', title: '承办检察官', width: 90},*/

            { field: 'CJSJ', title: '评查日期',  fixed:true, width: 115, sortable:true,
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

                }
            },
            { field: 'PCJDBH', title: '评查节点编号', hidden: true },
            { field: 'action', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="goto_eval_handle_page(\'' + row.PCSLBM + '\')">反馈</a> ';
                    return r;
                }
            }
        ]],
        onClickRow: function (rowIndex, rowData) {
            $('#grid_feedback_list').datagrid('clearSelections');
            $('#grid_feedback_list').datagrid('highlightRow', rowIndex);
        }
    });

    // 查询
    $("#btn_feedback_search").unbind( "click" );
    $("#btn_feedback_search").bind("click", function () {
        load_feedback_list();
    });

    // 加载评查案件列表
    load_feedback_list();
})

// 加载评查案件列表
function load_feedback_list() {

    // JS对象
    var obj = new Object();
    obj.keyword = $('#txt_feedback_ajmc').val();

    $('#grid_feedback_list').datagrid({
        url: getRootPath()+'/handle/get_cbrfklist',
        queryParams: { json : JSON.stringify(obj) }
    });
}

// 跳转到评查办理界面
function goto_eval_handle_page(pcslbm) {

    var obj = new Object();
    obj.PCSLBM = pcslbm;
    obj.PCCZLX = '3'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = 'CBJCGYJ'; //仅评查审批阶段有
    obj.PCYJMC = '承办检察官'; //评查意见名称
    var url = "view/evaluate/handle/deal.html"
    load_function("评查反馈", url, obj)
}


