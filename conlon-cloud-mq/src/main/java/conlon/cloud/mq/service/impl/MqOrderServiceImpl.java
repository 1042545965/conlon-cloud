package conlon.cloud.mq.service.impl;

import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import conlon.cloud.mq.service.MqService;
import org.springframework.stereotype.Service;

/**
 * @Author conlon
 * @Description
 * @Date 2021/3/24 10:00
 **/
@Service("mqOrderService")
@RocketMessage(groupID = "GID_order")
public class MqOrderServiceImpl implements MqService {


    @Override
    @OrderMessage(topic = "sdongpos-common",tag = "order")
    public String sendMsg(String message) {
        return message;
    }
}
