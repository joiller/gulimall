package com.joiller.gulimall.ware.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.ware.entity.WmsPurchaseDetail;
import com.joiller.gulimall.ware.service.IWmsPurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/ware/purchasedetail")
public class WmsPurchaseDetailController {

    @Autowired
    IWmsPurchaseDetailService purchaseDetailService;

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> map) {
        Page<WmsPurchaseDetail> purchaseDetailPage = new PageUtils<WmsPurchaseDetail>().page(map);
        Page<WmsPurchaseDetail> page = purchaseDetailService.page(purchaseDetailPage, map);
        return R.ok().put("page", page);
    }

    @PostMapping("save")
    public R save(@RequestBody WmsPurchaseDetail purchaseDetail) {
        boolean saved = purchaseDetailService.save(purchaseDetail);
        return R.ok().put("saved", saved);
    }
}

