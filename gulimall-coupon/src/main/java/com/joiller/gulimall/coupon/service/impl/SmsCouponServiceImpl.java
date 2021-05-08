package com.joiller.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joiller.gulimall.coupon.entity.SmsCoupon;
import com.joiller.gulimall.coupon.mapper.SmsCouponMapper;
import com.joiller.gulimall.coupon.service.ISmsCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joiller.gulimall.common.utils.R;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements ISmsCouponService {
    @Autowired
    private SmsCouponMapper smsCouponMapper;

    @Override
    public R lista() {
        List<SmsCoupon> smsCoupons = smsCouponMapper.selectList(new QueryWrapper<>());
        return R.ok().put("coupons", smsCoupons);
    }
}
