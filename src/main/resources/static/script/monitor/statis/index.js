var cbdwbm;
$(document).ready(function () {

    // 样式初始化以及事件绑定
    init_statistics_analysis();

    // 数据加载--单位展示
    //data_monitor_statistiscs_dw();
});

// 加载评查分类
function load_cbt_win_eval_build_pcfl() {

    $('#cbt_win_eval_build_pcfl').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1){
                pcflbm = data[0].id;
                $('#cbt_win_eval_build_pcfl').combotree('setValue', pcflbm);

                $('#pop_ups_win_input_pchd').show();
                $('#pop_ups_win_input_pcmb').hide();
                load_cbt_win_eval_build_pchd(pcflbm);
            }

        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }

            // 获取选中节点自定义数据
            if (node.attributes.SFJS == "Y") {

                $('#pop_ups_win_input_pcmb').show();
                $('#pop_ups_win_input_pchd').hide();
                // 加载对应评查模板
                load_cbt_win_eval_build_pcmb(node.id);
            }else {
                $('#pop_ups_win_input_pchd').show();
                $('#pop_ups_win_input_pcmb').hide();
                // 加载对应评查活动
                load_cbt_win_eval_build_pchd(node.id);
            }

        }
    });
}

// 加载评查活动
function load_cbt_win_eval_build_pchd(pcflbm) {

    $('#cbt_win_eval_build_pchd').combotree('loadData',[]);
    $('#cbt_win_eval_build_pchd').combotree('clear');
    $('#cbt_win_eval_build_pchd').combotree('setValue', '');

    $('#cbt_win_eval_build_pchd').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/manage/get_pchd',
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess: function (node, data) {
            // 默认选中刑事
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_build_pchd').combotree('setValue', data[0].id);
            }

        },
        onSelect: function (node) {

        }
    });

}

// 加载评查模板
function load_cbt_win_eval_build_pcmb(pcflbm) {

    $('#cbt_win_eval_build_pcmb').combotree('loadData',[]);
    $('#cbt_win_eval_build_pcmb').combotree('clear');
    $('#cbt_win_eval_build_pcmb').combotree('setValue', '');

    $('#cbt_win_eval_build_pcmb').combotree({
        method: 'get',
        lines: true,
        url: getRootPath()+'/template/template',
        queryParams: {
            pcflbm: pcflbm
        },
        onLoadSuccess: function (node,data) {
            // 专项默认选中
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_build_pcmb').combotree('setValue', data[0].id);
            }

        },
        onSelect: function (node) {

        }
    });

}
// 样式初始化：
function init_statistics_analysis() {

    // 单位tab
    init_monitor_statistiscs_dw();

    // 部门tab
    //init_monitor_statistiscs_bm();

    //检察官tab
     //init_monitor_statistiscs_stuff();


}

// 单位tab样式以及事件：
function init_monitor_statistiscs_dw(){
    //加载评查分类
    load_cbt_win_eval_build_pcfl();
    //单位--评查时间
    $('#monitor_statis_dw_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#monitor_statis_dw_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });

    // 单位表格查询：
    $("#btn_monitor_statis_dw_search").unbind('click');
    $("#btn_monitor_statis_dw_search").bind('click', function () {
        data_monitor_statistiscs_dw();
    });
    var resizeTabHeight = $("#tabsBox").height() - 62;
    $("#tabs").find(".panel-body").height(resizeTabHeight);
}

// 部门tab样式以及事件：
function init_monitor_statistiscs_bm(){

    //单位--评查时间
    $('#monitor_statis_bm_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#monitor_statis_bm_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });

    // 单位combotree:
    $('#monitor_handler_dw_combotree').combotree({
        method: 'get',
        editable: false,
        lines: true,
        panelWidth:270,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        // multiple: true,
        // cascadeCheck: false,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#monitor_handler_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,'monitor_handler_dw_combotree');
        }
    });

    // 部门--查询：
    $("#btn_monitor_statis_bm_search").unbind('click');
    $("#btn_monitor_statis_bm_search").bind('click', function () {
        data_monitor_statistiscs_bm();
    });
}

// 检察官tab初始化：
function init_monitor_statistiscs_stuff(){

    // 检察官--评查时间：
    $('#monitor_statis_stuff_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#monitor_statis_stuff_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });


    //部门：
    $('#monitor_handler_stuff_bm_combotree' ).combotree({
        method:'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        // queryParams:{dwbm:cbdwbm},
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#monitor_handler_stuff_bm_combotree').combotree('setValue', data[0].id); //部门默认选择

            }
            index_addMousedownDiv(this,'monitor_handler_stuff_bm_combotree');
        }
    });
    
    // 单位：
    $('#monitor_handler_stuff_dw_combotree').combotree({
        method: 'get',
        editable: false,
        lines: true,
        panelWidth:270,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        // multiple: true,
        // cascadeCheck: false,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                // $('#monitor_handler_stuff_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
                $(this.children[0].children[0]).click()
                cbdwbm = data[0].id;
            }
            index_addMousedownDiv(this,'monitor_handler_stuff_dw_combotree');
        },
        onSelect:function (index, row) {
            $('#monitor_handler_stuff_bm_combotree').combotree('reload',getRootPath() +'/organization/getBmList?dwbm='+index.id);
        }
    });



    // 检察官--查询：
    $("#btn_monitor_statis_stuff_search").unbind('click');
    $("#btn_monitor_statis_stuff_search").bind('click', function () {
        data_monitor_statistiscs_stuff();
    });


    // tab 切换加载数据：
    $("#tabs").tabs({
        onSelect:function(title,index){
            if (index ==0) { // 加载单位数据表格
                data_monitor_statistiscs_dw();
            }
            if (index == 1) {
                data_monitor_statistiscs_bm();
            }
            if (index == 2) {
                data_monitor_statistiscs_stuff();
            }
        }
    });
}




