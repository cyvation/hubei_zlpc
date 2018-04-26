

$(document).ready(function () {

    // 初始化统计样式，以及事件绑定
    init_tongji();


    // 加载数据
    load_tongji_data();

});

function init_tongji() {

    // 总表样式初始化
    init_table_case_build_total();

    // 案件类别初始化
    init_table_case_build_category();

    // 问题性质初始化
    init_table_case_build_problems();

    // 评查结论初始化
    init_table_case_build_conclusion();

    // 样式设置
    $(".pcajsx_tj_2").click(function(){
        $(this).addClass("pcajsx_tj_click");
        $(this).siblings().removeClass("pcajsx_tj_click")
    });

}

function load_tongji_data() {

    // 默认加载总表
    // load_table_case_build_total();
    $("#btn_eval_statistics_pcjbqk_total_search").click();
}

// 总表初始化及事件绑定
function init_table_case_build_total() {
    <!---总表-->
    $("#table_case_build_total").datagrid({
        //  url: "data1/data1_dwbm.json",
        pageSize: 10,
        pageList: [10, 20, 50],
        rownumbers: true,
        fit: false,        //自动大小
        fitColumns: true, //自适应列宽
        singleSelect: true,  //是否单选
        columns: [[
            {field:'dw',title:"（单位、部门、人）",rowspan:2,width:80},
            {field:'totalPec',title:'总评查率' ,rowspan:2,width:100},
            {field:'total',title:'评查总数',rowspan:2,width:100},
            {title:'随机',colspan:3},
            {title:'重点',colspan:2,width:100},
            {title:'专项',colspan:1,width:80}
        ],[
            {field:'random_pcl',title:'评查率',width:100 },
            {field:'random_ming',title:'民事',width:100 },
            {field:'random_xin',title:'刑事',width:100},

            {field:'key_ming',title:'民事',width:100},
            {field:'key_xin',title:'刑事',width:100},

            {field:'special_pcs',title:'评查数量',width:100}
        ]],
        onClickCell:function(index, field, value) {
            // 用户点击单位/部门/人--跳转-->部门/人，展示
            if (field == 'dw') {
                var thisRow = $("#table_case_build_total").datagrid('getRows')[index];
                load_table_case_build_total(thisRow);
            }

        }
    });

    // 总表切换标签样式及事件绑定
    $("#btn_eval_statistics_pcjbqk_total").unbind( "click" );
    $("#btn_eval_statistics_pcjbqk_total").bind("click", function () {
        $("#page_eval_statistics_pcjbqk_total").css("left","0");
        $("#page_eval_statistics_pcjbqk_category").css("left","100%");
        $("#page_eval_statistics_pcjbqk_problems").css("left","200%");
        $("#page_eval_statistics_pcjbqk_conclusion").css("left","300%");


        // 加载总表
        var data = $('#table_case_build_total').datagrid('getRows');
        if (!data || data.length <= 0){
            // 清空历史数据
            //$('#table_eval_build_rd_stuff').datagrid('loadData',[]);

            load_table_case_build_total();
        }

        // 查询
        $("#btn_eval_statistics_pcjbqk_total_search").unbind('click');
        $("#btn_eval_statistics_pcjbqk_total_search").bind('click',function () {
            load_table_case_build_total();
        });


    });

}

