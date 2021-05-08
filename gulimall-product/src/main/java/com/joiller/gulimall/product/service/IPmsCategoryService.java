package com.joiller.gulimall.product.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.joiller.gulimall.product.entity.PmsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsCategoryService extends IService<PmsCategory> {
    public List<PmsCategory> listAsTree();

    List<Serializable> getPath(Serializable id);

    boolean updateDetail(PmsCategory category, UpdateWrapper<PmsCategory> updateWrapper);
}
