/**
 * Created by user on 2017/11/4.
 */
$(function () {
    // 案件列表DataGrid初始化
    $('#grid_notice_list').datagrid({
        width: 'auto',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        toolbar: '#tool_notice_list',
        idField: 'ID',
        multiSort:true,
        remoteSort:false,
        columns: [[
            { field: 'BMSAH', title: '评查案号', width: 120, sortable:true,},
            { field: 'AJMC', title: '案件名称', width: 120, sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            { field: 'PCSAH', title: '部门受案号', width: 120, sortable:true,},
            { field: 'PCRDWMC', title: '评查人单位名称', width: 90, sortable:true,},
            /*{ field: 'BPCMC', title: '承办检察官', width: 90},*/
            { field: 'PCRMC', title: '评查员', width: 90, sortable:true},
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
            { field: 'PCJG', title: '评查结果', width: 90, sortable:true},
            { field: 'action', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="goto_eval_handle_page(' + index + ',\'' + row.PCSLBM + '\')">查看</a> ';
                    return r;
                }
            }
        ]],
        onClickRow: function (rowIndex, rowData) {
            $('#grid_notice_list').datagrid('clearSelections');
            $('#grid_notice_list').datagrid('highlightRow', rowIndex);
        }
    });
    $("#btn_notice_search").bind("click", function () {
        load_notice_list();
    });
    load_notice_list();
})

// 加载评查案件列表
function load_notice_list() {
    var obj = new Object();
    obj.ajmc = $('#txt_notice_ajmc').val();
    $.ajax({
        url:getRootPath()+'/message/getMessagePcEndList',
        type:'get',
        data:obj,
        success:function (data) {
            var json=JSON.parse(data);
            var result=data.value;
            $('#grid_notice_list').datagrid('loadData',json.value);
        }
    })
}

// 跳转到评查办理界面
function goto_eval_handle_page(index, pcslbm) {

    var obj = new Object();
    obj.PCSLBM = pcslbm;
    obj.PCCZLX = '3'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = 'CBJCGYJ'; //仅评查审批阶段有
    obj.PCYJMC = '承办检察官'; //评查意见名称
    $.ajax({
        url:getRootPath()+'/message/updateMessageZt',
        type:'get',
        data:obj,
        success:function (data) {
            //console.log(data)
        }
    });

    pcWinPage(index,'#grid_notice_list');
}


