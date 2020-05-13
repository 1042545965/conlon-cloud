package conlon.cloud.rocketmq.controller;


import conlon.cloud.rocketmq.producer.DefaultMqProducer;
import conlon.cloud.rocketmq.producer.TransactionMqProducer;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    /**
     * 普通消息生产者
     */
    @Autowired
    private DefaultMqProducer defaultProducer;

    /**
     * 普通消息生产者
     */
    @Autowired
    private TransactionMqProducer transactionMqProducer;

    /**
     * 普通测试消息
     *
     * @Author:Mr conlon
     * @create 202/5/13 10:09
     */
    @PutMapping("/sendMessage/{topic}/{tag}/{message}")
    public void sendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) throws Exception {
        try {
            Message msg = new Message(topic, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息到一个Broker
            SendResult sendResult = defaultProducer.getProducer().send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }


    /**
     * 顺序发送消息,在相同的队列中局部保持顺序,全局同队列则全局保持
     * @Author:Mr conlon
     * @create 202/5/13 10:54
     */
    @PutMapping("/sequenceSendMessage/{topic}/{tag}/{message}")
    public void sequenceSendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) {
        try {
            for (int i = 0; i < 5; i++) {
                Message msg = new Message(topic, tag, (message + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                /**
                 * @Description: 发送顺序消息
                 * @param Message msg 消息对象
                 * @param MessageQueueSelector selector 消息队列的选择器
                 * @param Object arg 选择队列的业务标识
                 * @Author:Mr conlon
                 * @return void
                 * @create 2020/5/13 11:08
                 */
                SendResult send = defaultProducer.getProducer().send(msg, new MessageQueueSelector() {
                    /**
                     * @param list 队列集合
                     * @param message 消息对象
                     * @param arg 业务标识参数
                     */
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                        // 用同一个订单 id 去选择队列 可以计算一下 (这里为了方便我默认是 1 号队列)
                        int i = (int) arg;
                        i = Objects.equals(i%2, 0) ? 0 : 1;
                        return list.get(i);
                    }
                }, i);
                // 通过sendResult返回消息是否成功送达
                log.info("sequenceSendMessage ==>{}" , send);
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }


    /**
     * 发送同步消息
     *
     * @Author:Mr conlon
     * @create 202/5/13 10:08
     */
    @PutMapping("/syncSendMessage/{topic}/{tag}/{message}")
    public void syncSendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) {
        try {
            for (int i = 0; i < 5; i++) {
                Message msg = new Message(topic, tag, (message + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送消息到一个Broker
                SendResult sendResult = defaultProducer.getProducer().send(msg);
                // 通过sendResult返回消息是否成功送达
                log.info("syncSendMessage ==> {}", sendResult);
                // 休眠
//                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }


    /**
     * 发送同步消息 键值对的形式
     *
     * @Author:Mr conlon
     * @create 202/5/13 10:08
     */
    @PutMapping("/syncKyeValueSendMessage/{topic}/{tag}/{message}")
    public void syncKyeValueSendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) {
        try {
            for (int i = 0; i < 5; i++) {
                Message msg = new Message(topic, tag, (message + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                msg.putUserProperty("i" , String.valueOf(i));
                // 发送消息到一个Broker
                SendResult sendResult = defaultProducer.getProducer().send(msg);
                // 通过sendResult返回消息是否成功送达
                log.info("syncSendMessage ==> {}", sendResult);
                // 休眠
                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

    /**
     * 发送异步消息  对于时间比较敏感
     *
     * @Author:Mr conlon
     * @create 202/5/13 10:10
     */
    @PutMapping("/asyncSendMessage/{topic}/{tag}/{message}")
    public void asyncSendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) {
        try {
            for (int i = 0; i < 5; i++) {
                Message msg = new Message(topic, tag, (message + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送异步消息
                defaultProducer.getProducer().send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        // 成功回调
                        log.info("asyncSendMessage 成功回调 ==> {}", sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        // 失败回调
                        log.info("asyncSendMessage 失败回调 ==> {}", throwable);
                    }
                });
                // 通过sendResult返回消息是否成功送达
                log.info("asyncSendMessage ==> {}");

            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }


    /**
     * 发送单向消息  不需要知道是否发送成功, 比如日志服务发送消息
     *
     * @Author:Mr conlon
     * @create 202/5/13 10:10
     */
    @PutMapping("/oneWaySendMessage/{topic}/{tag}/{message}")
    public void oneWaySendMessage(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) {
        try {
            for (int i = 0; i < 5; i++) {
                Message msg = new Message(topic, tag, (message + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送异步消息
                defaultProducer.getProducer().sendOneway(msg);
                // 通过sendResult返回消息是否成功送达
                log.info("asyncSendMessage ==> {}");
                // 休眠一秒
                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }


    /**
     * 发送事务消息
     *
     * @Author:Mr conlon
     * @create 202/5/13 10:10
     */
    @PutMapping("/transactionMqProducer/{topic}/{tag}/{message}")
    public void transactionMqProducer(
            @PathVariable("message") String message,
            @PathVariable("topic") String topic,
            @PathVariable("tag") String tag) {
        try {
            for (int i = 0; i < 5; i++) {
                Message msg = new Message(topic, tag, String.valueOf(i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送事务消息
                TransactionSendResult transactionSendResult = transactionMqProducer.getProducer()
                        .sendMessageInTransaction(msg, null);
                // 通过sendResult返回消息是否成功送达
                log.info("transactionSendResult ==> {}" , transactionSendResult);
                // 休眠一秒
                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

}