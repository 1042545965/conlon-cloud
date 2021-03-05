package conlon.cloud.design.service;

import conlon.cloud.design.from.ThirdPaymentFrom;

/**
 * <p>
 * 第三方支付参数 服务类
 * </p>
 *
 * @author conlon
 * @since 2021-03-04
 */
public interface PaymentConfigService {

    /**
     * 新增支付配置
     */
    void configInsert(ThirdPaymentFrom thirdPaymentFrom);
}
