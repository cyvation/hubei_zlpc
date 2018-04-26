
// 评查案件信息
var EVAL_CASE_WIN;

$(document).ready(function () {

    // 评查案件信息(未传递PCSLBM，则仅能查看案件信息)
    if (FUNCTION_PARAM.PCSLBM && !isNull(FUNCTION_PARAM.PCSLBM)){

        // 获取评查案件信息，Ajax同步请求，缓存在EVAL_CASE_WIN中
        init_eval_case_info();

        if(!EVAL_CASE_WIN || !EVAL_CASE_WIN.PCSLBM || isNull(EVAL_CASE_WIN.PCSLBM)){
            Alert("获取评查案件信息失败！");
            return;
        }
    }else{
        EVAL_CASE_WIN = FUNCTION_PARAM;
    }

    switch_to_splitscreen();
});

// 获取评查信息
function get_eval_handle_pcxx() {
    return EVAL_CASE_WIN;
}

// 切换到分屏
function switch_to_splitscreen() {
    var url = "view/evaluate/pcblWin/splitscreen.html";
    load_eval_handle_content_page(url);
}

// 加载评查办理页面
function load_eval_handle_content_page(href) {
    if(FUNCTION_PARAM.type==1){
        $('#pcblWin_ajcCcontent').panel({
            noheader:true,border:false,
            href:href,
            onLoad:function(){
            }
        });
    }else {
        $('#pcblWin_content').panel({
            noheader:true,border:false,
            href:href,
            onLoad:function(){
            }
        });
    }
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
                EVAL_CASE_WIN = result.value;
                EVAL_CASE_WIN.PCCZLX = FUNCTION_PARAM.PCCZLX;
                EVAL_CASE_WIN.PCSPBM = FUNCTION_PARAM.PCSPBM;
            }
        }

    });

}