//案件类别初始化及事件绑定
function init_table_case_build_category() {
    <!---类别-->
    $('#table_case_build_category').datagrid({
        //  url: "data2/data2_dwbm.json",
        pageSize: 10,
        pageList: [10, 20, 50],
        rownumbers: true,
        fit: false,        //自动大小
        fitColumns: true, //自适应列宽
        singleSelect: true,  //是否单选
        columns: [

            [
                {field:'dw',title:addbr("（单位、部门、人）"),rowspan:2,width:100},
                {field:'random_pcl',title:addbr('随机评查率'),rowspan:2,width:100 },
                {field:'random_pcs',title:addbr('随机总量'),rowspan:2 ,width:100},
                {title:'刑事',colspan:18},
                {title:'民事',colspan:9}
            ],[
                {field:'x1',title:addbr('审查逮捕') ,width:100},
                {field:'x2',title:addbr('不批准逮捕复议'),width:100 },
                {field:'x3',title:addbr('羁押必要性审查案件'),width:100},
                {field:'x4',title:addbr('减刑、假释提请中审查案件'),width:100},
                {field:'x5',title:addbr('减刑、假释提请中审查（开庭）案件'),width:100},
                {field:'x6',title:addbr('立案监督案件') ,width:100},
                {field:'x7',title:addbr('批准要延长羁押期限案件'),width:100},
                {field:'x8',title:addbr('重新计算羁押期限案件') ,width:100 },
                {field:'x9',title:addbr('一审公诉'),width:100  },
                {field:'x10',title:addbr('未成年人特别程序案件'),width:100},
                {field:'x11',title:addbr('二审上诉')  ,width:100},
                {field:'x12',title:addbr('审判监督程序抗诉（不抗诉）') ,width:100 },
                {field:'x13',title:addbr('不起诉复议案件') ,width:100 },
                {field:'x14',title:addbr('不起诉符核案件'),width:100  },
                {field:'x15',title:addbr('未成年法院决定再审案件'),width:100  },
                {field:'x16',title:addbr('刑事申诉审查案件'),width:100  },
                {field:'x17',title:addbr('刑事赔偿案件') ,width:100 },
                {field:'x18',title:addbr('刑事赔偿复议案件'),width:100  },

                {field:'m1',title:addbr('对民事、行政生效监督案件'),width:100},
                {field:'m2',title:addbr('对审判程序中违法行为的监督'),width:100},
                {field:'m3',title:addbr('对民事、行政执行活动的监督'),width:100},
                {field:'m4',title:addbr('对行政机关不当履行职责的监督'),width:100 },
                {field:'m5',title:addbr('支持起诉') ,width:100},
                {field:'m6',title:addbr('跟进监督') ,width:100},
                {field:'m7',title:addbr('复查纠正'),width:100 },
                {field:'m8',title:addbr('民行请示案件'),width:100 },
                {field:'m9',title:addbr('民事行政监督案件进一步审查'),width:100}
            ]],
        onClickCell:function(index, field, value) {
            // 用户点击单位/部门/人--跳转-->部门/人，展示
            if (field == 'dw') {
                var thisRow = $("#table_case_build_category").datagrid('getRows')[index];
                load_table_case_build_category(thisRow);
            }

        }

    });

    // 类别切换标签样式及事件绑定
    $("#btn_eval_statistics_pcjbqk_category").unbind('click');
    $("#btn_eval_statistics_pcjbqk_category").bind('click',function () {
        $("#page_eval_statistics_pcjbqk_total").css("left","-100%");
        $("#page_eval_statistics_pcjbqk_category").css("left","0%");
        $("#page_eval_statistics_pcjbqk_problems").css("left","100%");
        $("#page_eval_statistics_pcjbqk_conclusion").css("left","200%");

        // 加载案件类别总表
        var data = $('#table_case_build_category').datagrid('getRows');
        if (!data || data.length <= 0){
            // 清空历史数据
            //$('#table_eval_build_rd_dept').datagrid('loadData',[]);
            load_table_case_build_category();
        }

    });

    // 类别查询按钮；
    $("#btn_eval_statistics_pcjbqk_category_search").unbind("click");
    $("#btn_eval_statistics_pcjbqk_category_search").bind("click",function(){
        load_table_case_build_category();
    });
}

