package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.auth.dao.SysUserMenuDao;
import conlon.cloud.auth.entity.SysUserMenu;
import conlon.cloud.auth.service.SysUserMenuService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限关联sys_user_menu 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Service
public class SysUserMenuServiceImpl extends ServiceImpl<SysUserMenuDao, SysUserMenu> implements SysUserMenuService {


    @Override
    public List<UserMenuDto> getSysUserMenuList(String userName) {
        return baseMapper.getSysUserMenuList(userName);
    }
}
