$.messager.defaults.ok = "确定";
$.messager.defaults.cancel = "取消";
$.fn.datebox.defaults.closeText = "关闭";
$.fn.datebox.defaults.okText = "确定";
$.fn.datebox.defaults.currentText = "今天";

function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

function myparser(s) {
    //if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d);
    } else {
        return new Date();
    }
}
function DateFormatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '年' + (m < 10 ? ('0' + m) : m) + '月' + (d < 10 ? ('0' + d) : d) + '日';
}
function DateTimeFormatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var mm = date.getMinutes();
    var s = date.getSeconds();
    return y.toString() + SjFormater(m).toString() + SjFormater(d).toString()
     + SjFormater(h).toString() + SjFormater(mm).toString() + SjFormater(s).toString();
}
function SjFormater(t)
{
    return (t < 10 ? ('0' + t) : t);
}
function DateParser(s) {
    var ss = (s.split(' '))[0];
    var arr = (s.split('/'));
    var y = parseInt(arr[0], 10);
    var m = parseInt(arr[1], 10);
    var d = parseInt(arr[2], 10);
    return y + '年' + (m < 10 ? ('0' + m) : m) + '月' + (d < 10 ? ('0' + d) : d)+'日';
    //return DateFormatter(myparser(ss));
    //return ss;
}
function DateParser_heng(s) {
    var ss = (s.split(' '))[0];
    var arr = (s.split('-'));
    var y = parseInt(arr[0], 10);
    var m = parseInt(arr[1], 10);
    var d = parseInt(arr[2], 10);
    return y + '年' + (m < 10 ? ('0' + m) : m) + '月' + (d < 10 ? ('0' + d) : d) + '日';
}
function getStartDate() {
    var date = new Date();
    var year = date.getFullYear() - 1;
    var startDate = year + '年12月26日';
    return startDate;
}

function getStartDate_new() {
    var date = new Date();
    var year = date.getFullYear() - 1;
    var startDate = year + '-12-26';
    return startDate;
}

function getNowDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (day < 10) {
        day = '0' + day;
    }
    var startDate = year + '年' + month + '月' + day + '日';
    return startDate;
}

function getEndDate() {
    var date = new Date();
    var strMonth = date.getMonth();
    strMonth = strMonth + 1;
    strMonth = strMonth == 13 ? 0 : strMonth;
    if (strMonth < 10) {
        strMonth = '0' + strMonth;
    }
    var strDay = date.getDate();
    if (strDay < 10) {
        strDay = '0' + strDay;
    }
    var startDate = date.getFullYear() + '年' + strMonth + '月' + strDay + '日';
    return startDate;
}

function getEndDate_new() {
    var date = new Date();
    var strMonth = date.getMonth();
    strMonth = strMonth + 1;
    strMonth = strMonth == 13 ? 0 : strMonth;
    if (strMonth < 10) {
        strMonth = '0' + strMonth;
    }
    var strDay = date.getDate();
    if (strDay < 10) {
        strDay = '0' + strDay;
    }
    var startDate = date.getFullYear() + '-' + strMonth + '-' + strDay;
    return startDate;
}

function getDate(date) {
    if (undefined == date) {
        return null;
    }
    var result = '';
    if (date.length >= 11) {
        result = date.substr(0, 4) + '-' + date.substr(5, 2) + '-' + date.substr(8, 2);
    }
    return result;
}
function getOraDate(date) {
    if (undefined == date) {
        return null;
    }
    var result = '';
    if (date.length == 11) {
        result = date.substr(0, 4) + '' + date.substr(5, 2) + '' + date.substr(8, 2);
    }
    return result;
}
/***************消息提示**********************/
//提示
function Alert(msg) {
    $.messager.alert('提示', msg, 'info');
}
//提示+处理
function AlertAndDo(msg, fn) {
    $.messager.alert('提示', msg, 'info', fn);
}

//错误提示
function ShowError(msg) {
    $.messager.alert('错误', msg, 'error');
}

//错误提示+处理
function ShowErrorAndDo(msg, fn) {
    $.messager.alert('错误', msg, 'error', fn);
}

//问题
function ShowQuestion(msg) {
    $.messager.alert('问题', msg, 'question');
}

//问题+处理
function ShowQuestionAndDo(msg, fn) {
    $.messager.alert('问题', msg, 'question', fn);
}

//警告
function ShowWarning(msg) {
    $.messager.alert('警告', msg, 'warning');
}

//警告+处理
function ShowWarningAndDo(msg, fn) {
    $.messager.alert('警告', msg, 'warning', fn);
}

/****************确认对话框********************/
//确认对话框
function Confirm(title, msg, fn) {
    $.messager.confirm(title, msg, fn);
}

