package conlon.cloud.user.controller;


import conlon.cloud.api.connect.AuthApi;
import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.api.exception.InternalApiException;
import conlon.cloud.common.enums.ConstantEnum;
import conlon.cloud.common.jwt.JWTUtil;
import conlon.cloud.common.utils.Result;
import conlon.cloud.user.service.UserInfoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author conlonx
 * @since 2019-08-28
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthApi authApi;

    /**
     * 测试获取
     */
    @GetMapping(value = "/getUserName")
    @RequiresPermissions("system:menu:list")
    public String getUserName() {
        return "userName";
    }


    /**
     * 获取支付编码
     */
    @GetMapping(value = "/getUserList")
    @RequiresPermissions("system:menu:edit")
    public Result getUserList() {
        return Result.ok(userInfoService.list());
    }

    /**
     * 获取支付编码
     */
    @GetMapping(value = "/getSysUserMenuList")
    @RequiresPermissions("system:menu:edit")
    public Result getSysUserMenuList(HttpServletRequest request) {
        // jwt 获取userName
        String username = JWTUtil.getUsername(request.getHeader(ConstantEnum.HEADER_TOKEN.getCode()));
        try {
            List<UserMenuDto> sysUserMenuList = authApi.getSysUserMenuList(username);
            return Result.ok(sysUserMenuList);
        } catch (InternalApiException e) {
            return Result.build(e.getResultCode(), e.getResultMsg());
        }
    }


    /**
     * JSR 303 参数校验
     */
    @GetMapping(value = "/validateParameter")
    @RequiresPermissions("system:menu:edit")
    public Result validateParameter(@Length(min = 1) @NotBlank(message = "用户名不能为空") String userName) {
//        userInfoService.validateParameter(userName);
        try {
            authApi.validateParameter(userName);
            return Result.ok();
        }catch (InternalApiException e){
            return Result.build(e.getResultCode() ,  e.getResultMsg());
        }
    }

}