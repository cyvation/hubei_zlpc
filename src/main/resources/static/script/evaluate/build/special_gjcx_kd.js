/**
 * Created by cjc on 2017/12/15.
 */
var row_count = 0;//行下标index
var kp_gjcx_jsonData;//缓存数据方便应用
var request =new Object();//缓存请求条件
$(function () {
    $(".kd_tiaojian_delet").hide();
    init_gjcx_kd_datebox();
    load_gjcxtj();
    loadFixedDwData();
    init_gjcxGrid();
    click_cx();
});

// 完成日期框初始化
function init_gjcx_kd_datebox() {

    //完成日期
    $('#date_eval_build_gjcx_kp_dept_begin').datebox({
        editable: false,
        value: new Date().getFullYear() + '-01-01'
    });

    $('#date_eval_build_gjcx_kp_dept_end').datebox({
        editable: false,
        value: new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate())
    });
}

//初始化加载第一行条件下拉数data
function loadFixedDwData(){
    $.ajax({
        url: getRootPath()+'/advanceQuery/getDwbmTreeList',
        type: 'get',
        success: function (data) {
            var dwbm_jsonData = JSON.parse(data.data);
            //$('#kd_value_fixed').combotree('loadData',dwbm_jsonData);
            $('#kd_value_fixed').combotree({
                data:dwbm_jsonData,
                onShowPanel: index_onShowPanel,
                onHidePanel: index_onHidePanel,
                onLoadSuccess: function (node, data) {
                    index_addMousedownDiv(this,'kd_value_fixed');
                }
            });
            if(dwbm_jsonData.length > 0){
                $('#kd_value_fixed').combotree('setValue',dwbm_jsonData[0].id);
            }
        },
        error: function (result) {

        }
    });
}
//获取查询条件
function load_gjcxtj() {
    $.ajax({
        url:getRootPath()+'/advanceQuery/getAllCondition',
        type:'get',
        dataType:'json', 
        success:function (data) {
            kp_gjcx_jsonData = data.data;
            suanfa_loadData(kp_gjcx_jsonData);
        },
        error:function (result) {
            
        }
    });
}
//格式化算法下拉框数据
var suanfa_data = [];
function suanfa_loadData(data) {
    for(var i = 0;i < data.length;i++){
        var suanfaObj = {"value":"","bh":""};
        suanfaObj.value = data[i].mc;
        suanfaObj.bh = data[i].bh;
        suanfa_data.push(suanfaObj);
    }
    // load_sf(suanfa_data,'kd_sf_0');
}
//算法下拉框数据加载
function load_sf(suanfa_data,sfId) {
    $('#'+sfId).combobox({
        textField:'value',
        data:suanfa_data,
        editable:false,
        onSelect:function (node) {//选择算法加载对应的运算符和值
            var curBH = node.bh;
            var curIndex = $(this).attr('data-index');
            var curValueId = "kd_value_"+curIndex;
            for(var i = 0;i < kp_gjcx_jsonData.length;i++){
                if(kp_gjcx_jsonData[i].mc == node.value){
                    var curYSFdata = kp_gjcx_jsonData[i].ysf;
                    //为当前value绑定所需要的bh,bm,parent
                    $("#"+curValueId).attr("data-bh",kp_gjcx_jsonData[i].bh);
                    $("#"+curValueId).attr("data-bm",kp_gjcx_jsonData[i].bm);
                    $("#"+curValueId).attr("data-parent",kp_gjcx_jsonData[i].parent);
                    $("#"+curValueId).attr("data-gyzd",kp_gjcx_jsonData[i].gyzd);
                    $("#kd_ysf_"+curIndex).combobox({
                        valueField:'value',
                        textField:'text',
                        editable:false,
                        data:famaterYSF(curYSFdata)
                    });
                    switch (kp_gjcx_jsonData[i].lx){
                        case 'specialTree':
                            $("#"+curValueId).val("");
                            $("#"+curValueId).removeAttr("data-tip");
                            $("#"+curValueId).addClass("easyui-combotree");
                            $.parser.parse("#kd_value_box_"+curIndex);
                            load_sf_value(curBH,curValueId,"specialTree");
                            break;
                        case 'combotree':
                            $("#"+curValueId).val("");
                            $("#"+curValueId).removeAttr("data-tip");
                            $("#"+curValueId).addClass("easyui-combotree");
                            $.parser.parse("#kd_value_box_"+curIndex);
                            load_sf_value(curBH,curValueId,"combotree");
                            break;
                        case 'input':
                            $("#kd_value_box_"+curIndex).empty();
                            $("#kd_value_box_"+curIndex).append('<input data-tip="input" data-gyzd='+ kp_gjcx_jsonData[i].gyzd +' data-bm='+ kp_gjcx_jsonData[i].bm +' data-parent='+ kp_gjcx_jsonData[i].parent +' data-bh='+ kp_gjcx_jsonData[i].bh +'  style="height:22px;" class="easyui-textbox kd_sf_value" id='+ curValueId +'>')
                            //load_sf_value(curBH,curValueId,"input");
                            break;
                    }
                }
            }
        }
    });
}
//运算符数据格式化
function famaterYSF(data){
        var KeyARR = [];
        for(var j = 0;j<data.length;j++){
            var curkeyObj = data[j];
            for(var key in curkeyObj){
                var k = curkeyObj[key];
                for(var realkey in k){
                    var realkeyValue = k[realkey];
                    var keyObj = {
                       value:realkeyValue,
                       text:realkey
                    };
                    KeyARR.push(keyObj);
                }
            }
        }
        return KeyARR;
}
//getvalue加载下拉框
function load_sf_value(bh,valueId,type){
    $.ajax({
        url:getRootPath()+"/advanceQuery/getSingleCondition",
        type:"get",
        data:{
            bh:bh
        },
        success:function (data) {
            var curData = data.data;
            if(type == "combotree"){
                var realData = formatValue(curData,valueId);
               // $("#"+valueId).combotree("loadData",realData)
                $("#"+valueId).combotree({
                    data: realData,
                    onShowPanel: index_onShowPanel,
                    onHidePanel: index_onHidePanel,
                    onLoadSuccess: function (node, data) {
                        index_addMousedownDiv(this,valueId);
                    }
                });


            }else if(type == "specialTree"){
                var realData = formatSpecialValue(curData,valueId);
              //  $("#"+valueId).combotree("loadData",realData)
                $("#"+valueId).combotree({
                    data: realData,
                    onShowPanel: index_onShowPanel,
                    onHidePanel: index_onHidePanel,
                    onLoadSuccess: function (node, data) {
                        index_addMousedownDiv(this,valueId);
                    }
                });

            }else if(type == "input"){

            }
        },
        error:function(result){

        }
    });
}
//拼接类型为combotree的数据
function formatValue(data,valueId){
    var valueARR = [];
    for(var i = 0;i<data.length;i++){
        var temp = data[i];
        var resultTemp= findChildren(temp,valueId);
        valueARR.push(resultTemp)
    }
    return valueARR;
}
//递归查找children并赋值
function findChildren(temp,valueId) {
    var valueObj = {
        id:"",
        text:"",
        children:[],
        attributes:{
            bm:$("#"+valueId).attr("data-bm"),
            bh:$("#"+valueId).attr("data-bh"),
            parent:$("#"+valueId).attr("data-parent"),
            key:$("#"+valueId).attr("data-gyzd"),
            value:""
        }
    };
    //判断参数
    if(temp.value == null || temp.value == undefined || temp.value == ""){
        valueObj.attributes.value = temp.dm
    }else {
        valueObj.attributes.key = temp.dm,
        valueObj.attributes.value = temp.value
    }
    //显示
    if(isNumber(parseInt(temp.dm))){
        valueObj.id=temp.dm;
    }else{
        valueObj.id="1";
    }
    valueObj.text=temp.mc;
    if(temp.children){
        if(temp.children.length != 0){
            var childrens= temp.children;
            for(var j=0;j<childrens.length;j++){
                var result= findChildren(childrens[j],valueId);
                valueObj.children.push(result);
            }
        }
        return valueObj;
    }else{
        return valueObj;
    }
}
//拼接SpecialTree的下拉框数据
function formatSpecialValue(data,valueId) {
    var length = data.length;
    var specialArr = [];
    for(var key in data){
        var attrARR = [];
        var keyValue = data[key];
        for(var i = 0;i<keyValue.length;i++){
            var attrObj =  {
                    bm:$("#"+valueId).attr("data-bm"),
                    bh:$("#"+valueId).attr("data-bh"),
                    parent:$("#"+valueId).attr("data-parent"),
                    key:keyValue[i].dm,
                    value:keyValue[i].value
                }
            attrARR.push(attrObj);
        }
        var specialObj = {
            id:"1",
            text:key,
            children:[],
            attributes:attrARR
        };
        specialArr.push(specialObj);
    }
    return specialArr;
}
//添加条件行
var row_count = 0;
function addNewRow() {
    row_count++;
    if(row_count > 0){
        $('.kd_tiaojian_delet').show();
        $('.kd_tiaojian_delet').css('display','block');
    }
    var html = '';
    html += '<div class="kd_tj_row" id=kd_row_'+row_count+'>';
    html +='<div class="kd_tj_box_sf">';
    html +='<input style="height:22px;" class="easyui-combobox kd_sf"  id=kd_sf_'+ row_count +' data-index='+ row_count +'>';
    html +='</div>';
    html +='<div class="kd_tj_box_ysf">';
    html +='<input style="height:22px;" class="easyui-combobox kd_ysf"  id=kd_ysf_'+ row_count +'>';
    html +='</div>';
    html +='<div class="kd_tj_box_value" id=kd_value_box_'+ row_count +'>';
    html +='<input style="height:22px;" class="easyui-textbox kd_sf_value" id=kd_value_'+ row_count +'>';
    html +='</div>';
    html +='<div class="kd_tj_box_action" data-index='+ row_count +'>';
    // html +='<div class="tiaojian_btn tiaojian_add" onclick="addNewRow()">增加</div>';
    html +='<div class="kd_tiaojian_btn kd_tiaojian_delet" onclick="delRow(this)">x</div>';
    html +='</div>';
    html += '</div>';
    $("#kd_gjcx_tiaojian").append(html);
    $.parser.parse('#kd_row_'+row_count);
    load_sf(suanfa_data,'kd_sf_'+row_count);
}
//删除条件行
function delRow(obj){
    var rowArr = $(".kd_tj_row");
    if(rowArr.length == 1 || rowArr == undefined){
        $(".kd_tiaojian_delet").hide();
    }else{
        $(".kd_tiaojian_delet").show();
    }
    $(obj).parent().parent().remove();
}

