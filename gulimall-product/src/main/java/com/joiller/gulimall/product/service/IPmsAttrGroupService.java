package com.joiller.gulimall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.product.entity.PmsAttrGroup;
import com.joiller.gulimall.product.vo.AttrgroupWithAttrsVo;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsAttrGroupService extends IService<PmsAttrGroup> {
    public <E extends IPage<PmsAttrGroup>> E page(E page, String key, Integer catelogId);

    List<AttrgroupWithAttrsVo> getAttrGroupsWithAttrs(Long catelogId);
}
