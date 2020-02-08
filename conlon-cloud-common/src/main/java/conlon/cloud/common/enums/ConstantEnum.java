package conlon.cloud.common.enums;

/**
 * 应用常量枚举
 */
public enum ConstantEnum {

    DEFAULT_PASSWORD("123456", "默认密码"),

    HEADER_TOKEN("Token", "请求头token"),

    ;

    private String code;

    private String message;

    private ConstantEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