//点击查询获取数据OBJ
function click_cx(){
    $('#kd_cx_btn').click(function () {
        var postArr = new Array();
        var rowArr = $(".kd_tj_row");
        for(var m = 0;m < rowArr.length;m++){
            var curValueName = rowArr[m].children[2].children[0].id;
            var curYSFName = rowArr[m].children[1].children[0].id;
            var ysf_value = $("#" + curYSFName).combobox("getValue");
            //判断value框为输入框的情况
            if($("#"+curValueName).attr('data-tip') == "input"){
                var postObj ={
                    bm:$("#"+curValueName).attr("data-bm"),
                    bh:$("#"+curValueName).attr("data-bh"),
                    parent:$("#"+curValueName).attr("data-parent"),
                    key:$("#"+curValueName).attr("data-gyzd"),
                    value:"%"+$("#"+curValueName).val()+"%",
                    ysf:ysf_value
                };
                postArr.push(postObj);
            }else{//value框为combotree的情况
                try {
                    var t = $("#"+curValueName).combotree("tree");
                    var a = t.tree('getSelected').attributes;
                }
                catch (e){
                   continue;
                }
                //specialTree的情况
                if(Array.isArray(a) == true){
                    for(var h = 0;h<a.length;h++){
                        a[h].ysf = ysf_value;
                    }
                    Array.prototype.push.apply(postArr, a);
                }else{//普通combotree的情况
                    var postObj ={
                        bm:a.bm,
                        bh:a.bh,
                        parent:a.parent,
                        key:a.key,
                        value:"'" + a.value + "'",
                        ysf:ysf_value
                    };
                    postArr.push(postObj);
                }
            }
        }
        var gridOpts = $('#kd_gjcx_datagrid').datagrid('getPager').data('pagination').options;
        var selectCbdw = $('#kd_value_fixed').combotree('getValue');
        var startDate = $('#date_eval_build_gjcx_kp_dept_begin').datebox('getValue');
        var endDate = $('#date_eval_build_gjcx_kp_dept_end').datebox('getValue');
        request.page = gridOpts.pageNumber;
        request.pageSize = gridOpts.pageSize;
        request.parmarter=postArr;
        request.extCbdw =  selectCbdw;
        request.pcflbm = pcxx.PCFLBM;
        request.startDate = startDate;
        request.endDate = endDate;
        load_gjcxGrid(request)
    });
}

