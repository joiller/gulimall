package com.joiller.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.ware.entity.WmsPurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IWmsPurchaseDetailService extends IService<WmsPurchaseDetail> {

    Page<WmsPurchaseDetail> page(Page<WmsPurchaseDetail> purchaseDetailPage, Map<String, String> map);

    List<WmsPurchaseDetail> listByPurchaseIds(List<Long> ids);
}