// 展示单位表格数据：
function data_monitor_statistiscs_dw() {

    var obj = new Object();
    obj.dwbm = userInfo.DWBM;
    obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
    obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValue');
    var tree = $('#cbt_win_eval_build_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    if(node.attributes.SFJS == "Y"){
        obj.pcmbbm = $('#cbt_win_eval_build_pcmb').combotree('getValue');
    }else{
        obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
    }

    // 单位表格datagrid
    $('#table_monitor_statistiscs_dw').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        pageList: [10, 20, 30, 50, 100],
        url: getRootPath() + "/queryTable/getDwTableData",
        method:'post',
        queryParams:obj,
        columns: [[
            {
                field: 'name',
                title: '<span  style=\'font-size:16px;\'>单<br>位<br>名<br>称</span>',
                rowspan: 4,
                width: 260,
                align: 'center',
                halign: 'center'
            },
            {
                title: '<span  style=\'font-size:14px;margin-top: 10px;display: inline-block;\'>评<br>查<br>率</span>',
                rowspan: 3,
                width: 70,
                align: 'center',
                halign: 'center'
            },
            {title: '<span  style=\'font-size:14px\'></span>', colspan: 9},
            {
                title: '<span  style=\'font-size:14px\'>评查<br>发现<br>问题<br>数</span>',
                rowspan: 3,
                align: 'center',
                halign: 'center'
            },
            {title: '<span  style=\'font-size:14px\'></span>', colspan: 18},
        ], [
            {
                title: '<span  style=\'font-size:14px\'>审<br>结<br>数</span>',
                rowspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>评<br>查<br>数</span>',
                rowspan: 2,
                align: 'center'
            },
            {
                title: '<span  style="font-size:14px">评查方式</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>结果等次</span>',
                colspan: 4,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>证据采信</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>事实认定</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>法律适用</span>',
                colspan: 3,
                align: 'center'
            }
            ,{
                title: '<span  style=\'font-size:14px\'>办案程序</span>',
                colspan: 2,
                align: 'center'
            },{
                title: '<span  style=\'font-size:14px\'>法律文书</span>',
                colspan: 2,
                align: 'center'
            },{
                title: '<span  style=\'font-size:14px\'>侦查监督</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>司法责任制落实</span>',
                colspan: 2,
                align: 'center'
            }
            ,{
                title: '<span  style=\'font-size:14px\'>系统规范应用</span>',
                align: 'center'
            }
            ,{
                title: '<span  style=\'font-size:14px\'>其他情况</span>',
                align: 'center'
            }
        ]
            , [
                {
                    title: '<span  style=\'font-size:14px;\'>重点<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>常规<br>抽查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>专项<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>优<br>质</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>合<br>格</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>不合<br>格</span>',
                    align: 'center'
                }
                ,
                {   //证据采信
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                }
                ,{  //事实认定 瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }
                ,
                {   //法律适用  瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                }
                , { //办案程序
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }
                , { //法律文书
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }, {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }, { //侦查监督  错误
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }, {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {   //司法责任制落实
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {   // 系统规范应用 瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {   //其他 瑕疵
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
            ], [
                {
                    field: 'pcl',
                    title: '<span  style=\'font-size:14px\'>%</span>',
                    align: 'center'
                }, {
                    field: 'sjCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={"aa"} style="color: #145bae;text-decoration: none;"  onclick="alert_statistic_shengji_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'pcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center'
                }, {
                    field: 'zdpcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        //上级单位可以点击所有数据，下级单位仅能点击本级单位：
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"002\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }

                        return r;
                    }
                }, {
                    field: 'cgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"001\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zxCount',
                    title: '<span  style=\'font-size:14px\'>次/件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"003\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'yzCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"优质案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = '<a href="#" data-field={\"pcjl\":\"优质案件\"} style="color: #145bae;text-decoration: none;" >'+value+'</a> ';
                        }
                        return r;
                    }
                }, {
                    field: 'hgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'xcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"瑕疵案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bhgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"不合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'pcwtzsCount',
                    title: '<span  style=\'font-size:14px\'>件/处</span>',
                    align: 'center'
                }
                , {
                    field: 'zjcxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                },
                {
                    field: 'zjcxfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                ,{
                    field: 'ssrdXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'ssrdCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'flsyXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'bacxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bacxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'flwsCwCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律文书\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flwsXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律文书\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'zcjdCwCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"侦查监督\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zcjdXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"侦查监督\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'sfzrzlsCwCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"司法责任制落实\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'sfzrzlsXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"司法责任制落实\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'xtgfyyXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"系统规范应用\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
                , {
                    field: 'qtqkXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"其他\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,0,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }
            ]],
        loadFilter:function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        }

    });

}

// 展示部门表格数据
function data_monitor_statistiscs_bm() {

    var obj = new Object();
    var dwbm = $("#monitor_handler_dw_combotree").combotree('getValue');
    obj.dwbm = dwbm ==""?userInfo.DWBM:dwbm;
    obj.startDate = $('#monitor_statis_bm_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_bm_end").datebox('getValue');

    $('#table_monitor_statistiscs_bm').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        url:getRootPath() + '/queryTable/getBmTableData',
        queryParams:obj,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        pageList: [10, 20, 30, 50, 100],
        columns: [[
            {
                field: 'name',
                title: '<span  style=\'font-size:16px;\'>部<br>门<br>名<br>称</span>',
                rowspan: 4,
                align: 'center',
                halign: 'center'
            },
            {
                title: '<span  style=\'font-size:14px;margin-top: 10px;display: inline-block;\'>评<br>查<br>率</span>',
                rowspan: 3,
                align: 'center',
                halign: 'center'
            },
            {title: '<span  style=\'font-size:14px\'></span>', colspan: 10},
            {
                title: '<span  style=\'font-size:14px\'>评查<br>发现<br>问题<br>数</span>',
                rowspan: 3,
                align: 'center',
                halign: 'center'
            },
            {title: '<span  style=\'font-size:14px\'></span>', colspan: 12},


        ], [
            {
                title: '<span  style=\'font-size:14px\'>审<br>结<br>数</span>',
                rowspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>评<br>查<br>数</span>',
                rowspan: 2,
                align: 'center'
            },
            {
                title: '<span  style="font-size:14px">评查方式</span>',
                colspan: 4,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>结果等次</span>',
                colspan: 4,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>事实认定</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>证据采信</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>法律适用</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>办案程序</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>法律文书</span>',
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>办案效果</span>',
                align: 'center'
            }
        ]
            , [
                {
                    title: '<span  style=\'font-size:14px;\'>重点<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>常规<br>抽查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>专项<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>交叉<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>优<br>质</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>合<br>格</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>不合<br>格</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>一<br>般</span>',
                    align: 'center'
                }
            ], [
                {
                    field: 'pcl',
                    title: '<span  style=\'font-size:14px\'>%</span>',
                    align: 'center'
                }, {
                    field: 'sjCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={"aa"} style="color: #145bae;text-decoration: none;"  onclick="alert_statistic_shengji_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = '<a href="#" data-field={"aa"} style="color: #145bae;text-decoration: none;" >'+value+'</a> ';
                        }
                        return r;
                    }
                }, {
                    field: 'pcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center'
                }, {
                    field: 'zdpcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        //上级单位可以点击所有数据，下级单位仅能点击本级单位：
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"002\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = '<a href="#" data-field={\"pcflbm\":\"002\"} style="color: #145bae;text-decoration: none;" >'+value+'</a> ';
                        }

                        return r;
                    }
                }, {
                    field: 'cgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"001\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zxCount',
                    title: '<span  style=\'font-size:14px\'>次/件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"003\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'jxCount',
                    title: '<span  style=\'font-size:14px\'>次/件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"005\"}  style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'yzCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"优质案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'hgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'xcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"瑕疵案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bhgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"不合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'pcwtzsCount',
                    title: '<span  style=\'font-size:14px\'>件/处</span>',
                    align: 'center'
                }, {
                    field: 'ssrdXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'ssrdCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bacxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bacxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,1,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flwsXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center'
                }, {
                    field: 'baxgYbCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center'
                }
            ]],
        loadFilter:function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        }
    });

}

