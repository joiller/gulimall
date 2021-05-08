package com.joiller.gulimall.product.service;

import com.joiller.gulimall.product.entity.PmsProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.product.vo.BaseAttrs;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsProductAttrValueService extends IService<PmsProductAttrValue> {

    boolean save(Long id, List<BaseAttrs> baseAttrs);

    List<PmsProductAttrValue> listForSpu(Long spuId);

    boolean updateForSpu(Long spuId, List<PmsProductAttrValue> productAttrValues);
}
