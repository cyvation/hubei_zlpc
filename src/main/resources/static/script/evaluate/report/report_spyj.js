
$(function () {
    $("#win_approve_report_spyj").append("<iframe style='position:absolute;z-index:-1;width:100%;height:100%;left:0;top:0;scrolling:no;border:none;' frameborder='1'></iframe>");
    // 控件初始化及初始数据加载
    approve_report_spyj_marks_init(eval_report_info);
});

// 控件初始化及初始数据加载
function approve_report_spyj_marks_init(jbxx) {

    $('#lb_approve_report_spyj_bgmc').html(jbxx.WJMC);
    $('#lb_approve_report_spyj_pcmc').html("评查报告");
    $('#lb_approve_report_spyj_zzdwmc').html(jbxx.NZRDWMC);
    $('#lb_approve_report_spyj_zzr').html(jbxx.NZRXM);


    // 获取所有审批意见
    $.post(getRootPath() + "/pcreport/getPcspbgInfo", {spwjbm: jbxx.JZWJBH},
        function (result) {
            var data = result.data.rows;
            if (data.length > 0) {
                // 动态加入行与数据
                for (var i = 0; i < data.length; i++) {
                    if (data[i].SPYJ != undefined && data[i].SPYJ != "") {
                        var row = $("<tr></tr>");
                        var table = $('#table_approve_report_spyj').append(row);
                        var td = $("<td></td>");
                        td.attr("class", 'redGrid');
                        td.append('审批意见');
                        var td2 = $("<td></td>");
                        td2.addClass("dv-text");
                        td2.attr("class", 'redGrid');
                        td2.attr("colspan", '3');
                        td2.append("<textarea rows=\"4\" cols=\"78\" style=\" font-size:16px; border:0;resize:none;\" readonly=\"readonly\">" + data[i].SPYJ + "</textarea><div class=\"luokuan\">" + data[i].SPRXM + "&nbsp" + DateParser_heng(data[i].SPRQ) + "</div>");
                        row.append(td);
                        row.append(td2);
                    }
                }
                //本次要输入的审批意见
                if (eval_report_info.DONE == '1') {
                    $("#win_anyscx_xr").css('display','none');
                } else {
                    var row = $("<tr></tr>");
                    var table = $('#table_approve_report_spyj').append(row);
                    var td = $("<td></td>");
                    td.attr("class", 'redGrid');
                    td.append('审批意见');
                    var td2 = $("<td></td>");
                    td2.addClass("dv-text");
                    td2.attr("class", 'redGrid');
                    td2.attr("colspan", '3');
                    var radio = '<div class="radio_checkbox"><div class="redio_click"><input class="input_ok_checkbox input_ok_checkbox_edit" name="input_ok_checkbox_edit" type="radio" checked="checked" value="同意" ></div>同意</div>' +
                        '<div class="radio_checkbox"><div class="redio_click"><input class="input_ok_checkbox input_ok_checkbox_edit" name="input_ok_checkbox_edit" type="radio" value="不同意" ></div>不同意</div>';
                    td2.append("<textarea id=\"txt_approve_report_spyj_spnr\" rows=\"4\" cols=\"78\" style=\" font-size:16px; resize:none;\" ></textarea>" +
                        "<div class=\"luokuan\">"+radio+"<span id=\"txt_approve_report_spyj_spr\"></span>&nbsp<span id=\"sysdate_bgsp\"></span></div>");
                    row.append(td);
                    row.append(td2);
                }
                $('#txt_approve_report_spyj_spr').html(userInfo.MC);
                // 审批意见默认同意
                $('#txt_approve_report_spyj_spnr').val('同意');
                $('#sysdate_bgsp').html(getNowDate());
                var $radio = $(".input_ok_checkbox_edit");
                $(".redio_click_no").removeClass('redio_click_no');
                $radio.eq(0).parent().addClass('redio_click_no');
                $radio.unbind("click");
                $radio.bind("click", function () {
                    console.log($(".input_ok_checkbox_edit")[0].checked);
                    if ($(this)[0].checked) {
                        $(".redio_click_no").removeClass('redio_click_no');
                        $(this).parent().addClass('redio_click_no');
                    }
                    // 切换的时候保留原值：
                    var defaultpcyj =  $('#txt_approve_report_spyj_spnr').val();
                    if ($(".input_ok_checkbox_edit")[0].checked) {
                        if (defaultpcyj == '不同意'){
                            $('#txt_approve_report_spyj_spnr').val('同意');
                        }
                    }else{
                        if (defaultpcyj == '同意'){
                            $('#txt_approve_report_spyj_spnr').val('不同意');
                        }
                    }
                });

            } else {
                //本次要输入的审批意见
                var row = $("<tr></tr>");
                var table = $('#table_approve_report_spyj').append(row);
                var td = $("<td></td>");
                td.attr("class", 'redGrid');
                td.append('审批意见');

                var td2 = $("<td></td>");
                td2.addClass("dv-text");
                td2.attr("class", 'redGrid');
                td2.attr("colspan", '3');
                var radio = '<div class="radio_checkbox"><div class="redio_click"><input class="input_ok_checkbox input_ok_checkbox_edit" name="input_ok_checkbox_edit" type="radio" checked="checked" value="同意" ></div>同意</div>' +
                    '<div class="radio_checkbox"><div class="redio_click"><input class="input_ok_checkbox input_ok_checkbox_edit" name="input_ok_checkbox_edit" type="radio" value="不同意" ></div>不同意</div>';
                td2.append("<textarea id=\"txt_approve_report_spyj_spnr\" rows=\"4\" cols=\"78\" style=\" font-size:16px; resize:none;\" ></textarea>" +
                    "<div class=\"luokuan\">"+radio+"<span id=\"txt_approve_report_spyj_spr\"></span>&nbsp<span id=\"sysdate_bgsp\"></span></div>");
                row.append(td);
                row.append(td2);
                // 审批意见默认同意
                $('#txt_approve_report_spyj_spnr').val('同意');
                $('#txt_approve_report_spyj_spr').html(userInfo.MC);
                $('#sysdate_bgsp').html(getNowDate());
                var $radio = $(".input_ok_checkbox_edit");
                $(".redio_click_no").removeClass('redio_click_no');
                $radio.eq(0).parent().addClass('redio_click_no');
                $radio.unbind("click");
                $radio.bind("click", function () {
                    if ($(this)[0].checked) {
                        $(".redio_click_no").removeClass('redio_click_no');
                        $(this).parent().addClass('redio_click_no');
                    }
                    // 切换的时候保留原值：
                    var defaultpcyj =  $('#txt_approve_report_spyj_spnr').val();
                    if ($(".input_ok_checkbox_edit")[0].checked) {
                            if (defaultpcyj == '不同意'){
                                $('#txt_approve_report_spyj_spnr').val('同意');
                            }
                    }else{
                        if (defaultpcyj == '同意'){
                            $('#txt_approve_report_spyj_spnr').val('不同意');
                        }
                    }
                });

            }
        });

    // 如果检察长不显示继续送审按钮：
    if (userInfo.spjsbm == '70'){
        $("#btn_approve_report_spyj_agree").css('display','');
        $('#btn_approve_report_spyj_sendAgain').css('display','none');
    }else if (userInfo.spjsbm == '60'){ // 分管
        $("#btn_approve_report_spyj_agree").css('display','');
        $('#btn_approve_report_spyj_sendAgain').css('display','');
    }else{ //案管负责人
        $("#btn_approve_report_spyj_agree").css('display','');
        $('#btn_approve_report_spyj_sendAgain').css('display','');
    }



    //审批完成
    $('#btn_approve_report_spyj_agree').linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            var spyj = $('#txt_approve_report_spyj_spnr').val();
            if (isNull(spyj)) {
                Alert('请填写审批意见！');
                return;
            }
            $('#btn_approve_report_spyj_agree').linkbutton('disable');
            $.post(getRootPath() + "/pcreport/updateSpyj",
                {
                    pcspbm: jbxx.PCSPBM,
                    spyj: $('#txt_approve_report_spyj_spnr').val(),
                    spjl: $('input[name ="input_ok_checkbox_edit"]:checked').val()
                },
                function (result) {
                    if (result.code == "200") {
                        Alert('审批成功！');
                        $('#win_approve_report_spyj').window('close');
                        document.getElementById("txt_approve_report_spyj_spnr").value = '';
                       eval_report_info.DONE = '1';
                    }
                    else {
                        $('#btn_approve_report_spyj_agree').linkbutton('enable');
                        Alert(result);
                        return;
                    }
                });
        }
    });

    // 再次送审
    $("#btn_approve_report_spyj_sendAgain").linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            alert_win_sp_ry(jbxx);
        }
    });

    //退回评查员（不留痕迹：审批单均不留痕迹,且假删除相关记录(无论哪一级审批了).2018.7.27上海要求）
    $('#btn_approve_report_spyj_goback').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#btn_approve_report_spyj_agree').linkbutton('disable');
            $.post(getRootPath() + "/pcreport/backDoc",
                {
                    sprdwbm : userInfo.DWBM,
                    sprdwmc : userInfo.DWMC,
                    sprgh : userInfo.GH,
                    sprxm : userInfo.MC,

                    pcspbm: jbxx.PCSPBM,
                    spwjbm: jbxx.SPWJBM,
                    spyj: $('#txt_approve_report_spyj_spnr').val(),
                    spjl: '退回'
                },
                function (result) {
                    if (result.code == "200") {
                        Alert('退回成功！');
                        $('#win_approve_report_spyj').window('close');
                        $("#txt_approve_report_spyj").css('display','none');
                        document.getElementById("txt_approve_report_spyj_spnr").value = '';
                        eval_report_info.DONE = '1';
                        // 退回到审批界面
                        load_function('评查审批','view/evaluate/approval/index.html');
                    }
                    else {
                        console.log(result);
                        $('#btn_approve_report_spyj_agree').linkbutton('enable');
                        Alert("退回失败");
                        return;
                    }
                });
        }
    });
}

