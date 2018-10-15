var PCLZDLX = "2";
var PCBGLX = "3";
var ZDPCBG = "5";
var PCYFJ = "6";
var JCGFJ = "7";

var opening_eval_doc_file;
var editDocPath = ""; //当前打开卷宗路径(流转单/评查报告)
var isApproveDoc = false; //是否为评查流转单
var isSendApprove = "0"; //是否为评查报审，在评查员触发报审/发送承办人时使用
var isFirstLoad = "Y"; //是否首次加载页面，首次加载则默认打开评查流转单，否则刷新卷宗树时不操作文件

$(document).ready(function () {

    // 界面标签样式设置及事件绑定
    eval_info_marksInit();

    init_eval_info_detail();

    // 初始化操作按钮
    init_eval_handle_deal_toolbar(false);

    // 标签初始化数据加载
    eval_info_marksDataBind();

    // 间隔3分钟，自动保存
    //setTimeout("autoSaveDocument()", 180000);
});


// 自动保存评查报告/流转单
function autoSaveDocument() {

    save_edit_document();

    setTimeout("autoSaveDocument()", 180000);
}

// 保存评查报告/流转单
function save_edit_document() {

    if(isNull(editDocPath) || editDocPath == ""){
        return;
    }

    try {
        // 编辑保存
        var url = getRootPath() + "/manage/savePCBG?wjlj=" + editDocPath;
        var fileresult = SaveToUrl(url);
    }catch (e){

    }
}


// 评查案件信息初始化
function init_eval_info_detail() {
    $('#lbl_eval_handle_eval_ajmc,#win_lbl_eval_handle_eval_ajmc').text(EVAL_CASE.AJMC);
    $('#lbl_eval_handle_eval_cbr,#win_lbl_eval_handle_eval_cbr').text(EVAL_CASE.BPC_MC);
    $('#lbl_eval_handle_eval_pcr,#win_lbl_eval_handle_eval_pcr').text(EVAL_CASE.PCR_MC);
    $('#lbl_eval_handle_eval_pcsah,#win_lbl_eval_handle_eval_pcsah').text(EVAL_CASE.PCSAH);
    $('#lbl_eval_handle_eval_pcsj,#win_lbl_eval_handle_eval_pcsj').text(sjzh(EVAL_CASE.CJSJ));
    $('#lbl_eval_handle_eval_ay,#win_lbl_eval_handle_eval_ay').text(EVAL_CASE.AY);
    $('#collPcaj').val(EVAL_CASE.AJMC);
    $('#collPcah').val(EVAL_CASE.PCSAH);
}

