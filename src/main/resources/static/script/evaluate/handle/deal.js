
// 评查案件信息
var EVAL_CASE;

$(document).ready(function () {

    // 获取评查案件信息，Ajax同步请求，缓存在EVAL_CASE中
    init_eval_case_info();

    // 评查案件信息
    if(!EVAL_CASE || !EVAL_CASE.PCSLBM || isNull(EVAL_CASE.PCSLBM)){
        Alert("获取评查案件信息失败！");
        return;
    }

    // 显示控制
    //var windowWith = $(window).width();
    //if (windowWith <= 1500){
    //    switch_to_onescreen();
    //}else{
    //switch_to_splitscreen();
    //}
    switch_to_tabsPcbl();
});

// 获取评查信息
function get_eval_handle_pcxx() {
    return EVAL_CASE;
}

// 切换到tabs
function switch_to_tabsPcbl() {
    var url = "view/evaluate/handle/tabsPcbl.html";
    load_eval_handle_content_page(url);
    EVAL_CASE.PreviewMode ='标签';
}

// 切换到单屏
function switch_to_onescreen() {
    var url = "view/evaluate/handle/onescreen.html";
    load_eval_handle_content_page(url);
}

// 切换到分屏
function switch_to_splitscreen() {
    var url = "view/evaluate/handle/splitscreen.html";
    load_eval_handle_content_page(url);
    EVAL_CASE.PreviewMode ='分屏';
}

// 加载评查办理页面
function load_eval_handle_content_page(href) {
    // $('#div_eval_handle_content').panel('clear');
    $('#div_eval_handle_content').panel('refresh',href);
    //
    // $('#div_eval_handle_content').panel({
    //     href:href,
    //
    // });

}

// 获取评查案件详情
function init_eval_case_info() {

    $.ajax({
        type: 'POST',
        url: getRootPath()+'/handle/caseConfirm',
        data: { pcslbm: FUNCTION_PARAM.PCSLBM },
        dataType: "json",
        async: false,
        success: function (result) {
            if (result.status == 200){
                // 评查案件信息
                EVAL_CASE = result.value;
                EVAL_CASE.PCCZLX = FUNCTION_PARAM.PCCZLX;
                EVAL_CASE.PCSPBM = FUNCTION_PARAM.PCSPBM;
                EVAL_CASE.PCYJMC = FUNCTION_PARAM.PCYJMC;
            }
        }

    });

}
