package conlon.cloud.rocketmq.proxy;

import com.alibaba.fastjson.JSON;
import conlon.cloud.rocketmq.mq.ProxyModel;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author conlon
 * @Description 消息发送的动态代理
 * @Date 2021/1/10 7:44
 **/
@Slf4j
@Component
public class ProducerProxy implements InvocationHandler {

    private final TransactionMQProducer producer;

    private String topic;
    private String tags;

    @Autowired
    public ProducerProxy(TransactionMQProducer producer) {
        this.producer = producer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ProxyModel proxyModel = new ProxyModel()
                .setArgs(this.buildArgs(args))
                .setClassName(method.getDeclaringClass().getName())
                .setMethodName(method.getName());

        Message message = new Message(topic, tags, JSON.toJSONBytes(proxyModel));
        SendResult result = producer.send(message);
        log.info("MessageProxyHandler-invoke-SendResult : {} ", result);
//        System.out.println(method.getName());
//        System.out.println(method.getDeclaringClass().getName());
//        this.buildArgs(method.getParameterTypes() , args);
        return null;
    }

    private LinkedHashMap<Class<?>, String> buildArgs(Object[] args) {
        LinkedHashMap<Class<?>, String> map = new LinkedHashMap<>();
        for (int j = 0; j < args.length; j++) {
            Object object = args[j];
            map.put(object.getClass(), JSON.toJSONString(object));
        }
        return map;
    }

    public <T> T getProxy(Class<T> tClass, String topic, String tags) {
        this.topic = topic;
        this.tags = tags;
        return tClass
                .cast(Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this));

    }

}