// 问题性质初始化及事件绑定
function init_table_case_build_problems() {
    <!--问题性质-->
    $('#table_case_build_problems').datagrid({
        pageSize: 10,
        pageList: [10, 20, 50],
        rownumbers: true,
        fit: false,        //自动大小
        fitColumns: true, //自适应列宽
        singleSelect: true,  //是否单选
        columns: [[

            {field:'dw',title:addbr("（单位、部门、人）"),rowspan:2 ,width:100},
            {field:'totalPec',title:addbr('总评查率') ,rowspan:2,width:100},
            {field:'total',title:addbr('评查总数'),rowspan:2,width:100 },
            {field:'random_pcs',title:addbr('随机评查数'),rowspan:2,width:100},
            {field:'random_pcl',title:addbr('随机评查率'),rowspan:2,width:100},
            {title:'事实认定',colspan:4},
            {title:'证据采集',colspan:4},
            {title:'法律适用',colspan:4},
            {title:'办案程序',colspan:4},
            {title:'法律文书',colspan:4}
        ],[
            {field:'x1',title:addbr('无问题'),width:100 },
            {field:'x2',title:addbr('司法瑕疵') ,width:100},
            {field:'x3',title:addbr('一般司法错误'),width:100},
            {field:'x4',title:addbr('严重司法错误'),width:100},

            {field:'x5',title:addbr('无问题'),width:100 },
            {field:'x6',title:addbr('司法瑕疵'),width:100 },
            {field:'x7',title:addbr('一般司法错误'),width:100},
            {field:'x8',title:addbr('严重司法错误'),width:100},

            {field:'x9',title:addbr('无问题'),width:100 },
            {field:'x10',title:addbr('司法瑕疵'),width:100 },
            {field:'x11',title:addbr('一般司法错误'),width:100},
            {field:'x12',title:addbr('严重司法错误'),width:100},

            {field:'x13',title:addbr('无问题'),width:100 },
            {field:'x14',title:addbr('司法瑕疵'),width:100 },
            {field:'x15',title:addbr('一般司法错误'),width:100},
            {field:'x16',title:addbr('严重司法错误'),width:100},

            {field:'x17',title:addbr('无问题'),width:100 },
            {field:'x18',title:addbr('司法瑕疵'),width:100 },
            {field:'x19',title:addbr('一般司法错误'),width:100},
            {field:'x20',title:addbr('严重司法错误'),width:100},
        ]],
        onClickCell:function(index, field, value) {
            // 用户点击单位/部门/人--跳转-->部门/人，展示
            if (field == 'dw') {
                var thisRow = $("#table_case_build_problems").datagrid('getRows')[index];
                load_table_case_build_problems(thisRow);
            }

        }
    });

    // 问题性质标签切换样式及事件绑定
    $("#btn_eval_statistics_pcjbqk_problems").unbind('click');
    $("#btn_eval_statistics_pcjbqk_problems").bind('click',function () {
        $("#page_eval_statistics_pcjbqk_total").css("left","-200%");
        $("#page_eval_statistics_pcjbqk_category").css("left","-100%");
        $("#page_eval_statistics_pcjbqk_problems").css("left","0%");
        $("#page_eval_statistics_pcjbqk_conclusion").css("left","100%");
    });

    // 问题性质查询绑定
    $("#btn_eval_statistics_pcjbqk_problems_search").unbind("click");
    $("#btn_eval_statistics_pcjbqk_problems_search").bind("click",function(){
        load_table_case_build_problems();
    });
}

