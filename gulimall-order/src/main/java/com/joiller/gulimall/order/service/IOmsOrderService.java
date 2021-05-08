package com.joiller.gulimall.order.service;

import com.joiller.gulimall.order.entity.OmsOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.common.utils.R;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IOmsOrderService extends IService<OmsOrder> {

    public R lista();
}
