package com.joiller.gulimall.common.constant;

/**
 * @author jianghuilai
 * @since 2021-04-30 10:32
 **/

public class WareConstant {
    public enum PurchaseStatusEnum{
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        PURCHASING(2, "已领取"),
        COMPLETED(3, "已完成"),
        FAILED(4, "有异常");
        private Integer code;
        private String msg;

        PurchaseStatusEnum (Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
    public enum PurchaseDetailStatusEnum{
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        PURCHASING(2, "正在采购"),
        COMPLETED(3, "已完成"),
        FAILED(4, "采购失败");
        private Integer code;
        private String msg;

        PurchaseDetailStatusEnum (Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
