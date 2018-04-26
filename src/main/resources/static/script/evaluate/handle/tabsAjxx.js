var evalInfo;
var opening_case_doc_file;

$(document).ready(function () {

    // $(".pcbl_ajxx_btn_ul_li").mousemove(function () {
    //     $(this).addClass("pcbl_ajxx_btn_click");
    //     $(this).siblings().removeClass("pcbl_ajxx_btn_click");
    //     $(this).children(".pcbl_ajxx_sjx").show();
    //     $(this).siblings().children(".pcbl_ajxx_sjx").hide();
    //     $(this).children(".pcbl_ajxx_btn_postion").show();
    //     $(this).siblings().children(".pcbl_ajxx_btn_postion").hide()
    // })
    // $(".pcbl_ajxx_btn_postion").mouseout(function () {
    //     $(".pcbl_ajxx_btn_postion").hide()
    // })

    try {
        evalInfo = get_eval_handle_pcxx();
    } catch (e){
        evalInfo = new Object();
        evalInfo.PCSLBM = skip_cs.pcslbm;
        evalInfo.BMSAH = skip_cs.bmsah;
    }

    // 获取关联案件列表
    $('#cbt_case_info_bmsahs').combotree({
        height:'35px',
        width:'296px',
        url: getRootPath()+'/caseinfo/getGlajs',
        queryParams: {
            pcslbm: evalInfo.PCSLBM,
            bmsah: evalInfo.BMSAH
        },
        valueField: 'id',
        textField: 'text',
        onLoadSuccess: function (node, data) {
            $('#cbt_case_info_bmsahs').combotree('setValue', evalInfo.BMSAH);
        },
        onChange: function (newValue, oldValue) {
            //Alert(newValue);

            // 获取选中节点自定义数据
            var tree = $('#cbt_case_info_bmsahs').combotree('tree'); // 获取树对象
            var node = tree.tree('find', newValue); // 查找节点

            var caseInfo = new Object();
            caseInfo.BMSAH = node.attributes.BMSAH;
            caseInfo.AJLBBM = node.attributes.AJLBBM;
            load_case_info(caseInfo);
        }
    });

    // 清理本地存放附件
    try {
        boundObjectForJS.deleteDirectory(LOCAL_PATH);
    } catch (e) {

    }

    // 关闭按钮
    $('#btn_win_caseinfo_doc_files_close').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#win_caseinfo_doc_files').window('close');
        }
    });

});

// 案件信息
function load_case_info(caseInfo) {


    // 文书卷宗列表
    load_tree_caseinfo_doc_files(caseInfo);

    // 电子卷宗列表
    // load_tree_caseinfo_dossier_files(caseInfo);

    // 案卡
    load_case_card_list(caseInfo);

    // 过程
    load_process_card_list(caseInfo);
    //流程监控
    load_flow_card_list(caseInfo);
}

// 文书卷宗列表
function load_tree_caseinfo_doc_files(caseInfo) {

    $("#tree_caseinfo_doc_files").tree({
        lines: true,
        animate: true,
        data: [{
            id:'1',
            text: '文书卷宗',
        },{
            id:'2',
            text: '电子卷宗'
        }],
        loadFilter: function (data) {
            if (data || !isNull(data)) {
                return data;
            } else {
                return [];
            }
        },
        onLoadSuccess: function (node, data) {
            if (data == null)
                return;
            //Alert(1);
        },
        onLoadError: function (arguments) {
            //Alert(arguments);
        },
        onClick: function(node){
            var nodes = $('#tree_caseinfo_doc_files').tree('getRoot',node.target);
            if(nodes.id=='1'){
                if(!node.attributes)
                    return;
                var bmsah = node.attributes.BMSAH;
                var wjlx = node.attributes.WJLX;
                var wsbh = node.attributes.ID;
                var wjlj = node.attributes.WJLJ;
                if (isNull(wjlj)){
                    return;
                }

                // 打开文书
                if (wjlx == "1") {
                    open_doc_file(bmsah, wjlj, wsbh, '1')
                }
                // 下载附件到本地并用本机默认程序打开
                else {
                    open_doc_file(bmsah, wjlj, wsbh, '2')
                }
            }else {
                var info = node.id.split("}@{");

                if (info != null && info.length > 2) {
                    var dwbm = node.attributes.DWBM;
                    var bmsah = node.attributes.BMSAH;
                    var jzbh = node.attributes.ID;
                    open_dossier_file(dwbm, bmsah, jzbh);
                }
            }

        },
        onDblClick: function (node) {
            if (node.state == "closed") {
                $("#tree_caseinfo_dossier_files").tree('expand', node.target);
            }else {
                $("#tree_caseinfo_dossier_files").tree('collapse', node.target);
            }
        }
    });
    var data={
        pcslbm: evalInfo.PCSLBM,
        bmsah: caseInfo.BMSAH
    }
    //1:文书卷宗  2：电子卷宗
    treeAppend('/caseinfo/getDocFiles','#tree_caseinfo_doc_files',data,1);
    treeAppend('/caseinfo/getDossierFiles','#tree_caseinfo_doc_files',data,2)


}

