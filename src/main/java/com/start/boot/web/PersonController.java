package com.start.boot.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.start.boot.common.MessageResult;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.constant.FavoriteType;
import com.start.boot.domain.Person;
import com.start.boot.query.PersonQuery;
import com.start.boot.service.PersonService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.utils.ExportExcelUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 个人controller
 * Created by caomin on 2017/11/10.
 */
@Controller
@RequestMapping("/person")
public class PersonController extends ArchivesSystemBaseController {

    @Autowired
    PersonService personService;
    @Autowired
    SystemCoreConfigService systemConfigService;

    @ResponseBody
    @ApiOperation("下载文件")
    @RequestMapping("/downLoadFile")
    public HttpServletResponse downLoadFile(String id, HttpServletResponse response) {
        Person person = personService.getPersonById(id);
        String path = person.getGyzy();
        try{
            File file = new File(path);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition","attachment;fileName="+person.getZlmc());
            try {
                FileInputStream inputStream = new FileInputStream(file);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] data = new byte[512];
                int count = -1;
                while ((count = inputStream.read(data, 0, 512)) != -1) {
                    arrayOutputStream.write(data, 0, count);
                }
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(arrayOutputStream.toByteArray());
                arrayOutputStream.close();
                arrayOutputStream.flush();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            response.setStatus(404);
            e.printStackTrace();
        }
        return response;
    }




    @ResponseBody
    @PostMapping("/savePerson")
    @ApiOperation("收藏文件")
    public String saveFile(HttpServletRequest request,MultipartFile file){

        String wzbsPath = SystemConfiguration.wzbsPath;
        String filePath = SystemConfiguration.resourcefilepath;
        String originalFilename = file.getOriginalFilename();
        String realSavePath = filePath;
        String dwbm = getCurrentDwbm();
        String thisYear = String.valueOf(LocalDate.now().getYear());
        realSavePath += dwbm + "/" + thisYear + "/" + UUID.randomUUID();
        String path = wzbsPath + realSavePath;
        String currentDwbm = getCurrentDwbm();
        String currentGh = getCurrentGh();
        String zlmc = request.getParameter("zlmc");
        if (file!=null){
            File file1 = new File(path + "/" + originalFilename);
            try {
                savePerson(path, currentDwbm, currentGh, file, file1, zlmc, realSavePath);
            } catch (IOException e) {
                e.printStackTrace();
                return failure("400","添加失败");
            }
        }
        return success("200","添加成功");
    }

    @ResponseBody
    @PostMapping("/updatePerson")
    @ApiOperation("根据id，更新我的收藏")
    public String updatePerson(@RequestBody Person person){
        try {
            int i = personService.updatePerson(person);
            if (i!=0){
                return success("200","修改成功");
            }else {
                return success("400","没有找到相应的记录");
            }

        }catch (Exception e){
            e.printStackTrace();
            return  failure("没有数据","修改失败");
        }



    }
    private void savePerson(String path, String dwbm, String gh, MultipartFile file, File file1, String zlmc, String realSavePath) throws IOException {
        if (!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();
        }
        Person person=new Person();
        //保存文件
        file.transferTo(file1);
        //设置属性
        person.setDwbm(dwbm);
        person.setGh(gh);
        person.setZlmc(zlmc);
        person.setGyzy(realSavePath + "/" + file1.getName());
        person.setZllx(FavoriteType.FILE);
        person.setSfsc("N");
        personService.save(person);
    }

    @GetMapping("/getPersonList")
    @ResponseBody
    @ApiOperation("根据资料类型，资料名称模糊查询我的收藏")
    public String getPersonList(PersonQuery query){
        Page<Object> objects = PageHelper.startPage(query.getPage(), query.getPageSize());
        query.setDwbm(getCurrentDwbm());
        query.setGh(getCurrentGh());
        List<Person> personListByUserId = personService.getPersonListByUserId(query);

        if (CollectionUtils.isEmpty(personListByUserId)){
            return  failure("没有数据","没有查询到数据");
        }
        ArrayList<Map> result = new ArrayList<>();
        personListByUserId.stream().forEach(data->{
            try {
                Map describe = org.apache.commons.beanutils.BeanUtils.describe(data);
                describe.put("PCSLBM",data.getGxbm());
                describe.put("BMSAH",data.getGyzy());
                result.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return EasyUIHelper.buildDataGridDataSource(result, (int) objects.getTotal());
    }

    @GetMapping("/delPerson")
    @ApiOperation("根据收藏id删除我的收藏")
    @ResponseBody
    public String getPersonList(String  id){
        boolean b = personService.delPerson(id);
        if (b){
            return success("删除成功","删除成功");
        }else {
            return failure("删除失败","删除失败");
        }
    }
    @ApiOperation("收藏案件")
    @ResponseBody
    @PostMapping("/savePersonAj")
    public MessageResult savePersonByAj(@RequestBody Person person){
        person.setZllx(FavoriteType.PCAJ);
        person.setDwbm(getCurrentDwbm());
        person.setGh(getCurrentGh());
        person.setSfsc("N");
        personService.save(person);
        return new MessageResult("收藏成功",200);
    }

    @ApiOperation("获取收藏案件")
    @ResponseBody
    @PostMapping("/getScaj")
    public MessageResult getScaj(Person person){
        MessageResult messageResult;

        person.setDwbm(getCurrentDwbm());
        person.setGh(getCurrentGh());
        try {
            Person people = personService.getPerson(person);
            if(people!=null){
                messageResult = new MessageResult("案件已经收藏",200);
            }else {
                messageResult = new MessageResult("案件没有收藏",500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("案件没有收藏",500);
        }
        return messageResult;
    }
}
