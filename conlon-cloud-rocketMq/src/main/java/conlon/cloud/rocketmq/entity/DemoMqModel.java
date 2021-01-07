package conlon.cloud.rocketmq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author conlon
 * @Description mq
 * @Date 2021/1/6 19:47
 **/
@Data
@Accessors(chain = true)
public class DemoMqModel {

    @ApiModelProperty(value = "字符串标题")
    private String string;

    @ApiModelProperty(value = "日期标题")
    private Date date;

    @ApiModelProperty(value = "数字标题")
    private Double doubleData;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;
}