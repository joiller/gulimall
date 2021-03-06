package com.joiller.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.ware.entity.WmsWareSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 商品库存 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IWmsWareSkuService extends IService<WmsWareSku> {

    Page<WmsWareSku> page(Page<WmsWareSku> wareSkuPage, Map<String, String> map);

    boolean addStock(Long skuId, Long wareId, Integer skuNum);
}