// 展示检察官表格数据
function data_monitor_statistiscs_stuff() {

    var obj = new Object();
    obj.startDate = $('#monitor_statis_stuff_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_stuff_end").datebox('getValue');
    var dwbm = $("#monitor_handler_stuff_dw_combotree").combotree('getValue');
    var bmbm = $("#monitor_handler_stuff_bm_combotree").combotree('getValue');

    obj.dwbm = dwbm==""?userInfo.DWBM:dwbm;
    obj.bmbm = bmbm;

    $('#table_monitor_statistiscs_stuff').datagrid({
        method:'get',
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        url:getRootPath() + '/queryTable/getJcgTableData',
        queryParams:obj,
        pagination:true,
        pageNumber:1,
        pageList: [10, 20, 30, 50, 100],
        pageSize:20,
        columns: [[
            {
                field: 'name',
                title: '<span  style=\'font-size:16px;\'>检<br>察<br>官<br>名<br>称</span>',
                rowspan: 4,
                align: 'center',
                halign: 'center'
                // formatter: function (value, row, index) {
                //     if(value!='合计'){
                //         return  '<a href="#"  style="color: #145bae;text-decoration: none;"  onclick="grid_handle_table_bm(\'' + row.dwbm + '\')">'+value+'</a> ';
                //     }else {
                //         return  '<a href="#" style="color: #333;text-decoration: none;" >'+value+'</a> ';
                //     }
                //
                // }
            },
            {
                title: '<span  style=\'font-size:14px;margin-top: 10px;display: inline-block;\'>评<br>查<br>率</span>',
                rowspan: 3,
                align: 'center',
                halign: 'center'
            },
            {title: '<span  style=\'font-size:14px\'></span>', colspan: 10},
            {
                title: '<span  style=\'font-size:14px\'>评查<br>发现<br>问题<br>数</span>',
                rowspan: 3,
                align: 'center',
                halign: 'center'
            },
            {title: '<span  style=\'font-size:14px\'></span>', colspan: 12},


        ], [
            {
                title: '<span  style=\'font-size:14px\'>审<br>结<br>数</span>',
                rowspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>评<br>查<br>数</span>',
                rowspan: 2,
                align: 'center'
            },
            {
                title: '<span  style="font-size:14px">评查方式</span>',
                colspan: 4,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>结果等次</span>',
                colspan: 4,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>事实认定</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>证据采信</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>法律适用</span>',
                colspan: 3,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>办案程序</span>',
                colspan: 2,
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>法律文书</span>',
                align: 'center'
            },
            {
                title: '<span  style=\'font-size:14px\'>办案效果</span>',
                align: 'center'
            }
        ]
            , [
                {
                    title: '<span  style=\'font-size:14px;\'>重点<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>常规<br>抽查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>专项<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>交叉<br>评查</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>优<br>质</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>合<br>格</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>不合<br>格</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                    align: 'center'
                }
                ,
                {
                    title: '<span  style=\'font-size:14px\'>一<br>般</span>',
                    align: 'center'
                }
            ], [
                {
                    field: 'pcl',
                    title: '<span  style=\'font-size:14px\'>%</span>',
                    align: 'center'
                }, {
                    field: 'sjCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={"aa"} style="color: #145bae;text-decoration: none;"  onclick="alert_statistic_shengji_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'pcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center'
                }, {
                    field: 'zdpcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        //上级单位可以点击所有数据，下级单位仅能点击本级单位：
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"002\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }

                        return r;
                    }
                }, {
                    field: 'cgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r ='';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"001\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zxCount',
                    title: '<span  style=\'font-size:14px\'>次/件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"003\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'jxCount',
                    title: '<span  style=\'font-size:14px\'>次/件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcflbm\":\"005\"}  style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'yzCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"优质案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'hgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'xcCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"瑕疵案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bhgCount',
                    title: '<span  style=\'font-size:14px\'>件</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"pcjl\":\"不合格案件\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'pcwtzsCount',
                    title: '<span  style=\'font-size:14px\'>件/处</span>',
                    align: 'center'
                }, {
                    field: 'ssrdXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'ssrdCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"事实认定\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"  onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'zjcxfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"证据采集\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flsyfqCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"法律适用\",\"pcxflmc\":\"分歧\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bacxXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"瑕疵\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'bacxCuCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '';
                        if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                            r = '<a href="#" data-field={\"zmc\":\"办案程序\",\"pcxflmc\":\"错误\"} style="color: #145bae;text-decoration: none;"   onclick="alert_jbxx_ypc_window(this,2,'+index+')">'+value+'</a> ';
                        }else {
                            r = value;
                        }
                        return r;
                    }
                }, {
                    field: 'flwsXcCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center'
                }, {
                    field: 'baxgYbCount',
                    title: '<span  style=\'font-size:14px\'>处</span>',
                    align: 'center'
                }
            ]],
        loadFilter:function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        }
    });

    $('#table_monitor_statistiscs_stuff').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

}

