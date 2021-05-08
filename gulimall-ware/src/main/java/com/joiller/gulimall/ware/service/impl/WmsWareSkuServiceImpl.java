package com.joiller.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.ware.entity.WmsWareSku;
import com.joiller.gulimall.ware.feign.ProductFeignService;
import com.joiller.gulimall.ware.mapper.WmsWareSkuMapper;
import com.joiller.gulimall.ware.service.IWmsWareSkuService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品库存 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuMapper, WmsWareSku> implements IWmsWareSkuService {
    @Autowired
    ProductFeignService productFeignService;

    @Override
    public Page<WmsWareSku> page(Page<WmsWareSku> wareSkuPage, Map<String, String> map) {
        String skuId = map.get("skuId");
        String wareId = map.get("wareId");
        QueryWrapper<WmsWareSku> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }
        if (!StringUtils.isNullOrEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }
        return this.page(wareSkuPage, queryWrapper);
    }

    @Override
    public boolean addStock(Long skuId, Long wareId, Integer skuNum) {
        QueryWrapper<WmsWareSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", skuId).eq("ware_id", wareId);
        List<WmsWareSku> list = this.list(queryWrapper);
        if (list == null || list.size() == 0) {
            WmsWareSku wmsWareSku = new WmsWareSku();
            wmsWareSku.setSkuId(skuId);
            wmsWareSku.setWareId(wareId);
            try {
                R skuinfo = productFeignService.skuinfo(skuId);
                Map<String, Object> sku = (Map<String, Object>) skuinfo.get("sku");
                wmsWareSku.setSkuName((String) sku.get("sku_name"));
            }catch (Exception ignored) {

            }

            wmsWareSku.setStock(skuNum);
            return this.save(wmsWareSku);
        }
        return this.getBaseMapper().addStock(skuId, wareId, skuNum);
    }
}
