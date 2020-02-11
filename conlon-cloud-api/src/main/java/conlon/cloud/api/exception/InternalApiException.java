package conlon.cloud.api.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * 
 * @ClassName InternalApiException
 * @Description : 服务调用之间的异常抛出
 * @Author : conlon
 * @Date : 2020/2/11 16:52
*/

public class InternalApiException extends HystrixBadRequestException {

    private static final long serialVersionUID = -1549118434059351846L;

    private String resultCode;
    private String resultMsg;

    public InternalApiException(String resultCode, String resultMsg) {
        super(resultCode+":"+resultMsg);
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
