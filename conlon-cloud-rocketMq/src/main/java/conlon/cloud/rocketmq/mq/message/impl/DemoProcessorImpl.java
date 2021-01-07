package conlon.cloud.rocketmq.mq.message.impl;

import conlon.cloud.rocketmq.entity.DemoMqModel;
import conlon.cloud.rocketmq.mq.message.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author conlon
 * @Description 测试demo消费者
 * @Date 2021/1/6 19:58
 **/
@Service
@Slf4j
public class DemoProcessorImpl implements MessageProcessor<DemoMqModel> {

    @Override
    public boolean handleMessage(DemoMqModel demoMqModel) {
        log.info("demoMqModel receive : " + demoMqModel.toString());
        return true;
    }

    @Override
    public Class<DemoMqModel> getClazz() {
        return DemoMqModel.class;
    }
}