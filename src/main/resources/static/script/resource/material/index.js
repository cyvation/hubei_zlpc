/**
 * Created by lei on 2018/1/27.
 */
// 评查资料功能参数（1.典型案例--2，评查规定--3,新法速递）
// {type: 1, name: 典型案例}
var material_params;
var isOperate = false;
$(document).ready(function () {
    // 功能参数
    material_params = JSON.parse(FUNCTION_PARAM.gncs);
    if (!material_params){
        Alert("功能参数配置不正确，请联系管理员！");
        return;
    }

    // 初始化评查资料
    init_resource_pczl();

    // 默认数据加载
    load_resource_pczl_data();

    // 评查资料权限控制
    init_material_right();
});

//评查资料权限控制
function init_material_right() {
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


// 给页面样式样式绑定事件及
function init_resource_pczl() {

    //开始时间
    $("#date_resource_material_kssj").datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    //结束时间
    $("#date_resource_material_end").datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });

    // 典型案例DataGrid初始化
    $('#table_resource_pczl_list').datagrid({
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkOnSelect: false,
        loadMsg: '数据加载中，请稍后...',
        pagination: true,
        rownumbers: true,
        fit: true,
        idField: 'bh',
        toolbar: $('#div_tool_search'),
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        multiSort:true,
        remoteSort: false,
        columns: [[
            {field: 'bh', title: '唯一标示', hidden: true},
            {field: 'zlmc', title: '标题', width: 150, sortable:true},
            {field: 'msxx', title: '内容', width: 150, sortable:true},
            {field:'gh',title:'工号',width: 150,hidden: true },
            //{field:'grzl',title:'个人资料',width: 150 },
            {
                field: 'sfgx', title: '是否共享', width: 120, sortable:true,
                formatter: function (data, row, index) {
                    if (row.gxbm == '0') {
                        return '不共享';
                    } else {
                        return '共享';
                    }
                }
            },
            {field: 'mc', title: '上传人', width: 150, sortable:true},
            {
                field: 'cjsj', title: '上传时间', width: 150, sortable:true,
                formatter: function (value) { return sjzh(value);},
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
            {field: 'dwmc', title: '上传单位', width: 150, sortable:true},
            {
                field: 'action', title: '操作', width: 150, align: 'center',
                formatter: function (data, row, index) {
                    var a = '<a href="#" onclick="show_material_window(' + row.bh + ')">查看</a> ';
                    var e = "";
                    if (!isNull(row.glzy)) {
                        e = '<a href="' + getRootPath() + row.glzy + '" download="" > 下载 </a> ';
                    }
                    var d = ' <a href="#" onclick="upd_material_record(' + index + ')"> 修改 </a> ';
                    var f = ' <a href="#" onclick="del_material_record(' + index + ')"> 删除 </a>';

                    return userInfo.GH == row.gh && isOperate ? a + e + d + f : a + e;
                }
            }
        ]],
        // groupField: 'grzl',
        // view: groupview,
        // groupFormatter: function (value, rows) {
        //     if(value == 1){
        //         return "个人"+' - '+ rows.length + ' 条';
        //     }else {
        //         return "共享"+' - '+ rows.length + ' 条';
        //     }
        // },
        onClickRow: function (rowIndex, rowData) {
            $('#gridTzggList').datagrid('clearSelections');
            $('#gridTzggList').datagrid('highlightRow', rowIndex);
        },
        onLoadSuccess: function () {
        }
    });

    // 分页控件(中文)
    $('#table_resource_pczl_list').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    // 查询
    $("#btn_ressource_pczl_search").unbind('click');
    $("#btn_ressource_pczl_search").bind('click',function () {
        // 查询数据
        load_resource_pczl_data();
    });

    // 上传按钮，上传文件：
    $("#btn_ressource_pczl_add").unbind('click');
    $("#btn_ressource_pczl_add").bind('click',function () {
        // 查询数据
        add_resource_material();
    });

    // 取消附件
    $("#pczl_cancle_upload").linkbutton({
        onClick:function(){
            $("#fileUploadPczl").form('reset');
        }
    });

}

// 加载数据
function load_resource_pczl_data() {

    var keyword = $("#pczl_keyword").textbox('getText');
    var date_start = $("#date_resource_material_kssj").datebox('getValue');
    var date_end = $("#date_resource_material_end").datebox('getValue');

    // 获取数据：
    $('#table_resource_pczl_list').datagrid({
        url: getRootPath()+'/material/getPczl',
        queryParams: {
            keyword: keyword,
            zllx: material_params.type,
            date_start: date_start,
            date_end: date_end
        }
    });

}

// 查看
function  show_material_window(bh) {

    $("#marquee_window").window({
        title: material_params.name,
        width: 900,
        height: 600,
        top:screen.width < 1900 ? "3%" : "20%",
        collapsible: false,
        minimizable: false,
        maximizable: false,
        modal: true,
        resizable: true
    });


    $.ajax({
        url: getRootPath()+'/material/getPczlByBh',
        data: { bh: bh},
        dataType: 'json',
        success: function (data) {
            if (data.code==200){
                var  pczl=data.data;
                $("#marqee_window_content_top").text(pczl.zlmc);
                $("#marqee_window_content_mainbody").textbox('setText', pczl.msxx);
                $("#marquee_window_user").html(pczl.mc);
                $("#marquee_window_time").html(sjzh(pczl.cjsj));
                if(isNull(pczl.glzy)){
                    $('#tzgg_fjload').css('display', 'none');
                }else{
                    $("#marquee_window_href").attr("href",getRootPath()+pczl.glzy);
                    $("#marquee_window_mc").text(pczl.bz);
                    $('#tzgg_fjload').css('display', '');
                }
                $("#marquee_window").show();
            }
        }
    });


}

