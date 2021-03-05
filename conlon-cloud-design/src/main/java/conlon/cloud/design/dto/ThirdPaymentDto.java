package conlon.cloud.design.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdPaymentDto implements Serializable {

    private static final long serialVersionUID = -2166152158650071037L;
    @NotBlank(message = "银联支付配置(70)不能为空")
    @ApiModelProperty(value = "银联支付配置(70)")
    private String linkType;

    @NotBlank(message = "第三方编码(10:微信 , 20:支付宝 , 30:银联商务)不能为空")
    @ApiModelProperty(value = "第三方编码(10:微信 , 20:支付宝 , 30:银联商务)")
    private String payType;

    @Size(min = 1, max = 3, message = "请选择正确的使用范围")
    @ApiModelProperty(value = "适用范围 0门店 1商城")
    private List<String> scopeApplyList;

    @Size(min = 1, max = 999, message = "请选择门店")
    @ApiModelProperty(value = "门店id集合")
    private List<Long> shopIdList;

    /**
     *  第三方支付所需要的参数
     */
    @NotBlank(message = "银联给的来源编号不能为空")
    @ApiModelProperty(value = "银联给的来源编号")
    private String msgSrcId;

    @NotBlank(message = "通讯密钥不能为空")
    @ApiModelProperty(value = "通讯密钥")
    private String md5key;

    @NotBlank(message = "银联给的商户号不能为空")
    @ApiModelProperty(value = "银联给的商户号")
    private String merchantCode;

    @NotBlank(message = "银联给的终端号不能为空")
    @ApiModelProperty(value = "银联给的终端号")
    private String terminalCode;

    @NotBlank(message = "机构商户号不能为空")
    @ApiModelProperty(value = " 机构商户号")
    private String instMid;

    @NotBlank(message = "银联提供的appid不能为空")
    @ApiModelProperty(value = " 银联提供的appId")
    private String appId;

    @NotBlank(message = "银联提供的 appKey 不能为空")
    @ApiModelProperty(value = " 银联提供的 appKey")
    private String appKey;

}
