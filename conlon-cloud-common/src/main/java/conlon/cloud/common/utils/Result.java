package conlon.cloud.common.utils;

import com.alibaba.druid.support.json.JSONUtils;
import conlon.cloud.common.enums.ResponseCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * 自定义响应结构
 *
 * @author Journey
 * @version 创建时间：2019年7月17日 下午4:25:12
 * @ClassName 类名称
 * @Description 类描述
 */
public class Result<T> {

    public final static String CODE_SUCCESS = ResponseCode.SUCCESS.getResultCode();
    public final static String CODE_FAIL = ResponseCode.FAIL.getResultCode();
    public final static String MSG_SUCCESS = ResponseCode.SUCCESS.getResultMsg();
    public final static String MSG_FAIL = ResponseCode.FAIL.getResultMsg();

    // 响应业务状态 000000 成功， xxxxxx失败
    @ApiModelProperty(value = "响应状态")
    private String resultCode;

    // 响应消息
    @ApiModelProperty(value = "响应消息")
    private String resultMsg;

    // 响应中的数据
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result() {
    }

    public Result(String status, String resultMsg, T data) {
        this.resultCode = status;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public Result(T data) {
        this.resultCode = CODE_SUCCESS;
        this.resultMsg = MSG_SUCCESS;
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> ok() {
        return new Result<>(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(CODE_FAIL, MSG_FAIL, null);
    }

    public static <T> Result<T> build(String status, String msg) {
        return build(status, msg, null);
    }

    public static <T> Result<T> build(String status, String resultMsg, T data) {
        return new Result<>(status, resultMsg, data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONUtils.toJSONString(this);
    }
}
