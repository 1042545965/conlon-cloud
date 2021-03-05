package conlon.cloud.design.pay.alipay;

import conlon.cloud.design.abs.AbstractFactory;
import conlon.cloud.design.abs.BuildPay;
import conlon.cloud.design.abs.SendPay;
import java.io.Serializable;

/**
 * @program: conlon-cloud abstractFactory.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-03-04 21:29
 */
public class AliPayFactory extends AbstractFactory implements Serializable {

    private static final long serialVersionUID = -3343767413286897884L;

    @Override
    public BuildPay createBuild() {
        return new BuildAliModel();
    }

    @Override
    public SendPay createSend() {
        return null;
    }
}
