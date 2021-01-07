package conlon.cloud.rocketmq.mq.producer;

import conlon.cloud.rocketmq.config.RocketProperties;
import conlon.cloud.rocketmq.mq.message.LocalTransactionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Author conlon
 * @Description 消息发送者
 * @Date 2021/1/6 20:37
 **/
@Component
@Slf4j
public class TransactionMqProducer {

    private final RocketProperties rocketProperties;

    @Autowired
    public TransactionMqProducer(RocketProperties rocketProperties) {
        this.rocketProperties = rocketProperties;
    }

    @Bean
    public TransactionMQProducer getTransactionMqProducer() {
        TransactionListener transactionListener = new LocalTransactionListener();
        TransactionMQProducer producer = new TransactionMQProducer(rocketProperties.getTransactionGroupName());
        producer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        producer.setTransactionListener(transactionListener);
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            log.info("TransactionMQProducer, groupName : {},nameserAddr:{}", rocketProperties.getTransactionGroupName(),
                    rocketProperties.getNamesrvAddr());
        } catch (MQClientException e) {
            log.error("TransactionMQProducer-MQClientException : {} ", e);
        }
        return producer;
    }

}

