package conlon.cloud.rocketmq.mq.message;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author conlon
 * @Description 事务监听
 * @Date 2021/1/7 10:52
 **/
@Slf4j
public class LocalTransactionListener implements TransactionListener {

    private AtomicInteger transactionIndex = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * @Author conlon
     * @Description 对该方法的理解 当存在 spring 事务的时候该方法会被调用 。 会被执行本地事务逻辑
     * @Date 2021/1/7 12:05
     **/
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("LocalTransactionListener-LocalTransactionListener-executeLocalTransaction , msg : {} , arg : {}" , msg , arg);
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }

    /**
     * @Author conlon
     * @Description 该方法被用于回查
     * @Date 2021/1/7 12:07
      * @param msg
     **/
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}