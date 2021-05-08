package com.joiller.gulimall.coupon.controller;


import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.coupon.entity.SmsMemberPrice;
import com.joiller.gulimall.coupon.service.ISmsMemberPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品会员价格 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/coupon/sms-member-price")
public class SmsMemberPriceController {

    @Autowired
    ISmsMemberPriceService memberPriceService;

    @PostMapping("saveBatch")
    public R saveBatch(@RequestBody List<SmsMemberPrice> memberPrices) {
        boolean b = memberPriceService.saveBatch(memberPrices);
        return R.ok().put("saved", b);
    }
}

