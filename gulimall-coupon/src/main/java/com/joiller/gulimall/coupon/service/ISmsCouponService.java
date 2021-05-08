package com.joiller.gulimall.coupon.service;

import com.joiller.gulimall.coupon.entity.SmsCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.common.utils.R;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface ISmsCouponService extends IService<SmsCoupon> {

    public R lista();
}
