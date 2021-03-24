package com.joiller.gulimall.product.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * spu信息介绍
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsSpuInfoDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 商品介绍
     */
    private String decript;


}
