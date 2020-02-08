package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.auth.dao.SysUserDao;
import conlon.cloud.auth.entity.SysUser;
import conlon.cloud.auth.entity.SysUserMenu;
import conlon.cloud.auth.entity.SysUserRole;
import conlon.cloud.auth.service.SysMenuService;
import conlon.cloud.auth.service.SysRoleService;
import conlon.cloud.auth.service.SysUserMenuService;
import conlon.cloud.auth.service.SysUserRoleService;
import conlon.cloud.auth.service.SysUserService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 * 用户表sys_user 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    // 用户权限关联表
    @Autowired
    private SysUserMenuService sysUserMenuService;
    // 用户角色关联表
    @Autowired
    private SysUserRoleService sysUserRoleService;
    // 角色表
    @Autowired
    private SysRoleService sysRoleService;
    // 权限表
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public String getPassword(String username) {
        return baseMapper.getPassword(username);
    }

    @Override
    public int checkUserStatus(String username) {
        return baseMapper.checkUserStatus(username);
    }

    @Override
    public Set<String> getRoleSet(String username) {
        return baseMapper.getRoleSet(username);
    }

    @Override
    public Set<String> getPermissionSet(String username) {
        return baseMapper.getPermissionSet(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(SysUser sysUser, List<Long> roles, List<Long> menus) throws Exception {
        // 保存用户
        this.save(sysUser);
        Long userId = sysUser.getId();
        // 保存用户角色关联
        Map<Long, String> roleCodeMap = sysRoleService.getRoleCodeMap();
        List<SysUserRole> userRoles = roles.stream()
                .map(t -> new SysUserRole()
                        .setUserId(userId)
                        .setRoleId(t)
                        .setRoleCode(roleCodeMap.get(t))
                )
                .collect(Collectors.toList());
        sysUserRoleService.saveBatch(userRoles);
        // 保存用户权限关联
        Map<Long, String> menuCodeMap = sysMenuService.getMenuCodeMap();
        List<SysUserMenu> userMenus = menus.stream()
                .map(t -> new SysUserMenu()
                        .setMenuId(t)
                        .setUserId(userId)
                        .setMenuCode(menuCodeMap.get(t))
                )
                .collect(Collectors.toList());
        sysUserMenuService.saveBatch(userMenus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void isSave(SysUser sysUser){
        this.save(sysUser);
        int i= 1/0;
    }
}
