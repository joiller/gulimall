package com.joiller.gulimall.member.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.member.entity.UmsMemberLevel;
import com.joiller.gulimall.member.service.IUmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 会员等级 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/member/memberlevel")
public class UmsMemberLevelController {
    @Autowired
    IUmsMemberLevelService memberLevelService;

    @GetMapping("list")
    public R list(@RequestParam Map<String, String> params){
        Page<UmsMemberLevel> memberLevels = memberLevelService.page(params);
        return R.ok().put("page", memberLevels);
    }

    @PostMapping("save")
    public R save(@RequestBody UmsMemberLevel memberLevel) {
        boolean save = memberLevelService.save(memberLevel);
        return R.ok().put("save", save);
    }
}

