package com.joiller.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.product.entity.PmsAttrAttrgroupRelation;

import java.util.List;

/**
 * <p>
 * 属性&属性分组关联 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsAttrAttrgroupRelationService extends IService<PmsAttrAttrgroupRelation> {

    boolean saveOrUpdateByAttr(Long attrId, Long attrGroupId);

    List<Long> listAttrIdsByGroupId(Long attrGroupId);

    boolean removeBatchRelations(PmsAttrAttrgroupRelation[] relations);

    boolean saveBatchRelations(List<PmsAttrAttrgroupRelation> relations);
}
