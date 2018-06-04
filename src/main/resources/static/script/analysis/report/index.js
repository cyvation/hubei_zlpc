/**
 * Created by kwz on 2018/5/17.
 */
var add_pcfs;//新增评查报告选中评查方式
$(document).ready(function () {
    $("#win_upload_rept").append("<iframe style='position:absolute;z-index:-1;width:100%;height:100%;left:0;top:0;scrolling:no;border-left:0;border-top:0;' frameborder='1'></iframe>");
    //初始化评查方式combobox
    init_rept_win_func();
    combobox_analysis_report_pcfs();
    //加载评查单位树
    tree_analysis_report_pcdw();
    //初始化评查报告列表
    init_rept_datagrid();
    /*注册按钮事件*/
    regist_event_rept();


})

//初始化页面功能
function init_rept_win_func() {
    //隐藏删除下载按钮
    $("#btn_delete_rept ").css("display", "none");
    $("#btn_download_rept ").css("display", "none");
};

//初始化评查方式
function combobox_analysis_report_pcfs() {
    $("#combobox_analysis_report_pcfs").combotree({
        url: getRootPath() + '/analysisReport/loadFtlList',
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        multiple: true,
        editable: false,
        loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        },
        onLoadSuccess: function (node, data) {
            $("#combobox_analysis_report_pcfs").combotree('setValues', data[0].id);
            index_addMousedownDiv(this, "combobox_analysis_report_pcfs");
        },
        onSelect: function (record) {
            load_rept_datagrid();
        },
    })
    $("#combobox_analysis_report_sc").combotree({
        url: getRootPath() + '/analysisReport/loadFtlList',
        method: 'get',
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        multiple: false,
        editable: false,
        loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
        },
        onLoadSuccess: function (node, data) {
            $("#combobox_analysis_report_pcfs").combotree('setValues', data[0].id);
            index_addMousedownDiv(this, "combobox_analysis_report_pcfs");
        }
    })
}

//初始化评查单位树
function tree_analysis_report_pcdw() {
    $('#tree_analysis_report_pcdw').combotree({
        method: 'get',
        editable: false,
        panelWidth: 250,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        url: getRootPath() + '/organization/getDwbmTree',
        async: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        loadFilter: function (data) {
            return data.status == 200 ? JSON.parse(data.value) : [];
        },
        onLoadSuccess: function (node, data) {
            // 设置默认选中单位为当前单位
            if (data.length > 0) {
                //选中自己单位
                $('#tree_analysis_report_pcdw').combotree('setValue', userInfo.DWBM);
            }
            index_addMousedownDiv(this, "tree_analysis_report_pcdw");
            load_rept_datagrid();
        },

        onSelect: function (node) {
            // 设置当前选中单位
            load_rept_datagrid();
        }
    });

}

//按钮绑定事件
function regist_event_rept() {
//打开新增评查报告
    $("#btn_add_rept").linkbutton({
        onClick: function () {
            $("#combobox_analysis_report_name").textbox("setValue","")
            open_win_add_rept();
        }

    });
//下一步按钮点击事件
    $("#btn_wind_eval_next").linkbutton({
        onClick: function () {
            var t = $("#combobox_analysis_report_sc").combotree('tree'); // 得到树对象
            var n = t.tree('getSelected');
            console.log(n);
            var name = $("#combobox_analysis_report_name").textbox("getValue");//设置新增报告名称
            close_win_add_rept();
            clear_upload_win_rept();
            var obj=new Object();
            obj.dwbm=userInfo.DWBM;
            obj.wordName=name;
            obj.ftlFile= n.WSMBLJ;
            obj.ftlMb=add_pcfs;
          /*  $.ajax({
                type: 'GET',
                url: getRootPath()+'/analysisReport/generateWord',
                data: {json:JSON.stringify(obj)},
                success: function (result) {
                    if (result.code == 200){
                        window.location.href=getRootPath()+result.data;
                    }
                }
            });*/
        }
    });
//下载按钮点击事件
    /*  $("#btn_download_rept").linkbutton({
     onClick: function () {

     ShowProgress();

     }
     })*/
}

/*清除上传组件内容*/
function clear_upload_win_rept() {
    $('#pcflbm_up').textbox('setValue', '');
    $('#pcpcbm_up').textbox('setValue', '');
    $('#pclbbm_up').textbox('setValue', '');
    $('#bgmc_upload_rept').textbox('setValue', '');
}

//打开上传文件面板
function open_win_upload_rept() {
    // var bm = node.id;
    // var bits = getLength(bm.toString());
    //确定
    $('#btn_ok_upload_rept').linkbutton({
        // iconCls: 'icon-ok',
        onClick: function () {
            uploadfile_bg_new_rept();
        }
    });
    //关闭
    $('#btn_cancel_upload_rept').linkbutton({
        // iconCls: 'icon-cancel',
        onClick: function () {
            $('#win_upload_rept').window('close');
            //clear_upload_win_rept();
            $('#bgmc_upload_rept').textbox('setValue', '');
            $('#file_upload_rept').textbox('setValue', '');
        }
    });

    $('#win_upload_rept').window('open');

}

