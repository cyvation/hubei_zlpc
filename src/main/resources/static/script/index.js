var FUNCTION_LIST = []; //用户功能权限
var FAST_LIST = []; //快捷菜单
var NOTICE_LIST = []; //通知公告
var HOME_URL = './view/desktop/home.html';
var LOGIN_URL = 'login.html';
var LOCAL_PATH = 'C:/案件质量评查系统客户端/Temp/';
var userInfo;//当前登录用户信息
var FALVHTML; //法律法规地址
var ALKHTML; //案例库地址
var DJDWBM; // 顶级单位编码
var FUNCTION_PARAM; //功能参数
var Quene = new Array();
var ajxxWindowWidth;
var ajxxWindowHeight;

$.ajaxSetup({
    complete: function (xhr, status) {
        if(xhr.responseText == "sessionNull"){
            window.location = LOGIN_URL;
        }
    }
});

$(function () {

    // 标签初始化数据加载
    home_marksDataBind();

    // 界面标签样式设置及事件绑定
    home_marksInit();
    //刷新消息数量
    refresh_xiaoxi();
    // 2018,1,23 日（写字体）更改
    trends_collocate_index()
});
// 界面标签样式设置及事件绑定
function home_marksInit() {
    $('#pcblWin').window({
        modal: true,
        title: '评查办理',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {
            $('#pcblWin').window("clear")
        }
    });

    $('#ajxxWin').window({
        modal: true,
        title: '评查办理',
        collapsible: false,
        shadow:false,
        minimizable: false,
        maximizable: false,
        closed: true,
        onClose: function () {
            $('#ajxxWin').window("clear")
        },
        onBeforeClose:function () {
            $('#ajxxWin').parent('.window').width(ajxxWindowWidth);
            $('#ajxxWin').parent('.window').height(ajxxWindowHeight);
            $('#ajxxWin').siblings('div').width(ajxxWindowWidth);
            $('#ajxxWin').siblings('div').children().last().children().first().removeClass('panel-tool-huanyuan');
            $('#ajxxWin').siblings('div').children().last().children().first().addClass('panel-tool-max');
        },
        onMaximize:function(){
            var curWin_width = $(window).width();
            var curWin_height = $(window).height();
            $('#ajxxWin').css('width',curWin_width+'px');
            $('#ajxxWin').css('height',curWin_height+'px');
            $('#ajxxWin').parent('.window').width(curWin_width);
            $('#ajxxWin').parent('.window').height(curWin_height);
            $('#ajxxWin').siblings('div').width(curWin_width);
            $('#ajxxWin').siblings('div').children().last().children().first().removeClass('panel-tool-max');
            $('#ajxxWin').siblings('div').children().last().children().first().addClass('panel-tool-huanyuan');
            // $('#ajxxWin').children('.panel').width(curWin_width);
            // $('#ajxxWin').children('.panel').height(curWin_height);
            // if(curWin_width == '1366'){
            // $('#pcblWin_ajcCcontent').width(curWin_width/0.8);
            // $('#pcblWin_ajcCcontent').height(curWin_height/0.8);
            // $('#pcWinpan').width(curWin_width/0.8);
            // $('#pcWinpan').height(curWin_height/0.8);
            // $('#pcWinpan').children('.panel').width(curWin_width/0.8);
            // $('#pcWinpan').children('.panel').height(curWin_height/0.8);
            // $('#pcWin_ajxxdiv').width(curWin_width/0.8);
            // $('#pcWin_ajxxdiv').height(curWin_height/0.8 - 37);
            // $('#pcWin_ajxxdiv').siblings('div').width(curWin_width/0.8 - 10);
            // }else{
            // $('#pcblWin_ajcCcontent').width(curWin_width);
            // $('#pcblWin_ajcCcontent').height(curWin_height);
            // }
        },
        onRestore:function(){
            $('#ajxxWin').parent('.window').width(ajxxWindowWidth);
            $('#ajxxWin').parent('.window').height(ajxxWindowHeight);
            $('#ajxxWin').siblings('div').width(ajxxWindowWidth);
            $('#ajxxWin').parent('.window').siblings('.window-shadow').width(ajxxWindowWidth);
            $('#ajxxWin').siblings('div').children().last().children().first().removeClass('panel-tool-huanyuan');
            $('#ajxxWin').siblings('div').children().last().children().first().addClass('panel-tool-max');
        }
    });
    // 退出
    $("#signOut").unbind( "click" );
    $("#signOut").bind("click",function () {
        signOut();
    });

    // 右上角法律法规与案例库从数据库获取地址
    getFalvAndResource();

    // 个人收藏
    inti_personal_favorite();

    // 案例库绑定事件
    $("#index_right_one").click(function () {
        try {
            // 调用本机默认程序打开附件
            //boundObjectForJS.callEXE("iexplore.exe," + ALKHTML);
            boundObjectForJS.callDefaultEXE(ALKHTML);

        }catch (e){
            $("#iframe_alk").attr('src',ALKHTML);
            $("#index_window_alk").window('open');
        }
    });

    // 法律法规绑定事件
    $("#index_right_two").click(function () {

        try {
            // 调用本机默认程序打开附件
            //boundObjectForJS.callEXE("iexplore.exe," + FALVHTML);
            boundObjectForJS.callDefaultEXE(FALVHTML);
        }catch (e){
            $("#iframe_flfg").attr('src',FALVHTML);
            $("#index_window_flfg").window('open');
        }
    });

}

// 标签初始化数据加载
function home_marksDataBind() {

    //加载用户信息
    load_userifno();

    // 加载功能菜单
    load_menu();

    // 加载通知公告
    load_notice();

    //加载右上角消息
    load_xiaoxi();

}

// 用户信息加载
function load_userifno() {

    $.ajax({
        url: getRootPath() + "/account/getUserInfo",
        type: 'post',
        async: false,
        dataType: 'json',
        success: function (data) {
            if (data.status == 200){
                userInfo = data.value;
                userFuction();
               get_userJsbm();
            }else if(data.status ==500){
                //获取用户信息失败，重新登陆：
                goLogin();
            }else {
                alert("获取用户信息失败！");
            }
        }
    });

}

