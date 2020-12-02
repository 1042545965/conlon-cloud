import conlon.cloud.rocketmq.RocketMqApplication;
import conlon.cloud.rocketmq.config.RocketMQProperties;
import conlon.cloud.rocketmq.producer.DefaultMqProducer;
import lombok.extern.slf4j.Slf4j;
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
    private DefaultMqProducer defaultProducer;
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