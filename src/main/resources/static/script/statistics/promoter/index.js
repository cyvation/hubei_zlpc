$(document).ready(function () {

    // 初始化单位控件和表格
    init_control_grid_dw();
});

// 初始化单位控件、加载表格
function init_control_grid_dw()
{
    $('#pcWin_win').window({
        width: 780,
        height: 500,
        modal: true,
        title: '评查预览',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {
        }
    });
    //评查单位树
    $('#cbt_tj_promoters_dw').combotree({
        method: 'get',
        /*editable: false,*/
        panelWidth: 250,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                dt = data[0].id;
                $('#cbt_tj_promoters_dw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this,"cbt_tj_promoters_dw");
        },
        onChange:function(newValue, oldValue){
            if(isNull(newValue) || newValue.length != 1){
                $("#cbt_tj_promoters_bm").combotree('disable');
                $("#cbt_tj_promoters_bm").combotree("setValue","");
                return;
            }
            $("#cbt_tj_promoters_bm").combotree('enable');
            // 办案部门树：
            $("#cbt_tj_promoters_bm").combotree({
                method: 'get',
                editable: false,
                panelWidth: 250,
                lines: true,
                multiple: true,
                cascadeCheck: false,
                url: getRootPath() + '/filter/getAllBm',
                onShowPanel: index_onShowPanel,
                onHidePanel: index_onHidePanel,
                queryParams: {
                    dwbm: $('#cbt_tj_promoters_dw').combotree("getValue")
                },
                onLoadSuccess: function (node, data) {
                    index_addMousedownDiv(this,"cbt_tj_promoters_bm");
                }
            });
            //$("#cbt_tj_promoters_bm").combotree("reload",getRootPath() + '/filter/getAllBm?dwbm=' + newValue);

        }
    });

    //承办人身份
    $('#cbt_win_eval_build_cbrsf').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#cbt_win_eval_build_cbrsf').combotree('setValue', 'N');
            }
            index_addMousedownDiv(this, "cbt_win_eval_build_cbrsf");
        }
    });
    $('#cbt_win_eval_build_cbrsf').combotree("loadData", getCbrsfValues());
    //完成日期
    $('#date_tj_promoters_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01',
        width: 130
    });

    $('#date_tj_promoters_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate()),
        width: 130
    });
    // 评查时间
    $('#date_pc_promoters_start').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_pc_promoters_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });

    //办案检察官
    $('#txt_tj_promoters_cbr').textbox({});

  /*  // 分页控件(中文)
    $('#grid_tj_promoters').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/

    // 查询点击事件
    $("#btn_tj_promoters_search").unbind("click");
    $("#btn_tj_promoters_search").bind("click", function () {
        load_grid_promoters();
    });

    // 加载表格
    $('#grid_tj_promoters').datagrid({
        resizable: true,
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        pagination: true,
        rownumbers: true,
        fit: true,
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        multiSort: true,
        remoteSort: false,
        loadMsg: '数据加载中，请稍后...',
        loadFilter: function (data) {
            return  data.status === 200?data.value:[];
        },
        columns: [[
            { field: 'BPC_DWBM', hidden: true },
            { field: 'BPC_BMBM', hidden: true },
            { field: 'BPC_MC', title: '办案检察官', width: 110},
            { field: 'BPC_DWMC', title: '办案单位', width: 150 },
            { field: 'BPC_BMMC', title: '办案部门', width: 130},
            { field: 'BPCSL', title: '被评查数量', width: 130 ,
                formatter: function (value, row, index) {
                    var r ='';
                    if (row.BPC_DWBM == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a href="#" data-field="allNum" style="color: #145bae;text-decoration: none;"  onclick="alert_win_filter(this,'+index+')">'+value+'</a> ';
                    }else {
                        r = value;
                    }
                    return r;
                }
            },
            { field: 'YXSL', title: '优秀数量', width: 120 ,
                formatter: function (value, row, index) {
                    var r ='';
                    if (row.BPC_DWBM == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a href="#" data-field="优质案件" style="color: #145bae;text-decoration: none;"  onclick="alert_win_filter(this,'+index+')">'+value+'</a> ';
                    }else {
                        r = value;
                    }
                    return r;
                }
            },
            /*{ field: 'YXL', title: '优秀率', width: 110 },*/
            { field: 'HGSL', title: '合格数量', width: 120 ,
                formatter: function (value, row, index) {
                    var r ='';
                    if (row.BPC_DWBM == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a href="#" data-field="合格案件" style="color: #145bae;text-decoration: none;"  onclick="alert_win_filter(this,'+index+')">'+value+'</a> ';
                    }else {
                        r = value;
                    }
                    return r;
                }
            },
            { field: 'XCSL', title: '瑕疵数量', width: 120,
                formatter: function (value, row, index) {
                    var r ='';
                    if (row.BPC_DWBM == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a href="#" data-field="瑕疵案件" style="color: #145bae;text-decoration: none;"  onclick="alert_win_filter(this,'+index+')">'+value+'</a> ';
                    }else {
                        r = value;
                    }
                    return r;
                }
            },
            { field: 'BHGSL', title: '不合格数量', width: 120,
                formatter: function (value, row, index) {
                    var r ='';
                    if (row.BPC_DWBM == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a href="#" data-field="不合格案件" style="color: #145bae;text-decoration: none;"  onclick="alert_win_filter(this,'+index+')">'+value+'</a> ';
                    }else {
                        r = value;
                    }
                    return r;
                }
            },
            /*  { field: 'BHGL', title: '不合格率', width: 120 },*/
           /* { field: 'YYL', title: '异议率', width: 110 },*/
            /*{ field: 'TYYYSL', title: '部门同意异议数量', width: 130 }*/
        ]]
    });
    load_grid_promoters();
    var resizeTabHeight = $("#layout_tj_promoters").height() - 62;
    $("#layout_tj_promoters").find(".panel-body").height(resizeTabHeight);
}

