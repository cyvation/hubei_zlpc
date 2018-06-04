//@ sourceURL=datagrid.js
var queryData;
var isPaging;
var rowsconfig;
var datagridconfig;
var filter = "";
var perseObj = "";


$(document).ready(function () {
    document.body.style.zoom = !!param.zoom ? param.zoom : getZoom();
    //document.write("<input id=\"ckbDWBM\" class=\"r-input\" type=\"checkbox\" name=\"calogin\" checked=\"true\">");   
    //document.body.appendChild("<table id=\"dg\" border=\"0\" style='width:100%;height:100%'></table>");
    if (dgid == "")
        alert("编号为空，不能生成列表！");
    //isPaging = !param.isPaging ? true : param.isPaging == "true" ? true : false;
    //alert(dgid);

//    $('#btnSearch').unbind('click');
//    $("#btnSearch").click(function () {
//        Search();
//    });

    InitDataGrid();

    if (frameObject != null) {
        frameObject.ClearPicture();
    }
});

function InitDataGrid() {
    //document.write("<table id=\"dg" + dgid + "\" border=\"0\" style='width:100%;height:100%'></table>");
    $.ajax({
        type: "post",
        async: true,
        data: { dgid: dgid, provinceid:provinceid },
        url: "/Handler/Platform/PlatformHandler.ashx?action=DataGridConfig",
        success: function(result) {
            var config = '';
            try {
                config = eval("(" + result + ")");
            } catch (e) {
                document.write(result);
                return;
            } 
            rowsconfig = eval("(" + config.datagridrow + ")");
            datagridconfig = eval("(" + config.datagrid + ")");
            isPaging = datagridconfig[0].ispaging == 1 ? true : false;
            AddJSScript(datagridconfig[0].dghtmlbf.myReplace('<br>',''));

            //document.write("<script></script>");
            //document.write(getParameter('dgid'));
           
           var dgrow = [[]];
            for (var i = 0; i < rowsconfig.length; i++) {
                dgrow[0][dgrow[0].length] = { field: rowsconfig[i].colname, title: rowsconfig[i].title, align: rowsconfig[i].align, width: rowsconfig[i].width, fixed: rowsconfig[i].fixed == 1 ? true : false, hidden: rowsconfig[i].hidden == 1 ? true : false };
            }

            var buttons = '<a></a>';
            // canaddnew, canedit, candelete, canfilter, cansort
            if (datagridconfig[0].canaddnew == 1) {
                buttons += '&nbsp;&nbsp;<a href=javascript:dgFilter()>'+ datagridconfig[0].addnewtext +'</a>';
            }
            if (datagridconfig[0].canedit == 1) {
                buttons += '&nbsp;&nbsp;<a href=javascript:dgEdit()>'+ datagridconfig[0].edittext +'</a>';
            }
            if (datagridconfig[0].candelete == 1) {
                buttons += '&nbsp;&nbsp;<a href=javascript:dgDelete()>'+ datagridconfig[0].deletetext +'</a>';
            }
            if (datagridconfig[0].canfilter == 1) {
                buttons += '&nbsp;&nbsp;<a href=javascript:dgFilter()>'+ datagridconfig[0].filtertext +'</a>';
            }
            if (datagridconfig[0].cansort == 1) {
                buttons += '&nbsp;&nbsp;<a href=javascript:dgSort()>'+ datagridconfig[0].sorttext +'</a>';
            }
            buttons += datagridconfig[0].dgbtnhtml;

            if (datagridconfig[0].dgmode == 1) {
                var option = {
                    width: 'auto',
                    striped: true,
                    fitColumns: true,
                    singleSelect: true,
                    //设置复选框和行的选择状态不同步
                    checkOnSelect: false,
                    selectOnCheck: false,
                    pagination: isPaging,
                    rownumbers: true,
                    toolbar: $('#panelTool_ajjbxx'),
                    sortable: true,
                    pageSize: 20,
                    pageList: [10, 20, 30, 50, 100],
                    columns: dgrow,
                    loadMsg: '数据加载中，请稍候...'

                };
                $('#dg').datagrid(option);
                var limit = gnbm == "" ? "" : GetLimitCount();
                var displayMsg = '当前显示【{from} ~ {to}】条记录   共【{total}】条记录';
                displayMsg += limit == "0" || limit == "" ? '' : '   限制【' + limit + '】条记录';
                $('#dg').datagrid('getPager').pagination({
                    beforePageText: '第',
                    afterPageText: '页   共{pages}页',
                    buttons: buttons,
                    displayMsg: displayMsg
                });

                queryData = { provinceid:provinceid };
                $('#dg').datagrid("options").queryParams = queryData;
                $('#dg').datagrid("options").url = "/Handler/Platform/PlatformHandler.ashx?action=DataGridData" + location.search.replace("?","&");
                $('#dg').datagrid("load");

                if (param.opentype != null && param.opentype.substr(0, 1) == "1") {
                    $('#dg').datagrid('resize', {
                        width: $(window).width() - 20,
                        height: $(window).height() - 20
                    });
                }
                $("#DataGrid_div_daochu").hide();
                $("#DataGrid_div").css("height","100%");
            } else {
                var rootid = param.fdwbm ? param.fdwbm : 100000;
                var treeid = param.treeid ? param.treeid : "DWBM";
                var treepid = param.treepid ? param.treepid : "FDWBM";
                var treepname = param.treepname ? param.treepname : "DWMC";
                var treesname = param.treesname ? param.treesname : "DWJC";
                queryData = { provinceid: provinceid, dgmode: 2, tid: treeid, tpid: treepid, tnamecol: treepname, rootid: rootid, lnamecol: treesname };
                $('#dg').treegrid({
                    idField: 'dwbm',
                    queryParams: queryData,
                    url: '/Handler/Platform/PlatformHandler.ashx?action=DataGridData' + location.search.replace("?","&"),
                    loadMsg: '数据加载中，请稍候...',
                    rownumbers: true,
                    animate: true,
                    treeField: 'dwmc',
                    fitColumns: true,
                    expandible: false,
                    columns: dgrow,
                    onLoadSuccess: function() {
                        var data = $('#dg').treegrid('getData');
                        if (data[0].state == 'closed') {
                            data[0].state = 'open';
                            $('#dg').treegrid('loadData', data);
                        }
                    }
                });
                // 添加按钮
                if (datagridconfig[0].dgbtnhtml != "") {
                    $("body").append("<div style=\"height: 40px;width: 100%;\" id=\"DataGrid_div_daochu\"> <div style=\"width:100%;height:30px;line-heigth:30px;text-align: right;color:#0ff;position: absolute;bottom: 0px;    padding-right: 56px;background:#06274c\" class=\"window_datagird_bottom\">" + datagridconfig[0].dgbtnhtml + "</div> </div>");
                    $("#DataGrid_div_daochu").show();
                    $("#DataGrid_div").css("height", "calc(100% - 40px)");
                }
            }
        },
        error: function (data) {
            console.log("Error:" + data.responseText);
            document.write(data.responseText);
        }
    });

}

