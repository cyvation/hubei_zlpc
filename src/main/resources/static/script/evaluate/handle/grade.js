/*
 * 该评查分数编辑页面，仅适用于三级结构
 * 四级及以上级结构修改地方：
 * Line72:增加表格列。
 * Line194:增加合并单元格算法，及修改前面部分的列合并。
 */

var is100score = "Y";
var isDisplayTooltip = "N";
var FzPrecision = 2;
var tagScoreChange = false;

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

    // 控件初始化及初始数据加载
    handle_grade_marks_init();

    // 保存事件
    init_eval_bottom_save_pcjg();

    // 加载评查项分类列表
    load_eval_info_grade();

    // 间隔3分钟，自动保存
    setTimeout("autoSaveDocument()", 180000);

});


// 界面标签样式设置及事件绑定
function eval_info_marksInit() {

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
        $('.redio_click_no').removeClass('redio_click_no');
        $(this).parent().addClass('redio_click_no');

    });

    // 评查结果单选框选中样式事件
    $("#input_radio_pcjg").unbind("click");
    $("#input_radio_pcjg").bind("click", function () {
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

    // 评查办理（可修改评查内容及结果）
    if (EVAL_CASE.PCCZLX == "1") {
        // 显示评分表操作工具
        init_eval_handle_grade_tools("1");
    } else {
        // 隐藏评分表操作工具
        init_eval_handle_grade_tools("-1");
    }
}

// 评查案件信息初始化
function init_eval_info_detail() {

    // 评查信息初始化
    $('#span_handle_grade_ajmc,#win_span_handle_grade_eval_ajmc').text(EVAL_CASE.AJMC);
    $('#span_eval_handle_eval_cbr,#win_span_eval_handle_eval_cbr').text(EVAL_CASE.BPC_MC);
    $('#span_eval_handle_eval_pcr,#win_span_eval_handle_eval_pcr').text(EVAL_CASE.PCR_MC);
    $('#span_eval_handle_eval_pcsah,#win_span_eval_handle_eval_pcsah').text(EVAL_CASE.PCSAH);
    $('#span_eval_handle_eval_pcsj,#win_span_eval_handle_eval_pcsj').text(sjzh(EVAL_CASE.CJSJ));
    $('#span_eval_handle_eval_ay,#win_span_eval_handle_eval_ay').text(EVAL_CASE.AY);
    $('#collPcaj').val(EVAL_CASE.AJMC);
    $('#collPcah').val(EVAL_CASE.PCSAH);
}

// 展示评查案卡浮动面板
function show_eval_info_pcak_area() {
    $("#evalinfo_position_one").show();
    $("#evalinfo_position_two").hide();
    $(".center_right_box").removeClass('center_right_box_click');
    $(".center_right_box:first-child").addClass('center_right_box_click');
    $('.center_right_box_sj').removeClass('center_right_box_sj');
    $(".center_right_box:first-child").children('div').addClass('center_right_box_sj');
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

    // 处理结果
    load_cljg_pcjg('9101');
    // 评查结果
    load_cljg_pcjg('9102');
    // 评查结论
    $("#txt_eval_info_pcjl_bz").val(EVAL_CASE.SM);
    $("#lbl_handle_grade_value").text(EVAL_CASE.PCJG);
    // 初始化卷宗文件树
    init_eval_info_docfiles();

    // 加载评查卷宗
    load_tree_eval_doc_files();

}

// 控件初始化及初始数据加载
function handle_grade_marks_init() {

    // 自动评查
    $('#btn_handle_grade_auto').linkbutton({
        onClick: function () {
            auto_grade_score();
        }
    });

    // 保存
    $('#div_pcnl_pcxx_bc').linkbutton({
        onClick: function () {
            save_grade_score();
        }
    });

    // 评查项点击监听事件
    addClickListener();

    // 评分表初始化
    $('#grid_handle_grade_list').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        //  rownumbers: true,  <!-- 是否显示序列号注释！！！！ -->
        singleSelect: true,
        idField: 'pcxbm',
        loadMsg: '数据加载中，请稍后...',
        toolbar: $('#tool_handle_grade'),
        pagination: false,
        //method:'get',
        rowStyler: function(index,row){
            if (row.listprice>80){
                return 'background-color:#6293BB;color:#fff; border:1px;';
            }
        },
        columns: [
            [
                { field: 'fzGd', title: '<span class=\'headPCTitle\' >分值</span>', width: 75, align: 'center', height: '30px', hidden:true},
                {field: 'pcxmc', title: '<span class=\'headPCTitle\'>评查项内容及标准</span>', width: 450,height:'30px',
                    styler: function (value, row, index) {
                        return "padding:8px 0px 8px 0px;"
                    },
                    formatter: function (value, row, index) {
                        return "<div id='tdPCLine" + index + "' style='height:100%'>" + add_copy_function(index) + (value == "" ? "&nbsp;&nbsp;" : value) + "</div>";
                    }
                },
                { field: 'pcjg', title: '<span class=\'headPCTitle\'>问题</span>', width: 75,  align: 'center', height: '30px',
                    // editor: { type: 'checkbox', options: { height: 33, precision: FzPrecision} },
                    // styler: function (value, row, index) {
                    //     return "text-align:center"
                    // },
                    formatter:function (value,row,index) {
                        var data;
                        if(value >= 1){
                            data = '<div class="img_cancel_checkbox" ><input class="input_ok_checkbox" type="checkbox"  checked="checked" onclick="click_handle_grade_checkbox_pcx(\''+index+'\',this)" ></div>';
                        }else {
                            data= '<div class="img_ok_checkbox" ><input class="input_ok_checkbox" type="checkbox"   onclick="click_handle_grade_checkbox_pcx(\''+index+'\',this)" ></div>';
                        }
                        return data
                    }
                },
                { field: 'pcyj', title: '<span class=\'headPCTitle\' >具体理由</span>', width: 230,height: '100%',
                    editor: { type: 'textbox', options: { height: 65, multiline: 'true'} }//,
                    /*styler: function (value, row, index) {
                        return "height:100%"
                    },*/

                }//,
                /*{ field: 'action', title: '<span class=\'headPCTitle\'>操作</span>', width: 75, align: 'center',height: '30px',
                    formatter: function (value, row, index) {
                        var e = '<a href="#" style="color: #145bae"  onclick="alert_win_handle_grade_adjust(\'' + index + '\')">调整</a> ';
                        return !isNull(row.zdpccx) ? e : "";
                    }
                }*/
            ]
        ],
        onLoadSuccess: grid_handle_grade_list_load_success,
        onClickCell: grid_handle_grade_list_click_cell
    });

}

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
        if (EVAL_CASE.PCJDBH >= '011' && wjlx == PCLZDLX){
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

// 动态获取处理结果/评查结果
function load_cljg_pcjg(bm) {
    $.ajax({
        type: 'get',
        async: false,
        url: getRootPath() + '/common/getDataDictionaryByLBBM?lbbm='+bm,
        dataType: 'json',
        success: function(data) {
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
                    $('#div_load_Cljg').append(html)
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
                    $('#div_load_Pcjg').append(html);
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

// 判断每个选项面板是否显示
function toggleShow() {
    var sw = $(".switch");
    for (var j = 0; j < sw.length; j++) {
        var doms = sw.eq(j).siblings(".radio");
        for (var i = 0; i < doms.length; i++) {
            var no = doms.eq(i).attr('box-no');
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
    };

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


    // 评查预览
    $("#pcbl_pcxx_top_pcyl").unbind( "click" );
    $("#pcbl_pcxx_top_pcyl").bind("click", function () {
        grid_handle_grade_list_load_pckyl();
        $('#win').window('open');
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

                    send_eval_handle_deal_approve("20");
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

            // 第一次审批，不显示继续送审按钮
            if (EVAL_CASE.PCSPBM.indexOf("000001") >= 0) {
                $('#btn_eval_handle_deal_pcsp_sendApp').css('display', 'none');
            } else {
                $('#btn_eval_handle_deal_pcsp_sendApp').css('display', '');
                $("#btn_eval_handle_deal_pcsp_sendApp").unbind( "click" );
                $("#btn_eval_handle_deal_pcsp_sendApp").bind("click", function () {
                    deal_eval_handle_deal_approve('1');
                });
            }

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
                $('#btn_eval_handle_deal_pcsp_sendApp').css('display', '');
                $('#btn_eval_handle_deal_pcsp_confirm').css('display', '');
                $('#btn_eval_handle_deal_pcsp_save').css('display', '');
            }
            if (advice != "意见" && advice != "退回"){
                SetBookmarkValue((EVAL_CASE.PCCZLX == "2" ? "SP" : "") + EVAL_CASE.PCSPBM, advice + String.fromCharCode(10));
            }
        }
    });
}

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
            callback();
        }
        //Alert("修改评查报告成功。");
    } else {
        Alert("修改评查报告失败。" + fileresult);
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

// 评查案件退回
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

        if(obj.FKJL == "有异议"){
            Confirm("确认", "您的反馈意见，将发送给部门负责人，是否确认发送？", function (r) {
                if (r) {
                    send_pcfk_approve_ywbmfzr(obj);
                }
            });
        }else{
            send_eval_info_pcfk(obj);
        }
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
        $("#mm_eval_handle_btnlist").css("left", "1700px")
    }, function () {

    }
);

// 加载评查项分类列表
function load_eval_info_grade() {

    $('#cbt_case_info_pcgfxl').combotree({
        height:'27px',
        width:'250px',
        url: getRootPath()+'/pcx/getPcxFl',
        method:'get',
        queryParams : {
            pcslbm: EVAL_CASE.PCSLBM
        },
        valueField: 'id',
        textField: 'text',
        loadFilter:function (data,parent) {
            if(data.code==200){
                var dataSource= eval(data.data);
                return dataSource;
            }else {
                return [];
            }
        },
        onLoadSuccess: function (row, data) {
            if (data == null)
                return;

            // 查找根节点
            var rootNodes = $('#cbt_case_info_pcgfxl').combotree('tree').tree('getRoots');
            if (rootNodes.length > 0) {
                var firstNode = get_first_node(rootNodes[0]);
                if (firstNode) {
                    $('#cbt_case_info_pcgfxl').combotree('setValue', firstNode.id);
                }
            }
        },
        onChange: function (newValue, oldValue) {

            // 获取选中节点自定义数据
            var tree = $('#cbt_case_info_pcgfxl').combotree('tree'); // 获取树对象
            var node = tree.tree('find', newValue); // 查找节点
            var flInfo = new Object();
            flInfo.pcxflbm = node.attributes.pcxflbm;

            if (tagScoreChange) {
                Confirm("确认", "有评查项分值改动，是否保存？", function (r) {
                    if (r) {
                        var result = save_grade_score();
                        if (!result) {
                            return;
                        }
                    } else {
                        // 重置问题数
                        reset_handle_grade_problems();
                    }
                    load_grid_handle_grade_list(flInfo);
                    tagScoreChange = false;
                });
            } else {
                load_grid_handle_grade_list(flInfo);
            }

        }
    });
}

// 获取评查类容表格中的数据
function load_grid_handle_grade_list(caseInfo) {

    var obj = new Object();
    obj.pcslbm= EVAL_CASE.PCSLBM;
    obj.pcxflbm= caseInfo.pcxflbm;
    $.ajax({
        url:getRootPath() + '/pcx/getPcxByPcxflbmAndPcslbm',
        data: obj,
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (result) {
            if(result.code==200){
                $('#grid_handle_grade_list').datagrid('loadData',result.data);

                // 缓存当前评查项分类下问题数
                var node = $('#cbt_case_info_pcgfxl').combotree('tree').tree('find', caseInfo.pcxflbm);
                var attributes = node.attributes;
                attributes.pcwts = convertToInt(result.message);
                attributes.ypcwts = convertToInt(result.message);
                $('#cbt_case_info_pcgfxl').combotree('tree').tree('update', {
                    target: node.target,
                    attributes: attributes
                });
            }else {
                return [];
            }
        }
    });
}

// 评查项分类树第一个叶节点
function get_first_node(node) {

    var result = node;

    // 查找根节点
    var nodes = $('#cbt_case_info_pcgfxl').combotree('tree').tree('getChildren', node.target);
    if (nodes.length > 0) {
        result = get_first_node(nodes[0]);
    }

    return result;
}

// 勾选样式事件
function click_handle_grade_checkbox_pcx(index,event) {
    if(event.checked){
        event.parentNode.classList.add('img_cancel_checkbox');

    }else {
        event.parentNode.classList.add('img_ok_checkbox');
        event.parentNode.classList.remove('img_cancel_checkbox');
    }

    // 更新评查项结果
    $('#grid_handle_grade_list').datagrid('updateRow',{
        index: index,
        row: {
            pcjg: event.checked ? '1' : '0'
        }
    });
    // 激活评查理由输入框
    grid_handle_grade_list_click_cell(index, '', '');
    // 更新当前问题数
    update_handle_grade_problems();

    // 记录变更状态
    tagScoreChange = true;
}

// 初始化评查结论保存按钮事件
function init_eval_bottom_save_pcjg() {
    $("#div_pcxx_center_left_bc").unbind("click");
    $("#div_pcxx_center_left_bc").bind("click", function () {
        // 评查结论
        var obj = new Object();
        obj.PCSLBM = EVAL_CASE.PCSLBM;
        var cljg = $("input[name='rd_eval_info_pcjl_cl']:checked").val();
        obj.CLJG = isNull(cljg) ? "" : cljg;
        var pcjl = $("input[name='rd_eval_info_pcjl_jg']:checked").val();
        obj.PCJL = isNull(pcjl) ? "" : pcjl;
        obj.SM = $("#txt_eval_info_pcjl_bz").val();

        $.ajax({
            url: getRootPath() + "/pcx/savePcjl",
            data: obj,
            type: 'post',
            async: true,
            dataType: 'json',
            success: function (result) {

                if (result.code == 200){

                    EVAL_CASE.CLJG = obj.CLJG;
                    EVAL_CASE.PCJL = obj.PCJL;
                    EVAL_CASE.SM = obj.SM;
                    Alert("提交成功！");
                }else{
                    Alert(result.note);
                }
            }
        });
    });
}

// 保存评查分数
function save_grade_score() {
    var result = false;
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    var array = new Array();
    var total_pcx = 0;

    $('#btn_handle_grade_save').linkbutton('disable');
    ShowProgress();
    for (var i = 0; i < rowDatas.length; i++) {

        // 评查扣分
        var score = rowDatas[i].pcjg; //$(".input_ok_checkbox")[i].checked ? '1' : '0';

        // 扣分原因
        var editor_reason = $('#grid_handle_grade_list').datagrid('getEditor', {index: i, field: 'pcyj'});
        var pcly = editor_reason ? $(editor_reason.target).textbox('getValue') : rowDatas[i].pcyj;
        if (score > 0 && isNull(pcly)) {
            Alert("第" + (i + 1) + "行需填写具体扣分原因。");
            return result;
        }

        // JS对象
        var obj = new Object();
        obj.pcslbm = EVAL_CASE.PCSLBM;
        // obj.BMSAH = EVAL_CASE.BMSAH;
        obj.pcxbm = rowDatas[i].pcxbm;
        obj.pcjg = score;
        obj.pcyj = pcly;
        array[total_pcx++] = obj;
    }
    var pcxFlVo = new Object();
    pcxFlVo.pcxList = array;

    // 保存评查数据
    $.ajax({
        type: 'POST',
        url: getRootPath() + "/pcx/savePcx",
        data: JSON.stringify(pcxFlVo),
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {

                // 更新问题数
                var obj = new Object();
                obj.PCSLBM = EVAL_CASE.PCSLBM;
                obj.PCJG =$("#lbl_handle_grade_value").text();
                $.ajax({
                    url: getRootPath() + "/pcx/savePcjl",
                    data: obj,
                    type: 'post',
                    async: true,
                    dataType: 'json',
                    success: function (result) {

                        if (result.code == 200){

                            EVAL_CASE.PCJG = obj.PCJG;
                            $('#btn_handle_grade_save').linkbutton('enable');
                            CloseProgress();
                            Alert("保存成功！");
                            tagScoreChange = false;
                        }else{
                            $('#btn_handle_grade_save').linkbutton('enable');
                            CloseProgress();
                            Alert(result.note);
                        }
                    }
                });

            } else {
                $('#btn_handle_grade_save').linkbutton('enable');
                CloseProgress();
                Alert(result.code);
            }
        }
    });
    return true;
}