// 加载表格
function load_grid_promoters() {

    var obj = new Object();
    obj.dwbm = $('#cbt_tj_promoters_dw').combotree('getValue') == undefined ? userInfo.DWBM : $('#cbt_tj_promoters_dw').combotree('getValues').join(",");//评查单位编码
    obj.bmbm = $("#cbt_tj_promoters_bm").combotree('getValues').join(",");
    obj.cbrmc = $('#txt_tj_promoters_cbr').textbox('getValue');//办案检察官员
    obj.startDate = $('#date_tj_promoters_start').datebox('getValue');//完成日期开始
    obj.endDate = $('#date_tj_promoters_end').datebox('getValue');//完成日期结束
    obj.pcstartDate = $('#date_pc_promoters_start').datebox('getValue');//评查日期开始
    obj.pcendDate = $('#date_pc_promoters_end').datebox('getValue');//评查日期结束

    obj.cbrsf  =  $("#cbt_win_eval_build_cbrsf").combotree('getValues').join(",").trim();

    $('#grid_tj_promoters').datagrid({
        url: getRootPath() + '/count/countPcqkOrBaqk',
        async: false,
        queryParams: {jsonStr: JSON.stringify(obj)}
    });
}
function alert_win_filter(el,index) {
    var currField = $(el).attr("data-field");
    var rowDatas = $('#grid_tj_promoters').datagrid('getRows');
    var dwbm = rowDatas[index].BPC_DWBM;
    // 判断用户是否具有权限
    if (DJDWBM !=userInfo.DWBM && dwbm != userInfo.DWBM) {
        return;
    }
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.bmbm =  rowDatas[index].BPC_BMBM;
    obj.cbrmc =  rowDatas[index].BPC_MC;//办案检察官员
    obj.startDate = $('#date_tj_promoters_start').datebox('getValue');//完成日期开始
    obj.endDate = $('#date_tj_promoters_end').datebox('getValue');//完成日期结束
    obj.pcstartDate = $('#date_pc_promoters_start').datebox('getValue');//评查日期开始
    obj.pcendDate = $('#date_pc_promoters_end').datebox('getValue');//评查日期结束
    obj.pcjl = currField;//评查日期结束
    columns = [[
        {field:'BMSAH',title:'部门受案号',width:160 },
        {field:'AJMC',title:'案件名称',width:160,
            formatter: function (value) { return tipMessage(value); }},
        {field:'AJLBMC',title:'案件类别',width:90},
        {field:'DWMC',title:'承办单位',width:90},
        {field:'PCR_DWMC',title:'评查单位',width:90},
        {field:'CBRMC',title:'承办检察官',width:90},
        {field:'CJSJ',title:'评查日期', fixed:true, width: 115 ,
            formatter: function (value) {
                return sjzh(value);
            }
        },
        {field: 'PCFLBM', title: '操作', width: 80, align: 'center',
            formatter: function (value, row, index) {
                var r=(value=="009")? '<a href="#" onclick="pcWin_pcCountWin(' + index +')">查看</a>':'<a href="#" onclick="pcWinPages(' + index +',\'#table_pcCount_filter\',0)">查看</a>';
                return r;
            }
        }
    ]];

    $('#table_pcCount_filter').datagrid({
        border:0,
        fit:true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        url : getRootPath() + '/count/loadPcInfo',
        queryParams: {jsonStr: JSON.stringify(obj)},
        pagination:true,
        pageNumber:1,
        pageSize:20,
        pageList: [10, 20, 30, 50, 100],
        columns:columns,
        loadFilter:function (data) {
            return data.code ==200 ? JSON.parse(data.data):[];
        }
    });

  /*  $('#table_pc_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });*/

    $('#win_pcCount_filter').window('open');

}
// 办理win弹框加载
// 跳转到评查办理界面
// type类别 0：评查信息  1：案件信息
function pcWinPages(index,id,type) {
    var rowDatas = $(id).datagrid('getRows');
    if(type==''||type==null||type==undefined){
        type=0;
    }
    var obj = new Object();
    obj.PCSLBM = rowDatas[index].PCSLBM ;
    obj.BMSAH = rowDatas[index].BMSAH;
    obj.PCCZLX = '0'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = ''; //仅评查审批阶段有
    obj.type=type;
    var url = "view/evaluate/pcblWin/deal.html";
    loadPcblWin(url, obj);
}

