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
    var nowDate = new Date();
    var year = parseInt(nowDate.getFullYear());// 当前年份
    var month = parseInt(nowDate.getMonth());
    var date = parseInt(nowDate.getDate());

    var quarter = 1;
    var firstMonth = 3;
    if (month >= 3 && month <= 5) {
    } else if (month >= 6 && month <= 8) {
        firstMonth = 6;
        quarter = 2;
    } else if (month >= 9 && month <= 11) {
        firstMonth = 9
        quarter = 3;
    } else {
        quarter = 4;
        if (month >= 1 && month <= 2) {
            firstMonth = 1;
            year = year - 1;
        }
    }
    return {
        year: year,
        quarter: quarter,
        month: month,
        date: date,
        firstMonth: firstMonth
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
    if (!!$target) {
        var offset = $target.offset();
        if (!!offset) {
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
function emptytreat(str) {
    if (str == undefined || str == null)
        return '';
    return str;
}

/**
 * 获取窗口对象
 * @param id
 * @returns {*|jQuery}
 */
function getWindowObject(id) {
    var html = $(id).contents().find("body").html();
    return $(html).appendTo("body");
    ;
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

function hiddenDatagridToolbar($grid) {
    var panel = $grid.datagrid("getPanel");
    panel.find("div.datagrid-toolbar").remove();
}

//获取窗口对象
function getWindowObject(id) {
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
            with (showDiv.style) {
                cssText = "width:" + mouseX + "px !important;height:" + mouseY + "px !important"
            }
            with (showDiv.parentElement.style) {
                cssText = outer_style + "height:" + mouseY + "px !important";
                width = mouseX + 'px';
            }
            with (showDiv.parentElement.children[1].style) {
                top = mouseY - 10 + "px"
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
function index_checkAllOrNotCheckAll(list, className) {
    for (var i = 0; i < list.length; i++) {
        list.eq(i).children().children(className).click();
        if (list.eq(i).children("ul").length == 1) {
            index_checkAllOrNotCheckAll(list.eq(i).children("ul").children("li"), className);
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
        index_checkAllOrNotCheckAll(liList, ".tree-checkbox0")
    } else {
        $(el).addClass("tree-checkbox0");
        $(el).removeClass("tree-checkbox1");
        index_checkAllOrNotCheckAll(liList, ".tree-checkbox1")
    }

}

// 鼠标点击事件
function index_addMousedownDiv(el, id) {
    index_options_name[id] = false;
    if ($(el).children().eq(0).children().eq(0).children(".tree-checkbox").length != 0) {
        // <span class="tree-indent tree-join"></span><span class="tree-icon tree-file ">
        $(".tree-icon,.tree-file").removeClass("tree-file");
        $(".tree-icon,.tree-folder").removeClass("tree-folder");
        if ($(el).parent().children(".index_myCheckAll").length == 1) {
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
    if (window.innerWidth >= 1691 || window.innerWidth == 1300 || window.innerWidth < 1024) {
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
    } else if (window.innerWidth > 1250) {
        index_option.my_num = Math.sqrt(4.15 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth > 1200) {
        index_option.my_num = Math.sqrt(4.0 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth > 1150) {
        index_option.my_num = Math.sqrt(4.0 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth > 1100) {
        index_option.my_num = Math.sqrt(3.86 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth > 1050) {
        index_option.my_num = Math.sqrt(3.8 + (100 - Number(winWidth)) * 0.001 - window.innerWidth * 0.00166);
    } else if (window.innerWidth > 1000) {
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
    with (parent[0].children[1].style) {
        top = children[0].offsetHeight - 10 + "px"
    }
    // top:'+$(el).parent().eq(0)[0].offsetHeight+'px
}

// 当panel隐藏时触发
function index_onHidePanel() {
    // 标识，已点击过该下拉框（记录唯一标识）
    index_options_name[$(this).attr("id")] = true;
}

//获取年份数据
function getYearRange() {
    var curYear = parseInt(new Date().getFullYear());// 当前年份
    var yearArray=[];
    for(i=2013;i<=curYear;i++){
        yearArray.push({id: i, text: ''+i});
    }
    return yearArray;
}

//获取承办人身份下拉
function getCbrsfValues() {
    var cbrsfArray = [{id: "N", text: '非领导'},
        {id: "Y", text: '领导'}];
    return cbrsfArray;
}
//获取实体案件/
function getStajbs() {
    var stajbArray = [{id: "0", text: '实体案件'},
        {id: "1", text: '案件化办理的事项'}];
    return stajbArray;
}
/获取子项类/
function flxtdmType() {
    var stajbArray = [{id: "30002", text: '错误'},
        {id: "30003", text: '瑕疵'}];
    return stajbArray;
}
/**
 * 获取统计分析导航菜单
 */
function getDhcdValues() {
    var dhArrays = [
        {
            title: '评查总体情况', childs: [{
            id: '1',
            group: '(总体情况)',
            name: '按时间分析',
            imageOneUrl: 'image/icon/imgone.png',
            imageTwoUrl: 'image/icon/imgone_xz.png',
            gndz: '/view/analysis/pcGeneral/index.html',
            gnIndex: '0',
            tabheads:'年份, 办案人数, 办结案件数, 办结数, 评查人员数, 评查案件数评查比例, 评查比例, 承办人平均被评查案件数,被评查, 评查员平均评查案件数,平均评查'
        }, {
            id: '2',
            group: '(总体情况)',
            name: '按地区分析',
            imageOneUrl: 'image/icon/imgtwo.png',
            imageTwoUrl: 'image/icon/imgtwo_xz.png',
            gndz: '/view/analysis/pcGeneral/index.html',
            gnIndex: '1',
            tabheads:'地区, 办案人数, 办结案件数, 办结数,  评查人员数, 评查案件数评查比例, 评查比例, 承办人平均被评查案件数,被评查, 评查员平均评查案件数,平均评查'
        }, {
            id: '3',
            group: '(总体情况)',
            name: '按条线分析',
            imageOneUrl: 'image/icon/imgtree.png',
            imageTwoUrl: 'image/icon/imgtree_xz.png',
            gndz: '/view/analysis/pcGeneral/index.html',
            gnIndex: '2',
            tabheads:'条线, 办案人数, 办结案件数, 办结数,  评查人员数, 评查案件数评查比例, 评查比例, 承办人平均被评查案件数,被评查, 评查员平均评查案件数,平均评查'
        }]},
        {
            title: '案件质量情况', childs: [{
            id: '4',
             group: '(案件质量)',
            name: '按时间分析',
            imageOneUrl: 'image/icon/zlqk_one_xz.png',
            imageTwoUrl: 'image/icon/zlqk_one.png',
            gndz: '/view/analysis/ajqkzlfx/ndfx.html',
            gnIndex: '1',
            tabheads:'年份, 评查情况, 评查结果等次, 办结数, 评查案件数, 评查比, 优质, 合格, 瑕疵, 不合格, 件数, 优质占比,优质比,合格,合格占比,合格比,瑕疵,瑕疵占比,瑕疵比,不合格,不合格占比,不合格比'
        }, {
            id: '5',
             group: '(案件质量)',
            name: '按地区分析',
            imageOneUrl: 'image/icon/zlqk_two_xz.png',
            imageTwoUrl: 'image/icon/zlqk_two.png',
            gndz: '/view/analysis/ajqkzlfx/dqfx.html',
            gnIndex: '2',
            tabheads:'地区, 评查情况, 评查结果等次, 办结数, 评查案件数, 评查比, 优质, 合格, 瑕疵, 不合格, 件数, 优质占比,优质比,合格,合格占比,合格比,瑕疵,瑕疵占比,瑕疵比,不合格,不合格占比,不合格比'
        }, {
            id: '6',
             group: '(案件质量)',
            name: '按条线分析',
            imageOneUrl: 'image/icon/zlqk_tree_xz.png',
            imageTwoUrl: 'image/icon/zlqk_tree.png',
            gndz: '/view/analysis/ajqkzlfx/txfx.html',
            gnIndex: '3',
            tabheads:'条线, 评查情况, 评查结果等次, 办结数, 评查案件数, 评查比, 优质, 合格, 瑕疵, 不合格, 件数, 优质占比,优质比,合格,合格占比,合格比,瑕疵,瑕疵占比,瑕疵比,不合格,不合格占比,不合格比'
        }]},
        {
            title: '错误项目分析', childs: [{
            id: '7',
            group: '(错误项目)',
            name: '按时间分析',
            imageOneUrl: 'image/icon/cwxm_one_xz.png',
            imageTwoUrl: 'image/icon/cwxm_one.png',
            gndz: '/view/analysis/errorItem/index.html',
            gnIndex: '0',
            tabheads:'年份, 评查案件数, 存在错误项的案件数, 存在错误项的案件比例, 错误项个数, 案件平均错误项,平均错误项, 存在错误项案件平均错误项个数, 条线通用, 个别条线适用, 侦监, 公诉, 未检, 证据采信, 事实认定, 法律适用, 办案程序, 法律文书, 司法责任制落实, 系统规范应用, 其他情况, 侦查监督, 出席二审法庭, 特别程序, 出席二审法庭, 法律监督, 合计, 错误项占比,错误占比, 占比 '
        }, {
            id: '8',
             group: '(错误项目)',
            name: '按地区分析',
            imageOneUrl: 'image/icon/cwxz_two_xz.png',
            imageTwoUrl: 'image/icon/cwxm_two.png',
            gndz: '/view/analysis/errorItem/index.html',
            gnIndex: '1',
            tabheads:'地区, 评查案件数, 存在错误项的案件数, 存在错误项的案件比例, 错误项个数, 案件平均错误项,平均错误项, 存在错误项案件平均错误项个数, 条线通用, 个别条线适用, 侦监, 公诉, 未检, 证据采信, 事实认定, 法律适用, 办案程序, 法律文书, 司法责任制落实, 系统规范应用, 其他情况, 侦查监督, 出席二审法庭, 特别程序, 出席二审法庭, 法律监督, 合计, 错误项占比, 错误占比, 占比 '
        }, {
            id: '9',
             group: '(错误项目)',
            name: '按条线分析',
            imageOneUrl: 'image/icon/cwxm_tree_xz.png',
            imageTwoUrl: 'image/icon/cwxm_tree.png',
            gndz: '/view/analysis/errorItem/index.html',
            gnIndex: '2',
            tabheads:'条线, 评查案件数, 存在错误项的案件数, 存在错误项的案件比例, 错误项个数, 案件平均错误项,平均错误项, 存在错误项案件平均错误项个数, 条线通用, 个别条线适用, 侦监, 公诉, 未检, 证据采信, 事实认定, 法律适用, 办案程序, 法律文书, 司法责任制落实, 系统规范应用, 其他情况, 侦查监督, 出席二审法庭, 特别程序, 出席二审法庭, 法律监督, 合计, 错误项占比, 错误占比, 占比'
        }]},
        {
            title: '瑕疵项目分析', childs: [{
            id: '10',
             group: '(瑕疵项目)',
            name: '按时间分析',
            imageOneUrl: 'image/icon/xcxm_one_xz.png',
            imageTwoUrl: 'image/icon/xcxm_one.png',
            gndz: '/view/analysis/flaw/index.html',
            gnIndex: '0',
            tabheads:'年份, 评查案件数, 存在瑕疵项的案件数, 存在瑕疵项的案件比例, 瑕疵项个数,瑕疵数, 案件平均瑕疵项,平均瑕疵项, 存在瑕疵项案件平均错误项个数, 条线通用, 个别条线适用, 侦监, 公诉, 未检, 证据采信, 事实认定, 法律适用, 办案程序, 法律文书, 司法责任制落实, 系统规范应用, 其他情况, 侦查监督, 出席二审法庭, 特别程序, 出席二审法庭, 法律监督, 合计, 瑕疵项占比, 瑕疵占比, 占比 '
        }, {
            id: '11',
            group: '(瑕疵项目)',
            name: '按地区分析',
            imageOneUrl: 'image/icon/xcxm_two_xz.png',
            imageTwoUrl: 'image/icon/xcxm_two.png',
            gndz: '/view/analysis/flaw/index.html',
            gnIndex: '1',
            tabheads:'地区, 评查案件数, 存在瑕疵项的案件数, 存在瑕疵项的案件比例, 瑕疵项个数, 案件平均瑕疵项,平均瑕疵项, 存在瑕疵项案件平均错误项个数, 条线通用, 个别条线适用, 侦监, 公诉, 未检, 证据采信, 事实认定, 法律适用, 办案程序, 法律文书, 司法责任制落实, 系统规范应用, 其他情况, 侦查监督, 出席二审法庭, 特别程序, 出席二审法庭, 法律监督, 合计, 瑕疵项占比,瑕疵占比, 占比 '
        }, {
            id: '12',
            group: '(瑕疵项目)',
            name: '按条线分析',
            imageOneUrl: 'image/icon/xcxm_tree_xz.png',
            imageTwoUrl: 'image/icon/xcxm_tree.png',
            gndz: '/view/analysis/flaw/index.html',
            gnIndex: '2',
            tabheads:'条线, 评查案件数, 存在瑕疵项的案件数, 存在瑕疵项的案件比例, 瑕疵项个数, 案件平均瑕疵项,平均瑕疵项, 存在瑕疵项案件平均错误项个数, 条线通用, 个别条线适用, 侦监, 公诉, 未检, 证据采信, 事实认定, 法律适用, 办案程序, 法律文书, 司法责任制落实, 系统规范应用, 其他情况, 侦查监督, 出席二审法庭, 特别程序, 出席二审法庭, 法律监督, 合计, 瑕疵项占比,瑕疵占比, 占比 '
        }]},
        {
            title: '承办人案件质量分析', childs: [{
            id: '13',
            group: '',
            name: '承办人案件质量分析',
            imageOneUrl: 'image/icon/cbrnjzl_xz.png',
            imageTwoUrl: 'image/icon/xbrajzl.png',
            gndz: '/view/statistics/promoter/index.html',
            gnIndex: '0',
            tabheads:'办案检察官, 办案单位, 办案部门, 被评查案件数, 被评查数, 优秀案件数,优秀数, 合格案件数量,合格数, 瑕疵案件数, 瑕疵数, 不合格案件数, 不合格数'
        }]},
        {
            title: '突出项目分析', childs: [{
            id: '14',
            group: '(突出项目)',
            name: '按瑕疵分析',
            imageOneUrl: 'image/icon/tcxm_one_xz.png',
            imageTwoUrl: 'image/icon/tcxm_one.png',
            gndz: '/view/analysis/tccwxc/xcfx.html',
            gnIndex: '0',
            tabheads:'瑕疵项, 瑕疵占比, 占比, 瑕疵数'
        }, {
            id: '15',
            group: '(突出项目)',
            name: '按错误分析',
            imageOneUrl: 'image/icon/tcxm_two_xz.png',
            imageTwoUrl: 'image/icon/tcxm_two.png',
            gndz: '/view/analysis/tccwxc/cwfx.html',
            gnIndex: '2',
            tabheads:'错误项, 错误占比, 占比, 错误数'
        }, {
            id: '16',
            group: '(突出项目)',
            name: '按条线分析',
            imageOneUrl: 'image/icon/tcxm_tree_xz.png',
            imageTwoUrl: 'image/icon/tcxm_tree.png',
            gndz: '/view/statistics/ajwt_hz/index.html',
            gnIndex: '3',
            tabheads:'业务条线, 评查标准, 评查项, 问题案件数'
        }]}
    ];
    return dhArrays;
}

//获取统计导航的页面索引
function  getSelectTabIndex(){
    var tabs = $('#tabs_div');
    if(typeof(tabs) == undefined || typeof(tabs) == '' ){
        return -1;
    }else{
        var request = new UrlSearch();
        if(request.type == null || request.type == ''){
            return -1;
        }
        return request.type;
    }
}

function setSelectTabIndex(tabsName ,index){
    $(tabsName).tabs("select", parseInt(index));
}

function UrlSearch()
{
    var tabs = $('#tabs_div');
    if(typeof(tabs) == undefined || typeof(tabs) == ''){
        return;
    }
    var tab = $('#tabs_div').tabs("getTab", 1);
    var url = tab.panel('options').href;
    var name,value;
    var str=url; //取得整个地址栏
    var num=str.indexOf("?")
    str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]
    var arr=str.split("&"); //各个参数放到数组里
    for(var i=0;i < arr.length;i++){
        num=arr[i].indexOf("=");
        if(num>0){
            name=arr[i].substring(0,num);
            value=arr[i].substr(num+1);
            this[name]=value;
        }
    }
}

function GetParamsQueryString(url,name) {
    if(url.indexOf("name") > 0){
        var type = url.substr();
    }
    return null;
}

function setAllCheckbox(name, data){
    var valueArr = new Array();
    for (var i = 0; i < data.length; i++) {
        valueArr.push(data[i].id);
    }
    $(name).combotree("setValues", valueArr);
}

/**
 * 根据分隔符获取分隔后的字符串
 * @param oldStr 需要分隔的字符串
 * @param fgf 分隔符
 */
function getSplitString(oldStr, fgf){
    if(oldStr==null || oldStr == ''){
        return '';
    }
    var tempArr = oldStr.split(fgf);
    var tempStr = '';
    if (tempArr.length > 0 && oldStr) {
        for (var o in tempArr) {
            tempStr += "'" + tempArr[o] + "'" + ",";
        }
        tempStr = tempStr.substring(0, tempStr.length - 1);
    }
    return tempStr;
}



