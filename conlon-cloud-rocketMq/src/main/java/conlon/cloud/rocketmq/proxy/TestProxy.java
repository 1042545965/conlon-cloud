package conlon.cloud.rocketmq.proxy;

import conlon.cloud.common.utils.serializer.ProtoBufSerializer;
import conlon.cloud.rocketmq.mq.BeansUtils;
import conlon.cloud.rocketmq.mq.ProxyModel;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

/**
 * @Author conlon
 * @Description 消息发送的动态代理
 * @Date 2021/1/10 7:44
 **/
@Slf4j
public class TestProxy implements InvocationHandler {

    private Object target;

    public TestProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ProxyModel proxyModel = new ProxyModel()
                .setParameterTypes(method.getParameterTypes())
                .setClassName(method.getDeclaringClass().getName())
                .setMethodName(method.getName())
                .setArgs(args);
//        String jsonString = JSON.toJSONString(proxyModel);
        //proxyModel = JSON.parseObject(jsonString , ProxyModel.class);
        //proxyModel = deserialize(serialize(proxyModel) , ProxyModel.class);
        proxyModel = ProtoBufSerializer.deSerialize(ProtoBufSerializer.serialize(proxyModel) , ProxyModel.class);

//        Object object = BeansUtils.getBean(Class.forName(proxyModel.getClassName()));
//        Method isMethod = ReflectionUtils
//                .findMethod(object.getClass(), proxyModel.getMethodName(), proxyModel.getParameterTypes());
//        assert isMethod != null;
        //Object o = ReflectionUtils.invokeMethod(isMethod, object, proxyModel.getArgs());
        // method.invoke(target, args);
//         String jsonString = JSON.toJSONString(proxyModel);
//         proxyModel = JSON.parseObject(jsonString , TestProxyModel.class);
        //Object invoke = proxyModel.getMethod().invoke(proxyModel.getTarget(), proxyModel.getArgs());
        return null;
    }


    public <T> byte[] serialize(T object) {
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(object.getClass());
        return ProtobufIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public <T> T deSerialize(byte[] bytes, Class<T> clazz) {
        RuntimeSchema<T> runtimeSchema = RuntimeSchema.createFrom(clazz);
        T object = runtimeSchema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, object, runtimeSchema);
        return object;
    }
}