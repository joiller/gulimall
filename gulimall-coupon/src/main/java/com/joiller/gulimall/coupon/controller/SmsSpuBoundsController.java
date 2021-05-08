package com.joiller.gulimall.coupon.controller;


import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.coupon.entity.SmsSpuBounds;
import com.joiller.gulimall.coupon.service.ISmsSpuBoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品spu积分设置 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/coupon/sms-spu-bounds")
public class SmsSpuBoundsController {
    @Autowired
    ISmsSpuBoundsService spuBoundsService;

    @PostMapping("save")
    public R save(@RequestBody SmsSpuBounds spuBounds) {
        boolean saved = spuBoundsService.save(spuBounds);
        return R.ok().put("save", saved);
    }
}

