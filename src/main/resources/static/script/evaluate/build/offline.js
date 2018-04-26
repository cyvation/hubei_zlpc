var pcxx;
$(document).ready(function () {
    // 新建评查界面传递的参数，评查分类及评查模板等信息
    pcxx = FUNCTION_PARAM;
    if(pcxx.type!=undefined&& pcxx.type==0){
        $("#baseInfo").css("display","none");$(".pcblPcyl").css("display"," inline-block");
        $(".radioShow").css("display"," inline-block");
        if(pcxx.PCCZLX == '0'){
            $(".ZXpc_pcfa_2").css("display","none");
        }
        baseInfoPc();
    }
    // 界面标签样式设置及事件绑定
    eval_random_marksInit();
    // 评查结果
    load_cljg_pcjg('9102');
    addClickListener();
    // 初始化自定义筛选条件
    init_eval_build_rd_custom_condition(pcxx);
    saveInfo();
    // 人员信息：添加人员
    $('#offline_cbr').focus(function(){
            // 加载弹出框中人员列表
            var bm =$('#offline_cbbm').combotree('getValue');
            load_grid_win_org_user_add_list($('#offline_pcdw').combotree('getValue'), bm==""?"":bm, "", "");

            $('#win_org_user_manage_add').window({
                title: '添加人员'
            });
            $('#win_org_user_manage_add').window('open');
    });
    init_win_org_user_manage_add();
});
var info;
function baseInfoPc(){
    $.ajax({
        method: 'get',
        url: getRootPath() + '/offline/getPcAjInfo',
        data: { pcslbm: pcxx.PCSLBM, dwbm: pcxx.DWBM},
        async:false,
        dataType: 'json',
        success: function (data) {
            info=data.value;
            pcxx.PCFLBM=info.PCFLBM;
            $('#win_pcWin_offline_ajmc').text(info.AJMC);
            $('#win_pcWin_offline_cbr').text(info.BPC_MC);
            $('#win_pcWin_offline_pcr').text(info.PCR_MC);
            $('#win_pcWin_offline_pcsah').text(info.PCSAH);
            //$('.offline_date').text(sjzh(info.BPC_WCRQ));
            // $('#win_pcWin_lbl_eval_handle_eval_ay').text(info.AY);
        }
    });
}
// 载入弹出窗口未分配角色人员数据
function load_grid_win_org_user_add_list(dwbm, bmbm, jsbm, xm) {
    // JS对象
    var obj = new Object();
    obj.dwbm = dwbm;
    obj.bmbm = bmbm;
    obj.jsbm = jsbm;
    obj.xm = xm;

    $('#grid_win_org_user_add_list').datagrid({
        url: getRootPath()+'/organization/getWfpRyInfo',
        queryParams:obj,
        loadFilter:function (result) {
            return result.status == 200?JSON.parse(result.value):[];
        }
    });


}
// 初始化人员新增窗体
function init_win_org_user_manage_add() {

    //选择人员列表初始化
    $('#grid_win_org_user_add_list').datagrid({
        width: 635,
        striped: true,
        singleSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        toolbar: $('#tool_win_org_user_add'),
        columns: [[
            {field: 'MC', title: '姓名', width: 100},
            {field: 'BMMC', title: '部门名称', width: 100},
            {field: 'JSMC', title: '角色', width: 100},
            {field: 'GZZH', title: '工作证号', width: 100}
        ]]
    });

    // 调整分页控件位置
    // resize_grid_win_org_user_add_list();

    //选择人员窗口 -- 按钮：查询
    $('#btn_win_org_user_add_search').linkbutton({
        onClick: function () {
            var xm = trim($('#txt_win_org_user_add_xm').textbox('getValue'));
            var bm =$('#offline_cbbm').combotree('getValue');
            load_grid_win_org_user_add_list($('#offline_pcdw').combotree('getValue'),  bm==""?"":bm, "", xm);
        }
    });

    //选择人员窗口 -- 按钮：添加
    $('#btn_win_org_user_add_add').linkbutton({
        onClick: function () {
            var data = $('#grid_win_org_user_add_list').datagrid('getChecked');
            if (data.length <= 0) {
                var xm=$('#txt_win_org_user_add_xm').textbox('getValue');
                if(xm!=""){
                    Confirm("确认","暂无此人,是否添加此人为承办人？",function(r){
                        if(r){
                            $("#offline_cbr").val( xm);
                            // 刷新分配人员列表
                            $('#grid_org_user_manage_list').datagrid('load');
                            $('#win_org_user_manage_add').window('close');
                        }
                    })
                    return;
                }else{
                    Alert("请勾选人员！");
                    return;
                }
            }
            $("#offline_cbr").val(data[0].MC);
            $("#offline_cbrgh").val(data[0].GH);
            // 刷新分配人员列表
            $('#grid_org_user_manage_list').datagrid('load');
            $('#win_org_user_manage_add').window('close');
        }
    });

}

