package conlon.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import conlon.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表sys_menu
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -4784280561948978327L;
    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String menuName;


    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    private String menuCode;


    /**
     * 访问连接
     */
    @ApiModelProperty(value = "访问连接")
    private String menuUrl;


}