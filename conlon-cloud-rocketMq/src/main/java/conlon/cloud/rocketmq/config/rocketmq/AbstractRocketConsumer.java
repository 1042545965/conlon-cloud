package conlon.cloud.rocketmq.config.rocketmq;

import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 消费者基本信息
 *
 * @author conlon
 */
public abstract class AbstractRocketConsumer implements RocketConsumer {

    protected String topics;
    protected String tags;
    protected MessageListener messageListener;
    protected String consumerTitel;
    protected MQPushConsumer mqPushConsumer;

    /**
     * 必要的信息
     */
    public void necessary(String topics, String tags, String consumerTitel) {
        this.topics = topics;
        this.tags = tags;
        this.consumerTitel = consumerTitel;
    }

    public abstract void init();

    @Override
    public void registerMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener = messageListener;
    }

}