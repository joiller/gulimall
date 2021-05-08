package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.product.entity.PmsBrand;
import com.joiller.gulimall.product.entity.PmsCategoryBrandRelation;
import com.joiller.gulimall.product.mapper.PmsBrandMapper;
import com.joiller.gulimall.product.service.IPmsBrandService;
import com.joiller.gulimall.product.service.IPmsCategoryBrandRelationService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 品牌 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {
    @Autowired
    IPmsCategoryBrandRelationService categoryBrandRelationService;

    @Override
    public boolean updateDetail(PmsBrand brand) {
        this.updateById(brand);
        if (!StringUtils.isNullOrEmpty(brand.getName())){
            PmsCategoryBrandRelation relation = new PmsCategoryBrandRelation();
            relation.setBrandName(brand.getName());
            return categoryBrandRelationService.update(relation, new UpdateWrapper<PmsCategoryBrandRelation>().eq("brand_id", brand.getBrandId()));
        }
        return true;
    }

    @Override
    public Page<PmsBrand> page(Map<String, String> params) {
        Page<PmsBrand> page = new PageUtils<PmsBrand>().page(params);
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(params.get("key"))){
            wrapper.and(obj -> obj.eq("brand_id", params.get("key")).or().like("name", params.get("key")));
        }
        return this.page(page, wrapper);

    }
}
