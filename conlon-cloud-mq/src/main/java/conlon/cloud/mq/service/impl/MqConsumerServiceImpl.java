package conlon.cloud.mq.service.impl;

import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.github.thierrysquirrel.annotation.MessageListener;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketListener;
import com.github.thierrysquirrel.annotation.RocketMessage;
import conlon.cloud.mq.service.MqService;
import org.springframework.stereotype.Service;

/**
 * @Author conlon
 * @Description
 * @Date 2021/3/24 10:00
 **/
@RocketListener(groupID = "GID-sdongpos-shop",messageModel = PropertyValueConst.CLUSTERING)
public class MqConsumerServiceImpl {


    @MessageListener(topic = "sdongpos-common",tag = "order")
    public void consumerOrderMsg(String message) {
        System.out.println("consumerMsg ： "+message);
    }

    @MessageListener(topic = "sdongpos-common",tag = "commonA")
    public void consumerCommonMsg(String message) {
        System.out.println("consumercommonMsg ： "+message);
    }

    @MessageListener(topic = "sdongpos-common",tag = "commonB")
    public void consumerCommonBMsg(String message) {
        System.out.println("consumercommonMsg ： "+message);
    }
}
