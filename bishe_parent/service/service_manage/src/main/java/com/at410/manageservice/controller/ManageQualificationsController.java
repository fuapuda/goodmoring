package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageEmployees;
import com.at410.manageservice.entity.ManageQualifications;
import com.at410.manageservice.entity.vo.QuaQuery;
import com.at410.manageservice.service.ManageQualificationsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wtw
 * @since 2021-12-23
 */
@RestController
@CrossOrigin
@RequestMapping("/manageservice/manage-qualifications")
public class ManageQualificationsController {
    @Autowired
    private ManageQualificationsService qualificationsService;
    @PostMapping("addQualifications")
    public R addQualifications(@RequestBody ManageQualifications manageQualifications){
        String id=qualificationsService.saveCourseInfo(manageQualifications);
        return R.ok().data("quaId",id);
    }
    @GetMapping("getQuaInfo/{quaId}")
    public R getQuaInfo(@PathVariable String quaId) {
        ManageQualifications qualifications = qualificationsService.getQuaInfo(quaId);
        return R.ok().data("quaInfo",qualifications);
    }
    @PostMapping("updateQuaInfo")
    public R updateCourseInfo(@RequestBody ManageQualifications manageQualifications) {
        qualificationsService.updateQuaInfo(manageQualifications);
        return R.ok();
    }
    @PostMapping("publishQua/{id}")
    public R publishQua(@PathVariable String id){
        ManageQualifications manageQualifications=new ManageQualifications();
        manageQualifications.setId(id);
        manageQualifications.setStatus("Normal");
        qualificationsService.updateById(manageQualifications);
        return R.ok();
    }
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<ManageQualifications> list = qualificationsService.list(null);

        return R.ok().data("items",list);

    }
    @PostMapping("pageQuaCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) QuaQuery quaQuery){
        Page<ManageQualifications> pageTeacher = new Page<>(current, limit);
        QueryWrapper<ManageQualifications> wrapper = new QueryWrapper<>();
        String name = quaQuery.getName();
        String status =quaQuery.getStatus();
        String begin =quaQuery.getBegin();
        String end =quaQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        wrapper.orderByDesc("gmt_create");
        qualificationsService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<ManageQualifications> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);

    }
    @DeleteMapping("delete/{id}")
    public R removeEmployees(@PathVariable String id){
        boolean flag = qualificationsService.removeById(id);
        if(flag){
            return R.ok();
        }
        return R.error();
    }
    @PostMapping("backQua/{id}")
    public R backQua(@PathVariable String id){
        ManageQualifications manageQualifications=new ManageQualifications();
        manageQualifications.setId(id);
        manageQualifications.setStatus("Draft");
        qualificationsService.updateById(manageQualifications);
        return R.ok();
    }
}