// 界面标签样式设置及事件绑定
function eval_info_marksInit() {
    $('#win').window({
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

    $('#addColl').window({
        width: 400,
        height: 310,
        modal: true,
        title: '添加到收藏夹',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {
            $('#collBz').val('');
        }
    });

    $('#addWs').window({
        width: 400,
        height: 270,
        modal: true,
        title: '新建报告',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {

        },
        onOpen:function () {
            addWsTable();
        }
    });
    $('#addWsType').datagrid({
        singleSelect: true,
        collapsible: true,
        fit:true,
        remoteSort: false,
        multiSort: true,
        columns: [
            [{
                field: 'wsmbmc',
                title: '文书模板名称',
                width:'100%'
            }]
        ]
    });

    $(".center_right_box:eq(0)").click(function () {
        show_eval_info_pcak_area();
    });
    $(".center_right_box:eq(1)").click(function () {
        show_eval_info_pcjz_area();
    });
    $("#pcbl_ssyjksj").click(function () {
        $("#evalinfo_position_one").hide();
    });
    $("#pcbl_ssyjzsj").click(function () {
        $("#evalinfo_position_two").hide();
    });

    // 处理结果单选框选中样式事件
    $("#input_radio_cljg").unbind("click");
    $("#input_radio_cljg").bind("click", function () {
        // console.log(this.checked)
        $('.redio_click_no').removeClass('redio_click_no');
        $(this).parent().addClass('redio_click_no');

    });

    // 评查结果单选框选中样式事件
    $("#input_radio_pcjg").unbind("click");
    $("#input_radio_pcjg").bind("click", function () {
        // console.log(this.checked)
        $('.redio_click_no1').removeClass('redio_click_no1');
        $(this).parent().addClass('redio_click_no1');
    });

    // 评查结果单选框选中样式事件
    /*$("#input_radio_spyj").unbind("click");
    $("#input_radio_spyj").bind("click", function () {
        $('.redio_click_no2').removeClass('redio_click_no2');
        $(this).parent().addClass('redio_click_no2') ;
    });*/

    $("#btn_eval_info_pcak").unbind("click");
    $("#btn_eval_info_pcak").bind("click", function () {
        save_edit_document();
        display_eval_handle_cards();
        editDocPath = "";
        opening_eval_doc_file = "";
    });

    //备注信息不同分辨率处理
    var curWinfbl = window.screen.width;
    if(curWinfbl == 1366){
        $('.pcjg_input .pcjg_redio_text').width('80px');
    }
}

// 展示评查案卡浮动面板
function show_eval_info_pcak_area() {
    $("#evalinfo_position_one").show();
    $("#evalinfo_position_two").hide();
    $(".center_right_box").removeClass('center_right_box_click');
    $(".center_right_box:first-child").addClass('center_right_box_click');
    $('.center_right_box_sj').removeClass('center_right_box_sj');
    $(".center_right_box:first-child").children('div').addClass('center_right_box_sj');
    $("#btn_eval_info_pcak").click();
}

// 展示评查卷宗浮动面板
function show_eval_info_pcjz_area() {
    $("#evalinfo_position_one").hide();
    $("#evalinfo_position_two").show();
    $(".center_right_box").removeClass('center_right_box_click');
    $(".center_right_box:last-child").addClass('center_right_box_click');
    $('.center_right_box_sj').removeClass('center_right_box_sj');
    $(".center_right_box:last-child").children('div').addClass('center_right_box_sj');
}

// 标签初始化数据加载
function eval_info_marksDataBind() {

    // 评查案卡
    load_eval_info_card();
    // 处理结果
    load_cljg_pcjg('9101');
    // 评查结果
    load_cljg_pcjg('9102');
    if(EVAL_CASE.PCFLBM != '001'){
        $("#loadSfldba").hide();
    }
    //是否领导办案
    load_sfldba('9104');
    // 评查结论
    $("#txt_eval_info_pcjl_bz").val(EVAL_CASE.SM);
    // 初始化卷宗文件树
    init_eval_info_docfiles();

    // 加载评查卷宗
    load_tree_eval_doc_files()
}

// 初始化评查卷宗
function init_eval_info_docfiles(){

    // 卷宗树
    $('#tree_eval_doc_files').tree({
        loadFilter: function (data) {
            if (data || !isNull(data)) {
                return data;
            } else {
                return [];
            }
        },
        onLoadSuccess: function (node, data) {
            if (data == null || isFirstLoad == "N")
                return;
            isFirstLoad = "N";
            // 初始化文件操作工具栏
            init_tool_tree_eval_doc_files();

            // 评查操作类型：0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈

            if (EVAL_CASE.PCJDBH != '006') {
                var obj = get_eval_info_doc(PCLZDLX);
                if (isNull(obj))
                    return;
                // 打开卷宗文件
                isApproveDoc = true;
                open_eval_file(obj.jzwjbh, obj.pczybm, obj.pczylx, obj.wjlx, obj.wjlj);
            }

        },
        onLoadError: function (arguments) {
            //Alert(arguments);
        },
        onClick: function(node){

            // 仅评查员及检察官有操作权限
            $("#pcbl_pcxx_doc_rename").css('display', 'none');
            $('#pcbl_pcxx_doc_delete').css('display', 'none');
            if(EVAL_CASE.PCCZLX == "1"){

                var nodeLx = node.attributes.LX;
                if(nodeLx == PCLZDLX || nodeLx == PCBGLX || nodeLx == PCYFJ){
                    $("#pcbl_pcxx_doc_rename").css('display', '');
                    $('#pcbl_pcxx_doc_delete').css('display', '');
                } else if(nodeLx == '0'){
                    // 获取父节点
                    var pNode = $("#tree_eval_doc_files").tree('getParent', node.target);
                    if (!isNull(pNode)  && (pNode.attributes.LX == PCLZDLX || pNode.attributes.LX == PCBGLX || pNode.attributes.LX == PCYFJ)){
                        $("#pcbl_pcxx_doc_rename").css('display', '');
                        $('#pcbl_pcxx_doc_delete').css('display', '');
                    }
                }
            } else if(EVAL_CASE.PCCZLX == "3"){
                var nodeLx = node.attributes.LX;
                if(nodeLx == JCGFJ){
                    $("#pcbl_pcxx_doc_rename").css('display', '');
                    $('#pcbl_pcxx_doc_delete').css('display', '');
                } else if(nodeLx == '0') {
                    // 获取父节点
                    var pNode = $("#tree_eval_doc_files").tree('getParent', node.target);
                    if (!isNull(pNode) && pNode.attributes.LX == JCGFJ) {
                        $("#pcbl_pcxx_doc_rename").css('display', '');
                        $('#pcbl_pcxx_doc_delete').css('display', '');
                    }
                }
            }

            var jzwjbh = node.attributes.BM;
            var pczybm = node.attributes.ZYBM;
            var pczylx = node.attributes.ZYLX;
            var wjlx = node.attributes.LX; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
            var wjlj = node.attributes.WJLJ;
            if (isNull(wjlj)){
                return;
            }

            // 打开卷宗文件
            isSendApprove = "0";
            open_eval_file(jzwjbh, pczybm, pczylx, wjlx, wjlj);

        },
        onDblClick: function (node) {

            if (node.state == "closed") {
                $("#tree_eval_doc_files").tree('expand', node.target);
            }else {
                $("#tree_eval_doc_files").tree('collapse', node.target);
            }
        }
    });

    //点击上传按钮出现弹出层
    $("#pcbl_pcxx_doc_upload").unbind( "click" );
    $("#pcbl_pcxx_doc_upload").bind("click", function () {
        // 查找当前人员操作目录
        var mllx = "";
        switch (EVAL_CASE.PCCZLX){
            case "1":
                mllx = PCYFJ;
                break;
            case "3":
                mllx = JCGFJ;
                break;
        }
        if(isNull(mllx)){
            Alert("无上传权限，请联系管理员！");
            return;
        }

        var node = get_eval_info_doc_dir(mllx);
        $('#tree_eval_doc_files').tree('select', node.target);

        //var node = $('#tree_eval_doc_files').tree('getSelected');
        //if(!node || isNull(node)){
        //    Alert("请选择文件上传目录！");
        //    return;
        //}

        $('#win_tccys').window('setTitle', node.text);

        // 随着选择文件变化文件名变化
        $("#selectedFile").change(function () {
            var str=$(this).val();
            $("#tcc_input").val(str);
            try{
                var arr=str.split("\\");
                var my=arr[arr.length-1];
                $("#tcc_cmmys").val(my.substr(0, my.lastIndexOf('.')));
            }catch (e){

            }
        });

        // 选择文件
        $("#btn_select_file").unbind( "click" );
        $("#btn_select_file").bind("click", function () {

        });

        // 确定
        $("#uploadOk").unbind( "click" );
        $("#uploadOk").bind("click", function () {

            //判断文件是否选择
            var file = $("#selectedFile").val();
            if(isNull(file)){
                Alert("请选择上传文件！");
                return ;
            }
            var fileName=$('#tcc_cmmys').val();
            if(isNull(fileName)){
                Alert("请输入文件名称！");
                return ;
            }

            ShowProgress();
            var formData = new FormData($("#uploadForm")[0]);
            var obj = new Object();
            obj.PCSLBM = EVAL_CASE.PCSLBM;
            obj.PCFLBM = EVAL_CASE.PCFLBM;
            obj.PCHDBM = EVAL_CASE.PCHDBM;
            obj.BMSAH = EVAL_CASE.BMSAH;
            obj.PCZYLX = "1"; //0.评查方案 1.评查卷宗
            formData.append( "json", JSON.stringify(obj) );
            // 开始上传文件
            $.ajax({
                async : false,
                cache : false,
                type : "post",
                data : formData,
                url : getRootPath()+'/manage/fileUpload',
                dataType : 'json',
                contentType: false, //必须
                processData: false, //必须
                success : function(result) {

                    CloseProgress();
                    if(result.status != 200){
                        Alert("文件上传错误！");
                        return;
                    }

                    // 卷宗信息写入数据库
                    var obj = new Object();
                    obj.PCZYBM = EVAL_CASE.PCSLBM;
                    obj.WJMC = fileName;
                    obj.WJLX = '0'; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
                    obj.GXLX = "1"; //活动内共享
                    obj.JZMLH = node.id;
                    obj.FJZWJBH = "-1";
                    obj.WSCFLJ = result.value.filePathName;
                    $.ajax({
                        type: 'POST',
                        url:  getRootPath() + "/manage/addJzwj",
                        data: { json: JSON.stringify(obj) },
                        dataType: "json",
                        success: function (result) {
                            if (result.status != 200){
                                Alert(result.note);
                                return;
                            }

                            // 重新加载卷宗目录树
                            isFirstLoad = "N";
                            load_tree_eval_doc_files();

                            // 关闭弹窗
                            $('#win_tccys').window('close');
                            $("#tcc_input").val("");
                            $("#tcc_cmmys").val("");
                        }
                    });
                },
                error : function(arg1, arg2, arg3) {
                    CloseProgress();
                    Alert("文件上传错误");
                    return;
                }
            });
        });

        // 取消
        $("#uploadOk_qxan").unbind( "click" );
        $("#uploadOk_qxan").bind("click", function () {
            $('#win_tccys').window('close');
            $("#tcc_input").val("");
            $("#tcc_cmmys").val("");
        });

        $('#win_tccys').window('open');

    });

    // 点击重命名按钮出现弹出层
    $("#pcbl_pcxx_doc_rename").unbind( "click" );
    $("#pcbl_pcxx_doc_rename").bind("click", function () {
        var node = $('#tree_eval_doc_files').tree('getSelected');
        if(!node || isNull(node.attributes.WJLJ)){
            Alert("请选择文件！");
            return;
        }
        $("#YY_input").val(node.text);
        $("#XZ_input").val("");

        // 重命名确认按钮
        $("#rename_Ok").unbind("click");
        $("#rename_Ok").bind("click", function () {
            var obj = new Object();
            obj.JZWJBH = node.id;
            obj.GXLX = "1"; //活动内共享
            obj.WJMC = $("#XZ_input").val();
            $.ajax({
                url: getRootPath() + '/manage/updateFile',
                data: { json: JSON.stringify(obj)},
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {
                    if (result.status == 200){
                        var data = result.value;
                        //重新加载卷宗目录树
                        isFirstLoad = "N";
                        load_tree_eval_doc_files();
                        $('#win_cmmtccys').window('close');
                    }
                    else{
                        Alert(result.note);
                    }
                }
            });
        });

        // 取消
        $("#rename_qxan").unbind("click");
        $("#rename_qxan").bind("click", function () {
            $('#win_cmmtccys').window('close');
        });

        $('#win_cmmtccys').window('open');
    });

    // 删除文件
    $("#pcbl_pcxx_doc_delete").unbind( "click" );
    $("#pcbl_pcxx_doc_delete").bind("click", function () {
        var node = $('#tree_eval_doc_files').tree('getSelected');
        if(!node || isNull(node.attributes.WJLJ)){
            Alert("请选择文件！");
            return;
        }
        var wjlx = node.attributes.LX; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
        // 仅评查办理阶段可删除流转单
        if (EVAL_CASE.PCJDBH >= '007' && wjlx == PCLZDLX){
            Alert("流转单已生效，不能删除！");
            return;
        }

        Confirm("确认", "确定删除？", function (r) {
            if (r) {
                var obj = new Object();
                obj.JZWJBH = node.id;
                obj.PCZYLX = "1"; //0.评查方案 1.评查卷宗
                obj.WSCFLJ = node.attributes.WJLJ;
                $.ajax({
                    url: getRootPath() + '/manage/deleteFile',
                    data: { json: JSON.stringify(obj)},
                    type: 'post',
                    async: true,
                    dataType: 'json',
                    success: function (result) {
                        if (result.status == 200){
                            var data = result.value;
                            // 重新加载卷宗目录树
                            isFirstLoad = "N";
                            editDocPath = "";
                            opening_eval_doc_file = "";
                            $("#btn_eval_info_pcak").click();
                            load_tree_eval_doc_files();
                        }
                        else{
                            Alert(result.note);
                        }
                    }
                });
            }
        });

    });
}

// 获取指定文件类型目录
function get_eval_info_doc_dir(mllx){

    // 获取所有根目录节点
    var rootNodes = $('#tree_eval_doc_files').tree('getRoots');
    if (!rootNodes || rootNodes.length <= 0)
        return null;

    // 获取目录节点
    var dirNode;
    for (var i = 0; i < rootNodes.length; i++){
        if(rootNodes[i].attributes.LX == mllx){
            dirNode = rootNodes[i];
            break;
        }
    }

    return dirNode;
}

// 获取指定评查报告文件，模板类别（1.评查方案报告 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
function get_eval_info_doc(wjlx) {

    // 获取所有根目录节点
    var rootNodes = $('#tree_eval_doc_files').tree('getRoots');
    if (!rootNodes || rootNodes.length <= 0)
        return null;

    // 获取流转单目录节点
    var dirNode;
    for (var i = 0; i < rootNodes.length; i++){
        if(rootNodes[i].attributes.LX == wjlx){
            dirNode = rootNodes[i];
            break;
        }
    }
    if (!dirNode || dirNode == null){
        return null;
    }

    var data = $('#tree_eval_doc_files').tree('getData', dirNode.target);
    // 查找评查流转单
    var bh = "";
    if(data.children && data.children.length >= 1){
        for (var i = 0; i < data.children.length; i++){
            if(data.children[i].attributes.LX == wjlx){
                bh = data.children[i].attributes.BM;
                break;
            }
        }
    }

    var node = $('#tree_eval_doc_files').tree('find', bh);
    if(!node || node == null){
        return null;
    }
    $('#tree_eval_doc_files').tree('select', node.target);
    $('#tree_eval_doc_files').tree('select', node.target);

    var obj = new Object();
    obj.jzwjbh = node.attributes.BM;
    obj.pczybm = node.attributes.ZYBM;
    obj.pczylx = node.attributes.ZYLX;
    obj.wjlx = node.attributes.LX; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
    obj.wjlj = node.attributes.WJLJ;
    if (isNull(obj.wjlj)){
        return null;
    }

    return obj;
}

// 初始化文件操作工具栏
function init_tool_tree_eval_doc_files() {

    // 查找当前人员操作目录
    if(EVAL_CASE.PCCZLX == "1"){
        $("#pcbl_pcxx_doc_rename").css('display', '');
        $('#pcbl_pcxx_doc_delete').css('display', '');
        $('#tool_tree_eval_doc_files').css('display', '');
    } else if(EVAL_CASE.PCCZLX == "3"){
        $("#pcbl_pcxx_doc_rename").css('display', 'none');
        $('#pcbl_pcxx_doc_delete').css('display', 'none');
        $('#tool_tree_eval_doc_files').css('display', '');
    }else {
        $('#tool_tree_eval_doc_files').css('display', 'none');
    }

}

// 加载评查卷宗
function load_tree_eval_doc_files() {

    // 评查卷宗
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE.PCSLBM;
    obj.PCFLBM = EVAL_CASE.PCFLBM;
    obj.PCHDBM = EVAL_CASE.PCHDBM;
    $('#tree_eval_doc_files').tree({
        url: getRootPath()+'/handle/getDocFile',
        queryParams: { json: JSON.stringify(obj) }
    });

}

// 打开文件
function open_eval_file(jzwjbh, pczybm, pczylx, wjlx, wjlj) {

    save_edit_document();
    opening_eval_doc_file = "";
    editDocPath = (wjlx == PCLZDLX || wjlx == PCBGLX) ? wjlj : "";
    isApproveDoc = wjlx == PCLZDLX;

    ShowProgress();

    // 如果操作类型不为评查办理，且打开文书为评查流转单，系统需要自动识别是否追加评查意见行
    // 操作类型为评查办理，则评查员手动插入
    if (EVAL_CASE.PCCZLX != '1' && isApproveDoc){
        add_eval_info_approve(wjlj);
    }else {

        // 打开
        var obj = new Object();
        obj.JZWJBH = jzwjbh;
        obj.PCZYBM = pczybm;
        obj.PCZYLX = pczylx; //0.评查方案 1.评查卷宗
        obj.WJLX = wjlx; // 文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
        obj.WSCFLJ = wjlj;
        $.ajax({
            type: 'POST',
            url: getRootPath()+'/manage/getDocFile',
            data: { json: JSON.stringify(obj) },
            dataType: "json",
            success: function (result) {

                if (result == null || result == undefined) {
                    CloseProgress();
                    Alert("服务端返回数据为空。");
                    return;
                }

                if (result.status != 200){
                    CloseProgress();
                    Alert(result.note);
                    return;
                }

                try {
                    var ext = result.value.substring(result.value.lastIndexOf('.'));
                    switch (ext) {
                        case ".pdf":
                            show_eval_doc_panel("pdf");
                            CloseProgress();

                            var success = new PDFObject({ url: getRootPath() + result.value,
                                pdfOpenParams: { scrollbars: '0', toolbar: '0', statusbar: '0' }
                            }).embed("divPdf");

                            break;
                        case ".doc":
                        case ".docx":
                            show_eval_doc_panel("doc");
                            CloseProgress();

                            var error = OpenFile(getRootPath() + result.value, "TANGER_OCX");
                            if (!isNull(error)) {
                                Alert(error);
                            }

                            opening_eval_doc_file = result.value;
                            // 仅评查报告及流转单可编辑
                            SetSaveButtonState("TANGER_OCX", (wjlx == PCLZDLX || wjlx == PCBGLX));

                            break;
                        default:
                            // 下载附件
                            try{
                                var localFile = boundObjectForJS.downloadFile(getRootPath() + result.value + "," + LOCAL_PATH + result.value);
                                // 调用本机默认程序打开附件
                                boundObjectForJS.callDefaultEXE(localFile);
                                CloseProgress();
                            }catch (e){
                                CloseProgress();
                            }
                            break;
                    }

                } catch (e) {

                    CloseProgress();
                }
            }
        });
    }

}

// 新增意见输入栏
function add_eval_info_approve(wjlj) {
    isApproveDoc = true;

    // 打开
    var obj = new Object();
    obj.PCYJMC = EVAL_CASE.PCYJMC + "意见";
    obj.PCYJSQ = (EVAL_CASE.PCCZLX == "2" ? "SP" : "") + EVAL_CASE.PCSPBM;
    obj.PCYJLK = userInfo.MC + "    " +  getEndDate();
    obj.WSCFLJ = wjlj;
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/manage/getApproveDocFile',
        data: { json: JSON.stringify(obj) },
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.status != 200){
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();

                var error = OpenFile(getRootPath() + result.value, "TANGER_OCX");
                if (!isNull(error)) {
                    Alert(error);
                }
                opening_eval_doc_file = result.value;

                // 仅评查报告及流转单可编辑
                SetSaveButtonState("TANGER_OCX", true);
            } catch (e) {

                CloseProgress();
            }
        }
    });
}

