package conlon.cloud.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.auth.entity.SysUserMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户权限关联sys_user_menu Mapper 接口
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface SysUserMenuDao extends BaseMapper<SysUserMenu> {


    List<UserMenuDto> getSysUserMenuList(@Param("userName") String userName);
}