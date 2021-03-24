package com.joiller.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品三级分类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Long catId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Long parentCid;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    @TableLogic
    private Integer showStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 计量单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 子分类
     */
    @TableField(exist = false)
    private List<PmsCategory> children;

}
