package com.joiller.gulimall.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * @author jianghuilai
 * @since 2021-04-10 09:48
 **/

public class PageUtils<T> {

    /**
     * this.records = Collections.emptyList();
     * this.total = 0L;
     * this.size = 10L;
     * this.current = 1L;
     * this.orders = new ArrayList();
     * this.optimizeCountSql = true;
     * this.isSearchCount = true;
     * this.hitCount = false;
     */

    long total = 0L;
    long current = 1L;
    long size = 10L;
    boolean isSearchCount = true;

//    static Page<?> page(Class<?> c, Map<String, String> params){
//        Method method = null;
//        try {
//            method = c.getMethod("page", c);
//            Page<?> p = getPage(c, params);
//            QueryWrapper<?> queryWrapper = new QueryWrapper<>();
//            if (!StringUtils.isNullOrEmpty(params.get("key"))) {
//                queryWrapper.eq("")
//            }
////            page(page, wrapper)
//            method.invoke(c.newInstance(), )
//        } catch (NoSuchMethodException e) {
//            return null;
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
    
//    static Page<?> getPage(Class<?> c, Map<String, String> params){
//        Long current = null,
//                size=null,
//                total=null;
//        Boolean isSearchCount = null;
//        if (params.get("current") != null) {
//            current = Long.parseLong(params.get("current"));
//        }
//        if (params.get("size") != null) {
//            size = Long.parseLong(params.get("size"));
//        }
//        if (params.get("total") != null) {
//            total = Long.parseLong(params.get("total"));
//        }
//        if (params.get("isSearchCount") != null) {
//            isSearchCount = Boolean.parseBoolean(params.get("isSearchCount"));
//        }
//        return new Page<>(current, size, total, isSearchCount);
//    }

    public Page<T> page(Map<String, String> params) {
        if (params.get("current") != null) {
            current = Long.parseLong(params.get("current"));
        }
        if (params.get("size") != null) {
            size = Long.parseLong(params.get("size"));
        }
        if (params.get("total") != null) {
            total = Long.parseLong(params.get("total"));
        }
        if (params.get("isSearchCount") != null) {
            isSearchCount = Boolean.parseBoolean(params.get("isSearchCount"));
        }
        return new Page<>(current, size, total, isSearchCount);
    }

    public Page<T> from(Page<?> page){
        Page<T> tPage = new Page<>();
        BeanUtils.copyProperties(page, tPage, "records");
        return tPage;
    }
}