// 电子卷宗列表
function load_tree_caseinfo_dossier_files(caseInfo) {

    $("#tree_caseinfo_dossier_files").tree({
        lines: true,
        animate: true,
        url: getRootPath()+'/caseinfo/getDossierFiles',
        queryParams: {
            pcslbm: evalInfo.PCSLBM,
            bmsah: caseInfo.BMSAH
        },
        loadFilter: function (data) {
            if (data || !isNull(data)) {
                return data;
            } else {
                return [];
            }
        },
        onLoadSuccess: function (node, data) {
            if (data == null)
                return;
            //Alert(1);
        },
        onLoadError: function (arguments) {
            //Alert(arguments);
        },
        onClick: function(node){

            var info = node.id.split("}@{");

            if (info != null && info.length > 2) {
                current_node = node;
                var dwbm = node.attributes.DWBM;
                var bmsah = node.attributes.BMSAH;
                var jzbh = node.attributes.ID;
                open_dossier_file(dwbm, bmsah, jzbh);
            }
        },
        onDblClick: function (node) {

            if (node.state == "closed") {
                $("#tree_caseinfo_dossier_files").tree('expand', node.target);
            }else {
                $("#tree_caseinfo_dossier_files").tree('collapse', node.target);
            }
        }
    });

}

// 案卡
function load_case_card_list(caseInfo) {

    // 获取案卡配置
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/caseinfo/getCaseCards',
        data: { pcslbm: evalInfo.PCSLBM, ajlbbm: caseInfo.AJLBBM, bmsah: caseInfo.BMSAH },
        dataType: "json",
        success: function (data) {
            $('#gaxx_akList').children("li").remove();
            $('#gaxx_akList div').remove();
            if (data == null)
                return;

            // 遍历生成案卡项
            for (var i = 0; i < data.length; i++) {
                var li = $("<li></li>");
                var a = $("<a></a>");
                // 添加点击查看详情事件
                li.attr("onclick", "show_case_card_info(\"" + data[i].CardName + "\")");
                a.append(data[i].Title);
                li.append(a);
                var div = $("<div></div>");
                div.attr("class", "divider");
                $('#gaxx_akList').append(li);
                $('#gaxx_akList').append(div);

            }

            show_case_card_info(data[0].CardName);
        }
    });

}

// 过程
function load_process_card_list(caseInfo) {

    // 获取过程项配置
    $.ajax({
        type: 'GET',
        url: getRootPath()+'/caseinfo/getProcessCards',
        data: { pcslbm: evalInfo.PCSLBM, bmsah: caseInfo.BMSAH, ajlbbm: caseInfo.AJLBBM },
        dataType: "json",
        success: function (data) {
            $('#gaxx_gcList').children("li").remove();
            $('#gaxx_gcList div').remove();
            // 遍历生成过程项
            if (data == null)
                return;

            for (var i = 0; i < data.length; i++) {
                var li = $("<li></li>");
                var a = $("<a></a>");
                // 添加点击查看详情事件
                li.attr("onclick", "show_process_card_info(\"" + data[i].CardName + "\")");
                a.append(data[i].Title);
                li.append(a);
                var div = $("<div></div>");
                div.attr("class", "divider");
                $('#gaxx_gcList').append(li);
                $('#gaxx_gcList').append(div);
            }
        }
    });

}

