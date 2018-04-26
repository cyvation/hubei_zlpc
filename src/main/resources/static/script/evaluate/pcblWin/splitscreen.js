//
//
// $(".pcbl_center_right li").click(function () {
//     $(".pcbl_center_right_hound").css('display','none');
//     $(".pcbl_center_right_hound_sjx").css('display','none');
//     $(".pcbl_center_right_li").removeClass("pcbl_center_right_li");
//     $(this).addClass("pcbl_center_right_li");
//     $(this).children(".pcbl_center_right_hound_sjx").css('display','block');
//     $(this).children(".pcbl_center_right_hound").css('display','block');
//
// });
//
// $(".pcbl_ajxx_btn li").click(function () {
//     $(".pcbl_ajxx_sjx").css('display','none');
//     $(".pcbl_ajxx_btn_click").removeClass("pcbl_ajxx_btn_click");
//     $(this).addClass("pcbl_ajxx_btn_click");
//     $(this).children(".pcbl_ajxx_sjx").css('display','block')
// });
//
//
//
// $(".pckh_ajxx_btn li").click(function () {
//     $(".pckh_ajxx_sjx").css('display','none');
//     $(".pckh_ajxx_click").removeClass("pckh_ajxx_click");
//     $(this).addClass("pckh_ajxx_click");
//     $(this).children(".pckh_ajxx_sjx").css('display','block')
// });
//
//
//
// $(".pckh_center_right li").click(function () {
//     $(".pckh_ajxx_sjxs").css('display','none');
//     $(".pckh_center_right_li").removeClass("pckh_center_right_li");
//     $(this).addClass("pckh_center_right_li");
//     $(this).children(".pckh_ajxx_sjxs").css('display','block')
// });

$(".pcbl_ajxx_btn_ul_li").mousemove(function () {
    $(this).addClass("pcbl_ajxx_btn_click");
    $(this).siblings().removeClass("pcbl_ajxx_btn_click");
    $(this).children(".pcbl_ajxx_sjx").show();
    $(this).siblings().children(".pcbl_ajxx_sjx").hide();
    $(this).children(".pcbl_ajxx_btn_postion").show();
    $(this).siblings().children(".pcbl_ajxx_btn_postion").hide()
})
$(".pcbl_ajxx_btn_postion").mouseout(function () {
    $(".pcbl_ajxx_btn_postion").hide()
})
if(FUNCTION_PARAM.type==1){
    $('#pcWinpan').layout('remove','center');

    // var westPan = $('#pcWinpan').layout('panel','west');
    $('#pcWin_ajxxdiv').panel('resize',{
        width: '100%'
    });
}else {
    $('#pcWinpan').layout('collapse','west');
}








