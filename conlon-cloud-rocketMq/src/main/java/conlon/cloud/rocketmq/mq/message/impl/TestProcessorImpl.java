package conlon.cloud.rocketmq.mq.message.impl;

import conlon.cloud.rocketmq.entity.TestMqModel;
import conlon.cloud.rocketmq.mq.message.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author conlon
 * @Description 测试test消费者
 * @Date 2021/1/6 19:58
 **/
@Service
@Slf4j
public class TestProcessorImpl implements MessageProcessor<TestMqModel> {

    @Override
    public boolean handleMessage(TestMqModel testMqModel) {
        log.info("testMqModel receive : " + testMqModel.toString());
        return true;
    }

    @Override
    public Class<TestMqModel> getClazz() {
        return TestMqModel.class;
    }
}