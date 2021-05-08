package com.joiller.gulimall.product.mapper;

import com.joiller.gulimall.product.entity.PmsAttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 属性&属性分组关联 Mapper 接口
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface PmsAttrAttrgroupRelationMapper extends BaseMapper<PmsAttrAttrgroupRelation> {

    boolean deleteBatchRelations(@Param("relations") PmsAttrAttrgroupRelation[] relations);
}
