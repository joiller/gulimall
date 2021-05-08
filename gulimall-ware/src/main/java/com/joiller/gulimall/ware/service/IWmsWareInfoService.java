package com.joiller.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.ware.entity.WmsWareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IWmsWareInfoService extends IService<WmsWareInfo> {
    Page<WmsWareInfo> page(Page<WmsWareInfo> page, Map<String, String> map);
}