// 已经评查案件（type对应0，1,2分别是单位，部门，检察官表）
function alert_jbxx_ypc_window(el,type, index) {

    var obj = new Object();
    var ypcUrl = getRootPath() ;

    // 判断哪个表：
    if (type == 0) {
       var thisRow = $('#table_monitor_statistiscs_dw').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
        obj.dwbmList = thisRow.dwbm;

        ypcUrl += "/queryTable/getAjjbxx";
    }

    if (type == 1) {
        var thisRow = $('#table_monitor_statistiscs_bm').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_bm_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_bm_end").datebox('getValue');
        obj.dwbmList = $("#monitor_handler_dw_combotree").combotree('getValue');
        obj.bmbmList =thisRow.bmbm;

        ypcUrl += "/queryTable/getAjjbxxByBm";
    }

    if (type == 2) {
        var thisRow = $('#table_monitor_statistiscs_stuff').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_stuff_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_stuff_end").datebox('getValue');
        obj.dwbmList = $("#monitor_handler_stuff_dw_combotree").combotree('getValue');
        obj.bmbmList =$("#monitor_handler_stuff_bm_combotree").combotree('getValue');
        obj.ghList = thisRow.gh;

        ypcUrl += "/queryTable/getAjjbxxByJcg";
    }

    var currField = $(el).attr("data-field");
    var jsonField = JSON.parse(currField);


    if (!isNull(jsonField.zmc)) {
        obj.zmc = jsonField.zmc;
    }
    if (!isNull(jsonField.pcxflmc)) {
        obj.pcxflmc = jsonField.pcxflmc;
    }

    // if (!isNull(thisRow.dwbm)) {
    //     obj.dwbmList = thisRow.dwbm;
    // }
    // if (!isNull(thisRow.bmbm)) {
    //     obj.bmbmList = thisRow.bmbm;
    // }
    // if (!isNull(thisRow.gh)) {
    //     obj.ghList = thisRow.gh;
    // }
    if (!isNull(jsonField.pcflbm)) {
        obj.pcflbm = jsonField.pcflbm;
    }
    if (!isNull(jsonField.pcjl)) {
        obj.pcjl = jsonField.pcjl;
    }

    // 已评查案件datagrid
    $("#table_eval_build_statistics_analysis_filter").datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        // url: getRootPath() + "/queryTable/getAjjbxx",
        url:ypcUrl,
        queryParams:obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90},
            {field:'DWMC',title:'承办单位',width:90},
            {field:'CBRMC',title:'承办检察官',width:90},
            {field:'PCR_DWMC',title:'评查单位',width:90},
            {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_statistics_analysis_filter\',0)">查看</a>';
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_eval_build_statistics_analysis_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });


    $("#win_eval_build_statistics_analysis_filter").window('setTitle','已经评查案件');
    $("#win_eval_build_statistics_analysis_filter").window('open');

}

