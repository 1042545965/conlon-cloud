import conlon.cloud.auth.AuthApplication;
import conlon.cloud.auth.dao.SysUserDao;
import conlon.cloud.auth.entity.SysUser;
import conlon.cloud.auth.service.SysUserService;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class AuthTest {


    @Autowired
    private SysUserService sysUserService;
    @Resource
    private SysUserDao sysUserDao;

    @Test
    public void test01() {
        List<SysUser> list = sysUserService.list();
        System.out.println(list);
    }

    @Test
    public void test03() {
        sysUserService.isSave(new SysUser().setPassword("aaaaa"));
    }
}