package com.joiller.gulimall.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BizCodeEnum {
    UNKOWN_EXCEPTION(10000, "未知异常"),
    VALID_EXCEPTION(10001, "参数格式异常");

    private int code;
    private String message;

}
