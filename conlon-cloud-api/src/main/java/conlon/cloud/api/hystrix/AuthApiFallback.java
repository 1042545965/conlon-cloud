package conlon.cloud.api.hystrix;

import conlon.cloud.api.connect.AuthApi;
import conlon.cloud.api.constant.Canstant;
import conlon.cloud.api.dto.UserMenuDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class AuthApiFallback implements AuthApi {

    @Override
    public List<UserMenuDto> getSysUserMenuList(String userName) {
        ArrayList<UserMenuDto> userMenuDtos = new ArrayList<>();
        userMenuDtos.add(new UserMenuDto().setMenuCode(Canstant.CLOUD_AUTH_ERROR_MESSAGE));
        return userMenuDtos;
    }
}
