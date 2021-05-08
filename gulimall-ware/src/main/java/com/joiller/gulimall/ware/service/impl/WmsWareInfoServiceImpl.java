package com.joiller.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.ware.entity.WmsWareInfo;
import com.joiller.gulimall.ware.mapper.WmsWareInfoMapper;
import com.joiller.gulimall.ware.service.IWmsWareInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 仓库信息 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class WmsWareInfoServiceImpl extends ServiceImpl<WmsWareInfoMapper, WmsWareInfo> implements IWmsWareInfoService {

    @Override
    public Page<WmsWareInfo> page(Page<WmsWareInfo> page, Map<String, String> map) {
        String key = map.get("key");
        QueryWrapper<WmsWareInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.eq("id", key)
                    .or().like("name", key)
                    .or().like("address", key)
                    .or().like("areacode", key);
        }
        return this.page(page, queryWrapper);
    }
}