function loadPcblWin(href, param) {

    // 换从功能参数，作为参数传递
    FUNCTION_PARAM = param;
    if(FUNCTION_PARAM.type==1){
        skipWebPage("案件信息", getRootPath() + "/case.html", { pcslbm: "", bmsah: param.BMSAH }, '1');
    }else {
        $('#pcblWin').window('open').window('refresh', href);
    }
}
function pcWin_pcCountWin(index) {
    $('#pcWin_pcylCon').find("table").remove();
    var datas=$('#table_pcCount_filter').datagrid('getRows')[index];
    $('#pcWin_win').window('open');
    // 评查案件信息初始化
    $('#win_pcWin_ajmc').text(datas.AJMC);
    $('#win_pcWin_cbr').text(datas.BPC_MC);
    $('#win_pcWin_pcr').text(datas.PCR_MC);
    $('#win_pcWin_pcsah').text(datas.PCSAH);
    $('#win_pcWin_pcsj').text(sjzh(datas.CJSJ));
    $('#win_pcWin_ajsj').text(sjzh(datas.BPC_WCRQ));
    // $('#win_pcWin_lbl_eval_handle_eval_ay').text(data.AY);
    $.ajax({
        url: getRootPath() + '/offline/getPcjgInfo',
        type: 'get',
        dataType: 'json',
        data: {pcslbm:datas.PCSLBM,dw:datas.PCDWBM},
        success: function (result) {
            if (result.status == 200) {
                var data = result.value;
                var html = '<table border="">';
                for (var i = 0; i < data.length; i++) {
                    html += '<tr class="thead">';
                    var htmlTit = '';
                    var htmlCon = '';

                    for (var z = 0; z < data[i].children.length; z++) {
                        htmlTit += '<td>' + data[i].children[z].pcxflmc + '</td>';
                        var pcList = data[i].children[z].children.length;
                        var pclistCon = '';
                        var num = 1;
                        for (var j = 0; j < data[i].children[z].children.length; j++) {
                            var bz = "";
                            if(data[i].children[z].children[j].sm == 'null' || data[i].children[z].children[j].sm == '' || data[i].children[z].children[j].sm == undefined || data[i].children[z].children[j].sm =="" || data[i].children[z].children[j].sm == null){
                                data[i].children[z].children[j].sm = "";
                            }else{
                                bz = "(备注:"+ data[i].children[z].children[j].sm+")";
                            }
                            if (data[i].children[z].children[j].PCFS == '1' && data[i].children[z].children[j].pcjg == '1') {
                                pclistCon += '<p>' + num + '.' + data[i].children[z].children[j].pcxmc +bz +'</p>';
                                num++;
                            } else if (data[i].children[z].children[j].PCFS == '2' && data[i].children[z].children[j].pcjg != '1' && data[i].children[z].children[j].pcjg != '0' && data[i].children[z].children[j].pcjg != "" && data[i].children[z].children[j].pcjg != undefined && data[i].children[z].children[j].PCFS != null) {
                                pclistCon += '<p>' + num + '.' + data[i].children[z].children[j].pcxmc + '(' + data[i].children[z].children[j].pcjg + ')' + bz + '</p>';
                                num++;
                            }
                        }
                        htmlCon += '<td>' + pclistCon + '</td>';

                    }
                    html += '<td rowspan="2">' + data[i].pcxflmc + '</td>';
                    html += htmlTit;
                    html += '</tr>';
                    html += '<tr class="tbody">';
                    html += htmlCon;
                    html += '</tr>';

                }

                html += '</table>';
                $('#pcWin_pcylCon').html(html);
            }
        }
    });
}