// 界面标签样式设置及事件绑定
function eval_random_marksInit() {
    // 承办部门下拉树
    $("#offline_cbbm").combotree({
        method: 'get',
        disabled: false,
        editable: false,
        lines: true,
        multiple: false,
        cascadeCheck: false
    });
    //市院有选择其他院案件的权限
    $('#offline_pcdw').combotree({
        method: 'get',
        editable: false,
        lines: true,
        panelWidth: 270,
        // multiple: true,
        //cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                    $('#offline_pcdw').combotree('setValue', data[0].id); //单位默认选择
            }
            index_addMousedownDiv(this, 'offline_pcdw');
        },
        onChange: function (newValue, oldValue) {
            // 仅选中一个单位时加载部门列表
            if(isNull(newValue)){
                $("#offline_cbbm").combotree('disable');
                return;
            }

            $("#offline_cbbm").combotree('enable');
            // 承办部门下拉树
            $("#offline_cbbm").combotree({
                //method: 'get',
                onShowPanel: index_onShowPanel,
                onHidePanel: index_onHidePanel,
                url: getRootPath() + '/filter/getAllBm',
                queryParams: {
                    dwbm: newValue
                },
                onLoadSuccess: function (node, data) {
                    index_addMousedownDiv(this,'offline_cbbm');
                }
            });
        }
    });
    //设置检察官默认查询为当年时间
    //完成日期
    $('#offline_date').datebox({
        editable: false,
       // value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
        value: 2013 + '-' + 02 + '-' + 10
    });
    if(pcxx.type!=undefined&& pcxx.type==0){
        $('#offline_dates').datebox("setValue", new Date(info.BPC_WCRQ).getFullYear() + '-' + ((new Date(info.BPC_WCRQ).getMonth() + 1)<10?0+(new Date(info.BPC_WCRQ).getMonth() + 1):(new Date(info.BPC_WCRQ).getMonth() + 1))+ '-' + (new Date(info.BPC_WCRQ).getDate()));
    }
}

