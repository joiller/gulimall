package com.joiller.gulimall.product.exception;

import com.joiller.gulimall.common.exception.BizCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.joiller.gulimall.common.utils.R;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

/**
 * @author jianghuilai
 * @since 2021-04-07 11:41
 **/

@Slf4j
@RestControllerAdvice
public class PmsExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R argumentNotValid(MethodArgumentNotValidException e){
        R error = R.error(BizCodeEnum.VALID_EXCEPTION);
        BindingResult bindingResult = e.getBindingResult();
        Stream<LinkedHashMap<String, Object>> linkedHashMapStream = bindingResult.getFieldErrors().stream().map(objectError -> {
            LinkedHashMap<String, Object> err = new LinkedHashMap<>();
            err.put("field", objectError.getField());
            err.put("message", objectError.getDefaultMessage());
            return err;
        });
        error.put("errors", linkedHashMapStream);
        return error;
    }

    @ExceptionHandler(Throwable.class)
    public R otherExcption(Throwable throwable) {
        log.debug("error", (Object[]) throwable.getStackTrace());
        return R.error(BizCodeEnum.UNKOWN_EXCEPTION).put("error", throwable.getMessage());
    }
}
