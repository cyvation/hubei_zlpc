var TANGER_OCX_OBJ;

// 初始化文书控件
function InitDocCtr(docId) {
    if (TANGER_OCX_OBJ != null && typeof (TANGER_OCX_OBJ) != "undefined") {
        TANGER_OCX_OBJ.Close();
    }
    TANGER_OCX_OBJ = document.getElementById(docId);

    // TANGER_OCX_OBJ = document.getElementById("TANGER_OCX");
    if (TANGER_OCX_OBJ != null) {
        TANGER_OCX_OBJ.MenuBar = true;
        //TANGER_OCX_OBJ.activate(true);
        TANGER_OCX_OBJ.TitleBar = false;
        TANGER_OCX_OBJ.IsShowNetErrorMsg = false;
        //TANGER_OCX_OBJ.MenuBar = false;
        //加载文书模板
        //TANGER_OCX_OBJ.OpenFromURL("..\\..\\OfficeCtrlFiles\\WSMB\\hehe.doc");
    }
}

// 设置保存按钮状态
function SetSaveButtonState(docId,state) {
    var obj = document.getElementById(docId);
    obj.FileSave = state;
}

// 保存文书到服务器
function SaveToUrl(pagename) {
    var strInfo;
    try {
        strInfo = TANGER_OCX_OBJ.SaveToURL(pagename, "officectrl");
        if (strInfo == '') {
            return null;
        }
        else {
            var info = eval('(' + strInfo + ')');
            return info;
        }
    }
    catch (ex) {
        return "保存文件失败：" + strInfo;
    }
}

// 打开文件
function OpenFile(slpath, docId) {
    var error = "";
    TANGER_OCX_OBJ = null;

    if (TANGER_OCX_OBJ == null) {
        InitDocCtr(docId);
    }
    try {
        if (slpath != null && slpath != "") {
            /*TANGER_OCX_OBJ.OpenFromURL(slpath);
            //TANGER_OCX_OBJ.FileSave = false;
            //TANGER_OCX_OBJ.FileSaveAs = false;
            TANGER_OCX_OBJ.ToolBars = true;*/

            var sysUrl = getRootPath();
            var filePath = slpath.substring(sysUrl.length);
            var fileUrl = sysUrl + "/manage/getFileStream?filePath=" + filePath;
            TANGER_OCX_OBJ.OpenFromURL(fileUrl);
            TANGER_OCX_OBJ.ToolBars = true;
        } else {
            error = "文件路径为空";
        }
    }
    catch (ex) {
        error += "打开文件失败，未能找到文件！";
    }
    return error;
}

// 打开文书模板
function OpenTemplate(mbpath, docId) {
    var error = "";
    if (TANGER_OCX_OBJ == null) {
        InitDocCtr(docId);
    }
    try {
        if (mbpath != null && mbpath != "") {
            TANGER_OCX_OBJ.OpenFromURL(mbpath);
            TANGER_OCX_OBJ.ToolBars = true;
        } else {
            error = "文件路径为空";
        }
    }
    catch (ex) {
        error += "打开文书模板失败，未能找到文书模板！";
    }
    return error;
}

// 关闭文书
function CloseFile() {
    TANGER_OCX_OBJ.Close();
}

// 给书签设值
function SetBMarkValue(bmarkArry) {
    for (var i = 0; i < bmarkArry.length; i += 2) {
        TANGER_OCX_OBJ.SetBookmarkValue(bmarkArry[i], bmarkArry[i + 1]);
    }
}

// 获取书签值
function GetMarkValue(markKey) {

    /*var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(markKey);
     if(!bkmkObj)
     {
     Alert("Word 模板中不存在名称为：\""+markKey+"\"的书签！");
     }
     return bkmkObj.Range.Text;*/
    var value = "";
    try {
        value = TANGER_OCX_OBJ.GetBookmarkValue(markKey);
    } catch (e) {
        console.log(e);
    }
    return value;
}

// 设置书签值
function SetBookmarkValue(key, value) {
    TANGER_OCX_OBJ.SetBookmarkValue(key, value);

/*
    var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(key);
    if(!bkmkObj)
    {
        Alert("Word 模板中不存在名称为：\""+key+"\"的书签！");
    }
    var saverange = bkmkObj.Range
    saverange.Text = value;
    TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(BookMarkName,saverange);

*/

}

// 只读打开文书
function OpenForRead(wspath, docId) {
    var error = "";
    if (TANGER_OCX_OBJ == null) {
        InitDocCtr(docId);
    }
    try {
        //TANGER_OCX_OBJ.Close();
        if (wspath != null && wspath != "") {
            //TANGER_OCX_OBJ.OpenFromURL(wspath, true);
            //SetDocProtect();

            var filePath = wspath.substring(length(getRootPath()));
            var fileUrl = getRootPath() + "/manage/getFileStream?filePath=" + filePath;
            TANGER_OCX_OBJ.OpenFromURL(fileUrl, true);
        }
        else {
            error = "文件路径为空";
        }
    }
    catch (ex) {
        error += "打开文件失败，未能找到文件！";
    }
    return error;
}

// 让书签区域可编辑
function SetBmarkEditable(bmarkName) {

    try{
        TANGER_OCX_OBJ.ActiveDocument.Range(TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Item(bmarkName).Start, TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Item(bmarkName).End).Select();
        TANGER_OCX_OBJ.ActiveDocument.Application.Selection.Range.Editors.Add(-1);
    }catch (e){
        console.log(e);
    }

    /*TANGER_OCX_OBJ.ActiveDocument.Range(0, TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Item(bmarkName).Start - 1).Select();
    TANGER_OCX_OBJ.ActiveDocument.Application.Selection.Range.Editors.Add(-1);
    TANGER_OCX_OBJ.ActiveDocument.Application.Selection.WholeStory();
    var ed = TANGER_OCX_OBJ.ActiveDocument.Application.Selection.Range.End;
    TANGER_OCX_OBJ.ActiveDocument.Range(TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Item(bmarkName).End + 1, ed).Select();
    TANGER_OCX_OBJ.ActiveDocument.Application.Selection.Range.Editors.Add(-1);*/
}

//
function SetDocProtect() {
    var strLock = TANGER_OCX_OBJ.ActiveDocument.ProtectionType; //获取正文的保护状态
    if(strLock != 3) {
        TANGER_OCX_OBJ.ActiveDocument.Protect(3, true, "Cyvation");
        //TANGER_OCX_OBJ.ToolBars = false;
    }
}

//
function SetDocUnprotect() {
    TANGER_OCX_OBJ.ActiveDocument.DeleteAllEditableRanges(-1);
    var strLock = TANGER_OCX_OBJ.ActiveDocument.ProtectionType; //获取正文的保护状态
    if(strLock == 3){
        TANGER_OCX_OBJ.ActiveDocument.Unprotect("Cyvation");
        //TANGER_OCX_OBJ.ToolBars = true;
    }
}

function fileDownload() {
    window.open("Handler/Document/FileDownload.ashx?FilePath=OfficeRegisterTool.rar&FileName=OfficeRegisterTool.rar&pathType=ctrl");
}

// 表格追加行
function AppendTableRow() {
    var table = TANGER_OCX_OBJ.ActiveDocument.Tables(1);
    Alert(table);
    var rows = table.rows;
    Alert(rows);


    TANGER_OCX_OBJ.ActiveDocument.Tables(1).rows.add();
}