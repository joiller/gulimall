package com.joiller.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 秒杀商品通知订阅
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsSeckillSkuNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * member_id
     */
    private Long memberId;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 活动场次id
     */
    private Long sessionId;

    /**
     * 订阅时间
     */
    private LocalDateTime subcribeTime;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 通知方式[0-短信，1-邮件]
     */
    private Boolean noticeType;


}
