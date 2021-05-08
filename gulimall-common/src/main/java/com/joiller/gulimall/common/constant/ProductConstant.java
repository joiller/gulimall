package com.joiller.gulimall.common.constant;

/**
 * @author jianghuilai
 * @since 2021-04-20 11:25
 **/

public class ProductConstant {

    public enum AttrEnum{
        BASE_ATTR(1, "base"), SALE_ATTR(0, "sale");
        private Integer code;
        private String msg;
        AttrEnum(Integer code, String msg) {
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
