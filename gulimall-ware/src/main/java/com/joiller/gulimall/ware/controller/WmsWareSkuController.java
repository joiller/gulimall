package com.joiller.gulimall.ware.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.ware.entity.WmsWareSku;
import com.joiller.gulimall.ware.service.IWmsWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 商品库存 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/ware/waresku")
public class WmsWareSkuController {
    @Autowired
    IWmsWareSkuService wareSkuService;

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> map) {
        Page<WmsWareSku> wareSkuPage = new PageUtils<WmsWareSku>().page(map);
        Page<WmsWareSku> page = wareSkuService.page(wareSkuPage, map);
        return R.ok().put("page", page);
    }

    @PostMapping("save")
    public R save(@RequestBody WmsWareSku wareSku) {
        boolean saved = wareSkuService.save(wareSku);
        return R.ok().put("saved", saved);
    }
}

