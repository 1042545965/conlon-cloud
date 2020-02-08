package conlon.cloud.common.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表sys_menu
 * </p>
 *
 * @author conlon
 * @since 2020-01-27
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;


    /**
     * 创建人ID
     */
    @NotNull(message = "创建人ID不能为空")
    @ApiModelProperty(value = "创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createdUserId;


    /**
     * 创建人姓名
     */
    @NotBlank(message = "创建人姓名不能为空")
    @ApiModelProperty(value = "创建人姓名")
    @TableField(fill = FieldFill.INSERT)
    private String createdUserName;


    /**
     * 最后修改时间
     */
    @NotNull(message = "最后修改时间不能为空")
    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;


    /**
     * 最后修改人ID
     */
    @NotNull(message = "最后修改人ID不能为空")
    @ApiModelProperty(value = "最后修改人ID")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUserId;


    /**
     * 最后一次修改用户名称
     */
    @NotBlank(message = "最后一次修改用户名称不能为空")
    @ApiModelProperty(value = "最后一次修改用户名称")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifiedUserName;


    /**
     * 删除状态1表示删除，0表示未删除
     */
    @NotNull(message = "删除状态1表示删除，0表示未删除不能为空")
    @ApiModelProperty(value = "删除状态1表示删除，0表示未删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;


    /**
     * 状态（0正常，1停用）
     */
    @NotNull(message = "状态（0正常，1停用）不能为空")
    @ApiModelProperty(value = "状态（0正常，1停用）")
    private Integer status;


    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    @ApiModelProperty(value = "备注")
    private String remarks;


}