package com.joiller.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 属性分组
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsAttrGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId(value = "attr_group_id", type = IdType.AUTO)
    private Long attrGroupId;

    /**
     * 组名
     */
    private String attrGroupName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String descript;

    /**
     * 组图标
     */
    private String icon;

    /**
     * 所属分类id
     */
    private Long catelogId;


}
