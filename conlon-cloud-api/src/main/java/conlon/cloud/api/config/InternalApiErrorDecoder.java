package conlon.cloud.api.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import conlon.cloud.api.exception.InternalApiException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class InternalApiErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String msg = null;
        try {
            String json = Util.toString(response.body().asReader());
            JSONObject jsonObject = JSON.parseObject(json);
            msg = jsonObject.getString("message");
            if (msg.contains(":")) {
                String resultCode = msg.split(":")[0];
                String resultMsg = msg.split(":")[1];
                return new InternalApiException(resultCode, resultMsg);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        // 对于服务间调用的 非  InternalApiException 异常 ,
        // 还是需要使用降级的方式 , 那么抛出 Exception 异常 。 则会自动降级
        // , 如果有必要全部捕获 那么 这里可以改为 InternalApiException
        // return new InternalApiException("000000", msg);
        return new Exception(msg);
    }
}