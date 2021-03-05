package conlon.cloud.design.abs;

import java.util.Map;

/**
 * @program: conlon-cloud abstractFactory.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-03-04 21:29
 */
public abstract class SendPay {
    public abstract void sendMessage(Map<String, String> sendMap, String configUrl);
}
