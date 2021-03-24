package com.joiller.gulimall.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import utils.R;

/**
 * @author jianghuilai
 * @create 2021-02-24 14:35
 **/

@FeignClient("gulimall-order")
public interface OrderFeignService {

    @GetMapping("/order/oms-order/list")
    public R list();
}
