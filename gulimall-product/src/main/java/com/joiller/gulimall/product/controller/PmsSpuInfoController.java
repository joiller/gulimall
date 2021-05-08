package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.product.entity.PmsSpuInfo;
import com.joiller.gulimall.product.service.IPmsSpuInfoService;
import com.joiller.gulimall.product.vo.SpuSaveVo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * spu信息 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/spuinfo")
public class PmsSpuInfoController {
    @Autowired
    IPmsSpuInfoService spuInfoService;
    @PostMapping("save")
    public R save(@RequestBody SpuSaveVo spuSaveVo) {
        boolean save = spuInfoService.saveSpuSaveVo(spuSaveVo);
        return R.ok();
    }

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> params){
        Page<PmsSpuInfo> page = new PageUtils<PmsSpuInfo>().page(params);
        QueryWrapper<PmsSpuInfo> queryWrapper = new QueryWrapper<>();
        String key = params.get("key");
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and(w -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String brandId = params.get("brandId");
        String catelogId = params.get("catelogId");
        String status = params.get("status");
        if (!StringUtils.isNullOrEmpty(catelogId)) {
            queryWrapper.eq("catalog_id", catelogId);
        }
        if (!StringUtils.isNullOrEmpty(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isNullOrEmpty(status)) {
            queryWrapper.eq("publish_status", status);
        }

        Page<PmsSpuInfo> page1 = spuInfoService.page(page, queryWrapper);
        return R.ok().put("page", page1);
    }
}

