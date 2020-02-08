package conlon.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import conlon.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表bs_user
 * </p>
 *
 * @author conlon
 * @since 2020-02-02
 */
@Data
@Accessors(chain = true)
@TableName("bs_user_info")
public class UserInfo extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -6770461207326005883L;
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