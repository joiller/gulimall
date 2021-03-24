package com.joiller.gulimall.order.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MqMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String messageId;

    /**
     * JSON
     */
    private String content;

    private String toExchange;

    private String classType;

    /**
     * 0-新建 1-已发送 2-错误抵达 3-已抵达
     */
    private Integer messageStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
