package conlon.cloud.auth.enums;

/**
 * 全局异常码
 *
 * @author Journey
 * @version 创建时间：2019年7月17日 下午4:18:31
 * @ClassName 类名称
 * @Description 类描述
 */
public enum ResponseAuthCode {

    /**
     * 请求成功
     */
    SUCCESS("000000", "操作成功")
    /**
     * 请求失败
     */
    , FAIL("999999", "网络繁忙，请稍后再试!")

    ;

    private String resultCode;
    private String resultMsg;

    ResponseAuthCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getCodeStr() {
        return resultCode + "";
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public ResponseAuthCode setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
        return this;
    }

    public static String getMsgByCode(String resultCode) {
        ResponseAuthCode[] values = ResponseAuthCode.values();
        for (ResponseAuthCode ec : values) {
            if (ec.resultCode.equals(resultCode)) {
                return ec.resultMsg;
            }
        }
        return "";
    }
}
