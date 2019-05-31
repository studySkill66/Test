package com.hksy.framework.aop;

import com.hksy.framework.common.ResultJson;
import com.hksy.framework.common.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 控制器统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler(value={Exception.class})
    @ResponseBody
    public ResultJson handle(Exception e) {
        log.error("系统处理异常：{}", e);

        //系统异常
        ResultJson jsonResult = new ResultJson(StatusEnum.EXCEPTION);
        return jsonResult;
    }
}
