package conlon.cloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import conlon.cloud.user.dao.UserInfoDao;
import conlon.cloud.user.entity.UserInfo;
import conlon.cloud.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表bs_user 服务实现类
 * </p>
 *
 * @author conlon
 * @since 2020-02-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {


}
