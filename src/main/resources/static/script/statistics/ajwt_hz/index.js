var cbdwbm;
var cwx_select_rowData;//错误项行数据
$(document).ready(function () {

    // 样式初始化以及事件绑定
    init_seacth_evel();//init_statistics_analysis();
    init_monitor_statistiscs_dw();
    init_table_tccw_cwx_bz()
    $("#pcWin_win_offline").hide();
});

function init_seacth_evel() {
    //评查单位树
    $('#errorItem_dw_combotree').combotree({
        method: 'get',
        editable: false,
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
                $('#errorItem_dw_combotree').combotree('setValue', data[0].id); //单位默认选择
            }
            var root = $('#errorItem_dw_combotree').combotree('tree').tree('getRoot');
            var children=root.children
            var valueArr = new Array();
            valueArr.push(data[0].id)
            for(var i= 0;i<children.length;i++){
                valueArr.push(children[i].id);
            }
            $('#errorItem_dw_combotree').combotree("setValues", valueArr);
            index_addMousedownDiv(this,"errorItem_dw_combotree");
        }
    });
//年份
    $('#errorItem_date').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                setAllCheckbox('#errorItem_date', data);
            }
            index_addMousedownDiv(this, "errorItem_date");
        }
    });
    $('#errorItem_date').combotree("loadData", getYearRange());
    //承办人身份
    $('#general_cbrsf').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#general_cbrsf').combotree('setValues', ['N','Y']);
            }
            index_addMousedownDiv(this, "general_cbrsf");
        }
    });
    $('#general_cbrsf').combotree("loadData", getCbrsfValues());
    //评查方式
    $('#general_pcfl').combotree({
        method: 'get',
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var valueArr = new Array();
            if (data != null && data.length >= 1){
                for(var i= 0;i<data.length;i++){
                    valueArr.push(data[i].id);
                }
                $('#general_pcfl').combotree("setValues", valueArr);
            }
            index_addMousedownDiv(this, "general_pcfl");
        },
        onSelect: function (node) {
            if (!node) {
                Alert("请重新选择评查方式！");
                return;
            }
        }
    });
    /*$('#general_pcmb').combotree('loadData', []);
    $('#general_pcmb').combotree('clear');
    $('#general_pcmb').combotree('setValue', '');
*/
    $('#general_pcmb').combotree({
        editable: false,
        method: 'get',
        panelWidth: 160,
        lines: true,
        multiple: false,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/analysis/getPcdmByFdm',
        onLoadSuccess: function (node, data) {
            // 默认选中
           $('#general_pcmb').combotree("setValue", data[0].id);
           index_addMousedownDiv(this, "general_pcmb");
        },
    });
    $('#stajbs').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                $('#stajbs').combotree('setValues', ['0','1']);
            }
            index_addMousedownDiv(this, "stajbs");
        }
    });
    $('#stajbs').combotree("loadData", getStajbs());
    $('#flxtdmType').combotree({
        editable: false,
        panelWidth: 160,
        lines: true,
        multiple: false,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                // $('#flxtdmType').combotree('setValues', ['30002','30003']);
                $('#flxtdmType').combotree('setValue', '30002');
            }
            index_addMousedownDiv(this, "flxtdmType");
        }
    });
    $('#flxtdmType').combotree("loadData", flxtdmType());
    var resizeTabHeight = $("#ajwt_tabsBox").height() - 100;
    $("#ajwt_tabsBox").find(".panel-body").height(resizeTabHeight);
}

