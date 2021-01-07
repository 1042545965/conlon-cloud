package conlon.cloud.rocketmq.mq.message;


import com.alibaba.fastjson.JSON;

/**
 * @Author conlon
 * @Description 执行方法
 * @Date 2021/1/7 11:30
 **/
public interface MessageProcessor<T> {

    /**
     * @Author conlon
     * @Description 执行参数
     * @Date 2021/1/7 11:29
     * @param message
     **/
    boolean handleMessage(T message);

    /**
     * @Author conlon
     * @Description 获取类型
     * @Date 2021/1/7 11:29
     **/
    Class<T> getClazz();

    /**
     * @Author conlon
     * @Description 序列化参数
     * @Date 2021/1/7 11:30
     * @param message
     **/
    default T transferMessage(String message) {
        return JSON.parseObject(message, getClazz());
    }

}