// 审批人员列表
function alert_win_sp_ry(jbxx){

    var spjsbm = '0';
    var jsbmj = userInfo.JSBM;
    for(var i = 0; i < jsbmj.length; i++){
        var jsbm = jsbmj[i];
        if (jsbm.SPJSBM > spjsbm){
            spjsbm = jsbm.SPJSBM;
        }
    }

    //初始化送审接收人员表[案管负责人->分管副检察长（必须）->检察长（非必须）]
    $('#dg_spry_win_send_check_rept').datagrid({
        width: '460px',
        height: '280px',
        fitColumns: true,
        singleSelect: true,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        idField: 'ID',
        method: 'get',
        url: getRootPath()+'/pcreport/getPcsp',
        queryParams : {
            spjsbm : spjsbm
        },
        columns: [[
            {field: 'ID', title: '唯一标示', hidden: true},
            {field: 'ck', title: '复选框', checkbox: true, width: 80},
            {field: 'DWBM', title: '单位编码', hidden: true},
            {field: 'DWMC', title: '单位名称', width: 120},
            {field: 'MC', title: '姓名', width: 100},
            {field: 'GH', title: '工号', hidden: true},
            {field: 'JSMC', title: '角色', width: 100}
        ]],
        loadFilter: function (data) {
            return data.code == 200 ? JSON.parse(data.data) : [];
        },
        groupField: 'JSMC',
        view: groupview,
        groupFormatter: function (value, rows) {
            return ((value == '') ? "无角色" : value); // +' - ' + rows.length + ' 条';
        }
    });

    // 送审确定
    $("#btn_ok_win_send_check_rept").linkbutton({
        onClick:function(){
            confirm_send_again_check_rept(jbxx);
        }
    });

    // 送审取消
    $("#btn_cancel_win_send_check_rept").linkbutton({
        onClick:function(){
            $("#win_send_check_rept").window('close');
        }
    });

    $("#win_send_check_rept").window('open');

}

