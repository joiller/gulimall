package com.joiller.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.ware.entity.WmsPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.ware.vo.MergeVo;
import com.joiller.gulimall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购信息 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IWmsPurchaseService extends IService<WmsPurchase> {

    Page<WmsPurchase> page(Page<WmsPurchase> purchasePage, Map<String, String> map);

    Page<WmsPurchase> page(Map<String, String> map);

    Page<WmsPurchase> pageUnreceive(Page<WmsPurchase> purchasePage, Map<String, String> map);

    boolean merge(MergeVo mergeVo);

    boolean receive(List<Long> ids);

    boolean done(PurchaseDoneVo purchaseDoneVo);
}