//初始化查询结果datagrid
function init_gjcxGrid(){
    $("#kd_gjcx_datagrid").datagrid({
        fitColumns: true,
        singleSelect:true,
        striped:true,
        rownumbers: true,
        idField: 'BMSAH',
        pagination:true,
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100],
        pageNumber:1,
        columns:[[
            {field:'BMSAH',title:'部门受案号',width:160 },
            {field:'AJMC',title:'案件名称',width:160,
                formatter: function (value) { return tipMessage(value); }},
            {field:'AJLB_MC',title:'案件类别',width:90},
            {field:'CBDW_MC',title:'承办单位',width:90},
            {field:'CBR',title:'承办检察官',width:90},
            {field:'SLRQ',title:'受理日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'WCRQ',title:'完成日期',width:90,
                formatter: function (value) {
                    return sjzh(value);
                }
            },
            {field:'DWBM',title:'单位编码',hidden:true},
            {field:'SXGZBM',title:'筛选规则编码',hidden:true},
            {field: 'action', title: '操作', width: 90, align: 'center',
                formatter: function (value, row, index) {
                    var r = '<a href="#" onclick="pcWinPage(' + index +',\'#kd_gjcx_datagrid\',1)">查看 </a> ';
                    /*var r = '<a href="#" onclick="lookup_special_case(\'' + row.BMSAH + '\')">查看</a> ';*/
                    var d = '<a href="#" onclick="get_custom_filter_obj_gjcx(' + index + ')">评查</a> ';
                    return r + d;
                }
            }
        ]],
        onSelect:function(rowIndex,rowData){
            // alert("我被点击了！");
        },
        onLoadSuccess:function(){

        }
    });
    $('#kd_gjcx_datagrid').datagrid('getPager').pagination({
        beforePageText: '第',
        afterPageText: '页   共{pages}页',
        displayMsg: '当前显示【{from} ~ {to}】条记录   共【{total}】条记录',
        onSelectPage:function(pageNumber, pageSize){
            request.page = pageNumber;
            request.pageSize = pageSize;
            load_gjcxGrid(request);
        }
    });
}

//加载datagrid高级查询结果
function load_gjcxGrid(request) {
    ShowProgress();
    $.ajax({
        contentType:'application/json',
        url:getRootPath()+"/advanceQuery/getResult",
        type:"post",
        data:JSON.stringify(request),
        success:function (data) {
            if(data.data == null || data.data.length == 0){
                $("#kd_gjcx_datagrid").datagrid("loadData",{total:0,rows:[]});
            }else{
                $("#kd_gjcx_datagrid").datagrid("loadData",{total:data.total,rows:data.data});
            }
            CloseProgress();
        },
        error:function () {
            CloseProgress();
        }
    });
}
