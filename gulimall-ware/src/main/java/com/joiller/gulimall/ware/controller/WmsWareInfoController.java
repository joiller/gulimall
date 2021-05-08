package com.joiller.gulimall.ware.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.ware.entity.WmsWareInfo;
import com.joiller.gulimall.ware.service.IWmsWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 仓库信息 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/ware/wareinfo")
public class WmsWareInfoController {

    @Autowired
    IWmsWareInfoService wareInfoService;

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> map) {
        Page<WmsWareInfo> wareInfoPage = new PageUtils<WmsWareInfo>().page(map);
        Page<WmsWareInfo> page = wareInfoService.page(wareInfoPage, map);
        return R.ok().put("page", page);
    }
}