// 标签初始化数据加载
function init_eval_build_rd_custom_condition(pcxx) {
    // 获取评查模板
    $('#offline_pcmb').combotree({
        method: 'get',
        lines: true,
        panelWidth: 260,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + "/offline/get_pcbz",
        queryParams: {
            pcflbm: pcxx.PCFLBM,//"001"
            dwbm:userInfo.DWBM
        },
        loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        },
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {
                pchdbm = data[0].id;
                $('#offline_pcmb').combotree('setValue', pchdbm);
                load_cbt_eval_build_rd_custom_sxgz(pcxx.PCFLBM,pchdbm);
                if(pcxx.type!=undefined&& pcxx.type==0)
                     load_eval_info_card(info.PCFLBM,info.PCHDBM);
                else
                    load_eval_info_card(pcxx.PCFLBM,pchdbm);
                load_ajmb(pcxx.PCFLBM,pchdbm);
            }
            index_addMousedownDiv(this, "offline_pcmb");
        },
        onSelect: function (node) {
            load_cbt_eval_build_rd_custom_sxgz(pcxx.PCFLBM,node.id);
            if(pcxx.type!=undefined&& pcxx.type==0)
                load_eval_info_card(info.PCFLBM,info.PCHDBM);
            else
                load_eval_info_card(pcxx.PCFLBM,node.id);
            load_ajmb(pcxx.PCFLBM,node.id);
        }
    });
}
//加载筛选规则
function load_ajmb(pcflbm,pchdbm) {
    $('#offline_ajmb').combotree('loadData', []);
    $('#offline_ajmb').combotree('clear');
    $('#offline_ajmb').combotree('setValue', '');
    // 获取评查活动绑定的筛选规则列表
    $('#offline_ajmb').combotree({
        method: 'get',
        lines: true,
        panelWidth: 260,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/offline/getAjmb',
        queryParams: {
            pcflbm: pcflbm ,//"001"
            pchdbm: pchdbm,
            dwbm:userInfo.DWBM
        },
        loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        },
        onLoadSuccess: function (node, data) {
            if (data.length > 0) {
                $('#offline_ajmb').combotree('setValue', data[0].id);
            }
            index_addMousedownDiv(this, "offline_ajmb");
        }
    });
}
//加载筛选规则
function load_cbt_eval_build_rd_custom_sxgz(pcflbm,pchdbm) {
    // 获取评查活动绑定的筛选规则列表
    $.ajax({
        method: 'get',
        url: getRootPath() + '/filter/getHdsxgz?pcflbm='+pcflbm+'&pchdbm='+pchdbm,
        dataType: 'json',
       /* queryParams: {
            pcflbm: "001",//pcflbm
            pchdbm: pchdbm
        },*/
        success: function (data) {
            if (data.length > 0) {
                $('#offline_sxgz').val( data[0].id);
            }
          //  index_addMousedownDiv(this, "sxgz");
        }
    });
}
//动态获取处理结果/评查结果
function load_cljg_pcjg(bm) {
    $('.offline_loadPcjg').children(".radio").remove();
    $.ajax({
        type: 'get',
        async: false,
        url: getRootPath() + '/common/getDataDictionaryByLBBM?lbbm=' + bm,
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var data = data.data;
                if (bm == 9101) {
                    var html = '';
                    for (var i = 0; i < data.length; i++) {
                        html += '<div class="radio">';
                        html += '<div class="redio_click">';
                        html += '<input class="input_radio_cljg" name="rd_eval_info_pcjl_cl" type="radio" value="' + data[i].mc + '"/>';
                        html += '</div>' + data[i].mc + '</div>';
                    }
                    $('.offline_loadPcjg').append(html)
                } else {
                    var html = '';
                    for (var i = 0; i < data.length; i++) {
                        html += '<div class="radio">';
                        html += '<div class="redio_click">';
                        html += '<input class="input_radio_pcjg" name="rd_eval_info_pcjl_jg" type="radio" value="' + data[i].mc + '"/>';
                        html += '</div>' + data[i].mc + '</div>';
                    }
                    $('.offline_loadPcjg').append(html);
                }
            } else {
                Alert('getDataDictionaryByLBBM 错误' + data.message)
            }


        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("消息", 'getDataDictionaryByLBBM 接口错误')
        }
    });
    $(".radio").unbind('click');
    addClickListener();
    addHoverType();
    if(pcxx.type!=undefined&& pcxx.type==0){
        $("input[value="+info.PCJL+"]").parent().parent().click();
    }
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
        $(this).children(".redio_click").children("input").attr('checked', 'true');
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
// 访问接口，动态生成质量评查内容
function load_eval_info_card(pcflbm,pchdbm) {
    $("#ZLPCNR_CONTAINTER").find(".muban").remove();
    url ="/offline/getPcTree";
    data={mbNo: pchdbm,pcflbm:pcflbm/*"001"*/};
    if(pcxx.type!=undefined&& pcxx.type==0) {
        url="/offline/getPcjgInfo";
        data={pcslbm:pcxx.PCSLBM,dw:userInfo.DWBM };
    }
    $.ajax({
        url: getRootPath() + url,
        type: 'get',
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data.status == 200) {
                var muban_dom = $("<div class='muban'></div>");
                data.value.forEach(function (pcfl) {//评查分类循环
                    var pcfl_dom = $("<div class=\"pcbl_pcxx_center_left_menu\"><div class=\"center_left_menu_top\"><h5>" + pcfl.pcxflmc + "</h5></div></div>");

                    for (var k1 in pcfl) {
                        if (Object.prototype.toString.call(pcfl[k1]) != '[object Array]') {
                            pcfl_dom.attr(k1, pcfl[k1]);
                        }
                    }
                    var cli = 0;
                    pcfl.children.forEach(function (pczl, index) {//评查子分类循环
                        var pczl_dom = $("<div class=\"radio radio_wwt " + (cli == 0 ? 'cl' : '') + " \"><div class=\"redio_click\"></div>" + pczl.pcxflmc + "</div>");
                        var pczl_box = $("<div class=\" pczl_box\" style='display: none; margin:10px 0px 10px 0px;'></div>");
                        if (pczl.pcjg == "1") {
                            pczl_dom.find('.redio_click').addClass('redio_click_no');
                        }
                        for (var kk in pczl) {
                            if (Object.prototype.toString.call(pczl[kk]) != '[object Array]') {
                                pczl_box.attr(kk, pczl[kk]);
                            }
                        }
                        cli++;
                        pczl.children.forEach(function (item) { //评查项循环
                            var dom;
                            if (item.PCFS && item.PCFS == 2) {
                                dom = $("<div class=\"select_option\"><input type='text' placeholder='其他' value ="+item.pcjg+"></div>");
                            } else {
                                if (item.sm == 'null' || item.sm == '' || item.sm == undefined || item.sm == "" || item.sm == null) {
                                    item.sm = '';
                                }
                                dom = $("<div class=\"select_option\" style=\"height: 30px;position: relative;\"><div id=\"zlpc_pcxxys_id\" ><label style=\"margin-left: 11px;\"><input style=\" position: relative;top: 2px;right: 5px;\" type=\"checkbox\">" +
                                    item.pcxmc + "</label></div><div class=\"zlpc_pcxx_hideys\"><div class=\"zlpc_pcxx_xsjys\"></div><span>" + item.pcxmc + "</span></div><div class=\"zx_bzys\"><label style=\"width:60px;float: left;text-align: right;\">备注：</label><input type=\"text\" style='width: calc(100% - 65px);' value=" + item.sm + "></div> </div>");
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
                    pcfl_dom.find(".center_left_menu_top").append("<div class=\"switch\"></div>");
                    muban_dom.append(pcfl_dom);
                });

                $("#ZLPCNR_CONTAINTER").append(muban_dom);

                //$(".switch").off("click")
                $(".switch").unbind("click");
                $(".radio").unbind('click');
                addClickListener();
                addHoverType();
                if(pcxx.type==undefined|| pcxx.type!=0) {
                    $(".cl").children(".redio_click").addClass("redio_click_no");
                }
                //$(".cl").click();
            }
        },
        error: function (e) {
            Alert(JSON.stringify(e));
        }
    });
}

function addHoverType() {
    $("#zlpc_pcxxys_id>label").hover(function () {
        $(this).parent("#zlpc_pcxxys_id").siblings(".zlpc_pcxx_hideys").css("display", "block");
    }, function () {
        $(this).parent("#zlpc_pcxxys_id").siblings(".zlpc_pcxx_hideys").css("display", "none");
    });
}
function saveInfo(){
    $(".save").bind("click", function () {
        var vo = initPostData();
        var basedata = baseInfo();
        if (!basedata.flag&&basedata.flag != undefined) {
            Alert(basedata.err);
            return;
        }
        if(pcxx.type!=undefined&& pcxx.type==0) {
            saveBaseInfo(vo,basedata);
        }else{
            Confirm("确认", "案件时间是否为："+$('#offline_date').datebox('getValue'), function (r) {
               if(r){
                   $(".save").attr("disabled",true);
                   $.ajax({
                       url: getRootPath() + "/offline/isOnAj",
                       data: {json: JSON.stringify(basedata)},
                       type: 'post',
                       async: false,
                       dataType: 'json',
                       success: function (result) {
                           if (result.status == 200) {
                               if (result.value == 1) {
                                   $(".save").attr("disabled","");
                                   ShowWarning("已存在信息相同的案件!");
                                   /* Confirm("确认", "已存有信息相同案件，是否继续保存", function (r) {
                                    if (r) {
                                    saveBaseInfo(vo,basedata);
                                    }
                                    })*/
                               }else{
                                   saveBaseInfo(vo,basedata);
                               }
                           } else {
                               Alert(result.note);
                           }
                       }
                   });
               }
            });
        }
    });
}
function saveBaseInfo(vo,basedata){
    $.ajax({
        url: getRootPath() + "/offline/saveOfflineInfo",
        data: { vo: JSON.stringify(vo), baseInfo: JSON.stringify(basedata)},
        type: 'post',
        async: true,
        dataType: 'json',
        success: function (result) {
            if (result.status == 200){
                $("#offline_anjian").textbox("setValue","");
                $("#offline_cbr").val("");
                if(pcxx.type!=undefined&& pcxx.type==0){
                    baseInfoPc();
                }
                load_cljg_pcjg('9102');
                eval_random_marksInit();
                $('#grid_done_list_pc').datagrid('load');
                init_eval_build_rd_custom_condition(pcxx);
                Alert("保存成功！");
                $(".save").attr("disabled","");
            }else{
                Alert(result.note);
            }
        }
    });
}
function baseInfo(){
    var ajmc=trim($("#offline_anjian").val()),
        pcmb=$('#offline_pcmb').combotree('getValue'),
        sxgz=$('#offline_sxgz').val(),
        ajmb=$('#offline_ajmb').combotree('getValue'),
        date=$('#offline_date').datebox('getValue'),
        pcdw=$('#offline_pcdw').combotree('getValue'),
        cbbm=$('#offline_cbbm').combotree('getValue'),
        cbr=trim($("#offline_cbr").val()),
        pcjy=$(".input_radio_pcjg:checked").val();
    var t = $('#offline_ajmb').combotree('tree');	// 获取树对象
    var n = t.tree('getSelected');
    var err ="";
    err+=ajmc==""?"<span style='color:red'>*</span> 案件名称为必填项；":"";
    err+=pcmb==""?"<span style='color:red'>*</span>评查标准为必填项；":"";
    err+=ajmb==""?"<span style='color:red'>*</span>案件类别为必填项；":"";
    err+=date==""?"<span style='color:red'>*</span>完成时间为必填项；":"";
    err+=pcdw==""?"<span style='color:red'>*</span>承办单位为必填项；":"";
    err+=cbbm==""?"<span style='color:red'>*</span>承办部门为必填项；":"";
    err+=cbr==""?"<span style='color:red'>*</span>承办检察官为必填项；":"";
    err+=pcjy==""||pcjy==undefined?"<span style='color:red'>*</span>结果等次建议为必填项；":"";
    var basciInfo="";
    if(pcxx.type!=undefined&& pcxx.type==0) {
        date=$('#offline_dates').datebox('getValue');
        var bmsah= info.BMSAH.split("[")[0]+"["+date.split("-")[0]+"]"+info.BMSAH.split("]")[1];
        var tysah=info.TYSAH;
        tysah= tysah.substring(0,6)+date.split("-")[0]+ tysah.substring(10);
        basciInfo ={
            'type':0,
            'pcslbm':pcxx.PCSLBM,
            'pcjl':pcjy,
            'date':date,
            'bmsah':bmsah,
            'tysah':tysah,
            'dw':info.PCDWBM
        };
    }else {
        if (err != "") {
            basciInfo = {
                'flag': false,
                'err': err
            };
        } else {
            basciInfo = {
                'ajmc': ajmc,
                'pcmb': pcmb,
                'sxgz': sxgz,
                'ajmb': ajmb,
                'date': date,
                'pcdw': pcdw,
                'pcdwmc': $('#offline_pcdw').combotree('getText'),
                'cbbm': cbbm,
                'cbbmmc': $('#offline_cbbm').combotree('getText'),
                'cbr': cbr,
                'cbrgh': $('#offline_cbrgh').val(),
                'pcjy': pcjy,
                'pcflbm': pcxx.PCFLBM,
                'ajsljc': n.AJSLJC,
                'ywbm': n.YWBM,
                'ywtx': n.YWTX,
                'ajslmc': n.text,
                'userdwbm': userInfo.DWBM,
                'userdwmc': userInfo.DWMC,
                'usermc': userInfo.MC,
                'usergh': userInfo.GH
            };
        }
    }
    return basciInfo;
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
                    if (pcxs.eq(n).find('input:eq(1)').is(":text")) {
                        var val = pcxs.eq(n).find('input:eq(1)').val();
                        pcx_attr.sm = val;
                    }
                    if (pcxs.eq(n).find('input').is(":checked")) {
                        pcx_attr.pcjg = '1';
                        if (b == 0) {
                            pczl_att.pcjg = 1;
                            b = 1;
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


