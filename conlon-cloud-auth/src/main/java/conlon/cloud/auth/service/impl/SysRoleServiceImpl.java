package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.auth.dao.SysRoleDao;
import conlon.cloud.auth.entity.SysRole;
import conlon.cloud.auth.service.SysRoleService;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表sys_role 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Override
    public Map<Long, String> getRoleCodeMap() {
        return this.list()
                .stream()
                .collect(Collectors.toMap(SysRole::getId , SysRole::getRoleCode));
    }
}
