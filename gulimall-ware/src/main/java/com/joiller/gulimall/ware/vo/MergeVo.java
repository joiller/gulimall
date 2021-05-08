package com.joiller.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jianghuilai
 * @since 2021-04-30 10:22
 **/

@Data
public class MergeVo {
    /**
     * items: [7]
     * purchaseId: 5
     */
    private List<Long> items;
    private Long purchaseId;
}
