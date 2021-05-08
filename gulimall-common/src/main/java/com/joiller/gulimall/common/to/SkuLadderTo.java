package com.joiller.gulimall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jianghuilai
 * @since 2021-04-26 10:44
 **/

@Data
public class SkuLadderTo {
    private Long id;

    /**
     * spu_id
     */
    private Long skuId;

    /**
     * 满几件
     */
    private Integer fullCount;

    /**
     * 打几折
     */
    private BigDecimal discount;

    /**
     * 折后价
     */
    private BigDecimal price;

    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    private Boolean addOther;
}