// 获取所有自动评查项
function get_grid_handle_grade_list_auto_item() {

    var array = new Array();
    var total = 0;
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    // 获取所有自动评查项
    for (i = 0; i < rowDatas.length; i++) {
        if (isNull(rowDatas[i].zdpccx) || rowDatas[i].zdpccx.length <= 0)
            continue;

        // JS对象
        var obj = new Object();
        obj.pclbbm = EVAL_CASE.PCHDBM;
        obj.pcslbm = EVAL_CASE.PCSLBM;
        obj.bmsah = EVAL_CASE.BMSAH
        obj.mxkf = "1";//rowDatas[i].fzGd;
        obj.pcxbm = rowDatas[i].pcxbm;
        obj.pcxmc = rowDatas[i].pcxmc;
        obj.pcxzf = "1";//rowDatas[i].fzGd;
        obj.zdpccxbm = rowDatas[i].zdpccx;
        obj.index = i;
        array[total++] = obj;
    }

    return array;
}

// 初始化扣分原因输入框(isAdd:是否显示常用问题弹出框按钮)
function init_editor_reason(index, row, isAdd) {

    // 扣分原因输入框
    var editor_reason = $('#grid_handle_grade_list').datagrid('getEditor', { index: index, field: 'pcyj' });
    if(!(editor_reason && editor_reason.target)){
        return;
    }
    $(editor_reason.target).textbox({
        disabled: false,
        icons: [{
            iconCls: 'icon-add',
            handler: function (e) {
                if (isAdd) {
                    alert_win_handle_grade_problem(index);
                }
            }
        }]
    });

    return editor_reason;
}

