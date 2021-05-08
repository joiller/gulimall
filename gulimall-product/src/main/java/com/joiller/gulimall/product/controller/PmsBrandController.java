package com.joiller.gulimall.product.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.R;
import com.joiller.gulimall.common.valid.AddGroup;
import com.joiller.gulimall.common.valid.UpdateGroup;
import com.joiller.gulimall.product.entity.PmsBrand;
import com.joiller.gulimall.product.service.IPmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product/brand")
@Validated
public class PmsBrandController {
    @Autowired
    private IPmsBrandService brandService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, String> params) {
//        Page<PmsBrand> page = new PageUtils<PmsBrand>().page(params);
//        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
//        if (!StringUtils.isNullOrEmpty(params.get("key"))){
//            wrapper.and(obj -> obj.eq("brand_id", params.get("key")).or().like("name", params.get("key")));
//        }
//        Page<PmsBrand> page1 = brandService.page(page, wrapper);
////        Page<PmsBrand> page = brandService.page(new Page<PmsBrand>(Long.parseLong((String) params.get("current")), Long.parseLong((String) params.get("size"))));
//        return R.ok().put("page", page1);
//    }

    @GetMapping("list")
    public R list2(@RequestParam Map<String, String> params) {
         Page<PmsBrand> page = brandService.page(params);
         return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId) {
        PmsBrand brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody PmsBrand brand) {
        brandService.save(brand);

        return R.ok();
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ModelAndView handleMissingServletRequestParameter(MethodArgumentNotValidException
//                                                                     ex, HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) throws IOException {
//        System.out.println("msg"+ex.getMessage());
//        response.sendError(400, ex.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("msg", ex.getMessage());
//        return modelAndView;
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map arugumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) throws IOException {
//        LinkedHashMap<String, Object> error = new LinkedHashMap<>();
//        error.put("msg", ex.getMessage());
//        error.put("message", ex.getMessage());
//        response.sendError(400);
//        return error;
//    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody PmsBrand brand) {
        System.out.println(brand);
        brandService.updateDetail(brand);

        return R.ok();
    }

    @PostMapping("/updateShowStatus")
    public R updateById(@Validated(UpdateGroup.class) @RequestBody PmsBrand brand) {
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.clear();
        updateWrapper.eq("brand_id", brand.getBrandId()).set("show_status", brand.getShowStatus());
        boolean update = brandService.update(updateWrapper);
        return R.ok().put("update", update);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }
}

