package com.joiller.gulimall.product.feign;

import com.joiller.gulimall.common.to.SkuLadderTo;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.common.to.SkuFullReductionTo;
import com.joiller.gulimall.common.to.SkuMemberPriceTo;
import com.joiller.gulimall.common.to.SpuBoundsTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/sms-spu-bounds/save")
    R saveSpuBounds(@RequestBody SpuBoundsTo spuBoundsTo);

    @PostMapping("/coupon/sms-member-price/saveBatch")
    R saveSkuMemberPrices(@RequestBody List<SkuMemberPriceTo> skuMemberPriceTos);

    @PostMapping("/coupon/sms-sku-full-reduction/save")
    R saveSkuFullReduction(@RequestBody SkuFullReductionTo skuFullReductionTo);

    @PostMapping("/coupon/sms-sku-ladder/save")
    R saveSkuLadder(SkuLadderTo skuLadderTo);
}
// 测试一下：
/*
feignclient是否需要添加component注解才能被添加到bean中
是否可以在类上添加requestMapping注解
 */