package com.joiller.gulimall.product.service;

import com.joiller.gulimall.product.entity.PmsSpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.product.vo.SpuSaveVo;

/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsSpuInfoService extends IService<PmsSpuInfo> {

    boolean saveSpuSaveVo(SpuSaveVo spuSaveVo);
}
