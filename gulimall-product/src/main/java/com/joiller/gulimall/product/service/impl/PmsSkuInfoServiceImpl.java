package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.product.entity.PmsSkuInfo;
import com.joiller.gulimall.product.mapper.PmsSkuInfoMapper;
import com.joiller.gulimall.product.service.IPmsSkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfo> implements IPmsSkuInfoService {

    @Override
    public Page<PmsSkuInfo> queryPage(Page<PmsSkuInfo> skuInfoPage, Map<String, String> params) {
        QueryWrapper<PmsSkuInfo> queryWrapper = new QueryWrapper<>();
        /**
         * key:
         * catelogId: 0
         * brandId: 0
         * min: 0
         * max: 0
         */
        String key = params.get("key");
        String catalogId = params.get("catalogId");
        String brandId = params.get("brandId");
        String min = params.get("min");
        String max = params.get("max");
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and(w -> {
                w.eq("sku_id", key).or().like("sku_name", key);
            });
        }
        if (!StringUtils.isNullOrEmpty(catalogId)) {
            queryWrapper.eq("catalog_id", catalogId);
        }
        if (!StringUtils.isNullOrEmpty(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isNullOrEmpty(min)) {
            queryWrapper.ge("price", min);
        }
        if (!StringUtils.isNullOrEmpty(max)) {
            BigDecimal bigDecimal = new BigDecimal(max);
            if (bigDecimal.compareTo(BigDecimal.valueOf(0)) > 0) {
                queryWrapper.le("price", max);
            }
        }
        return this.page(skuInfoPage, queryWrapper);
    }
}
