package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.product.entity.PmsCategoryBrandRelation;
import com.joiller.gulimall.product.service.IPmsCategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌分类关联 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/categorybrandrelation")
public class PmsCategoryBrandRelationController {
    @Autowired
    private IPmsCategoryBrandRelationService categoryBrandRelationService;

    @GetMapping("brands/list")
    public R brandsList(@RequestParam("catId") Long catId){
        List<PmsCategoryBrandRelation> relations = categoryBrandRelationService.listByCatId(catId);
        return R.ok().put("data", relations);
    }

    @GetMapping("/catelog/list/{brandId}")
    public R catelogList(@PathVariable("brandId") Integer brandId){
        List<PmsCategoryBrandRelation> relations = categoryBrandRelationService.list(new QueryWrapper<PmsCategoryBrandRelation>().eq("brand_id", brandId));
        return R.ok().put("list", relations);
    }

    @PostMapping("saveDetail")
    public R saveDetail(@RequestBody PmsCategoryBrandRelation categoryBrandRelation) {
        Boolean aBoolean = categoryBrandRelationService.saveDetail(categoryBrandRelation);
        if (aBoolean) {
            return R.ok();
        }
        return R.error();
    }

    @DeleteMapping("delete")
    public R delete(@RequestBody List<Integer> ids){
        boolean b = categoryBrandRelationService.removeByIds(ids);
        return b?R.ok():R.error();
    }
}