// 再次送审
function confirm_send_again_check_rept(jbxx){
    // 校验：
    var selectedRy = $("#dg_spry_win_send_check_rept").datagrid('getSelected');
    if (!selectedRy){
        Alert("请选择审批人！");
        return;
    }

    var thyj = $('#txt_approve_report_spyj_spnr').val();
    if (isNull(thyj)) {
        Alert('请填写审批意见！');
        return;
    }

    var obj = new Object();
    obj.pcspbm = jbxx.PCSPBM;
    obj.spyj  = thyj;
    obj.spjl =  $('input[name ="input_ok_checkbox_edit"]:checked').val();

    obj.spwjlx = '2';
    obj.spwjbm = jbxx.JZWJBH;
    obj.ssrxm = userInfo.MC;
    obj.ssrgh = userInfo.GH;
    obj.ssrdwbm = userInfo.DWBM;
    obj.ssrdwmc = userInfo.DWMC;

    obj.sprdwbm = selectedRy.DWBM;
    obj.sprdwmc = selectedRy.DWMC;
    obj.sprgh = selectedRy.GH;
    obj.sprxm = selectedRy.MC;
    obj.spjsbm = selectedRy.SPJSBM;
    obj.spjsmc = selectedRy.JSMC;

    $.ajax({
        url: getRootPath() + '/pcreport/sendAgainPcsp',
        data: obj,
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                Alert("送审成功");
                document.getElementById("txt_approve_report_spyj_spnr").value = '';
                $("#win_send_check_rept").window('close');
                $('#win_approve_report_spyj').window('close');
                eval_report_info.DONE = '1';
                SetSaveButtonState("TANGER_OCX_PCBG", false);
            }else{
                Alert("报审失败");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ShowError(textStatus + "，" + errorThrown);
        }
    });
}

function DateParser_heng(s) {
    var ss = (s.split(' '))[0];
    var arr = (s.split('-'));
    var y = parseInt(arr[0], 10);
    var m = parseInt(arr[1], 10);
    var d = parseInt(arr[2], 10);
    return y + '年' + (m < 10 ? ('0' + m) : m) + '月' + (d < 10 ? ('0' + d) : d) + '日';

}