//展示用户信息、特色功能：
function userFuction() {

    //用户信息
    $("#index_right_username").text(userInfo.MC);

    //用户名的下拉菜单
    $("#index_right_three").mousemove(function(){
        $(this).children("div").show();
    });
    $("#index_right_three").mouseout(function(){
        $(this).children("div").hide();
    });
}
//获取人员角色编码
function get_userJsbm() {
    $.ajax({
        url: getRootPath() + "/account/getJsbm",
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (data) {
            //将角色编码信息放入userinfo中
            userInfo.JSBMS = data.value;
            if (data.status != 200) {
            }
        }
    });
}

// 加载功能菜单
function load_menu() {
    $.ajax({
        url: getRootPath() + "/account/functionList",
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (data) {
            if (data.status != 200) {
                goLogin();
            }

            // 缓存用户功能权限
            FUNCTION_LIST = data.value;
            // 生成功能菜单
            var html = '';
            FUNCTION_LIST.forEach(function(v,index){
                var a = "<div class='content_left_box' id='"+v.icon+"'>";
                var b = '';
                if(v){
                    if(v.lx == '1'&& v.functionList.length ==1) {
                        var tempGnbm = v.functionList[0].gnbm;
                        var tempGnmc = v.functionList[0].gnmc;//一级菜单名称用二级菜单替换
                        b = '<img src="image/cqe/dic/'+ v.icon +'.png" style="margin-top: 20px" class="img_one"/ >' +
                            '<img src="image/cqe/dic/'+ v.icon +'-.png" style="margin-top: 20px;display: none" class="img_two"/ >' +
                            '<div class="content_left_box_text" data-gnbm='+ tempGnbm +'>'+ tempGnmc +'</div>'+
                            '<div style="width: 5px;height: 5px;position:relative" id="'+ v.flxh +'" ></div>'
                    }else {
                        b = '<img src="image/cqe/dic/'+ v.icon +'.png" style="margin-top: 20px" class="img_one"/ >' +
                            '<img src="image/cqe/dic/'+ v.icon +'-.png" style="margin-top: 20px;display: none" class="img_two"/ >' +
                            '<div class="content_left_box_text">'+ v.flmc +'</div>'+
                            '<div style="width: 5px;height: 5px;position:relative" id="'+ v.flxh +'" ></div>'
                        //最后一个二级菜单位置
                        if(index==FUNCTION_LIST.length-2 || index==FUNCTION_LIST.length-1){
                            b += '<div class="lefttwo" style="position: absolute;left: 128px;bottom: 0;background: #154a8c;width: 230px;overflow:hidden;display: none">'
                        }else{
                            b += '<div class="lefttwo" style="position: absolute;left: 128px;top:-1px;background: #154a8c;width: 230px;overflow:hidden;display: none">'
                        }
                        if(v.functionList && v.functionList.length > 0){
                            var d1 = '<ul>';
                            var d2 = '';
                            //李志恒 2018年4月11日 暂时隐藏专题分析功能
                                v.functionList.forEach(function(jsonObj){
                                    if(jsonObj.gnsm!='N'){
                                        d2 += '<li class="lefttwo_text" id="'+ jsonObj.gnbm +'"><div class="lefttwo_left" style="float: left;"><img src="image/cqe/func/'+ jsonObj.icon +'.png"></div><div class="lefttwo_left_text">'+ jsonObj.gnxsmc +'</div><div class="lefttwo_right" style="float: right;"><img src="image/index/arrow.png"></div></li>'
                                    }
                                })
                            var d3 = '</ul>'
                            b += d1+d2+d3
                        }
                    }
                }
                var c = '</div></div>';
                html += a+b+c;

            });

            $('#gncd_ping').html(html);

            //加载主页
            load_home();

            // 功能菜单样式设置
            init_menu();

            //加载右侧快捷界面
            load_fast();

            //二级菜单整合到一级菜单
            init_click();

        },
        error: function (request, status, ex) {
            // alert(ex);
            goLogin();
        }

    });
}

//二级菜单整合到一级菜单
function init_click() {
    $('.content_left_box').click(function () {
        var curGnbm = $(this).children('.content_left_box_text').attr('data-gnbm');
        if(curGnbm != undefined){
            var func = getFunction(curGnbm);
            if (!func) {
                return;
            }
            $('.content_left_box:first-child').removeClass('content_left_box_click');
            $(this).addClass('content_left_box_click');
            $(this).siblings().removeClass('content_left_box_click');
            // var funcParams = null;
            // if(!isNull(func.gncs)){
            //     funcParams = func.gncs;
            // }
            load_function(func.gnxsmc, func.gnct, func.gncs,1);
        }
    });
}

