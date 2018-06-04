
// 待办任务列表
var Tabes;
$(document).ready(function () {
    //获取要过滤的菜单
    $.ajax({
        url: getRootPath() + "/menu/getDbrwMenu",
        type: 'get',
        async: false,
        dataType: 'json',
        success: function (data) {
            if (data.code==200){
                Tabes=data.data
            }
        }
    })

    // 生成待办任务
    var Tabeshtml='';
    var gnqxArr = new Array();
    Tabes.forEach(function(q){
        var func =  getFunction(q.gnbm);
        if (func){
            var tabesdiv = '<div class="tabs_box" id="home'+q.gnbm+'">';
            tabesdiv += q.gnmc+'<div class="tabs_box_yuan">'+ q.count +'</div>';
            tabesdiv += '</div>';
            Tabeshtml += tabesdiv;
            gnqxArr.push(q.gnbm);
        }
    });
    $('.tabs_top').html(Tabeshtml);


    $('.tabs_box:first').addClass("tabs_box_click");
    $('.tabs_box:first').children(".tabs_box_yuan").css("background","red")
    $('.tabs_box:first').css("border-radius","5px 0 0 0");
    $('.tabs_box').css("cursor","pointer");
    $('.tabs_box').click(function(){
        var val = $(this).attr("id").substring(4);
        var func = getFunction(val);
        load_function_one(func.gnct);
        $(this).addClass("tabs_box_click");
        $(this).siblings().removeClass("tabs_box_click");
        $(this).children(".tabs_box_yuan").css("background","red");
        $(this).siblings().children(".tabs_box_yuan").css("background","#4383AD");
    });
    $('.tabs_box:first').click();
    $('.tabs_box:first').addClass("tabs_box_click");
    $('.tabs_box:first').children(".tabs_box_yuan").css("background","red");
    $('.tabs_box:first').css("border-radius","5px 0 0 0");
    $('.tabs_box').click(function(){
        $(this).addClass("tabs_box_click");
        $(this).siblings().removeClass("tabs_box_click");
        $(this).children(".tabs_box_yuan").css("background","red");
        $(this).siblings().children(".tabs_box_yuan").css("background","#4383AD");
    });
});

// 加载功能
function load_function_one(href) {
    // 内容区
    $('#home_panel').panel({
        width:'100%',
        fit:true,
        href:href
    });
}
