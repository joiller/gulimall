package com.joiller.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.joiller.gulimall.common.valid.AddGroup;
import com.joiller.gulimall.common.valid.NonNegativeInteger;
import com.joiller.gulimall.common.valid.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * <p>
 * 品牌
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @Null(groups = {AddGroup.class}, message = "添加时不能有id")
//    @Null(groups = {AddGroup.class})
    @NotNull(groups = {UpdateGroup.class}, message = "更新时必须有id")
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    /**
     * 品牌名
     */
    @NotBlank(groups = {UpdateGroup.class, AddGroup.class})
    private String name;

    /**
     * 品牌logo地址
     */
    @NotEmpty(message = "添加时logo不能为空", groups = {AddGroup.class})
    @URL(groups = {AddGroup.class, UpdateGroup.class}, message = "logo必须是URL")
    private String logo;

    /**
     * 介绍
     */
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @TableLogic
//    @ListValue(value = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer showStatus;

    /**
     * 检索首字母
     */
    @Pattern(regexp = "^[a-zA-Z]$", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;

    /**
     * 排序
     */
    @NonNegativeInteger(groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;


}
