package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.Department;
import com.at410.manageservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wtw
 * @since 2021-12-29
 */
@CrossOrigin
@RestController
@RequestMapping("/manageservice/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<Department> list = departmentService.list(null);

        return R.ok().data("dpm",list);

    }
}

