package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.product.entity.PmsSkuInfo;
import com.joiller.gulimall.product.service.IPmsSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/skuinfo")
public class PmsSkuInfoController {
    @Autowired
    IPmsSkuInfoService skuInfoService;

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> params){
        Page<PmsSkuInfo> skuInfoPage = new PageUtils<PmsSkuInfo>().page(params);
        Page<PmsSkuInfo> page = skuInfoService.queryPage(skuInfoPage, params);
        return R.ok().put("page", page);
    }

    @GetMapping("info")
    public R info(@RequestParam Long id) {
        PmsSkuInfo pmsSkuInfo = skuInfoService.getById(id);
        return R.ok().put("sku", pmsSkuInfo);
    }
}

