package com.at410.manageservice.service;

import com.at410.manageservice.entity.ManageQualifications;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wtw
 * @since 2021-12-23
 */
public interface ManageQualificationsService extends IService<ManageQualifications> {

    String saveCourseInfo(ManageQualifications manageQualifications);

    ManageQualifications getQuaInfo(String quaId);

    void updateQuaInfo(ManageQualifications manageQualifications);
}
