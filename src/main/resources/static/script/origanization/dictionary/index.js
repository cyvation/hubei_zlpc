var minCenterHeight = 300;
var gridHeigth = minCenterHeight;
var parentTabHeight = 0;
var time;
var defualtSelected = "";
var isAdd = true;

$(document).ready(function () {

    //初始化数据字典
    initSJZD();

    //按钮及注册事件
    registEvent();

    //右侧treegrid信息
    initControl();

});

function initSJZD() {
    $('#tree_dic_father_info').tree({
        url: getRootPath() + '/dictionary/getSjzdfl',
        method: 'get',
        lines: true,
        loadMsg: '数据加载中，请稍后...',
        loadFilter: function (result) {
            return result.code == 200 ? result.data : [];

        },
        onLoadError: function (arguments) {
            Alert(arguments.responseText);
        },
        onDblClick: function () {
            if ($('#tree_dic_father_info').tree('getSelected').state == "open") {
                $('#tree_dic_father_info').tree('collapse', $('#tree_dic_father_info').tree('getSelected').target);
            }
            else {
                $('#tree_dic_father_info').tree('expand', $('#tree_dic_father_info').tree('getSelected').target);
            }
        },
        onLoadSuccess: function (node, data) {
            if (data && data.length > 0) {
                var findNode;
                if (defualtSelected && defualtSelected != "") {
                    findNode = $('#tree_dic_father_info').tree('find', defualtSelected);
                }
                else {
                    findNode = $('#tree_dic_father_info').tree('find', data[0].id);
                }
                if (findNode) {
                    //展开根节点到指定节点
                    $('#tree_dic_father_info').tree('expandTo', findNode.target);
                    $('#tree_dic_father_info').tree('select', findNode.target);
                }
            }
        },
        onSelect: function (node) {
            //右侧treegrid信息初始化
            initControl(node.id);
        }
    });
}


//按钮及事件
function registEvent() {
   // 新增子分类
    $('#win_add_dic_son').window({
        width: 420,
        height: 400,
        modal: true,
        maximizable: false,
        minimizable: false,
        closed: true,
        collapsible: false,
        title: '编辑子分类',
        onClose: function () {
            ClearWinSjzd();
        }
    });
    $('#win_add_dic_father').window({
        width: 420,
        height: 450,
        modal: true,
        maximizable: false,
        minimizable: false,
        closed: true,
        collapsible: false,
        title: '主分类',
        onClose: function () {
            ClearWinSjzd();
        }
    });

    //添加窗口确定按钮
    $('#btn_dic_son_edit_confirm').linkbutton({
        onClick: function () {
            btn_dic_son_edit_confirm();
        }
    });

    //添加窗体取消按钮
    $('#btn_dic_son_edit_cancel').linkbutton({
        onClick: function () {
            $('#win_add_dic_son').window('close');
        }
    });
}

//添加窗口确定按钮事件
//新增子分类
function btn_dic_son_edit_confirm() {
    if (!VerifySjzd()) {
        return;
    }
    $('#btn_dic_son_edit_confirm').linkbutton('disable');

    var obj = new Object();

    obj.dm = $('#dm_sjzd').val();
    obj.mc = $('#mc_sjzd').val();
    obj.fdm = $('#fdm_sjzd').val();
    obj.lbbm = $('#sslbdm_sjzd').val(), obj.sfsc = 'N'

        $.ajax({
            url: getRootPath() + '/dictionary/addFldm?isAdd='+isAdd,
            data: obj,
            type: 'post',
            dataType: 'json',
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                ShowError(textStatus + "，" + errorThrown);
            },
            success: function (data) {
                if (data && data.code == 200) {
                    AlertAndDo('保存成功！', function () {
                        $('#btn_dic_son_edit_confirm').linkbutton('enable');
                        $('#win_add_dic_son').window('close');
                        defualtSelected = data.sslbdm;
                        $('#tree_dic_father_info').tree('reload');
                    });
                }
                else {
                    $('#btn_dic_son_edit_confirm').linkbutton('enable');
                    ShowError("保存失败：" + data.errmsg);
                }
            }
        });
}


//初始化数据字典

