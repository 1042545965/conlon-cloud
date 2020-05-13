package conlon.cloud.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:Mr conlon
 * @return
 * @create 2020/5/6 17:18
 */
@ConfigurationProperties(prefix = "rocketmq")
@Data
@Component
public class RocketMQProperties {

    /**
     * 地址
     */
    private String namesrvAddr = "localhost:9876";

    /**
     * 组名
     */
    private String groupName = "default";

    /**
     * 组名
     */
    private String transactionGroupName = "transaction";
}