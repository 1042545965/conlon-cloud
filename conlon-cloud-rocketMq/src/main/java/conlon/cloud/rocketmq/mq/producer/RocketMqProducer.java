package conlon.cloud.rocketmq.mq.producer;

import conlon.cloud.rocketmq.config.RocketProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Author conlon
 * @Description 消息发送者
 * @Date 2021/1/6 20:37
 **/
@Component
@Slf4j
public class RocketMqProducer {

    private final RocketProperties rocketProperties;

    @Autowired
    public RocketMqProducer(RocketProperties rocketProperties) {
        this.rocketProperties = rocketProperties;
    }

    @Bean
    public DefaultMQProducer getRocketMqProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(rocketProperties.getGroupName());
        producer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            log.info("rocketMQ is start , groupName : {},nameserAddr:{}", rocketProperties.getGroupName(), rocketProperties.getNamesrvAddr());
        } catch (MQClientException e) {
            log.error("RocketMqProducer-MQClientException : {} ", e);
        }
        return producer;
    }

}

