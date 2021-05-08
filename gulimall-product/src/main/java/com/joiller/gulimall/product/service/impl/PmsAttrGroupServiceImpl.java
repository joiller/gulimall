package com.joiller.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joiller.gulimall.product.entity.PmsAttr;
import com.joiller.gulimall.product.entity.PmsAttrGroup;
import com.joiller.gulimall.product.mapper.PmsAttrGroupMapper;
import com.joiller.gulimall.product.service.IPmsAttrGroupService;
import com.joiller.gulimall.product.service.IPmsAttrService;
import com.joiller.gulimall.product.vo.AttrgroupWithAttrsVo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroup> implements IPmsAttrGroupService {

    @Autowired
    IPmsAttrService attrService;

    @Override
    public <E extends IPage<PmsAttrGroup>> E page(E page, String key, Integer catelogId) {
        QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(key)){
            System.out.println("key:"+key+"is null or empty?:"+ StringUtils.isNullOrEmpty(key));
            queryWrapper.and(wrapper -> wrapper.eq("attr_group_id", key).or().like("attr_group_name", key));
        }
        if (catelogId == null || catelogId == 0){
            return this.page(page, queryWrapper);

        }
        queryWrapper.eq("catelog_id", catelogId);
        return this.page(page, queryWrapper);

    }

    @Override
    public List<AttrgroupWithAttrsVo> getAttrGroupsWithAttrs(Long catelogId) {
        List<PmsAttrGroup> attrGroups = this.list(new QueryWrapper<PmsAttrGroup>().eq("catelog_id", catelogId));

        List<AttrgroupWithAttrsVo> collect = attrGroups.stream().map(pmsAttrGroup -> {
            AttrgroupWithAttrsVo attrgroupWithAttrsVo = new AttrgroupWithAttrsVo();
            BeanUtils.copyProperties(pmsAttrGroup, attrgroupWithAttrsVo);
            List<PmsAttr> attrs = attrService.getByRelation(pmsAttrGroup.getAttrGroupId());
            attrgroupWithAttrsVo.setAttrs(attrs);
            return attrgroupWithAttrsVo;
        }).collect(Collectors.toList());
        return collect;
    }

}