// 添加
function add_resource_material() {

    // 清除典型案例弹出框内数据
    $('#pczl_title').textbox('setText', "");
    $("#gx").attr('checked',true);
    $('#pczl_msxx').textbox('setText', "");
    $('#pczl_zlmc').textbox('setText', "");
    // $("#pczl_upload_file").val(" ");
    document.getElementById('fileUploadPczl').reset();

    // 确定
    $('#btn_resource_pczl_confirm').linkbutton({
        iconCls: 'icon-ok',
        disabled: false,
        text: '确定',
        onClick: function () {
            var obj = new Object();
            obj.zlmc = $("#pczl_title").textbox('getText');
            if (isNull(obj.zlmc)) {
                Alert("请输入名称！");
                return;
            }
            obj.zllx = material_params.type;
            obj.gxbm = $("#gx").attr('checked') == "checked" ? '1':'0';
            obj.msxx = $('#pczl_msxx').textbox('getText');
            obj.bz = $("#pczl_zlmc").textbox("getText");

            if($("#pczl_upload_file").val() != ''){
                upload_material_file(function(filePath){
                    obj.glzy = filePath;
                    addOrEdit_material_record(obj);
                })
            } else {
                addOrEdit_material_record(obj);
            }
        }
    });

    // 取消按钮绑定：
    $("#btn_resource_pczl_cancle").linkbutton({
        onClick:function(){
            $('#win_resource_pczl_assign').window('close');
        }
    });

    // 显示窗口：
    $("#win_resource_pczl_assign").window({
        title: material_params.name
    });
    $('#win_resource_pczl_assign').window('open');
}

// 编辑
function upd_material_record(index) {
    // 初始化输入框
    var row = $("#table_resource_pczl_list").datagrid('getRows')[index];

    // 标题：
    $("#pczl_title").textbox('setText',row.zlmc);
    // 是否共享
    if (row.gxbm == '1') {
        $("#gx").attr('checked',true);
    }else {
        $("#bgx").attr('checked',true);
    }
    // 描述信息
    $("#pczl_msxx").textbox('setText',row.msxx);
    $("#pczl_zlmc").textbox("setText",row.bz);

    var bh = row.bh;
    $('#btn_resource_pczl_confirm').linkbutton({
        iconCls: 'icon-ok',
        disabled: false,
        text: '确定',
        onClick: function () {

            var obj = new Object();
            obj.bh = bh;
            obj.zlmc = $('#pczl_title').textbox('getText');
            if (isNull(obj.zlmc)) {
                Alert("请输入名称！");
                return;
            }
            obj.gxbm = $("#gx").attr('checked') == "checked" ? '1':'0';
            obj.zllx = material_params.type;
            obj.msxx = $('#pczl_msxx').textbox('getText');

            if($("#pczl_upload_file").val() != ''){
                obj.bz = $("#pczl_zlmc").textbox("getText");
                upload_material_file(function(filePath){
                    obj.glzy = filePath;
                    addOrEdit_material_record(obj);
                })
            } else {
                addOrEdit_material_record(obj);
            }
        }
    });

    // 取消按钮绑定：
    $("#btn_resource_pczl_cancle").linkbutton({
        onClick:function(){
            $('#win_resource_pczl_assign').window('close');
        }
    });

    // 显示窗口：
    $("#win_resource_pczl_assign").window({
        title: material_params.name
    });

    $('#win_resource_pczl_assign').window('open');
}

// 删除
function del_material_record(index) {

    Confirm("确认", "确定删除？", function (r) {
        if (r) {
            var row = $("#table_resource_pczl_list").datagrid('getRows')[index];
            var bh = row.bh;

            $.ajax({
                url: getRootPath() + "/material/delPczlByBh",
                data: { bh: bh },
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 200) {
                        load_resource_pczl_data();
                        Alert('删除成功！');
                    } else {
                        Alert('删除失败！');
                    }
                }
            });
        }
    });
}

// 获取上传文件的名称并设置
$("#pczl_upload_file").change(function (){
    var file_val = $("#pczl_upload_file").val();
    file_val  = file_val .substring(file_val .lastIndexOf("\\") + 1, file_val .length);
    $("#pczl_zlmc").textbox("setText",file_val);
});

// 上传文件附件
function upload_material_file(callback) {

    // 评查资料功能参数（1.典型案例--2，评查规定--3,新法速递）
    // {type: 1, name: 典型案例, "savePath": "DXAL"}
    var formData = new FormData($("#fileUploadPczl")[0]);
    formData.append("materialtype",material_params.type);
    if (material_params.savePath == undefined) {
        formData.append("savePath","null");
    } else {
        formData.append("savePath",material_params.savePath);
    }


    $.ajax({
        url:getRootPath() +'/material/uploadFile',
        type:'POST',
        data: formData,
        async:false,
        cache:false,
        contentType:false,
        dataType:"json",
        processData:false,
        success:function (result) {
            if (result.code = 200 && callback) {
                callback(result.data);
            }else{
                Alert('上传文件失败！');
            }
        },
        error: function (data) {
            Alert('上传文件失败！');
        }
    });
}

// 新增资料
function addOrEdit_material_record(material){

    var note = isNull(material.bh) ? "添加" : "修改";
    $.ajax({
        url: getRootPath() + "/material/addOrEdit",
        data: JSON.stringify(material),
        type: 'post',
        async: false,
        dataType: 'json',
        contentType:"application/json",
        success: function (result) {
            if (result.code == 200) {
                load_resource_pczl_data();
                $('#win_resource_pczl_assign').window('close');
                Alert(note + "成功");
            } else {
                Alert(note + '失败');
            }
        }
    });
}