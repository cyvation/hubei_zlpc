/**
 * Created by cjc on 2017/12/6.
 */
$(document).ready(function () {
    $('#flow_datagrid').datagrid({
        striped: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        resizable:false,
        // idField: 'GNBM',
        columns: [[
            {field: 'LCSLBM', title: '流程实例编码', hidden:true},
            {field: 'BMSAH', title: '部门受案号',width:100,hidden:true},
            {field: 'WTDMS', title: '问题点描述', width:80},
            {field: 'WTFL', title: '问题分类', width:70},
            {field: 'JKCLFS', title: '监控处理方式', width:100},
            {field: 'JKCZSJ', title: '监控处置时间', width:100},
            {field: 'CBRZGSJ', title: '承办人整改时间',width:120},
            {field: 'CBRSFZG', title: '承办人是否整改',width:120}
        ]]
    });
})