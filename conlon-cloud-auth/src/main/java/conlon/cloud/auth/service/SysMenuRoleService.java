package conlon.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import conlon.cloud.auth.entity.SysMenuRole;

/**
 * <p>
 * 角色权限关联sys_menu_role 服务类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface SysMenuRoleService extends IService<SysMenuRole> {

    /**
     * @param sysMenuRole 权限model
     * @Author:Mr conlon
     * @create 2020/7/0 10:21
     */
    public void testEventPay(SysMenuRole sysMenuRole);

}
