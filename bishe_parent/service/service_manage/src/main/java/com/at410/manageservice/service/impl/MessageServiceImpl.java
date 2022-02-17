package com.at410.manageservice.service.impl;

import com.at410.manageservice.entity.Message;
import com.at410.manageservice.mapper.MessageMapper;
import com.at410.manageservice.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wtw
 * @since 2022-01-04
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
