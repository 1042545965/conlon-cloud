package conlon.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.auth.dao.SysMenuDao;
import conlon.cloud.auth.entity.SysMenu;
import conlon.cloud.auth.service.SysMenuService;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表sys_menu 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Override
    public Map<Long, String> getMenuCodeMap() {
        return this.list()
                .stream()
                .collect(
                        Collectors.toMap(SysMenu::getId, SysMenu::getMenuCode)
                );
    }
}
