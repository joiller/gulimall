package com.joiller.gulimall.ware.mapper;

import com.joiller.gulimall.ware.entity.WmsPurchaseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface WmsPurchaseDetailMapper extends BaseMapper<WmsPurchaseDetail> {

    List<WmsPurchaseDetail> listByPurchaseIds(@Param("purchase_ids") List<Long> ids);
}