// 常见问题
function alert_win_handle_grade_problem(index) {

    // 列表初始化
    $('#grid_win_handle_grade_problem').datagrid({
        fitColumns: true,
        striped: true,
        nowrap: false,
        rownumbers: true,
        idField: 'dm',
        pagination: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        method:'get',
        url: getRootPath() + '/pcx/getXtDmFldmBy',
        queryParams : {
            lbbm: "9104"
        },
        loadMsg: '数据加载中，请稍后...',
        pagination: false,
        columns: [[
            { field: 'ck', title: '复选框', checkbox: true },
            { field: 'mc', title: '常见问题名称', width: 300 }
        ]],
        loadFilter:function (data,parent) {
            if(data.code==200 && !isNull(data.data)){
                var dataSource= JSON.parse(data.data);
                return dataSource;
            }else {
                return { total: 0, rows: [] };
            }
        }
    });

    // 确认
    $('#btn_win_handle_grade_problem_confirm').linkbutton('enable');
    $('#btn_win_handle_grade_problem_confirm').linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            var oldval = '';
            var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
            var editor = $('#grid_handle_grade_list').datagrid('getEditor', { index: index, field: 'pcyj' });
            if (editor) {
                oldval = $(editor.target).textbox('getValue');
                var newval = '';
                var rows = $('#grid_win_handle_grade_problem').datagrid('getChecked');
                for (var i = 0; i < rows.length; i++) {
                    newval += rows[i].mc;
                }
                $(editor.target).textbox('setValue', oldval + newval);
            }
            $('#win_handle_grade_problem').window('close');
        }
    });

    // 取消
    $('#btn_win_handle_grade_problem_cancel').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#win_handle_grade_problem').window('close');
        }
    });

    $('#win_handle_grade_problem').window('open');
}

