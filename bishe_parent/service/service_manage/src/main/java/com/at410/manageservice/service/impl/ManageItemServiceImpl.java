package com.at410.manageservice.service.impl;

import com.at410.manageservice.entity.ItemDescription;
import com.at410.manageservice.entity.ManageItem;
import com.at410.manageservice.entity.ManageQualifications;
import com.at410.manageservice.entity.vo.ItemInfoVo;
import com.at410.manageservice.mapper.ManageItemMapper;
import com.at410.manageservice.service.ItemDescriptionService;
import com.at410.manageservice.service.ManageItemService;
import com.at410.manageservice.service.ManageQualificationsService;
import com.at410.servicebase.config.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wtw
 * @since 2021-12-25
 */
@Service
public class ManageItemServiceImpl extends ServiceImpl<ManageItemMapper, ManageItem> implements ManageItemService {
    @Autowired
    private ItemDescriptionService descriptionService;

    @Override
    public String saveItemInfo(ItemInfoVo itemInfoVo) {
        ManageItem eduCourse = new ManageItem();
        BeanUtils.copyProperties(itemInfoVo,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        String cid=eduCourse.getId();
//        ItemDescription courseDescription = new ItemDescription();
//        courseDescription.setDescription(itemInfoVo.getDescription());
//        courseDescription.setId(cid);
//        descriptionService.save(courseDescription);
        System.out.println(cid);
        return cid;
    }

    @Override
    public ItemInfoVo getItemInfo(String itemId) {

        //1 查询课程表
        ManageItem eduCourse = baseMapper.selectById(itemId);
        ItemInfoVo itemInfoVo = new ItemInfoVo();
        BeanUtils.copyProperties(eduCourse,itemInfoVo);

        //2 查询描述表
//        ItemDescription itemDescription = descriptionService.getById(itemId);
//        itemInfoVo.setDescription(itemDescription.getDescription());

        return itemInfoVo;
    }

    @Override
    public void updateItemInfo(ItemInfoVo itemInfoVo) {
        //1 修改课程表
        ManageItem eduCourse = new ManageItem();
        BeanUtils.copyProperties(itemInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new GuliException(20001,"修改课程信息失败");
        }

        //2 修改描述表
//        ItemDescription description = new ItemDescription();
//        description.setId(itemInfoVo.getId());
//        description.setDescription(itemInfoVo.getDescription());
//        descriptionService.updateById(description);
    }
}
