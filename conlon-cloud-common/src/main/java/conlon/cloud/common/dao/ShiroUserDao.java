package conlon.cloud.common.dao;

import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表sys_user Mapper 接口
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
public interface ShiroUserDao {


    @Select("SELECT a.`password` FROM `sys_user` a WHERE a.user_name = #{username}")
    String getPassword(@Param("username") String username);

    @Select("SELECT a.`status` FROM `sys_user` a WHERE a.user_name = #{username}")
    int checkUserStatus(String username);

    @Select("SELECT b.role_code FROM sys_user a LEFT JOIN sys_user_role b ON a.id = b.user_id WHERE a.user_name = #{username}")
    Set<String> getRoleSet(@Param("username") String username);

    @Select("SELECT b.menu_code FROM sys_user a LEFT JOIN sys_user_menu b ON a.id = b.user_id WHERE a.user_name = #{username}")
    Set<String> getPermissionSet(@Param("username") String username);

}