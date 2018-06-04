//页面初始化
$(document).ready(function () {
    //初始化easyUI文本框
    Init_moniter_random_EasyUiCom();

    //评查管理模块选择

    $('.radiopcs').click(function () {
        $(this).children().addClass('redio_click_no');
        $(this).siblings().children().removeClass('redio_click_no');
        // var type =  $(this).attr("data-value");
        var type = $(".radiopcs .redio_click_no").attr('data-value');
        if (type == '1') {
            load_function("评查管理","view/manage/manage/index.html");
        }
        if (type == '2') {
            load_function("评查管理","view/manage/manage/person.html");
        }
        if (type == '3') {
            return;
        }
    });

});

//初始化easyUI控件
function Init_moniter_random_EasyUiCom() {

    // 如果为顶级单位编码
    // if (userInfo.DWBM == DJDWBM) {
    // 承办单位：
    $("#div_manage_case_pcdwbm").css('display','block');
    $('#combo_manage_manage_pcaj_dwbm').combotree({
        method: 'get',
        lines: true,
        panelWidth:250,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath()+'/organization/getDwbmTree',
        loadFilter:function (data) {
            return data.status==200?JSON.parse(data.value):[];
        },

        onLoadSuccess: function () {
            // 设置默认选中单位为当前单位
            $('#combo_manage_manage_pcaj_dwbm').combotree('setValue', userInfo.DWBM);
            index_addMousedownDiv(this,'combo_manage_manage_pcaj_dwbm');
        },

        onSelect: function (node) {

        }
    });
    // }


    //评查方式
    $('#combo_manage_manage_pcaj_pcfl').combotree({
        multiple: true,
        cascadeCheck:false,
        editable: false,
        lines: true,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        url: getRootPath() + '/manage/getpcfl',
        loadFilter: function (data) {
            var datas=[];
            for(var i=0;i<data.length;i++){
                if(data[i].id!='009'){//湖北：过滤线下评查
                    datas.push(data[i]);
                }
            }
            return datas
        },
        onLoadSuccess: function (node, data) {
            // if (data != null && data.length >= 1) {
            //     $('#combo_manage_manage_pcaj_pcfl').combotree('setValue', data[0].id); //评查方式默认选择
            //
            // }
            index_addMousedownDiv(this,'combo_manage_manage_pcaj_pcfl');
        }
    });

    //案件名称
    $('#txtbo_manage_manage_pcaj_ajmc').textbox({});

    //评查员
    $('#txtbo_manage_manage_pcaj_pcy').textbox({});

    //评查员
    $('#txtbo_manage_manage_pcaj_cbjcg').textbox({});

    //评查日期
    $('#date_manage_manage_pcaj_ksrq').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });
    $('#date_manage_manage_pcaj_jsrq').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + (new Date().getDate())
    });
    //评查结论树
    $('#combo_manage_manage_pcaj_pcjl').combotree({
        lines: true,
        multiple: true,
        cascadeCheck: false,
        editable: false,
        url: getRootPath() + '/filter/getPcjl',
        loadFilter: function (data) {
            if (data == null) {
                data = new Array();
            }
            /*var pcjlN = new Object();
             pcjlN.id = "1000000000001";
             pcjlN.text = "无问题";
             var attN = new Object();
             attN.PCJLBM = "1000000000001";
             attN.FBM = "-1";
             attN.PCJL = "无问题";
             pcjlN.attributes = attN;
             data.push(pcjlN);*/
            return data;
        },
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            if (data != null && data.length >= 1) {

            }
            index_addMousedownDiv(this,"combo_manage_manage_pcaj_pcjl");
        }
    });
    //评查状态树
    $('#combo_manage_manage_pcaj_pczt').combotree({
        lines: true,
        multiple: true,
        cascadeCheck: false,
        editable: false,
        url: getRootPath() + '/filter/getPczt',
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            /*if (data != null && data.length >= 1){
             }*/
            //$('#cbt_moniter_random_pczt').combotree('setValue', data.id);
            index_addMousedownDiv(this,"combo_manage_manage_pcaj_pczt");
        }
    });

    //承办人身份
    $('#combo_manage_manage_pcaj_sfldba').combotree({
        editable: false,
        panelWidth: 130,
        lines: true,
        multiple: true,
        cascadeCheck: false,
        onShowPanel: index_onShowPanel,
        onHidePanel: index_onHidePanel,
        onLoadSuccess: function (node, data) {
            index_addMousedownDiv(this, "combo_manage_manage_pcaj_sfldba");
        }
    });
    $('#combo_manage_manage_pcaj_sfldba').combotree("loadData", getCbrsfValues());

    //点击查询按钮事件
    $("#btn_manage_manage_pcaj_cx").click(function () {
        load_manage_manage_pcaj();
    });

    $('#datagrid_easyui_manage_manage_pcaj').datagrid({
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        loadMsg: '数据加载中，请稍等。。。',
        fitColumns: true,
        striped: true,
        rownumbers: true,
        remoteSort:false,
        multiSort:true,
        columns: [[
            {field: 'PCSLBM', title: '评查受理编码', hidden: true},
            {field: 'PCFLBM', title: '评查分类编码', hidden: true},
            {field: 'PCHDBM', title: '评查活动编码', hidden: true},
            {field: 'PCDWBM', title: '评查单位编码', hidden: true},
            {field: 'PCSAH', title: '评查案号', hidden: true},
            //{ field: 'PCFLMC', title: '评查分类名称', width: 90 },
            {field: 'AJMC', title: '案件名称', width: 230,sortable:true,
                formatter: function (value) { return tipMessage(value); }},
            {field: 'BMSAH', title: '部门受案号', width: 240},
            {field: 'BPC_DWMC', title: '承办单位', sortable:true },
            //{ field: 'BPC_BMMC', title: '承办部门', width: 90 },
            {field: 'BPC_MC', title: '承办检察官', width: 70, sortable:true  },
            {field: 'PCR_DWMC', title: '评查员单位', width: 90, sortable:true  },
            {field: 'PCR_MC', title: '评查员', width: 55,sortable:true },
            {field: 'SFLDBA', title: '承办人身份', width: 65,
                formatter: function (value) {
                if(value =="Y") {
                    return "领导";
                }else{
                    return "非领导"
                }
              }
            },
            {
                field: 'CJSJ', title: '评查日期',  fixed:true, width: 115, sortable:true,
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
            {field: 'PCJDMS', title: '评查状态', width: 80,sortable:true},
            {field: 'PCJL', title: '评查结论', width: 70,sortable:true},
            {
                field: 'CZ',
                title: '操作',
                width: 200,
                align: 'left',sortable:true,
                formatter: function (value, row, index) {
                    var e = '<a href="#" style="margin-right: 5px;" onclick="pcWinPage(' + index +',\'#datagrid_easyui_manage_manage_pcaj\',0)">查看 </a>'

                    //案件完结并且单位编码为顶级单位编码才可以删除案件
                    var d = '<a href="#" onclick="table_manage_manage_pcaj_drop(' + index + ')"> 删除 </a> ';
                    if (row.PCJDBH  == '011' && userInfo.DWBM != DJDWBM) {
                        d= '';
                    }
                    //常规评查的案件可以修改承办人身份标记
                    var f = "";
                    if(row.PCFLBM == "001"){
                        f = '<a href="#" onclick="load_win_manage_pcaj_cbrsf_modify(' + index + ')"> 变更承办人身份</a>';
                    }
                    // if(row.PCFLBM == '008'){
                    //     var r = '<a href="#" onclick="table_manage_manage_pcaj_change('+index+')">变更</a> ';
                    //     return e + r + d;
                    // } else
                    {
                        //湖北支持节点回退
                        if('011'==row.PCJDBH)// && userInfo.DWBM == DJDWBM
                            d+='<a href="#" onclick="table_manage_manage_backspace(' + index + ')"> 节点回退 </a>';
                        return e + d + f;
                    }
                }
            },
        ]],
    });

    // 分页控件(中文)
    $('#datagrid_easyui_manage_manage_pcaj').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录'
    });

    try {
        load_manage_manage_pcaj();
    }catch(e) {

    }
}

