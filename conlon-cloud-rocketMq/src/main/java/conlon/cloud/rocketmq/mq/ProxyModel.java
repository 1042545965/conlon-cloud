package conlon.cloud.rocketmq.mq;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: conlon-cloud ProxyModel.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-01-10 09:46
 */
@Data
@Accessors(chain = true)
public class ProxyModel implements Serializable {

    private static final long serialVersionUID = 3088603852382590901L;

    private String methodName;

    private String className;

    private Class<?>[] parameterTypes;

    private Object[] args;

//    public Object[] getArgs(){
//        Object[] isArgs ;
//        for (Class<?> aClass : parameterTypes) {
//            for (Object arg : args) {
//
//            }
//        }
//    }
}
