package conlon.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import conlon.cloud.auth.entity.SysMenu;
import java.util.Map;

/**
 * <p>
 * 权限表sys_menu 服务类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface SysMenuService extends IService<SysMenu> {

    Map<Long, String> getMenuCodeMap();
}
