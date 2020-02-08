package conlon.cloud.api.connect;

import conlon.cloud.api.constant.Canstant;
import conlon.cloud.api.dto.UserMenuDto;
import conlon.cloud.api.hystrix.AuthApiFallback;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 用户登录验证接口
 * 检验登录状态
 * 获取用户数据
 * 获取权限等接口
 *
 * @author yuxue
 * @date 2019-08-08
 */
@FeignClient(name = Canstant.CLOUD_AUTH , fallback = AuthApiFallback.class)
public interface AuthApi {

    /**
     * 获取用户登陆权限
     */
    @RequestMapping(value = "/authApi/getSysUserMenuList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMenuDto> getSysUserMenuList(@RequestParam("userName") String userName);

}
