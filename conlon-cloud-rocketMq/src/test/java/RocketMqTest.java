import com.alibaba.fastjson.JSON;
import conlon.cloud.rocketmq.RocketMqApplication;
import conlon.cloud.rocketmq.api.DemoMqApi;
import conlon.cloud.rocketmq.entity.DemoMqModel;
import conlon.cloud.rocketmq.entity.TestMqModel;
import conlon.cloud.rocketmq.enums.MqEnum;
import conlon.cloud.rocketmq.mq.BeansUtils;
import conlon.cloud.rocketmq.proxy.ProducerProxy;
import conlon.cloud.rocketmq.proxy.TestProxy;
import conlon.cloud.rocketmq.service.DemoService;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqApplication.class)
@Slf4j
public class RocketMqTest {

//    @Autowired
//    private DefaultMQProducer producer;

    @Autowired
    private TransactionMQProducer producer;

    @Autowired
    private ProducerProxy producerProxy;

    @Autowired
    private TestProxy testProxy;

    @Autowired
    private DemoService demoService;

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

    @Test
    public void test03() throws Exception {
        Class c2 = Class.forName("conlon.cloud.rocketmq.api.DemoMqApi");
        Map beansOfType = BeansUtils.getBeansOfType(c2);
        for (Object obj : beansOfType.values()) {
            obj.getClass().getDeclaredMethod("testProxySend" , DemoMqModel.class).invoke(obj,this.buildDemoMqModel());
        }
    }

    @Test
    public void test04() throws Exception {
        Long shopId = 1234567890L;
        String macCode = "NettyClientSelector_1";
        Integer goodId = 10 ;
        String topic = MqEnum.DEFAULT_TOPIC.getCode();
        String tags = "demo_tag";
        DemoMqApi proxy = producerProxy.getProxy(DemoMqApi.class , topic , tags);
        proxy.testProxySend(buildDemoMqModel() ,shopId , macCode , goodId);
    }

    @Test
    public void test05() throws Exception {
        Long shopId = 1234567890L;
        String macCode = "NettyClientSelector_1";
        Integer goodId = 10 ;
        // TestProxy testProxy = new TestProxy().getProxy()
        DemoMqApi proxy = testProxy.getProxy(DemoMqApi.class);


        proxy.testProxySend(buildDemoMqModel() ,shopId , macCode , goodId);
    }

    @Test
    public void test06() throws Exception {
        System.out.println("Dadasdasdas");
        Class aClass = Class.forName("conlon.cloud.rocketmq.api.DemoMqApi");
        Object bean = BeansUtils.getBean(aClass);
        Method newMethod= ReflectionUtils.findMethod(bean.getClass(),"testProxySend");
        assert newMethod != null;
        Object objRe= ReflectionUtils.invokeMethod(newMethod,bean);
        System.out.println(bean);
    }

    private DemoMqModel buildDemoMqModel(){
        DemoMqModel demoMqModel = new DemoMqModel();
        demoMqModel.setDate(new Date());
        demoMqModel.setDoubleData(2.00);
        demoMqModel.setPrice(BigDecimal.TEN);
        demoMqModel.setString("DemoMqModel");
        return demoMqModel;
    }

}