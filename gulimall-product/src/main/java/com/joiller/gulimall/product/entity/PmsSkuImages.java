package com.joiller.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * sku图片
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsSkuImages implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 排序
     */
    private Integer imgSort;

    /**
     * 默认图[0 - 不是默认图，1 - 是默认图]
     */
    private Integer defaultImg;


}
