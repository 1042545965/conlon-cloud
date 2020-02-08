package conlon.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import conlon.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表sys_user
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -8277399771606221401L;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;


    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private String userCode;


    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

}