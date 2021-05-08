package com.joiller.gulimall.member.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joiller.gulimall.common.utils.PageUtils;
import com.joiller.gulimall.member.entity.UmsMemberLevel;
import com.joiller.gulimall.member.mapper.UmsMemberLevelMapper;
import com.joiller.gulimall.member.service.IUmsMemberLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 会员等级 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements IUmsMemberLevelService {

    @Override
    public Page<UmsMemberLevel> page(Map<String, String> params) {
        Page<UmsMemberLevel> page = new PageUtils<UmsMemberLevel>().page(params);
        return this.page(page);
    }
}
