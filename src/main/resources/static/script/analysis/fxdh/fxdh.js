
$(document).ready(function(){
    $("#index_content_center_top").hide();
    init_yedh_group();
});

//初始化页面导航分组
function init_yedh_group(){
    var ymdhArrays = getDhcdValues();
    var dhHtml, ymHtml ;
    var ymArrays = null;
    var i, j = 0;
    for(i=0; i<ymdhArrays.length; i++){
        dhHtml =
            '<div class="box">' +
            '<div class="box_top">' +
            '<div class="box_top_text">'+ymdhArrays[i].title+'</div></div>'+'<div class="xx">';
        ymArrays =  ymdhArrays[i].childs;
        for(j=0; j < ymArrays.length; j++){
            dhHtml += '' +
                ' <div class="xx_box">' +
                ' <div class="xx_box_img" id="'+ymArrays[j].id+'"  onclick=addTab("'+ymArrays[j].gndz+'","'+ymArrays[j].name+ymArrays[j].group+'","'+ymArrays[j].gnIndex+'")>' +
                // "'+ymArrays[j].gndz+'","'+ymArrays[j].name+'","'+ymArrays[j].gnIndex+'"
                '<img src="'+ymArrays[j].imageOneUrl+'" class="xx_box_img_one"></img>'+
                '<img src="'+ymArrays[j].imageTwoUrl+'" class="xx_box_img_two"></img>' +
                '</div>'+
                ' <div class="xx_box_text"  data-heads="'+ymArrays[j].tabheads+'">'+ymArrays[j].name+'</div>'+
                '</div>';
        }
        dhHtml+= '</div></div>';
        $('#groupboxbg').append(dhHtml);
    }

}

function serach() {
    var thistext = $("#top_input").val();
    var thislength = $(".xx_box_text").length;

    var i = 0;
    for (i; i < thislength; i++) {
        var thisdiv = $(".xx_box_text").eq(i).text();
        var thisTableheads = $(".xx_box_text").eq(i).attr("data-heads");
        if (thisTableheads.indexOf(thistext) > -1) {
            $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_one").hide();
            $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_two").show();
        }else if(thisdiv.indexOf(thistext) > -1){
            $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_one").hide();
            $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_two").show();
        }else {
            $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_two").hide();
            $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_one").show();
        }
    }

}

//回车搜索
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        serach();
    }
})

function reset() {
    var thislength = $(".xx_box_text").length;
    var i = 0;
    for (i; i < thislength; i++) {
        $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_two").hide();
        $(".xx_box_text").eq(i).siblings(".xx_box_img").children(".xx_box_img_one").show();
    }
}

function addTab(url, title, gnIndex){
    var count = $('#tabs_div').tabs("tabs").length;
    if(count > 1){
        $('#tabs_div').tabs('close', 1);
    }
    var url = getRootPath() +url+'?type='+gnIndex;
    $('#tabs_div').tabs('add',{
        title: title,
        closable:true,
        fit:true,
        href: url
    });

}

function btnUrl() {
    var tabs = $('#tabs_div');
    if(typeof(tabs) == undefined || typeof(tabs) == ''){
        return -1;
    }else{
       var int = getSelectTabIndex('all');
       setSelectTabIndex('#tabs_div',int);
    }
}