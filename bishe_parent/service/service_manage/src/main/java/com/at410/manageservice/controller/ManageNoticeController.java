package com.at410.manageservice.controller;


import com.at410.commonutils.R;
import com.at410.manageservice.entity.ConsumerBiding;
import com.at410.manageservice.entity.ManageBid;
import com.at410.manageservice.entity.ManageNotice;
import com.at410.manageservice.service.ConsumerBidingService;
import com.at410.manageservice.service.ManageBidService;
import com.at410.manageservice.service.ManageNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wtw
 * @since 2022-01-02
 */
@RestController
@RequestMapping("/manageservice/manage-notice")
@CrossOrigin
public class ManageNoticeController {
    @Autowired
    private ManageNoticeService manageNoticeService;
    @Autowired
    private ConsumerBidingService bidingService;
    @PostMapping("addNoticeInfo")
    public R addNoticeInfo(@RequestBody ManageNotice manageNotice){
        boolean save = manageNoticeService.save(manageNotice);
        if(save==true){
            ConsumerBiding biding = new ConsumerBiding();
//            ManageBid manageBid = new ManageBid();
//            manageBid.setId(manageNotice.getId());
//            manageBid.setStatus("success");
//            manageBidService.updateById(manageBid);
            return R.ok();
        }else{
            return R.error();
        }
    }
}

