package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.joiller.gulimall.product.entity.PmsCategory;
import com.joiller.gulimall.product.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joiller.gulimall.common.utils.R;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/category")
public class PmsCategoryController {
    @Autowired
    private IPmsCategoryService pmsCategoryService;

    @GetMapping("list")
    public R list() {
        List<PmsCategory> list = pmsCategoryService.list();
        return R.ok().put("categories", list);
    }

    @GetMapping("list/tree")
    public R listAsTree() {
        List<PmsCategory> pmsCategories = pmsCategoryService.listAsTree();
        return R.ok().put("categories", pmsCategories);
    }

    @PostMapping("create")
    public R createById(@RequestBody PmsCategory category){
        // 检查是否已存在
        PmsCategory one = pmsCategoryService.getOne(new QueryWrapper<>(category, "name"));
        if (null==one){
            System.out.println("category = " + category);
            boolean save = pmsCategoryService.save(category);
            pmsCategoryService.saveOrUpdate(category);
            return R.ok().put("created", save);
        }
        return R.error(400, "existed");
    }

    @Transactional
    @PutMapping("update")
    public R update(@RequestBody PmsCategory category){
        // 是否存在其他同名的数据
        QueryWrapper<PmsCategory> queryWrapper = new QueryWrapper<>(category);
        queryWrapper.clear();
        queryWrapper.eq("name", category.getName())
                .ne("cat_id", category.getCatId());
        System.out.println("here one ******");
        PmsCategory one = pmsCategoryService.getOne(queryWrapper);
        System.out.println("here two ******");

        if (null!=one){
            return R.error(400, "existed");
        }
        queryWrapper.clear();
        UpdateWrapper<PmsCategory> updateWrapper = new UpdateWrapper<>(category);
        updateWrapper.clear();
        updateWrapper.eq("cat_id", category.getCatId());
        pmsCategoryService.updateDetail(category, updateWrapper);
        return R.ok().put("updated", true);
    }

    @PutMapping("updateBatch")
    public R updateList(@RequestBody List<PmsCategory> pmsCategories){
        boolean b = pmsCategoryService.updateBatchById(pmsCategories);
        System.out.println("updateBatch:" + b);
        return R.ok().put("updateBatch", b);
    }

    @DeleteMapping("delete")
    public R deleteByIds(@RequestBody List<Integer> ids) {
        boolean b = pmsCategoryService.removeByIds(ids);
        return R.ok();
    }

}