//获取已审结未评查案件信息（type对应0，1,2分别是单位，部门，检察官表）
function alert_statistic_shengji_window(el,type,index){

    var obj = new Object();
    // 判断哪个表：
    if (type ==0) {
        var  thisRow = $('#table_monitor_statistiscs_dw').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
        obj.dwbmList = thisRow.dwbm;
        obj.pcflbm = $('#cbt_win_eval_build_pcfl').combotree('getValue');
        var tree = $('#cbt_win_eval_build_pcfl').combotree('tree');	// 获取树对象
        var node = tree.tree('getSelected');		// 获取选择的节点
        if(node.attributes.SFJS == "Y"){
            obj.pcmbbm = $('#cbt_win_eval_build_pcmb').combotree('getValue');
        }else{
            obj.pchdbm = $('#cbt_win_eval_build_pchd').combotree('getValue');
        }
    }

    if (type == 1) {
        var thisRow = $('#table_monitor_statistiscs_bm').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_bm_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_bm_end").datebox('getValue');
        obj.dwbmList = thisRow.dwbm;
    }

    if (type == 2) {
        var thisRow = $('#table_monitor_statistiscs_stuff').datagrid('getRows')[index];
        obj.startDate = $('#monitor_statis_stuff_start').datebox('getValue');
        obj.endDate = $("#monitor_statis_stuff_end").datebox('getValue');
        obj.dwbmList = $("#monitor_handler_stuff_dw_combotree").combotree('getValue');
        obj.bmbmList =$("#monitor_handler_stuff_bm_combotree").combotree('getValue');
        obj.ghList = thisRow.gh;
    }

    $('#table_eval_build_statistics_analysis_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination:true,
        pageNumber:1,
        pageSize:20,
        url: getRootPath() + "/queryTable/getWpcAjjbxx",
        queryParams:obj,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLBMC',title:'案件类别',width:90},
            {field:'DWMC',title:'承办单位',width:90},
            {field:'CBRMC',title:'承办检察官',width:90},
            {field:'SLRQ',title:'受理日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'WCBZRQ',title:'完成日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field: 'action', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#table_eval_build_statistics_analysis_filter\',1)">查看</a>'
                    return r;
                }
            }
        ]]
    });

    $('#table_eval_build_statistics_analysis_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 展示已审结未评查案件信息
    $("#win_eval_build_statistics_analysis_filter").window('setTitle','已经审结案件');
    $("#win_eval_build_statistics_analysis_filter").window('open');


}

