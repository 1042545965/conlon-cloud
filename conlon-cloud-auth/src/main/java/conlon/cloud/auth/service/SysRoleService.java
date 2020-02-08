package conlon.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import conlon.cloud.auth.entity.SysRole;
import java.util.Map;

/**
 * <p>
 * 角色表sys_role 服务类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface SysRoleService extends IService<SysRole> {

    Map<Long, String> getRoleCodeMap();
}
