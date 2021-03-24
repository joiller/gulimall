package com.joiller.gulimall.order.service.impl;

import com.joiller.gulimall.order.entity.MqMessage;
import com.joiller.gulimall.order.mapper.MqMessageMapper;
import com.joiller.gulimall.order.service.IMqMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage> implements IMqMessageService {

}
