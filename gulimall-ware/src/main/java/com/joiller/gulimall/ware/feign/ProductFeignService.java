package com.joiller.gulimall.ware.feign;

import com.joiller.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jianghuilai
 * @since 2021-04-30 19:36
 **/

@FeignClient("gulimall-product")
//@RestController("product")
public interface ProductFeignService {
    @PostMapping("skuinfo/info")
    R skuinfo(@RequestBody Long id);
}
