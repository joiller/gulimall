package com.joiller.gulimall.order.service.impl;

import com.joiller.gulimall.order.entity.OmsOrderItem;
import com.joiller.gulimall.order.mapper.OmsOrderItemMapper;
import com.joiller.gulimall.order.service.IOmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单项信息 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements IOmsOrderItemService {

}
