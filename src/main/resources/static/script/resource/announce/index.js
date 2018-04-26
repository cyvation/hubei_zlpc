var isOperate = false;
$(function () {

    // 权限
    init_announce_right();

    initDate_Tzgg();

    //控件初始化及初始数据加载
    initTzggControl();

    //按钮及事件
    registTzggEvent();

})

//通知公告权限控制
function init_announce_right() {
    if(isAdministrator()){
        isOperate = true;
    }else{
        var thisGncsj =  FUNCTION_PARAM.qxcs;
        if(!isNull(thisGncsj)){
            for(var i =0; i <thisGncsj.length; i++) {
                // 解析并遍历角色功能参数
                var role = JSON.parse(thisGncsj[i]);
                for (var j =0; j<role.length; j++) {
                    if(role[j].value == 1){
                        isOperate = true;
                    }
                }
            }
        }
    }
}

//初始化时间
function initDate_Tzgg() {

    $('#s_dateTzggCJSJBgn').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + 01 + '-' + 01

    });

    $('#s_dateTzggCJSJEnd').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });
}

// 控件初始化及初始数据加载
function initTzggControl() {

    // 可评查案件列表DataGrid初始化
    $('#gridTzggList').datagrid({
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        fit:true,
        idField: 'BH',
        toolbar: $('#divTzggSearch'),
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        multiSort:true,
        remoteSort: false,
        columns: [[
            {field: 'BH', title: '唯一标示', hidden: true },
            { field: 'BT', title: '标题', width: 150, sortable:true },
            { field: 'NR', title: '内容', width: 250, sortable:true },
            { field: 'GHSJ',title: '公告截至日期', width: 100, sortable:true,
                formatter:function (value,row,index) {
                    return sjzh(value)
                },
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }
                }
            },
            { field: 'SFJYKJ', title: '是否共享', width: 120 , sortable:true,
                formatter: function(value,row,index){
                    if (row.SFJYKJ == 'Y'){
                        return '是';
                    } else {
                        return '否';
                    }
                }
            },
            { field: 'CJSJ',title: '发布时间', width: 150,  sortable:true,
                formatter:function (value,row,index) {return sjzh(value)},
                sorter:function(a,b){
                    a = a.split('-');
                    b = b.split('-');
                    if (a[2] == b[2]){
                        if (a[0] == b[0]){
                            return (a[1]>b[1]?1:-1);
                        } else {
                            return (a[0]>b[0]?1:-1);
                        }
                    } else {
                        return (a[2]>b[2]?1:-1);
                    }
                }
            },

            { field: 'FBRXM', title: '发布人姓名', width: 80, sortable:true },
            { field: 'FBRDWBM', title: '发布单位编码', hidden: true },
            { field: 'action', title: '操作', width: 120, align: 'center',
                formatter: function (value, row, index) {
                    var e = '<a href="#" onclick="show_notice_window(' + row.BH + ')">查看</a> ';
                    if(row.FBRDWBM == userInfo.DWBM && row.FBRGH == userInfo.GH && isOperate){
                        var d = '<a id="btn_tzgg_xg" href="#" onclick="updateTzgg(' + index + ')">修改</a> ';
                        var f = '<a id="btn_tzgg_sc" href="#" onclick="deleteTzgg(' + index + ')">删除</a>';
                        return e+d+f;
                    } else{
                        return e;
                    }
                }
            }
        ]],
        onClickRow: function (rowIndex, rowData) {
            $('#gridTzggList').datagrid('clearSelections');
            $('#gridTzggList').datagrid('highlightRow', rowIndex);
        },
        onLoadSuccess: function () {
        }
    });
    // 分页控件(中文)
    $('#gridTzggList').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    getTzggDataSource();

}

// 按钮及事件
function registTzggEvent() {

    if(isOperate){
        //用户具有发布修改权限
        $("#btnTzggAssign").show();
        // 发布
        $('#btnTzggAssign').linkbutton({

            onClick: function () {
                insertTzgg();
            }
        });

    } else{
        $("#btnTzggAssign").hide();
    }

    // 查询
    $('#btnTzggSearch').linkbutton({

        onClick: function () {
            // 查询数据
            getTzggDataSource();
        }
    });


    // 取消附件
    $("#tzgg_cancle_upload").linkbutton({
        onClick:function(){
            $("#fileUpTZ").form('reset');
        }
    });


}

// 根据查询条件，获取通知公告列表
function getTzggDataSource() {
    var obj = new Object();
    obj.KeyWord = $("#keyword_announce").val();
    obj.FBSJBeg = $('#s_dateTzggCJSJBgn').datebox('getValue');
    obj.FBSJEnd =  $('#s_dateTzggCJSJEnd').datebox('getValue');

    // 调用一般处理程序获取数据
    $('#gridTzggList').datagrid({
        url: getRootPath()+'/resource/getTzgg',
        queryParams: { json : JSON.stringify(obj)}
    });
}

//发布通知公告
function insertTzgg() {
    commonTzgg();

    // 初始化输入框
    init_win_resource_tzgg(null, false);

    // 发布
    $('#btnTzggConfirm').linkbutton({
        iconCls: 'icon-ok',
        disabled: false,
        text: '发布',
        onClick: function () {
            //上传文件
            var bt = valiate($('#txtTzggBT').textbox('getValue'));
            if (bt == undefined || bt == "") {
                Alert("请添加标题。");
                return;
            }
            var nr = valiate($('#txt_tzgg_nr').textbox('getText'));
            var ghsj = $('#date_tzgg').datebox('getValue');
            var sfjykj = $("#gx").attr('checked') == "checked" ? 'Y':'N';
            var zlmc = $('#tzgg_zlmc').textbox("getValue");
            var obj = new Object();
            obj.bt = bt;
            obj.nr = nr;
            obj.ghsj = ghsj;
            obj.sfjykj = sfjykj;
            obj.zlmc = zlmc;
            if($("#tzgg_upload_file").val() != ''){
                upload_tzgg_file(function (filePath) {
                    obj.fj = filePath;
                    addOrEdit_Tzgg_record(obj);
                });
            } else {
                addOrEdit_Tzgg_record(obj);
            }
        }
    });
    // 弹出分配框
    $('#winTzggAssign').window({
        title: '通知公告【添加】'
    });
    $('#winTzggAssign').window('open');
}