// 自动评查调整
function alert_win_handle_grade_adjust(index) {

    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    // 选中当前行
    grid_handle_grade_list_click_cell(index, '', '');

    // 获取评查项扣分原因
    var editor_reason = $('#grid_handle_grade_list').datagrid('getEditor', { index: index, field: 'pcyj' });
    $('#txt_win_handle_grade_adjust_kfyy').textbox('setValue', $(editor_reason.target).textbox('getValue'));

    // // 初始化评查项分值
    // $('#txt_win_handle_grade_adjust_reduce').numberbox({
    //     precision: FzPrecision,
    //     min: 0,
    //     max: (rowDatas[index].fzGd) * 1
    // });

    // 获取自动评查内容
    var pcxbm = rowDatas[index].pcxbm;
    $('#txt_win_handle_grade_adjust_zdpcnr').textbox('setValue', '');
    $.get(getRootPath() + '/pcx/getYxPcZdPcByKey', { pcslbm: EVAL_CASE.PCSLBM, pcxbm: pcxbm },
        function (result) {
            if(result != 200){
                return;
            }

            $('#txt_win_handle_grade_adjust_zdpcnr').textbox('setValue', result.data.pcly);
        });

    // 确认
    $('#btn_win_handle_grade_adjust_confirm').linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            //$(editor_score.target).numberbox('setValue', $('#txt_win_handle_grade_adjust_reduce').numberbox('getValue'));
            $(editor_reason.target).textbox('setValue', $('#txt_win_handle_grade_adjust_kfyy').textbox('getValue'));
            $('#win_handle_grade_adjust').window('close');
        }
    });

    // 取消
    $('#btn_win_handle_grade_adjust_cancel').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#win_handle_grade_adjust').window('close');
        }
    });

    $('#win_handle_grade_adjust').window('open');
}