function tansParamFun(value) {
    var rel = value;
    try {
        rel = eval(value);
        if (value.substr(0, 1) == "'" && value.substr(value.length - 1, 1) == "'") {
            rel = "'" + rel + "'";
        }
    } catch (e) {

    }
    return rel;
}

function tansDwbm(dwbm, sjtype) {
    var rel = dwbm;
    if (sjtype == "1") {
        rel = dwbm;
    } else {
        if (dwbm.toString().substr(4, 2) != "00") {
            rel =  dwbm ;
        } else if(dwbm.toString().substr(2, 2) != "00") {
            rel =  dwbm.toString().substr(0, 4) + "%";
        } else {
            rel =  dwbm.toString().substr(0, 2) + "%";
        }
    }
    return rel;
}

//把ostr替换成nstr
String.prototype.myReplace= function(ostr, nstr) {
    var reg = new RegExp(ostr, "g"); //创建正则RegExp对象   
    return this.replace(reg, nstr);
}

function onClickAJXX(dwbm, dgid, ajlbbm, opentype, title) {
    //alert([dwbm,dgid,ajlb,opentype,title]);
    //alert([window.location.href,baseUrl]);
    if (dgid == "") {
        return;
    }
    var sdwbm = dwbm.toString().length == 6 ? tansDwbm(dwbm, getParameter('sjtype')) : dwbm.toString().substr(1, 6);
    var handlerUrl = "/View/Platform/datagrid.htm?dgid=" + dgid + "&dwbm=" + sdwbm + "&ajlbbm=" + ajlbbm + "&zoom=1"
        + location.search.replace('?', '&');
    //+ "&sjtype=" + getParameter('sjtype') + "&startdate=" + getParameter('startdate') + "&enddate=" + getParameter('enddate');
    if (opentype == 0) {
        window.location.href = baseUrl + handlerUrl;
    } else if (opentype == 1) {
        frameObject.OpenDialogWeb(3, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '', true);
    } else if (opentype == 2) {
        frameObject.OpenDialogWeb(0, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '');
    } else {
        frameObject.OpenDialogWeb(3, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '', true);
    }
}

