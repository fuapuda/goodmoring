package com.at410.manageservice.service;

import com.at410.manageservice.entity.ManageBid;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wtw
 * @since 2021-12-29
 */
public interface ManageBidService extends IService<ManageBid> {

    String saveCourseInfo(ManageBid manageBid);

    ManageBid getItemInfo(String bidId);
}
