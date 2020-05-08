package conlon.cloud.rocketmq.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author conlonx
 * @since 2019-08-28
 */
@Validated
@RestController
@RequestMapping("/rocket/index")
@Slf4j
public class IndexController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @PutMapping("/sendMessage/{topic}/{tag}/{message}")
    public void sendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) throws Exception {
        try {
            Message msg = new Message(topic, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息到一个Broker
            SendResult sendResult = defaultMQProducer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

}