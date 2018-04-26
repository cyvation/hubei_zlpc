
$(document).ready(function () {

    // 若评查受理编码为空，则不初始化评查信息
    if(isNull(EVAL_CASE_WIN.PCSLBM))
        return;

    // 界面标签样式设置及事件绑定
    pcWin_eval_info_marksInit();

    // 评查案件信息初始化
    pcWin_init_eval_info_detail();

    // 初始化操作按钮
    pcWin_init_eval_handle_deal_toolbar();

    // 标签初始化数据加载
    pcWin_eval_info_marksDataBind();

});

// 界面标签样式设置及事件绑定
function pcWin_eval_info_marksInit() {
    $('#pcWin_win').window({
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

    $('#pcWin_addColl').window({
        width: 400,
        height: 310,
        modal: true,
        title: '添加到收藏夹',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {
            $('#pcWin_collBz').val('');
        }
    });
    // 评查案卡及卷宗区控制
    // $("#pcbl_ssyjksj").unbind("click");
    // $("#pcbl_ssyjksj").bind("click", function () {
    //     if($(this).html()=='锁定'){
    //         $(this).html('解锁');
    //     }else {
    //         $(this).html('锁定');
    //     }
    //
    // });
    // $("#pcbl_ssyjzsj").unbind("click");
    // $("#pcbl_ssyjzsj").bind("click", function () {
    //     if($(this).html()=='锁定'){
    //         $(this).html('解锁');
    //     }else {
    //         $(this).html('锁定');
    //     }
    //
    // });
    $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(0)").click(function () {
        $("#pcWin_evalinfo_position_one").show();
        $("#pcWin_evalinfo_position_two").hide();
        $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(0)").siblings().removeClass('center_right_box_click');
        $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(0)").addClass('center_right_box_click');
        $('#pcWin_pcbl_pcxx_center_right .center_right_box_sj').removeClass('center_right_box_sj');
        $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(0)").children('div').addClass('center_right_box_sj');
    });
    $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(1)").click(function () {
        $("#pcWin_evalinfo_position_one").hide();
        $("#pcWin_evalinfo_position_two").show();
        $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(1)").siblings().removeClass('center_right_box_click');
        $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(1)").addClass('center_right_box_click');
        $('#pcWin_pcbl_pcxx_center_right .center_right_box_sj').removeClass('center_right_box_sj');
        $("#pcWin_pcbl_pcxx_center_right .center_right_box:eq(1)").children('div').addClass('center_right_box_sj');
    });
    $("#pcbl_ssyjksj").click(function () {
        $("#pcWin_evalinfo_position_one").hide();
    });
    $("#pcbl_ssyjzsj").click(function () {
        $("#pcWin_evalinfo_position_two").hide();
    });
    // $("#pcWin_evalinfo_position_two").hover(function () {
    //     $("#pcWin_evalinfo_position_two").show();
    //     $("#pcbl_ssyjzsj").html('解锁')
    // },function () {
    //     if($(this).find(".pcbl_ssys").html()=='锁定'){
    //         $("#pcWin_evalinfo_position_two").hide();
    //     }
    //
    // });

    // $("#pcWin_evalinfo_position_one").hover(function () {
    //     $("#pcWin_evalinfo_position_one").show();
    //     $("#pcbl_ssyjksj").html('解锁')
    // },function () {
    //     if($(this).find(".pcbl_ssys").html()=='锁定'){
    //         $("#pcWin_evalinfo_position_one").hide();
    //     }
    //
    // });
    // $(".pcWin_evalinfo_position_two_mouse").mousemove(function () {
    //     $("#pcWin_evalinfo_position_two").show()
    // });
    // $(".pcWin_evalinfo_position_two_mouse").mouseout(function () {
    //     $("#pcWin_evalinfo_position_two").show()
    // });

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
    $("#input_radio_spyj").unbind("click");
    $("#input_radio_spyj").bind("click", function () {
        $('.redio_click_no2').removeClass('redio_click_no2');
        $(this).parent().addClass('redio_click_no2') ;
    });

    $("#pcWin_btn_eval_info_pcak").unbind("click");
    $("#pcWin_btn_eval_info_pcak").bind("click", function () {
        pcWin_display_eval_handle_cards();
    });
    //备注信息不同分辨率处理
    var curWinfbl = window.screen.width;
    if(curWinfbl == 1366){
        $('.pcjg_input .pcjg_redio_text').width('80px');
    }
}

