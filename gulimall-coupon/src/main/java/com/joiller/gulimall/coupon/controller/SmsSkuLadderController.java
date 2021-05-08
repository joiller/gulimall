package com.joiller.gulimall.coupon.controller;


import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.coupon.entity.SmsSkuLadder;
import com.joiller.gulimall.coupon.service.ISmsSkuLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品阶梯价格 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/coupon/sms-sku-ladder")
public class SmsSkuLadderController {
    @Autowired
    ISmsSkuLadderService skuLadderService;

    @PostMapping("save")
    public R save(@RequestBody SmsSkuLadder skuLadder) {
        boolean saved = skuLadderService.save(skuLadder);
        return R.ok().put("save", saved);
    }
}

