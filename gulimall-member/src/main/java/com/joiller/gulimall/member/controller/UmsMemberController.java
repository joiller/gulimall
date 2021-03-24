package com.joiller.gulimall.member.controller;


import com.joiller.gulimall.member.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/member/ums-member")
public class UmsMemberController {
    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping("/order/list")
    public R orderList(){
        return orderFeignService.list();
    }
}