// 功能菜单样式设置
function init_menu() {
    // 鼠标移上一级菜单样式设置
    $(".content_left_box").mousemove(function(){
        // alert("移入一级菜单添加效果")
        $(this).children(".img_one").hide();
        $(this).children(".img_two").show();
        $(this).children(".content_left_box_text").css("color","#fff");
        var text = $(this).children(".content_left_box_text").text();
        //alert(text)
        if(text != '工作桌面'){
            $(this).children(".lefttwo").show();
        }
        $(this).siblings().children(".lefttwo").hide();
    });

    // 鼠标移出一级菜单样式设置
    $(".content_left_box").mouseout(function(){
        var clas =$(this).attr("class");
        if( clas != 'content_left_box content_left_box_click'){
            $(this).children(".img_one").show();
            $(this).children(".img_two").hide();
            $(this).children(".content_left_box_text").css("color","#4a9bff");
            $(this).children(".lefttwo").hide();
        }
    });

    // 工作桌面菜单样式设置
    $(".content_left_box:first").children(".content_left_box_text").css("color","#fff");
    $(".content_left_box:first").children(".img_one").hide();
    $(".content_left_box:first").children(".img_two").show();
    $(".content_left_box:first").addClass("content_left_box_click");
    $(".content_left_box:first").children(".lefttwo").hide();
    $(".content_left_box:first").css("border-top","1px solid #4a9bff");
    $(".content_left_box:first").click(function(){
        $(this).addClass("content_left_box_click");
        $(this).siblings().removeClass("content_left_box_click");
        $("#index_content_center_top").hide();
        $("#p_panel").css("height","100%");
        $("#p").css("border-radius","10px");
        $("#index_content_right").show();
    });
    // 工作桌面加载
    $("#gzzm_left .content_left_box").click(function(){
        $(this).parents(".gncd").siblings().children(".content_left_box_click").removeClass("content_left_box_click")
        $(this).parents(".gncd").siblings().children().children(".img_one").show();
        $(this).parents(".gncd").siblings().children().children(".img_two").hide();
        $(this).parents(".gncd").siblings().children().children(".content_left_box_text").css("color","#4a9bff")
    });
    $(".content_left_box:first").click(function(){
        load_home();
    });

    // 鼠标移出一级菜单样式设置
    $(".content_left_box").mouseout(function(){
        var clas =$(this).attr("class");
        if( clas != 'content_left_box content_left_box_click'){
            $(this).children(".img_one").show();
            $(this).children(".img_two").hide();
            $(this).children(".content_left_box_text").css("color","#4a9bff");
            $(this).children(".lefttwo").hide();
        }
    });

    // 鼠标移出二级菜单时，隐藏二级菜单
    $(".lefttwo").mouseout(function(){
        $(this).hide();
    });

    // 功能菜单点击事件
    $(".lefttwo_text").click(function(){
        // 一级菜单选中样式设置
        $(this).parents(".content_left_box").addClass("content_left_box_click");
        $(this).parents(".content_left_box").siblings().removeClass("content_left_box_click");
        $(this).parents(".content_left_box").siblings().children(".img_one").show();
        $(this).parents(".content_left_box").siblings().children(".img_two").hide();
        $(this).parents(".content_left_box").siblings().children(".content_left_box_text").css("color","#4a9bff");

        // 二级菜单选中样式设置
        $(this).children(".lefttwo_right").show();
        $(this).children(".lefttwo_left_text").css("color","#fff");
        $(this).siblings().children(".lefttwo_right").hide();
        $(this).siblings().children(".lefttwo_left_text").css("color","#c2e7ff");
        $(this).parents(".content_left_box").siblings().children(".lefttwo").children().children().children(".lefttwo_right").hide();
        $(this).parents(".content_left_box").siblings().children(".lefttwo").children().children().children(".lefttwo_left_text").css("color","#c2e7ff");
        $(this).parents(".gncd").siblings().children(".content_left_box").removeClass("content_left_box_click");
        $(this).parents(".gncd").siblings().children(".content_left_box").children(".img_one").show();
        $(this).parents(".gncd").siblings().children(".content_left_box").children(".img_two").hide();
        $(this).parents(".gncd").siblings().children(".content_left_box").children(".content_left_box_text").css("color","#4a9bff");
        $("#index_content_center_top").show();
        $("#p_panel").css("height","calc(100% - 40px)");
        $("#p").css("border-radius","0 0 10px 10px");
        $("#index_content_right").hide();

        // 功能加载
        var id = $(this).attr('id');
        var func = getFunction(id);
        if (!func) {
            return;
        }
        // var funcParams = null;
        // if(!isNull(func.gncs)){
        //     funcParams = JSON.parse(func.gncs);
        // }


        load_function(func.gnxsmc, func.gnct, func.gncs,1);

    });

}

// 快捷菜单数据加载
function load_fast() {
    $.ajax({
        url: getRootPath() + "/account/getKjcd",
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (data) {
            if (data.status == 200){
                load_kjrk_content(data.value);
            }else {
                Alert(data.note);
            }
        }
    });

    init_fast();
}

// 生成快捷菜单
function load_kjrk_content(kjrk) {

    FAST_LIST = kjrk;

    var kjrkhtml = '';
    var kjrkcontent ='';
    kjrk.forEach(function(x){
        var func = getFunction(x.GNBM);
        if(func){
            kjrkcontent = '<li class="'+ func.gnbm+'">'+'<img src="image/cqe/fast/func/'+ func.icon+'.png" class="box_li_one">' +
                '<img src="image/cqe/fast/func/'+ func.icon+'-.png" style="display: none" class="box_li_two">' +
                '<div class="kjrk_content_text">'+ func.gnxsmc+'</div>'
        }

        kjrkhtml += kjrkcontent
    });
    $('#kjrk_content_box ul').html(kjrkhtml);

    init_fast();
}

// 快捷菜单样式设置
function init_fast() {

    // 鼠标移上功能条样式设置
    // $("#index_content_right").mousemove(function(){
    //     $(this).css("right","0")
    //     // $("#kjrk_one").hide();
    //     // $("#kjrk_two").show();
    //     $("#kjrk").hide();
    //     $("#kjrk_content").css("float","right");
    // });

    // 鼠标移出功能条样式设置
    // $("#index_content_right").mouseout(function(){
    //     $(this).css("right","-115px");
    //     // $("#kjrk").show();
    //     $("#kjrk_one").show();
    //     $("#kjrk_two").hide();
    // });

    // 鼠标移上功能样式设置
    $("#kjrk_content #kjrk_content_box  ul li").mouseover(function(){
        $(this).children(".box_li_one").hide();
        $(this).children(".box_li_two").show();
    });

    // 鼠标移出功能样式设置
    $("#kjrk_content #kjrk_content_box  ul li").mouseout(function(){
        $(this).children(".box_li_one").show();
        $(this).children(".box_li_two").hide();
    });

    // 绑定菜单点击事件
    $("#kjrk").click(function(){
        var right = $("#index_content_right").css("right");
        if(right == "0px"){
            $("#index_content_right").css("right","-115px");
            $("#kjrk_one").show();
            $("#kjrk_two").hide();
        }
        else{
            $("#index_content_right").css("right","0px");
            $("#kjrk_one").hide();
            $("#kjrk_two").show();
        }
    });

    $("#kjrk_content_box ul li").click(function () {
        //快捷菜单功能加载
        var classs = $(this).attr('class');
        var func = getFunction(classs);
        if (!func) {
            return;
        }
        // var funcParams = null;
        // if(!isNull(func.gncs)){
        //     funcParams = JSON.parse(func.gncs);
        // }
        load_function(func.gnxsmc, func.gnct, func.gncs);
        $("#p_panel").css("height","calc(100% - 40px)");
        $("#p").css("border-radius","0 0 10px 10px");
        $("#index_content_right").hide();
    })
}

