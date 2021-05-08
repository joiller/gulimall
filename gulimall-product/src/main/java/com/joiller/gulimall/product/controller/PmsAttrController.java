package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.product.entity.PmsProductAttrValue;
import com.joiller.gulimall.product.service.IPmsAttrService;
import com.joiller.gulimall.product.service.IPmsProductAttrValueService;
import com.joiller.gulimall.product.vo.AttrRespVo;
import com.joiller.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/attr")
public class PmsAttrController {

    @Autowired
    IPmsAttrService attrService;

    @Autowired
    IPmsProductAttrValueService productAttrValueService;

    @PostMapping("update/{spuId}")
    public R updateBySpuId(@PathVariable("spuId") Long spuId, @RequestBody List<PmsProductAttrValue> productAttrValues) {
        boolean b = productAttrValueService.updateForSpu(spuId, productAttrValues);
        return R.ok().put("updated", b);
    }

    @GetMapping("base/listforspu/{spuId}")
    public R listForSpu(@PathVariable("spuId") Long spuId) {
        List<PmsProductAttrValue> list = productAttrValueService.listForSpu(spuId);
        return R.ok().put("data", list);
    }

    @GetMapping("{attrType}/list/{catId}")
    public R list(@RequestParam Map<String, String> params, @PathVariable("catId") Long catId, @PathVariable String attrType) throws Exception {
        Page<AttrRespVo> attrRespVoPage = attrService.basePage(params, catId, attrType);
        return R.ok().put("page", attrRespVoPage);
    }

    @GetMapping("info/{attrId}")
    public R info(@PathVariable("attrId") Integer attrId){
        AttrRespVo attrRespVo = attrService.infoById(attrId);
        return R.ok().put("attr", attrRespVo);
    }

    @PostMapping("save")
    public R save(@RequestBody AttrVo attrVo){
        boolean save = attrService.save(attrVo);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody AttrVo attrVo) {
        boolean update = attrService.update(attrVo);
        return R.ok().put("update", update);
    }
}

