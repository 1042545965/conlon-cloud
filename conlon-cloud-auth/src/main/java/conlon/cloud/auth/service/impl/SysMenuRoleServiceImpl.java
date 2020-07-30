package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.auth.dao.SysMenuRoleDao;
import conlon.cloud.auth.entity.SysMenuRole;
import conlon.cloud.auth.event.SysMenuRoleUpdateEvent;
import conlon.cloud.auth.service.SysMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
@Slf4j
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleDao, SysMenuRole> implements SysMenuRoleService {

    @Autowired
    ApplicationContext applicationContext;
    @Override
    public void testEventPay(SysMenuRole sysMenuRole) {
        log.info("==================业务代码开始==================");

        log.info("==================业务代码结束==================");

        applicationContext.publishEvent(new SysMenuRoleUpdateEvent(sysMenuRole));
    }
}
