package conlon.cloud.rocketmq.proxy;

import com.alibaba.fastjson.JSON;
import conlon.cloud.common.utils.serializer.ProtoBufSerializer;
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
                .setParameterTypes(method.getParameterTypes())
                .setClassName(method.getDeclaringClass().getName())
                .setMethodName(method.getName())
                .setArgs(args);
        Message message = new Message(topic, tags, ProtoBufSerializer.serialize(proxyModel));
        SendResult result = producer.send(message);
        log.info("MessageProxyHandler-invoke-SendResult : {} ", result);
        return null;
    }

    public <T> T getProxy(Class<T> tClass, String topic, String tags) {
        this.topic = topic;
        this.tags = tags;
        return tClass
                .cast(Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this));

    }

}