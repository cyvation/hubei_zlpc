
var gcdata;
var v_col = 0;

$(document).ready(function () {
    showInfo();
});

function showInfo() {

    var gcakxh = $('#gaxx_gcakxh').val();
    var bmsah = $('#pcWin_cbt_case_info_bmsahs').combotree('getValue');
    // 获取选中节点自定义数据
    var tree = $('#pcWin_cbt_case_info_bmsahs').combotree('tree'); // 获取树对象
    var node = tree.tree('find', bmsah); // 查找节点
    var ajlbbm = node.attributes.AJLBBM;

    // 获取案卡配置
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/caseinfo/getProcessCardInfo',
        data: { bmsah: bmsah, pcslbm: evalInfo_WIN.PCSLBM, ajlbbm: ajlbbm, gcakxh: gcakxh },
        dataType: "json",
        success: function (result) {

            if (result.status != 200) {
                //Alert(result.note);
                return;
            }

            var carddata = eval(result.value);
            if (carddata == null || carddata == undefined) {
                Alert("获取过程配置信息失败，请联系管理员！");
                return;
            }
            outdata = carddata[0].OutTabList;

            for (var k = 0; k < outdata.length; k++) {
                $("#" + outdata[k].Name + "gcTitle").remove();
                //外层Tab
                $("#tab_caseinfo_process_info").tabs('close', outdata[k].Title);

                $("#tab_caseinfo_process_info").tabs('add', { id: outdata[k].Name, title: outdata[k].Title, closable: false });

                //标题
                var title = $("<div></div>");
                title.addClass("gaxx_title");
                title.attr("align", "center");
                title.attr("id", outdata[k].Name + "gcTitle");
                title.append(carddata[0].Title);

                $("#" + outdata[k].Name).append(title);
                if (outdata[k].OutPageList.length > 0) {
                    gcdata = outdata[k].OutPageList[0].TabList;

                    CreateInnerTab(gcdata, k);
                }

            }

            $('#tab_caseinfo_process_info').tabs('select', 0);

            RegistEvent();
        }
    });

}

// 创建内部Tab页
function CreateInnerTab(da, index) {
    var content;

    for (var j = 0; j < gcdata.length; j++) {

        if (gcdata[j].SFFY) {
            content = '<div id="' + gcdata[j].TabId + 'pp" class="easyui-pagination" style="margin-top:20px;margin-left:32%;"  data-options="showPageList: false,showRefresh: false,displayMsg:\' \'"></div>';
        }
        else {
            content = '';
        }

        var table = $("<table align=\"center\" border=\"0\"  cellpadding=\"5\"></table>")
        table.attr("id", gcdata[j].TabId + "gc");
        table.addClass("dv-table");

        if (gcdata[j].PageList.length > 0) {
            var da = gcdata[j].PageList[0].FieldList;
            EditTable(da, table, v_col);

        }
        //内层Tab
        var InnerTab = $("<div></div>");
        InnerTab.attr("id", outdata[index].Name + "_InnerTab");

        $("#" + outdata[index].Name).append(InnerTab);

        $("#" + outdata[index].Name + "_InnerTab").tabs({
            tabPosition: "left",
            //height: 600
            fit: 'true',
            border: false
        });

        $("#" + outdata[index].Name + "_InnerTab").tabs('close', gcdata[j].TabName);
        $("#" + outdata[index].Name + "_InnerTab").tabs('add',
            {
                id: gcdata[j].TabId,
                title: gcdata[j].TabName,
                closable: false
            });

        $("#" + gcdata[j].TabId).append(table);

        if (gcdata[j].PageList.length > 0) {
            $("#" + gcdata[j].TabId).append(content);
        }

    }

    $("#" + outdata[index].Name + "_InnerTab").tabs('select', 0);
}

function RegistEvent() {
    for (var s = 0; s < gcdata.length; s++) {
        if (gcdata[s].SFFY) {
            $("#" + gcdata[s].TabId + "pp").pagination({
                total: gcdata[s].PageList.length * 10,
                onSelectPage: function (pageNumber, pageSize) {
                    var outtab = $("#tab_caseinfo_process_info").tabs('getSelected');
                    var outindex = $("#tab_caseinfo_process_info").tabs('getTabIndex', outtab);
                    var tab = $("#" + outdata[outindex].Name + "_InnerTab").tabs('getSelected');
                    var index = $("#" + outdata[outindex].Name + "_InnerTab").tabs('getTabIndex', tab);
                    var da1 = gcdata[index].PageList[pageNumber - 1].FieldList;
                    $("#" + gcdata[index].TabId + "gc tr").remove();
                    var table = $("#" + gcdata[index].TabId + "gc");
                    EditTable(da1,table,v_col);
                }
            });

        }
    }
}

// 修改表格内容
function EditTable(da1,table,v_col) {
    for (var i = 0; i < da1.length; i++) {
        if (i == 0 || v_col >= 2) {
            v_col = 0;
            var row = $("<tr></tr>");
            table.append(row);

        }
        var td = $("<td></td>");
        td.addClass("dv-label");
        td.append(da1[i].XSMC);

        var td2 = $("<td></td>");
        td2.addClass("dv-text");
        td2.attr("colspan", da1[i].cols);
        //td2.append(da1[i].VALUE);
        td2.append("<input type='text' style='width:100%' value='"+ (isNull(da1[i].VALUE) || da1[i].VALUE == 'null' ? "" : da1[i].VALUE) +"' readonly>");

        v_col = Number(v_col) + Number(da1[i].COLS);
        row.append(td)
        row.append(td2);
    }
}