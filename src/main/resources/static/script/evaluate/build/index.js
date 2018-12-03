var ywbm;
$(document).ready(function () {

    // 界面标签样式设置及事件绑定
    eval_build_marksInit();

    // 标签初始化数据加载
    eval_build_marksDataBind();
});

// 界面标签样式设置及事件绑定
function eval_build_marksInit() {

    $('#cbt_win_eval_build_pcfl').combotree('loadData',[]);
    $('#cbt_win_eval_build_pcfl').combotree('clear');
    $('#cbt_win_eval_build_pcfl').combotree('setValue', '');
}

// 标签初始化数据加载
function eval_build_marksDataBind() {

	// 加载评查分类
	load_cbt_win_eval_build_pcfl();

	// 获取当前登陆人的业务编码
    get_cbr_ywbm();
	
	// 下一步点击事件
    $("#btn_wind_eval_next").unbind( "click" );
    $("#btn_wind_eval_next").bind("click", function () {
		click_btn_wind_eval_next();
    });


    $('#pop_ups_win').window('open');
}

// 加载评查分类
function load_cbt_win_eval_build_pcfl() {

	$('#cbt_win_eval_build_pcfl').combotree({
		method: 'get',
		lines: true,
		url: getRootPath()+'/manage/getpcfl',
		onLoadSuccess: function (node, data) {
            var pcflbm;
			// 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1){
                pcflbm = data[0].id;
                $('#cbt_win_eval_build_pcfl').combotree('setValue', pcflbm);
            }

		},
		onSelect: function (node) {
			if (!node) {
				Alert("请重新选择评查方式！");
				return;
			}
		}
	});

}

function get_cbr_ywbm(){
    $.ajax({
        url: getRootPath() + "/filter/getStuffyYwbm",
        type: 'get',
        async: false,
        dataType: "json",
        success: function (result) {
            if (result.status == 200) {
                ywbm = result.value;
            }
        }
    });
}
// 下一步点击事件
function click_btn_wind_eval_next(){

    // 获取选中节点自定义数据
    var tree = $('#cbt_win_eval_build_pcfl').combotree('tree');	// 获取树对象
    var node = tree.tree('getSelected');		// 获取选择的节点
    var title = node.attributes.URL_SM;
    var url = node.attributes.URL;

    // JS对象,需要传输的值
    var obj = new Object();
    obj.PCFLBM = $('#cbt_win_eval_build_pcfl').combotree('getValue');
    obj.SFJS = node.attributes.SFJS;
    obj.YWBM = ywbm;

    if (isNull(obj.PCFLBM)){
        Alert("请选择评查方式！");
        return;
    }
    // 页面跳转
    $('#pop_ups_win').window('close');
    load_function(title, url, obj);
}
