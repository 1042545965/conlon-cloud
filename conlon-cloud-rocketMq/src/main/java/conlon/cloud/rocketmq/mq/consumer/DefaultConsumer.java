package conlon.cloud.rocketmq.mq.consumer;

import conlon.cloud.rocketmq.config.RocketProperties;
import conlon.cloud.rocketmq.enums.MqEnum;
import conlon.cloud.rocketmq.mq.message.MessageListen;
import conlon.cloud.rocketmq.mq.message.impl.DemoProcessorImpl;
import conlon.cloud.rocketmq.mq.message.impl.TestProcessorImpl;
import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author conlon
 * @Description 默认消费者
 * @Date 2021/1/6 20:06
 **/
@Component
@Slf4j
public class DefaultConsumer {

    private static final String DEMO_TAG = "demo_tag";
    private static final String TEST_TAG = "test_tag";
    private static final String JOIN_SYMBOL = "||";


    private final RocketProperties rocketProperties;

    private final DemoProcessorImpl demoProcessor;
    private final TestProcessorImpl testProcessor;

    @Autowired
    public DefaultConsumer(RocketProperties rocketProperties, DemoProcessorImpl demoProcessor,
            TestProcessorImpl testProcessor) {
        this.rocketProperties = rocketProperties;
        this.demoProcessor = demoProcessor;
        this.testProcessor = testProcessor;
    }

    /**
     * 通过构造函数 实例化对象
     */
    @Bean
    public DefaultMQPushConsumer initDefaultConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqEnum.DEFAULT_GROUP.getCode());
        consumer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //在监听类中增加两个消息处理类，当然可以增加更多，也就是往MessageListen类中的map集合放入处理类。
        MessageListen messageListener = new MessageListen();
        messageListener.registerHandler(DEMO_TAG, demoProcessor);
        messageListener.registerHandler(TEST_TAG,testProcessor);
        consumer.registerMessageListener(messageListener);
        //订阅主题和 标签（ * 代表所有标签)下信息
        String tag = DEMO_TAG + JOIN_SYMBOL + TEST_TAG;
        consumer.subscribe(MqEnum.DEFAULT_TOPIC.getCode(), tag);
        consumer.start();
        log.info(" DefaultConsumer 消费者 启动成功=======");
        return consumer;
    }
}