function onClickAJXX1(dgid, ajlbbm, ajlbmc, opentype, title) {
    //alert([dwbm,dgid,ajlb,opentype,title]);
    //alert([window.location.href,baseUrl]);
    if (dgid == "") {
        return;
    }
    var dwbm = getParameter('dwbm');
    var handlerUrl = "/View/Platform/datagrid.htm?dgid=" + dgid + "&dwbm=" + tansDwbm(dwbm, getParameter('sjtype')) + "&itemname=" + ajlbmc + "&ajlbbm=" + ajlbbm + "&zoom=1"
        + location.search.replace('?', '&');
    if (opentype == 0) {
        window.location.href = baseUrl + handlerUrl;
    } else if (opentype == 1) {
        frameObject.OpenDialogWeb(3, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '', true);
    } else if (opentype == 2) {
        frameObject.OpenDialogWeb(0, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '');
    } else if (opentype == 3) {
        parent.ShowThisDialog(handlerUrl + "&opentype=" + opentype, title);
    } else {
        frameObject.OpenDialogWeb(3, baseUrl + handlerUrl + "&opentype=" + opentype, '', '', title, '', true);
    }
}

function openCase(bmsah, ajlbbm) {
    //alert([bmsah, ajlbbm]);
    var url = '';
    if (queryData.caseType == null || queryData.caseType == 0) {
        url = caseUrl;
    }
    else if (queryData.caseType == 1) {
        url = caseLCJKUrl;
    }
    else if (queryData.caseType == 2) {
        url = caseErrorUrl;
    }

    frameObject.OpenDialogWeb(1, baseUrl + url + '?bmsah=' + bmsah, '', '{ "AJLBBM": "' + ajlbbm + '" }', '', selectedUrl);
}



function dgAddnew() {
    alert("Addnew");
}

function dgEdit() {
    alert("dgEdit");
}

function dgDelete() {
    alert("dgDelete");
}

function dgFilter() {
    perseObj = "";
    //移除存在的Dialog
    $("#datafilter").remove(); // $("body").remove("#editform");
    //先根据div的id删除，但界面元素还是会存在dialog div，还需执行dialog的销毁操作**
    $("#datafilter").dialog('destroy');
    //创建窗口
    createFilterDialog("datafilter", "筛选", 600, 400);
    if (perseObj != "") {
        var objID = perseObj.split('~');
        for (i = 0; i < objID.length; i++) {
            $.parser.parse("#" + objID[i]);
        }
    }
    $("#datafilter").dialog('open');
    //$.parser.parse("#datafilter");
    
}

function dgSort() {
    alert("dgSort");
}

// 保存数据值excel
function SavetoExcel() {
    //alert("fsavedata");
    var rootid = param.fdwbm ? param.fdwbm : 100000;
    var treeid = param.treeid ? param.treeid : "DWBM";
    var treepid = param.treepid ? param.treepid : "FDWBM";
    var treepname = param.treepname ? param.treepname : "DWMC";
    var queryData = {
        action: 'SavetoExcel',
        filter: filter,
        dgmode: datagridconfig[0].dgmode,
        tid: treeid,
        tpid: treepid,
        tnamecol: treepname,
        rootid: rootid,
        provinceid: provinceid
    };
    var url = '/Handler/Platform/PlatformHandler.ashx' + location.search;
    ShowProgress();
    $.post(url, queryData,
        function(result) {
            CloseProgress();
            frameObject.DownFiles(result);
        });
}

/**
 * 创建一个模态 Dialog
 * 
 * @param id divId
 * @param _url Div链接
 * @param _title 标题
 * @param _width 宽度
 * @param _height 高度
 * @param _icon ICON图标
 */
function createModalDialog(id, _url, _title, _width, _height, _icon){
    $("body").append("<div id='" + id + "' class='easyui-window'> </div>");
    if (_width == null)
        _width = 600;
    if (_height == null)
        _height = 800;

    $("#"+id).dialog({
        title: _title,
        width: _width,
        height: _height,
        cache: false,
        iconCls: _icon,
        href: _url,
        collapsible: false,
        minimizable:false,
        maximizable: false,
        resizable: false,
        modal: true,
        closed: true
    });
}

