package conlon.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关联sys_user_role
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
public class SysUserRole implements Serializable {


    private static final long serialVersionUID = -5730720861147244967L;
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;


    /**
     * 角色编码(查询方便适当冗余)
     */
    @ApiModelProperty(value = "角色编码(查询方便适当冗余)")
    private String roleCode;


}