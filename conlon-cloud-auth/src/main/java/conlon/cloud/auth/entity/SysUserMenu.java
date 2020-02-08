package conlon.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限关联sys_user_menu
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_menu")
public class SysUserMenu implements Serializable {

    private static final long serialVersionUID = 2621350593949258351L;
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Long menuId;


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