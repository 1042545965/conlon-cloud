package conlon.cloud.design.abs;

import java.io.Serializable;

/**
 * @program: conlon-cloud abstractFactory.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-03-04 21:29
 */
public class WeChatPayFactory extends AbstractFactory implements Serializable {

    private static final long serialVersionUID = -3343767413286897884L;

    @Override
    public BuildPay createBuild() {
        return new BuildWeChatModel();
    }

    @Override
    public SendPay createSend() {
        return new SendWeChatMessage();
    }
}