// 评查案件信息初始化
function pcWin_init_eval_info_detail() {
    $('#pcWin_lbl_eval_handle_eval_ajmc,#win_pcWin_lbl_eval_handle_eval_ajmc').text(EVAL_CASE_WIN.AJMC);
    $('#pcWin_lbl_eval_handle_eval_cbr,#win_pcWin_lbl_eval_handle_eval_cbr').text(EVAL_CASE_WIN.BPC_MC);
    $('#pcWin_lbl_eval_handle_eval_pcr,#win_pcWin_lbl_eval_handle_eval_pcr').text(EVAL_CASE_WIN.PCR_MC);
    $('#pcWin_lbl_eval_handle_eval_pcsah,#win_pcWin_lbl_eval_handle_eval_pcsah').text(EVAL_CASE_WIN.PCSAH);
    $('#pcWin_lbl_eval_handle_eval_pcsj,#win_pcWin_lbl_eval_handle_eval_pcsj').text(sjzh(EVAL_CASE_WIN.CJSJ));
    $('#pcWin_lbl_eval_handle_eval_ay,#win_pcWin_lbl_eval_handle_eval_ay').text(EVAL_CASE_WIN.AY);
    $('#pcWin_collPcaj').val(EVAL_CASE_WIN.AJMC);
    $('#pcWin_collPcah').val(EVAL_CASE_WIN.PCSAH);
}

// 标签初始化数据加载
function pcWin_eval_info_marksDataBind() {

    // 评查案卡
    pcWin_load_eval_info_card();

    // 处理结果
    pcWin_load_cljg_pcjg('9101');

    // 评查结果
    pcWin_load_cljg_pcjg('9102');
    // 评查结论
    $("#pcWin_txt_eval_info_pcjl_bz").val(EVAL_CASE_WIN.SM);

    // 初始化卷宗文件树
    pcWin_init_eval_info_docfiles();

    // 加载评查卷宗
    pcWin_load_pcWin_tree_eval_doc_files()
}

// 初始化评查卷宗
function pcWin_init_eval_info_docfiles(){

    // 卷宗树
    $('#pcWin_tree_eval_doc_files').tree({
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

            // 初始化文件操作工具栏
            //$('#tool_pcWin_tree_eval_doc_files').css('display', 'none');
        },
        onLoadError: function (arguments) {
            //Alert(arguments);
        },
        onClick: function(node){

            var jzwjbh = node.attributes.BM;
            var pczybm = node.attributes.ZYBM;
            var pczylx = node.attributes.ZYLX;
            var wjlx = node.attributes.LX; //文件类型(附件、文书)
            var wjlj = node.attributes.WJLJ;
            if (isNull(wjlj)){
                return;
            }

            // 打开卷宗文件
            pcWin_open_eval_file(jzwjbh, pczybm, pczylx, wjlx, wjlj);
        },
        onDblClick: function (node) {

            if (node.state == "closed") {
                $("#pcWin_tree_eval_doc_files").tree('expand', node.target);
            }else {
                $("#pcWin_tree_eval_doc_files").tree('collapse', node.target);
            }
        }
    });

}

// 加载评查卷宗
function pcWin_load_pcWin_tree_eval_doc_files() {

    // 评查卷宗
    var obj = new Object();
    obj.PCSLBM = EVAL_CASE_WIN.PCSLBM;
    obj.PCFLBM = EVAL_CASE_WIN.PCFLBM;
    obj.PCHDBM = EVAL_CASE_WIN.PCHDBM;
    $('#pcWin_tree_eval_doc_files').tree({
        url: getRootPath()+'/handle/getDocFile',
        queryParams: { json: JSON.stringify(obj) }
    });

}

