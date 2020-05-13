package conlon.cloud.rocketmq.enums;

/**
 * 应用常量枚举
 */
public enum MqEnum {

    DEFAULT_GROUP("defaultGroup", "用户组"),

    USER_GROUP("userGroup", "用户组"),

    SEQUENCE_GROUP("sequenceGroup", "顺序消息组"),

    SEQUENCE_TOPIC("sequenceTopic", "用户topic"),

    SEQUENCE_TOPIC_TAG_ONE("sequenceTagOne", "顺序topic"),

    USER_TOPIC("userTopic", "用户topic"),

    USER_TOPIC_TAG_ALL("*", "该topic下的所有的tag都被消费"),

    USER_TOPIC_TAG_AUTH("authTag", "用户权限tag"),

    USER_TOPIC_TAG_DEFAULT("defaultTag", "该topic下默认的tag"),

    ;

    private String code;

    private String message;

    private MqEnum(String code, String message) {
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