// 检委会意见/小组联席会议
function add_eval_info_approve_jwh(wjlj, text) {
    isApproveDoc = true;

    // 打开
    var obj = new Object();
    obj.PCYJMC = text + "意见";
    obj.PCYJSQ = (EVAL_CASE.PCCZLX == "2" ? "SP" : "") + EVAL_CASE.PCSPBM;
    obj.PCYJLK = userInfo.MC + "    " +  getEndDate();
    obj.WSCFLJ = wjlj;
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/manage/getApproveDocFile',
        data: { json: JSON.stringify(obj) },
        dataType: "json",
        success: function (result) {

            if (result == null || result == undefined) {
                CloseProgress();
                Alert("服务端返回数据为空。");
                return;
            }

            if (result.status != 200){
                CloseProgress();
                Alert(result.note);
                return;
            }

            try {
                show_eval_doc_panel("doc");
                CloseProgress();

                var error = OpenFile(getRootPath() + result.value, "TANGER_OCX");
                if (!isNull(error)) {
                    Alert(error);
                }
                opening_eval_doc_file = result.value;

                // 仅评查报告及流转单可编辑
                SetSaveButtonState("TANGER_OCX", true);
            } catch (e) {

                CloseProgress();
            }
        }
    });
}

// 显示评查卷宗预览区域
function show_eval_doc_panel(type) {
    $("#pcbl_pcxx_wdck").css('display','block');
    $("#pcbl_pcxx_center_left").css('display','none');
    show_eval_info_pcjz_area(); //显示评查卷宗浮动面板

    switch(type){
        case "doc":
            resize_div_eval_handle_doc_area();
            // 加载文书
            document.getElementById('divPdf').style.display = "none";
            document.getElementById('divDoc').style.display = "";

            break;
        case "pdf":

            // 控件展示区域
            var height = $("#div_eval_handle_file_area").height();
            $("#divPdf").css("height", (height - 4) + 'px');

            // PDF方式打开文书
            document.getElementById('divDoc').style.display = "none";
            document.getElementById('divPdf').style.display = "";

            break;
        default:
            break;
    }

    // 影藏底部菜单栏
    init_eval_handle_bottom_tool('');
}

// 调整文书控件大小
function resize_div_eval_handle_doc_area() {

    // 文书控件展示区域
    var height = $("#div_eval_handle_file_area").height();
    $("#divDoc").css("height", (height - 4) + 'px');

}