function export_excel_dw() {
    var obj = new Object();
    obj.dwbm = userInfo.DWBM;
    obj.startDate = $('#monitor_statis_dw_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_dw_end").datebox('getValue');
    $.ajax({
        url:getRootPath()+"/queryTable/exportDwExcel",
        type:"get",
        data:obj,
        success:function (data) {
            if (data.code == 200) {
                window.location.href=getRootPath()+ data.data;
            }
            CloseProgress();
        },
        error:function () {
            CloseProgress();
        }
    });
}


function exportBmExcel() {
    var obj = new Object();
    var dwbm = $("#monitor_handler_dw_combotree").combotree('getValue');
    obj.dwbm = dwbm ==""?userInfo.DWBM:dwbm;
    obj.startDate = $('#monitor_statis_bm_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_bm_end").datebox('getValue');
    $.ajax({
        url:getRootPath()+"/queryTable/exportBmExcel",
        type:"get",
        data:obj,
        success:function (data) {
            if (data.code == 200) {
                window.location.href=getRootPath()+data.data;
            }
            CloseProgress();
        },
        error:function () {
            CloseProgress();
        }
    });
}
function exportJcgExcel() {
    var obj = new Object();
    obj.startDate = $('#monitor_statis_stuff_start').datebox('getValue');
    obj.endDate = $("#monitor_statis_stuff_end").datebox('getValue');
    var dwbm = $("#monitor_handler_stuff_dw_combotree").combotree('getValue');
    var bmbm = $("#monitor_handler_stuff_bm_combotree").combotree('getValue');

    obj.dwbm = dwbm==""?userInfo.DWBM:dwbm;
    obj.bmbm = bmbm;
    $.ajax({
        url:getRootPath()+"/queryTable/exportJcgExcel",
        type:"get",
        data:obj,
        success:function (data) {
            if (data.code == 200) {
                window.location.href=getRootPath()+data.data;
            }
            CloseProgress();
        },
        error:function () {
            CloseProgress();
        }
    });
}


