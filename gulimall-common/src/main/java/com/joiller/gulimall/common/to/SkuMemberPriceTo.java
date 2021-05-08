package com.joiller.gulimall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jianghuilai
 * @since 2021-04-26 10:43
 **/

@Data
public class SkuMemberPriceTo {
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 会员等级id
     */
    private Long memberLevelId;

    /**
     * 会员等级名
     */
    private String memberLevelName;

    /**
     * 会员对应价格
     */
    private BigDecimal memberPrice;

    /**
     * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
     */
    private Boolean addOther;
}
