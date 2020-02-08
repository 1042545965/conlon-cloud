package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.auth.dao.SysMenuRoleDao;
import conlon.cloud.auth.entity.SysMenuRole;
import conlon.cloud.auth.service.SysMenuRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联sys_menu_role 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleDao, SysMenuRole> implements SysMenuRoleService {

}
