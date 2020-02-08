package conlon.cloud.auth.controller;


import conlon.cloud.auth.entity.SysUser;
import conlon.cloud.common.enums.ConstantEnum;
import conlon.cloud.auth.service.SysUserService;
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
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 新增用户
     */
    @PostMapping(value = "/createUser")
    public Result createUser(SysUser sysUser,
            @RequestParam("roles") List<Long> roles,
            @RequestParam("menus") List<Long> menus) {
        sysUser.setPassword(MD5Utils.MD5Lower(ConstantEnum.DEFAULT_PASSWORD.getCode()));
        try {
            sysUserService.createUser(sysUser, roles, menus);
        } catch (Exception e) {
            return Result.fail();
        }
        return Result.ok();
    }

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        String realPassword = sysUserService.getPassword(username);
        if (realPassword == null) {
            return Result.build("401", "用户名错误");
        } else if (!realPassword.equals(MD5Utils.MD5(password))) {
            return Result.build("401", "密码错误");
        } else {
            return Result.ok(JWTUtil.createToken(username));
        }
    }


    @RequestMapping(path = "/unauthorized/{message}")
    public Result unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Result.build("401", message);
    }


    /**
     * 拥有 list 权限可以获取数据  "user", "admin", "plain_admin" 三个角色
     */
    @GetMapping("/getUserList")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin", "plain_admin"})
    @RequiresPermissions("system:menu:list")
    public Result getUserList() {
        return Result.ok(sysUserService.list());
    }

    @GetMapping("/getUserListByLevel")
    @RequiresRoles("plain_admin")
    @RequiresPermissions("system:menu:list")
    public Result getUserListByLevel() {
        return Result.ok(sysUserService.list().get(0));
    }
}