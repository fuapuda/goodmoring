package com.at410.manageservice.service.impl;

import com.at410.manageservice.entity.ManageQualifications;
import com.at410.manageservice.mapper.ManageQualificationsMapper;
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
 * @since 2021-12-23
 */
@Service
public class ManageQualificationsServiceImpl extends ServiceImpl<ManageQualificationsMapper, ManageQualifications> implements ManageQualificationsService {
    @Autowired
    private ManageQualificationsService qualificationsService;
    @Override
    public String saveCourseInfo(ManageQualifications manageQualifications) {
        ManageQualifications eduCourse = new ManageQualifications();
        BeanUtils.copyProperties(manageQualifications,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        String cid=eduCourse.getId();
        System.out.println(cid);
        return cid;
    }

    @Override
    public ManageQualifications getQuaInfo(String quaId) {
        ManageQualifications manageQualifications = baseMapper.selectById(quaId);
        return manageQualifications;
    }

    @Override
    public void updateQuaInfo(ManageQualifications manageQualifications) {
        int i = baseMapper.updateById(manageQualifications);
        if(i== 0) {
            throw new GuliException(20001,"修改课程信息失败");
        }
    }
}
