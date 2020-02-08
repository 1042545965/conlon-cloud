package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.auth.dao.SysUserRoleDao;
import conlon.cloud.auth.entity.SysUserRole;
import conlon.cloud.auth.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联sys_user_role 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

}