//初始化通知公告弹出界面
function commonTzgg() {
    // 取消
    $('#btnTzggCancel').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#winTzggAssign').window('close');
        }
    });
}

//修改通知公告
function updateTzgg(index) {
    commonTzgg();

    var rowDatas = $('#gridTzggList').datagrid('getRows');
    // 初始化输入框
    init_win_resource_tzgg(rowDatas[index], false);
    var bh = rowDatas[index].BH;

    $('#btnTzggConfirm').linkbutton({
        iconCls: 'icon-ok',
        disabled: false,
        text: '确定',
        onClick: function () {
            var bt = valiate($('#txtTzggBT').textbox('getValue'));
            if (isNull(bt)) {
                Alert("请添加标题。");
                return;
            }
            var nr = $('#txt_tzgg_nr').textbox('getText');
            var zlmc = $('#tzgg_zlmc').textbox("getValue");
            var ghsj = $('#date_tzgg').datebox('getValue');
            var obj = new Object();
            obj.sfjykj = $("#gx").attr('checked') == "checked" ? 'Y':'N';
            obj.bt = bt;
            obj.bh=bh;
            obj.nr = nr;
            obj.ghsj = ghsj;
            obj.zlmc = zlmc;
            if($("#tzgg_upload_file").val() != ''){
                upload_tzgg_file(function (filePath) {
                    obj.fj = filePath;
                    addOrEdit_Tzgg_record(obj);
                });
            } else {
                addOrEdit_Tzgg_record(obj);
            }
        }
    });

    $('#winTzggAssign').window({
        title: '通知公告【修改】',

    });
    $('#winTzggAssign').window('open');
}

// 删除通知公告
function deleteTzgg(index) {
    Confirm("确认", "确定删除？", function (r) {
        if (r) {
            var rowDatas = $('#gridTzggList').datagrid('getRows');
            var bh = rowDatas[index].BH;

            $.ajax({
                url: getRootPath() + "/resource/delTzgg",
                data: { bh: bh },
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {
                    if (result.status == 200) {
                        getTzggDataSource();
                        load_notice();
                        Alert('删除成功！');
                    } else {
                        Alert('删除失败！');
                    }
                }
            });
        }
    });
}

// 数据验证
function valiate(data) {
    return (data == undefined) ? "" : data;
}

// 设置通知公告输入框初始值及只读属性
function init_win_resource_tzgg(data, readonly) {
    // 绑定数据
    if (data){
        $('#txtTzggBT').textbox('setText', data.BT);
        $('#txt_tzgg_nr').textbox('setText', data.NR);
        $('#date_tzgg').datebox('setValue', data.GHSJ);
        $('#tzgg_zlmc').textbox('setValue', data.ZLMC);
        if (data.SFJYKJ == 'Y') {
            $("#gx").attr('checked',true);
        }else {
            $("#bgx").attr('checked',true);
        }
    } else {
        $('#txtTzggBT').textbox('setText', "");
        $('#txt_tzgg_nr').textbox('setText', "");
        $('#date_tzgg').datebox('setValue', "");
        $('#tzgg_zlmc').textbox('setValue', "");
        $("#gx").attr('checked',true);
    }

    // 设置只读属性
    $('#txtTzggBT').textbox({ disabled: readonly });
    $('#txt_tzgg_nr').attr('disabled', readonly);
    $('#date_tzgg').datebox('readonly', readonly);
    $("input[name='sfjykj_tzgg']").attr("disabled", readonly);
}

//获取上传文件的名称并设置
$("#tzgg_upload_file").change(function (){
    var file_val = $("#tzgg_upload_file").val();
    var index = file_val .lastIndexOf("\\");
    file_val  = file_val .substring(index + 1, file_val .length);
    $("#tzgg_zlmc").textbox("setValue",file_val);
    // event.target.value='';
});

// 上传文件附件
function upload_tzgg_file(callback) {
    $.ajax({
        url:getRootPath() +'/notice/uploadFile',
        type:'POST',
        data:new FormData($("#fileUpTZ")[0]),
        async:false,
        cache:false,
        contentType:false,
        dataType:"json",
        processData:false,
        success:function (result) {
            if (result.code == 200 && callback) {
                callback(result.data);
            }else{
                Alert('上传文件失败!！');
            }
        },
        error: function (result) {
            Alert('上传文件失败！');
        }
    });
}

// 添加通知公告
function addOrEdit_Tzgg_record(tzgg){
    var note = isNull(tzgg.bh) ? "添加" : "修改";

    $.ajax({
        url: getRootPath() + "/notice/saveNotice",
        data: JSON.stringify(tzgg),
        type: 'post',
        async: false,
        dataType: 'json',
        contentType:"application/json",
        success: function (result) {
            if (result.code == 200) {
                getTzggDataSource();
                load_notice();
                $('#winTzggAssign').window('close');
                Alert(note + "成功");
            } else {
                Alert(note + '失败');
            }
        }
    });
}