// 评查结论初始化及事件绑定
function init_table_case_build_conclusion() {

    <!--评查结论-->
    $("#table_case_build_conclusion").datagrid({
        pageSize: 10,
        pageList: [10, 20, 50],
        rownumbers: true,
        fit: false,        //自动大小
        fitColumns: true, //自适应列宽
        singleSelect: true,  //是否单选
        columns: [[
            {field:'dw',title:addbr("（单位、部门、人）"),rowspan:2,width:100},
            {field:'totalPec',title:'总评查率' ,rowspan:2,width:100},
            {field:'total',title:'评查总数',rowspan:2 ,width:100},
            {title:'随机',colspan:7},
            {title:'重点',colspan:6},
            {title:'专项',colspan:6}
        ],[
            {field:'random_great',title:'优秀' ,width:100},
            {field:'random_ok',title:'无问题',width:100},
            {field:'random_bad',title:'司法瑕疵',width:100},
            {field:'random_normal',title:'一般司法错误',width:100},
            {field:'random_terrible',title:'严重司法错误',width:100},
            {field:'random_pcl',title:'评查率' ,width:100},
            {field:'random_pcs',title:'评查数' ,width:100},

            {field:'key_great',title:'优秀' ,width:100 },
            {field:'key_ok',title:'无问题' ,width:100},
            {field:'key_bad',title:'司法瑕疵' ,width:100},
            {field:'key_normal',title:'一般司法错误' ,width:100},
            {field:'key_terrible',title:'严重司法错误' ,width:100},
            {field:'key_pcs',title:'评查数' ,width:100},

            {field:'special_great',title:'优秀' ,width:100 },
            {field:'special_ok',title:'无问题' ,width:100},
            {field:'special_bad',title:'司法瑕疵' ,width:100},
            {field:'special_normal',title:'一般司法错误' ,width:100},
            {field:'special_terrible',title:'严重司法错误' ,width:100},
            {field:'special_pcs',title:'评查数' ,width:100 }
        ]],
        onClickCell:function(index, field, value) {
            // 用户点击单位/部门/人--跳转-->部门/人，展示
            if (field == 'dw') {
                var thisRow = $("#table_case_build_conclusion").datagrid('getRows')[index];
                load_table_case_build_conclusion(thisRow);
            }

        }
    });

    // 结论切换标签样式及事件绑定
    $("#btn_eval_statistics_pcjbqk_conclusion").unbind('click');
    $("#btn_eval_statistics_pcjbqk_conclusion").bind('click',function () {
        $("#page_eval_statistics_pcjbqk_total").css("left","-300%");
        $("#page_eval_statistics_pcjbqk_category").css("left","-200%");
        $("#page_eval_statistics_pcjbqk_problems").css("left","-100%");
        $("#page_eval_statistics_pcjbqk_conclusion").css("left","0%");

        // 加载案件类别总表
        var data = $('#table_case_build_category').datagrid('getRows');
        if (!data || data.length <= 0){
            // 清空历史数据
            //$('#table_eval_build_rd_dept').datagrid('loadData',[]);
            load_table_case_build_conclusion();
        }

    });

    // 查询绑定事件
    $("#btn_eval_statistics_pcjbqk_conclusion_search").unbind("click");
    $("#btn_eval_statistics_pcjbqk_conclusion_search").bind("click", function () {
        load_table_case_build_conclusion();
    });


}

// 总表数据加载
function load_table_case_build_total(thisRow) {

    // 查询条件
    var total_start = $("#total_start").datebox('getValue');
    var total_end = $("#total_end").datebox('getValue');

    //var obj = new Object();
    //obj.time_start = total_start;
    //obj.time_end  = total_end;
    //if (thisRow.dwbm !=undefined) {
    //    obj.dwbm = thisRow.dwbm;
    //}
    //if (thisRow.bmbm !=undefined) {
    //    obj.bmbm = thisRow.bmbm;
    //}
    //if (thisRow.gh !=undefined) {
    //
    //    obj.gh = thisRow.gh;
    //}


    // 请求数据
    // $.ajax({
    //     type: 'GET',
    //     url: getRootPath()+'/count/getJbpcTotal',
    //     data: {
    //         json: JSON.stringify(obj)
    //     },
    //     dataType: "json",
    //     success: function (data) {
    //
    //         if (data == "" || data == null || data == undefined){
    //             Alert(result.note);
    //         } else {
    //             // 总表
    //             $("#table_case_build_total").datagrid('loadData',result);
    //             CloseProgress();
    //             CloseProgress();
    //         }
    //     }
    // });

    // 首次加载数据
    if (thisRow ==undefined) {
        // ajax获取数据，单位作为行头
        $("#table_case_build_total").datagrid('load','data1/data1_dwbm.json');

    }else if (thisRow.dwbm !=null && thisRow.bmbm ==undefined){
        // 加载部门作为行的数据
        $("#table_case_build_total").datagrid('load','data1/data1_bmbm.json');

    }else if(thisRow.dwbm !=null && thisRow.gh ==undefined) {
        // 加载承办人作为行的数据
        $("#table_case_build_total").datagrid('load','data1/data1_gh.json');
    }

}

