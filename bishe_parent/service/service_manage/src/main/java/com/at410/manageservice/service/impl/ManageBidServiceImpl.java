package com.at410.manageservice.service.impl;

import com.at410.manageservice.entity.ManageBid;
import com.at410.manageservice.entity.ManageQualifications;
import com.at410.manageservice.mapper.ManageBidMapper;
import com.at410.manageservice.service.ManageBidService;
import com.at410.servicebase.config.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wtw
 * @since 2021-12-29
 */
@Service
public class ManageBidServiceImpl extends ServiceImpl<ManageBidMapper, ManageBid> implements ManageBidService {
    @Autowired
    private ManageBidService manageBidService;
    @Override
    public String saveCourseInfo(ManageBid manageBid) {
        int insert = baseMapper.insert(manageBid);
        if(insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        String cid=manageBid.getId();
        return cid;
    }

    @Override
    public ManageBid getItemInfo(String bidId) {
        ManageBid manageBid = baseMapper.selectById(bidId);
        return manageBid;
    }
}
