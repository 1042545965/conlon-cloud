package conlon.cloud.mq.service;

/**
 * @Author conlon
 * @Description 公共消息发送
 * @Date 2021/3/24 9:58
 **/
public interface MqService {
    public String sendMsg(String message);
}
