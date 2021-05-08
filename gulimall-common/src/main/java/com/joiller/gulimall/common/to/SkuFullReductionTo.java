package com.joiller.gulimall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jianghuilai
 * @since 2021-04-26 10:43
 **/

@Data
public class SkuFullReductionTo {
    private Long id;

    /**
     * spu_id
     */
    private Long skuId;

    /**
     * 满多少
     */
    private BigDecimal fullPrice;

    /**
     * 减多少
     */
    private BigDecimal reducePrice;

    /**
     * 是否参与其他优惠
     */
    private Boolean addOther;
}
