package com.at410.manageservice.service;

import com.at410.manageservice.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wtw
 * @since 2022-01-03
 */
public interface NewsService extends IService<News> {

    String saveNewsInfo(News news);

    News getNewsInfo(String newsId);

    int updateItemInfo(News news);
}
