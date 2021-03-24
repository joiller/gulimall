package com.joiller.gulimall.product.service.impl;

import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.mapper.PmsCategoryMapper;
import com.joiller.gulimall.product.service.IPmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
