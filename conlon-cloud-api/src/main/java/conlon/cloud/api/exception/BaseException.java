package conlon.cloud.api.exception;

import lombok.Data;

/**
 * @program: demo
 * @description: 用户友好提示异常类
 * @author: Journey
 * @create: 2019-07-22 19:58
 */
@Data
public class BaseException extends RuntimeException {

    protected String resultCode;
    protected String resultMsg;

    public BaseException(String resultCode, String resultMsg) {
        super(resultMsg);
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
