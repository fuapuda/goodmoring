package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.ManageQualifications;
import com.at410.manageservice.entity.vo.ItemInfoVo;
import com.at410.manageservice.entity.vo.ItemQuery;
import com.at410.manageservice.entity.vo.QuaQuery;
import com.at410.manageservice.service.ManageItemService;
import com.at410.manageservice.service.ManageQualificationsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wtw
 * @since 2021-12-25
 */
@CrossOrigin
@RestController
@RequestMapping("/manageservice/manage-item")
public class ManageItemController {
    @Autowired
    private ManageItemService itemService;
    @PostMapping("addItem")
    public R addItem(@RequestBody ItemInfoVo itemInfoVo){
        String id=itemService.saveItemInfo(itemInfoVo);
        return R.ok().data("itemId",id);
    }
    @GetMapping("getItemInfo/{itemId}")
    public R getItemInfo(@PathVariable String itemId) {
        ItemInfoVo itemInfoVo = itemService.getItemInfo(itemId);
        return R.ok().data("itemInfo",itemInfoVo);
    }
    @PostMapping("updateItemInfo")
    public R updateItemInfo(@RequestBody ItemInfoVo itemInfoVo) {
        itemService.updateItemInfo(itemInfoVo);
        return R.ok();
    }
    @PostMapping("publishItem/{id}")
    public R publishItem(@PathVariable String id){
        ManageItem manageItem=new ManageItem();
        manageItem.setId(id);
        manageItem.setStatus("Normal");
        itemService.updateById(manageItem);
        return R.ok();
    }
    @PostMapping("pageItemCondition/{current}/{limit}")
    public R pageItemCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) ItemQuery itemQuery){
        Page<ManageItem> pageTeacher = new Page<>(current, limit);
        QueryWrapper<ManageItem> wrapper = new QueryWrapper<>();
        String name = itemQuery.getName();
        String status =itemQuery.getStatus();
        String begin =itemQuery.getBegin();
        String end =itemQuery.getEnd();
        String department =itemQuery.getDepartment();

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
        if (!StringUtils.isEmpty(department)){
            wrapper.eq("department",department);
        }
        wrapper.orderByDesc("gmt_create");
        itemService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<ManageItem> records = pageTeacher.getRecords();
        System.out.println(records);
        return R.ok().data("total",total).data("rows",records);
    }
    @PostMapping("backItem/{id}")
    public R backItem(@PathVariable String id){
        ManageItem manageItem=new ManageItem();
        manageItem.setId(id);
        manageItem.setStatus("Draft");
        itemService.updateById(manageItem);
        return R.ok();
    }
    @DeleteMapping("delete/{id}")
    public R removeItem(@PathVariable String id){
        boolean flag = itemService.removeById(id);
        if(flag){
            return R.ok();
        }
        return R.error();
    }
}

