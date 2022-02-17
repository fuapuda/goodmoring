package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageEmployees;
import com.at410.manageservice.entity.vo.EmployeesQuery;
import com.at410.manageservice.service.ManageEmployeesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工 前端控制器
 * </p>
 *
 * @author wtw
 * @since 2021-12-23
 */
@RestController
@CrossOrigin
@RequestMapping("/manageservice/manage-employees")
//http://localhost:8001/manageservice/manage-employees/findAll
public class ManageEmployeesController {
    @Autowired
    private ManageEmployeesService employeesService;
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<ManageEmployees> list = employeesService.list(null);

        return R.ok().data("items",list);

    }
    @DeleteMapping("{id}")
    public R removeEmployees(@PathVariable String id){
        boolean flag = employeesService.removeById(id);
        if(flag){
            return R.ok();
        }
        return R.error();
    }
    //    分页查询                     当前页   每页记录数
    @GetMapping("pageEmployees/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        try {
//            int i=10/0;
        }catch (Exception e){
//            throw new GuliException(20001,"执行了自定义异常处理");
        }

        Page<ManageEmployees> pageTeacher = new Page<>(current, limit);
        employeesService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<ManageEmployees> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("row",records);
    }
    @PostMapping("pageEmployeesCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) EmployeesQuery teacherQuery){
        Page<ManageEmployees> pageTeacher = new Page<>(current, limit);
        QueryWrapper<ManageEmployees> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        wrapper.orderByDesc("gmt_create");
        employeesService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<ManageEmployees> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);

    }
    @PostMapping("addEmployees")
    public R addTeacher(@RequestBody ManageEmployees eduTeacher){
        boolean save = employeesService.save(eduTeacher);
        if (save){
            return R.ok();
        }else
            return R.error();
    }
    @GetMapping("getEmployees/{id}")
    public R getTeacher(@PathVariable String id){
        ManageEmployees eduTeacher=employeesService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    @PostMapping("updateEmployees")
    public R updateTeacher(@RequestBody ManageEmployees eduTeacher){
        boolean flag = employeesService.updateById(eduTeacher);
        if(flag){

            return R.ok();
        }
        return R.error();
    }
}

