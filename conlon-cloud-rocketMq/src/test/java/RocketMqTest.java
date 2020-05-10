import conlon.cloud.rocketmq.RocketMqApplication;
import conlon.cloud.rocketmq.config.RocketMQProperties;
import conlon.cloud.rocketmq.producer.DefaultProducer;
import java.io.UnsupportedEncodingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqApplication.class)
@Slf4j
public class RocketMqTest {

    @Autowired
    private DefaultProducer defaultProducer;
    @Autowired
    private RocketMQProperties rocketMQProperties;
    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Test
    public void test01() throws Exception {

        System.out.println(namesrvAddr);
//        String name = "hello";
//        Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
//        // 发送消息到一个Broker
//        SendResult sendResult = defaultMQProducer.send(msg);
//        // 通过sendResult返回消息是否成功送达
//        System.out.printf("%s%n", sendResult);
    }
}