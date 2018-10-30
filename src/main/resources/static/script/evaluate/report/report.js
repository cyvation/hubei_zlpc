var eval_report_info;

var pcjzPath='/Files/PCFA/';

$(function () {
    $("#win_approve_report_spyj").append("<iframe style='position:absolute;z-index:-1;width:100%;height:100%;left:0;top:0;scrolling:no;border:none;' frameborder='1'></iframe>");
    //控件初始化及初始数据加载
    approve_report_marks_init();

})

//控件初始化及初始数据加载
function approve_report_marks_init() {

    // 解析评查报告信息
    var jzwjbh = FUNCTION_PARAM.SPWJBM;
    //获取报告基本信息
    $.ajax({
        url: getRootPath() + '/pcreport/getPcbgDetailInfo',
        dataType: 'json',
        data: {jzwjbh: jzwjbh},
        success: function (data) {
            if (data.code == 200) {
                $('#txt_approve_report_bgmc').textbox('setValue', data.data.wjmc);
                $('#txt_approve_report_cjr').textbox('setValue', data.data.nzrxm);
                $('#txt_approve_report_cjsj').textbox('setValue', sjzh(data.data.cjsj));
                eval_report_info = new Object();
                eval_report_info.WJMC = data.data.wjmc;
                eval_report_info.NZRDWMC = data.data.nzrdwmc;
                eval_report_info.NZRXM = data.data.nzrxm;
                eval_report_info.JZWJBH = data.data.jzwjbh;
                eval_report_info.WJLX = data.data.wjlx;
                eval_report_info.WSCFLJ = data.data.wscflj;
                eval_report_info.DONE = FUNCTION_PARAM.DONE;
                eval_report_info.SPWJBM = FUNCTION_PARAM.SPWJBM;
                eval_report_info.PCSPBM = FUNCTION_PARAM.PCSPBM;
                init_File_Doc(data.data.wscflj);
            } else {
                Alert("无数据或错误");
            }
        }
    });
    // 审批
    $('#txt_approve_report_spyj').linkbutton({
        iconCls: 'icon-edit',
        onClick: function () {
            alert_win_approve_report_spyj();
        }
    });


}


function init_File_Doc(wscflj) {
    if (wscflj != "") {
        var docId = "TANGER_OCX_PCBG";
        OpenFile(getRootPath() + pcjzPath + wscflj,docId);
        SetSaveButtonState(docId, true);
    }
}
resize_divDoc_spbg();

$('#pnl_approve_report').panel({
    onResize: function (width, height) {
        resize_divDoc_spbg();
    }
});

function resize_divDoc_spbg() {
    var height = $('#pnl_approve_report').height();
    var pcbgxx_heigth = $('#tool_approve_report').height();
    $('#divDoc_bg').height(height -25 - pcbgxx_heigth);
}




//打开评查报告审批意见页面
function alert_win_approve_report_spyj() {
    var url = "view/evaluate/report/report_spyj.html?spwjbm=" + eval_report_info.SPWJBM;
    $('#win_approve_report_spyj').window({
        href: url
    });
    $('#win_approve_report_spyj').window('open');
}

function DateParser_heng(s) {
    var ss = (s.split(' '))[0];
    var arr = (s.split('-'));
    var y = parseInt(arr[0], 10);
    var m = parseInt(arr[1], 10);
    var d = parseInt(arr[2], 10);
    return y + '年' + (m < 10 ? ('0' + m) : m) + '月' + (d < 10 ? ('0' + d) : d) + '日';
}

// 获取页面传递参数
function getQueryString(paramStr, name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    //var r = window.location.search.substr(1).match(reg);
    var r = paramStr.match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

// 保存评查报告
function save_report_file() {

    //document.all("TANGER_OCX_PCBG").CancelLastCommand = true;

    if(isNull(eval_report_info.WSCFLJ) || eval_report_info.WSCFLJ == ""){
        return;
    }
    // 编辑保存
    var url = getRootPath() + "/manage/savePCBG?wjlj=" + eval_report_info.WSCFLJ + "&Pczylx=" +'0';
    var fileresult = SaveToUrl(url);
    if (fileresult == null && fileresult != "") {
    } else {
        Alert("修改评查报告失败。" + fileresult);
    }
}