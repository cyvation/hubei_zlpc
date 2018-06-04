package com.start.boot.web;

import com.start.boot.common.Param_Pager;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.*;

import com.start.boot.service.OriganizationService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.GetDw;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api("组织机构管理api")
@RestController
@RequestMapping("/organization")
@SuppressWarnings("all")
public class OriganizationController extends ArchivesSystemBaseController {

    @Autowired
    private OriganizationService origanizationService;


    @ApiOperation("获取单位树(获取本单位及下级单位)")
    @GetMapping("/getDwbmTree")
    public String getGetDwbmTree() {
        String result = "";

        try {
            String dwbm = getCurrentDwbm();
            List<Map> list = origanizationService.getDwbm(dwbm);

            //遍历集合，找到本单位的父单位
            String topBM = GetDw.topBM(list,dwbm);

            result = success(EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", topBM), "获取单位树成功");
        } catch (Exception e) {
            super.errMsg("获取单位树失败", "单位：", e);
            result = failure(e.getMessage(), "获取单位树失败");
        }

        return result;
    }

    /**
     * 湖北线下评查跨院
     * @return
     */
    @ApiOperation("获取单位树(获取本单位平级及下级单位)")
    @GetMapping("/getPjDwbmTree")
    public String getGePjtDwbmTree() {
        String result = "";

        try {
            List<Map> list=new ArrayList<>();
            String dwbm = getCurrentDwbm();
           if( SystemConfiguration.djdwbm.equals(dwbm)){
                list = origanizationService.getDwbm(dwbm);
            }else{
                list = origanizationService.getPjDwbm(dwbm);
            }
            //遍历集合，找到本单位的父单位
            String topBM = GetDw.topBM(list,dwbm);

            result = success(EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", topBM), "获取单位树成功");
        } catch (Exception e) {
            super.errMsg("获取单位树失败", "单位：", e);
            result = failure(e.getMessage(), "获取单位树失败");
        }

        return result;
    }


    @ApiOperation("获取组织结构树(单位部门角色)")
    @GetMapping("/getDwBmJsByDwbm")
    public String getDwBmJsByDwbm(String dwbm) {
        String result = "";

        try {
            List<Map> list = origanizationService.getDwBmJsInfoByDwBm(dwbm);

            //遍历集合，找到本单位的父单位
            String topBM = GetDw.topBM(list,dwbm);

            String tree = EasyUIHelper.buildTreeListDataSource(list, "BM", "FBM", "MC", "ICON", topBM);
            result = success(tree, "获取组织结构结构树成功");
        } catch (Exception e) {
            super.errMsg("获取组织结构树(单位部门角色)", "单位：" + dwbm, e);
            result = failure(e.getMessage(), "获取组织结构结构树失败");
        }

        return result;
    }


    @ApiOperation("添加部门/编辑部门")
    @PostMapping("/addBmInfo")
    public String addBmInfo(@RequestBody Bmbm bmbm) {
        String result = "";

        try {
            boolean isSuccess = origanizationService.addBmInfo(bmbm);
            result = success(isSuccess, "添加部门/编辑部门成功");
        } catch (Exception e) {
            super.errMsg("添加/更新部门失败", "单位：" + bmbm, e);
            result = failure(e.getMessage(), "添加/更新部门失败");
        }

        return result;
    }


    @ApiOperation("获取部门信息")
    @GetMapping("/getBmInfo")
    public String getBmInfo(String dwbm,String bmbm) {
        String result = "";

        try {
            List<Map> bmInfo = origanizationService.getBmInfo(dwbm, bmbm);
            result = success(bmInfo.get(0), "获取部门信息成功");
        } catch (Exception e) {
            super.errMsg("获取部门信息失败", "单位：" + dwbm, e);
            result = failure(e.getMessage(), "获取部门信息失败");
        }

        return result;
    }

    //删除部门
    @ApiOperation("删除部门")
    @RequestMapping("/deleteBmInfo")
    public String deleteBmInfo(String bmbm,String dwbm) {
        String result = "";

        try {
            boolean isSuccess = origanizationService.deleteBmInfo(dwbm, bmbm);
            result = success(isSuccess, "删除部门信息成功");
        } catch (Exception e) {
            super.errMsg("删除部门失败", "单位：" + dwbm, e);
            result = failure(e.getMessage(), "删除部门失败");
        }
        return result;
    }

    @ApiOperation("添加/修改角色")
    @PostMapping("/addJsInfo")
    public String addJsInfo(@RequestBody Jsbm jsbm) {
        String result = "";

        try {
            boolean isSuccess = origanizationService.addJsInfo(jsbm);
            result = success(isSuccess, "添加/修改角色成功");
        } catch (Exception e) {
            super.errMsg("添加/修改角色失败", "角色：" + jsbm, e);
            result = failure(e.getMessage(), "添加/修改角色失败");
        }

        return result;
    }


    @ApiOperation("获取人员信息")
    @RequestMapping("/getRyInfo")
    public String getRyInfo(Param_rybm param_rybm) {
        String result = "";

        //将json对象转为实体类
        try {
            Param_rybm ryInfo = origanizationService.getRyInfo(param_rybm);
            result = success(EasyUIHelper.buildDataGridDataSource(ryInfo.getList(),ryInfo.getCount()),"获取人员信息成功");
        } catch (Exception e) {
            super.errMsg("获取人员信息失败", "人员：" + param_rybm, e);
            result = failure(e.getMessage(), "获取人员信息失败");
        }
        return result;
    }


    @ApiOperation("获取角色信息")
    @RequestMapping("/getJsxh")
    public String getJsxh(Jsbm jsbm) {
        String result = "";
        try {
            List<Map> jsxh = origanizationService.getJsxh(jsbm);
            result = success(jsxh,"获取角色信息成功");
        } catch (Exception e) {
            super.errMsg("获取角色信息成功", "角色：" + jsbm, e);
            result = failure(e.getMessage(), "获取角色信息成功");
        }
       return result;

    }


    @ApiOperation("删除角色")
    @RequestMapping("/deleteJsInfo")
    public String deleteJsInfo(Jsbm jsbm) {
        String result = "";
        try {
            boolean isSuccess = origanizationService.deleteJsInfo(jsbm);
            result = success(isSuccess,"删除角色成功");
        } catch (Exception e) {
            super.errMsg("删除角色失败", "角色：" + jsbm, e);
            result = failure(e.getMessage(), "删除角色失败");;
        }
        return result;

    }


    @ApiOperation("添加角色功能权限")
    @PostMapping("/addJsgnfp")
    public String addJsgnfp(@RequestBody Jsgnfp jsgnfp) {
        String result = "";
        //解析json对象，转为实体类
        if (jsgnfp != null || StringUtils.isNoneEmpty(jsgnfp.getGnbm()))
            jsgnfp.setGnbm(jsgnfp.getGnbm().substring(1));

        try {
            boolean isSuccess = origanizationService.addJsgnfp(jsgnfp);
            result = success(isSuccess,"添加角色功能权限成功");
        } catch (Exception e) {
            super.errMsg("添加角色功能权限失败", "角色：" + jsgnfp, e);
            result = failure(e.getMessage(), "添加角色功能权限失败");
        }
        return  result;
    }


    @ApiOperation("获取角色功能权限列表")
    @GetMapping("/getJsGnqx")
    public String getJsGnqx(String bmbm, String jsbm, String dwbm) {
        String result = "";

        try {
            List<Map> jsGnqx = origanizationService.getJsGnqx(bmbm, jsbm, dwbm);
            result = success(EasyUIHelper.buildDataGridDataSource(jsGnqx,jsGnqx.size()),"获取角色功能权限列表成功");
        } catch (Exception e) {
            super.errMsg("获取角色功能权限列表失败", "角色：" + jsbm, e);
            result = failure(e.getMessage(), "获取角色功能权限列表失败");
        }
      return result;
    }


    @ApiOperation("分配功能权限")
    @PostMapping("/addDwGnQx")
    public String addDwGnQx(String dwbm,String gnbmj) {
        String result = "";

        try {
            boolean isSuccess = origanizationService.addDwGnQx(dwbm,gnbmj);
            result = success(isSuccess,"分配功能权限成功");
        } catch (Exception e) {
            super.errMsg("分配功能权限失败", "角色：" + gnbmj, e);
            result = failure(e.getMessage(), "分配功能权限失败");
        }
        return result;
    }


    @ApiOperation("未分配角色人员数据")
    @PostMapping("/getWfpRyInfo")
    public  String getWfpRyInfo(Param_rybm param_rybm) {
        String result= "";
        //获取当前页大小与页面

        try {
            Param_Pager wfpRyInfo = origanizationService.getWfpRyInfo(param_rybm);
            result = success(EasyUIHelper.buildDataGridDataSource(wfpRyInfo.getList(),wfpRyInfo.getCount()),"添加角色功能权限成功");
        } catch (Exception e) {
            super.errMsg("获取未分配角色人员数据失败", "角色：" + param_rybm, e);
            result = failure(e.getMessage(), "获取未分配角色人员数据失败");
        }
     return result;

    }


    @ApiOperation("加载单位功能权限列表")
    @GetMapping("/getAllGnqx")
    public String getAllGnqx(String dwbm) {
        String result = "";

        String djdwbm = SystemConfiguration.djdwbm;
        String uerDwbm = (String) getSession().getAttribute("dwbm");
        if (StringUtils.isEmpty(uerDwbm) || StringUtils.isEmpty(dwbm)) {
            super.errMsg("加载单位功能权限列表", null, new Exception("加载单位功能权限列表"));
            return null;
        }
        try {
            List<Map> allGnqx = origanizationService.getAllGnqx(uerDwbm, dwbm, djdwbm);
            result = success(EasyUIHelper.buildDataGridDataSource(allGnqx, allGnqx.size()), "加载单位功能权限列表成功");
        } catch (Exception e) {
            super.errMsg("加载单位功能权限列表失败", "单位：" + dwbm, e);
            result = failure(e.getMessage(), "加载单位功能权限列表失败");
        }
        return result;
    }


    @ApiOperation("添加人员")
    @PostMapping("/addRYJSFP")
    @Transactional
    public String addRYJSFP(@RequestBody List<Param_rybm> list) {
        String result = "";

        try{
            boolean ISSCCESS = false;

            //处理接受的数据：
            for (Param_rybm param : list) {
                boolean isSccess = origanizationService.addRYJSFP(param);
                ISSCCESS= isSccess;
            }
            result = success(ISSCCESS,"添加人员成功");

        }catch (Exception e){
           super.errMsg("添加人员失败", "角色：" , e);
           result = failure(e.getMessage(),"添加人员失败");
        }
        return result;
    }

    @ApiOperation("人员调岗")
    @PostMapping("/transJob")
    //todo
    public String transJob(@RequestBody Param_TransJob param_transJob) {
        String result = "";
        //获取人员信息，并解析为实体类
        try {
            boolean isSuccess = origanizationService.transJob(param_transJob);
            result = success(isSuccess,"人员调岗成功");
        } catch (Exception e) {
            super.errMsg("人员调岗失败", "param_transJob：" +param_transJob, e);
            result = failure(e.getMessage(),"人员调岗失败");
        }
        return result;
    }

    @ApiOperation("获取部门下角色列表")
    @GetMapping("/getJSInfoByDwBm")
    //todo
    public String getJSInfoByDwBm(String bmbm,String dwbm) {
        String result = "";

        //要获取的数据
        try {
            List<Map> jsInfoByDwBm = origanizationService.getJSInfoByDwBm(bmbm, dwbm);
            result = success(EasyUIHelper.buildComboBoxDataSource(jsInfoByDwBm, "BM", "MC" ),"获取人员调岗角色列表成功");
        } catch (Exception e) {
            super.errMsg("获取人员调岗角色列表失败", "param_transJob：" +dwbm, e);
            result = failure(e.getMessage(),"获取人员调岗角色列表失败");
        }

        return result;
    }


    @ApiOperation("解除人员岗位")
    @PostMapping("/removeJob")
    public  String removeJob(@RequestBody Param_TransJob param_transJob) {
       String result = "";

        //要获取的数据
        try {
            boolean isSccess = origanizationService.removeJob(param_transJob);
            result = success(isSccess,"解除人员岗位成功");
        } catch (Exception e) {
            super.errMsg("解除人员岗位失败", "param_transJob：" +param_transJob, e);
            result = failure(e.getMessage(),"解除人员岗位失败");
        }
       return result;
    }

    @ApiOperation("获取部门编码")
    @GetMapping("/getBmList")
    public String getBmList(String dwbm) {
        String result = "";
        try {
            List<Map> bmbm = origanizationService.getBMBM(dwbm);
            result = success(EasyUIHelper.buildComboBoxDataSource(bmbm,"BM", "MC" ),"获取部门编码成功");
        } catch (Exception e) {
            super.errMsg("获取部门编码失败", "dwbm：" +dwbm, e);
            result = failure(e.getMessage(),"获取部门编码失败");
        }

        return result;
    }


    @ApiOperation("获取功能参数")
    @PostMapping("/getGncs")
    public String getGncs(@RequestBody Jsgnfp jsgnfp) {
        String result = "";
        try {
            List<Map> list = origanizationService.getGncs(jsgnfp);
           result =  success(list,"获取功能参数成功");
        } catch (Exception e) {
            super.errMsg("获取功能参数失败", "jsgnfp：" +jsgnfp, e);
            result = failure(e.getMessage(),"获取功能参数失败");
        }

        return  result;
    }

    @ApiOperation("修改功能参数")
    @PostMapping("/updateGncs")
    public String updateGncs(@RequestBody Jsgnfp jsgnfp) {
        String result = "";
        try {
            int line = origanizationService.updateGncs(jsgnfp);
            if (line !=-1) {
             result =  success(line ,"修改功能参数成功");
            }
        } catch (Exception e) {
            super.errMsg("修改功能参数失败", "jsgnfp：" +jsgnfp, e);
            result = failure(e.getMessage(),"修改功能参数失败");
        }

        return  result;
    }



}