// 评查表（预览）
function grid_handle_grade_list_load_pckyl() {
    // 预览评分表初始化
    $('#grid_handle_pcyl').datagrid({
        fitColumns: true,
        fit: true,
        striped: true,
        nowrap: false,
        rownumbers: true,  <!-- 是否显示序列号注释！！！！ -->
        singleSelect: true,
        idField: 'pcxbm',
        loadMsg: '数据加载中，请稍后...',
         //toolbar: $('#div_win_pcblPcyl'),
        pagination: false,
        rowStyler: function(index,row){
            if (row.listprice>80){
                return 'background-color:#6293BB;color:#fff; border:1px;';
            }
        },
        columns: [[
            {
                field: 'f3flmc',
                title: '<span class=\'headPCTitle\' style=\'font-size:16px\'>项目</span>',
                width: 80,
                rowspan: 1,
                align: 'center',
                halign: 'center',
                formatter: function (value, row, index) {
                    return OneWordLine(value, '16') //一字一行，字大小为16
                },
                styler: function (value, row, index) {
                    return ' border-right: 1px solid #d6eaf4 !important;border-left: 1px solid #d6eaf4 !important;';
                }
            },
            // {title: '<span class=\'headPCTitle\' style=\'font-size:16px\'></span>', colspan: 3},
            {
                field: 'f2flmc',
                title: '<span class=\'headPCTitle\' style=\'font-size:16px\'>子项</span>',
                width: 60,
                align: 'center',
                formatter: function (value, row, index) {
                    return TwoWordLine(value, '14');  //两字一行，字体大小为14
                },
                styler: function (value, row, index) {
                    return "padding:5px 0px 5px 0px; border-right: 1px solid #d6eaf4 !important;"
                }
            },
            {
                field: 'f1flmc', width: 40, align: 'center',hidden: true,
                formatter: function (value, row, index) {
                    return TwoWordLine(value, '14');  //两字一行，字体大小为14
                },
                styler: function (value, row, index) {
                    return "padding:5px 0px 5px 0px; border-right: 1px solid #d6eaf4 !important;"
                }
            },
            //Code1:在这里可以继续增加子项，添加列。
            {
                field: 'pcxmc',
                title: '<span class=\'headPCTitle\'  style=\'font-size:16px;display: inline-block;width: 100%;  text-align: center;\'>具体内容及扣分标准</span>',
                width: 430,
                styler: function (value, row, index) {
                    return "padding:8px 0px 8px 0px;"
                }
            },
            {
                field: 'fz_gd',
                title: '<span class=\'headPCTitle\' style=\'font-size:16px\'>分值</span>',
                width: 75,
                rowspan: 1,
                align: 'center',
                halign: 'center',
                hidden: true,
                formatter: function (value, row, index) {
                    return TwoWordLine(value, '14');  //两字一行，字体大小为14
                }
            },
            {
                field: 'pcjg',
                title: '<span class=\'headPCTitle\' style=\'font-size:16px\'>扣分</span>',
                width: 75,
                rowspan: 1,
                align: 'center',
                halign: 'center',
                formatter: function (value, row, index) {
                    var data;
                    if (value >= 1) {
                        data = '<div class="img_cancel_checkbox" ><input class="input_ok_checkbox" type="checkbox"  checked="checked" ></div>';
                    } else {
                        data = '<div class="img_ok_checkbox" ><input class="input_ok_checkbox" type="checkbox" ></div>';
                    }
                    return data
                }
            },
            {
                field: 'pcyj', title: '<span class=\'headPCTitle\' >具体理由</span>', width: 230, rowspan: 1,paddingRight: 20
            }
            /*{
             field: 'action',
             title: '<span class=\'headPCTitle\' style=\'font-size:16px\'>自动评查<br>调整</span>',
             width: 70,
             align: 'center',
             rowspan: 2,
             formatter: function (value, row, index) {
             var e = '<a href="#" onclick="alert_win_handle_grade_adjust(\'' + index + '\')">调整</a> ';
             return isNull(row.ZDPCCX) ? "" : e;
             }
             }*/
        ]],
        method: 'get',
        url: getRootPath() + '/pcx/getMarkSheet',
        queryParams: {
            pcslbm: EVAL_CASE.PCSLBM
        },
        loadFilter: function (data, parent) {
            if(data.code == 200){
                return JSON.parse(data.data);
            }else {
                return [];
            }
        },
        onLoadSuccess: grid_handle_pcyl_load_success
    });
}

// 加载成功后，计算总分，浮动框。
function grid_handle_grade_list_load_success(data) {
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    var len = rowDatas.length;

    // 初始化计算总分
    computeScore();

    // 浮动框
    bind_grid_handle_grade_list_tooltip(len,rowDatas);
}

// 评分表是否显示浮动框
function bind_grid_handle_grade_list_tooltip(len,rowDatas) {
    if (isDisplayTooltip == 'Y') {
        try {
            for (var i = 0; i < len; i++) {
                $('#tdPCLine' + i).tooltip({
                    position: 'bottom',
                    content: '<div style="padding:5px;width:430px;line-height:18px;color:#000">' + (rowDatas[i].bz == "" ? "&nbsp;" : rowDatas[i].bz) + '</div>',
                    onShow: function () {
                        $(this).tooltip('tip').css({
                            borderColor: '#ff0000',
                            boxShadow: '1px 1px 3px #292929'
                        });
                    },
                    onPosition: function () {
                        $(this).tooltip('tip').css('left', $(this).offset().left);
                        $(this).tooltip('arrow').css('left', 20);
                    }
                });
            }

        }catch (e){

        }
    }
}

