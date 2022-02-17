package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.News;
import com.at410.manageservice.entity.vo.ItemInfoVo;
import com.at410.manageservice.entity.vo.ItemQuery;
import com.at410.manageservice.entity.vo.NewsQuery;
import com.at410.manageservice.service.ManageItemService;
import com.at410.manageservice.service.NewsService;
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
 * @since 2022-01-03
 */
@RestController
@RequestMapping("/manageservice/news")
@CrossOrigin
public class NewsController {
    @Autowired
    private NewsService newsService;
    @PostMapping("addNews")
    public R addNews(@RequestBody News news){
        String id=newsService.saveNewsInfo(news);
        return R.ok().data("newsId",id);
    }
    @GetMapping("getNewsInfo/{newsId}")
    public R getNewsInfo(@PathVariable String newsId) {
        News news = newsService.getNewsInfo(newsId);
        return R.ok().data("newsInfo",news);
    }
    @PostMapping("updateNewsInfo")
    public R updateNewsInfo(@RequestBody News news) {
        int i = newsService.updateItemInfo(news);
        if(i!=0){
            return R.ok();
        }else{
            return R.error();
        }
    }
    @PostMapping("publishNews/{id}")
    public R publishNews(@PathVariable String id){
        News news=new News();
        news.setId(id);
        news.setStatus("Normal");
        newsService.updateById(news);
        return R.ok();
    }
    @PostMapping("getNewsListPage/{current}/{limit}")
    public R getNewsListPage(@PathVariable long current,
                               @PathVariable long limit,
                               @RequestBody(required = false) NewsQuery newsQuery){
        Page<News> pageTeacher = new Page<>(current, limit);
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        String title = newsQuery.getTitle();
        String status =newsQuery.getStatus();
        String begin =newsQuery.getBegin();
        String end =newsQuery.getEnd();
        String category =newsQuery.getCategory();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
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
        if (!StringUtils.isEmpty(category)){
            wrapper.eq("category",category);
        }
        wrapper.orderByDesc("gmt_create");
        newsService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<News> records = pageTeacher.getRecords();
        System.out.println(records);
        return R.ok().data("total",total).data("rows",records);
    }
    @PostMapping("backNews/{id}")
    public R backNews(@PathVariable String id){
        News news = new News();
        news.setId(id);
        news.setStatus("Draft");
        boolean b = newsService.updateById(news);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @DeleteMapping("delete/{id}")
    public R removeNews(@PathVariable String id){
        boolean flag = newsService.removeById(id);
        if(flag){
            return R.ok();
        }
        return R.error();
    }
}