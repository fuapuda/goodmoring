package com.at410.manageservice.service.impl;

import com.at410.manageservice.entity.News;
import com.at410.manageservice.mapper.NewsMapper;
import com.at410.manageservice.service.NewsService;
import com.at410.servicebase.config.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wtw
 * @since 2022-01-03
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public String saveNewsInfo(News news) {
        int insert = baseMapper.insert(news);
        if(insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        String cid=news.getId();
        return cid;
    }

    @Override
    public News getNewsInfo(String newsId) {
        News news = baseMapper.selectById(newsId);
        return news;
    }

    @Override
    public int updateItemInfo(News news) {
        int i = baseMapper.updateById(news);
        return i;
    }
}