//动态获取处理结果/评查结果
function load_cljg_pcjg(bm) {
    $.ajax({
        type: 'get',
        async: false,
        url: getRootPath() + '/common/getDataDictionaryByLBBM?lbbm='+bm,
        dataType: 'json',
        success: function(data) {
            console.log('数据' + JSON.stringify(data))
            if(data.code==200){
                var data=data.data;
                if(bm==9101){
                    var html='';
                    for(var i=0;i<data.length;i++){
                        html+='<div class="radio">';
                        html+='<div class="redio_click">';
                        html+='<input class="input_radio_cljg" name="rd_eval_info_pcjl_cl" type="radio" value="'+data[i].mc+'"/>';
                        html+='</div>'+data[i].mc+'</div>';
                    }
                    $('#loadCljg').append(html)
                    var pcjlElement = $("input[name='rd_eval_info_pcjl_cl'][value='" + EVAL_CASE.CLJG + "']");
                    pcjlElement.parent().addClass('redio_click_no');
                    pcjlElement.attr("checked",true);
                }else {
                    var html='';
                    for(var i=0;i<data.length;i++){
                        html+='<div class="radio">';
                        html+='<div class="redio_click">';
                        html+='<input class="input_radio_pcjg" name="rd_eval_info_pcjl_jg" type="radio" value="'+data[i].mc+'"/>';
                        html+='</div>'+data[i].mc+'</div>';
                    }
                    $('#loadPcjg').append(html);
                    var pcjgElement = $("input[name='rd_eval_info_pcjl_jg'][value='" + EVAL_CASE.PCJL + "']");
                    pcjgElement.parent().addClass('redio_click_no');
                    pcjgElement.attr("checked",true);
                }
            }else {
                Alert('getDataDictionaryByLBBM 错误'+data.message)
            }


        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert("消息", 'getDataDictionaryByLBBM 接口错误')
        }
    })
}
//动态获取承办人身份
function load_sfldba(bm) {
    $.ajax({
        type: 'get',
        async: false,
        url: getRootPath() + '/common/getDataDictionaryByLBBM?lbbm='+bm,
        dataType: 'json',
        success: function(data) {
            console.log('数据' + JSON.stringify(data))
            if(data.code==200){
                var data=data.data;
                if(bm==9104){
                    var html='';
                    for(var i=0;i<data.length;i++){
                        html+='<div class="radio">';
                        html+='<div class="redio_click">';
                        html+='<input class="input_radio_cljg" name="rd_eval_info_sfldba" type="radio" value="'+data[i].sm+'"/>';
                        html+='</div>'+data[i].mc+'</div>';
                    }
                    $('#loadSfldba').append(html)
                    var pcjlElement = $("input[name='rd_eval_info_sfldba'][value='" + EVAL_CASE.SFLDBA + "']");
                    pcjlElement.parent().addClass('redio_click_no');
                    pcjlElement.attr("checked",true);
                }
            }else {
                Alert('getDataDictionaryByLBBM 错误'+data.message)
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert("消息", 'getDataDictionaryByLBBM 接口错误')
        }
    })
}

// 访问接口，动态生成质量评查内容
function load_eval_info_card() {
    $.ajax({
        url: getRootPath() + '/filter/getEvalCards',
        type: 'get',
        dataType: 'json',
        data: { pcslbm: EVAL_CASE.PCSLBM, pcxflbm: '' },
        success: function(data){

            var muban_dom = $("<div class='muban'></div>");
            data.pcxFlVos.forEach(function (pcfl) {//评查分类循环
                var pcfl_dom = $("<div class=\"pcbl_pcxx_center_left_menu\"><div class=\"center_left_menu_top\"><h5>" + pcfl.pcxflmc + "</h5></div></div>");

                for (var k1 in pcfl) {
                    if (Object.prototype.toString.call(pcfl[k1]) != '[object Array]') {
                        pcfl_dom.attr(k1, pcfl[k1]);
                    }
                }
                pcfl.children.forEach(function (pczl, index) {//评查子分类循环
                    var pczl_dom = $("<div class=\"radio radio_wwt\"><div class=\"redio_click\"></div>" + pczl.pcxflmc + "</div>");
                    var pczl_box = $("<div class=\" pczl_box\" style='display: none; margin:10px 0px 10px 0px;'></div>");
                    if (pczl.pcjg == "1") {
                        pczl_dom.find('.redio_click').addClass('redio_click_no');
                    }
                    for (var kk in pczl) {
                        if (Object.prototype.toString.call(pczl[kk]) != '[object Array]') {
                            pczl_box.attr(kk, pczl[kk]);
                        }
                    }
                    pczl.pcxList.forEach(function (item) { //评查项循环
                        if (!item.pcxmc) {
                            return;
                        }
                        var dom;
                        if (item.pcfs && item.pcfs == 2) {
                            dom = $("<div class=\"select_option\"><input type='text' placeholder='其他'></div>");
                        } else {
                            if(item.sm == 'null' || item.sm == '' || item.sm == undefined || item.sm =="" || item.sm == null){
                                item.sm = '';
                            }
                            dom = $("<div class=\"select_option\" style=\"height: 30px;position: relative;\"><div id=\"zlpc_pcxxys_id\" ><label style=\"margin-left: 11px;\"><input style=\" position: relative;top: 2px;right: 5px;\" type=\"checkbox\">" +
                                item.pcxmc + "</label></div><div class=\"zlpc_pcxx_hideys\"><div class=\"zlpc_pcxx_xsjys\"></div><span>"+item.pcxmc+"</span></div><div class=\"zx_bzys\"><label style=\"width:60px;float: left;text-align: right;\">备注：</label><input type=\"text\" style='width: calc(100% - 65px);' value="+item.sm+"></div> </div>");
                            // dom = $("<div class=\"select_option\" style=\"height: 30px;\"><div style=\"display: inline-block; margin: 6px 0 0 11px;\"><label title=\""+item.pcxmc+"\"><input id=\""+item.pcxbm+"\"  style=\" position: relative;top: 2px;right: 5px;\" type=\"checkbox\">" +
                            //     item.pcxmc + "</label></div><div class=\"zx_bzys\"><label style=\"width:60px;float: left;text-align: right;\">备注：</label><input id=\""+item.pcxbm+"\"  type=\"text\" style='width: calc(100% - 65px);' value="+item.sm+"></div> </div>");
                            if (item.pcjg == 1) {
                                dom.find("input[type='checkbox']").prop("checked", true);
                            }
                        }


                        //使用name属性关联到子分类
                        dom.attr("name", pczl.pcxflbm);
                        dom.find('input:eq(0)').val(item.pcjg);
                        dom.find('input:eq(0)').attr('number', item.pcxbm);
                        for (var ks in item) {
                            if (Object.prototype.toString.call(item[ks]) != '[object Array]') {
                                dom.attr(ks, item[ks]);
                            }
                        }

                        if (item.pcjg == 1 && item.pcjg != '') {
                            dom.find('iniput:eq(0)').prop('checked', true);
                        }
                        //添加到子分类框里
                        pczl_box.append(dom);
                    });


                    pczl_dom.attr("box-no", pczl.pcxflbm);
                    pczl_box.attr("box-no", pczl.pcxflbm);
                    pcfl_dom.find(".center_left_menu_top").append(pczl_dom);
                    pcfl_dom.append(pczl_box);
                });
                //添加备注
                var bz_box = '';
                if(pcfl.children[0].sm == null){
                    pcfl.children[0].sm = '';
                }
                //不显示备注信息 LZH 2018年4月2日
                //bz_box += '<div class=\"bz_btn\"><span style="width: 60px;float: left;text-align: right">备注：</span><input type="text" style="width:calc(100% - 65px)"  value='+ pcfl.children[0].sm +'></div>'
                pcfl_dom.find(".center_left_menu_top").append("<div class=\"switch\"></div>");
                //pcfl_dom.find(".center_left_menu_top").append(bz_box);
                // console.log(pcfl_dom);
                // Alert(JSON.stringify(pcfl_dom.html()));

                muban_dom.append(pcfl_dom);
            });

            $("#ZLPCNR_CONTAINTER").append(muban_dom);

            // $(".switch").off("click")
            $(".switch").unbind("click");
            $(".radio").unbind('click');
            addClickListener();
            addHoverType();
            // 若为评查办理，则显示保存按钮
            if(EVAL_CASE.PCCZLX == "1"){
                init_eval_handle_bottom_tool('0');
            }
        },
        error: function(e){
            Alert(JSON.stringify(e));
        }
    });

    // 监听窗口，
    //$("#ajxxdiv").click(function () {
    //    alert("hello")
    //})
}

// function  pczxCheckboxChange(code) {
//     var txt = $("#txt"+ code);
//     var ck = $("#ck"+ code).attr("id");
//     if(ck.checked){
//         txt.attr("disabled", false);
//     }else{
//         txt.attr("disabled", true );
//         txt.val("");
//     }
// }

//判断文本输入框输入内容实际长度是否满足MaxLength
function CheckRealLength()
{
    var realLength;                 //文本框中内容的实际长度
    var maxLength = 10;                  //要求的最大长度

    var list = document.getElementsByName("txtBz");        //获取表单并付给数组
    for (var i = 0 ; i < list.length ; i++)
    {
        realLength = list[i].value.length;
        if (realLength > 0)
        {
            if (realLength > maxLength)
            {
                alert("温馨提示：您输入的字符["+list[i].value+"],长度为:" + realLength + ")应该小于:" + maxLength);
                list[i].focus();
                return true;
            }
        }
    }

}
// 遍历质量评查内容，生成提交数据
function initPostData() {
    var aob = {};
    aob.pcxFlVos = [];

    var pcfls = $('.muban').find('.pcbl_pcxx_center_left_menu');

    for (var j = 0; j < pcfls.length; j++) {
        var pcfl_obj_attrs = pcfls[j].attributes;
        var pcfl = {};
        pcfl.children = [];

        for (var pcfl_attr_k in pcfl_obj_attrs) {
            pcfl[pcfl_obj_attrs[pcfl_attr_k].name] = pcfl_obj_attrs[pcfl_attr_k].value;
        }
        var pczls = pcfls.eq(j).find('.pczl_box');
        var lencon=pczls.length-1;
        var b=0;
        for (var m = lencon; m >=0; m--) {
            var pczl_attrs = pczls[m].attributes;
            var pczl_att = {};
            for (var pzcl_attr_k in pczl_attrs) {
                pczl_att[pczl_attrs[pzcl_attr_k].name] = pczl_attrs[pzcl_attr_k].value;
            }

            pczl_att.pcxList = [];

            var pcxs = pczls.eq(m).find(".select_option");
            pczl_att.pcjg=0;
            for (var n = 0; n < pcxs.length; n++) {
                var pcx_attrs = pcxs[n].attributes;
                var pcx_attr = {};
                for (var pcx_attr_k in pcx_attrs) {
                    pcx_attr[pcx_attrs[pcx_attr_k].name] = pcx_attrs[pcx_attr_k].value;
                }
                if (parseInt(pcx_attr.pcfs) == 2) {
                    pcx_attr.pcjg = pcxs.eq(n).find('input').val();
                    if(pcx_attr.pcjg!=''&&pcx_attr.pcjg!=null){
                        if(b==0){
                            pczl_att.pcjg=1;
                            b=1;
                        }
                    }
                } else {
                    if(pcxs.eq(n).find('input:eq(1)').is(":text")){
                        var val = pcxs.eq(n).find('input:eq(1)').val();
                        pcx_attr.sm = val;
                    }
                    if (pcxs.eq(n).find('input').is(":checked")) {
                        pcx_attr.pcjg = '1';
                        if(b==0){
                            pczl_att.pcjg=1;
                            b=1;
                        }
                    } else {
                        pcx_attr.pcjg = '0';

                    }
                }
                pczl_att.pcxList.push(pcx_attr);
            }

            pcfl.children.push(pczl_att);
        }

        // 若无问题项被选中，则问题性质取选中项
        if (b == 0) {
            var selected_no = "";
            var rds = pcfls.eq(j).find(".radio");

            for (var h = 0; h < rds.length; h++ ){
                if (rds[h].children[0].attributes['class'].value == "redio_click redio_click_no"){
                    selected_no = rds[h].attributes['box-no'].value;
                    break;
                }
            }
            for (var m = 0; m < pcfl.children.length; m++ ){
                if (pcfl.children[m].pcxflbm == selected_no){
                    pcfl.children[m].pcjg = "1";
                    break;
                }
            }
        }
        var bz_xx = pcfls.eq(j).find(".bz_btn>input");
        for (var m = 0; m < pcfl.children.length; m++ ){
            pcfl.children[m].sm = $(bz_xx).val();
        }
        aob.pcxFlVos.push(pcfl);
    }

    return aob;
}

// 判断每个选项面板是否显示
function toggleShow() {
    // alert("do toggleShow");
    var sw = $(".switch");
    // console.log(sw);
    for (var j = 0; j < sw.length; j++) {
        var doms = sw.eq(j).siblings(".radio");
        //console.log(doms);
        for (var i = 0; i < doms.length; i++) {
            var no = doms.eq(i).attr('box-no');
            //console.log(no);
            var target = $(".pczl_box[box-no=" + no + "]");
            //if (doms.eq(i).find('.redio_click').hasClass('redio_click_no') && sw.eq(j).hasClass('switch_click')) {
            if (doms.eq(i).find('.redio_click').hasClass('redio_click_no') && sw.eq(j).hasClass('switch_click')) {
                target.show();
            } else {
                target.hide();
            }
        }
    }
}

// 评查项点击监听事件
function addClickListener() {
    //console.log($(".switch"));
    $(".radio").unbind("click");
    $(".radio").bind("click", function () {
        $(this).children(".redio_click").addClass("redio_click_no");
        $(this).children(".redio_click").children("input").attr('checked','true');
        $(this).siblings().children(".redio_click").removeClass("redio_click_no");
        $(this).siblings(".switch").addClass('switch_click');
        toggleShow();
    });

    $(".switch").unbind("click");
    $(".switch").bind("click", function () {
        $(this).toggleClass('switch_click');
        toggleShow();
    });
}

// 案卡显隐
function display_eval_handle_cards() {
    $("#pcbl_pcxx_center_left").css('display','block');
    $("#pcbl_pcxx_wdck").css('display','none');

    // 仅评查办理才显示保存按钮
    init_eval_handle_bottom_tool(EVAL_CASE.PCCZLX == "1" ? "0" : "");
}

// 初始化菜单栏
function init_eval_handle_deal_toolbar(isReload) {

    // 是否重新加载评查状态
    if (isReload){
        // 初始化评查案件信息，方法定义在评查办理首页
        init_eval_case_info();
    }

    //动态显示按钮
    var data={
        "pcflbm": '000',
        "lcmbbm": "0000000001",
        "lcjdbh": EVAL_CASE.PCJDBH,
        "czlxbm": EVAL_CASE.PCCZLX,
        "pchdbm":EVAL_CASE.PCHDBM,
        "pczybm":EVAL_CASE.PCSLBM,
        "sm":''
    }

    // 获取动作列表
    if(EVAL_CASE.PreviewMode =='标签'){
        $('#pcblBut').empty();
    }else{
        var btnList = $('#pcblBut').children();
        for (var i=0; i<btnList.length; i++){
            if(btnList[i].id != "mb_eval_handle_btn_more"
               && btnList[i].id != "mm_eval_handle_btnlist"){
                btnList[i].remove();
            }
        }
    }
    // $('#pcblBut').empty();
    $('#pcblBut').append('<div class="pcbl_pcxx_top_box" onclick="click_pcbl_pcxx_top_ajxx()">案件信息</div>');
    $('#pcblBut').append('<div class="pcbl_pcxx_top_box" id="pcbl_pcxx_top_pcyl">评查预览</div>');
    $.ajax({
        type: "get",
        async: false,
        url: getRootPath() + '/menu/queryMenuButton',
        data:data,
        dataType: "json",
        success: function(data) {
            if(data.code==200){
                var data=data.data;
                var html='';
                for(var i=0;i<data.length;i++){
                    if(data[i].icon==''||data[i].icon==null){
                        html = '<div class="pcbl_pcxx_top_box" onclick="'+data[i].clcx +'('+data[i].clcxcs +')">'+data[i].dzmc+'</div>';
                    }else {
                        html = '<div class="pcbl_pcxx_but '+data[i].icon+'" onclick="'+data[i].clcx +'('+data[i].clcxcs +')">'+data[i].dzmc+'</div>';
                    }

                    if(EVAL_CASE.PreviewMode =='标签' || i < 5 ||  $(window).width()>1460){
                        $('#pcblBut').append(html);
                    }else {
                        $('#mm_eval_handle_btnlist').append(html);
                    }
                }
                if(EVAL_CASE.PreviewMode =='标签' ||  $(window).width()>1460){
                    $('#mb_eval_handle_btn_more').css('display', 'none');
                } else {
                    $('#mb_eval_handle_btn_more').css('display', 'block');
                }
            }else {
                Alert('queryMenuButton接口错误：' +data.message);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert('queryMenuButton接口访问错误');
        }
    });
    $('#mb_eval_handle_btn_more').css('display', 'block');
    // 评查预览
    $("#pcbl_pcxx_top_pcyl").unbind( "click" );
    $("#pcbl_pcxx_top_pcyl").bind("click", function () {

        $('#win').window('open');
        xlpcLn();
    });

}

// 评查办理底部菜单栏
function init_eval_handle_bottom_tool(toolID) {

    $('#tool_eval_handle_deal_save').css('display', 'none'); //评查案卡工具栏
    $('#tool_eval_handle_deal_pcbp').css('display', 'none'); //评查报审
    $('#tool_eval_handle_deal_pcsp').css('display', 'none'); //评查审批
    $('#tool_eval_handle_deal_pcfk').css('display', 'none'); //评查反馈
    $('#toll_eval_handle_deal_bmfk').css('display', 'none'); //部门反馈

    $('.pcbl_pcxx_cent_bloak').removeClass('pcbl_pcxx_cent_bloak');
    $('.pcbl_pcxx_cent_blo').removeClass('pcbl_pcxx_cent_blo');

    switch (toolID){
        case "0": //评查案卡
            $('#tool_eval_handle_deal_save').css('display', '');
            $('.pcbl_pcxx_cent').addClass('pcbl_pcxx_cent_bloak');
            // 保存（评查案卡）
            $("#pcbl_pcxx_center_left_bc").unbind("click");
            $("#pcbl_pcxx_center_left_bc").bind("click", function () {
                // var bl = CheckRealLength();
                // if(bl){
                //     return;
                // }
                var vo = initPostData();
                // 评查结论
                var obj = new Object();
                obj.PCSLBM = EVAL_CASE.PCSLBM;
                var cljg = $("input[name='rd_eval_info_pcjl_cl']:checked").val();
                obj.CLJG = isNull(cljg) ? "" : cljg;
                //是否领导办案
                var sfldba = $("input[name='rd_eval_info_sfldba']:checked").val();
                obj.SFLDBA = isNull(sfldba) ? "" : sfldba;
                var pcjl = $("input[name='rd_eval_info_pcjl_jg']:checked").val();

                if (isNull(pcjl)){
                    Alert("请勾选结果等次！");
                    return;
                }

                obj.PCJL = isNull(pcjl) ? "" : pcjl;
                obj.SM = $("#txt_eval_info_pcjl_bz").val();

                $.ajax({
                    url: getRootPath() + "/filter/saveEvalCards",
                    data: { json: JSON.stringify(vo), jbxx: JSON.stringify(obj)},
                    type: 'post',
                    async: true,
                    dataType: 'json',
                    success: function (result) {

                        if (result.status == 200){
                            EVAL_CASE.CLJG = obj.CLJG;
                            EVAL_CASE.SFLDBA = obj.SFLDBA;
                            EVAL_CASE.PCJL = obj.PCJL;
                            EVAL_CASE.SM = obj.SM;
                            Alert("提交成功！");
                        }else{
                            Alert(result.note);
                        }
                    }
                });
            });
            break;
        case "1": //评查报审

            if (isSendApprove == "0"){
                $('#btn_eval_handle_deal_pcbs_confirm').text("发送审批");
                $('#btn_eval_handle_deal_fscbr_confirm').text("发送承办人");
                $('#btn_eval_handle_deal_pcbs_confirm').css('display', '');
                $('#btn_eval_handle_deal_fscbr_confirm').css('display', '');
            }else if(isSendApprove == "1"){
                $('#btn_eval_handle_deal_pcbs_confirm').text("提交意见");
                $('#btn_eval_handle_deal_fscbr_confirm').text("提交意见");
                $('#btn_eval_handle_deal_pcbs_confirm').css('display', '');
                $('#btn_eval_handle_deal_fscbr_confirm').css('display', 'none');
            }else if(isSendApprove == "-1"){
                $('#btn_eval_handle_deal_pcbs_confirm').text("提交意见");
                $('#btn_eval_handle_deal_fscbr_confirm').text("提交意见");
                $('#btn_eval_handle_deal_pcbs_confirm').css('display', 'none');
                $('#btn_eval_handle_deal_fscbr_confirm').css('display', '');
            }else{
                $('#btn_eval_handle_deal_pcbs_confirm').text("发送审批");
                $('#btn_eval_handle_deal_fscbr_confirm').text("发送承办人");
                $('#btn_eval_handle_deal_pcbs_confirm').css('display', 'none');
                $('#btn_eval_handle_deal_fscbr_confirm').css('display', 'none');
            }

            // 取消选中状态
            $('#tool_eval_handle_deal_pcbp').find(".redio_click").removeClass("redio_click_no");
            $('#tool_eval_handle_deal_pcbp').find(".redio_click").children("input").attr('checked',false);

            $('#tool_eval_handle_deal_pcbp').css('display', '');
            $('.pcbl_pcxx_cent').addClass('pcbl_pcxx_cent_blo');
            $("#btn_eval_handle_deal_pcbs_confirm").unbind( "click" );
            $("#btn_eval_handle_deal_pcbs_confirm").bind("click", function () {

                save_doc_file(function () {

                    var pcyj = GetMarkValue("PCYYJ").replace(/[\r\n]/g,'');
                    if(isNull(pcyj)){
                        Alert("请填写评查意见！");
                        return;
                    }

                    // 拟认定为合格的案件，由评查人员报请分管该业务条线的检察长审定；拟认定为优质、瑕疵、不合格的案件，报检察长或者检察委员会审定。
                    // 暂定活动形式俩次审批，先组长审批，然后根据要求评查员再次报审
                    // 非活动形式 直接按照结论报审不同人员。
                    // var pcjl = $("input[name='rd_eval_info_pcjl_jg']:checked").val();
                    var pcjl = EVAL_CASE.PCJL;
                    var pcflbm = EVAL_CASE.PCFLBM;
                    var pcjdbh = EVAL_CASE.PCJDBH;

                    // 非活动
                    if (EVAL_CASE.SFPCFP == 'N'){

                        if (pcjl == '合格案件'){
                            send_eval_handle_deal_approve("60");
                        }else{
                            send_eval_handle_deal_approve("60");
                        }


                    }
                    // 活动，通过pcjdbh分辨第几次报审
                    if (EVAL_CASE.SFPCFP == 'Y'){

                        if(pcjdbh == '006'){ // 组长
                            send_eval_handle_deal_approve("20");
                        }

                        if (pcjdbh == '007'){
                            if (pcjl == '合格案件'){
                                send_eval_handle_deal_approve("60");
                            }else{
                                send_eval_handle_deal_approve("60");
                            }
                        }

                    }

                });
            });
            $("#btn_eval_handle_deal_fscbr_confirm").unbind( "click" );
            $("#btn_eval_handle_deal_fscbr_confirm").bind("click", function () {

                save_doc_file(function () {

                    var pcyj = GetMarkValue("PCYYJ").replace(/[\r\n]/g,'');
                    if(isNull(pcyj)){
                        Alert("请填写评查意见！");
                        return;
                    }

                    Confirm("确认", "是否发送给承办检察官？", function (r) {
                        if (r) {

                            var obj = new Object();
                            obj.PCSLBM = EVAL_CASE.PCSLBM;
                            obj.PCFLBM = EVAL_CASE.PCFLBM;
                            obj.PCHDBM = EVAL_CASE.PCHDBM;
                            obj.BMSAH = EVAL_CASE.BMSAH;
                            $.ajax({
                                type: 'POST',
                                url: getRootPath()+'/handle/sendFeedback',
                                data: { json: JSON.stringify(obj) },
                                dataType: "json",
                                success: function (result) {

                                    if (result.status == 200){
                                        // 初始化操作按钮
                                        init_eval_handle_deal_toolbar(true);
                                        init_eval_handle_bottom_tool('');
                                        Alert("发送给承办人成功！");
                                    } else {
                                        Alert(result.note);
                                    }
                                }

                            });
                        }
                    });
                });
            });

            break;
        case "2": //评查审批

            // 取消选中状态
            $('#tool_eval_handle_deal_pcsp').find(".redio_click").removeClass("redio_click_no");
            $('#tool_eval_handle_deal_pcsp').find(".redio_click").children("input").attr('checked',false);

            // 仅评查员直接送审的审批记录可退回
            if (EVAL_CASE.PCR_DWBM == EVAL_CASE.SSRDWBM && EVAL_CASE.PCR_GH == EVAL_CASE.SSRGH){
                //$('#rd_eval_handle_deal_pcsp_spyj_reject').css('display', '');
                //$('#rd_eval_handle_deal_pcsp_spyj_suggest').css('display', 'none');
                $("#btn_eval_handle_deal_pcsp_sendBack").css('display', '');
            } else {
                //$('#rd_eval_handle_deal_pcsp_spyj_reject').css('display', 'none');
                //$('#rd_eval_handle_deal_pcsp_spyj_suggest').css('display', 'none');
                $("#btn_eval_handle_deal_pcsp_sendBack").css('display', 'none');
            }

            $('#tool_eval_handle_deal_pcsp').css('display', '');
            $('.pcbl_pcxx_cent').addClass('pcbl_pcxx_cent_blo');
            $("#btn_eval_handle_deal_pcsp_sendBack").unbind( "click" );
            $("#btn_eval_handle_deal_pcsp_sendBack").bind("click", function () {
                deal_eval_handle_deal_approve_back();
            });
            $("#btn_eval_handle_deal_pcsp_save").unbind( "click" );
            $("#btn_eval_handle_deal_pcsp_save").bind("click", function () {
                save_edit_document();
            });
            $("#btn_eval_handle_deal_pcsp_confirm").unbind( "click" );
            $("#btn_eval_handle_deal_pcsp_confirm").bind("click", function () {
                deal_eval_handle_deal_approve('0');
            });

            $('#btn_eval_handle_deal_pcsp_sendApp').css('display', 'none');
        /*    // 第一次审批，不显示继续送审按钮
            if (EVAL_CASE.PCSPBM.indexOf("000001") >= 0) {
                $('#btn_eval_handle_deal_pcsp_sendApp').css('display', 'none');
            } else {*/
                // $('#btn_eval_handle_deal_pcsp_sendApp').css('display', '');
                // $("#btn_eval_handle_deal_pcsp_sendApp").unbind( "click" );
                // $("#btn_eval_handle_deal_pcsp_sendApp").bind("click", function () {
                //     deal_eval_handle_deal_approve('1');
                // });
            /*}*/

            break;
        case "3": //评查反馈

            // 取消选中状态
            $('#tool_eval_handle_deal_pcfk').find(".redio_click").removeClass("redio_click_no");
            $('#tool_eval_handle_deal_pcfk').find(".redio_click").children("input").attr('checked',false);

            $('#tool_eval_handle_deal_pcfk').css('display', '');
            $('.pcbl_pcxx_cent').addClass('pcbl_pcxx_cent_blo');
            $("#btn_eval_handle_deal_pcfk_confirm").unbind( "click" );
            $("#btn_eval_handle_deal_pcfk_confirm").bind("click", function () {
                deal_eval_handle_deal_pcfk();
            });
            break;
        case "4": //部门反馈

            // 取消选中状态
            $('#toll_eval_handle_deal_bmfk').find(".redio_click").removeClass("redio_click_no");
            $('#toll_eval_handle_deal_bmfk').find(".redio_click").children("input").attr('checked',false);

            $('#toll_eval_handle_deal_bmfk').css('display', '');
            $('.pcbl_pcxx_cent').addClass('pcbl_pcxx_cent_blo');
            $("#btn_eval_handle_deal_bmfk_confirm").unbind( "click" );
            $("#btn_eval_handle_deal_bmfk_confirm").bind("click", function () {
                deal_eval_handle_deal_bmfk();
            });
            break;
        default:
            break;
    }

    // 文书控件展示区域
    resize_div_eval_handle_doc_area();

    // 点击意见，写入评查报告
    $(".bgkjClick").unbind("click");
    $(".bgkjClick").bind("click", function () {
        $(this).children(".redio_click").addClass("redio_click_no");
        $(this).children(".redio_click").children("input").attr('checked','true');
        $(this).siblings().children(".redio_click").removeClass("redio_click_no");
        $(this).siblings(".switch").addClass('switch_click');

        if(EVAL_CASE.PCCZLX == "1"){
            insert_doc_eval_pcyyj();
        } else {
            var advice = $(this).children(".redio_click").children("input").val();
            if(advice == "退回"){
                $('#btn_eval_handle_deal_pcsp_sendApp').css('display', 'none');
                $('#btn_eval_handle_deal_pcsp_confirm').css('display', '');
                $('#btn_eval_handle_deal_pcsp_save').css('display', '');
            }else{
              $('#btn_eval_handle_deal_pcsp_sendApp').css('display', 'none');
                $('#btn_eval_handle_deal_pcsp_confirm').css('display', '');
                $('#btn_eval_handle_deal_pcsp_save').css('display', '');
            }
            if (advice != "意见" && advice != "退回"){
                SetBookmarkValue((EVAL_CASE.PCCZLX == "2" ? "SP" : "") + EVAL_CASE.PCSPBM, advice + String.fromCharCode(10));
            }
        }
    });
}

var documentSavedCallback;
// 保存评查报告
function save_doc_file(callback) {

    //document.all("TANGER_OCX").CancelLastCommand = true;

    if(isNull(editDocPath) || editDocPath == ""){
        return;
    }

    // 编辑保存
    var url = getRootPath() + "/manage/savePCBG?wjlj=" + editDocPath;
    var fileresult = SaveToUrl(url);
    if (fileresult == null && fileresult != "") {
        if(callback){
            documentSavedCallback = callback;
        }
        //Alert("修改评查报告成功。");
    } else {
        Alert("修改评查报告失败。" + fileresult);
    }
}

// 文书保存成功后触发事件
function documentSaved() {
    if(documentSavedCallback){
        documentSavedCallback();
        documentSavedCallback = null;
    }
}

// 评查卷宗文书打开后事件
function documentOpened() {

    // 非评查流转单，不执行后续操作
    if(!isApproveDoc)
        return;

    SetDocUnprotect();

    // 若为评查流转单，则显示意见栏
    show_eval_doc_approve_tool();

}

// 审批表操作工具栏
function show_eval_doc_approve_tool() {

    // 评查操作类型：0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    switch (EVAL_CASE.PCCZLX){
        case "0":
            SetDocProtect();
            break;
        case "1":
            // 若处于待审批状态，则不能编辑流转单
            if(!isNull(EVAL_CASE.PCSPBM_BL) && isNull(EVAL_CASE.SPJL)){
                SetDocProtect();
            }else{
                if(EVAL_CASE.PCJDBH == "006"){
                    SetDocProtect();
                    init_eval_handle_bottom_tool('1'); //评查意见菜单栏
                }else{
                    insert_doc_eval_pcyyj();
                }
            }
            break;
        case "2":
            init_approve_doc_input(); //评查审批
            init_eval_handle_bottom_tool('2'); //评查审批菜单栏
            break;
        case "3":
            init_approve_doc_input(); //评查反馈
            init_eval_handle_bottom_tool('3'); //评查反馈菜单栏
            break;
        case "4":
            init_approve_doc_input(); //部门反馈
            init_eval_handle_bottom_tool('4'); //部门反馈菜单栏
            break;
        default:
            break;
    }
}

// 初始化流转单输入框
function init_approve_doc_input() {
    SetDocProtect();
    SetBmarkEditable((EVAL_CASE.PCCZLX == "2" ? "SP" : "") + EVAL_CASE.PCSPBM);
}

// 评查员意见
function insert_doc_eval_pcyyj() {
    SetDocProtect();
    SetBmarkEditable("BZXX");
    SetBmarkEditable(EVAL_CASE.PCSPBM);
}

// 备注信息
function init_doc_eval_bzxx() {
    SetDocProtect();
    SetBmarkEditable("BZXX");
}

// 评查预览
function xlpcLn() {
    var data = initPostData();
    var data = data.pcxFlVos;
    var html = '<table border="">';
    var maxLength = 0;
    for (var n = 0; n < data.length; n++) {
        if(data[n].children.length > maxLength){
            maxLength = data[n].children.length;
        }
    }
    for (var i = 0; i < data.length; i++) {
        html += '<tr class="thead">';
        var htmlTit = '';
        var htmlCon = '';

        for (var z = 0; z < maxLength; z++) {
            if(data[i].children[z] == undefined || data[i].children[z] == null || data[i].children[z] == ""){
                htmlTit += '<td></td>';
                var pclistCon = '';
            }else{
                htmlTit += '<td>' + data[i].children[z].pcxflmc + '</td>';
                var pcList = data[i].children[z].pcxList.length;
                var pclistCon = '';
                var num = 1;
                for (var j = 0; j < data[i].children[z].pcxList.length; j++) {
                    var bz = "";
                    if(data[i].children[z].pcxList[j].sm == 'null' || data[i].children[z].pcxList[j].sm == '' || data[i].children[z].pcxList[j].sm == undefined || data[i].children[z].pcxList[j].sm =="" || data[i].children[z].pcxList[j].sm == null){
                        data[i].children[z].pcxList[j].sm = "";
                    }else{
                        bz = "(备注:"+ data[i].children[z].pcxList[j].sm+")";
                    }
                    if (data[i].children[z].pcxList[j].pcfs == '1' && data[i].children[z].pcxList[j].pcjg == '1') {
                        pclistCon += '<p>' + num + '.' + data[i].children[z].pcxList[j].pcxmc + bz +'</p>';
                        num++;
                    } else if (data[i].children[z].pcxList[j].pcfs == '2' && data[i].children[z].pcxList[j].pcjg != '1' && data[i].children[z].pcxList[j].pcjg != '0' && data[i].children[z].pcxList[j].pcjg != "" && data[i].children[z].pcxList[j].pcjg != undefined && data[i].children[z].pcxList[j].pcfs != null) {
                        pclistCon += '<p>' + num + '.' + data[i].children[z].pcxList[j].pcxmc + '(' + data[i].children[z].pcxList[j].pcjg + ')' + bz +'</p>';
                        num++;
                    }
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
    $('.pcylCon').html(html)
}

function deal_eval_handle_deal_approve_back() {

    // JS对象
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE.PCSLBM;
    obj.PCSPBM  = EVAL_CASE.PCSPBM;
    obj.SPJL  = "退回";
    obj.SPYJ  = "退回";
    obj.PCLZDLJ = editDocPath; //评查流转单路径，意见提交后，移除评查员书签，审批后不能再修改意见
    obj.SPJSMC = EVAL_CASE.PCYJMC + "意见"; //评查意见抬头

    // 退回
    $.ajax({
        url: getRootPath() + "/handle/dealApprove",
        data: { json: JSON.stringify(obj)},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {

            if (result.status == 200){

                SetSaveButtonState("TANGER_OCX", false);
                init_eval_handle_bottom_tool('');

                // 初始化操作按钮
                init_eval_handle_deal_toolbar(true);

                Alert("审批已退回！");
            }else{
                Alert(result.note);
            }
        }
    });
}

// 评查案件审批
function deal_eval_handle_deal_approve(type){

    // JS对象
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE.PCSLBM;
    obj.PCSPBM  = EVAL_CASE.PCSPBM;
    obj.SPJL  = $("input[name='rd_eval_handle_deal_pcsp_spyj']:checked").val();
    if(isNull(obj.SPJL)){
        Alert("请勾选审批结论！");
        return;
    }
    obj.SPYJ  = GetMarkValue("SP" + EVAL_CASE.PCSPBM).replace(/[\r\n]/g,'');
    if(isNull(obj.SPYJ)){
        Alert("请填写审批意见！");
        return;
    }
    obj.PCLZDLJ = editDocPath; //评查流转单路径，意见提交后，移除评查员书签，审批后不能再修改意见
    obj.PCYYJSQ = "PCYYJ"; //评查员意见书签

    save_doc_file(function () {

        if(!isNull(type) && type == "1"){
            // 保存审批意见并继续送审
            send_eval_handle_deal_approve(EVAL_CASE.SPJSBM, function(appinfo){

                $.ajax({
                    url: getRootPath() + "/handle/dealApprove",
                    data: { json: JSON.stringify(obj), appinfo: JSON.stringify(appinfo)},
                    type: 'post',
                    async: true,
                    dataType: 'json',
                    success: function (result) {

                        if (result.status == 200){

                            init_eval_handle_bottom_tool('');

                            // 初始化操作按钮
                            init_eval_handle_deal_toolbar(true);

                            Alert("审批成功！");

                            close_win_eval_handle_deal_fssp();
                        }else{
                            Alert(result.note);
                        }
                    }
                });
            });
        }else {
            // 保存审批意见并返回
            $.ajax({
                url: getRootPath() + "/handle/dealApprove",
                data: { json: JSON.stringify(obj)},
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {

                    if (result.status == 200){

                        init_eval_handle_bottom_tool('');

                        // 初始化操作按钮
                        init_eval_handle_deal_toolbar(true);

                        Alert("审批成功！");
                    }else{
                        Alert(result.note);
                    }
                }
            });
        }
    });
}

// 部门反馈
function deal_eval_handle_deal_bmfk(){

    // JS对象
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE.PCSLBM;
    obj.PCFLBM = EVAL_CASE.PCFLBM;
    obj.PCHDBM = EVAL_CASE.PCHDBM;
    obj.BMSAH = EVAL_CASE.BMSAH;
    obj.FKJL  = $("input[name='rd_eval_handle_deal_bmfk_fkyj']:checked").val();
    if(isNull(obj.FKJL)){
        Alert("请勾选反馈结论！");
        return;
    }
    obj.FKYJ  = GetMarkValue(EVAL_CASE.PCSPBM).replace(/[\r\n]/g,'');
    if(isNull(obj.FKYJ)){
        Alert("请填写反馈意见！");
        return;
    }

    save_doc_file(function () {
        $.ajax({
            url: getRootPath() + "/handle/dealDeptFeedbcak",
            data: { json: JSON.stringify(obj)},
            type: 'post',
            async: true,
            dataType: 'json',
            success: function (result) {

                if (result.status == 200){

                    init_eval_handle_bottom_tool('');

                    // 初始化操作按钮
                    init_eval_handle_deal_toolbar(true);

                    Alert("反馈成功！");
                    $('#win_eval_handle_deal_bmfk').window('close');
                }else{
                    Alert(result.note);
                }
            }
        });
    });

}

// 评查反馈
function deal_eval_handle_deal_pcfk(){

    // JS对象
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE.PCSLBM;
    obj.PCFLBM = EVAL_CASE.PCFLBM;
    obj.PCHDBM = EVAL_CASE.PCHDBM;
    obj.BMSAH = EVAL_CASE.BMSAH;
    obj.FKJL  = $("input[name='rd_eval_handle_deal_pcfk_fkyj']:checked").val();
    if(isNull(obj.FKJL)){
        Alert("请勾选反馈结论！");
        return;
    }

    obj.SFYY = obj.FKJL == "无异议" ? "N" : "Y";
    obj.FKYJ  = GetMarkValue(EVAL_CASE.PCSPBM).replace(/[\r\n]/g,'');
    if(isNull(obj.FKYJ)){
        Alert("请填写反馈意见！");
        return;
    }
    obj.PCLZDLJ = editDocPath; //评查流转单路径，意见提交后，移除评查员书签，审批后不能再修改意见
    obj.PCYYJSQ = "PCYYJ"; //评查员意见书签

    save_doc_file(function () {

        // if(obj.FKJL == "有异议"){
        //     Confirm("确认", "您的反馈意见，将发送给部门负责人，是否确认发送？", function (r) {
        //         if (r) {
        //             send_pcfk_approve_ywbmfzr(obj);
        //         }
        //     });
        // }else{
        //     send_eval_info_pcfk(obj);
        // }


        // 湖北检察官反馈到评查员，不走部门反馈
        send_eval_info_pcfk(obj);
    });
}

// 送审到业务部门负责人
function send_pcfk_approve_ywbmfzr(obj) {

    // 案件列表DataGrid初始化
    $('#grid_eval_handle_deal_ywbmfzr').datagrid({
        width:'460px',
        height: '280px',
        fitColumns: true,
        singleSelect: true,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        rownumbers: true,
        url: getRootPath()+'/handle/getYwbmfzr',
        queryParams : {
            spjsbm : ""
        },
        idField: 'ID',
        columns: [[
            { field: 'ID', title: '唯一标示', hidden: true },
            {field: 'ck', title: '复选框', checkbox: true, width: 80},
            { field: 'YDWBM', title: '单位编码', hidden: true},
            { field: 'YDWMC', title: '单位名称', width: 120},
            { field: 'MC', title: '姓名', width: 100},
            { field: 'GH', title: '工号', hidden: true},
            { field: 'YJSMC', title: '角色',  width: 100 }
        ]]
    });

    // 提交
    $("#btn_eval_handle_deal_ywbmfzr_confirm").unbind("click");
    $("#btn_eval_handle_deal_ywbmfzr_confirm").bind("click", function () {

        var row = $("#grid_eval_handle_deal_ywbmfzr").datagrid("getSelected");
        if(!row){
            Alert("请选择审批人员！");
            return;
        }

        obj.SPRDWBM = row.YDWBM;
        obj.SPRGH = row.GH;
        obj.SPRXM = row.MC;
        send_eval_info_pcfk(obj);
    });

    // 提交
    $("#btn_eval_handle_deal_ywbmfzr_cancle").unbind("click");
    $("#btn_eval_handle_deal_ywbmfzr_cancle").bind("click", function () {
        $('#win_eval_handle_deal_ywbmfzr').window('close');
    });

    // 弹出送审窗口
    $('#win_eval_handle_deal_ywbmfzr').window('open');
}

// 提交反馈信息
function send_eval_info_pcfk(obj) {
    $.ajax({
        url: getRootPath() + "/handle/dealFeedbcak",
        data: { json: JSON.stringify(obj)},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {

            if (result.status == 200){

                init_eval_handle_bottom_tool('');

                // 初始化操作按钮
                init_eval_handle_deal_toolbar(true);

                Alert("评查反馈成功！");
                try{
                    $('#win_eval_handle_deal_ywbmfzr').window('close');
                }catch(e){
                }
            }else{
                Alert(result.note);
            }
        }
    });
}
$("#mb_eval_handle_btn_more").hover(function () {
    $("#mm_eval_handle_btnlist").css("left","1700px")
},function () {

});

function addHoverType() {
    $("#zlpc_pcxxys_id>label").hover(function () {
        $(this).parent("#zlpc_pcxxys_id").siblings(".zlpc_pcxx_hideys").css("display","block");
    },function () {
        $(this).parent("#zlpc_pcxxys_id").siblings(".zlpc_pcxx_hideys").css("display","none");
    });
}