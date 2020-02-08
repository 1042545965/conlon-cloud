package conlon.cloud.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.auth.entity.SysUser;
import conlon.cloud.auth.service.SysUserMenuService;
import conlon.cloud.auth.service.SysUserService;
import conlon.cloud.common.enums.ConstantEnum;
import conlon.cloud.common.jwt.JWTUtil;
import conlon.cloud.common.utils.Result;
import conlon.cloud.common.utils.md5.MD5Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author conlonx
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/authApi")
public class AuthApiController {

    @Autowired
    private SysUserMenuService sysUserMenuService;

    /**
     * 新增用户
     */
    @GetMapping(value = "/getSysUserMenuList")
    public List<UserMenuDto> getSysUserMenuList(@RequestParam("userName") String userName) {
        return sysUserMenuService.getSysUserMenuList(userName);
    }

}