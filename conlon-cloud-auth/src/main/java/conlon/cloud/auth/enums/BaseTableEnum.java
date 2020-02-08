package conlon.cloud.auth.enums;

/**
 * 对应表枚举
 * 格式  表名_字段名称  统一大写
 * 字段名  字段描述
 */
public enum BaseTableEnum {

    APP_CODE("app_code", "应用"),

    ;

    private String code;

    private String message;

    private BaseTableEnum(String code, String message) {
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
