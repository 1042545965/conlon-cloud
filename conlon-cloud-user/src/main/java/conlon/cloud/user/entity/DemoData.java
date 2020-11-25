package conlon.cloud.user.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 测试 excel 大容量导入 导出实体类
 * </p>
 *
 * @author conlon
 * @since 2020-11-25
 */
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    @ApiModelProperty(value = "字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    @ApiModelProperty(value = "日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    @ApiModelProperty(value = "数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    @ApiModelProperty(value = "忽略这个字段")
    private String ignore;
}