//
// function view_evaluate_handle_qh() {
//     var url = "view/evaluate/handle/index_switch.html";
//
//     load_function("评查办理",url)
// }
// function view_evaluate_handle_qhhq() {
//     var url = "view/evaluate/handle/index.html";
//
//     load_function("评查办理",url)
// }
//



var firstLoadCaseInfo = true; //是否第一次加载案件信息
// 选项卡切换事件
$('#tt_eval_handle').tabs({
    onSelect: function (title, index) {
        if (title == "案件信息") {
            if (firstLoadCaseInfo) {
                var tab = $('#tt_eval_handle').tabs('getTab', '案件信息');
                $('#tt_eval_handle').tabs('update', {
                    tab: tab,
                    options: {
                        href: "./view/evaluate/handle/tabsAjxx.html" // 新内容的URL
                    }
                });
            }

            // Tab切换时文书会置空，识别到上次打开文书默认打开
            try{
                if(opening_case_doc_file && !isNull(opening_case_doc_file)){
                    var error = OpenFile(getRootPath() + opening_case_doc_file, "TANGER_OCX_CASEINFO");
                }
            }catch (e){

            }

            firstLoadCaseInfo = false;
        }
        else if (title == "评查信息") {

            // Tab切换时文书会置空，识别到上次打开文书默认打开
            try{
                // Tab切换时文书会置空，识别到上次打开文书默认打开
                if(opening_eval_doc_file && !isNull(opening_eval_doc_file)){
                    var error = OpenFile(getRootPath() + opening_eval_doc_file, "TANGER_OCX");
                }
            }catch (e){

            }

        }
    }
});




