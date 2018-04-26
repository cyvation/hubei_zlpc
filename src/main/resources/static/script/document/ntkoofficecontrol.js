﻿// 请勿修改，否则可能出错
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
var browserMatch = uaMatch(userAgent.toLowerCase());
if (browserMatch.browser) {
    browser = browserMatch.browser;
    version = browserMatch.version;
}
//html += browser);
/*
谷歌浏览器事件接管
*/
function OnComplete2(type, code, html) {
    //alert(type);
    //alert(code);
    //    alert(html);
    //    alert("SaveToURL成功回调");
}
function OnComplete(type, code, html) {
    //alert(type);
    //alert(code);
    //    alert(html);
    //    alert("BeginOpenFromURL成功回调");
}
function OnComplete3(str, doc) {
    TANGER_OCX_OBJ.activeDocument.saved = true; //saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
    //	TANGER_OCX_OBJ.SetReadOnly(true,"");
    //TANGER_OCX_OBJ.ActiveDocument.Protect(1,true,"123");
    //获取文档控件中打开的文档的文档类型
    /*switch (TANGER_OCX_OBJ.doctype) {
        case 1:
            fileType = "Word.Document";
            fileTypeSimple = "wrod";
            break;
        case 2:
            fileType = "Excel.Sheet";
            fileTypeSimple = "excel";
            break;
        case 3:
            fileType = "PowerPoint.Show";
            fileTypeSimple = "ppt";
            break;
        case 4:
            fileType = "Visio.Drawing";
            break;
        case 5:
            fileType = "MSProject.Project";
            break;
        case 6:
            fileType = "WPS Doc";
            fileTypeSimple = "wps";
            break;
        case 7:
            fileType = "Kingsoft Sheet";
            fileTypeSimple = "et";
            break;
        default:
            fileType = "unkownfiletype";
            fileTypeSimple = "unkownfiletype";
    }*/
    //    if (TANGER_OCX_OBJ.ToolBars == false) {
    //        TANGER_OCX_OBJ.ActiveDocument.Protect(3, true, "Cyvation");
    //    }
    //alert("ondocumentopened成功回调");

    documentOpened();
}
function publishashtml(type, code, html) {
    //    alert(html);
    //    alert("Onpublishashtmltourl成功回调");
}
function publishaspdf(type, code, html) {
    //    alert(html);
    //    alert("Onpublishaspdftourl成功回调");
}
function saveasotherurl(type, code, html) {
    //    alert(html);
    //    alert("SaveAsOtherformattourl成功回调");
}
function dowebget(type, code, html) {
    //    alert(html);
    //    alert("OnDoWebGet成功回调");
}
function webExecute(type, code, html) {
    //    alert(html);
    //    alert("OnDoWebExecute成功回调");
}
function webExecute2(type, code, html) {
    //    alert(html);
    //    alert("OnDoWebExecute2成功回调");
}

function fileCommand(cmd,canceled) {

    if (cmd == 3) //user has clicked on file save menu or button
    {
        //save to server
        //cancel default process
        save_doc_file();
        document.all("TANGER_OCX").CancelLastCommand = true;
    }

}

