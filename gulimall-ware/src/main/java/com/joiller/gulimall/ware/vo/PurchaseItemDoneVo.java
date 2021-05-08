package com.joiller.gulimall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author jianghuilai
 * @since 2021-04-30 15:34
 **/

@Data
public class PurchaseItemDoneVo {
    /**
     * "itemId": 8,
     * "status": 3,
     * "reason": ""
     */
    @NotNull
    private Long itemId;
    private Integer status;
    private String reason;
}
