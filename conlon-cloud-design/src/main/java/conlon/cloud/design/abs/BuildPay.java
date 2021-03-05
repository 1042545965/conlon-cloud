package conlon.cloud.design.abs;

import conlon.cloud.design.from.ThirdPaymentFrom;
import conlon.cloud.design.pay.SendModel;
import java.util.Map;

/**
 * @program: conlon-cloud abstractFactory.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-03-04 21:29
 */
public abstract class BuildPay {

    /**
     * 构建模型
     */
    public abstract Map<String, String> buildModel(ThirdPaymentFrom thirdPaymentFrom, Long shopId, String scopeApply);

}
