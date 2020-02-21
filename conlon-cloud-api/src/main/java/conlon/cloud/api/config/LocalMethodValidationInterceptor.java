package conlon.cloud.api.config;

import conlon.cloud.api.exception.InternalApiException;
import conlon.cloud.common.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author:Mr conlon
 * @return
 * @create 2020/2/21 17:36
 */
@Slf4j
@Service
public class LocalMethodValidationInterceptor {

    public void sendApiException(String toString) {
        // 抛出参数的api异常
        throw new InternalApiException(ResponseCode.PARAMETER_FAIL.getResultCode(),
                toString);
    }
}
