package com.joiller.gulimall.coupon.controller;


import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.coupon.entity.SmsSkuFullReduction;
import com.joiller.gulimall.coupon.service.ISmsSkuFullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品满减信息 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/coupon/sms-sku-full-reduction")
public class SmsSkuFullReductionController {
    @Autowired
    ISmsSkuFullReductionService skuFullReductionService;

    @PostMapping("save")
    public R save(@RequestBody SmsSkuFullReduction skuFullReduction) {
        boolean saved = skuFullReductionService.save(skuFullReduction);
        return R.ok().put("save", saved);
    }
}

