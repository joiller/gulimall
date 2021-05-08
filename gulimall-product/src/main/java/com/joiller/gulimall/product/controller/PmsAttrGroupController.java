package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.product.entity.PmsAttr;
import com.joiller.gulimall.product.entity.PmsAttrAttrgroupRelation;
import com.joiller.gulimall.product.entity.PmsAttrGroup;
import com.joiller.gulimall.product.service.IPmsAttrAttrgroupRelationService;
import com.joiller.gulimall.product.service.IPmsAttrGroupService;
import com.joiller.gulimall.product.service.IPmsAttrService;
import com.joiller.gulimall.product.service.IPmsCategoryService;
import com.joiller.gulimall.product.vo.AttrgroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/attrgroup")
public class PmsAttrGroupController {
    @Autowired
    private IPmsAttrGroupService pmsAttrGroupService;

    @Autowired
    private IPmsCategoryService pmsCategoryService;

    @Autowired
    private IPmsAttrService attrService;

    @Autowired
    private IPmsAttrAttrgroupRelationService relationService;

//    /product/attrgroup/{catId}/withattr

    @GetMapping("{catelogId}/withattr")
    public R withattr(@PathVariable("catelogId") Long catelogId) {
        List<AttrgroupWithAttrsVo> attrgroupWithAttrsVos = pmsAttrGroupService.getAttrGroupsWithAttrs(catelogId);
        return R.ok().put("data", attrgroupWithAttrsVos);
    }

    @PostMapping("attr/relation")
    public R addBatchRelation(@RequestBody List<PmsAttrAttrgroupRelation> relations) {
        boolean saved = relationService.saveBatchRelations(relations);
        return R.ok().put("saved", saved);
    }

    @DeleteMapping("attr/relation/delete")
    public R deleteRelations(@RequestBody PmsAttrAttrgroupRelation[] relations) {
        boolean deleted = relationService.removeBatchRelations(relations);
        return R.ok().put("deleted", deleted);
    }

    @GetMapping("{attrGroupId}/noattr/relation")
    public R getAttrByNoRelation(@PathVariable("attrGroupId") Long attrGroupId,
                                 @RequestParam Map<String, String> params){
        Page<PmsAttr> attrs = attrService.getByNoRelation(attrGroupId, params);
        return R.ok().put("page", attrs);
    }

    @GetMapping("{attrGroupId}/attr/relation")
    public R getAttrByRelation(@PathVariable("attrGroupId") Long attrGroupId){
        List<PmsAttr> attrs = attrService.getByRelation(attrGroupId);
        return R.ok().put("data", attrs);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, String> params, @PathVariable("catelogId") Integer catelogId) throws MethodArgumentNotValidException {
        Page<PmsAttrGroup> page = new PageUtils<PmsAttrGroup>().page(params);
        Page<PmsAttrGroup> page1 = pmsAttrGroupService.page(page, params.get("key"), catelogId);
        return R.ok().put("page", page1);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
        PmsAttrGroup pmsAttrGroup = pmsAttrGroupService.getById(attrGroupId);

        List<Serializable> path = pmsCategoryService.getPath(pmsAttrGroup.getCatelogId());
        pmsAttrGroup.setCatelogPath(path);
        return R.ok().put("pmsAttrGroup", pmsAttrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PmsAttrGroup pmsAttrGroup){
        pmsAttrGroupService.save(pmsAttrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PmsAttrGroup pmsAttrGroup){
        pmsAttrGroupService.updateById(pmsAttrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
        pmsAttrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }


}