// 加载成功后，合并单元格，增加分数输入框change事件，计算总分
function grid_handle_pcyl_load_success(data) {
    var rowDatas = $('#grid_handle_pcyl').datagrid('getRows');
    var len = rowDatas.length;
    //合并单元格----开始------
    var rowspanF3 = 1;
    var indexF3 = 0;
    var rowspanF2 = 1;
    var colspanF2 = 1;
    var indexF2 = 0;
    var rowspanF1 = 1;
    var indexF1 = 0;

    var sourceF3 = '';
    var sourceF2 = '';
    var sourceF1 = '';
    if (len > 0) sourceF3 = rowDatas[0].f3flmc;
    if (len > 0) sourceF2 = rowDatas[0].f2flmc;
    if (len > 0) sourceF1 = rowDatas[0].f1flmc;
    for (i = 1; i < len; i++) {
        //合并单元格----第一列【项目】------
        if (sourceF3 == rowDatas[i].f3flmc) {
            rowspanF3++;
        }
        else {
            $(this).datagrid('mergeCells', {
                index: indexF3,
                field: 'f3flmc',
                rowspan: rowspanF3
            });
            indexF3 = i;
            sourceF3 = rowDatas[i].f3flmc;
            rowspanF3 = 1;
        }
        //合并单元格----第二列【子项】--如子项为空，列合并----
        //Code2:修改这里的colspan值，增加判断。
        if (sourceF2.length > 0 && sourceF2 == rowDatas[i].f2flmc) {
            rowspanF2++;
        }
        else {
            //if(sourceF2.length == 0 && rowDatas[indexF2].f1flmc.length == 0){
            //    rowDatas[indexF2].f2flmc = rowDatas[indexF2].PCX_MC;
            //    colspanF2 = 3;
            //}
            if (rowDatas[indexF2].f1flmc.length == 0)
                colspanF2 = 2;
            $(this).datagrid('mergeCells', {
                index: indexF2,
                field: 'f2flmc',
                rowspan: rowspanF2,
                colspan: colspanF2
            });
            indexF2 = i;
            sourceF2 = rowDatas[i].f2flmc;
            rowspanF2 = 1;
            colspanF2 = 1;
        }
        //合并单元格----第三列【子项】------
        //Code3:修改这里的colspan值，增加判断。
        if (sourceF1 == rowDatas[i].f1flmc) {
            rowspanF1++;
        }
        else {
            $(this).datagrid('mergeCells', {
                index: indexF1,
                field: 'f1flmc',
                rowspan: rowspanF1
            });
            indexF1 = i;
            sourceF1 = rowDatas[i].f1flmc;
            rowspanF1 = 1;
        }
        //Code4:继续增加合并事件。
    }
    //最后一次循环后需要做最后的合并处理。
    $(this).datagrid('mergeCells', {
        index: indexF3,
        field: 'f3flmc',
        rowspan: rowspanF3
    });
    if (rowDatas[indexF2].f1flmc.length == 0)
        colspanF2 = 2;
    $(this).datagrid('mergeCells', {
        index: indexF2,
        field: 'f2flmc',
        rowspan: rowspanF2,
        colspan: colspanF2
    });
    $(this).datagrid('mergeCells', {
        index: indexF1,
        field: 'f1flmc',
        rowspan: rowspanF1
    });
    //Code5:继续增加收尾处理合并。
    //合并单元格----结束------

    //    //让每行处于编辑状态
    //    for (i = len - 1; i >= 0; i--) {
    //        $('#grid_handle_grade_list').datagrid('selectRow', i)
    //							.datagrid('beginEdit', i);
    //        editIndex = i;
    //    }
    //    $('#grid_handle_grade_list').datagrid('clearSelections');

    //初始化计算总分
    //computeScore();

    // 评分表是否显示浮动框
    /*if (isDisplayTooltip == 'Y') {
        for (i = 0; i < len; i++) {
            $('#tdPCLine' + i).tooltip({
                position: 'bottom',
                content: '<div style="padding:5px;width:430px;line-height:18px;color:#000">' + (rowDatas[i].bz == "" ? "&nbsp;" : rowDatas[i].bz) + '</div>',
                onShow: function () {
                    $(this).tooltip('tip').css({
                        borderColor: '#ff0000',
                        boxShadow: '1px 1px 3px #292929'
                    });
                },
                onPosition: function () {
                    $(this).tooltip('tip').css('left', $(this).offset().left);
                    $(this).tooltip('arrow').css('left', 20);
                }
            });
        }
    }*/
    //$('#grid_handle_pcyl').datagrid('fixRowHeight');
}

// datagrid单元格点击事件
function grid_handle_grade_list_click_cell(index, field, value) {
    if (field == "pcxmc")
        return;

    // 激活当前行为编辑行
    $('#grid_handle_grade_list').datagrid('selectRow', index);
    $('#grid_handle_grade_list').datagrid('endEdit', index);
    $('#grid_handle_grade_list').datagrid('beginEdit', index);

    // 初始化扣分原因输入框
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    init_editor_reason(index, rowDatas[index], true);
}

var current_total = 0; //当前已自动评查项总数
var total = 0; //评分表中自动评查项总数
// 自动评查
function auto_grade_score() {

    $('#win_handle_grade_save').window('open');

    $("#lbl_win_handle_grade_save_info").html('正在检测自动评查项...');

    // 获取所有自动评查项
    var array = get_grid_handle_grade_list_auto_item();

    $("#lbl_win_handle_grade_save_info").html('检测到 ' + array.length + ' 条自动评查项...');
    if (array == null || array.length <= 0) {
        $('#win_handle_grade_save').window('close');
        return;
    }
    $("#lbl_win_handle_grade_save_info").html('开始自动评查...');
    $('#pb_win_handle_grade_save').progressbar('setValue', 0);

    // 遍历执行自动评查程序
    current_total = 0;
    total = array.length;
    for (i = 0; i < total; i++) {
        get_zdpcx_score(array[i]);
    }
}