//流程
function load_flow_card_list(caseInfo){
    // 获取流程项配置
    $.ajax({
        type: 'GET',
        // url: getRootPath()+'/caseinfo/getFlowCards',
        data: { pcslbm: evalInfo.PCSLBM, bmsah: caseInfo.BMSAH, ajlbbm: caseInfo.AJLBBM },
        dataType: "json",
        success: function (data) {
            $('#gaxx_gcList').children("li").remove();
            $('#gaxx_gcList div').remove();
            // 遍历生成过程项
            if (data == null)
                return;

            for (var i = 0; i < data.length; i++) {
                var li = $("<li></li>");
                var a = $("<a></a>");
                // 添加点击查看详情事件
                li.attr("onclick", "show_flow_card_info(\"" + data[i].CardName + "\")");
                a.append(data[i].Title);
                li.append(a);
                var div = $("<div></div>");
                div.attr("class", "divider");
                $('#gaxx_gcList').append(li);
                $('#gaxx_gcList').append(div);
            }
        }
    });
}

// 上一页
function open_file_pre() {

    try{
        var node = $("#tree_caseinfo_doc_files").tree('getSelected');
        if(isNull(node))
            return;

        // 判断是否有子节点为文件节点
        var fNode = get_doc_file_node_prev(node);
        if(isNull(fNode))
            return;

        if(isNull(fNode.target)){
            fNode = $('#tree_caseinfo_doc_files').tree('find', fNode.id);
        }
        $('#tree_caseinfo_doc_files').tree('select', fNode.target);
        var nodes = $('#tree_caseinfo_doc_files').tree('getRoot',fNode.target);
        if(nodes.id=='1'){
            var bmsah = fNode.attributes.BMSAH;
            var wjlx = fNode.attributes.WJLX;
            var wsbh = fNode.attributes.ID;
            var wjlj = fNode.attributes.WJLJ;
            if (isNull(wjlj)){
                return;
            }

            // 打开文书
            if (wjlx == "1") {
                open_doc_file(bmsah, wjlj, wsbh, '1')
            }
            // 下载附件到本地并用本机默认程序打开
            else {
                open_doc_file(bmsah, wjlj, wsbh, '2')
            }
        }else {
            var info = fNode.id.split("}@{");

            if (info != null && info.length > 2) {
                var dwbm = fNode.attributes.DWBM;
                var bmsah = fNode.attributes.BMSAH;
                var jzbh = fNode.attributes.ID;
                open_dossier_file(dwbm, bmsah, jzbh);
            }
        }
    }catch (e){

    }
}

// 下一页
function open_file_next() {

    try{
        var node = $("#tree_caseinfo_doc_files").tree('getSelected');
        if(isNull(node))
            return;

        // 判断是否有子节点为文件节点
        var nNode = get_doc_file_node_first(node);
        if(isNull(nNode)) {
            // 获取下一文件节点
            nNode = get_doc_file_node_next(node);
        }

        if(isNull(nNode))
            return;
        if(isNull(nNode.target)){
            nNode = $('#tree_caseinfo_doc_files').tree('find', nNode.id);
        }
        $('#tree_caseinfo_doc_files').tree('select', nNode.target);
        var nodes = $('#tree_caseinfo_doc_files').tree('getRoot',nNode.target);
        if(nodes.id=='1'){
            var bmsah = nNode.attributes.BMSAH;
            var wjlx = nNode.attributes.WJLX;
            var wsbh = nNode.attributes.ID;
            var wjlj = nNode.attributes.WJLJ;
            if (isNull(wjlj)){
                return;
            }

            // 打开文书
            if (wjlx == "1") {
                open_doc_file(bmsah, wjlj, wsbh, '1')
            }
            // 下载附件到本地并用本机默认程序打开
            else {
                open_doc_file(bmsah, wjlj, wsbh, '2')
            }
        }else {
            var info = nNode.id.split("}@{");

            if (info != null && info.length > 2) {
                var dwbm = nNode.attributes.DWBM;
                var bmsah = nNode.attributes.BMSAH;
                var jzbh = nNode.attributes.ID;
                open_dossier_file(dwbm, bmsah, jzbh);
            }
        }
    }catch (e){

    }
}

