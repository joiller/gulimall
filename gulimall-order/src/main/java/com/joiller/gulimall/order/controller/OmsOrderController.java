package com.joiller.gulimall.order.controller;


import com.joiller.gulimall.order.service.IOmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.joiller.gulimall.common.utils.R;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RefreshScope
@RestController
@RequestMapping("/order/oms-order")
public class OmsOrderController {
    @Autowired
    private IOmsOrderService omsOrderService;

    @Value("${personal.user}")
    private String user;

    @Value("${personal.age}")
    private Integer age;

    @Value("${order.test.group}")
    private String group;

    @GetMapping("list")
    public R lista(){
        return omsOrderService.lista();
    }

    @GetMapping("property")
    public R prop(){
        return R.ok().put("user",user).put("age",age).put("group",group);
    }

}

