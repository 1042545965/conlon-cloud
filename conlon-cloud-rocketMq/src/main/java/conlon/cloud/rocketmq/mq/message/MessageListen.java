package conlon.cloud.rocketmq.mq.message;


import com.alibaba.fastjson.JSON;
import conlon.cloud.rocketmq.mq.BeansUtils;
import conlon.cloud.rocketmq.mq.ProxyModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author conlon
 * @Description
 * @Date 2021/1/6 20:02
 **/
@Slf4j
public class MessageListen implements MessageListenerConcurrently {

    /**
     * MessageProcessor接口的实现类放进map集合 key：tag value：MessageProcessor实体类
     **/
    private Map<String, MessageProcessor> handleMap = new HashMap<>();

    public void registerHandler(String tags, MessageProcessor messageProcessor) {
        handleMap.put(tags, messageProcessor);
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        try {
            MessageExt ext = list.get(0);
            String message = new String(ext.getBody());
            ProxyModel proxyModel = JSON.parseObject(message, ProxyModel.class);
            Object[] args = this.decodeArgs(proxyModel.getArgs());
            Class[] classes = this.decodeArgsClass(proxyModel.getArgs());
            Class aClass = Class.forName(proxyModel.getClassName());
            Map beansOfType = BeansUtils.getBeansOfType(aClass);
            for (Object obj : beansOfType.values()) {
                obj.getClass().getDeclaredMethod(proxyModel.getMethodName(), classes).invoke(obj, args);
            }
        } catch (Exception e) {
            log.info("MessageListen-consumeMessage-Exception : {} ", e);
//            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private Class[] decodeArgsClass(Map<Class<?>, String> map) throws ClassNotFoundException {
        Class[] classes = new Class[map.size()];
        int i = 0;
        for (Class key : map.keySet()) {
            classes[i] = key;
            i++;
        }
        return classes;
    }

    private Object[] decodeArgs(Map<Class<?>, String> args) throws ClassNotFoundException {
        Object[] objects = new Object[args.size()];
        int i = 0 ;
        for (Entry<Class<?>, String> entry : args.entrySet()) {
            Class<?> key = entry.getKey();
            String value = entry.getValue();
            objects[i] = JSON.parseObject(value ,key);
            i++;
        }
        return objects;
    }
}

