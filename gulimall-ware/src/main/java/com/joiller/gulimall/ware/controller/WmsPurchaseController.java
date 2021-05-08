package com.joiller.gulimall.ware.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.ware.entity.WmsPurchase;
import com.joiller.gulimall.ware.service.IWmsPurchaseService;
import com.joiller.gulimall.ware.vo.MergeVo;
import com.joiller.gulimall.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购信息 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/ware/purchase")
public class WmsPurchaseController {
    @Autowired
    IWmsPurchaseService purchaseService;

    @PostMapping("done")
    public R done(@RequestBody @Valid PurchaseDoneVo purchaseDoneVo) {
        boolean done = purchaseService.done(purchaseDoneVo);
        return R.ok().put("done", done);
    }

    @PostMapping("receive")
    public R receive(@RequestBody List<Long> ids) {
        boolean received = purchaseService.receive(ids);
        return R.ok().put("received", received);
    }

    @PostMapping("merge")
    public R merge(@RequestBody MergeVo mergeVo) {
        boolean merged = purchaseService.merge(mergeVo);
        return R.ok().put("merged", merged);
    }

    @PutMapping("update")
    public R update(@RequestBody WmsPurchase purchase) {
        boolean updated = purchaseService.updateById(purchase);
        return R.ok().put("updated", updated);
    }


    @GetMapping("unreceive/list")
    public R unreceiveList(@RequestParam Map<String, String> map) {
        Page<WmsPurchase> purchasePage = new PageUtils<WmsPurchase>().page(map);
        Page<WmsPurchase> page = purchaseService.pageUnreceive(purchasePage, map);
        return R.ok().put("page", page);
    }

//    @GetMapping("list")
//    public R list(@RequestParam Map<String, String> map) {
//        Page<WmsPurchase> purchasePage = new PageUtils<WmsPurchase>().page(map);
//        Page<WmsPurchase> page = purchaseService.page(purchasePage, map);
//        return R.ok().put("page", page);
//    }

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> map) {
        Page<WmsPurchase> page = purchaseService.page(map);
        return R.ok().put("page", page);
    }

    @PostMapping("save")
    public R save(@RequestBody WmsPurchase purchase) {
        boolean saved = purchaseService.save(purchase);
        return R.ok().put("saved", saved);
    }
}

