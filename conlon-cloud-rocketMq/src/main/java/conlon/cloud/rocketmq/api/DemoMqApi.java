package conlon.cloud.rocketmq.api;

import conlon.cloud.rocketmq.entity.DemoMqModel;

/**
 * @program: conlon-cloud DemoMqApi.java
 * @description: 测试 api
 * @author: Mr conlon
 * @create: 2021-01-09 23:48
 */
public interface DemoMqApi {

    default public void testProxySend(DemoMqModel demoMqModel, Long shopId, String macCode, Integer goodId) {

    }
}