// 打开文件
function pcWin_open_eval_file(jzwjbh, pczybm, pczylx, wjlx, wjlj) {

    ShowProgress();

    // 打开
    var obj = new Object();
    obj.JZWJBH = jzwjbh;
    obj.PCZYBM = pczybm;
    obj.PCZYLX = pczylx;
    obj.WJLX = wjlx; // 0.附件 1.评查报告(案件) 2.评查方案/评查报告(汇总) 3.评查流转单
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
                        pcWin_show_eval_doc_panel("pdf");
                        CloseProgress();

                        var success = new PDFObject({ url: getRootPath() + result.value,
                            pdfOpenParams: { scrollbars: '0', toolbar: '0', statusbar: '0' }
                        }).embed("pcWin_divPdf");

                        break;
                    case ".doc":
                    case ".docx":
                        pcWin_show_eval_doc_panel("doc");
                        CloseProgress();

                        var error = OpenFile(getRootPath() + result.value, "TANGER_OCX_PCXX");
                        if (!isNull(error)) {
                            Alert(error);
                        }

                        // 仅评查报告及流转单可编辑
                        SetSaveButtonState("TANGER_OCX_PCXX", false);

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

// 显示评查卷宗预览区域
function pcWin_show_eval_doc_panel(type) {
    $("#pcWin_pcbl_pcxx_wdck").css('display','block');
    $("#pcWin_pcbl_pcxx_center_left").css('display','none');

    switch(type){
        case "doc":
            pcWin_resize_div_eval_handle_doc_area();
            // 加载文书
            document.getElementById('pcWin_divPdf').style.display = "none";
            document.getElementById('div_pcxx_doc').style.display = "";

            break;
        case "pdf":

            // 控件展示区域
            var height = $("#pcWin_div_eval_handle_file_area").height();
            $("#pcWin_divPdf").css("height", (height - 4) + 'px');

            // PDF方式打开文书
            document.getElementById('div_pcxx_doc').style.display = "none";
            document.getElementById('pcWin_divPdf').style.display = "";

            break;
        default:
            break;
    }
}

// 调整文书控件大小
function pcWin_resize_div_eval_handle_doc_area() {

    // 文书控件展示区域
    var height = $("#pcWin_div_eval_handle_file_area").height();
    $("#div_pcxx_doc").css("height", (height - 4) + 'px');

}

// 动态获取处理结果/评查结果
function pcWin_load_cljg_pcjg(bm) {
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
                    $('#pcWin_loadCljg').append(html)
                    var pcjlElement = $("input[name='rd_eval_info_pcjl_cl'][value='" + EVAL_CASE_WIN.PCJG + "']");
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
                    $('#pcWin_loadPcjg').append(html);
                    var pcjgElement = $("input[name='rd_eval_info_pcjl_jg'][value='" + EVAL_CASE_WIN.PCJL + "']");
                    pcjgElement.parent().addClass('redio_click_no');
                    pcjgElement.attr("checked",true);
                }
            }else {
                Alert(data.message)
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert("消息", 'getDataDictionaryByLBBM 接口错误')
        }
    })
}

