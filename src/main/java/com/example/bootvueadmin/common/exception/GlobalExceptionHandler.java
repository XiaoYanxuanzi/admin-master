package com.example.bootvueadmin.common.exception;

import com.example.bootvueadmin.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public Result<Void> customerException(CustomerException e) {
        logger.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    /**
     * 全局Exception异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Void> exception(Exception e) {
        logger.error("系统错误", e);
        return Result.error("系统错误");
    }

}
