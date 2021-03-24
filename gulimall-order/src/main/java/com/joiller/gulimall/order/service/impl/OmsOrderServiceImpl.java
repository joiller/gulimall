package com.joiller.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joiller.gulimall.order.entity.OmsOrder;
import com.joiller.gulimall.order.mapper.OmsOrderMapper;
import com.joiller.gulimall.order.service.IOmsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.R;

import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {
    @Autowired
    private OmsOrderMapper omsOrderMapper;

    public R lista(){
        List<OmsOrder> omsOrders = omsOrderMapper.selectList(new QueryWrapper<>());
        return R.ok().put("orders", omsOrders);
    }
}