// 获取案件列表（查询）
function load_manage_manage_pcaj() {

    var pcdwbm = $('#combo_manage_manage_pcaj_dwbm').combotree('getValue');

    var obj = new Object();
    obj.PCFLBM = $('#combo_manage_manage_pcaj_pcfl').combotree('getValues').join(",");
    obj.PCDWBM =  pcdwbm ==""?userInfo.DWBM:pcdwbm;
    obj.AJMC = $('#txtbo_manage_manage_pcaj_ajmc').textbox('getText');
    obj.PCR_MC = $('#txtbo_manage_manage_pcaj_pcy').textbox('getText');
    obj.PCKSSJ = $('#date_manage_manage_pcaj_ksrq').datebox('getValue');
    obj.PCJSSJ = $('#date_manage_manage_pcaj_jsrq').datebox('getValue');
    obj.CBR = $('#txtbo_manage_manage_pcaj_cbjcg').textbox('getText');
    var tempSfldba = $('#combo_manage_manage_pcaj_sfldba').combotree('getValues').join(",").trim();
    obj.SFLDBA = getSplitString(tempSfldba, ",");
    var tempPCJL = $('#combo_manage_manage_pcaj_pcjl').combotree('getText');
    obj.PCJL = getSplitString(tempPCJL, ",");
    obj.PCZT = $('#combo_manage_manage_pcaj_pczt').combotree('getValues').join(",");//评查状态
    $('#datagrid_easyui_manage_manage_pcaj').datagrid({
        url: getRootPath() + '/manage/getPcaj',
        queryParams: {json: JSON.stringify(obj)}
    });
}

