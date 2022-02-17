package com.at410.manageservice.controller;

import com.at410.commonutils.R;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.ManageUser;
import com.at410.manageservice.entity.vo.UserVo;
import com.at410.manageservice.service.ManageUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manageservice/user")
@CrossOrigin
public class EduLoginController {
    @Autowired
    private ManageUserService manageUserService;
    @PostMapping("login")
    public R login(@RequestBody UserVo userVo){
        QueryWrapper<ManageUser> wrapper = new QueryWrapper<>();

        wrapper.eq("username",userVo.getUsername());
        wrapper.eq("password",userVo.getPassword());
        System.out.println(userVo.getUsername());
        System.out.println(userVo.getPassword());
        ManageUser one = manageUserService.getOne(wrapper);
        if(one!=null){
            return R.ok().data("token",one.getId());
        }else {
            return R.ok();
        }
    }
    @GetMapping("info/{token}")
    public R info(@PathVariable String token){
        ManageUser byId = manageUserService.getById(token);
        if(byId!=null){
            return R.ok().data("roles",byId.getRoles()).data("name",byId.getUsername()).data("avatar",byId.getAvatar());
        }else{
            return R.ok().data("roles",null);
        }
    }
}
