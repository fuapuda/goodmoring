package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageBid;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.vo.BidQuery;
import com.at410.manageservice.entity.vo.ItemInfoVo;
import com.at410.manageservice.entity.vo.ItemQuery;
import com.at410.manageservice.service.ManageBidService;
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
 * @since 2021-12-29
 */
@RestController
@RequestMapping("/manageservice/manage-bid")
@CrossOrigin
public class ManageBidController {
    @Autowired
    private ManageBidService manageBidService;
    @PostMapping("addBidInfo")
    public R addCourseInfo(@RequestBody ManageBid manageBid){
        String id=manageBidService.saveCourseInfo(manageBid);
        System.out.println(id);
        return R.ok().data("bidId",id);
    }
    @GetMapping("getBidInfo/{bidId}")
    public R getBidInfo(@PathVariable String bidId) {
        ManageBid manageBid = manageBidService.getItemInfo(bidId);
        return R.ok().data("itemInfo",manageBid);
    }
    @PostMapping("updateBidInfo")
    public R updateBidInfo(@RequestBody ManageBid manageBid) {
        manageBidService.updateById(manageBid);
        return R.ok();
    }
    @PostMapping("publishBid/{id}")
    public R publishBid(@PathVariable String id){
        ManageBid manageBid=new ManageBid();
        manageBid.setId(id);
        manageBid.setStatus("Normal");
        manageBidService.updateById(manageBid);
        return R.ok();
    }
    @PostMapping("pageBidCondition/{current}/{limit}")
    public R pageBidCondition(@PathVariable long current,
                               @PathVariable long limit,
                               @RequestBody(required = false) BidQuery bidQuery){
        Page<ManageBid> pageTeacher = new Page<>(current, limit);
        QueryWrapper<ManageBid> wrapper = new QueryWrapper<>();
        String title = bidQuery.getTitle();
        String status =bidQuery.getStatus();
        String begin =bidQuery.getBegin();
        String end =bidQuery.getEnd();
        String departmentId =bidQuery.getDepartmentId();

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
        if (!StringUtils.isEmpty(departmentId)){
            wrapper.eq("department_id",departmentId);
        }
        wrapper.orderByDesc("gmt_create");
        manageBidService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<ManageBid> records = pageTeacher.getRecords();
        System.out.println(records);
        return R.ok().data("total",total).data("rows",records);
    }
    @PostMapping("backBid/{id}")
    public R backBid(@PathVariable String id){
        ManageBid manageBid=new ManageBid();
        manageBid.setId(id);
        manageBid.setStatus("Draft");
        manageBidService.updateById(manageBid);
        return R.ok();
    }
    @GetMapping("findAllNormal")
    public R findAllNormal(){
        QueryWrapper<ManageBid> wrapper = new QueryWrapper<>();
        wrapper.eq("status","Normal");
        List<ManageBid> list = manageBidService.list(wrapper);
        return R.ok().data("bidList",list);
    }
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id){
        boolean flag = manageBidService.removeById(id);
        if(flag){
            return R.ok();
        }
        return R.error();
    }
    @PostMapping("successBid/{id}")
    public R successBid(@PathVariable String id){
        ManageBid manageBid=new ManageBid();
        manageBid.setId(id);
        manageBid.setStatus("Finally");
        manageBidService.updateById(manageBid);
        return R.ok();
    }
}

