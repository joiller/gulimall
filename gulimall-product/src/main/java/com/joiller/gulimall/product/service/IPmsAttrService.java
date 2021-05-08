package com.joiller.gulimall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.product.entity.PmsAttr;
import com.joiller.gulimall.product.vo.AttrRespVo;
import com.joiller.gulimall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IPmsAttrService extends IService<PmsAttr> {

    Page<AttrRespVo> page(Map<String, String> params, Long catId);

    boolean save(AttrVo attrVo);

    AttrRespVo infoById(Integer attrId);

    Page<AttrRespVo> basePage(Map<String, String> params, Long catId, String attrType) throws Exception;

    boolean update(AttrVo attrVo);

    List<PmsAttr> getByRelation(Long attrGroupId);

    Page<PmsAttr> getByNoRelation(Long attrGroupId, Map<String, String> params);
}
