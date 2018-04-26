
// 请勿修改，否则可能出错
var userAgent = navigator.userAgent,
    rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
    rFirefox = /(firefox)\/([\w.]+)/,
    rOpera = /(opera).+version\/([\w.]+)/,
    rChrome = /(chrome)\/([\w.]+)/,
    rSafari = /version\/([\w.]+).*(safari)/;
var browser;
var version;
var ua = userAgent.toLowerCase();
function uaMatch(ua) {
    var match = rMsie.exec(ua);
    if (match != null) {
        return { browser: "IE", version: match[2] || "0" };
    }
    var match = rFirefox.exec(ua);
    if (match != null) {
        return { browser: match[1] || "", version: match[2] || "0" };
    }
    var match = rOpera.exec(ua);
    if (match != null) {
        return { browser: match[1] || "", version: match[2] || "0" };
    }
    var match = rChrome.exec(ua);
    if (match != null) {
        return { browser: match[1] || "", version: match[2] || "0" };
    }
    var match = rSafari.exec(ua);
    if (match != null) {
        return { browser: match[2] || "", version: match[1] || "0" };
    }
    if (match != null) {
        return { browser: "", version: "0" };
    }
}

$(function () {

    isBrowser();

    trends_collocate();

    //加载单位树：
    load_input_login_dwbm();

    var $loginGo = $("#login_go");
    $loginGo.bind("click", function () {
        signIn();
    });

    // 读取Cookie值 (用户名)
    var username = decodeURI(getCookie("username"));
    $("#input_login_username").val(username);
    certificateWord();
});

// 加载登录单位列表
function load_input_login_dwbm() {

    $('#input_login_dwbm').combotree({
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/account/loginDwbmTree',
        onLoadSuccess: function (node, data) {
            // 读取Cookie值 (单位编码)
            var dwbm = getCookie("dwbm");
            if (isNull(dwbm) && data != null && data.length >= 1){
                dwbm = data[0].id;
            }
            $('#input_login_dwbm').combotree('setValue', dwbm);
            index_addMousedownDiv(this,'input_login_dwbm');
        }
    });
}

/**
 * 登录
 */
function signIn() {
    var dwbm = $('#input_login_dwbm').combotree('getValue');
    var username = $("#input_login_username").val();
    var password = $("#input_login_password").val();

    if (!username) {
        $("#yhmcw").text("请输入用户名！");
        return;
    }
    if (!password) {
        $("#mmcw").text("请输入密码！");
        return;
    }

    $.ajax({
        url: getRootPath() + "/account/signIn",
        type: 'post',
        async: false,
        data: {
            "dwbm": dwbm,
            "username": username,
            "password": password
        },
        dataType: 'json',
        success: function (res) {
            if (res.status === 200) {
                window.location.href = "./index.html";
            } else {
                $("#mmcw").text(res.note);
            }
        },
        error: function () {
            Alert("登录验证异常!");
        }
    });
}

//回车登录
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        $("#login_go").click()
    }
})

function goLogin() {
    window.location.href = "login.html";
}

//判断口令输入框是否有值
function certificateWord() {
    $("#certificate_word").bind('input', function () {
        if ($(this).val() != "") {
            $("#login_go").removeClass("jzdjys");
            $(".login_andl").css("display", "none");
        } else {
            $("#login_go").addClass("jzdjys");
            $(".login_andl").css("display", "block");
        }
    })
}

// 2018,1,23 日（写字体）更改    动态配置登陆名称
function trends_collocate() {
    $("#span_unit_name").html( getSystemName()[0].unit);
    $("#span_system_name").html( getSystemName()[1].system);

}

//验证浏览器版本
function  isBrowser() {
    var browserMatch = uaMatch(userAgent.toLowerCase());
    if (browserMatch.browser) {
        browser = browserMatch.browser;
        version = browserMatch.version;
    }
    if(browser.toLowerCase()=="Chrome".toLowerCase() && version =="41.0.2272.104"){
        $("#divBrowserInfo").css("display","none");
    }else{
        $("#divBrowserInfo").css("display","");
    }
}

