package com.at410.manageservice.service;

import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.vo.ItemInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wtw
 * @since 2021-12-25
 */
public interface ManageItemService extends IService<ManageItem> {


    String saveItemInfo(ItemInfoVo itemInfoVo);

    ItemInfoVo getItemInfo(String itemId);

    void updateItemInfo(ItemInfoVo itemInfoVo);
}