// 删除评查案件
table_manage_manage_pcaj_drop = function(index) {

    var rowDatas = $('#datagrid_easyui_manage_manage_pcaj').datagrid('getRows');
    var pcslbm = rowDatas[index].PCSLBM;
    var pcflbm = rowDatas[index].PCFLBM;

    Confirm("确认", "是否删除？", function (r) {
        if (r) {
            $.ajax({
                url: getRootPath() + "/manage/delPcaj",
                data: {pcslbm: pcslbm,pcflbm:pcflbm},
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (data) {
                    if (data.status == 200) {
                        Alert('删除评查案件成功');

                        load_manage_manage_pcaj();
                        return data.value;

                    } else {
                        Alert(data.note);
                    }
                }
            });
        }
    });
}
//变更专项活动人员分配的案件
table_manage_manage_pcaj_change = function (index) {

    $("#datagrid_easyui_manage_manage_pcaj").datagrid("selectRow",index);
    var aj = $("#datagrid_easyui_manage_manage_pcaj").datagrid("getSelected");
    if(!aj){
        Alert("请选择评查案件！");
        return;
    }
    $("#table_win_manage_manage_pcaj_modify_manual").datagrid({
        width: 'auto',
        height: 270,
        striped: true,
        fitColumns: true,
        singleSelect: true,
        //onClickRow: onClickRow,
        rownumbers: true,
        resizable: false,
        idField: 'ID',
        columns: [[
            {field: 'ID', title: '标识', hidden: true},
            {field: 'DWBM', title: '单位编码', hidden: true},
            {field: 'DWMC', title: '单位', width: 110},
            {field: 'GH', title: '工号', hidden: true},
            {field: 'MC', title: '姓名', width: 90},
            {field: 'PCZBM', title: '评查组编码', hidden: true},
            {field: 'PCZMC', title: '评查组', width: 100},
            {field: 'JSMC', title: '角色', width: 90},
            {field: 'JSBM', title: '角色编码', hidden: true}
        ]]
    });
    //' + index + ',' + row.BMSAH + ' index,row
    $('#win_manage_manage_pcaj_modify_manual').window('open');

    var obj =new Object();
    obj.pchdbm = aj.PCHDBM;
    $.ajax({
        url: getRootPath() + "/manage/getPcyInfoList",
        data:obj,
        type: 'get',
        async: true,
        dataType: 'json',
        success: function (data) {

            $("#table_win_manage_manage_pcaj_modify_manual").datagrid('loadData', data);

        }
    });
    $("#btn_win_manage_manage_pcaj_modify_manual_confirm").unbind("click");

    $("#btn_win_manage_manage_pcaj_modify_manual_confirm").bind("click", function () {

        var row = $("#table_win_manage_manage_pcaj_modify_manual").datagrid("getSelected");
        if (!row) {
            Alert("请选择评查人员！");
            return;
        }
        ShowProgress();
        var obj = new Object();
        obj.PCDWBM = aj.PCDWBM;
        obj.PCFLBM = aj.PCFLBM;
        obj.PCHDBM = aj.PCHDBM;
        obj.PCMBBM = aj.PCHDBM;
        obj.DWBM = aj.DWBM;
        obj.BMSAH = aj.BMSAH;
        obj.PCSLBM = aj.PCSLBM;
        obj.PCSAH = aj.PCSAH;
        obj.PCZ_BM = row.PCZBM;
        obj.PCZ_MC = row.PCZMC;
        obj.PCR_DWBM = row.DWBM;
        obj.PCR_DWMC = row.DWMC;
        obj.PCR_GH = row.GH;
        obj.PCR_MC = row.MC;
        var arr = new Array();
        arr.push(obj);
        // 分配案件
        $.ajax({
            url: getRootPath() + "/manage/assignCase",
            data: { json: JSON.stringify(arr)},
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data.status == 200){

                    // 更新评查案件列表
                    $("#datagrid_easyui_manage_manage_pcaj").datagrid("updateRow",{
                        index: index,
                        row: {
                            PCZ_BM : row.PCZBM,
                            PCZ_MC : row.PCZMC,
                            PCR_GH : row.GH,
                            PCR_MC : row.MC,
                            PCR_DWBM : row.DWBM,
                            PCR_DWMC : row.DWMC
                        }
                    });
                    CloseProgress();
                    $("#win_manage_manage_pcaj_modify_manual").window('close');
                }else {
                    CloseProgress();
                    Alert(data.note);
                }
                $("#win_manage_manage_pcaj_modify_manual").window('close');
            }
        });
    });
}

