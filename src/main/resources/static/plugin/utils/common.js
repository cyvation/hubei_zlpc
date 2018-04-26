/**********************工具类,由于函数不多,所以没有分工具种类划分****************************/


/**
 * 获取URL地址的查询参数
 *
 * @date 2017-3-2
 * @author 魏明欣
 *
 * @returns
 */
function getRequest() {

    var url = decodeURI(location.href);

    if (url.indexOf("?") === -1) {
        return;
    }

    var str = url.substr(url.indexOf("?") + 1);
    var strs = str.split("&");

    var theRequest = {};
    for (var i = 0; i < strs.length; i++) {
        theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
    }

    return theRequest;
}

/**
 * 返回到上一个页面
 *
 * @date 2017-3-2
 * @author 魏明欣
 *
 * @returns
 */
function goBackPage() {// 参考history.js

    if (!history) {
        return


    }

    history.back();
}

/**
 * 重新设置父页面的高度
 *
 * @date 2017-3-19
 * @author 魏明欣
 *
 */
function resizeParentIframe() {
    var maineIframe = $("iframe", window.parent.document);
    maineIframe.height(0);
    // maineIframe.width(0);

    var thisPageHight = $(window.document).height();
    var thisPageWidth = $(window.document).width();

    maineIframe.height(thisPageHight + 40);
    // maineIframe.width(thisPageWidth-20);

    //console.info(thisPageHight);
}

/**
 * 获取工程根目录
 *
 * @returns {String}
 */
function getRootPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;

    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    return (prePath + postPath);
};

/**
 * 表单转json
 *
 * @param $form
 *            表单对象
 * @returns {{}}
 */
function form2Json($form) {
    var obj = {};
    if ($form.length == 1) {
        var arr = $form.serializeArray();
        $.each(arr, function (k, v) {
            obj[v.name] = v.value;
        });
    }
    return obj;
};
/**
 * 是否是数字
 *
 * @param value
 */
function isNumber(value) {
    var pattern = /^[0-9]*$/;
    if (pattern.exec(value) == null || value === "") {
        return false;
    } else {
        return true;
    }
}
/**
 * 转boolean
 *
 * @param obj
 * @returns {boolean}
 */
function obj2bool(obj) {
    var bool = false;
    if (!!obj) {
        if (typeof obj === "string") {
            if (obj.toLowerCase() === "false" || obj.toLowerCase() === "no")
                bool = false;
            else if (obj.toLowerCase() === "true"
                || obj.toLowerCase() === "yes")
                bool = true;
        } else {
            bool = true;
        }
    }
    return bool;
};
/**
 * 删除数组元素
 *
 * @param dx
 *            索引
 * @param arr
 *            目标数组
 * @returns {boolean}
 */
function removeArrayItem(dx, arr) {
    if (isNaN(dx) || dx > arr.length) {
        return false;
    }
    for (var i = 0, n = 0; i < arr.length; i++) {
        if (arr[i] != arr[dx]) {
            arr[n++] = arr[i];
        }
    }
    arr.length -= 1;
};
/**
 * 首字母转大写
 *
 * @param str
 * @returns {string}
 */
function firstUpperCase(str) {
    return str.toLowerCase().replace(/\b[a-z]/g, function (s) {
        return s.toUpperCase();
    });
}
/**
 * 获取当前的年份、季度、月份、日期、该季度的第一月份
 */
function getCurrentYearQuarter() {
    var nowDate=new Date();
    var year = parseInt(nowDate.getFullYear());// 当前年份
    var month = parseInt(nowDate.getMonth());
    var  date= parseInt(nowDate.getDate());

    var quarter = 1;
    var firstMonth=3;
    if (month >= 3 && month <= 5) {
    } else if (month >= 6 && month <= 8) {
        firstMonth=6;
        quarter = 2;
    } else if (month >= 9 && month <= 11) {
        firstMonth=9
        quarter = 3;
    } else {
        quarter = 4;
        if (month >= 1 && month <= 2)
        {
            firstMonth=1;
            year = year - 1;
        }
    }
    return {
        year: year,
        quarter: quarter,
        month:month,
        date:date,
        firstMonth:firstMonth
    };
}
/**
 * 格式化日期
 *
 * @param long
 *            日期long
 * @param pattern
 *            格式
 * @returns {*}
 */