// 单位tab样式以及事件：
function init_monitor_statistiscs_dw() {

    // 单位表格查询：
    $("#btn_ajwt_dw_search").unbind('click');
    $("#btn_ajwt_dw_search").bind('click', function () {
        data_monitor_statistiscs_dw();
    });

    // 单位表格datagrid
    $('#table_ajwt_statistiscs_dw').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field: 'pcxflfmc',
                title: '<span  style=\'font-size:16px;\'>项目</span>',
                rowspan: 2,
                width: 120,
                align: 'center',
                halign: 'center'
            },
            {
                title: '<span  style=\'font-size:16px;margin-top: 15px;display: inline-block;\'>具体内容及扣分标准</span>',
                colspan: 2
            },
            {
                field: 'wts',
                width: 120,
                title: '<span  style=\'font-size:16px\'>存在该问题<br>项的案件数</span>',
                rowspan: 2,
                align: 'center',
                halign: 'center',
                formatter: function (value, row, index) {
                    var r = '';
                    if (row.dwbm == userInfo.DWBM || userInfo.DWBM == DJDWBM) {
                        r = '<a id="tgb_a_ajwt_hz_' + index + '" ajs="' + value + '" href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_tcxc_cwx_jbxx_window(' + index + ',\'#table_ajwt_statistiscs_dw\')">' + value + '</a> ';
                    } else {
                        r = value;
                    }
                    return r;
                }
            }
        ], [
            {
                field: 'pcxflmc',
                width: 64,
                title: '<span  style=\'font-size:16px\'>子项</span>',
                align: 'center',
                halign: 'center'
            },
            {
                field: 'pcxmc',
                title: '<span  style=\'font-size:16px\'>评查项内容</span>',
                // width: 180,
                align: 'left'
            }
        ]],
        loadFilter: function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        },
        onLoadSuccess: function (data) {
            if (data.rows.length > 0) {
                //调用mergeCellsByField()合并单元格
                mergeCellsByField("table_ajwt_statistiscs_dw", "pcxflfmc,pcxflmc");
                getColorOrderAjwthz();
            }
        },
        onSelect:function (rowIndex, rowData) {
            if(rowIndex != -1){
                cwx_select_rowData = rowData;
                $("#btn_tccw_cwx_bz_expExcel").removeAttr("disabled");
            }
            load_table_tccw_cwx_bz(rowData);
        }

    });
}
// 展示单位表格数据：
function data_monitor_statistiscs_dw() {
    var obj=new Object();
    var dwbm = $('#errorItem_dw_combotree').combotree('getValues').length==0?"":$('#errorItem_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    if($('#errorItem_date').combotree('getValues').length==0){
        Alert("请选择时间");
        return;
    }
    obj.date= $('#errorItem_date').combotree('getValues').length==0?"":$('#errorItem_date').combotree("getValues").join(",");
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    obj.flxtdm=$('#flxtdmType').combotree('getValues').length==0||$('#flxtdmType').combotree('getValues').length==2?"":$('#flxtdmType').combotree('getValues').join(",");
    var url = getRootPath() + "/queryTable/getDwAjwthzTableData";

    $('#table_ajwt_statistiscs_dw').datagrid({
        url: url,
        method: 'post',
        queryParams: {json : JSON.stringify(obj)}
    });

}
function init_table_tccw_cwx_bz() {

    $('#table_tccw_cwx_bz').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field: "SM",
                title: '<span  style=\'font-size:16px;\'>备注说明(已过滤重复内容和空备注)</span>',
                align: 'left',
                halign: 'center',
                formatter: function (value, row, index) {
                    var  r = '<a href="#"  style="color: #145bae;text-decoration: none;"   onclick="alert_tcxc_cwx_jbxx_window(' + index + ',\'#table_tccw_cwx_bz\')">' + value + '</a> ';
                    return r;
                }
            }
        ]],
        loadFilter: function (result) {
            return result.code == 200 ? JSON.parse(result.data) : [];
        }
    });
}
// 错误项备注数据获取
function load_table_tccw_cwx_bz(node) {
    var obj=new Object();
    obj.wcrqnf = $('#errorItem_date').combotree('getValues').join(",").trim();
    if(obj.wcrqnf == ""){
        Alert("请选择年度!");
        return;
    }
    var dwbm = $('#errorItem_dw_combotree').combotree('getValues').join(",").trim();
    if(dwbm == ""){
        Alert("请选择承办单位!");
        return;
    }
    obj.dwbm = dwbm;
    obj.pcflbm = $('#general_pcfl').combotree('getValues').join(",").trim();
    if(obj.pcflbm == ""){
        Alert("请选择评查方式!");
        return;
    }
    obj.ywtx = $('#general_pcmb').combotree('getValues').join(",").trim();
    if( obj.ywtx == ""){
        Alert("请选择评查模板!");
        return;
    }
    var cbrsfStr = $('#general_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(cbrsfStr, ",");
    obj.ajtjlb = $('#stajbs').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if (obj.ajtjlb == "") {
        Alert("请选择案件统计类别!");
        return;
    }
    obj.flxtdm = node.pcxflbm;//错误编码
    obj.xtdm = node.pcxbm;
    obj.fflxtdm =node.pcxflfbm;
    $('#table_tccw_cwx_bz').datagrid({
        url: getRootPath() + "/analysis/getPcxBzByXtdm",
        method: 'post',
        queryParams: {json: JSON.stringify(obj)}
    });
}
function alert_tcxc_cwx_jbxx_window(index, name) {
    var obj = new Object();
    var thisRow = $(name).datagrid('getRows')[index];
    obj.wcrqnf = $('#errorItem_date').combotree('getValues').join(",").trim();
    obj.dwbm = $('#errorItem_dw_combotree').combotree('getValues').join(",").trim();
    obj.pcflbm = $('#general_pcfl').combotree('getValues').join(",").trim();
    obj.ywtx = $('#general_pcmb').combotree('getValues').join(",").trim();
    obj.cbrsf = $('#general_cbrsf').combotree('getValues').join(",").trim();
    obj.cbrsf = getSplitString(obj.cbrsf, ",");
    obj.ajtjlb = $('#stajbs').combotree('getValues').join(",").trim();
    obj.ajtjlb = getSplitString(obj.ajtjlb, ",");
    if(name == "#table_ajwt_statistiscs_dw"){
        obj.xtdm = thisRow.pcxbm;
        obj.fflxtdm =thisRow.pcxflfbm;
        obj.wtType=thisRow.pcxflbm;
    }else{
        obj.xtdm = thisRow.XTDM;
        obj.sm = thisRow.SM;
        obj.wtType=thisRow.FLXTDM;
        obj.fflxtdm =thisRow.FFLXTDM;
    }
    console.log(obj);
    var ypcUrl = getRootPath() + "/analysis/getTccwxxPcAjxxByParams";

    // 已评查案件datagrid
    $("#table_eval_build_statistics_ajwthz_filter").datagrid({
        border: 0,
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        url: ypcUrl,
        queryParams: obj,
        columns: [[
            {field: 'BMSAH', title: '部门受案号', width: 160},
            {
                field: 'AJMC', title: '案件名称', width: 160,
                formatter: function (value) {
                    return tipMessage(value);
                }
            },
            {field: 'AJLBMC', title: '案件类别', width: 90},
            {field: 'DWMC', title: '承办单位', width: 90},
            {field: 'CBRMC', title: '承办检察官', width: 90},
            {field: 'PCR_DWMC', title: '评查单位', width: 90},
            {
                field: 'CJSJ', title: '评查日期', fixed: true, width: 115,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {
                field: 'CZ', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    return  '<a href="#" onclick="pcWin_xlpcLn(' + index +')">查看</a>';
                }
            }
        ]]

    });

    // 分页控件(中文)
    $('#table_eval_build_statistics_ajwthz_filter').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    $("#win_eval_build_statistics_ajwthz_filter").window('setTitle', '已经评查案件');
    $("#win_eval_build_statistics_ajwthz_filter").window('open');
}

function pcWin_xlpcLn(index) {
    $('#pcWin_win_offline').window({
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
    $('#pcWin_pcylCon_offline').find("table").remove();
    var datas=$('#table_eval_build_statistics_ajwthz_filter').datagrid('getRows')[index];
    $('#pcWin_win_offline').window('open');
    $("#pcWin_win_offline").show();
    // 评查案件信息初始化
    $('#win_pcWin_ajmc').text(datas.AJMC);
    $('#win_pcWin_cbr').text(datas.BCBRMC);
    $('#win_pcWin_pcr').text(datas.CBRMC);
    $('#win_pcWin_pcsah').text(datas.BMSAH);
    $('#win_pcWin_pcsj').text(sjzh(datas.CJSJ));
    $('#win_pcWin_ajsj').text(sjzh(datas.WCRQ));
    // $('#win_pcWin_lbl_eval_handle_eval_ay').text(data.AY);
    $.ajax({
        url: getRootPath() + '/offline/getPcjgInfo',
        type: 'get',
        dataType: 'json',
        data: {pcslbm:datas.PCSLBM,dw:datas.CBDWBM},
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
                $('#pcWin_pcylCon_offline').html(html);
            }
        }
    });
}

/**
 * EasyUI DataGrid根据字段动态合并单元格
 * 参数 tableID 要合并table的id
 * 参数 colList 要合并的列,用逗号分隔(例如："name,department,office");
 */
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var curPcxflfmc = "";
    var perPcxflfmc = "";
    for (j = ColArray.length - 1; j >= 0; j--) {
        PerTxt = "";
        perPcxflfmc = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
                curPcxflfmc = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
                curPcxflfmc = tTable.datagrid("getRows")[i]["pcxflfmc"];
            }
            if (PerTxt == CurTxt && curPcxflfmc == perPcxflfmc) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;

                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],　　//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
                tmpA = 1;
            }
            PerTxt = CurTxt;
            perPcxflfmc = curPcxflfmc;
        }
    }
}

