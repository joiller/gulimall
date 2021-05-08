package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.product.entity.PmsAttr;
import com.joiller.gulimall.product.entity.PmsProductAttrValue;
import com.joiller.gulimall.product.mapper.PmsProductAttrValueMapper;
import com.joiller.gulimall.product.service.IPmsAttrService;
import com.joiller.gulimall.product.service.IPmsProductAttrValueService;
import com.joiller.gulimall.product.vo.BaseAttrs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueMapper, PmsProductAttrValue> implements IPmsProductAttrValueService {
    @Autowired
    IPmsAttrService attrService;

    @Override
    public boolean save(Long id, List<BaseAttrs> baseAttrs) {
        List<PmsProductAttrValue> collect = baseAttrs.stream().map(baseAttr -> {
            PmsProductAttrValue attrValue = new PmsProductAttrValue();
            BeanUtils.copyProperties(baseAttr, attrValue);
            attrValue.setAttrValue(baseAttr.getAttrValues());
            attrValue.setSpuId(id);
            PmsAttr attr = attrService.getById(attrValue.getAttrId());
            attrValue.setAttrName(attr.getAttrName());
            return attrValue;
        }).collect(Collectors.toList());
        return this.saveBatch(collect);
    }

    @Override
    public List<PmsProductAttrValue> listForSpu(Long spuId) {
        QueryWrapper<PmsProductAttrValue> pmsProductAttrValueQueryWrapper = new QueryWrapper<>();
        pmsProductAttrValueQueryWrapper.eq("spu_id", spuId);
        return this.list(pmsProductAttrValueQueryWrapper);

    }

    @Transactional
    @Override
    public boolean updateForSpu(Long spuId, List<PmsProductAttrValue> productAttrValues) {
//        productAttrValues.stream().peek(pmsProductAttrValue -> {
//            UpdateWrapper<PmsProductAttrValue> productAttrValueUpdateWrapper = new UpdateWrapper<>();
//            pmsProductAttrValue.setSpuId(spuId);
//            productAttrValueUpdateWrapper.eq("spu_id", spuId);
//            productAttrValueUpdateWrapper.eq("attr_id", pmsProductAttrValue.getAttrId());
//            this.update(pmsProductAttrValue, productAttrValueUpdateWrapper);
//        });
//        return true;

        // 先把这个spu的属性全部删除 然后再全部添加
        this.remove(new QueryWrapper<PmsProductAttrValue>().eq("spu_id", spuId));

        List<PmsProductAttrValue> collect = productAttrValues.stream().peek(p -> {
            p.setSpuId(spuId);
        }).collect(Collectors.toList());
        return this.saveBatch(collect);
    }
}
