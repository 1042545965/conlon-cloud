package conlon.cloud.design.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import conlon.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 第三方支付参数
 * </p>
 *
 * @author conlon
 * @since 2021-03-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("shp_payment_config")
public class PaymentConfigEntity extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 5388093863937502536L;
    /**
     * 租户id
     */
    @NotNull(message = "租户id不能为空")
    @ApiModelProperty(value = "租户id")
    private Long tenantId;


    /**
     * 银联支付配置(70)
     */
    @NotBlank(message = "银联支付配置(70)不能为空")
    @ApiModelProperty(value = "银联支付配置(70)")
    private String linkType;


    /**
     * 第三方编码(10:微信 , 20:支付宝 , 30:银联商务)
     */
    @NotBlank(message = "第三方编码(10:微信 , 20:支付宝 , 30:银联商务)不能为空")
    @ApiModelProperty(value = "第三方编码(10:微信 , 20:支付宝 , 30:银联商务)")
    private String payType;


    /**
     * 适用范围0门店 1商城
     */
    @NotBlank(message = "适用范围0门店 1商城不能为空")
    @ApiModelProperty(value = "适用范围0门店 1商城")
    private String scopeApply;


    /**
     * 门店id
     */
    @NotNull(message = "门店id不能为空")
    @ApiModelProperty(value = "门店id")
    private Long shopId;


    /**
     * 网关对应(租户编码)
     */
    @NotBlank(message = "网关对应(租户编码)不能为空")
    @ApiModelProperty(value = "网关对应(租户编码)")
    private String projectCode;


    /**
     * 网关对应门店id
     */
    @NotBlank(message = "网关对应门店id不能为空")
    @ApiModelProperty(value = "网关对应门店id")
    private String siteId;


    /**
     * 网关对应
     */
    @NotNull(message = "网关对应不能为空")
    @ApiModelProperty(value = "网关对应")
    private Integer type;


    /**
     * 第三方支付参数JSON
     */
    @NotBlank(message = "第三方支付参数JSON不能为空")
    @ApiModelProperty(value = "第三方支付参数JSON")
    private String thirdParam;


}