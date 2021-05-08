package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joiller.gulimall.product.entity.PmsBrand;
import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.entity.PmsCategoryBrandRelation;
import com.joiller.gulimall.product.mapper.PmsBrandMapper;
import com.joiller.gulimall.product.mapper.PmsCategoryBrandRelationMapper;
import com.joiller.gulimall.product.mapper.PmsCategoryMapper;
import com.joiller.gulimall.product.service.IPmsCategoryBrandRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌分类关联 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsCategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationMapper, PmsCategoryBrandRelation> implements IPmsCategoryBrandRelationService {
    @Autowired
    PmsCategoryMapper categoryMapper;

    @Autowired
    PmsBrandMapper brandMapper;

    @Override
    public Boolean saveDetail(PmsCategoryBrandRelation categoryBrandRelation) {
        PmsBrand pmsBrand = brandMapper.selectById(categoryBrandRelation.getBrandId());
        PmsCategory pmsCategory = categoryMapper.selectById(categoryBrandRelation.getCatelogId());

        if (pmsBrand == null || pmsCategory == null) {
            return false;
        }
        categoryBrandRelation.setBrandName(pmsBrand.getName());
        categoryBrandRelation.setCatelogName(pmsCategory.getName());
        return this.save(categoryBrandRelation);
    }

    @Override
    public boolean updateCategory(PmsCategory category) {
        return this.getBaseMapper().updateCategory(category);
    }

    @Override
    public List<PmsCategoryBrandRelation> listByCatId(Long catId) {
        return this.list(new QueryWrapper<PmsCategoryBrandRelation>().eq("catelog_id", catId));
    }
}