// 快捷入口弹窗
function window_kjrk_content_tj() {

    // 功能列表
    $('#table_kjrk_content_ktj').datagrid({
        striped: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        resizable:false,
        idField: 'GNBM',
        columns: [[
            {field: 'GNBM', title: '功能编码', hidden:true},
            {field: 'GNMC', title: '功能名称',width:'198px'}
        ]]
        /*onClickRow: function (rowIndex, rowData) { //人员信息表格单行高亮
         $('#table_kjrk_content_ktj').datagrid('clearSelections');
         $('#table_kjrk_content_ktj').datagrid('highlightRow', rowIndex);
         }*/
    });
    $('#table_kjrk_content_ktj').datagrid('loadData', []);

    //添加功能列表
    $("#table_kjrk_content_ytj").datagrid({

        striped: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        resizable:false,
        idField: 'GNBM',
        columns: [[
            {field: 'GNBM', title: '功能编码', hidden:true },
            {field: 'GNMC', title: '功能名称',width:'198px'}
        ]]
    });
    $('#table_kjrk_content_ytj').datagrid('loadData', FAST_LIST);

    //生成快捷菜单可添加选项
    FUNCTION_LIST.forEach(function (n) {
        if(n.functionList && n.functionList.length > 0){
            n.functionList.forEach(function(jsonObj){
                if (!isFastFunction(jsonObj.gnbm)) {
                    $('#table_kjrk_content_ktj').datagrid('appendRow',{
                        GNBM: jsonObj.gnbm,
                        GNMC: jsonObj.gnxsmc
                    });
                }
            })
        }
    });
    //从可添加功能列表移除
    $("#window_kjrk_button_tj").unbind("click");
    $("#window_kjrk_button_tj").click(function () {

        var remover = $("#table_kjrk_content_ktj").datagrid("getSelected");
        $('#table_kjrk_content_ytj').datagrid('appendRow',{
            GNBM: remover.GNBM,
            GNMC: remover.GNMC
        });
        var index = $("#table_kjrk_content_ktj").datagrid("getRowIndex", remover.GNBM);
        $("#table_kjrk_content_ktj").datagrid("deleteRow", index);
    });
    //从已添加功能列表移除
    $("#window_kjrk_button_yc").unbind("click");
    $("#window_kjrk_button_yc").click(function () {
        var remover = $("#table_kjrk_content_ytj").datagrid("getSelected");
        $('#table_kjrk_content_ktj').datagrid('appendRow',{
            GNBM: remover.GNBM,
            GNMC: remover.GNMC
        });
        var index = $("#table_kjrk_content_ytj").datagrid("getRowIndex", remover.GNBM);
        $("#table_kjrk_content_ytj").datagrid("deleteRow", index);
    });
    //获取已添加列表的数据添加到快捷入口
    $("#table_kjrk_qd").unbind("click");
    $("#table_kjrk_qd").click(function () {
        var huoqu = $("#table_kjrk_content_ytj").datagrid("getRows");

        $.ajax({
            url: getRootPath() + "/account/addKjcd",
            data: { json: JSON.stringify(huoqu)},
            type: 'post',
            async: true,
            dataType: 'json',
            success: function (data) {
                if (data.status == 200){
                    load_kjrk_content(data.value);
                }else {
                    Alert(data.note);
                }
            }
        });

        load_kjrk_content(huoqu);
        $("#window_kjrk_content_tj").window('close');
    })

    $("#window_kjrk_content_tj").show();
    $("#window_kjrk_content_tj").window({
        title:'快捷入口',
        width:600,
        height:400,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        modal:true,
        resizable:true
    });

}

// 加载通知公告
function load_notice() {


    $.ajax({
        url: getRootPath() + "/account/getTzgg",
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (data) {
            if (data.status == 200){
                NOTICE_LIST = data.value;
                if(!NOTICE_LIST) return;

                var marqueecontent = '';
                for (var i = 0; i < NOTICE_LIST.length; i++){
                    marqueecontent += "<span class=\"marquee_span\" ><a href=\"#\" onclick=\"show_notice_window('" + NOTICE_LIST[i].BH + "')\">" + (i + 1) + "." + NOTICE_LIST[i].BT + "</a></span>";
                }
                $("#marqueetext").html(marqueecontent);

                /*var marqueehtml = '';
                 var marqueecontent = '';
                 var a = '<div class="marqueediv1">';
                 var b = '</div>'
                 marqueetext.forEach(function(l){
                 marqueecontent += '<span>'+ l.BT +'</span>'
                 })
                 marqueehtml += a + marqueecontent + b;
                 $(".marqueetext").html(marqueehtml+=marqueehtml);
                 var width = $('.marqueediv1').width();

                 var a =(2*width)+(width/4);
                 var c = width;
                 setInterval(function(){
                 a--;
                 c--;
                 if(a<(-width-120)) a=width+(width/2);
                 if(c<(-width-120)) c=width+(width/2);
                 $('.marqueediv1:eq(0)').css('left',c+'px');
                 $('.marqueediv1:eq(1)').css('left',a+'px')
                 },20);*/
            }else {
                //Alert(data.note);
            }
        }
    });
}

// 加载右上角消息数量
function load_xiaoxi() {

    $.ajax({
        url: getRootPath() + "/message/getMessageCount",
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (res) {
            if (res.code == 200) {
                //右上角消息绑定
                $("#index_msg_cnt").text(res.data);
            }
        }

    });

}

// 通知公告弹窗
function show_notice_window(bh) {


    $.ajax({
        url: getRootPath()+'/notice/getNotice',
        data: { bh: bh},
        dataType: 'json',
        success: function (data) {
            if (data.code==200){
                var  notice=data.data;
                $("#marqee_window_content_top").text(notice.bt);
                $("#marqee_window_content_mainbody").textbox('setText', notice.nr);
                $("#marquee_window_user").html(notice.fbrdwmc);
                $("#marquee_window_time").html(sjzh(notice.cjsj));
                if(isNull(notice.fj)){
                    $('#tzgg_fjload').css('display', 'none');
                }else{
                    $("#marquee_window_href").attr("href",getRootPath()+notice.fj);
                    $("#marquee_window_mc").text(notice.zlmc);
                    $('#tzgg_fjload').css('display', '');
                }
                $("#marquee_window").show();
            }
        }
    });

    $("#marquee_window").window({
        title: '通知',
        width: 900,
        height: 600,
        top: screen.width < 1900 ? "3%" : "20%",
        collapsible: false,
        minimizable: false,
        maximizable: false,
        modal: true,
        resizable: true
    });




}