// 访问接口，动态生成质量评查内容
function pcWin_load_eval_info_card() {
    $.ajax({
        url: getRootPath() + '/filter/getEvalCards',
        type: 'get',
        dataType: 'json',
        data: { pcslbm: EVAL_CASE_WIN.PCSLBM, pcxflbm: '' },
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
                            dom = $("<div class=\"select_option\"><input type='text' placeholder='其他' ></div>");
                        } else {
                            if(item.sm == 'null' || item.sm == '' || item.sm == undefined || item.sm =="" || item.sm == null){
                                item.sm = '';
                            }
                            dom = $("<div class=\"select_option\" style=\"height: 30px;position: relative;\"><div id=\"zlpc_pcxxys_id\" ><label style=\"margin-left: 11px;\"><input style=\" position: relative;top: 2px;right: 5px;\" type=\"checkbox\">" +
                                item.pcxmc + "</label></div><div class=\"zlpc_pcxx_hideys\"><div class=\"zlpc_pcxx_xsjys\"></div><span>"+item.pcxmc+"</span></div><div class=\"zx_bzys\"><label style=\"width:60px;float: left;text-align: right;\">备注：</label><input type=\"text\" style='width: calc(100% - 65px);' value="+item.sm+"></div> </div>");
                            // dom = $("<div class=\"select_option\" style=\"height: 30px;\"><div style=\"display: inline-block; margin: 6px 0 0 11px;\"><label title=\""+item.bz+"\"><input id=\""+item.pcxbm+"\"  style=\" position: relative;top: 2px;right: 5px;\" type=\"checkbox\">" +
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
                    // console.log(pcfl_dom.find(".center_left_menu_top"
                    pcfl_dom.append(pczl_box);
                });
                //添加备注匡
                var bz_box = '';
                if(pcfl.children[0].sm == null){
                    pcfl.children[0].sm = '';
                }
                //不显示备注信息 LZH 2018年4月2日
                //bz_box += '<div class=\"bz_btn\"><span style="width: 60px;float: left;text-align: right">备注：</span><input type="text" style="width:calc(100% - 65px)" value='+ pcfl.children[0].sm +'></div>'
                pcfl_dom.find(".center_left_menu_top").append("<div class=\"switch\"></div>");
               // pcfl_dom.find(".center_left_menu_top").append(bz_box);
                // console.log(pcfl_dom);
                // alert(JSON.stringify(pcfl_dom.html()));

                muban_dom.append(pcfl_dom);
            });

            $("#pcWin_ZLPCNR_CONTAINTER").append(muban_dom);
            addHoverType();
            // $(".switch").off("click")
            $(".switch").unbind("click");
            $(".radio").unbind('click');
            pcWin_addClickListener();
        },
        error: function(e){
            alert(JSON.stringify(e));
        }
    });
}

