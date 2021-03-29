package conlon.cloud.rocketmq.service.impl;

import conlon.cloud.rocketmq.api.DemoMqApi;
import conlon.cloud.rocketmq.entity.DemoMqModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author conlon
 * @Description 测试demo消费者
 * @Date 2021/1/6 19:58
 **/
@Slf4j
public class TestServiceImpl implements DemoMqApi {

    @Override
    public void testProxySend(DemoMqModel demoMqModel, Long shopId, String macCode, Integer goodId) {
        log.info("testProxySend , demoMqModel : {} , shopId : {} , macCode :{} , goodId : {}", demoMqModel, shopId,
                macCode, goodId);
    }
}