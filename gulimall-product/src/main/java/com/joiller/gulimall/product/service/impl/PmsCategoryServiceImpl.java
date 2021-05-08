package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.mapper.PmsCategoryMapper;
import com.joiller.gulimall.product.service.IPmsCategoryBrandRelationService;
import com.joiller.gulimall.product.service.IPmsCategoryService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {
    @Autowired
    IPmsCategoryBrandRelationService relationService;

    @Override
    public List<PmsCategory> listAsTree() {
        List<PmsCategory> all = list();
        List<PmsCategory> collect = all.stream()
                .filter(pmsCategory -> pmsCategory.getParentCid() == 0)
                .map(pmsCategory -> {
                    setChildren(pmsCategory, all);
                    return pmsCategory;
                })
                .sorted(Comparator.comparingInt(PmsCategory::getSort))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Serializable> getPath(Serializable id) {
        return getPath(id, new LinkedList<>());
    }

    @Override
    public boolean updateDetail(PmsCategory category, UpdateWrapper<PmsCategory> updateWrapper) {
        this.update(category, updateWrapper);
        if (!StringUtils.isNullOrEmpty(category.getName())){
            return relationService.updateCategory(category);
        }
        return true;
    }

    private List<Serializable> getPath(Serializable id, LinkedList<Serializable> path){
        path.addFirst(id);
        PmsCategory temp = this.getById(id);
        if (temp.getParentCid()==0){
            return path;
        }
        return getPath(temp.getParentCid(), path);
    }

    public List<PmsCategory> setChildren(PmsCategory temp, List<PmsCategory> all){
        List<PmsCategory> collect = all.stream()
                .filter(pmsCategory -> pmsCategory.getParentCid().equals(temp.getCatId()))
                .peek(pmsCategory -> setChildren(pmsCategory, all))
                .sorted(Comparator.comparingInt(PmsCategory::getSort))
                .collect(Collectors.toList());
        temp.setChildren(collect);
        return collect;
    }
}
