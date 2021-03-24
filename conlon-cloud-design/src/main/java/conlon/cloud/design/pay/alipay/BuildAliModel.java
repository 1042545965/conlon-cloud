package conlon.cloud.design.pay.alipay;

import com.alibaba.fastjson.JSON;
import conlon.cloud.design.abs.BuildPay;
import conlon.cloud.design.constant.DesignConstant;
import conlon.cloud.design.from.ThirdPaymentFrom;
import conlon.cloud.design.pay.alipay.model.AliPayParamModel;
import conlon.cloud.common.utils.md5.Signature;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 *  支付宝构建请求参数
 */
@Slf4j
public class BuildAliModel extends BuildPay {

    /**
     *  支付宝 appId
     */
    private static final String APPID = "appid";
    /**
     * 支付宝私钥
     */
    private static final String RSA_PRIVATE_KEY = "rsa_private_key";
    /**
     * 支付宝公钥
     */
    private static final String RSA_PUBLIC_KEY = "rsa_public_key";

    @Override
    public Map<String, String> buildModel(ThirdPaymentFrom thirdPaymentFrom, Long shopId, String scopeApply) {
        AliPayParamModel aliPayParamModel = JSON.parseObject(thirdPaymentFrom.getThirdParam(), AliPayParamModel.class);
        Integer type = Integer.parseInt(thirdPaymentFrom.getPayType() + scopeApply);
        String siteId = shopId.toString();

        Map<String, String> map = new HashMap<>(16);
        map.put(DesignConstant.GAT_WAY_SEND_KEY_PROJECT_CODE, DesignConstant.PROJECT_CODE);
        map.put(DesignConstant.GAT_WAY_SEND_KEY_SITE_ID, siteId);
        map.put(DesignConstant.CURRENT_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        map.put(DesignConstant.GAT_WAY_SEND_KEY_TYPE, String.valueOf(type));
        map.put(APPID, aliPayParamModel.getAliPayAppId());
        map.put(RSA_PRIVATE_KEY, aliPayParamModel.getAliPayPrivateKey());
        map.put(RSA_PUBLIC_KEY, aliPayParamModel.getAliPayPublicKey());
        map.put(DesignConstant.SDONGPO_SIGN, Signature.getSHA256(Signature.mapToAsciiString(map)));
        return map;
    }


}