// 预览通知公告
function seen_tzgg() {

    // 下载附件
    try{
        // 获取下载中的地址
        var notice_url = $("#marquee_window_href").attr('href').replace(getRootPath(),'');
        var localFile = boundObjectForJS.downloadFile(getRootPath() + notice_url + "," + LOCAL_PATH + notice_url);
        // 调用本机默认程序打开附件
        boundObjectForJS.callDefaultEXE(localFile);
        CloseProgress();
    }catch (e){
        CloseProgress();
    }

}


// 修改密码弹窗
function yhxx_winUpdPwd() {
    $("#winUpdPwd").window({
        title: '修改密码',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        modal: true,
        resizable: true
    })
    alertWinUpdPwd()
}

// 修改密码
function alertWinUpdPwd() {
    $('#pwdUser').textbox('disable');
    $('#pwdUser').textbox('setValue', userInfo.MC);
    $('#pwdOldPwd').textbox('setValue', '');
    $('#pwdNewPwd').textbox('setValue', '');
    $('#pwdNewPwd2').textbox('setValue', '');
    $('#btnUpdPwdCancel').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#winUpdPwd').window('close');
        }
    });
    $('#btnUpdPwdConfirm').linkbutton({
        iconCls: 'icon-ok',
        onClick: function () {
            var pass_old = $('#pwdOldPwd').val();
            var pass_new = $('#pwdNewPwd').val();
            var pass_new2 = $('#pwdNewPwd2').val();
            if (pass_new != pass_new2) {
                Alert('两次输入密码不一致！');
                return;
            }
            if (pass_new.length < 3) {
                Alert('密码长度过短！');
                return;
            }
            $.ajax({
                url: getRootPath()+'/account/UpdatePassword',
                data: { pass_old: pass_old, pass_new: pass_new },
                dataType: 'json',
                success: function (data) {
                    if (data && data.status == 200) {
                        Alert("密码修改成功！"); //提示修改成功
                    }
                    else {
                        ShowError(data.note);
                    }
                }
            });
            $('#winUpdPwd').window('close');
        }
    });
    $('#winUpdPwd').window('open');
}

// 消息通知弹窗
function index_window_tzxx() {
    $("#index_window_tzxx").window('open');
    //消息通知表格
    $("#index_window_tzxx_datagrid").datagrid({
        width:'100%',
        pagination:true,
        pageSize:10,
        pageNumber:1,
        rownumbers:true,
        fitColumns: true,
        autoRowHeight:true,
        singleSelect: false,
        nowrap:false,
        striped:true,
        columns:[[
            {field: 'ck', title: '复选框', checkbox: true},
            {field:'xxxh',title:'序号', hidden: true },
            {field:'xxbt',title:'标题', width: 100 },
            {field:'xxnr',title:'内容', width: 400 },
            {field:'fsrq',title:'时间', width: 150 ,formatter: function (value) {
                var temp=new Date(value)
                var year=  temp.getFullYear();
                var month=  (temp.getMonth()+1);
                var day= temp.getDate();
                var hour= temp.getHours();
                var minute= temp.getMinutes();
                return year+'年'+month+'月'+day+'日'+hour+'时'+minute+'分';
                // return sjzhsrq(value);
            }}/*,
             {field: 'action', title: '操作', width: 100 , align: 'center',
             formatter: function (value, row, index) {
             var r = '<a href="#" onclick="look_message(false ,'+ index + ')">查看</a> ';
             return r;
             }
             }*/
        ]]
    });
    // 分页控件(中文)
    $('#index_window_tzxx_datagrid').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录',
        onBeforeRefresh:function(){
            //alert('before refresh');  //当点击触发事件时 没有弹出来
        },
        onSelectPage:function(pageNumber, pageSize){
            $(this).pagination('loading');
            $(this).pagination('loaded');
            load_index_window_tzxx_datagrid();
        }
    });
    load_index_window_tzxx_datagrid();
}
$("#index_window_tzxx").window({
    onBeforeClose:function(){
        bjwydByDwbmAndGh();
    }
});

function bjwydByDwbmAndGh() {
    $.ajax({
        url: getRootPath() + "/message/updateMessageZtByDwbmAndGh",
        contentType:"application/json",
        type: 'get',
        async: false,
        dataType: 'json',
        success: function (res) {

        }
    });


}
//标记为已读
function  bjwyd() {
    var data = $('#index_window_tzxx_datagrid').datagrid('getChecked');
    var ids=new Array();
    if(data.length==0){
        Alert("请选择数据！");
    }
    for (var i = 0; i < data.length; i++) {
        ids.push(data[i].xxxh);
    }
    var obj=  new Object();
    obj.ids=ids;
    $.ajax({
        url: getRootPath() + "/message/batchUpdateXxzt",
        data:JSON.stringify(obj),
        contentType:"application/json",
        type: 'post',
        async: false,
        dataType: 'json',
        success: function (res) {
            if (res.code == 200) {
                Alert(res.message);
                load_index_window_tzxx_datagrid();
            }
        }
    });

}

// 加载消息列表
function load_index_window_tzxx_datagrid() {

    var obj = new Object();
    obj.xxzt = $("#index_window_tzxx_select").combobox('getValue');
    obj.searchName = $("#txt_index_window_tzxx_keyword").val();
    obj.startTime = $('#date_index_window_tzxx_beg').datebox('getValue');
    obj.endTime = $('#date_index_window_tzxx_end').datebox('getValue');
    var gridOpts = $('#index_window_tzxx_datagrid').datagrid('getPager').data('pagination').options;
    obj.page = gridOpts.pageNumber;
    obj.pageSize = gridOpts.pageSize;
    $.ajax({
        url: getRootPath() + "/message/getMessageList",
        data:JSON.stringify(obj),
        contentType:"application/json",
        type: 'post',
        //async: false,
        dataType: 'json',
        success: function (res) {
            if (res.code == 200) {
                $('#index_window_tzxx_datagrid').datagrid('loadData', {total: res.total, rows: res.data});
            }
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
        }
    });
}

// 工作桌面加载
function load_home() {

    $('#p').panel({
        width:'100%',
        fit:true,
        href:HOME_URL
    });

    $("#index_content_right").show();
    // 记录上一个加载的组件
    if(Quene.length < 2){
        Quene.push({
            title: '',
            href: HOME_URL,
            param: ''
        })
    }else{
        Quene.splice(0,1)
        Quene.push({
            title: '',
            href: HOME_URL,
            param: ''
        })
    }
}