// 打开文书，type：1.文书，2.附件
// 文书：调用文书控件打开文书
// 附件：下载附件到本地，并用本地默认程序打开文件
function open_doc_file(bmsah, wjlj, wsbh, type) {

    opening_case_doc_file = "";
    ShowProgress();

    // 获取文书并用文书控件加载文书
    $.ajax({
        type: 'POST',
        url: getRootPath()+'/caseinfo/getDocFile',
        data: { pcslbm: evalInfo.PCSLBM, bmsah: bmsah, wsbh: wsbh, wjlj: wjlj, wjlx: type, sfzpdf: "N" },
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
                        // PDF方式打开文书
                        document.getElementById('div_caseinfo_doc').style.display = "none";
                        document.getElementById('div_caseinfo_panel').style.display = "";
                        show_caseinfo_fanyeBtn(true);
                        CloseProgress();
                        /*var success = new PDFObject({ url: getRootPath() + result.value,
                            pdfOpenParams: { scrollbars: '0', toolbar: '0', statusbar: '0' }
                        }).embed("div_caseinfo_panel");*/
                        var urlHtml = '';
                        var pdfUrl =getRootPath() + "/plugin/pdfjs/web/viewer.html?file="+ getRootPath() + result.value;
                        urlHtml += '<iframe width="100%" height="100%" frameborder="0" src='+ pdfUrl +'></iframe>'
                        $("#div_caseinfo_panel").html(urlHtml);
                        break;
                    case ".doc":
                    case ".docx":
                        // 加载文书
                        show_caseinfo_fanyeBtn(false);
                        var height = $('#pcbl_ajxx_left').height();
                        var width = $('#pcbl_ajxx_left').width();
                        $('#div_caseinfo_doc').height(height);
                        $('#div_caseinfo_doc').width(width);
                        document.getElementById('div_caseinfo_panel').style.display = "none";
                        document.getElementById('div_caseinfo_doc').style.display = "";
                        var error = OpenFile(getRootPath() + result.value, "TANGER_OCX_CASEINFO");

                        opening_case_doc_file = result.value;
                        CloseProgress();
                        if (!isNull(error)) {
                            Alert(error);
                        }

                        // 只读
                        SetSaveButtonState("TANGER_OCX_CASEINFO", false);
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

// 打开电子卷宗文件
function open_dossier_file(dwbm, bmsah, jzbh) {

    opening_case_doc_file = "";
    document.getElementById('div_caseinfo_doc').style.display = "none";
    document.getElementById('div_caseinfo_panel').style.display = "";
    show_caseinfo_fanyeBtn(true);

    ShowProgress();

    // 获取电子卷宗文件
    $.ajax({
        type: 'POST',
        url: getRootPath() + '/caseinfo/getDossierFile',
        data: {pcslbm: evalInfo.PCSLBM, dwbm: dwbm, bmsah: bmsah, jzbh: jzbh},
        dataType: "json",
        success: function (result) {
            CloseProgress();

            try {
                if (result.status != 200) {
                    Alert(result.note);
                    return;
                }

                /*var success = new PDFObject({
                    url: getRootPath() + result.value,
                    pdfOpenParams: {scrollbars: '0', toolbar: '0', statusbar: '0'}
                }).embed("div_caseinfo_panel");*/
                var urlHtml = '';
                var pdfUrl =getRootPath() + "/plugin/pdfjs/web/viewer.html?file="+ getRootPath() + result.value;
                urlHtml += '<iframe width="100%" height="100%" frameborder="0" src='+ pdfUrl +'></iframe>'
                $("#div_caseinfo_panel").html(urlHtml);
            } catch (e) {
                //Alert(1);
            }
        }
    });
}

// 打开案卡项
function show_case_card_info(akbm) {
    opening_case_doc_file = "";
    show_caseinfo_fanyeBtn(false);
    document.getElementById('div_caseinfo_doc').style.display = "none";
    document.getElementById('div_caseinfo_panel').style.display = "";
    $('#gaxx_akxh').val(akbm);
    $('#div_caseinfo_panel').panel({
        href: './view/evaluate/handle/casecard.html'
    });
    $('.pcbl_ajxx_btn_postion').css('height',$('#pcbl_ajxx_left').height()+'px')
}

// 打开过程项
function show_process_card_info(akbm) {
    opening_case_doc_file = "";
    show_caseinfo_fanyeBtn(false);
    document.getElementById('div_caseinfo_doc').style.display = "none";
    document.getElementById('div_caseinfo_panel').style.display = "";
    $('#gaxx_gcakxh').val(akbm);
    $('#div_caseinfo_panel').panel({
        href: './view/evaluate/handle/processinfo.html'
    });
    $('.pcbl_ajxx_btn_postion').css('height',$('#pcbl_ajxx_left').height()+'px')
}
//打开流程项
function show_flow_card_info() {
    opening_case_doc_file = "";
    show_caseinfo_fanyeBtn(false);
    document.getElementById('div_caseinfo_doc').style.display = "none";
    document.getElementById('div_caseinfo_panel').style.display = "";
    // $('#gaxx_gcakxh').val(akbm);
    $('#div_caseinfo_panel').panel({
        href: './view/evaluate/handle/flow.html'
    });
    // $('.pcbl_ajxx_btn_postion').css('height',$('#pcbl_ajxx_left').height()+'px')
}
//控制翻页按钮显示隐藏
function show_caseinfo_fanyeBtn(flag){
    if(flag == true){
        $(".caseinfo_fanyeBtn").show();
    }else if(flag == false){
        $(".caseinfo_fanyeBtn").hide();
    }
}
//追加节点
function treeAppend(url,id,data,Fbm) {
    $.ajax({
        type: 'GET',
        url: getRootPath()+url,
        // url: getRootPath()+,
        data: data,
        dataType: "json",
        success: function (data) {
            var data = data;
            var selected = $(id).tree('find', Fbm);
            $(id).tree('append', {
                parent: selected.target,
                data: data
            });
        }
    });
}

// 文书卷宗上下翻页
// 获取上一个节点
function get_doc_file_node_prev(node) {

    var rNode;
    if(isNull(node))
        return rNode;

    // 验证是否为根节点
    var roots = $("#tree_caseinfo_doc_files").tree('getRoots');
    var rIndex = -1;
    for (var i = roots.length - 1; i >= 0; i--) {
        if (roots[i].id == node.id){
            rIndex = i;
            break;
        }
    }
    if (rIndex != "-1" && rIndex >= 1){
        for (var i = rIndex - 1; i >= 0; i--){
            // 搜索子节点下最后一个文件节点
            rNode = get_doc_file_node_last(roots[i]);
            if (!isNull(rNode))
                break;

            // 验证节点本身是否为文件节点
            if(validate_doc_file_node(roots[i])) {
                rNode = roots[i];
                break;
            }
        }
    }
    if(!isNull(rNode)){
        return rNode;
    }

    // 获取父节点，并搜索平级节点下文件节点
    var pNode = $("#tree_caseinfo_doc_files").tree('getParent', node.target);
    if (isNull(pNode))
        return rNode;

    // 获取当前节点位置
    var nodes = pNode.children;
    var index = -1;
    for (var i = nodes.length - 1; i >= 0 ; i--){
        if(nodes[i].id == node.id) {
            index = i;
            break;
        }
    }

    // 搜索兄弟节点下文件节点
    if(index != "-1" && index >= 1){
        for (var i = index - 1; i >= 0; i--){
            // 搜索子节点下文件节点
            rNode = get_doc_file_node_last(nodes[i]);
            if (!isNull(rNode))
                break;

            // 验证节点本身是否为文件节点
            if(validate_doc_file_node(nodes[i])) {
                rNode = nodes[i];
                break;
            }
        }
    }

    // 搜索父级结点下文件节点
    if (isNull(rNode)){
        rNode = get_doc_file_node_prev(pNode);
    }

    return rNode;
}

// 获取上一个节点
function get_doc_file_node_next(node) {

    var rNode;
    if(isNull(node))
        return rNode;

    // 验证是否为根节点
    var roots = $("#tree_caseinfo_doc_files").tree('getRoots');
    var rIndex = -1;
    for (var i = 0; i < roots.length; i++) {
        if (roots[i].id == node.id){
            rIndex = i;
            break;
        }
    }
    if (rIndex != "-1" && i <= roots.length - 2){
        for (var i = rIndex + 1; i < roots.length; i++){
            // 验证节点本身是否为文件节点
            if(validate_doc_file_node(roots[i])) {
                rNode = roots[i];
                break;
            }
            // 搜索子节点下文件节点
            rNode = get_doc_file_node_first(roots[i]);
            if (!isNull(rNode))
                break;
        }
    }
    if(!isNull(rNode)){
        return rNode;
    }

    // 获取父节点，并搜索平级节点下文件节点
    var pNode = $("#tree_caseinfo_doc_files").tree('getParent', node.target);
    if (isNull(pNode))
        return rNode;

    // 获取当前节点位置
    //var nodes = $("#tree_caseinfo_doc_files").tree('getChildren', pNode.target);
    var nodes = pNode.children;
    var index = -1;
    for (var i = 0; i < nodes.length; i++){
        if(nodes[i].id == node.id) {
            index = i;
            break;
        }
    }

    // 搜索兄弟节点下文件节点
    if(index != "-1" && index <= nodes.length - 2){
        for (var i = index + 1; i < nodes.length; i++){
            // 验证节点本身是否为文件节点
            if(validate_doc_file_node(nodes[i])) {
                rNode = nodes[i];
                break;
            }
            // 搜索子节点下文件节点
            rNode = get_doc_file_node_first(nodes[i]);
            if (!isNull(rNode))
                break;
        }
    }

    // 搜索父级结点下文件节点
    if (isNull(rNode)){
        rNode = get_doc_file_node_next(pNode);
    }

    return rNode;
}

// 获取节点下第一个文件节点
function get_doc_file_node_first(node) {

    var rNode;
    var cNodes = $("#tree_caseinfo_doc_files").tree('getChildren', node.target);
    if (isNull(cNodes) || cNodes.length <= 0)
        return rNode;

    for (var i = 0; i < cNodes.length; i++){
        if (validate_doc_file_node(cNodes[i])) {
            rNode = cNodes[i];
            break;
        }
    }

    return rNode;
}

// 获取节点下最后一个文件节点
function get_doc_file_node_last(node) {

    var rNode;
    var cNodes = $("#tree_caseinfo_doc_files").tree('getChildren', node.target);
    if (isNull(cNodes) || cNodes.length <= 0)
        return rNode;

    for (var i = cNodes.length - 1; i >= 0; i--){
        if (validate_doc_file_node(cNodes[i])) {
            rNode = cNodes[i];
            break;
        }
    }

    return rNode;
}

// 验证文书卷宗树节点是否为文件节点
function validate_doc_file_node(node) {

    var nodes = $('#tree_caseinfo_doc_files').tree('getRoot',node.target);
    if(nodes.id=='1'){
        return !isNull(node.attributes.WJLJ)
    }else {
        var info = node.id.split("}@{");
        return info != null && info.length > 2;
    }
}
