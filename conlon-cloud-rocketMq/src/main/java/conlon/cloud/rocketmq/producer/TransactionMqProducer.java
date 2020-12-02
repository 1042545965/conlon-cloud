package conlon.cloud.rocketmq.producer;

import conlon.cloud.rocketmq.config.RocketMQProperties;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionMqProducer {

    private TransactionMQProducer producer;

    @Autowired
    private RocketMQProperties rocketMQProperties;

    @PostConstruct
    public void initMQProducer() {
        //示例生产者
        producer = new TransactionMQProducer(rocketMQProperties.getTransactionGroupName());
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        // 添加事务监听器
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 在该方法中执行本地事务
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                // 如果这里返回的是
                int i = Integer.valueOf(new String(message.getBody()));
                if (i%2 == 0){
                    log.info("成功参数值 i ==> {}" , i);
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else {
                    log.info("失败参数值 i ==> {}" , i);
                    return LocalTransactionState.UNKNOW;
                }
            }

            /**
             * mq 进行事务状态回查
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                log.info("回查参数值 i ==> {}" , new String(messageExt.getBody()));
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });
        start();
    }

    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getProducer() {
        return this.producer;
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown() {
        this.producer.shutdown();
    }
}