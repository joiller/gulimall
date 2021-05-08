package com.joiller.gulimall.product.service;

import com.joiller.gulimall.product.entity.PmsSpuImages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu图片 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsSpuImagesService extends IService<PmsSpuImages> {

    boolean save(Long id, List<String> images);
}
