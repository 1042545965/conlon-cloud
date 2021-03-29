package conlon.cloud.rocketmq.mq.message;


import com.alibaba.fastjson.JSON;
import conlon.cloud.common.utils.serializer.ProtoBufSerializer;
import conlon.cloud.rocketmq.mq.BeansUtils;
import conlon.cloud.rocketmq.mq.ProxyModel;
import java.lang.reflect.Method;
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
import org.springframework.util.ReflectionUtils;

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
            ProxyModel proxyModel = ProtoBufSerializer.deSerialize(ext.getBody() , ProxyModel.class);
            log.info("MsgId : {} , proxyModel : {} " , ext.getMsgId() , proxyModel);
            Object object = BeansUtils.getBean(Class.forName(proxyModel.getClassName()));
            Method isMethod = ReflectionUtils.findMethod(object.getClass(), proxyModel.getMethodName(), proxyModel.getParameterTypes());
            assert isMethod != null;
            ReflectionUtils.invokeMethod(isMethod, object, proxyModel.getArgs());
        } catch (Exception e) {
            log.info("MessageListen-consumeMessage-Exception : {} ", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

