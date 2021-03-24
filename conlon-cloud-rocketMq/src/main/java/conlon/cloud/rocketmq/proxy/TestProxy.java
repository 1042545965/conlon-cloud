package conlon.cloud.rocketmq.proxy;

import com.alibaba.fastjson.JSON;
import conlon.cloud.rocketmq.mq.BeansUtils;
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
import org.springframework.util.ReflectionUtils;

/**
 * @Author conlon
 * @Description 消息发送的动态代理
 * @Date 2021/1/10 7:44
 **/
@Slf4j
@Component
public class TestProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ProxyModel proxyModel = new ProxyModel()
                .setTestArgs(args)
                .setClassName(method.getDeclaringClass().getName())
                .setMethodName(method.getName());
        //执行目标对象的方法
        return method.invoke(proxy, args);
    }

    /**
     *tClass 这个对象生成的动态代理的对象又强制转换成了 tClass 这个对象 tClass.cast 强制转换的意思  所以这里会直接调用 上面的 invoke 方法
     */
    public <T> T getProxy(Class<T> tClass) {
        return tClass
                .cast(Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this));

    }
}