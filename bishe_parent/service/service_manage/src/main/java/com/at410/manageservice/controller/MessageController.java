package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.Message;
import com.at410.manageservice.entity.News;
import com.at410.manageservice.entity.vo.MessageQuery;
import com.at410.manageservice.entity.vo.NewsQuery;
import com.at410.manageservice.service.MessageService;
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
 * @since 2022-01-04
 */
@RestController
@RequestMapping("/manageservice/message")
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;
    @PostMapping("getMsgListPage/{current}/{limit}")
    public R getMsgListPage(@PathVariable long current,
                            @PathVariable long limit,
                            @RequestBody(required = false)MessageQuery messageQuery){
        Page<Message> pageTeacher = new Page<>(current, limit);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        Integer status =messageQuery.getStatus();
        wrapper.orderByDesc("gmt_create");
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        messageService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<Message> records = pageTeacher.getRecords();
        System.out.println(records);
        return R.ok().data("total",total).data("rows",records);
    }
    @PostMapping("todo/{id}")
    public R todo(@PathVariable int id){
        Message message=new Message();
        message.setId(id);
        message.setStatus(1);
        messageService.updateById(message);
        return R.ok();
    }
}

