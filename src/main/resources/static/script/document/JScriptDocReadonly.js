var TANGER_OCX_OBJ_READONLY;

// 初始化文书控件
function InitDocCtrReadonly(docId) {
    if (TANGER_OCX_OBJ_READONLY != null && typeof (TANGER_OCX_OBJ_READONLY) != "undefined") {
        TANGER_OCX_OBJ_READONLY.Close();
    }
    TANGER_OCX_OBJ_READONLY = document.getElementById(docId);

    // TANGER_OCX_OBJ_READONLY = document.getElementById("TANGER_OCX");
    if (TANGER_OCX_OBJ_READONLY != null) {
        TANGER_OCX_OBJ_READONLY.MenuBar = true;
        //TANGER_OCX_OBJ_READONLY.activate(true);
        TANGER_OCX_OBJ_READONLY.TitleBar = false;
        TANGER_OCX_OBJ_READONLY.IsShowNetErrorMsg = false;
        //TANGER_OCX_OBJ_READONLY.MenuBar = false;
        //加载文书模板
        //TANGER_OCX_OBJ_READONLY.OpenFromURL("..\\..\\OfficeCtrlFiles\\WSMB\\hehe.doc");
    }
}

// 设置保存按钮状态
function SetSaveButtonStateReadonly(docId,state) {
    var obj = document.getElementById(docId);
    obj.FileSave = state;
}

// 打开文件
function OpenFileReadonly(slpath, docId) {
    var error = "";
    TANGER_OCX_OBJ_READONLY = null;

    if (TANGER_OCX_OBJ_READONLY == null) {
        InitDocCtrReadonly(docId);
    }
    try {
        if (slpath != null && slpath != "") {
            /*TANGER_OCX_OBJ_READONLY.OpenFromURL(slpath);
            //TANGER_OCX_OBJ_READONLY.FileSave = false;
            //TANGER_OCX_OBJ_READONLY.FileSaveAs = false;
            TANGER_OCX_OBJ_READONLY.ToolBars = true;*/

            var sysUrl = getRootPath();
            var filePath = slpath.substring(sysUrl.length);
            var fileUrl = sysUrl + "/manage/getFileStream?filePath=" + filePath;
            TANGER_OCX_OBJ_READONLY.OpenFromURL(fileUrl);
            TANGER_OCX_OBJ_READONLY.ToolBars = true;
        } else {
            error = "文件路径为空";
        }
    }
    catch (ex) {
        error += "打开文件失败，未能找到文件！";
    }
    return error;
}

// 关闭文书
function CloseFile() {
    TANGER_OCX_OBJ_READONLY.Close();
}

//
function SetDocProtect() {
    var strLock = TANGER_OCX_OBJ_READONLY.ActiveDocument.ProtectionType; //获取正文的保护状态
    if(strLock != 3) {
        TANGER_OCX_OBJ_READONLY.ActiveDocument.Protect(3, true, "Cyvation");
        //TANGER_OCX_OBJ_READONLY.ToolBars = false;
    }
}

//
function SetDocUnprotect() {
    TANGER_OCX_OBJ_READONLY.ActiveDocument.DeleteAllEditableRanges(-1);
    var strLock = TANGER_OCX_OBJ_READONLY.ActiveDocument.ProtectionType; //获取正文的保护状态
    if(strLock == 3){
        TANGER_OCX_OBJ_READONLY.ActiveDocument.Unprotect("Cyvation");
        //TANGER_OCX_OBJ_READONLY.ToolBars = true;
    }
}