//右侧treegrid信息初始化
function initControl(sslbdm) {
    var queryData = {lbbm: sslbdm};
    $('#tgd_dic_son_info').treegrid({
        method: 'get',
        url: getRootPath() + '/dictionary/getDataDictionaryByLBBM',
        idField: 'dm',
        rownumbers: true,
        fit: true,
        fitColumns: true,
        animate: true,
        treeField: 'mc',
        loadMsg: '数据加载中，请稍后...',
        queryParams: queryData,
        collapsible: true, //是否显示可折叠按钮
        toolbar: [
//        {
//            iconCls: 'icon-add',
//            text: '新增主分类',
//            handler: function () {
//                $('#win_add_dic_father').window({
//                    title: '新增主分类'
//                });
//                $('#win_add_dic_father').window('open');
//                AddSjzd_father();
//            }
//        },
//        {
//            iconCls: 'icon-edit',
//            text: '修改主分类',
//            handler: function () {
//                $('#win_add_dic_father').window({
//                    title: '修改主分类'
//                });
//                $('#win_add_dic_father').window('open');
//                UpdateSjzd_father();
//            }
//        },
//        {
//            iconCls: 'icon-remove',
//            text: '删除主分类',
//            handler: function () {
//                DelSjzd_father();
//            }
//        },

            {
                iconCls: 'icon-add',
                text: '新增分类',
                handler: function () {
                    $('#win_add_dic_son').window({
                        title: '新增分类'
                    });
                    $('#win_add_dic_son').window('open');
                    AddSjzd_son();
                }
            },
            {
                iconCls: 'icon-add',
                text: '新增子分类',
                handler: function () {
                    $('#win_add_dic_son').window({
                        title: '新增子分类'
                    });
                    $('#win_add_dic_son').window('open');
                    AddSjzd_grandson();
                }
            }, {
                iconCls: 'icon-edit',
                text: '修改',
                handler: function () {
                    $('#win_add_dic_son').window({
                        title: '修改'
                    });
                    $('#win_add_dic_son').window('open');
                    UpdateSjzd_son();
                }
            }, {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                    DelSjzd_son();
                }
            }
            ],
        columns: [[
            {title:'父代码',field:'_parentId',hidden:true},
            {title: '名称', field: 'mc', width: 300},
            {title: '代码', field: 'dm', width: 300},
            {title: '说明', field: 'sm', width: 450},
            {
                title: '是否启用', align: 'center', field: 'sfsc', width: 90, formatter: function (value, row, index) {
                if (value == "N") {
                    var s = '<div><img src="/plugin/jquery-easyui-1.4.3/themes/icons/ok.png"/></div>';
                    return s;
                }
                else {
                    var s = '<div><img src="/plugin/jquery-easyui-1.4.3/themes/icons/no.png"/></div>';
                    return s;
                }
            }
            },
            {
                field: 'action', title: '操作', width: 100, align: 'center', formatter: function (value, row, index) {
                    if(row.sfsc == "N"){
                        return '<a href="#" onclick="updateSjzd(' + row.dm+','+false+ ')">停用</a>';
                    }else {
                        return '<a href="#" onclick="updateSjzd(' + row.dm+','+true+ ')">启用</a> ';
                    }

                }
            }
        ]],
        loadFilter: function (result) {
            return result.code == 200 ? result.data : [];
        }
    });


}
//更新是否启用 var dmNode = $('#tgd_dic_son_info').treegrid('getSelected');
function updateSjzd(param_dm,open) {
   var obj = new Object();
   obj.dm = param_dm;
   open ? obj.sfsc="N":obj.sfsc="Y";

    $.ajax({
        type: 'post',
        url: getRootPath() + '/dictionary/updateFldmStatus',
        data: obj,
        dataType: 'json',
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ShowError(textStatus + "，" + errorThrown);
        },
        success: function (result) {
            if (result && result.code == 200) {
                AlertAndDo('操作成功！', function () {
                    $('#tree_dic_father_info').tree('reload');

                });
            }
            else {
                ShowError("操作失败：" + result.message);
            }
        }
    });

}


//新增分类
function AddSjzd_son() {
    var lbNode = $('#tree_dic_father_info').tree('getSelected');
    var dmNode = $('#tgd_dic_son_info').treegrid('getSelected');
    if (lbNode == null) {
        alert('请选择主分类！');
        return;
    }
    if (lbNode != null) {
        $('#dm_sjzd').textbox('setValue', lbNode.id)
        $('#sslbdm_sjzd').textbox('setValue', lbNode.id);
        $('#sslbmc_sjzd').textbox('setValue', lbNode.text);
    }

    if (dmNode != null) {
        $('#fdm_sjzd').textbox('setValue', '');
    }
    $('#dm_sjzd').textbox('enable', true);
    $('#sslbdm_sjzd').textbox('disable', true);
    $('#sslbmc_sjzd').textbox('disable', true);
    $('#fdm_sjzd').textbox('disable', true);
    isAdd = true;
}


//新增子分类
function AddSjzd_grandson() {
    var lbNode = $('#tree_dic_father_info').tree('getSelected');
    var dmNode = $('#tgd_dic_son_info').treegrid('getSelected');
    if (lbNode != null) {
        $('#sslbdm_sjzd').textbox('setValue', lbNode.id);
        $('#sslbmc_sjzd').textbox('setValue', lbNode.text);
    }
    if (dmNode == null) {
        AlertAndDo('必须选择一个分类！', function () {
            $('#win_add_dic_son').window('close');
        });
        return;
    }
    if (dmNode != null) {
        $('#fdm_sjzd').textbox('setValue', dmNode.dm);
    }
    $('#dm_sjzd').textbox('enable', true);
    $('#sslbdm_sjzd').textbox('disable', true);
    $('#sslbmc_sjzd').textbox('disable', true);
    $('#fdm_sjzd').textbox('disable', true);
    isAdd = true;
}


