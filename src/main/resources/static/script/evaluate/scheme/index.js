/**
 * 组织机构--评查方案列表
 */

$(document).ready(function () {
    $('#table_eval_pcfa_table').datagrid({
        width: 'auto',
        fitColumns: true,
        autoSizeColumn: true,
        striped: true,
        singleSelect: false,
        fit: true,
        toolbar: '#tool_eval_pcfa_grid_list',
        //checkOnSelect: false,
        //loadMsg: '数据加载中，请稍后...',
        //pagination: true,
        rownumbers: true,
        idField: 'PCHDBM',
        multiSort:true,
        remoteSort:false,
        columns:[[
            {field:"PCHDBM",title:"评查活动编码",hidden:true},
            {field:"PCHDMC",title:"评查活动",width:100},
            {field:"PCFLMC",title:"评查分类",width:100},
            {field:"CJSJ",title:"创建时间",width:100, sortable:true,
                formatter: function (value) { return sjzh(value);},
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
            {field:"HDZT_MS",title:"活动状态",width:100},
            {field:"opt",title:"操作",width:70,formatter:optFormatter}
        ]]
    });

    load_table_eval_pcfa_table();

    // 查询
    $("#btn_eval_pcfa_grid_search").unbind( "click" );
    $("#btn_eval_pcfa_grid_search").bind("click", function () {
        load_table_eval_pcfa_table();
    });

});

// 加载评查案件列表
function load_table_eval_pcfa_table() {

    // JS对象
    var obj = new Object();
    obj.keyword = $('#txt_eval_pcfa_grid_ajmc').val();

    $('#table_eval_pcfa_table').datagrid({
        url: getRootPath()+'/handle/getPchdList',
        queryParams: { json : JSON.stringify(obj) }
    });
}

//格式化显示操作列
function optFormatter(value,row,index){
	return '<a href="#" onclick="goto_eval_pcfa_page('+row.PCHDBM+')">方案</a>';
}


//点击方案调用
goto_eval_pcfa_page = function (pchdbm) {

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
            param.PCMBBM = param.PCMBJ;

            var url = "view/evaluate/build/special.html";
            load_function("专项评查 > 评查方案", url, param);
        }
    });
}


