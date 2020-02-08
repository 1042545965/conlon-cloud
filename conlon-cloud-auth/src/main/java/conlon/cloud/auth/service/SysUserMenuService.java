package conlon.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.auth.entity.SysUserMenu;
import java.util.List;

/**
 * <p>
 * 用户权限关联sys_user_menu 服务类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface SysUserMenuService extends IService<SysUserMenu> {


    /**
     * @Description: 根据用户名称查询权限
     * @param userName 用户登陆名称
     * @Author: Mr conlon
     * @return
     * @create 2020/2/5 0:26
     */
    List<UserMenuDto> getSysUserMenuList(String userName);
}