// 获取自动评查项分数
function get_zdpcx_score(item) {
    var index = item.index;
    // 获取自动评查结果
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/pcx/getZdpccxByPcx',
        data: item,
        //async: false,
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                var data = result.data;
                if (!isNull(data.fz)){
                    tagScoreChange = true;
                    $('#grid_handle_grade_list').datagrid('updateRow',{
                        index: index,
                        row: {
                            pcjg: data.fz
                        }
                    });
                }

                $('#grid_handle_grade_list').datagrid('selectRow', index);
                $('#grid_handle_grade_list').datagrid('endEdit', index);
                $('#grid_handle_grade_list').datagrid('beginEdit', index);
                var editor_reason = $('#grid_handle_grade_list').datagrid('getEditor', {index: index, field: 'pcyj'});
                if ((editor_reason && editor_reason.target)){
                    $(editor_reason.target).textbox({
                        disabled: false,
                        value: (data.fz == 0 || isNull(data.message)) ? "自动评查：符合要求。" : "自动评查：" + data.message,
                        icons: [{
                            iconCls: 'icon-add',
                            handler: function (e) {
                                if (isAdd) {
                                    alert_win_handle_grade_problem(index);
                                }
                            }
                        }]
                    });
                }
            } else {
                //自动评查失败
            }

            // 更新当前问题数
            update_handle_grade_problems();
            ++current_total;
            $("#lbl_win_handle_grade_save_info").html('自动评查进行中，请稍后... ' + current_total + '/' + total);
            if (current_total < total) {
                $('#pb_win_handle_grade_save').progressbar('setValue', current_total * 100 / total);
            } else {
                $('#pb_win_handle_grade_save').progressbar('自动评查完成，共 ' + total + ' 条');
                $('#win_handle_grade_save').window('close');
            }
        },
        error:function () {
            $('#win_handle_grade_save').window('close');
        }
    });
}

// 评查项特效（手动评查、文书、弹出网页、弹出信息）
// 评分表配置时配置特效
function DOPC(param) {
    param = param.replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"');
    param = param.replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"');
    param = param.replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"').replace('$', '\"');
    var pccs = JSON.parse(param);

    if (pccs.OpenType == "手动评查") {
        //该项扣分扣完，填写扣分原因
        Confirm("确认", "确认对该评查项扣分吗？", function (r) {
            if (r) {
                var index = $('#grid_handle_grade_list').datagrid('getRowIndex', EVAL_CASE.PCMBBM + pccs.PCXBH);
                if (index < 0) {
                    Alert("评分表中找不到手动评查项。");
                    return;
                }
                grid_handle_grade_list_click_cell(index, '', '');
                // var ed = $('#grid_handle_grade_list').datagrid('getEditor', { index: index, field: 'pcjg' });
                // $(ed.target).numberbox('setValue', pccs.PCXFZ);
                var ed2 = $('#grid_handle_grade_list').datagrid('getEditor', { index: index, field: 'pcyj' });
                $(ed2.target).textbox('setValue', pccs.PCLY);
            }
        });
    }
    if (pccs.OpenType == "文书") {
        //OpenWS_MBBH = pccs.MBBH;
        //$('#ttBLModule').tabs('select', "案件信息");
        //ExpandWSNodeByBM();
    }
    if (pccs.OpenType == "弹出网页") {
        $('#win_handle_grade_pcxtsxx').window({
            title: '信息提示网页',
            href: pccs.URL,
            width: 930,
            height: 720,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            shadow: true
        });
    }
    if (pccs.OpenType == "弹出信息") {
        $('#win_handle_grade_pcxtsxx').window({
            title: '信息提示',
            width: 930,
            content: "<div style='width:900px;height:680px;'>" + pccs.MSG + "</div>",
            height: 720,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            shadow: true
        });
    }
}

// 通过编码展开节点，并选中文书
function ExpandWSNodeByBM() {
    if (isNull(OpenWS_MBBH)) {
        return;
    }
    if (!$('#tree_caseinfo_doc_files')) return;
    var rootNotes = $('#tree_caseinfo_doc_files').tree('getRoots'), children;
    for (var i = 0; i < rootNotes.length; i++) {
        if (rootNotes[i].id == '-2') {
            //如果匹配到则展开选中节点
            $('#tree_caseinfo_doc_files').tree('select', rootNotes[i].target);
            $('#tree_caseinfo_doc_files').tree('expand', rootNotes[i].target);
        }
        children = $('#tree_caseinfo_doc_files').tree('getChildren', rootNotes[i].tartget);
        for (var j = 0; j < children.length; j++) {
            if (children[j].mbbh == OpenWS_MBBH) {
                //如果匹配到则选中节点，展开父节点
                $('#tree_caseinfo_doc_files').tree('select', children[j].target);
                $('#tree_caseinfo_doc_files').tree('expand', children[j].target);
                var pNode = $('#tree_caseinfo_doc_files').tree('getParent', children[j].target);
                $('#tree_caseinfo_doc_files').tree('expand', pNode.target);
                var ppNode = $('#tree_caseinfo_doc_files').tree('getParent', pNode.target);
                $('#tree_caseinfo_doc_files').tree('expand', ppNode.target);
                //return;
            }
        }
    }
}

// 计算总扣分(先乘100后除100)
function computeScore() {

    // var total = 0; //评分表总分
    // var reduce = 0; //评查项总扣分
    //
    // // 计算评分表总分及评查项总扣分
    // var editor; //评查项扣分输入框
    // var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    // for (i = 0; i < rowDatas.length; i++) {
    //     total = total + parseFloat(rowDatas[i].fzGd) * 100;
    //     editor = $('#grid_handle_grade_list').datagrid('getEditor', { index: i, field: 'pcjg' });
    //     if (editor) {
    //         reduce = reduce + parseFloat($(editor.target).numberbox('getValue') == '' ? '0' : $(editor.target).numberbox('getValue')) * 100;
    //     }
    //     else {
    //         reduce = reduce + parseFloat(rowDatas[i].pcjg == '' ? '0' : rowDatas[i].pcjg) * 100;
    //     }
    // }
    //
    // // 是否总分100
    // if (is100score == 'Y')
    //     total = 100 * 100;
    //
    // // 总得分
    // if(reduce){
    //     $('#lbl_handle_grade_value').html((total - reduce) / 100);
    // }else {
    //     $('#lbl_handle_grade_value').html(0);
    // }
    //
    //
    // // 总分非100（及评分表总分），则显示总扣分
    // if (is100score != 'Y') {
    //     $('#td_handle_grade_value_reduce').css('display', '');
    //     $('#lbl_handle_grade_value_reduce').html(reduce / 100);
    // }
}

