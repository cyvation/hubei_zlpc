$(document).ready(function () {

    // 界面标签样式设置及事件绑定
    manager_keypoint_marksInit();

});

//界面初始化及时间绑定
function manager_keypoint_marksInit() {
    //评查管理模块选择
    // $("#combo_manage_manage_choose").combobox({
    //     editable: false,
    //     textField: "text",
    //     valueField: "id",
    //     value: '评查活动管理',
    //     data: [{
    //         id: '3',
    //         text: '评查活动管理'
    //     },{
    //         id: '2',
    //         text: '评查案件管理'
    //     },{
    //         id: '1',
    //         text: '评查人员管理'
    //     }],
    //     onSelect: function (record) {
    //         if (record.id == '1') {
    //             load_function("评查管理","view/manage/manage/person.html");
    //         }
    //         if (record.id == '2') {
    //             load_function("评查管理","view/manage/manage/material.html");
    //         }
    //         if (record.id == '3') {
    //             return;
    //         }
    //
    //     }
    // });
    $('.radiopcs').click(function () {
        $(this).children().addClass('redio_click_no');
        $(this).siblings().children().removeClass('redio_click_no');
        // var type =  $(this).attr("data-value");
        var type = $(".radiopcs .redio_click_no").attr('data-value');
        if(type == '1'){
            load_function("评查管理","view/manage/manage/person.html");
        } if (type== '2') {
            load_function("评查管理","view/manage/manage/case.html");
        }
        if (type == '3') {
            return;
        }
    });

    //评查分类
    $("#combo_manage_manage_pcfl").combobox({
        textField: "text",
        valueField: "id",
        editable: false,
        url: getRootPath() + "/manage/getpcfl",
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        loadFilter: function (result) {

            var data = new Array();
            if(result != null && result.length >= 1){
                for(var i = 0; i < result.length; i++){
                    if(result[i].attributes.SFJS == "Y"){
                        data.push(result[i]);
                    }
                }
            }

            return data;
        },
        onLoadSuccess: function (data) {
            if (data != null && data.length >= 1){
                //$('#combo_manage_manage_pcfl').combobox('setValue', data[0].id);

                try {
                    load_table_manage_manage_pcgl_DataGrid();
                }catch (e){

                }
            }
            index_addMousedownDiv($(this).combobox("panel").children().eq(0)[0],"combo_manage_manage_pcfl")
        }
        //data:[{"id":"003","text":"专项评查"}]
    });


    //开始时间
    $("#date_manage_manage_kssj").datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    //结束时间
    $("#date_manage_manage_jssj").datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    //评查管理表格
    $("#table_manage_manage_pcgl").datagrid({
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        loadMsg: '数据加载中，请稍等。。。',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers:true,
        multiSort:true,
        remoteSort:false,
        loadFilter: function (data) {
           return data;
        },
        columns: [[
            {field: 'PCHDBM', title: '评查活动编码',  width: 0, hidden: true},
            {field: 'PCFLBM', title: '评查分类编码',  width: 0, hidden: true},
            {field: 'PCHDMC', title: '评查活动名称',  width: 300, sortable:true},
            {field: 'PCFLMC', title: '评查分类', width: 300,
                formatter: function (value, row, index){
                    var pcflmc = $("#combo_manage_manage_pcfl").combobox('getText');
                    if(isNull(pcflmc)){
                        var data = $("#combo_manage_manage_pcfl").combobox('getData');
                        for (var i = 0; i < data.length; i++){
                            if(row.PCFLBM == data[i].id){
                                pcflmc = data[i].text;
                                break;
                            }
                        }
                    }
                    return pcflmc;
                }},
            {field: 'CJRMC', title: '创建人',  width: 150, sortable:true},
            {field: 'CJSJ', title: '创建时间',  width: 300, sortable:true,
                formatter: function (value){ return formatCst(value);},
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
            {field: 'QDSJ', title: '启动时间',  width: 300,
                formatter: function (value){return formatCst(value);},sortable:true,
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
            {field: 'QDRMC', title: '启动人',  width: 200, sortable:true},
            {field: 'JSSJ', title: '结束时间', width: 300,sortable:true,
                formatter: function (value){
                    // return sjzh(value);
                    return formatCst(value);
                }},
            {field: 'JSRMC', title: '结束人',  width: 200, sortable:true},
            {
                field: 'CZ',
                title: '操作',
                align: 'center',
                width: 220,
                formatter: function (value, row, index) {
                    var rowDatas = $('#table_manage_manage_pcgl').datagrid('getRows');
                    var pchdbm = rowDatas[index].PCHDBM;
                    var r = '<a href="#" onclick="table_manage_manage_scheme(' + pchdbm + ')">方案</a> ';
                    /*var d = '<a href="#" onclick="table_manage_manage_finish(' + index + ')">结束</a> ';*/
                    var e = '<a href="#" onclick="table_manage_manage_drop(' + pchdbm + ')">删除</a> ';
                    return r + e;
                }
            }
        ]]
    });


    //初始化datagrid
    function init_manage_manage_table() {
        $("#table_manage_manage_pcgl").datagrid({
            url: ''
        });
    }

    //点击查询
    $("#btn_manage_manage_serch").click(function () {
        load_table_manage_manage_pcgl_DataGrid();
    });

    //加载datagrid数据列表
    function load_table_manage_manage_pcgl_DataGrid() {
        var pcfl = $("#combo_manage_manage_pcfl").combobox("getValue");
        var kssj = $("#date_manage_manage_kssj").datebox("getValue");
        var jssj = $("#date_manage_manage_jssj").datebox("getValue");

        var options = $('#table_manage_manage_pcgl').datagrid("options");
        options.url = getRootPath()+'/manage/query_PC_HD';

        $("#table_manage_manage_pcgl").datagrid("reload", {pcflbm:pcfl,cjsjStart:kssj,cjsjend:jssj});
    }


    //点击方案调用
    table_manage_manage_scheme = function(pchdbm) {

        $.ajax({
            url: getRootPath() + "/manage/getPcyInfo",
            data: { pchdbm: pchdbm},
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
                var title = "";
                var url = "";
                //湖北：根据不同的评查分类跳转到不同的页面
                if(obj.PCFLBM == "008"){
                    title = "重点评查 > 评查方案";
                    url = "view/evaluate/build/blend.html";
                }else{
                    title = "专项评查 > 评查方案";
                    url = "view/evaluate/build/special.html";
                }
                load_function(title, url, param);
            }
        });
    }

    //点击结束调用
    table_manage_manage_finish = function (index) {

        var rowDatas = $('#table_manage_manage_pcgl').datagrid('getRows');
        var param = JSON.stringify(rowDatas[index]);

        Confirm("确认", "是否结束？", function (r) {
            if (r) {
                $.ajax({
                    url: getRootPath() + "/manage/finish_pchd",
                    data: { json: param},
                    type: 'post',
                    async: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.status == 200){
                            load_table_manage_manage_pcgl_DataGrid();

                            return data.value;
                        }else {
                            Alert(data.note);
                        }
                    }
                });
            }
        });
    }

    //点击删除调用
    table_manage_manage_drop = function (pchdbm) {
        Confirm("确认", "是否删除？", function (r) {
            if (r) {
                $.ajax({
                    url: getRootPath() + "/manage/delPchd",
                    data: {pchdbm: pchdbm},
                    type: 'post',
                    async: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.status == 200) {
                            Alert('删除评查活动成功');

                            load_table_manage_manage_pcgl_DataGrid();
                            return data.value;

                        } else {
                            Alert(data.note);
                        }
                    }
                });
            }
        });
    }
}