package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ConsumerBiding;
import com.at410.manageservice.entity.ManageBid;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.vo.BidQuery;
import com.at410.manageservice.entity.vo.BidingAndBid;
import com.at410.manageservice.entity.vo.BidingQuery;
import com.at410.manageservice.service.ConsumerBidingService;
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
 * @since 2021-12-31
 */
@RestController
@RequestMapping("/manageservice/consumer-biding")
@CrossOrigin
public class ConsumerBidingController {
    @Autowired
    ManageBidService manageBidService;
    @Autowired
    ConsumerBidingService consumerBidingService;
    @PostMapping("pageBidingCondition/{current}/{limit}")
    public R pageBidingCondition(@PathVariable long current,
                              @PathVariable long limit,
                              @RequestBody(required = false) BidingQuery bidingQuery){
        Page<ConsumerBiding> pageTeacher = new Page<>(current, limit);
        QueryWrapper<ConsumerBiding> wrapper = new QueryWrapper<>();
        String companyName=bidingQuery.getCompanyName();
        String mbId =bidingQuery.getMbId();
        String begin =bidingQuery.getBegin();
        String end =bidingQuery.getEnd();
        String status=bidingQuery.getStatus();

        if (!StringUtils.isEmpty(companyName)){
            wrapper.like("companyName",companyName);
        }
        if (!StringUtils.isEmpty(mbId)){
            wrapper.eq("mb_id",mbId);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        wrapper.orderByDesc("gmt_create");
        consumerBidingService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<ConsumerBiding> records = pageTeacher.getRecords();
        for(ConsumerBiding biding:records){
            ManageBid byId = manageBidService.getById(biding.getMbId());
            biding.setTitle(byId.getTitle());
            boolean b = consumerBidingService.updateById(biding);
        }
        System.out.println(records);
        return R.ok().data("total",total).data("rows",records);
    }
    @GetMapping("getBidingInfo/{bidingId}")
    public R getBidingInfo(@PathVariable String bidingId) {
        ConsumerBiding biding = consumerBidingService.getById(bidingId);
        return R.ok().data("bidingInfo",biding);
    }
    @PostMapping("deprecated/{id}")
    public R deprecated(@PathVariable String id){
        QueryWrapper<ConsumerBiding> wrapper = new QueryWrapper<>();
        wrapper.eq("mb_id",id);
        wrapper.eq("status","todo");
        ConsumerBiding biding = new ConsumerBiding();
        biding.setStatus("deprecated");
        consumerBidingService.update(biding,wrapper);
        wrapper.eq("status","fail");
        consumerBidingService.update(biding,wrapper);
        return R.ok();
    }
    @PostMapping("successBiding/{id}")
    public R successBiding(@PathVariable int id){
        ConsumerBiding biding = new ConsumerBiding();
        biding.setId(id);
        biding.setStatus("success");
        consumerBidingService.updateById(biding);
        return R.ok();
    }

    @PostMapping("pass/{id}")
    public R pass(@PathVariable int id){
        ConsumerBiding biding = new ConsumerBiding();
        biding.setId(id);
        biding.setStatus("fail");
        consumerBidingService.updateById(biding);
        return R.ok();
    }

}

