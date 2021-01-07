package conlon.cloud.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import conlon.cloud.rocketmq.entity.TestMqModel;
import conlon.cloud.rocketmq.enums.MqEnum;
import conlon.cloud.rocketmq.mq.message.LocalTransactionListener;
import conlon.cloud.rocketmq.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author conlon
 * @Description 测试demo消费者
 * @Date 2021/1/6 19:58
 **/
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private TransactionMQProducer producer;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void transactionSend(TestMqModel testMqModel) throws MQClientException {
        Message message = new Message(MqEnum.DEFAULT_TOPIC.getCode(), "test_tag",
                JSON.toJSONString(testMqModel).getBytes());
        TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(message , new LocalTransactionListener());
        System.out.println("发送了事务消息" + transactionSendResult);
    }
}