package conlon.cloud.rocketmq.mq.message;


import com.alibaba.fastjson.JSON;

/**
 * @author:
 * @Date: 19:25 2019/1/15
 */
public interface MessageProcessor<T> {

    boolean handleMessage(T message);

    Class<T> getClazz();

    default T transferMessage(String message) {
        return JSON.parseObject(message, getClazz());
    }

}