// 类别数据加载
function load_table_case_build_category(thisRow) {

    // 查询条件
    // var category_fenlei = $("#category_fenlei").combobox('getValue');
    // var category_start = $("#category_start").datebox('getValue');
    // var category_end = $("#category_end").datebox('getValue');
    //
    // var obj = new Object();
    // obj.dwbm = thisRow.dwbm;
    // obj.bmbm = thisRow.bmbm;
    // obj.gh = thisRow.gh;
    // obj.time_start = category_start;
    // obj.time_end = category_end;
    // obj.type = category_fenlei;
    //
    //
    // // ajax获取数据
    // $.ajax({
    //     type: 'GET',
    //     url: getRootPath()+'/count/getJbpctotal',
    //     data: {
    //         json: JSON.stringify(obj)
    //     },
    //     dataType: "json",
    //     success: function (result) {
    //
    //         if (result !=null ){
    //             // 总表
    //             $("#table_case_build_total").datagrid('loadData',result);
    //             CloseProgress();
    //         } else {
    //             CloseProgress();
    //             Alert(result.note);
    //         }
    //     }
    // });

    if (thisRow ==undefined) {
        // ajax获取数据，单位作为行头
        $("#table_case_build_category").datagrid('load','data2/data2_dwbm.json');

    }else if (thisRow.dwbm !=null && thisRow.bmbm ==undefined){
        // 加载部门作为行的数据
        $("#table_case_build_category").datagrid('load','data2/data2_bmbm.json');

    }else if(thisRow.dwbm !=null && thisRow.gh ==undefined) {
        // 加载承办人作为行的数据
        $("#table_case_build_category").datagrid('load','data2/data2_gh.json');
    }


    // 加载数据
    //$("#table_case_build_category").datagrid('loadData',jsonData);

}

// 问题性质数据加载
function load_table_case_build_problems(thisRow) {

    // 获取分类
    var problems_fenlei = $("#problems_fenlei").combobox('getValue');
    // 获取性质
    var problems_fanshi =$("#problems_fanshi").combobox('getValue');

    var problems_start = $("#problems_start").datebox('getValue');
    var prblems_end = $("#problems_end").datebox('getValue');



    // 统计方式采用单位展示
    if (problems_fanshi == 'dw') {
        if (thisRow ==undefined) {
            init_table_case_build_problems();
            // ajax获取数据，单位作为行头
            $("#table_case_build_problems").datagrid('load','data3/data3_dwbm.json');

        }else if (thisRow.dwbm !=null && thisRow.bmbm ==undefined){
            // 加载部门作为行的数据
            $("#table_case_build_problems").datagrid('load','data3/data3_bmbm.json');

        }else if(thisRow.dwbm !=null && thisRow.gh ==undefined) {
            // 加载承办人作为行的数据
            $("#table_case_build_problems").datagrid('load','data3/data3_gh.json');
        }

    }else {
        //以类别行展示
        $('#table_case_build_problems').datagrid({
            columns: [[
                {field:'category',title:addbr("（案件类别）"),rowspan:2 ,width:120},
                {field:'totalPec',title:addbr('总评查率') ,rowspan:2,width:100},
                {field:'total',title:addbr('评查总数'),rowspan:2,width:100 },
                {field:'random_pcs',title:addbr('随机评查数'),rowspan:2,width:100},
                {field:'random_pcl',title:addbr('随机评查率'),rowspan:2,width:100},
                {title:'事实认定',colspan:4},
                {title:'证据采集',colspan:4},
                {title:'法律适用',colspan:4},
                {title:'办案程序',colspan:4},
                {title:'法律文书',colspan:4}
            ],[
                {field:'x1',title:addbr('无问题'),width:100 },
                {field:'x2',title:addbr('司法瑕疵') ,width:100},
                {field:'x3',title:addbr('一般司法错误'),width:100},
                {field:'x4',title:addbr('严重司法错误'),width:100},
                {field:'x5',title:addbr('无问题'),width:100 },
                {field:'x6',title:addbr('司法瑕疵'),width:100 },
                {field:'x7',title:addbr('一般司法错误'),width:100},
                {field:'x8',title:addbr('严重司法错误'),width:100},
                {field:'x9',title:addbr('无问题'),width:100 },
                {field:'x10',title:addbr('司法瑕疵'),width:100 },
                {field:'x11',title:addbr('一般司法错误'),width:100},
                {field:'x12',title:addbr('严重司法错误'),width:100},
                {field:'x13',title:addbr('无问题'),width:100 },
                {field:'x14',title:addbr('司法瑕疵'),width:100 },
                {field:'x15',title:addbr('一般司法错误'),width:100},
                {field:'x16',title:addbr('严重司法错误'),width:100},
                {field:'x17',title:addbr('无问题'),width:100 },
                {field:'x18',title:addbr('司法瑕疵'),width:100 },
                {field:'x19',title:addbr('一般司法错误'),width:100},
                {field:'x20',title:addbr('严重司法错误'),width:100},
            ]]
        });
        $("#table_case_build_problems").datagrid("load",'data3/data3_category.json');

    }


}

