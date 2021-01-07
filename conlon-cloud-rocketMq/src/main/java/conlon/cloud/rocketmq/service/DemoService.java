package conlon.cloud.rocketmq.service;


import conlon.cloud.rocketmq.entity.TestMqModel;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @Author conlon
 * @Description 执行方法
 * @Date 2021/1/7 11:30
 **/
public interface DemoService {


    /**
     * @Author conlon
     * @Description 发送事务消息
     * @Date 2021/1/7 12:01
     * @param testMqModel 消息体
     **/
    void transactionSend(TestMqModel testMqModel) throws MQClientException;
}

