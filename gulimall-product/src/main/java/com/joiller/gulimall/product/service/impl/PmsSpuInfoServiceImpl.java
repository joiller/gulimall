package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.product.entity.*;
import com.joiller.gulimall.product.feign.CouponFeignService;
import com.joiller.gulimall.product.mapper.PmsSpuInfoDescMapper;
import com.joiller.gulimall.product.mapper.PmsSpuInfoMapper;
import com.joiller.gulimall.product.service.*;
import com.joiller.gulimall.common.to.SkuFullReductionTo;
import com.joiller.gulimall.common.to.SkuLadderTo;
import com.joiller.gulimall.common.to.SkuMemberPriceTo;
import com.joiller.gulimall.common.to.SpuBoundsTo;
import com.joiller.gulimall.product.vo.*;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoMapper, PmsSpuInfo> implements IPmsSpuInfoService {
    @Autowired
    PmsSpuInfoDescMapper spuInfoDescMapper;
    @Autowired
    IPmsSpuImagesService spuImagesService;
    @Autowired
    IPmsProductAttrValueService productAttrValueService;
    @Autowired
    IPmsSkuInfoService skuInfoService;
    @Autowired
    IPmsSkuImagesService skuImagesService;
    @Autowired
    IPmsSkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    CouponFeignService couponFeignService;

    @Override
    @Transactional
    public boolean saveSpuSaveVo(SpuSaveVo spuSaveVo) {
        /*
            要save的有：
            1/spu info 基本信息
            2/spu info desc
            3/spu images
            4/pms_product_attr_value    spu属性
            5/sms_spu_bounds        spu积分


            6/sku
            6.1/pms_sku_info 基本属性
            6.2/pms_sku_sale_attr_value     sku属性
            6.3/pms_sku_images
            6.4/sms_sku_ladder
            6.5/sms_sku_full_reduction
            6.6/sms_member_price
         */

        PmsSpuInfo spuInfo = new PmsSpuInfo();
        BeanUtils.copyProperties(spuSaveVo, spuInfo);
        spuInfo.setCreateTime(LocalDateTime.now());
        spuInfo.setUpdateTime(LocalDateTime.now());
        this.save(spuInfo);

        PmsSpuInfoDesc spuInfoDesc = new PmsSpuInfoDesc();
        spuInfoDesc.setSpuId(spuInfo.getId());
        spuInfoDesc.setDecript(String.join(";", spuSaveVo.getDecript()));
        spuInfoDescMapper.insert(spuInfoDesc);

        spuImagesService.save(spuInfo.getId(), spuSaveVo.getImages());

        productAttrValueService.save(spuInfo.getId(), spuSaveVo.getBaseAttrs());


//        sku
        LinkedList<PmsSkuInfo> skuInfos = new LinkedList<>();
        spuSaveVo.getSkus().forEach(sku -> {
            String defaultImg = "";
            List<Images> images = sku.getImages();
            if (images != null && images.size() > 0) {
                for (Images image : images) {
                    if (image.getDefaultImg() == 1){
                        defaultImg = image.getImgUrl();
                    }
                }
            }
            PmsSkuInfo skuInfo = new PmsSkuInfo();
            BeanUtils.copyProperties(sku, skuInfo);
            skuInfo.setSkuDesc(String.join(";", sku.getDescar()));
            skuInfo.setBrandId(spuSaveVo.getBrandId());
            skuInfo.setSpuId(spuInfo.getId());
            skuInfo.setCatalogId(spuInfo.getCatalogId());
            skuInfo.setSaleCount(0L);
            skuInfo.setSkuDefaultImg(defaultImg);
            skuInfoService.save(skuInfo);

            if (images != null) {
                List<PmsSkuImages> skuImages = images.stream().filter(
                        // 要有image url 才有意义
                        images1 -> !StringUtils.isNullOrEmpty(images1.getImgUrl())
                ).map(img -> {
                    PmsSkuImages skuImage = new PmsSkuImages();
                    skuImage.setSkuId(skuInfo.getSkuId());
                    skuImage.setDefaultImg(img.getDefaultImg());
                    skuImage.setImgUrl(img.getImgUrl());
                    return skuImage;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(skuImages);
            }

            List<Attr> skuAttrs = sku.getAttr();
            List<PmsSkuSaleAttrValue> skuSaleAttrValues = skuAttrs.stream().map(attr -> {
                PmsSkuSaleAttrValue skuSaleAttrValue = new PmsSkuSaleAttrValue();
                BeanUtils.copyProperties(attr, skuSaleAttrValue);
                skuSaleAttrValue.setSkuId(skuInfo.getSkuId());
                return skuSaleAttrValue;
            }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValues);

            List<MemberPrice> memberPrices = sku.getMemberPrice();
            List<SkuMemberPriceTo> skuMemberPriceTos = memberPrices.stream()
                    .filter(memberPrice ->
                            // 要大于0才有意义
                        memberPrice.getPrice().compareTo(BigDecimal.valueOf(0)) > 0
                    )
                    .map(memberPrice -> {
                SkuMemberPriceTo skuMemberPriceTo = new SkuMemberPriceTo();
                skuMemberPriceTo.setMemberPrice(memberPrice.getPrice());
                skuMemberPriceTo.setSkuId(skuInfo.getSkuId());
                skuMemberPriceTo.setMemberLevelId(memberPrice.getId());
                skuMemberPriceTo.setMemberLevelName(memberPrice.getName());
                return skuMemberPriceTo;
            }).collect(Collectors.toList());
            couponFeignService.saveSkuMemberPrices(skuMemberPriceTos);

            if (sku.getReducePrice().compareTo(BigDecimal.valueOf(0)) > 0 && sku.getFullPrice().compareTo(BigDecimal.valueOf(0)) > 0){
                SkuFullReductionTo skuFullReductionTo = new SkuFullReductionTo();
                skuFullReductionTo.setFullPrice(sku.getFullPrice());
                skuFullReductionTo.setSkuId(skuInfo.getSkuId());
                skuFullReductionTo.setReducePrice(sku.getReducePrice());
                couponFeignService.saveSkuFullReduction(skuFullReductionTo);
            }

            SkuLadderTo skuLadderTo = new SkuLadderTo();
            skuLadderTo.setSkuId(skuInfo.getSkuId());
            skuLadderTo.setDiscount(sku.getDiscount());
            skuLadderTo.setFullCount(sku.getFullCount());
            skuLadderTo.setPrice(sku.getPrice());
            couponFeignService.saveSkuLadder(skuLadderTo);
        });

        //        sms_spu_bounds
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundsTo spuBoundsTo = new SpuBoundsTo();
        BeanUtils.copyProperties(bounds, spuBoundsTo);
        spuBoundsTo.setSpuId(spuInfo.getId());
        couponFeignService.saveSpuBounds(spuBoundsTo);

        return true;
    }
}