function formatDate(long, pattern) {
    var date = new Date(long);
    if (pattern == undefined) {
        pattern = "yyyy-MM-dd hh:mm:ss";
    }
    var o = {
        "M+": date.getMonth() + 1,
        "d+": date.getDate(),
        "h+": date.getHours(),
        "m+": date.getMinutes(),
        "s+": date.getSeconds(),
        "q+": Math.floor((date.getMonth() + 3) / 3),
        "S": date.getMilliseconds()
    };
    if (/(y+)/.test(pattern)) {
        pattern = pattern.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(pattern)) {
            pattern = pattern.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return pattern;
}
/**
 * 字符串去空格
 *
 * @param str
 * @returns {string|XML|void}
 */
function trim(str) {
    return str.replace(/^\s+|\s+$/gm, '');
}
/**
 * 获取cookie值
 *
 * @param name
 *            名称
 * @returns {*}
 */
function getCookie(name) {
    // debugger
    var cookieList = document.cookie.split(";");
    for (var i = 0; i < cookieList.length; i++) {
        var cookie = cookieList[i];
        var pair = cookie.split("=");
        if (trim(pair[0]) === name) {
            return pair[1];
        }

    }
    return "";
}
/**
 * 滚动到
 *
 * @param $container
 *            容器对象
 * @param $target
 *            目标对象
 */
function scrollTo($container, $target) {
    if(!!$target){
        var offset = $target.offset();
        if(!!offset){
            $container.animate({
                scrollTop: offset.top - $container.offset().top + $container.scrollTop()
            })
        }

    }
}
/**
 * 获取combotree复选框获取选中的节点，attributes的isUsable为true的id
 */
$.fn.getCheckUsableTrue = function () {
    var values = "";
    var t = this.combotree('tree');	// 获取树对象
    var nodes = t.tree('getChecked');		// 获取选择的节点
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].attributes.isUsable == true) {
            if (values == "")
                values = nodes[i].id;
            else
                values = values + "," + nodes[i].id;
        }
    }
    return values;
}
/**
 * 字符串若为undefined或null则返回''
 */
function emptytreat(str){
    if(str==undefined||str==null)
        return '';
    return str;
}

/**
 * 获取窗口对象
 * @param id
 * @returns {*|jQuery}
 */
function getWindowObject(id){
    var html = $(id).contents().find("body").html();
    return $(html).appendTo("body");;
}


/**
 * 验证按钮是否显示 (流程里按钮的显示)
 *
 * @param params   参数数据
 * @param func     回调
 */
function buttonIsShow(params, func) {
    var res;
    $.ajax({
        type: 'post',
        url: getRootPath() + "/audit/isusablebutton",
        dataType: 'json',
        async: false,// 同步
        data: params,
        success: function (data) {
            res = data.value;
        }
    });
    return res;
}

function hiddenDatagridToolbar($grid){
    var panel = $grid.datagrid("getPanel");
    panel.find("div.datagrid-toolbar").remove();
}
//获取窗口对象
function getWindowObject(id){
    var html = $("#win_iframe").contents().find("body").html();

    return $(html).appendTo("body");
}


//**********************************下拉框拖动改变大小************************

// 点击过的下拉框的id或者唯一标识符也行
var index_options_name = {};
// 点击的当前下拉框的所需的属性
var index_option;
// 鼠标现在的X坐标
var mouseX;
// 鼠标现在的Y坐标
var mouseY;

$(document).mouseup(function (e) {
    // 清除dom对象
    index_option = undefined;
})
// 鼠标移动事件
$(document).mousemove(function (e) {
    // 判断是否有dom对象
    if (index_option) {
        // 对该对象进行操作
        with (index_option) {
            // 获取边框大小（移动距离+原始大小）
            mouseX = (e.clientX - lastMouseX) * my_num + my_width;
            mouseY = (e.clientY - lastMouseY) * my_num + my_height;
            // EasyUI有个问题，当高度余4==3时，无法显示线条，特殊处理
            // if (mouseY.toFixed(0) % 4 == 3) {
            //     mouseY++;
            // }

            // 给内层添加样式
            with (showDiv.style){
                cssText="width:" + mouseX + "px !important;height:" + mouseY + "px !important"
            }
            with (showDiv.parentElement.style){
                cssText=outer_style + "height:" + mouseY + "px !important";
                width = mouseX  + 'px';
            }
            with (showDiv.parentElement.children[1].style){
                top=mouseY-10+"px"
            }

            cancelEvent(e)
        }
    }

})

function cancelEvent(e, c) {
    e.returnValue = false;
    if (e.preventDefault) e.preventDefault();
    if (c) {
        e.cancelBubble = true;
        if (e.stopPropagation) e.stopPropagation()
    }
}

// 选择全部或者取消选择全部
function index_checkAllOrNotCheckAll(list,className) {
    for(var i=0;i<list.length;i++){
        list.eq(i).children().children(className).click();
        if(list.eq(i).children("ul").length==1){
            index_checkAllOrNotCheckAll(list.eq(i).children("ul").children("li"),className);
        }
    }
}

// 点击全选触发事件
function index_clickCheckAll(el) {
    var className = $(el).attr("class");
    var liList = $(el).parent().next().children("li");
    if (className.indexOf("checkbox0") != -1) {
        $(el).addClass("tree-checkbox1");
        $(el).removeClass("tree-checkbox0");
        index_checkAllOrNotCheckAll(liList,".tree-checkbox0")
    } else {
        $(el).addClass("tree-checkbox0");
        $(el).removeClass("tree-checkbox1");
        index_checkAllOrNotCheckAll(liList,".tree-checkbox1")
    }

}

