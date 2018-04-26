

$(document).ready(function () {
    console.log(111111);
    grid_handle_table();
});
function grid_handle_table() {
    var obj = new Object();
    obj.dwbm =370000;
        $('#grid_handle_table_yscx').datagrid({
            method:'get',
            fitColumns: true,
            striped: true,
            nowrap: false,
            rownumbers: true,
            singleSelect: true,
            // rownumWidth:100,
           // idField: 'pcxbm',
            //loadMsg: '数据加载中，请稍后...',
            url:getRootPath() + '/queryTable//getJcgTableData?dwbm=370000',
            //data:obj,
            pagination: false,
            columns: [[
                {
                    field: 'name',
                    title: '<span  style=\'font-size:16px;\'>单<br>位<br>名<br>称</span>',
                    rowspan: 4,
                    align: 'center',
                    halign: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>评<br>查<br>率</span>',
                    rowspan: 3,
                    align: 'center',
                    halign: 'center'
                },
                {title: '<span  style=\'font-size:14px\'></span>', colspan: 10},
                {
                    title: '<span  style=\'font-size:14px\'>评查<br>发现<br>问题<br>数</span>',
                    rowspan: 3,
                    align: 'center',
                    halign: 'center'
                },
                {title: '<span  style=\'font-size:14px\'></span>', colspan: 12},


            ], [
                {
                    title: '<span  style=\'font-size:14px\'>审<br>结<br>数</span>',
                    rowspan: 2,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>评<br>查<br>数</span>',
                    rowspan: 2,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>评查方式</span>',
                    colspan: 4,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>结果等次</span>',
                    colspan: 4,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>事实认定</span>',
                    colspan: 2,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>证据采信</span>',
                    colspan: 3,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>法律适用</span>',
                    colspan: 3,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>办案程序</span>',
                    colspan: 2,
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>法律文书</span>',
                    align: 'center'
                },
                {
                    title: '<span  style=\'font-size:14px\'>办案效果</span>',
                    align: 'center'
                }
            ]
                , [
                    {
                        title: '<span  style=\'font-size:14px;\'>重点<br>评查</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>常规<br>抽查</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>专项<br>评查</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>交叉<br>评查</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>优<br>质</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>合<br>格</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>不合<br>格</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                        align: 'center'
                    }
                    ,
                    {
                        title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                        align: 'center'
                    }
                    ,
                    {
                        title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                        align: 'center'
                    }
                    ,
                    {
                        title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                        align: 'center'
                    }
                    ,
                    {
                        title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>分<br>歧</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                        align: 'center'
                    }
                    ,
                    {
                        title: '<span  style=\'font-size:14px\'>错<br>误</span>',
                        align: 'center'
                    },
                    {
                        title: '<span  style=\'font-size:14px\'>瑕<br>疵</span>',
                        align: 'center'
                    }
                    ,
                    {
                        title: '<span  style=\'font-size:14px\'>一<br>般</span>',
                        align: 'center'
                    }
                ], [
                    {
                        field: 'pcl',
                        title: '<span  style=\'font-size:14px\'>%</span>',
                        align: 'center'
                    }, {
                        field: 'sjCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'pcCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'zdpcCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'cgCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'zxCount',
                        title: '<span  style=\'font-size:14px\'>次/件</span>',
                        align: 'center'
                    }, {
                        field: 'jxCount',
                        title: '<span  style=\'font-size:14px\'>次/件</span>',
                        align: 'center'
                    }, {
                        field: 'yzCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'hgCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'xcCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'bhgCount',
                        title: '<span  style=\'font-size:14px\'>件</span>',
                        align: 'center'
                    }, {
                        field: 'pcwtzsCount',
                        title: '<span  style=\'font-size:14px\'>件/处</span>',
                        align: 'center'
                    }, {
                        field: 'ssrdXcCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'ssrdCuCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'zjcxXcCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'zjcxCuCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'zjcxfqCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'flsyXcCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'flsyCuCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'flsyfqCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'bacxXcCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'bacxCuCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'flwsXcCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }, {
                        field: 'baxgYbCount',
                        title: '<span  style=\'font-size:14px\'>处</span>',
                        align: 'center'
                    }
                ]],
            loadFilter:function (result) {
                return result.code == 200 ? JSON.parse(result.data) : [];
            }
        });

}


