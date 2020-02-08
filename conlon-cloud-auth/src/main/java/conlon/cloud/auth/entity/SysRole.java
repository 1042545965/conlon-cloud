package conlon.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import conlon.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表sys_role
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 6136659636811206702L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;


    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

}