package com.joiller.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.common.constant.WareConstant;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.ware.entity.WmsPurchase;
import com.joiller.gulimall.ware.entity.WmsPurchaseDetail;
import com.joiller.gulimall.ware.entity.WmsWareSku;
import com.joiller.gulimall.ware.mapper.WmsPurchaseMapper;
import com.joiller.gulimall.ware.service.IWmsPurchaseDetailService;
import com.joiller.gulimall.ware.service.IWmsPurchaseService;
import com.joiller.gulimall.ware.service.IWmsWareSkuService;
import com.joiller.gulimall.ware.vo.MergeVo;
import com.joiller.gulimall.ware.vo.PurchaseDoneVo;
import com.joiller.gulimall.ware.vo.PurchaseItemDoneVo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 采购信息 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class WmsPurchaseServiceImpl extends ServiceImpl<WmsPurchaseMapper, WmsPurchase> implements IWmsPurchaseService {
    @Autowired
    IWmsPurchaseDetailService purchaseDetailService;

    @Autowired
    IWmsWareSkuService wareSkuService;

    @Override
    public Page<WmsPurchase> page(Page<WmsPurchase> purchasePage, Map<String, String> map) {
        QueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<>();
        String key = map.get("key");
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and(wmsPurchaseQueryWrapper -> {
                wmsPurchaseQueryWrapper.eq("id", key).or().eq("assignee_id", key).or().like("assignee_name", key);
            });
        }
        String status = map.get("status");
        if (!StringUtils.isNullOrEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        return this.page(purchasePage, queryWrapper);
    }

    @Override
    public Page<WmsPurchase> page(Map<String, String> map) {
        Page<WmsPurchase> page = new PageUtils<WmsPurchase>().page(map);
        QueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<>();
        String key = map.get("key");
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and(wmsPurchaseQueryWrapper -> {
                wmsPurchaseQueryWrapper.eq("id", key).or().eq("assignee_id", key).or().like("assignee_name", key);
            });
        }
        String status = map.get("status");
        if (!StringUtils.isNullOrEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        return this.page(page, queryWrapper);

    }

    @Override
    public Page<WmsPurchase> pageUnreceive(Page<WmsPurchase> purchasePage, Map<String, String> map) {
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(0);
        integers.add(1);
        QueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<WmsPurchase>().in("status", integers);
        return this.page(purchasePage, queryWrapper);
    }

    @Override
    @Transactional
    public boolean merge(MergeVo mergeVo) {
        /**
         * items: [7]
         * purchaseId: 5
         */
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null) {
            WmsPurchase purchase = new WmsPurchase();
            purchase.setCreateTime(LocalDateTime.now());
            purchase.setUpdateTime(LocalDateTime.now());
            purchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            purchase.setPriority(1);
            this.save(purchase);
            purchaseId = purchase.getId();
        }

        Long finalPurchaseId = purchaseId;
        List<WmsPurchaseDetail> purchaseDetails = mergeVo.getItems().stream().map(integer -> {
            WmsPurchaseDetail purchaseDetail = new WmsPurchaseDetail();
            purchaseDetail.setPurchaseId(finalPurchaseId);
            purchaseDetail.setId(integer);
            purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return purchaseDetail;
        }).collect(Collectors.toList());
        return purchaseDetailService.updateBatchById(purchaseDetails);

    }

    @Override
    @Transactional
    public boolean receive(List<Long> ids) {
        List<WmsPurchase> purchases = this.listByIds(ids);
        List<WmsPurchase> collect = purchases.stream().filter(p -> Objects.equals(p.getStatus(), WareConstant.PurchaseStatusEnum.CREATED.getCode()) ||
                Objects.equals(p.getStatus(), WareConstant.PurchaseStatusEnum.ASSIGNED.getCode())
        ).peek(purchase -> {
            purchase.setStatus(WareConstant.PurchaseStatusEnum.PURCHASING.getCode());
            purchase.setUpdateTime(LocalDateTime.now());
        }).collect(Collectors.toList());
        this.updateBatchById(collect);

        List<WmsPurchaseDetail> purchaseDetails = purchaseDetailService.listByPurchaseIds(ids);
        List<WmsPurchaseDetail> collect1 = purchaseDetails.stream()
                .filter(purchaseDetail -> Objects.equals(purchaseDetail.getStatus(), WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode()) ||
                        Objects.equals(purchaseDetail.getStatus(), WareConstant.PurchaseDetailStatusEnum.CREATED.getCode()))
                .peek(purchaseDetail -> {
                    purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.PURCHASING.getCode());
                }).collect(Collectors.toList());
        return purchaseDetailService.updateBatchById(collect1);
    }

    @Override
    @Transactional
    public boolean done(PurchaseDoneVo purchaseDoneVo) {
        List<PurchaseItemDoneVo> purchaseItemDoneVos = purchaseDoneVo.getItems();
        Long purchaseId = purchaseDoneVo.getId();
        // 更新 purchaseDetail 采购需求
        boolean flag = true;
        LinkedList<WmsPurchaseDetail> purchaseDetails = new LinkedList<>();
        LinkedList<WmsWareSku> wareSkus = new LinkedList<>();
        for (PurchaseItemDoneVo purchaseItemDoneVo : purchaseItemDoneVos) {
            WmsPurchaseDetail purchaseDetail = purchaseDetailService.getById(purchaseItemDoneVo.getItemId());
            if (Objects.equals(purchaseItemDoneVo.getStatus(), WareConstant.PurchaseDetailStatusEnum.FAILED.getCode())) {
                flag = false;
            }else {
                // 更新 wareSku 库存
                wareSkuService.addStock(purchaseDetail.getSkuId(), purchaseDetail.getWareId(), purchaseDetail.getSkuNum());
            }
            purchaseDetail.setStatus(purchaseItemDoneVo.getStatus());
            purchaseDetails.add(purchaseDetail);

        }
        purchaseDetailService.updateBatchById(purchaseDetails);
        // 更新purchase 采购单
        WmsPurchase purchase = new WmsPurchase();
        purchase.setId(purchaseId);
        purchase.setStatus(flag ? WareConstant.PurchaseStatusEnum.COMPLETED.getCode() :
                WareConstant.PurchaseStatusEnum.FAILED.getCode());
        return this.updateById(purchase);
    }
}
