package com.joiller.gulimall.product.service.impl;

import com.joiller.gulimall.product.entity.PmsSpuImages;
import com.joiller.gulimall.product.mapper.PmsSpuImagesMapper;
import com.joiller.gulimall.product.service.IPmsSpuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu图片 服务实现类
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Service
public class PmsSpuImagesServiceImpl extends ServiceImpl<PmsSpuImagesMapper, PmsSpuImages> implements IPmsSpuImagesService {

    @Override
    public boolean save(Long id, List<String> images) {

        List<PmsSpuImages> collect = images.stream().map(s -> {
            PmsSpuImages spuImages = new PmsSpuImages();
            spuImages.setSpuId(id);
            spuImages.setImgUrl(s);
            return spuImages;
        }).collect(Collectors.toList());
        return this.saveBatch(collect);
    }
}
