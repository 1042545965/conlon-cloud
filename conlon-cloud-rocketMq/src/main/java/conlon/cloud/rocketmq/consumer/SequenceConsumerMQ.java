package conlon.cloud.rocketmq.consumer;

import conlon.cloud.rocketmq.config.RocketMQProperties;
import conlon.cloud.rocketmq.enums.MqEnum;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SequenceConsumerMQ {

    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;

    @Autowired
    private RocketMQProperties rocketMQProperties;

    /**
     * 通过构造函数 实例化对象
     */
    @PostConstruct
    public void initSequenceConsumer() throws MQClientException {

        consumer = new DefaultMQPushConsumer(MqEnum.SEQUENCE_GROUP.getCode());
        consumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(MqEnum.SEQUENCE_TOPIC.getCode(), MqEnum.SEQUENCE_TOPIC_TAG_ONE.getCode());
        // 在这里注册监听和普通消息有所不同
        consumer.registerMessageListener(new MessageListenerOrderly() {
            /**
             * 这里mq会使用相同的线程来处理同一个队列里面的消息
             */
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list,
                    ConsumeOrderlyContext context) {
                for (MessageExt msg : list) {
                    log.info("线程名称 ：【" + Thread.currentThread().getName() + "】 ：" + new String(msg.getBody()));
                }
                // 这里return的值也有所不同
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        log.info(" SequenceConsumerMQ 消费者 启动成功=======");
    }
}