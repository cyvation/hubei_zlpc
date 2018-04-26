
$(document).ready(function () {
    //init_get_path();
});
function init_get_path() {
    var rootPath=getRootPath();
    $.ajax({
        type: 'get',
        cache:false,
        url: rootPath+'/report/getPath',
        success: function (res) {

            OpenFile(data.value, "TANGER_OCX");
            SetSaveButtonState("TANGER_OCX", true);

            // 对一般处理程序返回的数据，进行错误处理及数据过滤
            /*   doActionWithErrorHandle(res, function (data) {
             debugger;

             });*/
        }
    });
}
function openFile() {
    try {
        //alert("1");

        //Alert(getRootPath() + "/plugin/officecontrol/TZS.doc");
        OpenFile(getRootPath() + "/plugin/officecontrol/评查流转单-测试.doc", "TANGER_OCX");
        //OpenForRead(getRootPath() + "/plugin/officecontrol/TZS.doc", "TANGER_OCX");
        //SetSaveButtonState("TANGER_OCX", true);

    } catch (ex){
        //debugger
    }
}

function openFileV1() {
    try {
        //alert("1");

        //Alert(getRootPath() + "/plugin/officecontrol/TZS.doc");
        var url = getRootPath() + "/Files/PCJZ/plugin/officecontrol/评查流转单-测试.doc";
        OpenFile(url, "TANGER_OCX");
        //OpenForRead(getRootPath() + "/plugin/officecontrol/TZS.doc", "TANGER_OCX");
        //SetSaveButtonState("TANGER_OCX", true);

    } catch (ex){
        //debugger
    }
}

function RangeROnly() {
    RangeReadOnly("PCYYJ");
}

function documentOpened() {
    InsertPcjbxx();
}

function InsertPcjbxx() {
    AppendTableRow();
    return;

    var pcdwmc = GetMarkValue("PCDWMC");
    if (isNull(pcdwmc)){
        SetBookmarkValue("PCDWMC", "浦东人民检察院");
    }

    var pcsxmc = GetMarkValue("PCSXMC");
    if (isNull(pcsxmc)){
        SetBookmarkValue("PCSXMC", "XXXXXXXXXXXX质量评查");
    }

    var pcymc = GetMarkValue("PCYMC");
    if (isNull(pcymc)){
        SetBookmarkValue("PCYMC", "吴鹏");
    }

    var wsnzsj = GetMarkValue("WSNZSJ");
    if (isNull(wsnzsj)){
        SetBookmarkValue("WSNZSJ", "2017年11月21日");
    }

}

function InsertPcyyj() {
    SetBookmarkValue("PCYYJ", "案件很优秀。");
    SetBookmarkValue("PCYLK", "评查员    2017年11月22日");
    RangeReadOnly("PCYYJ");
}

function InsertPcfzryj() {
    SetBookmarkValue("AGFZRYJ", "同意。");
    SetBookmarkValue("AGFZRLK", "案管负责人    2017年11月23日");
    RangeReadOnly("AGFZRYJ");
}

function InsertCbryj() {
    SetBookmarkValue("CBRYJ", "我不服。");
    SetBookmarkValue("CBRLK", "承办检察官    2017年11月24日");
    RangeReadOnly("CBRYJ");
}

function InsertCbbmyj() {
    SetBookmarkValue("CBBMYJ", "同意承办人意见。");
    SetBookmarkValue("CBBMLK", "业务部门    2017年11月25日");
    RangeReadOnly("CBBMYJ");
}

function save_doc_file() {

    try {
        document.all("TANGER_OCX").CancelLastCommand = true;

        // 编辑保存
        var url = getRootPath() + "/manage/savePCBG?wjlj=" + "/plugin/officecontrol/评查流转单-测试.doc";
        Alert(url);
        var fileresult = SaveToUrl(url);
        if (fileresult == null && fileresult != "") {
            Alert("修改评查报告成功。");
        } else {
            Alert("修改评查报告失败。" + fileresult);
        }

    } catch (ex) {
        //debugger
    }

}

function add_row() {
    
}