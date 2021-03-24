package com.joiller.gulimall.member.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会员收藏的商品
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsMemberCollectSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * spu_id
     */
    private Long spuId;

    /**
     * spu_name
     */
    private String spuName;

    /**
     * spu_img
     */
    private String spuImg;

    /**
     * create_time
     */
    private LocalDateTime createTime;


}