// 评查结论数据加载
function load_table_case_build_conclusion(thisRow) {

    // 查询条件
    var conclusion_start = $("#conclusion_start").datebox('getValue');
    var conclusion_end = $("#conclusion_end").datebox('getValue');

    var conclusion_fenlei = $("#conclusion_fenlei").combobox('getValue');
    var conclusion_fanshi = $("#conclusion_fanshi").combobox('getValue');

    if (conclusion_fanshi == 'dw') {

        if (thisRow ==undefined) {

            init_table_case_build_conclusion();
            // ajax获取数据，单位作为行头
            $("#table_case_build_conclusion").datagrid('load','data4/data4_dwbm.json');

        }else if (thisRow.dwbm !=null && thisRow.bmbm ==undefined){
            // 加载部门作为行的数据
            $("#table_case_build_conclusion").datagrid('load','data4/data4_bmbm.json');

        }else if(thisRow.dwbm !=null && thisRow.gh ==undefined) {
            // 加载承办人作为行的数据
            $("#table_case_build_conclusion").datagrid('load','data4/data4_gh.json');
        }

    }else {
        //案件类别作为行展示数据
        //以类别行展示
        $('#table_case_build_conclusion').datagrid({

            columns: [[
                {field:'category',title:addbr("案件类别"),rowspan:2,width:100},
                {field:'totalPec',title:'总评查率' ,rowspan:2,width:100},
                {field:'total',title:'评查总数',rowspan:2 ,width:100},
                {title:'随机',colspan:7},
                {title:'重点',colspan:6},
                {title:'专项',colspan:6}
            ],[
                {field:'random_great',title:'优秀' ,width:100},
                {field:'random_ok',title:'无问题',width:100},
                {field:'random_bad',title:'司法瑕疵',width:100},
                {field:'random_normal',title:'一般司法错误',width:100},
                {field:'random_terrible',title:'严重司法错误',width:100},
                {field:'random_pcl',title:'评查率' ,width:100},
                {field:'random_pcs',title:'评查数' ,width:100},

                {field:'key_great',title:'优秀' ,width:100 },
                {field:'key_ok',title:'无问题' ,width:100},
                {field:'key_bad',title:'司法瑕疵' ,width:100},
                {field:'key_normal',title:'一般司法错误' ,width:100},
                {field:'key_terrible',title:'严重司法错误' ,width:100},
                {field:'key_pcs',title:'评查数' ,width:100},

                {field:'special_great',title:'优秀' ,width:100 },
                {field:'special_ok',title:'无问题' ,width:100},
                {field:'special_bad',title:'司法瑕疵' ,width:100},
                {field:'special_normal',title:'一般司法错误' ,width:100},
                {field:'special_terrible',title:'严重司法错误' ,width:100},
                {field:'special_pcs',title:'评查数' ,width:100 }
            ]]
        });
        $("#table_case_build_conclusion").datagrid("load",'data4/data4_category.json');
    }


}



//title 竖立显示文字
function addbr(title) {
    var newTitle ='';
    var length = title.length;

    for(var i=0;i <length; i++){
        if(length<=4){
            newTitle +=title.charAt(i) + '<br>';
        }else if(4<length&&length<=8){

            if((i+1)%2 == 0){   newTitle +=title.charAt(i) + '<br>';}
            else { newTitle += title.charAt(i);}
        }else if(8<length){
            if((i+1)%3 == 0){ newTitle +=title.charAt(i) + '<br>';}
            else { newTitle += title.charAt(i);}

        }
    }
    return newTitle;

}

