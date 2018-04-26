/**
 * Created by wh on 2018/1/18.
 */

var LOCAL_PATH = 'C:/案件质量评查系统客户端/Temp/';
//接收传来的参数
var skip_cs;

$(function () {
        // 接收传入的加密参数
        var url = document.URL;
        // 接收传入的加密参数
        var value = url.split("?")[1];

        // 解密参数并转JSON
        var obj = JSON.parse(unescape(base64decode(value)));
        // 将传来的参数由skip_cs接收
        skip_cs = obj.pageCs;
        // 如果传入了网页title，赋值给网页
        $("#title").text(obj.pageTitle);
        // skip_layout加载传入的网页url
        $('#skip_layout').panel({
            href: obj.pageUrl,
            fit: true,
            onLoad:function(){
                // 判断是否有回调方法
                if(obj.callBack){
                    // 传入的回调方法为一个数组，遍历方法，并执行方法
                    for(var i in obj.callBack){
                            var func = eval(obj.callBack[i]);
                            new func();
                    }
                }
            }
        });
})
