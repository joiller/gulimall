package com.joiller.gulimall.ware.mapper;

import com.joiller.gulimall.ware.entity.WmsWareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品库存 Mapper 接口
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface WmsWareSkuMapper extends BaseMapper<WmsWareSku> {

    boolean addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);
}