// 跳转到登录页面
function goLogin() {
    window.location.href = LOGIN_URL;
}

// 退出登录
function signOut() {

    $.messager.confirm('确认','您确认想要退出吗？',function(r){
        if (r){
            $.ajax({
                url: getRootPath() + "/account/signOut",
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (res) {
                    if (res.status == 200) {
                        goLogin();
                    } else {
                        $.messager.show({
                            title:'警告',
                            msg:res.note+'该系统将在5s内强制退出',
                            timeout:5000,
                            showType:'slide'
                        });
                    }
                },
                error: function () {
                    // $.messager.show({
                    //     title:'警告',
                    //     msg:'推出失败,该系统将在5s内强制退出',
                    //     timeout:5000,
                    //     showType:'slide'
                    // });
                    goLogin();
                }

            });
        }
    });
}

// 获取功能详细信息
function getFunction(id){
    var func;
    for (var i = 0; i < FUNCTION_LIST.length; i++) {
        var funn = FUNCTION_LIST[i];
        for (var j = 0; j < funn.functionList.length; j++){
            var gnbm = funn.functionList[j].gnbm;
            if (gnbm != id)
                continue;

            // 获取该角色所拥有功能参数
            var qxcs = [];
            if (!isAdministrator()) {
                $.ajax({
                    type: 'post',
                    async:false,
                    url: getRootPath()+ '/account/getGncsByDwbm',
                    data: { gnbm: gnbm },
                    dataType: "json",
                    success: function (result) {

                        if(result.status == 200 && !isNull(result.value)){
                            qxcs = result.value;
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        Alert("获取功能参数失败");
                        return func;
                    }
                });
            }

            // 整合功能参数及权限册数
            func = new Object();
            func.gnbm = funn.functionList[j].gnbm;
            func.gnmc = funn.functionList[j].gnmc;
            func.flbm = funn.functionList[j].flbm;
            func.gncxj = funn.functionList[j].gncxj;
            func.gnct = funn.functionList[j].gnct;
            func.gnsm = funn.functionList[j].gnsm;
            func.gnxh = funn.functionList[j].gnxh;
            func.gnxsmc = funn.functionList[j].gnxsmc;
            func.dwbm = funn.functionList[j].dwbm;
            func.cscs = funn.functionList[j].cscs;
            func.sfmtck = funn.functionList[j].sfmtck;
            func.icon = funn.functionList[j].icon;
            func.sfmtck = funn.functionList[j].sfmtck;
            func.gncs = { gncs: funn.functionList[j].gncs, qxcs: qxcs};

            return func;
        }
    }

    return func;
}

// 加载功能页面，提供给其他页面调用，比如新建评查
function load_function(title, href, param, yj) {

    // 标题栏
    if (isNull(title)) {
        $("#index_content_center_top").hide();
        $("#p_panel").css("height","100%");
        //$("#index_content_right").show();
    } else {
        $("#index_content_title").text(title);
        $("#index_content_center_top").show();
        $("#p_panel").css("height","calc(100% - 40px)");
        //$("#index_content_right").hide();
    }

    // 内容区
    $('#p').panel({
        fit:true,
        href:href
    });

    if(href == HOME_URL){
        $("#index_content_right").show();
    }else{
        $("#index_content_right").hide();
    }

    // 换从功能参数，作为参数传递
    FUNCTION_PARAM = param;
    $('.disNone').removeClass('disNone');
    if(yj){
        $('.index_back').addClass('disNone');
        Quene=[];
    }else {

        // 记录上一个加载的组件
        if(Quene.length < 2){

            Quene.push({
                title: title,
                href: href,
                param: param
            })
        }else{
            if(Quene[1].href!=href){
                Quene.splice(0,1)
                Quene.push({
                    title: title,
                    href: href,
                    param: param
                })
            }
        }

    }
}

// 返回工作桌面
function backgzzm_function(){
    $("#p_panel").css("height",'100%');
    $('#p').panel({
        fit:true,
        href:HOME_URL
    });
    $("#index_content_center_top").hide();
    $("#index_content_right").show();
}

// 返回上一个组件
function lastView_function(){
    // 标题栏
    if (isNull(Quene[0].title)) {
        $("#index_content_center_top").hide();
        $("p_panel").css("height","100%");
        //$("#index_content_right").show();
    } else {
        $("#index_content_title").text(Quene[0].title);
        $("#index_content_center_top").show();
        $("#p_panel").css("height","calc(100% - 40px)");
        //$("#index_content_right").hide();
    }
    // 内容区
    $('#p').panel({
        fit:true,
        href:Quene[0].href
    });
    Quene.splice(1,1);
    if(Quene[0].href == HOME_URL){
        $("#index_content_right").show();
    }else{
        $("#index_content_right").hide();
    }
    if(Quene[0].param){
        FUNCTION_PARAM = Quene[0].param;
    }else{
        FUNCTION_PARAM = {};
    }
}

// 获取当前页面Href参数，返回JSON对象
function getCurrentFunctionParams() {
    // var paramStr = $('#p').panel('options').href.split("?")[1];
    // return JSON.parse(getQueryString(paramStr, 'json'));
    return FUNCTION_PARAM;
}

// 获取当前页面Href参数，返回JSON对象
function getCurrentFunctionUrlParam(key) {
    var paramStr = $('#p').panel('options').href.split("?")[1];
    return getQueryString(paramStr, 'key');
}

// 是否快捷菜单
function isFastFunction(id) {
    var result = false;
    for (var i=0; i<FAST_LIST.length; i++){
        if (FAST_LIST[i].GNBM == id)
        {
            result = true;
            break;
        }
    }

    return result;
}

// 获取法律法规
function getFalvAndResource() {
    $.ajax({
        url: getRootPath() + "/systemConfig/getPz",
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (res) {
            if (res != null) {
                //获取法律法规地址
                FALVHTML = res.FALVHTML;
                //获取案例库地址
                ALKHTML= res.ALKHTML;
                DJDWBM = res.DJDWBM;
            }
        }

    });
}

// 个人收藏
function inti_personal_favorite() {

    // 我的收藏弹窗
    $("#wdsc").click(function () {
        $("#index_window_wdsc").window("open");
        init_cxtj_combobox();
        init_Person_List_DataGrid();
        load_Person_List();
    });
    $("#wdsc_search_btn").click(function () {
        load_Person_List();
    });
    //上传文件
    index_wind_fileUplpload = function index_wind_fileUplpload() {
        if ($('#wjsc_zlmc').textbox("getValue") == "") {
            Alert("请输入资料名称！");
            return;
        }
        var path = getRootPath() + '/person/savePerson';
        var formData = new FormData($("#fileUploadPerson")[0]);
        $.ajax({
            url: path,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            dataType: "json",
            processData: false,
            success: function (data) {
                $('#wjsc_zlmc').textbox("setValue", "");
                $('#index_submit_file').window('close');
                Alert(data.note);
                load_Person_List();
            },
            error: function (data) {
                Alert(data);
            }

        })
    }
    function load_Person_List() {
        var zlmc = $("#search_zlmc").textbox("getValue");
        var zllx = $("#search_zllx").combobox("getValue");
        var gridOpts = $('#datagrid_easyui_resource_person_list').datagrid('getPager').data('pagination').options;
        var obj = new Object();
        obj.page = gridOpts.pageNumber;
        obj.pageSize = gridOpts.pageSize;
        obj.zlmc = zlmc;
        obj.zllx = zllx;
        $.ajax({
            url: getRootPath() + "/person/getPersonList",
            type: "get",
            async: true,
            data: obj,
            dataType: 'json',
            success: function (data) {
                if (data.status == 500) {
                    // Alert(data.note);
                    $('#datagrid_easyui_resource_person_list').datagrid('loadData', {total: 0, rows: []});
                } else {
                    $('#datagrid_easyui_resource_person_list').datagrid("loadData", data);
                }
            },
            error: function (data) {
                Alert("请求后台数据出错！请联系管理员");
            }
        });
    }

    init_Person_List_DataGrid = function init_Person_List_DataGrid() {
        $('#datagrid_easyui_resource_person_list').datagrid({
            pagination: true,
            pageNumber: 0,
            pageSize: 20,
            singleSelect: true,
            pageList: [10, 20, 30, 40, 50],
            loadMsg: '数据加载中，请稍等。。。',
            fitColumns: true,
            striped: true,
            // rownumbers:true,
            columns: [[
                {
                    field: ' ', title: '', width: 15, formatter: function (value, row, index) {
                    return index + 1;
                }, styler: function (value, row, index) {
                    return 'text-align:center;';
                }
                },
                {field: 'id', title: '评查受理编码', hidden: true},
                {
                    field: 'zllx', title: '资料类型', width: 90, formatter: function (value) {
                    if (value == 1) {
                        return "文件";
                    } else if (value == 2) {
                        return '案件';
                    }
                }
                },
                {field: 'zlmc', title: '资料名称', width: 200},
                {field: 'gyzy', title: '关联资源', width: 90, hidden: true},
                {
                    field: 'cjsj', title: '收藏时间', width: 150, formatter: function (value) {
                    var temp = new Date(value)
                    var year = temp.getFullYear();
                    var month = (temp.getMonth() + 1);
                    var day = temp.getDate();
                    return year + '年' + month + '月' + day + '日';
                }
                },
                {field: 'dwbm', title: '单位编码', width: 140, hidden: true},
                {field: 'gh', title: '工号', width: 80, hidden: true},
                {field: 'sfsc', title: '是否有效', hidden: true},
                {field: 'gxbm', title: '共享编码', hidden: true},
                {
                    field: 'cz',
                    title: '操作',
                    width: 120,
                    fixed: true,
                    align: 'center',
                    formatter: function (value, row, index) {
                        var r = '<a href="#" onclick="index_update_person(' + index + ')">修改</a> ';
                        var d = '<a href="#" onclick="index_del_PersonById(' + index + ')">移除</a> ';
                        var curType = row.zllx;
                        if (curType == "1") {
                            var c = '<a href="' + getRootPath() + row.gyzy + '" download="" >下载</a>'
                        } else if (curType == "2") {
                            // var c = '<a href="#">查看</a> ';
                            var c = '<a href="#" onclick="pcWinPage(' + index + ',\'#datagrid_easyui_resource_person_list\')">查看</a>'
                        }
                        return r + d + c;
                        // return  k+r+c+d;
                    }
                }
            ]]
        });
    }
    // 删除我的收藏
    index_del_PersonById = function (index) {

        var rowDatas = $('#datagrid_easyui_resource_person_list').datagrid('getRows');
        var id = rowDatas[index].id;

        Confirm("确认", "是否删除？", function (r) {
            if (r) {
                $.ajax({
                    url: getRootPath() + "/person/delPerson",
                    data: {'id': id},
                    type: 'get',
                    async: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.status == 200) {
                            load_Person_List();
                            Alert('删除我的收藏成功');
                            // return data.value;
                        } else {
                            Alert(data.note);
                        }
                    }
                });
            }
        });
    }

    // 修改我的收藏
    index_update_person = function index_update_person(index) {
        var rowDatas = $('#datagrid_easyui_resource_person_list').datagrid('getRows');
        var zlmc = rowDatas[index].zlmc;
        var id = rowDatas[index].id;
        //创建时间格式化
        var cjsj = rowDatas[index].cjsj;
        var temp = new Date(cjsj)
        var year = temp.getFullYear();
        var month = temp.getMonth();
        var day = temp.getDay();
        cjsj = year + '年' + month + '月' + day + '日';
        //资料类型格式化
        var zllx = rowDatas[index].zllx;
        if (zllx == 1) {
            zllx = "文件";
        } else if (zllx == 2) {
            zllx = "案件";
        }
        $('#cbt_win_person_zllx').textbox('setValue', zllx);
        $('#cbt_win_person_zlmc').textbox('setValue', zlmc);
        $('#cbt_win_person_cjsj').textbox('setValue', cjsj);
        $('#cbt_win_person_id').val(id);
        $('#index_update_person_window').window('open');
    }

    cancel_Update_person = function cancel_Update_person() {
        $('#index_update_person_window').window('close');
    }
    //添加按钮-上传文件
    $('#scAdd_btn').click(function () {
        $("#fileUploadPerson").form('reset');
        $('#index_submit_file').window('open');
    });
    $('#cancle_sc').click(function () {
        $('#index_submit_file').window('close');
        $('#wjsc_zlmc').textbox("setValue", "");
    });
    //修改我的收藏里面的信息
    save_org_person_info = function save_org_person_info() {
        var obj = new Object();
        obj.id = $("#cbt_win_person_id").val();
        obj.zlmc = $("#cbt_win_person_zlmc").val();

        var mc = trim($('#cbt_win_person_zlmc').textbox("getValue"));
        if (isNull(mc)) {
            alert('名称不能为空');
            return;
        }
        $.ajax({
            type: 'POST',
            url: getRootPath() + '/person/updatePerson',
            data: JSON.stringify(obj),
            contentType: "application/json",
            success: function (data) {
                //刷新数据
                load_Person_List();
            }, error: function (data) {
                Alert(JSON.parse(data).note);
            }
        });
        $('#index_update_person_window').window('close');

    }

    //获取上传文件的名称
    $("#person_upload_file").change(function (){
        var file_val = $("#person_upload_file").val();
        var index = file_val.lastIndexOf("\\");
        file_val = file_val.substring(index + 1, file_val.length);
        $("#wjsc_zlmc").textbox("setValue", file_val);
        // event.target.value='';
    });

    // 加载我的收藏查询条件下拉框
    function init_cxtj_combobox() {
        $('#search_zllx').combobox({
            idField: 'id',
            valueField: 'label',
            textField: 'value',
            editable: false,
            data: [{
                label: '1',
                value: '文件'
            }, {
                label: '2',
                value: '案件'
            }],
            onShowPanel: index_onShowPanel,
            onHidePanel: index_onHidePanel,
            onLoadSuccess: function (data) {
                index_addMousedownDiv($(this).combobox("panel").children().eq(0)[0],"search_zllx")
            }
        });
        // $('#search_zllx').combobox("setValue","1");
    }

}

