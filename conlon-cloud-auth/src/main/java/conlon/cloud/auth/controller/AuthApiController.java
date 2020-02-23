package conlon.cloud.auth.controller;


import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.auth.service.SysUserMenuService;
import conlon.cloud.common.enums.ResponseCode;
import conlon.cloud.api.exception.InternalApiException;

import java.util.List;

import javax.validation.constraints.NotBlank;
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
@RequestMapping("/authApi")
public class AuthApiController {

    @Autowired
    private SysUserMenuService sysUserMenuService;

    /**
     * 新增用户
     */
    @GetMapping(value = "/getSysUserMenuList")
    public List<UserMenuDto> getSysUserMenuList(@RequestParam("userName") String userName) {
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = 1/0;
        if (true){
            throw new InternalApiException(ResponseCode.SERVICE_INVOKING_ERROR.getResultCode(),
                    ResponseCode.SERVICE_INVOKING_ERROR.getResultMsg());
        }
        return sysUserMenuService.getSysUserMenuList(userName);
    }


    /**
     * @return
     * @Description:
     * @Author:Mr conlon
     * @create 20202/18 22:48
     */
    @GetMapping(value = "/validateParameter")
    public void validateParameter(@Length(min = 10 , message = "用户名大于10") @NotBlank(message = "用户名不能为空") String userName) {
        throw new InternalApiException(ResponseCode.SERVICE_INVOKING_ERROR.getResultCode(),
                ResponseCode.SERVICE_INVOKING_ERROR.getResultMsg());
    }

}