// 添加复制功能
function add_copy_function(index) {
    var temp = '<a href="#" onclick="copy_content(' + index + ',0)">复制内容</a> ';
    temp += '<a href="#" onclick="copy_content(' + index + ',1)">复制备注</a>';
    return "";
}

// 复制评查内容（type:1.评查项名称，2.评查项备注）
function copy_content(index, type) {

    // 获取拷贝文本
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    var copyText = type == 1 ? rowDatas[index].sm : rowDatas[index].pcxmc;

    // 赋值给评查项扣分原因输入框
    var editor_reason = $('#grid_handle_grade_list').datagrid('getEditor', { index: index, field: 'pcyj' });
    var oldval = $(editor_reason.target).textbox('getValue');
    $(editor_reason.target).textbox('setValue', oldval + copyText);

    //window.clipboardData.setData("Text",copyText);
}

// 单元格内容换行，一字一行
function OneWordLine(text, fontsize) {
    //return text;
    if(isNull(text))
        return "";
    var len = text.length;
    if (len > 9) return TwoWordLine(text, fontsize);
    var result = text.substring(0, 2);
    for (i = 2; i < len; i += 2) {
        if (i > 5 && i <= 9) {
            result = result + '' + text.substring(i, i + 2);
        }
        else {
            result = result + '<br>' + text.substring(i, i + 2);
        }
    }
    return '<span class="headPCFLTitle" style="font-size:' + fontsize + 'px">' + result + '</span>';
}

// 单元格内容换行，两字一行
function TwoWordLine(text, fontsize) {
    //return text;
    if(isNull(text))
        return "";
    var len = text.length;
    var add = 2;
    if (len > 4 && len < 6) add = 3;
    if (len >= 6 && len < 9) add = 4;
    if (len >= 9) add = 5;
    var result = text.substring(0, add);
    for (i = add; i < len; i += add) {
        result = result + '<br>' + text.substring(i, i + add);
    }
    return '<span class="headPCFLTitle" style="font-size:' + fontsize + 'px">' + result + '</span>';
}

// 更新当前选中分类节点扣分
function update_handle_grade_problems() {
    var reduce = 0; //评查项总扣分

    // 计算评分表总分及评查项总扣分
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    if (rowDatas.length <= 0)
        return;

    for (i = 0; i < rowDatas.length; i++) {
        reduce += convertToInt(rowDatas[i].pcjg); //$(".input_ok_checkbox")[i].checked ? 1 : 0;
    }

    // 缓存当前评查项分类下问题数
    var node = $('#cbt_case_info_pcgfxl').combotree('tree').tree('find', rowDatas[0].pcxflbm);

    // 问题总数更新
    var cnt = $('#lbl_handle_grade_value').text();
    $('#lbl_handle_grade_value').text(convertToInt(cnt) - convertToInt(node.attributes.pcwts) + reduce);

    var attributes = node.attributes;
    attributes.pcwts = reduce;
    $('#cbt_case_info_pcgfxl').combotree('tree').tree('update', {
        target: node.target,
        attributes: attributes
    });

}

// 重置当前选中分类节点扣分
function reset_handle_grade_problems() {
    var reduce = 0; //评查项总扣分

    // 计算评分表总分及评查项总扣分
    var rowDatas = $('#grid_handle_grade_list').datagrid('getRows');
    if (rowDatas.length <= 0)
        return;

    for (i = 0; i < rowDatas.length; i++) {
        reduce += convertToInt(rowDatas[i].pcjg); //$(".input_ok_checkbox")[i].checked ? 1 : 0;
    }

    // 缓存当前评查项分类下问题数
    var node = $('#cbt_case_info_pcgfxl').combotree('tree').tree('find', rowDatas[0].pcxflbm);

    // 问题总数更新
    var cnt = $('#lbl_handle_grade_value').text();
    $('#lbl_handle_grade_value').text(convertToInt(cnt) + convertToInt(node.attributes.ypcwts) - reduce);

    // var attributes = node.attributes;
    // attributes.pcwts = node.attributes.ypcwts;
    // $('#cbt_case_info_pcgfxl').combotree('tree').tree('update', {
    //     target: node.target,
    //     attributes: attributes
    // });
}

// 转换为整形数值
function convertToInt(strIntNum) {
    return isNull(strIntNum) ? 0 : parseInt(strIntNum);
}

// 隐藏评分表操作工具
function init_eval_handle_grade_tools(type) {

    if (type == "-1") {
        $('#div_pcnl_pcxx_bc').css('display', 'none'); //保存（评查内容）
        $('#btn_handle_grade_auto').css('display', 'none'); //自动评查
        $('#div_pcxx_center_left_bc').css('display', 'none'); //保存（处理结果）
    } else {
        $('#div_pcnl_pcxx_bc').css('display', ''); //保存（评查内容）
        $('#btn_handle_grade_auto').css('display', ''); //自动评查
        $('#div_pcxx_center_left_bc').css('display', ''); //保存（处理结果）
    }
}