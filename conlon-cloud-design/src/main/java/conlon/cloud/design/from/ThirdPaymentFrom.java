package conlon.cloud.design.from;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThirdPaymentFrom implements Serializable {


    private static final long serialVersionUID = 8230769429697193795L;

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

    @ApiModelProperty(value = "第三方支付参数JSON")
    private String thirdParam;

}