$.extend($.fn.datagrid.methods, {
    getEditingRowIndexs: function(jq) {
        var rows = $.data(jq[0], "datagrid").panel.find('.datagrid-row-editing');
        var indexs = [];
        rows.each(function(i, row) {
            var index = row.sectionRowIndex;
            if (indexs.indexOf(index) == -1) {
                indexs.push(index);
            }
        });
        return indexs;
    }
});

// 办理win弹框加载
// 跳转到评查办理界面
// type类别 0：评查信息  1：案件信息
function pcWinPage(index,id,type) {

    var rowDatas = $(id).datagrid('getRows');
    if(type==''||type==null||type==undefined){
        type=0;
    }

    var obj = new Object();
    obj.PCSLBM = isNull(rowDatas[index].SPWJBM) ? rowDatas[index].PCSLBM : rowDatas[index].SPWJBM;
    obj.BMSAH = rowDatas[index].BMSAH;
    obj.PCCZLX = '0'; //0.只读 1.评查办理 2.评查审批 3.评查反馈 4.部门反馈
    obj.PCSPBM = ''; //仅评查审批阶段有
    obj.type=type;
    var url = "view/evaluate/pcblWin/deal.html";
    loadPcblWin(url, obj);
}

function loadPcblWin(href, param) {

    // 换从功能参数，作为参数传递
    FUNCTION_PARAM = param;
    if(FUNCTION_PARAM.type==1){
        skipWebPage("案件信息", getRootPath() + "/case.html", { pcslbm: "", bmsah: param.BMSAH }, '1');
    }else {
        $('#pcblWin').window('open').window('refresh', href);
    }
}

