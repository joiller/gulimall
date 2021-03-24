package com.joiller.gulimall.coupon.controller;


import com.joiller.gulimall.coupon.service.ISmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/coupon/sms-coupon")
public class SmsCouponController {

    @Autowired
    private ISmsCouponService smsCouponService;

    @GetMapping("/list")
    public R list(){
        return smsCouponService.lista();
    }
}

