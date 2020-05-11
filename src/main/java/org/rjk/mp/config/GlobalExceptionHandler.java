package org.rjk.mp.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕捉类
 * @author Administrator
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 捕捉全局异常的类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e){
        R result = R.fail(500,e.getMessage());
        return result;
    }
}