// 刷新消息数量
function refresh_xiaoxi(){
    $("#index_window_tzxx").window({
        onClose:function (data) {
            load_xiaoxi();
        }
    });
}

// 显示当前时间
function showTime(){
    var curTime = new Date();
    var curWeek = "星期" + "日一二三四五六".charAt(curTime.getDay());
    var realCurTime = sjzh(curTime);
    $('#index_top_time').html(realCurTime +"&nbsp;&nbsp;&nbsp;" + curWeek);
}

// 通过弹窗方式浏览EasyUI内容页（页面标题, 页面路径，页面参数，展示方式，回调函数名称）
// showType：0.通用浏览器弹窗; 1.专用浏览器弹窗；2.EasyUI弹窗
function skipWebPage(pageTitle, pageUrl, pageCs, showType, callBackName) {

    // 判断页面名称是否未传入
    if (!pageTitle && !pageTitle.trim()) {
        Alert("缺失页面名称");
        return;
    }
    // 判断页面路径是否未传入
    if (!pageUrl && !pageUrl.trim()) {
        Alert("缺失页面路径");
        return;
    }

    var defaultShowType = showType ? showType : '2'; //默认EasyUI弹窗
    if (defaultShowType == '1' && typeof boundObjectForJS == 'undefined') {
        defaultShowType = '2';
    }
    var url = getRootPath() + '/skip.html?' + base64encode(escape(JSON.stringify({
            "pageTitle": pageTitle,
            "pageUrl": pageUrl,
            "callBack": callBackName,
            "pageCs": pageCs
        })));
    // 传入显示类别（特定页面/普通网页）
    switch (defaultShowType) {
        case '0':
            // 对传入的数据加密，并打开处理页面
            window.open(url);
            break;
        case '1':
            // 当附加页面超过2个时，先关闭第一个页面
            boundObjectForJS.closeSplitScreen('1');

            // 对传入的数据加密，并通过特定浏览器打开处理页面
            boundObjectForJS.openSplitScreen(url + ' 1');
            break;
        default :
            // 获取需要传旭给页面的数据，进行转义和加密 ，并绑定到网址后面
            // 将地址丢给指定的iframe
            $("#iframe_win_index_skip").attr('src', url);
            // 打开窗口，显示页面
            $("#win_index_skip").window('open');
            break;
    }
}

// 2018,1,23 日（写字体）更改    动态配置登陆名称
function trends_collocate_index() {
    /*$("#span_unit_name_index").html( getSystemName()[0].unit);*/
    $("#span_system_name_index").html( getSystemName()[0].unit + getSystemName()[1].system);
}

// 判断是否为超级管理员
function isAdministrator() {
    // 如果是超级管理员则拥有所有参数
    return userInfo.SFLX == "9" ? true : false;
}

// 点击喇叭进入通知公告页面
function goto_notice_function() {
    var gnbm = userInfo.DWBM.substr(0,2)+'00000016';
    var func = getFunction(gnbm);
    if (func && !isNull(func)) {
        load_function(func.gnxsmc, func.gnct, func.gncs);
    }
}