// 初始化评查报告datagrid列表
function init_rept_datagrid() {
    $('#dg_pcbg_rept').datagrid({
        width: 'auto',
        striped: true,
        fitColumns: false,
        autoRowHeight: false,
        singleSelect: true,
        rownumbers: true,
        loadMsg: '',
        toolbar: $('#div_toolbar_rept'),
        columns: [[/*{field: 'ck', checkbox: true},*/
            {field: 'WJMC', title: '报告名称', width: 150},
            {
                field: 'CJSJ', title: '创建时间', width: 70, sortable: true,
                formatter: function (value, row, index) {
                    return sjzh(value);
                }
            },
            {field: 'NZRXM', title: '创建人', width: 50}

        ]],
         loadFilter: function (data) {
            return data.status == 200 ? data.value : [];
         },
        /*onLoadSuccess: function () {//取消全选按钮
            $('#dg_pcbg_rept').datagrid('getPanel').find('div.datagrid-header-check input[type=checkbox]').eq(0).remove();
        },*/
        onSelect: function (rowIndex, rowData) {
            $.ajax({});
            $("#btn_delete_rept ").css("display", "");
            $("#btn_download_rept ").css("display", "");
            $("#btn_download_rept ").attr("href", getRootPath() + "/Files/PCBG" + rowData.wjcflj);
            //使用office控件查看评查报告
            view_doc_office_ocx_rept();
        },
        onUnselect: function () {
            $("#btn_delete_rept ").css("display", "none");
            $("#btn_download_rept ").css("display", "none");
        }
    });
}

//加载评查报告datagrid列表数据
function load_rept_datagrid() {
    var pcfs = $("#combobox_analysis_report_pcfs").combobox('getValues').join(",");
    var current_select_unit = $("#tree_analysis_report_pcdw").combotree('getValues').join(",") ;
    var obj=new Object();
    obj.dwbm=current_select_unit;
    obj.ftlMb=pcfs;
    $('#dg_pcbg_rept').datagrid({
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        url: getRootPath() + '/analysisReport/loadFtlDataList',
        queryParams: {json:JSON.stringify(obj)},

    })
}

//使用office控件查看报告
function view_doc_office_ocx_rept() {
    document.getElementById('divDoc_bg').style.display = "block";
    show_rpt_doc_panel("doc");
    var row = $('#dg_pcbg_rept').datagrid('getSelected');
    if (row.cjrmc == "") {
        document.getElementById('divDoc_bg').style.display = "none";
        Alert("文件路径不存在");
    }
    else {
        var docId = "TANGER_OCX_PCBG";
        OpenFile(getRootPath(), docId);
    }
}

//文件上传
function uploadfile_bg_new_rept() {

    //判断文件是否选择
    var filename = $('#bgmc_upload_rept').textbox('getValue');
    if (isNull(filename)) {
        Alert("请输入文件名称！");
        return;
    }
    var file = $('#file_upload_rept').textbox('getValue');
    if (isNull(file)) {
        Alert("请选择上传文件！");
        return;
    }
    ShowProgress();
    var formData = new FormData($("#form_upload_rept")[0]);
    var obj = new Object();
    obj.PCFLBM = add_pcfs;
    //obj.PCHDBM = pchdbm;
    obj.DWBM = current_select_unit;
    obj.PCZYLX = "4"; //0.评查方案 1.评查卷宗 4.评查报告
    formData.append("json", JSON.stringify(obj));
    // 开始上传文件
    $.ajax({
        async: false,
        cache: false,
        type: "post",
        data: formData,
        url: getRootPath() + '/report/fileUploadRept',
        dataType: 'json',
        contentType: false, //必须
        processData: false, //必须
        success: function (result) {
            CloseProgress();
            if (result.status != 200) {
                Alert("文件上传错误！");
                return;
            }
            // 卷宗信息写入数据库
            var obj = new Object();
            // obj.PCZYBM = pchdbm;
            obj.WJMC = filename;
            obj.WJLX = "0"; //文件类型（0.附件 1.评查方案 2.评查流转单 3.评查案件报告 4.评查汇总报告 5.自动评查报告）
            obj.GXLX = "1"; //活动内共享
            obj.JZMLH = "00000000";//等于文书模板编号
            obj.FJZWJBH = "-1";
            obj.WSCFLJ = result.value.filePathName;
            obj.PCFLBM = add_pcfs;
            $.ajax({
                type: 'POST',
                url: getRootPath() + "/report/addJzwj",
                data: {json: JSON.stringify(obj)},
                dataType: "json",
                success: function (result) {
                    if (result.status != 200) {
                        Alert(result.note);
                        return;
                    }
                    $('#bgmc_upload_rept').textbox('setValue', '');
                    $('#file_upload_rept').textbox('setValue', '');
                    $('#win_upload_rept').window('close');
                    Alert('文件上传成功！');
                    $('#dg_pcbg_rept').datagrid('reload');
                }
            });
        },
        error: function (arg1, arg2, arg3) {
            CloseProgress();
            Alert("文件上传错误");
            return;
        }
    });
    // $("#form_upload_rept").bind("click", function () { });
}

function show_rpt_doc_panel(type) {
    $("#layout_center_rept").css('display', 'block');
    // $("#pcbl_pcxx_center_left").css('display', 'none');
    //show_eval_info_pcjz_area(); //显示评查卷宗浮动面板

    switch (type) {
        case "doc":
            resize_div_rpt_doc_area();
            // 加载文书
            document.getElementById('divDoc_bg').style.display = "";
            break;
        default:
            break;
    }
}

function resize_div_rpt_doc_area() {
    // 评查报告控件展示区域
    var height = $("#layout_center_rept_toolpnl").height();
    $("#divDoc_bg").css("height", (height - 10) + 'px');

}

//加载评查方式
function load_cbt_win_rpt_pcfl() {

    $('#cbt_win_eval_build_pcfl').combotree({
        method: 'get',
        lines: true,
        url: getRootPath() + '/manage/getpcfl',
        onLoadSuccess: function (node, data) {
            var pcflbm;
            // 默认选中随机评查，同时选中刑事案件
            if (data != null && data.length >= 1) {
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

//打开新增报告面板
function open_win_add_rept() {
    $("#pop_ups_win").window("open");
    load_cbt_win_rpt_pcfl();
}

//关闭新增报告面板
function close_win_add_rept() {
    $("#pop_ups_win").window("close");
}



