package com.joiller.gulimall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.product.entity.PmsSkuInfo;

import java.util.Map;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsSkuInfoService extends IService<PmsSkuInfo> {

    Page<PmsSkuInfo> queryPage(Page<PmsSkuInfo> skuInfoPage, Map<String, String> params);
}
