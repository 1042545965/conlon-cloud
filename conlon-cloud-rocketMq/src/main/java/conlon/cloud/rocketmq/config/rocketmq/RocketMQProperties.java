package conlon.cloud.rocketmq.config.rocketmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author:Mr conlon
 * @return
 * @create 2020/5/6 17:18
 */
@ConfigurationProperties(prefix = "rocketmq")
@Data
public class RocketMQProperties {

    private boolean isEnable = false;
    private String namesrvAddr = "localhost:9876";
    private String groupName = "default";
    private int producerMaxMessageSize = 1024;
    private int producerSendMsgTimeout = 2000;
    private int producerRetryTimesWhenSendFailed = 2;
    private int consumerConsumeThreadMin = 5;
    private int consumerConsumeThreadMax = 30;
    private int consumerConsumeMessageBatchMaxSize = 1;
}