// 遍历质量评查内容，生成提交数据
function pcWin_initPostData() {
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
                    // pcx_attr.PCJLBM = "asdasdadfasdfa";
                    // console.log(pcxs.eq(n).find('input'));
                    // alert("find input text");
                } else {
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

            // var rds = pcfls.eq(j).find(".radio");
            // for (var h = 0; h < rds.length; h++ ){
            //     if(yzsfcw==1){
            //         pczl_att.pcjg='1';
            //     }else {
            //     if(rds[h].attributes['box-no'].value == pczls[m].attributes['box-no'].value){
            //         pczl_att.pcjg = rds[h].children[0].attributes['class'].value == "redio_click redio_click_no" ? "1" : "0";
            //     }}
            // }
            pcfl.children.push(pczl_att);
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
function pcWin_toggleShow() {
    // alert("do pcWin_toggleShow");
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
function pcWin_addClickListener() {
    //console.log($(".switch"));
    $(".radio").unbind("click");
    $(".radio").bind("click", function () {
        $(this).children(".redio_click").addClass("redio_click_no");
        $(this).children(".redio_click").children("input").attr('checked','true');
        $(this).siblings().children(".redio_click").removeClass("redio_click_no");
        $(this).siblings(".switch").addClass('switch_click');
        pcWin_toggleShow();
    });

    $(".switch").unbind("click");
    $(".switch").bind("click", function () {
        $(this).toggleClass('switch_click');
        pcWin_toggleShow();
    });
}

// 案卡显隐
function pcWin_display_eval_handle_cards() {
    $("#pcWin_pcbl_pcxx_center_left").css('display','block');
    $("#pcWin_pcbl_pcxx_wdck").css('display','none');
}

// 初始化菜单栏
function pcWin_init_eval_handle_deal_toolbar() {

    // 评查预览
    $("#pcWin_pcbl_pcxx_top_pcyl").unbind( "click" );
    $("#pcWin_pcbl_pcxx_top_pcyl").bind("click", function () {
        $('#pcWin_win').window('open');
        pcWin_xlpcLn();
    });

    // 添加到收藏夹
    // $('#pcWin_pcbl_pcxx_top_addscj').css('display', 'block');
    // $("#pcWin_pcbl_pcxx_top_addscj").unbind( "click" );
    pcBtn_top_addscj();

    $("#pcWin_pcbl_pcxx_top_addscj").unbind( "click" );
    $("#pcWin_pcbl_pcxx_top_addscj").bind("click", function () {

        // 确认收藏
        $("#pcWin_collBc").unbind( "click" );
        $("#pcWin_collBc").bind("click", function () {
            var data={
                "zlmc":$('#pcWin_collPcaj').val(),
                "gyzy":$('#pcWin_collPcah').val(),
                "tjly":$('#pcWin_collBz').val(),
                "json": 2,
                "gxbm":EVAL_CASE_WIN.PCSLBM,
            };
            pcWin_collBc(data)
        });

        $('#pcWin_addColl').window('open');
    });
}

// 保存评查报告
function pcWin_save_doc_file() {

}

// 评查卷宗文书打开后事件
function pcWin_documentOpened() {

}

function pcWin_xlpcLn() {
    var data = pcWin_initPostData();
    var data = data.pcxFlVos;
    var html = '<table border="">';
    for (var i = 0; i < data.length; i++) {
        html += '<tr class="thead">';
        var htmlTit = '';
        var htmlCon = '';

        for (var z = 0; z < data[i].children.length; z++) {
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
                    pclistCon += '<p>' + num + '.' + data[i].children[z].pcxList[j].pcxmc +bz +'</p>';
                    num++;
                } else if (data[i].children[z].pcxList[j].pcfs == '2' && data[i].children[z].pcxList[j].pcjg != '1' && data[i].children[z].pcxList[j].pcjg != '0' && data[i].children[z].pcxList[j].pcjg != "" && data[i].children[z].pcxList[j].pcjg != undefined && data[i].children[z].pcxList[j].pcfs != null) {
                    pclistCon += '<p>' + num + '.' + data[i].children[z].pcxList[j].pcxmc + '(' + data[i].children[z].pcxList[j].pcjg + ')' + bz + '</p>';
                    num++;
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
    $('#pcWin_pcylCon').html(html)
}

function pcWin_collBc(data) {
    $.ajax({
        url: getRootPath() + "/person/savePersonAj",
        data:JSON.stringify(data),
        type: 'post',
        contentType:'application/json',
        async: true,
        dataType: 'json',
        success: function (result) {
            if(result.code!=200){
                $.messager.alert('警告','未保存成功');
            }else{
                Alert(data.zlmc+'收藏成功');
                $('#pcWin_addColl').window('close');
            }
        }
    });
}

//判断是是否已经收藏 根据是否收藏来判断是否添加收藏按钮
function pcBtn_top_addscj() {
    var data={
        "zlmc":$('#pcWin_collPcaj').val(),
        "gyzy":$('#pcWin_collPcah').val(),
        "tjly":$('#pcWin_collBz').val(),
        "json": 2,
        "gxbm":EVAL_CASE_WIN.PCSLBM,
    };
    $.ajax({
        url: getRootPath() + "/person/getScaj",
        data:data,
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {
            if(result.code==200){
                $('#pcWin_pcbl_pcxx_top_addscj').css('display', 'none');
            }else{
                $('#pcWin_pcbl_pcxx_top_addscj').css('display', 'block');
            }
        }
    });
}

function addHoverType() {
    $("#zlpc_pcxxys_id>label").hover(function () {
        $(this).parent("#zlpc_pcxxys_id").siblings(".zlpc_pcxx_hideys").css("display","block");
    },function () {
        $(this).parent("#zlpc_pcxxys_id").siblings(".zlpc_pcxx_hideys").css("display","none");
    });
}