package com.joiller.gulimall.product.service;

import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.entity.PmsCategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌分类关联 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsCategoryBrandRelationService extends IService<PmsCategoryBrandRelation> {

    Boolean saveDetail(PmsCategoryBrandRelation categoryBrandRelation);

    boolean updateCategory(PmsCategory category);

    List<PmsCategoryBrandRelation> listByCatId(Long catId);
}
