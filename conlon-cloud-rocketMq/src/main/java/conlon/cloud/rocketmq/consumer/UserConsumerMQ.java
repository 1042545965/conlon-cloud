package conlon.cloud.rocketmq.consumer;

import conlon.cloud.rocketmq.config.RocketMQProperties;
import conlon.cloud.rocketmq.enums.MqEnum;
import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserConsumerMQ {

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
    public void initUserConsumer() throws MQClientException {

        consumer = new DefaultMQPushConsumer(MqEnum.USER_GROUP.getCode());
        consumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 负载均衡消费模式 默认是负载均衡消费模式 还有广播模式
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(MqEnum.USER_TOPIC.getCode(), MqEnum.USER_TOPIC_TAG_AUTH.getCode());
        // sql 语法过滤 使用这个 需要 broker.conf 添加 enablePropertyFilter=true (https://blog.csdn.net/yanwanwan/article/details/105288349)
//        consumer.subscribe(MqEnum.USER_TOPIC.getCode(), MessageSelector.bySql("i > 3"));
        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            try {
                for (Message msg : msgs) {
                    //消费者获取消息 这里只输出 不做后面逻辑处理
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("UserConsumerMQ-获取消息-主题topic为={}, 消费消息为={} , 消息tags为={}", msg.getTopic(), body , msg.getTags());
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        log.info(" UserConsumerMQ 消费者 启动成功=======");
    }
}