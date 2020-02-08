package conlon.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import conlon.cloud.auth.entity.SysUser;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表sys_user 服务类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * @Description: 根据登陆名称查询用户密码
     * @Author: Mr conlon
     * @create 2020/1/28 21:38
     */
    String getPassword(String username);

    /**
     * @Description: 校验用户状态
     * @Author: Mr conlon
     * @create 2020/1/28 21:38
     */
    int checkUserStatus(String username);

    /**
     * @Description: 获取用户角色
     * @Author: Mr conlon
     * @create 2020/1/28 21:38
     */
    Set<String> getRoleSet(String username);

    /**
     * @Description: 获取用户权限
     * @Author: Mr conlon
     * @create 2020/1/28 21:39
     */
    Set<String> getPermissionSet(String username);

    /**
     * @Description: 创建用户
     * @Author: Mr conlon
     * @create 2020/1/29 11:51
     */
    void createUser(SysUser sysUser, List<Long> roles, List<Long> menus) throws Exception;

    void isSave(SysUser sysUser);
}
