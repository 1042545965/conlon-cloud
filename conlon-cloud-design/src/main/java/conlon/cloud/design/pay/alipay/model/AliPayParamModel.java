package conlon.cloud.design.pay.alipay.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付包支付接收参数模型
 */
@Data
@Accessors(chain = true)
public class AliPayParamModel implements Serializable {

    private static final long serialVersionUID = -1746348484500128686L;

    @ApiModelProperty(value = "支付的 appId ")
    private String aliPayAppId;

    @ApiModelProperty(value = "支付私钥")
    private String aliPayPrivateKey;

    @ApiModelProperty(value = "支付公钥")
    private String aliPayPublicKey;



}