//湖北支持节点回退
table_manage_manage_backspace = function (index) {

    $("#datagrid_easyui_manage_manage_pcaj").datagrid("selectRow",index);
    var aj = $("#datagrid_easyui_manage_manage_pcaj").datagrid("getSelected");
    var pcslbm = aj.PCSLBM;
    var bmsah = aj.BMSAH;

    Confirm("确认", "确定要回退到评查办理阶段？", function (r) {
        if (r) {
            $.ajax({
                url: getRootPath() + "/manage/backspace",
                data: {pcslbm: pcslbm,bmsah:bmsah},
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (data) {
                    if (data.status == 200) {
                        Alert('评查节点回退成功');

                        load_manage_manage_pcaj();
                        return data.value;

                    } else {
                        Alert(data.note);
                    }
                }
            });
        }
    });

}

//湖北支持节点回退
var selectIndex ;
load_win_manage_pcaj_cbrsf_modify = function (index) {
    selectIndex = index;
    $("#datagrid_easyui_manage_manage_pcaj").datagrid("selectRow", selectIndex);
    var aj = $("#datagrid_easyui_manage_manage_pcaj").datagrid("getSelected");
    var sfldba = aj.SFLDBA;
    $("input[name='rd_eval_info_sfldba'][value='"+sfldba+"']").attr("checked",true);
    $("#btn_win_manage_pcaj_cbrsf_modify_confirm").unbind("click");

    $("#btn_win_manage_pcaj_cbrsf_modify_confirm").bind("click", function () {
        $("#datagrid_easyui_manage_manage_pcaj").datagrid("selectRow", selectIndex);
        var aj = $("#datagrid_easyui_manage_manage_pcaj").datagrid("getSelected");
        var obj = new Object();
        obj.PCSLBM = aj.PCSLBM;
        obj.SFLDBA = $("input[name='rd_eval_info_sfldba']:checked").val();

        $.ajax({
            url: getRootPath() + "/filter/updateJbxxByPcslbm",
            data: {json:JSON.stringify(obj)},
            type: 'post',
            async: true,
            dataType: 'json',
            success: function (result) {
                if (result.status == 200){
                    load_manage_manage_pcaj();
                    Alert("修改成功！");
                }else{
                    Alert(result.note);
                }
            }
        });
        $("#win_manage_pcaj_cbrsf_modify").window('close');
    });
    $('#win_manage_pcaj_cbrsf_modify').window({
        title: '编辑承办人身份'
    });
    $('#win_manage_pcaj_cbrsf_modify').window('open');
}


//动态获取承办人身份
function load_sfldba(bm) {
    $.ajax({
        type: 'get',
        async: false,
        url: getRootPath() + '/common/getDataDictionaryByLBBM?lbbm='+bm,
        dataType: 'json',
        success: function(data) {
            console.log('数据' + JSON.stringify(data))
            if(data.code==200){
                var data=data.data;
                if(bm==9104){
                    var html='';
                    for(var i=0;i<data.length;i++){
                        html+='<div class="radio">';
                        html+='<div class="redio_click">';
                        html+='<input class="input_radio_cljg" name="rd_eval_info_sfldba" type="radio" value="'+data[i].sm+'"/>';
                        html+='</div>'+data[i].mc+'</div>';
                    }
                    $('#loadSfldba').append(html)
                    var pcjlElement = $("input[name='rd_eval_info_sfldba'][value='N']");
                    pcjlElement.parent().addClass('redio_click_no');
                    pcjlElement.attr("checked",true);
                }
            }else {
                Alert('getDataDictionaryByLBBM 错误'+data.message)
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            Alert("消息", 'getDataDictionaryByLBBM 接口错误')
        }
    })
}






