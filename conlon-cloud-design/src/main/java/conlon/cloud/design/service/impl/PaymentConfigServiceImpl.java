package conlon.cloud.design.service.impl;

import conlon.cloud.design.abs.AbstractFactory;
import conlon.cloud.design.abs.BuildPay;
import conlon.cloud.design.abs.SendPay;
import conlon.cloud.design.constant.DesignConstant;
import conlon.cloud.design.from.ThirdPaymentFrom;
import conlon.cloud.design.pay.alipay.AliPayFactory;
import conlon.cloud.design.service.PaymentConfigService;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方支付参数 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2021-03-04
 */
@Service
public class PaymentConfigServiceImpl implements PaymentConfigService {


    @Override
    public void configInsert(ThirdPaymentFrom thirdPaymentFrom) {
        // 思路 变化的和不变的

        // 写入第三方支付端  在这里是需要抽象的
        // 写入又分 两步  构建参数 进行签名 发起支付 处理返回数据
        // 发起写入
        AbstractFactory abs = new AliPayFactory();
        // 获取参数
        List<String> scopeApplyList = thirdPaymentFrom.getScopeApplyList();
        List<Long> shopIdList = thirdPaymentFrom.getShopIdList();
        for (Long shopId : shopIdList) {
            for (String scopeApply : scopeApplyList) {
                BuildPay build = abs.createBuild();
                Map<String, String> sendMap = build.buildModel(thirdPaymentFrom, shopId, scopeApply);
                SendPay send = abs.createSend();
                send.sendMessage(sendMap , DesignConstant.CONFIG_URL);
            }
        }
        // 不变的有 校验 保存

    }

}
