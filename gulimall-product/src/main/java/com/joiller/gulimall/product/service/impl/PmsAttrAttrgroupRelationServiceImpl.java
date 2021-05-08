package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.product.entity.PmsAttrAttrgroupRelation;
import com.joiller.gulimall.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.joiller.gulimall.product.service.IPmsAttrAttrgroupRelationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性&属性分组关联 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsAttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationMapper, PmsAttrAttrgroupRelation> implements IPmsAttrAttrgroupRelationService {

    @Override
    public boolean saveOrUpdateByAttr(Long attrId, Long attrGroupId) {
        PmsAttrAttrgroupRelation relation = this.getOne(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("attr_id", attrId));
        if (relation == null) {
            relation = new PmsAttrAttrgroupRelation();
            relation.setAttrGroupId(attrGroupId);
            relation.setAttrId(attrId);
            relation.setAttrSort(0);
            return this.save(relation);
        }
        relation.setAttrGroupId(attrGroupId);
        return this.updateById(relation);
    }

    @Override
    public List<Long> listAttrIdsByGroupId(Long attrGroupId){
        List<PmsAttrAttrgroupRelation> relations = this.list(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("attr_group_id", attrGroupId));
        return relations.stream().map(PmsAttrAttrgroupRelation::getAttrId).collect(Collectors.toList());
    }

    @Override
    public boolean removeBatchRelations(PmsAttrAttrgroupRelation[] relations) {
        return this.getBaseMapper().deleteBatchRelations(relations);
    }

    @Override
    public boolean saveBatchRelations(List<PmsAttrAttrgroupRelation> relations) {
        return this.saveBatch(relations);
    }
}
