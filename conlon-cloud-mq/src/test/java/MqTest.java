import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import conlon.cloud.mq.MqApplication;
import conlon.cloud.mq.config.ProducerClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqApplication.class)
@Slf4j
public class MqTest {


    @Autowired
    private ProducerClient producerClient;

    /**
     * 处理返回对应source 的实体信息 并且分页
     *
     * @Author:Mr conlon
     * @create 202/3/31 11:24
     */
    @Test
    public void test03() {
        Message msg = new Message( //
                // Message 所属的 Topic
                "topic_family",
                // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在消息队列 RocketMQ 版的服务器过滤
                "TagA",
                // Message Body 可以是任何二进制形式的数据， 消息队列 RocketMQ 版不做任何干预
                // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
                "Hello MQ".getBytes());
        // 设置代表消息的业务关键属性，请尽可能全局唯一
        // 以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发
        // 注意：不设置也不会影响消息正常收发
        msg.setKey("ORDERID_100");
        // 发送消息，只要不抛异常就是成功
        try {
            SendResult sendResult = producerClient.buildProducer().send(msg);
            assert sendResult != null;
            System.out.println("send success: " + sendResult.getMessageId());
        }catch (ONSClientException e) {
            System.out.println("发送失败");
        }
    }


    @Test
    public void test04() {
        Message msg = new Message( //
                // Message 所属的 Topic
                "topic_family",
                // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在消息队列 RocketMQ 版的服务器过滤
                "TagB",
                // Message Body 可以是任何二进制形式的数据， 消息队列 RocketMQ 版不做任何干预
                // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
                "Hello MQ".getBytes());
        // 设置代表消息的业务关键属性，请尽可能全局唯一
        // 以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发
        // 注意：不设置也不会影响消息正常收发
        msg.setKey("ORDERID_100");
        // 发送消息，只要不抛异常就是成功
        try {
            SendResult sendResult = producerClient.buildProducer().send(msg);
            assert sendResult != null;
            System.out.println("send success: " + sendResult.getMessageId());
        }catch (ONSClientException e) {
            System.out.println("发送失败");
        }
    }
}