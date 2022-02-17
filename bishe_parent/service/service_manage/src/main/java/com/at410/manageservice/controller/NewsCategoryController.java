package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageBid;
import com.at410.manageservice.entity.NewsCategory;
import com.at410.manageservice.service.NewsCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2022-01-03
 */
@RestController
@RequestMapping("/manageservice/news-category")
@CrossOrigin
public class NewsCategoryController {
    @Autowired
    NewsCategoryService categoryService;
    @GetMapping("getList")
    public R getList(){
        List<NewsCategory> list = categoryService.list(null);
        return R.ok().data("list",list);
    }
}

