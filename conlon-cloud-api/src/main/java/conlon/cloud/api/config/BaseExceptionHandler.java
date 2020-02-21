package conlon.cloud.api.config;

import com.alibaba.fastjson.JSON;
import conlon.cloud.api.constant.Canstant;
import conlon.cloud.api.exception.InternalApiException;
import conlon.cloud.common.enums.ResponseCode;
import conlon.cloud.common.utils.Result;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @Autowired
    private LocalMethodValidationInterceptor localMethodValidationInterceptor;

    @ExceptionHandler(UnauthenticatedException.class)
    public Result<String> nauthenticatedExceptionHandler(UnauthenticatedException exception) {
        log.info("shiro_exception==>{}" + exception.getMessage());
        return Result.build(ResponseCode.AUTH_PERMISSIONS_ERROR.getResultCode(),
                ResponseCode.AUTH_PERMISSIONS_ERROR.getResultMsg());
    }

    /**
     * 参数校验的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> constraintViolationException(ConstraintViolationException exception) {
        log.info("Constraint==>{}" + exception);
        String rootBeanName ="";
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            stringBuilder.append(constraintViolation.getMessage());
            stringBuilder.append("  ");
            rootBeanName = constraintViolation.getRootBeanClass().getName();
            log.info("rootBeanName" + rootBeanName);

        }
        if (!StringUtils.isEmpty(rootBeanName) && rootBeanName.contains(Canstant.INTERNAL_API_EXCEPTION)) {
//            localMethodValidationInterceptor.sendApiException(stringBuilder.toString());
            // 抛出参数的api异常
            log.info("==========================================hahahah ==============================" + stringBuilder.toString());
            throw new InternalApiException(ResponseCode.PARAMETER_FAIL.getResultCode(),
                    "555555555555555555555");
        }
        return Result.build(ResponseCode.PARAMETER_FAIL.getResultCode(), stringBuilder.toString());
    }

}