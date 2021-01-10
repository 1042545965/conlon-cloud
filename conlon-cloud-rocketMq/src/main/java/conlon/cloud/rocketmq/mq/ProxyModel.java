package conlon.cloud.rocketmq.mq;

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
public class ProxyModel {

    private String methodName;
    private String className;
    private LinkedHashMap<Class<?>, String> args;
}
