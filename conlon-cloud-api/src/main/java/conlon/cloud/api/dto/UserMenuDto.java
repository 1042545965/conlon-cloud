package conlon.cloud.api.dto;

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
public class UserMenuDto implements Serializable {

    private static final long serialVersionUID = 2485578783562189714L;
    /**
     * id
     */
    private Long id;


    /**
     * 权限id
     */
    private Long menuId;


    /**
     * 用户id
     */
    private Long userId;


    /**
     * 权限编码(为了查询方便,适当冗余)
     */
    private String menuCode;


}