function export_excel_ajwthz() {
    var obj=new Object();
    var dwbm = $('#errorItem_dw_combotree').combotree('getValues').length==0?"":$('#errorItem_dw_combotree').combotree('getValues').join(",");
    if(dwbm==""){
        Alert("请选择单位");
        return;
    }
    obj.dwbm=dwbm;
    if($('#errorItem_date').combotree('getValues').length==0){
        Alert("请选择时间");
        return;
    }
    obj.date= $('#errorItem_date').combotree('getValues').length==0?"":$('#errorItem_date').combotree("getValues").join(",");
    obj.pcflbm =$('#general_pcfl').combotree('getValues').length==0?"":$('#general_pcfl').combotree('getValues').join(",");
    obj.ywtx=$('#general_pcmb').combotree('getValues').length==0||$('#general_pcmb').combotree('getValues').length==8?"":$('#general_pcmb').combotree('getValues').join(",");
    obj.sfld=$('#general_cbrsf').combotree('getValues').length==0||$('#general_cbrsf').combotree('getValues').length==2?"":$('#general_cbrsf').combotree('getValues').join(",");
    obj.stajbs=$('#stajbs').combotree('getValues').length==0||$('#stajbs').combotree('getValues').length==2?"":$('#stajbs').combotree('getValues').join(",");
    obj.flxtdm=$('#flxtdmType').combotree('getValues').length==0||$('#flxtdmType').combotree('getValues').length==2?"":$('#flxtdmType').combotree('getValues').join(",");
    var url = getRootPath() + "/queryTable/exportDwAjwthzTableDataExcel";

    $.ajax({
        url: url,
        type: "get",
        data: obj,
        success: function (data) {
            if (data.code == 200) {
                window.location.href = getRootPath() + data.data;
            }
            CloseProgress();
        },
        error: function () {
            CloseProgress();
        }
    });
}


//数字大小颜色排序,没有考虑数字重复
function getColorOrderAjwthz() {
    var dgRow = $('#table_ajwt_statistiscs_dw').datagrid('getData');
    //倒序函数
    function sortNumber(a, b) {
        return b - a;
    }

    if (dgRow && dgRow.rows) {
        var alinks = $("a[id*='tgb_a_ajwt_hz_']");
        var ajsArr = [];
        $(alinks).each(function (i) {
            ajsArr.push(parseInt($(this).attr('ajs')));
        });
        //排序
        ajsArr.sort(sortNumber);
        // 定义颜色序列
        var colorArr = ['#ff0000'
            , '#eF4500'
            , '#CD5C5C'
            , '#b60000'
            , '#660000'];
        $(ajsArr).each(function (i) {
            if (i < 5) {//定义排前5的颜色
                $(alinks).each(function (j) {
                    if (ajsArr[i]>0 && $(this).attr('ajs') == ajsArr[i]) {
                        $(this).css('color', colorArr[i]);
                        $(this).css('font-weight', 'bold');
                        $(this).css('font-size', (18 - i) < 14 ? 14 : (18 - i) );
                    }
                });
            }
        });

    }

}