// 筛选对话框
function createFilterDialog(id, _title, _width, _height, _icon) {
    //var html = "<div class=\'time\' style=\'margin: 0px 20px 10px 0px\'> <span id=\'\' style=\'color: #018ab7\'>承办部门： </span> <div class=\'kais\' style=\'width: 160px\'> <input id=\'cb_ajjbxx_bmbm\' class=\'easyui-combotree Company cc\' name=\'dept\'/> </div> </div>";
    //var btnhtml = "<div id=\"btnSearch\" class=\"wbk\" style=\"float: right;margin: 0px 20px 10px 0px\" data-options=\"iconCls:'icon-search'\" ><div class=\"cxan_ys\"></div> <span style=\"padding: 0px 16px 0px 6px\">确定</span> </div>";
    var btnhtml = "<div style=\"position:absolute;bottom:20px;right:20px\"> <input name=\"btn_search\" type=\"button\" onClick=\"Search()\" value=\"确定\"><\/input> </div>";
    //btnhtml = "<div id=\"btnSearch\" class=\"wbk\" style=\"float: right;margin: 0px 20px 10px 0px\" data-options=\"iconCls:'icon-search'\" ><div class=\"cxan_ys\"></div> <input name=\"按钮\" type=\"button\" onClick=\"Search()\" value=\"按钮\"><\/input> </div>";
    var html = "";
    for (i = 0; i < rowsconfig.length; i++) {
        if (rowsconfig[i].filter == "1") {
            html += CreateObjetHtml(rowsconfig[i]);
        }
    }

    //$("body").append("<div id='" + id + "' class='easyui-window'>" + html + btnhtml + "</div>");
    $("body").append("<div id='" + id + "' class=\"easyui-window\" title=\"My Window\" style=\"width:600px;height:400px\"data-options=\"iconCls:'icon-save',modal:true\"> <div style=\"width:100%;height:100% ;position: relative\"> " + html + btnhtml + " </div> </div> ");
    if (_width == null)
        _width = 600;
    if (_height == null)
        _height = 800;

    $("#"+id).dialog({
        title: _title,
        width: _width,
        height: _height,
        cache: false,
        iconCls: _icon,
        href: "",
        collapsible: false,
        minimizable:false,
        maximizable: false,
        resizable: false,
        modal: true,
        closed: true
    });
}


function CreateObjetHtml(row) {
    var html;
    switch (row.coltype) {
    case "1":
        html = CreateTextbox(row, "");
        break;
    case "2":
        html = CreateNumberbox(row, "");
        break;
    case "3":
        html = CreateDatebox(row, "");
        break;
    case "4":
        html = CreateDateToDatebox(row, "");
        break;
    case "5":
        html = CreateDownlist(row, "");
        break;
    case "6":
        html = CreateTreeDownlist(row, "");
        break;

    }
    return html;
}

// 创建普通文本框
function CreateTextbox(row, owidth) {
    owidth = owidth == "" ? 200 : owidth;
    var lbl = "<div style=\"width:120px;height:100%;text-align: right;float: left; line-height: 30px;\">" + row.title + "</div>";
    var inputext = "<div id=\"div_"+ row.colname +"\" style=\"float:left;margin-left:10px\"> "
        + "<input id=\""+ row.colname +"\" class=\"easyui-textbox\"  style=\"width:"+ owidth +"px;height:30px\"> "
        + "</div>";
    return "<div style=\"width:100%;height:30px;margin-top:20px;display: inline-block\">" + lbl + CompareCondition(row.colname, "1") + inputext + "</div>";
}

// 创建数字文本框
function CreateNumberbox(oid, otitle, owidth) {
    
}

// 创建日期文本框
function CreateDatebox(oid, otitle, owidth) {
    
}

// 创建日期间隔文本框
function CreateDateToDatebox(row, owidth) {
    owidth = owidth == "" ? 130 : owidth;
    var html = "<div style=\"width:100%;height:30px;margin-top:20px;display: inline-block\">";
    html += "<div style=\"width:120px;height:100%;text-align: right;float: left; line-height: 30px;\">" + row.title + "</div>";
    html += " <div id=\"date_"+ row.colname +"\" style=\"float:left;margin-left:10px\">";
    html += "  <input id=start_" + row.colname + " class=\"easyui-datebox\"  style=\"width:" + owidth + "px;height:30px\"> ";
    html += "&nbsp;至&nbsp;";
    html += "<input id=end_" + row.colname + " class=\"easyui-datebox\"  style=\"width:" + owidth + "px;height:30px\"> ";
    html += "</div>";
    html += "</div>";
    perseObj += "date_" + row.colname + "~";
    return html;
}