//删除子分类
function DelSjzd_son() {
    var dmNode = $('#tgd_dic_son_info').treegrid('getSelected');
    defualtSelected = $('#tree_dic_father_info').tree('getSelected');
    if (dmNode && dmNode.id != "") {
        var obj = new Object();
        obj.dm = dmNode.dm;
        obj.lbbm = dmNode.lbbm;
        Confirm("删除确认", "确定删除该数据项？", function (r) {
            if (r) {
                $.ajax({
                    type: 'post',
                    url: getRootPath() + '/dictionary/deleteFldm',
                    data: obj,
                    dataType: 'json',
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        ShowError(textStatus + "，" + errorThrown);
                    },
                    success: function (result) {
                        if (result && result.code == 200) {
                            defualtSelectedRow = "";
                            AlertAndDo('删除成功！', function () {
                                $('#tree_dic_father_info').tree('reload');
                                $('#tgd_dic_son_info').treegrid('reload');
                                $('#tgd_dic_son_info').treegrid('remove', dmNode.target);

                            });
                        }
                        else {
                            ShowError("删除失败：" + result.message);
                        }
                    }
                });
            }
        });
    }
    else {
        ShowError("请选中删除的指标项！");
    }
}


//修改子分类
function UpdateSjzd_son() {
    var lbNode = $('#tree_dic_father_info').tree('getSelected');
    var dmNode = $('#tgd_dic_son_info').treegrid('getSelected');
    if (lbNode != null) {
        $('#sslbdm_sjzd').textbox('setValue', lbNode.id);
        $('#sslbmc_sjzd').textbox('setValue', lbNode.text);
    }
    if (dmNode == null) {
        AlertAndDo('必须选择一个分类！', function () {
            $('#win_add_dic_son').window('close');
        });
        return;
    }
    if (dmNode != null) {
        $('#dm_sjzd').textbox('setValue', dmNode.dm);
        $('#mc_sjzd').textbox('setValue', dmNode.mc);
        $('#fdm_sjzd').textbox('setValue', dmNode._parentId);
        $('#sm_sjzd').textbox('setValue', dmNode.sm);
        $('#xh_sjzd').numberbox('setValue', dmNode.xh);
    }
    $('#dm_sjzd').textbox('disable', true);
    $('#sslbdm_sjzd').textbox('enable', true);
    $('#sslbmc_sjzd').textbox('enable', true);
    $('#fdm_sjzd').textbox('enable', true);
    isAdd = false;

}


//分类验证
function VerifySjzd() {
    if ($('#dm_sjzd').textbox('getValue') == "") {
        ShowWarningAndDo("代码不能为空！", function () {
            $('#dm_sjzd').next('span').find('input').focus();
        });
        return false;
    }
    if ($('#dm_sjzd').textbox('getValue').length > 20) {
        ShowWarningAndDo("代码最多为20字，当前长度为" + $('#dm_sjzd').textbox('getValue').length + "字！", function () {
            $('#dm_sjzd').next('span').find('input').focus();
        });
        return false;
    }
    if ($('#mc_sjzd').textbox('getValue') == "") {
        ShowWarningAndDo("名称不能为空！", function () {
            $('#mc_sjzd').next('span').find('input').focus();
        });
        return false;
    }
    if ($('#fdm_sjzd').textbox('getValue').length > 20) {
        ShowWarningAndDo("父代码最多为20字，当前长度为" + $('#fdm_sjzd').textbox('getValue').length + "字！", function () {
            $('#dm_sjzd').next('span').find('input').focus();
        });
        return false;
    }
    if ($('#sslbdm_sjzd').textbox('getValue').length > 20) {
        ShowWarningAndDo("所属类别代码最多为20字，当前长度为" + $('#sslbdm_sjzd').textbox('getValue').length + "字！", function () {
            $('#dm_sjzd').next('span').find('input').focus();
        });
        return false;
    }
    if ($('#xh_sjzd').numberbox('getValue') > 1000) {
        ShowWarningAndDo("序号最大值为1000！", function () {
            $('#xh_sjzd').next('span').find('input').focus();
        });
        return false;
    }
    return true;
}

//清除窗口数据
function ClearWinSjzd() {
    selectedFlbh = "";
    fflbh = "";
    $('#dm_sjzd').textbox('clear');
    $('#mc_sjzd').textbox('clear');
    $('#fdm_sjzd').textbox('clear');
    $('#sslbdm_sjzd').textbox('clear');
    $('#sslbmc_sjzd').textbox('clear');
    $('#sm_sjzd').textbox('clear');
    $('#xh_sjzd').numberbox('setValue', '999');

    $('#dm_father').textbox('clear');
    $('#mc_father').textbox('clear');
    $('#xh_father').numberbox('setValue', '999');
    $('#sm_father').textbox('clear');
    $('#dm_son').textbox('clear');
    $('#mc_son').textbox('clear');
    $('#sm_son').textbox('clear');
    $('#xh_son').numberbox('setValue', '999');
}




