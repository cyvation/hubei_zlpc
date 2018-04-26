package com.start.boot.service;

import com.start.boot.common.Param_Pager;
import com.start.boot.domain.*;

import java.util.List;
import java.util.Map;

public interface OriganizationService {

    /**
     * 获取单位树
     * @param dwbm
     * @return
     */
    List<Map> getDwbm(String dwbm)throws Exception;

    /**
     * 获取组织结构树(单位部门角色)
     * @param dwbm
     * @return
     * @throws Exception
     */
    List<Map> getDwBmJsInfoByDwBm(String dwbm)throws  Exception;

    /**
     * 添加/修改部门
     * @param param
     * @return
     */
    boolean addBmInfo(Bmbm param)throws  Exception;

    /**
     * 获取部门信息
     * @param dwbm
     * @param bmbm
     * @return
     * @throws Exception
     */
    List<Map> getBmInfo(String dwbm, String bmbm)throws  Exception;

    /**
     * 删除部门信息
     * @param dwbm
     * @param bmbm
     * @return
     * @throws Exception
     */
     boolean deleteBmInfo(String dwbm, String bmbm)throws  Exception;

    /**
     * 添加/修改角色
     * @param jsbm
     * @return
     * @throws Exception
     */
     boolean addJsInfo(Jsbm jsbm)throws  Exception;

    /**
     * 获取人员信息
     * @param param_rybm
     * @return
     * @throws Exception
     */
     Param_rybm getRyInfo(Param_rybm param_rybm)throws Exception;

    /**
     * 获取角色信息
     * @param jsbm
     * @return
     * @throws Exception
     */
    List<Map> getJsxh(Jsbm jsbm)throws Exception;

    /**
     * 删除角色信息
     * @param jsbm
     * @return
     * @throws Exception
     */
     boolean deleteJsInfo(Jsbm jsbm)throws Exception;

    /**
     * 添加角色功能权限
     * @param jsgnfp
     * @return
     * @throws Exception
     */
     boolean addJsgnfp(Jsgnfp jsgnfp)throws Exception;

    /**
     * 获取角色功能权限列表
     * @param bmbm
     * @param jsbm
     * @param dwbm
     * @return
     * @throws Exception
     */
    List<Map> getJsGnqx(String bmbm, String jsbm, String dwbm)throws Exception;

    /**
     * 分配功能权限
     * @param dwbm
     * @param gnbmj
     * @return
     * @throws Exception
     */
     boolean addDwGnQx(String dwbm, String gnbmj)throws Exception;

    /**
     * 未分配角色人员数据
     * @param param_rybm
     * @return
     * @throws Exception
     */
    Param_rybm getWfpRyInfo(Param_rybm param_rybm)throws Exception;

    /**
     * 加载单位功能权限列表
     * @param dwbm
     * @return
     * @throws Exception
     */
    List<Map> getAllGnqx(String uerDwbm,String dwbm,String djdwbm)throws Exception;

    /**
     * 添加人员
     * @param param
     * @return
     * @throws Exception
     */
    boolean addRYJSFP(Param_rybm param)throws Exception;

    /**
     * 人员调岗
     * @param param_transJob
     * @return
     * @throws Exception
     */
     boolean transJob(Param_TransJob param_transJob)throws Exception;

    /**
     * 获取部门下角色列表
     * @param bmbm
     * @param dwbm
     * @return
     * @throws Exception
     */
    List<Map> getJSInfoByDwBm(String bmbm, String dwbm)throws Exception;

    /**
     * 获取部门编码
     * @param dwbm
     * @return
     * @throws Exception
     */
    List<Map> getBMBM(String dwbm)throws Exception;


    /**
     * 解除人员岗位
     * @param param_transJob
     * @return
     * @throws Exception
     */
    boolean removeJob(Param_TransJob param_transJob) throws Exception;

    /**
     * 获取功能参数
     * @param jsgnfp
     * @return
     */
    List<Map> getGncs(Jsgnfp jsgnfp) throws Exception;


    /**
     * 修改功能参数
     * @param jsgnfp
     * @return
     */
    int updateGncs(Jsgnfp jsgnfp) throws Exception;
}
