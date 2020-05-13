package conlon.cloud.rocketmq.producer;

import conlon.cloud.rocketmq.config.RocketMQProperties;
import javax.annotation.PostConstruct;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultMqProducer {

    private DefaultMQProducer producer;

    @Autowired
    private RocketMQProperties rocketMQProperties;

    @PostConstruct
    public void initMQProducer() {
        //示例生产者
        producer = new DefaultMQProducer(rocketMQProperties.getGroupName());
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
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