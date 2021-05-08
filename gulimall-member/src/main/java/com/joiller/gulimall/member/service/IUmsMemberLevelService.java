package com.joiller.gulimall.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joiller.gulimall.member.entity.UmsMemberLevel;

import java.util.Map;

/**
 * <p>
 * 会员等级 服务类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
public interface IUmsMemberLevelService extends IService<UmsMemberLevel> {

    Page<UmsMemberLevel> page(Map<String, String> params);
}