// 鼠标点击事件
function index_addMousedownDiv(el, id) {
    index_options_name[id] = false;
    if ($(el).children().eq(0).children().eq(0).children(".tree-checkbox").length != 0) {
        // <span class="tree-indent tree-join"></span><span class="tree-icon tree-file ">
        $(".tree-icon,.tree-file").removeClass("tree-file");
        $(".tree-icon,.tree-folder").removeClass("tree-folder");
        if($(el).parent().children(".index_myCheckAll").length==1){
            $(el).parent().children(".index_myCheckAll").remove();
        }
        $(el).parent().prepend('<div class="index_myCheckAll"></span><span class="tree-checkbox tree-checkbox0" onclick="index_clickCheckAll(this)"></span><span class="">全选</div>')
    }
    // 判断是否已经给该下拉框添加过三角
    if ($(el).parent().parent().children(".dragresize-br").length == 1) {
        $(el).parent().parent().children(".dragresize-br").mousedown(function (e) {
            index_mousedown(e, el);
        });
    } else {
        var index_inherit = '<div class="dragresize-br" style="visibility: inherit;"></div>';

        // 创建可拉图标，并添加鼠标点击可拉图标时触发事件
        $(el).parent().parent().append(index_inherit).children(".dragresize-br").mousedown(function (e) {
            index_mousedown(e, el);
        });
    }

}
// 鼠标按下时触发
function index_mousedown(e, el) {
    index_option = new Object();
    // 获取鼠标点击时坐标
    index_option.lastMouseX = e.clientX
    index_option.lastMouseY = e.clientY
    // 把框这个dom对象赋给index_option
    index_option.showDiv = $(el)[0].parentElement;
    // 获取框的宽度
    index_option.my_width = index_option.showDiv.offsetWidth;
    // 获取框的高度
    index_option.my_height = index_option.showDiv.offsetHeight;
    // 获取最外层div的style
    index_option.outer_style = index_option.showDiv.parentElement.style.cssText.split("height:")[0];

    var winWidth = String(window.innerWidth);
    winWidth = winWidth.substring(winWidth.length - 2, winWidth.length);
    if (window.innerWidth >= 1691 || window.innerWidth == 1300|| window.innerWidth < 1024) {
        index_option.my_num = 1;
    } else if (window.innerWidth >= 1650) {
        index_option.my_num = Math.sqrt(4.04 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth >= 1600) {
        index_option.my_num = Math.sqrt(4.0 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth >= 1550) {
        index_option.my_num = Math.sqrt(4.1 - (110 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth >= 1500) {
        index_option.my_num = Math.sqrt(4.13 - (110 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth >= 1450) {
        index_option.my_num = Math.sqrt(3.9 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth >= 1400) {
        index_option.my_num = Math.sqrt(4.07 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth >= 1350) {
        index_option.my_num = Math.sqrt(4.1 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth > 1300) {
        index_option.my_num = Math.sqrt(4.15 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }else if (window.innerWidth > 1250) {
        index_option.my_num = Math.sqrt(4.15 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }else if (window.innerWidth > 1200) {
        index_option.my_num = Math.sqrt(4.0 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }else if (window.innerWidth > 1150) {
        index_option.my_num = Math.sqrt(4.0 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }else if (window.innerWidth > 1100) {
        index_option.my_num = Math.sqrt(3.86 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }else if (window.innerWidth > 1050) {
        index_option.my_num = Math.sqrt(3.8 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }else if (window.innerWidth > 1000) {
        index_option.my_num = Math.sqrt(3.65 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    }

}
// 当panel显示时触发
function index_onShowPanel() {

    // 获取显示DIV
    var children = $(this).combotree("panel").eq(0);
    // 获取该下拉框的外层div
    var parent = children.offsetParent();

    // 判断是否点击过该下拉框
    if (index_options_name[$(this).attr("id")]) {
        // 根据显示DIV的宽高，给外层初始化宽高
        with (parent[0].style) {
            cssText = cssText.split("height:")[0] + "height:" + (Number(children.css("height").substring(0, children.css("height").length - 2))) + "px !important"
            width = (Number(children.css("width").substring(0, children.css("width").length - 2))) + 'px';
        }
    }
    with (parent[0].children[1].style){
        top=children[0].offsetHeight-10+"px"
    }
    // top:'+$(el).parent().eq(0)[0].offsetHeight+'px
}
// 当panel隐藏时触发
function index_onHidePanel() {
    // 标识，已点击过该下拉框（记录唯一标识）
    index_options_name[$(this).attr("id")] = true;
}


