package conlon.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关联sys_menu_role
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu_role")
public class SysMenuRole implements Serializable {

    private static final long serialVersionUID = -125884940490124587L;
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
     * 权限编码(为了查询方便,适当冗余)
     */
    @ApiModelProperty(value = "权限编码(为了查询方便,适当冗余)")
    private String menuCode;


}