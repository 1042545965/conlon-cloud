package conlon.cloud.rocketmq.config.rocketmq;

import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 消费者接口
 *
 * @author conlon
 */
public interface RocketConsumer {

    /**
     * 初始化消费者
     */
    public abstract void init();

    /**
     * 注册监听
     */
    public void registerMessageListener(MessageListenerConcurrently messageListener);

}