/****************弹出输入框********************/
//弹出输入框
function Prompt(title, msg, fn) {
    $.messager.prompt(title, msg, fn);
}

/****************进度条**********************/
//显示进度条
function ShowProgress() {
    $.messager.progress();
}

//关闭进度条
function CloseProgress() {
    $.messager.progress('close');
}

/********************动态添加元素引用*********************/
//添加Script元素
function AddScript(type, src) {
    var add = true;
    var oHead = document.getElementsByTagName('head').item(0);
    for (var i = 0; i < oHead.childNodes.length; i++) {
        if (oHead.childNodes[i].src && oHead.childNodes[i].src.indexOf(src.replace(/\.\.\//g, '')) != -1) {
            var oScirpt = document.createElement("script");
            oScirpt.type = type;
            oScirpt.src = src;

            oHead.replaceChild(oScirpt, oHead.childNodes[i]);
            add = false;
            break;
        }
    }
    if (add) {

        var scirpt = document.createElement("script");
        scirpt.type = type;
        scirpt.src = src;
        oHead.appendChild(scirpt);
    }
}

//添加Link元素
function AddLink(type, href, rel) {
    var add = true;
    var es = document.getElementsByTagName('link');
    for (var i = 0; i < es.length; i++) {
        if (es[i]['href'].indexOf(href.replace(/\.\.\//g, '')) != -1) {
            add = false;
            break;
        }
    }
    if (add) {
        var oHead = document.getElementsByTagName('head').item(0);
        var oLink = document.createElement("link");
        oLink.type = type;
        oLink.href = href;
        oLink.rel = rel;
        oHead.appendChild(oLink);
    }
}

//获取combotree中选择的值
function getdwbmj(cbo) {
    var i = 0;
//    var strdwbmj = "370000";
    var strdwbmj = "";
    var dwbmj = $(cbo).combo('getValues');
    for (var i = 0; i < dwbmj.length; i++) {
        var dwbm = dwbmj[i].substring(1);
        if (strdwbmj == "") {
            strdwbmj = dwbm;
        }
        else {
            strdwbmj = strdwbmj + "," + dwbm;
        }
    }
    return strdwbmj
}

/*
* 获取字符串的真实长度
*/
function getLength(str) {
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) {
            realLength += 1;
        }
        else {
            realLength += 2;
        }
    }
    return realLength;
}

/*
* 除去字符串的左空格
*/
function ltrim(s) {
    return s.replace(/(^\s*)/g, "");
}

/*
* 除去字符串的右空格
*/
function rtrim(s) {
    return s.replace(/(\s*$)/g, "");
}

/*
* 去除首尾空格
*/
function trim(s) {
    return s.replace(/(^\s*) | (\s*$)/g, "");
}

/*
* 判断字符串是否为空
*/
function isNull(data) {
    return (data == "" || data == undefined || data == null) ? true : false;
}

//电话验证 11位数字以1开头
function HRFilePhonevalidate(textString) {
    var reg = new RegExp(/^1\d{10}$/);
    if (reg.test(textString)) {
        return true;
    } else {
        return false;
    }
}

//邮箱验证
function mainIsValidate(textString){
    var reg = new RegExp(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/);
    if (reg.test(textString)) {
     return true;
    } else {
        return false;
    }
}

$(document).ajaxSuccess(function (event, xhr, settings) {
    //if (xhr.responseText == "unlogin") {
    //    window.top.location.href = "~/login.htm";
    //}
    //alert("xhr.responseText:" + xhr.responseText);
});
$(document).ajaxError(function (event, jqxhr, settings, thrownError) {
    if ("session_null" == jqxhr.responseText) {
        window.location = 'Login.htm';
    }
});

jQuery.cookie = function (key, value, options) {

    // key and value given, set cookie...
    if (arguments.length > 1 && (value === null || typeof value !== "object")) {
        options = jQuery.extend({}, options);

        if (value === null) {
            options.expires = -1;
        }

        if (typeof options.expires === 'number') {
            var days = options.expires, t = options.expires = new Date();
            t.setDate(t.getDate() + days);
        }

        return (document.cookie = [
            encodeURIComponent(key), '=',
            options.raw ? String(value) : encodeURIComponent(String(value)),
            options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
            options.path ? '; path=' + options.path : '',
            options.domain ? '; domain=' + options.domain : '',
            options.secure ? '; secure' : ''
        ].join(''));
    }

    // key and possibly options given, get cookie...
    options = value || {};
    var result, decode = options.raw ? function (s) { return s; } : decodeURIComponent;
    return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

// 错误信息处理
// 通过Ajax调用一般处理程序返回结果进行错误信息处理及过滤
// 一般处理程序返回数据为封装有异常信息及数据类转换的JSON
// 并且错误信息为result.ErrMsg,数据为result.Data
function doActionWithErrorHandle(result, func) {
    if (result == null || result == undefined) return;

    if (result.errMsg != undefined && result.errMsg != null && result.errMsg !='') {
        Alert(result.errMsg);
        return;
    }

    if (result.data != undefined && result.data != null )
        func(result.Data);
}
// 错误信息处理
// 通过Ajax调用一般处理程序返回结果进行错误信息处理及过滤
// 一般处理程序返回数据为封装有异常信息及数据类转换的JSON
// 并且错误信息为result.ErrMsg,数据为result.Data
function doActionWithErrorHandleNoAlert(winid, result, func) {
    if (result == null || result == undefined) return;

    if (result.errMsg != undefined && result.errMsg != null && result.errMsg !='') {
        $("#" + winid).html($("#" + winid).html() + '<br>'+ result.errMsg);
        return;
    }

    if (result.data != undefined && result.data != null )
        func(result.data);
}
/**
 * 自动关闭信息提示窗
 * @param msg 提示信息
 * @param icon 图标 没有 传 ''
 * @param tm 倒计时 单位秒
 * @constructor
 */
function AlertAutoClose(msg, icon, tm) {
    var interval;
    var time = 800;
    var x;
    if (null == tm || '' == tm) {
        x = Number(3);
    } else {
        x = Number(tm);
    }
    //
    if (null == icon || '' == icon) {
        icon = "infoSunnyIcon";
    }
    $.messager.alert(' ', '<div style="color: ##266e94;text-align: center"><strong>' + msg + '</strong></div>', icon, function () {
    });
    $(".messager-window .window-header .panel-title").append("系统提示（" + x + "秒后自动关闭）");
    interval = setInterval(fun, time);
    function fun() {
        --x;
        if (x == 0) {
            clearInterval(interval);
            $(".messager-body").window('close');
        }
        $(".messager-window .window-header .panel-title").text("");
        $(".messager-window .window-header .panel-title").append("系统提示（" + x + "秒后自动关闭）");
    }
}

function GUID() {
    this.date = new Date();

    /* 判断是否初始化过，如果初始化过以下代码，则以下代码将不再执行，实际中只执行一次 */
    if (typeof this.newGUID != 'function') {

        /* 生成GUID码 */
        GUID.prototype.newGUID = function () {
            this.date = new Date();
            var guidStr = '';
            sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
            sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
            for (var i = 0; i < 9; i++) {
                guidStr += Math.floor(Math.random() * 16).toString(16);
            }
            guidStr += sexadecimalDate;
            guidStr += sexadecimalTime;
            while (guidStr.length < 32) {
                guidStr += Math.floor(Math.random() * 16).toString(16);
            }
            return this.formatGUID(guidStr);
        }

        /*
        * 功能：获取当前日期的GUID格式，即8位数的日期：19700101
        * 返回值：返回GUID日期格式的字条串
        */
        GUID.prototype.getGUIDDate = function () {
            return this.date.getFullYear() + this.addZero(this.date.getMonth() + 1) + this.addZero(this.date.getDay());
        }

        /*
        * 功能：获取当前时间的GUID格式，即8位数的时间，包括毫秒，毫秒为2位数：12300933
        * 返回值：返回GUID日期格式的字条串
        */
        GUID.prototype.getGUIDTime = function () {
            return this.addZero(this.date.getHours()) + this.addZero(this.date.getMinutes()) + this.addZero(this.date.getSeconds()) + this.addZero(parseInt(this.date.getMilliseconds() / 10));
        }

        /*
        * 功能: 为一位数的正整数前面添加0，如果是可以转成非NaN数字的字符串也可以实现
        * 参数: 参数表示准备再前面添加0的数字或可以转换成数字的字符串
        * 返回值: 如果符合条件，返回添加0后的字条串类型，否则返回自身的字符串
        */
        GUID.prototype.addZero = function (num) {
            if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
                return '0' + Math.floor(num);
            } else {
                return num.toString();
            }
        }

        /* 
        * 功能：将y进制的数值，转换为x进制的数值
        * 参数：第1个参数表示欲转换的数值；第2个参数表示欲转换的进制；第3个参数可选，表示当前的进制数，如不写则为10
        * 返回值：返回转换后的字符串
        */
        GUID.prototype.hexadecimal = function (num, x, y) {
            if (y != undefined) {
                return parseInt(num.toString(), y).toString(x);
            } else {
                return parseInt(num.toString()).toString(x);
            }
        }

        /*
        * 功能：格式化32位的字符串为GUID模式的字符串
        * 参数：第1个参数表示32位的字符串
        * 返回值：标准GUID格式的字符串
        */
        GUID.prototype.formatGUID = function (guidStr) {
            var str1 = guidStr.slice(0, 8) + '-',
        str2 = guidStr.slice(8, 12) + '-',
        str3 = guidStr.slice(12, 16) + '-',
        str4 = guidStr.slice(16, 20) + '-',
        str5 = guidStr.slice(20);
            return str1 + str2 + str3 + str4 + str5;
        }
    }
}

// 获取页面传递参数
function getQueryString(paramStr, name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    //var r = window.location.search.substr(1).match(reg);
    var r = paramStr.match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
//        2018.1.23更改
// 系统名称
function getSystemName() {
    var sysName = [{unit: '湖北省检察机关'}, {system: '案件质量评查专题分析系统'}];
    // 调用一般处理程序获取注册信息
    // $.ajax({
    //     type: "GET",
    //     url: "./Handler/Account/login.ashx",
    //     data: { action: 'SysName' },
    //     async: false,
    //     success: function (result) {
    //         sysName = result;
    //     }
    // });
    // if (sysName == null || sysName == '') {
    //     sysName = '案件质量评查系统';
    // }

    return sysName;
}

// 单元格内容换行，两字一行
function TwoWordLine(text) {
    //return text;
    var len = text.length;
    var add = 3;
//    if (len > 4 && len < 6) add = 3;
//    if (len >= 6 && len < 9) add = 4;
//    if (len >= 9) add = 5;
    var result = text.substring(0, add);
    for (i = add; i < len; i += add) {
        result = result + '<br>' + text.substring(i, i + add);
    }
    return result;
}

function getRandomNumWithoutM(n, m) {
    var w = m - n;
    return parseInt(Math.random() * (m - n) + n, 10)
}
function getRandomNumWithoutN(n, m) {
    var w = m - n;
    return Math.floor(Math.random() * (m - n) + n) + 1;
}
function getRandomNumWithoutMN(n, m) {

}

// 日期格式转换（年-月-日）
function  sjzh(value) {
    if(value!=''&&value!=null&value!=undefined){
        var date= new  Date(value);
        date=date.getFullYear()+'年'+(date.getMonth()+ 1)+'月'+date.getDate() + '日';
        return date;
    }else {return "";}
}

// 日期格式转换（年-月-日 时:分:秒）
function  sjzhsrq(value) {
    if(value!=''&&value!=null&value!=undefined){
        var date= new  Date(value);
        date=date.getFullYear()+'年'+(date.getMonth()+1)+'月'+date.getDate()+"日 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        return date;
    }else {return "";}
}

//CST类型时间格式化
function formatCst(value) {
    //Wed Mar 22 13:38:37 CST 2017

    if(value!=''&&value!=null&value!=undefined) {
        var month = new Array();
        var date = "";

        month["Jan"] = 1;
        month["Feb"] = 2;
        month["Mar"] = 3;
        month["Apr"] = 4;
        month["May"] = 5;
        month["Jun"] = 6;
        month["Jul"] = 7;
        month["Aug"] = 8;
        month["Sep"] = 9;
        month["Oct"] = 10;
        month["Nov"] = 11;
        month["Dec"] = 12;

        var str = value.split(" ");
        // yyyy-mm-dd
        date += str[5] + '-' + month[str[1]] + '-' + str[2];

        return sjzh(date);
    }else {
        return "";
    }

}

// 获取单位全称
function getUnitFullName(dwmc)
{
    var themeTxt = "";
    if (dwmc.lastIndexOf("人民检察院") >= 0)
    {
        themeTxt = dwmc;
    }
    else if (dwmc.lastIndexOf("检察院") >= 0)
    {
        themeTxt = dwmc.substring(0, dwmc.lastIndexOf("检察院")) + "人民检察院";
    }
    else if (dwmc.lastIndexOf("院") >= 0)
    {
        themeTxt = dwmc.substring(0, dwmc.lastIndexOf("院")) + "人民检察院";
    }
    else
    {
        themeTxt = dwmc + "人民检察院";
    }

    return themeTxt;
}
//判断开始时间不能大于结束时间
function checkTime(startTime,endTime){
    //获取到的日期转化成中国标准时间
    var tmpStartTime = new Date(startTime);
    var tmpEndTime = new Date(endTime);

    var start = Date.parse(tmpStartTime);
    var end = Date.parse(tmpEndTime);
    if(start > end){
        Alert("开始时间不能大于结束时间！")
        return false;
    }else {
        return true;
    }
}

//dagegrid列内容tip呈现
function tipMessage(value) {
    //return "<span title='"+value+"' class='easyui-tooltip'>"+value+"</span>";
    return value;
}