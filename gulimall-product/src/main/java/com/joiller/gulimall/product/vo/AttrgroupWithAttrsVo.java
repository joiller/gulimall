package com.joiller.gulimall.product.vo;

import com.joiller.gulimall.product.entity.PmsAttr;
import com.joiller.gulimall.product.entity.PmsAttrGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author jianghuilai
 * @since 2021-04-22 14:52
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class AttrgroupWithAttrsVo extends PmsAttrGroup {
    private List<PmsAttr> attrs;
}