// 创建下拉列表框
function CreateDownlist(row, owidth) {
    owidth = owidth == "" ? 290 : owidth;
    var option;
    if (row.listarray != "") {
        option = row.listarray.split("~");
    }
    var html = "<div style=\"width:100%;height:30px;margin-top:20px;display: inline-block\">";
    html += "   <div style=\"width:120px;height:100%;text-align: right;float: left; line-height: 30px;\">" + row.title + "</div>";
    html += "   <div style=\"float: left;margin-left:10px\">";
    html += "   <select id=\""+ row.colname +"\" class=\"easyui-combobox\" name=\""+ row.colname +"\" style=\"width:" + owidth + "px;height:30px\">   ";
    for (i = 0; i < option.length; i += 2) {
        html += "   <option value=\"" + option[i + 1] + "\">" + option[i] + "</option>   ";
    }
    html += "   </select> ";
    html += "  </div>";
    html += "    </div>    ";
    return html;
}

// 创建树状下拉列表框
function CreateTreeDownlist(oid, otitle, owidth) {
    
}


function Search() {
    //var tt = $("#bmsah").val();
    //alert(tt);
    filter = "";
    var tempstr = "";
    var cc = "";
    for (i = 0; i < rowsconfig.length; i++) {
        if (rowsconfig[i].filter == "1") {
            switch (rowsconfig[i].coltype) {
            case "1":
                tempstr = $("#" + rowsconfig[i].colname).val();
                break;
            case"4":
                var startdate = $("#start_" + rowsconfig[i].colname).datebox('getValue');
                var enddate = $("#end_" + rowsconfig[i].colname).datebox('getValue');
                if (startdate != "" && enddate == "") {
                    enddate = getEndDate();
                }
                filter += startdate == "" ? "" : " and " + rowsconfig[i].colname + " >= TO_date('" + startdate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
                filter += enddate == "" ? "" : " and " + rowsconfig[i].colname + " <= TO_date('" + enddate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
                break;
            case "5":
                filter += " and " + rowsconfig[i].colname + " = '" + $("#" + rowsconfig[i].colname).val() + "'";
            default:
            }

        }
        if (tempstr != "") {
            cc = $("#cc_" + rowsconfig[i].colname).val();
            switch (cc) {
            case "等于":
                filter += " and " + rowsconfig[i].colname + " = '" + tempstr + "'";
                break;
            case "大于":
                filter += " and " + rowsconfig[i].colname + " > '" + tempstr + "'";
                break;
            case "大于等于":
                filter += " and " + rowsconfig[i].colname + " >= '" + tempstr + "'";
                break;
            case "小于":
                filter += " and " + rowsconfig[i].colname + " < '" + tempstr + "'";
                break;
            case "小于等于":
                filter += " and " + rowsconfig[i].colname + " <= '" + tempstr + "'";
                break;
            case "左模糊":
                filter += " and " + rowsconfig[i].colname + " LIKE '%" + tempstr + "'";
                break;
            case "右模糊":
                filter += " and " + rowsconfig[i].colname + " LIKE '" + tempstr + "%'";
                break;
            case "全模糊":
                filter += " and " + rowsconfig[i].colname + " LIKE '%" + tempstr + "%'";
                break;

            default:
            }

        }

    }
    //alert(filter);
    //var options = $('#dg').datagrid('getPager').data("pagination").options.total;  
    // 筛选条件为空且datagrid中有数据则直接返回
    if (filter == "" && $('#dg').datagrid('getPager').data("pagination").options.total > 0) {
        $("#datafilter").dialog('close');
        return;
    }
    queryData = {filter: filter, provinceid: provinceid};
    $('#dg').datagrid("options").queryParams = queryData;
    $('#dg').datagrid("options").url = "/Handler/Platform/PlatformHandler.ashx?action=DataGridData" + location.search.replace("?","&");
    $('#dg').datagrid("load");
    $("#datafilter").dialog('close');
}

// 比较条件
function CompareCondition(id,type) {
    var rel = "";
    switch (type) {
    case "1":
        // 普通文本框
        rel = " <div style=\"float: left;margin-left:10px\">"
            + " <select id=\"cc_" + id + "\" class=\"easyui-combobox\" name=\"cc_" + id + "\" style=\"width:80px;height:30px\">"
            + "	<option value=\"等于\">等于</option> "
            + "	<option value=\"左模糊\">左模糊</option> "
            + "	<option value=\"右模糊\">右模糊</option> "
            + "	<option value=\"全模糊\">全模糊</option> "
            + " </select> "
            + " </div> ";
        break;
    default:
    }
    return rel;
}

// 获取因案件类别而限制的案件数量
function GetLimitCount() {
    var res = "";
    $.ajax({
        type: "post",
        async: false,
        data: {provinceid: provinceid },
        url: "/Handler/Platform/PlatformHandler.ashx?action=GetLimitCount" + location.search.replace("?","&"),
        success: function(result) {
            res = result;
        }
    });
    return res;
}