function CustomMenuCmd(menuPos, submenuPos, subsubmenuPos, menuCaption, menuID) {
    alert("第" + menuPos + "," + submenuPos + "," + subsubmenuPos + "个菜单项,menuID=" + menuID + ",菜单标题为\"" + menuCaption + "\"的命令被执行.");
}
var classid = 'C9BC4DFF-4248-4a3c-8A49-63A7D317F404';
var html = "";
if (browser == "IE") {
    html += '<!-- 用来产生编辑状态的ActiveX控件的JS脚本-->   ';
    html += '<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->   ';
    html += '<object id="TANGER_OCX" classid="clsid:6AA93B4B-D450-4a80-876E-3909055B0640"';
    html += 'codebase="plugin/officecontrol/OfficeControl.cab#version=5,0,2,2" width="100%" height="100%">   ';
    html += '<param name="IsUseUTF8URL" value="true"/>   ';
    html += '<param name="IsUseUTF8Data" value="true"/>   ';
    html += '<param name="Titlebar" value="-1" />';
    html += '<param name="Toolbars" value="-1" />';
    html += '<param name="Menubar" value="-1" />';
    html += '<param name="MakerCaption" value="成都赛威讯信息技术研究所"/>';
    html += '<param name="MakerKey" value="A3F9C486F1E1E469380C0A494394F9F7D58402D0"/>';
    html += '<param name="ProductCaption" value="案件质量评查"/> ';
    html += '<param name="ProductKey" value="0855204B17AF90DA948EB28D7199130A724B48D0"/>';
    html += '<param name="Caption" value="成都赛威讯信息技术研究所"/>   ';
    html += '<span style="color: red">不能装载文档控件。请检查浏览器选项中的安全设置，并<a href="#" onclick="fileDownload()">下载Office注册工具</a>，注册成功后请重新打开浏览器。</span>';
    html += '</object>';
}
else if (browser == "firefox") {
    html += '<object id="TANGER_OCX" type="application/ntko-plug"  codebase="plugin/officecontrol/OfficeControl.cab#version=5,0,2,2" width="100%" height="100%" ForOnSaveToURL="OnComplete2" ForOnBeginOpenFromURL="OnComplete" ForOndocumentopened="OnComplete3"';
    html += 'ForOnpublishAshtmltourl="publishashtml"';
    html += 'ForOnpublishAspdftourl="publishaspdf"';
    html += 'ForOnSaveAsOtherFormatToUrl="saveasotherurl"';
    html += 'ForOnDoWebGet="dowebget"';
    html += 'ForOnDoWebExecute="webExecute"';
    html += 'ForOnDoWebExecute2="webExecute2"';
    html += 'ForOnFileCommand="FileCommand"';
    html += 'ForOnCustomMenuCmd2="CustomMenuCmd"';
    html += '_IsUseUTF8URL="-1"   ';

    html += '_MakerCaption="成都赛威讯信息技术研究所"';
    html += '_MakerKey="A3F9C486F1E1E469380C0A494394F9F7D58402D0"';
    html += '_ProductCaption="案件质量评查" ';
    html += '_ProductKey="0855204B17AF90DA948EB28D7199130A724B48D0"';
    html += '_IsUseUTF8Data="-1"   ';
    html += '_BorderStyle="1"   ';
    html += '_BorderColor="14402205"   ';
    html += '_MenubarColor="14402205"   ';
    html += '_MenuButtonColor="16180947"   ';
    html += '_MenuBarStyle="3"  ';
    html += '_MenuButtonStyle="7"   ';
    html += '_WebUserName="NTKO"   ';
    html += 'clsid="{6AA93B4B-D450-4a80-876E-3909055B0640}" >';
    html += '<span style="color: red">不能装载文档控件。请检查浏览器选项中的安全设置，并<a href="#" onclick="fileDownload()">下载Office注册工具</a>，注册成功后请重新打开浏览器。</span>';
    html += '</object>   ';
} else if (browser == "chrome") {
    html += '<embed id="TANGER_OCX" wmode="transparent" clsid="{6AA93B4B-D450-4a80-876E-3909055B0640}" OnFileCommand="FileCommand11" ForOnSaveToURL="OnComplete2" ForOnBeginOpenFromURL="OnComplete" ForOndocumentopened="OnComplete3"';
    html += 'ForOnpublishAshtmltourl="publishashtml"';
    html += 'ForOnpublishAspdftourl="publishaspdf"';
    html += 'ForOnSaveAsOtherFormatToUrl="saveasotherurl"';
    html += 'ForOnDoWebGet="dowebget"';
    html += 'ForOnDoWebExecute="webExecute"';
    html += 'ForOnDoWebExecute2="webExecute2"';
    html += 'ForOnFileCommand="fileCommand"';
    html += 'ForOnCustomMenuCmd2="CustomMenuCmd"';
    html += '_MakerCaption="成都赛威讯信息技术研究所"';
    html += '_MakerKey="A3F9C486F1E1E469380C0A494394F9F7D58402D0"';
    html += '_ProductCaption="案件质量评查" ';
    html += '_ProductKey="0855204B17AF90DA948EB28D7199130A724B48D0"';
    html += 'codebase="plugin/officecontrol/OfficeControl.cab#version=5,0,2,2" width="100%" height="100%" type="application/ntko-plug" ';
    html += '_IsUseUTF8URL="-1"   ';
    html += '_IsUseUTF8Data="-1"   ';
    html += '_BorderStyle="1"   ';
    html += '_BorderColor="14402205"   ';
    html += '_MenubarColor="14402205"   ';
    html += '_MenuButtonColor="16180947"   ';
    html += '_MenuBarStyle="3"  ';
    html += '_MenuButtonStyle="7"   ';
    html += '_WebUserName="NTKO"   ';
    html += '_Caption="成都赛威讯信息技术研究所" >';
//    html += '<span style="color: red">不能装载文档控件。请检查浏览器选项中的安全设置，并<a href="#" onclick="fileDownload()">下载Office注册工具</a>，注册成功后请重新打开浏览器。</span>';
    html += '</embed>';
} else if (Sys.opera) {
    alert("sorry,ntko web印章暂时不支持opera!");
} else if (Sys.safari) {
    alert("sorry,ntko web印章暂时不支持safari!");
}
$('#divDoc').append(html);