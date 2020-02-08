package conlon.cloud.common.config;

import conlon.cloud.common.enums.ResponseCode;
import conlon.cloud.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: demo
 * @description: 配置全局异常通知
 * @author: Journey
 * @create: 2019-07-22 19:42
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(UnauthenticatedException.class)
    public Result<String> nauthenticatedExceptionHandler(UnauthenticatedException exception) {
        log.info("shiro_exception==>{}" + exception.getMessage());
        return Result.build(ResponseCode.AUTH_PERMISSIONS_ERROR.getResultCode(),
                ResponseCode.AUTH_PERMISSIONS_ERROR.getResultMsg());
    }

}