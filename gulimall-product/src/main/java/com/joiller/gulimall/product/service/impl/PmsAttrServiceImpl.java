package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.common.constant.ProductConstant;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.product.entity.PmsAttr;
import com.joiller.gulimall.product.entity.PmsAttrAttrgroupRelation;
import com.joiller.gulimall.product.entity.PmsAttrGroup;
import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.mapper.PmsAttrMapper;
import com.joiller.gulimall.product.service.IPmsAttrAttrgroupRelationService;
import com.joiller.gulimall.product.service.IPmsAttrGroupService;
import com.joiller.gulimall.product.service.IPmsAttrService;
import com.joiller.gulimall.product.service.IPmsCategoryService;
import com.joiller.gulimall.product.vo.AttrRespVo;
import com.joiller.gulimall.product.vo.AttrVo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements IPmsAttrService {
    @Autowired
    IPmsCategoryService categoryService;

    @Autowired
    IPmsAttrAttrgroupRelationService relationService;

    @Autowired
    IPmsAttrGroupService attrGroupService;

    @Override
    public Page<AttrRespVo> page(Map<String, String> params, Long catId){
        QueryWrapper<PmsAttr> wrapper = new QueryWrapper<>();
        if (catId != 0) {
            wrapper.eq("catelog_id", catId);
        }
        String key = params.get("key");
        if (!StringUtils.isNullOrEmpty(key)) {
            wrapper.and(queryWrapper -> queryWrapper.eq("attr_id", key).or().like("attr_name", key));
        }
        Page<PmsAttr> page = new PageUtils<PmsAttr>().page(params);
        Page<PmsAttr> page1 = this.page(page, wrapper);

        List<AttrRespVo> attrRespVos = page1.getRecords().stream().map(pmsAttr -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(pmsAttr, attrRespVo);
            PmsCategory category = categoryService.getById(pmsAttr.getCatelogId());
            PmsAttrAttrgroupRelation relation = relationService.getOne(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("attr_id", pmsAttr.getAttrId()));
            if (relation != null) {
                PmsAttrGroup attrGroup = attrGroupService.getById(relation.getAttrGroupId());
                if (attrGroup != null) {
                    attrRespVo.setAttrGroupName(attrGroup.getAttrGroupName());
                }
            }
            if (category != null) {
                attrRespVo.setCatelogName(category.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        Page<AttrRespVo> page2 = new PageUtils<AttrRespVo>().page(params);
        page2.setRecords(attrRespVos);
        return page2;
    }

    @Override
    public boolean save(AttrVo attrVo) {
        PmsAttr attr = new PmsAttr();
        BeanUtils.copyProperties(attrVo, attr);
        this.save(attr);
        if (attr.getAttrType().equals(ProductConstant.AttrEnum.BASE_ATTR.getCode())){
            // base 才有 分组， 才需要添加 relation
            relationService.saveOrUpdateByAttr(attr.getAttrId(), attrVo.getAttrGroupId());
        }

        return true;
    }

    @Override
    public AttrRespVo infoById(Integer attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        PmsAttr attr = this.getById(attrId);
        if (attr == null) {
            return null;
        }

        BeanUtils.copyProperties(attr, attrRespVo);

        List<Serializable> path = categoryService.getPath(attr.getCatelogId());

        PmsAttrAttrgroupRelation relation = relationService.getOne(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("attr_id", attrId));
        if (relation != null) {
            attrRespVo.setAttrGroupId(relation.getAttrGroupId());
        }
        attrRespVo.setCatelogPath(path);
        return attrRespVo;
    }

    @Override
    public Page<AttrRespVo> basePage(Map<String, String> params, Long catId, String attrType) throws Exception {

        Page<PmsAttr> page = new PageUtils<PmsAttr>().page(params);
        QueryWrapper<PmsAttr> wrapper = new QueryWrapper<>();
        if (catId != 0) {
            wrapper.eq("catelog_id", catId);
        }
        String key = params.get("key");
        if (!StringUtils.isNullOrEmpty(key)){
            wrapper.and(queryWrapper -> queryWrapper.eq("attr_id", key).or().like("attr_name", key));
        }

        if (!"base".equalsIgnoreCase(attrType) && !"sale".equalsIgnoreCase(attrType)) {
            throw new Exception("Not Found");
        }
        wrapper.eq("attr_type", attrType.equalsIgnoreCase("base")?1:0);
        Page<PmsAttr> page1 = this.page(page, wrapper);
        List<AttrRespVo> attrRespVos = page1.getRecords().stream().map(pmsAttr -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(pmsAttr, attrRespVo);
//            get attr group name
            PmsAttrAttrgroupRelation relation = relationService.getOne(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("attr_id", pmsAttr.getAttrId()));
            if (relation != null) {
                PmsAttrGroup attrGroup = attrGroupService.getById(relation.getAttrGroupId());
                if (attrGroup != null) {
                    attrRespVo.setAttrGroupName(attrGroup.getAttrGroupName());
                }
            }

//            get catelog name
            PmsCategory category = categoryService.getById(pmsAttr.getCatelogId());
            if (category != null) {
                attrRespVo.setCatelogName(category.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        Page<AttrRespVo> page2 = new PageUtils<AttrRespVo>().from(page1);
        page2.setRecords(attrRespVos);
        return page2;
    }

    @Override
    public boolean update(AttrVo attrVo) {
        PmsAttr attr = new PmsAttr();
        BeanUtils.copyProperties(attrVo, attr);
        boolean b = this.saveOrUpdate(attr);
        if (attrVo.getAttrGroupId() != null) {
            PmsAttrAttrgroupRelation relation =  null;
            relation  = relationService.getOne(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("attr_id", attr.getAttrId()));
            if (relation != null){
                relation.setAttrGroupId(attrVo.getAttrGroupId());
                return relationService.updateById(relation);
            }
            relation = new PmsAttrAttrgroupRelation();
            relation.setAttrSort(0);
            relation.setAttrId(attr.getAttrId());
            relation.setAttrGroupId(attrVo.getAttrGroupId());
            return relationService.save(relation);
        }
        return b;
    }

    @Override
    public List<PmsAttr> getByRelation(Long attrGroupId) {
        List<Long> attrIds = relationService.listAttrIdsByGroupId(attrGroupId);
        if (attrIds.isEmpty()) {
            return Collections.emptyList();
        }
        return this.list(new QueryWrapper<PmsAttr>().in("attr_id", attrIds));
    }

    @Override
    public Page<PmsAttr> getByNoRelation(Long attrGroupId, Map<String, String> params) {
//        获取可以与当前分组进行关联的属性
//        与当前分组属于同一类下，并且没有与任何分组进行关联

        PmsAttrGroup attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();

        List<PmsAttrGroup> attrGroups = attrGroupService.list(new QueryWrapper<PmsAttrGroup>().eq("catelog_id", catelogId));
        List<Long> attrGroupIds = attrGroups.stream().map(PmsAttrGroup::getAttrGroupId).collect(Collectors.toList());

        List<Long> excludedAttrs = Collections.emptyList();
        if (!attrGroupIds.isEmpty()){
            List<PmsAttrAttrgroupRelation> excludedRelations = relationService.list(new QueryWrapper<PmsAttrAttrgroupRelation>().in("attr_group_id", attrGroupIds));
            excludedAttrs = excludedRelations.stream().map(PmsAttrAttrgroupRelation::getAttrId).collect(Collectors.toList());
        }
        Page<PmsAttr> page = new PageUtils<PmsAttr>().page(params);
        return this.page(page, new QueryWrapper<PmsAttr>().notIn("attr_id", excludedAttrs).eq("attr_type", ProductConstant.AttrEnum.BASE_ATTR.getCode()));
    }
}
