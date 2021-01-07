import com.alibaba.fastjson.JSON;
import conlon.cloud.rocketmq.RocketMqApplication;
import conlon.cloud.rocketmq.entity.DemoMqModel;
import conlon.cloud.rocketmq.entity.TestMqModel;
import conlon.cloud.rocketmq.enums.MqEnum;
import java.math.BigDecimal;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqApplication.class)
@Slf4j
public class RocketMqTest {

    @Autowired
    private DefaultMQProducer producer;

    @Test
    public void test01() throws Exception {
        DemoMqModel demoMqModel = new DemoMqModel();
        demoMqModel.setDate(new Date());
        demoMqModel.setDoubleData(2.00);
        demoMqModel.setPrice(BigDecimal.TEN);
        demoMqModel.setString("DemoMqModel");
        Message message = new Message(MqEnum.DEFAULT_TOPIC.getCode(), "demo_tag",
                JSON.toJSONString(demoMqModel).getBytes());
        SendResult result = producer.send(message);
        System.out.println("发送了消息" + result);

    }


    @Test
    public void test02() throws Exception {
        TestMqModel testMqModel = new TestMqModel();
        testMqModel.setDate(new Date());
        testMqModel.setDoubleData(2.00);
        testMqModel.setPrice(BigDecimal.TEN);
        testMqModel.setString("TestMqModel");
        Message message = new Message(MqEnum.DEFAULT_TOPIC.getCode(), "test_tag",
                JSON.toJSONString(testMqModel).getBytes());
        SendResult result = producer.send(message);
        System.out.println("发送了消息" + result);

    }

}