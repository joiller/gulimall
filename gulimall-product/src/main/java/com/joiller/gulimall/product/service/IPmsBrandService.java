package com.joiller.gulimall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.product.entity.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 品牌 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsBrandService extends IService<PmsBrand> {

    boolean updateDetail(PmsBrand brand);

    Page<PmsBrand> page(Map<String, String> params);
}
