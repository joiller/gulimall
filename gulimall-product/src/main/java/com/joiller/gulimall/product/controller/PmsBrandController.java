package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.product.entity.PmsBrand;
import com.joiller.gulimall.product.service.IPmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/pms-brand")
public class PmsBrandController {
    @Autowired
    private IPmsBrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Page<PmsBrand> page = brandService.page(new Page<PmsBrand>(Long.parseLong((String) params.get("current")), Long.parseLong((String) params.get("size"))));
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
        PmsBrand brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PmsBrand brand){
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PmsBrand brand){
        System.out.println(brand);
        brandService.updateById(brand);

        return R.ok();
    }

    @PostMapping("/updateShowStatus")
    public R updateById(@RequestBody PmsBrand brand){
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.clear();
        updateWrapper.eq("brand_id", brand.getBrandId()).set("show_status", brand.getShowStatus());
        boolean update = brandService.update(updateWrapper);
        return R.ok().put("update", update);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }
}

