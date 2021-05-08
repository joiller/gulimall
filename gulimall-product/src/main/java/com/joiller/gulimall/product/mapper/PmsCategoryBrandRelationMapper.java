package com.joiller.gulimall.product.mapper;

import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.entity.PmsCategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 品牌分类关联 Mapper 接口
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface PmsCategoryBrandRelationMapper extends BaseMapper<PmsCategoryBrandRelation> {

    Boolean updateCategory(PmsCategory category);
}
