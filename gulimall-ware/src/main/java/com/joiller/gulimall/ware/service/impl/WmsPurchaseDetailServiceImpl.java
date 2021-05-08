package com.joiller.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.ware.entity.WmsPurchaseDetail;
import com.joiller.gulimall.ware.mapper.WmsPurchaseDetailMapper;
import com.joiller.gulimall.ware.service.IWmsPurchaseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class WmsPurchaseDetailServiceImpl extends ServiceImpl<WmsPurchaseDetailMapper, WmsPurchaseDetail> implements IWmsPurchaseDetailService {

    @Override
    public Page<WmsPurchaseDetail> page(Page<WmsPurchaseDetail> purchaseDetailPage, Map<String, String> map) {
        /**
         * key:
         * status:
         * wareId:
         */
        String key = map.get("key");
        String status = map.get("status");
        String wareId = map.get("wareId");
        QueryWrapper<WmsPurchaseDetail> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and(w -> {
                w.eq("id", key).or().eq("purchase_id", key).or().eq("sku_id", key);
            });
        }
        if (!StringUtils.isNullOrEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        if (!StringUtils.isNullOrEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }
        return this.page(purchaseDetailPage, queryWrapper);
    }

    @Override
    public List<WmsPurchaseDetail> listByPurchaseIds(List<Long> ids) {
        return this.getBaseMapper().listByPurchaseIds(ids);
    }
}
