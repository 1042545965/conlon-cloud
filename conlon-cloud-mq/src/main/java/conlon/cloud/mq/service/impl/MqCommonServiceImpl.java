package conlon.cloud.mq.service.impl;

import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.core.producer.MessageSendType;
import conlon.cloud.mq.service.MqService;
import org.springframework.stereotype.Service;

/**
 * @Author conlon
 * @Description
 * @Date 2021/3/24 10:00
 **/
@Service("mqCommonService")
@RocketMessage(groupID = "GID-sdongpos-shop")
public class MqCommonServiceImpl implements MqService {


    @Override
    @CommonMessage(topic = "sdongpos-common", tag = "commonA",messageSendType = MessageSendType.SEND)
    public String sendMsg(String message) {
        return message;
    }
}
