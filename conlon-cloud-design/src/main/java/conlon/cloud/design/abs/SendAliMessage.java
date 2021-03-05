package conlon.cloud.design.abs;

import com.alibaba.fastjson.JSON;
import conlon.cloud.design.constant.DesignConstant;
import java.io.Serializable;
import java.util.Map;

public class SendAliMessage extends SendPay implements Serializable {


    private static final long serialVersionUID = 6684840913741798771L;

    @Override
    public void sendMessage(Map<String, String> sendMap, String configUrl) {
        this.sendPay(configUrl + DesignConstant.CONFIG_ALIPAY_URL, JSON.toJSONString(sendMap));
    }

    private void sendPay(String url, String toJSONString) {

    }


}